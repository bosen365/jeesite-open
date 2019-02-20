/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  javax.validation.ConstraintViolation	
 *  javax.validation.ConstraintViolationException	
 *  javax.validation.Path	
 *  javax.validation.Validator	
 */	
package com.jeesite.common.validator;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.validator.E;	
import java.util.ArrayList;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import javax.validation.ConstraintViolation;	
import javax.validation.ConstraintViolationException;	
import javax.validation.Path;	
import javax.validation.Validator;	
import org.hyperic.sigar.FileWatcher;	
import org.hyperic.sigar.cmd.Watch;	
	
public class ValidatorUtils {	
    public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e2) {	
        return ValidatorUtils.extractPropertyAndMessageAsList(e2.getConstraintViolations(), " ");	
    }	
	
    public static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations) {	
        return ValidatorUtils.extractPropertyAndMessageAsList(constraintViolations, " ");	
    }	
	
    public static Map<String, String> extractPropertyAndMessage(ConstraintViolationException e2) {	
        return ValidatorUtils.extractPropertyAndMessage(e2.getConstraintViolations());	
    }	
	
    public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e2, String separator) {	
        return ValidatorUtils.extractPropertyAndMessageAsList(e2.getConstraintViolations(), separator);	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (2 ^ 5) << 3 ^ (3 ^ 5);	
        int n4 = n2;	
        4 << 3 ^ 5;	
        int n5 = 1 << 3 ^ (3 ^ 5);	
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
	
    public static List<String> extractMessage(Set<? extends ConstraintViolation> constraintViolations) {	
        Iterator<? extends ConstraintViolation> iterator;	
        ArrayList a2 = ListUtils.newArrayList();	
        Iterator<? extends ConstraintViolation> iterator2 = iterator = constraintViolations.iterator();	
        while (iterator2.hasNext()) {	
            ConstraintViolation a3 = iterator.next();	
            iterator2 = iterator;	
            a2.add(a3.getMessage());	
        }	
        return a2;	
    }	
	
    public static /* varargs */ void validateWithException(Object object, Class<?> ... groups) throws ConstraintViolationException {	
        ValidatorUtils.validateWithException(E.ALLATORIxDEMO(), object, groups);	
    }	
	
    public static /* varargs */ void validateWithException(Validator validator, Object object, Class<?> ... groups) throws ConstraintViolationException {	
        Set a2 = validator.validate(object, (Class[])groups);	
        if (!a2.isEmpty()) {	
            throw new ConstraintViolationException(a2);	
        }	
    }	
	
    public static List<String> extractMessage(ConstraintViolationException e2) {	
        return ValidatorUtils.extractMessage(e2.getConstraintViolations());	
    }	
	
    public static /* varargs */ boolean validate(StringBuilder message, Object object, Class<?> ... groups) {	
        try {	
            ValidatorUtils.validateWithException(E.ALLATORIxDEMO(), object, groups);	
        }	
        catch (ConstraintViolationException a2) {	
            List<String> a3 = ValidatorUtils.extractPropertyAndMessageAsList(a2, ": ");	
            if (a3.size() > 0) {	
                List<String> list = a3;	
                list.add(0, "☆");	
                message.append(StringUtils.join(list, (String)"<br/>☆"));	
            }	
            return false;	
        }	
        return true;	
    }	
	
    public static Map<String, String> extractPropertyAndMessage(Set<? extends ConstraintViolation> constraintViolations) {	
        Iterator<? extends ConstraintViolation> iterator;	
        HashMap a2 = MapUtils.newHashMap();	
        Iterator<? extends ConstraintViolation> iterator2 = iterator = constraintViolations.iterator();	
        while (iterator2.hasNext()) {	
            ConstraintViolation a3 = iterator.next();	
            iterator2 = iterator;	
            a2.put(a3.getPropertyPath().toString(), a3.getMessage());	
        }	
        return a2;	
    }	
	
    public static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations, String separator) {	
        Iterator<? extends ConstraintViolation> iterator;	
        ArrayList a2 = ListUtils.newArrayList();	
        Iterator<? extends ConstraintViolation> iterator2 = iterator = constraintViolations.iterator();	
        while (iterator2.hasNext()) {	
            void a3;	
            ConstraintViolation constraintViolation = iterator.next();	
            iterator2 = iterator;	
            a2.add((Object)a3.getPropertyPath() + separator + a3.getMessage());	
        }	
        return a2;	
    }	
}	
	
