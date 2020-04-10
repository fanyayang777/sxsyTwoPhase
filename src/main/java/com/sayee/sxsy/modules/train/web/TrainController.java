/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.train.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.test.dao.TestTreeDao;
import com.sayee.sxsy.test.entity.TestTree;
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
import com.sayee.sxsy.modules.train.entity.Train;
import com.sayee.sxsy.modules.train.service.TrainService;

import java.util.ArrayList;
import java.util.List;

/**
 * 培训视频Controller
 * @author wjm
 * @version 2020-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/train/train")
public class TrainController extends BaseController {
	@Autowired
	private TestTreeDao testTreeDao;
	@Autowired
	private TrainService trainService;
	
	@ModelAttribute
	public Train get(@RequestParam(required=false) String id) {
		Train entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = trainService.get(id);
		}
		if (entity == null){
			entity = new Train();
		}
		return entity;
	}
	
	@RequiresPermissions("train:train:view")
	@RequestMapping(value = {"list", ""})
	public String list(Train train, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Train> page = trainService.findPage(new Page<Train>(request, response), train);
		List newName = new ArrayList();
		for (Train train1 : page.getList()) {
			boolean br = train1.getDepartment().matches(".*[a-z]+.*");
			if (br == true) {
				TestTree departmentNewName = testTreeDao.get(train1.getDepartment().replaceAll("\'", "").replaceAll("\"", ""));
				train1.setDepartment(departmentNewName == null ? "" : departmentNewName.getName());
			}
		}
			model.addAttribute("page", page);
			return "modules/train/trainList";
		}
	@RequiresPermissions("train:train:view")
	@RequestMapping(value = "form")
	public String form(Train train, Model model) {
		model.addAttribute("train", train);
		return "modules/train/trainForm";
	}

	@RequiresPermissions("train:train:edit")
	@RequestMapping(value = "save")
	public String save(Train train, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, train)){
			return form(train, model);
		}
		trainService.save(train);
		addMessage(redirectAttributes, "保存培训视频成功");
		return "redirect:"+Global.getAdminPath()+"/train/train/?repage";
	}
	
	@RequiresPermissions("train:train:edit")
	@RequestMapping(value = "delete")
	public String delete(Train train, RedirectAttributes redirectAttributes) {
		trainService.delete(train);
		addMessage(redirectAttributes, "删除培训视频成功");
		return "redirect:"+Global.getAdminPath()+"/train/train/?repage";
	}

}