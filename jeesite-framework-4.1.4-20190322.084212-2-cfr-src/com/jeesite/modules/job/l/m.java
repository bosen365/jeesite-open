/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.job.l;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.job.entity.JobEntity;	
import com.jeesite.modules.job.entity.MethodInvokingJobDetail;	
import java.text.ParseException;	
import java.util.Properties;	
import org.apache.commons.lang3.math.NumberUtils;	
import org.hyperic.sigar.SudoFileInputStream;	
import org.quartz.CronTrigger;	
import org.quartz.JobDataMap;	
import org.quartz.JobDetail;	
import org.springframework.beans.factory.NoSuchBeanDefinitionException;	
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;	
	
/*	
 * Duplicate member names - consider using --renamedupmembers true	
 */	
public class m {	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 5 << 4 ^ (3 << 2 ^ 3);	
        5 << 4 ^ 3 << 1;	
        int n4 = n2;	
        int n5 = 5 << 3 ^ 3;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public static Properties ALLATORIxDEMO() {	
        Properties properties;	
        void a;	
        Properties a2 = new Properties();	
        a2.setProperty(m.ALLATORIxDEMO ("D-LqZ*J-_%\u0005+C-N>O\u000fD0GqH3J,X"), "org.quartz.simpl.SimpleThreadPool");	
        a2.setProperty(m.ALLATORIxDEMO ("D-LqZ*J-_%\u0005+C-N>O\u000fD0Gq_7Y:J;h0^1_"), Global.getProperty("job.threadPool.threadCount", m.ALLATORIxDEMO ("n\u001b")));	
        a2.setProperty("org.quartz.threadPool.threadPriority", Global.getProperty(m.ALLATORIxDEMO ("A0Iq_7Y:J;{0D3\u0005+C-N>O\u000fY6D-B+R"), "5"));	
        a2.setProperty("org.quartz.scheduler.instanceName", Global.getProperty(m.ALLATORIxDEMO ("5D=\u0005,H7N;^3N-\u00056E,_>E<N\u0011J2N"), "JeeSiteScheduler"));	
        a2.setProperty(m.ALLATORIxDEMO ("D-LqZ*J-_%\u0005,H7N;^3N-\u00056E,_>E<N\u0016O"), Global.getProperty("job.scheduler.instanceId", m.ALLATORIxDEMO ("\u001e~\u000bd")));	
        a2.setProperty("org.quartz.jobStore.tablePrefix", new StringBuilder().insert(0, Global.getTablePrefix()).append(m.ALLATORIxDEMO ("5D=t")).toString());	
        String a3 = null;	
        String string = Global.getJdbcType();	
        if ("oracle".equals(a)) {	
            a3 = m.ALLATORIxDEMO ("0Y8\u0005.^>Y+QqB2[3\u00055O=H5D=X+D-NqD-J<G:\u0005\u0010Y>H3N\u001bN3N8J+N");	
            properties = a2;	
        } else if ("mysql".equals(a)) {	
            a3 = m.ALLATORIxDEMO ("0Y8\u0005.^>Y+QqB2[3\u00055O=H5D=X+D-Nqx+O\u0015o\u001dh\u001bN3N8J+N");	
            properties = a2;	
        } else if ("mssql".equals(a)) {	
            a3 = m.ALLATORIxDEMO ("0Y8\u0005.^>Y+QqB2[3\u00055O=H5D=X+D-Nqf\fx\u000eg\u001bN3N8J+N");	
            properties = a2;	
        } else if ("postgresql".equals(a) || m.ALLATORIxDEMO ("7B8C8D").equals(a)) {	
            a3 = "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate";	
            properties = a2;	
        } else {	
            a3 = m.ALLATORIxDEMO ("0Y8\u0005.^>Y+QqB2[3\u00055O=H5D=X+D-Nqx+O\u0015o\u001dh\u001bN3N8J+N");	
            properties = a2;	
        }	
        properties.setProperty("org.quartz.jobStore.driverDelegateClass", a3);	
        a2.setProperty(m.ALLATORIxDEMO ("D-LqZ*J-_%\u00055D=x+D-NqH3J,X"), Global.getProperty("job.jobStore.className", m.ALLATORIxDEMO ("D-LqZ*J-_%\u00056F/GqA;I<A0I,_0Y:\u0005\u0015D=x+D-N\u000bs")));	
        a2.setProperty("org.quartz.jobStore.isClustered", Global.getProperty(m.ALLATORIxDEMO ("5D=\u00055D=x+D-NqB,h3^,_:Y:O"), "true"));	
        a2.setProperty("org.quartz.jobStore.clusterCheckinInterval", Global.getProperty(m.ALLATORIxDEMO ("A0IqA0I\f_0Y:\u0005<G*X+N-h7N<@6E\u0016E+N-]>G"), "1000"));	
        return a2;	
    }	
	
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
            a.setMisfireInstruction(job.getMisfireInstruction());	
        }	
        CronTriggerFactoryBean cronTriggerFactoryBean2 = a;	
        cronTriggerFactoryBean2.setJobDetail(jobDetail);	
        cronTriggerFactoryBean2.getJobDataMap().put(m.ALLATORIxDEMO ("6E)D4N\u000bJ-L:_"), job.getInvokeTarget());	
        cronTriggerFactoryBean2.getJobDataMap().put("concurrent", job.getConcurrent());	
        cronTriggerFactoryBean2.getJobDataMap().put(m.ALLATORIxDEMO ("Y:F>Y4X"), job.getRemarks());	
        try {	
            a.afterPropertiesSet();	
        }	
        catch (ParseException a2) {	
            throw new ServiceException(new StringBuilder().insert(0, "Cron表达式错误！").append(a2.getMessage()).toString());	
        }	
        return a.getObject();	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    public static JobDetail ALLATORIxDEMO(JobEntity job) {	
        void a;	
        MethodInvokingJobDetail methodInvokingJobDetail = m.ALLATORIxDEMO(job.getInvokeTarget());	
        void v0 = a;	
        JobEntity jobEntity = job;	
        a.setName(jobEntity.getJobName());	
        v0.setGroup(jobEntity.getJobGroup());	
        v0.setConcurrent("1".equals(job.getConcurrent()));	
        try {	
            a.afterPropertiesSet();	
        }	
        catch (ClassNotFoundException a2) {	
            throw new ServiceException(new StringBuilder().insert(0, m.ALLATORIxDEMO ("\u8c28\u7577\u76c5\u6858\u5b7c\u7b79\u4e19\u4e72\uff27\u6cfe\u6722\u8f86\u4e01\u7c24\uff2a")).append(a2.getMessage()).toString());	
        }	
        catch (NoSuchMethodException a3) {	
            throw new ServiceException(new StringBuilder().insert(0, "调用目标字符串中，没有这个方法！").append(a3.getMessage()).toString());	
        }	
        return a.getObject();	
    }	
	
    public static MethodInvokingJobDetail ALLATORIxDEMO(String invokeTarget) {	
        Object a;	
        Class<?> a2;	
        Class<?> class_;	
        int a3;	
        String a4;	
        block27 : {	
            Class<?> class_2;	
            if (StringUtils.isBlank(invokeTarget)) {	
                throw new ServiceException(m.ALLATORIxDEMO ("\u8c5c\u7503\u76b1\u682c\u5b08\u7b0d\u4e6d\u4e26\u80a2\u4e11\u7a25\uff2a"));	
            }	
            a2 = null;	
            a = null;	
            a4 = StringUtils.substringBeforeLast(invokeTarget, ".");	
            try {	
                a2 = Class.forName(a4);	
                a = SpringUtils.getBean(a2);	
                class_2 = a2;	
            }	
            catch (ClassNotFoundException classNotFoundException) {	
                class_2 = a2;	
            }	
            catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {	
                class_2 = a2;	
            }	
            if (class_2 == null || a == null) {	
                try {	
                    Object t = SpringUtils.getBean(a4);	
                    a = t;	
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
            throw new ServiceException(new StringBuilder().insert(0, "调用目标字符串中，找不到该Bean名或Class名！").append(a4).toString());	
        }	
        String string = invokeTarget;	
        String a5 = StringUtils.substringBeforeLast(StringUtils.substring(string, a4.length() + 1), m.ALLATORIxDEMO ("\u0003"));	
        String[] a6 = StringUtils.split(StringUtils.substringBetween(string, "(", m.ALLATORIxDEMO ("\u0002")), ",");	
        Object[] a7 = new Object[a6 == null ? 0 : a6.length];	
        int n = a3 = 0;	
        while (n < a7.length) {	
            String a8 = EncodeUtils.decodeHtml(StringUtils.trim(a6[a3]));	
            CharSequence[] arrcharSequence = new CharSequence[1];	
            arrcharSequence[0] = a8;	
            if (StringUtils.isNoneBlank(arrcharSequence)) {	
                if (NumberUtils.isCreatable(a8)) {	
                    if (StringUtils.endsWithIgnoreCase(a8, m.ALLATORIxDEMO ("g"))) {	
                        String string2 = a8;	
                        a7[a3] = NumberUtils.toLong(string2.substring(0, string2.length() - 1));	
                    } else if (StringUtils.endsWithIgnoreCase(a8, "D")) {	
                        String string3 = a8;	
                        a7[a3] = NumberUtils.toDouble(string3.substring(0, string3.length() - 1));	
                    } else {	
                        Object[] arrobject = a7;	
                        if (StringUtils.endsWithIgnoreCase(a8, m.ALLATORIxDEMO ("m"))) {	
                            String string4 = a8;	
                            arrobject[a3] = Float.valueOf(NumberUtils.toFloat(string4.substring(0, string4.length() - 1)));	
                        } else {	
                            arrobject[a3] = NumberUtils.toInt(a8);	
                        }	
                    }	
                } else if (StringUtils.startsWith(a8, "'") && StringUtils.endsWith(a8, m.ALLATORIxDEMO ("\f"))) {	
                    a7[a3] = StringUtils.substringBetween(a8, "'", m.ALLATORIxDEMO ("\f"));	
                } else {	
                    Object[] arrobject;	
                    block28 : {	
                        Object[] arrobject2;	
                        try {	
                            int n2 = a3;	
                            a7[n2] = SpringUtils.getBean(Class.forName(a6[n2]));	
                            arrobject2 = a7;	
                        }	
                        catch (ClassNotFoundException classNotFoundException) {	
                            arrobject2 = a7;	
                        }	
                        catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {	
                            arrobject2 = a7;	
                        }	
                        if (arrobject2[a3] == null) {	
                            try {	
                                int n3 = a3;	
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
                    if (arrobject[a3] == null) {	
                        throw new ServiceException(new StringBuilder().insert(0, "调用目标字符串中，对象参数 ").append(a6[a3]).append(m.ALLATORIxDEMO ("\u5bb5\u4fd4\u4e26\u80a2\u4e11\u7a25\uff2a")).toString());	
                    }	
                }	
            }	
            n = ++a3;	
        }	
        MethodInvokingJobDetail methodInvokingJobDetail = a3 = new MethodInvokingJobDetail();	
        MethodInvokingJobDetail methodInvokingJobDetail2 = a3;	
        a3.setInvokeTarget(invokeTarget);	
        methodInvokingJobDetail2.setTargetClass(a2);	
        methodInvokingJobDetail2.setTargetObject(a);	
        methodInvokingJobDetail.setTargetMethod(a5);	
        methodInvokingJobDetail.setArguments(a7);	
        return methodInvokingJobDetail;	
    }	
}	
	
