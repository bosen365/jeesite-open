/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.i18n;	
	
import com.jeesite.common.beetl.d.i;	
import java.text.MessageFormat;	
import java.util.Locale;	
	
public class I18nMessageSource	
extends i {	
    @Override	
    protected MessageFormat resolveCode(String code, Locale locale) {	
        return super.resolveCode(code, locale);	
    }	
	
    @Override	
    public void clearCache() {	
        super.clearCache();	
    }	
	
    @Override	
    protected String resolveCodeWithoutArguments(String code, Locale locale) {	
        return super.resolveCodeWithoutArguments(code, locale);	
    }	
}	
	
