/*	
 * Decompiled with CFR 0.141.	
 */	
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
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.File;	
import java.io.IOException;	
import java.io.InputStream;	
import java.io.Serializable;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.Date;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.hyperic.sigar.pager.SortAttribute;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.core.io.Resource;	
	
public class GenUtils {	
    private static Logger logger = LoggerFactory.getLogger(GenUtils.class);	
	
    public static String generateToFile(GenTemplate tpl, Map<String, Object> model, GenTable genTable) {	
        if (StringUtils.isBlank(genTable.getGenBaseDir())) {	
            genTable.setGenBaseDir(FileUtils.getProjectPath());	
        }	
        String[] arrstring = new String[3];	
        arrstring[0] = "//";	
        arrstring[1] = "/";	
        arrstring[2] = ".";	
        String[] arrstring2 = new String[3];	
        arrstring2[0] = File.separator;	
        arrstring2[1] = File.separator;	
        arrstring2[2] = File.separator;	
        String a = new StringBuilder().insert(0, genTable.getGenBaseDir()).append(File.separator).append(StringUtils.replaceEach(BeetlUtils.renderFromString(new StringBuilder().insert(0, tpl.getFilePath()).append("/").toString(), model), arrstring, arrstring2)).append(BeetlUtils.renderFromString(tpl.getFileName(), model)).toString();	
        logger.debug(new StringBuilder().insert(0, "Generate File ==> \r\n").append(a).toString());	
        String a2 = BeetlUtils.renderFromString(StringUtils.trimToEmpty(tpl.getContent()), model);	
        logger.debug(new StringBuilder().insert(0, "File Content ==> \r\n").append(a2).toString());	
        if ("2".equals(genTable.getGenFlag())) {	
            if ("1".equals(genTable.getReplaceFile())) {	
                FileUtils.deleteFile(a);	
            }	
            if (FileUtils.createFile(a)) {	
                FileUtils.writeToFile(a, a2, true);	
                logger.debug(new StringBuilder().insert(0, "File Create ==> ").append(a).toString());	
                return new StringBuilder().insert(0, "生成成功：").append(a).append("<br/>").toString();	
            }	
            logger.debug(new StringBuilder().insert(0, "File Extents ==> ").append(a).toString());	
            return new StringBuilder().insert(0, "文件已存在：").append(a).append("<br/>").toString();	
        }	
        return new StringBuilder().insert(0, a).append("<br/>").toString();	
    }	
	
    public static <T> T fileToObject(String fileName, Class<?> clazz) {	
        Resource a = ResourceUtils.getResource(new StringBuilder().insert(0, "/templates/modules/gen/").append(fileName).toString());	
        if (a.exists()) {	
            InputStream a2 = null;	
            try {	
                a2 = a.getInputStream();	
                String a3 = IOUtils.toString(a2, "UTF-8");	
                Object obj = JaxbMapper.fromXml(a3, clazz);	
                return (T)obj;	
            }	
            catch (IOException a4) {	
                throw ExceptionUtils.unchecked(a4);	
            }	
            finally {	
                IOUtils.closeQuietly(a2);	
            }	
        }	
        return null;	
    }	
	
    public static GenConfig getConfig() {	
        GenConfig a = (GenConfig)GenUtils.fileToObject("config-custom.xml", GenConfig.class);	
        if (a == null) {	
            a = (GenConfig)GenUtils.fileToObject("config.xml", GenConfig.class);	
        }	
        return a;	
    }	
	
