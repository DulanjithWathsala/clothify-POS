package org.example.bo.asset;

import jakarta.persistence.criteria.Order;

public interface OrderBo {

    void saveOrder(Order order);

    String getLatestOrderId();

    Order getOrderById(String orderId);

    boolean deleteOrderById(String id);
}
