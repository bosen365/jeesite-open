package com.jeesite.modules.gen.utils;	
	
import com.jeesite.common.beetl.BeetlUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.IOUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JaxbMapper;	
import com.jeesite.modules.gen.entity.GenTable;	
import com.jeesite.modules.gen.entity.GenTableColumn;	
import com.jeesite.modules.gen.entity.config.GenConfig;	
import com.jeesite.modules.gen.entity.config.GenTemplate;	
import com.jeesite.modules.gen.entity.config.GenTplCategory;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.File;	
import java.io.IOException;	
import java.io.InputStream;	
import java.util.Date;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.hyperic.sigar.Tcp;	
import org.hyperic.sigar.shell.ShellCommandInitException;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.core.io.Resource;	
	
public class GenUtils {	
   private static Logger logger = LoggerFactory.getLogger(GenUtils.class);	
	
   public static String generateToFile(GenTemplate tpl, Map model, GenTable genTable) {	
      if (StringUtils.isBlank(genTable.getGenBaseDir())) {	
         genTable.setGenBaseDir(FileUtils.getProjectPath());	
      }	
	
      StringBuilder var10000 = (new StringBuilder()).insert(0, genTable.getGenBaseDir()).append(File.separator);	
      String var10001 = BeetlUtils.renderFromString((new StringBuilder()).insert(0, tpl.getFilePath()).append("/").toString(), model);	
      String[] var10002 = new String[3];	
      boolean var10004 = true;	
      var10002[0] = "//";	
      var10002[1] = "/";	
      var10002[2] = ".";	
      String[] var10003 = new String[3];	
      boolean var10005 = true;	
      var10003[0] = File.separator;	
      var10003[1] = File.separator;	
      var10003[2] = File.separator;	
      String a = var10000.append(StringUtils.replaceEach(var10001, var10002, var10003)).append(BeetlUtils.renderFromString(tpl.getFileName(), model)).toString();	
      logger.debug((new StringBuilder()).insert(0, "Generate File ==> \r\n").append(a).toString());	
      String a = BeetlUtils.renderFromString(StringUtils.trimToEmpty(tpl.getContent()), model);	
      logger.debug((new StringBuilder()).insert(0, "File Content ==> \r\n").append(a).toString());	
      if ("2".equals(genTable.getGenFlag())) {	
         if ("1".equals(genTable.getReplaceFile())) {	
            FileUtils.deleteFile(a);	
         }	
	
         if (FileUtils.createFile(a)) {	
            FileUtils.writeToFile(a, a, true);	
            logger.debug((new StringBuilder()).insert(0, "File Create ==> ").append(a).toString());	
            return (new StringBuilder()).insert(0, "生成成功：").append(a).append("<br/>").toString();	
         } else {	
            logger.debug((new StringBuilder()).insert(0, "File Extents ==> ").append(a).toString());	
            return (new StringBuilder()).insert(0, "文件已存在：").append(a).append("<br/>").toString();	
         }	
      } else {	
         return (new StringBuilder()).insert(0, a).append("<br/>").toString();	
      }	
   }	
	
