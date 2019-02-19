package com.jeesite.modules.file.web;	
	
import com.jeesite.autoconfigure.core.DataSourceAutoConfiguration;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.lang.ByteUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.file.entity.FileEntity;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.entity.FileUploadParams;	
import com.jeesite.modules.file.service.FileEntityService;	
import com.jeesite.modules.file.service.FileUploadService;	
import com.jeesite.modules.file.service.FileUploadServiceExtend;	
import java.io.File;	
import java.io.IOException;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import javax.servlet.ServletException;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.web.bind.annotation.PathVariable;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.RequestMethod;	
import org.springframework.web.bind.annotation.ResponseBody;	
import org.springframework.web.multipart.MultipartFile;	
import org.springframework.web.multipart.MultipartHttpServletRequest;	
	
@Controller	
@RequestMapping({"${adminPath}/file"})	
@ConditionalOnProperty(	
   name = {"file.enabled", "web.core.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class FileUploadController extends BaseController {	
   @Autowired	
   private FileEntityService fileEntityService;	
   @Autowired	
   private FileUploadServiceExtend fileUploadServiceExtend;	
   @Autowired	
   private FileUploadService fileUploadService;	
	
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
         String a;	
         if (StringUtils.isNotBlank(preview)) {	
            String a = StringUtils.contains(a = a.getFileUrl(), "?") ? "&" : "?";	
            return (new StringBuilder()).insert(0, "redirect:").append(a).append(a).append("preview=").append(preview).toString();	
         }	
	
         a = this.fileUploadServiceExtend.downFile(a, request, response);	
         if (!"^0^".equals(a)) {	
            if (StringUtils.isNotBlank(a)) {	
               return (new StringBuilder()).insert(0, "redirect:").append(a).toString();	
            }	
	
            return null;	
         }	
      }	
	
      request.setAttribute("responseStatus", 200);	
      String var10001 = "message";	
      String var10002 = "sys.file.downloadFileNotExist";	
      String[] var10003 = new String[0];	
      boolean var10005 = true;	
      request.setAttribute(var10001, text(var10002, var10003));	
      request.getRequestDispatcher("/error/^0^").forward(request, response);	
      return null;	
   }	
	
   @RequestMapping({"upload"})	
   @ResponseBody	
   public String upload(FileUploadParams params, HttpServletRequest request) {	
      long a = System.currentTimeMillis();	
      HashMap a = MapUtils.newHashMap();	
      a.put("code", "server");	
      String var10002;	
      String[] var10003;	
      boolean var10005;	
      if (!StringUtils.isBlank(params.getFileMd5()) && !StringUtils.isBlank(params.getFileName())) {	
         params.initialize();	
         int a = false;	
         FileEntity a = new FileEntity();	
         a.setFileMd5(params.getFileMd5());	
         (a = this.fileEntityService.getByMd5(a)).setFileUploadParams(params);	
         if (!"0".equals(a.getStatus())) {	
            a = true;	
         }	
	
         if (a) {	
            MultipartFile a = null;	
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
	
            String a = params.getAllowSuffixes();	
            if (!StringUtils.contains(a, "." + params.getFileExtension() + ",")) {	
               a.put("code", "not_allow_type");	
               String a = params.getUploadType();	
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
	
            String a = a.getSize();	
            long a = params.getMaxFileSize();	
            if (a > a) {	
               a.put("code", "exceed_size");	
               var10002 = "sys.file.uploadValidSize";	
               var10003 = new String[1];	
               var10005 = true;	
               var10003[0] = ByteUtils.formatByteSize(a);	
               return this.renderResult("false", text(var10002, var10003));	
            }	
	
            int a = false;	
            if (StringUtils.isBlank(a.getFileId())) {	
               a.setFileId(IdGen.nextId());	
               a.setFileMd5(params.getFileMd5());	
               a.setFilePath(params.getRelativePath());	
               a.setFileContentType(a.getContentType());	
               a.setFileExtension(params.getFileExtension());	
               a.setFileSize(a);	
               a = true;	
            }	
	
            File a;	
            if (!(a = new File(a.getFileRealPath())).getParentFile().exists()) {	
               a.getParentFile().mkdirs();	
            }	
	
            try {	
               a.transferTo(a);	
            } catch (IOException var17) {	
               this.logger.error((new StringBuilder()).insert(0, "文件保存到本地失败：").append(a.getAbsolutePath()).toString(), var17);	
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
	
            this.fileUploadService.compressImage(params, a);	
            this.fileUploadServiceExtend.uploadFile(a);	
            if (a) {	
               this.fileEntityService.insert(a);	
            }	
         }	
	
         FileUpload a = new FileUpload(a);	
         a.setFileName(params.getFileName());	
         a.setFileType(params.getUploadType());	
         this.fileUploadServiceExtend.saveUploadFile(a);	
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
            var10002 = "sys.file.uploadSuccess";	
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
