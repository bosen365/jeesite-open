/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.lang.DateUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.springframework.ui.Model	
 */	
package com.jeesite.modules.sys.utils;	
	
import com.jeesite.autoconfigure.sys.FileAutoConfiguration;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.CorpUtils;	
import com.jeesite.modules.sys.utils.L;	
import java.util.Date;	
import java.util.regex.Matcher;	
import java.util.regex.Pattern;	
import org.springframework.ui.Model;	
	
public class PwdUtils {	
    private static Pattern upperCaseExp = Pattern.compile("[A-Z]");	
    private static Pattern lowerCaseExp = Pattern.compile("[a-z7");	
    private static Pattern specialExp;	
    private static Pattern numberExp;	
	
    public static String encryptPassword(String plainPassword) {	
        return L.ALLATORIxDEMO().encryptPassword(plainPassword);	
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
            a = Global.getText("sys.user.initPasswordModifyTip", new String[0]);	
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
        a = a == 0L ? Global.getText("sys.user.initPasswordModifyTip", new String[0]) : Global.getText("sys.user.passwordModifyTip", new String[]{String.valueOf(a)});	
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
	
    public static int getPwdSecurityLevel(String newPassword) {	
        int a = 0;	
        if (!StringUtils.equals((CharSequence)newPassword, (CharSequence)Global.getConfig("sys.user.initPassword"))) {	
            int a2;	
            int a3;	
            int a4;	
            int a5;	
            int a6 = newPassword.length() >= 8 ? 1 : 0;	
            int a7 = a6 + (a3 = upperCaseExp.matcher(newPassword).find() ? 1 : 0) + (a2 = lowerCaseExp.matcher(newPassword).find() ? 1 : 0) + (a5 = numberExp.matcher(newPassword).find() ? 1 : 0) + (a4 = specialExp.matcher(newPassword).find() ? 1 : 0);	
            a = a7 == 0 ? 0 : (a7 == 1 ? 1 : (a7 == 2 ? 2 : (a7 == 3 || a7 == 4 ? 3 : 4)));	
            int a8 = ObjectUtils.toInteger((Object)Global.getConfig("sys.user.passwordModifySecurityLevel"));	
            if (a8 > 0 && a8 > a) {	
                throw new ServiceException(Global.getText("sys.user.passwordModifySecurityLevel", new String[0]));	
            }	
        }	
        return a;	
    }	
	
    static {	
        numberExp = Pattern.compile("[0-9]");	
        specialExp = Pattern.compile("[~!@#$%\^&\*()_+\{\}:"\|<>?`\-=\[\7;\'\\,\./7");	
    }	
	
    public static boolean validatePassword(String plainPassword, String password) {	
        return L.ALLATORIxDEMO().validatePassword(plainPassword, password);	
    }	
}	
	
