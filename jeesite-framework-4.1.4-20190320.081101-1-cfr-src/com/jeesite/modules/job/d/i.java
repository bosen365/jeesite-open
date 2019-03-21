/*	
 * Decompiled with CFR 0.140.	
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
package com.jeesite.modules.job.d;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.job.entity.JobEntity;	
import com.jeesite.modules.job.entity.MethodInvokingJobDetail;	
import java.text.ParseException;	
import java.util.Properties;	
import org.apache.commons.lang3.math.NumberUtils;	
import org.hyperic.sigar.test.GetPass;	
import org.quartz.CronTrigger;	
import org.quartz.JobDataMap;	
import org.quartz.JobDetail;	
import org.springframework.beans.factory.NoSuchBeanDefinitionException;	
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;	
	
/*	
 * Duplicate member names - consider using --renamedupmembers true	
 */	
public class i {	
    public static CronTrigger ALLATORIxDEMO(JobEntity job, JobDetail jobDetail) {	
        CronTriggerFactoryBean a = new CronTriggerFactoryBean();	
        JobEntity jobEntity = job;	
        CronTriggerFactoryBean cronTriggerFactoryBean = a;	
        JobEntity jobEntity2 = job;	
        a.setName(jobEntity2.getJobName());	
        cronTriggerFactoryBean.setGroup(jobEntity2.getJobGroup());	
        cronTriggerFactoryBean.setDescription(job.getDescription());	
        a.setCronExpression(jobEntity.getCronExpression());	
        if (jobEntity.getMisfireInstruction() != null) {	
            a.setMisfireInstruction(job.getMisfireInstruction().intValue());	
        }	
        CronTriggerFactoryBean cronTriggerFactoryBean2 = a;	
        cronTriggerFactoryBean2.setJobDetail(jobDetail);	
        cronTriggerFactoryBean2.getJobDataMap().put("invokeTarget", job.getInvokeTarget());	
        cronTriggerFactoryBean2.getJobDataMap().put("concurrent", job.getConcurrent());	
        cronTriggerFactoryBean2.getJobDataMap().put("remarks", job.getRemarks());	
        try {	
            a.afterPropertiesSet();	
        }	
        catch (ParseException a2) {	
            throw new ServiceException(new StringBuilder().insert(0, "Cron表达式错误！").append(a2.getMessage()).toString());	
        }	
        return a.getObject();	
    }	
	
    public static MethodInvokingJobDetail ALLATORIxDEMO(String invokeTarget) {	
        Class<?> a;	
        Class<?> class_;	
        int a2;	
        Object a3;	
        String a4;	
        block27 : {	
            Class<?> class_2;	
            if (StringUtils.isBlank((CharSequence)invokeTarget)) {	
                throw new ServiceException("调用目标字符串不能为空！");	
            }	
            a = null;	
            a3 = null;	
            a4 = StringUtils.substringBeforeLast((String)invokeTarget, (String)".");	
            try {	
                a = Class.forName(a4);	
                a3 = SpringUtils.getBean(a);	
                class_2 = a;	
            }	
            catch (ClassNotFoundException classNotFoundException) {	
                class_2 = a;	
            }	
            catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {	
                class_2 = a;	
            }	
            if (class_2 == null || a3 == null) {	
                try {	
                    Object t = SpringUtils.getBean(a4);	
                    a3 = t;	
                    class_ = a = t.getClass();	
                    break block27;	
                }	
                catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {	
                    // empty catch block	
                }	
            }	
            class_ = a;	
        }	
        if (class_ == null) {	
            throw new ServiceException(new StringBuilder().insert(0, "调用目标字符串中，找不到该Bean名或Class名！").append(a4).toString());	
        }	
        String string = invokeTarget;	
        String a5 = StringUtils.substringBeforeLast((String)StringUtils.substring((String)string, (int)(a4.length() + 1)), (String)"(");	
        String[] a6 = StringUtils.split((String)StringUtils.substringBetween((String)string, (String)"(", (String)")"), (String)",");	
        Object[] a7 = new Object[a6 == null ? 0 : a6.length];	
        int n = a2 = 0;	
        while (n < a7.length) {	
            String a8 = EncodeUtils.decodeHtml((String)StringUtils.trim((String)a6[a2]));	
            if (StringUtils.isNoneBlank((CharSequence[])new CharSequence[]{a8})) {	
                if (NumberUtils.isCreatable((String)a8)) {	
                    if (StringUtils.endsWithIgnoreCase((CharSequence)a8, (CharSequence)"L")) {	
                        String string2 = a8;	
                        a7[a2] = NumberUtils.toLong((String)string2.substring(0, string2.length() - 1));	
                    } else if (StringUtils.endsWithIgnoreCase((CharSequence)a8, (CharSequence)"D")) {	
                        String string3 = a8;	
                        a7[a2] = NumberUtils.toDouble((String)string3.substring(0, string3.length() - 1));	
                    } else {	
                        Object[] arrobject = a7;	
                        if (StringUtils.endsWithIgnoreCase((CharSequence)a8, (CharSequence)"F")) {	
                            String string4 = a8;	
                            arrobject[a2] = Float.valueOf(NumberUtils.toFloat((String)string4.substring(0, string4.length() - 1)));	
                        } else {	
                            arrobject[a2] = NumberUtils.toInt((String)a8);	
                        }	
                    }	
                } else if (StringUtils.startsWith((CharSequence)a8, (CharSequence)"'") && StringUtils.endsWith((CharSequence)a8, (CharSequence)"'")) {	
                    a7[a2] = StringUtils.substringBetween((String)a8, (String)"'", (String)"'");	
                } else {	
                    Object[] arrobject;	
                    block28 : {	
                        Object[] arrobject2;	
                        try {	
                            int n2 = a2;	
                            a7[n2] = SpringUtils.getBean(Class.forName(a6[n2]));	
                            arrobject2 = a7;	
                        }	
                        catch (ClassNotFoundException classNotFoundException) {	
                            arrobject2 = a7;	
                        }	
                        catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {	
                            arrobject2 = a7;	
                        }	
                        if (arrobject2[a2] == null) {	
                            try {	
                                int n3 = a2;	
                                a7[n3] = SpringUtils.getBean(a6[n3]);	
                                arrobject = a7;	
                                break block28;	
                            }	
                            catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {	
                                // empty catch block	
                            }	
                        }	
                        arrobject = a7;	
                    }	
                    if (arrobject[a2] == null) {	
                        throw new ServiceException(new StringBuilder().insert(0, "调用目标字符串中，对象参数 ").append(a6[a2]).append(" 实例不能为空！").toString());	
                    }	
                }	
            }	
            n = ++a2;	
        }	
        MethodInvokingJobDetail methodInvokingJobDetail = a2 = new MethodInvokingJobDetail();	
        MethodInvokingJobDetail methodInvokingJobDetail2 = a2;	
        a2.setInvokeTarget(invokeTarget);	
        methodInvokingJobDetail2.setTargetClass(a);	
        methodInvokingJobDetail2.setTargetObject(a3);	
        methodInvokingJobDetail.setTargetMethod(a5);	
        methodInvokingJobDetail.setArguments(a7);	
        return methodInvokingJobDetail;	
    }	
	
