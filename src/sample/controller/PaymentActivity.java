package sample.controller;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.entity.Cart;
import sample.entity.Customer;
import sample.helper.DBHelper;
import sample.helper.Popup;
import sample.helper.UiLoaderCallback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class PaymentActivity implements Initializable, UiLoaderCallback, EventHandler {
    @FXML
    private AnchorPane main_frame;
    @FXML
    private StackPane main_stack_pane;
    @FXML
    private JFXButton btn_back;
    @FXML
    private Text txt_table;
    @FXML
    private Text txt_customer;
    @FXML
    private Text txt_total_items;
    @FXML
    private Text txt_total_price;
    @FXML
    private Text list_items;
    @FXML
    private JFXButton btn_make_order;
    private Popup pop = new Popup();
    private DBHelper dbHelper = new DBHelper();
    private ObservableList<Cart> cartsObservableList = FXCollections.observableArrayList();
    private Connection connection;
    private Customer mCustomer;
    private Cart mCart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_back.setOnAction(this::handle);
        btn_make_order.setOnAction(this::handle);
        try {
            connection = dbHelper.getConnection();
            mCustomer = dbHelper.getLastCustomer(connection);
            cartsObservableList = dbHelper.getItemsCart(connection);
        } catch (Exception e) {
            System.out.println(PaymentActivity.class.getSimpleName() + " Exc: " + e.getMessage());
        }
        txt_customer.setText(mCustomer.getName());
        txt_table.setText("Meja " + (mCustomer.getChairnum()));
        txt_total_items.setText(mCustomer.getTotalItems() + " Items");
        txt_total_price.setText(mCustomer.getTotalPay() + "K");
        String mList = "";
        for (int i = 0; i < cartsObservableList.size(); i++) {
            mList += cartsObservableList.get(i).getName() + "\t\t\t\t\t\t@" + cartsObservableList.get(i).getQty() + "\n";
        }
        list_items.setText(mList);
    }

    @Override
    public void handle(Event event) {
        EventTarget et = event.getTarget();
        if (et.equals(btn_back)) {
            loadUI("/sample/layout/cart_activity.fxml", main_frame);
        }
        if (et.equals(btn_make_order)) {
            mCustomer = dbHelper.getLastCustomer(connection);
            dbHelper.setStatustoDefault(connection);
            pop.popMakeOrder(main_stack_pane, this, main_frame, mCustomer, "Make Order",
                    "Terimakasih telah memasan", "Ok", "Cancel",
                    "Confirm", "Silahan Menunggu", "OK");
        }
    }

    @Override
    public void loadUI(String layout, AnchorPane pane) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(layout));
            pane.getChildren().setAll(root);
        }catch (IOException ignored){

        }
    }
}
