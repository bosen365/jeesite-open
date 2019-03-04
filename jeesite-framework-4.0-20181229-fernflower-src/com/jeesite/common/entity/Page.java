package com.jeesite.common.entity;	
	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.common.web.CookieUtils;	
import java.io.Serializable;	
import java.util.ArrayList;	
import java.util.List;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
	
public class Page implements Serializable {	
   private long count;	
   private Map otherData;	
   private int pageSize;	
   public static final int PAGE_SIZE_NOT_PAGING = -1;	
   private int first;	
   public static final int COUNT_NOT_COUNT = -1;	
   private int last;	
   public static final int COUNT_ONLY_COUNT = -2;	
   private static final long serialVersionUID = 1L;	
   private String funcName;	
   private boolean isFirst;	
   private int pageNo;	
   private int next;	
   private int bothNum;	
   private boolean isLast;	
   private String funcParam;	
   private List list;	
   private String orderBy;	
   private String pageInfo;	
   private int prev;	
   private int centerNum;	
	
   public Page(int pageNo, int pageSize, long count) {	
      this(pageNo, pageSize, count, new ArrayList());	
   }	
	
   public void setPageInfo(String pageInfo) {	
      this.pageInfo = pageInfo;	
   }	
	
   public Page(HttpServletRequest request, HttpServletResponse response) {	
      this(request, response, -9);	
   }	
	
   public void setOrderBy(String orderBy) {	
      this.orderBy = orderBy;	
   }	
	
   public void setFuncName(String funcName) {	
      this.funcName = funcName;	
   }	
	
   public List getList() {	
      return this.list;	
   }	
	
   public int getNext() {	
      return this.isLast ? this.pageNo : this.pageNo + 1;	
   }	
	
   public int getPrev() {	
      return this.isFirst ? this.pageNo : this.pageNo - 1;	
   }	
	
   public void addOtherData(String key, Object value) {	
      if (this.otherData == null) {	
         this.otherData = MapUtils.newHashMap();	
      }	
	
      this.otherData.put(key, value);	
   }	
	
   public int getLast() {	
      return this.last;	
   }	
	
   public Page() {	
      this.pageNo = 1;	
      this.pageSize = ObjectUtils.toInteger(Global.getProperty("page.pageSize", "20"));	
      this.bothNum = 1;	
      this.centerNum = 5;	
      this.list = new ArrayList();	
      this.pageSize = -1;	
   }	
	
   public boolean isFirst() {	
      return this.isFirst;	
   }	
	
   @JsonIgnore	
   public int getFirstResult() {	
      int a = (this.getPageNo() - 1) * this.getPageSize();	
      if (this.getCount() != -1L && (long)a >= this.getCount()) {	
         a = 0;	
      }	
	
      return a;	
   }	
	
   @JsonIgnore	
   public boolean isOnlyCount() {	
      return this.count == -2L;	
   }	
	
   public void setBothNum(int bothNum) {	
      this.bothNum = bothNum;	
   }	
	
   @JsonIgnore	
   public boolean isNotPaging() {	
      return this.pageSize == -1;	
   }	
	
   public void setCenterNum(int centerNum) {	
      this.centerNum = centerNum;	
   }	
	
   @JsonIgnore	
   public String getOrderBy() {	
      return EncodeUtils.sqlFilter(this.orderBy);	
   }	
	
   public long getCount() {	
      return this.count;	
   }	
	
   public void setOtherData(Map otherData) {	
      this.otherData = otherData;	
   }	
	
   public void setCount(long count) {	
      if ((this.count = count) != -1L && (long)this.pageSize >= count) {	
         this.pageNo = 1;	
      }	
	
      this.initialize();	
   }	
	
   public int getFirst() {	
      return this.first;	
   }	
	
   public int getPageSize() {	
      return this.pageSize;	
   }	
	
