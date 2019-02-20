/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.io.FileUtils	
 *  com.jeesite.common.io.IOUtils	
 *  com.jeesite.common.io.ResourceUtils	
 *  com.jeesite.common.lang.DateUtils	
 *  com.jeesite.common.lang.ExceptionUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.mapper.JaxbMapper	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 *  org.springframework.core.io.Resource	
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
import org.hyperic.sigar.Tcp;	
import org.hyperic.sigar.shell.ShellCommandInitException;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.core.io.Resource;	
	
public class GenUtils {	
    private static Logger logger = LoggerFactory.getLogger(GenUtils.class);	
	
    public static String generateToFile(GenTemplate tpl, Map<String, Object> model, GenTable genTable) {	
        if (StringUtils.isBlank((CharSequence)genTable.getGenBaseDir())) {	
            genTable.setGenBaseDir(FileUtils.getProjectPath());	
        }	
        String a2 = new StringBuilder().insert(0, genTable.getGenBaseDir()).append(File.separator).append(StringUtils.replaceEach((String)BeetlUtils.renderFromString(new StringBuilder().insert(0, tpl.getFilePath()).append("/").toString(), model), (String[])new String[]{"//", "/", "."}, (String[])new String[]{File.separator, File.separator, File.separator})).append(BeetlUtils.renderFromString(tpl.getFileName(), model)).toString();	
        logger.debug(new StringBuilder().insert(0, "Generate File ==> \r\n").append(a2).toString());	
        String a3 = BeetlUtils.renderFromString(StringUtils.trimToEmpty((String)tpl.getContent()), model);	
        logger.debug(new StringBuilder().insert(0, "File Content ==> \r\n").append(a3).toString());	
        if ("2".equals(genTable.getGenFlag())) {	
            if ("1".equals(genTable.getReplaceFile())) {	
                FileUtils.deleteFile((String)a2);	
            }	
            if (FileUtils.createFile((String)a2)) {	
                FileUtils.writeToFile((String)a2, (String)a3, (boolean)true);	
                logger.debug(new StringBuilder().insert(0, "File Create ==> ").append(a2).toString());	
                return new StringBuilder().insert(0, "生成成功：").append(a2).append("<br/>").toString();	
            }	
            logger.debug(new StringBuilder().insert(0, "File Extents ==> ").append(a2).toString());	
            return new StringBuilder().insert(0, "文件已存在：").append(a2).append("<br/>").toString();	
        }	
        return new StringBuilder().insert(0, a2).append("<br/>").toString();	
    }	
	
