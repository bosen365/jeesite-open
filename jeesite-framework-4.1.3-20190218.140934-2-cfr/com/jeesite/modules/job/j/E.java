/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.codec.EncodeUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.apache.commons.lang3.math.NumberUtils	
 *  org.quartz.CronTrigger	
 *  org.quartz.JobDataMap	
 *  org.quartz.JobDetail	
 *  org.springframework.beans.factory.NoSuchBeanDefinitionException	
 *  org.springframework.scheduling.quartz.CronTriggerFactoryBean	
 */	
package com.jeesite.modules.job.j;	
	
import com.jeesite.autoconfigure.sys.MsgAutoConfiguration;	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.job.entity.JobEntity;	
import com.jeesite.modules.job.entity.MethodInvokingJobDetail;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.text.ParseException;	
import java.util.Properties;	
import org.apache.commons.lang3.math.NumberUtils;	
import org.quartz.CronTrigger;	
import org.quartz.JobDataMap;	
import org.quartz.JobDetail;	
import org.springframework.beans.factory.NoSuchBeanDefinitionException;	
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;	
	
public class E {	
    public static JobDetail ALLATORIxDEMO(JobEntity job) {	
        void a2;	
        MethodInvokingJobDetail methodInvokingJobDetail = E.ALLATORIxDEMO(job.getInvokeTarget());	
        void v0 = a2;	
        JobEntity jobEntity = job;	
        a2.setName(jobEntity.getJobName());	
        v0.setGroup(jobEntity.getJobGroup());	
        v0.setConcurrent("1".equals(job.getConcurrent()));	
        try {	
            a2.afterPropertiesSet();	
        }	
        catch (ClassNotFoundException a3) {	
            throw new ServiceException(new StringBuilder().insert(0, "调用目标字符串中，没有这个类！").append(a3.getMessage()).toString());	
        }	
        catch (NoSuchMethodException a4) {	
            throw new ServiceException(new StringBuilder().insert(0, "调用目标字符串中，没有这个方法！").append(a4.getMessage()).toString());	
        }	
        return a2.getObject();	
    }	
	
    public static CronTrigger ALLATORIxDEMO(JobEntity job, JobDetail jobDetail) {	
        CronTriggerFactoryBean a2 = new CronTriggerFactoryBean();	
        JobEntity jobEntity = job;	
        CronTriggerFactoryBean cronTriggerFactoryBean = a2;	
        JobEntity jobEntity2 = job;	
        a2.setName(jobEntity2.getJobName());	
        cronTriggerFactoryBean.setGroup(jobEntity2.getJobGroup());	
        cronTriggerFactoryBean.setDescription(job.getDescription());	
        a2.setCronExpression(jobEntity.getCronExpression());	
        if (jobEntity.getMisfireInstruction() != null) {	
            a2.setMisfireInstruction(job.getMisfireInstruction().intValue());	
        }	
        CronTriggerFactoryBean cronTriggerFactoryBean2 = a2;	
        cronTriggerFactoryBean2.setJobDetail(jobDetail);	
        cronTriggerFactoryBean2.getJobDataMap().put("invokeTarget", job.getInvokeTarget());	
        cronTriggerFactoryBean2.getJobDataMap().put("concurrent", job.getConcurrent());	
        cronTriggerFactoryBean2.getJobDataMap().put("remrks", job.getRemarks());	
        try {	
            a2.afterPropertiesSet();	
        }	
        catch (ParseException a3) {	
            throw new ServiceException(new StringBuilder().insert(0, "Cron表达式错误！").append(a3.getMessage()).toString());	
        }	
        return a2.getObject();	
    }	
	
