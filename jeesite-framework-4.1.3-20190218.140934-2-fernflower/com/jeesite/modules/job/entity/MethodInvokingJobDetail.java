package com.jeesite.modules.job.entity;	
	
import com.jeesite.common.j.E;	
import java.lang.reflect.InvocationTargetException;	
import org.apache.commons.logging.Log;	
import org.apache.commons.logging.LogFactory;	
import org.quartz.DisallowConcurrentExecution;	
import org.quartz.JobExecutionContext;	
import org.quartz.JobExecutionException;	
import org.quartz.PersistJobDataAfterExecution;	
import org.quartz.impl.JobDetailImpl;	
import org.springframework.scheduling.quartz.JobMethodInvocationFailedException;	
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;	
import org.springframework.scheduling.quartz.QuartzJobBean;	
	
public class MethodInvokingJobDetail extends MethodInvokingJobDetailFactoryBean {	
   private String invokeTarget;	
   private boolean concurrent = true;	
	
   public void afterPropertiesSet() throws ClassNotFoundException, NoSuchMethodException {	
      super.afterPropertiesSet();	
      JobDetailImpl a;	
      (a = (JobDetailImpl)this.getObject()).setJobClass(this.concurrent ? MethodInvokingJobDetail.MethodInvokingJob.class : MethodInvokingJobDetail.StatefulMethodInvokingJob.class);	
      a.getJobDataMap().put("methodInvoker", this.invokeTarget);	
   }	
	
   public void setConcurrent(boolean concurrent) {	
      super.setConcurrent(this.concurrent = concurrent);	
   }	
	
   public void setInvokeTarget(String invokeTarget) {	
      this.invokeTarget = invokeTarget;	
   }	
	
   @PersistJobDataAfterExecution	
   @DisallowConcurrentExecution	
   public static class StatefulMethodInvokingJob extends MethodInvokingJobDetail.MethodInvokingJob {	
   }	
	
   public static class MethodInvokingJob extends QuartzJobBean {	
      private MethodInvokingJobDetail methodInvoker;	
      protected static final Log logger = LogFactory.getLog(MethodInvokingJobDetail.MethodInvokingJob.class);	
	
      public void setMethodInvoker(String methodInvoker) {	
         this.methodInvoker = com.jeesite.modules.job.j.E.ALLATORIxDEMO(methodInvoker);	
      }	
	
      protected void executeInternal(JobExecutionContext context) throws JobExecutionException {	
         try {	
            this.methodInvoker.prepare();	
            context.setResult(this.methodInvoker.invoke());	
         } catch (InvocationTargetException var3) {	
            if (var3.getTargetException() instanceof JobExecutionException) {	
               throw (JobExecutionException)var3.getTargetException();	
            } else {	
               throw new JobMethodInvocationFailedException(this.methodInvoker, var3.getTargetException());	
            }	
         } catch (Exception var4) {	
            throw new JobMethodInvocationFailedException(this.methodInvoker, var4);	
         }	
      }	
   }	
}	
