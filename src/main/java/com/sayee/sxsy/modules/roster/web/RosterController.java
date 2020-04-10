/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.roster.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.web.BaseController;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.roster.entity.Roster;
import com.sayee.sxsy.modules.roster.service.RosterService;

/**
 * 花名册Controller
 * @author yang
 * @version 2020-03-30
 */
@Controller
@RequestMapping(value = "${adminPath}/roster/roster")
public class RosterController extends BaseController {

	@Autowired
	private RosterService rosterService;
	
	@ModelAttribute
	public Roster get(@RequestParam(required=false) String id) {
		Roster entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rosterService.get(id);
		}
		if (entity == null){
			entity = new Roster();
		}
		return entity;
	}
	
	@RequiresPermissions("roster:roster:view")
	@RequestMapping(value = {"list", ""})
	public String list(Roster roster, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Roster> page = rosterService.findPage(new Page<Roster>(request, response), roster); 
		model.addAttribute("page", page);
		return "modules/roster/rosterList";
	}

	@RequiresPermissions("roster:roster:view")
	@RequestMapping(value = "form")
	public String form(Roster roster, Model model) {
		model.addAttribute("roster", roster);
		return "modules/roster/rosterForm";
	}

	@RequiresPermissions("roster:roster:edit")
	@RequestMapping(value = "save")
	public String save(Roster roster, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, roster)){
			return form(roster, model);
		}
		rosterService.save(roster);
		addMessage(redirectAttributes, "保存花名册成功");
		return "redirect:"+Global.getAdminPath()+"/roster/roster/?repage";
	}
	
	@RequiresPermissions("roster:roster:edit")
	@RequestMapping(value = "delete")
	public String delete(Roster roster, RedirectAttributes redirectAttributes) {
		rosterService.delete(roster);
		addMessage(redirectAttributes, "删除花名册成功");
		return "redirect:"+Global.getAdminPath()+"/roster/roster/?repage";
	}

}