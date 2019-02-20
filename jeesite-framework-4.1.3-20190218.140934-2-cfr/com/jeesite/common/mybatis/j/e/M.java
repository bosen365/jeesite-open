/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  net.sf.jsqlparser.expression.Alias	
 *  net.sf.jsqlparser.expression.Expression	
 *  net.sf.jsqlparser.expression.LongValue	
 *  net.sf.jsqlparser.expression.operators.relational.GreaterThan	
 *  net.sf.jsqlparser.parser.CCJSqlParserUtil	
 *  net.sf.jsqlparser.schema.Column	
 *  net.sf.jsqlparser.schema.Table	
 *  net.sf.jsqlparser.statement.Statement	
 *  net.sf.jsqlparser.statement.select.AllColumns	
 *  net.sf.jsqlparser.statement.select.AllTableColumns	
 *  net.sf.jsqlparser.statement.select.FromItem	
 *  net.sf.jsqlparser.statement.select.Join	
 *  net.sf.jsqlparser.statement.select.LateralSubSelect	
 *  net.sf.jsqlparser.statement.select.OrderByElement	
 *  net.sf.jsqlparser.statement.select.OrderByElement$NullOrdering	
 *  net.sf.jsqlparser.statement.select.PlainSelect	
 *  net.sf.jsqlparser.statement.select.Select	
 *  net.sf.jsqlparser.statement.select.SelectBody	
 *  net.sf.jsqlparser.statement.select.SelectExpressionItem	
 *  net.sf.jsqlparser.statement.select.SelectItem	
 *  net.sf.jsqlparser.statement.select.SetOperationList	
 *  net.sf.jsqlparser.statement.select.SubJoin	
 *  net.sf.jsqlparser.statement.select.SubSelect	
 *  net.sf.jsqlparser.statement.select.Top	
 *  net.sf.jsqlparser.statement.select.ValuesList	
 *  net.sf.jsqlparser.statement.select.WithItem	
 */	
package com.jeesite.common.mybatis.j.e;	
	
import com.jeesite.common.mybatis.j.d.E;	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import java.util.ArrayList;	
import java.util.Collections;	
import java.util.HashMap;	
import java.util.HashSet;	
import java.util.Iterator;	
import java.util.List;	
import java.util.ListIterator;	
import net.sf.jsqlparser.expression.Alias;	
import net.sf.jsqlparser.expression.Expression;	
import net.sf.jsqlparser.expression.LongValue;	
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;	
import net.sf.jsqlparser.parser.CCJSqlParserUtil;	
import net.sf.jsqlparser.schema.Column;	
import net.sf.jsqlparser.schema.Table;	
import net.sf.jsqlparser.statement.Statement;	
import net.sf.jsqlparser.statement.select.AllColumns;	
import net.sf.jsqlparser.statement.select.AllTableColumns;	
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
import net.sf.jsqlparser.statement.select.Top;	
import net.sf.jsqlparser.statement.select.ValuesList;	
import net.sf.jsqlparser.statement.select.WithItem;	
	
public class M {	
    protected static final String a = "WRAP_OUTER_TABLE";	
    protected static final Top h;	
    protected static final String M = "ROW_ALIAS_";	
    protected static final String f = "PAGE_TABLE_ALIAS";	
    public static final String G;	
    protected static final String k = "PAGE_ROW_NUMBER";	
    public static final String D;	
    protected static final Column c;	
    public static final Alias ALLATORIxDEMO;	
	
    protected OrderByElement ALLATORIxDEMO(OrderByElement orig, Expression expression) {	
        OrderByElement a2;	
        OrderByElement orderByElement = a2 = new OrderByElement();	
        OrderByElement orderByElement2 = orig;	
        a2.setAsc(orig.isAsc());	
        a2.setAscDescPresent(orderByElement2.isAscDescPresent());	
        orderByElement.setNullOrdering(orderByElement2.getNullOrdering());	
        orderByElement.setExpression(expression);	
        return orderByElement;	
    }	
	
    public boolean ALLATORIxDEMO(List<?> list) {	
        return list != null && list.size() != 0;	
    }	
	
    protected OrderByElement ALLATORIxDEMO(OrderByElement orig, String alias) {	
        return this.ALLATORIxDEMO(orig, (Expression)new Column(alias));	
    }	
	
