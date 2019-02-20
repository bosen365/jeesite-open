/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.core.JsonProcessingException	
 *  com.fasterxml.jackson.databind.ObjectWriter	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.mapper.JsonMapper	
 *  org.apache.commons.lang3.StringUtils	
 */	
package com.jeesite.modules.sys.utils;	
	
import com.fasterxml.jackson.core.JsonProcessingException;	
import com.fasterxml.jackson.databind.ObjectWriter;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.modules.sys.entity.DictData;	
import com.jeesite.modules.sys.entity.DictType;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import com.jeesite.modules.sys.utils.D;	
import java.util.ArrayList;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.ptql.ProcessFinder;	
import org.hyperic.sigar.ptql.QueryLoadException;	
	
public class DictUtils {	
    public static final String CACHE_DICT_MAP = "dictMap";	
	
    public static String getDictLabels(String dictType, String dictValues, String defaultValue) {	
        if (StringUtils.isNotBlank((CharSequence)dictType) && StringUtils.isNotBlank((CharSequence)dictValues)) {	
            int n;	
            ArrayList a2 = ListUtils.newArrayList();	
            String[] arrstring = StringUtils.split((String)dictValues, (String)",");	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a3 = arrstring[n];	
                a2.add(DictUtils.getDictLabel(dictType, a3, defaultValue));	
                n3 = ++n;	
            }	
            return StringUtils.join((Iterable)a2, (String)",");	
        }	
        return defaultValue;	
    }	
	
    public static void clearDictCache() {	
        CorpUtils.removeCache(CACHE_DICT_MAP);	
    }	
	
    public static String getDictLabel(String dictType, String dictValue, String defaultValue) {	
        if (StringUtils.isNotBlank((CharSequence)dictType) && StringUtils.isNotBlank((CharSequence)dictValue)) {	
            for (DictData a2 : DictUtils.getDictList(dictType)) {	
                if (!dictType.equals(a2.getDictType()) || !dictValue.equals(a2.getDictValue())) continue;	
                return a2.getDictLabel();	
            }	
        }	
        return defaultValue;	
    }	
	
    public static String getDictListJson(String dictType) {	
        try {	
            return JsonMapper.getInstance().writerWithView(DictData.SimpleView.class).writeValueAsString(DictUtils.getDictList(dictType));	
        }	
        catch (JsonProcessingException jsonProcessingException) {	
            void a2;	
            a2.printStackTrace();	
            return "[]";	
        }	
    }	
	
    public static List<DictData> getDictList(String dictType) {	
        Object a2;	
        Map a3 = (Map)CorpUtils.getCache(CACHE_DICT_MAP);	
        if (a3 == null) {	
            Iterator<Object> iterator;	
            a3 = MapUtils.newHashMap();	
            a2 = new DictType();	
            ((DataEntity)a2).setStatus("0");	
            Iterator<Object> iterator2 = iterator = D.ALLATORIxDEMO().findList(a2).iterator();	
            while (iterator2.hasNext()) {	
                DictData a4;	
                DictType a5 = (DictType)iterator.next();	
                ArrayList arrayList = ListUtils.newArrayList();	
                iterator2 = iterator;	
                a3.put(a5.getDictType(), a4);	
            }	
            DictData a6 = new DictData();	
            a6.setStatus("0");	
            for (DictData a4 : D.ALLATORIxDEMO().findList(a6)) {	
                List a7 = (List)a3.get(a4.getDictType());	
                if (a7 != null) {	
                    a7.add(a4);	
                    continue;	
                }	
                a3.put(a4.getDictType(), ListUtils.newArrayList((Object[])new DictData[]{a4}));	
            }	
            CorpUtils.putCache(CACHE_DICT_MAP, a3);	
        }	
        if ((a2 = (List)a3.get(dictType)) == null) {	
            a2 = ListUtils.newArrayList();	
        }	
        return a2;	
    }	
	
    public static String getDictValues(String dictType, String dictLabels, String defaultValue) {	
        if (StringUtils.isNotBlank((CharSequence)dictType) && StringUtils.isNotBlank((CharSequence)dictLabels)) {	
            int n;	
            ArrayList a2 = ListUtils.newArrayList();	
            String[] arrstring = StringUtils.split((String)dictLabels, (String)",");	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a3 = arrstring[n];	
                a2.add(DictUtils.getDictValue(a3, dictType, defaultValue));	
                n3 = ++n;	
            }	
            return StringUtils.join((Iterable)a2, (String)",");	
        }	
        return defaultValue;	
    }	
	
    public static String getDictValue(String dictType, String dictLabel, String defaultValue) {	
        if (StringUtils.isNotBlank((CharSequence)dictType) && StringUtils.isNotBlank((CharSequence)dictLabel)) {	
            for (DictData a2 : DictUtils.getDictList(dictType)) {	
                if (!dictType.equals(a2.getDictType()) || !dictLabel.equals(a2.getDictLabel())) continue;	
                return a2.getDictValue();	
            }	
        }	
        return defaultValue;	
    }	
}	
	
