/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.annotations.InsertProvider	
 *  org.apache.ibatis.annotations.Param	
 *  org.apache.ibatis.annotations.SelectProvider	
 *  org.apache.ibatis.annotations.UpdateProvider	
 */	
package com.jeesite.common.dao;	
	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.mybatis.mapper.provider.InsertSqlProvider;	
import com.jeesite.common.mybatis.mapper.provider.SelectSqlProvider;	
import com.jeesite.common.mybatis.mapper.provider.UpdateSqlProvider;	
import java.util.List;	
import org.apache.ibatis.annotations.InsertProvider;	
import org.apache.ibatis.annotations.Param;	
import org.apache.ibatis.annotations.SelectProvider;	
import org.apache.ibatis.annotations.UpdateProvider;	
	
public interface CrudDao<T>	
extends QueryDao<T> {	
    @UpdateProvider(type=UpdateSqlProvider.class, method="updateByEntity")	
    public long updateByEntity(@Param(value="param1") T var1, @Param(value="param2") T var2);	
	
    @InsertProvider(type=InsertSqlProvider.class, method="insert")	
    public long insert(T var1);	
	
    @SelectProvider(type=SelectSqlProvider.class, method="getByEntity")	
    @Override	
    public T getByEntity(T var1);	
	
    @UpdateProvider(type=UpdateSqlProvider.class, method="update")	
    public long update(T var1);	
	
    @InsertProvider(type=InsertSqlProvider.class, method="insertBatch")	
    public long insertBatch(@Param(value="param1") List<T> var1);	
	
    @UpdateProvider(type=UpdateSqlProvider.class, method="updateStatusByEntity")	
    public long updateStatusByEntity(@Param(value="param1") T var1, @Param(value="param2") T var2);	
	
    @UpdateProvider(type=UpdateSqlProvider.class, method="delete")	
    public long delete(T var1);	
	
    @UpdateProvider(type=UpdateSqlProvider.class, method="phyDelete")	
    public int phyDelete(T var1);	
	
    @SelectProvider(type=SelectSqlProvider.class, method="get")	
    @Override	
    public T get(T var1);	
	
    @UpdateProvider(type=UpdateSqlProvider.class, method="deleteByEntity")	
    public long deleteByEntity(T var1);	
	
    @SelectProvider(type=SelectSqlProvider.class, method="findList")	
    @Override	
    public List<T> findList(T var1);	
	
    @SelectProvider(type=SelectSqlProvider.class, method="findCount")	
    @Override	
    public long findCount(T var1);	
	
    @UpdateProvider(type=UpdateSqlProvider.class, method="phyDeleteByEntity")	
    public int phyDeleteByEntity(T var1);	
	
    @UpdateProvider(type=UpdateSqlProvider.class, method="updateStatus")	
    public long updateStatus(T var1);	
}	
	
