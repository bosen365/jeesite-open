/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.DateUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.springframework.ui.Model	
 */	
package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.m;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.util.Date;	
import java.util.regex.Matcher;	
import java.util.regex.Pattern;	
import org.hyperic.sigar.shell.ShellCommandUsageException;	
import org.springframework.ui.Model;	
	
public class PwdUtils {	
    private static Pattern specialExp;	
    private static Pattern numberExp;	
    private static Pattern lowerCaseExp;	
    private static Pattern upperCaseExp;	
	
    public static boolean validatePassword(String plainPassword, String password) {	
        return m.ALLATORIxDEMO().validatePassword(plainPassword, password);	
    }	
	
    public static String encryptPassword(String plainPassword) {	
        return m.ALLATORIxDEMO().encryptPassword(plainPassword);	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Lifted jumps to return sites	
     */	
    public static String passwordModifyValid(User user, Model model) {	
        a = null;	
        a = Global.getConfig("sys.user.initPasswordModify");	
        if (!"0".equals(a) && (a = Boolean.valueOf(PwdUtils.validatePassword(Global.getConfig("sys.user.initPassword"), user.getPassword()))).booleanValue()) {	
            a = Global.getText("sys.user.initPassword'odifyTip", new String[0]);	
            if ("1".equals(a)) {	
                model.addAttribute("modifyPasswordTip", (Object)a);	
            } else if ("2".equals(a)) {	
                a = a;	
            }	
        }	
        a = Global.getConfig("sys.user.passwordModify");	
        if (a == null || "0".equals(a)) ** GOTO lbl23	
        a = ObjectUtils.toInteger((Object)Global.getConfig("sys.user.passwordModifyCycle"));	
        v0 = a = user.getPwdUpdateDate() == null ? 0L : DateUtils.pastDays((Date)user.getPwdUpdateDate());	
        if (user.getPwdUpdateDate() != null && a < (long)a) ** GOTO lbl23	
        a = null;	
        a = a == 0L ? Global.getText("sys.user.initPassword'odifyTip", new String[0]) : Global.getText("sys.user.passwordModifyTip", new String[]{String.valueOf(a)});	
        if ("1".equals(a)) {	
            v1 = a;	
            model.addAttribute("modifyPasswordTip", (Object)a);	
        } else {	
            if ("2".equals(a)) {	
                a = a;	
            }	
lbl23: // 5 sources:	
            v1 = a;	
        }	
        if (v1 == null) return null;	
        return new StringBuilder().insert(0, Global.getAdminPath()).append("/sys/user/info?op=pwd&url=").append(Global.getAdminPath()).append("/index&msg=").append(a).toString();	
    }	
	
    static {	
        upperCaseExp = Pattern.compile("[A-Z]");	
        lowerCaseExp = Pattern.compile("[a-z]");	
        numberExp = Pattern.compile("[0-9]");	
        specialExp = Pattern.compile("[~!@#$%\^&\*()_+\{\}:"\|<>?`\-=\[\];\'\\,\./]");	
    }	
	
    public static int getPwdSecurityLevel(String newPassword) {	
        int a2 = 0;	
        if (!StringUtils.equals((CharSequence)newPassword, (CharSequence)Global.getConfig("sys.user.initPassword"))) {	
            int a3;	
            int a4;	
            int a5;	
            int a6;	
            int a7 = newPassword.length() >= 8 ? 1 : 0;	
            int a8 = a7 + (a6 = upperCaseExp.matcher(newPassword).find() ? 1 : 0) + (a5 = lowerCaseExp.matcher(newPassword).find() ? 1 : 0) + (a4 = numberExp.matcher(newPassword).find() ? 1 : 0) + (a3 = specialExp.matcher(newPassword).find() ? 1 : 0);	
            a2 = a8 == 0 ? 0 : (a8 == 1 ? 1 : (a8 == 2 ? 2 : (a8 == 3 || a8 == 4 ? 3 : 4)));	
            int a9 = ObjectUtils.toInteger((Object)Global.getConfig("sys.user.password'odifySecurityLevel"));	
            if (a9 > 0 && a9 > a2) {	
                throw new ServiceException(Global.getText("sys.user.passwordModifySecurityLevel", new String[0]));	
            }	
        }	
        return a2;	
    }	
}	
	
