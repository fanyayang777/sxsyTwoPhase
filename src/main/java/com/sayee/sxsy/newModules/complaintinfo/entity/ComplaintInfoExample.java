package com.sayee.sxsy.newModules.complaintinfo.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComplaintInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ComplaintInfoExample() {
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

        public Criteria andComplaintIdIsNull() {
            addCriterion("COMPLAINT_ID is null");
            return (Criteria) this;
        }

        public Criteria andComplaintIdIsNotNull() {
            addCriterion("COMPLAINT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintIdEqualTo(String value) {
            addCriterion("COMPLAINT_ID =", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdNotEqualTo(String value) {
            addCriterion("COMPLAINT_ID <>", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdGreaterThan(String value) {
            addCriterion("COMPLAINT_ID >", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLAINT_ID >=", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdLessThan(String value) {
            addCriterion("COMPLAINT_ID <", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdLessThanOrEqualTo(String value) {
            addCriterion("COMPLAINT_ID <=", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdLike(String value) {
            addCriterion("COMPLAINT_ID like", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdNotLike(String value) {
            addCriterion("COMPLAINT_ID not like", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdIn(List<String> values) {
            addCriterion("COMPLAINT_ID in", values, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdNotIn(List<String> values) {
            addCriterion("COMPLAINT_ID not in", values, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdBetween(String value1, String value2) {
            addCriterion("COMPLAINT_ID between", value1, value2, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdNotBetween(String value1, String value2) {
            addCriterion("COMPLAINT_ID not between", value1, value2, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintMainIdIsNull() {
            addCriterion("COMPLAINT_MAIN_ID is null");
            return (Criteria) this;
        }

        public Criteria andComplaintMainIdIsNotNull() {
            addCriterion("COMPLAINT_MAIN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintMainIdEqualTo(String value) {
            addCriterion("COMPLAINT_MAIN_ID =", value, "complaintMainId");
            return (Criteria) this;
        }

        public Criteria andComplaintMainIdNotEqualTo(String value) {
            addCriterion("COMPLAINT_MAIN_ID <>", value, "complaintMainId");
            return (Criteria) this;
        }

        public Criteria andComplaintMainIdGreaterThan(String value) {
            addCriterion("COMPLAINT_MAIN_ID >", value, "complaintMainId");
            return (Criteria) this;
        }

        public Criteria andComplaintMainIdGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLAINT_MAIN_ID >=", value, "complaintMainId");
            return (Criteria) this;
        }

        public Criteria andComplaintMainIdLessThan(String value) {
            addCriterion("COMPLAINT_MAIN_ID <", value, "complaintMainId");
            return (Criteria) this;
        }

        public Criteria andComplaintMainIdLessThanOrEqualTo(String value) {
            addCriterion("COMPLAINT_MAIN_ID <=", value, "complaintMainId");
            return (Criteria) this;
        }

        public Criteria andComplaintMainIdLike(String value) {
            addCriterion("COMPLAINT_MAIN_ID like", value, "complaintMainId");
            return (Criteria) this;
        }

        public Criteria andComplaintMainIdNotLike(String value) {
            addCriterion("COMPLAINT_MAIN_ID not like", value, "complaintMainId");
            return (Criteria) this;
        }

        public Criteria andComplaintMainIdIn(List<String> values) {
            addCriterion("COMPLAINT_MAIN_ID in", values, "complaintMainId");
            return (Criteria) this;
        }

        public Criteria andComplaintMainIdNotIn(List<String> values) {
            addCriterion("COMPLAINT_MAIN_ID not in", values, "complaintMainId");
            return (Criteria) this;
        }

        public Criteria andComplaintMainIdBetween(String value1, String value2) {
            addCriterion("COMPLAINT_MAIN_ID between", value1, value2, "complaintMainId");
            return (Criteria) this;
        }

        public Criteria andComplaintMainIdNotBetween(String value1, String value2) {
            addCriterion("COMPLAINT_MAIN_ID not between", value1, value2, "complaintMainId");
            return (Criteria) this;
        }

        public Criteria andVisitorNameIsNull() {
            addCriterion("VISITOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andVisitorNameIsNotNull() {
            addCriterion("VISITOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andVisitorNameEqualTo(String value) {
            addCriterion("VISITOR_NAME =", value, "visitorName");
            return (Criteria) this;
        }

        public Criteria andVisitorNameNotEqualTo(String value) {
            addCriterion("VISITOR_NAME <>", value, "visitorName");
            return (Criteria) this;
        }

        public Criteria andVisitorNameGreaterThan(String value) {
            addCriterion("VISITOR_NAME >", value, "visitorName");
            return (Criteria) this;
        }

        public Criteria andVisitorNameGreaterThanOrEqualTo(String value) {
            addCriterion("VISITOR_NAME >=", value, "visitorName");
            return (Criteria) this;
        }

        public Criteria andVisitorNameLessThan(String value) {
            addCriterion("VISITOR_NAME <", value, "visitorName");
            return (Criteria) this;
        }

        public Criteria andVisitorNameLessThanOrEqualTo(String value) {
            addCriterion("VISITOR_NAME <=", value, "visitorName");
            return (Criteria) this;
        }

        public Criteria andVisitorNameLike(String value) {
            addCriterion("VISITOR_NAME like", value, "visitorName");
            return (Criteria) this;
        }

        public Criteria andVisitorNameNotLike(String value) {
            addCriterion("VISITOR_NAME not like", value, "visitorName");
            return (Criteria) this;
        }

        public Criteria andVisitorNameIn(List<String> values) {
            addCriterion("VISITOR_NAME in", values, "visitorName");
            return (Criteria) this;
        }

        public Criteria andVisitorNameNotIn(List<String> values) {
            addCriterion("VISITOR_NAME not in", values, "visitorName");
            return (Criteria) this;
        }

        public Criteria andVisitorNameBetween(String value1, String value2) {
            addCriterion("VISITOR_NAME between", value1, value2, "visitorName");
            return (Criteria) this;
        }

        public Criteria andVisitorNameNotBetween(String value1, String value2) {
            addCriterion("VISITOR_NAME not between", value1, value2, "visitorName");
            return (Criteria) this;
        }

        public Criteria andVisitorMobileIsNull() {
            addCriterion("VISITOR_MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andVisitorMobileIsNotNull() {
            addCriterion("VISITOR_MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andVisitorMobileEqualTo(String value) {
            addCriterion("VISITOR_MOBILE =", value, "visitorMobile");
            return (Criteria) this;
        }

        public Criteria andVisitorMobileNotEqualTo(String value) {
            addCriterion("VISITOR_MOBILE <>", value, "visitorMobile");
            return (Criteria) this;
        }

        public Criteria andVisitorMobileGreaterThan(String value) {
            addCriterion("VISITOR_MOBILE >", value, "visitorMobile");
            return (Criteria) this;
        }

        public Criteria andVisitorMobileGreaterThanOrEqualTo(String value) {
            addCriterion("VISITOR_MOBILE >=", value, "visitorMobile");
            return (Criteria) this;
        }

        public Criteria andVisitorMobileLessThan(String value) {
            addCriterion("VISITOR_MOBILE <", value, "visitorMobile");
            return (Criteria) this;
        }

        public Criteria andVisitorMobileLessThanOrEqualTo(String value) {
            addCriterion("VISITOR_MOBILE <=", value, "visitorMobile");
            return (Criteria) this;
        }

        public Criteria andVisitorMobileLike(String value) {
            addCriterion("VISITOR_MOBILE like", value, "visitorMobile");
            return (Criteria) this;
        }

        public Criteria andVisitorMobileNotLike(String value) {
            addCriterion("VISITOR_MOBILE not like", value, "visitorMobile");
            return (Criteria) this;
        }

        public Criteria andVisitorMobileIn(List<String> values) {
            addCriterion("VISITOR_MOBILE in", values, "visitorMobile");
            return (Criteria) this;
        }

        public Criteria andVisitorMobileNotIn(List<String> values) {
            addCriterion("VISITOR_MOBILE not in", values, "visitorMobile");
            return (Criteria) this;
        }

        public Criteria andVisitorMobileBetween(String value1, String value2) {
            addCriterion("VISITOR_MOBILE between", value1, value2, "visitorMobile");
            return (Criteria) this;
        }

        public Criteria andVisitorMobileNotBetween(String value1, String value2) {
            addCriterion("VISITOR_MOBILE not between", value1, value2, "visitorMobile");
            return (Criteria) this;
        }

        public Criteria andPatientRelationIsNull() {
            addCriterion("PATIENT_RELATION is null");
            return (Criteria) this;
        }

        public Criteria andPatientRelationIsNotNull() {
            addCriterion("PATIENT_RELATION is not null");
            return (Criteria) this;
        }

        public Criteria andPatientRelationEqualTo(String value) {
            addCriterion("PATIENT_RELATION =", value, "patientRelation");
            return (Criteria) this;
        }

        public Criteria andPatientRelationNotEqualTo(String value) {
            addCriterion("PATIENT_RELATION <>", value, "patientRelation");
            return (Criteria) this;
        }

        public Criteria andPatientRelationGreaterThan(String value) {
            addCriterion("PATIENT_RELATION >", value, "patientRelation");
            return (Criteria) this;
        }

        public Criteria andPatientRelationGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_RELATION >=", value, "patientRelation");
            return (Criteria) this;
        }

        public Criteria andPatientRelationLessThan(String value) {
            addCriterion("PATIENT_RELATION <", value, "patientRelation");
            return (Criteria) this;
        }

        public Criteria andPatientRelationLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_RELATION <=", value, "patientRelation");
            return (Criteria) this;
        }

        public Criteria andPatientRelationLike(String value) {
            addCriterion("PATIENT_RELATION like", value, "patientRelation");
            return (Criteria) this;
        }

        public Criteria andPatientRelationNotLike(String value) {
            addCriterion("PATIENT_RELATION not like", value, "patientRelation");
            return (Criteria) this;
        }

        public Criteria andPatientRelationIn(List<String> values) {
            addCriterion("PATIENT_RELATION in", values, "patientRelation");
            return (Criteria) this;
        }

        public Criteria andPatientRelationNotIn(List<String> values) {
            addCriterion("PATIENT_RELATION not in", values, "patientRelation");
            return (Criteria) this;
        }

        public Criteria andPatientRelationBetween(String value1, String value2) {
            addCriterion("PATIENT_RELATION between", value1, value2, "patientRelation");
            return (Criteria) this;
        }

        public Criteria andPatientRelationNotBetween(String value1, String value2) {
            addCriterion("PATIENT_RELATION not between", value1, value2, "patientRelation");
            return (Criteria) this;
        }

        public Criteria andVisitorNumberIsNull() {
            addCriterion("VISITOR_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andVisitorNumberIsNotNull() {
            addCriterion("VISITOR_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andVisitorNumberEqualTo(String value) {
            addCriterion("VISITOR_NUMBER =", value, "visitorNumber");
            return (Criteria) this;
        }

        public Criteria andVisitorNumberNotEqualTo(String value) {
            addCriterion("VISITOR_NUMBER <>", value, "visitorNumber");
            return (Criteria) this;
        }

        public Criteria andVisitorNumberGreaterThan(String value) {
            addCriterion("VISITOR_NUMBER >", value, "visitorNumber");
            return (Criteria) this;
        }

        public Criteria andVisitorNumberGreaterThanOrEqualTo(String value) {
            addCriterion("VISITOR_NUMBER >=", value, "visitorNumber");
            return (Criteria) this;
        }

        public Criteria andVisitorNumberLessThan(String value) {
            addCriterion("VISITOR_NUMBER <", value, "visitorNumber");
            return (Criteria) this;
        }

        public Criteria andVisitorNumberLessThanOrEqualTo(String value) {
            addCriterion("VISITOR_NUMBER <=", value, "visitorNumber");
            return (Criteria) this;
        }

        public Criteria andVisitorNumberLike(String value) {
            addCriterion("VISITOR_NUMBER like", value, "visitorNumber");
            return (Criteria) this;
        }

        public Criteria andVisitorNumberNotLike(String value) {
            addCriterion("VISITOR_NUMBER not like", value, "visitorNumber");
            return (Criteria) this;
        }

        public Criteria andVisitorNumberIn(List<String> values) {
            addCriterion("VISITOR_NUMBER in", values, "visitorNumber");
            return (Criteria) this;
        }

        public Criteria andVisitorNumberNotIn(List<String> values) {
            addCriterion("VISITOR_NUMBER not in", values, "visitorNumber");
            return (Criteria) this;
        }

        public Criteria andVisitorNumberBetween(String value1, String value2) {
            addCriterion("VISITOR_NUMBER between", value1, value2, "visitorNumber");
            return (Criteria) this;
        }

        public Criteria andVisitorNumberNotBetween(String value1, String value2) {
            addCriterion("VISITOR_NUMBER not between", value1, value2, "visitorNumber");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNull() {
            addCriterion("PATIENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNotNull() {
            addCriterion("PATIENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPatientNameEqualTo(String value) {
            addCriterion("PATIENT_NAME =", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotEqualTo(String value) {
            addCriterion("PATIENT_NAME <>", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThan(String value) {
            addCriterion("PATIENT_NAME >", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_NAME >=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThan(String value) {
            addCriterion("PATIENT_NAME <", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_NAME <=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLike(String value) {
            addCriterion("PATIENT_NAME like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotLike(String value) {
            addCriterion("PATIENT_NAME not like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameIn(List<String> values) {
            addCriterion("PATIENT_NAME in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotIn(List<String> values) {
            addCriterion("PATIENT_NAME not in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameBetween(String value1, String value2) {
            addCriterion("PATIENT_NAME between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotBetween(String value1, String value2) {
            addCriterion("PATIENT_NAME not between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientSexIsNull() {
            addCriterion("PATIENT_SEX is null");
            return (Criteria) this;
        }

        public Criteria andPatientSexIsNotNull() {
            addCriterion("PATIENT_SEX is not null");
            return (Criteria) this;
        }

        public Criteria andPatientSexEqualTo(String value) {
            addCriterion("PATIENT_SEX =", value, "patientSex");
            return (Criteria) this;
        }

        public Criteria andPatientSexNotEqualTo(String value) {
            addCriterion("PATIENT_SEX <>", value, "patientSex");
            return (Criteria) this;
        }

        public Criteria andPatientSexGreaterThan(String value) {
            addCriterion("PATIENT_SEX >", value, "patientSex");
            return (Criteria) this;
        }

        public Criteria andPatientSexGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_SEX >=", value, "patientSex");
            return (Criteria) this;
        }

        public Criteria andPatientSexLessThan(String value) {
            addCriterion("PATIENT_SEX <", value, "patientSex");
            return (Criteria) this;
        }

        public Criteria andPatientSexLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_SEX <=", value, "patientSex");
            return (Criteria) this;
        }

        public Criteria andPatientSexLike(String value) {
            addCriterion("PATIENT_SEX like", value, "patientSex");
            return (Criteria) this;
        }

        public Criteria andPatientSexNotLike(String value) {
            addCriterion("PATIENT_SEX not like", value, "patientSex");
            return (Criteria) this;
        }

        public Criteria andPatientSexIn(List<String> values) {
            addCriterion("PATIENT_SEX in", values, "patientSex");
            return (Criteria) this;
        }

        public Criteria andPatientSexNotIn(List<String> values) {
            addCriterion("PATIENT_SEX not in", values, "patientSex");
            return (Criteria) this;
        }

        public Criteria andPatientSexBetween(String value1, String value2) {
            addCriterion("PATIENT_SEX between", value1, value2, "patientSex");
            return (Criteria) this;
        }

        public Criteria andPatientSexNotBetween(String value1, String value2) {
            addCriterion("PATIENT_SEX not between", value1, value2, "patientSex");
            return (Criteria) this;
        }

        public Criteria andPatientAgeIsNull() {
            addCriterion("PATIENT_AGE is null");
            return (Criteria) this;
        }

        public Criteria andPatientAgeIsNotNull() {
            addCriterion("PATIENT_AGE is not null");
            return (Criteria) this;
        }

        public Criteria andPatientAgeEqualTo(Byte value) {
            addCriterion("PATIENT_AGE =", value, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeNotEqualTo(Byte value) {
            addCriterion("PATIENT_AGE <>", value, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeGreaterThan(Byte value) {
            addCriterion("PATIENT_AGE >", value, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeGreaterThanOrEqualTo(Byte value) {
            addCriterion("PATIENT_AGE >=", value, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeLessThan(Byte value) {
            addCriterion("PATIENT_AGE <", value, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeLessThanOrEqualTo(Byte value) {
            addCriterion("PATIENT_AGE <=", value, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeIn(List<Byte> values) {
            addCriterion("PATIENT_AGE in", values, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeNotIn(List<Byte> values) {
            addCriterion("PATIENT_AGE not in", values, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeBetween(Byte value1, Byte value2) {
            addCriterion("PATIENT_AGE between", value1, value2, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeNotBetween(Byte value1, Byte value2) {
            addCriterion("PATIENT_AGE not between", value1, value2, "patientAge");
            return (Criteria) this;
        }

        public Criteria andInvolveHospitalIsNull() {
            addCriterion("INVOLVE_HOSPITAL is null");
            return (Criteria) this;
        }

        public Criteria andInvolveHospitalIsNotNull() {
            addCriterion("INVOLVE_HOSPITAL is not null");
            return (Criteria) this;
        }

        public Criteria andInvolveHospitalEqualTo(String value) {
            addCriterion("INVOLVE_HOSPITAL =", value, "involveHospital");
            return (Criteria) this;
        }

        public Criteria andInvolveHospitalNotEqualTo(String value) {
            addCriterion("INVOLVE_HOSPITAL <>", value, "involveHospital");
            return (Criteria) this;
        }

        public Criteria andInvolveHospitalGreaterThan(String value) {
            addCriterion("INVOLVE_HOSPITAL >", value, "involveHospital");
            return (Criteria) this;
        }

        public Criteria andInvolveHospitalGreaterThanOrEqualTo(String value) {
            addCriterion("INVOLVE_HOSPITAL >=", value, "involveHospital");
            return (Criteria) this;
        }

        public Criteria andInvolveHospitalLessThan(String value) {
            addCriterion("INVOLVE_HOSPITAL <", value, "involveHospital");
            return (Criteria) this;
        }

        public Criteria andInvolveHospitalLessThanOrEqualTo(String value) {
            addCriterion("INVOLVE_HOSPITAL <=", value, "involveHospital");
            return (Criteria) this;
        }

        public Criteria andInvolveHospitalLike(String value) {
            addCriterion("INVOLVE_HOSPITAL like", value, "involveHospital");
            return (Criteria) this;
        }

        public Criteria andInvolveHospitalNotLike(String value) {
            addCriterion("INVOLVE_HOSPITAL not like", value, "involveHospital");
            return (Criteria) this;
        }

        public Criteria andInvolveHospitalIn(List<String> values) {
            addCriterion("INVOLVE_HOSPITAL in", values, "involveHospital");
            return (Criteria) this;
        }

        public Criteria andInvolveHospitalNotIn(List<String> values) {
            addCriterion("INVOLVE_HOSPITAL not in", values, "involveHospital");
            return (Criteria) this;
        }

        public Criteria andInvolveHospitalBetween(String value1, String value2) {
            addCriterion("INVOLVE_HOSPITAL between", value1, value2, "involveHospital");
            return (Criteria) this;
        }

        public Criteria andInvolveHospitalNotBetween(String value1, String value2) {
            addCriterion("INVOLVE_HOSPITAL not between", value1, value2, "involveHospital");
            return (Criteria) this;
        }

        public Criteria andInvolveDepartmentIsNull() {
            addCriterion("INVOLVE_DEPARTMENT is null");
            return (Criteria) this;
        }

        public Criteria andInvolveDepartmentIsNotNull() {
            addCriterion("INVOLVE_DEPARTMENT is not null");
            return (Criteria) this;
        }

        public Criteria andInvolveDepartmentEqualTo(String value) {
            addCriterion("INVOLVE_DEPARTMENT =", value, "involveDepartment");
            return (Criteria) this;
        }

        public Criteria andInvolveDepartmentNotEqualTo(String value) {
            addCriterion("INVOLVE_DEPARTMENT <>", value, "involveDepartment");
            return (Criteria) this;
        }

        public Criteria andInvolveDepartmentGreaterThan(String value) {
            addCriterion("INVOLVE_DEPARTMENT >", value, "involveDepartment");
            return (Criteria) this;
        }

        public Criteria andInvolveDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("INVOLVE_DEPARTMENT >=", value, "involveDepartment");
            return (Criteria) this;
        }

        public Criteria andInvolveDepartmentLessThan(String value) {
            addCriterion("INVOLVE_DEPARTMENT <", value, "involveDepartment");
            return (Criteria) this;
        }

        public Criteria andInvolveDepartmentLessThanOrEqualTo(String value) {
            addCriterion("INVOLVE_DEPARTMENT <=", value, "involveDepartment");
            return (Criteria) this;
        }

        public Criteria andInvolveDepartmentLike(String value) {
            addCriterion("INVOLVE_DEPARTMENT like", value, "involveDepartment");
            return (Criteria) this;
        }

        public Criteria andInvolveDepartmentNotLike(String value) {
            addCriterion("INVOLVE_DEPARTMENT not like", value, "involveDepartment");
            return (Criteria) this;
        }

        public Criteria andInvolveDepartmentIn(List<String> values) {
            addCriterion("INVOLVE_DEPARTMENT in", values, "involveDepartment");
            return (Criteria) this;
        }

        public Criteria andInvolveDepartmentNotIn(List<String> values) {
            addCriterion("INVOLVE_DEPARTMENT not in", values, "involveDepartment");
            return (Criteria) this;
        }

        public Criteria andInvolveDepartmentBetween(String value1, String value2) {
            addCriterion("INVOLVE_DEPARTMENT between", value1, value2, "involveDepartment");
            return (Criteria) this;
        }

        public Criteria andInvolveDepartmentNotBetween(String value1, String value2) {
            addCriterion("INVOLVE_DEPARTMENT not between", value1, value2, "involveDepartment");
            return (Criteria) this;
        }

        public Criteria andInvolveEmployeeIsNull() {
            addCriterion("INVOLVE_EMPLOYEE is null");
            return (Criteria) this;
        }

        public Criteria andInvolveEmployeeIsNotNull() {
            addCriterion("INVOLVE_EMPLOYEE is not null");
            return (Criteria) this;
        }

        public Criteria andInvolveEmployeeEqualTo(String value) {
            addCriterion("INVOLVE_EMPLOYEE =", value, "involveEmployee");
            return (Criteria) this;
        }

        public Criteria andInvolveEmployeeNotEqualTo(String value) {
            addCriterion("INVOLVE_EMPLOYEE <>", value, "involveEmployee");
            return (Criteria) this;
        }

        public Criteria andInvolveEmployeeGreaterThan(String value) {
            addCriterion("INVOLVE_EMPLOYEE >", value, "involveEmployee");
            return (Criteria) this;
        }

        public Criteria andInvolveEmployeeGreaterThanOrEqualTo(String value) {
            addCriterion("INVOLVE_EMPLOYEE >=", value, "involveEmployee");
            return (Criteria) this;
        }

        public Criteria andInvolveEmployeeLessThan(String value) {
            addCriterion("INVOLVE_EMPLOYEE <", value, "involveEmployee");
            return (Criteria) this;
        }

        public Criteria andInvolveEmployeeLessThanOrEqualTo(String value) {
            addCriterion("INVOLVE_EMPLOYEE <=", value, "involveEmployee");
            return (Criteria) this;
        }

        public Criteria andInvolveEmployeeLike(String value) {
            addCriterion("INVOLVE_EMPLOYEE like", value, "involveEmployee");
            return (Criteria) this;
        }

        public Criteria andInvolveEmployeeNotLike(String value) {
            addCriterion("INVOLVE_EMPLOYEE not like", value, "involveEmployee");
            return (Criteria) this;
        }

        public Criteria andInvolveEmployeeIn(List<String> values) {
            addCriterion("INVOLVE_EMPLOYEE in", values, "involveEmployee");
            return (Criteria) this;
        }

        public Criteria andInvolveEmployeeNotIn(List<String> values) {
            addCriterion("INVOLVE_EMPLOYEE not in", values, "involveEmployee");
            return (Criteria) this;
        }

        public Criteria andInvolveEmployeeBetween(String value1, String value2) {
            addCriterion("INVOLVE_EMPLOYEE between", value1, value2, "involveEmployee");
            return (Criteria) this;
        }

        public Criteria andInvolveEmployeeNotBetween(String value1, String value2) {
            addCriterion("INVOLVE_EMPLOYEE not between", value1, value2, "involveEmployee");
            return (Criteria) this;
        }

        public Criteria andCaseNumberIsNull() {
            addCriterion("CASE_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andCaseNumberIsNotNull() {
            addCriterion("CASE_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andCaseNumberEqualTo(String value) {
            addCriterion("CASE_NUMBER =", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotEqualTo(String value) {
            addCriterion("CASE_NUMBER <>", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberGreaterThan(String value) {
            addCriterion("CASE_NUMBER >", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberGreaterThanOrEqualTo(String value) {
            addCriterion("CASE_NUMBER >=", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberLessThan(String value) {
            addCriterion("CASE_NUMBER <", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberLessThanOrEqualTo(String value) {
            addCriterion("CASE_NUMBER <=", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberLike(String value) {
            addCriterion("CASE_NUMBER like", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotLike(String value) {
            addCriterion("CASE_NUMBER not like", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberIn(List<String> values) {
            addCriterion("CASE_NUMBER in", values, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotIn(List<String> values) {
            addCriterion("CASE_NUMBER not in", values, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberBetween(String value1, String value2) {
            addCriterion("CASE_NUMBER between", value1, value2, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotBetween(String value1, String value2) {
            addCriterion("CASE_NUMBER not between", value1, value2, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andVisitorDateIsNull() {
            addCriterion("VISITOR_DATE is null");
            return (Criteria) this;
        }

        public Criteria andVisitorDateIsNotNull() {
            addCriterion("VISITOR_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andVisitorDateEqualTo(String value) {
            addCriterion("VISITOR_DATE =", value, "visitorDate");
            return (Criteria) this;
        }

        public Criteria andVisitorDateNotEqualTo(String value) {
            addCriterion("VISITOR_DATE <>", value, "visitorDate");
            return (Criteria) this;
        }

        public Criteria andVisitorDateGreaterThan(String value) {
            addCriterion("VISITOR_DATE >", value, "visitorDate");
            return (Criteria) this;
        }

        public Criteria andVisitorDateGreaterThanOrEqualTo(String value) {
            addCriterion("VISITOR_DATE >=", value, "visitorDate");
            return (Criteria) this;
        }

        public Criteria andVisitorDateLessThan(String value) {
            addCriterion("VISITOR_DATE <", value, "visitorDate");
            return (Criteria) this;
        }

        public Criteria andVisitorDateLessThanOrEqualTo(String value) {
            addCriterion("VISITOR_DATE <=", value, "visitorDate");
            return (Criteria) this;
        }

        public Criteria andVisitorDateLike(String value) {
            addCriterion("VISITOR_DATE like", value, "visitorDate");
            return (Criteria) this;
        }

        public Criteria andVisitorDateNotLike(String value) {
            addCriterion("VISITOR_DATE not like", value, "visitorDate");
            return (Criteria) this;
        }

        public Criteria andVisitorDateIn(List<String> values) {
            addCriterion("VISITOR_DATE in", values, "visitorDate");
            return (Criteria) this;
        }

        public Criteria andVisitorDateNotIn(List<String> values) {
            addCriterion("VISITOR_DATE not in", values, "visitorDate");
            return (Criteria) this;
        }

        public Criteria andVisitorDateBetween(String value1, String value2) {
            addCriterion("VISITOR_DATE between", value1, value2, "visitorDate");
            return (Criteria) this;
        }

        public Criteria andVisitorDateNotBetween(String value1, String value2) {
            addCriterion("VISITOR_DATE not between", value1, value2, "visitorDate");
            return (Criteria) this;
        }

        public Criteria andComplaintModeIsNull() {
            addCriterion("COMPLAINT_MODE is null");
            return (Criteria) this;
        }

        public Criteria andComplaintModeIsNotNull() {
            addCriterion("COMPLAINT_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintModeEqualTo(String value) {
            addCriterion("COMPLAINT_MODE =", value, "complaintMode");
            return (Criteria) this;
        }

        public Criteria andComplaintModeNotEqualTo(String value) {
            addCriterion("COMPLAINT_MODE <>", value, "complaintMode");
            return (Criteria) this;
        }

        public Criteria andComplaintModeGreaterThan(String value) {
            addCriterion("COMPLAINT_MODE >", value, "complaintMode");
            return (Criteria) this;
        }

        public Criteria andComplaintModeGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLAINT_MODE >=", value, "complaintMode");
            return (Criteria) this;
        }

        public Criteria andComplaintModeLessThan(String value) {
            addCriterion("COMPLAINT_MODE <", value, "complaintMode");
            return (Criteria) this;
        }

        public Criteria andComplaintModeLessThanOrEqualTo(String value) {
            addCriterion("COMPLAINT_MODE <=", value, "complaintMode");
            return (Criteria) this;
        }

        public Criteria andComplaintModeLike(String value) {
            addCriterion("COMPLAINT_MODE like", value, "complaintMode");
            return (Criteria) this;
        }

        public Criteria andComplaintModeNotLike(String value) {
            addCriterion("COMPLAINT_MODE not like", value, "complaintMode");
            return (Criteria) this;
        }

        public Criteria andComplaintModeIn(List<String> values) {
            addCriterion("COMPLAINT_MODE in", values, "complaintMode");
            return (Criteria) this;
        }

        public Criteria andComplaintModeNotIn(List<String> values) {
            addCriterion("COMPLAINT_MODE not in", values, "complaintMode");
            return (Criteria) this;
        }

        public Criteria andComplaintModeBetween(String value1, String value2) {
            addCriterion("COMPLAINT_MODE between", value1, value2, "complaintMode");
            return (Criteria) this;
        }

        public Criteria andComplaintModeNotBetween(String value1, String value2) {
            addCriterion("COMPLAINT_MODE not between", value1, value2, "complaintMode");
            return (Criteria) this;
        }

        public Criteria andIsMajorIsNull() {
            addCriterion("IS_MAJOR is null");
            return (Criteria) this;
        }

        public Criteria andIsMajorIsNotNull() {
            addCriterion("IS_MAJOR is not null");
            return (Criteria) this;
        }

        public Criteria andIsMajorEqualTo(String value) {
            addCriterion("IS_MAJOR =", value, "isMajor");
            return (Criteria) this;
        }

        public Criteria andIsMajorNotEqualTo(String value) {
            addCriterion("IS_MAJOR <>", value, "isMajor");
            return (Criteria) this;
        }

        public Criteria andIsMajorGreaterThan(String value) {
            addCriterion("IS_MAJOR >", value, "isMajor");
            return (Criteria) this;
        }

        public Criteria andIsMajorGreaterThanOrEqualTo(String value) {
            addCriterion("IS_MAJOR >=", value, "isMajor");
            return (Criteria) this;
        }

        public Criteria andIsMajorLessThan(String value) {
            addCriterion("IS_MAJOR <", value, "isMajor");
            return (Criteria) this;
        }

        public Criteria andIsMajorLessThanOrEqualTo(String value) {
            addCriterion("IS_MAJOR <=", value, "isMajor");
            return (Criteria) this;
        }

        public Criteria andIsMajorLike(String value) {
            addCriterion("IS_MAJOR like", value, "isMajor");
            return (Criteria) this;
        }

        public Criteria andIsMajorNotLike(String value) {
            addCriterion("IS_MAJOR not like", value, "isMajor");
            return (Criteria) this;
        }

        public Criteria andIsMajorIn(List<String> values) {
            addCriterion("IS_MAJOR in", values, "isMajor");
            return (Criteria) this;
        }

        public Criteria andIsMajorNotIn(List<String> values) {
            addCriterion("IS_MAJOR not in", values, "isMajor");
            return (Criteria) this;
        }

        public Criteria andIsMajorBetween(String value1, String value2) {
            addCriterion("IS_MAJOR between", value1, value2, "isMajor");
            return (Criteria) this;
        }

        public Criteria andIsMajorNotBetween(String value1, String value2) {
            addCriterion("IS_MAJOR not between", value1, value2, "isMajor");
            return (Criteria) this;
        }

        public Criteria andReceptionEmployeeIsNull() {
            addCriterion("RECEPTION_EMPLOYEE is null");
            return (Criteria) this;
        }

        public Criteria andReceptionEmployeeIsNotNull() {
            addCriterion("RECEPTION_EMPLOYEE is not null");
            return (Criteria) this;
        }

        public Criteria andReceptionEmployeeEqualTo(String value) {
            addCriterion("RECEPTION_EMPLOYEE =", value, "receptionEmployee");
            return (Criteria) this;
        }

        public Criteria andReceptionEmployeeNotEqualTo(String value) {
            addCriterion("RECEPTION_EMPLOYEE <>", value, "receptionEmployee");
            return (Criteria) this;
        }

        public Criteria andReceptionEmployeeGreaterThan(String value) {
            addCriterion("RECEPTION_EMPLOYEE >", value, "receptionEmployee");
            return (Criteria) this;
        }

        public Criteria andReceptionEmployeeGreaterThanOrEqualTo(String value) {
            addCriterion("RECEPTION_EMPLOYEE >=", value, "receptionEmployee");
            return (Criteria) this;
        }

        public Criteria andReceptionEmployeeLessThan(String value) {
            addCriterion("RECEPTION_EMPLOYEE <", value, "receptionEmployee");
            return (Criteria) this;
        }

        public Criteria andReceptionEmployeeLessThanOrEqualTo(String value) {
            addCriterion("RECEPTION_EMPLOYEE <=", value, "receptionEmployee");
            return (Criteria) this;
        }

        public Criteria andReceptionEmployeeLike(String value) {
            addCriterion("RECEPTION_EMPLOYEE like", value, "receptionEmployee");
            return (Criteria) this;
        }

        public Criteria andReceptionEmployeeNotLike(String value) {
            addCriterion("RECEPTION_EMPLOYEE not like", value, "receptionEmployee");
            return (Criteria) this;
        }

        public Criteria andReceptionEmployeeIn(List<String> values) {
            addCriterion("RECEPTION_EMPLOYEE in", values, "receptionEmployee");
            return (Criteria) this;
        }

        public Criteria andReceptionEmployeeNotIn(List<String> values) {
            addCriterion("RECEPTION_EMPLOYEE not in", values, "receptionEmployee");
            return (Criteria) this;
        }

        public Criteria andReceptionEmployeeBetween(String value1, String value2) {
            addCriterion("RECEPTION_EMPLOYEE between", value1, value2, "receptionEmployee");
            return (Criteria) this;
        }

        public Criteria andReceptionEmployeeNotBetween(String value1, String value2) {
            addCriterion("RECEPTION_EMPLOYEE not between", value1, value2, "receptionEmployee");
            return (Criteria) this;
        }

        public Criteria andReceptionDateIsNull() {
            addCriterion("RECEPTION_DATE is null");
            return (Criteria) this;
        }

        public Criteria andReceptionDateIsNotNull() {
            addCriterion("RECEPTION_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andReceptionDateEqualTo(String value) {
            addCriterion("RECEPTION_DATE =", value, "receptionDate");
            return (Criteria) this;
        }

        public Criteria andReceptionDateNotEqualTo(String value) {
            addCriterion("RECEPTION_DATE <>", value, "receptionDate");
            return (Criteria) this;
        }

        public Criteria andReceptionDateGreaterThan(String value) {
            addCriterion("RECEPTION_DATE >", value, "receptionDate");
            return (Criteria) this;
        }

        public Criteria andReceptionDateGreaterThanOrEqualTo(String value) {
            addCriterion("RECEPTION_DATE >=", value, "receptionDate");
            return (Criteria) this;
        }

        public Criteria andReceptionDateLessThan(String value) {
            addCriterion("RECEPTION_DATE <", value, "receptionDate");
            return (Criteria) this;
        }

        public Criteria andReceptionDateLessThanOrEqualTo(String value) {
            addCriterion("RECEPTION_DATE <=", value, "receptionDate");
            return (Criteria) this;
        }

        public Criteria andReceptionDateLike(String value) {
            addCriterion("RECEPTION_DATE like", value, "receptionDate");
            return (Criteria) this;
        }

        public Criteria andReceptionDateNotLike(String value) {
            addCriterion("RECEPTION_DATE not like", value, "receptionDate");
            return (Criteria) this;
        }

        public Criteria andReceptionDateIn(List<String> values) {
            addCriterion("RECEPTION_DATE in", values, "receptionDate");
            return (Criteria) this;
        }

        public Criteria andReceptionDateNotIn(List<String> values) {
            addCriterion("RECEPTION_DATE not in", values, "receptionDate");
            return (Criteria) this;
        }

        public Criteria andReceptionDateBetween(String value1, String value2) {
            addCriterion("RECEPTION_DATE between", value1, value2, "receptionDate");
            return (Criteria) this;
        }

        public Criteria andReceptionDateNotBetween(String value1, String value2) {
            addCriterion("RECEPTION_DATE not between", value1, value2, "receptionDate");
            return (Criteria) this;
        }

        public Criteria andIsMediateIsNull() {
            addCriterion("IS_MEDIATE is null");
            return (Criteria) this;
        }

        public Criteria andIsMediateIsNotNull() {
            addCriterion("IS_MEDIATE is not null");
            return (Criteria) this;
        }

        public Criteria andIsMediateEqualTo(String value) {
            addCriterion("IS_MEDIATE =", value, "isMediate");
            return (Criteria) this;
        }

        public Criteria andIsMediateNotEqualTo(String value) {
            addCriterion("IS_MEDIATE <>", value, "isMediate");
            return (Criteria) this;
        }

        public Criteria andIsMediateGreaterThan(String value) {
            addCriterion("IS_MEDIATE >", value, "isMediate");
            return (Criteria) this;
        }

        public Criteria andIsMediateGreaterThanOrEqualTo(String value) {
            addCriterion("IS_MEDIATE >=", value, "isMediate");
            return (Criteria) this;
        }

        public Criteria andIsMediateLessThan(String value) {
            addCriterion("IS_MEDIATE <", value, "isMediate");
            return (Criteria) this;
        }

        public Criteria andIsMediateLessThanOrEqualTo(String value) {
            addCriterion("IS_MEDIATE <=", value, "isMediate");
            return (Criteria) this;
        }

        public Criteria andIsMediateLike(String value) {
            addCriterion("IS_MEDIATE like", value, "isMediate");
            return (Criteria) this;
        }

        public Criteria andIsMediateNotLike(String value) {
            addCriterion("IS_MEDIATE not like", value, "isMediate");
            return (Criteria) this;
        }

        public Criteria andIsMediateIn(List<String> values) {
            addCriterion("IS_MEDIATE in", values, "isMediate");
            return (Criteria) this;
        }

        public Criteria andIsMediateNotIn(List<String> values) {
            addCriterion("IS_MEDIATE not in", values, "isMediate");
            return (Criteria) this;
        }

        public Criteria andIsMediateBetween(String value1, String value2) {
            addCriterion("IS_MEDIATE between", value1, value2, "isMediate");
            return (Criteria) this;
        }

        public Criteria andIsMediateNotBetween(String value1, String value2) {
            addCriterion("IS_MEDIATE not between", value1, value2, "isMediate");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeIsNull() {
            addCriterion("COMPLAINT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeIsNotNull() {
            addCriterion("COMPLAINT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeEqualTo(String value) {
            addCriterion("COMPLAINT_TYPE =", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeNotEqualTo(String value) {
            addCriterion("COMPLAINT_TYPE <>", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeGreaterThan(String value) {
            addCriterion("COMPLAINT_TYPE >", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLAINT_TYPE >=", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeLessThan(String value) {
            addCriterion("COMPLAINT_TYPE <", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeLessThanOrEqualTo(String value) {
            addCriterion("COMPLAINT_TYPE <=", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeLike(String value) {
            addCriterion("COMPLAINT_TYPE like", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeNotLike(String value) {
            addCriterion("COMPLAINT_TYPE not like", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeIn(List<String> values) {
            addCriterion("COMPLAINT_TYPE in", values, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeNotIn(List<String> values) {
            addCriterion("COMPLAINT_TYPE not in", values, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeBetween(String value1, String value2) {
            addCriterion("COMPLAINT_TYPE between", value1, value2, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeNotBetween(String value1, String value2) {
            addCriterion("COMPLAINT_TYPE not between", value1, value2, "complaintType");
            return (Criteria) this;
        }

        public Criteria andHandleWayIsNull() {
            addCriterion("HANDLE_WAY is null");
            return (Criteria) this;
        }

        public Criteria andHandleWayIsNotNull() {
            addCriterion("HANDLE_WAY is not null");
            return (Criteria) this;
        }

        public Criteria andHandleWayEqualTo(String value) {
            addCriterion("HANDLE_WAY =", value, "handleWay");
            return (Criteria) this;
        }

        public Criteria andHandleWayNotEqualTo(String value) {
            addCriterion("HANDLE_WAY <>", value, "handleWay");
            return (Criteria) this;
        }

        public Criteria andHandleWayGreaterThan(String value) {
            addCriterion("HANDLE_WAY >", value, "handleWay");
            return (Criteria) this;
        }

        public Criteria andHandleWayGreaterThanOrEqualTo(String value) {
            addCriterion("HANDLE_WAY >=", value, "handleWay");
            return (Criteria) this;
        }

        public Criteria andHandleWayLessThan(String value) {
            addCriterion("HANDLE_WAY <", value, "handleWay");
            return (Criteria) this;
        }

        public Criteria andHandleWayLessThanOrEqualTo(String value) {
            addCriterion("HANDLE_WAY <=", value, "handleWay");
            return (Criteria) this;
        }

        public Criteria andHandleWayLike(String value) {
            addCriterion("HANDLE_WAY like", value, "handleWay");
            return (Criteria) this;
        }

        public Criteria andHandleWayNotLike(String value) {
            addCriterion("HANDLE_WAY not like", value, "handleWay");
            return (Criteria) this;
        }

        public Criteria andHandleWayIn(List<String> values) {
            addCriterion("HANDLE_WAY in", values, "handleWay");
            return (Criteria) this;
        }

        public Criteria andHandleWayNotIn(List<String> values) {
            addCriterion("HANDLE_WAY not in", values, "handleWay");
            return (Criteria) this;
        }

        public Criteria andHandleWayBetween(String value1, String value2) {
            addCriterion("HANDLE_WAY between", value1, value2, "handleWay");
            return (Criteria) this;
        }

        public Criteria andHandleWayNotBetween(String value1, String value2) {
            addCriterion("HANDLE_WAY not between", value1, value2, "handleWay");
            return (Criteria) this;
        }

        public Criteria andShiftHandleIsNull() {
            addCriterion("SHIFT_HANDLE is null");
            return (Criteria) this;
        }

        public Criteria andShiftHandleIsNotNull() {
            addCriterion("SHIFT_HANDLE is not null");
            return (Criteria) this;
        }

        public Criteria andShiftHandleEqualTo(String value) {
            addCriterion("SHIFT_HANDLE =", value, "shiftHandle");
            return (Criteria) this;
        }

        public Criteria andShiftHandleNotEqualTo(String value) {
            addCriterion("SHIFT_HANDLE <>", value, "shiftHandle");
            return (Criteria) this;
        }

        public Criteria andShiftHandleGreaterThan(String value) {
            addCriterion("SHIFT_HANDLE >", value, "shiftHandle");
            return (Criteria) this;
        }

        public Criteria andShiftHandleGreaterThanOrEqualTo(String value) {
            addCriterion("SHIFT_HANDLE >=", value, "shiftHandle");
            return (Criteria) this;
        }

        public Criteria andShiftHandleLessThan(String value) {
            addCriterion("SHIFT_HANDLE <", value, "shiftHandle");
            return (Criteria) this;
        }

        public Criteria andShiftHandleLessThanOrEqualTo(String value) {
            addCriterion("SHIFT_HANDLE <=", value, "shiftHandle");
            return (Criteria) this;
        }

        public Criteria andShiftHandleLike(String value) {
            addCriterion("SHIFT_HANDLE like", value, "shiftHandle");
            return (Criteria) this;
        }

        public Criteria andShiftHandleNotLike(String value) {
            addCriterion("SHIFT_HANDLE not like", value, "shiftHandle");
            return (Criteria) this;
        }

        public Criteria andShiftHandleIn(List<String> values) {
            addCriterion("SHIFT_HANDLE in", values, "shiftHandle");
            return (Criteria) this;
        }

        public Criteria andShiftHandleNotIn(List<String> values) {
            addCriterion("SHIFT_HANDLE not in", values, "shiftHandle");
            return (Criteria) this;
        }

        public Criteria andShiftHandleBetween(String value1, String value2) {
            addCriterion("SHIFT_HANDLE between", value1, value2, "shiftHandle");
            return (Criteria) this;
        }

        public Criteria andShiftHandleNotBetween(String value1, String value2) {
            addCriterion("SHIFT_HANDLE not between", value1, value2, "shiftHandle");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andExpectedClosureIsNull() {
            addCriterion("EXPECTED_CLOSURE is null");
            return (Criteria) this;
        }

        public Criteria andExpectedClosureIsNotNull() {
            addCriterion("EXPECTED_CLOSURE is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedClosureEqualTo(String value) {
            addCriterion("EXPECTED_CLOSURE =", value, "expectedClosure");
            return (Criteria) this;
        }

        public Criteria andExpectedClosureNotEqualTo(String value) {
            addCriterion("EXPECTED_CLOSURE <>", value, "expectedClosure");
            return (Criteria) this;
        }

        public Criteria andExpectedClosureGreaterThan(String value) {
            addCriterion("EXPECTED_CLOSURE >", value, "expectedClosure");
            return (Criteria) this;
        }

        public Criteria andExpectedClosureGreaterThanOrEqualTo(String value) {
            addCriterion("EXPECTED_CLOSURE >=", value, "expectedClosure");
            return (Criteria) this;
        }

        public Criteria andExpectedClosureLessThan(String value) {
            addCriterion("EXPECTED_CLOSURE <", value, "expectedClosure");
            return (Criteria) this;
        }

        public Criteria andExpectedClosureLessThanOrEqualTo(String value) {
            addCriterion("EXPECTED_CLOSURE <=", value, "expectedClosure");
            return (Criteria) this;
        }

        public Criteria andExpectedClosureLike(String value) {
            addCriterion("EXPECTED_CLOSURE like", value, "expectedClosure");
            return (Criteria) this;
        }

        public Criteria andExpectedClosureNotLike(String value) {
            addCriterion("EXPECTED_CLOSURE not like", value, "expectedClosure");
            return (Criteria) this;
        }

        public Criteria andExpectedClosureIn(List<String> values) {
            addCriterion("EXPECTED_CLOSURE in", values, "expectedClosure");
            return (Criteria) this;
        }

        public Criteria andExpectedClosureNotIn(List<String> values) {
            addCriterion("EXPECTED_CLOSURE not in", values, "expectedClosure");
            return (Criteria) this;
        }

        public Criteria andExpectedClosureBetween(String value1, String value2) {
            addCriterion("EXPECTED_CLOSURE between", value1, value2, "expectedClosure");
            return (Criteria) this;
        }

        public Criteria andExpectedClosureNotBetween(String value1, String value2) {
            addCriterion("EXPECTED_CLOSURE not between", value1, value2, "expectedClosure");
            return (Criteria) this;
        }

        public Criteria andClosingMethodIsNull() {
            addCriterion("CLOSING_METHOD is null");
            return (Criteria) this;
        }

        public Criteria andClosingMethodIsNotNull() {
            addCriterion("CLOSING_METHOD is not null");
            return (Criteria) this;
        }

        public Criteria andClosingMethodEqualTo(String value) {
            addCriterion("CLOSING_METHOD =", value, "closingMethod");
            return (Criteria) this;
        }

        public Criteria andClosingMethodNotEqualTo(String value) {
            addCriterion("CLOSING_METHOD <>", value, "closingMethod");
            return (Criteria) this;
        }

        public Criteria andClosingMethodGreaterThan(String value) {
            addCriterion("CLOSING_METHOD >", value, "closingMethod");
            return (Criteria) this;
        }

        public Criteria andClosingMethodGreaterThanOrEqualTo(String value) {
            addCriterion("CLOSING_METHOD >=", value, "closingMethod");
            return (Criteria) this;
        }

        public Criteria andClosingMethodLessThan(String value) {
            addCriterion("CLOSING_METHOD <", value, "closingMethod");
            return (Criteria) this;
        }

        public Criteria andClosingMethodLessThanOrEqualTo(String value) {
            addCriterion("CLOSING_METHOD <=", value, "closingMethod");
            return (Criteria) this;
        }

        public Criteria andClosingMethodLike(String value) {
            addCriterion("CLOSING_METHOD like", value, "closingMethod");
            return (Criteria) this;
        }

        public Criteria andClosingMethodNotLike(String value) {
            addCriterion("CLOSING_METHOD not like", value, "closingMethod");
            return (Criteria) this;
        }

        public Criteria andClosingMethodIn(List<String> values) {
            addCriterion("CLOSING_METHOD in", values, "closingMethod");
            return (Criteria) this;
        }

        public Criteria andClosingMethodNotIn(List<String> values) {
            addCriterion("CLOSING_METHOD not in", values, "closingMethod");
            return (Criteria) this;
        }

        public Criteria andClosingMethodBetween(String value1, String value2) {
            addCriterion("CLOSING_METHOD between", value1, value2, "closingMethod");
            return (Criteria) this;
        }

        public Criteria andClosingMethodNotBetween(String value1, String value2) {
            addCriterion("CLOSING_METHOD not between", value1, value2, "closingMethod");
            return (Criteria) this;
        }

        public Criteria andAmountInvolvedIsNull() {
            addCriterion("AMOUNT_INVOLVED is null");
            return (Criteria) this;
        }

        public Criteria andAmountInvolvedIsNotNull() {
            addCriterion("AMOUNT_INVOLVED is not null");
            return (Criteria) this;
        }

        public Criteria andAmountInvolvedEqualTo(BigDecimal value) {
            addCriterion("AMOUNT_INVOLVED =", value, "amountInvolved");
            return (Criteria) this;
        }

        public Criteria andAmountInvolvedNotEqualTo(BigDecimal value) {
            addCriterion("AMOUNT_INVOLVED <>", value, "amountInvolved");
            return (Criteria) this;
        }

        public Criteria andAmountInvolvedGreaterThan(BigDecimal value) {
            addCriterion("AMOUNT_INVOLVED >", value, "amountInvolved");
            return (Criteria) this;
        }

        public Criteria andAmountInvolvedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AMOUNT_INVOLVED >=", value, "amountInvolved");
            return (Criteria) this;
        }

        public Criteria andAmountInvolvedLessThan(BigDecimal value) {
            addCriterion("AMOUNT_INVOLVED <", value, "amountInvolved");
            return (Criteria) this;
        }

        public Criteria andAmountInvolvedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AMOUNT_INVOLVED <=", value, "amountInvolved");
            return (Criteria) this;
        }

        public Criteria andAmountInvolvedIn(List<BigDecimal> values) {
            addCriterion("AMOUNT_INVOLVED in", values, "amountInvolved");
            return (Criteria) this;
        }

        public Criteria andAmountInvolvedNotIn(List<BigDecimal> values) {
            addCriterion("AMOUNT_INVOLVED not in", values, "amountInvolved");
            return (Criteria) this;
        }

        public Criteria andAmountInvolvedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMOUNT_INVOLVED between", value1, value2, "amountInvolved");
            return (Criteria) this;
        }

        public Criteria andAmountInvolvedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMOUNT_INVOLVED not between", value1, value2, "amountInvolved");
            return (Criteria) this;
        }

        public Criteria andNextLinkIsNull() {
            addCriterion("NEXT_LINK is null");
            return (Criteria) this;
        }

        public Criteria andNextLinkIsNotNull() {
            addCriterion("NEXT_LINK is not null");
            return (Criteria) this;
        }

        public Criteria andNextLinkEqualTo(String value) {
            addCriterion("NEXT_LINK =", value, "nextLink");
            return (Criteria) this;
        }

        public Criteria andNextLinkNotEqualTo(String value) {
            addCriterion("NEXT_LINK <>", value, "nextLink");
            return (Criteria) this;
        }

        public Criteria andNextLinkGreaterThan(String value) {
            addCriterion("NEXT_LINK >", value, "nextLink");
            return (Criteria) this;
        }

        public Criteria andNextLinkGreaterThanOrEqualTo(String value) {
            addCriterion("NEXT_LINK >=", value, "nextLink");
            return (Criteria) this;
        }

        public Criteria andNextLinkLessThan(String value) {
            addCriterion("NEXT_LINK <", value, "nextLink");
            return (Criteria) this;
        }

        public Criteria andNextLinkLessThanOrEqualTo(String value) {
            addCriterion("NEXT_LINK <=", value, "nextLink");
            return (Criteria) this;
        }

        public Criteria andNextLinkLike(String value) {
            addCriterion("NEXT_LINK like", value, "nextLink");
            return (Criteria) this;
        }

        public Criteria andNextLinkNotLike(String value) {
            addCriterion("NEXT_LINK not like", value, "nextLink");
            return (Criteria) this;
        }

        public Criteria andNextLinkIn(List<String> values) {
            addCriterion("NEXT_LINK in", values, "nextLink");
            return (Criteria) this;
        }

        public Criteria andNextLinkNotIn(List<String> values) {
            addCriterion("NEXT_LINK not in", values, "nextLink");
            return (Criteria) this;
        }

        public Criteria andNextLinkBetween(String value1, String value2) {
            addCriterion("NEXT_LINK between", value1, value2, "nextLink");
            return (Criteria) this;
        }

        public Criteria andNextLinkNotBetween(String value1, String value2) {
            addCriterion("NEXT_LINK not between", value1, value2, "nextLink");
            return (Criteria) this;
        }

        public Criteria andNextLinkManIsNull() {
            addCriterion("NEXT_LINK_MAN is null");
            return (Criteria) this;
        }

        public Criteria andNextLinkManIsNotNull() {
            addCriterion("NEXT_LINK_MAN is not null");
            return (Criteria) this;
        }

        public Criteria andNextLinkManEqualTo(String value) {
            addCriterion("NEXT_LINK_MAN =", value, "nextLinkMan");
            return (Criteria) this;
        }

        public Criteria andNextLinkManNotEqualTo(String value) {
            addCriterion("NEXT_LINK_MAN <>", value, "nextLinkMan");
            return (Criteria) this;
        }

        public Criteria andNextLinkManGreaterThan(String value) {
            addCriterion("NEXT_LINK_MAN >", value, "nextLinkMan");
            return (Criteria) this;
        }

        public Criteria andNextLinkManGreaterThanOrEqualTo(String value) {
            addCriterion("NEXT_LINK_MAN >=", value, "nextLinkMan");
            return (Criteria) this;
        }

        public Criteria andNextLinkManLessThan(String value) {
            addCriterion("NEXT_LINK_MAN <", value, "nextLinkMan");
            return (Criteria) this;
        }

        public Criteria andNextLinkManLessThanOrEqualTo(String value) {
            addCriterion("NEXT_LINK_MAN <=", value, "nextLinkMan");
            return (Criteria) this;
        }

        public Criteria andNextLinkManLike(String value) {
            addCriterion("NEXT_LINK_MAN like", value, "nextLinkMan");
            return (Criteria) this;
        }

        public Criteria andNextLinkManNotLike(String value) {
            addCriterion("NEXT_LINK_MAN not like", value, "nextLinkMan");
            return (Criteria) this;
        }

        public Criteria andNextLinkManIn(List<String> values) {
            addCriterion("NEXT_LINK_MAN in", values, "nextLinkMan");
            return (Criteria) this;
        }

        public Criteria andNextLinkManNotIn(List<String> values) {
            addCriterion("NEXT_LINK_MAN not in", values, "nextLinkMan");
            return (Criteria) this;
        }

        public Criteria andNextLinkManBetween(String value1, String value2) {
            addCriterion("NEXT_LINK_MAN between", value1, value2, "nextLinkMan");
            return (Criteria) this;
        }

        public Criteria andNextLinkManNotBetween(String value1, String value2) {
            addCriterion("NEXT_LINK_MAN not between", value1, value2, "nextLinkMan");
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