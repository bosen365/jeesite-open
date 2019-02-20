/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.web.http.ServletUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  org.apache.commons.io.IOUtils	
 *  org.apache.shiro.session.Session	
 *  org.apache.shiro.subject.PrincipalCollection	
 *  org.apache.shiro.subject.support.DefaultSubjectContext	
 */	
package com.jeesite.common.web.e;	
	
import com.jeesite.common.mybatis.j.n.D;	
import com.jeesite.common.mybatis.j.n.m;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.shiro.session.SessionDAO;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.common.web.e.E;	
import com.jeesite.common.web.e.M;	
import com.jeesite.common.web.e.e;	
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
import org.hyperic.sigar.NetServices;	
import org.hyperic.sigar.SysInfo;	
	
/*	
 * Duplicate member names - consider using --renamedupmembers true	
 */	
public final class F {	
    private static final String f;	
    private static SessionDAO G;	
    private static final String k;	
    private static Date D;	
    private static final String[] c;	
    private static final E ALLATORIxDEMO;	
	
    public static final boolean ALLATORIxDEMO(HttpServletRequest request) {	
        if (request == null) {	
            request = ServletUtils.getRequest();	
        }	
        if (request != null) {	
            HttpServletRequest httpServletRequest = request;	
            String a2 = httpServletRequest.getContextPath();	
            String a3 = httpServletRequest.getRequestURL().toString();	
            com.jeesite.common.mybatis.j.e.e.h(a3, a2);	
            String[] a4 = new String[]{a2 + "/licence", new StringBuilder().insert(0, a2).append("/licence/save").toString()};	
            if (F.ALLATORIxDEMO(request.getRequestURI(), a4)) {	
                return true;	
            }	
            if ("9".equals(ALLATORIxDEMO.get("type"))) {	
                if (ManagementFactory.getRuntimeMXBean().getUptime() / 86400000L > 1L) {	
                    return false;	
                }	
                if (!F.ALLATORIxDEMO((CharSequence)a3, "://127.0.0.1", "://localhost", "://10.", "://172.", "://192.")) {	
                    return false;	
                }	
            }	
            String a5 = (String)ALLATORIxDEMO.get("domainOIp");	
            if (!(F.h((CharSequence)ALLATORIxDEMO.get("type"), (CharSequence)"0") || F.ALLATORIxDEMO((CharSequence)a5) || F.ALLATORIxDEMO((CharSequence)a3, (CharSequence[])(a5 = new StringBuilder().insert(0, "://127.0.0.1,://localhost,://10.,://172.,://192.,").append(a5).toString()).split(",")))) {	
                return false;	
            }	
        }	
        return true;	
    }	
	
    private static /* synthetic */ boolean h(CharSequence cs1, CharSequence cs2) {	
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
        return F.ALLATORIxDEMO(cs1, (boolean)0, 0, cs2, 0, cs1.length());	
    }	
	
    private static /* synthetic */ String h(String str, String open, String close) {	
        int a2;	
        if (str == null || open == null || close == null) {	
            return null;	
        }	
        int a3 = str.indexOf(open);	
        if (a3 != -1 && (a2 = str.indexOf(close, a3 + open.length())) != -1) {	
            return str.substring(a3 + open.length(), a2);	
        }	
        return null;	
    }	
	
