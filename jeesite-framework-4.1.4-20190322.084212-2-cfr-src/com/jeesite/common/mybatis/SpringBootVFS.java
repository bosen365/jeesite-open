/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis;	
	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.mybatis.mapper.provider.InsertSqlProvider;	
import java.io.IOException;	
import java.net.URI;	
import java.net.URL;	
import java.util.ArrayList;	
import java.util.List;	
import org.apache.ibatis.io.VFS;	
import org.hyperic.sigar.test.GetPass;	
import org.springframework.core.io.Resource;	
	
public class SpringBootVFS	
extends VFS {	
    @Override	
    public boolean isValid() {	
        return true;	
    }	
	
    private static /* synthetic */ String preserveSubpackageName(URI uri, String rootPath) {	
        String string = uri.toString();	
        return string.substring(string.indexOf(rootPath));	
    }	
	
    @Override	
    protected List<String> list(URL url, String path) throws IOException {	
        int n;	
        Resource[] a = ResourceUtils.getResources(new StringBuilder().insert(0, "classpath*:").append(path).append("/**/*.*").toString());	
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
}	
	
