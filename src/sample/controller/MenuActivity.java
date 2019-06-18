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
import sample.helper.NotifUpdaterCallback;
import sample.helper.UiLoaderCallback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuActivity implements Initializable, UiLoaderCallback, EventHandler, NotifUpdaterCallback {

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
    private JFXButton btn_food;
    @FXML
    private JFXButton btn_snacks;
    @FXML
    private JFXButton btn_juice;
    @FXML
    private JFXButton btn_coffee;
    @FXML
    private JFXListView<Foods> list_view;

    private int notifCount;
    private Foods foods;

    private ObservableList<Foods> foodsObservableList;

    public MenuActivity() {
        loadFood();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_back.setOnAction(this::handle);
        btn_cart.setOnAction(this::handle);
        btn_pay.setOnAction(this::handle);
        notifCount = 0;
        btn_price.setOnAction(event -> changeGlyph());
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
        refreshData();
    }

    private void buttonSelected(JFXButton btnActive, JFXButton btnNon1, JFXButton btnNon2, JFXButton btnNon3) {
        btnActive.setStyle("-fx-background-color:#ffb600;-fx-background-radius:50");
        btnNon1.setStyle("-fx-background-radius:50;-fx-border-radius:50;-fx-border-color:#ffb600");
        btnNon2.setStyle("-fx-background-radius:50;-fx-border-radius:50;-fx-border-color:#ffb600");
        btnNon3.setStyle("-fx-background-radius:50;-fx-border-radius:50;-fx-border-color:#ffb600");
    }

    private void loadJuice() {
        foodsObservableList = FXCollections.observableArrayList();
        foodsObservableList.addAll(
                new Foods("Jus Tomat", "soto ayam adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Jus Kambing", "soto Kambing adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Jus Kebo", "soto kebo adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Jus Pitik", "soto pitik adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Jus Sapi", "soto sapi adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5)
        );
    }

    private void loadSnacks() {
        foodsObservableList = FXCollections.observableArrayList();
        foodsObservableList.addAll(
                new Foods("Snack Tomat", "soto ayam adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Snack Kambing", "soto Kambing adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Snack Kebo", "soto kebo adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Snack Pitik", "soto pitik adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Snack Sapi", "soto sapi adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5)
        );
    }

    private void loadFood() {
        foodsObservableList = FXCollections.observableArrayList();
        foodsObservableList.addAll(
                new Foods("Soto Ayam", "soto ayam adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Soto Kambing", "soto Kambing adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Soto Kebo", "soto kebo adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Soto Pitik", "soto pitik adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Soto Sapi", "soto sapi adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5)
        );
    }

    private void loadCoffee() {
        foodsObservableList = FXCollections.observableArrayList();
        foodsObservableList.addAll(
                new Foods("Kopi Tomat", "soto ayam adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Kopi Kambing", "soto Kambing adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Kopi Kebo", "soto kebo adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Kopi Pitik", "soto pitik adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Kopi Sapi", "soto sapi adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5)
        );
    }

    private void changeGlyph() {
        if (icon_price.getGlyphName().equals("MENU_DOWN")) {
            icon_price.setGlyphName("MENU_UP");
        } else {
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
            loadUI("/sample/layout/payment_activity.fxml", main_frame);
        }
    }

    @Override
    public void addItem() {
        if (notifCount > 0) {
            notifCount += 1;
            txt_notif.setText(String.valueOf(notifCount));
        } else {
            notif_pane.setVisible(true);
            notifCount += 1;
            txt_notif.setText(String.valueOf(notifCount));
        }
    }

    @Override
    public void minItem() {
        if (notifCount < 2) {
            notif_pane.setVisible(false);
            notifCount -= 1;
        } else {
            notifCount -= 1;
            txt_notif.setText(String.valueOf(notifCount));
        }
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
