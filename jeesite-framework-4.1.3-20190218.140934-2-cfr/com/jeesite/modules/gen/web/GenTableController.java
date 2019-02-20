/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.codec.EncodeUtils	
 *  com.jeesite.common.io.FileUtils	
 *  com.jeesite.common.lang.StringUtils	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpServletResponse	
 *  org.apache.shiro.authz.annotation.RequiresPermissions	
 *  org.springframework.beans.factory.annotation.Autowired	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.stereotype.Controller	
 *  org.springframework.ui.Model	
 *  org.springframework.validation.annotation.Validated	
 *  org.springframework.web.bind.annotation.ModelAttribute	
 *  org.springframework.web.bind.annotation.PostMapping	
 *  org.springframework.web.bind.annotation.RequestMapping	
 *  org.springframework.web.bind.annotation.ResponseBody	
 *  org.springframework.web.servlet.mvc.support.RedirectAttributes	
 */	
package com.jeesite.modules.gen.web;	
	
import com.jeesite.common.codec.EncodeUtils;	
import com.jeesite.common.entity.DataEntity;	
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
        boolean a2 = genTable.getIsNewRecord();	
        if (a2 && StringUtils.isNotBlank((CharSequence)genTable.getTableName()) && !this.genTableService.checkTableName(genTable.getTableName())) {	
            this.addMessage(redirectAttributes, genTable.getTableName() + " 表已经添加！这里已为您跳转！");	
            return new StringBuilder().insert(0, "redirect:").append(this.adminPath).append("/gen/genTable/form?tableName=").append(genTable.getTableName()).toString();	
        }	
        genTable = this.genTableService.getFromDb(genTable);	
        if (a2) {	
            genTable.setIsNewRecord(a2);	
        }	
        GenConfig a3 = GenUtils.getConfig();	
        List<DictType> a4 = this.dictTypeService.findList(new DictType());	
        model.addAttribute("dictTypeList", a4);	
        model.addAttribute("config", (Object)a3);	
        model.addAttribute("geTable", (Object)genTable);	
        if (StringUtils.isBlank((CharSequence)genTable.getTableName())) {	
            void a5;	
            void a6;	
            GenTable genTable2 = new GenTable();	
            a5.setDataSourceName(genTable.getDataSourceName());	
            List<GenTable> list = this.genTableService.findListFromDb((GenTable)a5);	
            model.addAttribute("tableList", (Object)a6);	
            return "modules/ge/geTableFormSelect";	
        }	
        GenTable a7 = new GenTable();	
        List<GenTable> a8 = this.genTableService.findList(a7);	
        List<GenTable> a9 = this.genTableService.findGenBaseDirList(new GenTable());	
        String a10 = FileUtils.getProjectPath();	
        model.addAttribute("defaultGenBaseDir", (Object)a10);	
        model.addAttribute("geBase.irList", a9);	
        model.addAttribute("tableList", a8);	
        if (StringUtils.isBlank((CharSequence)genTable.getGenBaseDir())) {	
            genTable.setGenBaseDir(a10);	
        }	
        if (StringUtils.isNotBlank((CharSequence)genTable.getTplCategory())) {	
            genTable.setGenFlag("0");	
        }	
        if (StringUtils.isBlank((CharSequence)op)) {	
            op = "step1";	
        }	
        model.addAttribute("op", (Object)op);	
        return "modules/ge/geTableForm";	
    }	
	
    @RequiresPermissions(value={"gen:genTable:edit"})	
    @RequestMapping(value={"createMenu"})	
    public String createMenu(GenTable genTable, Model model) {	
        void a2;	
        StringBuilder a3 = new StringBuilder();	
        Map<String, Object> map = GenUtils.getDataModel(genTable);	
        a3.append(new StringBuilder().insert(0, "&permissio=").append(a2.get("permissionPrefix")).append(":view,").append(a2.get("permissionPrefix")).append(":edit").toString());	
        a3.append(new StringBuilder().insert(0, "&menuHref=/").append(a2.get("urlPrefix")).append("/list").toString());	
        a3.append("?menuName=" + EncodeUtils.encodeUrl((String)((String)a2.get("fuctionName"))));	
        return new StringBuilder().insert(0, "redirect:").append(this.adminPath).append("/sys/menu/form").append(a3.toString()).toString();	
    }	
	
    @ModelAttribute	
    public GenTable get(String tableName) {	
        DataEntity a2 = null;	
        if (StringUtils.isNotBlank((CharSequence)tableName)) {	
            a2 = this.genTableService.get(tableName);	
        }	
        if (a2 == null) {	
            a2 = new GenTable();	
        }	
        return a2;	
    }	
	
    @RequiresPermissions(value={"gen:genTable:view"})	
    @RequestMapping(value={"list"})	
    public String list(GenTable genTable, Model model) {	
        return "modules/ge/geTableList";	
    }	
	
    @RequiresPermissions(value={"gen:genTable:edit"})	
    @PostMapping(value={"save"})	
    @ResponseBody	
    public String save(@Validated GenTable genTable, Model model) {	
        boolean a2 = false;	
        boolean a3 = false;	
        boolean a4 = false;	
        boolean a5 = false;	
        boolean a6 = false;	
        boolean a7 = false;	
        for (GenTableColumn a8 : genTable.getColumnList()) {	
            if ("1".equals(a8.getIsPk())) {	
                a2 = true;	
            }	
            if ("1".equals(a8.getIsRequired())) {	
                a3 = true;	
            }	
            if ("1".equals(a8.getIsInsert())) {	
                a4 = true;	
            }	
            if ("1".equals(a8.getIsUpdate())) {	
                a5 = true;	
            }	
            if ("1".equals(a8.getIsList())) {	
                a6 = true;	
            }	
            if (!"1".equals(a8.getIsQuery())) continue;	
            a7 = true;	
        }	
        if (!a2) {	
            return this.renderResult("false", "必须选择一列作为主键！");	
        }	
        if (!(a3 && a4 && a5 && a6 && a7)) {	
            return this.renderResult("false", "复选框的列必须选择一项！");	
        }	
        this.genTableService.save(genTable);	
        String a9 = new StringBuilder().insert(0, "保存表'").append(genTable.getTableName()).append("'成功").toString();	
        if (StringUtils.inString((String)genTable.getGenFlag(), (String[])new String[]{"1", "2"})) {	
            String a8 = this.genTableService.generateCode(genTable);	
            String a10 = "1".equals(genTable.getGenFlag()) ? "编译" : "生成";	
            a9 = new StringBuilder().insert(0, "posfull:").append(a10).append("'").append(genTable.getTableName()).append("'成功: <br/>").append(a8).toString();	
        }	
        return this.renderResult("true", a9);	
    }	
	
    @RequiresPermissions(value={"gen:genTable:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public Page<GenTable> listData(GenTable genTable, HttpServletRequest request, HttpServletResponse response) {	
        User a2 = genTable.getCurrentUser();	
        if (!a2.isSuperAdmin()) {	
            genTable.setCreateBy(a2.getUserCode());	
        }	
        genTable.setPage(new Page(request, response));	
        return this.genTableService.findPage(genTable);	
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
}	
	
