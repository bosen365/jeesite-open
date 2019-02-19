package com.jeesite.modules.msg.web;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.j.E;	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.msg.entity.MsgPush;	
import com.jeesite.modules.msg.entity.MsgPushed;	
import com.jeesite.modules.msg.service.MsgPushService;	
import com.jeesite.modules.msg.utils.MsgPcPoolUtils;	
import com.jeesite.modules.msg.utils.MsgPushUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.ArrayList;	
import java.util.Date;	
import java.util.Iterator;	
import java.util.List;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.ModelAttribute;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.RequestParam;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping({"${adminPath}/msg"})	
@ConditionalOnProperty(	
   name = {"msg.enabled", "web.core.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class MsgController extends BaseController {	
   @Autowired	
   private MsgPushService msgPushService;	
	
   @ModelAttribute	
   public MsgPush get() {	
      return new MsgPush();	
   }	
	
   @RequiresPermissions({"user"})	
   @ResponseBody	
   @RequestMapping({"unreadMsg"})	
   public Page unreadMsg(@RequestParam(defaultValue = "pc") String msgType, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize, Model model) {	
      String a = UserUtils.getUser().getUserCode();	
      MsgPush a = new MsgPush();	
      a.setMsgType(msgType);	
      a.setReceiveUserCode(a);	
      a.setReadStatus("2");	
      a.setPage(new Page(pageNo, pageSize));	
      Page a = this.msgPushService.findPage(a);	
      if ("pc".equals(msgType)) {	
         MsgPush a = new MsgPush();	
         a.setMsgType(msgType);	
         a.setReceiveUserCode(a);	
         a.setReadStatus("0");	
         List a = this.msgPushService.findList(a);	
         MsgPcPoolUtils.putPool(a, a);	
      }	
	
      return a;	
   }	
	
   @RequiresPermissions({"user"})	
   @ResponseBody	
   @RequestMapping({"pullPoolMsg"})	
   public List pullPoolMsg(Model model) {	
      String a;	
      List a;	
      Iterator var4 = (a = MsgPcPoolUtils.getPool(a = UserUtils.getUser().getUserCode())).iterator();	
	
      while(var4.hasNext()) {	
         MsgPush a = (MsgPush)var4.next();	
         MsgPush a;	
         if ((a = (MsgPush)this.msgPushService.get((String)a.getId())) != null) {	
            a.setReadStatus("2");	
            this.msgPushService.updateMsgPush(a);	
         }	
      }	
	
      ArrayList var10000 = ListUtils.newArrayList(a);	
      MsgPcPoolUtils.removeCache(a);	
      return var10000;	
   }	
	
   @RequiresPermissions({"user"})	
   @ResponseBody	
   @RequestMapping({"listData"})	
   public Page listData(MsgPush msgPush, boolean pushed, HttpServletRequest request, HttpServletResponse response, Model model) {	
      if (pushed) {	
         msgPush = new MsgPushed((MsgPush)msgPush);	
      }	
	
      if (StringUtils.isBlank(((MsgPush)msgPush).getMsgType())) {	
         ((MsgPush)msgPush).setMsgType("pc");	
      }	
	
      ((MsgPush)msgPush).setReceiveUserCode(((MsgPush)msgPush).getCurrentUser().getUserCode());	
      ((MsgPush)msgPush).setPage(new Page(request, response));	
      return this.msgPushService.findPage((MsgPush)msgPush);	
   }	
	
   @RequiresPermissions({"user"})	
   @ResponseBody	
   @RequestMapping({"readAllMsg"})	
   public String readAllMsg(String id) {	
      String a = UserUtils.getUser().getUserCode();	
      MsgPushUtils.readMsgByBiz((String)null, (String)null, a);	
      return this.renderResult("true", E.ALLATORIxDEMO("儛邌桴诔嶁讟扣勻ｲ"));	
   }	
	
   @RequiresPermissions({"user"})	
   @RequestMapping({"list"})	
   public String list(MsgPush msgPush, boolean pushed, Model model) {	
      model.addAttribute("pushed", pushed);	
      model.addAttribute(E.ALLATORIxDEMO("\u001e\u0017\u00144\u0006\u0017\u001b"), msgPush);	
      return "modules/msg/msgList";	
   }	
	
   @RequiresPermissions({"user"})	
   @RequestMapping({"readMsg"})	
   public String readMsg(MsgPush msgPush, boolean pushed, Model model) {	
      if (StringUtils.isBlank(msgPush.getId())) {	
         return this.list(msgPush, pushed, model);	
      } else {	
         MsgPush a;	
         if ((a = this.msgPushService.get(msgPush)) != null && !pushed) {	
            a.setReadDate(new Date());	
            a.setReadStatus("1");	
            this.msgPushService.updateMsgPush(a);	
         }	
	
         if (a == null) {	
            MsgPush msgPush = new MsgPushed(msgPush);	
            a = this.msgPushService.get((MsgPush)msgPush);	
         }	
	
         model.addAttribute(E.ALLATORIxDEMO("\u0014\u0006\u0017\u001b\u0001\u0017"), pushed);	
         model.addAttribute("msgPush", a);	
         return E.ALLATORIxDEMO("\u001e\u000b\u0017\u0011\u001f\u0001\u0000K\u001e\u0017\u0014K\u001e\u0017\u0014\"\u001c\u0016\u001e");	
      }	
   }	
}	
