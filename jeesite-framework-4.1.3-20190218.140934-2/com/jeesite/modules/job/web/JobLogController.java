package com.jeesite.modules.job.web;	
	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.job.entity.JobLog;	
import com.jeesite.modules.job.service.JobLogService;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.Who;	
import org.hyperic.sigar.cmd.Tail;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.validation.annotation.Validated;	
import org.springframework.web.bind.annotation.ModelAttribute;	
import org.springframework.web.bind.annotation.PostMapping;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping({"${adminPath}/job/jobLog"})	
@ConditionalOnProperty(	
   name = {"job.enabled", "web.core.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class JobLogController extends BaseController {	
   @Autowired	
   private JobLogService jobLogService;	
	
   @RequiresPermissions({"sys:job:view"})	
   @RequestMapping({"list", ""})	
   public String list(JobLog jobLog, Model model) {	
      model.addAttribute("jobLog", jobLog);	
      return "modules/job/jobLogList";	
   }	
	
   @RequiresPermissions({"sys:job:view"})	
   @RequestMapping({"listData"})	
   @ResponseBody	
   public Page listData(JobLog jobLog, HttpServletRequest request, HttpServletResponse response) {	
      jobLog.setPage(new Page(request, response));	
      return this.jobLogService.findPage(jobLog);	
   }	
	
   @RequiresPermissions({"sys:job:view"})	
   @RequestMapping({"form"})	
   public String form(JobLog jobLog, Model model) {	
      model.addAttribute("jobLog", jobLog);	
      return "modules/job/jobLogForm";	
   }	
	
   @RequiresPermissions({"sys:job:edit"})	
   @PostMapping({"save"})	
   @ResponseBody	
   public String save(@Validated JobLog jobLog) {	
      this.jobLogService.save(jobLog);	
      return this.renderResult("true", "保存调度日志成功！");	
   }	
	
   @ModelAttribute	
   public JobLog get(String id, boolean isNewRecord) {	
      return (JobLog)this.jobLogService.get(id, isNewRecord);	
   }	
}	
