package com.jeesite.common.mybatis.annotation;	
	
import java.lang.annotation.Documented;	
import java.lang.annotation.ElementType;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import java.lang.annotation.Target;	
import org.hyperic.sigar.ProcTime;	
	
@Retention(RetentionPolicy.RUNTIME)	
@Target({ElementType.TYPE})	
@Documented	
public @interface JoinTable {	
   String alias();	
	
   JoinTable.Type type() default JoinTable.Type.JOIN;	
	
   Class entity();	
	
   Column[] columns() default {};	
	
   String on();	
	
   String attrName() default "";	
	
   public static enum Type {	
      RIGHT_JOIN("RIGHT JOIN"),	
      LEFT_JOIN("LEFT JOIN");	
	
      private final String value;	
      JOIN("JOIN");	
	
      static {	
         JoinTable.Type[] var10000 = new JoinTable.Type[3];	
         boolean var10002 = true;	
         var10000[0] = JOIN;	
         var10000[1] = LEFT_JOIN;	
         var10000[2] = RIGHT_JOIN;	
      }	
	
      // $FF: synthetic method	
      private Type(String var3) {	
         this.value = var3;	
      }	
	
      public String value() {	
         return this.value;	
      }	
   }	
}	
