package com.sayee.sxsy.newModules.role.service;

import com.sayee.sxsy.newModules.role.dao.SysRoleMapper;
import com.sayee.sxsy.newModules.role.dao.SysRoleOfficeMapper;
import com.sayee.sxsy.newModules.role.entity.SysRole;
import com.sayee.sxsy.newModules.role.entity.SysRoleExample;
import com.sayee.sxsy.newModules.role.entity.SysRoleOfficeExample;
import com.sayee.sxsy.newModules.role.entity.SysRoleOfficeKey;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
@Transactional(readOnly = true)
public class RoleService {
    @Autowired
    SysRoleMapper mapper;
    @Autowired
    SysRoleOfficeMapper sysRoleOfficeMapper;
    public List<SysRole> fandByOfficeId(String id) {

        return   mapper.selectByOfficeId(id);
    }
    public List<SysRole> fandByOfficeIds(String[] ids) {
        SysRoleOfficeExample example = new SysRoleOfficeExample();
        example.setDistinct(true);
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.setDistinct(true);

        System.out.println(ids);
        if (ids.length==0) {
            return  null;
        }
        for (String id :ids) {
            example.or().andOfficeIdEqualTo(id);

        }
        List<SysRoleOfficeKey> sysRoleOfficeKeys = sysRoleOfficeMapper.selectByExample(example);

        if (sysRoleOfficeKeys.size()!=0) {
            for (SysRoleOfficeKey sysRoleOfficeKey:sysRoleOfficeKeys) {

                sysRoleExample.or().andIdEqualTo(sysRoleOfficeKey.getRoleId());

            }
        }


        return mapper.selectByExample(sysRoleExample) ;
    }

    public SysRole fandByRoleId(String id){
       return mapper.selectByPrimaryKey(id) ;
    }
}