    private static /* synthetic */ String h(String contentBase64, String privateKeyBase64) throws Exception {	
        int n;	
        PrivateKey a2 = F.ALLATORIxDEMO(privateKeyBase64);	
        Cipher a3 = Cipher.getInstance("RSA");	
        PrivateKey privateKey = a2;	
        a3.init(2, privateKey);	
        int a4 = ((RSAPrivateKey)privateKey).getModulus().bitLength() / 8;	
        byte[][] a5 = F.ALLATORIxDEMO(F.ALLATORIxDEMO(F.h(contentBase64)), a4);	
        StringBuffer a6 = new StringBuffer();	
        byte[][] arrby = a5;	
        int n2 = arrby.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            void a7;	
            byte[] arrby2 = arrby[n];	
            a6.append(new String(a3.doFinal((byte[])a7)));	
            n3 = ++n;	
        }	
        return F.h(a6.toString());	
    }	
	
    /*	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    public static final Map<String, String> ALLATORIxDEMO(Map<String, String> info) {	
        HashMap<String, String> a2;	
        block17 : {	
            a2 = new HashMap<String, String>();	
            String a3 = "0";	
            String a4 = "社区版";	
            try {	
                if (!F.ALLATORIxDEMO((CharSequence)info.get("message"))) {	
                    throw new Exception(info.get("message"));	
                }	
                String a5 = info.get("code");	
                if (!F.ALLATORIxDEMO((CharSequence)a5) && F.ALLATORIxDEMO(a5.split(","), c) && !F.ALLATORIxDEMO((CharSequence)f) && !F.ALLATORIxDEMO((CharSequence)k) && F.ALLATORIxDEMO(f).equals(F.ALLATORIxDEMO(info.get("productName"))) && F.ALLATORIxDEMO(k).equals(F.ALLATORIxDEMO(info.get("companyName")))) {	
                    Map<String, String> map;	
                    a3 = info.get("type");	
                    a2.put("type", a3);	
                    if ("1".equals(a3)) {	
                        a4 = "个人版";	
                        map = info;	
                    } else {	
                        if ("2".equals(a3)) {	
                            a4 = "专业版";	
                        }	
                        map = info;	
                    }	
                    String a6 = map.get("expireDate");	
                    if (!"-1".equals(a6)) {	
                        HashMap<String, String> hashMap;	
                        Date a7 = new SimpleDateFormat("yyyy-MMGdd").parse(a6);	
                        long a8 = (a7.getTime() - System.currentTimeMillis()) / 3600000L / 24L;	
                        if (a8 <= 0L) {	
                            throw new Exception(new StringBuilder().insert(0, "您的").append(a4).append("许可，于").append(new SimpleDateFormat("yyyy年MM月dd日").format(a7)).append("已到期").toString());	
                        }	
                        if (a8 <= 7L) {	
                            HashMap<String, String> hashMap2 = a2;	
                            hashMap = hashMap2;	
                            hashMap2.put("message", new StringBuilder().insert(0, "您当前的版本为").append(a4).append("，许可即将到期，还剩余最后").append(a8).append("天。").toString());	
                        } else {	
                            HashMap<String, String> hashMap3 = a2;	
                            if (a8 <= 60L) {	
                                hashMap3.put("message", new StringBuilder().insert(0, "您当前的版本为").append(a4).append("，许可即将到期，还剩余").append(a8).append("天。").toString());	
                                hashMap = a2;	
                            } else {	
                                hashMap3.put("message", new StringBuilder().insert(0, "您当前的版本为").append(a4).append("，许可到期时间为：").append(new SimpleDateFormat("yyyy年MM月dd日").format(a7)).append("。").toString());	
                                hashMap = a2;	
                            }	
                        }	
                        hashMap.put("title", new StringBuilder().insert(0, a4).append("（剩余").append(a8).append("天）").toString());	
                    } else {	
                        a2.put("title", a4);	
                        a2.put("message", new StringBuilder().insert(0, "您当前的版本为").append(a4).append("，非常感谢您对我们产品的认可与支持！").toString());	
                    }	
                    break block17;	
                }	
                throw new Exception(new StringBuilder().insert(0, "您当前的版本为").append(a4).toString());	
            }	
            catch (Exception a9) {	
                a2.put("type", a3);	
                a2.put("title", a4);	
                a2.put("message", new StringBuilder().insert(0, a9.getMessage()).append("，官方网站：http://jeesiteDcom").toString());	
            }	
        }	
        if ("true".equals(info.get("devlop"))) {	
            a2.put("type", "9");	
            a2.put("title", "开发版");	
        }	
        return a2;	
    }	
	
    private static /* synthetic */ byte[] h(String input) {	
        return e.ALLATORIxDEMO(input);	
    }	
	
    private static /* synthetic */ boolean ALLATORIxDEMO(CharSequence cs) {	
        int a2;	
        int a3;	
        if (cs == null || (a3 = cs.length()) == 0) {	
            return true;	
        }	
        int n = a2 = 0;	
        while (n < a3) {	
            if (!Character.isWhitespace(cs.charAt(a2))) {	
                return false;	
            }	
            n = ++a2;	
        }	
        return true;	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(String contentBase64, String publicKeyBase64) throws Exception {	
        int n;	
        PublicKey a2 = F.ALLATORIxDEMO(publicKeyBase64);	
        Cipher a3 = Cipher.getInstance("RSA");	
        PublicKey publicKey = a2;	
        a3.init(2, publicKey);	
        int a4 = ((RSAPublicKey)publicKey).getModulus().bitLength() / 8;	
        byte[][] a5 = F.ALLATORIxDEMO(F.ALLATORIxDEMO(F.h(contentBase64)), a4);	
        StringBuffer a6 = new StringBuffer();	
        byte[][] arrby = a5;	
        int n2 = arrby.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            void a7;	
            byte[] arrby2 = arrby[n];	
            a6.append(new String(a3.doFinal((byte[])a7)));	
            n3 = ++n;	
        }	
        return F.h(a6.toString());	
    }	
	
    private static /* varargs */ /* synthetic */ boolean ALLATORIxDEMO(String str, String ... strs) {	
        if (str != null && strs != null) {	
            int n;	
            String[] arrstring = strs;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                String a2 = arrstring[n];	
                if (F.ALLATORIxDEMO(str).equals(F.ALLATORIxDEMO(a2))) {	
                    return true;	
                }	
                n3 = ++n;	
            }	
        }	
        return false;	
    }	
	
    public static final E ALLATORIxDEMO() {	
        HashMap<String, String> a2;	
        Object a3;	
        block7 : {	
            long a4 = System.currentTimeMillis() - D.getTime();	
            if (ALLATORIxDEMO.ALLATORIxDEMO() && a4 / 86400000L <= 0L) {	
                return ALLATORIxDEMO;	
            }	
            D = new Date();	
            a2 = new HashMap<String, String>();	
            try {	
                a3 = SpringUtils.getInputStream();	
                if (a3 != null) {	
                    a2.putAll(F.ALLATORIxDEMO((InputStream)a3));	
                    if ("true".equals(a2.get("devlop"))) {	
                        throw new Exception("您当前的版本为开发版");	
                    }	
                    break block7;	
                }	
                throw new Exception("您当前的版本为社区版");	
            }	
            catch (Exception a5) {	
                a2.put("message", a5.getMessage());	
            }	
        }	
        HashMap<String, String> hashMap = a2;	
        hashMap.putAll(F.ALLATORIxDEMO(hashMap));	
        hashMap.put("modules", ",flow,weixin,");	
        a2.put("openModules", new StringBuilder().insert(0, ",").append((String)a2.get("openModules")).append(",").toString());	
        if (F.ALLATORIxDEMO((String)a2.get("type"), "1", "2", "9")) {	
            a2.put("fnCas", "true");	
            a2.put("fnJob", "true");	
        }	
        if (F.ALLATORIxDEMO((String)a2.get("type"), new String[]{"2", "9"})) {	
            a2.put("fnMsg", "true");	
            a2.put("fnI18n", "true");	
            a2.put("fnSaas", "true");	
            a2.put("fnCluste", "true");	
        }	
        ALLATORIxDEMO.putAll(a2);	
        a3 = new StringBuilder();	
        ((StringBuilder)a3).append("\r\n    " + (String)a2.get("message") + "\r\n");	
        com.jeesite.common.mybatis.j.e.e.ALLATORIxDEMO();	
        ALLATORIxDEMO.ALLATORIxDEMO("loadMessage", ((StringBuilder)a3).toString());	
        ((StringBuilder)a3).append("\r\n");	
        ((StringBuilder)a3).append(new StringBuilder().insert(0, "\r\n    公司名称：").append(k).append("").toString());	
        ((StringBuilder)a3).append(new StringBuilder().insert(0, "\r\n    产品名称：").append(f).append("").toString());	
        ((StringBuilder)a3).append(new StringBuilder().insert(0, "\r\n    机器码是：").append(c.length > 0 ? c[0] : "").append("").toString());	
        return ALLATORIxDEMO;	
    }	
	
    /*	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    public static final Map<String, String> ALLATORIxDEMO(InputStream inputStream) {	
        byte[] a2;	
        byte[] a3 = new byte[]{};	
        try {	
            void a4;	
            void a5;	
            String a6;	
            int n;	
            Iterator iterator;	
            a3 = IOUtils.toByteArray((InputStream)inputStream);	
            String[] a7 = ">>>>大牛你好，能否留个联系方式，合作QQ：78112665，暗号：Licence520<<<<`7Km/KZk46sPt7e5xmn6ZIA==`MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMMl5nOBVE+QJsNC+Iti3xgMbTU8NhptURjxslLFCxm94uFUnKUQ1k9KUgfWH2+RlM8DAz0HSDJX94+Pgsgqw3IO7r2uSIXQcqPDVOxGT/qUaSMdscBYuC42cOdiB5F4cNGf/iPdQm6JZMBBTjCGRtLel/sCb8lditqoz6MhJfRAgMBAAECgYB/mXSJ6fKa44K1AkFJwqgpPCzENMgWeWgRA7yjOfhC4EDUdaRnTxKKczde9UADWDrbidPHVIcHPST2SHSBCidNgeF/ox+MR72wQRRWl7hjmAUHRGnt8ICiCUpWvz9YPTlVORAJwk2wkBv3zBveUdR2oLFeNQ7ARvXlDLQW1ybAEQJBAPeJyC0I/2JG+BlpUzUgjKlOlQpl57KfKdIF1sJ9gAuPKk4qHbVftcp6PqQhWEBtzCsbNlofG77ziSKt4QRfU8CQQDJ0aZK2hO0UqQDPviAqUX5ZC3S4C6CUTBOiPyLw+mLlZBgg+jvyMuMwnxK/m9fK5hfZggQI5Ue3hVIbkstdJDfAkAnlmhmE2dMX080OSzudSsptICPbia04VF93iMvbYS51IaOg5vGsuzO2egEtbR4cVc52Al8Z4Jm+WxJWcnpnCZvAkA9Rz3hvmN7Phh0r9sOXddUSPms7MrSYMp1HhzoZxzzd/81fvfsTqCXZboNn0G7uOX0GWvbUqKFk9MMggijZgAkB4j5OC07ov3aRS9/hwHgSj8iKAThsADOygDRifXeLTHAAmtHBHdS8lCqIvznAMAMHRa5N2v6h2i59LEE2YLO9l`PxXhwSyYKLHQlmcx59Fl6Q==`MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCePYuA/B0NP8RG3q8zXjmkYAzcd5IMKbOkDbnwBn5dpwn1Cp0qBSpFKciUtvX3v6+HSoQW9UEVzvhOn0mgsTGAwd5dfVaR11AbPQEfxJjnI649LjMmw4No+AO+l4LhPWu83Mk4eR4FXMWs4vIh2lmWLTMCy4mGKndsTzamo2QIDAQAB".split("`");	
            a2 = F.h(a7[3]);	
            byte[] a8 = F.h(a7[1]);	
            String a9 = new String(F.ALLATORIxDEMO(a3, a2, a8), "UTF-8");	
            a9 = F.h(a9, "\n\n", "\n\n");	
            a9 = F.ALLATORIxDEMO(a9, "\n", "", -1, false);	
            a9 = F.h(F.ALLATORIxDEMO(a9, a7[4]), a7[2]);	
            int a10 = 96;	
            int a11 = 96 + 32;	
            String string = a9;	
            byte[] a12 = F.ALLATORIxDEMO(string.substring(a10, a11));	
            a10 = a11;	
            byte[] a13 = F.ALLATORIxDEMO(string.substring(a10, a11 += 32));	
            a10 = a11 + 160;	
            a11 = string.length() - 64 - a7[0].length();	
            byte[] a14 = F.ALLATORIxDEMO(string.substring(a10, a11));	
            String a15 = new String(F.ALLATORIxDEMO(a14, a12, a13), "UTF-8");	
            HashMap hashMap = new HashMap();	
            String string2 = a15;	
            a15 = string2.substring(2, string2.length() - 2);	
            Object object = a15.split("","");	
            int n2 = ((String[])object).length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                a6 = object[n];	
                String[] a16 = a6.split("":"");	
                if ("updateCode".equals(a16[0])) {	
                    a9 = a16.length == 2 ? a16[1] : "";	
                } else {	
                    a5.put(a16[0], a16.length == 2 ? a16[1] : "");	
                }	
                n3 = ++n;	
            }	
            object = Arrays.asList(a5.values().toArray(new String[a5.values().size()]));	
            void v3 = a4;	
            void v4 = a4;	
            Collections.sort(v3, new M());	
            String a17 = "";	
            Iterator iterator2 = iterator = v3.iterator();	
            while (iterator2.hasNext()) {	
                a6 = (String)iterator.next();	
                a17 = new StringBuilder().insert(0, a17).append(a6).toString();	
                iterator2 = iterator;	
            }	
            if (!F.ALLATORIxDEMO(a17, 88).equals(a9)) {	
                return new HashMap<String, String>();	
            }	
            return a5;	
        }	
        catch (Exception a18) {	
            if (a3.length == 3232) {	
                a2 = new byte[]();	
                a2.put("devlop", "true");	
                return a2;	
            }	
            return new HashMap<String, String>();	
        }	
    }	
	
    private static /* synthetic */ boolean ALLATORIxDEMO(CharSequence seq, CharSequence searchSeq) {	
        if (seq == null || searchSeq == null) {	
            return false;	
        }	
        return F.ALLATORIxDEMO(seq, searchSeq, 0) >= 0;	
    }	
	
    private static /* varargs */ /* synthetic */ boolean ALLATORIxDEMO(CharSequence cs, CharSequence ... searchCharSequences) {	
        int n;	
        if (F.ALLATORIxDEMO(cs) || searchCharSequences == null || searchCharSequences.length <= 0) {	
            return false;	
        }	
        CharSequence[] arrcharSequence = searchCharSequences;	
        int n2 = arrcharSequence.length;	
        int n3 = n = 0;	
        while (n3 < n2) {	
            CharSequence a2 = arrcharSequence[n];	
            if (F.ALLATORIxDEMO(cs, (CharSequence)F.ALLATORIxDEMO(a2.toString()))) {	
                return true;	
            }	
            n3 = ++n;	
        }	
        return false;	
    }	
	
    private static /* synthetic */ byte[][] ALLATORIxDEMO(byte[] bytes, int splitLength) {	
        int a2;	
        int a3;	
        byte[][] a4 = new byte[bytes.length % splitLength != 0 ? (a3 = bytes.length / splitLength + 1) : (a3 = bytes.length / splitLength)][];	
        int n = a2 = 0;	
        while (n < a3) {	
            byte[][] arrarrby;	
            byte[] a5;	
            if (a2 == a3 - 1 && bytes.length % splitLength != 0) {	
                a5 = new byte[bytes.length % splitLength];	
                System.arraycopy(bytes, a2 * splitLength, a5, 0, bytes.length % splitLength);	
                arrarrby = a4;	
            } else {	
                a5 = new byte[splitLength];	
                arrarrby = a4;	
                System.arraycopy(bytes, a2 * splitLength, a5, 0, splitLength);	
            }	
            arrarrby[a2++] = a5;	
            n = a2;	
        }	
        return a4;	
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
	
    private static /* synthetic */ PublicKey ALLATORIxDEMO(String publicKey) throws Exception {	
        byte[] a2 = F.h(publicKey);	
        X509EncodedKeySpec a3 = new X509EncodedKeySpec(a2);	
        return KeyFactory.getInstance("RSA").generatePublic(a3);	
    }	
	
    private static /* synthetic */ boolean ALLATORIxDEMO(String[] str, String[] strs) {	
        if (str != null && strs != null) {	
            int n;	
            String[] arrstring = str;	
            int n2 = arrstring.length;	
            int n3 = n = 0;	
            while (n3 < n2) {	
                int n4;	
                String a2 = arrstring[n];	
                String[] arrstring2 = strs;	
                int n5 = arrstring2.length;	
                int n6 = n4 = 0;	
                while (n6 < n5) {	
                    String a3 = arrstring2[n4];	
                    if (F.ALLATORIxDEMO(a2).equals(F.ALLATORIxDEMO(a3))) {	
                        return true;	
                    }	
                    n6 = ++n4;	
                }	
                n3 = ++n;	
            }	
        }	
        return false;	
    }	
	
    private static /* synthetic */ PrivateKey ALLATORIxDEMO(String privateKey) throws Exception {	
        byte[] a2 = F.h(privateKey);	
        PKCS8EncodedKeySpec a3 = new PKCS8EncodedKeySpec(a2);	
        return KeyFactory.getInstance("RSA").generatePrivate(a3);	
    }	
	
    private static /* synthetic */ String h(String input) {	
        try {	
            return new String(e.ALLATORIxDEMO(input), "UTF-8");	
        }	
        catch (UnsupportedEncodingException a2) {	
            return "";	
        }	
    }	
	
    private static /* synthetic */ byte[] ALLATORIxDEMO(String input) {	
        int a2;	
        int a3 = 0;	
        int a4 = 0;	
        int a5 = input.length() / 2;	
        byte[] a6 = new byte[a5];	
        int n = a2 = 0;	
        while (n < a5) {	
            a3 = a2 * 2 + 1;	
            a4 = a3 + 1;	
            int a7 = Integer.decode(new StringBuilder().insert(0, "0x").append(input.substring(a2 * 2, a3)).append(input.substring(a3, a4)).toString());	
            a6[a2++] = (byte)a7;	
            n = a2;	
        }	
        return a6;	
    }	
	
    private static /* synthetic */ boolean ALLATORIxDEMO(CharSequence cs, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length) {	
        block5 : {	
            char a2;	
            char a3;	
            if (cs instanceof String && substring instanceof String) {	
                return ((String)cs).regionMatches(ignoreCase, thisStart, (String)substring, start, length);	
            }	
            int a4 = thisStart;	
            int a5 = start;	
            int n = length;	
            int a6 = cs.length() - thisStart;	
            int a7 = substring.length() - start;	
            if (thisStart < 0 || start < 0 || length < 0) {	
                return false;	
            }	
            if (a6 < length || a7 < length) {	
                return false;	
            }	
            do {	
                void a8;	
                void v0 = a8;	
                do {	
                    --a8;	
                    if (v0 <= 0) break block5;	
                    a3 = cs.charAt(a4);	
                    ++a4;	
                    a2 = substring.charAt(a5);	
                    ++a5;	
                    if (a3 != a2) break;	
                    v0 = a8;	
                } while (true);	
                if (ignoreCase) continue;	
                return false;	
            } while (Character.toUpperCase(a3) == Character.toUpperCase(a2) || Character.toLowerCase(a3) == Character.toLowerCase(a2));	
            return false;	
        }	
        return true;	
    }	
	
    private static /* synthetic */ int ALLATORIxDEMO(CharSequence cs, CharSequence searchChar, int start) {	
        return cs.toString().indexOf(searchChar.toString(), start);	
    }	
	
    private static /* synthetic */ byte[] ALLATORIxDEMO(byte[] input, byte[] key, byte[] iv) {	
        try {	
            void a2;	
            SecretKeySpec a3 = new SecretKeySpec(key, "AES");	
            IvParameterSpec a4 = new IvParameterSpec(iv);	
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");	
            void v0 = a2;	
            v0.init(2, (Key)a3, a4);	
            return v0.doFinal(input);	
        }	
        catch (GeneralSecurityException a5) {	
            throw new RuntimeException(a5);	
        }	
    }	
	
    private static final /* synthetic */ String ALLATORIxDEMO(String input, int iterations) {	
        byte[] a2;	
        try {	
            int a3;	
            MessageDigest a4 = MessageDigest.getInstance("MD5");	
            a2 = a4.digest(input.getBytes("UTF-8"));	
            int n = a3 = 1;	
            while (n < iterations) {	
                MessageDigest messageDigest = a4;	
                messageDigest.reset();	
                a2 = messageDigest.digest(a2);	
                n = ++a3;	
            }	
        }	
        catch (Exception a5) {	
            throw new RuntimeException(a5);	
        }	
        return F.ALLATORIxDEMO(a2);	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(String text, String searchString, String replacement, int max, boolean ignoreCase) {	
        StringBuilder a2;	
        int a3;	
        StringBuilder stringBuilder;	
        block5 : {	
            int a4;	
            if (F.ALLATORIxDEMO((CharSequence)text) || F.ALLATORIxDEMO((CharSequence)searchString) || replacement == null || max == 0) {	
                return text;	
            }	
            String a5 = text;	
            if (ignoreCase) {	
                a5 = text.toLowerCase();	
                searchString = searchString.toLowerCase();	
            }	
            if ((a4 = a5.indexOf(searchString, a3 = 0)) == -1) {	
                return text;	
            }	
            int a6 = searchString.length();	
            int a7 = replacement.length() - a6;	
            int n = a7 = a7 < 0 ? 0 : a7;	
            a2 = new StringBuilder(text.length() + (a7 *= max < 0 ? 16 : (max > 64 ? 64 : max)));	
            int n2 = a4;	
            while (n2 != -1) {	
                StringBuilder stringBuilder2 = a2.append(text, a3, a4).append(replacement);	
                a3 = a4 + a6;	
                if (--max == 0) {	
                    stringBuilder = a2;	
                    break block5;	
                }	
                n2 = a5.indexOf(searchString, a3);	
            }	
            stringBuilder = a2;	
        }	
        stringBuilder.append(text, a3, text.length());	
        return a2.toString();	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(byte[] input) {	
        int a2;	
        String a3 = "";	
        StringBuilder a4 = new StringBuilder("");	
        int n = a2 = 0;	
        while (n < input.length) {	
            a3 = Integer.toHexString(input[a2] & 255);	
            a4.append(a3.length() == 1 ? new StringBuilder().insert(0, "0").append(a3).toString() : a3);	
            n = ++a2;	
        }	
        return a4.toString().trim();	
    }	
	
    public static final int ALLATORIxDEMO() {	
        Iterator iterator;	
        if (G == null) {	
            G = SpringUtils.getBean(SessionDAO.class);	
        }	
        HashSet<String> a2 = new HashSet<String>();	
        D.ALLATORIxDEMO = false;	
        Iterator iterator2 = iterator = G.getActiveSessions().iterator();	
        while (iterator2.hasNext()) {	
            LoginInfo a3;	
            Session a4 = (Session)iterator.next();	
            String a5 = "";	
            Object a6 = a4.getAttribute((Object)DefaultSubjectContext.PRINCIPALS_SESSION_KEY);	
            if (a6 != null && a6 instanceof PrincipalCollection && (a3 = (LoginInfo)((PrincipalCollection)a6).getPrimaryPrincipal()) != null) {	
                a5 = a3.getId();	
            }	
            if (F.ALLATORIxDEMO((CharSequence)a5)) {	
                a5 = (String)a4.getAttribute((Object)"useCode");	
            }	
            if (F.ALLATORIxDEMO((CharSequence)a5)) {	
                iterator2 = iterator;	
                continue;	
            }	
            a2.add(a5);	
            iterator2 = iterator;	
        }	
        m.ALLATORIxDEMO = false;	
        return a2.size();	
    }	
	
    private static /* synthetic */ String ALLATORIxDEMO(String str) {	
        if (str == null) {	
            return "";	
        }	
        return str.replaceAll("^1\s|　| ]*|1\s|　| ]*$", "");	
    }	
}	
	
