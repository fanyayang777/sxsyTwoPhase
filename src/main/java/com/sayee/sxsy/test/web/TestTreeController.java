/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.test.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.modules.sys.entity.Office;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.web.BaseController;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.test.entity.TestTree;
import com.sayee.sxsy.test.service.TestTreeService;

/**
 * 树结构生成Controller
 * @author ThinkGem
 * @version 2015-04-06
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testTree")
public class TestTreeController extends BaseController {

	@Autowired
	private TestTreeService testTreeService;
	
	@ModelAttribute
	public TestTree get(@RequestParam(required=false) String id) {
		TestTree entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testTreeService.get(id);
		}
		if (entity == null){
			entity = new TestTree();
		}
		return entity;
	}
	
	@RequiresPermissions("test:testTree:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestTree testTree, HttpServletRequest request, HttpServletResponse response, Model model) {
		String mold = request.getParameter("mold");
		List<TestTree> list = testTreeService.findList(testTree); 
		model.addAttribute("list", list);
		return "jeesite/test/testTreeList";
	}

	@RequiresPermissions("test:testTree:view")
	@RequestMapping(value = "form")
	public String form(TestTree testTree, Model model,HttpServletRequest request) {
		//如果 是父级进行修改 查看有没有子类，如果有 不让修改
		if(testTree.getParent()!=null && "0".equals(testTree.getParent().getId())){
			TestTree testTreeCs = new TestTree();
			testTreeCs.setParent(testTree);
			List<TestTree> list = testTreeService.findList(testTreeCs);
			if (list.size() > 0){
				model.addAttribute("isChild", "true");
			}
		}

		if (testTree.getParent()!=null && StringUtils.isNotBlank(testTree.getParent().getId())){
			testTree.setParent(testTreeService.get(testTree.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(testTree.getId())){
				TestTree testTreeChild = new TestTree();
				testTreeChild.setParent(new TestTree(testTree.getParent().getId()));
				List<TestTree> list = testTreeService.findList(testTree);
				if (list.size() > 0){
					testTree.setSort(list.get(list.size()-1).getSort());
					if (testTree.getSort() != null){
						testTree.setSort(testTree.getSort() + 30);
					}
				}
			}
		}
		String mold=request.getParameter("mold");
		if(StringUtils.isBlank(mold)){
			TestTree testTree1 = testTreeService.get(testTree.getId());
			testTree.setMold(testTree1.getMold());
		}else{
			testTree.setMold(mold);
			model.addAttribute("mold",testTree.getMold());
		}
		if(StringUtils.isNotBlank(testTree.getId())){
			TestTree testTree1 = testTreeService.get(testTree.getId());
			model.addAttribute("molds",testTree1.getMold());
		}
		if (testTree.getSort() == null){
			testTree.setSort(30);
		}
		model.addAttribute("testTree", testTree);
		return "jeesite/test/testTreeForm";
	}

	@RequiresPermissions("test:testTree:edit")
	@RequestMapping(value = "save")
	public String save(TestTree testTree, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request,HttpServletResponse response) {
		if (!beanValidator(model, testTree)){
			return form(testTree, model,request);
		}
		testTreeService.save(testTree);
		addMessage(redirectAttributes, "保存调解类别成功");
		testTree.getParent().setId("");
		testTree.setParentIds("");
		testTree.setName("");
		String list = list(testTree, request, response, model);
		return list;
	}
	
	@RequiresPermissions("test:testTree:edit")
	@RequestMapping(value = "delete")
	public String delete(TestTree testTree, RedirectAttributes redirectAttributes,Model model, HttpServletRequest request,HttpServletResponse response) {
		testTreeService.delete(testTree);
		addMessage(redirectAttributes, "删除调解类别成功");
		testTree.getParent().setId("");
		testTree.setParentIds("");
		testTree.setName("");
		String list = list(testTree, request, response, model);
		return list;
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response,@RequestParam(required=false) String mold,@RequestParam(required=false) String parent) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<TestTree> list = testTreeService.findList(new TestTree());
		for (int i=0; i<list.size(); i++){
			TestTree e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				if(mold==null){
					mapList.add(this.treeMap(e));
				}else if(mold != null && mold.equals(e.getMold())){
					if (StringUtils.isNotBlank(parent) ){
						if (parent.equals(e.getParent().getId())){
							mapList.add(this.treeMap(e));
						}
					}else {
						mapList.add(this.treeMap(e));
					}
				}
			}
		}
		return mapList;
	}

	public Map<String, Object> treeMap(TestTree e ){
		Map<String, Object> map = Maps.newHashMap();
		map.put("id", e.getId());
		map.put("pId", e.getParentId());
		map.put("name", e.getName());
		return map;
	}

}