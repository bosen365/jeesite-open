/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.job.service;	
	
import com.atomikos.jdbc.AtomikosDataSourceBean;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.datasource.RoutingDataSource;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.CrudService;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.web.l.j;	
import com.jeesite.modules.job.dao.JobDao;	
import com.jeesite.modules.job.entity.JobEntity;	
import com.jeesite.modules.job.listener.JobDetailListener;	
import com.jeesite.modules.job.listener.JobSchedulerListener;	
import com.jeesite.modules.job.listener.JobTriggerListener;	
import com.jeesite.modules.job.service.JobLogService;	
import com.jeesite.modules.job.service.m;	
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
import org.hyperic.sigar.DirUsage;	
import org.hyperic.sigar.ProcCred;	
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
    private SchedulerFactoryBean scheduler;	
    @Autowired	
    private DataSource dataSource;	
    @Autowired	
    private JobLogService jobLogService;	
	
    @PostConstruct	
    private /* synthetic */ void initialize() throws Exception {	
        Object a;	
        JobService jobService;	
        JobService jobService2;	
        if (Global.isTestProfileActive()) {	
            return;	
        }	
        if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return;	
        }	
        this.scheduler = new SchedulerFactoryBean();	
        JobService jobService3 = this;	
        if (!"9".equals(j.ALLATORIxDEMO().get("type"))) {	
            jobService3.scheduler.setAutoStartup(Global.getPropertyToBoolean("job.autoStartup", "true"));	
            jobService2 = this;	
        } else {	
            jobService3.scheduler.setAutoStartup(false);	
            jobService2 = this;	
        }	
        jobService2.scheduler.setStartupDelay(Global.getPropertyToInteger("job.startupDelay", "60"));	
        this.scheduler.setQuartzProperties(com.jeesite.modules.job.l.m.ALLATORIxDEMO());	
        String a2 = Global.getProperty("job.dataSourceName", "job");	
        JobService jobService4 = this;	
        if (StringUtils.isNotBlank(Global.getProperty(new StringBuilder().insert(0, "jdbc.").append(a2).append(".type").toString()))) {	
            jobService4.scheduler.setDataSource(RoutingDataSource.createDataSource(a2, false));	
            jobService = this;	
        } else {	
            a = jobService4.dataSource;	
            if (a instanceof RoutingDataSource) {	
                a = ((RoutingDataSource)a).getTargetDataSource("default");	
            }	
            if (a instanceof XADataSource || a instanceof AtomikosDataSourceBean) {	
                JobService jobService5 = this;	
                jobService = jobService5;	
                jobService5.scheduler.setDataSource(RoutingDataSource.createDataSource(a2, false));	
            } else {	
                JobService jobService6 = this;	
                jobService = jobService6;	
                jobService6.scheduler.setDataSource((DataSource)a);	
            }	
        }	
        jobService.scheduler.afterPropertiesSet();	
        if (!"9".equals(j.ALLATORIxDEMO().get("type")) && this.scheduler.isAutoStartup()) {	
            this.scheduler.start();	
        }	
        Object object = a = new JobEntity();	
        ((DataEntity)object).setStatus("");	
        ((JobEntity)object).setInstanceName(Global.getProperty("job.scheduler.instanceName", "JeeSiteScheduler"));	
        for (JobEntity a3 : super.findList(object)) {	
            if ("1".equals(((DataEntity)a).getStatus())) {	
                this.delete(a3);	
                continue;	
            }	
            try {	
                this.save(a3, false);	
            }	
            catch (Exception a4) {	
                if (a4.getMessage().contains("目标字符串")) continue;	
                this.logger.warn(a4.getMessage(), a4);	
            }	
        }	
        if (!"9".equals(j.ALLATORIxDEMO().get("type"))) {	
            try {	
                ListenerManager a5 = this.getScheduler().getListenerManager();	
                if (Global.getPropertyToBoolean("job.log.scheduler.enabled", "true").booleanValue()) {	
                    a5.addSchedulerListener(new JobSchedulerListener(this.jobLogService));	
                }	
                if (Global.getPropertyToBoolean("job.log.jobDetail.enabled", "true").booleanValue()) {	
                    a5.addJobListener(new JobDetailListener(this.jobLogService));	
                }	
                if (Global.getPropertyToBoolean("job.log.trigger.enabled", "true").booleanValue()) {	
                    a5.addTriggerListener(new JobTriggerListener(this.jobLogService));	
                    return;	
                }	
            }	
            catch (SchedulerException a6) {	
                this.logger.error("作业监听事件异常！", a6);	
            }	
        }	
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
                if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
                    return;	
                }	
                v0 = job;	
                a = com.jeesite.modules.job.l.m.ALLATORIxDEMO(v0);	
                a = com.jeesite.modules.job.l.m.ALLATORIxDEMO(v0, a);	
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
                this.getScheduler().rescheduleJob(a.getKey(), a);	
            } else {	
                v3.getScheduler().scheduleJob(a);	
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
	
    @Override	
    public List<JobEntity> findList(JobEntity job) {	
        ArrayList<JobEntity> a = ListUtils.newArrayList();	
        if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return a;	
        }	
        try {	
            Scheduler a2 = this.getScheduler();	
            Iterator<String> iterator = a2.getTriggerGroupNames().iterator();	
            while (iterator.hasNext()) {	
                GroupMatcher<TriggerKey> a3 = GroupMatcher.groupEquals(iterator.next());	
                for (TriggerKey a4 : a2.getTriggerKeys(a3)) {	
                    Trigger a5 = a2.getTrigger(a4);	
                    if (!(a5 instanceof CronTriggerImpl)) continue;	
                    a.add(new JobEntity().convert(a2, (CronTriggerImpl)a5));	
                }	
            }	
        }	
        catch (Exception a6) {	
            this.logger.error("查询任务列表失败！", a6);	
        }	
        return a;	
    }	
	
    public boolean startAll() {	
        if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return false;	
        }	
        try {	
            JobService jobService = this;	
            jobService.getFactoryBean().setStartupDelay(0);	
            jobService.getFactoryBean().start();	
            if ("9".equals(j.ALLATORIxDEMO().get("type"))) {	
                new Thread(new m(this)).start();	
            }	
        }	
        catch (Exception a) {	
            this.logger.error("启动定时器失败！", a);	
            return false;	
        }	
        return true;	
    }	
	
    public boolean isRunning() {	
        block4 : {	
            if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
                return false;	
            }	
            try {	
                if (this.getScheduler().isInStandbyMode() || !this.getScheduler().isStarted()) break block4;	
                return true;	
            }	
            catch (Exception a) {	
                this.logger.error("获取定时器状态时失败！", a);	
                return false;	
            }	
        }	
        return false;	
    }	
	
    private /* synthetic */ Scheduler getScheduler() {	
        return this.getFactoryBean().getScheduler();	
    }	
	
    @PreDestroy	
    private /* synthetic */ void destroy() throws SchedulerException {	
        if (this.scheduler != null) {	
            this.scheduler.destroy();	
        }	
    }	
	
    public boolean stopAll() {	
        if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return false;	
        }	
        try {	
            this.getFactoryBean().stop();	
        }	
        catch (Exception a) {	
            this.logger.error("停止定时器失败！", a);	
            return false;	
        }	
        return true;	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(JobEntity job) {	
        this.save(job, true);	
    }	
	
    @Transactional(readOnly=false)	
    public void resume(JobEntity job) {	
        if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return;	
        }	
        job.setStatus("0");	
        super.updateStatus(job);	
        try {	
            TriggerKey a = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());	
            JobService jobService = this;	
            Trigger a2 = jobService.getScheduler().getTrigger(a);	
            jobService.getScheduler().resumeJob(a2.getJobKey());	
            jobService.getScheduler().resumeTrigger(a2.getKey());	
            return;	
        }	
        catch (SchedulerException a) {	
            throw new ServiceException("恢复运行失败！", a);	
        }	
    }	
	
    private /* synthetic */ SchedulerFactoryBean getFactoryBean() {	
        return this.scheduler;	
    }	
	
    @Transactional(readOnly=false)	
    public void pause(JobEntity job) {	
        if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return;	
        }	
        job.setStatus("2");	
        super.updateStatus(job);	
        try {	
            TriggerKey a = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());	
            JobService jobService = this;	
            Trigger a2 = jobService.getScheduler().getTrigger(a);	
            jobService.getScheduler().pauseJob(a2.getJobKey());	
            jobService.getScheduler().pauseTrigger(a2.getKey());	
            return;	
        }	
        catch (SchedulerException a) {	
            throw new ServiceException("暂停运行失败！", a);	
        }	
    }	
	
    static /* synthetic */ SchedulerFactoryBean access$000(JobService x0) {	
        return x0.getFactoryBean();	
    }	
	
    @Override	
    public JobEntity get(JobEntity job) {	
        if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return null;	
        }	
        JobEntity a = null;	
        try {	
            if (StringUtils.isNotBlank(job.getJobName()) && StringUtils.isNotBlank(job.getJobGroup())) {	
                TriggerKey a2 = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());	
                Trigger a3 = this.getScheduler().getTrigger(a2);	
                if (a3 instanceof CronTriggerImpl) {	
                    a = new JobEntity().convert(this.getScheduler(), (CronTriggerImpl)a3);	
                }	
            }	
        }	
        catch (SchedulerException a4) {	
            this.logger.error("获取失败！", a4);	
        }	
        return a;	
    }	
	
    @Transactional(readOnly=false)	
    public void runOnce(JobEntity job) {	
        if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return;	
        }	
        try {	
            TriggerKey a = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());	
            JobService jobService = this;	
            Trigger a2 = jobService.getScheduler().getTrigger(a);	
            jobService.getScheduler().triggerJob(a2.getJobKey());	
            return;	
        }	
        catch (SchedulerException a) {	
            throw new ServiceException("运行一次失败！", a);	
        }	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(JobEntity job) {	
        if (!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnJob")).booleanValue()) {	
            return;	
        }	
        super.delete(job);	
        try {	
            TriggerKey a = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());	
            Trigger a2 = this.getScheduler().getTrigger(a);	
            if (a2 != null) {	
                JobService jobService = this;	
                jobService.getScheduler().deleteJob(a2.getJobKey());	
                jobService.getScheduler().unscheduleJob(a2.getKey());	
                return;	
            }	
        }	
        catch (SchedulerException a) {	
            throw new ServiceException("删除失败！", a);	
        }	
    }	
}	
	
