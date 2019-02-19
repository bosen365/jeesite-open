package com.jeesite.common.web.j;	
	
import com.fasterxml.jackson.annotation.JsonView;	
import com.fasterxml.jackson.databind.ser.FilterProvider;	
import com.jeesite.common.entity.Extend;	
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
	
public class m extends MappingJackson2XmlView {	
   private boolean c = false;	
   private Set ALLATORIxDEMO;	
	
   public final Set ALLATORIxDEMO() {	
      return this.ALLATORIxDEMO;	
   }	
	
   public void ALLATORIxDEMO(Set modelKeys) {	
      this.ALLATORIxDEMO = modelKeys;	
   }	
	
   public void setModelKey(String modelKey) {	
      this.ALLATORIxDEMO = Collections.singleton(modelKey);	
   }	
	
   public m() {	
      this.setContentType("application/xml");	
      this.setObjectMapper(XmlMapper.getInstance());	
   }	
	
   protected Object filterModel(Map model) {	
      Map a = new HashMap(model.size());	
      Set a = !CollectionUtils.isEmpty(this.ALLATORIxDEMO) ? this.ALLATORIxDEMO : model.keySet();	
      Iterator var4 = model.entrySet().iterator();	
	
      while(var4.hasNext()) {	
         Entry a;	
         if (!((a = (Entry)var4.next()).getValue() instanceof BindingResult) && a.contains(a.getKey()) && !((String)a.getKey()).equals(JsonView.class.getName()) && !((String)a.getKey()).equals(FilterProvider.class.getName())) {	
            a.put(a.getKey(), a.getValue());	
         }	
      }	
	
      if (this.c && a.size() == 1) {	
         return a.values().iterator().next();	
      } else {	
         return a;	
      }	
   }	
	
   public void ALLATORIxDEMO(boolean extractValueFromSingleKeyModel) {	
      this.c = extractValueFromSingleKeyModel;	
   }	
}	
