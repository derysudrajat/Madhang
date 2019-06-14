package sample.controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.entity.Foods;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FoodListCell extends ListCell<Foods> implements Initializable {
    @FXML
    private AnchorPane list_frame;
    @FXML
    private Text txt_name;
    @FXML
    private Text txt_desc;
    @FXML
    private Text txt_price;
    @FXML
    private Text txt_rate;
    @FXML
    private JFXButton btn_add;

    @FXML
    private MaterialDesignIconView btn_add_icon;

    private FXMLLoader mLLoader;


    @Override
    protected void updateItem(Foods foods, boolean empty) {
        super.updateItem(foods, empty);
        if (empty || foods == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/sample/layout/list_cell_item.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            txt_name.setText(foods.getName());
            txt_desc.setText(foods.getDesc());
            txt_price.setText(foods.getPrice() + "K");
            txt_rate.setText(foods.getRate() + "");
            setText(null);
            setGraphic(list_frame);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_add.setOnAction(event -> {
            if (btn_add_icon.getGlyphName() == "PLUS") {
                btn_add.setStyle("-fx-background-color: #ffb600;" +
                        "-fx-background-radius:50;");
                btn_add_icon.setGlyphName("CHECK");
                btn_add_icon.setStyle("-fx-fill: #ffffff");
            } else {
                btn_add.setStyle("-fx-background-color: #fff1d3;" +
                        "-fx-background-radius:50;");
                btn_add_icon.setGlyphName("PLUS");
                btn_add_icon.setStyle("-fx-fill: #522c2c");
            }
        });
    }
}
