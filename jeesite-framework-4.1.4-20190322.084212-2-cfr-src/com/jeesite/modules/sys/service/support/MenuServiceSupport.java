/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.l.h;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.service.TreeService;	
import com.jeesite.modules.sys.dao.MenuDao;	
import com.jeesite.modules.sys.entity.Menu;	
import com.jeesite.modules.sys.service.MenuService;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.Serializable;	
import java.util.Iterator;	
import java.util.LinkedHashMap;	
import java.util.List;	
import org.apache.shiro.session.Session;	
import org.hyperic.sigar.SysInfo;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class MenuServiceSupport	
extends TreeService<MenuDao, Menu>	
implements MenuService {	
    private static final String CACHE_MENU_NAME_PATH_MAP = "menuNamePathMap";	
	
    @Override	
    public List<Menu> findList(Menu menu) {	
        return super.findList(menu);	
    }	
	
    @Override	
    protected void updateChildNode(Menu childEntity, Menu parentEntity) {	
        Menu menu = childEntity;	
        menu.setSysCode(parentEntity.getSysCode());	
        menu.getSqlMap().updateTreeDataExtSql("sys_code = #{sysCode}");	
        super.updateChildNode(childEntity, parentEntity);	
    }	
	
    @Override	
    public String getMenuNamePath(String href, String permission) {	
        Object a;	
        Object object;	
        LinkedHashMap<String, String> a2;	
        block11 : {	
            String[] arrstring;	
            a2 = (LinkedHashMap<String, String>)CacheUtils.get(CACHE_MENU_NAME_PATH_MAP);	
            if (a2 == null) {	
                a2 = MapUtils.newLinkedHashMap();	
                a = this.findList(new Menu());	
                arrstring = a.iterator();	
                while (arrstring.hasNext()) {	
                    int n;	
                    Menu a3 = (Menu)arrstring.next();	
                    String a4 = StringUtils.substringBefore(a3.getMenuHref(), "?");	
                    if (StringUtils.isNotBlank(a4)) {	
                        if (StringUtils.endsWith(a4, "/")) {	
                            String string = a4;	
                            a4 = StringUtils.substring(string, string.length() - 1);	
                        }	
                        a2.put(a4, a3.getTreeNames());	
                        a2.put(StringUtils.substringBeforeLast(a4, "/"), a3.getTreeNames());	
                    }	
                    if (!StringUtils.isNotBlank(a3.getPermission())) continue;	
                    String[] arrstring2 = StringUtils.split(a3.getPermission(), ",");	
                    int n2 = arrstring2.length;	
                    int n3 = n = 0;	
                    while (n3 < n2) {	
                        String a5 = arrstring2[n];	
                        a2.put(a5, a3.getTreeNames());	
                        a2.put(StringUtils.substringBeforeLast(a5, ":"), StringUtils.substringBeforeLast(a3.getTreeNames(), "/"));	
                        n3 = ++n;	
                    }	
                }	
                CacheUtils.put(CACHE_MENU_NAME_PATH_MAP, a2);	
            }	
            if (StringUtils.endsWith(href, "/")) {	
                String string = href;	
                href = StringUtils.substring(string, string.length() - 1);	
            }	
            if ((a = (String)a2.get(href)) == null) {	
                int a4;	
                arrstring = StringUtils.split(permission, ",");	
                int a3 = arrstring.length;	
                int n = a4 = 0;	
                while (n < a3) {	
                    String a6 = arrstring[a4];	
                    a = (String)a2.get(a6);	
                    if (StringUtils.isNotBlank((CharSequence)a)) {	
                        object = a;	
                        break block11;	
                    }	
                    n = ++a4;	
                }	
            }	
            object = a;	
        }	
        if (object == null) {	
            a = (String)a2.get(StringUtils.substringBeforeLast(href, "/"));	
        }	
        if (a == null) {	
            return "";	
        }	
        return a;	
    }	
	
    @Override	
    public List<Menu> findByUserCode(Menu menu) {	
        return ((MenuDao)this.dao).findByUserCode(menu);	
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
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(Menu entity) {	
        MenuServiceSupport menuServiceSupport = this;	
        super.save(entity);	
        menuServiceSupport.clearMenuCache();	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateTreeSort(Menu menu) {	
        MenuServiceSupport menuServiceSupport = this;	
        super.updateTreeSort(menu);	
        menuServiceSupport.clearMenuCache();	
    }	
	
    @Override	
    public List<Menu> findByRoleCode(Menu menu) {	
        return ((MenuDao)this.dao).findByRoleCode(menu);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void disableByModuleCodes(String moduleCodes) {	
        Menu a = new Menu();	
        a.setStatus("2");	
        Menu a2 = new Menu();	
        a2.setModuleCodes(moduleCodes);	
        ((MenuDao)this.dao).updateStatusByEntity(a, a2);	
        this.clearMenuCache();	
    }	
	
    private /* synthetic */ void clearMenuCache() {	
        UserUtils.removeCacheByKeyPrefix("menuList");	
        Session a = UserUtils.getSession();	
        UserUtils.removeCache(new StringBuilder().insert(0, "authInfo_").append(a.getId()).toString());	
        CacheUtils.remove(CACHE_MENU_NAME_PATH_MAP);	
    }	
	
    @Override	
    public Menu get(Menu menu) {	
        return super.get(menu);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(Menu entity) {	
        MenuServiceSupport menuServiceSupport = this;	
        super.delete(entity);	
        menuServiceSupport.clearMenuCache();	
    }	
}	
	
