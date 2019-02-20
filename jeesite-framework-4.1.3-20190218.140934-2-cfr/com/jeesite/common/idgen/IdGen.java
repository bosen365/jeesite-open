/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.idgen.IdGenerate	
 *  org.apache.shiro.session.Session	
 *  org.apache.shiro.session.mgt.eis.SessionIdGenerator	
 */	
package com.jeesite.common.idgen;	
	
import com.jeesite.common.idgen.IdGenerate;	
import java.io.Serializable;	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;	
	
public class IdGen	
extends IdGenerate	
implements SessionIdGenerator {	
    public Serializable generateId(Session session) {	
        return IdGen.uuid();	
    }	
}	
	
