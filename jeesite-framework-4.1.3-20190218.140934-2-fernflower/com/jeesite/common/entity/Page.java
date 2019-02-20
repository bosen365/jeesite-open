package com.jeesite.common.entity;	
	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryWhere;	
import com.jeesite.common.web.CookieUtils;	
import java.io.Serializable;	
import java.util.ArrayList;	
import java.util.List;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
	
public class Page implements Serializable {	
   private long count;	
   private String funcName;	
   public static final int COUNT_NOT_COUNT = -1;	
   private Map otherData;	
   private static final long serialVersionUID = 1L;	
   private int last;	
   private String funcParam;	
   private int first;	
   private int pageSize;	
   private int bothNum;	
   private int pageNo;	
   public static final int COUNT_ONLY_COUNT = -2;	
   private List list;	
   private int centerNum;	
   private String pageInfo;	
   private String orderBy;	
   private int prev;	
   private int next;	
   public static final int PAGE_SIZE_NOT_PAGING = -1;	
	
   public void setFuncName(String funcName) {	
      this.funcName = funcName;	
   }	
	
   public Page(int pageNo, int pageSize, long count) {	
      this(pageNo, pageSize, count, (List)null);	
   }	
	
   public Page setList(List list) {	
      if (list == null) {	
         list = new ArrayList();	
      }	
	
      this.list = (List)list;	
      return this;	
   }	
	
   public Page(int pageNo, int pageSize, long var3, List list) {	
      this.pageNo = 1;	
      this.pageSize = Global.getPropertyToInteger("page.pageSize", "20");	
      this.list = new ArrayList();	
      this.bothNum = 1;	
      this.centerNum = 5;	
      this.funcName = "page";	
      this.funcParam = "";	
      this.pageNo = pageNo;	
      this.pageSize = pageSize;	
      this.count = var3;	
      if (list != null) {	
         this.list = list;	
      }	
	
   }	
	
   @JsonIgnore	
   public boolean isOnlyCount() {	
      return this.count == -2L;	
   }	
	
   public Page() {	
      this.pageNo = 1;	
      this.pageSize = Global.getPropertyToInteger("page.pageSize", "20");	
      this.list = new ArrayList();	
      this.bothNum = 1;	
      this.centerNum = 5;	
      this.funcName = "page";	
      this.funcParam = "";	
   }	
	
   @JsonIgnore	
   public boolean isNotPaging() {	
      return this.pageSize == -1;	
   }	
	
   @JsonIgnore	
   public int getFirstResult() {	
      int a = (this.getPageNo() - 1) * this.getPageSize();	
      if (this.getCount() != -1L && (long)a >= this.getCount()) {	
         a = 0;	
      }	
	
      return a;	
   }	
	
   /** @deprecated */	
   @Deprecated	
   @JsonIgnore	
   public int getFirst() {	
      return this.first;	
   }	
	
   /** @deprecated */	
   @JsonIgnore	
   @Deprecated	
   public int getNext() {	
      return this.next;	
   }	
	
   @JsonIgnore	
   public String getOrderBy() {	
      return EncodeUtils.sqlFilter(this.orderBy);	
   }	
	