    protected Select ALLATORIxDEMO(Select select) {	
        void a2;	
        void a3;	
        void a4;	
        SelectBody a5 = select.getSelectBody();	
        if (a5 instanceof SetOperationList) {	
            a5 = this.ALLATORIxDEMO((SetOperationList)a5);	
        }	
        if (((PlainSelect)a5).getTop() != null) {	
            throw new E("被分页的语句已经包含了Top，不能再通过分页插件进行分页查询!");	
        }	
        List<SelectItem> a6 = this.ALLATORIxDEMO((PlainSelect)a5);	
        ArrayList<SelectItem> a7 = new ArrayList<SelectItem>();	
        SelectItem a8 = this.ALLATORIxDEMO((PlainSelect)a5, a7);	
        ArrayList<SelectItem> arrayList = a7;	
        ((PlainSelect)a5).addSelectItems(arrayList.toArray((T[])new SelectItem[arrayList.size()]));	
        this.ALLATORIxDEMO(a5, 0);	
        PlainSelect a9 = new PlainSelect();	
        a9.addSelectItems(new SelectItem[]{a8});	
        List<SelectItem> list = a6;	
        a9.addSelectItems(list.toArray((T[])new SelectItem[list.size()]));	
        SubSelect subSelect = new SubSelect();	
        void v2 = a4;	
        v2.setSelectBody(a5);	
        v2.setAlias(ALLATORIxDEMO);	
        a9.setFromItem((FromItem)v2);	
        Select a10 = new Select();	
        PlainSelect a11 = new PlainSelect();	
        Top top = new Top();	
        void v3 = a3;	
        v3.setExpression((Expression)new LongValue(Long.MAX_VALUE));	
        a11.setTop((Top)v3);	
        ArrayList<void> a12 = new ArrayList<void>();	
        OrderByElement orderByElement = new OrderByElement();	
        void v4 = a2;	
        v4.setExpression((Expression)c);	
        PlainSelect plainSelect = a11;	
        plainSelect.setOrderByElements(a12);	
        GreaterThan greaterThan = new GreaterThan();	
        greaterThan.setLeftExpression((Expression)c);	
        greaterThan.setRightExpression((Expression)new LongValue(Long.MIN_VALUE));	
        plainSelect.setWhere((Expression)greaterThan);	
        plainSelect.setSelectItems(a6);	
        SubSelect subSelect2 = new SubSelect();	
        subSelect2.setSelectBody((SelectBody)a9);	
        subSelect2.setAlias(ALLATORIxDEMO);	
        plainSelect.setFromItem((FromItem)subSelect2);	
        a10.setSelectBody((SelectBody)a11);	
        a12.add(v4);	
        if (this.ALLATORIxDEMO(select.getWithItemsList())) {	
            a10.setWithItemsList(select.getWithItemsList());	
        }	
        return a10;	
    }	
	
    protected List<OrderByElement> ALLATORIxDEMO(PlainSelect plainSelect, List<SelectItem> autoItems) {	
        SelectExpressionItem a2;	
        Object a3;	
        PlainSelect plainSelect2 = plainSelect;	
        List a4 = plainSelect2.getOrderByElements();	
        ListIterator<OrderByElement> a5 = a4.listIterator();	
        HashMap<String, SelectExpressionItem> a6 = new HashMap<String, SelectExpressionItem>();	
        HashSet<String> a7 = new HashSet<String>();	
        boolean a8 = false;	
        HashSet<String> a9 = new HashSet<String>();	
        for (SelectItem a10 : plainSelect2.getSelectItems()) {	
            if (a10 instanceof SelectExpressionItem) {	
                a2 = (SelectExpressionItem)a10;	
                Alias alias = a2.getAlias();	
                a6.put(a2.getExpression().toString(), a2);	
                if (a3 == null) continue;	
                a7.add(a3.getName());	
                continue;	
            }	
            if (a10 instanceof AllColumns) {	
                a8 = true;	
                continue;	
            }	
            if (!(a10 instanceof AllTableColumns)) continue;	
            a9.add(((AllTableColumns)a10).getTable().getName());	
        }	
        int a11 = 1;	
        block1 : do {	
            ListIterator<OrderByElement> listIterator = a5;	
            while (listIterator.hasNext()) {	
                void a12;	
                SelectItem a10;	
                OrderByElement a13 = (OrderByElement)a5.next();	
                a10 = a13.getExpression();	
                a2 = (SelectExpressionItem)a6.get(a10.toString());	
                if (a2 != null) {	
                    a3 = a2.getAlias();	
                    if (a3 != null) {	
                        a5.set(this.ALLATORIxDEMO(a13, a3.getName()));	
                        continue block1;	
                    }	
                    if (a10 instanceof Column) {	
                        ((Column)a10).setTable(null);	
                        continue block1;	
                    }	
                    throw new E(new StringBuilder().insert(0, "列 "").append((Object)a10).append("" 需要定义别名").toString());	
                }	
                if (a10 instanceof Column) {	
                    a3 = ((Column)a10).getTable().getName();	
                    if (a3 == null) {	
                        if (a8 || a9.size() == 1 && plainSelect.getJoins() == null) continue block1;	
                        if (a7.contains(((Column)a10).getColumnName())) {	
                            listIterator = a5;	
                            continue;	
                        }	
                    } else if (a8 || a9.contains(a3)) {	
                        ((Column)a10).setTable(null);	
                        listIterator = a5;	
                        continue;	
                    }	
                }	
                a3 = new StringBuilder().insert(0, M).append(a11).toString();	
                ++a11;	
                SelectExpressionItem selectExpressionItem = new SelectExpressionItem();	
                void v2 = a12;	
                v2.setExpression((Expression)a10);	
                v2.setAlias(new Alias((String)a3));	
                a5.set(this.ALLATORIxDEMO(a13, (String)a3));	
                autoItems.add((SelectItem)a12);	
                continue block1;	
            }	
            break;	
        } while (true);	
        return a4;	
    }	
	
