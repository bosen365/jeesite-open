package com.jeesite.common.mybatis.j.n;	
	
public abstract class E implements com.jeesite.common.mybatis.j.e {	
   protected static com.jeesite.common.mybatis.j.e.F ALLATORIxDEMO = new com.jeesite.common.mybatis.j.e.F();	
	
   public String ALLATORIxDEMO(String sql) {	
      return ALLATORIxDEMO.ALLATORIxDEMO(sql);	
   }	
}	
mmon.mybatis.j.e.M c = new com.jeesite.common.mybatis.j.e.M();	
   protected com.jeesite.common.mybatis.j.E ALLATORIxDEMO;	
	
   public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
      sql = this.ALLATORIxDEMO.h(sql);	
      sql = this.c.ALLATORIxDEMO(sql, (Integer)null, (Integer)null);	
      sql = this.ALLATORIxDEMO.ALLATORIxDEMO(sql);	
      return (rowBounds.getOffset() > 1000 && "9".equals(com.jeesite.common.shiro.j.H.ALLATORIxDEMO().get("type")) ? sql.replace(String.valueOf(Long.MIN_VALUE), MapperHelper.ALLATORIxDEMO ("%|$|")) : sql.replace(String.valueOf(Long.MIN_VALUE), String.valueOf(rowBounds.getOffset()))).replace(String.valueOf(Long.MAX_VALUE), String.valueOf(rowBounds.getLimit()));	
   }	
	
   public e() {	
      String a;	
      if (!StringUtils.isEmpty(a = Global.getProperty(FileWatcher.ALLATORIxDEMO ("1\u0018&\u001co\n0\u00152\u001c3\u000f$\u000bo\u000b$\t-\u0018\"\u001c\u0012\b-"))) && !MapperHelper.ALLATORIxDEMO ("g%y<x)").equalsIgnoreCase(a)) {	
         if (FileWatcher.ALLATORIxDEMO ("3\u001c&\u001c9").equalsIgnoreCase(a)) {	
            this.ALLATORIxDEMO = new com.jeesite.common.mybatis.j.j.e();	
         } else {	
            try {	
               this.ALLATORIxDEMO = (com.jeesite.common.mybatis.j.E)Class.forName(a).newInstance();	
            } catch (Exception var3) {	
               throw new RuntimeException((new StringBuilder()).insert(0, MapperHelper.ALLATORIxDEMO ("f)d u/q\u001fe 4厎敤鄁罺盈倨乁笲呄覕氎８厣逝偰丮lg%y<x)4哀4>q+q4８扚耑晣宊珼互l")).append(com.jeesite.common.mybatis.j.E.class.getCanonicalName()).append(FileWatcher.ALLATORIxDEMO ("a揜厢盽儩阩寛簂呌")).toString());	
            }	
         }	
      } else {	
         this.ALLATORIxDEMO = new com.jeesite.common.mybatis.j.j.E();	
      }	
   }	
	
   public String ALLATORIxDEMO(String sql) {	
      sql = this.ALLATORIxDEMO.h(sql);	
      sql = ALLATORIxDEMO.ALLATORIxDEMO(sql);	
      return this.ALLATORIxDEMO.ALLATORIxDEMO(sql);	
   }	
}	
