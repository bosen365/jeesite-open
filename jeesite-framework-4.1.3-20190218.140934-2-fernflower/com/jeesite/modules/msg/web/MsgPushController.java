package com.jeesite.modules.msg.web;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.provider.UpdateSqlProvider;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.MsgPushed;	
import com.jeesite.modules.msg.service.MsgPushService;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.SysInfo;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.ModelAttribute;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping({"${adminPath}/msg/msgPush"})	
@ConditionalOnProperty(	
   name = {"msg.enabled", "web.core.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class MsgPushController extends BaseController {	
   @Autowired	
   private MsgPushService msgPushService;	
	
   @RequiresPermissions({"msg:msgPush:view"})	
   @RequestMapping({"list", ""})	
   public String list(MsgPush msgPush, boolean pushed, Model model) {	
      model.addAttribute("pushed", pushed);	
      model.addAttribute("msgPush", msgPush);	
      return "modules/msg/msgPushList";	
   }	
	
   @RequiresPermissions({"msg:msgPush:view"})	
   @RequestMapping({"listData"})	
   @ResponseBody	
   public Page listData(MsgPush msgPush, boolean pushed, HttpServletRequest request, HttpServletResponse response) {	
      if (pushed) {	
         msgPush = new MsgPushed((MsgPush)msgPush);	
      }	
	
      ((MsgPush)msgPush).setPage(new Page(request, response));	
      return this.msgPushService.findPage((MsgPush)msgPush);	
   }	
	
   @ModelAttribute	
   public MsgPush get(String id, boolean isNewRecord, boolean pushed) {	
      MsgPush a = null;	
      if (StringUtils.isNotBlank(id)) {	
         MsgPush a = new MsgPush(id);	
         if (pushed) {	
            a = new MsgPushed((MsgPush)a);	
         }	
	
         a = this.msgPushService.get((MsgPush)a);	
      }	
	
      if (a == null) {	
         a = new MsgPush();	
      }	
	
      return a;	
   }	
	
   @RequiresPermissions({"msg:msgPush:view"})	
   @RequestMapping({"form"})	
   public String form(MsgPush msgPush, boolean pushed, Model model) {	
      model.addAttribute("pushed", pushed);	
      model.addAttribute("msgPush", msgPush);	
      return "modules/msg/msgPushForm";	
   }	
	
   @RequiresPermissions({"msg:msgPush:edit"})	
   @RequestMapping({"delete"})	
   @ResponseBody	
   public String delete(MsgPush msgPush, boolean pushed) {	
      if (pushed) {	
         msgPush = new MsgPushed((MsgPush)msgPush);	
      }	
	
      this.msgPushService.delete((MsgPush)msgPush);	
      String var10002 = "删除消息推送成功！";	
      String[] var10003 = new String[0];	
      boolean var10005 = true;	
      return this.renderResult("true", text(var10002, var10003));	
   }	
}	
