package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import sample.entity.Foods;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuActivity implements Initializable, UiLoaderCallback {

    @FXML
    private AnchorPane main_frame;
    @FXML
    private JFXButton btn_rating;
    @FXML
    private JFXButton btn_price;
    @FXML
    private MaterialDesignIconView icon_price;
    @FXML
    private JFXButton btn_pay;
    @FXML
    private JFXButton btn_juice;
    @FXML
    private JFXButton btn_food;
    @FXML
    private JFXListView<Foods> list_view;
    @FXML
    private JFXButton btn_coffee;

    private ObservableList<Foods> foodsObservableList;

    public MenuActivity() {
        loadJuice();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_price.setOnAction(event -> {
            changeGlyph();
        });
        buttonSelected(btn_juice, btn_coffee, btn_food);
        btn_juice.setOnAction(event -> {
            buttonSelected(btn_juice, btn_coffee, btn_food);
            loadJuice();
            list_view.setItems(foodsObservableList);
            list_view.setCellFactory(foodListView -> new FoodListCell());
        });
        btn_coffee.setOnAction(event -> {
            buttonSelected(btn_coffee, btn_food, btn_juice);
            loadCoffee();
            list_view.setItems(foodsObservableList);
            list_view.setCellFactory(foodListView -> new FoodListCell());
        });
        btn_food.setOnAction(event -> {
            buttonSelected(btn_food, btn_juice, btn_coffee);
            loadFood();
            list_view.setItems(foodsObservableList);
            list_view.setCellFactory(foodListView -> new FoodListCell());
        });
        list_view.setItems(foodsObservableList);
        list_view.setCellFactory(foodListView -> new FoodListCell());


    }

    void buttonSelected(JFXButton btnActive, JFXButton btnNon1, JFXButton btnNon2){
        btnActive.setStyle("-fx-background-color:#ffb600;-fx-background-radius:50");
        btnNon1.setStyle("-fx-background-radius:50;-fx-border-radius:50;-fx-border-color:#ffb600");
        btnNon2.setStyle("-fx-background-radius:50;-fx-border-radius:50;-fx-border-color:#ffb600");
    }

    void loadJuice() {
        foodsObservableList = FXCollections.observableArrayList();
        foodsObservableList.addAll(
                new Foods("Jus Tomat", "soto ayam adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Jus Kambing", "soto Kambing adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Jus Kebo", "soto kebo adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Jus Pitik", "soto pitik adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Jus Sapi", "soto sapi adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5)
        );
    }

    void loadFood() {
        foodsObservableList = FXCollections.observableArrayList();
        foodsObservableList.addAll(
                new Foods("Soto Ayam", "soto ayam adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Soto Kambing", "soto Kambing adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Soto Kebo", "soto kebo adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Soto Pitik", "soto pitik adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Soto Sapi", "soto sapi adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5)
        );
    }

    void loadCoffee() {
        foodsObservableList = FXCollections.observableArrayList();
        foodsObservableList.addAll(
                new Foods("Kopi Tomat", "soto ayam adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Kopi Kambing", "soto Kambing adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Kopi Kebo", "soto kebo adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Kopi Pitik", "soto pitik adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Kopi Sapi", "soto sapi adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5)
        );
    }

    void changeGlyph() {
        if (icon_price.getGlyphName().equals("MENU_DOWN")) {
            icon_price.setGlyphName("MENU_UP");
        } else {
            icon_price.setGlyphName("MENU_DOWN");
        }
    }


    @Override
    public void loadUI(String layout, AnchorPane pane) {

    }
}
