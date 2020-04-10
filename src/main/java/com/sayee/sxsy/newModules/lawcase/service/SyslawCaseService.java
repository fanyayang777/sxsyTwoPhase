package com.sayee.sxsy.newModules.lawcase.service;

import com.sayee.sxsy.newModules.lawcase.dao.LawCaseMapper;

import com.sayee.sxsy.newModules.lawcase.entity.LawCase;
import com.sayee.sxsy.newModules.lawcase.entity.LawCaseExample;
import com.sayee.sxsy.newModules.utils.CaseUtils;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SyslawCaseService {
    @Autowired
    LawCaseMapper mapper;

    public ResponsesUtils fandByType(LawCase lawCase) {

        LawCaseExample example = new LawCaseExample();
        System.out.println(lawCase.getLawType());
        example.createCriteria().andLawTypeEqualTo(lawCase.getLawType());
        example.createCriteria().andTypeEqualTo(lawCase.getType());
        List<LawCase> lawCases = mapper.selectByExampleWithBLOBs(example);
        return ResponsesUtils.ok(lawCases);
    }

    public ResponsesUtils fandCaseByType(CaseUtils caseUtils) {

        LawCaseExample example = new LawCaseExample();

        if (caseUtils.getDISPUTE_TYPE().length!=0) {
            for (String str:caseUtils.getDISPUTE_TYPE()) {
                example.or().andDisputeTypeEqualTo(str);
            }
        }
        if (caseUtils.getMAJOR_TYPE().length!=0) {
            for (String str:caseUtils.getMAJOR_TYPE()) {
                example.or().andMajorTypeEqualTo(str);

            }
        }
        if (caseUtils.getCORE_SYSTEM_TYPE().length!=0) {
            for (String str:caseUtils.getCORE_SYSTEM_TYPE()) {
                example.or().andMajorTypeEqualTo(str);
            }
        }
        return ResponsesUtils.ok(mapper.selectByExampleWithBLOBs(example));
    }
}
