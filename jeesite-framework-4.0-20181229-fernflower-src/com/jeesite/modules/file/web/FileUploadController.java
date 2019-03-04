package com.jeesite.modules.file.web;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.lang.ByteUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.file.entity.FileEntity;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.entity.FileUploadParms;	
import com.jeesite.modules.file.service.FileEntityService;	
import com.jeesite.modules.file.service.FileUploadService;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.awt.image.BufferedImage;	
import java.io.File;	
import java.io.IOException;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import javax.imageio.ImageIO;	
import javax.servlet.ServletException;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import net.coobird.thumbnailator.Thumbnails;	
import net.coobird.thumbnailator.Thumbnails.Builder;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Controller;	
import org.springframework.web.bind.annotation.PathVariable;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.RequestMethod;	
import org.springframework.web.bind.annotation.ResponseBody;	
import org.springframework.web.multipart.MultipartHttpServletRequest;	
	
@Controller	
@RequestMapping({"${adminPath}/file"})	
public class FileUploadController extends BaseController {	
   @Autowired	
   private FileUploadService fileUploadService;	
   @Autowired	
   private FileEntityService fileEntityService;	
	
   @RequestMapping({"fileList"})	
   @ResponseBody	
   public String fileList(FileUpload fileUpload) {	
      List a;	
      return StringUtils.isNotBlank(fileUpload.getBizKey()) && StringUtils.isNotBlank(fileUpload.getBizType()) && (a = this.fileUploadService.findList(fileUpload)) != null && a.size() > 0 ? JsonMapper.toJson(a) : this.renderResult("false", "No files.");	
   }	
	
   @RequestMapping(	
      value = {"/download/{fileUploadId}"},	
      method = {RequestMethod.GET}	
   )	
   public String download(@PathVariable("fileUploadId") String fileUploadId, String preview, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
      FileUpload a;	
      if ((a = (FileUpload)this.fileUploadService.get((String)fileUploadId)) != null && a.getFileEntity() != null && a.getFileEntity().getFileMd5() != null) {	
         FileEntity a = a.getFileEntity();	
         if (StringUtils.isNotBlank(preview)) {	
            return (new StringBuilder()).insert(0, "redirect:").append(a.getFileUrl()).append("?preview=").append(preview).toString();	
         }	
	
         File a;	
         if ((a = new File(a.getFileRealPath())).exists()) {	
            FileUtils.downFile(a, request, response, a.getFileName());	
            return null;	
         }	
      }	
	
      request.setAttribute("responseStatus", 200);	
      String var10001 = "message";	
      String var10002 = "sys.file.downloadFileNotExist";	
      String[] var10003 = new String[0];	
      boolean var10005 = true;	
      request.setAttribute(var10001, text(var10002, var10003));	
      request.getRequestDispatcher("/error/404").forward(request, response);	
      return null;	
   }	
	
