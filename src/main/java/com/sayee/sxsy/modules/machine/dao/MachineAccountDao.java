/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.machine.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.machine.entity.MachineAccount;
import com.sayee.sxsy.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 台账信息展示DAO接口
 * @author zhangfan
 * @version 2019-05-17
 */
@MyBatisDao
public interface MachineAccountDao extends CrudDao<MachineAccount> {
    /**
     * 卷宗编号验重
     * @param fileNumber
     * @return
     */
    public MachineAccount checkFileNumber(String fileNumber);

    /**
     * 获取详情信息
     * @param machineAccountId
     * @return
     */
    public MachineAccount getDetail(String machineAccountId);
    /**
     * 导出台账
     */
    public List<MachineAccount> findMachine(MachineAccount machineAccount);

    long findPageCount(MachineAccount machineAccount);
}