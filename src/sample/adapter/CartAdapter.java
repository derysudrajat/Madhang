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
import sample.controller.CartActivity;
import sample.entity.Cart;
import sample.helper.DBHelper;
import sample.helper.ImageLoaderCallback;
import sample.helper.Popup;
import sample.helper.SummaryOrderCallback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class CartAdapter extends ListCell<Cart> implements Initializable, ImageLoaderCallback {
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
    private JFXListView<Cart> cartsListView;
    private Cart carts;
    private StackPane mStackPane;
    private Popup pop = new Popup();
    private CartActivity mContext;
    private int mPrice;
    private int mQyt;
    private DBHelper dbHelper = new DBHelper();
    private Connection connection;

    public CartAdapter(JFXListView<Cart> cartsListView, StackPane mStackPane, CartActivity mContext) {
        this.cartsListView = cartsListView;
        this.mStackPane = mStackPane;
        this.mContext = mContext;
    }

    @Override
    protected void updateItem(Cart item, boolean empty) {
        carts = item;
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
            String url = item.getImg();
            img_frame.getChildren().add(loadImage(url));
            mPrice = item.getTotalPrice();
            txt_name.setText(item.getName());
            txt_price.setText(item.getTotalPrice() * item.getQty() + "K");
            txt_qty.setText(String.valueOf(item.getQty()));
            setText(null);
            setGraphic(list_frame);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mQyt = 1;
        btn_menu.setOnAction(event -> {
            try {
                carts = cartsListView.getSelectionModel().getSelectedItem();
                if (carts != null) {
                    pop.poupMenuCart(mStackPane, btn_menu, "Delete", carts, mContext);
                } else {
                    pop.toast(mStackPane, "Silakan pilih item terlebih dahulu");
                }
            } catch (Exception e) {
                pop.toast(mStackPane, "Silakan pilih item terlebih dahulu");
            }
        });
        btn_add.setOnAction(event -> {
            try {
                carts = cartsListView.getSelectionModel().getSelectedItem();
                if (carts != null) {
                    mQyt += 1;                    
                    txt_qty.setText(String.valueOf(mQyt));
                    mPrice = carts.getTotalPrice() * mQyt;
                    txt_price.setText(mPrice + "K");

                    carts.setQty(mQyt);

                    dbHelper.updateQty(connection, carts);
                    if (mContext instanceof CartActivity) {
                        ((SummaryOrderCallback) mContext).summaryChanged();
                    }
                } else {
                    pop.toast(mStackPane, "Silakan pilih item terlebih dahulu");
                }
            } catch (Exception e) {
                pop.toast(mStackPane, "Silakan pilih item terlebih dahulu");
            }
        });
        btn_min.setOnAction(event -> {
            try {
                carts = cartsListView.getSelectionModel().getSelectedItem();
                if (carts != null) {
                    if (mQyt < 2) {
                        pop.poup2Dialog(mStackPane, "Hapus Item", "Apakah anda ingin menghapus item ini?",
                                "IYA", "TIDAK", "Berhasil", "Item berhasil di hapus", "OK");
                    } else {
                        mQyt -= 1;
                        txt_qty.setText(String.valueOf(mQyt));
                        mPrice = carts.getTotalPrice() * mQyt;
                        txt_price.setText(mPrice + "K");

                        carts.setQty(mQyt);

                        dbHelper.updateQty(connection, carts);
                        if (mContext instanceof CartActivity) {
                            ((SummaryOrderCallback) mContext).summaryChanged();
                        }
                    }
                } else {
                    pop.toast(mStackPane, "Silakan pilih item terlebih dahulu");
                }
            } catch (Exception e) {
                pop.toast(mStackPane, "Silakan pilih item terlebih dahulu");
            }
        });
        try {
            connection = dbHelper.getConnection();
        } catch (Exception e) {
            System.out.println(FoodAdapter.class.getSimpleName() + " Exc: " + e.getMessage());
        }
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
