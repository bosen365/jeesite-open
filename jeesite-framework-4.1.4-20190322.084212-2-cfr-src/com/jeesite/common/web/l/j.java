/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.web.l;	
	
import com.jeesite.common.mybatis.l.w.I;	
import com.jeesite.common.mybatis.l.w.l;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.shiro.session.SessionDAO;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.common.web.l.H;	
import com.jeesite.common.web.l.h;	
import com.jeesite.common.web.l.m;	
import java.io.InputStream;	
import java.io.UnsupportedEncodingException;	
import java.lang.management.ManagementFactory;	
import java.math.BigInteger;	
import java.security.GeneralSecurityException;	
import java.security.Key;	
import java.security.KeyFactory;	
import java.security.MessageDigest;	
import java.security.PrivateKey;	
import java.security.PublicKey;	
import java.security.interfaces.RSAPrivateKey;	
import java.security.interfaces.RSAPublicKey;	
import java.security.spec.AlgorithmParameterSpec;	
import java.security.spec.KeySpec;	
import java.security.spec.PKCS8EncodedKeySpec;	
import java.security.spec.X509EncodedKeySpec;	
import java.text.SimpleDateFormat;	
import java.util.Arrays;	
import java.util.Collection;	
import java.util.Collections;	
import java.util.Date;	
import java.util.HashMap;	
import java.util.HashSet;	
import java.util.Iterator;	
import java.util.Map;	
import javax.crypto.Cipher;	
import javax.crypto.spec.IvParameterSpec;	
import javax.crypto.spec.SecretKeySpec;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.commons.io.IOUtils;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.support.DefaultSubjectContext;	
import org.hyperic.sigar.FileWatcher;	
import org.hyperic.sigar.cmd.EventLogTail;	
	
/*	
 * Duplicate member names - consider using --renamedupmembers true	
 */	
public final class j {	
    private static final String a;	
    private static Date l;	
    private static final m h;	
    private static SessionDAO J;	
    private static final String[] c;	
    private static final String ALLATORIxDEMO;	
	