   public static Map getDataModel(GenTable genTable) {	
      if (genTable.getParentExists() && genTable.getParent() != null) {	
         GenTable a;	
         genTable.setPackageName((a = genTable.getParent()).getPackageName());	
         genTable.setModuleName(a.getModuleName());	
         genTable.setSubModuleName(a.getSubModuleName());	
         genTable.setFunctionName(a.getFunctionName());	
         genTable.setFunctionNameSimple(a.getFunctionNameSimple());	
         genTable.setFunctionAuthor(a.getFunctionAuthor());	
         genTable.setGenBaseDir(a.getGenBaseDir());	
         genTable.setOptions(a.getOptions());	
         genTable.setReplaceFile(a.getReplaceFile());	
         genTable.setGenFlag(a.getGenFlag());	
      }	
	
      GenTable a = MapUtils.newHashMap();	
      a.put("packageName", StringUtils.lowerCase(genTable.getPackageName()));	
      a.put("lastPackageName", StringUtils.substringAfterLast((String)a.get("packageName"), "."));	
      a.put("moduleName", StringUtils.lowerCase(genTable.getModuleName()));	
      a.put("subModuleName", StringUtils.lowerCase(genTable.getSubModuleName()));	
      a.put("className", StringUtils.uncapitalize(genTable.getClassName()));	
      a.put("ClassName", StringUtils.capitalize(genTable.getClassName()));	
      a.put("functionName", genTable.getFunctionName());	
      a.put("functionNameSimple", genTable.getFunctionNameSimple());	
      a.put("functionAuthor", StringUtils.defaultIfBlank(genTable.getFunctionAuthor(), UserUtils.getUser().getUserName()));	
      a.put("functionVersion", DateUtils.getDate());	
      a.put("urlPrefix", StringUtils.replace(a.get("moduleName") + (StringUtils.isNotBlank(genTable.getSubModuleName()) ? (new StringBuilder()).insert(0, "/").append(StringUtils.lowerCase(genTable.getSubModuleName())).toString() : "") + "/" + a.get("className"), ".", "/"));	
      a.put("viewPrefix", a.get("urlPrefix"));	
      a.put("permissionPrefix", StringUtils.replace(a.get("moduleName") + (StringUtils.isNotBlank(genTable.getSubModuleName()) ? (new StringBuilder()).insert(0, ":").append(StringUtils.lowerCase(genTable.getSubModuleName())).toString() : "") + ":" + a.get("className"), ".", ":"));	
      a.put("Global", Global.getInstance());	
      a.put("dbName", Global.getDbName());	
      a.put("table", genTable);	
      String a = "";	
      if (genTable != null && genTable.getPkList() != null && genTable.getPkList().size() == 1) {	
         a = ((GenTableColumn)genTable.getPkList().get(0)).getSimpleAttrName();	
      }	
	
      a.put("pkField", a);	
      return a;	
   }	
	
   public static List getTemplateList(GenConfig config, String category, boolean isChildTable) {	
      List a = ListUtils.newArrayList();	
      if (config != null && config.getTplCategoryList() != null && category != null) {	
         Iterator var4 = config.getTplCategoryList().iterator();	
	
         while(var4.hasNext()) {	
            GenTplCategory a = (GenTplCategory)var4.next();	
            if (category.equals(a.getValue())) {	
               List a = null;	
               if ((!isChildTable ? (a = a.getTemplate()) : (a = a.getChildTableTemplate())) != null) {	
                  Iterator var7 = a.iterator();	
	
                  while(var7.hasNext()) {	
                     String a;	
                     if (StringUtils.startsWith(a = (String)var7.next(), GenTplCategory.CATEGORY_REF)) {	
                        a.addAll(getTemplateList(config, StringUtils.replace(a, GenTplCategory.CATEGORY_REF, ""), false));	
                     } else {	
                        GenTemplate a;	
                        if ((a = (GenTemplate)fileToObject(a, GenTemplate.class)) != null) {	
                           a.add(a);	
                        }	
                     }	
                  }	
               }	
               break;	
            }	
         }	
      }	
	
      return a;	
   }	
	
   public static GenConfig getConfig() {	
      GenConfig a;	
      if ((a = (GenConfig)fileToObject("config-custom.xml", GenConfig.class)) == null) {	
         a = (GenConfig)fileToObject("config.xml", GenConfig.class);	
      }	
	
      return a;	
   }	
	
   public static Object fileToObject(String fileName, Class clazz) {	
      Resource a;	
      if ((a = ResourceUtils.getResource((new StringBuilder()).insert(0, "/templates/modules/gen/").append(fileName).toString())).exists()) {	
         InputStream a = null;	
	
         Object var6;	
         try {	
            try {	
               var6 = JaxbMapper.fromXml(IOUtils.toString(a = a.getInputStream(), "UTF-8"), clazz);	
            } catch (IOException var10) {	
               throw ExceptionUtils.unchecked(var10);	
            }	
         } catch (Throwable var11) {	
            IOUtils.closeQuietly(a);	
            throw var11;	
         }	
	
         IOUtils.closeQuietly(a);	
         return var6;	
      } else {	
         return null;	
      }	
   }	
	
