/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintmain.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.sayee.sxsy.common.utils.*;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.oa.entity.OaNotify;
import com.sayee.sxsy.modules.oa.service.OaNotifyService;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import com.sayee.sxsy.test.dao.TestTreeDao;
import com.sayee.sxsy.test.entity.TestTree;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Method;
import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.web.BaseController;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;

import java.text.NumberFormat;
import java.util.*;

/**
 * 纠纷调解Controller
 * @author lyt
 * @version 2019-06-04
 */
@Controller
@RequestMapping(value = "${adminPath}/complaintmain/complaintMain")
public class ComplaintMainController extends BaseController {
    @Autowired
    private OaNotifyService oaNotifyService;
	@Autowired
	private TestTreeDao testTreeDao;

    @Autowired
    private ComplaintMainDao complaintMainDao;

	@Autowired
	private ComplaintMainService complaintMainService;

	@ModelAttribute
	public ComplaintMain get(@RequestParam(required=false) String id) {
		ComplaintMain entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = complaintMainService.get(id);
		}
		if (entity == null){
			entity = new ComplaintMain();
		}
		return entity;
	}
	
	@RequiresPermissions("complaintmain:complaintMain:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComplaintMain complaintMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComplaintMain> page = complaintMainService.findPage(new Page<ComplaintMain>(request, response), complaintMain); 
		model.addAttribute("page", page);
		return "modules/complaintmain/complaintMainList";
	}

	@RequiresPermissions("complaintmain:complaintMain:view")
	@RequestMapping(value = "form")
	public String form(ComplaintMain complaintMain, Model model) {
		model.addAttribute("complaintMain", complaintMain);
		return "modules/complaintmain/complaintMainForm";
	}

	@RequiresPermissions("complaintmain:complaintMain:edit")
	@RequestMapping(value = "save")
	public String save(ComplaintMain complaintMain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, complaintMain)){
			return form(complaintMain, model);
		}
		complaintMainService.save(complaintMain);
		addMessage(redirectAttributes, "保存纠纷调解成功");
		return "redirect:"+Global.getAdminPath()+"/complaintmain/complaintMain/?repage";
	}
	
	@RequiresPermissions("complaintmain:complaintMain:edit")
	@RequestMapping(value = "delete")
	public String delete(ComplaintMain complaintMain, RedirectAttributes redirectAttributes) {
		complaintMainService.delete(complaintMain);
		addMessage(redirectAttributes, "删除纠纷调解成功");
		return "redirect:"+Global.getAdminPath()+"/complaintmain/complaintMain/?repage";
	}

	/**
	 * 根据身份证 和 医院  判断是否重复
	 */
	@RequestMapping(value = "getRepeat")
	public void getRepeat(HttpServletRequest request,HttpServletResponse response) {
		String card=request.getParameter("card");//前台传过来的状态
		String hospital=request.getParameter("hospital");//前台传过来的状态
		String complaintMainId=request.getParameter("complaintMainId");//前台传过来的状态
		List<ComplaintMain> list = complaintMainService.getRepeat(card,hospital,complaintMainId);
		list.removeAll(Collections.singleton(null));
		Map<String,Object> map=new HashMap<String,Object>();
		if (!CollectionUtils.isEmpty(list) && list!=null){
			StringBuffer number=new StringBuffer();
			for (ComplaintMain c:list) {
				number.append(c.getCaseNumber()+"，");
			}
			map.put("size",list.size());
			map.put("number",number);
			map.put("name",UserUtils.getOfficeId(hospital).getName());
			AjaxHelper.responseWrite(request,response,"1","success",map);
		}else {
			AjaxHelper.responseWrite(request,response,"0","error",map);
		}

	}
	/*
	* 后台首页
	*
	* */
	@RequestMapping(value = "index")
	public String index(OaNotify oaNotify,ComplaintMain complaintMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		//案件类表 分3类人 调解员 医院的 其余人
		User user=UserUtils.getUser();
		List<ComplaintMain> list=complaintMainDao.selfList(user.getLoginName());
		complaintMainService.format(list);
		model.addAttribute("listSize", list.size());
		List<ComplaintMain> ywcList=complaintMainService.getMyDone(user.getLoginName());
		int ywc=0;
		for (ComplaintMain complaintMain1 : ywcList) {
			if ("assess".equals(complaintMain1.getTaskDefKey()) || "feedback".equals(complaintMain1.getTaskDefKey())){
				ywc++;
			}
		}
		model.addAttribute("sum", list.size()+ywc);
		model.addAttribute("list", list.size()>10 ? list.subList(0,10) : list);
		List<String> aa=ObjectUtils.convert(UserUtils.getRoleList().toArray(),"enname",true);
		if (  (  aa.contains("quanshengtiaojiebuzhuren") ||aa.contains("yitiaoweizhuren") || aa.contains("yitiaoweifuzhuren")|| aa.contains("yiyuantousubanrenyuan")|| aa.contains("yiyuanxingzhengrenyuan")|| aa.contains("gongzuozhanzhuren/fuzhuren")|| aa.contains("shengzhitiaojiebuzhuren/fuzhuren")|| aa.contains("jinzhuyiyuantiaojieyuan")
				|| aa.contains("jinzhuxingzhengbumenzhuren") || aa.contains("xingzhengbumenrenyuan") ) && list.isEmpty() && "1".equals(user.getCompany().getOfficeType())){//医调委的进
			int year=Integer.valueOf(DateUtils.getYear())+1;
			List newList=complaintMainDao.getMachine(DateUtils.getYear(),String.valueOf(year),user.getCompany().getArea().getId(),"");
			complaintMainService.format(newList);
			model.addAttribute("list", newList.size()>10 ? newList.subList(0,10) : newList);
		}else if("2".equals(user.getCompany().getOfficeType())){ //医院帐号进
			List newList=complaintMainDao.getMachine("","","",user.getCompany().getId());
			complaintMainService.format(newList);
			model.addAttribute("list", newList.size()>10 ? newList.subList(0,10) : newList);
		}else {

		}
		//通知
		oaNotify.setSelf(true);
		long notifyCount=oaNotifyService.findCount(oaNotify);
		oaNotify.setReadFlag("0");
		model.addAttribute("notifyPageSize", oaNotifyService.findCount(oaNotify));
		Page<OaNotify> notifyPage = oaNotifyService.find(new Page<OaNotify>(request, response), oaNotify);
		if (notifyPage.getList().isEmpty()){
			oaNotify=new OaNotify();
			notifyPage = oaNotifyService.find(new Page<OaNotify>(request, response), oaNotify);
		}
		notifyPage.setList(notifyPage.getList().size()>10 ? notifyPage.getList().subList(0,10) : notifyPage.getList());
		model.addAttribute("notifyPage", notifyPage);
		model.addAttribute("notifyCount", notifyCount);
		return "modules/home/index";
//		complaintMain.setUser(UserUtils.getUser());
//        Page<ComplaintMain> page = complaintMainService.selfList(new Page<ComplaintMain>(request, response), complaintMain);
//        page.setList(page.getList().size()>10 ? page.getList().subList(0,10) : page.getList());
//        model.addAttribute("page", page);
//        Page<OaNotify> notifyPage = oaNotifyService.find(new Page<OaNotify>(request, response), oaNotify);
//        notifyPage.setList(notifyPage.getList().size()>10 ? notifyPage.getList().subList(0,10) : notifyPage.getList());
//        model.addAttribute("notifyPage", notifyPage);
//		return "modules/home/index";
	}


	/**
	 * 我的待办列表
	 */
	@RequestMapping(value = "self")
	public String selfList(ComplaintMain complaintMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		//在 task 表中 根据 处理人信息 来查询 当前登录人有几条待办信息，不同的节点返回不同的页面
		complaintMain.setUser(UserUtils.getUser());
		Page<ComplaintMain> page = complaintMainService.selfList(new Page<ComplaintMain>(request, response), complaintMain);
		model.addAttribute("page", page);
//		List<Map<String,Object>> list=complaintMainService.selfList(UserUtils.getUser());
//		model.addAttribute("complaintMain", list);
		return "modules/complaintmain/complaintMainList";
	}
	/*
	 * 看工作站整合的案件
	 *
	 * */
	@RequestMapping(value = "workstation")
	public String workstation(ComplaintMain complaintMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		//在 task 表中 根据 处理人信息 来查询 当前登录人有几条待办信息，不同的节点返回不同的页面
		//complaintMain.setUser(UserUtils.getUser());
		Page<ComplaintMain> page = complaintMainService.workstation(new Page<ComplaintMain>(request, response), complaintMain);
		model.addAttribute("page", page);
		model.addAttribute("num","1");
//    List<Map<String,Object>> list=complaintMainService.selfList(UserUtils.getUser());
//    model.addAttribute("complaintMain", list);
		return "modules/complaintmain/complaintMainList";
	}


	//我的已办 列表
	@RequestMapping(value = "home")
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询当前登录人 有几条 数据时 在 结案总结 之后
		String loginName=UserUtils.getUser().getLoginName();
		List<ComplaintMain> list=complaintMainService.getMyDone(loginName);
		int ywc=0;
		for (ComplaintMain complaintMain : list) {
			if ("assess".equals(complaintMain.getTaskDefKey()) || "feedback".equals(complaintMain.getTaskDefKey())){
				ywc++;
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("ywc", ywc);
		model.addAttribute("wwc", complaintMainService.findCount(UserUtils.getUser()));
		return "modules/home/homePage";
	}

	//
	@RequestMapping(value = "aa")
	public String aa(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/home/aa";
	}

	/**
	 * 获取我的待办数目
	 */
	@RequestMapping(value = "self/count")
	@ResponseBody
	public String selfCount( Model model) {
		User user= UserUtils.getUser();
		return String.valueOf(complaintMainService.findCount(user));
	}

	//主任页面
	@RequestMapping(value = "head")
	public String head(HttpServletRequest request, HttpServletResponse response, Model model) {
		String type=request.getParameter("type");//根据类型判断是 投诉数据  还是 调解数据
		String commonType=request.getParameter("commonType");//根据类型判断是 焦点 专业 医院等级 数量

		String year=request.getParameter("yearDate");
		String beginMonthDate=request.getParameter("beginMonthDate");
		String endMonthDate=request.getParameter("endMonthDate");
		String newType=request.getParameter("newType");
        User user=UserUtils.getUser();
		model.addAttribute("yearDate", year );
        model.addAttribute("beginMonthDate", beginMonthDate );
        model.addAttribute("endMonthDate", endMonthDate );
		if (StringUtils.isNotBlank(newType)){
		    if ("duty".equals(newType)){//责任度
				List<Map<String,Object>> list=new ArrayList<>();
		    	if ((StringUtils.isBlank(year) || "2019".equals(year)) && "tj".equals(type)){
            	Map<String,Object> map=new HashMap<>();
                map.put("name","无责"); map.put("value","34");
                Map<String,Object> map1=new HashMap<>();
                map1.put("name","轻微责任");map1.put("value","82");
                Map<String,Object> map2=new HashMap<>();
                map2.put("name","次要责任");map2.put("value","89");
                Map<String,Object> map3=new HashMap<>();
                map3.put("name","对等责任");map3.put("value","101");
                Map<String,Object> map4=new HashMap<>();
                map4.put("name","主要责任");map4.put("value","86");
                Map<String,Object> map5=new HashMap<>();
                map5.put("name","全部责任");map5.put("value","33");
                Map<String,Object> map6=new HashMap<>();
                map6.put("name","无法判定");map6.put("value","7");
                list.add(map);list.add(map1);list.add(map2);list.add(map3);list.add(map4);list.add(map5);list.add(map6);
				year="2019";
		    	}else if("2018".equals(year)){
					Map<String,Object> map=new HashMap<>();
					map.put("name","无责"); map.put("value","54");
					Map<String,Object> map1=new HashMap<>();
					map1.put("name","轻微责任");map1.put("value","82");
					Map<String,Object> map2=new HashMap<>();
					map2.put("name","次要责任");map2.put("value","123");
					Map<String,Object> map3=new HashMap<>();
					map3.put("name","对等责任");map3.put("value","145");
					Map<String,Object> map4=new HashMap<>();
					map4.put("name","主要责任");map4.put("value","108");
					Map<String,Object> map5=new HashMap<>();
					map5.put("name","全部责任");map5.put("value","21");
					Map<String,Object> map6=new HashMap<>();
					map6.put("name","无法判定");map6.put("value","5");
					list.add(map);list.add(map1);list.add(map2);list.add(map3);list.add(map4);list.add(map5);list.add(map6);
				}
		    	else {
					 list=complaintMainService.findDuty(user,year,beginMonthDate,endMonthDate,type);
				}

				//循环 得到总数
				int sum=0;
				for (Map sumMap:list) {
					sum+=MapUtils.getInteger(sumMap,"value",0);
				}
				System.out.println("=========================="+sum);
				//根据总数 得到百分比
				for (Map ratioMap:list) {
					// 创建一个数值格式化对象
					NumberFormat numberFormat = NumberFormat.getInstance();
					// 设置精确到小数点后2位
					numberFormat.setMaximumFractionDigits(2);
					String result = numberFormat.format(MapUtils.getFloat(ratioMap,"value",(float) 0) / (float) sum * 100);
					ratioMap.put("value",result);
				}

                model.addAttribute("dutyName", this.convert(list.toArray(),"ratio",true) );
                String toJson = JsonUtil.toJson(list);
                List name = this.convert(list.toArray(), "name", true);
                List newName = new ArrayList();
                for (Object o : name) {
                    newName.add("'"+o+"'");
                }

                System.out.println(newName);
                System.out.println(list);
                model.addAttribute("name", newName);
                model.addAttribute("dutyNum",toJson);
                model.addAttribute("dutyTableInfo",list);
				model.addAttribute("yearDate", year );
                return "modules/home/duty";
            }else {
                Map<String,Object> map=new LinkedHashMap<>();

                List<Map<String, String>> cityMap=null;
                List<Map<String, String>> departmentMap=null;
                List<Map<String, String>> fiveYearMap=null;
				if ((StringUtils.isBlank(year) || "2019".equals(year)) && "tj".equals(type)){
					map.put("2万及以下","165");
					map.put("2万到10万及以下","88");
					map.put("10万到50万及以下","55");
					map.put("50万以上","5");
					model.addAttribute("yearDate", "2019" );
				}else if("2018".equals(year)){
					map.put("2万及以下","319");
					map.put("2万到10万及以下","201");
					map.put("10万到50万及以下","158");
					map.put("50万以上","4");
				}
				else {
					map=complaintMainService.findAmountRatio(user,year,beginMonthDate,endMonthDate,type);
                    cityMap=complaintMainService.findCityAmountRatio(user,year,beginMonthDate,endMonthDate,type);
                    departmentMap=complaintMainService.findDepartmentAmountRatio(user,year,beginMonthDate,endMonthDate,type);
                    fiveYearMap=complaintMainService.fiveYearAmountRatio(user,year,beginMonthDate,endMonthDate,type);
				}
                List keyList = new ArrayList();
                List valuesList = new ArrayList();
                List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				list.add(map);

				//循环 得到总数
                int sum=0;
                for(String key : map.keySet()){
					sum+=MapUtils.getInteger(map,key,0);
                }
                for(String key : map.keySet()) {
					// 创建一个数值格式化对象
					NumberFormat numberFormat = NumberFormat.getInstance();
					// 设置精确到小数点后2位
					numberFormat.setMaximumFractionDigits(2);
					String result = numberFormat.format(MapUtils.getFloat(map, key, (float) 0) / (float) sum * 100);
					map.put(key,result);
				}
				int index=0;
				for(String key : map.keySet()){
					List valueList = new ArrayList();
					keyList.add(key);
					String value =MapUtils.getString(map,key,"0");
					valueList.add("0");
					valueList.add(index);
					valueList.add(value);
					valuesList.add(valueList);
					index++;
				}
				String lists = JsonUtil.toJson(list);
				List name = null;
				List departmentName = null;
				List yearNewName = new ArrayList();
				List newName = new ArrayList();
                List newDepartmentName = new ArrayList();
                String newDepartmentMap = null;
                String newCityMap= null;
                try {
                    name = this.convert(cityMap.toArray(), "name", true);
                    for (Object o : name) {
                        newName.add("'"+o+"'");
                    }
                    newCityMap = JsonUtil.toJson(cityMap);

                    departmentName = this.convert(departmentMap.toArray(), "name", true);
                    for (Object o : departmentName) {
                        String s = o.toString();
                        boolean br = s.matches(".*[a-z]+.*");
                        if(br==true){
                            TestTree departmentNewName = testTreeDao.get(s.replaceAll("\'","").replaceAll("\"",""));
                            newDepartmentName.add(departmentNewName==null ? "" : "'"+departmentNewName.getName()+"'");
                        }else {
                            newDepartmentName.add("'" + o + "'");
                        }
                    }
                    newDepartmentMap = JsonUtil.toJson(departmentMap);
					//5年数据
					yearNewName = this.convert(fiveYearMap.toArray(), "name", true);

                }catch (Exception e){
                }
                //处理年度对比分析
				List l = new ArrayList();
				l.add(map);
				String regEx="[\n`~!@#$%^&*()+|{}':;'\\[\\]<>/?~！@#￥……&*（）——+|{}【】‘；：”“’。， 、？]";
				String a = "";
				String s = l.toString().replaceAll(regEx,a);

				List<Map<String,String>> newListMap = new ArrayList<Map<String,String>>();
				String[] split = s.split(",");
				for (String s1 : split) {
					Map<String,String> newMap = new LinkedHashMap<>();
					String[] split1 = s1.split("=");
					newMap.put("name",split1[0]);
					newMap.put("value",split1[1]);
					newListMap.add(newMap);
				}

				model.addAttribute("yearName",yearNewName);
				model.addAttribute("fiveYearInfo",JsonUtil.toJson(fiveYearMap));
				model.addAttribute("departmentMap",newDepartmentMap);
                model.addAttribute("departmentName",newDepartmentName);
                model.addAttribute("cityMap",newCityMap);
                model.addAttribute("name",newName);
                model.addAttribute("amountTableInfo", JsonUtil.toJson(newListMap));
				model.addAttribute("keyList", JsonUtil.toJson(keyList));
				model.addAttribute("valueList", valuesList);
                return "modules/home/amountRatio";
            }
        }else {
			model.addAttribute("type", type );
			if("jfjd".equals(commonType)){//调解数据统计下的  纠纷纠纷焦点 分析
				//查询当前登录人 有几条 数据时 在 结案总结 之后
				List<Map<String,Object>> list=complaintMainService.findTypeInfo(user,year,beginMonthDate,endMonthDate,type);
                list.sort(new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> o1, Map<String, Object> o2) {
						Integer i1 = MapUtils.getInteger(o1,"num",0);
						Integer i2 = MapUtils.getInteger(o2,"num",0);
						return i2.compareTo(i1);
					}
				});
				if ((StringUtils.isBlank(year) || "2019".equals(year)) && "tj".equals(type)){
					year="2019";
				}
				//循环 得到总数
				int sum=0;
				for (Map sumMap:list) {
					sum+=MapUtils.getInteger(sumMap,"num",0);
				}
				//根据总数 得到百分比
				for (Map ratioMap:list) {
					// 创建一个数值格式化对象
					NumberFormat numberFormat = NumberFormat.getInstance();
					// 设置精确到小数点后2位
					numberFormat.setMaximumFractionDigits(2);
					String result = numberFormat.format(MapUtils.getFloat(ratioMap,"num",(float) 0) / (float) sum * 100);
					ratioMap.put("num",result);
				}
				model.addAttribute("yearDate", year );
				model.addAttribute("list3",JsonUtil.toJson(list));
				model.addAttribute("list", this.convert(list.toArray(),"typeName",true) );
				model.addAttribute("jfjdTableInfo", list );
				model.addAttribute("list2", this.convert(list.toArray(),"num",true) );
				model.addAttribute("ids", this.convert(list.toArray(),"typeId",true) );
				return "modules/home/focus";
			}else if("sjzy".equals(commonType)){//涉及专业
				//各专业数据
				List<Map<String,Object>> dList=new ArrayList<>();
				if ((StringUtils.isBlank(year) || "2019".equals(year)) && "tj".equals(type)){
					Map<String,Object> map=new HashMap<>();
					map.put("name","'外科'"); map.put("department","454");
					Map<String,Object> map1=new HashMap<>();
					map1.put("name","'妇科'");map1.put("department","224");
					Map<String,Object> map2=new HashMap<>();
					map2.put("name","'内科'");map2.put("department","223");
					Map<String,Object> map3=new HashMap<>();
					map3.put("name","'儿科'");map3.put("department","75");
					Map<String,Object> map4=new HashMap<>();
					map4.put("name","'急诊科'");map4.put("department","49");
					Map<String,Object> map5=new HashMap<>();
					map5.put("name","'整形科'");map5.put("department","40");
					Map<String,Object> map6=new HashMap<>();
					map6.put("name","'眼科'");map6.put("department","35");
					Map<String,Object> map7=new HashMap<>();
					map7.put("name","'口腔科'");map7.put("department","27");
					Map<String,Object> map8=new HashMap<>();
					map8.put("name","'辅助检查科'");map8.put("department","24");
					Map<String,Object> map9=new HashMap<>();
					map9.put("name","'耳鼻喉科'");map9.put("department","13");
					Map<String,Object> map10=new HashMap<>();
					map10.put("name","'康复科'");map10.put("department","12");
					Map<String,Object> map12=new HashMap<>();
					map12.put("name","'中医科'");map12.put("department","11");
					Map<String,Object> map13=new HashMap<>();
					map13.put("name","'重症医学科'");map13.put("department","11");
					Map<String,Object> map14=new HashMap<>();
					map14.put("name","'皮肤科'");map14.put("department","5");
					Map<String,Object> map15=new HashMap<>();
					map15.put("name","'护理'");map15.put("department","4");
					Map<String,Object> map16=new HashMap<>();
					map16.put("name","'麻醉科'");map16.put("department","2");
					Map<String,Object> map17=new HashMap<>();
					map17.put("name","'其他'");map17.put("department","13");
					dList.add(map);
					dList.add(map1);
					dList.add(map2);
					dList.add(map3);
					dList.add(map4);
					dList.add(map5);
					dList.add(map6);
					dList.add(map7);
					dList.add(map8);
					dList.add(map9);
					dList.add(map10);
					dList.add(map12);
					dList.add(map13);
					dList.add(map14);
					dList.add(map15);
					dList.add(map16);
					dList.add(map17);
					model.addAttribute("yearDate", "2019" );
				}else if("2018".equals(year)){
					Map<String,Object> map=new HashMap<>();
					map.put("name","'外科'"); map.put("department","411");
					Map<String,Object> map1=new HashMap<>();
					map1.put("name","'妇科'");map1.put("department","259");
					Map<String,Object> map2=new HashMap<>();
					map2.put("name","'内科'");map2.put("department","192");
					Map<String,Object> map3=new HashMap<>();
					map3.put("name","'儿科'");map3.put("department","42");
					Map<String,Object> map4=new HashMap<>();
					map4.put("name","'急诊科'");map4.put("department","41");
					Map<String,Object> map5=new HashMap<>();
					map5.put("name","'整形科'");map5.put("department","7");
					Map<String,Object> map6=new HashMap<>();
					map6.put("name","'眼科'");map6.put("department","24");
					Map<String,Object> map7=new HashMap<>();
					map7.put("name","'口腔科'");map7.put("department","19");
					Map<String,Object> map9=new HashMap<>();
					map9.put("name","'耳鼻喉科'");map9.put("department","15");
					Map<String,Object> map10=new HashMap<>();
					map10.put("name","'康复科'");map10.put("department","11");
					Map<String,Object> map12=new HashMap<>();
					map12.put("name","'中医科'");map12.put("department","14");
					Map<String,Object> map14=new HashMap<>();
					map14.put("name","'皮肤科'");map14.put("department","7");
					Map<String,Object> map15=new HashMap<>();
					map15.put("name","'综检科'");map15.put("department","19");
					Map<String,Object> map16=new HashMap<>();
					map16.put("name","'麻醉科'");map16.put("department","6");
					Map<String,Object> map17=new HashMap<>();
					map17.put("name","'其他'");map17.put("department","9");
					dList.add(map);
					dList.add(map1);
					dList.add(map2);
					dList.add(map3);
					dList.add(map4);
					dList.add(map5);
					dList.add(map6);
					dList.add(map7);
					dList.add(map9);
					dList.add(map10);
					dList.add(map12);
					dList.add(map14);
					dList.add(map15);
					dList.add(map16);
					dList.add(map17);
				}
				else {
					dList=complaintMainService.findDepartment(user,year,beginMonthDate,endMonthDate,type);
				}
				//循环 得到总数
				int sum=0;
				for (Map sumMap:dList) {
					sum+=MapUtils.getInteger(sumMap,"department",0);
				}
				//根据总数 得到百分比
				for (Map ratioMap:dList) {
					// 创建一个数值格式化对象
					NumberFormat numberFormat = NumberFormat.getInstance();
					// 设置精确到小数点后2位
					numberFormat.setMaximumFractionDigits(2);
					String result = numberFormat.format(MapUtils.getFloat(ratioMap,"department",(float) 0) / (float) sum * 100);
					ratioMap.put("department",result);
				}
				List name = ObjectUtils.convert(dList.toArray(), "name", true);
				List newName = new ArrayList();
				for (Object o : name) {
					String s = o.toString();
					boolean br = s.matches(".*[a-z]+.*");
					if(br==true){
						TestTree departmentNewName = testTreeDao.get(s.replaceAll("\'","").replaceAll("\"",""));
						newName.add(departmentNewName==null ? "" : "'"+departmentNewName.getName()+"'");
					}else{
						newName.add(o);
					}
				}


				model.addAttribute("nameList", newName );
				model.addAttribute("departmentList", ObjectUtils.convert(dList.toArray(),"department",true) );
				model.addAttribute("sjzyTableInfo", dList );
				return "modules/home/major";
			}else if("yydj".equals(commonType)){//机构等级
				//各等级医院的案件数量统计
				List<Map<String, Object>> gradeList = new ArrayList<>();
				if ((StringUtils.isBlank(year) || "2019".equals(year)) && "tj".equals(type)){
					Map<String,Object> map=new HashMap<>();
					map.put("name","二级甲等"); map.put("value","337");
					Map<String,Object> map1=new HashMap<>();
					map1.put("name","三级甲等");map1.put("value","164");
					Map<String,Object> map2=new HashMap<>();
					map2.put("name","民营医疗机构");map2.put("value","112");
					Map<String,Object> map3=new HashMap<>();
					map3.put("name","三级乙等");map3.put("value","17");
					Map<String,Object> map4=new HashMap<>();
					map4.put("name","其他机构");map4.put("value","35");
					gradeList.add(map);
					gradeList.add(map1);
					gradeList.add(map2);
					gradeList.add(map3);
					gradeList.add(map4);
					model.addAttribute("yearDate", "2019" );
				}else if("2018".equals(year)){
					Map<String,Object> map=new HashMap<>();
					map.put("name","二级甲等"); map.put("value","337");
					Map<String,Object> map1=new HashMap<>();
					map1.put("name","三级甲等");map1.put("value","164");
					Map<String,Object> map2=new HashMap<>();
					map2.put("name","民营医疗机构");map2.put("value","112");
					Map<String,Object> map3=new HashMap<>();
					map3.put("name","三级乙等");map3.put("value","17");
					Map<String,Object> map4=new HashMap<>();
					map4.put("name","其他机构");map4.put("value","35");
					gradeList.add(map);
					gradeList.add(map1);
					gradeList.add(map2);
					gradeList.add(map3);
					gradeList.add(map4);
				}else {
					gradeList=complaintMainService.findGrade(user, year, beginMonthDate,endMonthDate,type);
				}

                gradeList.sort(new Comparator<Map<String, Object>>() {
                    @Override
                    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                        Integer i1 = MapUtils.getInteger(o1,"value",0);
                        Integer i2 = MapUtils.getInteger(o2,"value",0);
                        return i2.compareTo(i1);
                    }
                });

                //循环 得到总数
                int sum=0;
                for (Map sumMap:gradeList) {
                    sum+=MapUtils.getInteger(sumMap,"value",0);
                }
                //根据总数 得到百分比
                for (Map ratioMap:gradeList) {
                    // 创建一个数值格式化对象
                    NumberFormat numberFormat = NumberFormat.getInstance();
                    // 设置精确到小数点后2位
                    numberFormat.setMaximumFractionDigits(2);
                    String result = numberFormat.format(MapUtils.getFloat(ratioMap,"value",(float) 0) / (float) sum * 100);
                    ratioMap.put("value",result);
                }


				String toJson = JsonUtil.toJson(gradeList);
                List name = this.convert(gradeList.toArray(), "name", true);
                List newName = new ArrayList();
                for (Object o : name) {
                    newName.add("'"+o+"'");
                }
                model.addAttribute("name",newName);
                model.addAttribute("list",toJson);
                model.addAttribute("yydjTableInfo",gradeList);
				return "modules/home/grade";
			}else {//调解数量分析
				//案件数量统计
				String loginName=UserUtils.getUser().getLoginName();
				List<ComplaintMain> listNum=complaintMainService.findCompleted(user, year, beginMonthDate,endMonthDate);
				Long allEvent = complaintMainService.findAllEvent(user, year, beginMonthDate,endMonthDate);
				model.addAttribute("ywc", listNum.size());//
				model.addAttribute("wwc", allEvent);//
				model.addAttribute("listZ", listNum.size()+allEvent);//
				//每月数据统计
				List<Map<String,Object>> MonthDataList=complaintMainService.getEveryMonthData(user,year,beginMonthDate,endMonthDate,type);
//                Map<String,Object> map=new HashMap<>();
//                map.put("monthDate","'2019-04'"); map.put("num","5");
//                Map<String,Object> map1=new HashMap<>();
//                map1.put("monthDate","'2019-05'");map1.put("num","16");
//                Map<String,Object> map2=new HashMap<>();
//                map2.put("monthDate","'2019-06'");map2.put("num","34");
//                Map<String,Object> map3=new HashMap<>();
//                map3.put("monthDate","'2019-07'");map3.put("num","36");
//                Map<String,Object> map4=new HashMap<>();
//                map4.put("monthDate","'2019-08'");map4.put("num","42");
//                Map<String,Object> map5=new HashMap<>();
//                map5.put("monthDate","'2019-09'");map5.put("num","55");
//                Map<String,Object> map6=new HashMap<>();
//                map6.put("monthDate","'2019-10'");map6.put("num","10");
//                MonthDataList.add(map);MonthDataList.add(map1);MonthDataList.add(map2);MonthDataList.add(map3);
//                MonthDataList.add(map4);MonthDataList.add(map5);MonthDataList.add(map6);
				model.addAttribute("monthData", this.convert(MonthDataList.toArray(),"monthDate",true) );
				model.addAttribute("number", this.convert(MonthDataList.toArray(),"num",true) );
				model.addAttribute("monthTableInfo", MonthDataList );
				//各市数据
				List<Map<String,Object>> areaList=new ArrayList<>();
				if ((StringUtils.isBlank(year) || "2019".equals(year)) && "tj".equals(type)){
					Map<String,Object> m=new HashMap<>();
					m.put("name","临汾市"); m.put("value","148");
					Map<String,Object> m1=new HashMap<>();
					m1.put("name","阳泉市");m1.put("value","72");
					Map<String,Object> m2=new HashMap<>();
					m2.put("name","太原市");m2.put("value","237");
					Map<String,Object> m3=new HashMap<>();
					m3.put("name","晋中市");m3.put("value","204");
					Map<String,Object> m4=new HashMap<>();
					m4.put("name","运城市");m4.put("value","141");
					Map<String,Object> m5=new HashMap<>();
					m5.put("name","晋城市");m5.put("value","117");
					Map<String,Object> m6=new HashMap<>();
					m6.put("name","吕梁市");m6.put("value","157");
					Map<String,Object> m7=new HashMap<>();
					m7.put("name","长治市");m7.put("value","59");
					Map<String,Object> m8=new HashMap<>();
					m8.put("name","朔州市");m8.put("value","37");
					Map<String,Object> m9=new HashMap<>();
					m9.put("name","大同市");m9.put("value","30");
					Map<String,Object> m10=new HashMap<>();
					m10.put("name","忻州市");m10.put("value","20");
					areaList.add(m);areaList.add(m1);areaList.add(m2);areaList.add(m3);
					areaList.add(m4);areaList.add(m5);areaList.add(m6);areaList.add(m7);
					areaList.add(m9);areaList.add(m8);areaList.add(m10);

					model.addAttribute("yearDate", "2019" );
				}else if("2018".equals(year)){
					Map<String,Object> m=new HashMap<>();
					m.put("name","临汾市"); m.put("value","174");
					Map<String,Object> m1=new HashMap<>();
					m1.put("name","阳泉市");m1.put("value","79");
					Map<String,Object> m2=new HashMap<>();
					m2.put("name","太原市");m2.put("value","176");
					Map<String,Object> m3=new HashMap<>();
					m3.put("name","晋中市");m3.put("value","174");
					Map<String,Object> m4=new HashMap<>();
					m4.put("name","运城市");m4.put("value","122");
					Map<String,Object> m5=new HashMap<>();
					m5.put("name","晋城市");m5.put("value","108");
					Map<String,Object> m6=new HashMap<>();
					m6.put("name","吕梁市");m6.put("value","112");
					Map<String,Object> m7=new HashMap<>();
					m7.put("name","长治市");m7.put("value","78");
					Map<String,Object> m8=new HashMap<>();
					m8.put("name","朔州市");m8.put("value","43");
					Map<String,Object> m9=new HashMap<>();
					m9.put("name","大同市");m9.put("value","30");
					Map<String,Object> m10=new HashMap<>();
					m10.put("name","忻州市");m10.put("value","13");
					areaList.add(m);areaList.add(m1);areaList.add(m2);areaList.add(m3);
					areaList.add(m4);areaList.add(m5);areaList.add(m6);areaList.add(m7);
					areaList.add(m9);areaList.add(m8);areaList.add(m10);

				}else {
					areaList=complaintMainService.findAreaName(user,year,beginMonthDate,endMonthDate,type);
				}
				areaList.sort(new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> o1, Map<String, Object> o2) {
						Integer i1 = MapUtils.getInteger(o1,"value",0);
						Integer i2 = MapUtils.getInteger(o2,"value",0);
						return i2.compareTo(i1);
					}
				});

				//循环 得到总数
				int sum=0;
				for (Map sumMap:areaList) {
					sum+=MapUtils.getInteger(sumMap,"value",0);
				}
				//根据总数 得到百分比
				for (Map ratioMap:areaList) {
					// 创建一个数值格式化对象
					NumberFormat numberFormat = NumberFormat.getInstance();
					// 设置精确到小数点后2位
					 numberFormat.setMaximumFractionDigits(2);
					 String result = numberFormat.format(MapUtils.getFloat(ratioMap,"value",(float) 0) / (float) sum * 100);
					ratioMap.put("value",result);
				}
				List name = this.convert(areaList.toArray(), "name", true);
				List newName = new ArrayList();
				for (Object o : name) {
					newName.add("'"+o+"'");
				}

				System.out.println(areaList);
                System.out.println(newName);
                model.addAttribute("city",newName);
				model.addAttribute("areaList", JsonUtil.toJson(areaList));
				model.addAttribute("areaTableInfo", areaList);
                if ("ts".equals(type)){
                    return "modules/home/number";
                }else {
                    return "modules/home/numberMediate";
                }

			}
        }
	}

	public static List convert(Object[] list, String field, boolean skipNull)
	{
		Map map = new HashMap();
		List array = new ArrayList();
		Object[] arrayOfObject = list; int j = list.length; for (int i = 0; i < j; i++) { Object t = arrayOfObject[i];
		Object value = null;
		if (field != null) {
			Class c = t.getClass();
			if ((t instanceof Map)) {
				value = ((Map)t).get(field);
			} else if (c.isArray()) {
				value = ((Object[])t)[Integer.valueOf(field).intValue()];
			} else {
				String cls = c.getName();
				Method getMethod = (Method)map.get(cls);
				if (getMethod == null) getMethod = BeanUtils.getMethod(field, c);
				if (getMethod != null) {
					try {
						value = getMethod.invoke(t, new Object[0]);
					} catch (Exception e) {
						e.printStackTrace();
					}
					map.put(cls, getMethod);
				}
			}
		} else { value = t; }
		if (((value == null) && (!skipNull)) || (value != null)) array.add(value);
	}
		return array;
	}

}