/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.common.mybatis.mapper;	
	
public class MapperException	
extends RuntimeException {	
    private static final long serialVersionUID = 1L;	
	
    public MapperException(Throwable cause) {	
        super(cause);	
    }	
	
    public MapperException() {	
    }	
	
    public MapperException(String message, Throwable cause) {	
        super(message, cause);	
    }	
	
    public MapperException(String message) {	
        super(message);	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (3 ^ 5) << 4 ^ 3 << 1;	
        int n4 = n2;	
        int n5 = 5 << 4 ^ 1 << 1;	
        (3 ^ 5) << 3 ^ 3;	
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
	
