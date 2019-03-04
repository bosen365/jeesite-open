package com.jeesite.common.l.l;	
	
import com.jeesite.modules.sys.web.AdviceController;	
import com.jeesite.modules.sys.web.ValidCodeController;	
import java.util.HashMap;	
import java.util.Map;	
	
public class E {	
   private static final Map c = new HashMap() {	
      {	
         a.put("JPG", ".jpg");	
      }	
   };	
   public static final String ALLATORIxDEMO = "JPG";	
	
   public static String H(String key) {	
      return (String)c.get(key);	
   }	
	
   public static String ALLATORIxDEMO(String filename) {	
      return filename.substring(filename.lastIndexOf(".")).toLowerCase();	
   }	
}	
