/*	
 * Decompiled with CFR 0.139.	
 */	
package com.jeesite.modules.sys.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.annotation.MyBatisDao;	
import com.jeesite.modules.sys.entity.User;	
import java.util.List;	
	
@MyBatisDao(dataSourceName="default")	
public interface UserDao	
extends CrudDao<User> {	
    public List<User> findListByRoleCode(User var1);	
	
    public int updateLoginInfo(User var1);	
	
    public List<User> findCorpList(User var1);	
	
    public int updateMgrType(User var1);	
	
    public User getByUserTypeAndRefCode(User var1);	
	
    public User getByLoginCode(User var1);	
	
    public int updateUserInfo(User var1);	
	
    public int updatePassword(User var1);	
	
    public int updateQuestion(User var1);	
}	
	
