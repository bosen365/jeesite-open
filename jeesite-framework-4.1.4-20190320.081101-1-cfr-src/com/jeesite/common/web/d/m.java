/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.web.http.ServletUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  org.apache.commons.io.IOUtils	
 *  org.apache.shiro.session.Session	
 *  org.apache.shiro.subject.PrincipalCollection	
 *  org.apache.shiro.subject.support.DefaultSubjectContext	
 */	
package com.jeesite.common.web.d;	
	
import com.jeesite.common.mybatis.d.b.D;	
import com.jeesite.common.mybatis.d.b.L;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.shiro.session.SessionDAO;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.web.d.C;	
import com.jeesite.common.web.d.c;	
import com.jeesite.common.web.d.i;	
import com.jeesite.common.web.http.ServletUtils;	
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
import org.hyperic.sigar.ProcCred;	
	
/*	
 * Duplicate member names - consider using --renamedupmembers true	
 */	
public final class m {	
    private static final String a;	
    private static Date g;	
    private static final String[] J;	
    private static final i i;	
    private static SessionDAO b;	
    private static final String ALLATORIxDEMO;	
	
    private static /* synthetic */ int ALLATORIxDEMO(CharSequence cs, CharSequence searchChar, int start) {	
        return cs.toString().indexOf(searchChar.toString(), start);	
    }	
	
