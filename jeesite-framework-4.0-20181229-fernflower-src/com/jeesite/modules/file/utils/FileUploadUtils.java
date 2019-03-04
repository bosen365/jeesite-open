package com.jeesite.modules.file.utils;	
	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.service.FileUploadService;	
import org.apache.commons.lang3.StringUtils;	
	
public class FileUploadUtils {	
   public static void saveFileUpload(String bizKey, String bizType) {	
      if (!StringUtils.isBlank(bizKey) && !StringUtils.isBlank(bizType)) {	
         String a;	
         int var6;	
         int var10000;	
         if (StringUtils.isNotBlank(a = ServletUtils.getParameter(bizType))) {	
            FileUploadService a = null.ALLATORIxDEMO();	
            String[] var4;	
            int var5 = (var4 = a.split(",")).length;	
	
            for(var10000 = var6 = 0; var10000 < var5; var10000 = var6) {	
               String a = var4[var6];	
               FileUpload a = new FileUpload(a);	
               ++var6;	
               a.setBizKey(bizKey);	
               a.setBizType(bizType);	
               a.save(a);	
            }	
         }	
	
         String a;	
         if (StringUtils.isNotBlank(a = ServletUtils.getParameter((new StringBuilder()).insert(0, bizType).append("__del").toString()))) {	
            FileUploadService a = null.ALLATORIxDEMO();	
            String[] var12;	
            var6 = (var12 = a.split(",")).length;	
	
            int var13;	
            for(var10000 = var13 = 0; var10000 < var6; var10000 = var13) {	
               String a = var12[var13];	
               FileUpload a = new FileUpload(a);	
               ++var13;	
               a.delete(a);	
            }	
         }	
	
      }	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 4 << 3 ^ 3;	
      int var10001 = (2 ^ 5) << 4 ^ 5;	
      int var10002 = 4 << 3 ^ 4;	
      int var10003 = s.length();	
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
}	
