package com.jeesite.modules.msg.entity;	
	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import org.springframework.beans.BeanUtils;	
	
@Table(	
   name = "${_prefix}sys_msg_pushed",	
   alias = "a",	
   columns = {@Column(	
   includeEntity = MsgPush.class	
)},	
   orderBy = "a.id DESC"	
)	
public class MsgPushed extends MsgPush {	
   private static final long serialVersionUID = 1L;	
	
   public MsgPushed(String id) {	
      super(id);	
   }	
	
   public MsgPushed(MsgPush var1) {	
      BeanUtils.copyProperties(var1, this);	
   }	
	
   public MsgPushed() {	
      super((String)null);	
   }	
}	
