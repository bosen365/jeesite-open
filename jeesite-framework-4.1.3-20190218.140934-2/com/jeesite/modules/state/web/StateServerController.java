package com.jeesite.modules.state.web;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.state.service.StateServerService;	
import java.util.LinkedHashMap;	
import java.util.Map;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.cmd.Watch;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping({"${adminPath}/state/server"})	
@ConditionalOnProperty(	
   name = {"state.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class StateServerController extends BaseController {	
   @Autowired	
   private StateServerService stateServerService;	
	
   @RequiresPermissions({"sys:state:server"})	
   @RequestMapping({"rtInfo"})	
   @ResponseBody	
   public Map rtInfo(Model model) {	
      LinkedHashMap a;	
      (a = MapUtils.newLinkedHashMap()).put("cpu", this.stateServerService.getCpuInfo());	
      a.put("mem", this.stateServerService.getMemInfo());	
      a.put("jvm", this.stateServerService.getJvmInfo());	
      return a;	
   }	
	
   @RequiresPermissions({"sys:state:server"})	
   @RequestMapping({"index"})	
   public String index(Model model) {	
      model.addAttribute("server", this.stateServerService.getServerInfo());	
      model.addAttribute("diskList", this.stateServerService.getDiskListInfo());	
      return "modules/state/serverIndex";	
   }	
	
   @RequiresPermissions({"sys:state:server"})	
   @RequestMapping({"gc"})	
   @ResponseBody	
   public String gc(Model model) {	
      return this.renderResult("true", "发起执行垃圾回收任务成功！");	
   }	
}	
