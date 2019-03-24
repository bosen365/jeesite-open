/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.mybatis.e;	
	
import com.jeesite.common.mybatis.e.H;	
import com.jeesite.common.mybatis.e.h;	
import com.jeesite.common.mybatis.e.m;	
import com.jeesite.common.mybatis.l.w.I;	
import com.jeesite.common.mybatis.l.w.l;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.shiro.session.SessionDAO;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import com.jeesite.modules.sys.utils.CorpUtils;	
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
import org.hyperic.sigar.NfsClientV3;	
	
/*	
 * Duplicate member names - consider using --renamedupmembers true	
 */	
public final class j {	
    private static final m a;	
    private static final String l;	
    private static SessionDAO h;	
    private static Date J;	
    private static final String[] c;	
    private static final String ALLATORIxDEMO;	
	
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
	
    public static final int ALLATORIxDEMO() {	
        Iterator<Session> iterator;	
        if (h == null) {	
            h = SpringUtils.getBean(SessionDAO.class);	
        }	
        HashSet<String> a = new HashSet<String>();	
        I.ALLATORIxDEMO = false;	
        Iterator<Session> iterator2 = iterator = h.getActiveSessions().iterator();	
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
            if ("9".equals(j.a.get("type"))) {	
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
            String a4 = (String)j.a.get("domainOrIp");	
            if (!(j.e((CharSequence)j.a.get("type"), (CharSequence)"0") || j.ALLATORIxDEMO((CharSequence)a4) || j.ALLATORIxDEMO((CharSequence)a2, (CharSequence[])(a4 = new StringBuilder().insert(0, "://127.0.0.1,://localhost,://10.,://172.,://192.,").append(a4).toString()).split(",")))) {	
                return false;	
            }	
        }	
        return true;	
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
	
    private static /* synthetic */ byte[] e(String input) {	
        return h.ALLATORIxDEMO(input);	
    }	
	
    private static /* synthetic */ int ALLATORIxDEMO(CharSequence cs, CharSequence searchChar, int start) {	
        return cs.toString().indexOf(searchChar.toString(), start);	
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
            if (j.ALLATORIxDEMO(cs, (CharSequence)j.ALLATORIxDEMO(a.toString()))) {	
                return true;	
            }	
            n3 = ++n;	
        }	
        return false;	
    }	
	
