/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.utils.e;	
import java.util.Date;	
import java.util.regex.Matcher;	
import java.util.regex.Pattern;	
import org.hyperic.sigar.win32.FileVersion;	
import org.springframework.ui.Model;	
	
public class PwdUtils {	
    private static Pattern numberExp;	
    private static Pattern specialExp;	
    private static Pattern upperCaseExp;	
    private static Pattern lowerCaseExp;	
	
    public static int getPwdSecurityLevel(String newPassword) {	
        int a = 0;	
        if (!StringUtils.equals(newPassword, Global.getConfig("sys.user.initPssword"))) {	
            int a2;	
            int a3;	
            int a4;	
            int a5;	
            int a6 = newPassword.length() >= 8 ? 1 : 0;	
            int a7 = a6 + (a5 = upperCaseExp.matcher(newPassword).find() ? 1 : 0) + (a4 = lowerCaseExp.matcher(newPassword).find() ? 1 : 0) + (a3 = numberExp.matcher(newPassword).find() ? 1 : 0) + (a2 = specialExp.matcher(newPassword).find() ? 1 : 0);	
            a = a7 == 0 ? 0 : (a7 == 1 ? 1 : (a7 == 2 ? 2 : (a7 == 3 || a7 == 4 ? 3 : 4)));	
            int a8 = ObjectUtils.toInteger(Global.getConfig("sys.user.passwordModifySecurityLevel"));	
            if (a8 > 0 && a8 > a) {	
                throw new ServiceException(Global.getText("sys.user.passwordModifySecurityLevel", new String[0]));	
            }	
        }	
        return a;	
    }	
	
    public static String encryptPassword(String plainPassword) {	
        return e.ALLATORIxDEMO().encryptPassword(plainPassword);	
    }	
	
    public static boolean validatePassword(String plainPassword, String password) {	
        return e.ALLATORIxDEMO().validatePassword(plainPassword, password);	
    }	
	
    static {	
        upperCaseExp = Pattern.compile("[A-Z]");	
        lowerCaseExp = Pattern.compile("[a-z]");	
        numberExp = Pattern.compile("[0-9]");	
        specialExp = Pattern.compile("[~!@#$%\^&\*()_+\{\}:"\|<>?`\-=\[\];\'\\,\./]");	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Lifted jumps to return sites	
     */	
    public static String passwordModifyValid(User user, Model model) {	
        a = null;	
        a = Global.getConfig("sys.user.initPasswordModify");	
        if (!"0".equals(a) && (a = Boolean.valueOf(PwdUtils.validatePassword(Global.getConfig("sys.user.initPssword"), user.getPassword()))).booleanValue()) {	
            a = Global.getText("sys.user.initPasswordModifyTip", new String[0]);	
            if ("1".equals(a)) {	
                model.addAttribute("modifyPasswordTip", a);	
            } else if ("2".equals(a)) {	
                a = a;	
            }	
        }	
        a = Global.getConfig("sys.user.passwordModify");	
        if (a == null || "0".equals(a)) ** GOTO lbl36	
        a = ObjectUtils.toInteger(Global.getConfig("sys.user.passwordModifyCycle"));	
        v0 = a = user.getPwdUpdateDate() == null ? 0L : DateUtils.pastDays(user.getPwdUpdateDate());	
        if (user.getPwdUpdateDate() != null && a < (long)a) ** GOTO lbl36	
        a = null;	
        if (a == 0L) {	
            a = Global.getText("sys.user.initPasswordModifyTip", new String[0]);	
        } else {	
            v1 = new String[1];	
            v1[0] = String.valueOf(a);	
            a = Global.getText("sys.user.passwordModifyTip", v1);	
        }	
        if ("1".equals(a)) {	
            v2 = a;	
            model.addAttribute("modifyPasswordTip", a);	
        } else {	
            if ("2".equals(a)) {	
                a = a;	
            }	
lbl36: // 5 sources:	
            v2 = a;	
        }	
        if (v2 == null) return null;	
        return new StringBuilder().insert(0, Global.getAdminPath()).append("/sys/user/info?op=pwd&url=").append(Global.getAdminPath()).append("/index&msg=").append(a).toString();	
    }	
}	
	
