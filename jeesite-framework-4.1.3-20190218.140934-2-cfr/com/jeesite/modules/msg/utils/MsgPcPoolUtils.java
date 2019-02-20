/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 */	
package com.jeesite.modules.msg.utils;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.modules.msg.entity.MsgPush;	
import java.util.ArrayList;	
import java.util.List;	
	
public class MsgPcPoolUtils {	
    private static final String MSG_PC_POOL_CACHE = "msgPcPoolCache";	
	
    public static void removeCache(String key) {	
        CacheUtils.remove(MSG_PC_POOL_CACHE, key);	
    }	
	
    public static List<MsgPush> getPool(String userCode) {	
        List a2 = (List)MsgPcPoolUtils.getCache(userCode);	
        if (a2 == null) {	
            a2 = ListUtils.newArrayList();	
        }	
        return a2;	
    }	
	
    public static <V> V getCache(String key) {	
        return (V)CacheUtils.get(MSG_PC_POOL_CACHE, key);	
    }	
	
    public static <V> V getCache(String key, V defaultValue) {	
        V a2 = MsgPcPoolUtils.getCache(key);	
        if (a2 != null) {	
            return a2;	
        }	
        return defaultValue;	
    }	
	
    public static void putPool(String userCode, List<MsgPush> msgPush) {	
        MsgPcPoolUtils.putCache(userCode, msgPush);	
    }	
	
    public static <V> void putCache(String key, V value) {	
        CacheUtils.put(MSG_PC_POOL_CACHE, key, value);	
    }	
	
    public static void putPool(String userCode, MsgPush msgPush) {	
        ArrayList a2 = MsgPcPoolUtils.getPool(userCode);	
        if (a2 == null) {	
            a2 = ListUtils.newArrayList();	
        }	
        MsgPcPoolUtils.putCache(userCode, a2);	
        a2.add(msgPush);	
    }	
}	
	
