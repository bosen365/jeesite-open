jeesite-framework4.1.3 反编译版本

1.反编译

java -jar fernflower.jar jeesite-framework-4.1.3-20190218.140934-2.jar ./jeesite20190218

java -jar cfr-0.139.jar jeesite-framework-4.1.3-20190218.140934-2 --outputdir ./jeesite20190218

2.处理ALLATORIxDEMO加密字符串

自己写程序处理了ALLATORIxDEMO加密字符串，反编译之后代码基本可读

文件说明

fernflower.jar反编译工具 存在问题，反编译后变量名都是a无法区分是哪个

cfr-0.139.jar反编译工具 存在问题，反编译后StringBuilder的多个append顺序会错乱

jeesite-framework-4.1.3-20190218.140934-2.jar原始jar包

jeesite-framework-4.1.3-20190218.140934-2-cfr cfr反编译处理后的java源文件

jeesite-framework-4.1.3-20190218.140934-2-fernflower fernflower反编译处理后的java源文件

建议先看4.0版代码

反编译授权破解等技术交流 
QQ群:885483571
