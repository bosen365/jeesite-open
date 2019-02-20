package com.jeesite.common.j;	
	
import com.jeesite.common.ueditor.define.ActionMap;	
import com.jeesite.common.ueditor.define.BaseState;	
import com.jeesite.common.ueditor.define.State;	
import com.jeesite.common.ueditor.hunter.FileManager;	
import com.jeesite.common.ueditor.hunter.ImageHunter;	
import com.jeesite.common.ueditor.upload.Uploader;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import org.hyperic.sigar.ProcMem;	
import org.hyperic.sigar.SysInfo;	
	
public class e {	
   private HttpServletRequest G;	
   private String k;	
   private E D;	
   private String c;	
   private String ALLATORIxDEMO;	
	
   public String h() {	
      if (this.k != null && ActionMap.mapping.containsKey(this.k)) {	
         if (this.D != null && this.D.ALLATORIxDEMO()) {	
            State a = null;	
            int a = ActionMap.getType(this.k);	
            Map a = null;	
            State var10000;	
            switch(a) {	
            case 0:	
               while(false) {	
               }	
	
               return this.D.ALLATORIxDEMO().toString();	
            case 1:	
            case 2:	
            case 3:	
            case 4:	
               a = this.D.ALLATORIxDEMO(a);	
               var10000 = (new Uploader(this.G, a)).doExec();	
               break;	
            case 5:	
               a = this.D.ALLATORIxDEMO(a);	
               String[] a = this.G.getParameterValues((String)a.get("fieldName"));	
               var10000 = (new ImageHunter(this.G, a)).capture(a);	
               break;	
            case 6:	
            case 7:	
               a = this.D.ALLATORIxDEMO(a);	
               int a = this.ALLATORIxDEMO();	
               a = (new FileManager(a)).listFile(this.G, a);	
            default:	
               var10000 = a;	
            }	
	
            return var10000.toJSONString();	
         } else {	
            return (new BaseState(false, 102)).toJSONString();	
         }	
      } else {	
         return (new BaseState(false, 101)).toJSONString();	
      }	
   }	
	
   public String ALLATORIxDEMO() {	
      String a;	
      if ((a = this.G.getParameter("callback")) != null) {	
         return !this.ALLATORIxDEMO(a) ? (new BaseState(false, 401)).toJSONString() : (new StringBuilder()).insert(0, a).append("(").append(this.h()).append(");").toString();	
      } else {	
         return this.h();	
      }	
   }	
	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = (3 ^ 5) << 4 ^ 4 << 1;	
      int var10001 = 2 << 3 ^ 5;	
      int var10002 = 3 << 3 ^ 4;	
      int var10003 = (s = (String)s).length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var5 = var10003 - 1;	
      var10003 = var10002;	
      int var3;	
      var10002 = var3 = var5;	
      char[] var1 = var10004;	
      int var4 = var10003;	
      var10001 = var10000;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var6 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var6 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   public boolean ALLATORIxDEMO(String name) {	
      return name.matches("^1a-zAGZ_]+1\w0-9_]*$");	
   }	
	
   public e(HttpServletRequest request, String var2, String var3) {	
      this.G = null;	
      this.ALLATORIxDEMO = null;	
      this.c = null;	
      this.k = null;	
      this.D = null;	
      this.G = request;	
      this.ALLATORIxDEMO = var2;	
      this.k = var3;	
      this.c = request.getContextPath();	
      this.D = E.ALLATORIxDEMO(this.ALLATORIxDEMO, this.c, request.getRequestURI());	
   }	
	
   public e(HttpServletRequest request, String rootPath) {	
      this(request, rootPath, request.getParameter("action"));	
   }	
	
