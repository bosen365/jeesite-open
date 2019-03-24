/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.job.entity;	
	
import com.jeesite.modules.job.l.m;	
import java.lang.reflect.InvocationTargetException;	
import org.apache.commons.logging.Log;	
import org.apache.commons.logging.LogFactory;	
import org.hyperic.sigar.DirUsage;	
import org.quartz.DisallowConcurrentExecution;	
import org.quartz.Job;	
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
	
    @Override	
    public void setConcurrent(boolean concurrent) {	
        this.concurrent = concurrent;	
        super.setConcurrent(concurrent);	
    }	
	
    public void setInvokeTarget(String invokeTarget) {	
        this.invokeTarget = invokeTarget;	
    }	
	
    @Override	
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
        protected static final Log logger = LogFactory.getLog(MethodInvokingJob.class);	
        private MethodInvokingJobDetail methodInvoker;	
	
        public void setMethodInvoker(String methodInvoker) {	
            this.methodInvoker = m.ALLATORIxDEMO(methodInvoker);	
        }	
	
        @Override	
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
                throw new JobMethodInvocationFailedException(this.methodInvoker, a.getTargetException());	
            }	
            catch (Exception a) {	
                throw new JobMethodInvocationFailedException(this.methodInvoker, a);	
            }	
        }	
    }	
	
}	
	
