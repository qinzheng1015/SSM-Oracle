package cn.itcast.dao;

import cn.itcast.domain.Member;
import cn.itcast.domain.Orders;
import cn.itcast.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderName",column = "orderName"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "cn.itcast.dao.ProductDao.findById")),

    })
    public List<Orders> findAll() throws Exception;

    @Select("select * from orders where id = #{ordersId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderName",column = "orderName"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "cn.itcast.dao.MemberDao.findById")),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "cn.itcast.dao.ProductDao.findById")),
            @Result(property = "travellers",column = "id",javaType = List.class,many = @Many(select = "cn.itcast.dao.TravellerDao.findByOrdersId"))
    })
    public Orders findById(String ordersId) throws Exception;
}
