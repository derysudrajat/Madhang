package sample.adapter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import sample.entity.Foods;
import sample.helper.ImageLoaderCallback;
import sample.helper.Popup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartAdapter extends ListCell<Foods> implements Initializable, ImageLoaderCallback {
    @FXML
    private AnchorPane list_frame;
    @FXML
    private AnchorPane img_frame;
    @FXML
    private Text txt_name;
    @FXML
    private Text txt_qty;
    @FXML
    private Text txt_price;
    @FXML
    private JFXButton btn_menu;
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
    private StackPane mStackPane;
    private Popup pop = new Popup();
    private int mPrice;
    private int mQyt;

    public CartAdapter(JFXListView<Foods> foodsListView, StackPane mStackPane) {
        this.foodsListView = foodsListView;
        this.mStackPane = mStackPane;
    }

    @Override
    protected void updateItem(Foods item, boolean empty) {
        foods = item;
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
            String url = "/sample/res/juice/jusjeruk.png";
            img_frame.getChildren().add(loadImage(url));
            mPrice = item.getPrice();
            txt_name.setText(item.getName());
            txt_price.setText(item.getPrice() + "K");
            setText(null);
            setGraphic(list_frame);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mQyt = 1;
        btn_menu.setOnAction(event -> {
            foods = foodsListView.getSelectionModel().getSelectedItem();
            pop.poupMenu(mStackPane, btn_menu, "Delete");
        });
        btn_add.setOnAction(event -> {
            mQyt += 1;
            txt_qty.setText(String.valueOf(mQyt));
            mPrice = foods.getPrice() * mQyt;
            txt_price.setText(mPrice + "K");
        });
        btn_min.setOnAction(event -> {
            if (mQyt < 2) {
                pop.poup2Dialog(mStackPane, "Hapus Item", "Apakah anda ingin menghapus item ini?",
                        "IYA", "TIDAK", "Berhasil", "Item berhasil di hapus", "OK");
            } else {
                mQyt -= 1;
                txt_qty.setText(String.valueOf(mQyt));
                mPrice = foods.getPrice() * mQyt;
                txt_price.setText(mPrice + "K");
            }
        });
    }

    @Override
    public ImageView loadImage(String url) {
        ImageView imgResult = new ImageView(url);
        double newMeasure =
                (imgResult.getImage().getWidth() < imgResult.getImage().getHeight()) ?
                        imgResult.getImage().getWidth() :
                        imgResult.getImage().getHeight();
        double x = (imgResult.getImage().getWidth() - newMeasure) / 2;
        double y = (imgResult.getImage().getHeight() - newMeasure) / 2;
        Rectangle2D recRatio = new Rectangle2D(x, y, newMeasure, newMeasure);
        Rectangle recRaduis = new Rectangle(0, 0, 80, 110);
        recRaduis.setArcHeight(20.0);
        recRaduis.setArcWidth(20.0);
        imgResult.setViewport(recRatio);
        imgResult.setClip(recRaduis);
        imgResult.setFitWidth(80);
        imgResult.setFitHeight(110);
        imgResult.setSmooth(true);
        return imgResult;
    }

}
