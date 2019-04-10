本项目仅供学习研究使用，切勿用于商业用途，如需使用请购买正版授权。

jeesite-framework4.X 反编译版本

1.反编译

java -jar fernflower.jar jeesite-framework-4.1.3-20190218.140934-2.jar ./jeesite20190218

java -jar cfr-0.141.jar --extraclasspath "依赖jar路径" jeesite-framework-4.1.3-20190218.140934-2 --outputdir ./jeesite20190218

注:增加--extraclasspath 会减少如下提示信息

/*
 * Could not load the following classes:
 *  net.oschina.j2cache.CacheChannel
 *  org.springframework.boot.autoconfigure.ImportAutoConfiguration
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 */

2.处理ALLATORIxDEMO加密字符串

自己写程序处理了ALLATORIxDEMO加密字符串，反编译之后代码基本可读

文件说明

fernflower.jar反编译工具 存在问题，反编译后变量名都是a无法区分是哪个

cfr-0.139.jar反编译工具 存在问题，反编译后StringBuilder的多个append顺序会错乱

已给cfr作者反馈，作者在0.141版本已经修复这个问题，强烈建议使用cfr-0.141.jar版本

jeesite-framework-4.1.3-20190218.140934-2.jar原始jar包

jeesite-framework-4.1.3-20190218.140934-2-cfr cfr反编译处理后的java源文件

jeesite-framework-4.1.3-20190218.140934-2-fernflower fernflower反编译处理后的java源文件

建议先看4.0版代码

反编译授权破解等技术交流 
QQ群:885483571
