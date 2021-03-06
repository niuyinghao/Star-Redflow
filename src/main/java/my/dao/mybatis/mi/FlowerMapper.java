package my.dao.mybatis.mi;

import my.dao.mybatis.Mapper;
import my.model.b_example.FlowerExample;
import my.model.persist.spirit.Flower;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlowerMapper extends Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower
     *
     * @mbggenerated
     */
    int countByExample(FlowerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower
     *
     * @mbggenerated
     */
    int deleteByExample(FlowerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower
     *
     * @mbggenerated
     */
    int insert(Flower record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower
     *
     * @mbggenerated
     */
    int insertSelective(Flower record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower
     *
     * @mbggenerated
     */
    List<Flower> selectByExampleWithBLOBs(FlowerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower
     *
     * @mbggenerated
     */
    List<Flower> selectByExample(FlowerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower
     *
     * @mbggenerated
     */
    Flower selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Flower record, @Param("example") FlowerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") Flower record, @Param("example") FlowerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Flower record, @Param("example") FlowerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Flower record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Flower record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flower
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Flower record);
}
