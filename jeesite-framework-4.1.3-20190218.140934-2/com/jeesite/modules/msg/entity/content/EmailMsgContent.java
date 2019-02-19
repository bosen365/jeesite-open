package com.jeesite.modules.msg.entity.content;	
	
public class EmailMsgContent extends BaseMsgContent {	
   private String bcc;	
   private String cc;	
   private static final long serialVersionUID = 1L;	
	
   public String getBcc() {	
      return this.bcc;	
   }	
	
   public String getMsgType() {	
      return "email";	
   }	
	
   public void setBcc(String bcc) {	
      this.bcc = bcc;	
   }	
	
   public String getCc() {	
      return this.cc;	
   }	
	
   public void setCc(String cc) {	
      this.cc = cc;	
   }	
}	
