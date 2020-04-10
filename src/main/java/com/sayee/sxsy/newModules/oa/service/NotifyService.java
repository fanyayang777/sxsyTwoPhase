package com.sayee.sxsy.newModules.oa.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sayee.sxsy.newModules.oa.dao.OaNotifyMapper;
import com.sayee.sxsy.newModules.oa.entity.OaNotify;
import com.sayee.sxsy.newModules.oa.entity.OaNotifyExample;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class NotifyService {
    @Autowired
    OaNotifyMapper mapper;

    public ResponsesUtils getNotify(Integer page, Integer size) {
        OaNotifyExample example = new OaNotifyExample();
        List<OaNotify> oaNotifies = mapper.selectByExampleWithBLOBs(example);
        PageHelper.startPage(page,size);
        PageInfo<OaNotify> pageInfo = new PageInfo<>(oaNotifies);
        return ResponsesUtils.ok(pageInfo);

    }


}
