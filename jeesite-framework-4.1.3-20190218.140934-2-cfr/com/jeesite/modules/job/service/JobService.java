/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.atomikos.jdbc.AtomikosDataSourceBean	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.quartz.CronTrigger	
 *  org.quartz.JobDetail	
 *  org.quartz.JobKey	
 *  org.quartz.JobListener	
 *  org.quartz.ListenerManager	
 *  org.quartz.Scheduler	
 *  org.quartz.SchedulerException	
 *  org.quartz.SchedulerListener	
 *  org.quartz.Trigger	
 *  org.quartz.TriggerKey	
 *  org.quartz.TriggerListener	
 *  org.quartz.impl.matchers.GroupMatcher	
 *  org.quartz.impl.triggers.CronTriggerImpl	
 *  org.slf4j.Logger	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.context.annotation.DependsOn	
 *  org.springframework.scheduling.quartz.SchedulerFactoryBean	
 *  org.springframework.stereotype.Service	
 *  org.springframework.transaction.annotation.Transactional	
 */	
package com.jeesite.modules.job.service;	
	
import com.atomikos.jdbc.AtomikosDataSourceBean;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.RoutingDataSource;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.web.j.F;	
import com.jeesite.modules.job.dao.JobDao;	
import com.jeesite.modules.job.entity.JobEntity;	
import com.jeesite.modules.job.listener.JobDetailListener;	
import com.jeesite.modules.job.listener.JobSchedulerListener;	
import com.jeesite.modules.job.listener.JobTriggerListener;	
import com.jeesite.modules.job.service.E;	
import com.jeesite.modules.job.service.JobLogService;	
import java.util.ArrayList;	
import java.util.Date;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Properties;	
import java.util.Set;	
import javax.annotation.PostConstruct;	
import javax.annotation.PreDestroy;	
import javax.sql.DataSource;	
import javax.sql.XADataSource;	
import org.hyperic.sigar.FileSystemUsage;	
import org.quartz.CronTrigger;	
import org.quartz.JobDetail;	
import org.quartz.JobKey;	
import org.quartz.JobListener;	
import org.quartz.ListenerManager;	
import org.quartz.Scheduler;	
import org.quartz.SchedulerException;	
import org.quartz.SchedulerListener;	
import org.quartz.Trigger;	
import org.quartz.TriggerKey;	
import org.quartz.TriggerListener;	
import org.quartz.impl.matchers.GroupMatcher;	
import org.quartz.impl.triggers.CronTriggerImpl;	
import org.slf4j.Logger;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.context.annotation.DependsOn;	
import org.springframework.scheduling.quartz.SchedulerFactoryBean;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@DependsOn(value={"dbUpgrade"})	
@Transactional(readOnly=true)	
@ConditionalOnProperty(name={"job.enabled"}, havingValue="true", matchIfMissing=true)	
public class JobService	
extends CrudService<JobDao, JobEntity> {	
    @Autowired	
    private JobLogService jobLogService;	
    private SchedulerFactoryBean scheduler;	
    @Autowired	
    private DataSource dataSource;	
	
    @Transactional(readOnly=false)	
    public void runOnce(JobEntity job) {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return;	
        }	
        try {	
            TriggerKey a2 = TriggerKey.triggerKey((String)job.getJobName(), (String)job.getJobGroup());	
            JobService jobService = this;	
            Trigger a3 = jobService.getScheduler().getTrigger(a2);	
            jobService.getScheduler().triggerJob(a3.getJobKey());	
            return;	
        }	
        catch (SchedulerException a4) {	
            throw new ServiceException("运行一次失败！", (Throwable)a4);	
        }	
    }	
	
    @Override	
    public JobEntity get(JobEntity job) {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return null;	
        }	
        JobEntity a2 = null;	
        try {	
            if (StringUtils.isNotBlank((CharSequence)job.getJobName()) && StringUtils.isNotBlank((CharSequence)job.getJobGroup())) {	
                TriggerKey a3 = TriggerKey.triggerKey((String)job.getJobName(), (String)job.getJobGroup());	
                Trigger a4 = this.getScheduler().getTrigger(a3);	
                if (a4 instanceof CronTriggerImpl) {	
                    a2 = new JobEntity().convert(this.getScheduler(), (CronTriggerImpl)a4);	
                }	
            }	
        }	
        catch (SchedulerException a5) {	
            this.logger.error("获取失败！", (Throwable)a5);	
        }	
        return a2;	
    }	
	
    static /* synthetic */ SchedulerFactoryBean access$000(JobService x0) {	
        return x0.getFactoryBean();	
    }	
	
    public boolean isRunning() {	
        block4 : {	
            if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
                return false;	
            }	
            try {	
                if (this.getScheduler().isInStandbyMode() || !this.getScheduler().isStarted()) break block4;	
                return true;	
            }	
            catch (Exception a2) {	
                this.logger.error("获取定时器状态时失败！", (Throwable)a2);	
                return false;	
            }	
        }	
        return false;	
    }	
	
    @Transactional(readOnly=false)	
    public void pause(JobEntity job) {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return;	
        }	
        job.setStatus("2");	
        super.updateStatus(job);	
        try {	
            TriggerKey a2 = TriggerKey.triggerKey((String)job.getJobName(), (String)job.getJobGroup());	
            JobService jobService = this;	
            Trigger a3 = jobService.getScheduler().getTrigger(a2);	
            jobService.getScheduler().pauseJob(a3.getJobKey());	
            jobService.getScheduler().pauseTrigger(a3.getKey());	
            return;	
        }	
        catch (SchedulerException a4) {	
            throw new ServiceException("暂停运行失败！", (Throwable)a4);	
        }	
    }	
	
    public boolean stopAll() {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return false;	
        }	
        try {	
            this.getFactoryBean().stop();	
        }	
        catch (Exception a2) {	
            this.logger.error("停止定时器失败！", (Throwable)a2);	
            return false;	
        }	
        return true;	
    }	
	
    private /* synthetic */ Scheduler getScheduler() {	
        return this.getFactoryBean().getScheduler();	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(JobEntity job) {	
        this.save(job, true);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(JobEntity job) {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return;	
        }	
        super.delete(job);	
        try {	
            TriggerKey a2 = TriggerKey.triggerKey((String)job.getJobName(), (String)job.getJobGroup());	
            Trigger a3 = this.getScheduler().getTrigger(a2);	
            if (a3 != null) {	
                JobService jobService = this;	
                jobService.getScheduler().deleteJob(a3.getJobKey());	
                jobService.getScheduler().unscheduleJob(a3.getKey());	
                return;	
            }	
        }	
        catch (SchedulerException a4) {	
            throw new ServiceException("删除失败！", (Throwable)a4);	
        }	
    }	
	
    public boolean startAll() {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return false;	
        }	
        try {	
            JobService jobService = this;	
            jobService.getFactoryBean().setStartupDelay(0);	
            jobService.getFactoryBean().start();	
            if ("9".equals(F.ALLATORIxDEMO().get("type"))) {	
                new Thread(new E(this)).start();	
            }	
        }	
        catch (Exception a2) {	
            this.logger.error("启动定时器失败！", (Throwable)a2);	
            return false;	
        }	
        return true;	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     * Lifted jumps to return sites	
     */	
    @Transactional(readOnly=false)	
    public void save(JobEntity job, boolean isSaveToDb) {	
        block5 : {	
            block6 : {	
                if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
                    return;	
                }	
                v0 = job;	
                a = com.jeesite.modules.job.j.E.ALLATORIxDEMO(v0);	
                a = com.jeesite.modules.job.j.E.ALLATORIxDEMO(v0, a);	
                if (!isSaveToDb) break block5;	
                if (!job.getIsNewRecord()) break block6;	
                v1 = this;	
                v2 = v1;	
                super.insert(job);	
                ** GOTO lbl17	
            }	
            super.update(job);	
        }	
        try {	
            v2 = this;	
lbl17: // 2 sources:	
            v2.getScheduler().addJob(a, true);	
            v3 = this;	
            if (this.getScheduler().checkExists(a.getKey())) {	
                v3.getScheduler().pauseTrigger(a.getKey());	
                this.getScheduler().rescheduleJob(a.getKey(), (Trigger)a);	
            } else {	
                v3.getScheduler().scheduleJob((Trigger)a);	
            }	
            if ("2".equals(job.getStatus()) == false) return;	
            v4 = this;	
            v4.getScheduler().pauseJob(a.getJobKey());	
            v4.getScheduler().pauseTrigger(a.getKey());	
            return;	
        }	
        catch (Exception a) {	
            throw new ServiceException("添加计划任务失败！", a);	
        }	
    }	
	
    private /* synthetic */ SchedulerFactoryBean getFactoryBean() {	
        return this.scheduler;	
    }	
	
    @Override	
    public List<JobEntity> findList(JobEntity job) {	
        ArrayList a2 = ListUtils.newArrayList();	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return a2;	
        }	
        try {	
            Scheduler a3 = this.getScheduler();	
            Iterator iterator = a3.getTriggerGroupNames().iterator();	
            while (iterator.hasNext()) {	
                GroupMatcher a4 = GroupMatcher.groupEquals((String)((String)iterator.next()));	
                for (TriggerKey a5 : a3.getTriggerKeys(a4)) {	
                    Trigger a6 = a3.getTrigger(a5);	
                    if (!(a6 instanceof CronTriggerImpl)) continue;	
                    a2.add(new JobEntity().convert(a3, (CronTriggerImpl)a6));	
                }	
            }	
        }	
        catch (Exception a7) {	
            this.logger.error("查询任务列表失败！", (Throwable)a7);	
        }	
        return a2;	
    }	
	
    @Transactional(readOnly=false)	
    public void resume(JobEntity job) {	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return;	
        }	
        job.setStatus("0");	
        super.updateStatus(job);	
        try {	
            TriggerKey a2 = TriggerKey.triggerKey((String)job.getJobName(), (String)job.getJobGroup());	
            JobService jobService = this;	
            Trigger a3 = jobService.getScheduler().getTrigger(a2);	
            jobService.getScheduler().resumeJob(a3.getJobKey());	
            jobService.getScheduler().resumeTrigger(a3.getKey());	
            return;	
        }	
        catch (SchedulerException a4) {	
            throw new ServiceException("恢复运行失败！", (Throwable)a4);	
        }	
    }	
	
    @PreDestroy	
    private /* synthetic */ void destroy() throws SchedulerException {	
        if (this.scheduler != null) {	
            this.scheduler.destroy();	
        }	
    }	
	
    @PostConstruct	
    private /* synthetic */ void initialize() throws Exception {	
        JobEntity a2;	
        JobService jobService;	
        JobService jobService2;	
        if (Global.isTestProfileActive()) {	
            return;	
        }	
        if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return;	
        }	
        this.scheduler = new SchedulerFactoryBean();	
        JobService jobService3 = this;	
        if (!"9".equals(F.ALLATORIxDEMO().get("type"))) {	
            jobService3.scheduler.setAutoStartup(Global.getPropertyToBoolean("job.autoStartup", "true").booleanValue());	
            jobService2 = this;	
        } else {	
            jobService3.scheduler.setAutoStartup(false);	
            jobService2 = this;	
        }	
        jobService2.scheduler.setStartupDelay(Global.getPropertyToInteger("job.startupDelay", "60").intValue());	
        this.scheduler.setQuartzProperties(com.jeesite.modules.job.j.E.ALLATORIxDEMO());	
        String a3 = Global.getProperty("job.dataSourceName", "job");	
        JobService jobService4 = this;	
        if (StringUtils.isNotBlank((CharSequence)Global.getProperty(new StringBuilder().insert(0, "jdbc.").append(a3).append(".type").toString()))) {	
            jobService4.scheduler.setDataSource(RoutingDataSource.createDataSource(a3, false));	
            jobService = this;	
        } else {	
            DataSource a22 = jobService4.dataSource;	
            if (a22 instanceof RoutingDataSource) {	
                a22 = ((RoutingDataSource)((Object)a22)).getTargetDataSource("default");	
            }	
            if (a22 instanceof XADataSource || a22 instanceof AtomikosDataSourceBean) {	
                JobService jobService5 = this;	
                jobService = jobService5;	
                jobService5.scheduler.setDataSource(RoutingDataSource.createDataSource(a3, false));	
            } else {	
                JobService jobService6 = this;	
                jobService = jobService6;	
                jobService6.scheduler.setDataSource(a22);	
            }	
        }	
        jobService.scheduler.afterPropertiesSet();	
        if (!"9".equals(F.ALLATORIxDEMO().get("type")) && this.scheduler.isAutoStartup()) {	
            this.scheduler.start();	
        }	
        JobEntity jobEntity = a2 = new JobEntity();	
        jobEntity.setStatus("");	
        for (JobEntity a4 : super.findList(jobEntity)) {	
            if ("1".equals(a2.getStatus())) {	
                this.delete(a4);	
                continue;	
            }	
            try {	
                this.save(a4, false);	
            }	
            catch (Exception a5) {	
                if (a5.getMessage().contains("目标字符串")) continue;	
                this.logger.warn(a5.getMessage(), (Throwable)a5);	
            }	
        }	
        if (!"9".equals(F.ALLATORIxDEMO().get("type"))) {	
            try {	
                ListenerManager a6 = this.getScheduler().getListenerManager();	
                if (Global.getPropertyToBoolean("job.log.scheduler.enabled", "true").booleanValue()) {	
                    a6.addSchedulerListener((SchedulerListener)new JobSchedulerListener(this.jobLogService));	
                }	
                if (Global.getPropertyToBoolean("job.log.jobDetail.enabled", "true").booleanValue()) {	
                    a6.addJobListener((JobListener)new JobDetailListener(this.jobLogService));	
                }	
                if (Global.getPropertyToBoolean("job.log.trigger.enabled", "true").booleanValue()) {	
                    a6.addTriggerListener((TriggerListener)new JobTriggerListener(this.jobLogService));	
                    return;	
                }	
            }	
            catch (SchedulerException a7) {	
                this.logger.error("作业监听事件异常！", (Throwable)a7);	
            }	
        }	
    }	
}	
	
