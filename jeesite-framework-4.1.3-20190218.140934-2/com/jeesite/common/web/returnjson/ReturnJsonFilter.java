package com.jeesite.common.web.returnjson;	
	
import com.fasterxml.jackson.annotation.JsonFilter;	
import com.fasterxml.jackson.core.JsonGenerator;	
import com.fasterxml.jackson.databind.SerializerProvider;	
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;	
import com.fasterxml.jackson.databind.ser.FilterProvider;	
import com.fasterxml.jackson.databind.ser.PropertyFilter;	
import com.fasterxml.jackson.databind.ser.PropertyWriter;	
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;	
import java.util.Arrays;	
import java.util.HashMap;	
import java.util.HashSet;	
import java.util.Map;	
import java.util.Set;	
	
@JsonFilter("ReturnJsonFilter")	
public class ReturnJsonFilter extends FilterProvider {	
   Map filterMap = new HashMap();	
   Map includeMap = new HashMap();	
	
   public BeanPropertyFilter findFilter(Object filterId) {	
      throw new UnsupportedOperationException(com.jeesite.common.j.E.ALLATORIxDEMO("%\u0010\u0007\u0016\u0017\u0000D\u0007\u000bS\u0000\u0016\u0014\u0001\u0001\u0010\u0005\u0007\u0001\u0017D\u0015\r\u001f\u0010\u0016\u0016\u0000D\u001d\u000b\u0007D\u0000\u0011\u0003\u0014\u001c\u0016\u0007\u0001\u0017"));	
   }	
	
   public void filter(Class type, String[] fields) {	
      this.addToMap(this.filterMap, type, fields);	
   }	
	
   public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {	
      return new SimpleBeanPropertyFilter() {	
         public void serializeAsField(Object axx, JsonGenerator axxxx, SerializerProvider ax, PropertyWriter axxx) throws Exception {	
            if (ReturnJsonFilter.this.apply(axx.getClass(), axxx.getName())) {	
               axxx.serializeAsField(axx, axxxx, ax);	
            } else {	
               if (!axxxx.canOmitFields()) {	
                  axxx.serializeAsOmittedField(axx, axxxx, ax);	
               }	
	
            }	
         }	
      };	
   }	
	
   // $FF: synthetic method	
   private void addToMap(Map map, Class type, String[] fields) {	
      Object a;	
      if ((a = (Set)map.get(type)) == null) {	
         a = new HashSet();	
      }	
	
      ((Set)a).addAll(Arrays.asList(fields));	
      map.put(type, a);	
   }	
	
   public void include(Class type, String[] fields) {	
      this.addToMap(this.includeMap, type, fields);	
   }	
	
   public boolean apply(Class type, String name) {	
      Set a = (Set)this.includeMap.get(type);	
      Set a = (Set)this.filterMap.get(type);	
      if (a != null && a.contains(name)) {	
         return true;	
      } else if (a != null && !a.contains(name)) {	
         return true;	
      } else {	
         return a == null && a == null;	
      }	
   }	
}	
