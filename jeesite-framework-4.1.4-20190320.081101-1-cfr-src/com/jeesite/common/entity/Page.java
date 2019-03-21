/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.entity;	
	
import com.fasterxml.jackson.annotation.JsonIgnore;	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.j2cache.cache.support.utils.J2CacheConfigUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.CookieUtils;	
import java.io.Serializable;	
import java.util.ArrayList;	
import java.util.List;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
	
public class Page<T>	
implements Serializable {	
    private int prev;	
    private int centerNum;	
    private String orderBy;	
    public static final int COUNT_NOT_COUNT = -1;	
    private int first;	
    private List<T> list;	
    private String funcName;	
    private int bothNum;	
    private int next;	
    private static final long serialVersionUID = 1L;	
    private String pageInfo;	
    private int pageSize;	
    public static final int COUNT_ONLY_COUNT = -2;	
    private String funcParam;	
    private long count;	
    private int last;	
    public static final int PAGE_SIZE_NOT_PAGING = -1;	
    private Map<String, Object> otherData;	
    private int pageNo;	
	
    @Deprecated	
    @JsonIgnore	
    public int getPrev() {	
        return this.prev;	
    }	
	
    public Page(HttpServletRequest request, HttpServletResponse response) {	
        this(request, response, -9);	
    }	
	
    public void setPageNo(int pageNo) {	
        this.pageNo = pageNo;	
    }	
	
    public Page(int pageNo, int pageSize) {	
        this(pageNo, pageSize, 0L);	
    }	
	
    public void setPageInfo(String pageInfo) {	
        this.pageInfo = pageInfo;	
    }	
	
    public int getPageSize() {	
        return this.pageSize;	
    }	
	
    public Map<String, Object> getOtherData() {	
        return this.otherData;	
    }	
	
    public Page(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int n) {	
        void v6;	
        block6 : {	
            void request;	
            block8 : {	
                void defaultPageSize;	
                block7 : {	
                    String a;	
                    block5 : {	
                        void v4;	
                        void response;	
                        Page page = this;	
                        Page page2 = this;	
                        Page page3 = this;	
                        page3.pageNo = 1;	
                        page3.pageSize = Global.getPropertyToInteger("page.pageSize", "20");	
                        Page page4 = this;	
                        page3.list = new ArrayList<T>();	
                        page2.bothNum = 1;	
                        page2.centerNum = 5;	
                        page.funcName = "page";	
                        page.funcParam = "";	
                        String a2 = httpServletRequest.getParameter("pageNo");	
                        if (StringUtils.isNumeric(a2)) {	
                            v4 = request;	
                            String string = a2;	
                            CookieUtils.setCookie((HttpServletResponse)response, "pageNo", string);	
                            this.setPageNo(Integer.parseInt(string));	
                        } else {	
                            if (request.getParameter("repage") != null && StringUtils.isNumeric(a2 = CookieUtils.getCookie((HttpServletRequest)request, "pageNo"))) {	
                                this.setPageNo(Integer.parseInt(a2));	
                            }	
                            v4 = request;	
                        }	
                        a = v4.getParameter("pageSize");	
                        if (!StringUtils.isNumeric(a)) break block5;	
                        v6 = request;	
                        String string = a;	
                        CookieUtils.setCookie((HttpServletResponse)response, "pageSize", string);	
                        this.setPageSize(Integer.parseInt(string));	
                        break block6;	
                    }	
                    if (request.getParameter("repage") == null) break block7;	
                    a = CookieUtils.getCookie((HttpServletRequest)request, "pageSize");	
                    if (!StringUtils.isNumeric(a)) break block8;	
                    v6 = request;	
                    this.setPageSize(Integer.parseInt(a));	
                    break block6;	
                }	
                if (defaultPageSize != -9) {	
                    this.pageSize = defaultPageSize;	
                }	
            }	
            v6 = request;	
        }	
        String a = v6.getParameter("orderBy");	
        if (StringUtils.isNotBlank(a)) {	
            this.setOrderBy(a);	
        }	
    }	
	
    @JsonIgnore	
    public int getFirstResult() {	
        Page page = this;	
        int a = (this.getPageNo() - 1) * page.getPageSize();	
        if (page.getCount() != -1L && (long)a >= this.getCount()) {	
            a = 0;	
        }	
        return a;	
    }	
	
    public Page<T> setList(List<T> list) {	
        if (list == null) {	
            list = new ArrayList<T>();	
        }	
        this.list = list;	
        return this;	
    }	
	
    public void setFuncParam(String funcParam) {	
        this.funcParam = funcParam;	
    }	
	
    @JsonIgnore	
    public boolean isNotCount() {	
        return this.count == -1L || this.isNotPaging();	
    }	
	
    public void setOtherData(Map<String, Object> otherData) {	
        this.otherData = otherData;	
    }	
	
    @JsonIgnore	
    public int getMaxResults() {	
        return this.getPageSize();	
    }	
	
    public void addOtherData(String key, Object value) {	
        if (this.otherData == null) {	
            this.otherData = MapUtils.newHashMap();	
        }	
        this.otherData.put(key, value);	
    }	
	
    public void setOrderBy(String orderBy) {	
        this.orderBy = orderBy;	
    }	
	
    @JsonIgnore	
    public boolean isNotPaging() {	
        return this.pageSize == -1;	
    }	
	
    @JsonIgnore	
    public String getOrderBy() {	
        return EncodeUtils.sqlFilter(this.orderBy);	
    }	
	
    public Page() {	
        Page page = this;	
        Page page2 = this;	
        Page page3 = this;	
        page3.pageNo = 1;	
        page3.pageSize = Global.getPropertyToInteger("page.pageSize", "20");	
        Page page4 = this;	
        page3.list = new ArrayList<T>();	
        page2.bothNum = 1;	
        page2.centerNum = 5;	
        page.funcName = "page";	
        page.funcParam = "";	
    }	
	
    public Page(int n, int n2, long l2, List<T> list) {	
        void count;	
        void pageSize;	
        void pageNo;	
        Page page = this;	
        Page page2 = this;	
        Page page3 = this;	
        Page page4 = this;	
        this.pageNo = 1;	
        page4.pageSize = Global.getPropertyToInteger("page.pageSize", "20");	
        Page page5 = this;	
        page4.list = new ArrayList<T>();	
        page4.bothNum = 1;	
        page3.centerNum = 5;	
        page3.funcName = "page";	
        page2.funcParam = "";	
        page2.pageNo = pageNo;	
        page.pageSize = pageSize;	
        page.count = count;	
        if (list != null) {	
            void list2;	
            this.list = list2;	
        }	
    }	
	
    @Deprecated	
    @JsonIgnore	
    public int getLast() {	
        return this.last;	
    }	
	
    public Page(int pageNo, int pageSize, long count) {	
        this(pageNo, pageSize, count, null);	
    }	
	
    public void setCenterNum(int centerNum) {	
        this.centerNum = centerNum;	
    }	
	
    public void setPageSize(int pageSize) {	
        if (pageSize <= 0) {	
            this.pageSize = 20;	
            return;	
        }	
        this.pageSize = pageSize;	
    }	
	
    @JsonIgnore	
    public boolean isOnlyCount() {	
        return this.count == -2L;	
    }	
	
    @JsonIgnore	
    @Deprecated	
    public int getNext() {	
        return this.next;	
    }	
	
    public void initialize() {	
        Page page;	
        if (this.isNotPaging() || this.isNotCount() || this.isOnlyCount()) {	
            return;	
        }	
        if (this.pageSize <= 0) {	
            this.pageSize = 20;	
        }	
        Page page2 = this;	
        page2.first = 1;	
        this.last = (int)(page2.count / (long)this.pageSize) + this.first - 1;	
        if (page2.count % (long)this.pageSize != 0L || this.last == 0) {	
            ++this.last;	
        }	
        Page page3 = this;	
        if (page3.last < page3.first) {	
            this.last = this.first;	
        }	
        Page page4 = this;	
        if (page4.pageNo <= page4.first) {	
            this.pageNo = this.first;	
        }	
        Page page5 = this;	
        if (page5.pageNo >= page5.last) {	
            this.pageNo = this.last;	
        }	
        if (this.pageNo > 1) {	
            Page page6 = this;	
            page = page6;	
            page6.prev = page6.pageNo - 1;	
        } else {	
            Page page7 = this;	
            page = page7;	
            page7.prev = page7.first;	
        }	
        if (page.pageNo < this.last - 1) {	
            this.next = this.pageNo + 1;	
            return;	
        }	
        this.next = this.last;	
    }	
	
    public String toHtml() {	
        Page page;	
        int a;	
        StringBuilder stringBuilder;	
        void a2;	
        int n;	
        Page page2 = this;	
        page2.initialize();	
        int a3 = page2.pageNo - this.centerNum / 2;	
        if (a3 < this.first) {	
            a3 = this.first;	
        }	
        if ((a = a3 + this.centerNum - 1) >= this.last) {	
            a = this.last;	
            a3 = a - this.centerNum + 1;	
        }	
        if (a3 == this.first) {	
            a += this.bothNum;	
        }	
        if (a == this.last) {	
            a3 -= this.bothNum;	
        }	
        if (this.last - a <= this.bothNum) {	
            --a3;	
        }	
        if (a3 < this.first) {	
            a3 = this.first;	
        }	
        StringBuilder a4 = new StringBuilder();	
        int a5 = 0;	
        a4.append("<ul class="pagination">\n");	
        if (this.pageNo == this.first) {	
            page = this;	
            a4.append("<li class="disabled"><a href="javascript:"><i class="fa fa-angle-left"></i></a></li>\n");	
        } else {	
            a4.append(new StringBuilder().insert(0, "Vli>Va href="javascript:" onclick="").append(this.funcName).append("(").append(this.prev).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');"> <i class="fa fa-angle-left"></i></a></li>\n").toString());	
            page = this;	
        }	
        int n2 = a5 = page.first;	
        do {	
            Page page3 = this;	
            if (n2 >= page3.first + page3.bothNum || a5 >= a3) break;	
            StringBuilder stringBuilder2 = new StringBuilder().append("<li><a href="javascript:" onclick="").append(this.funcName).append("(").append(a5).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');">").append(a5 + 1 - this.first);	
            a4.append(stringBuilder2.append("</a></li>\n").toString());	
            n2 = ++a5;	
        } while (true);	
        if (a5 < a3) {	
            n = a;	
            a4.append("<li class="disabled"><a href="javascript:">...</a></li>\n");	
        } else {	
            n = ++a;	
        }	
        if (n > this.last) {	
            a = this.last;	
        }	
        int n3 = a5 = a3;	
        while (n3 <= a) {	
            if (a5 == this.pageNo) {	
                a4.append(new StringBuilder().insert(0, "Vli class="active"><a href="javascript:">").append(a5 + 1 - this.first).append("</a></li>\n").toString());	
            } else {	
                a4.append(new StringBuilder().insert(0, "Vli>Va href="javascript:" onclick="").append(this.funcName).append("(").append(a5).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');">").append(a5 + 1 - this.first).append("</a></li>\n").toString());	
            }	
            n3 = ++a5;	
        }	
        if (this.last - a > this.bothNum) {	
            Page page4 = this;	
            a = page4.last - page4.bothNum;	
            a4.append("<li class="disabled">Va href="javascript:">...</a></li>\n");	
        }	
        int n4 = a5 = a + 1;	
        while (n4 <= this.last) {	
            StringBuilder stringBuilder3 = new StringBuilder().insert(0, "<li><a href="javascript:" onclick="").append(this.funcName).append("(").append(a5).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');">").append(a5 + 1 - this.first);	
            a4.append(stringBuilder3.append("</a></li>\n").toString());	
            n4 = ++a5;	
        }	
        Page page5 = this;	
        if (page5.pageNo == page5.last) {	
            StringBuilder stringBuilder4 = a4;	
            stringBuilder = stringBuilder4;	
            stringBuilder4.append("<li class="disabled"><a href="javascript:"><i class="fa fa-angle-right"></i></a></li>\n");	
        } else {	
            StringBuilder stringBuilder5 = a4;	
            stringBuilder = stringBuilder5;	
            stringBuilder5.append(new StringBuilder().insert(0, "Vli>Va href="javascript:" onclick="").append(this.funcName).append("(").append(this.next).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');">Vi class="fa fa-angle-right"></i></a></li>\n").toString());	
        }	
        stringBuilder.append("</ul>\n");	
        String a6 = Global.getText("点击数字，可填写页码和每页条数，按回车即可生效！", new String[0]);	
        String a7 = Global.getText("当前", new String[0]);	
        String a8 = Global.getText("页，每页", new String[0]);	
        String string = Global.getText("条，共 {0} 条", String.valueOf(this.count));	
        StringBuilder stringBuilder6 = a4;	
        a4.append((String)a2);	
        a4.append(new StringBuilder().insert(0, this.funcName).append("(").append(this.pageNo).append(",this.value,'").append(this.funcParam).append("');" onclick="this.select();"/> ").toString());	
        a4.append(new StringBuilder().insert(0, "<input type="text" value="").append(this.pageSize).append("" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)").toString());	
        stringBuilder6.append(new StringBuilder().insert(0, this.funcName).append("(this.value,").append(this.pageSize).append(",'").append(this.funcParam).append("');" onclick="this.select();"/> ").append(a8).append(" ").toString());	
        a4.append(new StringBuilder().insert(0, "<input type="text" value="").append(this.pageNo).append("" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)").toString());	
        a4.append(new StringBuilder().insert(0, "Vli class="disabled controls" title="").append(a6).append("">").append(a7).append(" ").toString());	
        a4.append("Vdiv style="clear:both;"></div>");	
        a4.append("</ul>\n");	
        stringBuilder6.append((this.pageInfo != null && this.pageInfo != "" ? this.pageInfo : "") + "</li>\n");	
        return a4.toString();	
    }	
	
    public void setCount(long count) {	
        this.count = count;	
        if (this.count != -1L && (long)this.pageSize >= count) {	
            this.pageNo = 1;	
        }	
    }	
	
    public void setBothNum(int bothNum) {	
        this.bothNum = bothNum;	
    }	
	
    @Deprecated	
    @JsonIgnore	
    public int getFirst() {	
        return this.first;	
    }	
	
    public void setFuncName(String funcName) {	
        this.funcName = funcName;	
    }	
	
    public List<T> getList() {	
        return this.list;	
    }	
	
    public int getPageNo() {	
        return this.pageNo;	
    }	
	
    public long getCount() {	
        return this.count;	
    }	
}	
	
