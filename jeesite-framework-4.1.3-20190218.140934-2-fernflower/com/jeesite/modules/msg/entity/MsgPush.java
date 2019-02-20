package com.jeesite.modules.msg.entity;	
	
import com.fasterxml.jackson.annotation.JsonFormat;	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.autoconfigure.sys.Sys0AutoConfiguration;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import java.util.Date;	
import java.util.HashMap;	
import java.util.Map;	
import javax.validation.constraints.NotBlank;	
import javax.validation.constraints.NotNull;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.ptql.QueryLoadException;	
	
@Table(	
   name = "${_prefix}sys_msg_push",	
   alias = "a",	
   columns = {@Column(	
   name = "id",	
   attrName = "id",	
   label = "编号",	
   isPK = true	
), @Column(	
   name = "msg_type",	
   attrName = "msgType",	
   label = "消息类型",	
   comment = "消息类型（PC APP 短信 邮件 微信）"	
), @Column(	
   name = "msg_title",	
   attrName = "msgTitle",	
   label = "消息标题",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "msg_content",	
   attrName = "msgContent",	
   label = "消息内容"	
), @Column(	
   name = "biz_key",	
   attrName = "bizKey",	
   label = "业务主键"	
), @Column(	
   name = "biz_type",	
   attrName = "bizType",	
   label = "业务类型"	
), @Column(	
   name = "receive_code",	
   attrName = "receiveCode",	
   label = "接受者账号"	
), @Column(	
   name = "receive_user_code",	
   attrName = "receiveUserCode",	
   label = "接受者用户编码"	
), @Column(	
   name = "receive_user_name",	
   attrName = "receiveUserName",	
   label = "接受者用户姓名",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "send_user_code",	
   attrName = "sendUserCode",	
   label = "发送者用户编码"	
), @Column(	
   name = "send_user_name",	
   attrName = "sendUserName",	
   label = "发送者用户姓名",	
   queryType = QueryType.LIKE	
), @Column(	
   name = "send_date",	
   attrName = "sendDate",	
   label = "发送时间"	
), @Column(	
   name = "is_merge_push",	
   attrName = "isMergePush",	
   label = "是否合并推送"	
), @Column(	
   name = "plan_push_date",	
   attrName = "planPushDate",	
   label = "计划推送时间"	
), @Column(	
   name = "push_number",	
   attrName = "pushNumber",	
   label = "推送尝试次数"	
), @Column(	
   name = "push_return_code",	
   attrName = "pushReturnCode",	
   label = "推送返回结果码"	
), @Column(	
   name = "push_return_msg_id",	
   attrName = "pushReturnMsgId",	
   label = "推送返回消息编号"	
), @Column(	
   name = "push_return_content",	
   attrName = "pushReturnContent",	
   label = "推送返回的内容信息"	
), @Column(	
   name = "push_status",	
   attrName = "pushStatus",	
   label = "推送状态",	
   comment = "推送状态（0未推送 1成功  2失败）"	
), @Column(	
   name = "push_date",	
   attrName = "pushDate",	
   label = "推送时间"	
), @Column(	
   name = "read_status",	
   attrName = "readStatus",	
   label = "读取状态",	
   comment = "读取状态（0未送达 1已读 2未读）"	
), @Column(	
   name = "read_date",	
   attrName = "readDate",	
   label = "读取时间"	
)},	
   orderBy = "a.id DESC"	
)	
public class MsgPush extends DataEntity {	
   public static final String PUSH_STATUS_NONE = "0";	
   private String sendUserCode;	
   private String readStatus;	
   private String pushStatus;	
   public static final String TYPE_WEIXIN = "weixin";	
   public static final String TYPE_APP = "app";	
   private String bizKey;	
   public static final String PUSH_STATUS_FAIL = "2";	
   public static final String TYPE_SMS = "sms";	
   public static final String PUSH_STATUS_SUCCESS = "1";	
   private Date sendDate;	
   private String msgType;	
   private String sendUserName;	
   private String bizType;	
   private String pushReturnCode;	
   private static final long serialVersionUID = 1L;	
   private String receiveCode;	
   private Integer pushNumber;	
   public static final String READ_STATUS_UNREAD = "2";	
   private Date pushDate;	
   private Date readDate;	
   private String receiveUserCode;	
   public static final String READ_STATUS_READ = "1";	
   public static final String TYPE_EMAIL = "email";	
   public static final String TYPE_PC = "pc";	
   private String isMergePush;	
   private String receiveUserName;	
   private Long mergePushCount;	
   private String pushReturnContent;	
   private String msgTitle;	
   private String msgContent;	
   public static final String READ_STATUS_NONE = "0";	
   private String pushReturnMsgId;	
   private Date planPushDate;	
	
   @Length(	
      min = 0,	
      max = 1,	
      message = "是否合并推送长度不能超过 1 个字符"	
   )	
   public String getIsMergePush() {	
      return this.isMergePush;	
   }	
	
   public Integer getPushNumber() {	
      return this.pushNumber;	
   }	
	
   public void setPushNumber(Integer pushNumber) {	
      this.pushNumber = pushNumber;	
   }	
	