    public String ALLATORIxDEMO(String sql) {	
        return this.ALLATORIxDEMO(sql, null, null);	
    }	
	
    static {	
        D = String.valueOf(Long.MIN_VALUE);	
        G = String.valueOf(Long.MAX_VALUE);	
        ALLATORIxDEMO = new Alias(f);	
        c = new Column(k);	
        h = new Top();	
        h.setExpression((Expression)new LongValue(100L));	
        h.setPercentage(true);	
    }	
	
    public String ALLATORIxDEMO(String sql, Integer offset, Integer limit) {	
        void a2;	
        try {	
            Statement statement = CCJSqlParserUtil.parse((String)sql);	
        }	
        catch (Throwable a3) {	
            throw new E("不支持该SQL转换为分页查询!");	
        }	
        if (!(a2 instanceof Select)) {	
            throw new E("分页语句必须是Select查询!");	
        }	
        Select a4 = this.ALLATORIxDEMO((Select)a2);	
        String a5 = a4.toString();	
        if (offset != null) {	
            a5 = a5.replace(D, String.valueOf(offset));	
        }	
        if (limit != null) {	
            a5 = a5.replace(G, String.valueOf(limit));	
        }	
        return a5;	
    }	
	
    protected SelectItem ALLATORIxDEMO(PlainSelect plainSelect, List<SelectItem> autoItems) {	
        void v1;	
        void a2;	
        StringBuilder stringBuilder = new StringBuilder();	
        a2.append("ROW_NUMBER() OVER (");	
        if (this.ALLATORIxDEMO(plainSelect.getOrderByElements())) {	
            void v0 = a2;	
            v1 = v0;	
            plainSelect.setOrderByElements(null);	
            v0.append(PlainSelect.orderByToString(this.ALLATORIxDEMO(plainSelect, autoItems)).substring(1));	
        } else {	
            void v2 = a2;	
            v1 = v2;	
            v2.append("ORDER BY RAND()");	
        }	
        a2.append(k);	
        v1.append(") ");	
        return new SelectExpressionItem((Expression)new Column(a2.toString()));	
    }	
	
    protected void ALLATORIxDEMO(FromItem fromItem, int level) {	
        if (fromItem instanceof SubJoin) {	
            SubJoin a2 = (SubJoin)fromItem;	
            if (a2.getJoin() != null && a2.getJoin().getRightItem() != null) {	
                this.ALLATORIxDEMO(a2.getJoin().getRightItem(), level + 1);	
            }	
            if (a2.getLeft() != null) {	
                this.ALLATORIxDEMO(a2.getLeft(), level + 1);	
                return;	
            }	
        } else if (fromItem instanceof SubSelect) {	
            SubSelect a3 = (SubSelect)fromItem;	
            if (a3.getSelectBody() != null) {	
                this.ALLATORIxDEMO(a3.getSelectBody(), level + 1);	
                return;	
            }	
        } else {	
            SubSelect a4;	
            LateralSubSelect a5;	
            if (fromItem instanceof ValuesList) {	
                return;	
            }	
            if (fromItem instanceof LateralSubSelect && (a5 = (LateralSubSelect)fromItem).getSubSelect() != null && (a4 = a5.getSubSelect()).getSelectBody() != null) {	
                this.ALLATORIxDEMO(a4.getSelectBody(), level + 1);	
            }	
        }	
    }	
	
