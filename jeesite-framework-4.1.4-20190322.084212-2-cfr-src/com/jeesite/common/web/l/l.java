/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.web.l;	
	
import com.fasterxml.jackson.annotation.JsonView;	
import com.fasterxml.jackson.databind.ObjectMapper;	
import com.fasterxml.jackson.databind.ser.FilterProvider;	
import com.jeesite.common.mapper.XmlMapper;	
import com.jeesite.common.mybatis.mapper.query.QueryColumn;	
import java.util.Collection;	
import java.util.Collections;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.Map;	
import java.util.Set;	
import org.springframework.util.CollectionUtils;	
import org.springframework.validation.BindingResult;	
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;	
	
public class l	
extends MappingJackson2XmlView {	
    private boolean c;	
    private Set<String> ALLATORIxDEMO;	
	
    @Override	
    public void setModelKey(String modelKey) {	
        this.ALLATORIxDEMO = Collections.singleton(modelKey);	
    }	
	
    public final Set<String> ALLATORIxDEMO() {	
        return this.ALLATORIxDEMO;	
    }	
	
    public void ALLATORIxDEMO(boolean extractValueFromSingleKeyModel) {	
        this.c = extractValueFromSingleKeyModel;	
    }	
	
    public void ALLATORIxDEMO(Set<String> modelKeys) {	
        this.ALLATORIxDEMO = modelKeys;	
    }	
	
    @Override	
    protected Object filterModel(Map<String, Object> model) {	
        HashMap<String, Object> a = new HashMap<String, Object>(model.size());	
        Set<String> a2 = !CollectionUtils.isEmpty(this.ALLATORIxDEMO) ? this.ALLATORIxDEMO : model.keySet();	
        for (Map.Entry<String, Object> a3 : model.entrySet()) {	
            if (a3.getValue() instanceof BindingResult || !a2.contains(a3.getKey()) || a3.getKey().equals(JsonView.class.getName()) || a3.getKey().equals(FilterProvider.class.getName())) continue;	
            a.put(a3.getKey(), a3.getValue());	
        }	
        if (this.c && a.size() == 1) {	
            return a.values().iterator().next();	
        }	
        return a;	
    }	
	
    public l() {	
        l l2 = this;	
        l2.c = false;	
        l2.setContentType("application/xml");	
        l2.setObjectMapper(XmlMapper.getInstance());	
    }	
}	
	