   public void setBizType(String bizType) {	
      this.bizType = bizType;	
   }	
	
   @NotBlank(	
      message = "发送者用户姓名不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "发送者用户姓名长度不能超过 100 个字符"	
   )	
   public String getSendUserName() {	
      return this.sendUserName;	
   }	
	
   @Length(	
      min = 0,	
      max = 1,	
      message = "读取状态长度不能超过 1 个字符"	
   )	
   public String getReadStatus() {	
      return this.readStatus;	
   }	
	
   @NotBlank(	
      message = "接受者用户编码不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 64,	
      message = "接受者用户编码长度不能超过 64 个字符"	
   )	
   public String getReceiveUserCode() {	
      return this.receiveUserCode;	
   }	
	
   @NotBlank(	
      message = "接受者用户姓名不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 100,	
      message = "接受者用户姓名长度不能超过 100 个字符"	
   )	
   public String getReceiveUserName() {	
      return this.receiveUserName;	
   }	
	
   @Length(	
      min = 0,	
      max = 200,	
      message = "推送返回结果码长度不能超过 200 个字符"	
   )	
   public String getPushReturnCode() {	
      return this.pushReturnCode;	
   }	
	
   public void setMsgType(String msgType) {	
      this.msgType = msgType;	
   }	
	
   public Date getPlanPushDate_lte() {	
      return (Date)this.sqlMap.getWhere().getValue("plan_push_date", QueryType.LTE);	
   }	
	
   @NotBlank(	
      message = "消息标题不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 200,	
      message = "消息标题长度不能超过 200 个字符"	
   )	
   public String getMsgTitle() {	
      return this.msgTitle;	
   }	
	
   @Length(	
      min = 0,	
      max = 64,	
      message = "业务主键长度不能超过 64 个字符"	
   )	
   public String getBizKey() {	
      return this.bizKey;	
   }	
	
   public void setReadDate(Date readDate) {	
      this.readDate = readDate;	
   }	
	
   public Date getSendDate_gte() {	
      return (Date)this.sqlMap.getWhere().getValue("send_date", QueryType.GTE);	
   }	
	
   public void addPushReturnContent(String pushReturnContent) {	
      this.setPushNumber(ObjectUtils.toInteger(this.getPushNumber()) + 1);	
      if (StringUtils.isBlank(this.pushReturnContent)) {	
         this.pushReturnContent = "";	
      } else {	
         this.pushReturnContent = this.pushReturnContent + "；第" + this.pushNumber + "次：";	
      }	
	
      this.pushReturnContent = this.pushReturnContent + pushReturnContent;	
   }	
	
   public void setSendDate_lte(Date sendDate) {	
      this.sqlMap.getWhere().and("send_date", QueryType.LTE, sendDate);	
   }	
	
   public String[] getMsgType_in() {	
      return (String[])this.sqlMap.getWhere().getValue("msg_type", QueryType.IN);	
   }	
	
   @NotBlank(	
      message = "消息类型不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 16,	
      message = "消息类型长度不能超过 16 个字符"	
   )	
   public String getMsgType() {	
      return this.msgType;	
   }	
	
   @NotBlank(	
      message = "发送者用户编码不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 64,	
      message = "发送者用户编码长度不能超过 64 个字符"	
   )	
   public String getSendUserCode() {	
      return this.sendUserCode;	
   }	
	
   public MsgPush(String id) {	
      super(id);	
   }	
	
   public void setSendDate(Date sendDate) {	
      this.sendDate = sendDate;	
   }	
	
   public void setPushReturnContent(String pushReturnContent) {	
      this.pushReturnContent = pushReturnContent;	
   }	
	
   public void setPushReturnCode(String pushReturnCode) {	
      this.pushReturnCode = pushReturnCode;	
   }	
	
   public Object parseMsgContent(Class clazz) {	
      if (StringUtils.isNotBlank(this.msgContent)) {	
         try {	
            return JsonMapper.getInstance().readValue(this.msgContent, clazz);	
         } catch (Exception var5) {	
            try {	
               BaseMsgContent a = (BaseMsgContent)clazz.newInstance();	
               a.setTitle("格式错误");	
               a.setContent("内容格式错误！");	
               return a;	
            } catch (Exception var4) {	
            }	
         }	
      }	
	
      return null;	
   }	
	
   public MsgPush() {	
      this((String)null);	
   }	
	
   @Length(	
      min = 0,	
      max = 200,	
      message = "推送返回消息编号长度不能超过 200 个字符"	
   )	
   public String getPushReturnMsgId() {	
      return this.pushReturnMsgId;	
   }	
	
   @JsonFormat(	
      pattern = "yyyy-MM-dd HH:mm:ss"	
   )	
   public Date getPlanPushDate() {	
      return this.planPushDate;	
   }	
	
   public void setMsgTitle(String msgTitle) {	
      this.msgTitle = msgTitle;	
   }	
	
   @Length(	
      min = 0,	
      max = 1,	
      message = "推送状态长度不能超过 1 个字符"	
   )	
   public String getPushStatus() {	
      return this.pushStatus;	
   }	
	
