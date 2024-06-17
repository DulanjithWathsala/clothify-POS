package org.example.dao.crud.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.dao.crud.PlaceOrderDao;
import org.example.entitiy.OrderHasItemEntity;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PlaceOrderDaoImpl implements PlaceOrderDao {
    @Override
    public OrderHasItemEntity search(Integer integer) {
        return null;
    }

    @Override
    public ObservableList<OrderHasItemEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<OrderHasItemEntity> list = session.createQuery("FROM order_has_items").list();
        session.close();

        ObservableList<OrderHasItemEntity> observableList = FXCollections.observableArrayList();
        observableList.addAll(list);
        return observableList;
    }

    @Override
    public void insert(OrderHasItemEntity orderHasItemEntity) {
        //
    }

    @Override
    public boolean update(OrderHasItemEntity orderHasItemEntity) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM order_has_items WHERE orderId=:id");
        query.setParameter("id",id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i > 0;
    }
}