    public static Map<String, Object> getDataModel(GenTable genTable) {	
        String a2;	
        Serializable a3;	
        if (genTable.getParentExists().booleanValue() && genTable.getParent() != null) {	
            GenTable genTable2 = genTable;	
            a3 = genTable2.getParent();	
            genTable2.setPackageName(((GenTable)a3).getPackageName());	
            genTable2.setModuleName(((GenTable)a3).getModuleName());	
            genTable2.setSubModuleName(((GenTable)a3).getSubModuleName());	
            genTable2.setFunctionName(((GenTable)a3).getFunctionName());	
            genTable2.setFunctionNameSimple(((GenTable)a3).getFunctionNameSimple());	
            genTable2.setFunctionAuthor(((GenTable)a3).getFunctionAuthor());	
            genTable2.setGenBaseDir(((GenTable)a3).getGenBaseDir());	
            genTable2.setOptions(((GenTable)a3).getOptions());	
            genTable2.setReplaceFile(((GenTable)a3).getReplaceFile());	
            genTable2.setGenFlag(((GenTable)a3).getGenFlag());	
        }	
        a3 = MapUtils.newHashMap();	
        a3.put("packageName", StringUtils.lowerCase((String)genTable.getPackageName()));	
        Serializable serializable = a3;	
        a3.put("functionVersion", DateUtils.getDate());	
        a3.put("functionAuthor", StringUtils.defaultIfBlank((CharSequence)genTable.getFunctionAuthor(), (CharSequence)UserUtils.getUser().getUserName()));	
        a3.put("functionNameSimple", genTable.getFunctionNameSimple());	
        a3.put("functionName", genTable.getFunctionName());	
        a3.put("ClassName", StringUtils.capitalize((String)genTable.getClassName()));	
        a3.put("className", StringUtils.uncapitalize((String)genTable.getClassName()));	
        a3.put("subModuleName", StringUtils.lowerCase((String)genTable.getSubModuleName()));	
        a3.put("moduleName", StringUtils.lowerCase((String)genTable.getModuleName()));	
        serializable.put("lastPackageName", StringUtils.substringAfterLast((String)((String)serializable.get("packageName")), (String)"."));	
        Serializable serializable2 = a3;	
        Serializable serializable3 = a3;	
        serializable3.put("viewPrefix", serializable3.get("urlPrefix"));	
        serializable2.put("urlPrefix", StringUtils.replace((String)(serializable2.get("moduleName") + (StringUtils.isNotBlank((CharSequence)genTable.getSubModuleName()) ? new StringBuilder().insert(0, "/").append(StringUtils.lowerCase((String)genTable.getSubModuleName())).toString() : "") + "/" + a3.get("className")), (String)".", (String)"/"));	
        Serializable serializable4 = a3;	
        String string = "";	
        a3.put("table", genTable);	
        a3.put("dbName", Global.getDbName());	
        a3.put("Global", Global.getInstance());	
        serializable4.put("permissionPrefix", StringUtils.replace((String)(serializable4.get("moduleName") + (StringUtils.isNotBlank((CharSequence)genTable.getSubModuleName()) ? new StringBuilder().insert(0, ":").append(StringUtils.lowerCase((String)genTable.getSubModuleName())).toString() : "") + ":" + a3.get("className")), (String)".", (String)":"));	
        if (genTable != null && genTable.getPkList() != null && genTable.getPkList().size() == 1) {	
            a2 = genTable.getPkList().get(0).getSimpleAttrName();	
        }	
        Serializable serializable5 = a3;	
        serializable5.put("pkField", a2);	
        return serializable5;	
    }	
	
    public static List<GenTemplate> getTemplateList(GenConfig config, String category, boolean isChildTable) {	
        ArrayList a2 = ListUtils.newArrayList();	
        if (config != null && config.getTplCategoryList() != null && category != null) {	
            for (GenTplCategory a3 : config.getTplCategoryList()) {	
                if (!category.equals(a3.getValue())) continue;	
                List<String> a4 = null;	
                if ((!isChildTable ? (a4 = a3.getTemplate()) : (a4 = a3.getChildTableTemplate())) == null) break;	
                for (String a5 : a4) {	
                    if (StringUtils.startsWith((CharSequence)a5, (CharSequence)GenTplCategory.CATEGORY_REF)) {	
                        a2.addAll(GenUtils.getTemplateList(config, StringUtils.replace((String)a5, (String)GenTplCategory.CATEGORY_REF, (String)""), false));	
                        continue;	
                    }	
                    GenTemplate a6 = (GenTemplate)GenUtils.fileToObject(a5, GenTemplate.class);	
                    if (a6 == null) continue;	
                    a2.add(a6);	
                }	
            }	
        }	
        return a2;	
    }	
	
    public static GenConfig getConfig() {	
        GenConfig a2 = (GenConfig)GenUtils.fileToObject("config-custom.xml", GenConfig.class);	
        if (a2 == null) {	
            a2 = (GenConfig)GenUtils.fileToObject("config.xml", GenConfig.class);	
        }	
        return a2;	
    }	
	
