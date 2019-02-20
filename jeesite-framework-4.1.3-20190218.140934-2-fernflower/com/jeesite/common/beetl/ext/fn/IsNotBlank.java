package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.lang.StringUtils;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
import org.beetl.ext.fn.EmptyExpressionFunction;	
	
public class IsNotBlank implements Function {	
   EmptyExpressionFunction fn = new EmptyExpressionFunction();	
	
   public Object call(Object[] paras, Context ctx) {	
      Object a;	
      if ((a = paras[0]) == null) {	
         return false;	
      } else {	
         return a instanceof String ? StringUtils.isNotBlank((String)a) : !this.fn.call(paras, ctx);	
      }	
   }	
}	
