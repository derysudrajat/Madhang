package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
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
import sample.adapter.CartAdapter;
import sample.entity.Cart;
import sample.helper.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class CartActivity implements Initializable, UiLoaderCallback, EventHandler, SummaryOrderCallback, ListChangedCallback {
    @FXML
    public AnchorPane content_frame;
    @FXML
    private AnchorPane main_frame;
    @FXML
    private JFXButton btn_back;
    @FXML
    private JFXListView<Cart> list_view;
    @FXML
    private StackPane main_stackpane;
    @FXML
    private JFXButton btn_menu;
    @FXML
    private Text txt_price;
    @FXML
    private Text txt_qty;
    @FXML
    private JFXButton btn_pay;
    private Cart carts;
    private ObservableList<Cart> cartsObservableList = FXCollections.observableArrayList();
    private DBHelper dbHelper = new DBHelper();
    private Popup pop = new Popup();
    private Connection connection;

    public CartActivity() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_back.setOnAction(this::handle);
        btn_pay.setOnAction(this::handle);
        btn_menu.setOnAction(this::handle);
        try {
            connection = dbHelper.getConnection();
            txt_price.setText(dbHelper.getTotalPay(connection) + "K");
            txt_qty.setText(String.valueOf(dbHelper.getTotalItems(connection)));
        } catch (Exception e) {
            System.out.println(CartActivity.class.getSimpleName() + " Exc: " + e.getMessage());
        }
        LoadData();
        if (cartsObservableList.size() == 0) {
            loadUI("/sample/layout/empty_error_state.fxml", content_frame);
        } else {
            refreshData();
        }
    }

    void LoadData() {
        cartsObservableList = dbHelper.getItemsCart(connection);
    }

    void refreshData() {
        list_view.setItems(cartsObservableList);
        list_view.setCellFactory(foodListView -> new CartAdapter(list_view, main_stackpane, this));
    }

    @Override
    public void loadUI(String layout, AnchorPane pane) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(layout));
            pane.getChildren().setAll(root);
        } catch (IOException ignored) {

        }
    }

    @Override
    public void handle(Event event) {
        EventTarget et = event.getTarget();
        if (et.equals(btn_back)) {
            loadUI("/sample/layout/menu_activity.fxml", main_frame);
        }
        if (et.equals(btn_menu)) {
            carts = list_view.getSelectionModel().getSelectedItem();
            pop.poup2MenuCart(main_stackpane, btn_menu, "Delete      ", "Delete All ", carts);
        }
        if (et.equals(btn_pay)) {
            loadUI("/sample/layout/payment_activity.fxml", main_frame);
        }
    }

    @Override
    public void summaryChanged() {
        txt_price.setText(dbHelper.getTotalPay(connection) + "K");
        txt_qty.setText(String.valueOf(dbHelper.getTotalItems(connection)));
    }

    @Override
    public void listChanged() {
        LoadData();
        refreshData();
    }
}