package com.jeesite.modules.msg.entity.content;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.entity.Extend;	
import java.util.List;	
import java.util.Map;	
import org.hyperic.sigar.Tcp;	
	
public class PcMsgContent extends BaseMsgContent {	
   private List buttons = ListUtils.newArrayList();	
   private static final long serialVersionUID = 1L;	
   private Long timeout;	
   private String type;	
	
   public void addButton(String... values) {	
      if (values != null) {	
         Map a = MapUtils.newHashMap();	
	
         int a;	
         for(int var10000 = a = 0; var10000 < values.length; var10000 = a) {	
            switch(a) {	
            case 0:	
               while(false) {	
               }	
	
               a.put("name", values[a]);	
               break;	
            case 1:	
               a.put("href", values[a]);	
            }	
	
            ++a;	
         }	
	
         this.buttons.add(a);	
      }	
   }	
	
   public String getType() {	
      return this.type;	
   }	
	
   public void setType(String type) {	
      this.type = type;	
   }	
	
   public List getButtons() {	
      return this.buttons;	
   }	
	
   public void setTimeout(Long timeout) {	
      this.timeout = timeout;	
   }	
	
   public Long getTimeout() {	
      return this.timeout;	
   }	
	
   public void setButtons(List buttons) {	
      this.buttons = buttons;	
   }	
	
   public String getMsgType() {	
      return "pc";	
   }	
}	
