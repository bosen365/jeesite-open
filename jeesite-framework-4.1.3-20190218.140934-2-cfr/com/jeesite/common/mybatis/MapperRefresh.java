/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.commons.lang3.StringUtils	
 *  org.apache.ibatis.builder.annotation.ProviderSqlSource	
 *  org.apache.ibatis.executor.ErrorContext	
 *  org.apache.ibatis.mapping.MappedStatement	
 *  org.apache.ibatis.mapping.SqlSource	
 *  org.apache.ibatis.parsing.XNode	
 *  org.apache.ibatis.session.Configuration	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 *  org.springframework.core.NestedIOException	
 *  org.springframework.core.io.Resource	
 */	
package com.jeesite.common.mybatis;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.mybatis.E;	
import com.jeesite.common.mybatis.mapper.xml.XMLMapperBuilder;	
import com.jeesite.common.mybatis.mapper.xmltags.DynamicSqlSource;	
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
import org.hyperic.sigar.DirStat;	
import org.hyperic.sigar.Who;	
import org.hyperic.sigar.pager.PageList;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.core.NestedIOException;	
import org.springframework.core.io.Resource;	
	
public class MapperRefresh	
implements Runnable {	
    private static boolean refresh;	
    public static Logger log;	
    private Long beforeTime;	
    private static String mappingPath;	
    private static boolean enabled;	
    private static int sleepSeconds;	
    private static int delaySeconds;	
    private Configuration configuration;	
    private Resource[] mapperLocations;	
    private Set<String> location;	
	
    @Override	
    public void run() {	
        log.debug(new StringBuilder().insert(0, "enabled: ").append(enabled).append(", delaySeconds: ").append(delaySeconds).append(", sleepSeconds: ").append(sleepSeconds).append(", mappingPath: ").append(mappingPath).append(", location: ").append(this.location).append(", configuration: ").append((Object)this.configuration).toString());	
        if (enabled) {	
            this.beforeTime = System.currentTimeMillis();	
            MapperRefresh a2 = this;	
            new Thread((Runnable)new E(this, a2), "MyBatis-Mapper-Reresh").start();	
        }	
    }	
	
    static /* synthetic */ int access$300() {	
        return delaySeconds;	
    }	
	
    static /* synthetic */ String access$200() {	
        return mappingPath;	
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
	
    static /* synthetic */ Set access$002(MapperRefresh x0, Set x1) {	
        x0.location = x1;	
        return x0.location;	
    }	
	
    public static boolean isRefresh() {	
        return refresh;	
    }	
	
    private /* synthetic */ void refresh(String filePath, Long beforeTime) throws Exception {	
        int a2;	
        Long l2 = System.currentTimeMillis();	
        List<File> a3 = this.getRefreshFile(new File(filePath), beforeTime);	
        if (a3.size() > 0) {	
            log.debug(new StringBuilder().insert(0, "Refresh file: ").append(a3.size()).toString());	
        }	
        int n = a2 = 0;	
        while (n < a3.size()) {	
            FileInputStream a4 = new FileInputStream(a3.get(a2));	
            String a5 = a3.get(a2).getAbsolutePath();	
            try {	
                void a6;	
                Field field = this.configuration.getClass().getDeclaredField("loadedResources");	
                void v1 = a6;	
                v1.setAccessible(true);	
                ((Set)v1.get((Object)this.configuration)).remove(a5);	
                new XMLMapperBuilder(a4, this.configuration, a5, (Map<String, XNode>)this.configuration.getSqlFragments()).parse();	
            }	
            catch (Exception a7) {	
                throw new NestedIOException(new StringBuilder().insert(0, "Failed to parse mapping resource: '").append(a5).append("'").toString(), (Throwable)a7);	
            }	
            finally {	
                ErrorContext.instance().reset();	
            }	
            System.out.println(new StringBuilder().insert(0, "Refresh file: ").append(mappingPath).append(StringUtils.substringAfterLast((String)a3.get(a2).getAbsolutePath(), (String)mappingPath)).toString());	
            if (log.isDebugEnabled()) {	
                log.debug(new StringBuilder().insert(0, "Refresh file: ").append(a3.get(a2).getAbsolutePath()).toString());	
                log.debug(new StringBuilder().insert(0, "Refresh filename: ").append(a3.get(a2).getName()).toString());	
            }	
            n = ++a2;	
        }	
        if (a3.size() > 0) {	
            void a8;	
            this.beforeTime = a8;	
        }	
    }	
	
    static /* synthetic */ Resource[] access$100(MapperRefresh x0) {	
        return x0.mapperLocations;	
    }	
	
    private static /* synthetic */ String getPropString(String key) {	
        return Global.getProperty(key);	
    }	
	
    private /* synthetic */ List<File> getRefreshFile(File dir, Long beforeTime) {	
        ArrayList<File> a2 = new ArrayList<File>();	
        File[] a3 = dir.listFiles();	
        if (a3 != null) {	
            int a4;	
            int n = a4 = 0;	
            while (n < a3.length) {	
                File a5 = a3[a4];	
                if (a5.isDirectory()) {	
                    a2.addAll(this.getRefreshFile(a5, beforeTime));	
                } else if (a5.isFile()) {	
                    if (this.checkFile(a5, beforeTime)) {	
                        a2.add(a5);	
                    }	
                } else {	
                    System.out.println(new StringBuilder().insert(0, "Error file.").append(a5.getName()).toString());	
                }	
                n = ++a4;	
            }	
        }	
        return a2;	
    }	
	
    static /* synthetic */ void access$600(MapperRefresh x0, String x1, Long x2) throws Exception {	
        x0.refresh(x1, x2);	
    }	
	
    static /* synthetic */ int access$700() {	
        return sleepSeconds;	
    }	
	
    static /* synthetic */ boolean access$402(boolean x0) {	
        refresh = x0;	
        return refresh;	
    }	
	
    static {	
        log = LoggerFactory.getLogger(MapperRefresh.class);	
        enabled = "true".equalsIgnoreCase(MapperRefresh.getPropString("mybatis.mapper.reresh.enabled"));	
        delaySeconds = MapperRefresh.getPropInt("mybatis.mapper.refresh.delaySeconds");	
        sleepSeconds = MapperRefresh.getPropInt("mybatis.mapper.refresh.sleepSeconds");	
        mappingPath = MapperRefresh.getPropString("mybatis.mapper.refresh.mappingPath");	
        delaySeconds = delaySeconds == 0 ? 50 : delaySeconds;	
        sleepSeconds = sleepSeconds == 0 ? 3 : sleepSeconds;	
        mappingPath = StringUtils.isBlank((CharSequence)mappingPath) ? "mappings" : mappingPath;	
    }	
	
    private static /* synthetic */ int getPropInt(String key) {	
        int a2 = 0;	
        try {	
            a2 = Integer.parseInt(MapperRefresh.getPropString(key));	
            return a2;	
        }	
        catch (Exception exception) {	
            return a2;	
        }	
    }	
	
    private /* synthetic */ boolean checkFile(File file, Long beforeTime) {	
        return file.lastModified() > beforeTime;	
    }	
	
    static /* synthetic */ Long access$500(MapperRefresh x0) {	
        return x0.beforeTime;	
    }	
	
    static /* synthetic */ Set access$000(MapperRefresh x0) {	
        return x0.location;	
    }	
	
    protected static class StrictMap<V>	
    extends HashMap<String, V> {	
        private final String name;	
        private static final long serialVersionUID = -4950446264854982944L;	
	
        public StrictMap(String name) {	
            this.name = name;	
        }	
	
        public StrictMap(String name, int initialCapacity, float loadFactor) {	
            super(initialCapacity, loadFactor);	
            this.name = name;	
        }	
	
        private /* synthetic */ String getShortName(String key) {	
            String[] arrstring = key.split("\.");	
            return arrstring[arrstring.length - 1];	
        }	
	
        public StrictMap(String name, Map<String, ? extends V> m2) {	
            super(m2);	
            this.name = name;	
        }	
	
        @Override	
        public V get(Object key) {	
            Object a2 = super.get(key);	
            if (a2 == null) {	
                throw new IllegalArgumentException(new StringBuilder().insert(0, this.name).append(" does not contain value for ").append(key).toString());	
            }	
            if (a2 instanceof Ambiguity) {	
                throw new IllegalArgumentException(((Ambiguity)a2).getSubject() + " is ambiguous in " + this.name + " (try using the full name including the namespace, or rename one of the entries)");	
            }	
            return a2;	
        }	
	
        /*	
         * Enabled aggressive block sorting	
         */	
        @Override	
        public V put(String key, V value) {	
            Object a2;	
            StrictMap strictMap;	
            if (MapperRefresh.isRefresh()) {	
                MapperRefresh.log.debug(new StringBuilder().insert(0, "Mapper [").append(key).append("] already exists, is refreshed.").toString());	
                this.remove(key);	
            }	
            if ((a2 = super.get(key)) != null && value instanceof MappedStatement) {	
                SqlSource a3 = ((MappedStatement)value).getSqlSource();	
                if (a3 instanceof DynamicSqlSource) {	
                    int a4 = ((DynamicSqlSource)a3).getWeight();	
                    if (a2 instanceof MappedStatement) {	
                        int a5;	
                        SqlSource a6 = ((MappedStatement)a2).getSqlSource();	
                        if (a3 instanceof DynamicSqlSource && a4 < (a5 = ((DynamicSqlSource)a6).getWeight())) {	
                            return null;	
                        }	
                    }	
                } else if (a3 instanceof ProviderSqlSource) {	
                    MapperRefresh.log.debug(new StringBuilder().insert(0, "Mapper [").append(key).append("] already exists, is ignored.").toString());	
                    return null;	
                }	
            }	
            if (key.contains(".")) {	
                StrictMap strictMap2 = this;	
                String a7 = strictMap2.getShortName(key);	
                StrictMap strictMap3 = this;	
                if (super.get(a7) == null) {	
                    super.put(a7, value);	
                    strictMap = this;	
                    return super.put(key, value);	
                }	
                String string = a7;	
                super.put(a7, new Ambiguity(a7));	
            }	
            strictMap = this;	
            return super.put(key, value);	
        }	
	
        public StrictMap(String name, int initialCapacity) {	
            super(initialCapacity);	
            this.name = name;	
        }	
	
        protected static class Ambiguity {	
            private final String subject;	
	
            public String getSubject() {	
                return this.subject;	
            }	
	
            public Ambiguity(String subject) {	
                this.subject = subject;	
            }	
        }	
	
    }	
	
}	
	
