package sample.controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

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
    private JFXButton btn_coffee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_price.setOnAction(event -> {
            changeGlyph();
        });
        btn_juice.setOnAction(event -> {buttonSelected(btn_juice,btn_coffee,btn_food);});
        btn_coffee.setOnAction(event -> {buttonSelected(btn_coffee,btn_food,btn_juice);});
        btn_food.setOnAction(event -> {buttonSelected(btn_food,btn_juice,btn_coffee);});

    }

    void buttonSelected(JFXButton btnActive, JFXButton btnNon1, JFXButton btnNon2){
        btnActive.setStyle("-fx-background-color:#ffb600;-fx-background-radius:50");
        btnNon1.setStyle("-fx-background-radius:50;-fx-border-radius:50;-fx-border-color:#ffb600");
        btnNon2.setStyle("-fx-background-radius:50;-fx-border-radius:50;-fx-border-color:#ffb600");
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
