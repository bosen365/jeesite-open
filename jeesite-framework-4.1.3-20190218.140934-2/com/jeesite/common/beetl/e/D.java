package com.jeesite.common.beetl.e;	
	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.StringUtils;	
import java.io.IOException;	
import java.util.Iterator;	
import java.util.Map;	
import org.apache.ibatis.io.VFS;	
import org.beetl.core.GroupTemplate;	
import org.beetl.core.Resource;	
import org.beetl.core.ResourceLoader;	
import org.beetl.core.fun.FileFunctionWrapper;	
import org.beetl.core.misc.BeetlUtil;	
import org.hyperic.sigar.pager.PageControl;	
	
public class D implements ResourceLoader {	
   private String c = "";	
   private boolean ALLATORIxDEMO = true;	
	
   public void init(GroupTemplate gt) {	
      D var10000;	
      Map a;	
      String a;	
      label41: {	
         if ((a = gt.getConf().getResourceMap()).get("root") != null && !(a = (String)a.get("root")).equals("/") && a.length() != 0) {	
            if (this.c.endsWith("/")) {	
               this.c = this.c + (String)a.get("root");	
               var10000 = this;	
               break label41;	
            }	
	
            this.c = (new StringBuilder()).insert(0, this.c).append("/").append((String)a.get("root")).toString();	
         }	
	
         var10000 = this;	
      }	
	
      var10000.ALLATORIxDEMO = Boolean.parseBoolean((String)a.get("autoCheck"));	
	
      try {	
         a = (String)a.get("fuctionRoot");	
         String a = (String)a.get("functionSuffix");	
         String a;	
         if ((a = (new StringBuilder()).insert(0, this.c).append("/").append(a).toString()).startsWith("/")) {	
            a = a.substring(1);	
         }	
	
         Iterator var7 = VFS.getInstance().list(a).iterator();	
	
         while(var7.hasNext()) {	
            String a;	
            if ((a = (String)var7.next()).endsWith((new StringBuilder()).insert(0, ".").append(a).toString())) {	
               String a = FileUtils.getFileNameWithoutExtension(StringUtils.substringAfter(a = StringUtils.substringAfter((new StringBuilder()).insert(0, "/").append(a).toString(), this.c), (new StringBuilder()).insert(0, a).append("/").toString())).replaceAll("/", ".");	
               FileFunctionWrapper a = new FileFunctionWrapper(a);	
               gt.registerFunction(a, a);	
            }	
         }	
      } catch (IOException var11) {	
         var11.printStackTrace();	
      }	
	
   }	
	
   public boolean isModified(Resource key) {	
      return this.ALLATORIxDEMO ? key.isModified() : false;	
   }	
	
   public String getResourceId(Resource resource, String id) {	
      return resource == null ? id : BeetlUtil.getRelPath(resource.getId(), id);	
   }	
	
   public void close() {	
   }	
	
   public Resource getResource(String key) {	
      return new B(this.c, key, this);	
   }	
	
   public boolean exist(String key) {	
      return ResourceUtils.getResource((new StringBuilder()).insert(0, this.c).append(key).toString()).exists();	
   }	
	
   public boolean ALLATORIxDEMO() {	
      return this.ALLATORIxDEMO;	
   }	
	
   public void ALLATORIxDEMO(boolean autoCheck) {	
      this.ALLATORIxDEMO = autoCheck;	
   }	
	
   public String getInfo() {	
      return "SpringResourceLoader";	
   }	
}	
