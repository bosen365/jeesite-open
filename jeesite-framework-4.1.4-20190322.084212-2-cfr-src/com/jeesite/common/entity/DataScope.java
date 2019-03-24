/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.entity;	
	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import org.hyperic.sigar.NfsServerV2;	
import org.hyperic.sigar.pager.PageFetcher;	
	
public class DataScope<T extends DataEntity<?>>	
extends DataEntity<T> {	
    public static final String CTRL_PERMI_MANAGE = "2";	
    private String ctrlData;	
    private static final long serialVersionUID = 1L;	
    private String ctrlType;	
    private String ctrlPermi = "1";	
    public static final String CTRL_PERMI_HAVE = "1";	
	
    public void setCtrlData_in(String[] ctrlDatas) {	
        this.sqlMap.getWhere().and("ctrl_data", QueryType.IN, ctrlDatas);	
    }	
	
    public String getCtrlType() {	
        return this.ctrlType;	
    }	
	
    public void setCtrlType(String ctrlType) {	
        this.ctrlType = ctrlType;	
    }	
	
    public String getCtrlData_in() {	
        return (String)this.sqlMap.getWhere().getValue("ctrl_data", QueryType.IN);	
    }	
	
    public String getCtrlData() {	
        return this.ctrlData;	
    }	
	
    public String getCtrlPermi() {	
        return this.ctrlPermi;	
    }	
	
    public void setCtrlData(String ctrlData) {	
        this.ctrlData = ctrlData;	
    }	
	
    public void setCtrlPermi(String ctrlPermi) {	
        this.ctrlPermi = ctrlPermi;	
    }	
}	
	
