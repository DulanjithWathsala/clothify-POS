package org.example.bo.asset;

import jakarta.persistence.criteria.Order;
import org.example.bo.SuperBo;

public interface OrderBo extends SuperBo {

    void saveOrder(Order order);

    String getLatestOrderId();

    Order getOrderById(String orderId);

    boolean deleteOrderById(String id);
}
