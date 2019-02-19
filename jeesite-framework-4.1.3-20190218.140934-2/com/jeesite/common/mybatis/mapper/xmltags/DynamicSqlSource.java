package com.jeesite.common.mybatis.mapper.xmltags;	
	
import org.apache.ibatis.scripting.xmltags.SqlNode;	
import org.apache.ibatis.session.Configuration;	
	
public class DynamicSqlSource extends org.apache.ibatis.scripting.xmltags.DynamicSqlSource {	
   private int weight;	
	
   public DynamicSqlSource(Configuration configuration, SqlNode rootSqlNode) {	
      super(configuration, rootSqlNode);	
   }	
	
   public int getWeight() {	
      return this.weight;	
   }	
	
   public void setWeight(int weight) {	
      this.weight = weight;	
   }	
}	
