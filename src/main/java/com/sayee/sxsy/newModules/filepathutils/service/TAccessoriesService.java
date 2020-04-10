package com.sayee.sxsy.newModules.filepathutils.service;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.newModules.filepathutils.dao.TAccessoriesMapper;
import com.sayee.sxsy.newModules.filepathutils.entity.TAccessories;
import com.sayee.sxsy.newModules.filepathutils.entity.TAccessoriesExample;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TAccessoriesService {
    @Autowired
    TAccessoriesMapper mapper;

    public ResponsesUtils saveFilePath(TAccessories tAccessories){

        tAccessories.setAcceId(IdGen.uuid());

        int i = mapper.insertSelective(tAccessories);
        if (i==0){
            return ResponsesUtils.build(500,"保存地址时出错");
        }
        return ResponsesUtils.ok();
    }
public List<TAccessories> fandByEmployeeId(TAccessories tAccessories){

    TAccessoriesExample example = new TAccessoriesExample();

    example.createCriteria().andEmployeeidEqualTo(tAccessories.getEmployeeid());

    return mapper.selectByExampleWithBLOBs(example);
};


}
