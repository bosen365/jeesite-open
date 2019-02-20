/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.io.FileUtils	
 *  com.jeesite.common.lang.ByteUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.lang.TimeUtils	
 *  com.jeesite.common.mapper.JsonMapper	
 *  javax.servlet.RequestDispatcher	
 *  javax.servlet.ServletException	
 *  javax.servlet.ServletRequest	
 *  javax.servlet.ServletResponse	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.slf4j.Logger	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.stereotype.Controller	
 *  org.springframework.web.bind.annotation.PathVariable	
 *  org.springframework.web.bind.annotation.RequestMapping	
 *  org.springframework.web.bind.annotation.RequestMethod	
 *  org.springframework.web.bind.annotation.ResponseBody	
 *  org.springframework.web.multipart.MultipartFile	
 *  org.springframework.web.multipart.MultipartHttpServletRequest	
 */	
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
import javax.servlet.RequestDispatcher;	
import javax.servlet.ServletException;	
import javax.servlet.ServletRequest;	
import javax.servlet.ServletResponse;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.slf4j.Logger;	
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
@RequestMapping(value={"${adminPath}/file"})	
@ConditionalOnProperty(name={"file.enabled", "web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class FileUploadController	
extends BaseController {	
    @Autowired	
    private FileEntityService fileEntityService;	
    @Autowired	
    private FileUploadServiceExtend fileUploadServiceExtend;	
    @Autowired	
    private FileUploadService fileUploadService;	
	
    @RequestMapping(value={"fileList"})	
    @ResponseBody	
    public String fileList(FileUpload fileUpload) {	
        List<FileUpload> a2;	
        if (StringUtils.isNotBlank((CharSequence)fileUpload.getBizKey()) && StringUtils.isNotBlank((CharSequence)fileUpload.getBizType()) && (a2 = this.fileUploadService.findList(fileUpload)) != null && a2.size() > 0) {	
            return JsonMapper.toJson(a2);	
        }	
        return this.renderResult("false", "No files.");	
    }	
	
    @RequestMapping(value={"/download/{fileUploadId}"}, method={RequestMethod.GET})	
    public String download(@PathVariable(value="fileUploadId") String fileUploadId, String preview, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
        FileUpload a2 = (FileUpload)this.fileUploadService.get(fileUploadId);	
        if (a2 != null && a2.getFileEntity() != null && a2.getFileEntity().getFileMd5() != null) {	
            if (StringUtils.isNotBlank((CharSequence)preview)) {	
                String a3 = a2.getFileUrl();	
                String a4 = StringUtils.contains((CharSequence)a3, (CharSequence)"?") ? "&" : "?";	
                return new StringBuilder().insert(0, "redirect:").append(a3).append(a4).append("preview=").append(preview).toString();	
            }	
            String a5 = this.fileUploadServiceExtend.downFile(a2, request, response);	
            if (!"^0^".equals(a5)) {	
                if (StringUtils.isNotBlank((CharSequence)a5)) {	
                    return new StringBuilder().insert(0, "redirect:").append(a5).toString();	
                }	
                return null;	
            }	
        }	
        request.setAttribute("responseStatus", (Object)200);	
        request.setAttribute("message", (Object)FileUploadController.text("sys.file.downloadFileNotExist", new String[0]));	
        request.getRequestDispatcher("/error/^0^").forward((ServletRequest)request, (ServletResponse)response);	
        return null;	
    }	
	
    @RequestMapping(value={"upload"})	
    @ResponseBody	
    public String upload(FileUploadParams params, HttpServletRequest request) {	
        void a2;	
        FileEntity a3;	
        void a4;	
        FileUpload a5;	
        long a6 = System.currentTimeMillis();	
        HashMap hashMap = MapUtils.newHashMap();	
        a2.put("code", "server");	
        if (StringUtils.isBlank((CharSequence)params.getFileMd5()) || StringUtils.isBlank((CharSequence)params.getFileName())) {	
            return this.renderResult("false", FileUploadController.text("sys.file.uploadValidNotBlank", new String[0]), a2);	
        }	
        boolean a7 = false;	
        FileEntity fileEntity = new FileEntity();	
        a3.setFileMd5(params.getFileMd5());	
        a3 = this.fileEntityService.getByMd5(a3);	
        a3.setFileUploadParams(params);	
        params.initialize();	
        if (!"0".equals(a3.getStatus())) {	
            a7 = true;	
        }	
        if (a7) {	
            Object a8;	
            Object a9;	
            long a10;	
            File a11;	
            a5 = null;	
            if (request instanceof MultipartHttpServletRequest && (a8 = (a9 = (MultipartHttpServletRequest)request).getFileNames()).hasNext()) {	
                a5 = a9.getFile((String)a8.next());	
            }	
            if (a5 == null || a5.isEmpty() || a5.getOriginalFilename() == null) {	
                return this.renderResult("false", FileUploadController.text("sys.file.uploadFileIsEmpty", new String[0]), a2);	
            }	
            a9 = params.getAllowSuffixes();	
            if (!StringUtils.contains((CharSequence)a9, (CharSequence)("." + params.getFileExtension() + ","))) {	
                a8 = params.getUploadType();	
                a2.put("code", "not_allow_type");	
                if ("image".equals(a8)) {	
                    return this.renderResult("false", FileUploadController.text("sys.file.uploadValidImage", new String[]{a9}), a2);	
                }	
                if ("media".equals(a8)) {	
                    return this.renderResult("false", FileUploadController.text("sys.file.uploadValidVideo", new String[]{a9}), a2);	
                }	
                if ("file".equals(a8)) {	
                    return this.renderResult("false", FileUploadController.text("sys.file.uploadValidFile", new String[]{a9}), a2);	
                }	
                return this.renderResult("false", FileUploadController.text("sys.file.uploadValidAll", new String[]{a9}), a2);	
            }	
            long a22 = a5.getSize();	
            if (a22 > (a10 = params.getMaxFileSize().longValue())) {	
                a2.put("code", "exceed_size");	
                return this.renderResult("false", FileUploadController.text("sys.file.uploadValidSize", ByteUtils.formatByteSize((long)a10)));	
            }	
            boolean a12 = false;	
            if (StringUtils.isBlank((CharSequence)a3.getFileId())) {	
                FileEntity fileEntity2 = a3;	
                FileEntity fileEntity3 = a3;	
                FileUploadParams fileUploadParams = params;	
                a3.setFileId(IdGen.nextId());	
                a3.setFileMd5(fileUploadParams.getFileMd5());	
                fileEntity3.setFilePath(fileUploadParams.getRelativePath());	
                fileEntity3.setFileContentType(a5.getContentType());	
                fileEntity2.setFileExtension(params.getFileExtension());	
                fileEntity2.setFileSize(a22);	
                a12 = true;	
            }	
            if (!(a11 = new File(a3.getFileRealPath())).getParentFile().exists()) {	
                a11.getParentFile().mkdirs();	
            }	
            try {	
                a5.transferTo(a11);	
            }	
            catch (IOException a13) {	
                this.logger.error(new StringBuilder().insert(0, "文件保存到本地失败：").append(a11.getAbsolutePath()).toString(), (Throwable)a13);	
            }	
            String a14 = Global.getConfig("file.allowContentTypes");	
            if (StringUtils.isNotBlank((CharSequence)a14) && !StringUtils.inString((String)FileUtils.getRealContentType((File)a11), (String[])a14.split(","))) {	
                a2.put("code", "not_allow_type");	
                a11.delete();	
                return this.renderResult("false", FileUploadController.text("sys.file.uploadValidContent", new String[0]));	
            }	
            FileUploadController fileUploadController = this;	
            fileUploadController.fileUploadService.compressImage(params, a11);	
            fileUploadController.fileUploadServiceExtend.uploadFile(a3);	
            if (a12) {	
                this.fileEntityService.insert(a3);	
            }	
        }	
        a5 = new FileUpload(a3);	
        FileUploadController fileUploadController = this;	
        FileUpload fileUpload = a5;	
        fileUpload.setFileName(params.getFileName());	
        fileUpload.setFileType(params.getUploadType());	
        fileUploadController.fileUploadServiceExtend.saveUploadFile(a5);	
        fileUploadController.fileUploadService.save(a5);	
        long a15 = System.currentTimeMillis();	
        String string = TimeUtils.formatDateAgo((long)(a15 - a6));	
        a2.put("fileUpload", a5);	
        if (!a7) {	
            return this.renderResult("true", FileUploadController.text("sys.file.uploadSuccessSeconds", new String[]{a4}), a2);	
        }	
        return this.renderResult("true", FileUploadController.text("sys.file.uploadSuccess", new String[]{a4}), a2);	
    }	
}	
	
