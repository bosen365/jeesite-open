/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.DateUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  javax.validation.ValidationException	
 *  org.apache.shiro.authz.annotation.RequiresPermissions	
 *  org.quartz.CronExpression	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.stereotype.Controller	
 *  org.springframework.ui.Model	
 *  org.springframework.validation.annotation.Validated	
 *  org.springframework.web.bind.annotation.ModelAttribute	
 *  org.springframework.web.bind.annotation.PostMapping	
 *  org.springframework.web.bind.annotation.RequestMapping	
 *  org.springframework.web.bind.annotation.RequestParam	
 *  org.springframework.web.bind.annotation.ResponseBody	
 */	
package com.jeesite.modules.job.web;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.common.web.e.F;	
import com.jeesite.modules.job.entity.JobEntity;	
import com.jeesite.modules.job.service.JobService;	
import java.text.ParseException;	
import java.util.Date;	
import java.util.List;	
import javax.validation.ValidationException;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.Swap;	
import org.hyperic.sigar.pager.PageControl;	
import org.quartz.CronExpression;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.validation.annotation.Validated;	
import org.springframework.web.bind.annotation.ModelAttribute;	
import org.springframework.web.bind.annotation.PostMapping;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.RequestParam;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping(value={"${adminPath}/job"})	
@ConditionalOnProperty(name={"job.enabled", "web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class JobController	
extends BaseController {	
    @Autowired	
    private JobService jobService;	
	
    /*	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"checkCron"})	
    @ResponseBody	
    public String checkCron(String cron, @RequestParam(defaultValue="10") Integer num) {	
        StringBuilder a2 = new StringBuilder();	
        try {	
            void a3;	
            CronExpression a4 = new CronExpression(cron);	
            if (num <= 0) {	
                a2.append("Cron表达式正确。");	
                return this.renderResult("true", a2.toString());	
            }	
            Date a5 = new Date();	
            boolean bl = false;	
            a2.append("<>最近" + num + "次运行时间：</p>");	
            void v0 = a3;	
            while (v0 <= num) {	
                if ((a5 = a4.getTimeAfter(a5)) == null) {	
                    return this.renderResult("true", a2.toString());	
                }	
                a2.append(new StringBuilder().insert(0, DateUtils.formatDateTime((Date)a5)).append("</br>").toString());	
                v0 = ++a3;	
            }	
            return this.renderResult("true", a2.toString());	
        }	
        catch (ParseException a6) {	
            return this.renderResult("false", new StringBuilder().insert(0, "Cron表达式错误：").append(a6.getMessage()).toString());	
        }	
    }	
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @RequestMapping(value={"runOnce"})	
    @ResponseBody	
    public String runOnce(JobEntity job) {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        this.jobService.runOnce(job);	
        return this.renderResult("true", "运行一次成功！");	
    }	
	
    @RequiresPermissions(value={"sys:job:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public List<JobEntity> listData(JobEntity job, String orderBy) {	
        if (StringUtils.isBlank((CharSequence)orderBy)) {	
            orderBy = "jobName";	
        }	
        List a2 = this.jobService.findList(job);	
        a2 = ListUtils.listOrderBy(a2, (String)orderBy);	
        return a2;	
    }	
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @RequestMapping(value={"resume"})	
    @ResponseBody	
    public String resume(JobEntity job) {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        this.jobService.resume(job);	
        return this.renderResult("true", "恢复运行成功！");	
    }	
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @PostMapping(value={"save"})	
    @ResponseBody	
    public String save(@Validated JobEntity job) {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        this.jobService.save(job);	
        return this.renderResult("true", "保存成功！");	
    }	
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @RequestMapping(value={"delete"})	
    @ResponseBody	
    public String delete(JobEntity job) {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        this.jobService.delete(job);	
        return this.renderResult("true", "删除成功！");	
    }	
	
    @ModelAttribute(value="job")	
    public JobEntity get(String jobName, String jobGroup, boolean isNewRecord) {	
        JobEntity a2 = null;	
        if (StringUtils.isNoneBlank((CharSequence[])new CharSequence[]{jobName, jobGroup})) {	
            a2 = this.jobService.get(new JobEntity(jobName, jobGroup));	
            if (isNewRecord && a2 != null) {	
                throw new ValidationException("名称及组名已经存在");	
            }	
        }	
        if (a2 == null) {	
            a2 = new JobEntity();	
        }	
        return a2;	
    }	
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @RequestMapping(value={"startAll"})	
    @ResponseBody	
    public String startAll() {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        if (!this.jobService.startAll()) {	
            return this.renderResult("false", "启动定时器失败！");	
        }	
        return this.renderResult("true", "启动定时器成功！");	
    }	
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @RequestMapping(value={"pause"})	
    @ResponseBody	
    public String pause(JobEntity job) {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        this.jobService.pause(job);	
        return this.renderResult("true", "暂停运行成功！");	
    }	
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @RequestMapping(value={"stopAll"})	
    @ResponseBody	
    public String stopAll() {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        if (!this.jobService.stopAll()) {	
            return this.renderResult("false", "停止定时器失败！");	
        }	
        return this.renderResult("true", "停止定时器成功！");	
    }	
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @RequestMapping(value={"form"})	
    public String form(@ModelAttribute(value="job") JobEntity job, Model model) {	
        if (job.getMisfireInstruction() == null) {	
            job.setMisfireInstruction(2);	
        }	
        if (StringUtils.isBlank((CharSequence)job.getConcurrent())) {	
            job.setConcurrent("0");	
        }	
        model.addAttribute("job", (Object)job);	
        return "modules/job/jobForm";	
    }	
	
    @RequiresPermissions(value={"sys:job:view"})	
    @RequestMapping(value={"list"})	
    public String list(JobEntity job, Model model) {	
        void a2;	
        Boolean bl = this.jobService.isRunning();	
        model.addAttribute("isRunning", (Object)a2);	
        return "modules/job/jobList";	
    }	
}	
	
