package cn.itcast.dao;

import cn.itcast.domain.Items;
import org.apache.ibatis.annotations.Select;

public interface ItemsDao {
    @Select("select * from Items where id = #{id}")
    public Items findById(Integer id);

}