   public static void initColumnField(GenTable genTable) {	
      int a = genTable.getColumnList().size();	
      int a = genTable.getIsTreeEntity();	
      Iterator var3 = genTable.getColumnList().iterator();	
	
      label228:	
      while(true) {	
         for(Iterator var10000 = var3; var10000.hasNext(); var10000 = var3) {	
            GenTableColumn a;	
            if (!StringUtils.isNotBlank((a = (GenTableColumn)var3.next()).getId())) {	
               a.setShowType("input");	
               if (StringUtils.isBlank(a.getComments())) {	
                  a.setComments(a.getColumnName());	
               }	
	
               GenTableColumn var8;	
               if (!StringUtils.startsWithIgnoreCase(a.getColumnType(), "date") && !StringUtils.startsWithIgnoreCase(a.getColumnType(), "timestamp")) {	
                  if (!StringUtils.startsWithIgnoreCase(a.getColumnType(), "float") && !StringUtils.startsWithIgnoreCase(a.getColumnType(), "double")) {	
                     if (!StringUtils.containsIgnoreCase(a.getColumnType(), "int") && !StringUtils.startsWithIgnoreCase(a.getColumnType(), "number") && !StringUtils.startsWithIgnoreCase(a.getColumnType(), "decimal") && !StringUtils.startsWithIgnoreCase(a.getColumnType(), "numeric")) {	
                        a.setAttrType(String.class.getSimpleName());	
                     } else {	
                        String[] a;	
                        if ((a = StringUtils.split(StringUtils.substringBetween(a.getColumnType(), "(", ")"), ",")) != null && a.length == 2 && Integer.parseInt(a[1]) > 0) {	
                           a.setAttrType(Double.class.getSimpleName());	
                           a.getOptionMap().put("fieldValid", "number");	
                        } else if (a != null && a.length >= 1 && Integer.parseInt(a[0]) <= 10) {	
                           a.setAttrType(Integer.class.getSimpleName());	
                           a.getOptionMap().put("fieldValid", "digits");	
                        } else {	
                           a.setAttrType(Long.class.getSimpleName());	
                           a.getOptionMap().put("fieldValid", "digits");	
                        }	
                     }	
	
                     var8 = a;	
                  } else {	
                     var8 = a;	
                     a.setAttrType(Double.class.getSimpleName());	
                     a.getOptionMap().put("fieldValid", "number");	
                  }	
               } else {	
                  var8 = a;	
                  a.setAttrType(Date.class.getName());	
                  a.setShowType("datetime");	
               }	
	
               var8.setFullAttrName(StringUtils.camelCase(a.getColumnName()));	
               Iterator var7 = genTable.getPkList().iterator();	
	
               while(var7.hasNext()) {	
                  GenTableColumn a = (GenTableColumn)var7.next();	
                  if (a.getColumnName().equals(a.getColumnName())) {	
                     a.setIsPk("1");	
                     a.setIsEdit("1");	
                     a.setShowType(a ? "input" : "hidden");	
                     a.getOptionMap().put("fieldValid", "abc");	
                  }	
               }	
	
               a.setIsInsert("1");	
               if (!"1".equals(a.getIsPk()) && !StringUtils.equalsIgnoreCase(a.getColumnName(), "status") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "create_by") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "create_date") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "corp_code") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "corp_name")) {	
                  a.setIsUpdate("1");	
               }	
	
