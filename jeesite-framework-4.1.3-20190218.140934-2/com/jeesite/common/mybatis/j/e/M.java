package com.jeesite.common.mybatis.j.e;	
	
import com.jeesite.modules.sys.utils.ConfigUtils;	
import java.util.ArrayList;	
import java.util.Collections;	
import java.util.HashMap;	
import java.util.HashSet;	
import java.util.Iterator;	
import java.util.List;	
import java.util.ListIterator;	
import java.util.Map;	
import java.util.Set;	
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
   protected static final Top h = new Top();	
   protected static final String M = "ROW_ALIAS_";	
   protected static final String f = "PAGE_TABLE_ALIAS";	
   public static final String G = String.valueOf(Long.MAX_VALUE);	
   protected static final String k = "PAGE_ROW_NUMBER";	
   public static final String D = String.valueOf(Long.MIN_VALUE);	
   protected static final Column c = new Column("PAGE_ROW_NUMBER");	
   public static final Alias ALLATORIxDEMO = new Alias("PAGE_TABLE_ALIAS");	
	
   protected OrderByElement ALLATORIxDEMO(OrderByElement orig, Expression expression) {	
      OrderByElement a = new OrderByElement();	
      a.setAsc(orig.isAsc());	
      a.setAscDescPresent(orig.isAscDescPresent());	
      a.setNullOrdering(orig.getNullOrdering());	
      a.setExpression(expression);	
      return a;	
   }	
	
   public boolean ALLATORIxDEMO(List list) {	
      return list != null && list.size() != 0;	
   }	
	
   protected OrderByElement ALLATORIxDEMO(OrderByElement orig, String alias) {	
      return this.ALLATORIxDEMO((OrderByElement)orig, (Expression)(new Column(alias)));	
   }	
	
   protected Select ALLATORIxDEMO(Select select) {	
      SelectBody a;	
      if ((a = select.getSelectBody()) instanceof SetOperationList) {	
         a = this.ALLATORIxDEMO((SetOperationList)a);	
      }	
	
      if (((PlainSelect)a).getTop() != null) {	
         throw new com.jeesite.common.mybatis.j.d.E("被分页的语句已经包含了Top，不能再通过分页插件进行分页查询!");	
      } else {	
         List a = this.ALLATORIxDEMO((PlainSelect)a);	
         List a = new ArrayList();	
         SelectItem a = this.ALLATORIxDEMO((PlainSelect)((PlainSelect)a), (List)a);	
         PlainSelect var10000 = (PlainSelect)a;	
         SelectItem[] var10002 = new SelectItem[a.size()];	
         boolean var10004 = true;	
         var10000.addSelectItems((SelectItem[])a.toArray(var10002));	
         this.ALLATORIxDEMO((SelectBody)a, 0);	
         PlainSelect a;	
         var10000 = a = new PlainSelect();	
         SelectItem[] var10001 = new SelectItem[1];	
         boolean var10003 = true;	
         var10001[0] = a;	
         var10000.addSelectItems(var10001);	
         var10002 = new SelectItem[a.size()];	
         var10004 = true;	
         a.addSelectItems((SelectItem[])a.toArray(var10002));	
         SubSelect a = new SubSelect();	
         a.setSelectBody(a);	
         a.setAlias(ALLATORIxDEMO);	
         a.setFromItem(a);	
         Select a = new Select();	
         PlainSelect a = new PlainSelect();	
         Top a = new Top();	
         a.setExpression(new LongValue(Long.MAX_VALUE));	
         a.setTop(a);	
         List a = new ArrayList();	
         OrderByElement a = new OrderByElement();	
         a.setExpression(c);	
         a.add(a);	
         a.setOrderByElements(a);	
         GreaterThan var14 = new GreaterThan();	
         var14.setLeftExpression(c);	
         var14.setRightExpression(new LongValue(Long.MIN_VALUE));	
         a.setWhere(var14);	
         a.setSelectItems(a);	
         SubSelect var13 = new SubSelect();	
         var13.setSelectBody(a);	
         var13.setAlias(ALLATORIxDEMO);	
         a.setFromItem(var13);	
         a.setSelectBody(a);	
         if (this.ALLATORIxDEMO(select.getWithItemsList())) {	
            a.setWithItemsList(select.getWithItemsList());	
         }	
	
         return a;	
      }	
   }	
	
   protected List ALLATORIxDEMO(PlainSelect plainSelect, List autoItems) {	
      List a;	
      ListIterator a = (a = plainSelect.getOrderByElements()).listIterator();	
      Map a = new HashMap();	
      Set a = new HashSet();	
      int a = false;	
      Set a = new HashSet();	
      Iterator var10 = plainSelect.getSelectItems().iterator();	
	
      SelectExpressionItem a;	
      Alias a;	
      while(var10.hasNext()) {	
         SelectItem a;	
         if ((a = (SelectItem)var10.next()) instanceof SelectExpressionItem) {	
            a = (SelectExpressionItem)a;	
            a.put(a.getExpression().toString(), a);	
            a = a.getAlias();	
            if (a != null) {	
               a.add(a.getName());	
            }	
         } else if (a instanceof AllColumns) {	
            a = true;	
         } else if (a instanceof AllTableColumns) {	
            a.add(((AllTableColumns)a).getTable().getName());	
         }	
      }	
	
      int a = 1;	
	
      while(true) {	
         label67:	
         while(true) {	
            ListIterator var10000 = a;	
	
            OrderByElement a;	
            Expression a;	
            String a;	
            while(true) {	
               if (!var10000.hasNext()) {	
                  return a;	
               }	
	
               a = (a = (OrderByElement)a.next()).getExpression();	
               if ((a = (SelectExpressionItem)a.get(a.toString())) != null) {	
                  if ((a = a.getAlias()) != null) {	
                     a.set(this.ALLATORIxDEMO(a, a.getName()));	
                  } else {	
                     if (!(a instanceof Column)) {	
                        throw new com.jeesite.common.mybatis.j.d.E((new StringBuilder()).insert(0, "列 "").append(a).append("" 需要定义别名").toString());	
                     }	
	
                     ((Column)a).setTable((Table)null);	
                  }	
                  continue label67;	
               }	
	
               if (!(a instanceof Column)) {	
                  break;	
               }	
	
               if ((a = ((Column)a).getTable().getName()) == null) {	
                  if (a || a.size() == 1 && plainSelect.getJoins() == null) {	
                     continue label67;	
                  }	
	
                  if (!a.contains(((Column)a).getColumnName())) {	
                     break;	
                  }	
	
                  var10000 = a;	
               } else {	
                  if (!a && !a.contains(a)) {	
                     break;	
                  }	
	
                  ((Column)a).setTable((Table)null);	
                  var10000 = a;	
               }	
            }	
	
            a = (new StringBuilder()).insert(0, "ROW_ALIAS_").append(a).toString();	
            ++a;	
            SelectExpressionItem a = new SelectExpressionItem();	
            a.setExpression(a);	
            a.setAlias(new Alias(a));	
            autoItems.add(a);	
            a.set(this.ALLATORIxDEMO(a, a));	
         }	
      }	
   }	
	
   public String ALLATORIxDEMO(String sql) {	
      return this.ALLATORIxDEMO(sql, (Integer)null, (Integer)null);	
   }	
	
   static {	
      h.setExpression(new LongValue(100L));	
      h.setPercentage(true);	
   }	
	
   public String ALLATORIxDEMO(String sql, Integer offset, Integer limit) {	
      Statement a;	
      try {	
         a = CCJSqlParserUtil.parse(sql);	
      } catch (Throwable var7) {	
         throw new com.jeesite.common.mybatis.j.d.E("不支持该SQL转换为分页查询!");	
      }	
	
      if (!(a instanceof Select)) {	
         throw new com.jeesite.common.mybatis.j.d.E("分页语句必须是Select查询!");	
      } else {	
         String a = this.ALLATORIxDEMO((Select)a).toString();	
         if (offset != null) {	
            a = a.replace(D, String.valueOf(offset));	
         }	
	
         if (limit != null) {	
            a = a.replace(G, String.valueOf(limit));	
         }	
	
         return a;	
      }	
   }	
	
   protected SelectItem ALLATORIxDEMO(PlainSelect plainSelect, List autoItems) {	
      StringBuilder a = new StringBuilder();	
      a.append("ROW_NUMBER() OVER (");	
      StringBuilder var10000;	
      if (this.ALLATORIxDEMO(plainSelect.getOrderByElements())) {	
         var10000 = a;	
         a.append(PlainSelect.orderByToString(this.ALLATORIxDEMO(plainSelect, autoItems)).substring(1));	
         plainSelect.setOrderByElements((List)null);	
      } else {	
         var10000 = a;	
         a.append("ORDER BY RAND()");	
      }	
	
      var10000.append(") ");	
      a.append("PAGE_ROW_NUMBER");	
      return new SelectExpressionItem(new Column(a.toString()));	
   }	
	
   protected void ALLATORIxDEMO(FromItem fromItem, int level) {	
      if (fromItem instanceof SubJoin) {	
         SubJoin a;	
         if ((a = (SubJoin)fromItem).getJoin() != null && a.getJoin().getRightItem() != null) {	
            this.ALLATORIxDEMO(a.getJoin().getRightItem(), level + 1);	
         }	
	
         if (a.getLeft() != null) {	
            this.ALLATORIxDEMO(a.getLeft(), level + 1);	
            return;	
         }	
      } else if (fromItem instanceof SubSelect) {	
         SubSelect a;	
         if ((a = (SubSelect)fromItem).getSelectBody() != null) {	
            this.ALLATORIxDEMO(a.getSelectBody(), level + 1);	
            return;	
         }	
      } else {	
         if (fromItem instanceof ValuesList) {	
            return;	
         }	
	
         SubSelect a;	
         LateralSubSelect a;	
         if (fromItem instanceof LateralSubSelect && (a = (LateralSubSelect)fromItem).getSubSelect() != null && (a = a.getSubSelect()).getSelectBody() != null) {	
            this.ALLATORIxDEMO(a.getSelectBody(), level + 1);	
         }	
      }	
	
   }	
	
   protected SelectBody ALLATORIxDEMO(SetOperationList setOperationList) {	
      SelectBody a;	
      if (!((a = (SelectBody)setOperationList.getSelects().get(setOperationList.getSelects().size() - 1)) instanceof PlainSelect)) {	
         throw new com.jeesite.common.mybatis.j.d.E("目前无法处理该SQL，您可以将该SQL发送给abel533@gmail.com协助作者解决!");	
      } else {	
         PlainSelect a = (PlainSelect)a;	
         PlainSelect a = new PlainSelect();	
         List a = this.ALLATORIxDEMO(a);	
         a.setSelectItems(a);	
         SubSelect a;	
         SubSelect var10002 = a = new SubSelect();	
         var10002.setSelectBody(setOperationList);	
         var10002.setAlias(new Alias("WRAP_OUTER_TABLE"));	
         a.setFromItem(a);	
         if (this.ALLATORIxDEMO(a.getOrderByElements())) {	
            a.setOrderByElements(a.getOrderByElements());	
            a.setOrderByElements((List)null);	
         }	
	
         return a;	
      }	
   }	
	
   protected void ALLATORIxDEMO(SelectBody selectBody, int level) {	
      if (selectBody instanceof PlainSelect) {	
         this.ALLATORIxDEMO((PlainSelect)selectBody, level + 1);	
      } else {	
         if (selectBody instanceof WithItem) {	
            WithItem a;	
            if ((a = (WithItem)selectBody).getSelectBody() != null) {	
               this.ALLATORIxDEMO(a.getSelectBody(), level + 1);	
               return;	
            }	
         } else {	
            SetOperationList a;	
            if ((a = (SetOperationList)selectBody).getSelects() != null && a.getSelects().size() > 0) {	
               Iterator var5;	
               Iterator var10000 = var5 = a.getSelects().iterator();	
	
               while(var10000.hasNext()) {	
                  SelectBody a = (SelectBody)var5.next();	
                  var10000 = var5;	
                  this.ALLATORIxDEMO(a, level + 1);	
               }	
            }	
         }	
	
      }	
   }	
	
   protected List ALLATORIxDEMO(PlainSelect plainSelect) {	
      List a = new ArrayList();	
      Iterator var3 = plainSelect.getSelectItems().iterator();	
	
      SelectItem a;	
      while(var3.hasNext()) {	
         if ((a = (SelectItem)var3.next()) instanceof SelectExpressionItem) {	
            SelectExpressionItem a;	
            Column a;	
            SelectExpressionItem a;	
            if ((a = (SelectExpressionItem)a).getAlias() != null) {	
               a = new Column(a.getAlias().getName());	
               a = new SelectExpressionItem(a);	
               a.add(a);	
            } else if (a.getExpression() instanceof Column) {	
               a = (Column)a.getExpression();	
               a = null;	
               if (a.getTable() != null) {	
                  Column a = new Column(a.getColumnName());	
                  a = new SelectExpressionItem(a);	
                  a.add(a);	
               } else {	
                  a.add(a);	
               }	
            } else {	
               a.add(a);	
            }	
         } else if (a instanceof AllTableColumns) {	
            a.add(new AllColumns());	
         } else {	
            a.add(a);	
         }	
      }	
	
      var3 = a.iterator();	
	
      do {	
         if (!var3.hasNext()) {	
            return a;	
         }	
      } while(!((a = (SelectItem)var3.next()) instanceof AllColumns));	
	
      return Collections.singletonList(a);	
   }	
	
   protected void ALLATORIxDEMO(PlainSelect plainSelect, int level) {	
      if (level > 1 && this.ALLATORIxDEMO(plainSelect.getOrderByElements()) && plainSelect.getTop() == null) {	
         plainSelect.setTop(h);	
      }	
	
      if (plainSelect.getFromItem() != null) {	
         this.ALLATORIxDEMO(plainSelect.getFromItem(), level + 1);	
      }	
	
      if (plainSelect.getJoins() != null && plainSelect.getJoins().size() > 0) {	
         Iterator var4 = plainSelect.getJoins().iterator();	
	
         while(var4.hasNext()) {	
            Join a;	
            if ((a = (Join)var4.next()).getRightItem() != null) {	
               this.ALLATORIxDEMO(a.getRightItem(), level + 1);	
            }	
         }	
      }	
	
   }	
}	
