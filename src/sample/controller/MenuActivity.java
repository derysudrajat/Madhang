package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
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
import sample.adapter.FoodAdapter;
import sample.entity.Foods;
import sample.helper.DBHelper;
import sample.helper.NotifUpdaterCallback;
import sample.helper.UiLoaderCallback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class MenuActivity implements Initializable, UiLoaderCallback, EventHandler, NotifUpdaterCallback {

    @FXML
    public AnchorPane content_frame;
    @FXML
    private AnchorPane main_frame;
    @FXML
    private Text activity_title;
    @FXML
    private StackPane main_stackpane;
    @FXML
    private JFXButton btn_back;
    @FXML
    private JFXButton btn_cart;
    @FXML
    private JFXButton btn_pay;
    @FXML
    private AnchorPane notif_pane;
    @FXML
    private Text txt_notif;
    @FXML
    private JFXButton btn_rating;
    @FXML
    private JFXButton btn_price;
    @FXML
    private MaterialDesignIconView icon_price;
    @FXML
    private AnchorPane nav_bottom;
    @FXML
    private JFXButton btn_food;
    @FXML
    private JFXButton btn_snacks;
    @FXML
    private JFXButton btn_juice;
    @FXML
    private JFXButton btn_coffee;
    @FXML
    private Text txt_price;
    @FXML
    private Text txt_qty;
    @FXML
    private JFXListView<Foods> list_view;

    private int SORT;
    private int TYPE;
    private int notifCount;
    private Foods foods;
    private ObservableList<Foods> foodsObservableList = FXCollections.observableArrayList();
    private DBHelper dbHelper = new DBHelper();
    private Connection connection;

    public MenuActivity() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nav_bottom.setVisible(false);
        nav_bottom.setManaged(false);
        btn_back.setOnAction(this::handle);
        btn_cart.setOnAction(this::handle);
        btn_pay.setOnAction(this::handle);

        buttonSelected(btn_food, btn_juice, btn_coffee, btn_snacks);
        btn_juice.setOnAction(event -> {
            buttonSelected(btn_juice, btn_coffee, btn_food, btn_snacks);
            loadJuice();
            refreshData();
        });
        btn_coffee.setOnAction(event -> {
            buttonSelected(btn_coffee, btn_food, btn_juice, btn_snacks);
            loadCoffee();
            refreshData();
        });
        btn_food.setOnAction(event -> {
            buttonSelected(btn_food, btn_juice, btn_coffee, btn_snacks);
            loadFood();
            refreshData();
        });
        btn_snacks.setOnAction(event -> {
            buttonSelected(btn_snacks, btn_food, btn_juice, btn_coffee);
            loadSnacks();
            refreshData();
        });
        btn_rating.setOnAction(event -> {
            SORT = 0;
            foodsObservableList = dbHelper.sortItemBy(connection, TYPE, SORT);
            refreshData();
            SORT = 1;
        });
        btn_price.setOnAction(event -> {
            if (SORT == 1) {
                changeGlyph();
                foodsObservableList = dbHelper.sortItemBy(connection, TYPE, SORT);
                refreshData();
            } else {
                changeGlyph();
                foodsObservableList = dbHelper.sortItemBy(connection, TYPE, SORT);
                refreshData();
            }
        });
        try {
            connection = dbHelper.getConnection();
            txt_price.setText(dbHelper.getTotalPay(connection) + "K");
            txt_qty.setText(String.valueOf(dbHelper.getTotalItems(connection)));
            txt_notif.setText(String.valueOf(dbHelper.getTotalItemCart(connection)));
        } catch (Exception e) {
            loadUI("/sample/layout/database_error_state.fxml", content_frame);
            System.out.println("MenuActivity Exc: " + e.getMessage());
        }
        if (dbHelper.getTotalItemCart(connection) != 0) {
            notifCount = dbHelper.getTotalItemCart(connection);
            notifAvaliable();
        } else {
            notifCount = 0;
            notifUnavailable();
        }
        loadFood();
        refreshData();
    }

    private void buttonSelected(JFXButton btnActive, JFXButton btnNon1, JFXButton btnNon2, JFXButton btnNon3) {
        btnActive.setStyle("-fx-background-color:#ffb600;-fx-background-radius:50");
        btnNon1.setStyle("-fx-background-radius:50;-fx-border-radius:50;-fx-border-color:#ffb600");
        btnNon2.setStyle("-fx-background-radius:50;-fx-border-radius:50;-fx-border-color:#ffb600");
        btnNon3.setStyle("-fx-background-radius:50;-fx-border-radius:50;-fx-border-color:#ffb600");
    }

    private void loadJuice() {
        TYPE = 2;
        foodsObservableList = dbHelper.getListItems(connection, TYPE);
    }

    private void loadSnacks() {
        TYPE = 1;
        foodsObservableList = dbHelper.getListItems(connection, TYPE);
    }

    private void loadFood() {
        TYPE = 0;
        foodsObservableList = dbHelper.getListItems(connection, TYPE);
    }

    private void loadCoffee() {
        TYPE = 3;
        foodsObservableList = dbHelper.getListItems(connection, TYPE);
    }

    private void changeGlyph() {
        if (icon_price.getGlyphName().equals("MENU_DOWN")) {
            SORT = 2;
            icon_price.setGlyphName("MENU_UP");
        } else {
            SORT = 1;
            icon_price.setGlyphName("MENU_DOWN");
        }
    }

    private void refreshData() {
        list_view.setItems(foodsObservableList);
        list_view.setCellFactory(foodListView -> new FoodAdapter(list_view, this, main_stackpane));
    }

    @Override
    public void handle(Event event) {
        EventTarget et = event.getTarget();
        if (et.equals(btn_back)) {
            loadUI("/sample/layout/table_choose_activity.fxml", main_frame);
        }
        if (et.equals(btn_cart)) {
            loadUI("/sample/layout/cart_activity.fxml", main_frame);
        }
        if (et.equals(btn_pay)) {
            int id = dbHelper.getIdCustomer(connection);
            int pay = dbHelper.getTotalPay(connection);
            int items = dbHelper.getTotalItems(connection);
            dbHelper.setCustomerPay(connection, items, pay, id);
            loadUI("/sample/layout/payment_activity.fxml", main_frame);
        }
    }

    @Override
    public void addItem() {
        if (notifCount > 0) {
            notifCount += 1;
            txt_notif.setText(String.valueOf(notifCount));
            txt_price.setText(dbHelper.getTotalPrice(connection) + "K");
            txt_qty.setText(String.valueOf(dbHelper.getTotalItems(connection)));
        } else {
            notifAvaliable();
            notifCount += 1;
            txt_price.setText(dbHelper.getTotalPay(connection) + "K");
            txt_qty.setText(String.valueOf(dbHelper.getTotalItems(connection)));
            txt_notif.setText(String.valueOf(notifCount));
        }
    }

    @Override
    public void minItem() {
        if (notifCount < 2) {
            notifUnavailable();
            notifCount -= 1;
        } else {
            notifCount -= 1;
            txt_notif.setText(String.valueOf(notifCount));
            txt_price.setText(dbHelper.getTotalPay(connection) + "K");
            txt_qty.setText(String.valueOf(dbHelper.getTotalItems(connection)));
        }
    }

    private void notifAvaliable() {
        notif_pane.setVisible(true);
        nav_bottom.setVisible(true);
        nav_bottom.setManaged(true);
        list_view.setPrefHeight(254);
    }

    private void notifUnavailable() {
        notif_pane.setVisible(false);
        nav_bottom.setVisible(false);
        nav_bottom.setManaged(false);
        list_view.setPrefHeight(294);
    }

    @Override
    public void loadUI(String layout, AnchorPane pane) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(layout));
            pane.getChildren().setAll(root);
        } catch (IOException ignored) {

        }
    }
}