               if (!"1".equals(a.getIsPk()) && !StringUtils.equalsIgnoreCase(a.getColumnName(), "status") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "create_by") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "create_date") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "update_by") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "update_date") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "parent_code") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "parent_codes") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "tree_sort") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "tree_sorts") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "tree_leaf") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "tree_level") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "tree_names") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "corp_code") && !StringUtils.equalsIgnoreCase(a.getColumnName(), "corp_name")) {	
                  a.setIsList("1");	
                  a.setIsQuery("1");	
                  a.setIsEdit("1");	
               }	
	
               if (StringUtils.equalsIgnoreCase(a.getColumnName(), "name") || StringUtils.containsIgnoreCase(a.getColumnName(), "_name") || StringUtils.equalsIgnoreCase(a.getColumnName(), "title") || StringUtils.containsIgnoreCase(a.getColumnName(), "_title") || StringUtils.containsIgnoreCase(a.getColumnName(), "remarks")) {	
                  a.setIsQuery("1");	
                  a.setQueryType("LIKE");	
               }	
	
               if (!"1".equals(a.getIsPk())) {	
                  label219: {	
                     if (a.getIsExtendColumn()) {	
                        a.setFullAttrName("extend." + StringUtils.camelCase(a.getColumnName()));	
                        a.setIsList("0");	
                        a.setIsQuery("0");	
                        if (StringUtils.equalsIgnoreCase(a.getColumnName(), "extend_s1")) {	
                           var8 = a;	
                           a.getOptionMap().put("isNewLine", "1");	
                           break label219;	
                        }	
                     } else {	
                        if (StringUtils.startsWithIgnoreCase(a.getColumnName(), "create_date") || StringUtils.startsWithIgnoreCase(a.getColumnName(), "update_date")) {	
                           if (StringUtils.startsWithIgnoreCase(a.getColumnName(), "update_date")) {	
                              a.setIsList("1");	
                           }	
	
                           var8 = a;	
                           a.setShowType("dateselect");	
                           break label219;	
                        }	
	
                        if (StringUtils.equalsIgnoreCase(a.getColumnName(), "remarks") || StringUtils.equalsIgnoreCase(a.getColumnName(), "content")) {	
                           var8 = a;	
                           a.setShowType("textarea");	
                           break label219;	
                        }	
	
                        if (StringUtils.equalsIgnoreCase(a.getColumnName(), "parent_code")) {	
                           a.setAttrType("This");	
                           var8 = a;	
                           a.setFullAttrName("parentCode|parentName");	
                           a.setShowType("treeselect");	
                           break label219;	
                        }	
	
                        if (StringUtils.equalsIgnoreCase(a.getColumnName(), "parent_codes")) {	
                           a.setQueryType("LIKE");	
                           var8 = a;	
                           a.setIsEdit("0");	
                           break label219;	
                        }	
	
                        if (StringUtils.equalsIgnoreCase(a.getColumnName(), "tree_sort")) {	
                           a.setAttrType(Integer.class.getSimpleName());	
                           var8 = a;	
                           a.setIsList("1");	
                           a.setIsEdit("1");	
                           break label219;	
                        }	
	
                        if (StringUtils.equalsIgnoreCase(a.getColumnName(), "tree_sorts")) {	
                           a.setIsList("0");	
                           var8 = a;	
                           a.setIsEdit("0");	
                           break label219;	
                        }	
	
                        if (StringUtils.equalsIgnoreCase(a.getColumnName(), "corp_code") || StringUtils.equalsIgnoreCase(a.getColumnName(), "corp_name")) {	
                           a.setIsUpdate("0");	
                           a.setIsList("0");	
                           if (StringUtils.equalsIgnoreCase(a.getColumnName(), "corp_name")) {	
                              a.setIsQuery("0");	
                           }	
	
                           var8 = a;	
                           a.setQueryType("=");	
                           break label219;	
                        }	
	
                        if (StringUtils.equalsIgnoreCase(a.getColumnName(), "status")) {	
                           a.setIsUpdate("0");	
                           a.setIsList("1");	
                           a.setIsQuery("1");	
                           a.setShowType("select");	
                           a.getOptionMap().put("dictType", "sys_search_status");	
                           a.getOptionMap().put("dictName", "sys_search_status");	
                        }	
                     }	
	
                     var8 = a;	
                  }	
	
                  if (StringUtils.equals(var8.getShowType(), "textarea")) {	
                     a.getOptionMap().put("isNewLine", "1");	
                     a.getOptionMap().put("gridRowCol", "12/2/10");	
                  }	
               }	
	
               if (a <= 6) {	
                  a.getOptionMap().put("isNewLine", "1");	
                  a.getOptionMap().put("gridRowCol", "12/2/5");	
                  if (StringUtils.equals(a.getShowType(), "textarea")) {	
                     a.getOptionMap().put("gridRowCol", "12/2/8");	
                  }	
               }	
               continue label228;	
            }	
         }	
	
         return;	
      }	
   }	
}	
