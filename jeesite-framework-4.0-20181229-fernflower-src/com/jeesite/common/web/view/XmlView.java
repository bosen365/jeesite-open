package com.jeesite.common.web.view;	
	
import com.fasterxml.jackson.databind.ser.FilterProvider;	
import com.jeesite.common.l.C;	
import com.jeesite.common.mapper.XmlMapper;	
import java.util.Collections;	
import java.util.HashMap;	
import java.util.Iterator;	
import java.util.Map;	
import java.util.Set;	
import java.util.Map.Entry;	
import org.springframework.util.CollectionUtils;	
import org.springframework.validation.BindingResult;	
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;	
	
public class XmlView extends MappingJackson2XmlView {	
   private Set modelKeys;	
   private boolean extractValueFromSingleKeyModel = false;	
	
   public void setModelKey(String modelKey) {	
      this.modelKeys = Collections.singleton(modelKey);	
   }	
	
   public void setExtractValueFromSingleKeyModel(boolean extractValueFromSingleKeyModel) {	
      this.extractValueFromSingleKeyModel = extractValueFromSingleKeyModel;	
   }	
	
   public XmlView() {	
      this.setContentType("application/xml");	
      this.setObjectMapper(XmlMapper.getInstance());	
   }	
	
   public void setModelKeys(Set modelKeys) {	
      this.modelKeys = modelKeys;	
   }	
	
   protected Object filterModel(Map model) {	
      Map a = new HashMap(model.size());	
      Set a = !CollectionUtils.isEmpty(this.modelKeys) ? this.modelKeys : model.keySet();	
      Iterator var4 = model.entrySet().iterator();	
	
      while(var4.hasNext()) {	
         Entry a;	
         if (!((a = (Entry)var4.next()).getValue() instanceof BindingResult) && a.contains(a.getKey()) && !((String)a.getKey()).equals(com.fasterxml.jackson.annotation.JsonView.class.getName()) && !((String)a.getKey()).equals(FilterProvider.class.getName())) {	
            a.put(a.getKey(), a.getValue());	
         }	
      }	
	
      if (this.extractValueFromSingleKeyModel && a.size() == 1) {	
         return a.values().iterator().next();	
      } else {	
         return a;	
      }	
   }	
	
   public final Set getModelKeys() {	
      return this.modelKeys;	
   }	
}	
