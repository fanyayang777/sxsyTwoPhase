package com.sayee.sxsy.newModules.complaintinfo.service;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.common.utils.IdGen;

import com.sayee.sxsy.newModules.complaintinfo.dao.ComplaintInfoMapper;

import com.sayee.sxsy.newModules.complaintinfo.entity.ComplaintInfoWithBLOBs;
import com.sayee.sxsy.newModules.complaintman.dao.ComplaintMainMapper;
import com.sayee.sxsy.newModules.complaintman.entity.ComplaintMain;
import com.sayee.sxsy.newModules.comuser.dao.SysComuserMapper;
import com.sayee.sxsy.newModules.comuser.entity.SysComuser;
import com.sayee.sxsy.newModules.comuser.service.SysComuserService;
import com.sayee.sxsy.newModules.middleutils.dao.ComuserComplaintinfoidMapper;
import com.sayee.sxsy.newModules.middleutils.entity.ComuserComplaintinfoidKey;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class SysComplaintInfoService {
    @Autowired
    ComplaintInfoMapper mapper;
    @Autowired
    ComuserComplaintinfoidMapper comuserComplaintinfoidMapper;
    @Autowired
    SysComuserMapper sysComuserMapper;
    @Autowired
    ComplaintMainMapper complaintMainMapper;
    @Transactional(readOnly = false)
    public ResponsesUtils save(JSONObject jsonObject) {
        SysComuser sysComuser = jsonObject.toJavaObject(SysComuser.class);
        ComplaintInfoWithBLOBs complaintInfoWithBLOBs = jsonObject.toJavaObject(ComplaintInfoWithBLOBs.class);
        ComplaintMain complaintMain = jsonObject.toJavaObject(ComplaintMain.class);

        System.out.println(sysComuser.toString());
        System.out.println(complaintInfoWithBLOBs.toString());
        System.out.println(complaintMain.toString());


        //完善用户信息
        int i = sysComuserMapper.updateByPrimaryKeySelective(sysComuser);

        if (i == 0) {
            return ResponsesUtils.build(500, "用户信息错误");
        }

        //创建主表数据
        complaintMain.setComplaintMainId(IdGen.uuid());
        int i1 = complaintMainMapper.insertSelective(complaintMain);
        if (i1==0){
            return ResponsesUtils.build(500,"主数据保存失败");
        }

        //创建info表数据
        complaintInfoWithBLOBs.setComplaintId(IdGen.uuid());

        complaintInfoWithBLOBs.setComplaintMainId(complaintMain.getComplaintMainId());
        int insert = mapper.insertSelective(complaintInfoWithBLOBs);
        if (insert == 0) {
            return ResponsesUtils.build(500, "数据保存错误");
        }

        //用户与主数据关联
        ComuserComplaintinfoidKey comuserComplaintinfoidKey = new ComuserComplaintinfoidKey();

        comuserComplaintinfoidKey.setComuserId(sysComuser.getId()+"");

        comuserComplaintinfoidKey.setComplaintInfoId(complaintInfoWithBLOBs.getComplaintId());

        int insert1 = comuserComplaintinfoidMapper.insertSelective(comuserComplaintinfoidKey);

        if (insert1 == 0) {
            return ResponsesUtils.build(500, "用户与主数据关联关联失败");
        }
        return ResponsesUtils.ok();

    }
    @Transactional(readOnly = false)
public void test123(){
    ComplaintInfoWithBLOBs complaintInfoWithBLOBs = new ComplaintInfoWithBLOBs();
    complaintInfoWithBLOBs.setComplaintId(IdGen.uuid());
    mapper.insertSelective(complaintInfoWithBLOBs);
}

}
