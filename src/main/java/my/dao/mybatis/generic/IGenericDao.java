package my.dao.mybatis.generic;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IGenericDao<T,PK> {

	@Select("MapperGD.find.entityById")
	@ResultMap("BaseResultMap")
     T findEntityById(PK id);  
      
    @Select("MapperGD.find.entitys")  
     List<T> findEntity(Object... obj);  
      
    @Select("MapperGD.find.ListByLike")
	@ResultMap("BaseResultMap")
     List<T> findLikeEntity(Object... obj);
      
    @Insert("MapperGD.insert.entity")  
     void insertEntity(T t);  
      
    @Update("MapperGD.update.entity")  
     void updateEntityById(T entity);  
      
    @Delete("MapperGD.delete.id")  
     void deleteById(PK id);  
      
    @Delete("MapperGD.delete.condition")  
     void deleteByCondition(Object param);  
      
//    @Select("MapperGD.find.entity.queryByVo")
//     PageMyBatis<T> queryByVo(int i,int c,Object... obj);
//
//    @Select("MapperGD.find.entity.queryByVoLike")
//     PageMyBatis<T> LikequeryByVo(int i,int c,Object... obj);
      
      
}  