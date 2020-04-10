package com.sayee.sxsy.newModules.filepathutils.entity;

import java.util.ArrayList;
import java.util.List;

public class TAccessoriesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TAccessoriesExample() {
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

        public Criteria andAcceIdIsNull() {
            addCriterion("ACCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAcceIdIsNotNull() {
            addCriterion("ACCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAcceIdEqualTo(String value) {
            addCriterion("ACCE_ID =", value, "acceId");
            return (Criteria) this;
        }

        public Criteria andAcceIdNotEqualTo(String value) {
            addCriterion("ACCE_ID <>", value, "acceId");
            return (Criteria) this;
        }

        public Criteria andAcceIdGreaterThan(String value) {
            addCriterion("ACCE_ID >", value, "acceId");
            return (Criteria) this;
        }

        public Criteria andAcceIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACCE_ID >=", value, "acceId");
            return (Criteria) this;
        }

        public Criteria andAcceIdLessThan(String value) {
            addCriterion("ACCE_ID <", value, "acceId");
            return (Criteria) this;
        }

        public Criteria andAcceIdLessThanOrEqualTo(String value) {
            addCriterion("ACCE_ID <=", value, "acceId");
            return (Criteria) this;
        }

        public Criteria andAcceIdLike(String value) {
            addCriterion("ACCE_ID like", value, "acceId");
            return (Criteria) this;
        }

        public Criteria andAcceIdNotLike(String value) {
            addCriterion("ACCE_ID not like", value, "acceId");
            return (Criteria) this;
        }

        public Criteria andAcceIdIn(List<String> values) {
            addCriterion("ACCE_ID in", values, "acceId");
            return (Criteria) this;
        }

        public Criteria andAcceIdNotIn(List<String> values) {
            addCriterion("ACCE_ID not in", values, "acceId");
            return (Criteria) this;
        }

        public Criteria andAcceIdBetween(String value1, String value2) {
            addCriterion("ACCE_ID between", value1, value2, "acceId");
            return (Criteria) this;
        }

        public Criteria andAcceIdNotBetween(String value1, String value2) {
            addCriterion("ACCE_ID not between", value1, value2, "acceId");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("ITEM_ID =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("ITEM_ID <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("ITEM_ID >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_ID >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("ITEM_ID <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("ITEM_ID <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("ITEM_ID like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("ITEM_ID not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("ITEM_ID in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("ITEM_ID not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("ITEM_ID between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("ITEM_ID not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("FILE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("FILE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("FILE_NAME =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("FILE_NAME <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("FILE_NAME >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_NAME >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("FILE_NAME <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("FILE_NAME <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("FILE_NAME like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("FILE_NAME not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("FILE_NAME in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("FILE_NAME not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("FILE_NAME between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("FILE_NAME not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNull() {
            addCriterion("FILE_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNotNull() {
            addCriterion("FILE_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andFileSizeEqualTo(Float value) {
            addCriterion("FILE_SIZE =", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotEqualTo(Float value) {
            addCriterion("FILE_SIZE <>", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThan(Float value) {
            addCriterion("FILE_SIZE >", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThanOrEqualTo(Float value) {
            addCriterion("FILE_SIZE >=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThan(Float value) {
            addCriterion("FILE_SIZE <", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThanOrEqualTo(Float value) {
            addCriterion("FILE_SIZE <=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeIn(List<Float> values) {
            addCriterion("FILE_SIZE in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotIn(List<Float> values) {
            addCriterion("FILE_SIZE not in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeBetween(Float value1, Float value2) {
            addCriterion("FILE_SIZE between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotBetween(Float value1, Float value2) {
            addCriterion("FILE_SIZE not between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andUploadDateIsNull() {
            addCriterion("UPLOAD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUploadDateIsNotNull() {
            addCriterion("UPLOAD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUploadDateEqualTo(String value) {
            addCriterion("UPLOAD_DATE =", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateNotEqualTo(String value) {
            addCriterion("UPLOAD_DATE <>", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateGreaterThan(String value) {
            addCriterion("UPLOAD_DATE >", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateGreaterThanOrEqualTo(String value) {
            addCriterion("UPLOAD_DATE >=", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateLessThan(String value) {
            addCriterion("UPLOAD_DATE <", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateLessThanOrEqualTo(String value) {
            addCriterion("UPLOAD_DATE <=", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateLike(String value) {
            addCriterion("UPLOAD_DATE like", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateNotLike(String value) {
            addCriterion("UPLOAD_DATE not like", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateIn(List<String> values) {
            addCriterion("UPLOAD_DATE in", values, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateNotIn(List<String> values) {
            addCriterion("UPLOAD_DATE not in", values, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateBetween(String value1, String value2) {
            addCriterion("UPLOAD_DATE between", value1, value2, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateNotBetween(String value1, String value2) {
            addCriterion("UPLOAD_DATE not between", value1, value2, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andShowAsImageIsNull() {
            addCriterion("SHOW_AS_IMAGE is null");
            return (Criteria) this;
        }

        public Criteria andShowAsImageIsNotNull() {
            addCriterion("SHOW_AS_IMAGE is not null");
            return (Criteria) this;
        }

        public Criteria andShowAsImageEqualTo(Integer value) {
            addCriterion("SHOW_AS_IMAGE =", value, "showAsImage");
            return (Criteria) this;
        }

        public Criteria andShowAsImageNotEqualTo(Integer value) {
            addCriterion("SHOW_AS_IMAGE <>", value, "showAsImage");
            return (Criteria) this;
        }

        public Criteria andShowAsImageGreaterThan(Integer value) {
            addCriterion("SHOW_AS_IMAGE >", value, "showAsImage");
            return (Criteria) this;
        }

        public Criteria andShowAsImageGreaterThanOrEqualTo(Integer value) {
            addCriterion("SHOW_AS_IMAGE >=", value, "showAsImage");
            return (Criteria) this;
        }

        public Criteria andShowAsImageLessThan(Integer value) {
            addCriterion("SHOW_AS_IMAGE <", value, "showAsImage");
            return (Criteria) this;
        }

        public Criteria andShowAsImageLessThanOrEqualTo(Integer value) {
            addCriterion("SHOW_AS_IMAGE <=", value, "showAsImage");
            return (Criteria) this;
        }

        public Criteria andShowAsImageIn(List<Integer> values) {
            addCriterion("SHOW_AS_IMAGE in", values, "showAsImage");
            return (Criteria) this;
        }

        public Criteria andShowAsImageNotIn(List<Integer> values) {
            addCriterion("SHOW_AS_IMAGE not in", values, "showAsImage");
            return (Criteria) this;
        }

        public Criteria andShowAsImageBetween(Integer value1, Integer value2) {
            addCriterion("SHOW_AS_IMAGE between", value1, value2, "showAsImage");
            return (Criteria) this;
        }

        public Criteria andShowAsImageNotBetween(Integer value1, Integer value2) {
            addCriterion("SHOW_AS_IMAGE not between", value1, value2, "showAsImage");
            return (Criteria) this;
        }

        public Criteria andEmployeeidIsNull() {
            addCriterion("EMPLOYEEID is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeidIsNotNull() {
            addCriterion("EMPLOYEEID is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeidEqualTo(String value) {
            addCriterion("EMPLOYEEID =", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidNotEqualTo(String value) {
            addCriterion("EMPLOYEEID <>", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidGreaterThan(String value) {
            addCriterion("EMPLOYEEID >", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidGreaterThanOrEqualTo(String value) {
            addCriterion("EMPLOYEEID >=", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidLessThan(String value) {
            addCriterion("EMPLOYEEID <", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidLessThanOrEqualTo(String value) {
            addCriterion("EMPLOYEEID <=", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidLike(String value) {
            addCriterion("EMPLOYEEID like", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidNotLike(String value) {
            addCriterion("EMPLOYEEID not like", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidIn(List<String> values) {
            addCriterion("EMPLOYEEID in", values, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidNotIn(List<String> values) {
            addCriterion("EMPLOYEEID not in", values, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidBetween(String value1, String value2) {
            addCriterion("EMPLOYEEID between", value1, value2, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidNotBetween(String value1, String value2) {
            addCriterion("EMPLOYEEID not between", value1, value2, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeenameIsNull() {
            addCriterion("EMPLOYEENAME is null");
            return (Criteria) this;
        }

        public Criteria andEmployeenameIsNotNull() {
            addCriterion("EMPLOYEENAME is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeenameEqualTo(String value) {
            addCriterion("EMPLOYEENAME =", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameNotEqualTo(String value) {
            addCriterion("EMPLOYEENAME <>", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameGreaterThan(String value) {
            addCriterion("EMPLOYEENAME >", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameGreaterThanOrEqualTo(String value) {
            addCriterion("EMPLOYEENAME >=", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameLessThan(String value) {
            addCriterion("EMPLOYEENAME <", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameLessThanOrEqualTo(String value) {
            addCriterion("EMPLOYEENAME <=", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameLike(String value) {
            addCriterion("EMPLOYEENAME like", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameNotLike(String value) {
            addCriterion("EMPLOYEENAME not like", value, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameIn(List<String> values) {
            addCriterion("EMPLOYEENAME in", values, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameNotIn(List<String> values) {
            addCriterion("EMPLOYEENAME not in", values, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameBetween(String value1, String value2) {
            addCriterion("EMPLOYEENAME between", value1, value2, "employeename");
            return (Criteria) this;
        }

        public Criteria andEmployeenameNotBetween(String value1, String value2) {
            addCriterion("EMPLOYEENAME not between", value1, value2, "employeename");
            return (Criteria) this;
        }

        public Criteria andFiletypeIsNull() {
            addCriterion("FILETYPE is null");
            return (Criteria) this;
        }

        public Criteria andFiletypeIsNotNull() {
            addCriterion("FILETYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFiletypeEqualTo(String value) {
            addCriterion("FILETYPE =", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeNotEqualTo(String value) {
            addCriterion("FILETYPE <>", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeGreaterThan(String value) {
            addCriterion("FILETYPE >", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeGreaterThanOrEqualTo(String value) {
            addCriterion("FILETYPE >=", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeLessThan(String value) {
            addCriterion("FILETYPE <", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeLessThanOrEqualTo(String value) {
            addCriterion("FILETYPE <=", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeLike(String value) {
            addCriterion("FILETYPE like", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeNotLike(String value) {
            addCriterion("FILETYPE not like", value, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeIn(List<String> values) {
            addCriterion("FILETYPE in", values, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeNotIn(List<String> values) {
            addCriterion("FILETYPE not in", values, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeBetween(String value1, String value2) {
            addCriterion("FILETYPE between", value1, value2, "filetype");
            return (Criteria) this;
        }

        public Criteria andFiletypeNotBetween(String value1, String value2) {
            addCriterion("FILETYPE not between", value1, value2, "filetype");
            return (Criteria) this;
        }

        public Criteria andRealpathIsNull() {
            addCriterion("REALPATH is null");
            return (Criteria) this;
        }

        public Criteria andRealpathIsNotNull() {
            addCriterion("REALPATH is not null");
            return (Criteria) this;
        }

        public Criteria andRealpathEqualTo(String value) {
            addCriterion("REALPATH =", value, "realpath");
            return (Criteria) this;
        }

        public Criteria andRealpathNotEqualTo(String value) {
            addCriterion("REALPATH <>", value, "realpath");
            return (Criteria) this;
        }

        public Criteria andRealpathGreaterThan(String value) {
            addCriterion("REALPATH >", value, "realpath");
            return (Criteria) this;
        }

        public Criteria andRealpathGreaterThanOrEqualTo(String value) {
            addCriterion("REALPATH >=", value, "realpath");
            return (Criteria) this;
        }

        public Criteria andRealpathLessThan(String value) {
            addCriterion("REALPATH <", value, "realpath");
            return (Criteria) this;
        }

        public Criteria andRealpathLessThanOrEqualTo(String value) {
            addCriterion("REALPATH <=", value, "realpath");
            return (Criteria) this;
        }

        public Criteria andRealpathLike(String value) {
            addCriterion("REALPATH like", value, "realpath");
            return (Criteria) this;
        }

        public Criteria andRealpathNotLike(String value) {
            addCriterion("REALPATH not like", value, "realpath");
            return (Criteria) this;
        }

        public Criteria andRealpathIn(List<String> values) {
            addCriterion("REALPATH in", values, "realpath");
            return (Criteria) this;
        }

        public Criteria andRealpathNotIn(List<String> values) {
            addCriterion("REALPATH not in", values, "realpath");
            return (Criteria) this;
        }

        public Criteria andRealpathBetween(String value1, String value2) {
            addCriterion("REALPATH between", value1, value2, "realpath");
            return (Criteria) this;
        }

        public Criteria andRealpathNotBetween(String value1, String value2) {
            addCriterion("REALPATH not between", value1, value2, "realpath");
            return (Criteria) this;
        }

        public Criteria andChoosefileIsNull() {
            addCriterion("CHOOSEFILE is null");
            return (Criteria) this;
        }

        public Criteria andChoosefileIsNotNull() {
            addCriterion("CHOOSEFILE is not null");
            return (Criteria) this;
        }

        public Criteria andChoosefileEqualTo(String value) {
            addCriterion("CHOOSEFILE =", value, "choosefile");
            return (Criteria) this;
        }

        public Criteria andChoosefileNotEqualTo(String value) {
            addCriterion("CHOOSEFILE <>", value, "choosefile");
            return (Criteria) this;
        }

        public Criteria andChoosefileGreaterThan(String value) {
            addCriterion("CHOOSEFILE >", value, "choosefile");
            return (Criteria) this;
        }

        public Criteria andChoosefileGreaterThanOrEqualTo(String value) {
            addCriterion("CHOOSEFILE >=", value, "choosefile");
            return (Criteria) this;
        }

        public Criteria andChoosefileLessThan(String value) {
            addCriterion("CHOOSEFILE <", value, "choosefile");
            return (Criteria) this;
        }

        public Criteria andChoosefileLessThanOrEqualTo(String value) {
            addCriterion("CHOOSEFILE <=", value, "choosefile");
            return (Criteria) this;
        }

        public Criteria andChoosefileLike(String value) {
            addCriterion("CHOOSEFILE like", value, "choosefile");
            return (Criteria) this;
        }

        public Criteria andChoosefileNotLike(String value) {
            addCriterion("CHOOSEFILE not like", value, "choosefile");
            return (Criteria) this;
        }

        public Criteria andChoosefileIn(List<String> values) {
            addCriterion("CHOOSEFILE in", values, "choosefile");
            return (Criteria) this;
        }

        public Criteria andChoosefileNotIn(List<String> values) {
            addCriterion("CHOOSEFILE not in", values, "choosefile");
            return (Criteria) this;
        }

        public Criteria andChoosefileBetween(String value1, String value2) {
            addCriterion("CHOOSEFILE between", value1, value2, "choosefile");
            return (Criteria) this;
        }

        public Criteria andChoosefileNotBetween(String value1, String value2) {
            addCriterion("CHOOSEFILE not between", value1, value2, "choosefile");
            return (Criteria) this;
        }

        public Criteria andThumbPathIsNull() {
            addCriterion("THUMB_PATH is null");
            return (Criteria) this;
        }

        public Criteria andThumbPathIsNotNull() {
            addCriterion("THUMB_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andThumbPathEqualTo(String value) {
            addCriterion("THUMB_PATH =", value, "thumbPath");
            return (Criteria) this;
        }

        public Criteria andThumbPathNotEqualTo(String value) {
            addCriterion("THUMB_PATH <>", value, "thumbPath");
            return (Criteria) this;
        }

        public Criteria andThumbPathGreaterThan(String value) {
            addCriterion("THUMB_PATH >", value, "thumbPath");
            return (Criteria) this;
        }

        public Criteria andThumbPathGreaterThanOrEqualTo(String value) {
            addCriterion("THUMB_PATH >=", value, "thumbPath");
            return (Criteria) this;
        }

        public Criteria andThumbPathLessThan(String value) {
            addCriterion("THUMB_PATH <", value, "thumbPath");
            return (Criteria) this;
        }

        public Criteria andThumbPathLessThanOrEqualTo(String value) {
            addCriterion("THUMB_PATH <=", value, "thumbPath");
            return (Criteria) this;
        }

        public Criteria andThumbPathLike(String value) {
            addCriterion("THUMB_PATH like", value, "thumbPath");
            return (Criteria) this;
        }

        public Criteria andThumbPathNotLike(String value) {
            addCriterion("THUMB_PATH not like", value, "thumbPath");
            return (Criteria) this;
        }

        public Criteria andThumbPathIn(List<String> values) {
            addCriterion("THUMB_PATH in", values, "thumbPath");
            return (Criteria) this;
        }

        public Criteria andThumbPathNotIn(List<String> values) {
            addCriterion("THUMB_PATH not in", values, "thumbPath");
            return (Criteria) this;
        }

        public Criteria andThumbPathBetween(String value1, String value2) {
            addCriterion("THUMB_PATH between", value1, value2, "thumbPath");
            return (Criteria) this;
        }

        public Criteria andThumbPathNotBetween(String value1, String value2) {
            addCriterion("THUMB_PATH not between", value1, value2, "thumbPath");
            return (Criteria) this;
        }

        public Criteria andIsPicIsNull() {
            addCriterion("IS_PIC is null");
            return (Criteria) this;
        }

        public Criteria andIsPicIsNotNull() {
            addCriterion("IS_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andIsPicEqualTo(Integer value) {
            addCriterion("IS_PIC =", value, "isPic");
            return (Criteria) this;
        }

        public Criteria andIsPicNotEqualTo(Integer value) {
            addCriterion("IS_PIC <>", value, "isPic");
            return (Criteria) this;
        }

        public Criteria andIsPicGreaterThan(Integer value) {
            addCriterion("IS_PIC >", value, "isPic");
            return (Criteria) this;
        }

        public Criteria andIsPicGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_PIC >=", value, "isPic");
            return (Criteria) this;
        }

        public Criteria andIsPicLessThan(Integer value) {
            addCriterion("IS_PIC <", value, "isPic");
            return (Criteria) this;
        }

        public Criteria andIsPicLessThanOrEqualTo(Integer value) {
            addCriterion("IS_PIC <=", value, "isPic");
            return (Criteria) this;
        }

        public Criteria andIsPicIn(List<Integer> values) {
            addCriterion("IS_PIC in", values, "isPic");
            return (Criteria) this;
        }

        public Criteria andIsPicNotIn(List<Integer> values) {
            addCriterion("IS_PIC not in", values, "isPic");
            return (Criteria) this;
        }

        public Criteria andIsPicBetween(Integer value1, Integer value2) {
            addCriterion("IS_PIC between", value1, value2, "isPic");
            return (Criteria) this;
        }

        public Criteria andIsPicNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_PIC not between", value1, value2, "isPic");
            return (Criteria) this;
        }

        public Criteria andFjtypeIsNull() {
            addCriterion("FJTYPE is null");
            return (Criteria) this;
        }

        public Criteria andFjtypeIsNotNull() {
            addCriterion("FJTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFjtypeEqualTo(String value) {
            addCriterion("FJTYPE =", value, "fjtype");
            return (Criteria) this;
        }

        public Criteria andFjtypeNotEqualTo(String value) {
            addCriterion("FJTYPE <>", value, "fjtype");
            return (Criteria) this;
        }

        public Criteria andFjtypeGreaterThan(String value) {
            addCriterion("FJTYPE >", value, "fjtype");
            return (Criteria) this;
        }

        public Criteria andFjtypeGreaterThanOrEqualTo(String value) {
            addCriterion("FJTYPE >=", value, "fjtype");
            return (Criteria) this;
        }

        public Criteria andFjtypeLessThan(String value) {
            addCriterion("FJTYPE <", value, "fjtype");
            return (Criteria) this;
        }

        public Criteria andFjtypeLessThanOrEqualTo(String value) {
            addCriterion("FJTYPE <=", value, "fjtype");
            return (Criteria) this;
        }

        public Criteria andFjtypeLike(String value) {
            addCriterion("FJTYPE like", value, "fjtype");
            return (Criteria) this;
        }

        public Criteria andFjtypeNotLike(String value) {
            addCriterion("FJTYPE not like", value, "fjtype");
            return (Criteria) this;
        }

        public Criteria andFjtypeIn(List<String> values) {
            addCriterion("FJTYPE in", values, "fjtype");
            return (Criteria) this;
        }

        public Criteria andFjtypeNotIn(List<String> values) {
            addCriterion("FJTYPE not in", values, "fjtype");
            return (Criteria) this;
        }

        public Criteria andFjtypeBetween(String value1, String value2) {
            addCriterion("FJTYPE between", value1, value2, "fjtype");
            return (Criteria) this;
        }

        public Criteria andFjtypeNotBetween(String value1, String value2) {
            addCriterion("FJTYPE not between", value1, value2, "fjtype");
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