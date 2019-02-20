package com.jeesite.modules.gen.web;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.j2cache.autoconfigure.J2CacheAutoConfiguration;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.gen.entity.GenTable;	
import com.jeesite.modules.gen.entity.GenTableColumn;	
import com.jeesite.modules.gen.entity.config.GenConfig;	
import com.jeesite.modules.gen.service.GenTableService;	
import com.jeesite.modules.gen.utils.GenUtils;	
import com.jeesite.modules.sys.entity.DictType;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.service.DictTypeService;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.jni.ArchLoaderException;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.validation.annotation.Validated;	
import org.springframework.web.bind.annotation.ModelAttribute;	
import org.springframework.web.bind.annotation.PostMapping;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
import org.springframework.web.servlet.mvc.support.RedirectAttributes;	
	
@Controller	
@RequestMapping({"${adminPath}/gen/genTable"})	
@ConditionalOnProperty(	
   name = {"gen.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class GenTableController extends BaseController {	
   @Autowired	
   private GenTableService genTableService;	
   @Autowired	
   private DictTypeService dictTypeService;	
	
   @RequiresPermissions({"gen:genTable:view"})	
   @RequestMapping({"form"})	
   public String form(GenTable genTable, String op, Model model, RedirectAttributes redirectAttributes) {	
      boolean a;	
      if ((a = genTable.getIsNewRecord()) && StringUtils.isNotBlank(genTable.getTableName()) && !this.genTableService.checkTableName(genTable.getTableName())) {	
         String[] var10002 = new String[1];	
         boolean var10004 = true;	
         boolean var10007 = false;	
         var10002[0] = genTable.getTableName() + " 表已经添加！这里已为您跳转！";	
         this.addMessage(redirectAttributes, var10002);	
         return (new StringBuilder()).insert(0, "redirect:").append(this.adminPath).append("/gen/genTable/form?tableName=").append(genTable.getTableName()).toString();	
      } else {	
         genTable = this.genTableService.getFromDb(genTable);	
         if (a) {	
            genTable.setIsNewRecord(a);	
         }	
	
         model.addAttribute("geTable", genTable);	
         GenConfig a = GenUtils.getConfig();	
         model.addAttribute("config", a);	
         List a = this.dictTypeService.findList(new DictType());	
         model.addAttribute("dictTypeList", a);	
         GenTable a;	
         List a;	
         if (StringUtils.isBlank(genTable.getTableName())) {	
            a = new GenTable();	
            a.setDataSourceName(genTable.getDataSourceName());	
            a = this.genTableService.findListFromDb(a);	
            model.addAttribute("tableList", a);	
            return "modules/ge/geTableFormSelect";	
         } else {	
            a = new GenTable();	
            a = this.genTableService.findList(a);	
            model.addAttribute("tableList", a);	
            List a = this.genTableService.findGenBaseDirList(new GenTable());	
            model.addAttribute("geBase.irList", a);	
            String a = FileUtils.getProjectPath();	
            model.addAttribute("defaultGenBaseDir", a);	
            if (StringUtils.isBlank(genTable.getGenBaseDir())) {	
               genTable.setGenBaseDir(a);	
            }	
	
            if (StringUtils.isNotBlank(genTable.getTplCategory())) {	
               genTable.setGenFlag("0");	
            }	
	
            if (StringUtils.isBlank(op)) {	
               op = "step1";	
            }	
	
            model.addAttribute("op", op);	
            return "modules/ge/geTableForm";	
         }	
      }	
   }	
	
   @RequiresPermissions({"gen:genTable:edit"})	
   @RequestMapping({"createMenu"})	
   public String createMenu(GenTable genTable, Model model) {	
      StringBuilder a = new StringBuilder();	
      Map a = GenUtils.getDataModel(genTable);	
      a.append("?menuName=" + EncodeUtils.encodeUrl((String)a.get("fuctionName")));	
      a.append((new StringBuilder()).insert(0, "&menuHref=/").append(a.get("urlPrefix")).append("/list").toString());	
      a.append((new StringBuilder()).insert(0, "&permissio=").append(a.get("permissionPrefix")).append(":view,").append(a.get("permissionPrefix")).append(":edit").toString());	
      return (new StringBuilder()).insert(0, "redirect:").append(this.adminPath).append("/sys/menu/form").append(a.toString()).toString();	
   }	
	
   @ModelAttribute	
   public GenTable get(String tableName) {	
      GenTable a = null;	
      if (StringUtils.isNotBlank(tableName)) {	
         a = this.genTableService.get(tableName);	
      }	
	
      if (a == null) {	
         a = new GenTable();	
      }	
	
      return a;	
   }	
	
   @RequiresPermissions({"gen:genTable:view"})	
   @RequestMapping({"list"})	
   public String list(GenTable genTable, Model model) {	
      return "modules/ge/geTableList";	
   }	
	
   @RequiresPermissions({"gen:genTable:edit"})	
   @PostMapping({"save"})	
   @ResponseBody	
   public String save(@Validated GenTable genTable, Model model) {	
      int a = false;	
      int a = false;	
      int a = false;	
      int a = false;	
      int a = false;	
      int a = false;	
      Iterator var9 = genTable.getColumnList().iterator();	
	
      while(var9.hasNext()) {	
         GenTableColumn a = (GenTableColumn)var9.next();	
         if ("1".equals(a.getIsPk())) {	
            a = true;	
         }	
	
         if ("1".equals(a.getIsRequired())) {	
            a = true;	
         }	
	
         if ("1".equals(a.getIsInsert())) {	
            a = true;	
         }	
	
         if ("1".equals(a.getIsUpdate())) {	
            a = true;	
         }	
	
         if ("1".equals(a.getIsList())) {	
            a = true;	
         }	
	
         if ("1".equals(a.getIsQuery())) {	
            a = true;	
         }	
      }	
	
      if (!a) {	
         return this.renderResult("false", "必须选择一列作为主键！");	
      } else if (a && a && a && a && a) {	
         this.genTableService.save(genTable);	
         String a = (new StringBuilder()).insert(0, "保存表'").append(genTable.getTableName()).append("'成功").toString();	
         String var10000 = genTable.getGenFlag();	
         String[] var10001 = new String[2];	
         boolean var10003 = true;	
         var10001[0] = "1";	
         var10001[1] = "2";	
         if (StringUtils.inString(var10000, var10001)) {	
            String a = this.genTableService.generateCode(genTable);	
            String a = "1".equals(genTable.getGenFlag()) ? "编译" : "生成";	
            a = (new StringBuilder()).insert(0, "posfull:").append(a).append("'").append(genTable.getTableName()).append("'成功: <br/>").append(a).toString();	
         }	
	
         return this.renderResult("true", a);	
      } else {	
         return this.renderResult("false", "复选框的列必须选择一项！");	
      }	
   }	
	
   @RequiresPermissions({"gen:genTable:view"})	
   @RequestMapping({"listData"})	
   @ResponseBody	
   public Page listData(GenTable genTable, HttpServletRequest request, HttpServletResponse response) {	
      User a;	
      if (!(a = genTable.getCurrentUser()).isSuperAdmin()) {	
         genTable.setCreateBy(a.getUserCode());	
      }	
	
      genTable.setPage(new Page(request, response));	
      return this.genTableService.findPage(genTable);	
   }	
	
   @RequiresPermissions({"gen:genTable:edit"})	
   @RequestMapping({"delete"})	
   @ResponseBody	
   public String delete(GenTable genTable) {	
      this.genTableService.delete(genTable);	
      return this.renderResult("true", "删除表成功");	
   }	
}	
