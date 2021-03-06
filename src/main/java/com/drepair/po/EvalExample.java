package com.drepair.po;

import java.util.ArrayList;
import java.util.List;

public class EvalExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public EvalExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
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

        public Criteria andEvalIdIsNull() {
            addCriterion("eval_id is null");
            return (Criteria) this;
        }

        public Criteria andEvalIdIsNotNull() {
            addCriterion("eval_id is not null");
            return (Criteria) this;
        }

        public Criteria andEvalIdEqualTo(Integer value) {
            addCriterion("eval_id =", value, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdNotEqualTo(Integer value) {
            addCriterion("eval_id <>", value, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdGreaterThan(Integer value) {
            addCriterion("eval_id >", value, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eval_id >=", value, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdLessThan(Integer value) {
            addCriterion("eval_id <", value, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdLessThanOrEqualTo(Integer value) {
            addCriterion("eval_id <=", value, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdIn(List<Integer> values) {
            addCriterion("eval_id in", values, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdNotIn(List<Integer> values) {
            addCriterion("eval_id not in", values, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdBetween(Integer value1, Integer value2) {
            addCriterion("eval_id between", value1, value2, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eval_id not between", value1, value2, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalContentIsNull() {
            addCriterion("eval_content is null");
            return (Criteria) this;
        }

        public Criteria andEvalContentIsNotNull() {
            addCriterion("eval_content is not null");
            return (Criteria) this;
        }

        public Criteria andEvalContentEqualTo(String value) {
            addCriterion("eval_content =", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentNotEqualTo(String value) {
            addCriterion("eval_content <>", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentGreaterThan(String value) {
            addCriterion("eval_content >", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentGreaterThanOrEqualTo(String value) {
            addCriterion("eval_content >=", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentLessThan(String value) {
            addCriterion("eval_content <", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentLessThanOrEqualTo(String value) {
            addCriterion("eval_content <=", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentLike(String value) {
            addCriterion("eval_content like", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentNotLike(String value) {
            addCriterion("eval_content not like", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentIn(List<String> values) {
            addCriterion("eval_content in", values, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentNotIn(List<String> values) {
            addCriterion("eval_content not in", values, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentBetween(String value1, String value2) {
            addCriterion("eval_content between", value1, value2, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentNotBetween(String value1, String value2) {
            addCriterion("eval_content not between", value1, value2, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalScoreIsNull() {
            addCriterion("eval_score is null");
            return (Criteria) this;
        }

        public Criteria andEvalScoreIsNotNull() {
            addCriterion("eval_score is not null");
            return (Criteria) this;
        }

        public Criteria andEvalScoreEqualTo(Integer value) {
            addCriterion("eval_score =", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreNotEqualTo(Integer value) {
            addCriterion("eval_score <>", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreGreaterThan(Integer value) {
            addCriterion("eval_score >", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("eval_score >=", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreLessThan(Integer value) {
            addCriterion("eval_score <", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreLessThanOrEqualTo(Integer value) {
            addCriterion("eval_score <=", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreIn(List<Integer> values) {
            addCriterion("eval_score in", values, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreNotIn(List<Integer> values) {
            addCriterion("eval_score not in", values, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreBetween(Integer value1, Integer value2) {
            addCriterion("eval_score between", value1, value2, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("eval_score not between", value1, value2, "evalScore");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andStuIdIsNull() {
            addCriterion("stu_id is null");
            return (Criteria) this;
        }

        public Criteria andStuIdIsNotNull() {
            addCriterion("stu_id is not null");
            return (Criteria) this;
        }

        public Criteria andStuIdEqualTo(String value) {
            addCriterion("stu_id =", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdNotEqualTo(String value) {
            addCriterion("stu_id <>", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdGreaterThan(String value) {
            addCriterion("stu_id >", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdGreaterThanOrEqualTo(String value) {
            addCriterion("stu_id >=", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdLessThan(String value) {
            addCriterion("stu_id <", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdLessThanOrEqualTo(String value) {
            addCriterion("stu_id <=", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdLike(String value) {
            addCriterion("stu_id like", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdNotLike(String value) {
            addCriterion("stu_id not like", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdIn(List<String> values) {
            addCriterion("stu_id in", values, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdNotIn(List<String> values) {
            addCriterion("stu_id not in", values, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdBetween(String value1, String value2) {
            addCriterion("stu_id between", value1, value2, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdNotBetween(String value1, String value2) {
            addCriterion("stu_id not between", value1, value2, "stuId");
            return (Criteria) this;
        }

        public Criteria andHmrIdIsNull() {
            addCriterion("hmr_id is null");
            return (Criteria) this;
        }

        public Criteria andHmrIdIsNotNull() {
            addCriterion("hmr_id is not null");
            return (Criteria) this;
        }

        public Criteria andHmrIdEqualTo(Integer value) {
            addCriterion("hmr_id =", value, "hmrId");
            return (Criteria) this;
        }

        public Criteria andHmrIdNotEqualTo(Integer value) {
            addCriterion("hmr_id <>", value, "hmrId");
            return (Criteria) this;
        }

        public Criteria andHmrIdGreaterThan(Integer value) {
            addCriterion("hmr_id >", value, "hmrId");
            return (Criteria) this;
        }

        public Criteria andHmrIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("hmr_id >=", value, "hmrId");
            return (Criteria) this;
        }

        public Criteria andHmrIdLessThan(Integer value) {
            addCriterion("hmr_id <", value, "hmrId");
            return (Criteria) this;
        }

        public Criteria andHmrIdLessThanOrEqualTo(Integer value) {
            addCriterion("hmr_id <=", value, "hmrId");
            return (Criteria) this;
        }

        public Criteria andHmrIdIn(List<Integer> values) {
            addCriterion("hmr_id in", values, "hmrId");
            return (Criteria) this;
        }

        public Criteria andHmrIdNotIn(List<Integer> values) {
            addCriterion("hmr_id not in", values, "hmrId");
            return (Criteria) this;
        }

        public Criteria andHmrIdBetween(Integer value1, Integer value2) {
            addCriterion("hmr_id between", value1, value2, "hmrId");
            return (Criteria) this;
        }

        public Criteria andHmrIdNotBetween(Integer value1, Integer value2) {
            addCriterion("hmr_id not between", value1, value2, "hmrId");
            return (Criteria) this;
        }

        public Criteria andRepairerIdIsNull() {
            addCriterion("repairer_id is null");
            return (Criteria) this;
        }

        public Criteria andRepairerIdIsNotNull() {
            addCriterion("repairer_id is not null");
            return (Criteria) this;
        }

        public Criteria andRepairerIdEqualTo(Integer value) {
            addCriterion("repairer_id =", value, "repairerId");
            return (Criteria) this;
        }

        public Criteria andRepairerIdNotEqualTo(Integer value) {
            addCriterion("repairer_id <>", value, "repairerId");
            return (Criteria) this;
        }

        public Criteria andRepairerIdGreaterThan(Integer value) {
            addCriterion("repairer_id >", value, "repairerId");
            return (Criteria) this;
        }

        public Criteria andRepairerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("repairer_id >=", value, "repairerId");
            return (Criteria) this;
        }

        public Criteria andRepairerIdLessThan(Integer value) {
            addCriterion("repairer_id <", value, "repairerId");
            return (Criteria) this;
        }

        public Criteria andRepairerIdLessThanOrEqualTo(Integer value) {
            addCriterion("repairer_id <=", value, "repairerId");
            return (Criteria) this;
        }

        public Criteria andRepairerIdIn(List<Integer> values) {
            addCriterion("repairer_id in", values, "repairerId");
            return (Criteria) this;
        }

        public Criteria andRepairerIdNotIn(List<Integer> values) {
            addCriterion("repairer_id not in", values, "repairerId");
            return (Criteria) this;
        }

        public Criteria andRepairerIdBetween(Integer value1, Integer value2) {
            addCriterion("repairer_id between", value1, value2, "repairerId");
            return (Criteria) this;
        }

        public Criteria andRepairerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("repairer_id not between", value1, value2, "repairerId");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNull() {
            addCriterion("admin_id is null");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNotNull() {
            addCriterion("admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdminIdEqualTo(Integer value) {
            addCriterion("admin_id =", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotEqualTo(Integer value) {
            addCriterion("admin_id <>", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThan(Integer value) {
            addCriterion("admin_id >", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("admin_id >=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThan(Integer value) {
            addCriterion("admin_id <", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThanOrEqualTo(Integer value) {
            addCriterion("admin_id <=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdIn(List<Integer> values) {
            addCriterion("admin_id in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotIn(List<Integer> values) {
            addCriterion("admin_id not in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdBetween(Integer value1, Integer value2) {
            addCriterion("admin_id between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotBetween(Integer value1, Integer value2) {
            addCriterion("admin_id not between", value1, value2, "adminId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table eval
     *
     * @mbggenerated do_not_delete_during_merge Sun Jul 09 15:19:34 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table eval
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
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