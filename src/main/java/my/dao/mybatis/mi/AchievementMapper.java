package my.dao.mybatis.mi;

import my.dao.mybatis.Mapper;
import my.model.b_example.AchievementExample;
import my.model.persist.project.Achievement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AchievementMapper extends Mapper{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table achievement
     *
     * @mbggenerated
     */
    int countByExample(AchievementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table achievement
     *
     * @mbggenerated
     */
    int deleteByExample(AchievementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table achievement
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table achievement
     *
     * @mbggenerated
     */
    int insert(Achievement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table achievement
     *
     * @mbggenerated
     */
    int insertSelective(Achievement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table achievement
     *
     * @mbggenerated
     */
    List<Achievement> selectByExampleWithBLOBs(AchievementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table achievement
     *
     * @mbggenerated
     */
    List<Achievement> selectByExample(AchievementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table achievement
     *
     * @mbggenerated
     */
    Achievement selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table achievement
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Achievement record, @Param("example") AchievementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table achievement
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") Achievement record, @Param("example") AchievementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table achievement
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Achievement record, @Param("example") AchievementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table achievement
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Achievement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table achievement
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Achievement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table achievement
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Achievement record);
}