    private static /* synthetic */ PublicKey ALLATORIxDEMO(String publicKey) throws Exception {	
        byte[] a = j.e(publicKey);	
        X509EncodedKeySpec a2 = new X509EncodedKeySpec(a);	
        return KeyFactory.getInstance("RSA").generatePublic(a2);	
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
	
    private static /* synthetic */ String e(String input) {	
        try {	
            return new String(h.ALLATORIxDEMO(input), "UTF-8");	
        }	
        catch (UnsupportedEncodingException a) {	
            return "";	
        }	
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
	
    private static /* synthetic */ String ALLATORIxDEMO(String str) {	
        if (str == null) {	
            return "";	
        }	
        return str.replaceAll("^[\s|　| ]*|[\s|　| ]*$", "");	
    }	
	
    private static /* synthetic */ PrivateKey ALLATORIxDEMO(String privateKey) throws Exception {	
        byte[] a = j.e(privateKey);	
        PKCS8EncodedKeySpec a2 = new PKCS8EncodedKeySpec(a);	
        return KeyFactory.getInstance("RSA").generatePrivate(a2);	
    }	
	
    private static /* synthetic */ boolean e(CharSequence cs1, CharSequence cs2) {	
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
	
    private static /* synthetic */ boolean ALLATORIxDEMO(CharSequence seq, CharSequence searchSeq) {	
        if (seq == null || searchSeq == null) {	
            return false;	
        }	
        return j.ALLATORIxDEMO(seq, searchSeq, 0) >= 0;	
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
            String[] a6 = CorpUtils.ALLATORIxDEMO ("))\u5930\u721a\u4f77\u593c\uff1b\u80bc\u5431\u7518\u4e3d\u8015\u7cec\u65f8\u5f18\uff4d\u541f\u4f1dF\u0010\uff0dv/p&s!w\"\uff4d\u6680\u53b6\uff0d\r~\"r/t$\"s'}+}+! \nzn\\\u001b|u!2G5 $\"9z/!\u001b^\u0000*|w\f^\bT%F\bU\u0000S\u0000Y\u0003p*f)|(Px`qU\u0000F\u0004Q\u0000V\u0012T\u0000{y`&p\u000bu\u0000p\u0004V\u0000x\u0006U\u0000Z\f{ty\u000eU\u0017RjF\u000bd\u000fTj^5~re9p\fu\u0015ByY)g5B\u0013}9d-[\u0007T9zx#4Q\u0014y\nB\u0010&*.\nB&q\u0016_s<\u0013{\f/\u0005V;'\tD\u0005]\u0019.u<\u0011p2p0`r^\u000e 3%4D\bO\u0010t0G\u0005A\u000eo\u0006Cnf\u0014v\u0012Z%d\"U\u0018b\u0002#st\u000es(UtQut\u000fP'8(G%F,!\u000bM\fU\u0003C+T\u0006E5[${nd\u0002uy{%~5f.mwZ)]'E\u0000p\fU\u0000V\u0004T&N\u00038,O\u0012]wq\nvu#\n&\u0000|\u0007]6f&g\u0011T;R\u000fZ&@$@&E\u0000 8}\u000eq)TuR\u0005B%v\u0013y\u0015o\n\\\"m%rxB\u0000S\u0016S3u(s\u0011_\u0017^\"_\u0011D\u0015%\u0012_\u0012U\u0002~%Y&r\u00078.ojZ\u0013 s`\u0010E\u0013@- )},V\u0014_\u0013P/cy^\u0002~\u0002B1@7mxN\u0011C-A\u000eE\u0000]6|s`*U7$;U7r\u0014s\u0013%.[\u0007r\u000fFvV\u0013a\u0019{\u0005[\u0010@pn#V\u0004F\u000bU\u0000G$]8Tq^n%\u000bPjU-g\u0014m\u0014p+\\-X-F1{t \nq\ns\bQpd\u000b.&V4G\n|uf\tu\u0017q5t1!\u0011f\u0010\u0016R\u0003c;e\u0002d#Y-x'Pv ;~\u0012\\5#\u0010E'ByT\u0010F\u0005]qv\u001b\\s\u000e'\u0014f\u0010S\u0011a(V0B\u0019\"\u001bTrDuTwT\u0014C\u0003X(G8[6<,[-M\u0003p&<+a8Z4Z6y9\\nzxq\n\")q\u001bp&F\b\"\u0014rr\u0017^#|2c%]\u0005q\u0000|\u0000y-z)z\u0004%%Z\u0019'y'\u000eD;b%D2g5^\u0002G#~ 'uA\u0007.r~\fa#N\u0012\"p^ X&\"7P2b;Xsr&R5u\u0013#\"A\"\"sV-/\u001b#\u000bzj@9]\u0016t/g/T\u001ba\u0000|\u0000.\u0013mr7z\u000f \u0011)'3.2X\u0019s%B\u0012G,dvZ3D\u0018Z1&\t;x\u001bo;m%8y&'a'd\u0015f\u0002O\u001bu.Y/'\u0006 4X\u0019'\u0006@7u\u0014f\nQ*.\fZ&p(e+M&e\u0000|\u0003#+\"\u000eTq .arv\u0013Dx8)`\tp\u0012}y~\nV\u00152V\u0005X8p\u0005E(q\u0019r\rC\tV\u0000z5_\u0003_%Dy{\u0002f\ba;y\u0000Z\u0000Z\tE \"\u000f%7!)%(\"x[\u0004RsN\rXx{!G9O)`\u0012n\u0018\\\r_\u0010{,t9\"xQ-!\u0010*|w\f^\u0006q\fVqP\u0002D0P\u0012^#$\u0005F\u0004U\u0000F\u0014V\u0000#\u0006Y\u0000S\u0002U(F\nU&F\u0002r\u0011N4VnUqY\u0011/\u0013Prfym\u0019},|\u0018e\u0000m\"st^\f\\#X*S#y6U/\"%g6ypT1'0U\u0012g\u0007\\\"~\u0014c7Oraw<\tD.F\u0016.\u0014R\u0017m7\u000eyqz&d\u0015P\u0000e6sts'A Ep&\u0000u\u0011F\u0004q9]+y\b!u.\re+Z,`uY.<\u0000Xj{u[)G\u0016by$\f|ur\u0013#\u0007O\f@2#7^)%-z\u0016[\u0015Z\u0002nuz\u0006\\/s2C;v,xsF\bS\u0000F\u0000U").split("`");	
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
	
    public static final m ALLATORIxDEMO() {	
        HashMap<String, String> a;	
        Object a2;	
        block7 : {	
            long a3 = System.currentTimeMillis() - J.getTime();	
            if (j.a.ALLATORIxDEMO() && a3 / 86400000L <= 0L) {	
                return j.a;	
            }	
            J = new Date();	
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
        hashMap.put("modules", ",flow,weixin,");	
        a.put("openModules", new StringBuilder().insert(0, ",").append((String)a.get("openModules")).append(",").toString());	
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
        j.a.putAll(a);	
        a2 = new StringBuilder();	
        ((StringBuilder)a2).append("\r\n    " + (String)a.get("message") + "\r\n");	
        ((StringBuilder)a2).append(new StringBuilder().insert(0, "\r\n    机器码是：").append(c.length > 0 ? c[0] : "").append("").toString());	
        ((StringBuilder)a2).append(new StringBuilder().insert(0, "\r\n    产品名称：").append(l).append("").toString());	
        ((StringBuilder)a2).append(new StringBuilder().insert(0, "\r\n    公司名称：").append(ALLATORIxDEMO).append("").toString());	
        ((StringBuilder)a2).append("\r\n");	
        j.a.ALLATORIxDEMO("loadMessage", ((StringBuilder)a2).toString());	
        com.jeesite.common.mybatis.l.e.h.ALLATORIxDEMO();	
        return j.a;	
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
                    throw new Exception(info.get("message"));	
                }	
                String a4 = info.get("code");	
                if (!j.ALLATORIxDEMO((CharSequence)a4) && j.ALLATORIxDEMO(a4.split(","), c) && !j.ALLATORIxDEMO((CharSequence)l) && !j.ALLATORIxDEMO((CharSequence)ALLATORIxDEMO) && j.ALLATORIxDEMO(l).equals(j.ALLATORIxDEMO(info.get("productName"))) && j.ALLATORIxDEMO(ALLATORIxDEMO).equals(j.ALLATORIxDEMO(info.get("companyName")))) {	
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
                            hashMap2.put("message", new StringBuilder().insert(0, "您当前的版本为").append(a3).append("，许可即将到期，还剩余最后").append(a7).append("天。").toString());	
                        } else {	
                            HashMap<String, String> hashMap3 = a;	
                            if (a7 <= 60L) {	
                                hashMap3.put("message", new StringBuilder().insert(0, "您当前的版本为").append(a3).append("，许可即将到期，还剩余").append(a7).append("天。").toString());	
                                hashMap = a;	
                            } else {	
                                hashMap3.put("message", new StringBuilder().insert(0, "您当前的版本为").append(a3).append("，许可到期时间为：").append(new SimpleDateFormat("yyyy年MM月dd日").format(a6)).append("。").toString());	
                                hashMap = a;	
                            }	
                        }	
                        hashMap.put("title", new StringBuilder().insert(0, a3).append("（剩余").append(a7).append("天）").toString());	
                    } else {	
                        a.put("message", new StringBuilder().insert(0, "您当前的版本为").append(a3).append("，非常感谢您对我们产品的认可与支持！").toString());	
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
}	
	
