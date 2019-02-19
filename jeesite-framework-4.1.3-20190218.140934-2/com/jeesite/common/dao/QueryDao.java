package com.jeesite.common.dao;	
	
import com.jeesite.common.mybatis.mapper.provider.SelectSqlProvider;	
import java.util.List;	
import org.apache.ibatis.annotations.SelectProvider;	
	
public interface QueryDao extends BaseDao {	
   @SelectProvider(	
      type = SelectSqlProvider.class,	
      method = "get"	
   )	
   Object get(Object var1);	
	
   @SelectProvider(	
      type = SelectSqlProvider.class,	
      method = "findList"	
   )	
   List findList(Object var1);	
	
   @SelectProvider(	
      type = SelectSqlProvider.class,	
      method = "findCount"	
   )	
   long findCount(Object var1);	
	
   @SelectProvider(	
      type = SelectSqlProvider.class,	
      method = "getByEntity"	
   )	
   Object getByEntity(Object var1);	
}	