    public static <T> T fileToObject(String fileName, Class<?> clazz) {	
        Resource a2 = ResourceUtils.getResource((String)new StringBuilder().insert(0, "/templates/modules/gen/").append(fileName).toString());	
        if (a2.exists()) {	
            InputStream a3 = null;	
            try {	
                a3 = a2.getInputStream();	
                String a4 = IOUtils.toString((InputStream)a3, (String)"UTF-8");	
                Object object = JaxbMapper.fromXml((String)a4, clazz);	
                return (T)object;	
            }	
            catch (IOException a5) {	
                throw ExceptionUtils.unchecked((Exception)a5);	
            }	
            finally {	
                IOUtils.closeQuietly((InputStream)a3);	
            }	
        }	
        return null;	
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
                                if (StringUtils.isNotBlank((CharSequence)a.getId())) {	
                                    v1 = var3_3;	
                                    continue;	
                                }	
                                break block36;	
                            }	
                            return;	
                        }	
                        v2 = a;	
                        v2.setShowType("input");	
                        if (StringUtils.isBlank((CharSequence)v2.getComments())) {	
                            v3 = a;	
                            v3.setComments(v3.getColumnName());	
                        }	
                        if (StringUtils.startsWithIgnoreCase((CharSequence)a.getColumnType(), (CharSequence)"date") || StringUtils.startsWithIgnoreCase((CharSequence)a.getColumnType(), (CharSequence)"timestamp")) {	
                            v4 = a;	
                            v5 = a;	
                            v5.setAttrType(Date.class.getName());	
                            v5.setShowType("datetime");	
                        } else if (StringUtils.startsWithIgnoreCase((CharSequence)a.getColumnType(), (CharSequence)"float") || StringUtils.startsWithIgnoreCase((CharSequence)a.getColumnType(), (CharSequence)"double")) {	
                            v6 = a;	
                            v4 = v6;	
                            v6.setAttrType(Double.class.getSimpleName());	
                            v6.getOptionMap().put("fieldValid", "number");	
                        } else {	
                            if (StringUtils.containsIgnoreCase((CharSequence)a.getColumnType(), (CharSequence)"int") || StringUtils.startsWithIgnoreCase((CharSequence)a.getColumnType(), (CharSequence)"number") || StringUtils.startsWithIgnoreCase((CharSequence)a.getColumnType(), (CharSequence)"decimal") || StringUtils.startsWithIgnoreCase((CharSequence)a.getColumnType(), (CharSequence)"numeric")) {	
                                a = StringUtils.split((String)StringUtils.substringBetween((String)a.getColumnType(), (String)"(", (String)")"), (String)",");	
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
                        v4.setFullAttrName(StringUtils.camelCase((String)a.getColumnName()));	
                        for (GenTableColumn a : genTable.getPkList()) {	
                            if (!a.getColumnName().equals(a.getColumnName())) continue;	
                            v10 = a;	
                            a.setIsPk("1");	
                            v10.setIsEdit("1");	
                            v10.setShowType(a != false ? "input" : "hidden");	
                            a.getOptionMap().put("fieldValid", "abc");	
                        }	
                        a.setIsInsert("1");	
                        if (!("1".equals(a.getIsPk()) || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"status") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"create_by") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"create_date") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"corp_code") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"corp_name"))) {	
                            a.setIsUpdate("1");	
                        }	
                        if (!("1".equals(a.getIsPk()) || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"status") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"create_by") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"create_date") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"update_by") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"update_date") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"parent_code") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"parent_codes") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"tree_sort") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"tree_sorts") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"tree_leaf") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"tree_level") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"tree_names") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"corp_code") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"corp_name"))) {	
                            v11 = a;	
                            a.setIsList("1");	
                            v11.setIsQuery("1");	
                            v11.setIsEdit("1");	
                        }	
                        if (StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"name") || StringUtils.containsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"_name") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"title") || StringUtils.containsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"_title") || StringUtils.containsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"remarks")) {	
                            a.setIsQuery("1");	
                            a.setQueryType("LIKE");	
                        }	
                        if ("1".equals(a.getIsPk())) break block37;	
                        if (!a.getIsExtendColumn().booleanValue()) break block38;	
                        v12 = a;	
                        v13 = a;	
                        v14 = a;	
                        v14.setFullAttrName("extend." + StringUtils.camelCase((String)v14.getColumnName()));	
                        v12.setIsList("0");	
                        v12.setIsQuery("0");	
                        if (!StringUtils.equalsIgnoreCase((CharSequence)v12.getColumnName(), (CharSequence)"extend_s1")) ** GOTO lbl145	
                        v15 = a;	
                        v16 = v15;	
                        v15.getOptionMap().put("isNewLine", "1");	
                        break block39;	
                    }	
                    if (StringUtils.startsWithIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"create_date") || StringUtils.startsWithIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"update_date")) {	
                        if (StringUtils.startsWithIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"update_date")) {	
                            a.setIsList("1");	
                        }	
                        v17 = a;	
                        v16 = v17;	
                        v17.setShowType("dateselect");	
                    } else if (StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"remarks") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"content")) {	
                        v18 = a;	
                        v16 = v18;	
                        v18.setShowType("textarea");	
                    } else {	
                        v19 = a;	
                        if (StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"parent_code")) {	
                            v19.setAttrType("This");	
                            v20 = a;	
                            v16 = v20;	
                            v20.setFullAttrName("parentCode|parentName");	
                            v20.setShowType("treeselect");	
                        } else {	
                            v21 = a;	
                            if (StringUtils.equalsIgnoreCase((CharSequence)v19.getColumnName(), (CharSequence)"parent_codes")) {	
                                v21.setQueryType("LIKE");	
                                v22 = a;	
                                v16 = v22;	
                                v22.setIsEdit("0");	
                            } else {	
                                v23 = a;	
                                if (StringUtils.equalsIgnoreCase((CharSequence)v21.getColumnName(), (CharSequence)"tree_sort")) {	
                                    v23.setAttrType(Integer.class.getSimpleName());	
                                    v24 = a;	
                                    v16 = v24;	
                                    v24.setIsList("1");	
                                    v24.setIsEdit("1");	
                                } else {	
                                    v25 = a;	
                                    if (StringUtils.equalsIgnoreCase((CharSequence)v23.getColumnName(), (CharSequence)"tree_sorts")) {	
                                        v25.setIsList("0");	
                                        v26 = a;	
                                        v16 = v26;	
                                        v26.setIsEdit("0");	
                                    } else if (StringUtils.equalsIgnoreCase((CharSequence)v25.getColumnName(), (CharSequence)"corp_code") || StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"corp_name")) {	
                                        v27 = a;	
                                        v27.setIsUpdate("0");	
                                        v27.setIsList("0");	
                                        if (StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"corp_name")) {	
                                            a.setIsQuery("0");	
                                        }	
                                        v28 = a;	
                                        v16 = v28;	
                                        v28.setQueryType("=");	
                                    } else {	
                                        if (StringUtils.equalsIgnoreCase((CharSequence)a.getColumnName(), (CharSequence)"status")) {	
                                            v29 = a;	
                                            v30 = a;	
                                            v30.setIsUpdate("0");	
                                            v30.setIsList("1");	
                                            v29.setIsQuery("1");	
                                            v29.setShowType("select");	
                                            a.getOptionMap().put("dictName", "sys_search_status");	
                                            v29.getOptionMap().put("dictType", "sys_search_status");	
                                        }	
lbl145: // 4 sources:	
                                        v16 = a;	
                                    }	
                                }	
                            }	
                        }	
                    }	
                }	
                if (StringUtils.equals((CharSequence)v16.getShowType(), (CharSequence)"textarea")) {	
                    a.getOptionMap().put("gridRowCol", "12/2/10");	
                    a.getOptionMap().put("isNewLine", "1");	
                }	
            }	
            if (a > 6) continue;	
            v31 = a;	
            v31.getOptionMap().put("gridRowCol", "12/2/5");	
            a.getOptionMap().put("isNewLine", "1");	
            if (!StringUtils.equals((CharSequence)v31.getShowType(), (CharSequence)"textarea")) continue;	
            a.getOptionMap().put("gridRowCol", "12/2/8");	
        } while (true);	
    }	
}	
	
