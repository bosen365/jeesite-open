/*	
 * Decompiled with CFR 0.139.	
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
	
import com.jeesite.common.j.E;	
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
    private String invokeTarget;	
    private boolean concurrent = true;	
	
    public void afterPropertiesSet() throws ClassNotFoundException, NoSuchMethodException {	
        MethodInvokingJobDetail methodInvokingJobDetail = this;	
        super.afterPropertiesSet();	
        JobDetailImpl a2 = (JobDetailImpl)methodInvokingJobDetail.getObject();	
        a2.setJobClass(this.concurrent ? MethodInvokingJob.class : StatefulMethodInvokingJob.class);	
        a2.getJobDataMap().put("methodInvoker", this.invokeTarget);	
    }	
	
    public void setConcurrent(boolean concurrent) {	
        this.concurrent = concurrent;	
        super.setConcurrent(concurrent);	
    }	
	
    public void setInvokeTarget(String invokeTarget) {	
        this.invokeTarget = invokeTarget;	
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
            this.methodInvoker = com.jeesite.modules.job.j.E.ALLATORIxDEMO(methodInvoker);	
        }	
	
        protected void executeInternal(JobExecutionContext context) throws JobExecutionException {	
            try {	
                this.methodInvoker.prepare();	
                context.setResult(this.methodInvoker.invoke());	
                return;	
            }	
            catch (InvocationTargetException a2) {	
                if (a2.getTargetException() instanceof JobExecutionException) {	
                    throw (JobExecutionException)a2.getTargetException();	
                }	
                throw new JobMethodInvocationFailedException((MethodInvoker)this.methodInvoker, a2.getTargetException());	
            }	
            catch (Exception a3) {	
                throw new JobMethodInvocationFailedException((MethodInvoker)this.methodInvoker, (Throwable)a3);	
            }	
        }	
    }	
	
}	
	
