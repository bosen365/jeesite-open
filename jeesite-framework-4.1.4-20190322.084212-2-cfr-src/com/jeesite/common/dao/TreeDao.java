/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.dao;	
	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.mybatis.mapper.provider.SelectSqlProvider;	
import com.jeesite.common.mybatis.mapper.provider.TreeSqlProvider;	
import java.util.List;	
import org.apache.ibatis.annotations.SelectProvider;	
import org.apache.ibatis.annotations.UpdateProvider;	
	
public interface TreeDao<T>	
extends CrudDao<T> {	
    @SelectProvider(type=SelectSqlProvider.class, method="get")	
    @Override	
    public T get(T var1);	
	
    @UpdateProvider(type=TreeSqlProvider.class, method="updateTreeData")	
    public long updateTreeData(T var1);	
	
    @SelectProvider(type=TreeSqlProvider.class, method="findByParentCodesLike")	
    public List<T> findByParentCodesLike(T var1);	
	
    @SelectProvider(type=SelectSqlProvider.class, method="findCount")	
    @Override	
    public long findCount(T var1);	
	
    @UpdateProvider(type=TreeSqlProvider.class, method="updateTreeSort")	
    public long updateTreeSort(T var1);	
	
    @SelectProvider(type=SelectSqlProvider.class, method="findList")	
    @Override	
    public List<T> findList(T var1);	
	
    @UpdateProvider(type=TreeSqlProvider.class, method="updateTreeLeaf")	
    public long updateTreeLeaf(T var1);	
	
    @SelectProvider(type=SelectSqlProvider.class, method="getByEntity")	
    @Override	
    public T getByEntity(T var1);	
}	
	
