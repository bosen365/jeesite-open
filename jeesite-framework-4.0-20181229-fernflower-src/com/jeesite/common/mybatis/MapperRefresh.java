package com.jeesite.common.mybatis;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.mybatis.mapper.query.QueryOrder;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.io.File;	
import java.io.FileInputStream;	
import java.io.InputStream;	
import java.lang.reflect.Field;	
import java.util.ArrayList;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.ibatis.builder.annotation.ProviderSqlSource;	
import org.apache.ibatis.builder.xml.XMLMapperBuilder;	
import org.apache.ibatis.executor.ErrorContext;	
import org.apache.ibatis.mapping.MappedStatement;	
import org.apache.ibatis.session.Configuration;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.core.NestedIOException;	
import org.springframework.core.io.Resource;	
	
public class MapperRefresh implements Runnable {	
   private Configuration configuration;	
   private Set location;	
   private static boolean enabled = "true".equalsIgnoreCase(getPropString("mapper.refresh.enabled"));	
   public static Logger log = LoggerFactory.getLogger(MapperRefresh.class);	
   private static int sleepSeconds = getPropInt("mapper.refresh.sleepSeconds");	
   private static boolean refresh;	
   private Resource[] mapperLocations;	
   private static String mappingPath = getPropString("mapper.refresh.mappingPath");	
   private static int delaySeconds = getPropInt("mapper.refresh.delaySeconds");	
   private Long beforeTime = 0L;	
	
   // $FF: synthetic method	
   static Set access$002(MapperRefresh x0, Set x1) {	
      return x0.location = x1;	
   }	
	
   public static boolean isRefresh() {	
      return refresh;	
   }	
	
   // $FF: synthetic method	
   static Long access$500(MapperRefresh x0) {	
      return x0.beforeTime;	
   }	
	
   public MapperRefresh(Resource[] mapperLocations, Configuration configuration) {	
      this.mapperLocations = mapperLocations;	
      this.configuration = configuration;	
	
      try {	
         String[] var10000 = new String[6];	
         boolean var10002 = true;	
         var10000[0] = "mappedStatements";	
         var10000[1] = "caches";	
         var10000[2] = "resultMaps";	
         var10000[3] = "parameterMaps";	
         var10000[4] = "keyGenerators";	
         var10000[5] = "sqlFragments";	
         String[] var4 = var10000;	
         int var5 = var4.length;	
	
         for(int var6 = 0; var6 < var5; ++var6) {	
            String a = var4[var6];	
            Field a = configuration.getClass().getDeclaredField(a);	
            a.setAccessible(true);	
            Map a;	
            if (!((a = (Map)a.get(configuration)) instanceof MapperRefresh.StrictMap)) {	
               Map a = new MapperRefresh.StrictMap((new StringBuilder()).insert(0, StringUtils.capitalize(a)).append("collection").toString());	
               Iterator var11 = a.keySet().iterator();	
	
               while(var11.hasNext()) {	
                  Object a = var11.next();	
	
                  try {	
                     a.put(a, a.get(a));	
                  } catch (IllegalArgumentException var14) {	
                     a.put(a, var14.getMessage());	
                  }	
               }	
	
               a.set(configuration, a);	
            }	
         }	
      } catch (Exception var15) {	
         var15.printStackTrace();	
      }	
	
   }	
	
   // $FF: synthetic method	
   static Resource[] access$100(MapperRefresh x0) {	
      return x0.mapperLocations;	
   }	
	
   // $FF: synthetic method	
   static int access$300() {	
      return delaySeconds;	
   }	
	
   static {	
      delaySeconds = delaySeconds == 0 ? 50 : delaySeconds;	
      sleepSeconds = sleepSeconds == 0 ? 3 : sleepSeconds;	
      mappingPath = StringUtils.isBlank(mappingPath) ? "mappings" : mappingPath;	
   }	
	
   // $FF: synthetic method	
   private static int getPropInt(String key) {	
      byte a = 0;	
	
      try {	
         int a = Integer.parseInt(getPropString(key));	
         return a;	
      } catch (Exception var3) {	
         return a;	
      }	
   }	
	
   // $FF: synthetic method	
   static void access$600(MapperRefresh x0, String x1, Long x2) throws Exception {	
      x0.refresh(x1, x2);	
   }	
	
   // $FF: synthetic method	
   static String access$200() {	
      return mappingPath;	
   }	
	
   // $FF: synthetic method	
   private boolean checkFile(File file, Long beforeTime) {	
      return file.lastModified() > beforeTime;	
   }	
	
   // $FF: synthetic method	
   private void refresh(String filePath, Long beforeTime) throws Exception {	
      Long a = System.currentTimeMillis();	
      List a;	
      if ((a = this.getRefreshFile(new File(filePath), beforeTime)).size() > 0) {	
         log.debug((new StringBuilder()).insert(0, "Refresh file: ").append(a.size()).toString());	
      }	
	
      int a;	
      for(int var10000 = a = 0; var10000 < a.size(); var10000 = a) {	
         InputStream a = new FileInputStream((File)a.get(a));	
         String a = ((File)a.get(a)).getAbsolutePath();	
	
         try {	
            Field a = this.configuration.getClass().getDeclaredField("loadedResources");	
            a.setAccessible(true);	
            ((Set)a.get(this.configuration)).remove(a);	
            (new XMLMapperBuilder(a, this.configuration, a, this.configuration.getSqlFragments())).parse();	
         } catch (Exception var14) {	
            throw new NestedIOException((new StringBuilder()).insert(0, "Failed to parse mapping resource: '").append(a).append("'").toString(), var14);	
         } finally {	
            ErrorContext.instance().reset();	
         }	
	
         System.out.println((new StringBuilder()).insert(0, "Refresh file: ").append(mappingPath).append(StringUtils.substringAfterLast(((File)a.get(a)).getAbsolutePath(), mappingPath)).toString());	
         if (log.isDebugEnabled()) {	
            log.debug((new StringBuilder()).insert(0, "Refresh file: ").append(((File)a.get(a)).getAbsolutePath()).toString());	
            log.debug((new StringBuilder()).insert(0, "Refresh filename: ").append(((File)a.get(a)).getName()).toString());	
         }	
	
         ++a;	
      }	
	
      if (a.size() > 0) {	
         this.beforeTime = a;	
      }	
	
   }	
	
