package com.sayee.sxsy.newModules.training.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TrainExample() {
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

        public Criteria andTrainIdIsNull() {
            addCriterion("TRAIN_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainIdIsNotNull() {
            addCriterion("TRAIN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainIdEqualTo(String value) {
            addCriterion("TRAIN_ID =", value, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdNotEqualTo(String value) {
            addCriterion("TRAIN_ID <>", value, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdGreaterThan(String value) {
            addCriterion("TRAIN_ID >", value, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_ID >=", value, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdLessThan(String value) {
            addCriterion("TRAIN_ID <", value, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_ID <=", value, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdLike(String value) {
            addCriterion("TRAIN_ID like", value, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdNotLike(String value) {
            addCriterion("TRAIN_ID not like", value, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdIn(List<String> values) {
            addCriterion("TRAIN_ID in", values, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdNotIn(List<String> values) {
            addCriterion("TRAIN_ID not in", values, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdBetween(String value1, String value2) {
            addCriterion("TRAIN_ID between", value1, value2, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdNotBetween(String value1, String value2) {
            addCriterion("TRAIN_ID not between", value1, value2, "trainId");
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

        public Criteria andVidioTypeIsNull() {
            addCriterion("VIDIO_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andVidioTypeIsNotNull() {
            addCriterion("VIDIO_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andVidioTypeEqualTo(String value) {
            addCriterion("VIDIO_TYPE =", value, "vidioType");
            return (Criteria) this;
        }

        public Criteria andVidioTypeNotEqualTo(String value) {
            addCriterion("VIDIO_TYPE <>", value, "vidioType");
            return (Criteria) this;
        }

        public Criteria andVidioTypeGreaterThan(String value) {
            addCriterion("VIDIO_TYPE >", value, "vidioType");
            return (Criteria) this;
        }

        public Criteria andVidioTypeGreaterThanOrEqualTo(String value) {
            addCriterion("VIDIO_TYPE >=", value, "vidioType");
            return (Criteria) this;
        }

        public Criteria andVidioTypeLessThan(String value) {
            addCriterion("VIDIO_TYPE <", value, "vidioType");
            return (Criteria) this;
        }

        public Criteria andVidioTypeLessThanOrEqualTo(String value) {
            addCriterion("VIDIO_TYPE <=", value, "vidioType");
            return (Criteria) this;
        }

        public Criteria andVidioTypeLike(String value) {
            addCriterion("VIDIO_TYPE like", value, "vidioType");
            return (Criteria) this;
        }

        public Criteria andVidioTypeNotLike(String value) {
            addCriterion("VIDIO_TYPE not like", value, "vidioType");
            return (Criteria) this;
        }

        public Criteria andVidioTypeIn(List<String> values) {
            addCriterion("VIDIO_TYPE in", values, "vidioType");
            return (Criteria) this;
        }

        public Criteria andVidioTypeNotIn(List<String> values) {
            addCriterion("VIDIO_TYPE not in", values, "vidioType");
            return (Criteria) this;
        }

        public Criteria andVidioTypeBetween(String value1, String value2) {
            addCriterion("VIDIO_TYPE between", value1, value2, "vidioType");
            return (Criteria) this;
        }

        public Criteria andVidioTypeNotBetween(String value1, String value2) {
            addCriterion("VIDIO_TYPE not between", value1, value2, "vidioType");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNull() {
            addCriterion("DEPARTMENT is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNotNull() {
            addCriterion("DEPARTMENT is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentEqualTo(String value) {
            addCriterion("DEPARTMENT =", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotEqualTo(String value) {
            addCriterion("DEPARTMENT <>", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThan(String value) {
            addCriterion("DEPARTMENT >", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("DEPARTMENT >=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThan(String value) {
            addCriterion("DEPARTMENT <", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThanOrEqualTo(String value) {
            addCriterion("DEPARTMENT <=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLike(String value) {
            addCriterion("DEPARTMENT like", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotLike(String value) {
            addCriterion("DEPARTMENT not like", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentIn(List<String> values) {
            addCriterion("DEPARTMENT in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotIn(List<String> values) {
            addCriterion("DEPARTMENT not in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentBetween(String value1, String value2) {
            addCriterion("DEPARTMENT between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotBetween(String value1, String value2) {
            addCriterion("DEPARTMENT not between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andPathIsNull() {
            addCriterion("PATH is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("PATH is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("PATH =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("PATH <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("PATH >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("PATH >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("PATH <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("PATH <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("PATH like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("PATH not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("PATH in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("PATH not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("PATH between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("PATH not between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andSendIsNull() {
            addCriterion("SEND is null");
            return (Criteria) this;
        }

        public Criteria andSendIsNotNull() {
            addCriterion("SEND is not null");
            return (Criteria) this;
        }

        public Criteria andSendEqualTo(String value) {
            addCriterion("SEND =", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendNotEqualTo(String value) {
            addCriterion("SEND <>", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendGreaterThan(String value) {
            addCriterion("SEND >", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendGreaterThanOrEqualTo(String value) {
            addCriterion("SEND >=", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendLessThan(String value) {
            addCriterion("SEND <", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendLessThanOrEqualTo(String value) {
            addCriterion("SEND <=", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendLike(String value) {
            addCriterion("SEND like", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendNotLike(String value) {
            addCriterion("SEND not like", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendIn(List<String> values) {
            addCriterion("SEND in", values, "send");
            return (Criteria) this;
        }

        public Criteria andSendNotIn(List<String> values) {
            addCriterion("SEND not in", values, "send");
            return (Criteria) this;
        }

        public Criteria andSendBetween(String value1, String value2) {
            addCriterion("SEND between", value1, value2, "send");
            return (Criteria) this;
        }

        public Criteria andSendNotBetween(String value1, String value2) {
            addCriterion("SEND not between", value1, value2, "send");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNull() {
            addCriterion("INTRODUCE is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNotNull() {
            addCriterion("INTRODUCE is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceEqualTo(String value) {
            addCriterion("INTRODUCE =", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotEqualTo(String value) {
            addCriterion("INTRODUCE <>", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThan(String value) {
            addCriterion("INTRODUCE >", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("INTRODUCE >=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThan(String value) {
            addCriterion("INTRODUCE <", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThanOrEqualTo(String value) {
            addCriterion("INTRODUCE <=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLike(String value) {
            addCriterion("INTRODUCE like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotLike(String value) {
            addCriterion("INTRODUCE not like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceIn(List<String> values) {
            addCriterion("INTRODUCE in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotIn(List<String> values) {
            addCriterion("INTRODUCE not in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceBetween(String value1, String value2) {
            addCriterion("INTRODUCE between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotBetween(String value1, String value2) {
            addCriterion("INTRODUCE not between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("SCORE is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(String value) {
            addCriterion("SCORE =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(String value) {
            addCriterion("SCORE <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(String value) {
            addCriterion("SCORE >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(String value) {
            addCriterion("SCORE <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(String value) {
            addCriterion("SCORE <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLike(String value) {
            addCriterion("SCORE like", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotLike(String value) {
            addCriterion("SCORE not like", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<String> values) {
            addCriterion("SCORE in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<String> values) {
            addCriterion("SCORE not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(String value1, String value2) {
            addCriterion("SCORE between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(String value1, String value2) {
            addCriterion("SCORE not between", value1, value2, "score");
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

        public Criteria andPicturePathIsNull() {
            addCriterion("PICTURE_PATH is null");
            return (Criteria) this;
        }

        public Criteria andPicturePathIsNotNull() {
            addCriterion("PICTURE_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andPicturePathEqualTo(String value) {
            addCriterion("PICTURE_PATH =", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathNotEqualTo(String value) {
            addCriterion("PICTURE_PATH <>", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathGreaterThan(String value) {
            addCriterion("PICTURE_PATH >", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathGreaterThanOrEqualTo(String value) {
            addCriterion("PICTURE_PATH >=", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathLessThan(String value) {
            addCriterion("PICTURE_PATH <", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathLessThanOrEqualTo(String value) {
            addCriterion("PICTURE_PATH <=", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathLike(String value) {
            addCriterion("PICTURE_PATH like", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathNotLike(String value) {
            addCriterion("PICTURE_PATH not like", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathIn(List<String> values) {
            addCriterion("PICTURE_PATH in", values, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathNotIn(List<String> values) {
            addCriterion("PICTURE_PATH not in", values, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathBetween(String value1, String value2) {
            addCriterion("PICTURE_PATH between", value1, value2, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathNotBetween(String value1, String value2) {
            addCriterion("PICTURE_PATH not between", value1, value2, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNull() {
            addCriterion("PAYMENT is null");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNotNull() {
            addCriterion("PAYMENT is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentEqualTo(String value) {
            addCriterion("PAYMENT =", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotEqualTo(String value) {
            addCriterion("PAYMENT <>", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThan(String value) {
            addCriterion("PAYMENT >", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThanOrEqualTo(String value) {
            addCriterion("PAYMENT >=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThan(String value) {
            addCriterion("PAYMENT <", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThanOrEqualTo(String value) {
            addCriterion("PAYMENT <=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLike(String value) {
            addCriterion("PAYMENT like", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotLike(String value) {
            addCriterion("PAYMENT not like", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentIn(List<String> values) {
            addCriterion("PAYMENT in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotIn(List<String> values) {
            addCriterion("PAYMENT not in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentBetween(String value1, String value2) {
            addCriterion("PAYMENT between", value1, value2, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotBetween(String value1, String value2) {
            addCriterion("PAYMENT not between", value1, value2, "payment");
            return (Criteria) this;
        }

        public Criteria andCourseChapterIsNull() {
            addCriterion("COURSE_CHAPTER is null");
            return (Criteria) this;
        }

        public Criteria andCourseChapterIsNotNull() {
            addCriterion("COURSE_CHAPTER is not null");
            return (Criteria) this;
        }

        public Criteria andCourseChapterEqualTo(String value) {
            addCriterion("COURSE_CHAPTER =", value, "courseChapter");
            return (Criteria) this;
        }

        public Criteria andCourseChapterNotEqualTo(String value) {
            addCriterion("COURSE_CHAPTER <>", value, "courseChapter");
            return (Criteria) this;
        }

        public Criteria andCourseChapterGreaterThan(String value) {
            addCriterion("COURSE_CHAPTER >", value, "courseChapter");
            return (Criteria) this;
        }

        public Criteria andCourseChapterGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_CHAPTER >=", value, "courseChapter");
            return (Criteria) this;
        }

        public Criteria andCourseChapterLessThan(String value) {
            addCriterion("COURSE_CHAPTER <", value, "courseChapter");
            return (Criteria) this;
        }

        public Criteria andCourseChapterLessThanOrEqualTo(String value) {
            addCriterion("COURSE_CHAPTER <=", value, "courseChapter");
            return (Criteria) this;
        }

        public Criteria andCourseChapterLike(String value) {
            addCriterion("COURSE_CHAPTER like", value, "courseChapter");
            return (Criteria) this;
        }

        public Criteria andCourseChapterNotLike(String value) {
            addCriterion("COURSE_CHAPTER not like", value, "courseChapter");
            return (Criteria) this;
        }

        public Criteria andCourseChapterIn(List<String> values) {
            addCriterion("COURSE_CHAPTER in", values, "courseChapter");
            return (Criteria) this;
        }

        public Criteria andCourseChapterNotIn(List<String> values) {
            addCriterion("COURSE_CHAPTER not in", values, "courseChapter");
            return (Criteria) this;
        }

        public Criteria andCourseChapterBetween(String value1, String value2) {
            addCriterion("COURSE_CHAPTER between", value1, value2, "courseChapter");
            return (Criteria) this;
        }

        public Criteria andCourseChapterNotBetween(String value1, String value2) {
            addCriterion("COURSE_CHAPTER not between", value1, value2, "courseChapter");
            return (Criteria) this;
        }

        public Criteria andBuyNumberIsNull() {
            addCriterion("BUY_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andBuyNumberIsNotNull() {
            addCriterion("BUY_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andBuyNumberEqualTo(String value) {
            addCriterion("BUY_NUMBER =", value, "buyNumber");
            return (Criteria) this;
        }

        public Criteria andBuyNumberNotEqualTo(String value) {
            addCriterion("BUY_NUMBER <>", value, "buyNumber");
            return (Criteria) this;
        }

        public Criteria andBuyNumberGreaterThan(String value) {
            addCriterion("BUY_NUMBER >", value, "buyNumber");
            return (Criteria) this;
        }

        public Criteria andBuyNumberGreaterThanOrEqualTo(String value) {
            addCriterion("BUY_NUMBER >=", value, "buyNumber");
            return (Criteria) this;
        }

        public Criteria andBuyNumberLessThan(String value) {
            addCriterion("BUY_NUMBER <", value, "buyNumber");
            return (Criteria) this;
        }

        public Criteria andBuyNumberLessThanOrEqualTo(String value) {
            addCriterion("BUY_NUMBER <=", value, "buyNumber");
            return (Criteria) this;
        }

        public Criteria andBuyNumberLike(String value) {
            addCriterion("BUY_NUMBER like", value, "buyNumber");
            return (Criteria) this;
        }

        public Criteria andBuyNumberNotLike(String value) {
            addCriterion("BUY_NUMBER not like", value, "buyNumber");
            return (Criteria) this;
        }

        public Criteria andBuyNumberIn(List<String> values) {
            addCriterion("BUY_NUMBER in", values, "buyNumber");
            return (Criteria) this;
        }

        public Criteria andBuyNumberNotIn(List<String> values) {
            addCriterion("BUY_NUMBER not in", values, "buyNumber");
            return (Criteria) this;
        }

        public Criteria andBuyNumberBetween(String value1, String value2) {
            addCriterion("BUY_NUMBER between", value1, value2, "buyNumber");
            return (Criteria) this;
        }

        public Criteria andBuyNumberNotBetween(String value1, String value2) {
            addCriterion("BUY_NUMBER not between", value1, value2, "buyNumber");
            return (Criteria) this;
        }

        public Criteria andVideoPriceIsNull() {
            addCriterion("VIDEO_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andVideoPriceIsNotNull() {
            addCriterion("VIDEO_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andVideoPriceEqualTo(BigDecimal value) {
            addCriterion("VIDEO_PRICE =", value, "videoPrice");
            return (Criteria) this;
        }

        public Criteria andVideoPriceNotEqualTo(BigDecimal value) {
            addCriterion("VIDEO_PRICE <>", value, "videoPrice");
            return (Criteria) this;
        }

        public Criteria andVideoPriceGreaterThan(BigDecimal value) {
            addCriterion("VIDEO_PRICE >", value, "videoPrice");
            return (Criteria) this;
        }

        public Criteria andVideoPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("VIDEO_PRICE >=", value, "videoPrice");
            return (Criteria) this;
        }

        public Criteria andVideoPriceLessThan(BigDecimal value) {
            addCriterion("VIDEO_PRICE <", value, "videoPrice");
            return (Criteria) this;
        }

        public Criteria andVideoPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("VIDEO_PRICE <=", value, "videoPrice");
            return (Criteria) this;
        }

        public Criteria andVideoPriceIn(List<BigDecimal> values) {
            addCriterion("VIDEO_PRICE in", values, "videoPrice");
            return (Criteria) this;
        }

        public Criteria andVideoPriceNotIn(List<BigDecimal> values) {
            addCriterion("VIDEO_PRICE not in", values, "videoPrice");
            return (Criteria) this;
        }

        public Criteria andVideoPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VIDEO_PRICE between", value1, value2, "videoPrice");
            return (Criteria) this;
        }

        public Criteria andVideoPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VIDEO_PRICE not between", value1, value2, "videoPrice");
            return (Criteria) this;
        }

        public Criteria andIntendedForIsNull() {
            addCriterion("INTENDED_FOR is null");
            return (Criteria) this;
        }

        public Criteria andIntendedForIsNotNull() {
            addCriterion("INTENDED_FOR is not null");
            return (Criteria) this;
        }

        public Criteria andIntendedForEqualTo(String value) {
            addCriterion("INTENDED_FOR =", value, "intendedFor");
            return (Criteria) this;
        }

        public Criteria andIntendedForNotEqualTo(String value) {
            addCriterion("INTENDED_FOR <>", value, "intendedFor");
            return (Criteria) this;
        }

        public Criteria andIntendedForGreaterThan(String value) {
            addCriterion("INTENDED_FOR >", value, "intendedFor");
            return (Criteria) this;
        }

        public Criteria andIntendedForGreaterThanOrEqualTo(String value) {
            addCriterion("INTENDED_FOR >=", value, "intendedFor");
            return (Criteria) this;
        }

        public Criteria andIntendedForLessThan(String value) {
            addCriterion("INTENDED_FOR <", value, "intendedFor");
            return (Criteria) this;
        }

        public Criteria andIntendedForLessThanOrEqualTo(String value) {
            addCriterion("INTENDED_FOR <=", value, "intendedFor");
            return (Criteria) this;
        }

        public Criteria andIntendedForLike(String value) {
            addCriterion("INTENDED_FOR like", value, "intendedFor");
            return (Criteria) this;
        }

        public Criteria andIntendedForNotLike(String value) {
            addCriterion("INTENDED_FOR not like", value, "intendedFor");
            return (Criteria) this;
        }

        public Criteria andIntendedForIn(List<String> values) {
            addCriterion("INTENDED_FOR in", values, "intendedFor");
            return (Criteria) this;
        }

        public Criteria andIntendedForNotIn(List<String> values) {
            addCriterion("INTENDED_FOR not in", values, "intendedFor");
            return (Criteria) this;
        }

        public Criteria andIntendedForBetween(String value1, String value2) {
            addCriterion("INTENDED_FOR between", value1, value2, "intendedFor");
            return (Criteria) this;
        }

        public Criteria andIntendedForNotBetween(String value1, String value2) {
            addCriterion("INTENDED_FOR not between", value1, value2, "intendedFor");
            return (Criteria) this;
        }

        public Criteria andPreknowledgeIsNull() {
            addCriterion("PREKNOWLEDGE is null");
            return (Criteria) this;
        }

        public Criteria andPreknowledgeIsNotNull() {
            addCriterion("PREKNOWLEDGE is not null");
            return (Criteria) this;
        }

        public Criteria andPreknowledgeEqualTo(String value) {
            addCriterion("PREKNOWLEDGE =", value, "preknowledge");
            return (Criteria) this;
        }

        public Criteria andPreknowledgeNotEqualTo(String value) {
            addCriterion("PREKNOWLEDGE <>", value, "preknowledge");
            return (Criteria) this;
        }

        public Criteria andPreknowledgeGreaterThan(String value) {
            addCriterion("PREKNOWLEDGE >", value, "preknowledge");
            return (Criteria) this;
        }

        public Criteria andPreknowledgeGreaterThanOrEqualTo(String value) {
            addCriterion("PREKNOWLEDGE >=", value, "preknowledge");
            return (Criteria) this;
        }

        public Criteria andPreknowledgeLessThan(String value) {
            addCriterion("PREKNOWLEDGE <", value, "preknowledge");
            return (Criteria) this;
        }

        public Criteria andPreknowledgeLessThanOrEqualTo(String value) {
            addCriterion("PREKNOWLEDGE <=", value, "preknowledge");
            return (Criteria) this;
        }

        public Criteria andPreknowledgeLike(String value) {
            addCriterion("PREKNOWLEDGE like", value, "preknowledge");
            return (Criteria) this;
        }

        public Criteria andPreknowledgeNotLike(String value) {
            addCriterion("PREKNOWLEDGE not like", value, "preknowledge");
            return (Criteria) this;
        }

        public Criteria andPreknowledgeIn(List<String> values) {
            addCriterion("PREKNOWLEDGE in", values, "preknowledge");
            return (Criteria) this;
        }

        public Criteria andPreknowledgeNotIn(List<String> values) {
            addCriterion("PREKNOWLEDGE not in", values, "preknowledge");
            return (Criteria) this;
        }

        public Criteria andPreknowledgeBetween(String value1, String value2) {
            addCriterion("PREKNOWLEDGE between", value1, value2, "preknowledge");
            return (Criteria) this;
        }

        public Criteria andPreknowledgeNotBetween(String value1, String value2) {
            addCriterion("PREKNOWLEDGE not between", value1, value2, "preknowledge");
            return (Criteria) this;
        }

        public Criteria andLecturerNameIsNull() {
            addCriterion("LECTURER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLecturerNameIsNotNull() {
            addCriterion("LECTURER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerNameEqualTo(String value) {
            addCriterion("LECTURER_NAME =", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameNotEqualTo(String value) {
            addCriterion("LECTURER_NAME <>", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameGreaterThan(String value) {
            addCriterion("LECTURER_NAME >", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURER_NAME >=", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameLessThan(String value) {
            addCriterion("LECTURER_NAME <", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameLessThanOrEqualTo(String value) {
            addCriterion("LECTURER_NAME <=", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameLike(String value) {
            addCriterion("LECTURER_NAME like", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameNotLike(String value) {
            addCriterion("LECTURER_NAME not like", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameIn(List<String> values) {
            addCriterion("LECTURER_NAME in", values, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameNotIn(List<String> values) {
            addCriterion("LECTURER_NAME not in", values, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameBetween(String value1, String value2) {
            addCriterion("LECTURER_NAME between", value1, value2, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameNotBetween(String value1, String value2) {
            addCriterion("LECTURER_NAME not between", value1, value2, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerCompanyIsNull() {
            addCriterion("LECTURER_COMPANY is null");
            return (Criteria) this;
        }

        public Criteria andLecturerCompanyIsNotNull() {
            addCriterion("LECTURER_COMPANY is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerCompanyEqualTo(String value) {
            addCriterion("LECTURER_COMPANY =", value, "lecturerCompany");
            return (Criteria) this;
        }

        public Criteria andLecturerCompanyNotEqualTo(String value) {
            addCriterion("LECTURER_COMPANY <>", value, "lecturerCompany");
            return (Criteria) this;
        }

        public Criteria andLecturerCompanyGreaterThan(String value) {
            addCriterion("LECTURER_COMPANY >", value, "lecturerCompany");
            return (Criteria) this;
        }

        public Criteria andLecturerCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURER_COMPANY >=", value, "lecturerCompany");
            return (Criteria) this;
        }

        public Criteria andLecturerCompanyLessThan(String value) {
            addCriterion("LECTURER_COMPANY <", value, "lecturerCompany");
            return (Criteria) this;
        }

        public Criteria andLecturerCompanyLessThanOrEqualTo(String value) {
            addCriterion("LECTURER_COMPANY <=", value, "lecturerCompany");
            return (Criteria) this;
        }

        public Criteria andLecturerCompanyLike(String value) {
            addCriterion("LECTURER_COMPANY like", value, "lecturerCompany");
            return (Criteria) this;
        }

        public Criteria andLecturerCompanyNotLike(String value) {
            addCriterion("LECTURER_COMPANY not like", value, "lecturerCompany");
            return (Criteria) this;
        }

        public Criteria andLecturerCompanyIn(List<String> values) {
            addCriterion("LECTURER_COMPANY in", values, "lecturerCompany");
            return (Criteria) this;
        }

        public Criteria andLecturerCompanyNotIn(List<String> values) {
            addCriterion("LECTURER_COMPANY not in", values, "lecturerCompany");
            return (Criteria) this;
        }

        public Criteria andLecturerCompanyBetween(String value1, String value2) {
            addCriterion("LECTURER_COMPANY between", value1, value2, "lecturerCompany");
            return (Criteria) this;
        }

        public Criteria andLecturerCompanyNotBetween(String value1, String value2) {
            addCriterion("LECTURER_COMPANY not between", value1, value2, "lecturerCompany");
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