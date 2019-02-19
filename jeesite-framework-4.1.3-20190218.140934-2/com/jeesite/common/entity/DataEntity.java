package com.jeesite.common.entity;	
	
import com.fasterxml.jackson.annotation.JsonFormat;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.validator.OptimisticLock;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import com.jeesite.modules.sys.utils.ValidCodeUtils;	
import java.util.Date;	
import org.apache.commons.lang3.StringUtils;	
import org.hibernate.validator.constraints.Length;	
	
@Table(	
   columns = {@Column(	
   name = "status",	
   attrName = "status",	
   label = "状态",	
   isUpdate = false,	
   comment = "（推荐状态：0：正常；1：删除；2：停用；3：冻结；4：审核、待审核；5：审核驳回；9：草稿）"	
), @Column(	
   name = "create_by",	
   attrName = "createBy",	
   label = "创建者",	
   isUpdate = false	
), @Column(	
   name = "create_date",	
   attrName = "createDate",	
   label = "创建时间",	
   isUpdate = false,	
   isQuery = false	
), @Column(	
   name = "update_by",	
   attrName = "updateBy",	
   label = "更新者",	
   isUpdate = true	
), @Column(	
   name = "update_date",	
   attrName = "updateDate",	
   label = "更新时间",	
   isUpdate = true,	
   isQuery = false	
), @Column(	
   name = "remarks",	
   attrName = "remarks",	
   label = "备注信息",	
   queryType = QueryType.LIKE	
)}	
)	
@OptimisticLock	
public abstract class DataEntity extends BaseEntity {	
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
      updateDate = DateUtils.getOfDayLast(updateDate);	
      this.sqlMap.getWhere().and("update_date", QueryType.LTE, updateDate);	
   }	
	
   public void setCreateDate_between(String createDateStr) {	
      Date[] a;	
      Date[] var10001 = a = DateUtils.parseDateBetweenString(createDateStr);	
      this.setCreateDate_gte(a[0]);	
      this.setCreateDate_lte(var10001[1]);	
   }	
	
   public String getUpdateDate_between() {	
      Date a = this.getUpdateDate_gte();	
      Date a = this.getUpdateDate_lte();	
      return DateUtils.formatDateBetweenString(a, a);	
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
      Date a = this.getCreateDate_gte();	
      Date a = this.getCreateDate_lte();	
      return DateUtils.formatDateBetweenString(a, a);	
   }	
	
   public void setUpdateDate_between(String updateDateStr) {	
      Date[] a;	
      Date[] var10001 = a = DateUtils.parseDateBetweenString(updateDateStr);	
      this.setUpdateDate_gte(a[0]);	
      this.setUpdateDate_lte(var10001[1]);	
   }	
	
   public void setRemarks(String remarks) {	
      this.remarks = remarks;	
   }	
	
   @Length(	
      min = 0,	
      max = 255	
   )	
   public String getRemarks() {	
      return this.remarks;	
   }	
	
   public void setCreateDate_gte(Date createDate) {	
      createDate = DateUtils.getOfDayFirst(createDate);	
      this.sqlMap.getWhere().and("create_date", QueryType.GTE, createDate);	
   }	
	
   public String[] getStatus_in() {	
      return (String[])this.sqlMap.getWhere().getValue("status", QueryType.IN);	
   }	
	
   @JsonFormat(	
      pattern = "yyyy-MM-dd HH:mm"	
   )	
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
      updateDate = DateUtils.getOfDayFirst(updateDate);	
      this.sqlMap.getWhere().and("udate_date", QueryType.GTE, updateDate);	
   }	
	
   public void setCreateDate_lte(Date createDate) {	
      createDate = DateUtils.getOfDayLast(createDate);	
      this.sqlMap.getWhere().and("create_date", QueryType.LTE, createDate);	
   }	
	
   public void setUpdateBy(String updateBy) {	
      this.updateBy = updateBy;	
   }	
	
   public void preInsert() {	
      if (StringUtils.isBlank(this.getId())) {	
         this.setId(IdGen.nextId());	
      }	
	
      if (StringUtils.isBlank(this.getStatus())) {	
         this.setStatus("0");	
      }	
	
      DataEntity var10000;	
      User a;	
      if (StringUtils.isNotBlank((a = this.getCurrentUser()).getUserCode())) {	
         var10000 = this;	
         this.updateBy = a.getUserCode();	
         this.updateByName = a.getUserName();	
      } else {	
         String a;	
         if ((a = UserUtils.get(a = User.SUPER_ADMIN_CODE.split(",")[0])) == null) {	
            a = new User();	
            a.setUserCode(a);	
            a.setUserName(a);	
         }	
	
         var10000 = this;	
         this.updateBy = a.getUserCode();	
         this.updateByName = a.getUserName();	
      }	
	
      if (var10000.createBy == null) {	
         this.createBy = this.updateBy;	
         this.createByName = this.updateByName;	
      }	
	
      this.updateDate = new Date();	
      if (this.createDate == null) {	
         this.createDate = this.updateDate;	
      }	
	
   }	
	
   public DataEntity() {	
      this((String)null);	
   }	
	
   public Long getLastUpdateDateTime() {	
      return this.lastUpdateDateTime;	
   }	
	
   @JsonFormat(	
      pattern = "yyyy-MM-dd HH:mm"	
   )	
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
	
   public void preUpdate() {	
      DataEntity var10000;	
      User a;	
      if (StringUtils.isNotBlank((a = this.getCurrentUser()).getUserCode())) {	
         var10000 = this;	
         this.updateBy = a.getUserCode();	
         this.updateByName = a.getUserName();	
      } else {	
         String a;	
         if ((a = UserUtils.get(a = User.SUPER_ADMIN_CODE.split(",")[0])) == null) {	
            a = new User();	
            a.setUserCode(a);	
            a.setUserName(a);	
         }	
	
         var10000 = this;	
         this.updateBy = a.getUserCode();	
         this.updateByName = a.getUserName();	
      }	
	
      var10000.updateDate = new Date();	
   }	
	
   public String getCreateBy() {	
      return this.createBy;	
   }	
	
   @Length(	
      min = 0,	
      max = 1,	
      message = "状态长度不能超过 1 个字符"	
   )	
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
