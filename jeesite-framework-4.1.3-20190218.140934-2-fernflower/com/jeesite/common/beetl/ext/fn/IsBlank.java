package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.lang.StringUtils;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
import org.beetl.ext.fn.EmptyExpressionFunction;	
	
public class IsBlank implements Function {	
   EmptyExpressionFunction fn = new EmptyExpressionFunction();	
	
   public Object call(Object[] paras, Context ctx) {	
      Object a;	
      if ((a = paras[0]) == null) {	
         return true;	
      } else {	
         return a instanceof String ? StringUtils.isBlank((String)a) : this.fn.call(paras, ctx);	
      }	
   }	
}	
