/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.dao;	
	
import com.jeesite.common.dao.BaseDao;	
import com.jeesite.common.mybatis.mapper.provider.SelectSqlProvider;	
import java.util.List;	
import org.apache.ibatis.annotations.SelectProvider;	
	
public interface QueryDao<T>	
extends BaseDao {	
    @SelectProvider(type=SelectSqlProvider.class, method="getByEntity")	
    public T getByEntity(T var1);	
	
    @SelectProvider(type=SelectSqlProvider.class, method="findList")	
    public List<T> findList(T var1);	
	
    @SelectProvider(type=SelectSqlProvider.class, method="findCount")	
    public long findCount(T var1);	
	
    @SelectProvider(type=SelectSqlProvider.class, method="get")	
    public T get(T var1);	
}	
	
