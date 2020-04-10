package com.sayee.sxsy.newModules.office.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysOfficeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysOfficeExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("PARENT_ID =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("PARENT_ID <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("PARENT_ID >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ID >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("PARENT_ID <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ID <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("PARENT_ID like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("PARENT_ID not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("PARENT_ID in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("PARENT_ID not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("PARENT_ID between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("PARENT_ID not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdsIsNull() {
            addCriterion("PARENT_IDS is null");
            return (Criteria) this;
        }

        public Criteria andParentIdsIsNotNull() {
            addCriterion("PARENT_IDS is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdsEqualTo(String value) {
            addCriterion("PARENT_IDS =", value, "parentIds");
            return (Criteria) this;
        }

        public Criteria andParentIdsNotEqualTo(String value) {
            addCriterion("PARENT_IDS <>", value, "parentIds");
            return (Criteria) this;
        }

        public Criteria andParentIdsGreaterThan(String value) {
            addCriterion("PARENT_IDS >", value, "parentIds");
            return (Criteria) this;
        }

        public Criteria andParentIdsGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_IDS >=", value, "parentIds");
            return (Criteria) this;
        }

        public Criteria andParentIdsLessThan(String value) {
            addCriterion("PARENT_IDS <", value, "parentIds");
            return (Criteria) this;
        }

        public Criteria andParentIdsLessThanOrEqualTo(String value) {
            addCriterion("PARENT_IDS <=", value, "parentIds");
            return (Criteria) this;
        }

        public Criteria andParentIdsLike(String value) {
            addCriterion("PARENT_IDS like", value, "parentIds");
            return (Criteria) this;
        }

        public Criteria andParentIdsNotLike(String value) {
            addCriterion("PARENT_IDS not like", value, "parentIds");
            return (Criteria) this;
        }

        public Criteria andParentIdsIn(List<String> values) {
            addCriterion("PARENT_IDS in", values, "parentIds");
            return (Criteria) this;
        }

        public Criteria andParentIdsNotIn(List<String> values) {
            addCriterion("PARENT_IDS not in", values, "parentIds");
            return (Criteria) this;
        }

        public Criteria andParentIdsBetween(String value1, String value2) {
            addCriterion("PARENT_IDS between", value1, value2, "parentIds");
            return (Criteria) this;
        }

        public Criteria andParentIdsNotBetween(String value1, String value2) {
            addCriterion("PARENT_IDS not between", value1, value2, "parentIds");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("SORT is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("SORT is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Long value) {
            addCriterion("SORT =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Long value) {
            addCriterion("SORT <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Long value) {
            addCriterion("SORT >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Long value) {
            addCriterion("SORT >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Long value) {
            addCriterion("SORT <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Long value) {
            addCriterion("SORT <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Long> values) {
            addCriterion("SORT in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Long> values) {
            addCriterion("SORT not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Long value1, Long value2) {
            addCriterion("SORT between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Long value1, Long value2) {
            addCriterion("SORT not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNull() {
            addCriterion("AREA_ID is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("AREA_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(String value) {
            addCriterion("AREA_ID =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(String value) {
            addCriterion("AREA_ID <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(String value) {
            addCriterion("AREA_ID >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("AREA_ID >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(String value) {
            addCriterion("AREA_ID <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(String value) {
            addCriterion("AREA_ID <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLike(String value) {
            addCriterion("AREA_ID like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotLike(String value) {
            addCriterion("AREA_ID not like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<String> values) {
            addCriterion("AREA_ID in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<String> values) {
            addCriterion("AREA_ID not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(String value1, String value2) {
            addCriterion("AREA_ID between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(String value1, String value2) {
            addCriterion("AREA_ID not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andOfficeTypeIsNull() {
            addCriterion("OFFICE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOfficeTypeIsNotNull() {
            addCriterion("OFFICE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeTypeEqualTo(String value) {
            addCriterion("OFFICE_TYPE =", value, "officeType");
            return (Criteria) this;
        }

        public Criteria andOfficeTypeNotEqualTo(String value) {
            addCriterion("OFFICE_TYPE <>", value, "officeType");
            return (Criteria) this;
        }

        public Criteria andOfficeTypeGreaterThan(String value) {
            addCriterion("OFFICE_TYPE >", value, "officeType");
            return (Criteria) this;
        }

        public Criteria andOfficeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("OFFICE_TYPE >=", value, "officeType");
            return (Criteria) this;
        }

        public Criteria andOfficeTypeLessThan(String value) {
            addCriterion("OFFICE_TYPE <", value, "officeType");
            return (Criteria) this;
        }

        public Criteria andOfficeTypeLessThanOrEqualTo(String value) {
            addCriterion("OFFICE_TYPE <=", value, "officeType");
            return (Criteria) this;
        }

        public Criteria andOfficeTypeLike(String value) {
            addCriterion("OFFICE_TYPE like", value, "officeType");
            return (Criteria) this;
        }

        public Criteria andOfficeTypeNotLike(String value) {
            addCriterion("OFFICE_TYPE not like", value, "officeType");
            return (Criteria) this;
        }

        public Criteria andOfficeTypeIn(List<String> values) {
            addCriterion("OFFICE_TYPE in", values, "officeType");
            return (Criteria) this;
        }

        public Criteria andOfficeTypeNotIn(List<String> values) {
            addCriterion("OFFICE_TYPE not in", values, "officeType");
            return (Criteria) this;
        }

        public Criteria andOfficeTypeBetween(String value1, String value2) {
            addCriterion("OFFICE_TYPE between", value1, value2, "officeType");
            return (Criteria) this;
        }

        public Criteria andOfficeTypeNotBetween(String value1, String value2) {
            addCriterion("OFFICE_TYPE not between", value1, value2, "officeType");
            return (Criteria) this;
        }

        public Criteria andBedsIsNull() {
            addCriterion("BEDS is null");
            return (Criteria) this;
        }

        public Criteria andBedsIsNotNull() {
            addCriterion("BEDS is not null");
            return (Criteria) this;
        }

        public Criteria andBedsEqualTo(String value) {
            addCriterion("BEDS =", value, "beds");
            return (Criteria) this;
        }

        public Criteria andBedsNotEqualTo(String value) {
            addCriterion("BEDS <>", value, "beds");
            return (Criteria) this;
        }

        public Criteria andBedsGreaterThan(String value) {
            addCriterion("BEDS >", value, "beds");
            return (Criteria) this;
        }

        public Criteria andBedsGreaterThanOrEqualTo(String value) {
            addCriterion("BEDS >=", value, "beds");
            return (Criteria) this;
        }

        public Criteria andBedsLessThan(String value) {
            addCriterion("BEDS <", value, "beds");
            return (Criteria) this;
        }

        public Criteria andBedsLessThanOrEqualTo(String value) {
            addCriterion("BEDS <=", value, "beds");
            return (Criteria) this;
        }

        public Criteria andBedsLike(String value) {
            addCriterion("BEDS like", value, "beds");
            return (Criteria) this;
        }

        public Criteria andBedsNotLike(String value) {
            addCriterion("BEDS not like", value, "beds");
            return (Criteria) this;
        }

        public Criteria andBedsIn(List<String> values) {
            addCriterion("BEDS in", values, "beds");
            return (Criteria) this;
        }

        public Criteria andBedsNotIn(List<String> values) {
            addCriterion("BEDS not in", values, "beds");
            return (Criteria) this;
        }

        public Criteria andBedsBetween(String value1, String value2) {
            addCriterion("BEDS between", value1, value2, "beds");
            return (Criteria) this;
        }

        public Criteria andBedsNotBetween(String value1, String value2) {
            addCriterion("BEDS not between", value1, value2, "beds");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");
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

        public Criteria andGradeIsNull() {
            addCriterion("GRADE is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(String value) {
            addCriterion("GRADE =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(String value) {
            addCriterion("GRADE <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(String value) {
            addCriterion("GRADE >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(String value) {
            addCriterion("GRADE >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(String value) {
            addCriterion("GRADE <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(String value) {
            addCriterion("GRADE <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLike(String value) {
            addCriterion("GRADE like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotLike(String value) {
            addCriterion("GRADE not like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<String> values) {
            addCriterion("GRADE in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<String> values) {
            addCriterion("GRADE not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(String value1, String value2) {
            addCriterion("GRADE between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(String value1, String value2) {
            addCriterion("GRADE not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("ADDRESS like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("ADDRESS not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andZipCodeIsNull() {
            addCriterion("ZIP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andZipCodeIsNotNull() {
            addCriterion("ZIP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andZipCodeEqualTo(String value) {
            addCriterion("ZIP_CODE =", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotEqualTo(String value) {
            addCriterion("ZIP_CODE <>", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeGreaterThan(String value) {
            addCriterion("ZIP_CODE >", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ZIP_CODE >=", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLessThan(String value) {
            addCriterion("ZIP_CODE <", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLessThanOrEqualTo(String value) {
            addCriterion("ZIP_CODE <=", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLike(String value) {
            addCriterion("ZIP_CODE like", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotLike(String value) {
            addCriterion("ZIP_CODE not like", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeIn(List<String> values) {
            addCriterion("ZIP_CODE in", values, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotIn(List<String> values) {
            addCriterion("ZIP_CODE not in", values, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeBetween(String value1, String value2) {
            addCriterion("ZIP_CODE between", value1, value2, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotBetween(String value1, String value2) {
            addCriterion("ZIP_CODE not between", value1, value2, "zipCode");
            return (Criteria) this;
        }

        public Criteria andMasterIsNull() {
            addCriterion("MASTER is null");
            return (Criteria) this;
        }

        public Criteria andMasterIsNotNull() {
            addCriterion("MASTER is not null");
            return (Criteria) this;
        }

        public Criteria andMasterEqualTo(String value) {
            addCriterion("MASTER =", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterNotEqualTo(String value) {
            addCriterion("MASTER <>", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterGreaterThan(String value) {
            addCriterion("MASTER >", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER >=", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterLessThan(String value) {
            addCriterion("MASTER <", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterLessThanOrEqualTo(String value) {
            addCriterion("MASTER <=", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterLike(String value) {
            addCriterion("MASTER like", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterNotLike(String value) {
            addCriterion("MASTER not like", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterIn(List<String> values) {
            addCriterion("MASTER in", values, "master");
            return (Criteria) this;
        }

        public Criteria andMasterNotIn(List<String> values) {
            addCriterion("MASTER not in", values, "master");
            return (Criteria) this;
        }

        public Criteria andMasterBetween(String value1, String value2) {
            addCriterion("MASTER between", value1, value2, "master");
            return (Criteria) this;
        }

        public Criteria andMasterNotBetween(String value1, String value2) {
            addCriterion("MASTER not between", value1, value2, "master");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("PHONE is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("PHONE =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("PHONE <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("PHONE >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("PHONE <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("PHONE <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("PHONE like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("PHONE not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("PHONE in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("PHONE not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("PHONE between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("PHONE not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andFaxIsNull() {
            addCriterion("FAX is null");
            return (Criteria) this;
        }

        public Criteria andFaxIsNotNull() {
            addCriterion("FAX is not null");
            return (Criteria) this;
        }

        public Criteria andFaxEqualTo(String value) {
            addCriterion("FAX =", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotEqualTo(String value) {
            addCriterion("FAX <>", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThan(String value) {
            addCriterion("FAX >", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThanOrEqualTo(String value) {
            addCriterion("FAX >=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThan(String value) {
            addCriterion("FAX <", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThanOrEqualTo(String value) {
            addCriterion("FAX <=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLike(String value) {
            addCriterion("FAX like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotLike(String value) {
            addCriterion("FAX not like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxIn(List<String> values) {
            addCriterion("FAX in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotIn(List<String> values) {
            addCriterion("FAX not in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxBetween(String value1, String value2) {
            addCriterion("FAX between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotBetween(String value1, String value2) {
            addCriterion("FAX not between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIsNull() {
            addCriterion("HOSPITAL_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIsNotNull() {
            addCriterion("HOSPITAL_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeEqualTo(String value) {
            addCriterion("HOSPITAL_GRADE =", value, "hospitalGrade");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNotEqualTo(String value) {
            addCriterion("HOSPITAL_GRADE <>", value, "hospitalGrade");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeGreaterThan(String value) {
            addCriterion("HOSPITAL_GRADE >", value, "hospitalGrade");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_GRADE >=", value, "hospitalGrade");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeLessThan(String value) {
            addCriterion("HOSPITAL_GRADE <", value, "hospitalGrade");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_GRADE <=", value, "hospitalGrade");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeLike(String value) {
            addCriterion("HOSPITAL_GRADE like", value, "hospitalGrade");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNotLike(String value) {
            addCriterion("HOSPITAL_GRADE not like", value, "hospitalGrade");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIn(List<String> values) {
            addCriterion("HOSPITAL_GRADE in", values, "hospitalGrade");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNotIn(List<String> values) {
            addCriterion("HOSPITAL_GRADE not in", values, "hospitalGrade");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeBetween(String value1, String value2) {
            addCriterion("HOSPITAL_GRADE between", value1, value2, "hospitalGrade");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_GRADE not between", value1, value2, "hospitalGrade");
            return (Criteria) this;
        }

        public Criteria andUseableIsNull() {
            addCriterion("USEABLE is null");
            return (Criteria) this;
        }

        public Criteria andUseableIsNotNull() {
            addCriterion("USEABLE is not null");
            return (Criteria) this;
        }

        public Criteria andUseableEqualTo(String value) {
            addCriterion("USEABLE =", value, "useable");
            return (Criteria) this;
        }

        public Criteria andUseableNotEqualTo(String value) {
            addCriterion("USEABLE <>", value, "useable");
            return (Criteria) this;
        }

        public Criteria andUseableGreaterThan(String value) {
            addCriterion("USEABLE >", value, "useable");
            return (Criteria) this;
        }

        public Criteria andUseableGreaterThanOrEqualTo(String value) {
            addCriterion("USEABLE >=", value, "useable");
            return (Criteria) this;
        }

        public Criteria andUseableLessThan(String value) {
            addCriterion("USEABLE <", value, "useable");
            return (Criteria) this;
        }

        public Criteria andUseableLessThanOrEqualTo(String value) {
            addCriterion("USEABLE <=", value, "useable");
            return (Criteria) this;
        }

        public Criteria andUseableLike(String value) {
            addCriterion("USEABLE like", value, "useable");
            return (Criteria) this;
        }

        public Criteria andUseableNotLike(String value) {
            addCriterion("USEABLE not like", value, "useable");
            return (Criteria) this;
        }

        public Criteria andUseableIn(List<String> values) {
            addCriterion("USEABLE in", values, "useable");
            return (Criteria) this;
        }

        public Criteria andUseableNotIn(List<String> values) {
            addCriterion("USEABLE not in", values, "useable");
            return (Criteria) this;
        }

        public Criteria andUseableBetween(String value1, String value2) {
            addCriterion("USEABLE between", value1, value2, "useable");
            return (Criteria) this;
        }

        public Criteria andUseableNotBetween(String value1, String value2) {
            addCriterion("USEABLE not between", value1, value2, "useable");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeIsNull() {
            addCriterion("LEGAL_REPRESENTATIVE is null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeIsNotNull() {
            addCriterion("LEGAL_REPRESENTATIVE is not null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeEqualTo(String value) {
            addCriterion("LEGAL_REPRESENTATIVE =", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotEqualTo(String value) {
            addCriterion("LEGAL_REPRESENTATIVE <>", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeGreaterThan(String value) {
            addCriterion("LEGAL_REPRESENTATIVE >", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeGreaterThanOrEqualTo(String value) {
            addCriterion("LEGAL_REPRESENTATIVE >=", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLessThan(String value) {
            addCriterion("LEGAL_REPRESENTATIVE <", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLessThanOrEqualTo(String value) {
            addCriterion("LEGAL_REPRESENTATIVE <=", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLike(String value) {
            addCriterion("LEGAL_REPRESENTATIVE like", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotLike(String value) {
            addCriterion("LEGAL_REPRESENTATIVE not like", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeIn(List<String> values) {
            addCriterion("LEGAL_REPRESENTATIVE in", values, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotIn(List<String> values) {
            addCriterion("LEGAL_REPRESENTATIVE not in", values, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeBetween(String value1, String value2) {
            addCriterion("LEGAL_REPRESENTATIVE between", value1, value2, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotBetween(String value1, String value2) {
            addCriterion("LEGAL_REPRESENTATIVE not between", value1, value2, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andRepresentPhoneIsNull() {
            addCriterion("represent_phone is null");
            return (Criteria) this;
        }

        public Criteria andRepresentPhoneIsNotNull() {
            addCriterion("represent_phone is not null");
            return (Criteria) this;
        }

        public Criteria andRepresentPhoneEqualTo(String value) {
            addCriterion("represent_phone =", value, "representPhone");
            return (Criteria) this;
        }

        public Criteria andRepresentPhoneNotEqualTo(String value) {
            addCriterion("represent_phone <>", value, "representPhone");
            return (Criteria) this;
        }

        public Criteria andRepresentPhoneGreaterThan(String value) {
            addCriterion("represent_phone >", value, "representPhone");
            return (Criteria) this;
        }

        public Criteria andRepresentPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("represent_phone >=", value, "representPhone");
            return (Criteria) this;
        }

        public Criteria andRepresentPhoneLessThan(String value) {
            addCriterion("represent_phone <", value, "representPhone");
            return (Criteria) this;
        }

        public Criteria andRepresentPhoneLessThanOrEqualTo(String value) {
            addCriterion("represent_phone <=", value, "representPhone");
            return (Criteria) this;
        }

        public Criteria andRepresentPhoneLike(String value) {
            addCriterion("represent_phone like", value, "representPhone");
            return (Criteria) this;
        }

        public Criteria andRepresentPhoneNotLike(String value) {
            addCriterion("represent_phone not like", value, "representPhone");
            return (Criteria) this;
        }

        public Criteria andRepresentPhoneIn(List<String> values) {
            addCriterion("represent_phone in", values, "representPhone");
            return (Criteria) this;
        }

        public Criteria andRepresentPhoneNotIn(List<String> values) {
            addCriterion("represent_phone not in", values, "representPhone");
            return (Criteria) this;
        }

        public Criteria andRepresentPhoneBetween(String value1, String value2) {
            addCriterion("represent_phone between", value1, value2, "representPhone");
            return (Criteria) this;
        }

        public Criteria andRepresentPhoneNotBetween(String value1, String value2) {
            addCriterion("represent_phone not between", value1, value2, "representPhone");
            return (Criteria) this;
        }

        public Criteria andDirectorChargeIsNull() {
            addCriterion("director_charge is null");
            return (Criteria) this;
        }

        public Criteria andDirectorChargeIsNotNull() {
            addCriterion("director_charge is not null");
            return (Criteria) this;
        }

        public Criteria andDirectorChargeEqualTo(String value) {
            addCriterion("director_charge =", value, "directorCharge");
            return (Criteria) this;
        }

        public Criteria andDirectorChargeNotEqualTo(String value) {
            addCriterion("director_charge <>", value, "directorCharge");
            return (Criteria) this;
        }

        public Criteria andDirectorChargeGreaterThan(String value) {
            addCriterion("director_charge >", value, "directorCharge");
            return (Criteria) this;
        }

        public Criteria andDirectorChargeGreaterThanOrEqualTo(String value) {
            addCriterion("director_charge >=", value, "directorCharge");
            return (Criteria) this;
        }

        public Criteria andDirectorChargeLessThan(String value) {
            addCriterion("director_charge <", value, "directorCharge");
            return (Criteria) this;
        }

        public Criteria andDirectorChargeLessThanOrEqualTo(String value) {
            addCriterion("director_charge <=", value, "directorCharge");
            return (Criteria) this;
        }

        public Criteria andDirectorChargeLike(String value) {
            addCriterion("director_charge like", value, "directorCharge");
            return (Criteria) this;
        }

        public Criteria andDirectorChargeNotLike(String value) {
            addCriterion("director_charge not like", value, "directorCharge");
            return (Criteria) this;
        }

        public Criteria andDirectorChargeIn(List<String> values) {
            addCriterion("director_charge in", values, "directorCharge");
            return (Criteria) this;
        }

        public Criteria andDirectorChargeNotIn(List<String> values) {
            addCriterion("director_charge not in", values, "directorCharge");
            return (Criteria) this;
        }

        public Criteria andDirectorChargeBetween(String value1, String value2) {
            addCriterion("director_charge between", value1, value2, "directorCharge");
            return (Criteria) this;
        }

        public Criteria andDirectorChargeNotBetween(String value1, String value2) {
            addCriterion("director_charge not between", value1, value2, "directorCharge");
            return (Criteria) this;
        }

        public Criteria andDirectorPhoneIsNull() {
            addCriterion("director_phone is null");
            return (Criteria) this;
        }

        public Criteria andDirectorPhoneIsNotNull() {
            addCriterion("director_phone is not null");
            return (Criteria) this;
        }

        public Criteria andDirectorPhoneEqualTo(String value) {
            addCriterion("director_phone =", value, "directorPhone");
            return (Criteria) this;
        }

        public Criteria andDirectorPhoneNotEqualTo(String value) {
            addCriterion("director_phone <>", value, "directorPhone");
            return (Criteria) this;
        }

        public Criteria andDirectorPhoneGreaterThan(String value) {
            addCriterion("director_phone >", value, "directorPhone");
            return (Criteria) this;
        }

        public Criteria andDirectorPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("director_phone >=", value, "directorPhone");
            return (Criteria) this;
        }

        public Criteria andDirectorPhoneLessThan(String value) {
            addCriterion("director_phone <", value, "directorPhone");
            return (Criteria) this;
        }

        public Criteria andDirectorPhoneLessThanOrEqualTo(String value) {
            addCriterion("director_phone <=", value, "directorPhone");
            return (Criteria) this;
        }

        public Criteria andDirectorPhoneLike(String value) {
            addCriterion("director_phone like", value, "directorPhone");
            return (Criteria) this;
        }

        public Criteria andDirectorPhoneNotLike(String value) {
            addCriterion("director_phone not like", value, "directorPhone");
            return (Criteria) this;
        }

        public Criteria andDirectorPhoneIn(List<String> values) {
            addCriterion("director_phone in", values, "directorPhone");
            return (Criteria) this;
        }

        public Criteria andDirectorPhoneNotIn(List<String> values) {
            addCriterion("director_phone not in", values, "directorPhone");
            return (Criteria) this;
        }

        public Criteria andDirectorPhoneBetween(String value1, String value2) {
            addCriterion("director_phone between", value1, value2, "directorPhone");
            return (Criteria) this;
        }

        public Criteria andDirectorPhoneNotBetween(String value1, String value2) {
            addCriterion("director_phone not between", value1, value2, "directorPhone");
            return (Criteria) this;
        }

        public Criteria andDisputeHeadIsNull() {
            addCriterion("dispute_head is null");
            return (Criteria) this;
        }

        public Criteria andDisputeHeadIsNotNull() {
            addCriterion("dispute_head is not null");
            return (Criteria) this;
        }

        public Criteria andDisputeHeadEqualTo(String value) {
            addCriterion("dispute_head =", value, "disputeHead");
            return (Criteria) this;
        }

        public Criteria andDisputeHeadNotEqualTo(String value) {
            addCriterion("dispute_head <>", value, "disputeHead");
            return (Criteria) this;
        }

        public Criteria andDisputeHeadGreaterThan(String value) {
            addCriterion("dispute_head >", value, "disputeHead");
            return (Criteria) this;
        }

        public Criteria andDisputeHeadGreaterThanOrEqualTo(String value) {
            addCriterion("dispute_head >=", value, "disputeHead");
            return (Criteria) this;
        }

        public Criteria andDisputeHeadLessThan(String value) {
            addCriterion("dispute_head <", value, "disputeHead");
            return (Criteria) this;
        }

        public Criteria andDisputeHeadLessThanOrEqualTo(String value) {
            addCriterion("dispute_head <=", value, "disputeHead");
            return (Criteria) this;
        }

        public Criteria andDisputeHeadLike(String value) {
            addCriterion("dispute_head like", value, "disputeHead");
            return (Criteria) this;
        }

        public Criteria andDisputeHeadNotLike(String value) {
            addCriterion("dispute_head not like", value, "disputeHead");
            return (Criteria) this;
        }

        public Criteria andDisputeHeadIn(List<String> values) {
            addCriterion("dispute_head in", values, "disputeHead");
            return (Criteria) this;
        }

        public Criteria andDisputeHeadNotIn(List<String> values) {
            addCriterion("dispute_head not in", values, "disputeHead");
            return (Criteria) this;
        }

        public Criteria andDisputeHeadBetween(String value1, String value2) {
            addCriterion("dispute_head between", value1, value2, "disputeHead");
            return (Criteria) this;
        }

        public Criteria andDisputeHeadNotBetween(String value1, String value2) {
            addCriterion("dispute_head not between", value1, value2, "disputeHead");
            return (Criteria) this;
        }

        public Criteria andDisputePhoneIsNull() {
            addCriterion("dispute_phone is null");
            return (Criteria) this;
        }

        public Criteria andDisputePhoneIsNotNull() {
            addCriterion("dispute_phone is not null");
            return (Criteria) this;
        }

        public Criteria andDisputePhoneEqualTo(String value) {
            addCriterion("dispute_phone =", value, "disputePhone");
            return (Criteria) this;
        }

        public Criteria andDisputePhoneNotEqualTo(String value) {
            addCriterion("dispute_phone <>", value, "disputePhone");
            return (Criteria) this;
        }

        public Criteria andDisputePhoneGreaterThan(String value) {
            addCriterion("dispute_phone >", value, "disputePhone");
            return (Criteria) this;
        }

        public Criteria andDisputePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("dispute_phone >=", value, "disputePhone");
            return (Criteria) this;
        }

        public Criteria andDisputePhoneLessThan(String value) {
            addCriterion("dispute_phone <", value, "disputePhone");
            return (Criteria) this;
        }

        public Criteria andDisputePhoneLessThanOrEqualTo(String value) {
            addCriterion("dispute_phone <=", value, "disputePhone");
            return (Criteria) this;
        }

        public Criteria andDisputePhoneLike(String value) {
            addCriterion("dispute_phone like", value, "disputePhone");
            return (Criteria) this;
        }

        public Criteria andDisputePhoneNotLike(String value) {
            addCriterion("dispute_phone not like", value, "disputePhone");
            return (Criteria) this;
        }

        public Criteria andDisputePhoneIn(List<String> values) {
            addCriterion("dispute_phone in", values, "disputePhone");
            return (Criteria) this;
        }

        public Criteria andDisputePhoneNotIn(List<String> values) {
            addCriterion("dispute_phone not in", values, "disputePhone");
            return (Criteria) this;
        }

        public Criteria andDisputePhoneBetween(String value1, String value2) {
            addCriterion("dispute_phone between", value1, value2, "disputePhone");
            return (Criteria) this;
        }

        public Criteria andDisputePhoneNotBetween(String value1, String value2) {
            addCriterion("dispute_phone not between", value1, value2, "disputePhone");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonIsNull() {
            addCriterion("PRIMARY_PERSON is null");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonIsNotNull() {
            addCriterion("PRIMARY_PERSON is not null");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonEqualTo(String value) {
            addCriterion("PRIMARY_PERSON =", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonNotEqualTo(String value) {
            addCriterion("PRIMARY_PERSON <>", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonGreaterThan(String value) {
            addCriterion("PRIMARY_PERSON >", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonGreaterThanOrEqualTo(String value) {
            addCriterion("PRIMARY_PERSON >=", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonLessThan(String value) {
            addCriterion("PRIMARY_PERSON <", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonLessThanOrEqualTo(String value) {
            addCriterion("PRIMARY_PERSON <=", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonLike(String value) {
            addCriterion("PRIMARY_PERSON like", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonNotLike(String value) {
            addCriterion("PRIMARY_PERSON not like", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonIn(List<String> values) {
            addCriterion("PRIMARY_PERSON in", values, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonNotIn(List<String> values) {
            addCriterion("PRIMARY_PERSON not in", values, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonBetween(String value1, String value2) {
            addCriterion("PRIMARY_PERSON between", value1, value2, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonNotBetween(String value1, String value2) {
            addCriterion("PRIMARY_PERSON not between", value1, value2, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonIsNull() {
            addCriterion("DEPUTY_PERSON is null");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonIsNotNull() {
            addCriterion("DEPUTY_PERSON is not null");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonEqualTo(String value) {
            addCriterion("DEPUTY_PERSON =", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonNotEqualTo(String value) {
            addCriterion("DEPUTY_PERSON <>", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonGreaterThan(String value) {
            addCriterion("DEPUTY_PERSON >", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonGreaterThanOrEqualTo(String value) {
            addCriterion("DEPUTY_PERSON >=", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonLessThan(String value) {
            addCriterion("DEPUTY_PERSON <", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonLessThanOrEqualTo(String value) {
            addCriterion("DEPUTY_PERSON <=", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonLike(String value) {
            addCriterion("DEPUTY_PERSON like", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonNotLike(String value) {
            addCriterion("DEPUTY_PERSON not like", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonIn(List<String> values) {
            addCriterion("DEPUTY_PERSON in", values, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonNotIn(List<String> values) {
            addCriterion("DEPUTY_PERSON not in", values, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonBetween(String value1, String value2) {
            addCriterion("DEPUTY_PERSON between", value1, value2, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonNotBetween(String value1, String value2) {
            addCriterion("DEPUTY_PERSON not between", value1, value2, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andIsInsuredIsNull() {
            addCriterion("is_insured is null");
            return (Criteria) this;
        }

        public Criteria andIsInsuredIsNotNull() {
            addCriterion("is_insured is not null");
            return (Criteria) this;
        }

        public Criteria andIsInsuredEqualTo(String value) {
            addCriterion("is_insured =", value, "isInsured");
            return (Criteria) this;
        }

        public Criteria andIsInsuredNotEqualTo(String value) {
            addCriterion("is_insured <>", value, "isInsured");
            return (Criteria) this;
        }

        public Criteria andIsInsuredGreaterThan(String value) {
            addCriterion("is_insured >", value, "isInsured");
            return (Criteria) this;
        }

        public Criteria andIsInsuredGreaterThanOrEqualTo(String value) {
            addCriterion("is_insured >=", value, "isInsured");
            return (Criteria) this;
        }

        public Criteria andIsInsuredLessThan(String value) {
            addCriterion("is_insured <", value, "isInsured");
            return (Criteria) this;
        }

        public Criteria andIsInsuredLessThanOrEqualTo(String value) {
            addCriterion("is_insured <=", value, "isInsured");
            return (Criteria) this;
        }

        public Criteria andIsInsuredLike(String value) {
            addCriterion("is_insured like", value, "isInsured");
            return (Criteria) this;
        }

        public Criteria andIsInsuredNotLike(String value) {
            addCriterion("is_insured not like", value, "isInsured");
            return (Criteria) this;
        }

        public Criteria andIsInsuredIn(List<String> values) {
            addCriterion("is_insured in", values, "isInsured");
            return (Criteria) this;
        }

        public Criteria andIsInsuredNotIn(List<String> values) {
            addCriterion("is_insured not in", values, "isInsured");
            return (Criteria) this;
        }

        public Criteria andIsInsuredBetween(String value1, String value2) {
            addCriterion("is_insured between", value1, value2, "isInsured");
            return (Criteria) this;
        }

        public Criteria andIsInsuredNotBetween(String value1, String value2) {
            addCriterion("is_insured not between", value1, value2, "isInsured");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIsNull() {
            addCriterion("INSURANCE_COMPANY is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIsNotNull() {
            addCriterion("INSURANCE_COMPANY is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyEqualTo(String value) {
            addCriterion("INSURANCE_COMPANY =", value, "insuranceCompany");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyNotEqualTo(String value) {
            addCriterion("INSURANCE_COMPANY <>", value, "insuranceCompany");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyGreaterThan(String value) {
            addCriterion("INSURANCE_COMPANY >", value, "insuranceCompany");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("INSURANCE_COMPANY >=", value, "insuranceCompany");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyLessThan(String value) {
            addCriterion("INSURANCE_COMPANY <", value, "insuranceCompany");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyLessThanOrEqualTo(String value) {
            addCriterion("INSURANCE_COMPANY <=", value, "insuranceCompany");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyLike(String value) {
            addCriterion("INSURANCE_COMPANY like", value, "insuranceCompany");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyNotLike(String value) {
            addCriterion("INSURANCE_COMPANY not like", value, "insuranceCompany");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIn(List<String> values) {
            addCriterion("INSURANCE_COMPANY in", values, "insuranceCompany");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyNotIn(List<String> values) {
            addCriterion("INSURANCE_COMPANY not in", values, "insuranceCompany");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyBetween(String value1, String value2) {
            addCriterion("INSURANCE_COMPANY between", value1, value2, "insuranceCompany");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyNotBetween(String value1, String value2) {
            addCriterion("INSURANCE_COMPANY not between", value1, value2, "insuranceCompany");
            return (Criteria) this;
        }

        public Criteria andInsuredTimeIsNull() {
            addCriterion("insured_time is null");
            return (Criteria) this;
        }

        public Criteria andInsuredTimeIsNotNull() {
            addCriterion("insured_time is not null");
            return (Criteria) this;
        }

        public Criteria andInsuredTimeEqualTo(String value) {
            addCriterion("insured_time =", value, "insuredTime");
            return (Criteria) this;
        }

        public Criteria andInsuredTimeNotEqualTo(String value) {
            addCriterion("insured_time <>", value, "insuredTime");
            return (Criteria) this;
        }

        public Criteria andInsuredTimeGreaterThan(String value) {
            addCriterion("insured_time >", value, "insuredTime");
            return (Criteria) this;
        }

        public Criteria andInsuredTimeGreaterThanOrEqualTo(String value) {
            addCriterion("insured_time >=", value, "insuredTime");
            return (Criteria) this;
        }

        public Criteria andInsuredTimeLessThan(String value) {
            addCriterion("insured_time <", value, "insuredTime");
            return (Criteria) this;
        }

        public Criteria andInsuredTimeLessThanOrEqualTo(String value) {
            addCriterion("insured_time <=", value, "insuredTime");
            return (Criteria) this;
        }

        public Criteria andInsuredTimeLike(String value) {
            addCriterion("insured_time like", value, "insuredTime");
            return (Criteria) this;
        }

        public Criteria andInsuredTimeNotLike(String value) {
            addCriterion("insured_time not like", value, "insuredTime");
            return (Criteria) this;
        }

        public Criteria andInsuredTimeIn(List<String> values) {
            addCriterion("insured_time in", values, "insuredTime");
            return (Criteria) this;
        }

        public Criteria andInsuredTimeNotIn(List<String> values) {
            addCriterion("insured_time not in", values, "insuredTime");
            return (Criteria) this;
        }

        public Criteria andInsuredTimeBetween(String value1, String value2) {
            addCriterion("insured_time between", value1, value2, "insuredTime");
            return (Criteria) this;
        }

        public Criteria andInsuredTimeNotBetween(String value1, String value2) {
            addCriterion("insured_time not between", value1, value2, "insuredTime");
            return (Criteria) this;
        }

        public Criteria andInsuredEndTimeIsNull() {
            addCriterion("insured_end_time is null");
            return (Criteria) this;
        }

        public Criteria andInsuredEndTimeIsNotNull() {
            addCriterion("insured_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andInsuredEndTimeEqualTo(String value) {
            addCriterion("insured_end_time =", value, "insuredEndTime");
            return (Criteria) this;
        }

        public Criteria andInsuredEndTimeNotEqualTo(String value) {
            addCriterion("insured_end_time <>", value, "insuredEndTime");
            return (Criteria) this;
        }

        public Criteria andInsuredEndTimeGreaterThan(String value) {
            addCriterion("insured_end_time >", value, "insuredEndTime");
            return (Criteria) this;
        }

        public Criteria andInsuredEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("insured_end_time >=", value, "insuredEndTime");
            return (Criteria) this;
        }

        public Criteria andInsuredEndTimeLessThan(String value) {
            addCriterion("insured_end_time <", value, "insuredEndTime");
            return (Criteria) this;
        }

        public Criteria andInsuredEndTimeLessThanOrEqualTo(String value) {
            addCriterion("insured_end_time <=", value, "insuredEndTime");
            return (Criteria) this;
        }

        public Criteria andInsuredEndTimeLike(String value) {
            addCriterion("insured_end_time like", value, "insuredEndTime");
            return (Criteria) this;
        }

        public Criteria andInsuredEndTimeNotLike(String value) {
            addCriterion("insured_end_time not like", value, "insuredEndTime");
            return (Criteria) this;
        }

        public Criteria andInsuredEndTimeIn(List<String> values) {
            addCriterion("insured_end_time in", values, "insuredEndTime");
            return (Criteria) this;
        }

        public Criteria andInsuredEndTimeNotIn(List<String> values) {
            addCriterion("insured_end_time not in", values, "insuredEndTime");
            return (Criteria) this;
        }

        public Criteria andInsuredEndTimeBetween(String value1, String value2) {
            addCriterion("insured_end_time between", value1, value2, "insuredEndTime");
            return (Criteria) this;
        }

        public Criteria andInsuredEndTimeNotBetween(String value1, String value2) {
            addCriterion("insured_end_time not between", value1, value2, "insuredEndTime");
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

        public Criteria andPolicyNumberIsNull() {
            addCriterion("POLICY_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andPolicyNumberIsNotNull() {
            addCriterion("POLICY_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyNumberEqualTo(String value) {
            addCriterion("POLICY_NUMBER =", value, "policyNumber");
            return (Criteria) this;
        }

        public Criteria andPolicyNumberNotEqualTo(String value) {
            addCriterion("POLICY_NUMBER <>", value, "policyNumber");
            return (Criteria) this;
        }

        public Criteria andPolicyNumberGreaterThan(String value) {
            addCriterion("POLICY_NUMBER >", value, "policyNumber");
            return (Criteria) this;
        }

        public Criteria andPolicyNumberGreaterThanOrEqualTo(String value) {
            addCriterion("POLICY_NUMBER >=", value, "policyNumber");
            return (Criteria) this;
        }

        public Criteria andPolicyNumberLessThan(String value) {
            addCriterion("POLICY_NUMBER <", value, "policyNumber");
            return (Criteria) this;
        }

        public Criteria andPolicyNumberLessThanOrEqualTo(String value) {
            addCriterion("POLICY_NUMBER <=", value, "policyNumber");
            return (Criteria) this;
        }

        public Criteria andPolicyNumberLike(String value) {
            addCriterion("POLICY_NUMBER like", value, "policyNumber");
            return (Criteria) this;
        }

        public Criteria andPolicyNumberNotLike(String value) {
            addCriterion("POLICY_NUMBER not like", value, "policyNumber");
            return (Criteria) this;
        }

        public Criteria andPolicyNumberIn(List<String> values) {
            addCriterion("POLICY_NUMBER in", values, "policyNumber");
            return (Criteria) this;
        }

        public Criteria andPolicyNumberNotIn(List<String> values) {
            addCriterion("POLICY_NUMBER not in", values, "policyNumber");
            return (Criteria) this;
        }

        public Criteria andPolicyNumberBetween(String value1, String value2) {
            addCriterion("POLICY_NUMBER between", value1, value2, "policyNumber");
            return (Criteria) this;
        }

        public Criteria andPolicyNumberNotBetween(String value1, String value2) {
            addCriterion("POLICY_NUMBER not between", value1, value2, "policyNumber");
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