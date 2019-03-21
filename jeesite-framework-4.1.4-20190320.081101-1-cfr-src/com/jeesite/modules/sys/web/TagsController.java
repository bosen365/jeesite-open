/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.modules.sys.web;	
	
import com.jeesite.common.web.BaseController;	
import com.jeesite.common.web.http.ServletUtils;	
import java.util.Map;	
import javax.servlet.http.HttpServletRequest;	
import org.hyperic.jni.ArchNotSupportedException;	
import org.hyperic.sigar.Who;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.RequestMapping;	
	
@Controller	
@RequestMapping(value={"tags"})	
@ConditionalOnProperty(name={"web.core.enabled"}, havingValue="true", matchIfMissing=true)	
public class TagsController	
extends BaseController {	
    @RequestMapping(value={"iconselect"})	
    public String iconselect(HttpServletRequest request, Model model) {	
        model.addAllAttributes(ServletUtils.getParameters(request));	
        return "tagsview/form/iconselect";	
    }	
	
    @RequestMapping(value={"imageclip"})	
    public String imageclip(HttpServletRequest request, Model model) {	
        model.addAllAttributes(ServletUtils.getParameters(request));	
        return "tagsview/form/imageclip";	
    }	
	
    @RequestMapping(value={"treeselect"})	
    public String treeselect(HttpServletRequest request, Model model) {	
        model.addAllAttributes(ServletUtils.getParameters(request));	
        return "tagsview/form/treeselect";	
    }	
}	
	
