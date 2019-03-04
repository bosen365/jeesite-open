package com.jeesite.common.dao;	
	
import com.jeesite.common.mybatis.mapper.provider.SelectSqlProvider;	
import com.jeesite.common.mybatis.mapper.provider.TreeSqlProvider;	
import java.util.List;	
import org.apache.ibatis.annotations.SelectProvider;	
import org.apache.ibatis.annotations.UpdateProvider;	
	
public interface TreeDao extends CrudDao {	
   @SelectProvider(	
      type = TreeSqlProvider.class,	
      method = "findByParentCodesLike"	
   )	
   List findByParentCodesLike(Object var1);	
	
   @UpdateProvider(	
      type = TreeSqlProvider.class,	
      method = "updateTreeSort"	
   )	
   long updateTreeSort(Object var1);	
	
   @UpdateProvider(	
      type = TreeSqlProvider.class,	
      method = "updateTreeData"	
   )	
   long updateTreeData(Object var1);	
	
   @SelectProvider(	
      type = SelectSqlProvider.class,	
      method = "findList"	
   )	
   List findList(Object var1);	
	
   @UpdateProvider(	
      type = TreeSqlProvider.class,	
      method = "updateTreeLeaf"	
   )	
   long updateTreeLeaf(Object var1);	
	
   @SelectProvider(	
      type = SelectSqlProvider.class,	
      method = "get"	
   )	
   Object get(Object var1);	
	
   @SelectProvider(	
      type = SelectSqlProvider.class,	
      method = "getByEntity"	
   )	
   Object getByEntity(Object var1);	
	
   @SelectProvider(	
      type = SelectSqlProvider.class,	
      method = "findCount"	
   )	
   long findCount(Object var1);	
}	
