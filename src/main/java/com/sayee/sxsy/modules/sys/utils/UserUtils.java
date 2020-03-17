/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;

import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.Menu;
import com.sayee.sxsy.modules.sys.entity.Role;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.security.SystemAuthorizingRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.sayee.sxsy.common.service.BaseService;
import com.sayee.sxsy.common.utils.CacheUtils;
import com.sayee.sxsy.common.utils.SpringContextHolder;
import com.sayee.sxsy.modules.sys.dao.AreaDao;
import com.sayee.sxsy.modules.sys.dao.MenuDao;
import com.sayee.sxsy.modules.sys.dao.OfficeDao;
import com.sayee.sxsy.modules.sys.dao.RoleDao;
import com.sayee.sxsy.modules.sys.dao.UserDao;
import com.sayee.sxsy.modules.sys.entity.Office;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-12-05
 */
public class UserUtils {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);
	private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);

	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";
	public static final String USER_CACHE_LIST_BY_OFFICE_ROLE_ID_ = "role_";

	public static final String CACHE_AUTH_INFO = "authInfo";
	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";

	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(String id){
		User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user ==  null){
			user = userDao.get(id);
			if (user == null){
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}

	/**
	 * 根据角色编码获取用户
	 * @param ename
	 * @return 取不到返回null
	 */
	public static List<User> getUser(String ename){
		return userDao.findUserByOfficeRoleId("",ename);
	}

	/**
	 * 根据角色编码获取用户
	 * @param officeId
	 * @return 取不到返回null
	 */
	public static List<User> getUserByOffice(String officeId){
		return userDao.findUserByOfficeRoleId(officeId,"");
	}

	/**
	 * 根据姓名获取用户
	 * @param name
	 * @return 取不到返回null
	 */
	public static User getId(String name){
		User user = new User();
		user = userDao.getId(StringUtils.trim(name));
		if (user == null){
			return null;
		}
		user.setRoleList(roleDao.findList(new Role(user)));
		return user;
	}
	/**
	 * 根据部门名称获得部门ID
	 * @param name
	 * @return 取不到返回null
	 */
	public static Office officeId(String name){
		Office office = officeDao.getOfficeName(StringUtils.trim(name));
		if (office == null){
			office=officeDao.getOfficeNameLike(StringUtils.trim(name));
			if (office == null){
				return null;
			}
		}
		return office;
	}

	/**
	 * 根据部门主键获得OFFICE实体类
	 *  @param id
	 * @return 取不到返回null
	 */
	public static Office getOfficeId(String id){
		Office office = officeDao.getOfficeId(StringUtils.trim(id));
		if (office == null){
			return null;
		}
		return office;
	}
	/**
	 * 根据登录名获取用户
	 * @param loginName
	 * @return 取不到返回null
	 */
	public static User getByLoginName(String loginName){
		User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
		if (user == null){
			user = userDao.getByLoginName(new User(null, loginName));
			if (user == null){
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}

	/**
	 * 根据区域id获取区域实体类
	 * @param id
	 * @return 取不到返回null
	 */
	public static Area getArea(String id){
		Area area = areaDao.get(id);
			if (area == null){
				return null;
			}
		return area;
	}

	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		removeCache(CACHE_AUTH_INFO);
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_MENU_LIST);
		removeCache(CACHE_AREA_LIST);
		removeCache(CACHE_OFFICE_LIST);
		removeCache(CACHE_OFFICE_ALL_LIST);
		UserUtils.clearCache(getUser());
	}

	/**
	 * 清除指定用户缓存
	 * @param user
	 */
	public static void clearCache(User user){
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());
		if (user.getOffice() != null && user.getOffice().getId() != null){
			CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ + user.getOffice().getId());
		}
	}

	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static User getUser(){
		SystemAuthorizingRealm.Principal principal = getPrincipal();
		if (principal!=null){
			User user = get(principal.getId());
			if (user != null){
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 获取当前用户角色列表
	 * @return
	 */
	public static List<Role> getRoleList(){
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>)getCache(CACHE_ROLE_LIST);
		if (roleList == null){
			User user = getUser();
			if (user.isAdmin()){
				roleList = roleDao.findAllList(new Role());
			}else{
				Role role = new Role();
				role.getSqlMap().put("dsf", BaseService.dataScopeFilter(user.getCurrentUser(), "o", "u"));
				roleList = roleDao.findList(role);
			}
			putCache(CACHE_ROLE_LIST, roleList);
		}
		return roleList;
	}

	/**
	 * 获取当前用户授权菜单
	 * @return
	 */
	public static List<Menu> getMenuList(){
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>)getCache(CACHE_MENU_LIST);
		if (menuList == null){
			User user = getUser();
			if (user.isAdmin()){
				menuList = menuDao.findAllList(new Menu());
			}else{
				Menu m = new Menu();
				m.setUserId(user.getId());
				menuList = menuDao.findByUserId(m);
			}
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}

	/**
	 * 获取当前用户授权的区域
	 * @return
	 */
	public static List<Area> getAreaList(){
		@SuppressWarnings("unchecked")
		List<Area> areaList = null;//(List<Area>)getCache(CACHE_AREA_LIST)
		String areaId=UserUtils.getUser().getCompany().getArea().getId();
		if (areaList == null){
			Area area=new Area();
			if (!UserUtils.getUser().isAdmin()){
				area.setAreaId(areaId);
				area.setCity("1");
			}
			areaList = areaDao.findAllList(area);
			putCache(CACHE_AREA_LIST, areaList);
		}
		return areaList;
	}

	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Office> getOfficeList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_LIST);
		if (officeList == null){
			User user = getUser();
			if (user.isAdmin()){
				officeList = officeDao.findAllList(new Office());
			}else{
				Office office = new Office();
				office.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
				officeList = officeDao.findList(office);
			}
			putCache(CACHE_OFFICE_LIST, officeList);
		}
		return officeList;
	}



	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Office> getOfficeAllList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_ALL_LIST);
		if (officeList == null){
			Office office = new Office();
			String areaId = UserUtils.getUser().getCompany().getArea().getId();
			office.setArea(new Area());
			office.getArea().setAreaId(areaId);
			officeList = officeDao.findAllList(office);
		}
		return officeList;
	}

	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前登录者对象
	 */
	public static SystemAuthorizingRealm.Principal getPrincipal(){
		try{
			Subject subject = SecurityUtils.getSubject();
			SystemAuthorizingRealm.Principal principal = (SystemAuthorizingRealm.Principal)subject.getPrincipal();
			if (principal != null){
				return principal;
			}
//			subject.logout();
		}catch (UnavailableSecurityManagerException e) {

		}catch (InvalidSessionException e){

		}
		return null;
	}

	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
//			subject.logout();
		}catch (InvalidSessionException e){

		}
		return null;
	}

	// ============== User Cache ==============

	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
//		Object obj = getCacheMap().get(key);
		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
//		getCacheMap().put(key, value);
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
//		getCacheMap().remove(key);
		getSession().removeAttribute(key);
	}

//	public static Map<String, Object> getCacheMap(){
//		Principal principal = getPrincipal();
//		if(principal!=null){
//			return principal.getCacheMap();
//		}
//		return new HashMap<String, Object>();
//	}
	public static List<Role> selectRoles(String id){
		return roleDao.selectRole(id);
	}
}
