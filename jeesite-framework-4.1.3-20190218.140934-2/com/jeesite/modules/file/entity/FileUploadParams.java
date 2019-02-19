package com.jeesite.modules.file.entity;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.j.e;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.StringUtils;	
import java.io.Serializable;	
import java.util.Map;	
import org.hyperic.sigar.FileSystemUsage;	
	
public class FileUploadParams implements Serializable {	
   public static final String FILEUPLOAD_BASE_URL = "fileupload";	
   private static final long serialVersionUID = 1L;	
   private Long maxFileSize;	
   private String fileExtension;	
   private String fileMd5;	
   private String uploadPath;	
   private String allowSuffixes;	
   private String imageAllowSuffixes;	
   private String bizKey;	
   private String mediaAllowSuffixes;	
   private String bizType;	
   private Integer imageMaxWidth = -1;	
   private String relativePath;	
   private Integer imageMaxHeight = -1;	
   private String uploadType = "file";	
   private String fileName;	
   private String fileAllowSuffixes;	
   private Map extend = MapUtils.newHashMap();	
	
   public void setImageAllowSuffixes(String imageAllowSuffixes) {	
      this.imageAllowSuffixes = imageAllowSuffixes;	
   }	
	
   public String getAllowSuffixes() {	
      return this.allowSuffixes;	
   }	
	
   public Long getMaxFileSize() {	
      return this.maxFileSize;	
   }	
	
   public void setFileName(String fileName) {	
      this.fileName = fileName;	
   }	
	
   public Map getExtend() {	
      return this.extend;	
   }	
	
   public void setBizKey(String bizKey) {	
      this.bizKey = bizKey;	
   }	
	
   public Integer getImageMaxWidth() {	
      return this.imageMaxWidth;	
   }	
	
