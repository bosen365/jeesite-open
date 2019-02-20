package com.jeesite.modules.sys.utils;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.DateUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.util.regex.Pattern;	
import org.hyperic.sigar.shell.ShellCommandUsageException;	
import org.springframework.ui.Model;	
	
public class PwdUtils {	
   private static Pattern specialExp = Pattern.compile("[~!@#$%\^&\*()_+\{\}:"\|<>?`\-=\[\];\'\\,\./]");	
   private static Pattern numberExp = Pattern.compile("[0-9]");	
   private static Pattern lowerCaseExp = Pattern.compile("[a-z]");	
   private static Pattern upperCaseExp = Pattern.compile("[A-Z]");	
	
   public static boolean validatePassword(String plainPassword, String password) {	
      return null.ALLATORIxDEMO().validatePassword(plainPassword, password);	
   }	
	
   public static String encryptPassword(String plainPassword) {	
      return null.ALLATORIxDEMO().encryptPassword(plainPassword);	
   }	
	
   public static String passwordModifyValid(User user, Model model) {	
      String a = null;	
      String a = Global.getConfig("sys.user.initPasswordModify");	
      String var10000;	
      String[] var10001;	
      boolean var10003;	
      if (!"0".equals(a) && Boolean.valueOf(validatePassword(Global.getConfig("sys.user.initPassword"), user.getPassword()))) {	
         var10000 = "sys.user.initPassword'odifyTip";	
         var10001 = new String[0];	
         var10003 = true;	
         String a = Global.getText(var10000, var10001);	
         if ("1".equals(a)) {	
            model.addAttribute("modifyPasswordTip", a);	
         } else if ("2".equals(a)) {	
            a = a;	
         }	
      }	
	
      Boolean a = Global.getConfig("sys.user.passwordModify");	
      if (a != null && !"0".equals(a)) {	
         int a = ObjectUtils.toInteger(Global.getConfig("sys.user.passwordModifyCycle"));	
         long a = user.getPwdUpdateDate() == null ? 0L : DateUtils.pastDays(user.getPwdUpdateDate());	
         if (user.getPwdUpdateDate() == null || a >= (long)a) {	
            String a = null;	
            if (a == 0L) {	
               var10000 = "sys.user.initPassword'odifyTip";	
               var10001 = new String[0];	
               var10003 = true;	
               a = Global.getText(var10000, var10001);	
            } else {	
               var10000 = "sys.user.passwordModifyTip";	
               var10001 = new String[1];	
               var10003 = true;	
               var10001[0] = String.valueOf(a);	
               a = Global.getText(var10000, var10001);	
            }	
	
            if ("1".equals(a)) {	
               var10000 = a;	
               model.addAttribute("modifyPasswordTip", a);	
               return var10000 != null ? (new StringBuilder()).insert(0, Global.getAdminPath()).append("/sys/user/info?op=pwd&url=").append(Global.getAdminPath()).append("/index&msg=").append(a).toString() : null;	
            }	
	
            if ("2".equals(a)) {	
               a = a;	
            }	
         }	
      }	
	
      var10000 = a;	
      return var10000 != null ? (new StringBuilder()).insert(0, Global.getAdminPath()).append("/sys/user/info?op=pwd&url=").append(Global.getAdminPath()).append("/index&msg=").append(a).toString() : null;	
   }	
	
   public static int getPwdSecurityLevel(String newPassword) {	
      int a = 0;	
      if (!StringUtils.equals(newPassword, Global.getConfig("sys.user.initPassword"))) {	
         int a = newPassword.length() >= 8 ? 1 : 0;	
         int a = upperCaseExp.matcher(newPassword).find() ? 1 : 0;	
         int a = lowerCaseExp.matcher(newPassword).find() ? 1 : 0;	
         int a = numberExp.matcher(newPassword).find() ? 1 : 0;	
         int a = specialExp.matcher(newPassword).find() ? 1 : 0;	
         int a;	
         if ((a = a + a + a + a + a) == 0) {	
            a = 0;	
         } else if (a == 1) {	
            a = 1;	
         } else if (a == 2) {	
            a = 2;	
         } else if (a != 3 && a != 4) {	
            a = 4;	
         } else {	
            a = 3;	
         }	
	
         int a;	
         if ((a = ObjectUtils.toInteger(Global.getConfig("sys.user.password'odifySecurityLevel"))) > 0 && a > a) {	
            String var10002 = "sys.user.passwordModifySecurityLevel";	
            String[] var10003 = new String[0];	
            boolean var10005 = true;	
            throw new ServiceException(Global.getText(var10002, var10003));	
         }	
      }	
	
      return a;	
   }	
}	
