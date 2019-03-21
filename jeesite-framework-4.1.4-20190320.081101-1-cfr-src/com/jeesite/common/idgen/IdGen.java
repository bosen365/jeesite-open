/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.idgen;	
	
import com.jeesite.common.idgen.IdGenerate;	
import java.io.Serializable;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;	
	
public class IdGen	
extends IdGenerate	
implements SessionIdGenerator {	
    @Override	
    public Serializable generateId(Session session) {	
        return IdGen.uuid();	
    }	
}	
	
