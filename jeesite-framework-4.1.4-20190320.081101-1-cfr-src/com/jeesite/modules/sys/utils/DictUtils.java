/*	
 * Decompiled with CFR 0.140.	
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
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.NfsClientV3;	
	
public class DictUtils {	
    public static final String CACHE_DICT_MAP = "dictMap";	
	
    public static String getDictValue(String dictType, String dictLabel, String defaultValue) {	
        if (StringUtils.isNotBlank(dictType) && StringUtils.isNotBlank(dictLabel)) {	
            for (DictData a : DictUtils.getDictList(dictType)) {	
                if (!dictType.equals(a.getDictType()) || !dictLabel.equals(a.getDictLabel())) continue;	
                return a.getDictValue();	
            }	
        }	
        return defaultValue;	
    }	
	
    public static String getDictValues(String dictType, String dictLabels, String defaultValue) {	
        if (StringUtils.isNotBlank(dictType) && StringUtils.isNotBlank(dictLabels)) {	
            int n;	
            ArrayList<String> a = ListUtils.newArrayList();	
            String[] arrstring = StringUtils.split(dictLabels, ",");	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a2 = arrstring[n];	
                a.add(DictUtils.getDictValue(a2, dictType, defaultValue));	
                n3 = ++n;	
            }	
            return StringUtils.join(a, ",");	
        }	
        return defaultValue;	
    }	
	
    public static String getDictLabels(String dictType, String dictValues, String defaultValue) {	
        if (StringUtils.isNotBlank(dictType) && StringUtils.isNotBlank(dictValues)) {	
            int n;	
            ArrayList<String> a = ListUtils.newArrayList();	
            String[] arrstring = StringUtils.split(dictValues, ",");	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a2 = arrstring[n];	
                a.add(DictUtils.getDictLabel(dictType, a2, defaultValue));	
                n3 = ++n;	
            }	
            return StringUtils.join(a, ",");	
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
        if (StringUtils.isNotBlank(dictType) && StringUtils.isNotBlank(dictValue)) {	
            for (DictData a : DictUtils.getDictList(dictType)) {	
                if (!dictType.equals(a.getDictType()) || !dictValue.equals(a.getDictValue())) continue;	
                return a.getDictLabel();	
            }	
        }	
        return defaultValue;	
    }	
	
    public static List<DictData> getDictList(String dictType) {	
        ArrayList<DictData> a;	
        HashMap<String, Object> a2 = (HashMap<String, Object>)CorpUtils.getCache(CACHE_DICT_MAP);	
        if (a2 == null) {	
            Iterator<ArrayList<DictData>> iterator;	
            a2 = MapUtils.newHashMap();	
            a = new DictType();	
            ((DataEntity)((Object)a)).setStatus("0");	
            Iterator<ArrayList<DictData>> iterator2 = iterator = b.ALLATORIxDEMO().findList(a).iterator();	
            while (iterator2.hasNext()) {	
                DictData a3;	
                DictType a4 = (DictType)((Object)iterator.next());	
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
                a2.put(a3.getDictType(), ListUtils.newArrayList(a3));	
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
	
