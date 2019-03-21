/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.scripting.xmltags.DynamicSqlSource	
 *  org.apache.ibatis.scripting.xmltags.SqlNode	
 *  org.apache.ibatis.session.Configuration	
 */	
package com.jeesite.common.mybatis.mapper.xmltags;	
	
import org.apache.ibatis.scripting.xmltags.SqlNode;	
import org.apache.ibatis.session.Configuration;	
	
public class DynamicSqlSource	
extends org.apache.ibatis.scripting.xmltags.DynamicSqlSource {	
    private int weight;	
	
    public DynamicSqlSource(Configuration configuration, SqlNode rootSqlNode) {	
        super(configuration, rootSqlNode);	
    }	
	
    public void setWeight(int weight) {	
        this.weight = weight;	
    }	
	
    public int getWeight() {	
        return this.weight;	
    }	
}	
	
