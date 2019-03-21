/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.annotation.JsonIgnore	
 *  javax.validation.constraints.NotBlank	
 *  org.apache.commons.lang3.StringUtils	
 *  org.hibernate.validator.constraints.Length	
 */	
package com.jeesite.modules.sys.entity;	
	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.modules.job.d.i;	
import javax.validation.constraints.NotBlank;	
import org.apache.commons.lang3.StringUtils;	
import org.hibernate.validator.constraints.Length;	
	
@Table(name="${_prefix}sys_module", columns={@Column(includeEntity=DataEntity.class), @Column(name="module_code", attrName="moduleCode", label="\u6a21\u5757\u7f16\u7801", isPK=true), @Column(name="module_name", attrName="moduleName", label="\u6a21\u5757\u540d\u79f0", queryType=QueryType.LIKE), @Column(name="description", attrName="description", label="\u6a21\u5757\u63cf\u8ff0"), @Column(name="main_class_name", attrName="mainClassName", label="\u4e3b\u7c7b\u5168\u540d", queryType=QueryType.LIKE), @Column(name="current_version", attrName="currentVersion", label="\u5f53\u524d\u7248\u672c"), @Column(name="upgrade_info", attrName="upgradeInfo", label="\u5347\u7ea7\u4fe1\u606f")}, orderBy="a.update_date DESC")	
public class Module	
extends DataEntity<Module> {	
    private String mainClassName;	
    public static final String MODULE_MSG = "msg";	
    public static final String MODULE_CMS = "cms";	
    private static final long serialVersionUID = 1L;	
    private String moduleName;	
    public static final String MODULE_CORE = "core";	
    private String[] versions = null;	
    private String moduleCode;	
    private String description;	
    private String currentVersion;	
    private String upgradeInfo;	
	
    public void setUpgradeInfo(String upgradeInfo) {	
        this.upgradeInfo = upgradeInfo;	
    }	
	
    @Length(min=0, max=300, message="\u5347\u7ea7\u4fe1\u606f\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 300 \u4e2a\u5b57\u7b26")	
    public String getUpgradeInfo() {	
        return this.upgradeInfo;	
    }	
	
    @Length(min=0, max=50, message="\u5f53\u524d\u7248\u672c\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 50 \u4e2a\u5b57\u7b26")	
    public String getCurrentVersion() {	
        return this.currentVersion;	
    }	
	
    public void setModuleCode(String moduleCode) {	
        this.moduleCode = moduleCode;	
    }	
	
    public void setCurrentVersion(String currentVersion) {	
        this.currentVersion = currentVersion;	
    }	
	
    public String getModuleCode() {	
        return this.moduleCode;	
    }	
	
    @NotBlank(message="\u6a21\u5757\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u6a21\u5757\u540d\u79f0\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getModuleName() {	
        return this.moduleName;	
    }	
	
    public void setModuleName(String moduleName) {	
        this.moduleName = moduleName;	
    }	
	
    public void setDescription(String description) {	
        this.description = description;	
    }	
	
    @Length(min=0, max=500, message="\u6a21\u5757\u63cf\u8ff0\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 500 \u4e2a\u5b57\u7b26")	
    public String getDescription() {	
        return this.description;	
    }	
	
    /*	
     * Enabled force condition propagation	
     * Lifted jumps to return sites	
     */	
    public Boolean getIsLoader() {	
        block4 : {	
            boolean bl;	
            block5 : {	
                try {	
                    if (!StringUtils.isNotBlank((CharSequence)this.mainClassName)) break block4;	
                    if (Class.forName(this.mainClassName) == null) break block5;	
                    bl = true;	
                    return bl;	
                }	
                catch (ClassNotFoundException a) {	
                    return false;	
                }	
            }	
            bl = false;	
            return bl;	
        }	
        return false;	
    }	
	
    public Module() {	
        this(null);	
    }	
	
    public Module(String id) {	
        super(id);	
    }	
	
    /*	
     * Exception decompiling	
     */	
    @JsonIgnore	
    public synchronized String[] getVersions() {	
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 7[CATCHBLOCK]	
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
	
    @Length(min=0, max=500, message="\u4e3b\u7c7b\u5168\u540d\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 500 \u4e2a\u5b57\u7b26")	
    public String getMainClassName() {	
        return this.mainClassName;	
    }	
	
    public Boolean getIsEnable() {	
        return this.getIsLoader() != false && "0".equals(this.status);	
    }	
	
    public void setMainClassName(String mainClassName) {	
        this.mainClassName = mainClassName;	
    }	
	
    public synchronized String getLastVersion() {	
        String[] a = this.getVersions();	
        if (a.length == 0) {	
            return "未知版本号";	
        }	
        String[] arrstring = a;	
        return arrstring[arrstring.length - 1];	
    }	
}	
	
