package org.example.bo.asset.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.bo.asset.PlaceOrderBo;
import org.example.dao.DaoFactory;
import org.example.dao.crud.impl.OrderDaoImpl;
import org.example.dao.crud.impl.PlaceOrderDaoImpl;
import org.example.dao.crud.impl.ProductDaoImpl;
import org.example.entitiy.ProductEntity;
import org.example.model.Product;
import org.example.util.DaoType;

public class PlaceOrderBoImpl implements PlaceOrderBo {

    private final PlaceOrderDaoImpl placeOrderDao = DaoFactory.getInstance().getDao(DaoType.CART);
    private final ProductDaoImpl productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
    private final OrderDaoImpl orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);

    @Override
    public ObservableList<String> getProductIds(){
        return productDao.getAllIds();
    }

    @Override
    public Product getProductById(String id){
        return new ObjectMapper()
                .convertValue(productDao.search(id), Product.class);
    }

    @Override
    public ObservableList<Product> getAllProducts(){
        ObservableList<ProductEntity> list = productDao.getAll();
        ObservableList<Product> products = FXCollections.observableArrayList();

        list.forEach(productEntity -> {
            products.add(
                    new ObjectMapper()
                            .convertValue(productEntity, Product.class));
        });
        return products;
    }

    public String generateOrderId(){
        String id = new OrderDaoImpl().getLatestOrderId();

        if (id == null){
            return "B0001";
        }
        int number = Integer.parseInt(id.split("B")[1]);
        number++;
        return String.format("B%04d", number);
    }

    @Override
    public int getLatestCartId(){
        return placeOrderDao.getLatestId() + 1;
    }

}
