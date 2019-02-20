/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.ListUtils	
 *  com.jeesite.common.collect.MapUtils	
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
 *  org.springframework.web.bind.annotation.RequestParam	
 *  org.springframework.web.bind.annotation.ResponseBody	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.DictType;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.service.DictTypeService;	
import java.util.ArrayList;	
import java.util.HashMap;	
import java.util.List;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.NfsClientV3;	
import org.hyperic.sigar.cmd.Tail;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.validation.annotation.Validated;	
import org.springframework.web.bind.annotation.ModelAttribute;	
import org.springframework.web.bind.annotation.PostMapping;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.RequestParam;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping(value={"${adminPath}/sys/dictType"})	
@ConditionalOnProperty(name={"web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class DictTypeController	
extends BaseController {	
    @Autowired	
    private DictTypeService dictTypeService;	
	
    @RequiresPermissions(value={"sys:dictType:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public Page<DictType> listData(DictType dictType, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {	
        void dictType2;	
        void request;	
        void response;	
        dictType2.setPage(new Page((HttpServletRequest)request, (HttpServletResponse)response));	
        return this.dictTypeService.findPage((DictType)dictType2);	
    }	
	
    @RequiresPermissions(value={"sys:dictType:edit"})	
    @RequestMapping(value={"checkDictType"})	
    @ResponseBody	
    public String checkDictType(String oldDictType, String dictType) {	
        DictType a2 = new DictType();	
        String string = dictType;	
        a2.setDictType(string);	
        if (string != null && dictType.equals(oldDictType)) {	
            return "true";	
        }	
        if (dictType != null && this.dictTypeService.findCount(a2) == 0L) {	
            return "true";	
        }	
        return "false";	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"treeData"})	
    @ResponseBody	
    public List<Map<String, Object>> treeData(String dictType, String excludeCode, @RequestParam(defaultValue="1") String isShowCode) {	
        int a2;	
        ArrayList a3 = ListUtils.newArrayList();	
        List<DictType> a4 = this.dictTypeService.findList(new DictType());	
        int n = a2 = 0;	
        while (n < a4.size()) {	
            DictType a5 = a4.get(a2);	
            if (!(!"0".equals(a5.getStatus()) || StringUtils.isNotBlank((CharSequence)excludeCode) && a5.getId().equals(excludeCode))) {	
                HashMap a6 = MapUtils.newHashMap();	
                a3.add(a6);	
                a6.put("value", a5.getDictType());	
                a6.put("name", StringUtils.getTreeNodeName((String)isShowCode, (String)a5.getDictType(), (String)a5.getDictName()));	
                a6.put("pId", "0");	
                a6.put("id", a5.getId());	
            }	
            n = ++a2;	
        }	
        return a3;	
    }	
	
    @ModelAttribute	
    public DictType get(String id, boolean isNewRecord) {	
        return (DictType)this.dictTypeService.get(id, isNewRecord);	
    }	
	
    @RequiresPermissions(value={"sys:dictType:view"})	
    @RequestMapping(value={"form"})	
    public String form(DictType dictType, Model model) {	
        if (StringUtils.isBlank((CharSequence)dictType.getIsSys())) {	
            dictType.setIsSys("1");	
        }	
        model.addAttribute("dictType", (Object)dictType);	
        return "modules/sys/dictTypeForm";	
    }	
	
    @RequiresPermissions(value={"sys:dictType:edit"})	
    @PostMapping(value={"save"})	
    @ResponseBody	
    public String save(@Validated DictType dictType, HttpServletRequest request) {	
        DictType a2 = (DictType)super.getWebDataBinderSource(request);	
        if (a2 != null && "1".equals(a2.getIsSys()) && !dictType.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        this.dictTypeService.save(dictType, a2);	
        return this.renderResult("true", new StringBuilder().insert(0, "保存分类").append(dictType.getDictName()).append("成功！").toString());	
    }	
	
    @RequiresPermissions(value={"sys:dictType:view"})	
    @RequestMapping(value={"list"})	
    public String list(DictType dictType, Model model) {	
        return "modules/sys/dictTypeList";	
    }	
	
    @RequiresPermissions(value={"sys:dictType:edit"})	
    @RequestMapping(value={"delete"})	
    @ResponseBody	
    public String delete(DictType dictType, HttpServletRequest request) {	
        DictType a2 = (DictType)super.getWebDataBinderSource(request);	
        if (a2 != null && "1".equals(a2.getIsSys()) && !dictType.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        this.dictTypeService.delete(dictType);	
        return this.renderResult("true", new StringBuilder().insert(0, "删除分类").append(dictType.getDictName()).append("成功！").toString());	
    }	
	
    @RequiresPermissions(value={"sys:dictType:edit"})	
    @RequestMapping(value={"enable"})	
    @ResponseBody	
    public String enable(DictType dictType) {	
        DictTypeController dictTypeController = this;	
        dictType.setStatus("0");	
        dictTypeController.dictTypeService.updateStatus(dictType);	
        return dictTypeController.renderResult("true", "启用字典类型成功");	
    }	
	
    @RequiresPermissions(value={"sys:dictType:edit"})	
    @RequestMapping(value={"disable"})	
    @ResponseBody	
    public String disable(DictType dictType) {	
        DictTypeController dictTypeController = this;	
        dictType.setStatus("2");	
        dictTypeController.dictTypeService.updateStatus(dictType);	
        return dictTypeController.renderResult("true", "停用字典类型成功");	
    }	
}	
	
