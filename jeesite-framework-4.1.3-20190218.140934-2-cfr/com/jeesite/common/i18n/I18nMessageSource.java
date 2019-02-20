/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.common.i18n;	
	
import com.jeesite.common.beetl.j.E;	
import java.text.MessageFormat;	
import java.util.Locale;	
	
public class I18nMessageSource	
extends E {	
    @Override	
    public void clearCache() {	
        super.clearCache();	
    }	
	
    @Override	
    protected String resolveCodeWithoutArguments(String code, Locale locale) {	
        return super.resolveCodeWithoutArguments(code, locale);	
    }	
	
    @Override	
    protected MessageFormat resolveCode(String code, Locale locale) {	
        return super.resolveCode(code, locale);	
    }	
}	
	
