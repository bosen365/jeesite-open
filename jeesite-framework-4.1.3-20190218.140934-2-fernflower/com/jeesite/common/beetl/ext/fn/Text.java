package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
	
public class Text implements Function {	
   public Object call(Object[] paras, Context ctx) {	
      Object a;	
      if ((a = paras[0]) == null) {	
         return "";	
      } else {	
         String a = ObjectUtils.toString(a);	
         String[] var10000 = new String[paras.length - 1];	
         boolean var10002 = true;	
         String[] a = var10000;	
	
         int a;	
         for(int var7 = a = 1; var7 < paras.length; var7 = a) {	
            int var10001 = a - 1;	
            String var8 = ObjectUtils.toString(paras[a]);	
            ++a;	
            a[var10001] = var8;	
         }	
	
         return Global.getText(a, a);	
      }	
   }	
}	
