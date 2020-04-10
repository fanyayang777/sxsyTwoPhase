package com.sayee.sxsy.newModules.sysuser.service;

import com.sayee.sxsy.common.utils.CacheUtils;
import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.newModules.office.dao.SysOfficeMapper;
import com.sayee.sxsy.newModules.office.entity.SysOffice;
import com.sayee.sxsy.newModules.sysuser.dao.SysUserMapper;
import com.sayee.sxsy.newModules.sysuser.entity.SysUser;
import com.sayee.sxsy.newModules.sysuser.entity.SysUserExample;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class sysUserService {

    @Autowired
    SysUserMapper mapper;
    @Autowired
    SysOfficeMapper sysOfficeMapper;

    public List<SysUser> fandByOfficeId(String[] officeids){
        if (officeids.length==0){
            return null;
        }
        SysUserExample example = new SysUserExample();
        for (String officeid:officeids) {

            example.or().andOfficeIdEqualTo(officeid);

        }
        return mapper.selectByExample(example);
    }
    public Integer saveUser (SysUser sysUser, SysOffice sysOffice,String code){

        sysOffice.setId(IdGen.uuid());
        sysOffice.setMaster("0");
        sysOffice.setParentId("0");
        sysOffice.setParentIds("0,");
        sysOffice.setSort(30L);
        sysOffice.setCreateBy(sysOffice.getId());
        sysOffice.setUpdateBy(sysOffice.getId());
        sysOffice.setCreateDate(new java.sql.Date(new Date().getTime()));
        sysOffice.setUpdateDate(new java.sql.Date(new Date().getTime()));


        sysUser.setId(IdGen.uuid());
        sysUser.setOfficeId(sysOffice.getId());
        sysUser.setCreateBy(sysUser.getId());
        sysUser.setUpdateBy(sysUser.getId());
        sysUser.setCreateDate(new java.sql.Date(new Date().getTime()));
        sysUser.setUpdateDate(new java.sql.Date(new Date().getTime()));
        sysUser.setLoginFlag("0");
        int i = mapper.insertSelective(sysUser);
        if (i==0) {
            return 0;
        }



        int i1 = sysOfficeMapper.insertSelective(sysOffice);
        if (i1==0) {
            return 0;
        }
        String random = CacheUtils.get("YSJ_CODE",sysUser.getLoginName()).toString();



        if (!code.equals(random)) {
            return 500;
        }
        return  1;

    }
}
