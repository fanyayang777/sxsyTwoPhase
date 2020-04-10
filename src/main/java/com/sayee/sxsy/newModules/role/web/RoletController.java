package com.sayee.sxsy.newModules.role.web;


import com.sayee.sxsy.newModules.role.entity.SysRole;
import com.sayee.sxsy.newModules.role.entity.SysRoleOfficeKey;
import com.sayee.sxsy.newModules.role.service.RoleService;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoletController {
    @Autowired
    RoleService roleService;
    @RequestMapping(value = "ysj/role" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponsesUtils fandByOfficeIds(@RequestBody String[] str){
        List<SysRole> list = roleService.fandByOfficeIds(str);
        return ResponsesUtils.ok(list);
    }
    @RequestMapping(value = "ysj/roleById" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponsesUtils fandByRoleId(@RequestBody String id){


        return ResponsesUtils.ok(roleService.fandByRoleId(id));
    }
}