    public static Map<String, Object> getDataModel(GenTable genTable) {	
        String a;	
        Serializable a2;	
        if (genTable.getParentExists().booleanValue() && genTable.getParent() != null) {	
            GenTable genTable2 = genTable;	
            a2 = genTable2.getParent();	
            genTable2.setPackageName(((GenTable)a2).getPackageName());	
            genTable2.setModuleName(((GenTable)a2).getModuleName());	
            genTable2.setSubModuleName(((GenTable)a2).getSubModuleName());	
            genTable2.setFunctionName(((GenTable)a2).getFunctionName());	
            genTable2.setFunctionNameSimple(((GenTable)a2).getFunctionNameSimple());	
            genTable2.setFunctionAuthor(((GenTable)a2).getFunctionAuthor());	
            genTable2.setGenBaseDir(((GenTable)a2).getGenBaseDir());	
            genTable2.setOptions(((GenTable)a2).getOptions());	
            genTable2.setReplaceFile(((GenTable)a2).getReplaceFile());	
            genTable2.setGenFlag(((GenTable)a2).getGenFlag());	
        }	
        a2 = MapUtils.newHashMap();	
        a2.put("packageName", StringUtils.lowerCase(genTable.getPackageName()));	
        Serializable serializable = a2;	
        serializable.put("lastPackageName", StringUtils.substringAfterLast((String)serializable.get("packageName"), "."));	
        a2.put("moduleName", StringUtils.lowerCase(genTable.getModuleName()));	
        a2.put("subModuleName", StringUtils.lowerCase(genTable.getSubModuleName()));	
        a2.put("className", StringUtils.uncapitalize(genTable.getClassName()));	
        a2.put("ClassName", StringUtils.capitalize(genTable.getClassName()));	
        a2.put("functionName", genTable.getFunctionName());	
        a2.put("functionNameSimple", genTable.getFunctionNameSimple());	
        a2.put("functionAuthor", StringUtils.defaultIfBlank(genTable.getFunctionAuthor(), UserUtils.getUser().getUserName()));	
        a2.put("functionVersion", DateUtils.getDate());	
        Serializable serializable2 = a2;	
        serializable2.put("urlPrefix", StringUtils.replace(serializable2.get("moduleName") + (StringUtils.isNotBlank(genTable.getSubModuleName()) ? new StringBuilder().insert(0, "/").append(StringUtils.lowerCase(genTable.getSubModuleName())).toString() : "") + "/" + a2.get("className"), ".", "/"));	
        Serializable serializable3 = a2;	
        serializable3.put("viewPrefix", serializable3.get("urlPrefix"));	
        Serializable serializable4 = a2;	
        serializable4.put("permissionPrefix", StringUtils.replace(serializable4.get("moduleName") + (StringUtils.isNotBlank(genTable.getSubModuleName()) ? new StringBuilder().insert(0, ":").append(StringUtils.lowerCase(genTable.getSubModuleName())).toString() : "") + ":" + a2.get("className"), ".", ":"));	
        a2.put("Global", Global.getInstance());	
        a2.put("dbName", Global.getDbName());	
        a2.put("table", genTable);	
        String string = "";	
        if (genTable != null && genTable.getPkList() != null && genTable.getPkList().size() == 1) {	
            a = genTable.getPkList().get(0).getSimpleAttrName();	
        }	
        Serializable serializable5 = a2;	
        serializable5.put("pkField", a);	
        return serializable5;	
    }	
	
