/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.io.ResourceUtils	
 *  org.apache.ibatis.io.VFS	
 *  org.springframework.core.io.Resource	
 */	
package com.jeesite.common.mybatis;	
	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.modules.job.d.i;	
import java.io.IOException;	
import java.net.URI;	
import java.net.URL;	
import java.util.ArrayList;	
import java.util.List;	
import org.apache.ibatis.io.VFS;	
import org.hyperic.sigar.FileSystemUsage;	
import org.springframework.core.io.Resource;	
	
public class SpringBootVFS	
extends VFS {	
    private static /* synthetic */ String preserveSubpackageName(URI uri, String rootPath) {	
        String string = uri.toString();	
        return string.substring(string.indexOf(rootPath));	
    }	
	
    protected List<String> list(URL url, String path) throws IOException {	
        int n;	
        Resource[] a = ResourceUtils.getResources((String)new StringBuilder().insert(0, "claspath*:").append(path).append("/**/*.*").toString());	
        ArrayList<String> a2 = new ArrayList<String>();	
        Resource[] arrresource = a;	
        int n2 = arrresource.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            Resource a3 = arrresource[n];	
            a2.add(SpringBootVFS.preserveSubpackageName(a3.getURI(), path));	
            n3 = ++n;	
        }	
        return a2;	
    }	
	
    public boolean isValid() {	
        return true;	
    }	
}	
	
