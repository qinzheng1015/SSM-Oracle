package cn.itcast.dao;

import cn.itcast.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductDao {
    //查询所有
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    //保存
    @Insert("insert into product (productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus " +
            "vslues (#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product);

    //根据ID查询
    @Select("select * from product where id = #{id}")
    public Product findById(String id) throws Exception;
}
