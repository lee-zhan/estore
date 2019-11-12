package com.briup.app.estore.mapper;

import com.briup.app.estore.bean.Orderline;
import java.util.List;

public interface OrderlineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(List<Orderline> orderlines);

    Orderline selectByPrimaryKey(Integer id);

    List<Orderline> selectAll();

    int updateByPrimaryKey(Orderline record);
    
    void deleteByOrderId(Integer orderId);
}