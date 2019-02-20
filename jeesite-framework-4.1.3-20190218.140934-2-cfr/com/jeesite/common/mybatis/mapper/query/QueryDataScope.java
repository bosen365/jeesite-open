/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.SetUtils	
 *  com.jeesite.common.lang.ExceptionUtils	
 *  com.jeesite.common.lang.ObjectUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  com.jeesite.common.mapper.JsonMapper	
 *  com.jeesite.common.reflect.ReflectUtils	
 */	
package com.jeesite.common.mybatis.mapper.query;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.SetUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.lang.ExceptionUtils;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mapper.JsonMapper;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.MapperException;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.mybatis.mapper.SqlMap;	
import com.jeesite.common.reflect.ReflectUtils;	
import com.jeesite.common.service.ServiceException;	
import com.jeesite.common.utils.SpringUtils;	
import com.jeesite.modules.sys.dao.UserDataScopeDao;	
import com.jeesite.modules.sys.entity.Role;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.entity.UserDataScope;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.io.Serializable;	
import java.util.ArrayList;	
import java.util.HashSet;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import java.util.Set;	
import org.hyperic.sigar.FileWatcher;	
import org.hyperic.sigar.shell.ShellCommandUsageException;	
	
public class QueryDataScope	
implements Serializable {	
    public static final String USER_CACHE_USER_DATA_SCOPE_ = "userDataScope_";	
    private static UserDataScopeDao userDataScopeDao;	
    private static final long serialVersionUID = 1L;	
    public static final String USER_CACHE_ROLE_EXTEND_DATA_SCOPES = "roleExtendDataScopes";	
    private Map<String, Map<String, Map<String, String>>> roleExtendDataScopes;	
    private BaseEntity<?> entity;	
	
    public QueryDataScope clearFilter(String sqlMapKey) {	
        SqlMap a2;	
        QueryDataScope queryDataScope = this;	
        a2.remove(new StringBuilder().insert(0, sqlMapKey).append("Where").toString());	
        a2 = queryDataScope.entity.getSqlMap();	
        a2.remove(sqlMapKey + "From");	
        a2.remove(sqlMapKey);	
        return queryDataScope;	
    }	
	
    private /* synthetic */ void addRoleDataScope(String sqlMapKey, List<String> roleList, String ctrlType, String ctrlPermi, String bizCtrlDataField, String bizCtrlUserField) {	
        if (roleList.size() > 0) {	
            void a2;	
            StringBuilder a3 = new StringBuilder();	
            StringBuilder a4 = new StringBuilder();	
            String string = new StringBuilder().insert(0, "a").append(IdGen.randomBase62((int)4)).toString();	
            StringBuilder a5 = new StringBuilder();	
            this.addFilter(sqlMapKey, a5.toString());	
            a5.append(new StringBuilder().insert(0, " AND ctrl_data = ").append(bizCtrlDataField).append(")").toString());	
            a5.append(new StringBuilder().insert(0, " AND role_code #N ('").append(StringUtils.join(roleList, (String)"','")).append("')").toString());	
            a5.append(new StringBuilder().insert(0, " AND ctrl_type = '").append(ctrlType).append("'").toString());	
            a5.append(new StringBuilder().insert(0, " WHERE ctrl_permi = '").append(ctrlPermi).append("'").toString());	
            a5.append(new StringBuilder().insert(0, " EXISTS (SELECT 1 FROM ").append(Global.getTablePrefix()).append("sys_role_data_scope").toString());	
            this.addJoinWhere(sqlMapKey, a4.toString());	
            this.addJoinFrom(sqlMapKey, a3.toString());	
            a4.append(new StringBuilder().insert(0, " AND ").append((String)a2).append(".role_code IN ('").append(StringUtils.join(roleList, (String)"','")).append("'))").toString());	
            a4.append(new StringBuilder().insert(0, " AND ").append((String)a2).append(".ctrl_type = '").append(ctrlType).append("'").toString());	
            a4.append(new StringBuilder().insert(0, " OR (").append((String)a2).append(".ctrl_permi = '").append(ctrlPermi).append("'").toString());	
            a3.append(new StringBuilder().insert(0, " ON ").append((String)a2).append(".ctrl_data = ").append(bizCtrlDataField).toString());	
            a3.append(" JO#N " + Global.getTablePrefix() + "sys_role_data_scope " + (String)a2);	
        }	
    }	
	
    private /* synthetic */ QueryDataScope addJoinWhere(String string, String string2) {	
        void sqlWhere;	
        void sqlMapKey;	
        return this.addFilter((String)sqlMapKey + "Where", (String)sqlWhere);	
    }	
	
    private /* synthetic */ String removeTheStartingAndOr(String sql) {	
        if (StringUtils.startsWithIgnoreCase((CharSequence)(sql = StringUtils.trim((String)sql)), (CharSequence)"AND ")) {	
            sql = sql.substring("AND ".length());	
        }	
        if (StringUtils.startsWithIgnoreCase((CharSequence)sql, (CharSequence)"OR ")) {	
            sql = sql.substring("OR ".length());	
        }	
        return sql;	
    }	
	
    public QueryDataScope addFilter(String sqlMapKey, String sqlWhere) {	
        SqlMap a2 = this.entity.getSqlMap();	
        String a3 = ObjectUtils.toString(a2.get(sqlMapKey));	
        if (StringUtils.isNotBlank((CharSequence)a3)) {	
            a2.add(sqlMapKey, "AND ((" + this.removeTheStartingAndOr(a3) + ") OR (" + this.removeTheStartingAndOr(sqlWhere) + "))");	
            return this;	
        }	
        a2.add(sqlMapKey, new StringBuilder().insert(0, "AND ").append(sqlWhere).toString());	
        return this;	
    }	
	
    public QueryDataScope addFilter(String sqlMapKey, String ctrlTypes, String bizCtrlDataFields, String ctrlPermi) {	
        QueryDataScope queryDataScope = this;	
        queryDataScope.addFilter(sqlMapKey, ctrlTypes, bizCtrlDataFields, null, ctrlPermi);	
        return queryDataScope;	
    }	
	
    private /* synthetic */ void addUserDataScope(String sqlMapKey, User user, String ctrlType, String ctrlPermi, String bizCtrlDataField, String bizCtrlUserField) {	
        StringBuilder a2;	
        Long a3 = (Long)UserUtils.getCache(new StringBuilder().insert(0, USER_CACHE_USER_DATA_SCOPE_).append(ctrlType).append(ctrlPermi).toString());	
        if (a3 == null) {	
            UserDataScope userDataScope = new UserDataScope();	
            void v0 = a2;	
            ((UserDataScope)((Object)a2)).setUserCode(user.getUserCode());	
            v0.setCtrlType(ctrlType);	
            v0.setCtrlPermi(ctrlPermi);	
            if (userDataScopeDao == null) {	
                userDataScopeDao = SpringUtils.getBean(UserDataScopeDao.class);	
            }	
            a3 = userDataScopeDao.findCount(a2);	
            UserUtils.putCache(new StringBuilder().insert(0, USER_CACHE_USER_DATA_SCOPE_).append(ctrlType).append(ctrlPermi).toString(), a3);	
        }	
        if (a3 == 0L) {	
            if (StringUtils.isNotBlank((CharSequence)bizCtrlUserField)) {	
                this.addFilter(sqlMapKey, new StringBuilder().insert(0, bizCtrlUserField).append(" = '").append(user.getUserCode()).append("'").toString());	
                this.addJoinWhere(sqlMapKey, bizCtrlUserField + " = '" + user.getUserCode() + "'");	
                return;	
            }	
            this.addFilter(sqlMapKey, "1=2");	
            this.addJoinWhere(sqlMapKey, "1=2");	
            return;	
        }	
        a2 = new StringBuilder();	
        StringBuilder a4 = new StringBuilder();	
        String a5 = new StringBuilder().insert(0, "a").append(IdGen.randomBase62((int)4)).toString();	
        StringBuilder a6 = new StringBuilder();	
        this.addFilter(sqlMapKey, a6.toString());	
        a6.append(new StringBuilder().insert(0, " AND ctrl_data = ").append(bizCtrlDataField).append(")").toString());	
        a6.append(new StringBuilder().insert(0, " AND user_code = '").append(user.getUserCode()).append("'").toString());	
        a6.append(new StringBuilder().insert(0, " AND ctrl_type = '").append(ctrlType).append("'").toString());	
        a6.append(new StringBuilder().insert(0, " WHERE ctrl_permi = '").append(ctrlPermi).append("'").toString());	
        a6.append(new StringBuilder().insert(0, " EXISTS (SELECT 1 FROM ").append(Global.getTablePrefix()).append("sys_user_data_scope").toString());	
        this.addJoinWhere(sqlMapKey, a4.toString());	
        this.addJoinFrom(sqlMapKey, a2.toString());	
        a4.append(new StringBuilder().insert(0, " AND ").append(a5).append(".user_code = '").append(user.getUserCode()).append("'").toString());	
        a4.append(new StringBuilder().insert(0, " AND ").append(a5).append(".ctrl_type = '").append(ctrlType).append("'").toString());	
        a4.append(new StringBuilder().insert(0, a5).append(".ctrl_permi = '").append(ctrlPermi).append("'").toString());	
        a2.append(new StringBuilder().insert(0, " JOIN ").append(Global.getTablePrefix()).append("sys_user_data_scope ").append(a5).append(" ON ").append(a5).append(".ctrl_data = ").append(bizCtrlDataField).toString());	
    }	
	
    private /* synthetic */ QueryDataScope addJoinFrom(String sqlMapKey, String sqlFrom) {	
        void a2;	
        SqlMap sqlMap = this.entity.getSqlMap();	
        String a3 = ObjectUtils.toString(a2.get(sqlMapKey + "From"));	
        if (StringUtils.isNotBlank((CharSequence)a3)) {	
            a2.add(new StringBuilder().insert(0, sqlMapKey).append("From").toString(), new StringBuilder().insert(0, a3).append(" ").append(sqlFrom).toString());	
            return this;	
        }	
        a2.add(new StringBuilder().insert(0, sqlMapKey).append("From").toString(), sqlFrom);	
        return this;	
    }	
	
    /*	
     * Loose catch block	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     * Lifted jumps to return sites	
     */	
    private /* synthetic */ void addRoleExtendDataScope(String sqlMapKey, Set<String> extendSet, String ctrlType, String bizCtrlDataField) {	
        if (this.roleExtendDataScopes == null) {	
            this.roleExtendDataScopes = (Map)UserUtils.getCache(USER_CACHE_ROLE_EXTEND_DATA_SCOPES);	
            if (this.roleExtendDataScopes == null) {	
                this.roleExtendDataScopes = (Map)JsonMapper.fromJson((String)Global.getProperty("role.extendDataScopes", "{}"), Map.class);	
                UserUtils.putCache(USER_CACHE_ROLE_EXTEND_DATA_SCOPES, this.roleExtendDataScopes);	
            }	
        }	
        Iterator<String> a2 = extendSet.iterator();	
        do {	
            String a5;	
            String a4;	
            String string;	
            String a9;	
            void a3;	
            CharSequence a6;	
            String a8;	
            String a7;	
            block15 : {	
                Map<String, String> a10;	
                block14 : {	
                    Iterator<String> iterator = a2;	
                    while (iterator.hasNext()) {	
                        Map<String, Map<String, String>> a11 = this.roleExtendDataScopes.get(a2.next());	
                        if (a11 == null) {	
                            iterator = a2;	
                            continue;	
                        }	
                        a10 = a11.get(ctrlType);	
                        if (a10 == null) {	
                            iterator = a2;	
                            continue;	
                        }	
                        break block14;	
                    }	
                    return;	
                }	
                a9 = a10.get("ctrlTypeClass");	
                a4 = a10.get("ctrlDataAttrName");	
                a7 = a10.get("ctrlDataParentCodesAttrName");	
                if ("NONE".equals(a9)) {	
                    this.clearFilter(sqlMapKey);	
                    return;	
                }	
                Table table = MapperHelper.getTable(Class.forName(a9));	
                a5 = MapperHelper.getTableName(table, null);	
                a8 = null;	
                for (Column a12 : MapperHelper.getColumns(table, ListUtils.newArrayList())) {	
                    if (!a12.isPK()) continue;	
                    string = a8 = a12.name();	
                    break block15;	
                }	
                string = a8;	
            }	
            if (StringUtils.isBlank(string)) {	
                throw new MapperException(new StringBuilder().insert(0, "Error: ").append(a9).append(" 定义@Table没有isPK=true的列.").toString());	
            }	
            String a13 = (String)ReflectUtils.invokeGetter(this.entity, (String)a4);	
            StringBuilder a14 = new StringBuilder();	
            StringBuilder a15 = new StringBuilder();	
            String string2 = new StringBuilder().insert(0, "a").append(IdGen.randomBase62((int)4)).toString();	
            a15.append(new StringBuilder().insert(0, "(").append((String)a3).append(".").append(a8).append(" = '").append(a13).append("'").toString());	
            a14.append(new StringBuilder().insert(0, " ON ").append((String)a3).append(".").append(a8).append(" = ").append(bizCtrlDataField).toString());	
            a14.append(" JO#N " + a5 + " " + (String)a3);	
            if (StringUtils.isNotBlank((CharSequence)a7)) {	
                a6 = (String)ReflectUtils.invokeGetter(this.entity, (String)a7);	
                a15.append(new StringBuilder().insert(0, " OR ").append((String)a3).append(".parent_codes L#KE '").append((String)a6).append(a13).append(",%'").toString());	
            }	
            a6 = new StringBuilder();	
            ((StringBuilder)a6).append(new StringBuilder().insert(0, " WHERE (").append(a8).append(" = '").append(a13).append("'").toString());	
            ((StringBuilder)a6).append(new StringBuilder().insert(0, " EXISTS (SELECT 1 FROM ").append(a5).toString());	
            this.addJoinWhere(sqlMapKey, a15.toString());	
            this.addJoinFrom(sqlMapKey, a14.toString());	
            a15.append(")");	
            if (StringUtils.isNotBlank((CharSequence)a7)) {	
                String a16 = (String)ReflectUtils.invokeGetter(this.entity, (String)a7);	
                ((StringBuilder)a6).append(new StringBuilder().insert(0, " OR parent_codes LIKE '").append(a16).append(a13).append(",%')").toString());	
            }	
            ((StringBuilder)a6).append(new StringBuilder().insert(0, " AND ").append(a8).append(" = ").append(bizCtrlDataField).append(")").toString());	
            this.addFilter(sqlMapKey, ((StringBuilder)a6).toString());	
            continue;	
            break;	
        } while (true);	
        catch (ClassNotFoundException a17) {	
            throw ExceptionUtils.unchecked((Exception)a17);	
        }	
    }	
	
    private /* synthetic */ void addRoleDataScope(String sqlMapKey, User user, String bizCtrlDataField) {	
        String a2 = new StringBuilder().insert(0, "a").append(IdGen.randomBase62((int)4)).toString();	
        this.addFilter(sqlMapKey, new StringBuilder().insert(0, " EXISTS (SELECT 1 FROM ").append(Global.getTablePrefix()).append("sys_user_role WHERE user_code = '").append(user.getUserCode()).append("' AND role_code = ").append(bizCtrlDataField).append(")").toString());	
        this.addJoinWhere(sqlMapKey, new StringBuilder().insert(0, a2).append(".user_code = '").append(user.getUserCode()).append("'").toString());	
        this.addJoinFrom(sqlMapKey, " JOIN " + Global.getTablePrefix() + "sys_user_role " + a2 + " ON " + a2 + ".role_code = " + bizCtrlDataField);	
    }	
	
    public QueryDataScope addFilter(String sqlMapKey, String ctrlTypes, String bizCtrlDataFields, String bizCtrlUserField, String ctrlPermi) {	
        int a2;	
        String[] a3;	
        User a4 = this.entity.getCurrentUser();	
        if (a4.isAdmin()) {	
            return this;	
        }	
        ArrayList a5 = ListUtils.newArrayList();	
        HashSet a6 = SetUtils.newHashSet();	
        Iterator<Role> iterator = a4.getRoleList().iterator();	
        block0 : do {	
            Iterator<Role> iterator2 = iterator;	
            while (iterator2.hasNext()) {	
                a3 = iterator.next();	
                if ("0".equals(a3.getDataScope())) {	
                    iterator2 = iterator;	
                    continue;	
                }	
                if ("1".equals(a3.getDataScope())) {	
                    return this;	
                }	
                if ("2".equals(a3.getDataScope())) {	
                    a5.add(a3.getRoleCode());	
                    continue block0;	
                }	
                a6.add(a3.getDataScope());	
                continue block0;	
            }	
            break;	
        } while (true);	
        String[] a7 = StringUtils.split((String)ctrlTypes, (String)",");	
        a3 = StringUtils.split((String)bizCtrlDataFields, (String)",");	
        if (a7 == null || a3 == null || a7.length != a3.length) {	
            throw new ServiceException(new StringBuilder().insert(0, "注意ctrlTypes和bizCtrlDataFields使用“,”分隔，长度必须相等。ctrlTypes: ").append(ctrlTypes).append(" bizCtrlDataFields: ").append(bizCtrlDataFields).toString());	
        }	
        if (!StringUtils.inString((String)ctrlPermi, (String[])new String[]{"1", "2"})) {	
            ctrlPermi = "1";	
        }	
        int n = a2 = 0;	
        while (n < a7.length) {	
            if ("Role".equals(a7[a2]) && "1".equals(ctrlPermi)) {	
                this.addRoleDataScope(sqlMapKey, a4, a3[a2]);	
            } else {	
                String string = sqlMapKey;	
                this.addUserDataScope(string, a4, a7[a2], ctrlPermi, a3[a2], bizCtrlUserField);	
                this.addRoleDataScope(string, a5, a7[a2], ctrlPermi, a3[a2], bizCtrlUserField);	
                if ("1".equals(ctrlPermi)) {	
                    this.addRoleExtendDataScope(sqlMapKey, a6, a7[a2], a3[a2]);	
                }	
            }	
            n = ++a2;	
        }	
        return this;	
    }	
	
    public QueryDataScope(BaseEntity<?> entity) {	
        this.entity = entity;	
    }	
}	
	
