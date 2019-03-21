/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.mybatis.d.v;	
	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.modules.sys.web.AdviceController;	
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
	
/*	
 * Duplicate member names - consider using --renamedupmembers true	
 */	
public class m {	
    private static final Alias i = new Alias("table_count");	
    public static final String b = "/*keep orderby*/";	
    public static final String ALLATORIxDEMO = "/*return count*/";	
	
    public void ALLATORIxDEMO(List<WithItem> withItemsList) {	
        if (withItemsList != null && withItemsList.size() > 0) {	
            Iterator<WithItem> iterator;	
            Iterator<WithItem> iterator2 = iterator = withItemsList.iterator();	
            while (iterator2.hasNext()) {	
                WithItem a = iterator.next();	
                iterator2 = iterator;	
                this.ALLATORIxDEMO(a.getSelectBody());	
            }	
        }	
    }	
	
    static {	
        i.setUseAs(false);	
    }	
	
    public void ALLATORIxDEMO(SelectBody selectBody) {	
        if (selectBody instanceof PlainSelect) {	
            this.ALLATORIxDEMO((PlainSelect)selectBody);	
            return;	
        }	
        if (selectBody instanceof WithItem) {	
            WithItem a = (WithItem)selectBody;	
            if (a.getSelectBody() != null) {	
                this.ALLATORIxDEMO(a.getSelectBody());	
                return;	
            }	
        } else {	
            SetOperationList a = (SetOperationList)selectBody;	
            if (a.getSelects() != null && a.getSelects().size() > 0) {	
                Iterator<SelectBody> iterator;	
                Iterator<SelectBody> iterator2 = iterator = a.getSelects().iterator();	
                while (iterator2.hasNext()) {	
                    void a2;	
                    SelectBody selectBody2 = iterator.next();	
                    iterator2 = iterator;	
                    this.ALLATORIxDEMO((SelectBody)a2);	
                }	
            }	
            if (!this.ALLATORIxDEMO(a.getOrderByElements())) {	
                a.setOrderByElements(null);	
            }	
        }	
    }	
	
    public String d(String sql) {	
        return this.d(sql, "0");	
    }	
	
    public void ALLATORIxDEMO(Select select, String name) {	
        void a;	
        void a2;	
        SelectBody a3 = select.getSelectBody();	
        ArrayList arrayList = new ArrayList();	
        a2.add(new SelectExpressionItem(new Column(new StringBuilder().insert(0, "count(").append(name).append(")").toString())));	
        if (a3 instanceof PlainSelect && this.ALLATORIxDEMO((PlainSelect)a3)) {	
            ((PlainSelect)a3).setSelectItems((List<SelectItem>)a2);	
            return;	
        }	
        PlainSelect a4 = new PlainSelect();	
        SubSelect subSelect = new SubSelect();	
        void v0 = a;	
        v0.setSelectBody(a3);	
        v0.setAlias(i);	
        PlainSelect plainSelect = a4;	
        plainSelect.setFromItem((FromItem)a);	
        plainSelect.setSelectItems((List<SelectItem>)a2);	
        select.setSelectBody(plainSelect);	
    }	
	
    public String d(String sql, String name) {	
        void a;	
        StringBuilder stringBuilder = new StringBuilder(sql.length() + 40);	
        void v0 = a;	
        a.append(") tmp_count");	
        a.append(sql);	
        v0.append(") from (");	
        stringBuilder.append(name);	
        stringBuilder.append("select count(");	
        return v0.toString();	
    }	
	
    public void ALLATORIxDEMO(FromItem fromItem) {	
        if (fromItem instanceof SubJoin) {	
            SubJoin a = (SubJoin)fromItem;	
            if (a.getJoin() != null && a.getJoin().getRightItem() != null) {	
                this.ALLATORIxDEMO(a.getJoin().getRightItem());	
            }	
            if (a.getLeft() != null) {	
                this.ALLATORIxDEMO(a.getLeft());	
                return;	
            }	
        } else if (fromItem instanceof SubSelect) {	
            SubSelect a = (SubSelect)fromItem;	
            if (a.getSelectBody() != null) {	
                this.ALLATORIxDEMO(a.getSelectBody());	
                return;	
            }	
        } else {	
            SubSelect a;	
            LateralSubSelect a2;	
            if (fromItem instanceof ValuesList) {	
                return;	
            }	
            if (fromItem instanceof LateralSubSelect && (a2 = (LateralSubSelect)fromItem).getSubSelect() != null && (a = a2.getSubSelect()).getSelectBody() != null) {	
                this.ALLATORIxDEMO(a.getSelectBody());	
            }	
        }	
    }	
	
    public String ALLATORIxDEMO(String sql, String name) {	
        Statement a = null;	
        if (sql.contains(b)) {	
            return this.d(sql);	
        }	
        try {	
            a = CCJSqlParserUtil.parse(sql);	
        }	
        catch (Throwable a2) {	
            return this.d(sql);	
        }	
        Select a3 = (Select)a;	
        SelectBody a4 = a3.getSelectBody();	
        try {	
            this.ALLATORIxDEMO(a4);	
        }	
        catch (Exception a5) {	
            return this.d(sql);	
        }	
        this.ALLATORIxDEMO(a3.getWithItemsList());	
        Select select = a3;	
        this.ALLATORIxDEMO(select, name);	
        String a6 = select.toString();	
        return a6;	
    }	
	
    public String ALLATORIxDEMO(String sql) {	
        return this.ALLATORIxDEMO(sql, "0");	
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
	
    public void ALLATORIxDEMO(PlainSelect plainSelect) {	
        if (!this.ALLATORIxDEMO(plainSelect.getOrderByElements())) {	
            plainSelect.setOrderByElements(null);	
        }	
        if (plainSelect.getFromItem() != null) {	
            this.ALLATORIxDEMO(plainSelect.getFromItem());	
        }	
        if (plainSelect.getJoins() != null && plainSelect.getJoins().size() > 0) {	
            for (Join a : plainSelect.getJoins()) {	
                if (a.getRightItem() == null) continue;	
                this.ALLATORIxDEMO(a.getRightItem());	
            }	
        }	
    }	
	
    public boolean ALLATORIxDEMO(PlainSelect select) {	
        if (select.getGroupByColumnReferences() != null) {	
            return false;	
        }	
        if (select.getDistinct() != null) {	
            return false;	
        }	
        for (SelectItem a : select.getSelectItems()) {	
            if (a.toString().contains("?")) {	
                return false;	
            }	
            if (!(a instanceof SelectExpressionItem) || !(((SelectExpressionItem)a).getExpression() instanceof Function)) continue;	
            return false;	
        }	
        return true;	
    }	
}	
	
