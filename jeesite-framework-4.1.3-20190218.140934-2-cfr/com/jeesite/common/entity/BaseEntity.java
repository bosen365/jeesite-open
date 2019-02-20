/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.annotation.JsonIgnore	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.reflect.ReflectUtils	
 */	
package com.jeesite.common.entity;	
	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.autoconfigure.core.TransactionAutoConfiguration;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.validator.PatternValue;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.Serializable;	
import java.util.ArrayList;	
import javax.xml.bind.annotation.XmlTransient;	
import org.hyperic.sigar.SysInfo;	
	
@Table(columns={@Column(name="corp_code", attrName="corpCode", label="\u79df\u6237\u4ee3\u7801", isUpdate=false), @Column(name="corp_name", attrName="corpName", label="\u79df\u6237\u540d\u79f0", isUpdate=false, isQuery=false)})	
public abstract class BaseEntity<T>	
implements Serializable {	
    protected String idColumnName;	
    protected User currentUser;	
    private static final long serialVersionUID = 1L;	
    protected boolean isNewRecord;	
    protected String idAttrName;	
    protected String corpName;	
    protected Page<T> page;	
    protected String id;	
    protected SqlMap sqlMap;	
    protected String corpCode;	
	
    @PatternValue(value="web.validator.id", regexp="[a-zA-Z0-9_\\-/#\u4e00-\u9fa5]{0,64}", message="\u7f16\u7801\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26\uff0c\u5e76\u4e14\u53ea\u80fd\u5305\u542b\u5b57\u6bcd\u3001\u6570\u5b57\u3001\u4e0b\u5212\u7ebf\u3001\u51cf\u53f7\u3001\u659c\u6760\u3001\u4e95\u53f7\u6216\u4e2d\u6587")	
    public String getId() {	
        BaseEntity baseEntity;	
        block9 : {	
            if (StringUtils.isBlank((CharSequence)this.id)) {	
                this.idColumnName = "";	
                this.idAttrName = "";	
                this.id = "";	
                Table a2 = MapperHelper.getTable(this);	
                ArrayList a3 = ListUtils.newArrayList();	
                for (Column a4 : MapperHelper.getColumns(a2, a3)) {	
                    block8 : {	
                        if (!a4.isPK()) continue;	
                        BaseEntity baseEntity2 = this;	
                        baseEntity2.columnNameAndAttrName(a4);	
                        if (!StringUtils.equals((CharSequence)baseEntity2.idAttrName, (CharSequence)"id")) break block8;	
                        baseEntity = this;	
                    }	
                    try {	
                        String a5;	
                        BaseEntity baseEntity3 = this;	
                        String a6 = baseEntity3.idAttrName;	
                        if (StringUtils.contains((CharSequence)baseEntity3.idAttrName, (CharSequence)"#")) {	
                            a6 = StringUtils.substringAfterLast((String)this.idAttrName, (String)"#");	
                        }	
                        if ((a5 = ObjectUtils.toString((Object)ReflectUtils.invokeGetter((Object)this, (String)a6))) == null) continue;	
                        if (this.id.length() != 0) {	
                            this.id = this.id + "#";	
                        }	
                        this.id = this.id + a5;	
                        continue;	
                    }	
                    catch (Exception a6) {	
                        baseEntity = this;	
                    }	
                    break block9;	
                }	
            }	
            baseEntity = this;	
        }	
        if (StringUtils.isBlank((CharSequence)baseEntity.id)) {	
            return null;	
        }	
        return this.id;	
    }	
	
    @JsonIgnore	
    @XmlTransient	
    public User getCurrentUser() {	
        if (this.currentUser == null) {	
            this.currentUser = UserUtils.getUser();	
        }	
        return this.currentUser;	
    }	
	
    public boolean equals(Object obj) {	
        if (null == obj) {	
            return false;	
        }	
        if (this == obj) {	
            return true;	
        }	
        if (!this.getClass().equals(obj.getClass())) {	
            return false;	
        }	
        BaseEntity a2 = (BaseEntity)obj;	
        if (null == this.getId()) {	
            return false;	
        }	
        return this.getId().equals(a2.getId());	
    }	
	
    public String getOrderBy() {	
        if (this.page != null) {	
            return this.page.getOrderBy();	
        }	
        return null;	
    }	
	
    public BaseEntity(String id) {	
        BaseEntity baseEntity = this;	
        this.sqlMap = new SqlMap(this);	
        this.isNewRecord = false;	
        if (id != null) {	
            this.setId(id);	
        }	
    }	
	
    @JsonIgnore	
    @XmlTransient	
    public Global getGlobal() {	
        return Global.getInstance();	
    }	
	
    public void setId_in(String[] ids) {	
        this.sqlMap.getWhere().and(this.getIdColumnName(), QueryType.IN, ids);	
    }	
	
    public boolean getIsNewRecord() {	
        this.isNewRecord = this.isNewRecord || StringUtils.isBlank((CharSequence)this.getId());	
        return this.isNewRecord;	
    }	
	
    public Object clone() {	
        return ObjectUtils.cloneBean((Object)this);	
    }	
	
    @JsonIgnore	
    @XmlTransient	
    public String getCorpName() {	
        if (StringUtils.isBlank((CharSequence)this.corpName)) {	
            this.corpName = CorpUtils.getCurrentCorpName();	
        }	
        return this.corpName;	
    }	
	
    public void setPage(Page<?> page) {	
        this.page = page;	
    }	
	
    public void setCurrentUser(User currentUser) {	
        this.currentUser = currentUser;	
    }	
	
    public void setPageSize(Integer pageSize) {	
        if (pageSize != null) {	
            if (this.page == null) {	
                BaseEntity baseEntity = this;	
                baseEntity.page = new Page();	
            }	
            this.page.setPageSize(pageSize);	
        }	
    }	
	
    public Integer getPageNo() {	
        if (this.page != null) {	
            return this.page.getPageNo();	
        }	
        return null;	
    }	
	
    @JsonIgnore	
    @XmlTransient	
    public String getIdColumnName() {	
        if (this.idColumnName == null) {	
            this.getId();	
        }	
        return this.idColumnName;	
    }	
	
    public Integer getPageSize() {	
        if (this.page != null) {	
            return this.page.getPageSize();	
        }	
        return null;	
    }	
	
    private /* synthetic */ void columnNameAndAttrName(Column c2) {	
        if (this.idColumnName.length() != 0) {	
            this.idColumnName = this.idColumnName + "#";	
        }	
        this.idColumnName = this.idColumnName + MapperHelper.getColumnName(c2);	
        if (this.idAttrName.length() != 0) {	
            this.idAttrName = this.idAttrName + "#";	
        }	
        this.idAttrName = this.idAttrName + MapperHelper.getAttrName(c2);	
    }	
	
    public BaseEntity() {	
        this(null);	
    }	
	
    @JsonIgnore	
    @XmlTransient	
    public SqlMap getSqlMap() {	
        return this.sqlMap;	
    }	
	
    public void setId(String id) {	
        block6 : {	
            BaseEntity baseEntity;	
            String[] a2 = StringUtils.split((String)id, (String)"#");	
            if (a2 == null || a2.length <= 0) {	
                a2 = new String[]{null};	
            }	
            boolean bl = false;	
            this.idColumnName = "";	
            this.idAttrName = "";	
            Table a3 = MapperHelper.getTable(this);	
            ArrayList a4 = ListUtils.newArrayList();	
            for (Column a5 : MapperHelper.getColumns(a3, a4)) {	
                block5 : {	
                    if (!a5.isPK()) continue;	
                    BaseEntity baseEntity2 = this;	
                    baseEntity2.columnNameAndAttrName(a5);	
                    if (!StringUtils.equals((CharSequence)baseEntity2.idAttrName, (CharSequence)"id")) break block5;	
                    baseEntity = this;	
                }	
                try {	
                    void a6;	
                    BaseEntity baseEntity3 = this;	
                    ReflectUtils.invokeSetter((Object)baseEntity3, (String)baseEntity3.idAttrName, (Object)a2[++a6]);	
                    continue;	
                }	
                catch (Exception exception) {	
                    baseEntity = this;	
                }	
                break block6;	
            }	
            baseEntity = this;	
        }	
        baseEntity.id = id;	
    }	
	
    public abstract void preInsert();	
	
    public void setIsNewRecord(boolean isNewRecord) {	
        this.isNewRecord = isNewRecord;	
    }	
	
    public void setCorpName(String corpName) {	
        this.corpName = corpName;	
    }	
	
    public abstract void preUpdate();	
	
    public String toString() {	
        if (this.getId() != null) {	
            return this.getId();	
        }	
        return Integer.toHexString(this.hashCode());	
    }	
	
    public void setOrderBy(String orderBy) {	
        if (StringUtils.isNotBlank((CharSequence)orderBy)) {	
            if (this.page == null) {	
                BaseEntity baseEntity = this;	
                baseEntity.page = new Page<T>();	
            }	
            this.page.setOrderBy(orderBy);	
        }	
    }	
	
    @JsonIgnore	
    @XmlTransient	
    public Page<?> getPage() {	
        return this.page;	
    }	
	
    public void setPageNo(Integer pageNo) {	
        if (pageNo != null) {	
            if (this.page == null) {	
                BaseEntity baseEntity = this;	
                baseEntity.page = new Page<T>();	
            }	
            this.page.setPageNo(pageNo);	
        }	
    }	
	
    @JsonIgnore	
    @XmlTransient	
    public String getIdAttrName() {	
        if (this.idAttrName == null) {	
            this.getId();	
        }	
        return this.idAttrName;	
    }	
	
    public String[] getId_in() {	
        return (String[])this.sqlMap.getWhere().getValue(this.getIdColumnName(), QueryType.IN);	
    }	
	
    @JsonIgnore	
    @XmlTransient	
    public String getCorpCode() {	
        if (StringUtils.isBlank((CharSequence)this.corpCode)) {	
            this.corpCode = CorpUtils.getCurrentCorpCode();	
        }	
        return this.corpCode;	
    }	
	
    public void setCorpCode(String corpCode) {	
        this.corpCode = corpCode;	
    }	
}	
	
