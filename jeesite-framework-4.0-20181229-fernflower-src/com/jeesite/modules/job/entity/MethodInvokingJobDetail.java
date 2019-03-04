package com.jeesite.modules.job.entity;	
	
import com.jeesite.modules.job.l.l;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
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
	
   public void setInvokeTarget(String invokeTarget) {	
      this.invokeTarget = invokeTarget;	
   }	
	
   public void afterPropertiesSet() throws ClassNotFoundException, NoSuchMethodException {	
      super.afterPropertiesSet();	
      JobDetailImpl a;	
      (a = (JobDetailImpl)this.getObject()).setJobClass(this.concurrent ? MethodInvokingJobDetail.MethodInvokingJob.class : MethodInvokingJobDetail.StatefulMethodInvokingJob.class);	
      a.getJobDataMap().put("methdInvoker", this.invokeTarget);	
   }	
	
   public void setConcurrent(boolean concurrent) {	
      super.setConcurrent(this.concurrent = concurrent);	
   }	
	
   @PersistJobDataAfterExecution	
   @DisallowConcurrentExecution	
   public static class StatefulMethodInvokingJob extends MethodInvokingJobDetail.MethodInvokingJob {	
   }	
	
   public static class MethodInvokingJob extends QuartzJobBean {	
      private MethodInvokingJobDetail methodInvoker;	
      protected static final Log logger = LogFactory.getLog(MethodInvokingJobDetail.MethodInvokingJob.class);	
	
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
	
      public void setMethodInvoker(String methodInvoker) {	
         this.methodInvoker = l.ALLATORIxDEMO(methodInvoker);	
      }	
   }	
}	
