package com.sayee.sxsy.newModules.middleutils.entity;

import java.util.ArrayList;
import java.util.List;

public class ComuserComplaintinfoidExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ComuserComplaintinfoidExample() {
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

        public Criteria andComuserIdIsNull() {
            addCriterion("COMUSER_ID is null");
            return (Criteria) this;
        }

        public Criteria andComuserIdIsNotNull() {
            addCriterion("COMUSER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andComuserIdEqualTo(String value) {
            addCriterion("COMUSER_ID =", value, "comuserId");
            return (Criteria) this;
        }

        public Criteria andComuserIdNotEqualTo(String value) {
            addCriterion("COMUSER_ID <>", value, "comuserId");
            return (Criteria) this;
        }

        public Criteria andComuserIdGreaterThan(String value) {
            addCriterion("COMUSER_ID >", value, "comuserId");
            return (Criteria) this;
        }

        public Criteria andComuserIdGreaterThanOrEqualTo(String value) {
            addCriterion("COMUSER_ID >=", value, "comuserId");
            return (Criteria) this;
        }

        public Criteria andComuserIdLessThan(String value) {
            addCriterion("COMUSER_ID <", value, "comuserId");
            return (Criteria) this;
        }

        public Criteria andComuserIdLessThanOrEqualTo(String value) {
            addCriterion("COMUSER_ID <=", value, "comuserId");
            return (Criteria) this;
        }

        public Criteria andComuserIdLike(String value) {
            addCriterion("COMUSER_ID like", value, "comuserId");
            return (Criteria) this;
        }

        public Criteria andComuserIdNotLike(String value) {
            addCriterion("COMUSER_ID not like", value, "comuserId");
            return (Criteria) this;
        }

        public Criteria andComuserIdIn(List<String> values) {
            addCriterion("COMUSER_ID in", values, "comuserId");
            return (Criteria) this;
        }

        public Criteria andComuserIdNotIn(List<String> values) {
            addCriterion("COMUSER_ID not in", values, "comuserId");
            return (Criteria) this;
        }

        public Criteria andComuserIdBetween(String value1, String value2) {
            addCriterion("COMUSER_ID between", value1, value2, "comuserId");
            return (Criteria) this;
        }

        public Criteria andComuserIdNotBetween(String value1, String value2) {
            addCriterion("COMUSER_ID not between", value1, value2, "comuserId");
            return (Criteria) this;
        }

        public Criteria andComplaintInfoIdIsNull() {
            addCriterion("COMPLAINT_INFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andComplaintInfoIdIsNotNull() {
            addCriterion("COMPLAINT_INFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintInfoIdEqualTo(String value) {
            addCriterion("COMPLAINT_INFO_ID =", value, "complaintInfoId");
            return (Criteria) this;
        }

        public Criteria andComplaintInfoIdNotEqualTo(String value) {
            addCriterion("COMPLAINT_INFO_ID <>", value, "complaintInfoId");
            return (Criteria) this;
        }

        public Criteria andComplaintInfoIdGreaterThan(String value) {
            addCriterion("COMPLAINT_INFO_ID >", value, "complaintInfoId");
            return (Criteria) this;
        }

        public Criteria andComplaintInfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLAINT_INFO_ID >=", value, "complaintInfoId");
            return (Criteria) this;
        }

        public Criteria andComplaintInfoIdLessThan(String value) {
            addCriterion("COMPLAINT_INFO_ID <", value, "complaintInfoId");
            return (Criteria) this;
        }

        public Criteria andComplaintInfoIdLessThanOrEqualTo(String value) {
            addCriterion("COMPLAINT_INFO_ID <=", value, "complaintInfoId");
            return (Criteria) this;
        }

        public Criteria andComplaintInfoIdLike(String value) {
            addCriterion("COMPLAINT_INFO_ID like", value, "complaintInfoId");
            return (Criteria) this;
        }

        public Criteria andComplaintInfoIdNotLike(String value) {
            addCriterion("COMPLAINT_INFO_ID not like", value, "complaintInfoId");
            return (Criteria) this;
        }

        public Criteria andComplaintInfoIdIn(List<String> values) {
            addCriterion("COMPLAINT_INFO_ID in", values, "complaintInfoId");
            return (Criteria) this;
        }

        public Criteria andComplaintInfoIdNotIn(List<String> values) {
            addCriterion("COMPLAINT_INFO_ID not in", values, "complaintInfoId");
            return (Criteria) this;
        }

        public Criteria andComplaintInfoIdBetween(String value1, String value2) {
            addCriterion("COMPLAINT_INFO_ID between", value1, value2, "complaintInfoId");
            return (Criteria) this;
        }

        public Criteria andComplaintInfoIdNotBetween(String value1, String value2) {
            addCriterion("COMPLAINT_INFO_ID not between", value1, value2, "complaintInfoId");
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