    public static final int ALLATORIxDEMO() {	
        Iterator<Session> iterator;	
        if (J == null) {	
            J = SpringUtils.getBean(SessionDAO.class);	
        }	
        HashSet<String> a = new HashSet<String>();	
        I.ALLATORIxDEMO = false;	
        Iterator<Session> iterator2 = iterator = J.getActiveSessions().iterator();	
        while (iterator2.hasNext()) {	
            LoginInfo a2;	
            Session a3 = iterator.next();	
            String a4 = "";	
            Object a5 = a3.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);	
            if (a5 != null && a5 instanceof PrincipalCollection && (a2 = (LoginInfo)((PrincipalCollection)a5).getPrimaryPrincipal()) != null) {	
                a4 = a2.getId();	
            }	
            if (j.ALLATORIxDEMO((CharSequence)a4)) {	
                a4 = (String)a3.getAttribute("userCode");	
            }	
            if (j.ALLATORIxDEMO((CharSequence)a4)) {	
                iterator2 = iterator;	
                continue;	
            }	
            a.add(a4);	
            iterator2 = iterator;	
        }	
        l.ALLATORIxDEMO = false;	
        return a.size();	
    }	
	
    public static final m ALLATORIxDEMO() {	
        HashMap<String, String> a;	
        Object a2;	
        block7 : {	
            long a3 = System.currentTimeMillis() - l.getTime();	
            if (h.ALLATORIxDEMO() && a3 / 86400000L <= 0L) {	
                return h;	
            }	
            l = new Date();	
            a = new HashMap<String, String>();	
            try {	
                a2 = SpringUtils.getInputStream();	
                if (a2 != null) {	
                    a.putAll(j.ALLATORIxDEMO((InputStream)a2));	
                    if ("true".equals(a.get("devlop"))) {	
                        throw new Exception("您当前的版本为开发版");	
                    }	
                    break block7;	
                }	
                throw new Exception("您当前的版本为社区版");	
            }	
            catch (Exception a4) {	
                a.put("message", a4.getMessage());	
            }	
        }	
        HashMap<String, String> hashMap = a;	
        hashMap.putAll(j.ALLATORIxDEMO(hashMap));	
        hashMap.put("module", ",flow,weixin,");	
        a.put("openModule", new StringBuilder().insert(0, ",").append((String)a.get("openModule")).append(",").toString());	
        String[] arrstring = new String[3];	
        arrstring[0] = "1";	
        arrstring[1] = "2";	
        arrstring[2] = "9";	
        if (j.ALLATORIxDEMO((String)a.get("type"), arrstring)) {	
            a.put("fnJob", "true");	
            a.put("fnCas", "true");	
        }	
        String[] arrstring2 = new String[2];	
        arrstring2[0] = "2";	
        arrstring2[1] = "9";	
        if (j.ALLATORIxDEMO((String)a.get("type"), arrstring2)) {	
            a.put("fnCluster", "true");	
            a.put("fnSaas", "true");	
            a.put("fnI18n", "true");	
            a.put("fnMsg", "true");	
        }	
        h.putAll(a);	
        a2 = new StringBuilder();	
        ((StringBuilder)a2).append("\r\n    " + (String)a.get("message") + "\r\n");	
        ((StringBuilder)a2).append(new StringBuilder().insert(0, "\r\n    机器码是：").append(c.length > 0 ? c[0] : "").append("").toString());	
        ((StringBuilder)a2).append(new StringBuilder().insert(0, "\r\n    产品名称：").append(j.a).append("").toString());	
        ((StringBuilder)a2).append(new StringBuilder().insert(0, "\r\n    公司名称：").append(ALLATORIxDEMO).append("").toString());	
        ((StringBuilder)a2).append("\r\n");	
        h.ALLATORIxDEMO("loadMessage", ((StringBuilder)a2).toString());	
        com.jeesite.common.mybatis.l.e.h.ALLATORIxDEMO();	
        return h;	
    }	
	
    private static /* synthetic */ boolean e(CharSequence seq, CharSequence searchSeq) {	
        if (seq == null || searchSeq == null) {	
            return false;	
        }	
        return j.ALLATORIxDEMO(seq, searchSeq, 0) >= 0;	
    }	
	
    public static final boolean ALLATORIxDEMO(HttpServletRequest request) {	
        if (request == null) {	
            request = ServletUtils.getRequest();	
        }	
        if (request != null) {	
            HttpServletRequest httpServletRequest = request;	
            String a = httpServletRequest.getContextPath();	
            String a2 = httpServletRequest.getRequestURL().toString();	
            com.jeesite.common.mybatis.l.e.h.e(a2, a);	
            String[] arrstring = new String[2];	
            arrstring[0] = a + "/licence";	
            arrstring[1] = new StringBuilder().insert(0, a).append("/licence/save").toString();	
            String[] a3 = arrstring;	
            if (j.ALLATORIxDEMO(request.getRequestURI(), a3)) {	
                return true;	
            }	
            if ("9".equals(h.get("type"))) {	
                if (ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L > 1L) {	
                    return false;	
                }	
                CharSequence[] arrcharSequence = new CharSequence[5];	
                arrcharSequence[0] = "://127.0.0.1";	
                arrcharSequence[1] = "://localhost";	
                arrcharSequence[2] = "://10.";	
                arrcharSequence[3] = "://172.";	
                arrcharSequence[4] = "://192.";	
                if (!j.ALLATORIxDEMO((CharSequence)a2, arrcharSequence)) {	
                    return false;	
                }	
            }	
            String a4 = (String)h.get("domainOrIp");	
            if (!(j.ALLATORIxDEMO((CharSequence)h.get("type"), (CharSequence)"0") || j.ALLATORIxDEMO((CharSequence)a4) || j.ALLATORIxDEMO((CharSequence)a2, (CharSequence[])(a4 = new StringBuilder().insert(0, "://127.0.0.1,://localhost,://10.,://172.,://192.,").append(a4).toString()).split(",")))) {	
                return false;	
            }	
        }	
        return true;	
    }	
	
    private static /* synthetic */ String e(String input) {	
        try {	
            return new String(h.ALLATORIxDEMO(input), "UTF-8");	
        }	
        catch (UnsupportedEncodingException a) {	
            return "";	
        }	
    }	
	
    /*	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    public static final Map<String, String> ALLATORIxDEMO(Map<String, String> info) {	
        HashMap<String, String> a;	
        block17 : {	
            a = new HashMap<String, String>();	
            String a2 = "0";	
            String a3 = "社区版";	
            try {	
                if (!j.ALLATORIxDEMO((CharSequence)info.get("message"))) {	
                    throw new Exception(info.get("mesage"));	
                }	
                String a4 = info.get("code");	
                if (!j.ALLATORIxDEMO((CharSequence)a4) && j.ALLATORIxDEMO(a4.split(","), c) && !j.ALLATORIxDEMO((CharSequence)j.a) && !j.ALLATORIxDEMO((CharSequence)ALLATORIxDEMO) && j.ALLATORIxDEMO(j.a).equals(j.ALLATORIxDEMO(info.get("productName"))) && j.ALLATORIxDEMO(ALLATORIxDEMO).equals(j.ALLATORIxDEMO(info.get("companyName")))) {	
                    Map<String, String> map;	
                    a2 = info.get("type");	
                    a.put("type", a2);	
                    if ("1".equals(a2)) {	
                        a3 = "个人版";	
                        map = info;	
                    } else {	
                        if ("2".equals(a2)) {	
                            a3 = "专业版";	
                        }	
                        map = info;	
                    }	
                    String a5 = map.get("expireDate");	
                    if (!"-1".equals(a5)) {	
                        HashMap<String, String> hashMap;	
                        Date a6 = new SimpleDateFormat("yyyy-MM-dd").parse(a5);	
                        long a7 = (a6.getTime() - System.currentTimeMillis()) / 3600000L / 24L;	
                        if (a7 <= 0L) {	
                            throw new Exception(new StringBuilder().insert(0, "您的").append(a3).append("许可，于").append(new SimpleDateFormat("yyyy年MM月dd日").format(a6)).append("已到期").toString());	
                        }	
                        if (a7 <= 7L) {	
                            HashMap<String, String> hashMap2 = a;	
                            hashMap = hashMap2;	
                            hashMap2.put("mesage", new StringBuilder().insert(0, "您当前的版本为").append(a3).append("，许可即将到期，还剩余最后").append(a7).append("天。").toString());	
                        } else {	
                            HashMap<String, String> hashMap3 = a;	
                            if (a7 <= 60L) {	
                                hashMap3.put("mesage", new StringBuilder().insert(0, "您当前的版本为").append(a3).append("，许可即将到期，还剩余").append(a7).append("天。").toString());	
                                hashMap = a;	
                            } else {	
                                hashMap3.put("mesage", new StringBuilder().insert(0, "您当前的版本为").append(a3).append("，许可到期时间为：").append(new SimpleDateFormat("yyyy年MM月dd日").format(a6)).append("。").toString());	
                                hashMap = a;	
                            }	
                        }	
                        hashMap.put("title", new StringBuilder().insert(0, a3).append("（剩余").append(a7).append("天）").toString());	
                    } else {	
                        a.put("mesage", new StringBuilder().insert(0, "您当前的版本为").append(a3).append("，非常感谢您对我们产品的认可与支持！").toString());	
                        a.put("title", a3);	
                    }	
                    break block17;	
                }	
                throw new Exception(new StringBuilder().insert(0, "您当前的版本为").append(a3).toString());	
            }	
            catch (Exception a8) {	
                a.put("message", new StringBuilder().insert(0, a8.getMessage()).append("，官方网站：http://jeesite.com").toString());	
                a.put("title", a3);	
                a.put("type", a2);	
            }	
        }	
        if ("true".equals(info.get("devlop"))) {	
            a.put("title", "开发版");	
            a.put("type", "9");	
        }	
        return a;	
    }	
	
    private static /* synthetic */ PublicKey ALLATORIxDEMO(String publicKey) throws Exception {	
        byte[] a = j.e(publicKey);	
        X509EncodedKeySpec a2 = new X509EncodedKeySpec(a);	
        return KeyFactory.getInstance("RSA").generatePublic(a2);	
    }	
	
    private static /* synthetic */ int ALLATORIxDEMO(CharSequence cs, CharSequence searchChar, int start) {	
        return cs.toString().indexOf(searchChar.toString(), start);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    private static /* synthetic */ String e(String contentBase64, String publicKeyBase64) throws Exception {	
        int n;	
        PublicKey a = j.ALLATORIxDEMO(publicKeyBase64);	
        Cipher a2 = Cipher.getInstance("RSA");	
        PublicKey publicKey = a;	
        a2.init(2, publicKey);	
        int a3 = ((RSAPublicKey)publicKey).getModulus().bitLength() / 8;	
        byte[][] a4 = j.ALLATORIxDEMO(j.ALLATORIxDEMO(j.e(contentBase64)), a3);	
        StringBuffer a5 = new StringBuffer();	
        byte[][] arrby = a4;	
        int n2 = arrby.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            void a6;	
            byte[] arrby2 = arrby[n];	
            a5.append(new String(a2.doFinal((byte[])a6)));	
            n3 = ++n;	
        }	
        return j.e(a5.toString());	
    }	
	
    private static /* synthetic */ boolean ALLATORIxDEMO(CharSequence cs1, CharSequence cs2) {	
        if (cs1 == cs2) {	
            return true;	
        }	
        if (cs1 == null || cs2 == null) {	
            return false;	
        }	
        if (cs1.length() != cs2.length()) {	
            return false;	
        }	
        if (cs1 instanceof String && cs2 instanceof String) {	
            return cs1.equals(cs2);	
        }	
        return j.ALLATORIxDEMO(cs1, (boolean)0, 0, cs2, 0, cs1.length());	
    }	
	
    /*	
     * WARNING - void declaration	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    public static final Map<String, String> ALLATORIxDEMO(InputStream inputStream) {	
        byte[] a;	
        byte[] a2 = new byte[]{};	
        try {	
            String a3;	
            int n;	
            Iterator iterator;	
            void a4;	
            void a5;	
            a2 = IOUtils.toByteArray(inputStream);	
            String[] a6 = FileWatcher.ALLATORIxDEMO (" h h\u5939\u720d\u4f7e\u592b\uff12\u80ab\u5438\u750f\u4e34\u8002\u7ce5\u65ef\u5f11\uff5a\u5416\u4f0aO\u0007\uff04a&g/d(`+\uff5a\u6689\u53a1\uff04\u001aw5{8}3+d.j\"j\"6)\u001dsyU\fub(%N\")3+.s8(\fW\u0017#k~\u001bW\u001f]2O\u001f\\\u0017Z\u0017P\u0014y=o>u?Yoif\\\u0017O\u0013X\u0017_\u0005]\u0017rni1y\u001c|\u0017y\u0013_\u0017q\u0011\\\u0017S\u001brcp\u0019\\\u0000[}O\u001cm\u0018]}W\"wel.y\u001b|\u0002KnP>n\"K\u0004t.m:R\u0010].so*#X\u0003p\u001dK\u0007/='\u001dK1x\u0001Vd5\u0004r\u001b&\u0012_,.\u001eM\u0012T\u000e'b5\u0006y%y'ieW\u0019)$,#M\u001fF\u0007}'N\u0012H\u0019f\u0011Jyo\u0003\u0005S2m5\\\u000fk\u0015*d}\u0019z?\\cXb}\u0018Y01?N2O;(\u001cD\u001b\\\u0014J<]\u0011L\"R3rym\u0015|nr2w\"o9d`S>T0L\u0017y\u001b\\\u0017_\u0013]1G\u00141;F\u0005T`x\u001db*\u001d/\u0017u\u0010T!o1n\u0006],[\u0018S1I3I1L\u0017)/t\u0019x>]b[\u0012K2\u0004p\u0002f\u001dU5d2{oK\u0017Z\u0001Z$|?z\u0006V\u0000W5V\u0006M\u0002,\u0005V\u0005\\\u0015w2P1{\u001019f}S\u0004)di\u0007L\u0004I:)>t;_\u0003V\u0004Y8jnW\u0015w\u0015K&I doG\u0006J:H\u0019L\u0017T!udi=\\ -,\\ {\u0003z\u0004,9R\u0010{\u0018Oa_\u0004h\u000er\u0012R\u0007Igg4_\u0013O\u001c\\\u0017N3T/]fWy,\u001cY}\\:n\u0003d\u0003y<U:Q:O&rc)\u001dx\u001dz\u001fXgm\u001c'1_#N\u001dubo\u001e|\u0000x\"}&(\u0006o\u0007v\u0001[\u0014j,l\u0015m4P:q0Ya),w\u0005U\"*\u0007L0Kn]\u0007O\u0012Tf\fUdv\u0019.\u0003o\u0007Z\u0006h?_'K\u000e+\f]eMb]`]\u0003J\u0014Q?N/R!5;R:D\u0014y15<h/S#S!p.Uysox\u001d+>x\fy1O\u001f+\u0003{ev\u0000W4u%j2T\u0012x\u0017u\u0017p:s>s\u0013,2S\u000e.n.\u0019M,k2M%n\"W\u0015N4w7.bH\u0010'ew\u001bh4G\u0005+gW7Q1+ Y%k,Qd{1[\"|\u0004*5H5+d_:&\f*\u001cs}I.T\u0001}8n8]\fh\u0017u\u0017'\u0004dev s\u0018)\u0006v>.$'%Q\u000ez2K\u0005N;maS$M\u000fS&/\u001ev,q\ff,d21n/0h0m\u0002o\u0015F\f|9P8.\u0011)#Q\u000e.\u0011I |\u0003o\u001dX='\u001bS1y?l<D1l\u0017u\u0014*<+\u0019]f)9he\u0004Mo1>i\u001ey\u0005tnw\u001d_\u0002v%_\u0012Q/y\u0012L?x\u000e{\u001aJ\u001e_\u0017s\"V\u0014V2Mnr\u0015o\u001fh,p\u0017S\u0017S\u001eL7+\u0018, (>,?+oR\u0013[dG\u001aQor6N.F>i\u0005g\u000fU\u001aV\u0007r;}.+oX:(\u0007#k~\u001bW\u0011x\u001b_fY\u0015M'Y\u0005W4-\u0012O\u0013\\\u0017O\u0003_\u0017*\u0011P\u0017Z\u0015\\?O\u001d\\1O\u0015{\u0006G#_y\\fP\u0006&\u0004Yeond\u000et;u\u000fl\u0017d5zcW\u001bU4Q=Z4p!\\8+2n!pg]&.'\\\u0005n\u0010U5w\u0003j Feh`5\u001eM9O\u0001'\u0003[\u0000d v\u0019pfs1m\u0002Y\u0017l!zcz0H7Lg/\u0017|\u0006O\u0013x.T<p\u001f(b'\u001al<S;ibP95\u0017Q}rbR>N\u0001kn-\u001bub{\u0004*\u0010F\u001bI%* W>,:s\u0001R\u0002S\u0015gbs\u0011U8z%J,;qdO\u001fZ\u0017O\u0017\\").split("`");	
            a = j.e(a6[3]);	
            byte[] a7 = j.e(a6[1]);	
            String a8 = new String(j.ALLATORIxDEMO(a2, a, a7), "UTF-8");	
            a8 = j.ALLATORIxDEMO(a8, "\n\n", "\n\n");	
            a8 = j.ALLATORIxDEMO(a8, "\n", "", -1, false);	
            a8 = j.ALLATORIxDEMO(j.e(a8, a6[4]), a6[2]);	
            int a9 = 96;	
            int a10 = 96 + 32;	
            String string = a8;	
            byte[] a11 = j.ALLATORIxDEMO(string.substring(a9, a10));	
            a9 = a10;	
            byte[] a12 = j.ALLATORIxDEMO(string.substring(a9, a10 += 32));	
            a9 = a10 + 160;	
            a10 = string.length() - 64 - a6[0].length();	
            byte[] a13 = j.ALLATORIxDEMO(string.substring(a9, a10));	
            String a14 = new String(j.ALLATORIxDEMO(a13, a11, a12), "UTF-8");	
            HashMap hashMap = new HashMap();	
            String string2 = a14;	
            a14 = string2.substring(2, string2.length() - 2);	
            Object object = a14.split("","");	
            int n2 = ((String[])object).length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                a3 = object[n];	
                String[] a15 = a3.split("":"");	
                if ("updateCode".equals(a15[0])) {	
                    a8 = a15.length == 2 ? a15[1] : "";	
                } else {	
                    a4.put(a15[0], a15.length == 2 ? a15[1] : "");	
                }	
                n3 = ++n;	
            }	
            object = Arrays.asList(a4.values().toArray(new String[a4.values().size()]));	
            void v3 = a5;	
            void v4 = a5;	
            Collections.sort(v3, new H());	
            String a16 = "";	
            Iterator iterator2 = iterator = v3.iterator();	
            while (iterator2.hasNext()) {	
                a3 = (String)iterator.next();	
                a16 = new StringBuilder().insert(0, a16).append(a3).toString();	
                iterator2 = iterator;	
            }	
            if (!j.ALLATORIxDEMO(a16, 88).equals(a8)) {	
                return new HashMap<String, String>();	
            }	
            return a4;	
        }	
        catch (Exception a17) {	
            if (a2.length == 3232) {	
                a = new byte[]();	
                a.put("devlop", "true");	
                return a;	
            }	
            return new HashMap<String, String>();	
        }	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    private static /* synthetic */ boolean ALLATORIxDEMO(CharSequence cs, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length) {	
        block5 : {	
            char a;	
            char a2;	
            if (cs instanceof String && substring instanceof String) {	
                return ((String)cs).regionMatches(ignoreCase, thisStart, (String)substring, start, length);	
            }	
            int a3 = thisStart;	
            int a4 = start;	
            int n = length;	
            int a5 = cs.length() - thisStart;	
            int a6 = substring.length() - start;	
            if (thisStart < 0 || start < 0 || length < 0) {	
                return false;	
            }	
            if (a5 < length || a6 < length) {	
                return false;	
            }	
            do {	
                void a7;	
                void v0 = a7;	
                do {	
                    --a7;	
                    if (v0 <= 0) break block5;	
                    a2 = cs.charAt(a3);	
                    ++a3;	
                    a = substring.charAt(a4);	
                    ++a4;	
                    if (a2 != a) break;	
                    v0 = a7;	
                } while (true);	
                if (ignoreCase) continue;	
                return false;	
            } while (Character.toUpperCase(a2) == Character.toUpperCase(a) || Character.toLowerCase(a2) == Character.toLowerCase(a));	
            return false;	
        }	
        return true;	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(String str) {	
        if (str == null) {	
            return "";	
        }	
        return str.replaceAll("^[\s|　| ]*|[\s|　| ]*$", "");	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(byte[] input) {	
        int a;	
        String a2 = "";	
        StringBuilder a3 = new StringBuilder("");	
        int n = a = 0;	
        while (n < input.length) {	
            a2 = Integer.toHexString(input[a] & 255);	
            a3.append(a2.length() == 1 ? new StringBuilder().insert(0, "0").append(a2).toString() : a2);	
            n = ++a;	
        }	
        return a3.toString().trim();	
    }	
	
    private static /* synthetic */ byte[] e(String input) {	
        return h.ALLATORIxDEMO(input);	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    private static /* synthetic */ byte[] ALLATORIxDEMO(byte[] input, byte[] key, byte[] iv) {	
        try {	
            void a;	
            SecretKeySpec a2 = new SecretKeySpec(key, "AES");	
            IvParameterSpec a3 = new IvParameterSpec(iv);	
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");	
            void v0 = a;	
            v0.init(2, (Key)a2, a3);	
            return v0.doFinal(input);	
        }	
        catch (GeneralSecurityException a) {	
            throw new RuntimeException(a);	
        }	
    }	
	
    private static /* synthetic */ byte[][] ALLATORIxDEMO(byte[] bytes, int splitLength) {	
        int a;	
        int a2;	
        byte[][] a3 = new byte[bytes.length % splitLength != 0 ? (a2 = bytes.length / splitLength + 1) : (a2 = bytes.length / splitLength)][];	
        int n = a = 0;	
        while (n < a2) {	
            byte[][] arrarrby;	
            byte[] a4;	
            if (a == a2 - 1 && bytes.length % splitLength != 0) {	
                a4 = new byte[bytes.length % splitLength];	
                System.arraycopy(bytes, a * splitLength, a4, 0, bytes.length % splitLength);	
                arrarrby = a3;	
            } else {	
                a4 = new byte[splitLength];	
                arrarrby = a3;	
                System.arraycopy(bytes, a * splitLength, a4, 0, splitLength);	
            }	
            arrarrby[a++] = a4;	
            n = a;	
        }	
        return a3;	
    }	
	
    /*	
     * WARNING - void declaration	
     */	
    private static /* synthetic */ String ALLATORIxDEMO(String contentBase64, String privateKeyBase64) throws Exception {	
        int n;	
        PrivateKey a = j.ALLATORIxDEMO(privateKeyBase64);	
        Cipher a2 = Cipher.getInstance("RSA");	
        PrivateKey privateKey = a;	
        a2.init(2, privateKey);	
        int a3 = ((RSAPrivateKey)privateKey).getModulus().bitLength() / 8;	
        byte[][] a4 = j.ALLATORIxDEMO(j.ALLATORIxDEMO(j.e(contentBase64)), a3);	
        StringBuffer a5 = new StringBuffer();	
        byte[][] arrby = a4;	
        int n2 = arrby.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            void a6;	
            byte[] arrby2 = arrby[n];	
            a5.append(new String(a2.doFinal((byte[])a6)));	
            n3 = ++n;	
        }	
        return j.e(a5.toString());	
    }	
	
    private static /* synthetic */ boolean ALLATORIxDEMO(String[] str, String[] strs) {	
        if (str != null && strs != null) {	
            int n;	
            String[] arrstring = str;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                int n4;	
                String a = arrstring[n];	
                String[] arrstring2 = strs;	
                int n5 = arrstring2.length;	
                int n6 = n4 = 0;	
                while (n6 < n5) {	
                    String a2 = arrstring2[n4];	
                    if (j.ALLATORIxDEMO(a).equals(j.ALLATORIxDEMO(a2))) {	
                        return true;	
                    }	
                    n6 = ++n4;	
                }	
                n3 = ++n;	
            }	
        }	
        return false;	
    }	
	
    private static /* synthetic */ boolean ALLATORIxDEMO(String str, String ... strs) {	
        if (str != null && strs != null) {	
            int n;	
            String[] arrstring = strs;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a = arrstring[n];	
                if (j.ALLATORIxDEMO(str).equals(j.ALLATORIxDEMO(a))) {	
                    return true;	
                }	
                n3 = ++n;	
            }	
        }	
        return false;	
    }	
	
    private static /* synthetic */ PrivateKey ALLATORIxDEMO(String privateKey) throws Exception {	
        byte[] a = j.e(privateKey);	
        PKCS8EncodedKeySpec a2 = new PKCS8EncodedKeySpec(a);	
        return KeyFactory.getInstance("RSA").generatePrivate(a2);	
    }	
	
    /*	
     * Exception decompiling	
     */	
    static {	
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 6[TRYBLOCK]	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:432)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:607)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:696)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:184)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:129)	
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:96)	
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:397)	
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:890)	
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:792)	
        // org.benf.cfr.reader.Driver.doJar(Driver.java:128)	
        // org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:63)	
        // org.benf.cfr.reader.Main.main(Main.java:48)	
        throw new IllegalStateException("Decompilation failed");	
    }	
	
    private static final /* synthetic */ String ALLATORIxDEMO(String input, int iterations) {	
        byte[] a;	
        try {	
            int a2;	
            MessageDigest a3 = MessageDigest.getInstance("MD5");	
            a = a3.digest(input.getBytes("UTF-8"));	
            int n = a2 = 1;	
            while (n < iterations) {	
                MessageDigest messageDigest = a3;	
                messageDigest.reset();	
                a = messageDigest.digest(a);	
                n = ++a2;	
            }	
        }	
        catch (Exception a4) {	
            throw new RuntimeException(a4);	
        }	
        return j.ALLATORIxDEMO(a);	
    }	
	
    private static /* synthetic */ boolean ALLATORIxDEMO(CharSequence cs, CharSequence ... searchCharSequences) {	
        int n;	
        if (j.ALLATORIxDEMO(cs) || searchCharSequences == null || searchCharSequences.length <= 0) {	
            return false;	
        }	
        CharSequence[] arrcharSequence = searchCharSequences;	
        int n2 = arrcharSequence.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            CharSequence a = arrcharSequence[n];	
            if (j.e(cs, (CharSequence)j.ALLATORIxDEMO(a.toString()))) {	
                return true;	
            }	
            n3 = ++n;	
        }	
        return false;	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(String str, String open, String close) {	
        int a;	
        if (str == null || open == null || close == null) {	
            return null;	
        }	
        int a2 = str.indexOf(open);	
        if (a2 != -1 && (a = str.indexOf(close, a2 + open.length())) != -1) {	
            return str.substring(a2 + open.length(), a);	
        }	
        return null;	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(String text, String searchString, String replacement, int max, boolean ignoreCase) {	
        StringBuilder a;	
        int a2;	
        StringBuilder stringBuilder;	
        block5 : {	
            int a3;	
            if (j.ALLATORIxDEMO((CharSequence)text) || j.ALLATORIxDEMO((CharSequence)searchString) || replacement == null || max == 0) {	
                return text;	
            }	
            String a4 = text;	
            if (ignoreCase) {	
                a4 = text.toLowerCase();	
                searchString = searchString.toLowerCase();	
            }	
            if ((a3 = a4.indexOf(searchString, a2 = 0)) == -1) {	
                return text;	
            }	
            int a5 = searchString.length();	
            int a6 = replacement.length() - a5;	
            int n = a6 = a6 < 0 ? 0 : a6;	
            a = new StringBuilder(text.length() + (a6 *= max < 0 ? 16 : (max > 64 ? 64 : max)));	
            int n2 = a3;	
            while (n2 != -1) {	
                a.append(text, a2, a3).append(replacement);	
                a2 = a3 + a5;	
                if (--max == 0) {	
                    stringBuilder = a;	
                    break block5;	
                }	
                n2 = a4.indexOf(searchString, a2);	
            }	
            stringBuilder = a;	
        }	
        stringBuilder.append(text, a2, text.length());	
        return a.toString();	
    }	
	
    private static /* synthetic */ byte[] ALLATORIxDEMO(String input) {	
        int a;	
        int a2 = 0;	
        int a3 = 0;	
        int a4 = input.length() / 2;	
        byte[] a5 = new byte[a4];	
        int n = a = 0;	
        while (n < a4) {	
            a2 = a * 2 + 1;	
            a3 = a2 + 1;	
            int a6 = Integer.decode(new StringBuilder().insert(0, "0x").append(input.substring(a * 2, a2)).append(input.substring(a2, a3)).toString());	
            a5[a++] = (byte)a6;	
            n = a;	
        }	
        return a5;	
    }	
	
    private static /* synthetic */ boolean ALLATORIxDEMO(CharSequence cs) {	
        int a;	
        int a2;	
        if (cs == null || (a2 = cs.length()) == 0) {	
            return true;	
        }	
        int n = a = 0;	
        while (n < a2) {	
            if (!Character.isWhitespace(cs.charAt(a))) {	
                return false;	
            }	
            n = ++a;	
        }	
        return true;	
    }	
}	
	
