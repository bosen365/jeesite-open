/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.state.web;	
	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.msg.entity.content.BaseMsgContent;	
import com.jeesite.modules.state.service.StateServerService;	
import java.util.LinkedHashMap;	
import java.util.List;	
import java.util.Map;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.NfsClientV3;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping(value={"${adminPath}/state/server"})	
@ConditionalOnProperty(name={"state.enabled"}, havingValue="true", matchIfMissing=true)	
public class StateServerController	
extends BaseController {	
    @Autowired	
    private StateServerService stateServerService;	
	
    @RequiresPermissions(value={"sys:state:server"})	
    @RequestMapping(value={"index"})	
    public String index(Model model) {	
        model.addAttribute("server", this.stateServerService.getServerInfo());	
        model.addAttribute("diskList", this.stateServerService.getDiskListInfo());	
        return "modules/state/serverIndex";	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    @RequiresPermissions(value={"sys:state:server"})	
    @RequestMapping(value={"rtInfo"})	
    @ResponseBody	
    public Map<String, Object> rtInfo(Model model) {	
        void a;	
        LinkedHashMap<String, Map<String, Object>> linkedHashMap = MapUtils.newLinkedHashMap();	
        void v0 = a;	
        linkedHashMap.put("mem", this.stateServerService.getMemInfo());	
        linkedHashMap.put("cpu", this.stateServerService.getCpuInfo());	
        v0.put("jvm", this.stateServerService.getJvmInfo());	
        return v0;	
    }	
	
    @RequiresPermissions(value={"sys:state:server"})	
    @RequestMapping(value={"gc"})	
    @ResponseBody	
    public String gc(Model model) {	
        return this.renderResult("true", "发起执行垃圾回收任务成功！");	
    }	
}	
	