   public Page(HttpServletRequest request, HttpServletResponse response, int defaultPageSize) {	
      this.pageNo = 1;	
      this.pageSize = ObjectUtils.toInteger(Global.getProperty("page.pageSize", "20"));	
      this.bothNum = 1;	
      this.centerNum = 5;	
      this.list = new ArrayList();	
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
	
   public Page setList(List list) {	
      if (list == null) {	
         list = new ArrayList();	
      }	
	
      if (this.isNotPaging()) {	
         this.count = (long)((List)list).size();	
      }	
	
      this.list = (List)list;	
      return this;	
   }	
	
   public Map getOtherData() {	
      return this.otherData;	
   }	
	
   public void setPageNo(int pageNo) {	
      this.pageNo = pageNo;	
   }	
	
   public Page(int pageNo, int pageSize) {	
      this(pageNo, pageSize, 0L);	
   }	
	
   public String toHtml() {	
      StringBuilder a = new StringBuilder();	
      if (this.funcName == null) {	
         this.funcName = "page";	
      }	
	
      if (this.funcParam == null) {	
         this.funcParam = "";	
      }	
	
      int a;	
      if ((a = this.pageNo - this.centerNum / 2) < this.first) {	
         a = this.first;	
      }	
	
      int a;	
      if ((a = a + this.centerNum - 1) >= this.last && (a = (a = this.last) - this.centerNum + 1) < this.first) {	
         a = this.first;	
      }	
	
      int var10000;	
      if (this.pageNo == this.first) {	
         var10000 = a;	
         a.append("<li class="disabled"><a href="javascript:">&#171; 上一页</a></li>\n");	
      } else {	
         a.append((new StringBuilder()).insert(0, "<li><a href="javascript:" onclick="").append(this.funcName).append("(").append(this.prev).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');">&#171; 上一页</a></li>\n").toString());	
         var10000 = a;	
      }	
	
      StringBuilder var10001;	
      String var10002;	
      int a;	
      if (var10000 > this.first) {	
         int a = false;	
	
         for(var10000 = a = this.first; var10000 < this.first + this.bothNum && a < a; var10000 = a) {	
            var10001 = (new StringBuilder()).append("<li><a hre="javascript:" onclick="").append(this.funcName).append("(").append(a).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');">").append(a + 1 - this.first);	
            var10002 = "</a></li>\n";	
            ++a;	
            a.append(var10001.append(var10002).toString());	
         }	
	
         if (a < a) {	
            a.append("<li class="disabled"><a hre="javascript:">...</a></li>\n");	
         }	
      }	
	
      for(var10000 = a = a; var10000 <= a; var10000 = a) {	
         if (a == this.pageNo) {	
            a.append((new StringBuilder()).insert(0, "<li class="active"><a href="javascript:">").append(a + 1 - this.first).append("</a></li>\n").toString());	
         } else {	
            a.append((new StringBuilder()).insert(0, "<li><a href="javascript:" onclick="").append(this.funcName).append("(").append(a).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');">").append(a + 1 - this.first).append("</a></li>\n").toString());	
         }	
	
         ++a;	
      }	
	
      if (this.last - a > this.bothNum) {	
         a.append("<li class="disabled"><a href="javascript:">...</a></li>\n");	
         a = this.last - this.bothNum;	
      }	
	
      for(var10000 = a = a + 1; var10000 <= this.last; var10000 = a) {	
         var10001 = (new StringBuilder()).insert(0, "<li><a hre="javascript:" onclick="").append(this.funcName).append("(").append(a).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');">").append(a + 1 - this.first);	
         var10002 = "</a></li>\n";	
         ++a;	
         a.append(var10001.append(var10002).toString());	
      }	
	
      StringBuilder var6;	
      if (this.pageNo == this.last) {	
         var6 = a;	
         a.append("<li class="disabled"><a href="javascript:">下一页 &#187;</a></li>\n");	
      } else {	
         var6 = a;	
         a.append((new StringBuilder()).insert(0, "<li><a href="javascript:" onclick="").append(this.funcName).append("(").append(this.next).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');">下一页 &#187;</a></li>\n").toString());	
      }	
	
      var6.append("<li class="disabled controls"><a hre="javascript:" style="cursor:deault;"");	
      a.append(" title="点击数字，可填写页码和每页条数，按回车即可生效！">当前 ");	
      a.append((new StringBuilder()).insert(0, "<input type="text" value="").append(this.pageNo).append("" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)").toString());	
      a.append((new StringBuilder()).insert(0, this.funcName).append("(this.value,").append(this.pageSize).append(",'").append(this.funcParam).append("');" onclick="this.select();"/> 页，每页 ").toString());	
      a.append((new StringBuilder()).insert(0, "<input type="text" value="").append(this.pageSize).append("" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)").toString());	
      a.append((new StringBuilder()).insert(0, this.funcName).append("(").append(this.pageNo).append(",this.value,'").append(this.funcParam).append("');" onclick="this.select();"/> 条，").toString());	
      a.append((new StringBuilder()).insert(0, "共 ").append(this.count).append(" 条").append(this.pageInfo != null ? this.pageInfo : "").append("</a></li>\n").toString());	
      a.insert(0, "<ul class="pagination" style="margin:8px 0 0 0;">\n").append("</ul>\n");	
      a.append("<div style="clear:both;"></div>");	
      return a.toString();	
   }	
	
   @JsonIgnore	
   public boolean isNotCount() {	
      return this.count == -1L || this.isNotPaging();	
   }	
	
   @JsonIgnore	
   public int getMaxResults() {	
      return this.getPageSize();	
   }	
	
   public int getPageNo() {	
      return this.pageNo;	
   }	
	
   public void initialize() {	
      if (!this.isNotPaging() && !this.isNotCount() && !this.isOnlyCount()) {	
         this.first = 1;	
         this.last = (int)(this.count / (long)(this.pageSize < 1 ? 20 : this.pageSize) + (long)this.first - 1L);	
         if (this.count % (long)this.pageSize != 0L || this.last == 0) {	
            ++this.last;	
         }	
	
         if (this.last < this.first) {	
            this.last = this.first;	
         }	
	
         Page var10000;	
         if (this.pageNo <= 1) {	
            var10000 = this;	
            this.pageNo = this.first;	
            this.isFirst = true;	
         } else {	
            var10000 = this;	
            this.isFirst = false;	
         }	
	
         if (var10000.pageNo >= this.last) {	
            var10000 = this;	
            this.pageNo = this.last;	
            this.isLast = true;	
         } else {	
            var10000 = this;	
            this.isLast = false;	
         }	
	
         if (var10000.pageNo < this.last - 1) {	
            var10000 = this;	
            this.next = this.pageNo + 1;	
         } else {	
            var10000 = this;	
            this.next = this.last;	
         }	
	
         if (var10000.pageNo > 1) {	
            var10000 = this;	
            this.prev = this.pageNo - 1;	
         } else {	
            var10000 = this;	
            this.prev = this.first;	
         }	
	
         if (var10000.pageNo < this.first) {	
            this.pageNo = this.first;	
         }	
	
         if (this.pageNo > this.last) {	
            this.pageNo = this.last;	
         }	
	
      }	
   }	
	
   public void setFuncParam(String funcParam) {	
      this.funcParam = funcParam;	
   }	
	
   public void setPageSize(int pageSize) {	
      this.pageSize = pageSize <= 0 ? 10 : pageSize;	
   }	
	
   public Page(int pageNo, int pageSize, long count, List var5) {	
      this.pageNo = 1;	
      this.pageSize = ObjectUtils.toInteger(Global.getProperty("page.pageSize", "20"));	
      this.bothNum = 1;	
      this.centerNum = 5;	
      this.list = new ArrayList();	
      this.pageNo = pageNo;	
      this.pageSize = pageSize;	
      this.count = count;	
      this.list = var5;	
   }	
	
   public boolean isLast() {	
      return this.isLast;	
   }	
}	
