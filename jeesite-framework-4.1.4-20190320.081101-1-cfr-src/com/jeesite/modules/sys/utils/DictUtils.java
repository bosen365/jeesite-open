/*	
 * Decompiled with CFR 0.140.	
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
import com.jeesite.modules.job.d.i;	
import com.jeesite.modules.sys.entity.DictData;	
import com.jeesite.modules.sys.entity.DictType;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import com.jeesite.modules.sys.utils.b;	
import java.util.ArrayList;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.NfsClientV3;	
	
public class DictUtils {	
    public static final String CACHE_DICT_MAP = "dictMap";	
	
    public static String getDictValue(String dictType, String dictLabel, String defaultValue) {	
        if (StringUtils.isNotBlank((CharSequence)dictType) && StringUtils.isNotBlank((CharSequence)dictLabel)) {	
            for (DictData a : DictUtils.getDictList(dictType)) {	
                if (!dictType.equals(a.getDictType()) || !dictLabel.equals(a.getDictLabel())) continue;	
                return a.getDictValue();	
            }	
        }	
        return defaultValue;	
    }	
	
    public static String getDictValues(String dictType, String dictLabels, String defaultValue) {	
        if (StringUtils.isNotBlank((CharSequence)dictType) && StringUtils.isNotBlank((CharSequence)dictLabels)) {	
            int n;	
            ArrayList a = ListUtils.newArrayList();	
            String[] arrstring = StringUtils.split((String)dictLabels, (String)",");	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a2 = arrstring[n];	
                a.add(DictUtils.getDictValue(a2, dictType, defaultValue));	
                n3 = ++n;	
            }	
            return StringUtils.join((Iterable)a, (String)",");	
        }	
        return defaultValue;	
    }	
	
    public static String getDictLabels(String dictType, String dictValues, String defaultValue) {	
        if (StringUtils.isNotBlank((CharSequence)dictType) && StringUtils.isNotBlank((CharSequence)dictValues)) {	
            int n;	
            ArrayList a = ListUtils.newArrayList();	
            String[] arrstring = StringUtils.split((String)dictValues, (String)",");	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a2 = arrstring[n];	
                a.add(DictUtils.getDictLabel(dictType, a2, defaultValue));	
                n3 = ++n;	
            }	
            return StringUtils.join((Iterable)a, (String)",");	
        }	
        return defaultValue;	
    }	
	
    public static String getDictListJson(String dictType) {	
        try {	
            return JsonMapper.getInstance().writerWithView(DictData.SimpleView.class).writeValueAsString(DictUtils.getDictList(dictType));	
        }	
        catch (JsonProcessingException jsonProcessingException) {	
            void a;	
            a.printStackTrace();	
            return "[]";	
        }	
    }	
	
    public static String getDictLabel(String dictType, String dictValue, String defaultValue) {	
        if (StringUtils.isNotBlank((CharSequence)dictType) && StringUtils.isNotBlank((CharSequence)dictValue)) {	
            for (DictData a : DictUtils.getDictList(dictType)) {	
                if (!dictType.equals(a.getDictType()) || !dictValue.equals(a.getDictValue())) continue;	
                return a.getDictLabel();	
            }	
        }	
        return defaultValue;	
    }	
	
    public static List<DictData> getDictList(String dictType) {	
        Object a;	
        Map a2 = (Map)CorpUtils.getCache(CACHE_DICT_MAP);	
        if (a2 == null) {	
            Iterator<Object> iterator;	
            a2 = MapUtils.newHashMap();	
            a = new DictType();	
            ((DataEntity)a).setStatus("0");	
            Iterator<Object> iterator2 = iterator = b.ALLATORIxDEMO().findList(a).iterator();	
            while (iterator2.hasNext()) {	
                DictData a3;	
                DictType a4 = (DictType)iterator.next();	
                ArrayList arrayList = ListUtils.newArrayList();	
                iterator2 = iterator;	
                a2.put(a4.getDictType(), a3);	
            }	
            DictData a5 = new DictData();	
            a5.setStatus("0");	
            for (DictData a3 : b.ALLATORIxDEMO().findList(a5)) {	
                List a6 = (List)a2.get(a3.getDictType());	
                if (a6 != null) {	
                    a6.add(a3);	
                    continue;	
                }	
                a2.put(a3.getDictType(), ListUtils.newArrayList((Object[])new DictData[]{a3}));	
            }	
            CorpUtils.putCache(CACHE_DICT_MAP, a2);	
        }	
        if ((a = (List)a2.get(dictType)) == null) {	
            a = ListUtils.newArrayList();	
        }	
        return a;	
    }	
	
    public static void clearDictCache() {	
        CorpUtils.removeCache(CACHE_DICT_MAP);	
    }	
}	
	
