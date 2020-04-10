package com.sayee.sxsy.newModules.lawcase.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LawCaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LawCaseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andLawCaseIdIsNull() {
            addCriterion("LAW_CASE_ID is null");
            return (Criteria) this;
        }

        public Criteria andLawCaseIdIsNotNull() {
            addCriterion("LAW_CASE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLawCaseIdEqualTo(String value) {
            addCriterion("LAW_CASE_ID =", value, "lawCaseId");
            return (Criteria) this;
        }

        public Criteria andLawCaseIdNotEqualTo(String value) {
            addCriterion("LAW_CASE_ID <>", value, "lawCaseId");
            return (Criteria) this;
        }

        public Criteria andLawCaseIdGreaterThan(String value) {
            addCriterion("LAW_CASE_ID >", value, "lawCaseId");
            return (Criteria) this;
        }

        public Criteria andLawCaseIdGreaterThanOrEqualTo(String value) {
            addCriterion("LAW_CASE_ID >=", value, "lawCaseId");
            return (Criteria) this;
        }

        public Criteria andLawCaseIdLessThan(String value) {
            addCriterion("LAW_CASE_ID <", value, "lawCaseId");
            return (Criteria) this;
        }

        public Criteria andLawCaseIdLessThanOrEqualTo(String value) {
            addCriterion("LAW_CASE_ID <=", value, "lawCaseId");
            return (Criteria) this;
        }

        public Criteria andLawCaseIdLike(String value) {
            addCriterion("LAW_CASE_ID like", value, "lawCaseId");
            return (Criteria) this;
        }

        public Criteria andLawCaseIdNotLike(String value) {
            addCriterion("LAW_CASE_ID not like", value, "lawCaseId");
            return (Criteria) this;
        }

        public Criteria andLawCaseIdIn(List<String> values) {
            addCriterion("LAW_CASE_ID in", values, "lawCaseId");
            return (Criteria) this;
        }

        public Criteria andLawCaseIdNotIn(List<String> values) {
            addCriterion("LAW_CASE_ID not in", values, "lawCaseId");
            return (Criteria) this;
        }

        public Criteria andLawCaseIdBetween(String value1, String value2) {
            addCriterion("LAW_CASE_ID between", value1, value2, "lawCaseId");
            return (Criteria) this;
        }

        public Criteria andLawCaseIdNotBetween(String value1, String value2) {
            addCriterion("LAW_CASE_ID not between", value1, value2, "lawCaseId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNull() {
            addCriterion("PUBLISH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNotNull() {
            addCriterion("PUBLISH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeEqualTo(String value) {
            addCriterion("PUBLISH_TIME =", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotEqualTo(String value) {
            addCriterion("PUBLISH_TIME <>", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThan(String value) {
            addCriterion("PUBLISH_TIME >", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_TIME >=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThan(String value) {
            addCriterion("PUBLISH_TIME <", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_TIME <=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLike(String value) {
            addCriterion("PUBLISH_TIME like", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotLike(String value) {
            addCriterion("PUBLISH_TIME not like", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIn(List<String> values) {
            addCriterion("PUBLISH_TIME in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotIn(List<String> values) {
            addCriterion("PUBLISH_TIME not in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeBetween(String value1, String value2) {
            addCriterion("PUBLISH_TIME between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_TIME not between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("REMARKS is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("REMARKS is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("REMARKS =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("REMARKS <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("REMARKS >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("REMARKS >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("REMARKS <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("REMARKS <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("REMARKS like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("REMARKS not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("REMARKS in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("REMARKS not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("REMARKS between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("REMARKS not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("CREATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("CREATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("CREATE_BY =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("CREATE_BY <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("CREATE_BY >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_BY >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("CREATE_BY <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("CREATE_BY <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("CREATE_BY like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("CREATE_BY not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("CREATE_BY in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("CREATE_BY not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("CREATE_BY between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("CREATE_BY not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("UPDATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("UPDATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("UPDATE_BY =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("UPDATE_BY <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("UPDATE_BY >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("UPDATE_BY <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("UPDATE_BY like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("UPDATE_BY not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("UPDATE_BY in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("UPDATE_BY not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("UPDATE_BY between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("UPDATE_BY not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("DEL_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("DEL_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("DEL_FLAG =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("DEL_FLAG <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("DEL_FLAG >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("DEL_FLAG >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("DEL_FLAG <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("DEL_FLAG <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("DEL_FLAG like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("DEL_FLAG not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("DEL_FLAG in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("DEL_FLAG not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("DEL_FLAG between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("DEL_FLAG not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andLawTypeIsNull() {
            addCriterion("LAW_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andLawTypeIsNotNull() {
            addCriterion("LAW_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andLawTypeEqualTo(String value) {
            addCriterion("LAW_TYPE =", value, "lawType");
            return (Criteria) this;
        }

        public Criteria andLawTypeNotEqualTo(String value) {
            addCriterion("LAW_TYPE <>", value, "lawType");
            return (Criteria) this;
        }

        public Criteria andLawTypeGreaterThan(String value) {
            addCriterion("LAW_TYPE >", value, "lawType");
            return (Criteria) this;
        }

        public Criteria andLawTypeGreaterThanOrEqualTo(String value) {
            addCriterion("LAW_TYPE >=", value, "lawType");
            return (Criteria) this;
        }

        public Criteria andLawTypeLessThan(String value) {
            addCriterion("LAW_TYPE <", value, "lawType");
            return (Criteria) this;
        }

        public Criteria andLawTypeLessThanOrEqualTo(String value) {
            addCriterion("LAW_TYPE <=", value, "lawType");
            return (Criteria) this;
        }

        public Criteria andLawTypeLike(String value) {
            addCriterion("LAW_TYPE like", value, "lawType");
            return (Criteria) this;
        }

        public Criteria andLawTypeNotLike(String value) {
            addCriterion("LAW_TYPE not like", value, "lawType");
            return (Criteria) this;
        }

        public Criteria andLawTypeIn(List<String> values) {
            addCriterion("LAW_TYPE in", values, "lawType");
            return (Criteria) this;
        }

        public Criteria andLawTypeNotIn(List<String> values) {
            addCriterion("LAW_TYPE not in", values, "lawType");
            return (Criteria) this;
        }

        public Criteria andLawTypeBetween(String value1, String value2) {
            addCriterion("LAW_TYPE between", value1, value2, "lawType");
            return (Criteria) this;
        }

        public Criteria andLawTypeNotBetween(String value1, String value2) {
            addCriterion("LAW_TYPE not between", value1, value2, "lawType");
            return (Criteria) this;
        }

        public Criteria andDisputeTypeIsNull() {
            addCriterion("DISPUTE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDisputeTypeIsNotNull() {
            addCriterion("DISPUTE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDisputeTypeEqualTo(String value) {
            addCriterion("DISPUTE_TYPE =", value, "disputeType");
            return (Criteria) this;
        }

        public Criteria andDisputeTypeNotEqualTo(String value) {
            addCriterion("DISPUTE_TYPE <>", value, "disputeType");
            return (Criteria) this;
        }

        public Criteria andDisputeTypeGreaterThan(String value) {
            addCriterion("DISPUTE_TYPE >", value, "disputeType");
            return (Criteria) this;
        }

        public Criteria andDisputeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DISPUTE_TYPE >=", value, "disputeType");
            return (Criteria) this;
        }

        public Criteria andDisputeTypeLessThan(String value) {
            addCriterion("DISPUTE_TYPE <", value, "disputeType");
            return (Criteria) this;
        }

        public Criteria andDisputeTypeLessThanOrEqualTo(String value) {
            addCriterion("DISPUTE_TYPE <=", value, "disputeType");
            return (Criteria) this;
        }

        public Criteria andDisputeTypeLike(String value) {
            addCriterion("DISPUTE_TYPE like", value, "disputeType");
            return (Criteria) this;
        }

        public Criteria andDisputeTypeNotLike(String value) {
            addCriterion("DISPUTE_TYPE not like", value, "disputeType");
            return (Criteria) this;
        }

        public Criteria andDisputeTypeIn(List<String> values) {
            addCriterion("DISPUTE_TYPE in", values, "disputeType");
            return (Criteria) this;
        }

        public Criteria andDisputeTypeNotIn(List<String> values) {
            addCriterion("DISPUTE_TYPE not in", values, "disputeType");
            return (Criteria) this;
        }

        public Criteria andDisputeTypeBetween(String value1, String value2) {
            addCriterion("DISPUTE_TYPE between", value1, value2, "disputeType");
            return (Criteria) this;
        }

        public Criteria andDisputeTypeNotBetween(String value1, String value2) {
            addCriterion("DISPUTE_TYPE not between", value1, value2, "disputeType");
            return (Criteria) this;
        }

        public Criteria andMajorTypeIsNull() {
            addCriterion("MAJOR_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andMajorTypeIsNotNull() {
            addCriterion("MAJOR_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMajorTypeEqualTo(String value) {
            addCriterion("MAJOR_TYPE =", value, "majorType");
            return (Criteria) this;
        }

        public Criteria andMajorTypeNotEqualTo(String value) {
            addCriterion("MAJOR_TYPE <>", value, "majorType");
            return (Criteria) this;
        }

        public Criteria andMajorTypeGreaterThan(String value) {
            addCriterion("MAJOR_TYPE >", value, "majorType");
            return (Criteria) this;
        }

        public Criteria andMajorTypeGreaterThanOrEqualTo(String value) {
            addCriterion("MAJOR_TYPE >=", value, "majorType");
            return (Criteria) this;
        }

        public Criteria andMajorTypeLessThan(String value) {
            addCriterion("MAJOR_TYPE <", value, "majorType");
            return (Criteria) this;
        }

        public Criteria andMajorTypeLessThanOrEqualTo(String value) {
            addCriterion("MAJOR_TYPE <=", value, "majorType");
            return (Criteria) this;
        }

        public Criteria andMajorTypeLike(String value) {
            addCriterion("MAJOR_TYPE like", value, "majorType");
            return (Criteria) this;
        }

        public Criteria andMajorTypeNotLike(String value) {
            addCriterion("MAJOR_TYPE not like", value, "majorType");
            return (Criteria) this;
        }

        public Criteria andMajorTypeIn(List<String> values) {
            addCriterion("MAJOR_TYPE in", values, "majorType");
            return (Criteria) this;
        }

        public Criteria andMajorTypeNotIn(List<String> values) {
            addCriterion("MAJOR_TYPE not in", values, "majorType");
            return (Criteria) this;
        }

        public Criteria andMajorTypeBetween(String value1, String value2) {
            addCriterion("MAJOR_TYPE between", value1, value2, "majorType");
            return (Criteria) this;
        }

        public Criteria andMajorTypeNotBetween(String value1, String value2) {
            addCriterion("MAJOR_TYPE not between", value1, value2, "majorType");
            return (Criteria) this;
        }

        public Criteria andCoreSystemTypeIsNull() {
            addCriterion("CORE_SYSTEM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCoreSystemTypeIsNotNull() {
            addCriterion("CORE_SYSTEM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCoreSystemTypeEqualTo(String value) {
            addCriterion("CORE_SYSTEM_TYPE =", value, "coreSystemType");
            return (Criteria) this;
        }

        public Criteria andCoreSystemTypeNotEqualTo(String value) {
            addCriterion("CORE_SYSTEM_TYPE <>", value, "coreSystemType");
            return (Criteria) this;
        }

        public Criteria andCoreSystemTypeGreaterThan(String value) {
            addCriterion("CORE_SYSTEM_TYPE >", value, "coreSystemType");
            return (Criteria) this;
        }

        public Criteria andCoreSystemTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CORE_SYSTEM_TYPE >=", value, "coreSystemType");
            return (Criteria) this;
        }

        public Criteria andCoreSystemTypeLessThan(String value) {
            addCriterion("CORE_SYSTEM_TYPE <", value, "coreSystemType");
            return (Criteria) this;
        }

        public Criteria andCoreSystemTypeLessThanOrEqualTo(String value) {
            addCriterion("CORE_SYSTEM_TYPE <=", value, "coreSystemType");
            return (Criteria) this;
        }

        public Criteria andCoreSystemTypeLike(String value) {
            addCriterion("CORE_SYSTEM_TYPE like", value, "coreSystemType");
            return (Criteria) this;
        }

        public Criteria andCoreSystemTypeNotLike(String value) {
            addCriterion("CORE_SYSTEM_TYPE not like", value, "coreSystemType");
            return (Criteria) this;
        }

        public Criteria andCoreSystemTypeIn(List<String> values) {
            addCriterion("CORE_SYSTEM_TYPE in", values, "coreSystemType");
            return (Criteria) this;
        }

        public Criteria andCoreSystemTypeNotIn(List<String> values) {
            addCriterion("CORE_SYSTEM_TYPE not in", values, "coreSystemType");
            return (Criteria) this;
        }

        public Criteria andCoreSystemTypeBetween(String value1, String value2) {
            addCriterion("CORE_SYSTEM_TYPE between", value1, value2, "coreSystemType");
            return (Criteria) this;
        }

        public Criteria andCoreSystemTypeNotBetween(String value1, String value2) {
            addCriterion("CORE_SYSTEM_TYPE not between", value1, value2, "coreSystemType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}