    public static List<GenTemplate> getTemplateList(GenConfig config, String category, boolean isChildTable) {	
        ArrayList<GenTemplate> a = ListUtils.newArrayList();	
        if (config != null && config.getTplCategoryList() != null && category != null) {	
            for (GenTplCategory a2 : config.getTplCategoryList()) {	
                if (!category.equals(a2.getValue())) continue;	
                List<String> a3 = null;	
                if ((!isChildTable ? (a3 = a2.getTemplate()) : (a3 = a2.getChildTableTemplate())) == null) break;	
                for (String a4 : a3) {	
                    if (StringUtils.startsWith(a4, GenTplCategory.CATEGORY_REF)) {	
                        a.addAll(GenUtils.getTemplateList(config, StringUtils.replace(a4, GenTplCategory.CATEGORY_REF, ""), false));	
                        continue;	
                    }	
                    GenTemplate a5 = (GenTemplate)GenUtils.fileToObject(a4, GenTemplate.class);	
                    if (a5 == null) continue;	
                    a.add(a5);	
                }	
            }	
        }	
        return a;	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Lifted jumps to return sites	
     */	
    public static void initColumnField(GenTable genTable) {	
        v0 = genTable;	
        a = v0.getColumnList().size();	
        a = v0.getIsTreeEntity();	
        var3_3 = v0.getColumnList().iterator();	
        do {	
            block37 : {	
                block39 : {	
                    block38 : {	
                        block36 : {	
                            v1 = var3_3;	
                            while (v1.hasNext() != false) {	
                                a = var3_3.next();	
                                if (StringUtils.isNotBlank(a.getId())) {	
                                    v1 = var3_3;	
                                    continue;	
                                }	
                                break block36;	
                            }	
                            return;	
                        }	
                        v2 = a;	
                        v2.setShowType("input");	
                        if (StringUtils.isBlank(v2.getComments())) {	
                            v3 = a;	
                            v3.setComments(v3.getColumnName());	
                        }	
                        if (StringUtils.startsWithIgnoreCase(a.getColumnType(), "date") || StringUtils.startsWithIgnoreCase(a.getColumnType(), "timestamp")) {	
                            v4 = a;	
                            v5 = a;	
                            v5.setAttrType(Date.class.getName());	
                            v5.setShowType("datetime");	
                        } else if (StringUtils.startsWithIgnoreCase(a.getColumnType(), "float") || StringUtils.startsWithIgnoreCase(a.getColumnType(), "double")) {	
                            v6 = a;	
                            v4 = v6;	
                            v6.setAttrType(Double.class.getSimpleName());	
                            v6.getOptionMap().put("fieldValid", "number");	
                        } else {	
                            if (StringUtils.containsIgnoreCase(a.getColumnType(), "int") || StringUtils.startsWithIgnoreCase(a.getColumnType(), "number") || StringUtils.startsWithIgnoreCase(a.getColumnType(), "decimal") || StringUtils.startsWithIgnoreCase(a.getColumnType(), "numeric")) {	
                                a = StringUtils.split(StringUtils.substringBetween(a.getColumnType(), "(", ")"), ",");	
                                if (a != null && a.length == 2 && Integer.parseInt(a[1]) > 0) {	
                                    v7 = a;	
                                    v7.setAttrType(Double.class.getSimpleName());	
                                    v7.getOptionMap().put("fieldValid", "number");	
                                } else if (a != null && a.length >= 1 && Integer.parseInt(a[0]) <= 10) {	
                                    v8 = a;	
                                    v8.setAttrType(Integer.class.getSimpleName());	
                                    v8.getOptionMap().put("fieldValid", "digits");	
                                } else {	
                                    v9 = a;	
                                    v9.setAttrType(Long.class.getSimpleName());	
                                    v9.getOptionMap().put("fieldValid", "digits");	
                                }	
                            } else {	
                                a.setAttrType(String.class.getSimpleName());	
                            }	
                            v4 = a;	
                        }	
                        v4.setFullAttrName(StringUtils.camelCase(a.getColumnName()));	
                        for (GenTableColumn a : genTable.getPkList()) {	
                            if (!a.getColumnName().equals(a.getColumnName())) continue;	
                            v10 = a;	
                            a.setIsPk("1");	
                            v10.setIsEdit("1");	
                            v10.setShowType(a != false ? "input" : "hidden");	
                            a.getOptionMap().put("fieldValid", "abc");	
                        }	
                        a.setIsInsert("1");	
                        if (!("1".equals(a.getIsPk()) || StringUtils.equalsIgnoreCase(a.getColumnName(), "status") || StringUtils.equalsIgnoreCase(a.getColumnName(), "create_by") || StringUtils.equalsIgnoreCase(a.getColumnName(), "create_date") || StringUtils.equalsIgnoreCase(a.getColumnName(), "corp_code") || StringUtils.equalsIgnoreCase(a.getColumnName(), "corp_name"))) {	
                            a.setIsUpdate("1");	
                        }	
                        if (!("1".equals(a.getIsPk()) || StringUtils.equalsIgnoreCase(a.getColumnName(), "status") || StringUtils.equalsIgnoreCase(a.getColumnName(), "create_by") || StringUtils.equalsIgnoreCase(a.getColumnName(), "create_date") || StringUtils.equalsIgnoreCase(a.getColumnName(), "update_by") || StringUtils.equalsIgnoreCase(a.getColumnName(), "update_date") || StringUtils.equalsIgnoreCase(a.getColumnName(), "parent_code") || StringUtils.equalsIgnoreCase(a.getColumnName(), "parent_codes") || StringUtils.equalsIgnoreCase(a.getColumnName(), "tree_sort") || StringUtils.equalsIgnoreCase(a.getColumnName(), "tree_sorts") || StringUtils.equalsIgnoreCase(a.getColumnName(), "tree_leaf") || StringUtils.equalsIgnoreCase(a.getColumnName(), "tree_level") || StringUtils.equalsIgnoreCase(a.getColumnName(), "tree_names") || StringUtils.equalsIgnoreCase(a.getColumnName(), "corp_code") || StringUtils.equalsIgnoreCase(a.getColumnName(), "corp_name"))) {	
                            v11 = a;	
                            a.setIsList("1");	
                            v11.setIsQuery("1");	
                            v11.setIsEdit("1");	
                        }	
                        if (StringUtils.equalsIgnoreCase(a.getColumnName(), "name") || StringUtils.containsIgnoreCase(a.getColumnName(), "_name") || StringUtils.equalsIgnoreCase(a.getColumnName(), "title") || StringUtils.containsIgnoreCase(a.getColumnName(), "_title") || StringUtils.containsIgnoreCase(a.getColumnName(), "remarks")) {	
                            a.setIsQuery("1");	
                            a.setQueryType("LIKE");	
                        }	
                        if ("1".equals(a.getIsPk())) break block37;	
                        if (!a.getIsExtendColumn().booleanValue()) break block38;	
                        v12 = a;	
                        v13 = a;	
                        v14 = a;	
                        v14.setFullAttrName("extend." + StringUtils.camelCase(v14.getColumnName()));	
                        v12.setIsList("0");	
                        v12.setIsQuery("0");	
                        if (!StringUtils.equalsIgnoreCase(v12.getColumnName(), "extend_s1")) ** GOTO lbl155	
                        v15 = a;	
                        v16 = v15;	
                        v15.getOptionMap().put("isNewLine", "1");	
                        break block39;	
                    }	
                    if (StringUtils.startsWithIgnoreCase(a.getColumnName(), "create_date") || StringUtils.startsWithIgnoreCase(a.getColumnName(), "update_date")) {	
                        if (StringUtils.startsWithIgnoreCase(a.getColumnName(), "update_date")) {	
                            a.setIsList("1");	
                        }	
                        v17 = a;	
                        v16 = v17;	
                        v17.setShowType("dateselect");	
                    } else if (StringUtils.equalsIgnoreCase(a.getColumnName(), "remarks") || StringUtils.equalsIgnoreCase(a.getColumnName(), "content")) {	
                        v18 = a;	
                        v16 = v18;	
                        v18.setShowType("textarea");	
                    } else {	
                        v19 = a;	
                        if (StringUtils.equalsIgnoreCase(a.getColumnName(), "parent_code")) {	
                            v19.setAttrType("This");	
                            v20 = a;	
                            v16 = v20;	
                            v20.setFullAttrName("parentCode|parentName");	
                            v20.setShowType("treeselect");	
                        } else {	
                            v21 = a;	
                            if (StringUtils.equalsIgnoreCase(v19.getColumnName(), "parent_codes")) {	
                                v21.setQueryType("LIKE");	
                                v22 = a;	
                                v16 = v22;	
                                v22.setIsEdit("0");	
                            } else {	
                                v23 = a;	
                                if (StringUtils.equalsIgnoreCase(v21.getColumnName(), "tree_sort")) {	
                                    v23.setAttrType(Integer.class.getSimpleName());	
                                    v24 = a;	
                                    v16 = v24;	
                                    v24.setIsList("1");	
                                    v24.setIsEdit("1");	
                                } else {	
                                    v25 = a;	
                                    if (StringUtils.equalsIgnoreCase(v23.getColumnName(), "tree_sorts")) {	
                                        v25.setIsList("0");	
                                        v26 = a;	
                                        v16 = v26;	
                                        v26.setIsEdit("0");	
                                    } else if (StringUtils.equalsIgnoreCase(v25.getColumnName(), "corp_code") || StringUtils.equalsIgnoreCase(a.getColumnName(), "corp_name")) {	
                                        v27 = a;	
                                        v27.setIsUpdate("0");	
                                        v27.setIsList("0");	
                                        if (StringUtils.equalsIgnoreCase(a.getColumnName(), "corp_name")) {	
                                            a.setIsQuery("0");	
                                        }	
                                        v28 = a;	
                                        v16 = v28;	
                                        v28.setQueryType("=");	
                                    } else {	
                                        if (StringUtils.equalsIgnoreCase(a.getColumnName(), "status")) {	
                                            v29 = a;	
                                            v30 = a;	
                                            v30.setIsUpdate("0");	
                                            v30.setIsList("1");	
                                            v29.setIsQuery("1");	
                                            v29.setShowType("select");	
                                            v29.getOptionMap().put("dictType", "sys_search_status");	
                                            a.getOptionMap().put("dictName", "sys_search_status");	
                                        }	
lbl155: // 4 sources:	
                                        v16 = a;	
                                    }	
                                }	
                            }	
                        }	
                    }	
                }	
                if (StringUtils.equals(v16.getShowType(), "textarea")) {	
                    a.getOptionMap().put("isNewLine", "1");	
                    a.getOptionMap().put("gridRowCol", "12/2/10");	
                }	
            }	
            if (a > 6) continue;	
            v31 = a;	
            a.getOptionMap().put("isNewLine", "1");	
            v31.getOptionMap().put("gridRowCol", "12/2/5");	
            if (!StringUtils.equals(v31.getShowType(), "textarea")) continue;	
            a.getOptionMap().put("gridRowCol", "12/2/8");	
        } while (true);	
    }	
}	
	
