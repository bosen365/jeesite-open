/*	
 * Decompiled with CFR 0.140.	
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
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.lang.ByteUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.lang.TimeUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.file.entity.FileEntity;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.entity.FileUploadParams;	
import com.jeesite.modules.file.service.FileEntityService;	
import com.jeesite.modules.file.service.FileUploadService;	
import com.jeesite.modules.file.service.FileUploadServiceExtend;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import java.io.File;	
import java.io.IOException;	
import java.io.RandomAccessFile;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import javax.servlet.RequestDispatcher;	
import javax.servlet.ServletException;	
import javax.servlet.ServletRequest;	
import javax.servlet.ServletResponse;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.sigar.DirStat;	
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
        List<FileUpload> a;	
        if (StringUtils.isNotBlank((CharSequence)fileUpload.getBizKey()) && StringUtils.isNotBlank((CharSequence)fileUpload.getBizType()) && (a = this.fileUploadService.findList(fileUpload)) != null && a.size() > 0) {	
            return JsonMapper.toJson(a);	
        }	
        return this.renderResult("false", "No files.");	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     * Lifted jumps to return sites	
     */	
    @RequestMapping(value={"upload"})	
    @ResponseBody	
    public String upload(FileUploadParams params, HttpServletRequest request) {	
        block39 : {	
            block40 : {	
                block36 : {	
                    a = System.currentTimeMillis();	
                    var5_4 = MapUtils.newHashMap();	
                    a.put("code", "server");	
                    if (StringUtils.isBlank((CharSequence)params.getFileMd5()) != false) return this.renderResult("false", FileUploadController.text("sys.file.uploadValidNotBlank", new String[0]), a);	
                    if (StringUtils.isBlank((CharSequence)params.getFileName())) {	
                        return this.renderResult("false", FileUploadController.text("sys.file.uploadValidNotBlank", new String[0]), a);	
                    }	
                    a = false;	
                    var7_6 = new FileEntity();	
                    a.setFileMd5(params.getFileMd5());	
                    a = this.fileEntityService.getByMd5(a);	
                    a.setFileUploadParams(params);	
                    params.initialize();	
                    if (!"0".equals(a.getStatus())) {	
                        a = true;	
                    }	
                    v0 = request;	
                    a = v0.getParameter("fileUploadId");	
                    a = v0.getParameter("fileEntityId");	
                    a = new FileUpload(a);	
                    if (StringUtils.isBlank((CharSequence)a)) {	
                        a = IdGen.nextId();	
                    }	
                    v1 = a;	
                    v2 = params;	
                    a.setId(a);	
                    v1.setFileName(v2.getFileName());	
                    v1.setFileType(v2.getUploadType());	
                    if (!a) break block40;	
                    a = null;	
                    if (request instanceof MultipartHttpServletRequest && (a = (a = (MultipartHttpServletRequest)request).getFileNames()).hasNext()) {	
                        a = a.getFile((String)a.next());	
                    }	
                    if (a == null || a.isEmpty() || a.getOriginalFilename() == null) {	
                        a.put("fileEntityId", IdGen.nextId());	
                        a.put("fileUploadId", IdGen.nextId());	
                        return this.renderResult("false", FileUploadController.text("sys.file.uploadFileIsEmpty", new String[0]), a);	
                    }	
                    a = params.getAllowSuffixes();	
                    if (!StringUtils.contains((CharSequence)a, (CharSequence)("." + params.getFileExtension() + ","))) {	
                        a = params.getUploadType();	
                        a.put("code", "not_allow_type");	
                        if ("image".equals(a)) {	
                            return this.renderResult("false", FileUploadController.text("sys.file.uploadValidImage", new String[]{a}), a);	
                        }	
                        if ("media".equals(a)) {	
                            return this.renderResult("false", FileUploadController.text("sys.file.uploadValidVideo", new String[]{a}), a);	
                        }	
                        if (!"file".equals(a)) return this.renderResult("false", FileUploadController.text("sys.file.uploadValidAll", new String[]{a}), a);	
                        return this.renderResult("false", FileUploadController.text("sys.file.uploadValidFile", new String[]{a}), a);	
                    }	
                    v3 = params;	
                    a = v3.getSize();	
                    if (a > (a = v3.getMaxFileSize().longValue())) {	
                        a.put("code", "exceed_size");	
                        return this.renderResult("false", FileUploadController.text("sys.file.uploadValidSize", new String[]{ByteUtils.formatByteSize((long)a)}));	
                    }	
                    a = false;	
                    if (StringUtils.isBlank((CharSequence)a.getFileId())) {	
                        if (StringUtils.isBlank((CharSequence)a)) {	
                            a = IdGen.nextId();	
                        }	
                        v4 = a;	
                        v5 = a;	
                        a.setFileId(a);	
                        v5.setFileMd5(params.getFileMd5());	
                        v5.setFilePath(params.getRelativePath());	
                        v4.setFileContentType(a.getContentType());	
                        v4.setFileExtension(params.getFileExtension());	
                        a.setFileSize(a);	
                        a = true;	
                    }	
                    if (!(a = new File(a.getFileRealPath())).getParentFile().exists()) {	
                        a.getParentFile().mkdirs();	
                    }	
                    try {	
                        block41 : {	
                            block37 : {	
                                block38 : {	
                                    v6 = params;	
                                    a = v6.getChunks();	
                                    a = v6.getChunk();	
                                    a = v6.getChunkSize();	
                                    a = v6.getSize();	
                                    if (a == null || a == null || a == null || a == null) ** GOTO lbl124	
                                    if (a.longValue() == 1L && a.longValue() == 0L) {	
                                        a.transferTo(a);	
                                        break block36;	
                                    }	
                                    a = new File(new StringBuilder().insert(0, a.getAbsolutePath()).append(".tmp").append(a).toString());	
                                    if (!a.exists()) {	
                                        a.createNewFile();	
                                    }	
                                    a = new RandomAccessFile(a, "rw");	
                                    var25_29 = null;	
                                    a.seek(a.longValue() * a);	
                                    a.write(a.getBytes());	
                                    if (a == null) break block37;	
                                    if (var25_29 == null) break block38;	
                                    try {	
                                        a.close();	
                                        v7 = a;	
                                    }	
                                    catch (Throwable var26_30) {	
                                        v7 = a;	
                                        var25_29.addSuppressed(var26_30);	
                                    }	
                                    break block41;	
                                }	
                                a.close();	
                                v7 = a;	
                                break block41;	
                                catch (Throwable var26_31) {	
                                    try {	
                                        var25_29 = var26_31;	
                                        throw var25_29;	
                                    }	
                                    catch (Throwable var27_32) {	
                                        if (a != null) {	
                                            if (var25_29 != null) {	
                                                try {	
                                                    a.close();	
                                                    v8 = var27_32;	
                                                    throw v8;	
                                                }	
                                                catch (Throwable var28_33) {	
                                                    v8 = var27_32;	
                                                    var25_29.addSuppressed(var28_33);	
                                                    throw v8;	
                                                }	
                                            }	
                                            a.close();	
                                        }	
                                        v8 = var27_32;	
                                        throw v8;	
                                    }	
                                }	
                            }	
                            v7 = a;	
                        }	
                        if (v7.length() == a.longValue() && a.exists()) {	
                            if (a.exists()) {	
                                a.delete();	
                            }	
                            a.renameTo(a);	
                        } else {	
                            a.put("fileUpload", a);	
                            return this.renderResult("true", FileUploadController.text("sys.file.chunkUploading", new String[]{String.valueOf(a.longValue() + 1L), String.valueOf(a)}), a);	
lbl124: // 1 sources:	
                            a.transferTo(a);	
                        }	
                    }	
                    catch (IOException a) {	
                        this.logger.error(new StringBuilder().insert(0, "文件保存到本地失败：").append(a.getAbsolutePath()).toString(), (Throwable)a);	
                    }	
                }	
                a = Global.getConfig("file.allowContentTypes");	
                if (StringUtils.isNotBlank((CharSequence)a) && !StringUtils.inString((String)(a = FileUtils.getRealContentType((File)a)), (String[])a.split(","))) {	
                    a.put("code", "not_allow_type");	
                    a.delete();	
                    return this.renderResult("false", FileUploadController.text("sys.file.uploadValidContent", new String[0]));	
                }	
                v9 = this;	
                v9.fileUploadService.compressImage(params, a);	
                v9.fileUploadServiceExtend.uploadFile(a);	
                if (a) {	
                    try {	
                        this.fileEntityService.insert(a);	
                        v10 = this;	
                        break block39;	
                    }	
                    catch (Exception a) {	
                        // empty catch block	
                    }	
                }	
            }	
            v10 = this;	
        }	
        v10.fileUploadServiceExtend.saveUploadFile(a);	
        try {	
            this.fileUploadService.insert(a);	
            v11 = a;	
        }	
        catch (Exception a) {	
            v11 = a;	
        }	
        a = System.currentTimeMillis();	
        a = TimeUtils.formatDateAgo((long)(a - a));	
        v11.put("fileUpload", a);	
        if (a) return this.renderResult("true", FileUploadController.text("sys.file.uploadSuccess", new String[]{a}), a);	
        return this.renderResult("true", FileUploadController.text("sys.file.uploadSuccessSeconds", new String[]{a}), a);	
    }	
	
    @RequestMapping(value={"/download/{fileUploadId}"}, method={RequestMethod.GET})	
    public String download(@PathVariable(value="fileUploadId") String fileUploadId, String preview, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
        FileUpload a = (FileUpload)this.fileUploadService.get(fileUploadId);	
        if (a != null && a.getFileEntity() != null && a.getFileEntity().getFileMd5() != null) {	
            if (StringUtils.isNotBlank((CharSequence)preview)) {	
                String a2 = a.getFileUrl();	
                String a3 = StringUtils.contains((CharSequence)a2, (CharSequence)"?") ? "&" : "?";	
                return new StringBuilder().insert(0, "redirect:").append(a2).append(a3).append("preview=").append(preview).toString();	
            }	
            String a4 = this.fileUploadServiceExtend.downFile(a, request, response);	
            if (!"404".equals(a4)) {	
                if (StringUtils.isNotBlank((CharSequence)a4)) {	
                    return new StringBuilder().insert(0, "redirect:").append(a4).toString();	
                }	
                return null;	
            }	
        }	
        request.setAttribute("responseStatus", (Object)200);	
        request.setAttribute("message", (Object)FileUploadController.text("sys.file.downloadFileNotExist", new String[0]));	
        request.getRequestDispatcher("/error/404").forward((ServletRequest)request, (ServletResponse)response);	
        return null;	
    }	
}	
	