    public static Properties ALLATORIxDEMO() {	
        void a;	
        Properties properties;	
        Properties a2 = new Properties();	
        String a3 = null;	
        String string = Global.getJdbcType();	
        a2.setProperty("org.quartz.jobStore.tablePrefix", new StringBuilder().insert(0, Global.getTablePrefix()).append("job_").toString());	
        a2.setProperty("org.quartz.scheduler.instanceId", Global.getProperty("job.scheduler.instanceId", "AUTO"));	
        a2.setProperty("org.quartz.scheduler.instanceName", Global.getProperty("job.scheduler.instanceName", "JeeSiteScheduler"));	
        a2.setProperty("org.quartz.threadPool.threadPriority", Global.getProperty("job.threadPool.threadPriority", "5"));	
        a2.setProperty("org.quartz.threadPool.threadCount", Global.getProperty("job.threadPool.threadCount", "10"));	
        a2.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");	
        if ("oracle".equals(a)) {	
            a3 = "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";	
            properties = a2;	
        } else if ("mysql".equals(a)) {	
            a3 = "org.quartz.impl.jdbcjobstore.StdJDBCDelegate";	
            properties = a2;	
        } else if ("mssql".equals(a)) {	
            a3 = "org.quartz.impl.jdbcjobstore.MSSQLDelegate";	
            properties = a2;	
        } else if ("postgresql".equals(a) || "highgo".equals(a)) {	
            a3 = "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate";	
            properties = a2;	
        } else {	
            a3 = "org.quartz.impl.jdbcjobstore.StdJDBCDelegate";	
            properties = a2;	
        }	
        a2.setProperty("org.quartz.jobStore.clusterCheckinInterval", Global.getProperty("job.jobStore.clusterCheckinInterval", "1000"));	
        a2.setProperty("org.quartz.jobStore.isClustered", Global.getProperty("job.jobStore.isClustered", "true"));	
        a2.setProperty("org.quartz.jobStore.class", Global.getProperty("job.jobStore.className", "org.quartz.impl.jdbcjobstore.JobStoreTX"));	
        properties.setProperty("org.quartz.jobStore.driverDelegateClass", a3);	
        return a2;	
    }	
	
    public static JobDetail ALLATORIxDEMO(JobEntity job) {	
        void a;	
        MethodInvokingJobDetail methodInvokingJobDetail = i.ALLATORIxDEMO(job.getInvokeTarget());	
        void v0 = a;	
        JobEntity jobEntity = job;	
        a.setName(jobEntity.getJobName());	
        v0.setGroup(jobEntity.getJobGroup());	
        v0.setConcurrent("1".equals(job.getConcurrent()));	
        try {	
            a.afterPropertiesSet();	
        }	
        catch (ClassNotFoundException a2) {	
            throw new ServiceException(new StringBuilder().insert(0, "调用目标字符串中，没有这个类！").append(a2.getMessage()).toString());	
        }	
        catch (NoSuchMethodException a3) {	
            throw new ServiceException(new StringBuilder().insert(0, "调用目标字符串中，没有这个方法！").append(a3.getMessage()).toString());	
        }	
        return a.getObject();	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        char c2 = '\u0001';	
        int n3 = n2;	
        int n4 = 4 << 3 ^ 5;	
        1 << 3 ^ 1;	
        while (n3 >= 0) {	
            int n5 = n2--;	
            arrc[n5] = (char)(s.charAt(n5) ^ n4);	
            if (n2 < 0) break;	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ c2);	
            n3 = n2;	
        }	
        return new String(arrc);	
    }	
}	
	
