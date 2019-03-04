package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import com.jeesite.modules.sys.web.ValidCodeController;	
	
public enum QueryType {	
   NOT_IN("NOT IN"),	
   GT(">"),	
   IS_NULL("IS NULL"),	
   IN("IN");	
	
   private final String operator;	
   LTE("<="),	
   LEFT_LIKE("LIKE", "%", ""),	
   NE_FORCE("!=", true),	
   EQ_FORCE("=", true),	
   GTE(">=");	
	
   private final String valuePrefix;	
   LT("<"),	
   IS_NOT_NULL("IS NOT NULL"),	
   EQ("=");	
	
   private final Boolean isForce;	
   LIKE("LIKE", "%", "%"),	
   NE("!="),	
   RIGHT_LIKE("LIKE", "", "%");	
	
   private final String valueSuffux;	
	
   // $FF: synthetic method	
   private QueryType(String operator, String valuePrefix, String valueSuffux) {	
      this(operator, valuePrefix, valueSuffux, false);	
   }	
	
   // $FF: synthetic method	
   private QueryType(String operator) {	
      this(operator, "", "", false);	
   }	
	
   public Boolean isForce() {	
      return this.isForce;	
   }	
	
   public String valueSuffux() {	
      return this.valueSuffux;	
   }	
	
   static {	
      QueryType[] var10000 = new QueryType[15];	
      boolean var10002 = true;	
      var10000[0] = EQ;	
      var10000[1] = NE;	
      var10000[2] = GT;	
      var10000[3] = GTE;	
      var10000[4] = LT;	
      var10000[5] = LTE;	
      var10000[6] = IN;	
      var10000[7] = NOT_IN;	
      var10000[8] = LIKE;	
      var10000[9] = LEFT_LIKE;	
      var10000[10] = RIGHT_LIKE;	
      var10000[11] = IS_NULL;	
      var10000[12] = IS_NOT_NULL;	
      var10000[13] = EQ_FORCE;	
      var10000[14] = NE_FORCE;	
   }	
	
   // $FF: synthetic method	
   private QueryType(String operator, String valuePrefix, String valueSuffux, Boolean var6) {	
      this.operator = operator;	
      this.valuePrefix = valuePrefix;	
      this.valueSuffux = valueSuffux;	
      this.isForce = var6;	
   }	
	
   public String valuePrefix() {	
      return this.valuePrefix;	
   }	
	
   // $FF: synthetic method	
   private QueryType(String operator, Boolean isForce) {	
      this(operator, "", "", isForce);	
   }	
	
   public String operator() {	
      return this.operator;	
   }	
}	