   public String toHtml() {	
      this.initialize();	
      int a;	
      if ((a = this.pageNo - this.centerNum / 2) < this.first) {	
         a = this.first;	
      }	
	
      int a;	
      if ((a = a + this.centerNum - 1) >= this.last) {	
         a = (a = this.last) - this.centerNum + 1;	
      }	
	
      if (a == this.first) {	
         a += this.bothNum;	
      }	
	
      if (a == this.last) {	
         a -= this.bothNum;	
      }	
	
      if (this.last - a <= this.bothNum) {	
         --a;	
      }	
	
      if (a < this.first) {	
         a = this.first;	
      }	
	
      StringBuilder a = new StringBuilder();	
      a.append("<ul class="paginatio">\n");	
      int a = false;	
      Page var10000;	
      if (this.pageNo == this.first) {	
         var10000 = this;	
         a.append("<li class="disabled"><a href="javascript:"><i class="fa fa-angle-left"></i></a></li>\n");	
      } else {	
         a.append((new StringBuilder()).insert(0, "<li><a href="javascript:" oclick="").append(this.funcName).append("(").append(this.prev).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');"> <i class="fa fa-angle-left"></i></a></li>\n").toString());	
         var10000 = this;	
      }	
	
      int a;	
      int var10;	
      StringBuilder var10001;	
      String var10002;	
      for(var10 = a = var10000.first; var10 < this.first + this.bothNum && a < a; var10 = a) {	
         var10001 = (new StringBuilder()).append("<li><a href="javascript:" onclick="").append(this.funcName).append("(").append(a).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');">").append(a + 1 - this.first);	
         var10002 = "</a></li>\n";	
         ++a;	
         a.append(var10001.append(var10002).toString());	
      }	
	
      if (a < a) {	
         var10 = a;	
         a.append("<li class="disabled"><a href="javascript:">...</a></li>\n");	
      } else {	
         ++a;	
         var10 = a;	
      }	
	
      if (var10 > this.last) {	
         a = this.last;	
      }	
	
      for(var10 = a = a; var10 <= a; var10 = a) {	
         if (a == this.pageNo) {	
            a.append((new StringBuilder()).insert(0, "<li class="active"><a href="javascript:">").append(a + 1 - this.first).append("</a></li>\n").toString());	
         } else {	
            a.append((new StringBuilder()).insert(0, "<li><a href="javascript:" oclick="").append(this.funcName).append("(").append(a).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');">").append(a + 1 - this.first).append("</a></li>\n").toString());	
         }	
	
         ++a;	
      }	
	
      if (this.last - a > this.bothNum) {	
         a.append("<li class="disabled"><a href="javascript:">...</a></li>\n");	
         a = this.last - this.bothNum;	
      }	
	
      for(var10 = a = a + 1; var10 <= this.last; var10 = a) {	
         var10001 = (new StringBuilder()).insert(0, "<li><a href="javascript:" onclick="").append(this.funcName).append("(").append(a).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');">").append(a + 1 - this.first);	
         var10002 = "</a></li>\n";	
         ++a;	
         a.append(var10001.append(var10002).toString());	
      }	
	
      StringBuilder var12;	
      if (this.pageNo == this.last) {	
         var12 = a;	
         a.append("<li class="disabled"><a href="javascript:"><i class="fa fa-angle-right"></i></a></li>\n");	
      } else {	
         var12 = a;	
         a.append((new StringBuilder()).insert(0, "<li><a href="javascript:" oclick="").append(this.funcName).append("(").append(this.next).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');"><i class="fa fa-agle-right"></i></a></li>\n").toString());	
      }	
	
      var12.append("</ul>\n");	
      String var13 = "点击数字，可填写页码和每页条数，按回车即可生效！";	
      String[] var11 = new String[0];	
      boolean var10003 = true;	
      String a = Global.getText(var13, var11);	
      var13 = "当前";	
      var11 = new String[0];	
      var10003 = true;	
      String a = Global.getText(var13, var11);	
      var13 = "页，每页";	
      var11 = new String[0];	
      var10003 = true;	
      String a = Global.getText(var13, var11);	
      var13 = "条，共 {0} 条";	
      var11 = new String[1];	
      var10003 = true;	
      var11[0] = String.valueOf(this.count);	
      String a = Global.getText(var13, var11);	
      a.append((new StringBuilder()).insert(0, "<li class="disabled controls" title="").append(a).append("">").append(a).append(" ").toString());	
      a.append((new StringBuilder()).insert(0, "<input type="text" value="").append(this.pageNo).append("" onkeypress="var e=widow.evet||event;var c=e.keyCode||e.which;if(c==13)").toString());	
      a.append((new StringBuilder()).insert(0, this.funcName).append("(this.value,").append(this.pageSize).append(",'").append(this.funcParam).append("');" onclick="this.select();"/> ").append(a).append(" ").toString());	
      a.append((new StringBuilder()).insert(0, "<input type="text" value="").append(this.pageSize).append("" onkeypress="var e=widow.evet||event;var c=e.keyCode||e.which;if(c==13)").toString());	
      a.append((new StringBuilder()).insert(0, this.funcName).append("(").append(this.pageNo).append(",this.value,'").append(this.funcParam).append("');" onclick="this.select();"/> ").toString());	
      a.append(a);	
      a.append((this.pageInfo != null && this.pageInfo != "" ? this.pageInfo : "") + "</li>\n");	
      a.append("</ul>\n");	
      a.append("<div style="clear:both;"></div>");	
      return a.toString();	
   }	
	
   public Map getOtherData() {	
      return this.otherData;	
   }	
	
   public int getPageSize() {	
      return this.pageSize;	
   }	
	
   public List getList() {	
      return this.list;	
   }	
	
   public void setFuncParam(String funcParam) {	
      this.funcParam = funcParam;	
   }	
	
   /** @deprecated */	
   @Deprecated	
   @JsonIgnore	
   public int getPrev() {	
      return this.prev;	
   }	
	
   public void addOtherData(String key, Object value) {	
      if (this.otherData == null) {	
         this.otherData = MapUtils.newHashMap();	
      }	
	
      this.otherData.put(key, value);	
   }	
	
   public void setCount(long count) {	
      if ((this.count = count) != -1L && (long)this.pageSize >= count) {	
         this.pageNo = 1;	
      }	
	
   }	
	
   @JsonIgnore	
   public boolean isNotCount() {	
      return this.count == -1L || this.isNotPaging();	
   }	
	
   public void setBothNum(int bothNum) {	
      this.bothNum = bothNum;	
   }	
	
   public void setCenterNum(int centerNum) {	
      this.centerNum = centerNum;	
   }	
	
   public void setPageNo(int pageNo) {	
      this.pageNo = pageNo;	
   }	
	
   public Page(HttpServletRequest request, HttpServletResponse response) {	
      this(request, response, -9);	
   }	
	
   /** @deprecated */	
   @Deprecated	
   @JsonIgnore	
   public int getLast() {	
      return this.last;	
   }	
	
   public void setOtherData(Map otherData) {	
      this.otherData = otherData;	
   }	
	
   public void setOrderBy(String orderBy) {	
      this.orderBy = orderBy;	
   }	
	
   public long getCount() {	
      return this.count;	
   }	
	
   public Page(int pageNo, int pageSize) {	
      this(pageNo, pageSize, 0L);	
   }	
	
   public void setPageSize(int pageSize) {	
      if (pageSize <= 0) {	
         this.pageSize = 20;	
      } else {	
         this.pageSize = pageSize;	
      }	
   }	
	
   @JsonIgnore	
   public int getMaxResults() {	
      return this.getPageSize();	
   }	
	
   public void initialize() {	
      if (!this.isNotPaging() && !this.isNotCount() && !this.isOnlyCount()) {	
         if (this.pageSize <= 0) {	
            this.pageSize = 20;	
         }	
	
         this.first = 1;	
         this.last = (int)(this.count / (long)this.pageSize) + this.first - 1;	
         if (this.count % (long)this.pageSize != 0L || this.last == 0) {	
            ++this.last;	
         }	
	
         if (this.last < this.first) {	
            this.last = this.first;	
         }	
	
         if (this.pageNo <= this.first) {	
            this.pageNo = this.first;	
         }	
	
         if (this.pageNo >= this.last) {	
            this.pageNo = this.last;	
         }	
	
         Page var10000;	
         if (this.pageNo > 1) {	
            var10000 = this;	
            this.prev = this.pageNo - 1;	
         } else {	
            var10000 = this;	
            this.prev = this.first;	
         }	
	
         if (var10000.pageNo < this.last - 1) {	
            this.next = this.pageNo + 1;	
         } else {	
            this.next = this.last;	
         }	
      }	
   }	
	
   public Page(HttpServletRequest request, HttpServletResponse response, int defaultPageSize) {	
      this.pageNo = 1;	
      this.pageSize = Global.getPropertyToInteger("page.pageSize", "20");	
      this.list = new ArrayList();	
      this.bothNum = 1;	
      this.centerNum = 5;	
      this.funcName = "page";	
      this.funcParam = "";	
      HttpServletRequest var10000;	
      String a;	
      if (StringUtils.isNumeric(a = request.getParameter("pageNo"))) {	
         var10000 = request;	
         CookieUtils.setCookie(response, "pageNo", a);	
         this.setPageNo(Integer.parseInt(a));	
      } else {	
         if (request.getParameter("repage") != null && StringUtils.isNumeric(a = CookieUtils.getCookie(request, "pageNo"))) {	
            this.setPageNo(Integer.parseInt(a));	
         }	
	
         var10000 = request;	
      }	
	
      String a;	
      if (StringUtils.isNumeric(a = var10000.getParameter("pageSize"))) {	
         var10000 = request;	
         CookieUtils.setCookie(response, "pageSize", a);	
         this.setPageSize(Integer.parseInt(a));	
      } else {	
         label33: {	
            if (request.getParameter("repage") != null) {	
               if (StringUtils.isNumeric(a = CookieUtils.getCookie(request, "pageSize"))) {	
                  var10000 = request;	
                  this.setPageSize(Integer.parseInt(a));	
                  break label33;	
               }	
            } else if (defaultPageSize != -9) {	
               this.pageSize = defaultPageSize;	
            }	
	
            var10000 = request;	
         }	
      }	
	
      String a;	
      if (StringUtils.isNotBlank(a = var10000.getParameter("orderBy"))) {	
         this.setOrderBy(a);	
      }	
	
   }	
	
   public int getPageNo() {	
      return this.pageNo;	
   }	
	
   public void setPageInfo(String pageInfo) {	
      this.pageInfo = pageInfo;	
   }	
}	
