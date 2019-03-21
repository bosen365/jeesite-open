/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.apache.shiro.session.Session	
 *  org.springframework.transaction.annotation.Transactional	
 */	
package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.cache.JedisUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.service.TreeService;	
import com.jeesite.modules.sys.dao.MenuDao;	
import com.jeesite.modules.sys.entity.Menu;	
import com.jeesite.modules.sys.service.MenuService;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.Serializable;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.apache.shiro.session.Session;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class MenuServiceSupport	
extends TreeService<MenuDao, Menu>	
implements MenuService {	
    private static final String CACHE_MENU_NAME_PATH_MAP = "menuNamePathMap";	
	
    private /* synthetic */ void clearMenuCache() {	
        UserUtils.removeCacheByKeyPrefix("menuList");	
        Session a = UserUtils.getSession();	
        UserUtils.removeCache(new StringBuilder().insert(0, "authInfo_").append(a.getId()).toString());	
        CacheUtils.remove(CACHE_MENU_NAME_PATH_MAP);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(Menu entity) {	
        MenuServiceSupport menuServiceSupport = this;	
        super.save(entity);	
        menuServiceSupport.clearMenuCache();	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void disableByModuleCodes(String moduleCodes) {	
        Menu a = new Menu();	
        a.setStatus("2");	
        Menu a2 = new Menu();	
        a2.setModuleCodes(moduleCodes);	
        this.clearMenuCache();	
        ((MenuDao)this.dao).updateStatusByEntity(a, a2);	
    }	
	
    @Override	
    protected void updateChildNode(Menu childEntity, Menu parentEntity) {	
        Menu menu = childEntity;	
        menu.setSysCode(parentEntity.getSysCode());	
        super.updateChildNode(childEntity, parentEntity);	
        menu.getSqlMap().updateTreeDataExtSql("sys_code = #{sysCode}");	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(Menu entity) {	
        MenuServiceSupport menuServiceSupport = this;	
        super.delete(entity);	
        menuServiceSupport.clearMenuCache();	
    }	
	
    @Override	
    public List<Menu> findByUserCode(Menu menu) {	
        return ((MenuDao)this.dao).findByUserCode(menu);	
    }	
	
    @Override	
    public Menu get(Menu menu) {	
        return super.get(menu);	
    }	
	
    @Override	
    public List<Menu> findByRoleCode(Menu menu) {	
        return ((MenuDao)this.dao).findByRoleCode(menu);	
    }	
	
    @Override	
    public List<Menu> findList(Menu menu) {	
        return super.findList(menu);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateTreeSort(Menu menu) {	
        MenuServiceSupport menuServiceSupport = this;	
        super.updateTreeSort(menu);	
        menuServiceSupport.clearMenuCache();	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void enableByModuleCodes(String moduleCodes) {	
        Menu a = new Menu();	
        a.setStatus("0");	
        Menu a2 = new Menu();	
        a2.setModuleCodes(moduleCodes);	
        ((MenuDao)this.dao).updateStatusByEntity(a, a2);	
    }	
	
    @Override	
    public String getMenuNamePath(String href, String permission) {	
        Map a;	
        Object a2;	
        Object object;	
        block11 : {	
            String[] arrstring;	
            a = (Map)CacheUtils.get(CACHE_MENU_NAME_PATH_MAP);	
            if (a == null) {	
                a = MapUtils.newLinkedHashMap();	
                a2 = this.findList(new Menu());	
                arrstring = a2.iterator();	
                while (arrstring.hasNext()) {	
                    int n;	
                    Menu a3 = (Menu)arrstring.next();	
                    String a4 = StringUtils.substringBefore((String)a3.getMenuHref(), (String)"?");	
                    if (StringUtils.isNotBlank((CharSequence)a4)) {	
                        if (StringUtils.endsWith((CharSequence)a4, (CharSequence)"/")) {	
                            String string = a4;	
                            a4 = StringUtils.substring((String)string, (int)(string.length() - 1));	
                        }	
                        a.put(StringUtils.substringBeforeLast((String)a4, (String)"/"), a3.getTreeNames());	
                        a.put(a4, a3.getTreeNames());	
                    }	
                    if (!StringUtils.isNotBlank((CharSequence)a3.getPermission())) continue;	
                    String[] arrstring2 = StringUtils.split((String)a3.getPermission(), (String)",");	
                    int n2 = arrstring2.length;	
                    int n3 = n = 0;	
                    while (n3 < n2) {	
                        String a5 = arrstring2[n];	
                        a.put(StringUtils.substringBeforeLast((String)a5, (String)":"), StringUtils.substringBeforeLast((String)a3.getTreeNames(), (String)"/"));	
                        a.put(a5, a3.getTreeNames());	
                        n3 = ++n;	
                    }	
                }	
                CacheUtils.put(CACHE_MENU_NAME_PATH_MAP, a);	
            }	
            if (StringUtils.endsWith((CharSequence)href, (CharSequence)"/")) {	
                String string = href;	
                href = StringUtils.substring((String)string, (int)(string.length() - 1));	
            }	
            if ((a2 = (String)a.get(href)) == null) {	
                int a4;	
                arrstring = StringUtils.split((String)permission, (String)",");	
                int a3 = arrstring.length;	
                int n = a4 = 0;	
                while (n < a3) {	
                    String a6 = arrstring[a4];	
                    a2 = (String)a.get(a6);	
                    if (StringUtils.isNotBlank((CharSequence)a2)) {	
                        object = a2;	
                        break block11;	
                    }	
                    n = ++a4;	
                }	
            }	
            object = a2;	
        }	
        if (object == null) {	
            a2 = (String)a.get(StringUtils.substringBeforeLast((String)href, (String)"/"));	
        }	
        if (a2 == null) {	
            return "";	
        }	
        return a2;	
    }	
}	
	
