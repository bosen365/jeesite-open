/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.io.FileUtils	
 *  com.jeesite.common.io.ResourceUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.apache.ibatis.io.VFS	
 *  org.beetl.core.Configuration	
 *  org.beetl.core.Function	
 *  org.beetl.core.GroupTemplate	
 *  org.beetl.core.Resource	
 *  org.beetl.core.ResourceLoader	
 *  org.beetl.core.fun.FileFunctionWrapper	
 *  org.beetl.core.misc.BeetlUtil	
 */	
package com.jeesite.common.beetl.e;	
	
import com.jeesite.common.beetl.e.B;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.StringUtils;	
import java.io.IOException;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.apache.ibatis.io.VFS;	
import org.beetl.core.Configuration;	
import org.beetl.core.Function;	
import org.beetl.core.GroupTemplate;	
import org.beetl.core.Resource;	
import org.beetl.core.ResourceLoader;	
import org.beetl.core.fun.FileFunctionWrapper;	
import org.beetl.core.misc.BeetlUtil;	
import org.hyperic.sigar.pager.PageControl;	
	
public class D	
implements ResourceLoader {	
    private String c;	
    private boolean ALLATORIxDEMO;	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     * Lifted jumps to return sites	
     */	
    public void init(GroupTemplate gt) {	
        a = gt.getConf().getResourceMap();	
        if (a.get("root") == null || (a = (String)a.get("root")).equals("/") || a.length() == 0) ** GOTO lbl9	
        if (this.c.endsWith("/")) {	
            v0 = this;	
            this.c = this.c + (String)a.get("root");	
            v1 = this;	
        } else {	
            this.c = new StringBuilder().insert(0, this.c).append("/").append((String)a.get("root")).toString();	
lbl9: // 2 sources:	
            v1 = this;	
        }	
        v1.ALLATORIxDEMO = Boolean.parseBoolean((String)a.get("autoCheck"));	
        try {	
            a = (String)a.get("fuctionRoot");	
            a = (String)a.get("functionSuffix");	
            a = new StringBuilder().insert(0, this.c).append("/").append(a).toString();	
            if (a.startsWith("/")) {	
                a = a.substring(1);	
            }	
            var7_7 = VFS.getInstance().list(a).iterator();	
            while (var7_7.hasNext() != false) {	
                a = (String)var7_7.next();	
                if (!a.endsWith(new StringBuilder().insert(0, ".").append(a).toString())) continue;	
                a = StringUtils.substringAfter((String)new StringBuilder().insert(0, "/").append(a).toString(), (String)this.c);	
                a = StringUtils.substringAfter((String)a, (String)new StringBuilder().insert(0, a).append("/").toString());	
                a = FileUtils.getFileNameWithoutExtension((String)a);	
                a = a.replaceAll("/", ".");	
                a = new FileFunctionWrapper(a);	
                gt.registerFunction(a, (Function)a);	
            }	
            return;	
        }	
        catch (IOException a) {	
            a.printStackTrace();	
        }	
    }	
	
    public boolean isModified(Resource key) {	
        if (this.ALLATORIxDEMO) {	
            return key.isModified();	
        }	
        return false;	
    }	
	
    public String getResourceId(Resource resource, String id) {	
        if (resource == null) {	
            return id;	
        }	
        return BeetlUtil.getRelPath((String)resource.getId(), (String)id);	
    }	
	
    public void close() {	
    }	
	
    public Resource getResource(String key) {	
        return new B(this.c, key, this);	
    }	
	
    public boolean exist(String key) {	
        return ResourceUtils.getResource((String)new StringBuilder().insert(0, this.c).append(key).toString()).exists();	
    }	
	
    public boolean ALLATORIxDEMO() {	
        return this.ALLATORIxDEMO;	
    }	
	
    public D() {	
        D d = this;	
        d.c = "";	
        d.ALLATORIxDEMO = true;	
    }	
	
    public void ALLATORIxDEMO(boolean autoCheck) {	
        this.ALLATORIxDEMO = autoCheck;	
    }	
	
    public String getInfo() {	
        return "SpringResourceLoader";	
    }	
}	
	
