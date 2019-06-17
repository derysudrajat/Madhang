package sample.adapter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import sample.entity.Foods;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartAdapter extends ListCell<Foods> implements Initializable {
    @FXML
    private AnchorPane list_frame;
    @FXML
    private AnchorPane img_frame;
    @FXML
    private Text txt_name;
    @FXML
    private Text txt_price;
    @FXML
    private JFXButton btn_add;
    @FXML
    private MaterialDesignIconView btn_add_icon;
    @FXML
    private JFXButton btn_min;
    @FXML
    private MaterialDesignIconView btn_add_icon1;
    private FXMLLoader mLLoader;
    private JFXListView<Foods> foodsListView;
    private Foods foods;

    public CartAdapter(JFXListView<Foods> foodsListView) {
        this.foodsListView = foodsListView;
    }

    @Override
    protected void updateItem(Foods item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/sample/layout/list_cart_item.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Rectangle mRec = new Rectangle(0, 0, 80, 110);
            mRec.setArcHeight(20.0);
            mRec.setArcWidth(20.0);
            ImagePattern pat = new ImagePattern(
                    new Image("/sample/mamam.jpg", 80, 110, false, false)
            );
            mRec.setFill(pat);
            img_frame.getChildren().add(mRec);
            txt_name.setText(item.getName());
            txt_price.setText(item.getPrice() + "K");
            setText(null);
            setGraphic(list_frame);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
