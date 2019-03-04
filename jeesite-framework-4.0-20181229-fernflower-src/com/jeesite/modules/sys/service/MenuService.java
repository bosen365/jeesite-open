package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.l.i.D;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.service.TreeService;	
import com.jeesite.modules.sys.dao.MenuDao;	
import com.jeesite.modules.sys.entity.Menu;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;	
	
@Service	
@Transactional(	
   readOnly = true	
)	
public class MenuService extends TreeService {	
   private static final String CACHE_MENU_NAME_PATH_MAP = "menuNamePathMap";	
	
   public List findList(Menu menu) {	
      return super.findList(menu);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void updateTreeSort(Menu menu) {	
      super.updateTreeSort(menu);	
      this.clearMenuCache();	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void enableByModuleCodes(String moduleCodes) {	
      Menu a;	
      (a = new Menu()).setStatus("0");	
      Menu a = new Menu();	
      a.setModuleCodes(moduleCodes);	
      ((MenuDao)this.dao).updateStatusByEntity(a, a);	
   }	
	
   protected void updateChildNode(Menu childEntity, Menu parentEntity) {	
      childEntity.setSysCode(parentEntity.getSysCode());	
      childEntity.getSqlMap().updateTreeDataExtSql("sys_code = #{sysCode}");	
      super.updateChildNode(childEntity, parentEntity);	
   }	
	
   public String getMenuNamePath(String href, String permission) {	
      Object a;	
      int var10000;	
      if ((a = (Map)CacheUtils.get("menuNamePathMap")) == null) {	
         a = MapUtils.newLinkedHashMap();	
         Iterator var5 = this.findList(new Menu()).iterator();	
	
         label64:	
         while(true) {	
            Menu a;	
            do {	
               if (!var5.hasNext()) {	
                  CacheUtils.put("menuNamePathMap", a);	
                  break label64;	
               }	
	
               String a;	
               if (StringUtils.isNotBlank(a = StringUtils.substringBefore((a = (Menu)var5.next()).getMenuHref(), "?"))) {	
                  if (StringUtils.endsWith(a, "/")) {	
                     a = StringUtils.substring(a, a.length() - 1);	
                  }	
	
                  ((Map)a).put(a, a.getTreeNames());	
                  ((Map)a).put(StringUtils.substringBeforeLast(a, "/"), a.getTreeNames());	
               }	
            } while(!StringUtils.isNotBlank(a.getPermission()));	
	
            String[] var8;	
            int var9 = (var8 = StringUtils.split(a.getPermission(), ",")).length;	
	
            int var10;	
            for(var10000 = var10 = 0; var10000 < var9; var10000 = var10) {	
               String a = var8[var10];	
               ((Map)a).put(a, a.getTreeNames());	
               String var10002 = StringUtils.substringBeforeLast(a, ":");	
               String var10003 = a.getTreeNames();	
               ++var10;	
               ((Map)a).put(var10002, StringUtils.substringBeforeLast(var10003, "/"));	
            }	
         }	
      }	
	
      if (StringUtils.endsWith(href, "/")) {	
         href = StringUtils.substring(href, href.length() - 1);	
      }	
	
      String a;	
      String var16;	
      label48: {	
         if ((a = (String)((Map)a).get(href)) == null) {	
            String[] var12;	
            int var13 = (var12 = StringUtils.split(permission, ",")).length;	
	
            int var14;	
            for(var10000 = var14 = 0; var10000 < var13; var10000 = var14) {	
               String a = var12[var14];	
               if (StringUtils.isNotBlank(a = (String)((Map)a).get(a))) {	
                  var16 = a;	
                  break label48;	
               }	
	
               ++var14;	
            }	
         }	
	
         var16 = a;	
      }	
	
      if (var16 == null) {	
         a = (String)((Map)a).get(StringUtils.substringBeforeLast(href, "/"));	
      }	
	
      return a == null ? "" : a;	
   }	
	
   public List findByUserCode(Menu menu) {	
      return ((MenuDao)this.dao).findByUserCode(menu);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void save(Menu entity) {	
      super.save((TreeEntity)entity);	
      this.clearMenuCache();	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void disableByModuleCodes(String moduleCodes) {	
      Menu a;	
      (a = new Menu()).setStatus("2");	
      Menu a = new Menu();	
      a.setModuleCodes(moduleCodes);	
      ((MenuDao)this.dao).updateStatusByEntity(a, a);	
      this.clearMenuCache();	
   }	
	
   // $FF: synthetic method	
   private void clearMenuCache() {	
      UserUtils.removeCache("menuList");	
      UserUtils.removeCache("authInfo");	
      CacheUtils.remove("menuNamePathMap");	
   }	
	
   public Menu get(Menu menu) {	
      return (Menu)super.get(menu);	
   }	
	
   @Transactional(	
      readOnly = false	
   )	
   public void delete(Menu entity) {	
      super.delete((TreeEntity)entity);	
      this.clearMenuCache();	
   }	
	
   public List findByRoleCode(Menu menu) {	
      return ((MenuDao)this.dao).findByRoleCode(menu);	
   }	
}	
