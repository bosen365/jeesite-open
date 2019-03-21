/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.codec.EncodeUtils	
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
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.j2cache.cache.support.utils.J2CacheConfigUtils;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.common.web.v.m;	
import com.jeesite.modules.job.entity.JobEntity;	
import com.jeesite.modules.job.service.JobService;	
import java.text.ParseException;	
import java.util.Date;	
import java.util.List;	
import javax.validation.ValidationException;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.SudoFileInputStream;	
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
        StringBuilder a = new StringBuilder();	
        try {	
            void a2;	
            CronExpression a3 = new CronExpression(cron);	
            if (num <= 0) {	
                a.append("Cron表达式正确。");	
                return this.renderResult("true", a.toString());	
            }	
            Date a4 = new Date();	
            boolean bl = false;	
            a.append("Vp>最近" + num + "次运行时间：</p>");	
            void v0 = a2;	
            while (v0 <= num) {	
                if ((a4 = a3.getTimeAfter(a4)) == null) {	
                    return this.renderResult("true", a.toString());	
                }	
                a.append(new StringBuilder().insert(0, DateUtils.formatDateTime((Date)a4)).append("V/br>").toString());	
                v0 = ++a2;	
            }	
            return this.renderResult("true", a.toString());	
        }	
        catch (ParseException a5) {	
            return this.renderResult("false", new StringBuilder().insert(0, "Cron表达式错误：").append(a5.getMessage()).toString());	
        }	
    }	
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @RequestMapping(value={"delete"})	
    @ResponseBody	
    public String delete(JobEntity job) {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        this.jobService.delete(job);	
        return this.renderResult("true", "删除成功！");	
    }	
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @RequestMapping(value={"runOnce"})	
    @ResponseBody	
    public String runOnce(JobEntity job) {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        this.jobService.runOnce(job);	
        return this.renderResult("true", "运行一次成功！");	
    }	
	
    @RequiresPermissions(value={"sys:job:view"})	
    @RequestMapping(value={"list"})	
    public String list(JobEntity job, Model model) {	
        void a;	
        Boolean bl = this.jobService.isRunning();	
        model.addAttribute("isRunning", (Object)a);	
        return "modules/job/jobList";	
    }	
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @RequestMapping(value={"stopAll"})	
    @ResponseBody	
    public String stopAll() {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        if (!this.jobService.stopAll()) {	
            return this.renderResult("false", "停止定时器失败！");	
        }	
        return this.renderResult("true", "停止定时器成功！");	
    }	
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @RequestMapping(value={"pause"})	
    @ResponseBody	
    public String pause(JobEntity job) {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        this.jobService.pause(job);	
        return this.renderResult("true", "暂停运行成功！");	
    }	
	
    @RequiresPermissions(value={"sys:job:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public List<JobEntity> listData(JobEntity job, String orderBy) {	
        if (StringUtils.isBlank((CharSequence)orderBy)) {	
            orderBy = "jobName";	
        }	
        List a = this.jobService.findList(job);	
        a = ListUtils.listOrderBy(a, (String)orderBy);	
        return a;	
    }	
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @PostMapping(value={"save"})	
    @ResponseBody	
    public String save(@Validated JobEntity job) {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        JobEntity jobEntity = job;	
        jobEntity.setInvokeTarget(EncodeUtils.decodeBase64String((String)jobEntity.getInvokeTarget()));	
        JobController jobController = this;	
        jobController.jobService.save(job);	
        return jobController.renderResult("true", "保存成功！");	
    }	
	
    @ModelAttribute(value="job")	
    public JobEntity get(String jobName, String jobGroup, boolean isNewRecord) {	
        JobEntity a = null;	
        if (StringUtils.isNoneBlank((CharSequence[])new CharSequence[]{jobName, jobGroup})) {	
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
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @RequestMapping(value={"startAll"})	
    @ResponseBody	
    public String startAll() {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        if (!this.jobService.startAll()) {	
            return this.renderResult("false", "启动定时器失败！");	
        }	
        return this.renderResult("true", "启动定时器成功！");	
    }	
	
    @RequiresPermissions(value={"sys:job:edit"})	
    @RequestMapping(value={"resume"})	
    @ResponseBody	
    public String resume(JobEntity job) {	
        if (!ObjectUtils.toBoolean(m.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return this.renderResult("false", "当前版本未开放此功能！");	
        }	
        this.jobService.resume(job);	
        return this.renderResult("true", "恢复运行成功！");	
    }	
}	
	
