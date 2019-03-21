/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.mybatis;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.mybatis.i;	
import com.jeesite.common.mybatis.mapper.xml.XMLMapperBuilder;	
import com.jeesite.common.mybatis.mapper.xmltags.DynamicSqlSource;	
import com.jeesite.common.shiro.d.D;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import java.io.File;	
import java.io.FileInputStream;	
import java.io.InputStream;	
import java.io.PrintStream;	
import java.lang.reflect.Field;	
import java.util.ArrayList;	
import java.util.Collection;	
import java.util.HashMap;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import org.apache.commons.lang3.StringUtils;	
import org.apache.ibatis.builder.annotation.ProviderSqlSource;	
import org.apache.ibatis.executor.ErrorContext;	
import org.apache.ibatis.mapping.MappedStatement;	
import org.apache.ibatis.mapping.SqlSource;	
import org.apache.ibatis.parsing.XNode;	
import org.apache.ibatis.session.Configuration;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.core.NestedIOException;	
import org.springframework.core.io.Resource;	
	
public class MapperRefresh	
implements Runnable {	
    private static boolean enabled;	
    private static int delaySeconds;	
    private Set<String> location;	
    private Resource[] mapperLocations;	
    private static int sleepSeconds;	
    private Configuration configuration;	
    private static boolean refresh;	
    public static Logger log;	
    private static String mappingPath;	
    private Long beforeTime;	
	
    static /* synthetic */ boolean access$402(boolean x0) {	
        refresh = x0;	
        return refresh;	
    }	
	
    static /* synthetic */ Long access$500(MapperRefresh x0) {	
        return x0.beforeTime;	
    }	
	
    @Override	
    public void run() {	
        log.debug(new StringBuilder().insert(0, "enabled: ").append(enabled).append(", delaySeconds: ").append(delaySeconds).append(", sleepSeconds: ").append(sleepSeconds).append(", mappingPath: ").append(mappingPath).append(", location: ").append(this.location).append(", configuration: ").append(this.configuration).toString());	
        if (enabled) {	
            this.beforeTime = System.currentTimeMillis();	
            MapperRefresh a = this;	
            new Thread((Runnable)new i(this, a), "MyBatis-Mapper-Refresh").start();	
        }	
    }	
	
    static /* synthetic */ int access$700() {	
        return sleepSeconds;	
    }	
	
    private static /* synthetic */ String getPropString(String key) {	
        return Global.getProperty(key);	
    }	
	
    static /* synthetic */ Set access$002(MapperRefresh x0, Set x1) {	
        x0.location = x1;	
        return x0.location;	
    }	
	
    static /* synthetic */ Set access$000(MapperRefresh x0) {	
        return x0.location;	
    }	
	
    private /* synthetic */ boolean checkFile(File file, Long beforeTime) {	
        return file.lastModified() > beforeTime;	
    }	
	
    static /* synthetic */ void access$600(MapperRefresh x0, String x1, Long x2) throws Exception {	
        x0.refresh(x1, x2);	
    }	
	
    static {	
        log = LoggerFactory.getLogger(MapperRefresh.class);	
        enabled = "true".equalsIgnoreCase(MapperRefresh.getPropString("mybatis.mapper.refresh.enabled"));	
        delaySeconds = MapperRefresh.getPropInt("mybatis.mapper.refres.delaySeconds");	
        sleepSeconds = MapperRefresh.getPropInt("mybatis.mapper.refresh.sleepSeconds");	
        mappingPath = MapperRefresh.getPropString("mybatis.mapper.refresh.mappingPath");	
        delaySeconds = delaySeconds == 0 ? 50 : delaySeconds;	
        sleepSeconds = sleepSeconds == 0 ? 3 : sleepSeconds;	
        mappingPath = StringUtils.isBlank(mappingPath) ? "mappings" : mappingPath;	
    }	
	
    static /* synthetic */ int access$300() {	
        return delaySeconds;	
    }	
	
    static /* synthetic */ Resource[] access$100(MapperRefresh x0) {	
        return x0.mapperLocations;	
    }	
	
    /*	
     * Exception decompiling	
     */	
    public MapperRefresh(Resource[] mapperLocations, Configuration configuration) {	
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 6[WHILELOOP]	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:432)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:607)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:692)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:182)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:127)	
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:96)	
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:396)	
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:890)	
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:792)	
        // org.benf.cfr.reader.Driver.doJar(Driver.java:128)	
        // org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:63)	
        // org.benf.cfr.reader.Main.main(Main.java:48)	
        throw new IllegalStateException("Decompilation failed");	
    }	
	
    private /* synthetic */ void refresh(String filePath, Long beforeTime) throws Exception {	
        int a;	
        Long l2 = System.currentTimeMillis();	
        List<File> a2 = this.getRefreshFile(new File(filePath), beforeTime);	
        if (a2.size() > 0) {	
            log.debug(new StringBuilder().insert(0, "Refresh file: ").append(a2.size()).toString());	
        }	
        int n = a = 0;	
        while (n < a2.size()) {	
            FileInputStream a3 = new FileInputStream(a2.get(a));	
            String a4 = a2.get(a).getAbsolutePath();	
            try {	
                void a5;	
                Field field = this.configuration.getClass().getDeclaredField("loadedResources");	
                void v1 = a5;	
                v1.setAccessible(true);	
                ((Set)v1.get(this.configuration)).remove(a4);	
                new XMLMapperBuilder(a3, this.configuration, a4, this.configuration.getSqlFragments()).parse();	
            }	
            catch (Exception a6) {	
                throw new NestedIOException(new StringBuilder().insert(0, "Failed to parse mapping resource: '").append(a4).append("'").toString(), a6);	
            }	
            finally {	
                ErrorContext.instance().reset();	
            }	
            System.out.println(new StringBuilder().insert(0, "Refresh file: ").append(mappingPath).append(StringUtils.substringAfterLast(a2.get(a).getAbsolutePath(), mappingPath)).toString());	
            if (log.isDebugEnabled()) {	
                log.debug(new StringBuilder().insert(0, "Refres file: ").append(a2.get(a).getAbsolutePath()).toString());	
                log.debug(new StringBuilder().insert(0, "Refresh filename: ").append(a2.get(a).getName()).toString());	
            }	
            n = ++a;	
        }	
        if (a2.size() > 0) {	
            void a7;	
            this.beforeTime = a7;	
        }	
    }	
	
    static /* synthetic */ String access$200() {	
        return mappingPath;	
    }	
	
    public static boolean isRefresh() {	
        return refresh;	
    }	
	
    private /* synthetic */ List<File> getRefreshFile(File dir, Long beforeTime) {	
        ArrayList<File> a = new ArrayList<File>();	
        File[] a2 = dir.listFiles();	
        if (a2 != null) {	
            int a3;	
            int n = a3 = 0;	
            while (n < a2.length) {	
                File a4 = a2[a3];	
                if (a4.isDirectory()) {	
                    a.addAll(this.getRefreshFile(a4, beforeTime));	
                } else if (a4.isFile()) {	
                    if (this.checkFile(a4, beforeTime)) {	
                        a.add(a4);	
                    }	
                } else {	
                    System.out.println(new StringBuilder().insert(0, "Error file.").append(a4.getName()).toString());	
                }	
                n = ++a3;	
            }	
        }	
        return a;	
    }	
	
    private static /* synthetic */ int getPropInt(String key) {	
        int a = 0;	
        try {	
            a = Integer.parseInt(MapperRefresh.getPropString(key));	
            return a;	
        }	
        catch (Exception exception) {	
            return a;	
        }	
    }	
	
    protected static class StrictMap<V>	
    extends HashMap<String, V> {	
        private static final long serialVersionUID = -4950446264854982944L;	
        private final String name;	
	
        public StrictMap(String name, Map<String, ? extends V> m2) {	
            super(m2);	
            this.name = name;	
        }	
	
        public StrictMap(String name, int initialCapacity) {	
            super(initialCapacity);	
            this.name = name;	
        }	
	
        private /* synthetic */ String getShortName(String key) {	
            String[] arrstring = key.split("\.");	
            return arrstring[arrstring.length - 1];	
        }	
	
        /*	
         * Enabled aggressive block sorting	
         */	
        @Override	
        public V put(String key, V value) {	
            Object a;	
            Object a2;	
            StrictMap strictMap;	
            if (MapperRefresh.isRefresh()) {	
                MapperRefresh.log.debug(new StringBuilder().insert(0, "Mapper [").append(key).append("] already exists, is refreshed.").toString());	
                this.remove(key);	
            }	
            if ((a = super.get(key)) != null && value instanceof MappedStatement) {	
                a2 = ((MappedStatement)value).getSqlSource();	
                if (a2 instanceof DynamicSqlSource) {	
                    int a3 = ((DynamicSqlSource)a2).getWeight();	
                    if (a instanceof MappedStatement) {	
                        int a4;	
                        SqlSource a5 = ((MappedStatement)a).getSqlSource();	
                        if (a2 instanceof DynamicSqlSource && a3 < (a4 = ((DynamicSqlSource)a5).getWeight())) {	
                            return null;	
                        }	
                    }	
                } else if (a2 instanceof ProviderSqlSource) {	
                    MapperRefresh.log.debug(new StringBuilder().insert(0, "Mapper [").append(key).append("] already exists, is ignored.").toString());	
                    return null;	
                }	
            }	
            if (key.contains(".")) {	
                StrictMap strictMap2 = this;	
                a2 = strictMap2.getShortName(key);	
                StrictMap strictMap3 = this;	
                if (super.get(a2) == null) {	
                    super.put(a2, value);	
                    strictMap = this;	
                    return super.put(key, value);	
                }	
                Object object = a2;	
                super.put(a2, new Ambiguity((String)a2));	
            }	
            strictMap = this;	
            return super.put(key, value);	
        }	
	
        @Override	
        public V get(Object key) {	
            Object a = super.get(key);	
            if (a == null) {	
                throw new IllegalArgumentException(new StringBuilder().insert(0, this.name).append(" does not contain value for ").append(key).toString());	
            }	
            if (a instanceof Ambiguity) {	
                throw new IllegalArgumentException(((Ambiguity)a).getSubject() + " is ambiguous in " + this.name + " (try using the full name including the namespace, or rename one of the entries)");	
            }	
            return a;	
        }	
	
        public StrictMap(String name) {	
            this.name = name;	
        }	
	
        public StrictMap(String name, int initialCapacity, float loadFactor) {	
            super(initialCapacity, loadFactor);	
            this.name = name;	
        }	
	
        protected static class Ambiguity {	
            private final String subject;	
	
            public Ambiguity(String subject) {	
                this.subject = subject;	
            }	
	
            public String getSubject() {	
                return this.subject;	
            }	
        }	
	
    }	
	
}	
	
