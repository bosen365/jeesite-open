package com.jeesite.common.dao;	
	
import com.jeesite.common.mybatis.mapper.provider.InsertSqlProvider;	
import com.jeesite.common.mybatis.mapper.provider.SelectSqlProvider;	
import com.jeesite.common.mybatis.mapper.provider.UpdateSqlProvider;	
import java.util.List;	
import org.apache.ibatis.annotations.InsertProvider;	
import org.apache.ibatis.annotations.Param;	
import org.apache.ibatis.annotations.SelectProvider;	
import org.apache.ibatis.annotations.UpdateProvider;	
	
public interface CrudDao extends QueryDao {	
   @UpdateProvider(	
      type = UpdateSqlProvider.class,	
      method = "updateStatus"	
   )	
   long updateStatus(Object var1);	
	
   @UpdateProvider(	
      type = UpdateSqlProvider.class,	
      method = "deleteByEntity"	
   )	
   long deleteByEntity(Object var1);	
	
   @InsertProvider(	
      type = InsertSqlProvider.class,	
      method = "insertBatch"	
   )	
   long insertBatch(@Param("param1") List var1);	
	
   @SelectProvider(	
      type = SelectSqlProvider.class,	
      method = "findCount"	
   )	
   long findCount(Object var1);	
	
   @SelectProvider(	
      type = SelectSqlProvider.class,	
      method = "get"	
   )	
   Object get(Object var1);	
	
   @InsertProvider(	
      type = InsertSqlProvider.class,	
      method = "insert"	
   )	
   long insert(Object var1);	
	
   @SelectProvider(	
      type = SelectSqlProvider.class,	
      method = "findList"	
   )	
   List findList(Object var1);	
	
   @UpdateProvider(	
      type = UpdateSqlProvider.class,	
      method = "updateStatusByEntity"	
   )	
   long updateStatusByEntity(@Param("param1") Object var1, @Param("param2") Object var2);	
	
   @SelectProvider(	
      type = SelectSqlProvider.class,	
      method = "getByEntity"	
   )	
   Object getByEntity(Object var1);	
	
   @UpdateProvider(	
      type = UpdateSqlProvider.class,	
      method = "updateByEntity"	
   )	
   long updateByEntity(@Param("param1") Object var1, @Param("param2") Object var2);	
	
   @UpdateProvider(	
      type = UpdateSqlProvider.class,	
      method = "delete"	
   )	
   long delete(Object var1);	
	
   @UpdateProvider(	
      type = UpdateSqlProvider.class,	
      method = "update"	
   )	
   long update(Object var1);	
}	
