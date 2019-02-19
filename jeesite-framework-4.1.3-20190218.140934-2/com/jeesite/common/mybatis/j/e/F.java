package com.jeesite.common.mybatis.j.e;	
	
import java.util.ArrayList;	
import java.util.Iterator;	
import java.util.List;	
import net.sf.jsqlparser.expression.Alias;	
import net.sf.jsqlparser.expression.Function;	
import net.sf.jsqlparser.parser.CCJSqlParserUtil;	
import net.sf.jsqlparser.schema.Column;	
import net.sf.jsqlparser.statement.Statement;	
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
	
public class F {	
   public static final String D = "/*return count*/";	
   public static final String c = "/*keep orderby*/";	
   private static final Alias ALLATORIxDEMO = new Alias("table_count");	
	
   public boolean ALLATORIxDEMO(PlainSelect select) {	
      if (select.getGroupByColumnReferences() != null) {	
         return false;	
      } else if (select.getDistinct() != null) {	
         return false;	
      } else {	
         Iterator var2 = select.getSelectItems().iterator();	
	
         SelectItem a;	
         do {	
            if (!var2.hasNext()) {	
               return true;	
            }	
	
            if ((a = (SelectItem)var2.next()).toString().contains("?")) {	
               return false;	
            }	
         } while(!(a instanceof SelectExpressionItem) || !(((SelectExpressionItem)a).getExpression() instanceof Function));	
	
         return false;	
      }	
   }	
	
   public boolean ALLATORIxDEMO(List orderByElements) {	
      if (orderByElements == null) {	
         return false;	
      } else {	
         Iterator var2 = orderByElements.iterator();	
	
         do {	
            if (!var2.hasNext()) {	
               return false;	
            }	
         } while(!((OrderByElement)var2.next()).toString().contains("?"));	
	
         return true;	
      }	
   }	
	
   static {	
      ALLATORIxDEMO.setUseAs(false);	
   }	
	
   public void ALLATORIxDEMO(SelectBody selectBody) {	
      if (selectBody instanceof PlainSelect) {	
         this.ALLATORIxDEMO((PlainSelect)selectBody);	
      } else {	
         if (selectBody instanceof WithItem) {	
            WithItem a;	
            if ((a = (WithItem)selectBody).getSelectBody() != null) {	
               this.ALLATORIxDEMO(a.getSelectBody());	
               return;	
            }	
         } else {	
            SetOperationList a;	
            if ((a = (SetOperationList)selectBody).getSelects() != null && a.getSelects().size() > 0) {	
               Iterator var4;	
               Iterator var10000 = var4 = a.getSelects().iterator();	
	
               while(var10000.hasNext()) {	
                  SelectBody a = (SelectBody)var4.next();	
                  var10000 = var4;	
                  this.ALLATORIxDEMO(a);	
               }	
            }	
	
            if (!this.ALLATORIxDEMO(a.getOrderByElements())) {	
               a.setOrderByElements((List)null);	
            }	
         }	
	
      }	
   }	
	
   public String h(String sql, String name) {	
      Statement a = null;	
      if (sql.contains("/*keep orderby*/")) {	
         return this.h(sql);	
      } else {	
         try {	
            a = CCJSqlParserUtil.parse(sql);	
         } catch (Throwable var8) {	
            return this.h(sql);	
         }	
	
         Select a;	
         SelectBody a = (a = (Select)a).getSelectBody();	
	
         try {	
            this.ALLATORIxDEMO(a);	
         } catch (Exception var7) {	
            return this.h(sql);	
         }	
	
         this.ALLATORIxDEMO(a.getWithItemsList());	
         this.ALLATORIxDEMO(a, name);	
         return a.toString();	
      }	
   }	
	
   public void ALLATORIxDEMO(Select select, String name) {	
      SelectBody a = select.getSelectBody();	
      ArrayList a = new ArrayList();	
      a.add(new SelectExpressionItem(new Column((new StringBuilder()).insert(0, "count(").append(name).append(")").toString())));	
      if (a instanceof PlainSelect && this.ALLATORIxDEMO((PlainSelect)a)) {	
         ((PlainSelect)a).setSelectItems(a);	
      } else {	
         PlainSelect a = new PlainSelect();	
         SubSelect a = new SubSelect();	
         a.setSelectBody(a);	
         a.setAlias(ALLATORIxDEMO);	
         a.setFromItem(a);	
         a.setSelectItems(a);	
         select.setSelectBody(a);	
      }	
   }	
	
   public String h(String sql) {	
      return this.ALLATORIxDEMO(sql, "0");	
   }	
	
   public String ALLATORIxDEMO(String sql, String name) {	
      StringBuilder a;	
      (a = new StringBuilder(sql.length() + 40)).append("select count(");	
      a.append(name);	
      a.append(") from (");	
      a.append(sql);	
      a.append(") tmp_count");	
      return a.toString();	
   }	
	
   public void ALLATORIxDEMO(PlainSelect plainSelect) {	
      if (!this.ALLATORIxDEMO(plainSelect.getOrderByElements())) {	
         plainSelect.setOrderByElements((List)null);	
      }	
	
      if (plainSelect.getFromItem() != null) {	
         this.ALLATORIxDEMO(plainSelect.getFromItem());	
      }	
	
      if (plainSelect.getJoins() != null && plainSelect.getJoins().size() > 0) {	
         Iterator var3 = plainSelect.getJoins().iterator();	
	
         while(var3.hasNext()) {	
            Join a;	
            if ((a = (Join)var3.next()).getRightItem() != null) {	
               this.ALLATORIxDEMO(a.getRightItem());	
            }	
         }	
      }	
	
   }	
	
   public void ALLATORIxDEMO(FromItem fromItem) {	
      if (fromItem instanceof SubJoin) {	
         SubJoin a;	
         if ((a = (SubJoin)fromItem).getJoin() != null && a.getJoin().getRightItem() != null) {	
            this.ALLATORIxDEMO(a.getJoin().getRightItem());	
         }	
	
         if (a.getLeft() != null) {	
            this.ALLATORIxDEMO(a.getLeft());	
            return;	
         }	
      } else if (fromItem instanceof SubSelect) {	
         SubSelect a;	
         if ((a = (SubSelect)fromItem).getSelectBody() != null) {	
            this.ALLATORIxDEMO(a.getSelectBody());	
            return;	
         }	
      } else {	
         if (fromItem instanceof ValuesList) {	
            return;	
         }	
	
         SubSelect a;	
         LateralSubSelect a;	
         if (fromItem instanceof LateralSubSelect && (a = (LateralSubSelect)fromItem).getSubSelect() != null && (a = a.getSubSelect()).getSelectBody() != null) {	
            this.ALLATORIxDEMO(a.getSelectBody());	
         }	
      }	
	
   }	
	
   public void ALLATORIxDEMO(List withItemsList) {	
      if (withItemsList != null && withItemsList.size() > 0) {	
         Iterator var2;	
         Iterator var10000 = var2 = withItemsList.iterator();	
	
         while(var10000.hasNext()) {	
            WithItem a = (WithItem)var2.next();	
            var10000 = var2;	
            this.ALLATORIxDEMO(a.getSelectBody());	
         }	
      }	
	
   }	
	
   public String ALLATORIxDEMO(String sql) {	
      return this.h(sql, "0");	
   }	
}	
