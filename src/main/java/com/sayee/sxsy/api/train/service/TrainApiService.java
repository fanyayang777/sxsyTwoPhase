package com.sayee.sxsy.api.train.service;

import com.sayee.sxsy.api.train.dao.TrainApiDao;
import com.sayee.sxsy.api.train.entity.TrainEntity;
import com.sayee.sxsy.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 */
@Service
public class TrainApiService {
    @Autowired
    private TrainApiDao trainDao;


    public List<TrainEntity> getVideo(String s) {
        TrainEntity train=new TrainEntity();
        train.setSend(s);
        List<TrainEntity> aa= trainDao.findList(train);
        return aa;
    }

    public TrainEntity videoDetail(String id) {
        TrainEntity train=trainDao.get(id);
        train.setPath(StringUtils.isBlank(train.getPath()) ? "" : train.getPath().replace("|",""));
        return train;
    }
}
