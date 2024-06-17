package org.example.bo.asset.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.Order;
import org.example.bo.asset.OrderBo;
import org.example.dao.DaoFactory;
import org.example.dao.crud.impl.OrderDaoImpl;
import org.example.entitiy.OrderEntity;
import org.example.util.DaoType;

public class OrderBoImpl implements OrderBo {

    private final OrderDaoImpl orderDaoImpl = DaoFactory.getInstance().getDao(DaoType.ORDER);

    @Override
    public void saveOrder(Order order){
        new OrderDaoImpl().insert(
                new ObjectMapper().convertValue(order, OrderEntity.class));
    }

    @Override
    public String getLatestOrderId(){
        return orderDaoImpl.getLatestOrderId();
    }

    public Order getOrderById(String orderId){
        return new ObjectMapper()
                .convertValue(orderDaoImpl.search(orderId), Order.class);
    }

    @Override
    public boolean deleteOrderById(String id){
        return orderDaoImpl.delete(id);
    }
}
