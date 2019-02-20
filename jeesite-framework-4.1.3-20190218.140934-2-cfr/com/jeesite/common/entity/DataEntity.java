/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.annotation.JsonFormat	
 *  com.jeesite.common.lang.DateUtils	
 *  org.apache.commons.lang3.StringUtils	
 *  org.hibernate.validator.constraints.Length	
 */	
package com.jeesite.common.entity;	
	
import com.fasterxml.jackson.annotation.JsonFormat;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.validator.OptimisticLock;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.util.Date;	
import org.apache.commons.lang3.StringUtils;	
import org.hibernate.validator.constraints.Length;	
	
@Table(columns={@Column(name="status", attrName="status", label="\u72b6\u6001", isUpdate=false, comment="\uff08\u63a8\u8350\u72b6\u6001\uff1a0\uff1a\u6b63\u5e38\uff1b1\uff1a\u5220\u9664\uff1b2\uff1a\u505c\u7528\uff1b3\uff1a\u51bb\u7ed3\uff1b4\uff1a\u5ba1\u6838\u3001\u5f85\u5ba1\u6838\uff1b5\uff1a\u5ba1\u6838\u9a73\u56de\uff1b9\uff1a\u8349\u7a3f\uff09"), @Column(name="create_by", attrName="createBy", label="\u521b\u5efa\u8005", isUpdate=false), @Column(name="create_date", attrName="createDate", label="\u521b\u5efa\u65f6\u95f4", isUpdate=false, isQuery=false), @Column(name="update_by", attrName="updateBy", label="\u66f4\u65b0\u8005", isUpdate=true), @Column(name="update_date", attrName="updateDate", label="\u66f4\u65b0\u65f6\u95f4", isUpdate=true, isQuery=false), @Column(name="remarks", attrName="remarks", label="\u5907\u6ce8\u4fe1\u606f", queryType=QueryType.LIKE)})	
@OptimisticLock	
public abstract class DataEntity<T extends DataEntity<?>>	
extends BaseEntity<T> {	
    public static final String STATUS_DRAFT = "9";	
    public static final String STATUS_AUDIT = "4";	
    protected String createBy;	
    protected String createByName;	
    protected String status;	
    protected String updateBy;	
    public static final String STATUS_AUDIT_BACK = "5";	
    protected Date updateDate;	
    protected Date createDate;	
    public static final String STATUS_NORMAL = "0";	
    public static final String STATUS_FREEZE = "3";	
    protected Long lastUpdateDateTime;	
    protected String remarks;	
    protected String updateByName;	
    private static final long serialVersionUID = 1L;	
    public static final String STATUS_DELETE = "1";	
    public static final String STATUS_DISABLE = "2";	
	
    public void setCreateByName(String createByName) {	
        this.createByName = createByName;	
    }	
	
    public void setUpdateDate_lte(Date updateDate) {	
        updateDate = DateUtils.getOfDayLast((Date)updateDate);	
        this.sqlMap.getWhere().and("update_date", QueryType.LTE, updateDate);	
    }	
	
    public void setCreateDate_between(String createDateStr) {	
        Date[] a2 = DateUtils.parseDateBetweenString((String)createDateStr);	
        DataEntity dataEntity = this;	
        dataEntity.setCreateDate_gte(a2[0]);	
        dataEntity.setCreateDate_lte(a2[1]);	
    }	
	
    public String getUpdateDate_between() {	
        DataEntity dataEntity = this;	
        Date a2 = dataEntity.getUpdateDate_gte();	
        Date a3 = dataEntity.getUpdateDate_lte();	
        return DateUtils.formatDateBetweenString((Date)a2, (Date)a3);	
    }	
	
    public void setCreateBy(String createBy) {	
        this.createBy = createBy;	
    }	
	
    public void setStatus_in(String[] statues) {	
        this.sqlMap.getWhere().and("status", QueryType.IN, statues);	
    }	
	
    public void setStatus(String status) {	
        this.status = status;	
    }	
	
    public Date getCreateDate_lte() {	
        return (Date)this.sqlMap.getWhere().getValue("create_date", QueryType.LTE);	
    }	
	
    public void setUpdateDate(Date updateDate) {	
        this.updateDate = updateDate;	
    }	
	
    public String getCreateDate_between() {	
        DataEntity dataEntity = this;	
        Date a2 = dataEntity.getCreateDate_gte();	
        Date a3 = dataEntity.getCreateDate_lte();	
        return DateUtils.formatDateBetweenString((Date)a2, (Date)a3);	
    }	
	
    public void setUpdateDate_between(String updateDateStr) {	
        Date[] a2 = DateUtils.parseDateBetweenString((String)updateDateStr);	
        DataEntity dataEntity = this;	
        dataEntity.setUpdateDate_gte(a2[0]);	
        dataEntity.setUpdateDate_lte(a2[1]);	
    }	
	
    public void setRemarks(String remarks) {	
        this.remarks = remarks;	
    }	
	
    @Length(min=0, max=255)	
    public String getRemarks() {	
        return this.remarks;	
    }	
	
    public void setCreateDate_gte(Date createDate) {	
        createDate = DateUtils.getOfDayFirst((Date)createDate);	
        this.sqlMap.getWhere().and("create_date", QueryType.GTE, createDate);	
    }	
	
    public String[] getStatus_in() {	
        return (String[])this.sqlMap.getWhere().getValue("status", QueryType.IN);	
    }	
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")	
    public Date getUpdateDate() {	
        return this.updateDate;	
    }	
	
    public String getCreateByName() {	
        return this.createByName;	
    }	
	
    public void setLastUpdateDateTime(Long lastUpdateDateTime) {	
        this.lastUpdateDateTime = lastUpdateDateTime;	
    }	
	
    public void setUpdateDate_gte(Date updateDate) {	
        updateDate = DateUtils.getOfDayFirst((Date)updateDate);	
        this.sqlMap.getWhere().and("udate_date", QueryType.GTE, updateDate);	
    }	
	
    public void setCreateDate_lte(Date createDate) {	
        createDate = DateUtils.getOfDayLast((Date)createDate);	
        this.sqlMap.getWhere().and("create_date", QueryType.LTE, createDate);	
    }	
	
    public void setUpdateBy(String updateBy) {	
        this.updateBy = updateBy;	
    }	
	
    @Override	
    public void preInsert() {	
        DataEntity dataEntity;	
        User a2;	
        if (StringUtils.isBlank((CharSequence)this.getId())) {	
            this.setId(IdGen.nextId());	
        }	
        if (StringUtils.isBlank((CharSequence)this.getStatus())) {	
            this.setStatus(STATUS_NORMAL);	
        }	
        if (StringUtils.isNotBlank((CharSequence)(a2 = this.getCurrentUser()).getUserCode())) {	
            dataEntity = this;	
            this.updateBy = a2.getUserCode();	
            this.updateByName = a2.getUserName();	
        } else {	
            String a3 = User.SUPER_ADMIN_CODE.split(",")[0];	
            a2 = UserUtils.get(a3);	
            if (a2 == null) {	
                User user = a2 = new User();	
                user.setUserCode(a3);	
                user.setUserName(a3);	
            }	
            dataEntity = this;	
            DataEntity dataEntity2 = this;	
            dataEntity2.updateBy = a2.getUserCode();	
            dataEntity2.updateByName = a2.getUserName();	
        }	
        if (dataEntity.createBy == null) {	
            DataEntity dataEntity3 = this;	
            dataEntity3.createBy = dataEntity3.updateBy;	
            dataEntity3.createByName = dataEntity3.updateByName;	
        }	
        this.updateDate = new Date();	
        if (this.createDate == null) {	
            this.createDate = this.updateDate;	
        }	
    }	
	
    public DataEntity() {	
        this(null);	
    }	
	
    public Long getLastUpdateDateTime() {	
        return this.lastUpdateDateTime;	
    }	
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")	
    public Date getCreateDate() {	
        return this.createDate;	
    }	
	
    public Date getCreateDate_gte() {	
        return (Date)this.sqlMap.getWhere().getValue("create_date", QueryType.GTE);	
    }	
	
    public DataEntity(String id) {	
        super(id);	
    }	
	
    public String getUpdateByName() {	
        return this.updateByName;	
    }	
	
    public void setCreateDate(Date createDate) {	
        this.createDate = createDate;	
    }	
	
    public Date getUpdateDate_gte() {	
        return (Date)this.sqlMap.getWhere().getValue("udate_date", QueryType.GTE);	
    }	
	
    @Override	
    public void preUpdate() {	
        DataEntity dataEntity;	
        User a2 = this.getCurrentUser();	
        if (StringUtils.isNotBlank((CharSequence)a2.getUserCode())) {	
            dataEntity = this;	
            this.updateBy = a2.getUserCode();	
            this.updateByName = a2.getUserName();	
        } else {	
            String a3 = User.SUPER_ADMIN_CODE.split(",")[0];	
            a2 = UserUtils.get(a3);	
            if (a2 == null) {	
                User user = a2 = new User();	
                user.setUserCode(a3);	
                user.setUserName(a3);	
            }	
            dataEntity = this;	
            DataEntity dataEntity2 = this;	
            dataEntity2.updateBy = a2.getUserCode();	
            dataEntity2.updateByName = a2.getUserName();	
        }	
        dataEntity.updateDate = new Date();	
    }	
	
    public String getCreateBy() {	
        return this.createBy;	
    }	
	
    @Length(min=0, max=1, message="\u72b6\u6001\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getStatus() {	
        return this.status;	
    }	
	
    public Date getUpdateDate_lte() {	
        return (Date)this.sqlMap.getWhere().getValue("udate_date", QueryType.LTE);	
    }	
	
    public String getUpdateBy() {	
        return this.updateBy;	
    }	
	
    public void setUpdateByName(String updateByName) {	
        this.updateByName = updateByName;	
    }	
}	
	
