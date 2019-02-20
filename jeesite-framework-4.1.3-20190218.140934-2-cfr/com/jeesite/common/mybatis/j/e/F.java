/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  net.sf.jsqlparser.expression.Alias	
 *  net.sf.jsqlparser.expression.Expression	
 *  net.sf.jsqlparser.expression.Function	
 *  net.sf.jsqlparser.parser.CCJSqlParserUtil	
 *  net.sf.jsqlparser.schema.Column	
 *  net.sf.jsqlparser.statement.Statement	
 *  net.sf.jsqlparser.statement.select.Distinct	
 *  net.sf.jsqlparser.statement.select.FromItem	
 *  net.sf.jsqlparser.statement.select.Join	
 *  net.sf.jsqlparser.statement.select.LateralSubSelect	
 *  net.sf.jsqlparser.statement.select.OrderByElement	
 *  net.sf.jsqlparser.statement.select.PlainSelect	
 *  net.sf.jsqlparser.statement.select.Select	
 *  net.sf.jsqlparser.statement.select.SelectBody	
 *  net.sf.jsqlparser.statement.select.SelectExpressionItem	
 *  net.sf.jsqlparser.statement.select.SelectItem	
 *  net.sf.jsqlparser.statement.select.SetOperationList	
 *  net.sf.jsqlparser.statement.select.SubJoin	
 *  net.sf.jsqlparser.statement.select.SubSelect	
 *  net.sf.jsqlparser.statement.select.ValuesList	
 *  net.sf.jsqlparser.statement.select.WithItem	
 */	
package com.jeesite.common.mybatis.j.e;	
	
import java.util.ArrayList;	
import java.util.Iterator;	
import java.util.List;	
import net.sf.jsqlparser.expression.Alias;	
import net.sf.jsqlparser.expression.Expression;	
import net.sf.jsqlparser.expression.Function;	
import net.sf.jsqlparser.parser.CCJSqlParserUtil;	
import net.sf.jsqlparser.schema.Column;	
import net.sf.jsqlparser.statement.Statement;	
import net.sf.jsqlparser.statement.select.Distinct;	
import net.sf.jsqlparser.statement.select.FromItem;	
import net.sf.jsqlparser.statement.select.Join;	
import net.sf.jsqlparser.statement.select.LateralSubSelect;	
import net.sf.jsqlparser.statement.select.OrderByElement;	
import net.sf.jsqlparser.statement.select.PlainSelect;	
import net.sf.jsqlparser.statement.select.Select;	
import net.sf.jsqlparser.statement.select.SelectBody;	
import net.sf.jsqlparser.statement.select.SelectExpressionItem;	
import net.sf.jsqlparser.statement.select.SelectItem;	
import net.sf.jsqlparser.statement.select.SetOperationList;	
import net.sf.jsqlparser.statement.select.SubJoin;	
import net.sf.jsqlparser.statement.select.SubSelect;	
import net.sf.jsqlparser.statement.select.ValuesList;	
import net.sf.jsqlparser.statement.select.WithItem;	
import org.hyperic.sigar.FileWatcher;	
import org.hyperic.sigar.shell.ShellCommandUsageException;	
	
/*	
 * Duplicate member names - consider using --renamedupmembers true	
 */	
public class F {	
    public static final String D = "/*return count*/";	
    public static final String c = "/*keep orderby*/";	
    private static final Alias ALLATORIxDEMO = new Alias("table_count");	
	
    public boolean ALLATORIxDEMO(PlainSelect select) {	
        if (select.getGroupByColumnReferences() != null) {	
            return false;	
        }	
        if (select.getDistinct() != null) {	
            return false;	
        }	
        for (SelectItem a2 : select.getSelectItems()) {	
            if (a2.toString().contains("?")) {	
                return false;	
            }	
            if (!(a2 instanceof SelectExpressionItem) || !(((SelectExpressionItem)a2).getExpression() instanceof Function)) continue;	
            return false;	
        }	
        return true;	
    }	
	
    public boolean ALLATORIxDEMO(List<OrderByElement> orderByElements) {	
        if (orderByElements == null) {	
            return false;	
        }	
        Iterator<OrderByElement> iterator = orderByElements.iterator();	
        while (iterator.hasNext()) {	
            if (!iterator.next().toString().contains("?")) continue;	
            return true;	
        }	
        return false;	
    }	
	
    static {	
        ALLATORIxDEMO.setUseAs(false);	
    }	
	
    public void ALLATORIxDEMO(SelectBody selectBody) {	
        if (selectBody instanceof PlainSelect) {	
            this.ALLATORIxDEMO((PlainSelect)selectBody);	
            return;	
        }	
        if (selectBody instanceof WithItem) {	
            WithItem a2 = (WithItem)selectBody;	
            if (a2.getSelectBody() != null) {	
                this.ALLATORIxDEMO(a2.getSelectBody());	
                return;	
            }	
        } else {	
            SetOperationList a3 = (SetOperationList)selectBody;	
            if (a3.getSelects() != null && a3.getSelects().size() > 0) {	
                Iterator iterator;	
                Iterator iterator2 = iterator = a3.getSelects().iterator();	
                while (iterator2.hasNext()) {	
                    void a4;	
                    SelectBody selectBody2 = (SelectBody)iterator.next();	
                    iterator2 = iterator;	
                    this.ALLATORIxDEMO((SelectBody)a4);	
                }	
            }	
            if (!this.ALLATORIxDEMO(a3.getOrderByElements())) {	
                a3.setOrderByElements(null);	
            }	
        }	
    }	
	