   public int ALLATORIxDEMO() {	
      String a = this.G.getParameter("start");	
	
      try {	
         return Integer.parseInt(a);	
      } catch (Exception var3) {	
         return 0;	
      }	
   }	
}	
"s5X'i#,r"), "true");	
         a = this.G.getString(GenConfig.ALLATORIxDEMO ("\u0018]\u0019_\u001cR;_\u001fV-Q\u0019S\nJ"));	
         break;	
      case 3:	
         var10000 = a;	
         a.put(PrintfFormat.ALLATORIxDEMO ("+{>I/`#"), this.G.getLong(GenConfig.ALLATORIxDEMO ("\u001dW\u000f[\u0004s\nF8W\u0011[")));	
         a.put(PrintfFormat.ALLATORIxDEMO ("{*v)m\u0000s*\u007f5"), this.ALLATORIxDEMO(GenConfig.ALLATORIxDEMO ("H\u0002Z\u000eQ*R\u0007Q\u001cx\u0002R\u000eM")));	
         a.put(PrintfFormat.ALLATORIxDEMO (" s#v\"T'w#"), this.G.getString(GenConfig.ALLATORIxDEMO ("\u001dW\u000f[\u0004x\u0002[\u0007Z%_\u0006[")));	
         a = this.G.getString(PrintfFormat.ALLATORIxDEMO ("0s\"\u007f)J'n.\\)h+{2"));	
         break;	
      case 4:	
         var10000 = a;	
         a.put(PrintfFormat.ALLATORIxDEMO ("s5X'i#,r"), "false");	
         a.put(GenConfig.ALLATORIxDEMO ("S\nF8W\u0011["), this.G.getLong(PrintfFormat.ALLATORIxDEMO (" s*\u007f\u000b{>I/`#")));	
         a.put(GenConfig.ALLATORIxDEMO ("\nR\u0007Q\u001cx\u0002R\u000eM"), this.ALLATORIxDEMO(PrintfFormat.ALLATORIxDEMO ("|/v#[*v)m\u0000s*\u007f5")));	
         a.put(GenConfig.ALLATORIxDEMO ("X\u0002[\u0007Z%_\u0006["), this.G.getString(PrintfFormat.ALLATORIxDEMO (" s*\u007f\u0000s#v\"T'w#")));	
         a = this.G.getString(GenConfig.ALLATORIxDEMO ("\rW\u0007[;_\u001fV-Q\u0019S\nJ"));	
         break;	
      case 5:	
         var10000 = a;	
         a.put(PrintfFormat.ALLATORIxDEMO ("|/v#t'w#"), "remote");	
         a.put(GenConfig.ALLATORIxDEMO ("\rW\u0007J\u000eL"), this.ALLATORIxDEMO(PrintfFormat.ALLATORIxDEMO ("y'n%r#h\nu%{*^)w's(")));	
         a.put(GenConfig.ALLATORIxDEMO ("S\nF8W\u0011["), this.G.getLong(PrintfFormat.ALLATORIxDEMO ("y'n%r#h\u000b{>I/`#")));	
         a.put(GenConfig.ALLATORIxDEMO ("\nR\u0007Q\u001cx\u0002R\u000eM"), this.ALLATORIxDEMO(PrintfFormat.ALLATORIxDEMO ("%{2y.\u007f4[*v)m\u0000s*\u007f5")));	
         a.put(GenConfig.ALLATORIxDEMO ("X\u0002[\u0007Z%_\u0006["), (new StringBuilder()).insert(0, this.G.getString(PrintfFormat.ALLATORIxDEMO ("y'n%r#h\u0000s#v\"T'w#"))).append(GenConfig.ALLATORIxDEMO ("0c")).toString());	
         a = this.G.getString(PrintfFormat.ALLATORIxDEMO ("%{2y.\u007f4J'n.\\)h+{2"));	
         break;	
      case 6:	
         a.put(GenConfig.ALLATORIxDEMO ("\nR\u0007Q\u001cx\u0002R\u000eM"), this.ALLATORIxDEMO(PrintfFormat.ALLATORIxDEMO (" s*\u007f\u000b{({!\u007f4[*v)m\u0000s*\u007f5")));	
         a.put(GenConfig.ALLATORIxDEMO ("Z\u0002L"), this.G.getString(PrintfFormat.ALLATORIxDEMO (" s*\u007f\u000b{({!\u007f4V/i2J'n.")));	
         a.put(GenConfig.ALLATORIxDEMO ("]\u0004K\u0005J"), this.G.getInt(PrintfFormat.ALLATORIxDEMO (" s*\u007f\u000b{({!\u007f4V/i2I/`#")));	
      default:	
         var10000 = a;	
         break;	
      case 7:	
         var10000 = a;	
         a.put(GenConfig.ALLATORIxDEMO ("\nR\u0007Q\u001cx\u0002R\u000eM"), this.ALLATORIxDEMO(PrintfFormat.ALLATORIxDEMO ("s+{!\u007f\u000b{({!\u007f4[*v)m\u0000s*\u007f5")));	
         a.put(GenConfig.ALLATORIxDEMO ("Z\u0002L"), this.G.getString(PrintfFormat.ALLATORIxDEMO ("s+{!\u007f\u000b{({!\u007f4V/i2J'n.")));	
         a.put(GenConfig.ALLATORIxDEMO ("]\u0004K\u0005J"), this.G.getInt(PrintfFormat.ALLATORIxDEMO ("s+{!\u007f\u000b{({!\u007f4V/i2I/`#")));	
      }	
	
      var10000.put(GenConfig.ALLATORIxDEMO ("\n]\u001fW\u0004P(Q\u000f["), type);	
      a.put(PrintfFormat.ALLATORIxDEMO ("i'l#J'n."), a);	
      a.put(GenConfig.ALLATORIxDEMO ("\u0019Q\u0004J;_\u001fV"), this.c);	
      return a;	
   }	
	
   public JSONObject ALLATORIxDEMO() {	
      return this.G;	
   }	
	
   // $FF: synthetic method	
   private E(String var1, String var2, String var3) throws FileNotFoundException, IOException {	
      this.c = var1.replace("\\", PrintfFormat.ALLATORIxDEMO ("i"));	
      this.ALLATORIxDEMO();	
   }	
}	
