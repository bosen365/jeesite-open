/*	
 * Decompiled with CFR 0.141.	
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
import org.hyperic.sigar.ProcTime;	
import org.hyperic.sigar.pager.PageList;	
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
    private FileUploadServiceExtend fileUploadServiceExtend;	
    @Autowired	
    private FileEntityService fileEntityService;	
    @Autowired	
    private FileUploadService fileUploadService;	
	
    @RequestMapping(value={"/download/{fileUploadId}"}, method={RequestMethod.GET})	
    public String download(@PathVariable(value="fileUploadId") String fileUploadId, String preview, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
        FileUpload a = (FileUpload)((Object)this.fileUploadService.get(fileUploadId));	
        if (a != null && a.getFileEntity() != null && a.getFileEntity().getFileMd5() != null) {	
            if (StringUtils.isNotBlank(preview)) {	
                String a2 = a.getFileUrl();	
                String a3 = StringUtils.contains((CharSequence)a2, "?") ? "&" : "?";	
                return new StringBuilder().insert(0, "redirect:").append(a2).append(a3).append("preview=").append(preview).toString();	
            }	
            String a4 = this.fileUploadServiceExtend.downFile(a, request, response);	
            if (!"404".equals(a4)) {	
                if (StringUtils.isNotBlank(a4)) {	
                    return new StringBuilder().insert(0, "redirect:").append(a4).toString();	
                }	
                return null;	
            }	
        }	
        request.setAttribute("responseStatus", 200);	
        request.setAttribute("message", FileUploadController.text("sys.file.downloadFileNotExist", new String[0]));	
        request.getRequestDispatcher("/error/404").forward(request, response);	
        return null;	
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
        block41 : {	
            block42 : {	
                block38 : {	
                    a = System.currentTimeMillis();	
                    var5_4 = MapUtils.newHashMap();	
                    a.put("code", "server");	
                    if (StringUtils.isBlank(params.getFileMd5()) != false) return this.renderResult("false", FileUploadController.text("sys.file.uploadValidNotBlank", new String[0]), a);	
                    if (StringUtils.isBlank(params.getFileName())) {	
                        return this.renderResult("false", FileUploadController.text("sys.file.uploadValidNotBlank", new String[0]), a);	
                    }	
                    params.initialize();	
                    a = false;	
                    var7_6 = new FileEntity();	
                    a.setFileMd5(params.getFileMd5());	
                    a = this.fileEntityService.getByMd5(a);	
                    a.setFileUploadParams(params);	
                    if (!"0".equals(a.getStatus())) {	
                        a = true;	
                    }	
                    v0 = request;	
                    a = v0.getParameter("fileUploadId");	
                    a = v0.getParameter("fileEntityId");	
                    a = new FileUpload(a);	
                    if (StringUtils.isBlank(a)) {	
                        a = IdGen.nextId();	
                    }	
                    v1 = a;	
                    v2 = params;	
                    a.setId(a);	
                    v1.setFileName(v2.getFileName());	
                    v1.setFileType(v2.getUploadType());	
                    if (!a) break block42;	
                    a = null;	
                    if (request instanceof MultipartHttpServletRequest && (a = (a = (MultipartHttpServletRequest)request).getFileNames()).hasNext()) {	
                        a = a.getFile(a.next());	
                    }	
                    if (a == null || a.isEmpty() || a.getOriginalFilename() == null) {	
                        a.put("fileUploadId", IdGen.nextId());	
                        a.put("fileEntityId", IdGen.nextId());	
                        return this.renderResult("false", FileUploadController.text("sys.file.uploadFileIsEmpty", new String[0]), a);	
                    }	
                    a = params.getAllowSuffixes();	
                    if (!StringUtils.contains((CharSequence)a, "." + params.getFileExtension() + ",")) {	
                        a.put("code", "not_allow_type");	
                        a = params.getUploadType();	
                        if ("image".equals(a)) {	
                            v3 = new String[1];	
                            v3[0] = a;	
                            return this.renderResult("false", FileUploadController.text("sys.file.uploadValidImage", v3), a);	
                        }	
                        if ("media".equals(a)) {	
                            v4 = new String[1];	
                            v4[0] = a;	
                            return this.renderResult("false", FileUploadController.text("sys.file.uploadValidVideo", v4), a);	
                        }	
                        if ("file".equals(a)) {	
                            v5 = new String[1];	
                            v5[0] = a;	
                            return this.renderResult("false", FileUploadController.text("sys.file.uploadValidFile", v5), a);	
                        }	
                        v6 = new String[1];	
                        v6[0] = a;	
                        return this.renderResult("false", FileUploadController.text("sys.file.uploadValidAll", v6), a);	
                    }	
                    v7 = params;	
                    a = v7.getSize();	
                    if (a > (a = v7.getMaxFileSize().longValue())) {	
                        a.put("code", "exceed_size");	
                        v8 = new String[1];	
                        v8[0] = ByteUtils.formatByteSize(a);	
                        return this.renderResult("false", FileUploadController.text("sys.file.uploadValidSize", v8));	
                    }	
                    a = false;	
                    if (StringUtils.isBlank(a.getFileId())) {	
                        if (StringUtils.isBlank(a)) {	
                            a = IdGen.nextId();	
                        }	
                        v9 = a;	
                        v10 = a;	
                        a.setFileId(a);	
                        v10.setFileMd5(params.getFileMd5());	
                        v10.setFilePath(params.getRelativePath());	
                        v9.setFileContentType(a.getContentType());	
                        v9.setFileExtension(params.getFileExtension());	
                        a.setFileSize(a);	
                        a = true;	
                    }	
                    if (!(a = new File(a.getFileRealPath())).getParentFile().exists()) {	
                        a.getParentFile().mkdirs();	
                    }	
                    try {	
                        block43 : {	
                            block39 : {	
                                block40 : {	
                                    v11 = params;	
                                    a = v11.getChunks();	
                                    a = v11.getChunk();	
                                    a = v11.getChunkSize();	
                                    a = v11.getSize();	
                                    if (a == null || a == null || a == null || a == null) ** GOTO lbl165	
                                    if (a.longValue() == 1L && a.longValue() == 0L) {	
                                        a.transferTo(a);	
                                        break block38;	
                                    }	
                                    a = new File(new StringBuilder().insert(0, a.getAbsolutePath()).append(".tmp").append(a).toString());	
                                    if (!a.exists()) {	
                                        a.createNewFile();	
                                    }	
                                    a = new RandomAccessFile(a, "rw");	
                                    var25_28 = null;	
                                    a.seek(a.longValue() * a);	
                                    a.write(a.getBytes());	
                                    if (a == null) break block39;	
                                    if (var25_28 == null) break block40;	
                                    try {	
                                        a.close();	
                                        v12 = a;	
                                    }	
                                    catch (Throwable var26_29) {	
                                        v12 = a;	
                                        var25_28.addSuppressed(var26_29);	
                                    }	
                                    break block43;	
                                }	
                                a.close();	
                                v12 = a;	
                                break block43;	
                                catch (Throwable var26_30) {	
                                    try {	
                                        var25_28 = var26_30;	
                                        throw var25_28;	
                                    }	
                                    catch (Throwable var27_31) {	
                                        if (a != null) {	
                                            if (var25_28 != null) {	
                                                try {	
                                                    a.close();	
                                                    v13 = var27_31;	
                                                    throw v13;	
                                                }	
                                                catch (Throwable var28_32) {	
                                                    v13 = var27_31;	
                                                    var25_28.addSuppressed(var28_32);	
                                                    throw v13;	
                                                }	
                                            }	
                                            a.close();	
                                        }	
                                        v13 = var27_31;	
                                        throw v13;	
                                    }	
                                }	
                            }	
                            v12 = a;	
                        }	
                        if (v12.length() == a.longValue() && a.exists()) {	
                            if (a.exists()) {	
                                a.delete();	
                            }	
                            a.renameTo(a);	
                        } else {	
                            a.put("fileUpload", a);	
                            v14 = new String[2];	
                            v14[0] = String.valueOf(a.longValue() + 1L);	
                            v14[1] = String.valueOf(a);	
                            return this.renderResult("true", FileUploadController.text("sys.file.chunkUploading", v14), a);	
lbl165: // 1 sources:	
                            a.transferTo(a);	
                        }	
                    }	
                    catch (IOException a) {	
                        this.logger.error(new StringBuilder().insert(0, "文件保存到本地失败：").append(a.getAbsolutePath()).toString(), a);	
                    }	
                }	
                a = Global.getConfig("file.allowContentTypes");	
                if (StringUtils.isNotBlank((CharSequence)a) && !StringUtils.inString((String)(a = FileUtils.getRealContentType(a)), a.split(","))) {	
                    a.delete();	
                    a.put("code", "not_allow_type");	
                    return this.renderResult("false", FileUploadController.text("sys.file.uploadValidContent", new String[0]));	
                }	
                v15 = this;	
                v15.fileUploadService.compressImage(params, a);	
                v15.fileUploadServiceExtend.uploadFile(a);	
                if (a) {	
                    try {	
                        this.fileEntityService.insert(a);	
                        v16 = this;	
                        break block41;	
                    }	
                    catch (Exception a) {	
                        // empty catch block	
                    }	
                }	
            }	
            v16 = this;	
        }	
        v16.fileUploadServiceExtend.saveUploadFile(a);	
        try {	
            this.fileUploadService.insert(a);	
            v17 = a;	
        }	
        catch (Exception a) {	
            v17 = a;	
        }	
        v17.put("fileUpload", a);	
        a = System.currentTimeMillis();	
        a = TimeUtils.formatDateAgo(a - a);	
        if (!a) {	
            v18 = new String[1];	
            v18[0] = a;	
            return this.renderResult("true", FileUploadController.text("sys.file.uploadSuccessSeconds", v18), a);	
        }	
        v19 = new String[1];	
        v19[0] = a;	
        return this.renderResult("true", FileUploadController.text("sys.file.uploadSuccess", v19), a);	
    }	
	
    @RequestMapping(value={"fileList"})	
    @ResponseBody	
    public String fileList(FileUpload fileUpload) {	
        List<FileUpload> a;	
        if (StringUtils.isNotBlank(fileUpload.getBizKey()) && StringUtils.isNotBlank(fileUpload.getBizType()) && (a = this.fileUploadService.findList(fileUpload)) != null && a.size() > 0) {	
            return JsonMapper.toJson(a);	
        }	
        return this.renderResult("false", "No files.");	
    }	
}	
	
