package com.jeesite.modules.file.utils;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.service.FileUploadService;	
import java.util.List;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.Mem;	
import org.hyperic.sigar.ProcState;	
	
public class FileUploadUtils {	
   public static FileUploadService getFileUploadService() {	
      return null.ALLATORIxDEMO();	
   }	
	
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
	
   public static List findFileUpload(String bizKey, String bizType) {	
      List a = null;	
      if (StringUtils.isNotBlank(bizKey) && StringUtils.isNotBlank(bizType)) {	
         FileUploadService a = null.ALLATORIxDEMO();	
         FileUpload a = new FileUpload();	
         a.setBizKey(bizKey);	
         a.setBizType(bizType);	
         return a.findList(a);	
      } else {	
         return ListUtils.newArrayList();	
      }	
   }	
}	