    private static /* synthetic */ byte[][] ALLATORIxDEMO(byte[] bytes, int splitLength) {	
        int a;	
        int a2;	
        byte[][] a3 = new byte[bytes.length % splitLength != 0 ? (a2 = bytes.length / splitLength + 1) : (a2 = bytes.length / splitLength)][];	
        int n = a = 0;	
        while (n < a2) {	
            byte[] a4;	
            byte[][] arrarrby;	
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
	
    private static /* synthetic */ byte[] d(String input) {	
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
	
    private static /* synthetic */ boolean d(CharSequence cs1, CharSequence cs2) {	
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
        return m.ALLATORIxDEMO(cs1, (boolean)0, 0, cs2, 0, cs1.length());	
    }	
	
    /*	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    public static final Map<String, String> ALLATORIxDEMO(InputStream inputStream) {	
        byte[] a;	
        byte[] a2 = new byte[]{};	
        try {	
            String a3;	
            void a4;	
            Iterator iterator;	
            void a5;	
            int n;	
            a2 = IOUtils.toByteArray((InputStream)inputStream);	
            String[] a6 = ">>>>大牛你好，能否留个联系方式，合作QQ：78112665，暗号：Licence520<<<<`7Km/KZk46sPt7e5xmn6ZIA==`MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMMl5nOBVE+QJsNC+Iti3rxgMbTU8NhptURjxslLFCxm94uFUnKUQ1k9KUgfWH2+RlM8DAz0HSDJX94+Pgsgqw3IO7r2uSIXQcqPDVOxGT/qUaSMdscBYuC42cOdiB5F4cNGfEiPdQm6JZMBBTjCGRtLel/sCb8lditqoz6MhJfRAgMBAAECgYBEmXSJ6fKa44K1AkFJwqgpPCzENMgWeWgRA7yjOfhC4EDUdaRnTxKKczde9UADWDrbidPHVIcHPST2SHSBCidNgeFEox+MR72wQRRWl7hjmAUHRGnt8ICiCUpWvz9YPTlVORAJwk2wkBv3zBveUdR2oLFeNQ7ARvXlDLQW1ybAEQJBAPeJyC0I/2JG+BlpUzUgjKlOlQpl57KfKdIF1sJ9gAuPKk4qHbVftcp6PqQhWEBtzrCsbNlofG77ziSKt4QRfU8CQQDJ0aZK2hO0UqQDPviAqUX5ZC3S4C6CUTBOiPyLw+mLlZBgg+jvyMuMwnxK/m9fK5hfZggQI5Ue3hVIbkstdJDfAkAnlmhmE2dMX080OSzudSsptICPbia04VF93iMvbYS51IaOg5vGsuzO2egEtbR4cVc52Al8Z4Jm+WxJWcnpnCZvAkA9Rz3hvmN7Phh0r9sOXddUSPms7MrSYMp1HhzoZxzzdE81fvfsTqCXZboNn0G7uOX0GWvbUqKFk9MMggirjZgrAkB4j5OC07ov3aRS9EhwHgSj8iKAThsADOygDRifXeLTHAAmtHBHdS8lCqIvznAMAMHRa5N2v6h2i59LEE2YLO9l`PxXhwSyYKLHQlmcx59Fl6Q==`MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCePYuA/B0NP8RG3q8zXjmkYrAzcd5IMKbOkDbnwBn5dpwn1Cp0qBSpFKciUtvX3v6+HSoQW9UEVzvhOn0mgsTGArwd5dfVaR11AbPQEfxJjnI649LrjMmw4No+AO+l4LhPWu83Mk4eR4FXMWs4vIh2lmWLTMCy4mGKndsTzamo2QIDAQAB".split("`");	
            a = m.ALLATORIxDEMO(a6[3]);	
            byte[] a7 = m.ALLATORIxDEMO(a6[1]);	
            String a8 = new String(m.ALLATORIxDEMO(a2, a, a7), "UTF-8");	
            a8 = m.d(a8, "\n\n", "\n\n");	
            a8 = m.ALLATORIxDEMO(a8, "\n", "", -1, false);	
            a8 = m.d(m.ALLATORIxDEMO(a8, a6[4]), a6[2]);	
            int a9 = 96;	
            int a10 = 96 + 32;	
            String string = a8;	
            byte[] a11 = m.d(string.substring(a9, a10));	
            a9 = a10;	
            byte[] a12 = m.d(string.substring(a9, a10 += 32));	
            a9 = a10 + 160;	
            a10 = string.length() - 64 - a6[0].length();	
            byte[] a13 = m.d(string.substring(a9, a10));	
            String a14 = new String(m.ALLATORIxDEMO(a13, a11, a12), "UTF-8");	
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
                    a5.put(a15[0], a15.length == 2 ? a15[1] : "");	
                }	
                n3 = ++n;	
            }	
            object = Arrays.asList(a5.values().toArray(new String[a5.values().size()]));	
            void v3 = a4;	
            void v4 = a4;	
            Collections.sort(v3, new C());	
            String a16 = "";	
            Iterator iterator2 = iterator = v3.iterator();	
            while (iterator2.hasNext()) {	
                a3 = (String)iterator.next();	
                a16 = new StringBuilder().insert(0, a16).append(a3).toString();	
                iterator2 = iterator;	
            }	
            if (!m.ALLATORIxDEMO(a16, 88).equals(a8)) {	
                return new HashMap<String, String>();	
            }	
            return a5;	
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
	
    private static /* varargs */ /* synthetic */ boolean ALLATORIxDEMO(CharSequence cs, CharSequence ... searchCharSequences) {	
        int n;	
        if (m.ALLATORIxDEMO(cs) || searchCharSequences == null || searchCharSequences.length <= 0) {	
            return false;	
        }	
        CharSequence[] arrcharSequence = searchCharSequences;	
        int n2 = arrcharSequence.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            CharSequence a = arrcharSequence[n];	
            if (m.ALLATORIxDEMO(cs, (CharSequence)m.ALLATORIxDEMO(a.toString()))) {	
                return true;	
            }	
            n3 = ++n;	
        }	
        return false;	
    }	
	
    private static /* synthetic */ String d(String str, String open, String close) {	
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
	
    private static /* synthetic */ boolean ALLATORIxDEMO(CharSequence seq, CharSequence searchSeq) {	
        if (seq == null || searchSeq == null) {	
            return false;	
        }	
        return m.ALLATORIxDEMO(seq, searchSeq, 0) >= 0;	
    }	
	
    private static /* varargs */ /* synthetic */ boolean ALLATORIxDEMO(String str, String ... strs) {	
        if (str != null && strs != null) {	
            int n;	
            String[] arrstring = strs;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a = arrstring[n];	
                if (m.ALLATORIxDEMO(str).equals(m.ALLATORIxDEMO(a))) {	
                    return true;	
                }	
                n3 = ++n;	
            }	
        }	
        return false;	
    }	
	
    public static final int ALLATORIxDEMO() {	
        Iterator iterator;	
        if (b == null) {	
            b = SpringUtils.getBean(SessionDAO.class);	
        }	
        HashSet<String> a = new HashSet<String>();	
        D.ALLATORIxDEMO = false;	
        Iterator iterator2 = iterator = b.getActiveSessions().iterator();	
        while (iterator2.hasNext()) {	
            LoginInfo a2;	
            Session a3 = (Session)iterator.next();	
            String a4 = "";	
            Object a5 = a3.getAttribute((Object)DefaultSubjectContext.PRINCIPALS_SESSION_KEY);	
            if (a5 != null && a5 instanceof PrincipalCollection && (a2 = (LoginInfo)((PrincipalCollection)a5).getPrimaryPrincipal()) != null) {	
                a4 = a2.getId();	
            }	
            if (m.ALLATORIxDEMO((CharSequence)a4)) {	
                a4 = (String)a3.getAttribute((Object)"userCode");	
            }	
            if (m.ALLATORIxDEMO((CharSequence)a4)) {	
                iterator2 = iterator;	
                continue;	
            }	
            a.add(a4);	
            iterator2 = iterator;	
        }	
        L.ALLATORIxDEMO = false;	
        return a.size();	
    }	
	
    public static final boolean ALLATORIxDEMO(HttpServletRequest request) {	
        if (request == null) {	
            request = ServletUtils.getRequest();	
        }	
        if (request != null) {	
            HttpServletRequest httpServletRequest = request;	
            String a = httpServletRequest.getContextPath();	
            String a2 = httpServletRequest.getRequestURL().toString();	
            com.jeesite.common.mybatis.d.v.c.ALLATORIxDEMO(a2, a);	
            String[] a3 = new String[]{a + "/licence", new StringBuilder().insert(0, a).append("/licence/save").toString()};	
            if (m.ALLATORIxDEMO(request.getRequestURI(), a3)) {	
                return true;	
            }	
            if ("9".equals(i.get("type"))) {	
                if (ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L > 1L) {	
                    return false;	
                }	
                if (!m.ALLATORIxDEMO((CharSequence)a2, ":E/127.0.0.1", "://loclhost", ":E/10.", "://172.", ":/E192.")) {	
                    return false;	
                }	
            }	
            String a4 = (String)i.get("domainOrIp");	
            if (!(m.d((CharSequence)i.get("type"), (CharSequence)"0") || m.ALLATORIxDEMO((CharSequence)a4) || m.ALLATORIxDEMO((CharSequence)a2, (CharSequence[])(a4 = new StringBuilder().insert(0, ":/E127.0.0.1,:E/localhost,:/E10.,:E/172.,:E/192.,").append(a4).toString()).split(",")))) {	
                return false;	
            }	
        }	
        return true;	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(String text, String searchString, String replacement, int max, boolean ignoreCase) {	
        StringBuilder a;	
        int a2;	
        StringBuilder stringBuilder;	
        block5 : {	
            int a3;	
            if (m.ALLATORIxDEMO((CharSequence)text) || m.ALLATORIxDEMO((CharSequence)searchString) || replacement == null || max == 0) {	
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
                StringBuilder stringBuilder2 = a.append(text, a2, a3).append(replacement);	
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
	
    private static /* synthetic */ String d(String input) {	
        try {	
            return new String(c.ALLATORIxDEMO(input), "UTF-8");	
        }	
        catch (UnsupportedEncodingException a) {	
            return "";	
        }	
    }	
	
    private static /* synthetic */ PublicKey ALLATORIxDEMO(String publicKey) throws Exception {	
        byte[] a = m.ALLATORIxDEMO(publicKey);	
        X509EncodedKeySpec a2 = new X509EncodedKeySpec(a);	
        return KeyFactory.getInstance("RSA").generatePublic(a2);	
    }	
	
    private static /* synthetic */ String d(String contentBase64, String privateKeyBase64) throws Exception {	
        int n;	
        PrivateKey a = m.ALLATORIxDEMO(privateKeyBase64);	
        Cipher a2 = Cipher.getInstance("RSA");	
        PrivateKey privateKey = a;	
        a2.init(2, privateKey);	
        int a3 = ((RSAPrivateKey)privateKey).getModulus().bitLength() / 8;	
        byte[][] a4 = m.ALLATORIxDEMO(m.d(m.d(contentBase64)), a3);	
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
        return m.d(a5.toString());	
    }	
	
    private static /* synthetic */ byte[] ALLATORIxDEMO(String input) {	
        return c.ALLATORIxDEMO(input);	
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
     * Exception decompiling	
     */	
    static {	
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.	
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 6[TRYBLOCK]	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:432)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)	
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:607)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:692)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:182)	
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:127)	
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:96)	
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:396)	
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:890)	
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:792)	
        // org.benf.cfr.reader.Driver.doJar(Driver.java:128)	
        // org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:63)	
        // org.benf.cfr.reader.Main.main(Main.java:48)	
        throw new IllegalStateException("Decompilation failed");	
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
                    if (m.ALLATORIxDEMO(a).equals(m.ALLATORIxDEMO(a2))) {	
                        return true;	
                    }	
                    n6 = ++n4;	
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
                if (!m.ALLATORIxDEMO((CharSequence)info.get("message"))) {	
                    throw new Exception(info.get("message"));	
                }	
                String a4 = info.get("code");	
                if (!m.ALLATORIxDEMO((CharSequence)a4) && m.ALLATORIxDEMO(a4.split(","), J) && !m.ALLATORIxDEMO((CharSequence)m.a) && !m.ALLATORIxDEMO((CharSequence)ALLATORIxDEMO) && m.ALLATORIxDEMO(m.a).equals(m.ALLATORIxDEMO(info.get("productName"))) && m.ALLATORIxDEMO(ALLATORIxDEMO).equals(m.ALLATORIxDEMO(info.get("companyName")))) {	
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
                        a.put("title", a3);	
                        a.put("message", new StringBuilder().insert(0, "您当前的版本为").append(a3).append("，非常感谢您对我们产品的认可与支持！").toString());	
                    }	
                    break block17;	
                }	
                throw new Exception(new StringBuilder().insert(0, "您当前的版本为").append(a3).toString());	
            }	
            catch (Exception a8) {	
                a.put("type", a2);	
                a.put("title", a3);	
                a.put("message", new StringBuilder().insert(0, a8.getMessage()).append("，官方网站：http://jeesite.com").toString());	
            }	
        }	
        if ("true".equals(info.get("devlop"))) {	
            a.put("type", "9");	
            a.put("title", "开发版");	
        }	
        return a;	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(String contentBase64, String publicKeyBase64) throws Exception {	
        int n;	
        PublicKey a = m.ALLATORIxDEMO(publicKeyBase64);	
        Cipher a2 = Cipher.getInstance("RSA");	
        PublicKey publicKey = a;	
        a2.init(2, publicKey);	
        int a3 = ((RSAPublicKey)publicKey).getModulus().bitLength() / 8;	
        byte[][] a4 = m.ALLATORIxDEMO(m.d(m.d(contentBase64)), a3);	
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
        return m.d(a5.toString());	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(String str) {	
        if (str == null) {	
            return "";	
        }	
        return str.replaceAll("^[\s|　| ]*|[\s|　| ]*$", "");	
    }	
	
    private static /* synthetic */ byte[] ALLATORIxDEMO(byte[] input, byte[] key, byte[] iv) {	
        try {	
            void a;	
            SecretKeySpec a2 = new SecretKeySpec(key, "AES");	
            IvParameterSpec a3 = new IvParameterSpec(iv);	
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Pdding");	
            void v0 = a;	
            v0.init(2, (Key)a2, a3);	
            return v0.doFinal(input);	
        }	
        catch (GeneralSecurityException a) {	
            throw new RuntimeException(a);	
        }	
    }	
	
    public static final i ALLATORIxDEMO() {	
        HashMap<String, String> a;	
        Object a2;	
        block7 : {	
            long a3 = System.currentTimeMillis() - g.getTime();	
            if (i.ALLATORIxDEMO() && a3 / 86400000L <= 0L) {	
                return i;	
            }	
            g = new Date();	
            a = new HashMap<String, String>();	
            try {	
                a2 = SpringUtils.getInputStream();	
                if (a2 != null) {	
                    a.putAll(m.ALLATORIxDEMO((InputStream)a2));	
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
        hashMap.putAll(m.ALLATORIxDEMO(hashMap));	
        hashMap.put("modules", ",flow,weixin,");	
        a.put("openModules", new StringBuilder().insert(0, ",").append((String)a.get("openModules")).append(",").toString());	
        if (m.ALLATORIxDEMO((String)a.get("type"), "1", "2", "9")) {	
            a.put("fnCs", "true");	
            a.put("fnJob", "true");	
        }	
        if (m.ALLATORIxDEMO((String)a.get("type"), new String[]{"2", "9"})) {	
            a.put("fnMsg", "true");	
            a.put("fnI18n", "true");	
            a.put("fnSas", "true");	
            a.put("fnCluster", "true");	
        }	
        i.putAll(a);	
        a2 = new StringBuilder();	
        ((StringBuilder)a2).append("\r\n    " + (String)a.get("message") + "\r\n");	
        com.jeesite.common.mybatis.d.v.c.ALLATORIxDEMO();	
        i.ALLATORIxDEMO("loadMessage", ((StringBuilder)a2).toString());	
        ((StringBuilder)a2).append("\r\n");	
        ((StringBuilder)a2).append(new StringBuilder().insert(0, "\r\n    公司名称：").append(ALLATORIxDEMO).append("").toString());	
        ((StringBuilder)a2).append(new StringBuilder().insert(0, "\r\n    产品名称：").append(m.a).append("").toString());	
        ((StringBuilder)a2).append(new StringBuilder().insert(0, "\r\n    机器码是：").append(J.length > 0 ? J[0] : "").append("").toString());	
        return i;	
    }	
	
    private static /* synthetic */ PrivateKey ALLATORIxDEMO(String privateKey) throws Exception {	
        byte[] a = m.ALLATORIxDEMO(privateKey);	
        PKCS8EncodedKeySpec a2 = new PKCS8EncodedKeySpec(a);	
        return KeyFactory.getInstance("RSA").generatePrivate(a2);	
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
        return m.ALLATORIxDEMO(a);	
    }	
}	
	
