/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.apache.commons.logging.Log	
 *  org.apache.commons.logging.LogFactory	
 *  org.quartz.DisallowConcurrentExecution	
 *  org.quartz.JobDataMap	
 *  org.quartz.JobDetail	
 *  org.quartz.JobExecutionContext	
 *  org.quartz.JobExecutionException	
 *  org.quartz.PersistJobDataAfterExecution	
 *  org.quartz.impl.JobDetailImpl	
 *  org.springframework.scheduling.quartz.JobMethodInvocationFailedException	
 *  org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean	
 *  org.springframework.scheduling.quartz.QuartzJobBean	
 *  org.springframework.util.MethodInvoker	
 */	
package com.jeesite.modules.job.entity;	
	
import com.jeesite.common.j2cache.cache.support.utils.J2CacheConfigUtils;	
import com.jeesite.modules.job.d.i;	
import java.lang.reflect.InvocationTargetException;	
import org.apache.commons.logging.Log;	
import org.apache.commons.logging.LogFactory;	
import org.quartz.DisallowConcurrentExecution;	
import org.quartz.JobDataMap;	
import org.quartz.JobDetail;	
import org.quartz.JobExecutionContext;	
import org.quartz.JobExecutionException;	
import org.quartz.PersistJobDataAfterExecution;	
import org.quartz.impl.JobDetailImpl;	
import org.springframework.scheduling.quartz.JobMethodInvocationFailedException;	
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;	
import org.springframework.scheduling.quartz.QuartzJobBean;	
import org.springframework.util.MethodInvoker;	
	
public class MethodInvokingJobDetail	
extends MethodInvokingJobDetailFactoryBean {	
    private boolean concurrent = true;	
    private String invokeTarget;	
	
    public void setInvokeTarget(String invokeTarget) {	
        this.invokeTarget = invokeTarget;	
    }	
	
    public void setConcurrent(boolean concurrent) {	
        this.concurrent = concurrent;	
        super.setConcurrent(concurrent);	
    }	
	
    public void afterPropertiesSet() throws ClassNotFoundException, NoSuchMethodException {	
        MethodInvokingJobDetail methodInvokingJobDetail = this;	
        super.afterPropertiesSet();	
        JobDetailImpl a = (JobDetailImpl)methodInvokingJobDetail.getObject();	
        a.setJobClass(this.concurrent ? MethodInvokingJob.class : StatefulMethodInvokingJob.class);	
        a.getJobDataMap().put("methodInvoker", this.invokeTarget);	
    }	
	
    @PersistJobDataAfterExecution	
    @DisallowConcurrentExecution	
    public static class StatefulMethodInvokingJob	
    extends MethodInvokingJob {	
    }	
	
    public static class MethodInvokingJob	
    extends QuartzJobBean {	
        private MethodInvokingJobDetail methodInvoker;	
        protected static final Log logger = LogFactory.getLog(MethodInvokingJob.class);	
	
        public void setMethodInvoker(String methodInvoker) {	
            this.methodInvoker = i.ALLATORIxDEMO(methodInvoker);	
        }	
	
        protected void executeInternal(JobExecutionContext context) throws JobExecutionException {	
            try {	
                this.methodInvoker.prepare();	
                context.setResult(this.methodInvoker.invoke());	
                return;	
            }	
            catch (InvocationTargetException a) {	
                if (a.getTargetException() instanceof JobExecutionException) {	
                    throw (JobExecutionException)a.getTargetException();	
                }	
                throw new JobMethodInvocationFailedException((MethodInvoker)this.methodInvoker, a.getTargetException());	
            }	
            catch (Exception a) {	
                throw new JobMethodInvocationFailedException((MethodInvoker)this.methodInvoker, (Throwable)a);	
            }	
        }	
    }	
	
}	
	
