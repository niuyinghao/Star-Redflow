package my.dao.mybatis.mi;

import my.dao.mybatis.Mapper;
import my.model.b_example.MusicExample;
import my.model.persist.project.Music;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MusicMapper extends Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table music
     *
     * @mbggenerated
     */
    int countByExample(MusicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table music
     *
     * @mbggenerated
     */
    int deleteByExample(MusicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table music
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table music
     *
     * @mbggenerated
     */
    int insert(Music record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table music
     *
     * @mbggenerated
     */
    int insertSelective(Music record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table music
     *
     * @mbggenerated
     */
    List<Music> selectByExample(MusicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table music
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Music record, @Param("example") MusicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table music
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Music record, @Param("example") MusicExample example);
}
