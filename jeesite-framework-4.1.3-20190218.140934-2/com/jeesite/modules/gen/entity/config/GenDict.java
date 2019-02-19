package com.jeesite.modules.gen.entity.config;	
	
import java.io.Serializable;	
import javax.xml.bind.annotation.XmlAttribute;	
	
public class GenDict implements Serializable {	
   private String description;	
   private static final long serialVersionUID = 1L;	
   private String type;	
   private String value;	
   private String label;	
   private String classes;	
	
   @XmlAttribute	
   public String getLabel() {	
      return this.label;	
   }	
	
   public GenDict(String value, String var2) {	
      this.value = value;	
      this.label = var2;	
   }	
	
   public void setDescription(String description) {	
      this.description = description;	
   }	
	
   public GenDict() {	
   }	
	
   public String toString() {	
      return this.value;	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 4 << 4 ^ 5 << 1;	
      int var10001 = (3 ^ 5) << 4 ^ 2 ^ 5;	
      int var10002 = (2 ^ 5) << 3 ^ 2 ^ 5;	
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
	
   public void setValue(String value) {	
      this.value = value;	
   }	
	
   @XmlAttribute	
   public String getType() {	
      return this.type;	
   }	
	
   @XmlAttribute	
   public String getDescription() {	
      return this.description;	
   }	
	
   public void setType(String type) {	
      this.type = type;	
   }	
	
   public void setClasses(String classes) {	
      this.classes = classes;	
   }	
	
   @XmlAttribute	
   public String getValue() {	
      return this.value;	
   }	
	
   public void setLabel(String label) {	
      this.label = label;	
   }	
	
   @XmlAttribute	
   public String getClasses() {	
      return this.classes;	
   }	
}	
