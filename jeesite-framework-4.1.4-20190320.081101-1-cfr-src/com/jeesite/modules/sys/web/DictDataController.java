/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.entity.TreeEntity;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.DictData;	
import com.jeesite.modules.sys.entity.DictType;	
import com.jeesite.modules.sys.entity.User;	
import com.jeesite.modules.sys.service.DictDataService;	
import com.jeesite.modules.sys.service.DictTypeService;	
import com.jeesite.modules.sys.utils.DictUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import com.jeesite.modules.sys.web.AdviceController;	
import java.util.ArrayList;	
import java.util.HashMap;	
import java.util.List;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.NfsServerV2;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.validation.annotation.Validated;	
import org.springframework.web.bind.annotation.ModelAttribute;	
import org.springframework.web.bind.annotation.PostMapping;	
import org.springframework.web.bind.annotation.RequestMapping;	
import org.springframework.web.bind.annotation.ResponseBody;	
	
@Controller	
@RequestMapping(value={"${adminPath}/sys/dictData"})	
@ConditionalOnProperty(name={"web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class DictDataController	
extends BaseController {	
    @Autowired	
    private DictDataService dictDataService;	
    @Autowired	
    private DictTypeService dictTypeService;	
	
    @RequiresPermissions(value={"sys:dictData:edit"})	
    @RequestMapping(value={"enable"})	
    @ResponseBody	
    public String enable(DictData dictData, HttpServletRequest request) {	
        DictData a = (DictData)super.getWebDataBinderSource(request);	
        if (a != null && "1".equals(a.getIsSys()) && !dictData.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        dictData.setStatus("0");	
        DictDataController dictDataController = this;	
        dictDataController.dictDataService.updateStatus(dictData);	
        return dictDataController.renderResult("true", new StringBuilder().insert(0, "启用字典").append(dictData.getDictLabel()).append("成功").toString());	
    }	
	
    @RequiresPermissions(value={"sys:dictData:view"})	
    @RequestMapping(value={"form"})	
    public String form(DictData dictData, Model model) {	
        dictData = this.createNextNode(dictData);	
        model.addAttribute("dictData", dictData);	
        return "modules/sys/dictDataForm";	
    }	
	
    @RequiresPermissions(value={"sys:dictData:edit"})	
    @RequestMapping(value={"delete"})	
    @ResponseBody	
    public String delete(DictData dictData, HttpServletRequest request) {	
        DictData a = (DictData)super.getWebDataBinderSource(request);	
        if (a != null && "1".equals(a.getIsSys()) && !dictData.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        this.dictDataService.delete(dictData);	
        return this.renderResult("true", "删除字典成功！");	
    }	
	
    @RequiresPermissions(value={"sys:dictData:edit"})	
    @RequestMapping(value={"fixTreeData"})	
    @ResponseBody	
    public String fixTreeData() {	
        if (!UserUtils.getUser().isAdmin()) {	
            return this.renderResult("false", "操作失败，只有管理员才能进行修复！");	
        }	
        this.dictDataService.fixTreeData();	
        return this.renderResult("true", "数据修复成功");	
    }	
	
    @RequiresPermissions(value={"sys:dictData:view"})	
    @RequestMapping(value={"list"})	
    public String list(DictData dictData, Model model) {	
        model.addAttribute("dictData", dictData);	
        return "modules/sys/dictDataList";	
    }	
	
    @RequiresPermissions(value={"user"})	
    @RequestMapping(value={"treeData"})	
    @ResponseBody	
    public List<Map<String, Object>> treeData(String dictType, String excludeCode, String isShowCode, boolean isShowNameOrig) {	
        int a;	
        ArrayList<Map<String, Object>> a2 = ListUtils.newArrayList();	
        List<DictData> a3 = DictUtils.getDictList(dictType);	
        int n = a = 0;	
        while (n < a3.size()) {	
            DictData a4 = a3.get(a);	
            if ("0".equals(a4.getStatus()) && (!StringUtils.isNotBlank(excludeCode) || !a4.getId().equals(excludeCode) && !a4.getParentCodes().contains(new StringBuilder().insert(0, ",").append(excludeCode).append(",").toString()))) {	
                void a5;	
                HashMap<String, String> hashMap = MapUtils.newHashMap();	
                void v1 = a5;	
                v1.put("pId", a4.getParentCode());	
                hashMap.put("id", a4.getId());	
                DictData dictData = a4;	
                a2.add((Map<String, Object>)a5);	
                a5.put("value", a4.getDictValue());	
                v1.put("name", StringUtils.getTreeNodeName(isShowCode, a4.getDictValue(), isShowNameOrig ? dictData.getDictLabelOrig() : dictData.getDictLabel()));	
            }	
            n = ++a;	
        }	
        return a2;	
    }	
	
    @RequiresPermissions(value={"sys:dictData:edit"})	
    @RequestMapping(value={"disable"})	
    @ResponseBody	
    public String disable(DictData dictData, HttpServletRequest request) {	
        void a;	
        DictData a2 = (DictData)super.getWebDataBinderSource(request);	
        if (a2 != null && "1".equals(a2.getIsSys()) && !dictData.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        DictData dictData2 = new DictData();	
        dictData2.setStatus("0");	
        a.setParentCodes("," + dictData.getId() + ",");	
        if (this.dictDataService.findCount(a) > 0L) {	
            return this.renderResult("false", "该字典包含未停用的子字典！");	
        }	
        dictData.setStatus("2");	
        DictDataController dictDataController = this;	
        dictDataController.dictDataService.updateStatus(dictData);	
        return dictDataController.renderResult("true", new StringBuilder().insert(0, "停用字典").append(dictData.getDictLabel()).append("成功").toString());	
    }	
	
    @RequiresPermissions(value={"sys:dictData:edit"})	
    @PostMapping(value={"save"})	
    @ResponseBody	
    public String save(@Validated DictData dictData, HttpServletRequest request) {	
        DictType a;	
        DictData a2 = (DictData)super.getWebDataBinderSource(request);	
        if (a2 != null && "1".equals(a2.getIsSys()) && !dictData.getCurrentUser().isSuperAdmin()) {	
            return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
        }	
        if (!dictData.getCurrentUser().isSuperAdmin() && "1".equals(dictData.getIsSys())) {	
            return this.renderResult("false", "保存失败，只有系统管理员才能保存为系统字典！");	
        }	
        DictType dictType = new DictType();	
        a.setDictType(dictData.getDictType());	
        a = this.dictTypeService.get(a);	
        if (a == null) {	
            return this.renderResult("false", new StringBuilder().insert(0, "保存失败，没有找到“").append(dictData.getDictType()).append("”字典类型！").toString());	
        }	
        if ("1".equals(a.getIsSys()) && !"1".equals(dictData.getIsSys())) {	
            return this.renderResult("false", "保存失败，字典类型是系统的，字典数据也必须是系统字典！");	
        }	
        if (StringUtils.isBlank(dictData.getIsSys())) {	
            dictData.setIsSys(a.getIsSys());	
        }	
        this.dictDataService.save(dictData);	
        return this.renderResult("true", "保存字典成功");	
    }	
	
    /*	
     * Unable to fully structure code	
     * Enabled aggressive block sorting	
     * Lifted jumps to return sites	
     */	
    @RequiresPermissions(value={"sys:dictData:edit"})	
    @RequestMapping(value={"createNextNode"})	
    @ResponseBody	
    public DictData createNextNode(DictData dictData) {	
        if (StringUtils.isNotBlank(dictData.getParentCode())) {	
            v0 = dictData;	
            v0.setParent((DictData)this.dictDataService.get(v0.getParentCode()));	
        }	
        if (!dictData.getIsNewRecord()) ** GOTO lbl20	
        var2_2 = new DictData();	
        v1 = a;	
        v1.setDictType(dictData.getDictType());	
        v1.setParentCode(dictData.getParentCode());	
        a = (DictData)this.dictDataService.getLastByParentCode(a);	
        if (a != null) {	
            v2 = dictData;	
            v3 = v2;	
            v2.setTreeSort(a.getTreeSort() + 30);	
            v2.setDictValue(IdGen.nextCode(a.getDictValue()));	
        } else {	
            if (dictData.getParent() != null) {	
                v4 = dictData;	
                v5 = dictData;	
                v5.setDictValue(v5.getParent().getDictValue() + "001");	
            }	
lbl20: // 4 sources:	
            v3 = dictData;	
        }	
        if (v3.getTreeSort() != null) return dictData;	
        dictData.setTreeSort(30);	
        return dictData;	
    }	
	
    @ModelAttribute	
    public DictData get(String dictCode, boolean isNewRecord) {	
        return (DictData)this.dictDataService.get(dictCode, isNewRecord);	
    }	
	
    @RequiresPermissions(value={"sys:dictData:view"})	
    @RequestMapping(value={"listData"})	
    @ResponseBody	
    public List<DictData> listData(DictData dictData) {	
        if (StringUtils.isBlank(dictData.getParentCode())) {	
            dictData.setParentCode("0");	
        }	
        return this.dictDataService.findList(dictData);	
    }	
}	
	
