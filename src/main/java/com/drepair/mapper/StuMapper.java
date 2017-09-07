package com.drepair.mapper;

import com.drepair.po.Stu;
import com.drepair.po.StuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    int countByExample(StuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    int deleteByExample(StuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    int deleteByPrimaryKey(String stuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    int insert(Stu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    int insertSelective(Stu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    List<Stu> selectByExample(StuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    Stu selectByPrimaryKey(String stuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    int updateByExampleSelective(@Param("record") Stu record, @Param("example") StuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    int updateByExample(@Param("record") Stu record, @Param("example") StuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    int updateByPrimaryKeySelective(Stu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stu
     *
     * @mbggenerated Sun Jul 09 15:19:34 CST 2017
     */
    int updateByPrimaryKey(Stu record);
}