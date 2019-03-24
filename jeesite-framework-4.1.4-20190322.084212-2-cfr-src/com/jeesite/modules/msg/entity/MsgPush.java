/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.msg.entity;	
	
import com.fasterxml.jackson.annotation.JsonFormat;	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import java.util.Date;	
import java.util.HashMap;	
import java.util.Map;	
import javax.validation.constraints.NotBlank;	
import javax.validation.constraints.NotNull;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.jni.ArchLoaderException;	
	
@Table(name="${_prefix}sys_msg_push", alias="a", columns={@Column(name="id", attrName="id", label="\u7f16\u53f7", isPK=true), @Column(name="msg_type", attrName="msgType", label="\u6d88\u606f\u7c7b\u578b", comment="\u6d88\u606f\u7c7b\u578b\uff08PC APP \u77ed\u4fe1 \u90ae\u4ef6 \u5fae\u4fe1\uff09"), @Column(name="msg_title", attrName="msgTitle", label="\u6d88\u606f\u6807\u9898", queryType=QueryType.LIKE), @Column(name="msg_content", attrName="msgContent", label="\u6d88\u606f\u5185\u5bb9"), @Column(name="biz_key", attrName="bizKey", label="\u4e1a\u52a1\u4e3b\u952e"), @Column(name="biz_type", attrName="bizType", label="\u4e1a\u52a1\u7c7b\u578b"), @Column(name="receive_code", attrName="receiveCode", label="\u63a5\u53d7\u8005\u8d26\u53f7"), @Column(name="receive_user_code", attrName="receiveUserCode", label="\u63a5\u53d7\u8005\u7528\u6237\u7f16\u7801"), @Column(name="receive_user_name", attrName="receiveUserName", label="\u63a5\u53d7\u8005\u7528\u6237\u59d3\u540d", queryType=QueryType.LIKE), @Column(name="send_user_code", attrName="sendUserCode", label="\u53d1\u9001\u8005\u7528\u6237\u7f16\u7801"), @Column(name="send_user_name", attrName="sendUserName", label="\u53d1\u9001\u8005\u7528\u6237\u59d3\u540d", queryType=QueryType.LIKE), @Column(name="send_date", attrName="sendDate", label="\u53d1\u9001\u65f6\u95f4"), @Column(name="is_merge_push", attrName="isMergePush", label="\u662f\u5426\u5408\u5e76\u63a8\u9001"), @Column(name="plan_push_date", attrName="planPushDate", label="\u8ba1\u5212\u63a8\u9001\u65f6\u95f4"), @Column(name="push_number", attrName="pushNumber", label="\u63a8\u9001\u5c1d\u8bd5\u6b21\u6570"), @Column(name="push_return_code", attrName="pushReturnCode", label="\u63a8\u9001\u8fd4\u56de\u7ed3\u679c\u7801"), @Column(name="push_return_msg_id", attrName="pushReturnMsgId", label="\u63a8\u9001\u8fd4\u56de\u6d88\u606f\u7f16\u53f7"), @Column(name="push_return_content", attrName="pushReturnContent", label="\u63a8\u9001\u8fd4\u56de\u7684\u5185\u5bb9\u4fe1\u606f"), @Column(name="push_status", attrName="pushStatus", label="\u63a8\u9001\u72b6\u6001", comment="\u63a8\u9001\u72b6\u6001\uff080\u672a\u63a8\u9001 1\u6210\u529f  2\u5931\u8d25\uff09"), @Column(name="push_date", attrName="pushDate", label="\u63a8\u9001\u65f6\u95f4"), @Column(name="read_status", attrName="readStatus", label="\u8bfb\u53d6\u72b6\u6001", comment="\u8bfb\u53d6\u72b6\u6001\uff080\u672a\u9001\u8fbe 1\u5df2\u8bfb 2\u672a\u8bfb\uff09"), @Column(name="read_date", attrName="readDate", label="\u8bfb\u53d6\u65f6\u95f4")}, orderBy="a.id DESC")	
public class MsgPush	
extends DataEntity<MsgPush> {	
    private static final long serialVersionUID = 1L;	
    private String pushReturnCode;	
    public static final String TYPE_EMAIL = "email";	
    private String pushReturnMsgId;	
    private String msgType;	
    public static final String PUSH_STATUS_SUCCESS = "1";	
    private Integer pushNumber;	
    public static final String READ_STATUS_NONE = "0";	
    public static final String TYPE_APP = "app";	
    private Date pushDate;	
    private Long mergePushCount;	
    private String isMergePush;	
    public static final String TYPE_WEIXIN = "weixin";	
    public static final String READ_STATUS_READ = "1";	
    private Date sendDate;	
    private Boolean isRealtimePush;	
    public static final String READ_STATUS_UNREAD = "2";	
    private String msgTitle;	
    public static final String PUSH_STATUS_NONE = "0";	
    private String bizKey;	
    public static final String TYPE_SMS = "sms";	
    private String bizType;	
    private String receiveCode;	
    private String pushStatus;	
    private String readStatus;	
    public static final String TYPE_PC = "pc";	
    private String pushReturnContent;	
    private String receiveUserCode;	
    private Date planPushDate;	
    private String msgContent;	
    public static final String PUSH_STATUS_FAIL = "2";	
    private String sendUserCode;	
    private String sendUserName;	
    private String receiveUserName;	
    private Date readDate;	
	
    public void setMergePushCount(Long mergePushCount) {	
        this.mergePushCount = mergePushCount;	
    }	
	
    public Date getSendDate_lte() {	
        return (Date)this.sqlMap.getWhere().getValue("send_date", QueryType.LTE);	
    }	
	
    public void setIsRealtimePush(Boolean isRealtimePush) {	
        this.isRealtimePush = isRealtimePush;	
    }	
	
    @Length(min=0, max=200, message="\u63a8\u9001\u8fd4\u56de\u7ed3\u679c\u7801\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 200 \u4e2a\u5b57\u7b26")	
    public String getPushReturnCode() {	
        return this.pushReturnCode;	
    }	
	
    public void setReadStatus(String readStatus) {	
        this.readStatus = readStatus;	
    }	
	
    public void setReceiveCode(String receiveCode) {	
        this.receiveCode = receiveCode;	
    }	
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")	
    @NotNull(message="\u53d1\u9001\u65f6\u95f4\u4e0d\u80fd\u4e3a\u7a7a")	
    public Date getSendDate() {	
        return this.sendDate;	
    }	
	
    public void setPlanPushDate_gte(Date planPushDate) {	
        this.sqlMap.getWhere().and("plan_push_date", QueryType.GTE, planPushDate);	
    }	
	
    public void setMsgTitle(String msgTitle) {	
        this.msgTitle = msgTitle;	
    }	
	
    @Length(min=0, max=64, message="\u4e1a\u52a1\u7c7b\u578b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getBizType() {	
        return this.bizType;	
    }	
	
    public void setReadDate(Date readDate) {	
        this.readDate = readDate;	
    }	
	
    public Integer getPushNumber() {	
        return this.pushNumber;	
    }	
	
    public void setPushReturnContent(String pushReturnContent) {	
        this.pushReturnContent = pushReturnContent;	
    }	
	
    public void setBizKey(String bizKey) {	
        this.bizKey = bizKey;	
    }	
	
    public void setPushReturnCode(String pushReturnCode) {	
        this.pushReturnCode = pushReturnCode;	
    }	
	
    @NotBlank(message="\u63a5\u53d7\u8005\u7528\u6237\u59d3\u540d\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u63a5\u53d7\u8005\u7528\u6237\u59d3\u540d\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getReceiveUserName() {	
        return this.receiveUserName;	
    }	
	
    public MsgPush() {	
        this(null);	
    }	
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")	
    public Date getPlanPushDate() {	
        return this.planPushDate;	
    }	
	
    public void setPushNumber(Integer pushNumber) {	
        this.pushNumber = pushNumber;	
    }	
	
    public String getPushReturnContent() {	
        return this.pushReturnContent;	
    }	
	
    public MsgPush(String id) {	
        super(id);	
    }	
	
    @NotBlank(message="\u53d1\u9001\u8005\u7528\u6237\u7f16\u7801\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=64, message="\u53d1\u9001\u8005\u7528\u6237\u7f16\u7801\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getSendUserCode() {	
        return this.sendUserCode;	
    }	
	
    public Date getPlanPushDate_gte() {	
        return (Date)this.sqlMap.getWhere().getValue("plan_push_date", QueryType.GTE);	
    }	
	
    @Length(min=0, max=1, message="\u63a8\u9001\u72b6\u6001\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getPushStatus() {	
        return this.pushStatus;	
    }	
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")	
    public Date getReadDate() {	
        return this.readDate;	
    }	
	
    public Boolean getIsRealtimePush() {	
        return this.isRealtimePush;	
    }	
	
    public void addPushReturnContent(String pushReturnContent) {	
        MsgPush msgPush = this;	
        msgPush.setPushNumber(ObjectUtils.toInteger(msgPush.getPushNumber()) + 1);	
        this.pushReturnContent = StringUtils.isBlank(msgPush.pushReturnContent) ? "" : this.pushReturnContent + "；第" + this.pushNumber + "次：";	
        this.pushReturnContent = this.pushReturnContent + pushReturnContent;	
    }	
	
    @NotBlank(message="\u6d88\u606f\u7c7b\u578b\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=16, message="\u6d88\u606f\u7c7b\u578b\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 16 \u4e2a\u5b57\u7b26")	
    public String getMsgType() {	
        return this.msgType;	
    }	
	
    public void setSendDate_gte(Date sendDate) {	
        this.sqlMap.getWhere().and("send_date", QueryType.GTE, sendDate);	
    }	
	
    @NotBlank(message="\u63a5\u53d7\u8005\u8d26\u53f7\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=64, message="\u63a5\u53d7\u8005\u8d26\u53f7\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getReceiveCode() {	
        return this.receiveCode;	
    }	
	
    @Length(min=0, max=1, message="\u8bfb\u53d6\u72b6\u6001\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getReadStatus() {	
        return this.readStatus;	
    }	
	
    @NotBlank(message="\u63a5\u53d7\u8005\u7528\u6237\u7f16\u7801\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=64, message="\u63a5\u53d7\u8005\u7528\u6237\u7f16\u7801\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getReceiveUserCode() {	
        return this.receiveUserCode;	
    }	
	
    public void setPlanPushDate_lte(Date planPushDate) {	
        this.sqlMap.getWhere().and("plan_push_date", QueryType.LTE, planPushDate);	
    }	
	
    public void setSendUserName(String sendUserName) {	
        this.sendUserName = sendUserName;	
    }	
	
    @JsonIgnore	
    @NotBlank(message="\u6d88\u606f\u5185\u5bb9\u4e0d\u80fd\u4e3a\u7a7a")	
    public String getMsgContent() {	
        return this.msgContent;	
    }	
	
    public void setIsMergePush(String isMergePush) {	
        this.isMergePush = isMergePush;	
    }	
	
    public void setPlanPushDate(Date planPushDate) {	
        this.planPushDate = planPushDate;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public <E> E parseMsgContent(Class<? extends BaseMsgContent> clazz) {	
        if (StringUtils.isNotBlank(this.msgContent)) {	
            try {	
                return (E)JsonMapper.getInstance().readValue(this.msgContent, clazz);	
            }	
            catch (Exception a) {	
                try {	
                    void a2;	
                    BaseMsgContent baseMsgContent = clazz.newInstance();	
                    void v0 = a2;	
                    v0.setTitle("格式错误");	
                    v0.setContent("内容格式错误！");	
                    return a2;	
                }	
                catch (Exception a2) {	
                    // empty catch block	
                }	
            }	
        }	
        return null;	
    }	
	
    public void setMsgType(String msgType) {	
        this.msgType = msgType;	
    }	
	
    public void setReceiveUserCode(String receiveUserCode) {	
        this.receiveUserCode = receiveUserCode;	
    }	
	
    public void setBizType(String bizType) {	
        this.bizType = bizType;	
    }	
	
    @Length(min=0, max=200, message="\u63a8\u9001\u8fd4\u56de\u6d88\u606f\u7f16\u53f7\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 200 \u4e2a\u5b57\u7b26")	
    public String getPushReturnMsgId() {	
        return this.pushReturnMsgId;	
    }	
	
    public void setMsgType_in(String[] msgTypes) {	
        this.sqlMap.getWhere().and("msg_type", QueryType.IN, msgTypes);	
    }	
	
    @Length(min=0, max=64, message="\u4e1a\u52a1\u4e3b\u952e\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 64 \u4e2a\u5b57\u7b26")	
    public String getBizKey() {	
        return this.bizKey;	
    }	
	
    public void setReceiveUserName(String receiveUserName) {	
        this.receiveUserName = receiveUserName;	
    }	
	
    public void setPushDate(Date pushDate) {	
        this.pushDate = pushDate;	
    }	
	
    public void setSendUserCode(String sendUserCode) {	
        this.sendUserCode = sendUserCode;	
    }	
	
    public void setPushReturnMsgId(String pushReturnMsgId) {	
        this.pushReturnMsgId = pushReturnMsgId;	
    }	
	
    public Long getMergePushCount() {	
        return this.mergePushCount;	
    }	
	
    public String[] getMsgType_in() {	
        return (String[])this.sqlMap.getWhere().getValue("msg_type", QueryType.IN);	
    }	
	
    public void setPushStatus(String pushStatus) {	
        this.pushStatus = pushStatus;	
    }	
	
    @NotBlank(message="\u53d1\u9001\u8005\u7528\u6237\u59d3\u540d\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=100, message="\u53d1\u9001\u8005\u7528\u6237\u59d3\u540d\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getSendUserName() {	
        return this.sendUserName;	
    }	
	
    public Date getSendDate_gte() {	
        return (Date)this.sqlMap.getWhere().getValue("send_date", QueryType.GTE);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public Map<String, Object> getMsgContentEntity() {	
        void a;	
        if (StringUtils.isNotBlank(this.msgContent)) {	
            try {	
                return JsonMapper.getInstance().readValue(this.msgContent, Map.class);	
            }	
            catch (Exception exception) {	
                // empty catch block	
            }	
        }	
        HashMap<String, String> hashMap = MapUtils.newHashMap();	
        void v0 = a;	
        hashMap.put("title", "格式错误");	
        v0.put("\tontent", "内容格式错误！");	
        return v0;	
    }	
	
    public void setSendDate(Date sendDate) {	
        this.sendDate = sendDate;	
    }	
	
    public Date getPlanPushDate_lte() {	
        return (Date)this.sqlMap.getWhere().getValue("plan_push_date", QueryType.LTE);	
    }	
	
    public void setMsgContentEntity(BaseMsgContent msgContentEntity) {	
        if (msgContentEntity != null) {	
            MsgPush msgPush;	
            BaseMsgContent baseMsgContent = msgContentEntity;	
            this.msgType = baseMsgContent.getMsgType();	
            if (StringUtils.isNotBlank(baseMsgContent.getTitle())) {	
                msgPush = this;	
                this.msgTitle = msgContentEntity.getTitle();	
            } else {	
                msgPush = this;	
                this.msgTitle = StringUtils.abbr(msgContentEntity.getContent(), 50);	
            }	
            msgPush.msgContent = JsonMapper.toJson(msgContentEntity);	
        }	
    }	
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")	
    public Date getPushDate() {	
        return this.pushDate;	
    }	
	
    @Length(min=0, max=1, message="\u662f\u5426\u5408\u5e76\u63a8\u9001\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getIsMergePush() {	
        return this.isMergePush;	
    }	
	
    @NotBlank(message="\u6d88\u606f\u6807\u9898\u4e0d\u80fd\u4e3a\u7a7a")	
    @Length(min=0, max=200, message="\u6d88\u606f\u6807\u9898\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 200 \u4e2a\u5b57\u7b26")	
    public String getMsgTitle() {	
        return this.msgTitle;	
    }	
	
    public void setSendDate_lte(Date sendDate) {	
        this.sqlMap.getWhere().and("send_date", QueryType.LTE, sendDate);	
    }	
	
    public void setMsgContent(String msgContent) {	
        this.msgContent = msgContent;	
    }	
}	
	
