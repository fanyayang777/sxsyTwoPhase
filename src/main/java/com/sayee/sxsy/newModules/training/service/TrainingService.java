package com.sayee.sxsy.newModules.training.service;

import com.sayee.sxsy.newModules.training.dao.TrainMapper;

import com.sayee.sxsy.newModules.training.entity.Train;
import com.sayee.sxsy.newModules.training.entity.TrainExample;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    @Autowired
    TrainMapper mapper;

    public ResponsesUtils fandByVidioType(Train train){
        TrainExample example = new TrainExample();
        example.createCriteria().andVidioTypeEqualTo(train.getVidioType());

        List<Train> trainings = mapper.selectByExample(example);

        return ResponsesUtils.ok(trainings);

    }
}
