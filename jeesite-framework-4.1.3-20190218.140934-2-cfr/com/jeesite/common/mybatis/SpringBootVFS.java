/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.io.ResourceUtils	
 *  org.apache.ibatis.io.VFS	
 *  org.springframework.core.io.Resource	
 */	
package com.jeesite.common.mybatis;	
	
import com.jeesite.common.cache.JedisUtils;	
import com.jeesite.common.io.ResourceUtils;	
import java.io.IOException;	
import java.net.URI;	
import java.net.URL;	
import java.util.ArrayList;	
import java.util.List;	
import org.apache.ibatis.io.VFS;	
import org.hyperic.sigar.ProcMem;	
import org.springframework.core.io.Resource;	
	
public class SpringBootVFS	
extends VFS {	
    protected List<String> list(URL url, String path) throws IOException {	
        int n;	
        Resource[] a2 = ResourceUtils.getResources((String)new StringBuilder().insert(0, "classpath*:").append(path).append("/**/*.*").toString());	
        ArrayList<String> a3 = new ArrayList<String>();	
        Resource[] arrresource = a2;	
        int n2 = arrresource.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            Resource a4 = arrresource[n];	
            a3.add(SpringBootVFS.preserveSubpackageName(a4.getURI(), path));	
            n3 = ++n;	
        }	
        return a3;	
    }	
	
    public boolean isValid() {	
        return true;	
    }	
	
    private static /* synthetic */ String preserveSubpackageName(URI uri, String rootPath) {	
        String string = uri.toString();	
        return string.substring(string.indexOf(rootPath));	
    }	
}	
	
