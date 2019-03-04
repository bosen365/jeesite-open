package com.jeesite.modules.job.web;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.l.C;	
import com.jeesite.common.l.d.j;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.job.entity.JobEntity;	
import com.jeesite.modules.job.service.JobService;	
import java.text.ParseException;	
import java.util.Date;	
import java.util.List;	
import javax.validation.ValidationException;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.quartz.CronExpression;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.validation.annotation.Validated;	
import org.springframework.web.bind.annotation.ModelAttribute;	
import org.springframework.web.bind.annotation.PostMapping;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.RequestParam;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping({"${adminPath}/job"})	
public class JobController extends BaseController {	
   @Autowired	
   private JobService jobService;	
	
   @RequiresPermissions({"user"})	
   @RequestMapping({"checkCron"})	
   @ResponseBody	
   public String checkCron(String cron, @RequestParam(defaultValue = "10") Integer num) {	
      StringBuilder a = new StringBuilder();	
	
      try {	
         CronExpression a = new CronExpression(cron);	
         if (num > 0) {	
            a.append("<p>最近" + num + "次运行时间：</p>");	
            Date a = new Date();	
            int a = 0;	
	
            for(int var10000 = a; var10000 <= num && (a = a.getTimeAfter(a)) != null; var10000 = a) {	
               StringBuilder var10001 = (new StringBuilder()).insert(0, DateUtils.formatDateTime(a));	
               String var10002 = "</br>";	
               ++a;	
               a.append(var10001.append(var10002).toString());	
            }	
         } else {	
            a.append("Cron表达式正确。");	
         }	
      } catch (ParseException var7) {	
         return this.renderResult("false", (new StringBuilder()).insert(0, "Cron表达式错误：").append(var7.getMessage()).toString());	
      }	
	
      return this.renderResult("true", a.toString());	
   }	
	
   @RequiresPermissions({"sys:job:edit"})	
   @RequestMapping({"stopAll"})	
   @ResponseBody	
   public String stopAll() {	
      if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob"))) {	
         return this.renderResult("false", "当前版本未开放此功能！");	
      } else {	
         return !this.jobService.stopAll() ? this.renderResult("false", "停止定时器失败！") : this.renderResult("true", "停止定时器成功！");	
      }	
   }	
	
   @RequiresPermissions({"sys:job:edit"})	
   @PostMapping({"save"})	
   @ResponseBody	
   public String save(@Validated JobEntity job) {	
      if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob"))) {	
         return this.renderResult("false", "当前版本未开放此功能！");	
      } else {	
         this.jobService.save(job);	
         return this.renderResult("true", "保存成功！");	
      }	
   }	
	
   @RequiresPermissions({"sys:job:edit"})	
   @RequestMapping({"form"})	
   public String form(JobEntity job, Model model) {	
      model.addAttribute("jobEntity", job);	
      return "modules/job/jobForm";	
   }	
	
   @RequiresPermissions({"sys:job:edit"})	
   @RequestMapping({"pause"})	
   @ResponseBody	
   public String pause(JobEntity job) {	
      this.jobService.pause(job);	
      return this.renderResult("true", "暂停运行成功！");	
   }	
	
   @RequiresPermissions({"sys:job:edit"})	
   @RequestMapping({"runOnce"})	
   @ResponseBody	
   public String runOnce(JobEntity job) {	
      this.jobService.runOnce(job);	
      return this.renderResult("true", "运行一次成功！");	
   }	
	
   @RequiresPermissions({"sys:job:edit"})	
   @RequestMapping({"resume"})	
   @ResponseBody	
   public String resume(JobEntity job) {	
      this.jobService.resume(job);	
      return this.renderResult("true", "恢复运行成功！");	
   }	
	
   @RequiresPermissions({"sys:job:edit"})	
   @RequestMapping({"startAll"})	
   @ResponseBody	
   public String startAll() {	
      if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob"))) {	
         return this.renderResult("false", "当前版本未开放此功能！");	
      } else {	
         return !this.jobService.startAll() ? this.renderResult("false", "启动定时器失败！") : this.renderResult("true", "启动定时器成功！");	
      }	
   }	
	
   @ModelAttribute("job")	
   public JobEntity get(String jobName, String jobGroup, boolean isNewRecord) {	
      JobEntity a = null;	
      CharSequence[] var10000 = new CharSequence[2];	
      boolean var10002 = true;	
      var10000[0] = jobName;	
      var10000[1] = jobGroup;	
      if (StringUtils.isNoneBlank(var10000)) {	
         a = this.jobService.get(new JobEntity(jobName, jobGroup));	
         if (isNewRecord && a != null) {	
            throw new ValidationException("名称及组名已经存在");	
         }	
      }	
	
      if (a == null) {	
         a = new JobEntity();	
      }	
	
      return a;	
   }	
	
   @RequiresPermissions({"sys:job:view"})	
   @RequestMapping({"list"})	
   public String list(JobEntity job, Model model) {	
      Boolean a = this.jobService.isRunning();	
      model.addAttribute("isRunning", a);	
      return "modules/job/jobList";	
   }	
	
   @RequiresPermissions({"sys:job:edit"})	
   @RequestMapping({"delete"})	
   @ResponseBody	
   public String delete(JobEntity job) {	
      this.jobService.delete(job);	
      return this.renderResult("true", "删除成功！");	
   }	
	
   @RequiresPermissions({"sys:job:view"})	
   @RequestMapping({"listData"})	
   @ResponseBody	
   public List listData(JobEntity job, String orderBy) {	
      return ListUtils.listOrderBy(this.jobService.findList(job), orderBy);	
   }	
}	
