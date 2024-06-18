package org.example.bo.asset;

import javafx.collections.ObservableList;
import org.example.bo.SuperBo;
import org.example.model.Product;

public interface PlaceOrderBo extends SuperBo {

    ObservableList<String> getProductIds();

    Product getProductById(String id);

    ObservableList<Product> getAllProducts();

    String generateOrderId();

    int getLatestCartId();
}