   public String getFileAllowSuffixes() {	
      return this.fileAllowSuffixes;	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (3 ^ 5) << 4 ^ 3 << 2 ^ 3;	
      int var10002 = (2 ^ 5) << 4 ^ 5;	
      int var10003 = (s = (String)s).length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10000 = var10002;	
	
      for(byte var2 = 4; var10000 >= 0; var10000 = var3) {	
         int var10001 = var3;	
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
	
   public String getUploadType() {	
      return this.uploadType;	
   }	
	
   public void setMaxFileSize(Long maxFileSize) {	
      this.maxFileSize = maxFileSize;	
   }	
	
   public void setAllowSuffixes(String allowSuffixes) {	
      this.allowSuffixes = allowSuffixes;	
   }	
	
   public void setImageMaxHeight(Integer imageMaxHeight) {	
      this.imageMaxHeight = imageMaxHeight;	
   }	
	
   public void setRelativePath(String relativePath) {	
      this.relativePath = relativePath;	
   }	
	
   public String getFileExtension() {	
      return this.fileExtension;	
   }	
	
   public void setUploadPath(String uploadPath) {	
      this.uploadPath = uploadPath;	
   }	
	
   public void setFileAllowSuffixes(String fileAllowSuffixes) {	
      this.fileAllowSuffixes = fileAllowSuffixes;	
   }	
	
   public void setMediaAllowSuffixes(String mediaAllowSuffixes) {	
      this.mediaAllowSuffixes = mediaAllowSuffixes;	
   }	
	
   public void setFileMd5(String fileMd5) {	
      this.fileMd5 = fileMd5;	
   }	
	
   public String getUploadPath() {	
      return this.uploadPath;	
   }	
	
   public void setFileExtension(String fileExtension) {	
      this.fileExtension = fileExtension;	
   }	
	
   public Integer getImageMaxHeight() {	
      return this.imageMaxHeight;	
   }	
	
   public String getMediaAllowSuffixes() {	
      return this.mediaAllowSuffixes;	
   }	
	
   public void setExtend(Map extend) {	
      this.extend = extend;	
   }	
	
   public String getRelativePath() {	
      return this.relativePath;	
   }	
	
   public FileUploadParams initialize() {	
      this.uploadPath = Global.getProperty(e.ALLATORIxDEMO("\u000eu\u0004yFi\u0018p\u0007}\fL\th\u0000"), "yyyy}MM}/");	
      String[] a;	
      int var10000;	
      if ((a = StringUtils.substringsBetween(this.uploadPath, e.ALLATORIxDEMO("\u0013"), "}")) != null) {	
         String[] var2 = a;	
         int var3 = a.length;	
	
         int var4;	
         for(var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
            String a;	
            String var11 = a = var2[var4];	
            String[] var10001 = new String[7];	
            boolean var10003 = true;	
            var10001[0] = e.ALLATORIxDEMO("e\u0011e\u0011");	
            var10001[1] = "MM";	
            var10001[2] = e.ALLATORIxDEMO("x\f");	
            var10001[3] = "HH";	
            var10001[4] = e.ALLATORIxDEMO("q\u0005");	
            var10001[5] = "ss";	
            var10001[6] = e.ALLATORIxDEMO("-");	
            if (StringUtils.inString(var11, var10001)) {	
               this.uploadPath = StringUtils.replace(this.uploadPath, (new StringBuilder()).insert(0, "").append(a).append(e.ALLATORIxDEMO("\u0015")).toString(), DateUtils.getDate(a));	
            }	
	
            ++var4;	
         }	
      }	
	
      this.relativePath = FileUtils.path(this.uploadPath);	
      String a;	
      if (StringUtils.isNotBlank(a = Global.getProperty("file.maxFileSize"))) {	
         String[] var12 = StringUtils.split(a, e.ALLATORIxDEMO("B"));	
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
	
      if (this.fileName != null) {	
         this.fileName = this.fileName.trim();	
         this.fileExtension = FileUtils.getFileExtension(this.fileName);	
      }	
	
      this.imageAllowSuffixes = Global.getProperty("file.imageAllowSuffixes", e.ALLATORIxDEMO("1Y"));	
      this.mediaAllowSuffixes = Global.getProperty("file.mediaAllowSuffixes", e.ALLATORIxDEMO("1Y"));	
      this.fileAllowSuffixes = Global.getProperty("file.fileAllowSuffixes", e.ALLATORIxDEMO("1Y"));	
      if ("image".equals(this.uploadType)) {	
         this.allowSuffixes = this.imageAllowSuffixes;	
         return this;	
      } else if ("media".equals(this.uploadType)) {	
         this.allowSuffixes = this.mediaAllowSuffixes;	
         return this;	
      } else if ("file".equals(this.uploadType)) {	
         this.allowSuffixes = this.fileAllowSuffixes;	
         return this;	
      } else {	
         if (StringUtils.contains(this.imageAllowSuffixes, (new StringBuilder()).insert(0, ".").append(this.fileExtension).append(",").toString())) {	
            this.uploadType = "image";	
         }	
	
         if (StringUtils.contains(this.mediaAllowSuffixes, (new StringBuilder()).insert(0, ".").append(this.fileExtension).append(e.ALLATORIxDEMO("D")).toString())) {	
            this.uploadType = "media";	
         }	
	
         if (StringUtils.contains(this.fileAllowSuffixes, (new StringBuilder()).insert(0, ".").append(this.fileExtension).append(",").toString())) {	
            this.uploadType = "file";	
         }	
	
         this.allowSuffixes = (new StringBuilder()).insert(0, this.imageAllowSuffixes).append(e.ALLATORIxDEMO("D")).append(this.mediaAllowSuffixes).append(",").append(this.fileAllowSuffixes).append(e.ALLATORIxDEMO("D")).toString();	
         return this;	
      }	
   }	
	
   public void setBizType(String bizType) {	
      this.bizType = bizType;	
   }	
	
   public String getFileName() {	
      return this.fileName;	
   }	
	
   public String getImageAllowSuffixes() {	
      return this.imageAllowSuffixes;	
   }	
	
   public void setImageMaxWidth(Integer imageMaxWidth) {	
      this.imageMaxWidth = imageMaxWidth;	
   }	
	
   public void setUploadType(String uploadType) {	
      this.uploadType = uploadType;	
   }	
	
   public String getBizType() {	
      return this.bizType;	
   }	
	
   public String getBizKey() {	
      return this.bizKey;	
   }	
	
   public String getFileMd5() {	
      return this.fileMd5;	
   }	
}	
