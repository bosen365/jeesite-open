package com.jeesite.common.idgen;	
	
import java.io.Serializable;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;	
	
public class IdGen extends IdGenerate implements SessionIdGenerator {	
   public Serializable generateId(Session session) {	
      return uuid();	
   }	
}	