   public void setReceiveCode(String receiveCode) {	
      this.receiveCode = receiveCode;	
   }	
	
   @JsonFormat(	
      pattern = "yyyy-MM-dd HH:mm:ss"	
   )	
   @NotNull(	
      message = "发送时间不能为空"	
   )	
   public Date getSendDate() {	
      return this.sendDate;	
   }	
	
   @JsonFormat(	
      pattern = "yyyy-MM-dd HH:mm:ss"	
   )	
   public Date getPushDate() {	
      return this.pushDate;	
   }	
	
   public void setSendUserName(String sendUserName) {	
      this.sendUserName = sendUserName;	
   }	
	
   @JsonFormat(	
      pattern = "yyyy-MM-dd HH:mm:ss"	
   )	
   public Date getReadDate() {	
      return this.readDate;	
   }	
	
   public Date getSendDate_lte() {	
      return (Date)this.sqlMap.getWhere().getValue("send_date", QueryType.LTE);	
   }	
	
   public Long getMergePushCount() {	
      return this.mergePushCount;	
   }	
	
   @Length(	
      min = 0,	
      max = 64,	
      message = "业务类型长度不能超过 64 个字符"	
   )	
   public String getBizType() {	
      return this.bizType;	
   }	
	
   public void setReadStatus(String readStatus) {	
      this.readStatus = readStatus;	
   }	
	
   public void setSendDate_gte(Date sendDate) {	
      this.sqlMap.getWhere().and("send_date", QueryType.GTE, sendDate);	
   }	
	
   public void setMsgType_in(String[] msgTypes) {	
      this.sqlMap.getWhere().and("msg_type", QueryType.IN, msgTypes);	
   }	
	
   public Date getPlanPushDate_gte() {	
      return (Date)this.sqlMap.getWhere().getValue("plan_push_date", QueryType.GTE);	
   }	
	
   public void setIsMergePush(String isMergePush) {	
      this.isMergePush = isMergePush;	
   }	
	
   public void setPushDate(Date pushDate) {	
      this.pushDate = pushDate;	
   }	
	
   public void setPlanPushDate(Date planPushDate) {	
      this.planPushDate = planPushDate;	
   }	
	
   public Map getMsgContentEntity() {	
      if (StringUtils.isNotBlank(this.msgContent)) {	
         try {	
            return (Map)JsonMapper.getInstance().readValue(this.msgContent, Map.class);	
         } catch (Exception var2) {	
         }	
      }	
	
      HashMap a;	
      (a = MapUtils.newHashMap()).put("title", "格式错误");	
      a.put("content", "内容格式错误！");	
      return a;	
   }	
	
   public void setPlanPushDate_gte(Date planPushDate) {	
      this.sqlMap.getWhere().and("plan_push_date", QueryType.GTE, planPushDate);	
   }	
	
   public void setSendUserCode(String sendUserCode) {	
      this.sendUserCode = sendUserCode;	
   }	
	
   public void setMsgContentEntity(BaseMsgContent msgContentEntity) {	
      if (msgContentEntity != null) {	
         this.msgType = msgContentEntity.getMsgType();	
         MsgPush var10000;	
         if (StringUtils.isNotBlank(msgContentEntity.getTitle())) {	
            var10000 = this;	
            this.msgTitle = msgContentEntity.getTitle();	
         } else {	
            var10000 = this;	
            this.msgTitle = StringUtils.abbr(msgContentEntity.getContent(), 50);	
         }	
	
         var10000.msgContent = JsonMapper.toJson(msgContentEntity);	
      }	
	
   }	
	
   public void setMergePushCount(Long mergePushCount) {	
      this.mergePushCount = mergePushCount;	
   }	
	
   public void setReceiveUserCode(String receiveUserCode) {	
      this.receiveUserCode = receiveUserCode;	
   }	
	
   public void setMsgContent(String msgContent) {	
      this.msgContent = msgContent;	
   }	
	
   public void setBizKey(String bizKey) {	
      this.bizKey = bizKey;	
   }	
	
   public void setPushStatus(String pushStatus) {	
      this.pushStatus = pushStatus;	
   }	
	
   @JsonIgnore	
   @NotBlank(	
      message = "消息内容不能为空"	
   )	
   public String getMsgContent() {	
      return this.msgContent;	
   }	
	
   public void setPushReturnMsgId(String pushReturnMsgId) {	
      this.pushReturnMsgId = pushReturnMsgId;	
   }	
	
   public String getPushReturnContent() {	
      return this.pushReturnContent;	
   }	
	
   public void setReceiveUserName(String receiveUserName) {	
      this.receiveUserName = receiveUserName;	
   }	
	
   @NotBlank(	
      message = "接受者账号不能为空"	
   )	
   @Length(	
      min = 0,	
      max = 64,	
      message = "接受者账号长度不能超过 64 个字符"	
   )	
   public String getReceiveCode() {	
      return this.receiveCode;	
   }	
	
   public void setPlanPushDate_lte(Date planPushDate) {	
      this.sqlMap.getWhere().and("plan_push_date", QueryType.LTE, planPushDate);	
   }	
}	
