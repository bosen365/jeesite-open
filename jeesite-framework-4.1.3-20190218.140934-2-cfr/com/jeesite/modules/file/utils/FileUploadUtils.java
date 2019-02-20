/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.web.http.ServletUtils	
 *  org.apache.commons.lang3.StringUtils	
 */	
package com.jeesite.modules.file.utils;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.file.entity.FileUpload;	
import com.jeesite.modules.file.service.FileUploadService;	
import com.jeesite.modules.file.utils.E;	
import java.util.List;	
import org.apache.commons.lang3.StringUtils;	
import org.hyperic.sigar.Mem;	
import org.hyperic.sigar.ProcState;	
	
public class FileUploadUtils {	
    public static FileUploadService getFileUploadService() {	
        return E.ALLATORIxDEMO();	
    }	
	
    public static void saveFileUpload(String bizKey, String bizType) {	
        int n;	
        Object a2;	
        String a3;	
        if (StringUtils.isBlank((CharSequence)bizKey) || StringUtils.isBlank((CharSequence)bizType)) {	
            return;	
        }	
        String a4 = ServletUtils.getParameter((String)bizType);	
        if (StringUtils.isNotBlank((CharSequence)a4)) {	
            a2 = E.ALLATORIxDEMO();	
            String[] arrstring = a4.split(",");	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a5 = arrstring[n];	
                FileUpload fileUpload = new FileUpload(a5);	
                void v1 = a3;	
                v1.setBizKey(bizKey);	
                v1.setBizType(bizType);	
                a2.save((FileUpload)v1);	
                n3 = ++n;	
            }	
        }	
        if (StringUtils.isNotBlank((CharSequence)(a2 = ServletUtils.getParameter((String)new StringBuilder().insert(0, bizType).append("__del").toString())))) {	
            int a5;	
            FileUploadService a6 = E.ALLATORIxDEMO();	
            String[] arrstring = ((String)a2).split(",");	
            n = arrstring.length;	
            int n4 = a5 = 0;	
            while (n4 < n) {	
                void a7;	
                a3 = arrstring[a5];	
                FileUpload fileUpload = new FileUpload(a3);	
                a6.delete((FileUpload)a7);	
                n4 = ++a5;	
            }	
        }	
    }	
	
    public static List<FileUpload> findFileUpload(String bizKey, String bizType) {	
        List<Object> a2 = null;	
        if (StringUtils.isNotBlank((CharSequence)bizKey) && StringUtils.isNotBlank((CharSequence)bizType)) {	
            void a3;	
            FileUploadService a4 = E.ALLATORIxDEMO();	
            FileUpload fileUpload = new FileUpload();	
            void v0 = a3;	
            v0.setBizKey(bizKey);	
            v0.setBizType(bizType);	
            a2 = a4.findList(v0);	
            return a2;	
        }	
        a2 = ListUtils.newArrayList();	
        return a2;	
    }	
}	
	
