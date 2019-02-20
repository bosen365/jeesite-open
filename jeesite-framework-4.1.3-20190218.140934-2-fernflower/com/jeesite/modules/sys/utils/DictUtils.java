package com.jeesite.modules.sys.utils;	
	
import com.fasterxml.jackson.core.JsonProcessingException;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.modules.sys.entity.DictData;	
import com.jeesite.modules.sys.entity.DictType;	
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
      if (StringUtils.isNotBlank(dictType) && StringUtils.isNotBlank(dictValues)) {	
         List a = ListUtils.newArrayList();	
         String[] var4;	
         int var5 = (var4 = StringUtils.split(dictValues, ",")).length;	
	
         int var6;	
         for(int var10000 = var6 = 0; var10000 < var5; var10000 = var6) {	
            String a = var4[var6];	
            ++var6;	
            a.add(getDictLabel(dictType, a, defaultValue));	
         }	
	
         return StringUtils.join(a, ",");	
      } else {	
         return defaultValue;	
      }	
   }	
	
   public static void clearDictCache() {	
      CorpUtils.removeCache("dictMap");	
   }	
	
   public static String getDictLabel(String dictType, String dictValue, String defaultValue) {	
      if (StringUtils.isNotBlank(dictType) && StringUtils.isNotBlank(dictValue)) {	
         Iterator var3 = getDictList(dictType).iterator();	
	
         while(var3.hasNext()) {	
            DictData a = (DictData)var3.next();	
            if (dictType.equals(a.getDictType()) && dictValue.equals(a.getDictValue())) {	
               return a.getDictLabel();	
            }	
         }	
      }	
	
      return defaultValue;	
   }	
	
   public static String getDictListJson(String dictType) {	
      try {	
         return JsonMapper.getInstance().writerWithView(DictData.SimpleView.class).writeValueAsString(getDictList(dictType));	
      } catch (JsonProcessingException var2) {	
         var2.printStackTrace();	
         return "[]";	
      }	
   }	
	
   public static List getDictList(String dictType) {	
      Object a;	
      if ((a = (Map)CorpUtils.getCache("dictMap")) == null) {	
         a = MapUtils.newHashMap();	
         DictType a;	
         (a = new DictType()).setStatus("0");	
         Iterator var3;	
         Iterator var10000 = var3 = null.ALLATORIxDEMO().findList(a).iterator();	
	
         while(var10000.hasNext()) {	
            DictType a = (DictType)var3.next();	
            ArrayList a = ListUtils.newArrayList();	
            var10000 = var3;	
            ((Map)a).put(a.getDictType(), a);	
         }	
	
         DictData a;	
         (a = new DictData()).setStatus("0");	
         Iterator var9 = null.ALLATORIxDEMO().findList(a).iterator();	
	
         while(var9.hasNext()) {	
            DictData a = (DictData)var9.next();	
            List a;	
            if ((a = (List)((Map)a).get(a.getDictType())) != null) {	
               a.add(a);	
            } else {	
               String var10001 = a.getDictType();	
               DictData[] var10002 = new DictData[1];	
               boolean var10004 = true;	
               var10002[0] = a;	
               ((Map)a).put(var10001, ListUtils.newArrayList(var10002));	
            }	
         }	
	
         CorpUtils.putCache("dictMap", a);	
      }	
	
      Object a;	
      if ((a = (List)((Map)a).get(dictType)) == null) {	
         a = ListUtils.newArrayList();	
      }	
	
      return (List)a;	
   }	
	
   public static String getDictValues(String dictType, String dictLabels, String defaultValue) {	
      if (StringUtils.isNotBlank(dictType) && StringUtils.isNotBlank(dictLabels)) {	
         List a = ListUtils.newArrayList();	
         String[] var4;	
         int var5 = (var4 = StringUtils.split(dictLabels, ",")).length;	
	
         int var6;	
         for(int var10000 = var6 = 0; var10000 < var5; var10000 = var6) {	
            String a = var4[var6];	
            ++var6;	
            a.add(getDictValue(a, dictType, defaultValue));	
         }	
	
         return StringUtils.join(a, ",");	
      } else {	
         return defaultValue;	
      }	
   }	
	
   public static String getDictValue(String dictType, String dictLabel, String defaultValue) {	
      if (StringUtils.isNotBlank(dictType) && StringUtils.isNotBlank(dictLabel)) {	
         Iterator var3 = getDictList(dictType).iterator();	
	
         while(var3.hasNext()) {	
            DictData a = (DictData)var3.next();	
            if (dictType.equals(a.getDictType()) && dictLabel.equals(a.getDictLabel())) {	
               return a.getDictValue();	
            }	
         }	
      }	
	
      return defaultValue;	
   }	
}	