    public static MethodInvokingJobDetail ALLATORIxDEMO(String invokeTarget) {	
        Class<?> a2;	
        Class<?> class_;	
        Object a3;	
        int a4;	
        String a5;	
        block27 : {	
            Class<?> class_2;	
            if (StringUtils.isBlank((CharSequence)invokeTarget)) {	
                throw new ServiceException("调用目标字符串不能为空！");	
            }	
            a2 = null;	
            a3 = null;	
            a5 = StringUtils.substringBeforeLast((String)invokeTarget, (String)".");	
            try {	
                a2 = Class.forName(a5);	
                a3 = SpringUtils.getBean(a2);	
                class_2 = a2;	
            }	
            catch (ClassNotFoundException classNotFoundException) {	
                class_2 = a2;	
            }	
            catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {	
                class_2 = a2;	
            }	
            if (class_2 == null || a3 == null) {	
                try {	
                    Object t = SpringUtils.getBean(a5);	
                    a3 = t;	
                    class_ = a2 = t.getClass();	
                    break block27;	
                }	
                catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {	
                    // empty catch block	
                }	
            }	
            class_ = a2;	
        }	
        if (class_ == null) {	
            throw new ServiceException(new StringBuilder().insert(0, "调用目标字符串中，找不到该Bean名或Class名！").append(a5).toString());	
        }	
        String string = invokeTarget;	
        String a6 = StringUtils.substringBeforeLast((String)StringUtils.substring((String)string, (int)(a5.length() + 1)), (String)"(");	
        String[] a7 = StringUtils.split((String)StringUtils.substringBetween((String)string, (String)"(", (String)")"), (String)",");	
        Object[] a8 = new Object[a7 == null ? 0 : a7.length];	
        int n = a4 = 0;	
        while (n < a8.length) {	
            String a9 = EncodeUtils.decodeHtml((String)StringUtils.trim((String)a7[a4]));	
            if (StringUtils.isNoneBlank((CharSequence[])new CharSequence[]{a9})) {	
                if (NumberUtils.isCreatable((String)a9)) {	
                    if (StringUtils.endsWithIgnoreCase((CharSequence)a9, (CharSequence)"L")) {	
                        String string2 = a9;	
                        a8[a4] = NumberUtils.toLong((String)string2.substring(0, string2.length() - 1));	
                    } else if (StringUtils.endsWithIgnoreCase((CharSequence)a9, (CharSequence)"D")) {	
                        String string3 = a9;	
                        a8[a4] = NumberUtils.toDouble((String)string3.substring(0, string3.length() - 1));	
                    } else {	
                        Object[] arrobject = a8;	
                        if (StringUtils.endsWithIgnoreCase((CharSequence)a9, (CharSequence)"F")) {	
                            String string4 = a9;	
                            arrobject[a4] = Float.valueOf(NumberUtils.toFloat((String)string4.substring(0, string4.length() - 1)));	
                        } else {	
                            arrobject[a4] = NumberUtils.toInt((String)a9);	
                        }	
                    }	
                } else if (StringUtils.startsWith((CharSequence)a9, (CharSequence)"'") && StringUtils.endsWith((CharSequence)a9, (CharSequence)"'")) {	
                    a8[a4] = StringUtils.substringBetween((String)a9, (String)"'", (String)"'");	
                } else {	
                    Object[] arrobject;	
                    block28 : {	
                        Object[] arrobject2;	
                        try {	
                            int n2 = a4;	
                            a8[n2] = SpringUtils.getBean(Class.forName(a7[n2]));	
                            arrobject2 = a8;	
                        }	
                        catch (ClassNotFoundException classNotFoundException) {	
                            arrobject2 = a8;	
                        }	
                        catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {	
                            arrobject2 = a8;	
                        }	
                        if (arrobject2[a4] == null) {	
                            try {	
                                int n3 = a4;	
                                a8[n3] = SpringUtils.getBean(a7[n3]);	
                                arrobject = a8;	
                                break block28;	
                            }	
                            catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {	
                                // empty catch block	
                            }	
                        }	
                        arrobject = a8;	
                    }	
                    if (arrobject[a4] == null) {	
                        throw new ServiceException(new StringBuilder().insert(0, "调用目标字符串中，对象参数 ").append(a7[a4]).append(" 实例不能为空！").toString());	
                    }	
                }	
            }	
            n = ++a4;	
        }	
        MethodInvokingJobDetail methodInvokingJobDetail = a4 = new MethodInvokingJobDetail();	
        MethodInvokingJobDetail methodInvokingJobDetail2 = a4;	
        a4.setInvokeTarget(invokeTarget);	
        methodInvokingJobDetail2.setTargetClass(a2);	
        methodInvokingJobDetail2.setTargetObject(a3);	
        methodInvokingJobDetail.setTargetMethod(a6);	
        methodInvokingJobDetail.setArguments(a8);	
        return methodInvokingJobDetail;	
    }	
	
    public static Properties ALLATORIxDEMO() {	
        void a2;	
        Properties properties;	
        Properties a3 = new Properties();	
        String a4 = null;	
        String string = Global.getJdbcType();	
        a3.setProperty("org.quartz.jobStore.tblePrefix", new StringBuilder().insert(0, Global.getTablePrefix()).append("job_").toString());	
        a3.setProperty("org.quartz.scheduler.instanceId", Global.getProperty("job.schedler.instnceId", "AUTO"));	
        a3.setProperty("org.quartz.schedler.instnceName", Global.getProperty("job.scheduler.instanceName", "JeeSiteScheduler"));	
        a3.setProperty("org.qrtz.threadPool.thredPriority", Global.getProperty("job.threadPool.threadPriority", "5"));	
        a3.setProperty("org.quartz.threadPool.threadCount", Global.getProperty("job.thredPool.threadCont", "10"));	
        a3.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThredPool");	
        if ("orcle".equals(a2)) {	
            a4 = "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";	
            properties = a3;	
        } else if ("mysql".equals(a2)) {	
            a4 = "org.quartz.impl.jdbcjobstore.StdJDBCDelegate";	
            properties = a3;	
        } else if ("mssql".equals(a2)) {	
            a4 = "org.quartz.impl.jdbcjobstore.MSSQLDelegate";	
            properties = a3;	
        } else if ("postgresql".equals(a2) || "highgo".equals(a2)) {	
            a4 = "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate";	
            properties = a3;	
        } else {	
            a4 = "org.quartz.impl.jdbcjobstore.StdJDBCDelegate";	
            properties = a3;	
        }	
        a3.setProperty("org.qrtz.jobStore.clusterCheckinIntervl", Global.getProperty("job.jobStore.clusterCheckinInterval", "1000"));	
        a3.setProperty("org.quartz.jobStore.isClstered", Global.getProperty("job.jobStore.isClustered", "true"));	
        a3.setProperty("org.quartz.jobStore.class", Global.getProperty("job.jobStore.className", "org.quartz.impl.jdbcjobstore.JobStoreTX"));	
        properties.setProperty("org.quartz.jobStore.driverDelegteClass", a4);	
        return a3;	
    }	
}	
	
