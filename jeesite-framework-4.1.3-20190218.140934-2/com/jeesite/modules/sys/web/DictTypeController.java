package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.entity.Page;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.DictType;	
import com.jeesite.modules.sys.service.DictTypeService;	
import java.util.HashMap;	
import java.util.List;	
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
@RequestMapping({"${adminPath}/sys/dictType"})	
@ConditionalOnProperty(	
   name = {"web.core.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class DictTypeController extends BaseController {	
   @Autowired	
   private DictTypeService dictTypeService;	
	
   @RequiresPermissions({"sys:dictType:view"})	
   @RequestMapping({"listData"})	
   @ResponseBody	
   public Page listData(DictType dictType, HttpServletRequest request, HttpServletResponse response) {	
      dictType.setPage(new Page(request, response));	
      return this.dictTypeService.findPage(dictType);	
   }	
	
   @RequiresPermissions({"sys:dictType:edit"})	
   @RequestMapping({"checkDictType"})	
   @ResponseBody	
   public String checkDictType(String oldDictType, String dictType) {	
      DictType a;	
      (a = new DictType()).setDictType(dictType);	
      if (dictType != null && dictType.equals(oldDictType)) {	
         return "true";	
      } else {	
         return dictType != null && this.dictTypeService.findCount(a) == 0L ? "true" : "false";	
      }	
   }	
	
   @RequiresPermissions({"user"})	
   @RequestMapping({"treeData"})	
   @ResponseBody	
   public List treeData(String dictType, String excludeCode, @RequestParam(defaultValue = "1") String isShowCode) {	
      List a = ListUtils.newArrayList();	
      List a = this.dictTypeService.findList(new DictType());	
	
      int a;	
      for(int var10000 = a = 0; var10000 < a.size(); var10000 = a) {	
         DictType a = (DictType)a.get(a);	
         if ("0".equals(a.getStatus()) && (!StringUtils.isNotBlank(excludeCode) || !a.getId().equals(excludeCode))) {	
            HashMap a;	
            (a = MapUtils.newHashMap()).put("id", a.getId());	
            a.put("pId", "0");	
            a.put("name", StringUtils.getTreeNodeName(isShowCode, a.getDictType(), a.getDictName()));	
            a.put("value", a.getDictType());	
            a.add(a);	
         }	
	
         ++a;	
      }	
	
      return a;	
   }	
	
   @ModelAttribute	
   public DictType get(String id, boolean isNewRecord) {	
      return (DictType)this.dictTypeService.get(id, isNewRecord);	
   }	
	
   @RequiresPermissions({"sys:dictType:view"})	
   @RequestMapping({"form"})	
   public String form(DictType dictType, Model model) {	
      if (StringUtils.isBlank(dictType.getIsSys())) {	
         dictType.setIsSys("1");	
      }	
	
      model.addAttribute("dictType", dictType);	
      return "modules/sys/dictTypeForm";	
   }	
	
   @RequiresPermissions({"sys:dictType:edit"})	
   @PostMapping({"save"})	
   @ResponseBody	
   public String save(@Validated DictType dictType, HttpServletRequest request) {	
      DictType a;	
      if ((a = (DictType)super.getWebDataBinderSource(request)) != null && "1".equals(a.getIsSys()) && !dictType.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
      } else {	
         this.dictTypeService.save(dictType, a);	
         return this.renderResult("true", (new StringBuilder()).insert(0, "保存分类").append(dictType.getDictName()).append("成功！").toString());	
      }	
   }	
	
   @RequiresPermissions({"sys:dictType:view"})	
   @RequestMapping({"list"})	
   public String list(DictType dictType, Model model) {	
      return "modules/sys/dictTypeList";	
   }	
	
   @RequiresPermissions({"sys:dictType:edit"})	
   @RequestMapping({"delete"})	
   @ResponseBody	
   public String delete(DictType dictType, HttpServletRequest request) {	
      DictType a;	
      if ((a = (DictType)super.getWebDataBinderSource(request)) != null && "1".equals(a.getIsSys()) && !dictType.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
      } else {	
         this.dictTypeService.delete(dictType);	
         return this.renderResult("true", (new StringBuilder()).insert(0, "删除分类").append(dictType.getDictName()).append("成功！").toString());	
      }	
   }	
	
   @RequiresPermissions({"sys:dictType:edit"})	
   @RequestMapping({"enable"})	
   @ResponseBody	
   public String enable(DictType dictType) {	
      dictType.setStatus("0");	
      this.dictTypeService.updateStatus(dictType);	
      return this.renderResult("true", "启用字典类型成功");	
   }	
	
   @RequiresPermissions({"sys:dictType:edit"})	
   @RequestMapping({"disable"})	
   @ResponseBody	
   public String disable(DictType dictType) {	
      dictType.setStatus("2");	
      this.dictTypeService.updateStatus(dictType);	
      return this.renderResult("true", "停用字典类型成功");	
   }	
}	
