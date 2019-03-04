package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.modules.file.utils.FileUploadUtils;	
import com.jeesite.modules.job.l.l;	
import java.util.Map;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
	
public class FromJson implements Function {	
   public Object call(Object[] paras, Context ctx) {	
      String a;	
      if (StringUtils.isBlank(a = ObjectUtils.toString(paras[0]))) {	
         return null;	
      } else {	
         String a;	
         String var10000 = a = ObjectUtils.toString(paras.length < 2 ? "" : paras[1]);	
         String[] var10001 = new String[2];	
         boolean var10003 = true;	
         var10001[0] = "";	
         var10001[1] = "list";	
         if (StringUtils.inString(var10000, var10001)) {	
            return JsonMapper.fromJsonForMapList(a);	
         } else if (StringUtils.equals(a, "map")) {	
            return JsonMapper.fromJson(a, Map.class);	
         } else {	
            try {	
               Class a = Class.forName(a);	
               return JsonMapper.fromJson(a, a);	
            } catch (ClassNotFoundException var6) {	
               return null;	
            }	
         }	
      }	
   }	
}	
