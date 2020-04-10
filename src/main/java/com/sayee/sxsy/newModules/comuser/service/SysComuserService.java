package com.sayee.sxsy.newModules.comuser.service;

import com.sayee.sxsy.newModules.comuser.dao.SysComuserMapper;
import com.sayee.sxsy.newModules.comuser.entity.SysComuser;
import com.sayee.sxsy.newModules.comuser.entity.SysComuserExample;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SysComuserService {
    /**
     * 通过登录名查找
     */
    @Autowired
    SysComuserMapper mapper;

    public SysComuser findByLoginName(String loginName) {

        SysComuserExample example = new SysComuserExample();
        example.createCriteria().andLoginNameEqualTo(loginName);
        List<SysComuser> sysComusers = mapper.selectByExample(example);
        if (sysComusers.size() == 0) {
            return null;
        }
        return sysComusers.get(0);
    }

    @Transactional(readOnly = false)
    public ResponsesUtils save(SysComuser sysComuser) {

        int insert = mapper.insert(sysComuser);
        if (insert != 0) {
            return ResponsesUtils.ok(sysComuser);
        } else {
            return ResponsesUtils.build(500, "注册失败");
        }

    }
    @Transactional(readOnly = false)
    public ResponsesUtils savaUpdateComUser (SysComuser sysComuser) {
        int i = mapper.updateByPrimaryKeySelective(sysComuser);
        if (i==0){
            return ResponsesUtils.build(500,"保存出错");
        }
        return ResponsesUtils.ok();
    }


}
