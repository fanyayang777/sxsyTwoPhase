package com.sayee.sxsy.newModules.complaintman.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComplaintMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ComplaintMainExample() {
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

        public Criteria andPatientCardIsNull() {
            addCriterion("PATIENT_CARD is null");
            return (Criteria) this;
        }

        public Criteria andPatientCardIsNotNull() {
            addCriterion("PATIENT_CARD is not null");
            return (Criteria) this;
        }

        public Criteria andPatientCardEqualTo(String value) {
            addCriterion("PATIENT_CARD =", value, "patientCard");
            return (Criteria) this;
        }

        public Criteria andPatientCardNotEqualTo(String value) {
            addCriterion("PATIENT_CARD <>", value, "patientCard");
            return (Criteria) this;
        }

        public Criteria andPatientCardGreaterThan(String value) {
            addCriterion("PATIENT_CARD >", value, "patientCard");
            return (Criteria) this;
        }

        public Criteria andPatientCardGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_CARD >=", value, "patientCard");
            return (Criteria) this;
        }

        public Criteria andPatientCardLessThan(String value) {
            addCriterion("PATIENT_CARD <", value, "patientCard");
            return (Criteria) this;
        }

        public Criteria andPatientCardLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_CARD <=", value, "patientCard");
            return (Criteria) this;
        }

        public Criteria andPatientCardLike(String value) {
            addCriterion("PATIENT_CARD like", value, "patientCard");
            return (Criteria) this;
        }

        public Criteria andPatientCardNotLike(String value) {
            addCriterion("PATIENT_CARD not like", value, "patientCard");
            return (Criteria) this;
        }

        public Criteria andPatientCardIn(List<String> values) {
            addCriterion("PATIENT_CARD in", values, "patientCard");
            return (Criteria) this;
        }

        public Criteria andPatientCardNotIn(List<String> values) {
            addCriterion("PATIENT_CARD not in", values, "patientCard");
            return (Criteria) this;
        }

        public Criteria andPatientCardBetween(String value1, String value2) {
            addCriterion("PATIENT_CARD between", value1, value2, "patientCard");
            return (Criteria) this;
        }

        public Criteria andPatientCardNotBetween(String value1, String value2) {
            addCriterion("PATIENT_CARD not between", value1, value2, "patientCard");
            return (Criteria) this;
        }

        public Criteria andPatientMobileIsNull() {
            addCriterion("PATIENT_MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andPatientMobileIsNotNull() {
            addCriterion("PATIENT_MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andPatientMobileEqualTo(String value) {
            addCriterion("PATIENT_MOBILE =", value, "patientMobile");
            return (Criteria) this;
        }

        public Criteria andPatientMobileNotEqualTo(String value) {
            addCriterion("PATIENT_MOBILE <>", value, "patientMobile");
            return (Criteria) this;
        }

        public Criteria andPatientMobileGreaterThan(String value) {
            addCriterion("PATIENT_MOBILE >", value, "patientMobile");
            return (Criteria) this;
        }

        public Criteria andPatientMobileGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_MOBILE >=", value, "patientMobile");
            return (Criteria) this;
        }

        public Criteria andPatientMobileLessThan(String value) {
            addCriterion("PATIENT_MOBILE <", value, "patientMobile");
            return (Criteria) this;
        }

        public Criteria andPatientMobileLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_MOBILE <=", value, "patientMobile");
            return (Criteria) this;
        }

        public Criteria andPatientMobileLike(String value) {
            addCriterion("PATIENT_MOBILE like", value, "patientMobile");
            return (Criteria) this;
        }

        public Criteria andPatientMobileNotLike(String value) {
            addCriterion("PATIENT_MOBILE not like", value, "patientMobile");
            return (Criteria) this;
        }

        public Criteria andPatientMobileIn(List<String> values) {
            addCriterion("PATIENT_MOBILE in", values, "patientMobile");
            return (Criteria) this;
        }

        public Criteria andPatientMobileNotIn(List<String> values) {
            addCriterion("PATIENT_MOBILE not in", values, "patientMobile");
            return (Criteria) this;
        }

        public Criteria andPatientMobileBetween(String value1, String value2) {
            addCriterion("PATIENT_MOBILE between", value1, value2, "patientMobile");
            return (Criteria) this;
        }

        public Criteria andPatientMobileNotBetween(String value1, String value2) {
            addCriterion("PATIENT_MOBILE not between", value1, value2, "patientMobile");
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

        public Criteria andHospitalLevelIsNull() {
            addCriterion("HOSPITAL_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIsNotNull() {
            addCriterion("HOSPITAL_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL =", value, "hospitalLevel");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNotEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL <>", value, "hospitalLevel");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelGreaterThan(String value) {
            addCriterion("HOSPITAL_LEVEL >", value, "hospitalLevel");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL >=", value, "hospitalLevel");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelLessThan(String value) {
            addCriterion("HOSPITAL_LEVEL <", value, "hospitalLevel");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL <=", value, "hospitalLevel");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelLike(String value) {
            addCriterion("HOSPITAL_LEVEL like", value, "hospitalLevel");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNotLike(String value) {
            addCriterion("HOSPITAL_LEVEL not like", value, "hospitalLevel");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIn(List<String> values) {
            addCriterion("HOSPITAL_LEVEL in", values, "hospitalLevel");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNotIn(List<String> values) {
            addCriterion("HOSPITAL_LEVEL not in", values, "hospitalLevel");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelBetween(String value1, String value2) {
            addCriterion("HOSPITAL_LEVEL between", value1, value2, "hospitalLevel");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_LEVEL not between", value1, value2, "hospitalLevel");
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

        public Criteria andProcInsIdIsNull() {
            addCriterion("PROC_INS_ID is null");
            return (Criteria) this;
        }

        public Criteria andProcInsIdIsNotNull() {
            addCriterion("PROC_INS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProcInsIdEqualTo(String value) {
            addCriterion("PROC_INS_ID =", value, "procInsId");
            return (Criteria) this;
        }

        public Criteria andProcInsIdNotEqualTo(String value) {
            addCriterion("PROC_INS_ID <>", value, "procInsId");
            return (Criteria) this;
        }

        public Criteria andProcInsIdGreaterThan(String value) {
            addCriterion("PROC_INS_ID >", value, "procInsId");
            return (Criteria) this;
        }

        public Criteria andProcInsIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROC_INS_ID >=", value, "procInsId");
            return (Criteria) this;
        }

        public Criteria andProcInsIdLessThan(String value) {
            addCriterion("PROC_INS_ID <", value, "procInsId");
            return (Criteria) this;
        }

        public Criteria andProcInsIdLessThanOrEqualTo(String value) {
            addCriterion("PROC_INS_ID <=", value, "procInsId");
            return (Criteria) this;
        }

        public Criteria andProcInsIdLike(String value) {
            addCriterion("PROC_INS_ID like", value, "procInsId");
            return (Criteria) this;
        }

        public Criteria andProcInsIdNotLike(String value) {
            addCriterion("PROC_INS_ID not like", value, "procInsId");
            return (Criteria) this;
        }

        public Criteria andProcInsIdIn(List<String> values) {
            addCriterion("PROC_INS_ID in", values, "procInsId");
            return (Criteria) this;
        }

        public Criteria andProcInsIdNotIn(List<String> values) {
            addCriterion("PROC_INS_ID not in", values, "procInsId");
            return (Criteria) this;
        }

        public Criteria andProcInsIdBetween(String value1, String value2) {
            addCriterion("PROC_INS_ID between", value1, value2, "procInsId");
            return (Criteria) this;
        }

        public Criteria andProcInsIdNotBetween(String value1, String value2) {
            addCriterion("PROC_INS_ID not between", value1, value2, "procInsId");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("SOURCE =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("SOURCE <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("SOURCE >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("SOURCE <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("SOURCE <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("SOURCE like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("SOURCE not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("SOURCE in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("SOURCE not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("SOURCE between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("SOURCE not between", value1, value2, "source");
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