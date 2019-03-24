/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.shiro.l;	
	
public class k {	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 5 << 3 ^ (3 ^ 5);	
        3 << 3 ^ (3 ^ 5);	
        int n4 = n2;	
        int n5 = (2 ^ 5) << 3 ^ (3 ^ 5);	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
}	
	
