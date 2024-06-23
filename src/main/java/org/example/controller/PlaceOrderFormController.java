package org.example.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.bo.BoFactory;
import org.example.bo.asset.CustomerBo;
import org.example.bo.asset.PlaceOrderBo;
import org.example.bo.asset.ProductBo;
import org.example.model.Cart;
import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.Product;
import org.example.util.BoType;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnCustomerDetails;

    @FXML
    private Button btnFinalizeOrder;

    @FXML
    private Button btnManageEmployee;

    @FXML
    private Button btnOrderDetails;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Button btnProductDetails;

    @FXML
    private Button btnSupplierDetails;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbItemId;

    @FXML
    private Label lblCashier;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<Cart> tblCart;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtItemQty;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane placeOrderWindow;

    private final SceneSwitchController sceneSwitch;

    private final ProductBo productBo;

    private final CustomerBo customerBo;

    private final PlaceOrderBo placeOrderBo;

    private int cartId;

    private double total;

    private final ObservableList<Cart> cartList = FXCollections.observableArrayList();

    public PlaceOrderFormController() {
        this.sceneSwitch = SceneSwitchController.getInstance();
        this.productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);
        this.customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);
        this.placeOrderBo = BoFactory.getInstance().getBo(BoType.PLACEORDER);
        this.cartId = 1;
        this.total = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateTime();
        txtOrderId.setEditable(false);
        txtCustomerName.setEditable(false);
        txtCustomerEmail.setEditable(false);
        txtCustomerAddress.setEditable(false);
        txtItemName.setEditable(false);
        txtPrice.setEditable(false);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        cmbCustomerId.setItems(customerBo.getAllCustomerIds());
        cmbItemId.setItems(productBo.getAllProductIds());

        txtOrderId.setText(placeOrderBo.generateOrderId());
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        double unitPrice = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtItemQty.getText());
        total += (unitPrice * qty);
        Cart cart = new Cart(
                cartId++,
                txtItemName.getText(),
                 unitPrice,
                qty,
                (unitPrice * qty)
        );
        lblTotal.setText(String.valueOf(total));
        cartList.add(cart);
        tblCart.setItems(cartList);
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        cartId = 1;
        lblTotal.setText("0.00");
        Customer customer = customerBo.getCustomerById(cmbCustomerId.getValue());
        txtCustomerName.setText(customer.getName());
        txtCustomerEmail.setText(customer.getEmail());
        txtCustomerAddress.setText(customer.getAddress());
        tblCart.getItems().clear();
    }

    @FXML
    void cmbItemIdOnAction(ActionEvent event) {
        Product product = productBo.getProductById(cmbItemId.getValue());
        txtItemName.setText(product.getName());
        txtPrice.setText(String.valueOf(product.getPrice()));
    }


    @FXML
    void txtQtyKeyTypedEvent(KeyEvent event) {
    }

    @FXML
    void btnCustomerDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(placeOrderWindow,"customer-details-form.fxml");
    }

    @FXML
    void btnFinalizeOrderOnAction(ActionEvent event) {
        System.out.println(cmbCustomerId.getValue());
        Order order = new Order(
                txtOrderId.getText(),
                cmbCustomerId.getValue(),
                new Date(),
                Double.parseDouble(lblTotal.getText())
        );

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Finalize order?");
        Optional<ButtonType> result = alert.showAndWait();
        // Check if the response was OK or Cancel
        if (result.isPresent() && result.get() == ButtonType.OK) {
            placeOrderBo.saveOrder(order);
            new Alert(Alert.AlertType.INFORMATION, "Order placed Successfully").show();
        }
    }

    private void loadDateTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime localTime = LocalTime.now();
            lblTime.setText(
                    localTime.getHour()+" : "+localTime.getMinute()+" : "+localTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void btnManageEmployeeOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(placeOrderWindow,"manage-employee-form.fxml");
    }

    @FXML
    void btnOrderDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(placeOrderWindow,"order-details-form.fxml");
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(placeOrderWindow,"place-order-form.fxml");
    }

    @FXML
    void btnProductDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(placeOrderWindow,"product-details-form.fxml");
    }

    @FXML
    void btnSupplierDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(placeOrderWindow,"supplier-details-form.fxml");
    }

    @FXML
    void lblClothifyMouseClicked(MouseEvent event) throws IOException {
        sceneSwitch.switchScene(placeOrderWindow,"dashboard-form.fxml");
    }
}
