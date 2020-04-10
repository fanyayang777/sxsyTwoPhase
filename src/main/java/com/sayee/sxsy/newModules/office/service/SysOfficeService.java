package com.sayee.sxsy.newModules.office.service;

import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.newModules.office.dao.SysOfficeMapper;
import com.sayee.sxsy.newModules.office.entity.SysOffice;
import com.sayee.sxsy.newModules.office.entity.SysOfficeExample;
import com.sayee.sxsy.newModules.role.service.RoleService;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SysOfficeService {
    @Autowired
    SysOfficeMapper mapper;
    @Autowired
    RoleService roleService;

    public ResponsesUtils findByOffice(SysOffice office) {
        SysOfficeExample example = new SysOfficeExample();
        SysOfficeExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(office.getType());
        criteria.andOfficeTypeEqualTo(office.getOfficeType());
        criteria.andParentIdEqualTo(office.getParentId());
        return ResponsesUtils.ok(mapper.selectByExample(example));
    }
    public ResponsesUtils findByParentId(String id){
        ArrayList<String> list = new ArrayList<>();
        SysOfficeExample example = new SysOfficeExample();
        SysOfficeExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<SysOffice> sysOffices = mapper.selectByExample(example);
        if (sysOffices.size()==0){
            System.out.println(id);
            list.add(id);
            return ResponsesUtils.build(201,"已经是最下级目录", roleService.fandByOfficeId(id));
        }
        return ResponsesUtils.ok(sysOffices);
    }

    public SysOffice findById(String id){

        return  mapper.selectByPrimaryKey(id);
    }
 public List<SysOffice> findHospitalByCity(SysOffice sysOffice){
     SysOfficeExample example = new SysOfficeExample();
     if (sysOffice.getAreaId()!=null) {


         example.createCriteria().andAreaIdEqualTo(sysOffice.getAreaId());

         example.createCriteria().andOfficeTypeEqualTo("2");

         return mapper.selectByExample(example);
     }else {

            example.createCriteria().andParentIdEqualTo(sysOffice.getParentId());

            return mapper.selectByExample(example);


     }

 }

}
