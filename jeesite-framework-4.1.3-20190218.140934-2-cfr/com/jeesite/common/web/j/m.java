/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.annotation.JsonView	
 *  com.fasterxml.jackson.databind.ObjectMapper	
 *  com.fasterxml.jackson.databind.ser.FilterProvider	
 *  com.jeesite.common.mapper.XmlMapper	
 *  org.springframework.util.CollectionUtils	
 *  org.springframework.validation.BindingResult	
 *  org.springframework.web.servlet.view.xml.MappingJackson2XmlView	
 */	
package com.jeesite.common.web.j;	
	
import com.fasterxml.jackson.annotation.JsonView;	
import com.fasterxml.jackson.databind.ObjectMapper;	
import com.fasterxml.jackson.databind.ser.FilterProvider;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.mapper.XmlMapper;	
import java.util.Collection;	
import java.util.Collections;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.Map;	
import java.util.Set;	
import org.springframework.util.CollectionUtils;	
import org.springframework.validation.BindingResult;	
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;	
	
public class m	
extends MappingJackson2XmlView {	
    private boolean c;	
    private Set<String> ALLATORIxDEMO;	
	
    public final Set<String> ALLATORIxDEMO() {	
        return this.ALLATORIxDEMO;	
    }	
	
    public void ALLATORIxDEMO(Set<String> modelKeys) {	
        this.ALLATORIxDEMO = modelKeys;	
    }	
	
    public void setModelKey(String modelKey) {	
        this.ALLATORIxDEMO = Collections.singleton(modelKey);	
    }	
	
    public m() {	
        m m2 = this;	
        m2.c = false;	
        m2.setContentType("application/xml");	
        m2.setObjectMapper((ObjectMapper)XmlMapper.getInstance());	
    }	
	
    protected Object filterModel(Map<String, Object> model) {	
        HashMap<String, Object> a2 = new HashMap<String, Object>(model.size());	
        Set<String> a3 = !CollectionUtils.isEmpty(this.ALLATORIxDEMO) ? this.ALLATORIxDEMO : model.keySet();	
        for (Map.Entry<String, Object> a4 : model.entrySet()) {	
            if (a4.getValue() instanceof BindingResult || !a3.contains(a4.getKey()) || a4.getKey().equals(JsonView.class.getName()) || a4.getKey().equals(FilterProvider.class.getName())) continue;	
            a2.put(a4.getKey(), a4.getValue());	
        }	
        if (this.c && a2.size() == 1) {	
            return a2.values().iterator().next();	
        }	
        return a2;	
    }	
	
    public void ALLATORIxDEMO(boolean extractValueFromSingleKeyModel) {	
        this.c = extractValueFromSingleKeyModel;	
    }	
}	
	
