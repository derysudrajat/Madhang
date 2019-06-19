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
import sample.controller.MenuActivity;
import sample.entity.Foods;
import sample.helper.ImageLoaderCallback;
import sample.helper.NotifUpdaterCallback;
import sample.helper.Popup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FoodAdapter extends ListCell<Foods> implements Initializable, ImageLoaderCallback {
    @FXML
    private AnchorPane list_frame;
    @FXML
    private AnchorPane img_frame;
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
    private StackPane mStackpane;
    private Popup pop = new Popup();

    public FoodAdapter(JFXListView<Foods> foodsListView, MenuActivity mContext, StackPane mStackPane) {
        this.foodsListView = foodsListView;
        this.mContext = mContext;
        this.mStackpane = mStackPane;
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
            String path = "/sample/res/food/miegoreng.jpg";
            img_frame.getChildren().add(loadImage(path));
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
                        pop.toast(mStackpane, foods.getName() + " telah ditambahkan");
                    } else {
                        btn_add.setStyle("-fx-background-color: #fff1d3;" +
                                "-fx-background-radius:50;");
                        btn_add_icon.setGlyphName("PLUS");
                        btn_add_icon.setStyle("-fx-fill: #522c2c");
                        System.out.println(foods.getName() + " batal di tambahkan");
                        ((NotifUpdaterCallback) mContext).minItem();
                        pop.toast(mStackpane, foods.getName() + " batal ditambahkan");
                    }
                } else {
                    pop.toast(mStackpane, "Please Select item first");
                    System.out.println("Please Select item first");
                }
            } catch (Exception e) {
                pop.toast(mStackpane, "Please Select item first");
                System.out.println("Please Select item first");
            }

        });
    }


    @Override
    public ImageView loadImage(String url) {
        ImageView imgRes = new ImageView(url);
        double newMeasure = (imgRes.getImage().getWidth() < imgRes.getImage().getHeight()) ?
                imgRes.getImage().getWidth() :
                imgRes.getImage().getHeight();
        double x = (imgRes.getImage().getWidth() - newMeasure) / 2;
        double y = (imgRes.getImage().getHeight() - newMeasure) / 2;
        Rectangle2D rectangle = new Rectangle2D(x, y, newMeasure, newMeasure);
        Rectangle rect = new Rectangle(0, 0, 115, 160);
        rect.setArcHeight(20.0);
        rect.setArcWidth(20.0);
        imgRes.setViewport(rectangle);
        imgRes.setClip(rect);
        imgRes.setFitWidth(115);
        imgRes.setFitHeight(160);
        imgRes.setSmooth(true);
        return imgRes;
    }
}