    public String h(String sql, String name) {	
        Statement a2 = null;	
        if (sql.contains(c)) {	
            return this.h(sql);	
        }	
        try {	
            a2 = CCJSqlParserUtil.parse((String)sql);	
        }	
        catch (Throwable a3) {	
            return this.h(sql);	
        }	
        Select a4 = (Select)a2;	
        SelectBody a5 = a4.getSelectBody();	
        try {	
            this.ALLATORIxDEMO(a5);	
        }	
        catch (Exception a6) {	
            return this.h(sql);	
        }	
        this.ALLATORIxDEMO(a4.getWithItemsList());	
        Select select = a4;	
        this.ALLATORIxDEMO(select, name);	
        String a7 = select.toString();	
        return a7;	
    }	
	
    public void ALLATORIxDEMO(Select select, String name) {	
        void a2;	
        void a3;	
        SelectBody a4 = select.getSelectBody();	
        ArrayList arrayList = new ArrayList();	
        a2.add(new SelectExpressionItem((Expression)new Column(new StringBuilder().insert(0, "count(").append(name).append(")").toString())));	
        if (a4 instanceof PlainSelect && this.ALLATORIxDEMO((PlainSelect)a4)) {	
            ((PlainSelect)a4).setSelectItems((List)a2);	
            return;	
        }	
        PlainSelect a5 = new PlainSelect();	
        SubSelect subSelect = new SubSelect();	
        void v0 = a3;	
        v0.setSelectBody(a4);	
        v0.setAlias(ALLATORIxDEMO);	
        PlainSelect plainSelect = a5;	
        plainSelect.setFromItem((FromItem)a3);	
        plainSelect.setSelectItems((List)a2);	
        select.setSelectBody((SelectBody)plainSelect);	
    }	
	
    public String h(String sql) {	
        return this.ALLATORIxDEMO(sql, "0");	
    }	
	
    public String ALLATORIxDEMO(String sql, String name) {	
        void a2;	
        StringBuilder stringBuilder = new StringBuilder(sql.length() + 40);	
        void v0 = a2;	
        a2.append(") tmp_count");	
        a2.append(sql);	
        v0.append(") from (");	
        stringBuilder.append(name);	
        stringBuilder.append("select count(");	
        return v0.toString();	
    }	
	
    public void ALLATORIxDEMO(PlainSelect plainSelect) {	
        if (!this.ALLATORIxDEMO(plainSelect.getOrderByElements())) {	
            plainSelect.setOrderByElements(null);	
        }	
        if (plainSelect.getFromItem() != null) {	
            this.ALLATORIxDEMO(plainSelect.getFromItem());	
        }	
        if (plainSelect.getJoins() != null && plainSelect.getJoins().size() > 0) {	
            for (Join a2 : plainSelect.getJoins()) {	
                if (a2.getRightItem() == null) continue;	
                this.ALLATORIxDEMO(a2.getRightItem());	
            }	
        }	
    }	
	
    public void ALLATORIxDEMO(FromItem fromItem) {	
        if (fromItem instanceof SubJoin) {	
            SubJoin a2 = (SubJoin)fromItem;	
            if (a2.getJoin() != null && a2.getJoin().getRightItem() != null) {	
                this.ALLATORIxDEMO(a2.getJoin().getRightItem());	
            }	
            if (a2.getLeft() != null) {	
                this.ALLATORIxDEMO(a2.getLeft());	
                return;	
            }	
        } else if (fromItem instanceof SubSelect) {	
            SubSelect a3 = (SubSelect)fromItem;	
            if (a3.getSelectBody() != null) {	
                this.ALLATORIxDEMO(a3.getSelectBody());	
                return;	
            }	
        } else {	
            SubSelect a4;	
            LateralSubSelect a5;	
            if (fromItem instanceof ValuesList) {	
                return;	
            }	
            if (fromItem instanceof LateralSubSelect && (a5 = (LateralSubSelect)fromItem).getSubSelect() != null && (a4 = a5.getSubSelect()).getSelectBody() != null) {	
                this.ALLATORIxDEMO(a4.getSelectBody());	
            }	
        }	
    }	
	
    public void ALLATORIxDEMO(List<WithItem> withItemsList) {	
        if (withItemsList != null && withItemsList.size() > 0) {	
            Iterator<WithItem> iterator;	
            Iterator<WithItem> iterator2 = iterator = withItemsList.iterator();	
            while (iterator2.hasNext()) {	
                WithItem a2 = iterator.next();	
                iterator2 = iterator;	
                this.ALLATORIxDEMO(a2.getSelectBody());	
            }	
        }	
    }	
	
    public String ALLATORIxDEMO(String sql) {	
        return this.h(sql, "0");	
    }	
}	
	
