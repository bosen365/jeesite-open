package com.jeesite.common.l.l;	
	
import com.jeesite.modules.sys.web.ValidCodeController;	
import java.util.HashMap;	
import java.util.Map;	
	
public class g {	
   public static final Map ALLATORIxDEMO = new HashMap() {	
      {	
         a.put("image/gif", ".gif");	
         a.put("image/jpeg", ".jpg");	
         a.put("image/jpg", ".jpg");	
         a.put("image/png", ".png");	
         a.put("image/bmp", ".bmp");	
      }	
   };	
	
   public static String ALLATORIxDEMO(String mime) {	
      return (String)ALLATORIxDEMO.get(mime);	
   }	
}	
