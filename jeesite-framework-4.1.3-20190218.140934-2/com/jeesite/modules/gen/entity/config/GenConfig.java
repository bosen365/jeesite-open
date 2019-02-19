package com.jeesite.modules.gen.entity.config;	
	
import java.io.Serializable;	
import java.util.List;	
import javax.xml.bind.annotation.XmlElement;	
import javax.xml.bind.annotation.XmlElementWrapper;	
import javax.xml.bind.annotation.XmlRootElement;	
	
@XmlRootElement(	
   name = "config"	
)	
public class GenConfig implements Serializable {	
   private List tplCategoryList;	
   private List fieldValidList;	
   private List queryTypeList;	
   private List gridRowColList;	
   private List showTypeList;	
   private List attrTypeList;	
   private static final long serialVersionUID = 1L;	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (2 ^ 5) << 3 ^ 3 ^ 5;	
      int var10001 = 1 << 3 ^ 2;	
      int var10002 = (3 ^ 5) << 4 ^ 2 << 2 ^ 3;	
      int var10003 = (s = (String)s).length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10001 = var10000;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   @XmlElementWrapper(	
      name = "gridRowCol"	
   )	
   @XmlElement(	
      name = "dict"	
   )	
   public List getGridRowColList() {	
      return this.gridRowColList;	
   }	
	
   @XmlElementWrapper(	
      name = "attrType"	
   )	
   @XmlElement(	
      name = "dict"	
   )	
   public List getAttrTypeList() {	
      return this.attrTypeList;	
   }	
	
   @XmlElementWrapper(	
      name = "showType"	
   )	
   @XmlElement(	
      name = "dict"	
   )	
   public List getShowTypeList() {	
      return this.showTypeList;	
   }	
	
   @XmlElementWrapper(	
      name = "queryType"	
   )	
   @XmlElement(	
      name = "dict"	
   )	
   public List getQueryTypeList() {	
      return this.queryTypeList;	
   }	
	
   public void setGridRowColList(List gridRowColList) {	
      this.gridRowColList = gridRowColList;	
   }	
	
   @XmlElementWrapper(	
      name = "tplCategory"	
   )	
   @XmlElement(	
      name = "category"	
   )	
   public List getTplCategoryList() {	
      return this.tplCategoryList;	
   }	
	
   @XmlElementWrapper(	
      name = "fieldValid"	
   )	
   @XmlElement(	
      name = "dict"	
   )	
   public List getFieldValidList() {	
      return this.fieldValidList;	
   }	
	
   public void setFieldValidList(List fieldValidList) {	
      this.fieldValidList = fieldValidList;	
   }	
	
   public void setAttrTypeList(List attrTypeList) {	
      this.attrTypeList = attrTypeList;	
   }	
	
   public void setTplCategoryList(List tplCategoryList) {	
      this.tplCategoryList = tplCategoryList;	
   }	
	
   public void setShowTypeList(List showTypeList) {	
      this.showTypeList = showTypeList;	
   }	
	
   public void setQueryTypeList(List queryTypeList) {	
      this.queryTypeList = queryTypeList;	
   }	
}	
