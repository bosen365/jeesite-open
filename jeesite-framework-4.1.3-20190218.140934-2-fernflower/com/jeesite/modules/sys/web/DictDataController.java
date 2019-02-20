package com.jeesite.modules.sys.web;	
	
import com.jeesite.autoconfigure.sys.MsgAutoConfiguration;	
import com.jeesite.common.collect.ListUtils;	
import com.jeesite.common.collect.MapUtils;	
import com.jeesite.common.idgen.IdGen;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.web.BaseController;	
import com.jeesite.modules.sys.entity.DictData;	
import com.jeesite.modules.sys.entity.DictType;	
import com.jeesite.modules.sys.service.DictDataService;	
import com.jeesite.modules.sys.service.DictTypeService;	
import com.jeesite.modules.sys.utils.DictUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import java.util.HashMap;	
import java.util.List;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.shiro.authz.annotation.RequiresPermissions;	
import org.hyperic.sigar.ProcTime;	
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
@RequestMapping({"${adminPath}/sys/dictData"})	
@ConditionalOnProperty(	
   name = {"web.core.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class DictDataController extends BaseController {	
   @Autowired	
   private DictDataService dictDataService;	
   @Autowired	
   private DictTypeService dictTypeService;	
	
   @RequiresPermissions({"sys:dictData:view"})	
   @RequestMapping({"listData"})	
   @ResponseBody	
   public List listData(DictData dictData) {	
      if (StringUtils.isBlank(dictData.getParentCode())) {	
         dictData.setParentCode("0");	
      }	
	
      return this.dictDataService.findList(dictData);	
   }	
	
   @RequiresPermissions({"user"})	
   @RequestMapping({"treeData"})	
   @ResponseBody	
   public List treeData(String dictType, String excludeCode, String isShowCode, boolean isShowNameOrig) {	
      List a = ListUtils.newArrayList();	
      List a = DictUtils.getDictList(dictType);	
	
      int a;	
      for(int var10000 = a = 0; var10000 < a.size(); var10000 = a) {	
         DictData a = (DictData)a.get(a);	
         if ("0".equals(a.getStatus()) && (!StringUtils.isNotBlank(excludeCode) || !a.getId().equals(excludeCode) && !a.getParentCodes().contains((new StringBuilder()).insert(0, ",").append(excludeCode).append(",").toString()))) {	
            HashMap a = MapUtils.newHashMap();	
            a.put("id", a.getId());	
            a.put("pI", a.getParentCode());	
            a.put("name", StringUtils.getTreeNodeName(isShowCode, a.getDictValue(), isShowNameOrig ? a.getDictLabelOrig() : a.getDictLabel()));	
            a.put("value", a.getDictValue());	
            a.add(a);	
         }	
	
         ++a;	
      }	
	
      return a;	
   }	
	
   @ModelAttribute	
   public DictData get(String dictCode, boolean isNewRecord) {	
      return (DictData)this.dictDataService.get(dictCode, isNewRecord);	
   }	
	
   @RequiresPermissions({"sys:dictData:edit"})	
   @RequestMapping({"fixTreeData"})	
   @ResponseBody	
   public String fixTreeData() {	
      if (!UserUtils.getUser().isAdmin()) {	
         return this.renderResult("false", "操作失败，只有管理员才能进行修复！");	
      } else {	
         this.dictDataService.fixTreeData();	
         return this.renderResult("true", "数据修复成功");	
      }	
   }	
	
   @RequiresPermissions({"sys:dictData:edit"})	
   @RequestMapping({"enable"})	
   @ResponseBody	
   public String enable(DictData dictData, HttpServletRequest request) {	
      DictData a;	
      if ((a = (DictData)super.getWebDataBinderSource(request)) != null && "1".equals(a.getIsSys()) && !dictData.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
      } else {	
         dictData.setStatus("0");	
         this.dictDataService.updateStatus(dictData);	
         return this.renderResult("true", (new StringBuilder()).insert(0, "启用字典").append(dictData.getDictLabel()).append("成功").toString());	
      }	
   }	
	
   @RequiresPermissions({"sys:dictData:view"})	
   @RequestMapping({"list"})	
   public String list(DictData dictData, Model model) {	
      model.addAttribute("dictData", dictData);	
      return "modules/sys/dictDataList";	
   }	
	
   @RequiresPermissions({"sys:dictData:view"})	
   @RequestMapping({"form"})	
   public String form(DictData dictData, Model model) {	
      dictData = this.createNextNode(dictData);	
      model.addAttribute("dictData", dictData);	
      return "modules/sys/dictDataForm";	
   }	
	
   @RequiresPermissions({"sys:dictData:edit"})	
   @RequestMapping({"disable"})	
   @ResponseBody	
   public String disable(DictData dictData, HttpServletRequest request) {	
      DictData a;	
      if ((a = (DictData)super.getWebDataBinderSource(request)) != null && "1".equals(a.getIsSys()) && !dictData.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
      } else {	
         DictData a = new DictData();	
         a.setStatus("0");	
         a.setParentCodes("," + dictData.getId() + ",");	
         if (this.dictDataService.findCount(a) > 0L) {	
            return this.renderResult("false", "该字典包含未停用的子字典！");	
         } else {	
            dictData.setStatus("2");	
            this.dictDataService.updateStatus(dictData);	
            return this.renderResult("true", (new StringBuilder()).insert(0, "停用字典").append(dictData.getDictLabel()).append("成功").toString());	
         }	
      }	
   }	
	
   @RequiresPermissions({"sys:dictData:edit"})	
   @PostMapping({"save"})	
   @ResponseBody	
   public String save(@Validated DictData dictData, HttpServletRequest request) {	
      DictData a;	
      if ((a = (DictData)super.getWebDataBinderSource(request)) != null && "1".equals(a.getIsSys()) && !dictData.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
      } else if (!dictData.getCurrentUser().isSuperAdmin() && "1".equals(dictData.getIsSys())) {	
         return this.renderResult("false", "保存失败，只有系统管理员才能保存为系统字典！");	
      } else {	
         DictType a = new DictType();	
         a.setDictType(dictData.getDictType());	
         if ((a = this.dictTypeService.get(a)) == null) {	
            return this.renderResult("false", (new StringBuilder()).insert(0, "保存失败，没有找到“").append(dictData.getDictType()).append("”字典类型！").toString());	
         } else if ("1".equals(a.getIsSys()) && !"1".equals(dictData.getIsSys())) {	
            return this.renderResult("false", "保存失败，字典类型是系统的，字典数据也必须是系统字典！");	
         } else {	
            if (StringUtils.isBlank(dictData.getIsSys())) {	
               dictData.setIsSys(a.getIsSys());	
            }	
	
            this.dictDataService.save(dictData);	
            return this.renderResult("true", "保存字典成功");	
         }	
      }	
   }	
	
   @RequiresPermissions({"sys:dictData:edit"})	
   @RequestMapping({"delete"})	
   @ResponseBody	
   public String delete(DictData dictData, HttpServletRequest request) {	
      DictData a;	
      if ((a = (DictData)super.getWebDataBinderSource(request)) != null && "1".equals(a.getIsSys()) && !dictData.getCurrentUser().isSuperAdmin()) {	
         return this.renderResult("false", "越权操作，只有超级管理员才能修改系统数据！");	
      } else {	
         this.dictDataService.delete(dictData);	
         return this.renderResult("true", "删除字典成功！");	
      }	
   }	
	
   @RequiresPermissions({"sys:dictData:edit"})	
   @RequestMapping({"createNextNode"})	
   @ResponseBody	
   public DictData createNextNode(DictData dictData) {	
      if (StringUtils.isNotBlank(dictData.getParentCode())) {	
         dictData.setParent((DictData)this.dictDataService.get((String)dictData.getParentCode()));	
      }	
	
      DictData var10000;	
      label24: {	
         if (dictData.getIsNewRecord()) {	
            DictData a = new DictData();	
            a.setDictType(dictData.getDictType());	
            a.setParentCode(dictData.getParentCode());	
            DictData a;	
            if ((a = (DictData)this.dictDataService.getLastByParentCode(a)) != null) {	
               var10000 = dictData;	
               dictData.setTreeSort(a.getTreeSort() + 30);	
               dictData.setDictValue(IdGen.nextCode(a.getDictValue()));	
               break label24;	
            }	
	
            if (dictData.getParent() != null) {	
               dictData.setDictValue(dictData.getParent().getDictValue() + "001");	
            }	
         }	
	
         var10000 = dictData;	
      }	
	
      if (var10000.getTreeSort() == null) {	
         dictData.setTreeSort(30);	
      }	
	
      return dictData;	
   }	
}	
