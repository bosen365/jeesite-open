package com.jeesite.common.l.i;	
	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.l.l.A;	
import com.jeesite.common.l.l.E;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.media.VideoUtils;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import java.awt.image.BufferedImage;	
import java.io.File;	
import java.io.IOException;	
import java.io.InputStream;	
import java.util.Arrays;	
import java.util.Iterator;	
import java.util.Map;	
import javax.imageio.ImageIO;	
import javax.servlet.http.HttpServletRequest;	
import net.coobird.thumbnailator.Thumbnails;	
import net.coobird.thumbnailator.Thumbnails.Builder;	
import org.apache.commons.fileupload.FileItemIterator;	
import org.apache.commons.fileupload.FileItemStream;	
import org.apache.commons.fileupload.FileUploadException;	
import org.apache.commons.fileupload.disk.DiskFileItemFactory;	
import org.apache.commons.fileupload.servlet.ServletFileUpload;	
import org.springframework.web.multipart.MultipartFile;	
import org.springframework.web.multipart.MultipartHttpServletRequest;	
	
public class g {	
   public static String ALLATORIxDEMO(String s) {	
      int var10000 = 2 << 3 ^ 3 ^ 5;	
      int var10001 = (3 ^ 5) << 4 ^ 2 << 2 ^ 1;	
      int var10003 = s.length();	
      char[] var10004 = new char[var10003];	
      boolean var10006 = true;	
      int var3;	
      int var10002 = var3 = var10003 - 1;	
      char[] var1 = var10004;	
      byte var4 = 3;	
      var10000 = var10002;	
	
      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {	
         var10001 = var3;	
         char var5 = s.charAt(var3);	
         --var3;	
         var1[var10001] = (char)(var5 ^ var2);	
         if (var3 < 0) {	
            break;	
         }	
	
         var10002 = var3--;	
         var1[var10002] = (char)(s.charAt(var10002) ^ var4);	
      }	
	
      return new String(var1);	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(String type, String[] allowTypes) {	
      return Arrays.asList(allowTypes).contains(type);	
   }	
	
   public static final com.jeesite.common.l.l.l ALLATORIxDEMO(HttpServletRequest request, Map conf) {	
      FileItemStream a = null;	
      MultipartFile a = null;	
      int a = request.getHeader("X_Reqested_With") != null;	
      if (!ServletFileUpload.isMultipartContent(request)) {	
         return new A(false, 5);	
      } else {	
         ServletFileUpload a = new ServletFileUpload(new DiskFileItemFactory());	
         if (a) {	
            a.setHeaderEncoding("UTF-8");	
         }	
	
         try {	
            FileItemIterator a;	
            FileItemIterator var10000 = a = a.getItemIterator(request);	
	
            FileItemStream var25;	
            while(true) {	
               if (!var10000.hasNext()) {	
                  var25 = a;	
                  break;	
               }	
	
               if (!(a = a.next()).isFormField()) {	
                  var25 = a;	
                  break;	
               }	
	
               a = null;	
               var10000 = a;	
            }	
	
            if (var25 == null) {	
               MultipartFile a = null;	
               MultipartHttpServletRequest a;	
               Iterator a;	
               if (request instanceof MultipartHttpServletRequest && (a = (a = (MultipartHttpServletRequest)request).getFileNames()).hasNext()) {	
                  a = a.getFile((String)a.next());	
               }	
	
               if (a != null && !a.isEmpty() && a.getOriginalFilename() != null) {	
                  a = a;	
               }	
            }	
	
            if (a == null && a == null) {	
               return new A(false, 7);	
            } else {	
               String a = (String)conf.get(com.jeesite.common.l.l.j.ALLATORIxDEMO ("]-X)~-Z$"));	
               String a = a != null ? a.getName() : a.getOriginalFilename();	
               String a = E.ALLATORIxDEMO(a);	
               a = a.substring(0, a.length() - a.length());	
               a = (new StringBuilder()).insert(0, a).append(a).toString();	
               long a = (Long)conf.get("maxSize");	
               if (!ALLATORIxDEMO(a, (String[])((String[])conf.get(com.jeesite.common.l.l.j.ALLATORIxDEMO ("O B#Y\nG K?"))))) {	
                  return new A(false, 8);	
               } else {	
                  a = com.jeesite.common.l.l.ALLATORIxDEMO(a, a);	
                  String a = FileUtils.path((String)conf.get("rootPath") + a);	
                  InputStream a = a != null ? a.openStream() : a.getInputStream();	
                  com.jeesite.common.l.l.l a = K.ALLATORIxDEMO(a, a, a);	
                  a.close();	
                  if (a.ALLATORIxDEMO()) {	
                     int a = (Integer)conf.get(com.jeesite.common.l.l.j.ALLATORIxDEMO ("O/Z%A\"m#J)"));	
                     String a = request.getContextPath();	
                     if (a == 1) {	
                        if ((Boolean)conf.get("imageCompressEnable")) {	
                           Integer a = (Integer)conf.get(com.jeesite.common.l.l.j.ALLATORIxDEMO ("%C-I)m#C<\\)]?l#\\(K>"));	
                           String var27 = FileUtils.getFileExtension(a);	
                           String[] var10001 = new String[1];	
                           boolean var10003 = true;	
                           var10001[0] = "gif";	
                           if (!StringUtils.inString(var27, var10001)) {	
                              String[] var28 = new String[1];	
                              boolean var10002 = true;	
                              var28[0] = a;	
                              Builder a = Thumbnails.of(var28);	
                              BufferedImage a;	
                              if ((a = ImageIO.read(new File(a))) != null) {	
                                 Builder var29;	
                                 if (a.getWidth() <= a) {	
                                    var29 = a;	
                                    a.width(a.getWidth());	
                                 } else {	
                                    var29 = a;	
                                    a.width(a);	
                                 }	
	
                                 var29.toFile(a);	
                              }	
                           }	
                        }	
                     } else {	
                        final VideoUtils a;	
                        if (a == 3 && (a = new VideoUtils(a)).cutPic()) {	
                           (new Thread(new Runnable() {	
                              public void run() {	
                                 try {	
                                    Thread.sleep(5000L);	
                                    a.convert();	
                                 } catch (InterruptedException var2) {	
                                    var2.printStackTrace();	
                                 }	
                              }	
                           })).start();	
                           a.ALLATORIxDEMO(com.jeesite.common.l.l.j.ALLATORIxDEMO ("9\\ "), (new StringBuilder()).insert(0, a).append(com.jeesite.common.l.l.F(a)).append(".").append(a.getOutputFileExtension()).toString());	
                           a.ALLATORIxDEMO(com.jeesite.common.l.l.j.ALLATORIxDEMO ("Z5^)"), (new StringBuilder()).insert(0, ".").append(a.getOutputFileExtension()).toString());	
                           a.ALLATORIxDEMO(com.jeesite.common.l.l.j.ALLATORIxDEMO ("A>G+G\"O "), (new StringBuilder()).insert(0, a).append(".").append(a.getInputFileExtension()).toString());	
                           return a;	
                        }	
                     }	
	
                     a.ALLATORIxDEMO(com.jeesite.common.l.l.j.ALLATORIxDEMO ("9\\ "), (new StringBuilder()).insert(0, a).append(com.jeesite.common.l.l.F(a)).toString());	
                     a.ALLATORIxDEMO("type", a);	
                     a.ALLATORIxDEMO(com.jeesite.common.l.l.j.ALLATORIxDEMO ("A>G+G\"O "), (new StringBuilder()).insert(0, a).append(a).toString());	
                  }	
	
                  return a;	
               }	
            }	
         } catch (FileUploadException var20) {	
            return new A(false, 6);	
         } catch (IOException var21) {	
            return new A(false, 4);	
         }	
      }	
   }	
}	