   @RequestMapping({"upload"})	
   @ResponseBody	
   public String upload(FileUploadParms fileUploadParms, HttpServletRequest request, HttpServletResponse response) {	
      long a = System.currentTimeMillis();	
      HashMap a = MapUtils.newHashMap();	
      a.put("code", "server");	
      String var10002;	
      String[] var10003;	
      boolean var10005;	
      if (!StringUtils.isBlank(fileUploadParms.getFileMd5()) && !StringUtils.isBlank(fileUploadParms.getFileName())) {	
         String a = fileUploadParms.getUploadType();	
         String a = "";	
         if ("image".equals(a)) {	
            a = fileUploadParms.getImageAllowSuffixes();	
         } else if ("media".equals(a)) {	
            a = fileUploadParms.getMediaAllowSuffixes();	
         } else if ("file".equals(a)) {	
            a = fileUploadParms.getFileAllowSuffixes();	
         } else {	
            a = fileUploadParms.getAllowSuffixes();	
            a = "";	
         }	
	
         int a = false;	
         FileEntity a = new FileEntity();	
         a.setFileMd5(fileUploadParms.getFileMd5());	
         a = this.fileEntityService.getByMd5(a);	
         FileUploadParms var10000;	
         if (!"0".equals(a.getStatus())) {	
            a = true;	
            var10000 = fileUploadParms;	
         } else {	
            if (!(new File(a.getFileRealPath())).exists()) {	
               a = true;	
            }	
	
            var10000 = fileUploadParms;	
         }	
	
         String a = var10000.getFileName().trim();	
         String a = FileUtils.getFileExtension(fileUploadParms.getFileName());	
         if (StringUtils.isBlank(a)) {	
            String a = fileUploadParms.getImageAllowSuffixes();	
            if (StringUtils.contains(a, "." + a + ",")) {	
               a = "image";	
            }	
	
            if (StringUtils.contains(fileUploadParms.getMediaAllowSuffixes(), (new StringBuilder()).insert(0, ".").append(a).append(",").toString())) {	
               a = "media";	
            }	
	
            if (StringUtils.contains(fileUploadParms.getFileAllowSuffixes(), (new StringBuilder()).insert(0, ".").append(a).append(",").toString())) {	
               a = "file";	
            }	
         }	
	
         if (a) {	
            String a = null;	
            MultipartHttpServletRequest a;	
            Iterator a;	
            if (request instanceof MultipartHttpServletRequest && (a = (a = (MultipartHttpServletRequest)request).getFileNames()).hasNext()) {	
               a = a.getFile((String)a.next());	
            }	
	
            if (a == null || a.isEmpty() || a.getOriginalFilename() == null) {	
               var10002 = "sys.file.uploadFileIsEmpty";	
               var10003 = new String[0];	
               var10005 = true;	
               return this.renderResult("false", text(var10002, var10003), a);	
            }	
	
            long a = a.getSize();	
            long a = fileUploadParms.getMaxFileSize();	
            if (!StringUtils.contains(a, (new StringBuilder()).insert(0, ".").append(a).append(",").toString())) {	
               a.put("code", "not_allow_type");	
               if ("image".equals(a)) {	
                  var10002 = "sys.file.uploadValidImage";	
                  var10003 = new String[1];	
                  var10005 = true;	
                  var10003[0] = a;	
                  return this.renderResult("false", text(var10002, var10003), a);	
               }	
	
               if ("media".equals(a)) {	
                  var10002 = "sys.file.uploadValidVideo";	
                  var10003 = new String[1];	
                  var10005 = true;	
                  var10003[0] = a;	
                  return this.renderResult("false", text(var10002, var10003), a);	
               }	
	
               if ("file".equals(a)) {	
                  var10002 = "sys.file.uploadValidFile";	
                  var10003 = new String[1];	
                  var10005 = true;	
                  var10003[0] = a;	
                  return this.renderResult("false", text(var10002, var10003), a);	
               }	
	
               var10002 = "sys.file.uploadValidAll";	
               var10003 = new String[1];	
               var10005 = true;	
               var10003[0] = a;	
               return this.renderResult("false", text(var10002, var10003), a);	
            }	
	
            if (a > a) {	
               a.put("code", "ex\teed_size");	
               var10002 = "sys.file.uploadValidSize";	
               var10003 = new String[1];	
               var10005 = true;	
               var10003[0] = ByteUtils.formatByteSize(a);	
               return this.renderResult("false", text(var10002, var10003));	
            }	
	
            int a = false;	
            if (StringUtils.isBlank(a.getFileId())) {	
               a.setFileId(IdGen.nextId());	
               a.setFileMd5(fileUploadParms.getFileMd5());	
               a.setFilePath(fileUploadParms.getRelativePath());	
               a.setFileContentType(a.getContentType());	
               a.setFileExtension(a);	
               a.setFileSize(a);	
               a = true;	
            }	
	
            File a;	
            if (!(a = new File(a.getFileRealPath())).getParentFile().exists()) {	
               a.getParentFile().mkdirs();	
            }	
	
            try {	
               a.transferTo(a);	
            } catch (IOException var25) {	
               this.logger.error((new StringBuilder()).insert(0, "文件保存到本地失败：").append(a.getAbsolutePath()).toString(), var25);	
            }	
	
            String a;	
            if (StringUtils.isNotBlank(a = Global.getConfig("file.allowContentTypes")) && !StringUtils.inString(FileUtils.getRealContentType(a), a.split(","))) {	
               a.delete();	
               a.put("code", "not_allow_type");	
               var10002 = "sys.file.uploadValidContent";	
               var10003 = new String[0];	
               var10005 = true;	
               return this.renderResult("false", text(var10002, var10003));	
            }	
	
            if (a) {	
               this.fileEntityService.insert(a);	
            }	
	
            if ("image".equals(a)) {	
               String[] var10001 = new String[1];	
               boolean var35 = true;	
               var10001[0] = "gif";	
               if (!StringUtils.inString(a, var10001)) {	
                  try {	
                     int a = -1;	
                     int a = -1;	
                     if (ObjectUtils.toInteger(fileUploadParms.getImageMaxWidth()) > 0) {	
                        a = fileUploadParms.getImageMaxWidth();	
                     }	
	
                     if (ObjectUtils.toInteger(fileUploadParms.getImageMaxHeight()) > 0) {	
                        a = fileUploadParms.getImageMaxHeight();	
                     }	
	
                     if (a != -1 || a != -1) {	
                        File[] var31 = new File[1];	
                        boolean var33 = true;	
                        var31[0] = a;	
                        Builder a = Thumbnails.of(var31);	
                        BufferedImage a;	
                        if ((a = ImageIO.read(a)) != null) {	
                           int var32;	
                           label126: {	
                              if (a != -1) {	
                                 if (a.getWidth() <= fileUploadParms.getImageMaxWidth()) {	
                                    var32 = a;	
                                    a.width(a.getWidth());	
                                    break label126;	
                                 }	
	
                                 a.width(a);	
                              }	
	
                              var32 = a;	
                           }	
	
                           Builder var34;	
                           label120: {	
                              if (var32 != -1) {	
                                 if (a.getHeight() <= fileUploadParms.getImageMaxHeight()) {	
                                    var34 = a;	
                                    a.height(a.getHeight());	
                                    break label120;	
                                 }	
	
                                 a.height(a);	
                              }	
	
                              var34 = a;	
                           }	
	
                           var34.toFile(a);	
                        }	
                     }	
                  } catch (IOException var26) {	
                     this.logger.error((new StringBuilder()).insert(0, "图片压缩失败：").append(a.getAbsoluteFile()).toString(), var26);	
                  }	
               }	
            }	
         }	
	
         FileUpload a = new FileUpload(a);	
         a.setFileName(a);	
         a.setFileType(a);	
         this.fileUploadService.save(a);	
         a.put("fileUpload", a);	
         String a = TimeUtils.formatDateAgo(System.currentTimeMillis() - a);	
         if (!a) {	
            var10002 = "sys.file.uploadSuccessSeconds";	
            var10003 = new String[1];	
            var10005 = true;	
            var10003[0] = a;	
            return this.renderResult("true", text(var10002, var10003), a);	
         } else {	
            var10002 = "sys.file.uploadSu\tcess";	
            var10003 = new String[1];	
            var10005 = true;	
            var10003[0] = a;	
            return this.renderResult("true", text(var10002, var10003), a);	
         }	
      } else {	
         var10002 = "sys.file.uploadValidNotBlank";	
         var10003 = new String[0];	
         var10005 = true;	
         return this.renderResult("false", text(var10002, var10003), a);	
      }	
   }	
}	
