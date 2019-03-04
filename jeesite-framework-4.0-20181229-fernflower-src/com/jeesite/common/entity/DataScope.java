package com.jeesite.common.entity;	
	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.modules.sys.web.AdviceController;	
	
public class DataScope extends DataEntity {	
   private String ctrlData;	
   private static final long serialVersionUID = 1L;	
   public static final String CTRL_PERMI_HAVE = "1";	
   public static final String CTRL_PERMI_MANAGE = "2";	
   private String ctrlPermi = "1";	
   private String ctrlType;	
	
   public String getCtrlPermi() {	
      return this.ctrlPermi;	
   }	
	
   public void setCtrlData_in(String[] ctrlDatas) {	
      this.sqlMap.getWhere().and("ctrl_data", QueryType.IN, ctrlDatas);	
   }	
	
   public void setCtrlData(String ctrlData) {	
      this.ctrlData = ctrlData;	
   }	
	
   public String getCtrlData_in() {	
      return (String)this.sqlMap.getWhere().getValue("ctrl_data", QueryType.IN);	
   }	
	
   public void setCtrlType(String ctrlType) {	
      this.ctrlType = ctrlType;	
   }	
	
   public String getCtrlType() {	
      return this.ctrlType;	
   }	
	
   public void setCtrlPermi(String ctrlPermi) {	
      this.ctrlPermi = ctrlPermi;	
   }	
	
   public String getCtrlData() {	
      return this.ctrlData;	
   }	
}	
