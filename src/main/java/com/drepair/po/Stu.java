package com.drepair.po;

public class Stu {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stu.stu_id
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    private String stuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stu.stu_pwd
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    private String stuPwd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column stu.stu_phone
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    private String stuPhone;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stu.stu_id
     *
     * @return the value of stu.stu_id
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public String getStuId() {
        return stuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stu.stu_id
     *
     * @param stuId the value for stu.stu_id
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stu.stu_pwd
     *
     * @return the value of stu.stu_pwd
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public String getStuPwd() {
        return stuPwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stu.stu_pwd
     *
     * @param stuPwd the value for stu.stu_pwd
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public void setStuPwd(String stuPwd) {
        this.stuPwd = stuPwd == null ? null : stuPwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stu.stu_phone
     *
     * @return the value of stu.stu_phone
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public String getStuPhone() {
        return stuPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stu.stu_phone
     *
     * @param stuPhone the value for stu.stu_phone
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone == null ? null : stuPhone.trim();
    }
}