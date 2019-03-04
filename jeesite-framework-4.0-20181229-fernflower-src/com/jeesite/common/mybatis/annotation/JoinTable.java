package com.jeesite.common.mybatis.annotation;	
	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import java.lang.annotation.Documented;	
import java.lang.annotation.ElementType;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import java.lang.annotation.Target;	
	
@Retention(RetentionPolicy.RUNTIME)	
@Target({ElementType.TYPE})	
@Documented	
public @interface JoinTable {	
   Class entity();	
	
   JoinTable.Type type() default JoinTable.Type.JOIN;	
	
   String alias();	
	
   String on();	
	
   String attrName() default "";	
	
   Column[] columns() default {};	
	
   public static enum Type {	
      JOIN("JOIN");	
	
      private final String value;	
      RIGHT_JOIN("RIGHT JOIN"),	
      LEFT_JOIN("LEFT JOIN");	
	
      public String value() {	
         return this.value;	
      }	
	
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
   }	
}	
