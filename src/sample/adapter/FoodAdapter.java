package sample.adapter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.controller.MenuActivity;
import sample.helper.NotifUpdaterCallback;
import sample.entity.Foods;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FoodAdapter extends ListCell<Foods> implements Initializable {
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
    private JFXListView<Foods> foodsListView;
    private Foods foods;
    private MenuActivity mContext;

    public FoodAdapter(JFXListView<Foods> foodsListView, MenuActivity mContext) {
        this.foodsListView = foodsListView;
        this.mContext = mContext;
    }

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
            try {
                foods = foodsListView.getSelectionModel().getSelectedItem();
                if (foods != null) {
                    if (btn_add_icon.getGlyphName().equals("PLUS")) {
                        btn_add.setStyle("-fx-background-color: #ffb600;" +
                                "-fx-background-radius:50;");
                        btn_add_icon.setGlyphName("CHECK");
                        btn_add_icon.setStyle("-fx-fill: #ffffff");
                        System.out.println(foods.getName() + " berhasil di tambahkan");
                        if (mContext instanceof MenuActivity) {
                            ((NotifUpdaterCallback) mContext).addItem();
                        }
                        //menuCategoryActivity.addItem();
                    } else {
                        btn_add.setStyle("-fx-background-color: #fff1d3;" +
                                "-fx-background-radius:50;");
                        btn_add_icon.setGlyphName("PLUS");
                        btn_add_icon.setStyle("-fx-fill: #522c2c");
                        System.out.println(foods.getName() + " batal di tambahkan");
                        ((NotifUpdaterCallback) mContext).minItem();
                        //menuCategoryActivity.minItem();
                    }
                }
            } catch (Exception e) {
                System.out.println("Please Select item first");
            }

        });
    }
}
