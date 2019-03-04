package com.jeesite.modules.file.entity;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.validator.ValidatorUtils;	
import java.io.Serializable;	
	
public class FileUploadParms implements Serializable {	
   private String bizKey;	
   private String uploadPath;	
   private static final long serialVersionUID = 1L;	
   private String bizType;	
   private String fileMd5;	
   private String mediaAllowSuffixes;	
   private String allowSuffixes;	
   private Long maxFileSize;	
   private Integer imageMaxWidth = -1;	
   private String relativePath;	
   public static final String FILEUPLOAD_BASE_URL = "fileupload";	
   private String uploadType = "file";	
   private String fileAllowSuffixes;	
   private String imageAllowSuffixes;	
   private String fileName;	
   private Integer imageMaxHeight = -1;	
	
   public void setImageMaxHeight(Integer imageMaxHeight) {	
      this.imageMaxHeight = imageMaxHeight;	
   }	
	
   public String getMediaAllowSuffixes() {	
      return this.mediaAllowSuffixes;	
   }	
	
   public void setMediaAllowSuffixes(String mediaAllowSuffixes) {	
      this.mediaAllowSuffixes = mediaAllowSuffixes;	
   }	
	
   public void setFileMd5(String fileMd5) {	
      this.fileMd5 = fileMd5;	
   }	
	
   public void setMaxFileSize(Long maxFileSize) {	
      this.maxFileSize = maxFileSize;	
   }	
	
   public Long getMaxFileSize() {	
      return this.maxFileSize;	
   }	
	
   public void setFileAllowSuffixes(String fileAllowSuffixes) {	
      this.fileAllowSuffixes = fileAllowSuffixes;	
   }	
	
   public String getBizKey() {	
      return this.bizKey;	
   }	
	
   public void setImageAllowSuffixes(String imageAllowSuffixes) {	
      this.imageAllowSuffixes = imageAllowSuffixes;	
   }	
	
   public String getFileName() {	
      return this.fileName;	
   }	
	
   public void setBizKey(String bizKey) {	
      this.bizKey = bizKey;	
   }	
	
   public String getUploadType() {	
      return this.uploadType;	
   }	
	
   public void setImageMaxWidth(Integer imageMaxWidth) {	
      this.imageMaxWidth = imageMaxWidth;	
   }	
	
   public void setFileName(String fileName) {	
      this.fileName = fileName;	
   }	
	
   public String getRelativePath() {	
      return this.relativePath;	
   }	
	
   public FileUploadParms() {	
      this.initialize();	
   }	
	
   public void setAllowSuffixes(String allowSuffixes) {	
      this.allowSuffixes = allowSuffixes;	
   }	
	
   public void setUploadPath(String uploadPath) {	
      this.uploadPath = uploadPath;	
   }	
	
   public void setBizType(String bizType) {	
      this.bizType = bizType;	
   }	
	
   // $FF: synthetic method	
   private void initialize() {	
      this.uploadPath = Global.getProperty("file.uploadPath", "{yyyy}{MM}/");	
      String[] a;	
      int var10000;	
      if ((a = StringUtils.substringsBetween(this.uploadPath, "{", "}")) != null) {	
         String[] var2 = a;	
         int var3 = a.length;	
	
         int var4;	
         for(var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
            String a;	
            String var11 = a = var2[var4];	
            String[] var10001 = new String[7];	
            boolean var10003 = true;	
            var10001[0] = "yyyy";	
            var10001[1] = "MM";	
            var10001[2] = "dd";	
            var10001[3] = "HH";	
            var10001[4] = "mm";	
            var10001[5] = "ss";	
            var10001[6] = "E";	
            if (StringUtils.inString(var11, var10001)) {	
               this.uploadPath = StringUtils.replace(this.uploadPath, (new StringBuilder()).insert(0, "{").append(a).append("}").toString(), DateUtils.getDate(a));	
            }	
	
            ++var4;	
         }	
      }	
	
      this.relativePath = FileUtils.path(this.uploadPath);	
      String a;	
      if (StringUtils.isNotBlank(a = Global.getProperty("file.maxFileSize"))) {	
         String[] var12 = StringUtils.split(a, "*");	
         this.maxFileSize = 1L;	
         String[] var9 = var12;	
         int var10 = var12.length;	
	
         int var6;	
         for(var10000 = var6 = 0; var10000 < var10; var10000 = var6) {	
            String a = var9[var6];	
            long var13 = this.maxFileSize;	
            long var10002 = Long.valueOf(a);	
            ++var6;	
            this.maxFileSize = var13 * var10002;	
         }	
      } else {	
         this.maxFileSize = 0L;	
      }	
	
      if (this.maxFileSize < 1L) {	
         this.maxFileSize = 5242880L;	
      }	
	
      this.imageAllowSuffixes = Global.getProperty("file.imageAllowSuffixes", "-1");	
      this.mediaAllowSuffixes = Global.getProperty("file.mediaAllowSuffixes", "-1");	
      this.fileAllowSuffixes = Global.getProperty("file.fileAllowSuffixes", "-1");	
      this.allowSuffixes = this.imageAllowSuffixes + "," + this.mediaAllowSuffixes + "," + this.fileAllowSuffixes + ",";	
   }	
	
   public void setRelativePath(String relativePath) {	
      this.relativePath = relativePath;	
   }	
	
   public Integer getImageMaxWidth() {	
      return this.imageMaxWidth;	
   }	
	
   public void setUploadType(String uploadType) {	
      this.uploadType = uploadType;	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 4 << 4 ^ 2 << 1;	
      int var10001 = 5 << 4 ^ (3 ^ 5) << 1;	
      int var10002 = 4 << 3 ^ 1;	
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
	
   public String getUploadPath() {	
      return this.uploadPath;	
   }	
	
   public String getImageAllowSuffixes() {	
      return this.imageAllowSuffixes;	
   }	
	
   public Integer getImageMaxHeight() {	
      return this.imageMaxHeight;	
   }	
	
   public String getAllowSuffixes() {	
      return this.allowSuffixes;	
   }	
	
   public String getFileAllowSuffixes() {	
      return this.fileAllowSuffixes;	
   }	
	
   public String getFileMd5() {	
      return this.fileMd5;	
   }	
	
   public String getBizType() {	
      return this.bizType;	
   }	
}	