   // $FF: synthetic method	
   static Set access$000(MapperRefresh x0) {	
      return x0.location;	
   }	
	
   // $FF: synthetic method	
   static boolean access$402(boolean x0) {	
      refresh = x0;	
      return x0;	
   }	
	
   public void run() {	
      log.debug((new StringBuilder()).insert(0, "enabled: ").append(enabled).append(", delaySeconds: ").append(delaySeconds).append(", sleepSeconds: ").append(sleepSeconds).append(", mappingPath: ").append(mappingPath).append(", location: ").append(this.location).append(", configuration: ").append(this.configuration).toString());	
      if (enabled) {	
         this.beforeTime = System.currentTimeMillis();	
         (new Thread(new Runnable() {	
            public void run() {	
               // $FF: Couldn't be decompiled	
            }	
         }, "MyBatis-Mapper-Refresh")).start();	
      }	
	
   }	
	
   // $FF: synthetic method	
   static int access$700() {	
      return sleepSeconds;	
   }	
	
   // $FF: synthetic method	
   private List getRefreshFile(File dir, Long beforeTime) {	
      List a = new ArrayList();	
      File[] a;	
      int a;	
      if ((a = dir.listFiles()) != null) {	
         for(int var10000 = a = 0; var10000 < a.length; var10000 = a) {	
            File a;	
            if ((a = a[a]).isDirectory()) {	
               a.addAll(this.getRefreshFile(a, beforeTime));	
            } else if (a.isFile()) {	
               if (this.checkFile(a, beforeTime)) {	
                  a.add(a);	
               }	
            } else {	
               System.out.println((new StringBuilder()).insert(0, "Error file.").append(a.getName()).toString());	
            }	
	
            ++a;	
         }	
      }	
	
      return a;	
   }	
	
   // $FF: synthetic method	
   private static String getPropString(String key) {	
      return Global.getProperty(key);	
   }	
	
   protected static class StrictMap extends HashMap {	
      private static final long serialVersionUID = -4950446264854982944L;	
      private final String name;	
	
      public Object put(String key, Object value) {	
         if (MapperRefresh.isRefresh()) {	
            this.remove(key);	
            MapperRefresh.log.debug((new StringBuilder()).insert(0, com.jeesite.common.l.j.ALLATORIxDEMO ("\u001f8\")7+r\u0002")).append(key).append("] alread exists, is refreshed.").toString());	
         }	
	
         if (this.containsKey(key) && value instanceof MappedStatement && ((MappedStatement)value).getSqlSource() instanceof ProviderSqlSource) {	
            MapperRefresh.log.debug((new StringBuilder()).insert(0, com.jeesite.common.l.j.ALLATORIxDEMO ("\u001f8\")7+r\u0002")).append(key).append("] alread exists, is ignored.").toString());	
            return null;	
         } else {	
            MapperRefresh.StrictMap var10000;	
            if (key.contains(com.jeesite.common.l.j.ALLATORIxDEMO ("w"))) {	
               String a;	
               if (super.get(a = this.getShortName(key)) == null) {	
                  var10000 = this;	
                  super.put(a, value);	
                  return var10000.put(key, value);	
               }	
	
               super.put(a, new MapperRefresh.StrictMap.Ambiguity(a));	
            }	
	
            var10000 = this;	
            return var10000.put(key, value);	
         }	
      }	
	
      public StrictMap(String var1, int initialCapacity) {	
         super(initialCapacity);	
         this.name = var1;	
      }	
	
      // $FF: synthetic method	
      private String getShortName(String key) {	
         String[] var10000 = key.split("\.");	
         return var10000[var10000.length - 1];	
      }	
	
      public StrictMap(String var1, Map m) {	
         super(m);	
         this.name = var1;	
      }	
	
      public StrictMap(String var1, int initialCapacity, float loadFactor) {	
         super(initialCapacity, loadFactor);	
         this.name = var1;	
      }	
	
      public StrictMap(String var1) {	
         this.name = var1;	
      }	
	
      public Object get(Object key) {	
         Object a;	
         if ((a = super.get(key)) == null) {	
            throw new IllegalArgumentException((new StringBuilder()).insert(0, this.name).append(com.jeesite.common.l.j.ALLATORIxDEMO ("r==<!y<6&y16<-30<y$8>,7y46 y")).append(key).toString());	
         } else if (a instanceof MapperRefresh.StrictMap.Ambiguity) {	
            throw new IllegalArgumentException(((MapperRefresh.StrictMap.Ambiguity)a).getSubject() + " is ambiguous in " + this.name + com.jeesite.common.l.j.ALLATORIxDEMO ("rq&++y'*;75y&17y4,>5r7347y;715'=;75y&17y<8?<!)3:7ur6 y <<8?<r6<<r64y&17y77&+;<!p"));	
         } else {	
            return a;	
         }	
      }	
	
      protected static class Ambiguity {	
         private final String subject;	
	
         public String getSubject() {	
            return this.subject;	
         }	
	
         public Ambiguity(String var1) {	
            this.subject = var1;	
         }	
      }	
   }	
}	
