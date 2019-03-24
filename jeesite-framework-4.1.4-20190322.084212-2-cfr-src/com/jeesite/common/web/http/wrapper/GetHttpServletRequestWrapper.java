/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.web.http.wrapper;	
	
import javax.servlet.ServletRequest;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletRequestWrapper;	
import org.hyperic.sigar.win32.EventLogRecord;	
	
public class GetHttpServletRequestWrapper	
extends HttpServletRequestWrapper {	
    @Override	
    public String getMethod() {	
        return "GET";	
    }	
	
    public GetHttpServletRequestWrapper(HttpServletRequest request) {	
        super(request);	
    }	
	
    public GetHttpServletRequestWrapper(ServletRequest request) {	
        super((HttpServletRequest)request);	
    }	
}	
	
