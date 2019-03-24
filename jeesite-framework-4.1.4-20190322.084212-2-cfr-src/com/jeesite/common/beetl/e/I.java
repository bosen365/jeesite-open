/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.beetl.e;	
	
import com.jeesite.common.beetl.e.A;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.l.h;	
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
import org.hyperic.sigar.ProcCred;	
	
public class I	
implements ResourceLoader {	
    private String c;	
    private boolean ALLATORIxDEMO;	
	
    public void ALLATORIxDEMO(boolean autoCheck) {	
        this.ALLATORIxDEMO = autoCheck;	
    }	
	
    @Override	
    public String getInfo() {	
        return "SpringResourceLoader";	
    }	
	
    @Override	
    public boolean isModified(Resource key) {	
        if (this.ALLATORIxDEMO) {	
            return key.isModified();	
        }	
        return false;	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     * Lifted jumps to return sites	
     */	
    @Override	
    public void init(GroupTemplate gt) {	
        a = gt.getConf().getResourceMap();	
        if (a.get("root") == null || (a = a.get("root")).equals("/") || a.length() == 0) ** GOTO lbl11	
        if (this.c.endsWith("/")) {	
            v0 = this;	
            this.c = this.c + a.get("root");	
            v1 = this;	
        } else {	
            this.c = new StringBuilder().insert(0, this.c).append("/").append(a.get("root")).toString();	
lbl11: // 2 sources:	
            v1 = this;	
        }	
        v1.ALLATORIxDEMO = Boolean.parseBoolean(a.get("autoCheck"));	
        try {	
            a = a.get("functionRoot");	
            a = a.get("functionSuffix");	
            a = new StringBuilder().insert(0, this.c).append("/").append(a).toString();	
            if (a.startsWith("/")) {	
                a = a.substring(1);	
            }	
            var7_7 = VFS.getInstance().list(a).iterator();	
            while (var7_7.hasNext() != false) {	
                a = var7_7.next();	
                if (!a.endsWith(new StringBuilder().insert(0, ".").append(a).toString())) continue;	
                a = StringUtils.substringAfter(new StringBuilder().insert(0, "/").append(a).toString(), this.c);	
                a = StringUtils.substringAfter(a, new StringBuilder().insert(0, a).append("/").toString());	
                a = FileUtils.getFileNameWithoutExtension(a);	
                a = a.replaceAll("/", ".");	
                a = new FileFunctionWrapper(a);	
                gt.registerFunction(a, a);	
            }	
            return;	
        }	
        catch (IOException a) {	
            a.printStackTrace();	
        }	
    }	
	
    public boolean ALLATORIxDEMO() {	
        return this.ALLATORIxDEMO;	
    }	
	
    @Override	
    public boolean exist(String key) {	
        return ResourceUtils.getResource(new StringBuilder().insert(0, this.c).append(key).toString()).exists();	
    }	
	
    @Override	
    public Resource getResource(String key) {	
        return new A(this.c, key, this);	
    }	
	
    public I() {	
        I i2 = this;	
        i2.c = "";	
        i2.ALLATORIxDEMO = true;	
    }	
	
    @Override	
    public void close() {	
    }	
	
    @Override	
    public String getResourceId(Resource resource, String id) {	
        if (resource == null) {	
            return id;	
        }	
        return BeetlUtil.getRelPath(resource.getId(), id);	
    }	
}	
	
