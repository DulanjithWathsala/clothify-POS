package org.example.dao.crud;

import javafx.collections.ObservableList;
import org.example.dao.CrudDao;
import org.example.entitiy.ProductEntity;

public interface ProductDao extends CrudDao<ProductEntity, String> {

    String getLatestId();

    ObservableList<String> getAllIds();
}
