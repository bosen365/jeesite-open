/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.gen.web;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.io.FileUtils;	
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
import java.util.List;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.Mem;	
import org.hyperic.sigar.ThreadCpu;	
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
@RequestMapping(value={"${adminPath}/gen/genTable"})	
@ConditionalOnProperty(name={"gen.enabled"}, havingValue="true", matchIfMissing=true)	
public class GenTableController	
extends BaseController {	
    @Autowired	
    private GenTableService genTableService;	
    @Autowired	
    private DictTypeService dictTypeService;	
	
    @RequiresPermissions(value={"gen:genTable:view"})	
    @RequestMapping(value={"form"})	
    public String form(GenTable genTable, String op, Model model, RedirectAttributes redirectAttributes) {	
        boolean a = genTable.getIsNewRecord();	
        if (a && StringUtils.isNotBlank(genTable.getTableName()) && !this.genTableService.checkTableName(genTable.getTableName())) {	
            this.addMessage(redirectAttributes, genTable.getTableName() + " 表已经添加！这里已为您跳转！");	
            return new StringBuilder().insert(0, "redirect:").append(this.adminPath).append("/gen/genTable/form?tableName=").append(genTable.getTableName()).toString();	
        }	
        genTable = this.genTableService.getFromDb(genTable);	
        if (a) {	
            genTable.setIsNewRecord(a);	
        }	
        GenConfig a2 = GenUtils.getConfig();	
        List<DictType> a3 = this.dictTypeService.findList(new DictType());	
        model.addAttribute("dictTypeList", a3);	
        model.addAttribute("config", a2);	
        model.addAttribute("genTable", genTable);	
        if (StringUtils.isBlank(genTable.getTableName())) {	
            void a4;	
            void a5;	
            GenTable genTable2 = new GenTable();	
            a4.setDataSourceName(genTable.getDataSourceName());	
            List<GenTable> list = this.genTableService.findListFromDb((GenTable)a4);	
            model.addAttribute("tableList", a5);	
            return "modules/gen/genTableFormSelect";	
        }	
        GenTable a6 = new GenTable();	
        List<GenTable> a7 = this.genTableService.findList(a6);	
        List<GenTable> a8 = this.genTableService.findGenBaseDirList(new GenTable());	
        String a9 = FileUtils.getProjectPath();	
        model.addAttribute("defaultGenBaseDir", a9);	
        model.addAttribute("genBaseDirList", a8);	
        model.addAttribute("tableList", a7);	
        if (StringUtils.isBlank(genTable.getGenBaseDir())) {	
            genTable.setGenBaseDir(a9);	
        }	
        if (StringUtils.isNotBlank(genTable.getTplCategory())) {	
            genTable.setGenFlag("0");	
        }	
        if (StringUtils.isBlank(op)) {	
            op = "step1";	
        }	
        model.addAttribute("op", op);	
        return "modules/gen/genTableForm";	
    }	
	
    @RequiresPermissions(value={"gen:genTable:edit"})	
    @RequestMapping(value={"createMenu"})	
    public String createMenu(GenTable genTable, Model model) {	
        void a;	
        StringBuilder a2 = new StringBuilder();	
        Map<String, Object> map = GenUtils.getDataModel(genTable);	
        a2.append(new StringBuilder().insert(0, "&permission=").append(a.get("permissionPrefix")).append(":view,").append(a.get("permissionPrefix")).append(":edit").toString());	
        a2.append(new StringBuilder().insert(0, "&menuHref=/").append(a.get("urlPrefix")).append("/list").toString());	
        a2.append("UmenuName=" + EncodeUtils.encodeUrl((String)a.get("functionName")));	
        return new StringBuilder().insert(0, "redirect:").append(this.adminPath).append("/sys/menu/form").append(a2.toString()).toString();	
    }	
	
    @RequiresPermissions(value={"gen:genTable:edit"})	
    @PostMapping(value={"save"})	
    @ResponseBody	
    public String save(@Validated GenTable genTable, Model model) {	
        boolean a = false;	
        boolean a2 = false;	
        boolean a3 = false;	
        boolean a4 = false;	
        boolean a5 = false;	
        boolean a6 = false;	
        for (GenTableColumn a7 : genTable.getColumnList()) {	
            if ("1".equals(a7.getIsPk())) {	
                a = true;	
            }	
            if ("1".equals(a7.getIsRequired())) {	
                a2 = true;	
            }	
            if ("1".equals(a7.getIsInsert())) {	
                a3 = true;	
            }	
            if ("1".equals(a7.getIsUpdate())) {	
                a4 = true;	
            }	
            if ("1".equals(a7.getIsList())) {	
                a5 = true;	
            }	
            if (!"1".equals(a7.getIsQuery())) continue;	
            a6 = true;	
        }	
        if (!a) {	
            return this.renderResult("false", "必须选择一列作为主键！");	
        }	
        if (!(a2 && a3 && a4 && a5 && a6)) {	
            return this.renderResult("false", "复选框的列必须选择一项！");	
        }	
        this.genTableService.save(genTable);	
        String a8 = new StringBuilder().insert(0, "保存表'").append(genTable.getTableName()).append("'成功").toString();	
        if (StringUtils.inString(genTable.getGenFlag(), "1", "2")) {	
            String a7 = this.genTableService.generateCode(genTable);	
            String a9 = "1".equals(genTable.getGenFlag()) ? "编译" : "生成";	
            a8 = new StringBuilder().insert(0, "posfull:").append(a9).append("'").append(genTable.getTableName()).append("'成功: <r/>").append(a7).toString();	
        }	
        return this.renderResult("true", a8);	
    }	
	
    @RequiresPermissions(value={"gen:genTable:edit"})	
    @RequestMapping(value={"delete"})	
    @ResponseBody	
    public String delete(GenTable genTable) {	
        void genTable2;	
        GenTableController genTableController = this;	
        genTableController.genTableService.delete((GenTable)genTable2);	
        return genTableController.renderResult("true", "删除表成功");	
    }	
	
    @RequiresPermissions(value={"gen:genTable:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public Page<GenTable> listData(GenTable genTable, HttpServletRequest request, HttpServletResponse response) {	
        User a = genTable.getCurrentUser();	
        if (!a.isSuperAdmin()) {	
            genTable.setCreateBy(a.getUserCode());	
        }	
        genTable.setPage(new Page(request, response));	
        return this.genTableService.findPage(genTable);	
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
	
    @RequiresPermissions(value={"gen:genTable:view"})	
    @RequestMapping(value={"list"})	
    public String list(GenTable genTable, Model model) {	
        return "modules/gen/genTableList";	
    }	
}	
	
