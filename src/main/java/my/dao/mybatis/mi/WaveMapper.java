package my.dao.mybatis.mi;

import my.dao.mybatis.Mapper;
import my.model.b_example.WaveExample;
import my.model.persist.spirit.Wave;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaveMapper extends Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wave
     *
     * @mbggenerated
     */
    int countByExample(WaveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wave
     *
     * @mbggenerated
     */
    int deleteByExample(WaveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wave
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wave
     *
     * @mbggenerated
     */
    int insert(Wave record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wave
     *
     * @mbggenerated
     */
    int insertSelective(Wave record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wave
     *
     * @mbggenerated
     */
    List<Wave> selectByExampleWithBLOBs(WaveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wave
     *
     * @mbggenerated
     */
    List<Wave> selectByExample(WaveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wave
     *
     * @mbggenerated
     */
    Wave selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wave
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Wave record, @Param("example") WaveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wave
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") Wave record, @Param("example") WaveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wave
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Wave record, @Param("example") WaveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wave
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Wave record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wave
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Wave record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wave
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Wave record);
}