    protected SelectBody ALLATORIxDEMO(SetOperationList setOperationList) {	
        void a2;	
        SelectBody a3 = (SelectBody)setOperationList.getSelects().get(setOperationList.getSelects().size() - 1);	
        if (!(a3 instanceof PlainSelect)) {	
            throw new E("目前无法处理该SQL，您可以将该SQL发送给abel533@gmail.com协助作者解决!");	
        }	
        PlainSelect a4 = (PlainSelect)a3;	
        PlainSelect a5 = new PlainSelect();	
        M m2 = this;	
        List<SelectItem> a6 = m2.ALLATORIxDEMO(a4);	
        PlainSelect plainSelect = a5;	
        plainSelect.setSelectItems(a6);	
        SubSelect subSelect = new SubSelect();	
        subSelect.setSelectBody((SelectBody)setOperationList);	
        subSelect.setAlias(new Alias(a));	
        plainSelect.setFromItem((FromItem)a2);	
        if (m2.ALLATORIxDEMO(a4.getOrderByElements())) {	
            PlainSelect plainSelect2 = a4;	
            a5.setOrderByElements(plainSelect2.getOrderByElements());	
            plainSelect2.setOrderByElements(null);	
        }	
        return a5;	
    }	
	
    protected void ALLATORIxDEMO(SelectBody selectBody, int level) {	
        if (selectBody instanceof PlainSelect) {	
            this.ALLATORIxDEMO((PlainSelect)selectBody, level + 1);	
            return;	
        }	
        if (selectBody instanceof WithItem) {	
            WithItem a2 = (WithItem)selectBody;	
            if (a2.getSelectBody() != null) {	
                this.ALLATORIxDEMO(a2.getSelectBody(), level + 1);	
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
                    this.ALLATORIxDEMO((SelectBody)a4, level + 1);	
                }	
            }	
        }	
    }	
	
    protected List<SelectItem> ALLATORIxDEMO(PlainSelect plainSelect) {	
        ArrayList<SelectItem> a2 = new ArrayList<SelectItem>();	
        for (SelectItem a3 : plainSelect.getSelectItems()) {	
            if (a3 instanceof SelectExpressionItem) {	
                Column a4;	
                SelectExpressionItem a5;	
                SelectExpressionItem a6 = (SelectExpressionItem)a3;	
                if (a6.getAlias() != null) {	
                    a4 = new Column(a6.getAlias().getName());	
                    a5 = new SelectExpressionItem((Expression)a4);	
                    a2.add((SelectItem)a5);	
                    continue;	
                }	
                if (a6.getExpression() instanceof Column) {	
                    a4 = (Column)a6.getExpression();	
                    a5 = null;	
                    if (a4.getTable() != null) {	
                        Column a7 = new Column(a4.getColumnName());	
                        a5 = new SelectExpressionItem((Expression)a7);	
                        a2.add((SelectItem)a5);	
                        continue;	
                    }	
                    a2.add(a3);	
                    continue;	
                }	
                a2.add(a3);	
                continue;	
            }	
            ArrayList<SelectItem> arrayList = a2;	
            if (a3 instanceof AllTableColumns) {	
                ArrayList<SelectItem> arrayList2 = a2;	
                arrayList.add((SelectItem)new AllColumns());	
                continue;	
            }	
            arrayList.add(a3);	
        }	
        for (SelectItem a3 : a2) {	
            if (!(a3 instanceof AllColumns)) continue;	
            return Collections.singletonList(a3);	
        }	
        return a2;	
    }	
	
    protected void ALLATORIxDEMO(PlainSelect plainSelect, int level) {	
        if (level > 1 && this.ALLATORIxDEMO(plainSelect.getOrderByElements()) && plainSelect.getTop() == null) {	
            plainSelect.setTop(h);	
        }	
        if (plainSelect.getFromItem() != null) {	
            this.ALLATORIxDEMO(plainSelect.getFromItem(), level + 1);	
        }	
        if (plainSelect.getJoins() != null && plainSelect.getJoins().size() > 0) {	
            for (Join a2 : plainSelect.getJoins()) {	
                if (a2.getRightItem() == null) continue;	
                this.ALLATORIxDEMO(a2.getRightItem(), level + 1);	
            }	
        }	
    }	
}	
	
