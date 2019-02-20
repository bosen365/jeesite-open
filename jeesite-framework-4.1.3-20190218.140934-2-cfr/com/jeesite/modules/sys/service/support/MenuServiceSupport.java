/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  org.apache.shiro.session.Session	
 *  org.springframework.transaction.annotation.Transactional	
 */	
package com.jeesite.modules.sys.service.support;	
	
import com.jeesite.common.cache.CacheUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.service.TreeService;	
import com.jeesite.modules.sys.dao.MenuDao;	
import com.jeesite.modules.sys.entity.Menu;	
import com.jeesite.modules.sys.service.MenuService;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.Serializable;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import org.apache.shiro.session.Session;	
import org.hyperic.sigar.CpuPerc;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public class MenuServiceSupport	
extends TreeService<MenuDao, Menu>	
implements MenuService {	
    private static final String CACHE_MENU_NAME_PATH_MAP = "menuNamePathMap";	
	
    @Override	
    public List<Menu> findByUserCode(Menu menu) {	
        return ((MenuDao)this.dao).findByUserCode(menu);	
    }	
	
    private /* synthetic */ void clearMenuCache() {	
        UserUtils.removeCacheByKeyPrefix("menuList");	
        Session a2 = UserUtils.getSession();	
        UserUtils.removeCache(new StringBuilder().insert(0, "authInfo_").append(a2.getId()).toString());	
        CacheUtils.remove(CACHE_MENU_NAME_PATH_MAP);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void disableByModuleCodes(String moduleCodes) {	
        Menu a2 = new Menu();	
        a2.setStatus("2");	
        Menu a3 = new Menu();	
        a3.setModuleCodes(moduleCodes);	
        this.clearMenuCache();	
        ((MenuDao)this.dao).updateStatusByEntity(a2, a3);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateTreeSort(Menu menu) {	
        MenuServiceSupport menuServiceSupport = this;	
        super.updateTreeSort(menu);	
        menuServiceSupport.clearMenuCache();	
    }	
	
    @Override	
    public String getMenuNamePath(String href, String permission) {	
        Map a2;	
        Object a3;	
        Object object;	
        block11 : {	
            String[] arrstring;	
            a2 = (Map)CacheUtils.get(CACHE_MENU_NAME_PATH_MAP);	
            if (a2 == null) {	
                a2 = MapUtils.newLinkedHashMap();	
                a3 = this.findList(new Menu());	
                arrstring = a3.iterator();	
                while (arrstring.hasNext()) {	
                    int n;	
                    Menu a4 = (Menu)arrstring.next();	
                    String a5 = StringUtils.substringBefore((String)a4.getMenuHref(), (String)"?");	
                    if (StringUtils.isNotBlank((CharSequence)a5)) {	
                        if (StringUtils.endsWith((CharSequence)a5, (CharSequence)"/")) {	
                            String string = a5;	
                            a5 = StringUtils.substring((String)string, (int)(string.length() - 1));	
                        }	
                        a2.put(StringUtils.substringBeforeLast((String)a5, (String)"/"), a4.getTreeNames());	
                        a2.put(a5, a4.getTreeNames());	
                    }	
                    if (!StringUtils.isNotBlank((CharSequence)a4.getPermission())) continue;	
                    String[] arrstring2 = StringUtils.split((String)a4.getPermission(), (String)",");	
                    int n2 = arrstring2.length;	
                    int n3 = n = 0;	
                    while (n3 < n2) {	
                        String a6 = arrstring2[n];	
                        a2.put(StringUtils.substringBeforeLast((String)a6, (String)":"), StringUtils.substringBeforeLast((String)a4.getTreeNames(), (String)"/"));	
                        a2.put(a6, a4.getTreeNames());	
                        n3 = ++n;	
                    }	
                }	
                CacheUtils.put(CACHE_MENU_NAME_PATH_MAP, a2);	
            }	
            if (StringUtils.endsWith((CharSequence)href, (CharSequence)"/")) {	
                String string = href;	
                href = StringUtils.substring((String)string, (int)(string.length() - 1));	
            }	
            if ((a3 = (String)a2.get(href)) == null) {	
                int a5;	
                arrstring = StringUtils.split((String)permission, (String)",");	
                int a4 = arrstring.length;	
                int n = a5 = 0;	
                while (n < a4) {	
                    String a7 = arrstring[a5];	
                    a3 = (String)a2.get(a7);	
                    if (StringUtils.isNotBlank((CharSequence)a3)) {	
                        object = a3;	
                        break block11;	
                    }	
                    n = ++a5;	
                }	
            }	
            object = a3;	
        }	
        if (object == null) {	
            a3 = (String)a2.get(StringUtils.substringBeforeLast((String)href, (String)"/"));	
        }	
        if (a3 == null) {	
            return "";	
        }	
        return a3;	
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
    public Menu get(Menu menu) {	
        return super.get(menu);	
    }	
	
    @Override	
    public List<Menu> findList(Menu menu) {	
        return super.findList(menu);	
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
    public void enableByModuleCodes(String moduleCodes) {	
        Menu a2 = new Menu();	
        a2.setStatus("0");	
        Menu a3 = new Menu();	
        a3.setModuleCodes(moduleCodes);	
        ((MenuDao)this.dao).updateStatusByEntity(a2, a3);	
    }	
	
    @Override	
    public List<Menu> findByRoleCode(Menu menu) {	
        return ((MenuDao)this.dao).findByRoleCode(menu);	
    }	
}	
	
