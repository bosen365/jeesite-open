/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.file.utils;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.service.FileUploadService;	
import com.jeesite.modules.file.utils.i;	
import java.util.List;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.cmd.Watch;	
import org.hyperic.sigar.win32.FileVersion;	
	
public class FileUploadUtils {	
    public static FileUploadService getFileUploadService() {	
        return i.ALLATORIxDEMO();	
    }	
	
    public static void saveFileUpload(String bizKey, String bizType) {	
        int n;	
        Object a;	
        String a2;	
        if (StringUtils.isBlank(bizKey) || StringUtils.isBlank(bizType)) {	
            return;	
        }	
        String a3 = ServletUtils.getParameter(bizType);	
        if (StringUtils.isNotBlank(a3)) {	
            a = i.ALLATORIxDEMO();	
            String[] arrstring = a3.split(",");	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a4 = arrstring[n];	
                FileUpload fileUpload = new FileUpload(a4);	
                void v1 = a2;	
                v1.setBizKey(bizKey);	
                v1.setBizType(bizType);	
                a.save((FileUpload)v1);	
                n3 = ++n;	
            }	
        }	
        if (StringUtils.isNotBlank((CharSequence)(a = ServletUtils.getParameter(new StringBuilder().insert(0, bizType).append("__del").toString())))) {	
            int a4;	
            FileUploadService a5 = i.ALLATORIxDEMO();	
            String[] arrstring = ((String)a).split(",");	
            n = arrstring.length;	
            int n4 = a4 = 0;	
            while (n4 < n) {	
                void a6;	
                a2 = arrstring[a4];	
                FileUpload fileUpload = new FileUpload(a2);	
                a5.delete((FileUpload)a6);	
                n4 = ++a4;	
            }	
        }	
    }	
	
    public static List<FileUpload> findFileUpload(String bizKey, String bizType) {	
        List<FileUpload> a = null;	
        if (StringUtils.isNotBlank(bizKey) && StringUtils.isNotBlank(bizType)) {	
            void a2;	
            FileUploadService a3 = i.ALLATORIxDEMO();	
            FileUpload fileUpload = new FileUpload();	
            void v0 = a2;	
            v0.setBizKey(bizKey);	
            v0.setBizType(bizType);	
            a = a3.findList(v0);	
            return a;	
        }	
        a = ListUtils.newArrayList();	
        return a;	
    }	
}	
	
