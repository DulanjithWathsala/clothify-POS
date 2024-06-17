package org.example.dao.crud.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.dao.crud.ProductDao;
import org.example.entitiy.ProductEntity;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public ProductEntity search(String s) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("FROM product WHERE id=:id");
        query.setParameter("id",s);
        ProductEntity product = (ProductEntity) query.uniqueResult();
        session.close();

        return product;
    }

    @Override
    public ObservableList<ProductEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<ProductEntity> list = session.createQuery("FROM product").list();
        session.close();

        ObservableList<ProductEntity> productEntities = FXCollections.observableArrayList();
        productEntities.addAll(list);

        return productEntities;
    }

    @Override
    public void insert(ProductEntity productEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(productEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean update(ProductEntity productEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE product SET name =:name,qty =:qty,size =:size,category= :category,image= :image ,price= :price WHERE id =:id");
        query.setParameter("id",productEntity.getId());
        query.setParameter("name",productEntity.getName());
        query.setParameter("qty",productEntity.getQty());
        query.setParameter("size",productEntity.getSize());
        query.setParameter("category",productEntity.getCategory());
        query.setParameter("price",productEntity.getPrice());

        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i > 0;
    }

    @Override
    public boolean delete(String s) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM product WHERE id= :id");
        query.setParameter("id",s);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return i > 0;
    }
}
