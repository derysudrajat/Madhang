package sample.helper;

import com.jfoenix.controls.*;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.adapter.FoodAdapter;
import sample.controller.CartActivity;
import sample.controller.PaymentActivity;
import sample.entity.Cart;
import sample.entity.Customer;
import sample.entity.Foods;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class Popup implements Initializable {
    private CartActivity mContext;
    private DBHelper dbHelper = new DBHelper();
    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = dbHelper.getConnection();
        } catch (Exception e) {
            System.out.println(FoodAdapter.class.getSimpleName() + " Exc: " + e.getMessage());
        }
    }

    public void poup1Dialog(StackPane stackPane, String title, String body, String textConfirm) {
        try {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text(title));
            content.setBody(new Text(body));
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
            content.getStyleClass().addAll("dialog-style");
            JFXButton OkButton = new JFXButton(textConfirm);
            OkButton.getStyleClass().addAll("button-dialog");
            content.setActions(OkButton);
            OkButton.setOnAction(event1 -> {
                dialog.close();
            });
            dialog.show();
        } catch (Exception e) {
            System.out.println("No data Selected");
        }
    }

    public void confirmPayment(StackPane stackPane, PaymentActivity mContext, AnchorPane anchorPane, String title, String body, String textConfirm) {
        try {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text(title));
            content.setBody(new Text(body));
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
            content.getStyleClass().addAll("dialog-style");
            JFXButton OkButton = new JFXButton(textConfirm);
            OkButton.getStyleClass().addAll("button-dialog");
            content.setActions(OkButton);
            OkButton.setOnAction(event1 -> {
                if (mContext instanceof PaymentActivity) {
                    ((UiLoaderCallback) mContext).loadUI("/sample/layout/register_activity.fxml", anchorPane);
                }
                dialog.close();
            });
            dialog.show();
        } catch (Exception e) {
            System.out.println("No data Selected");
        }
    }

    public void poup2Dialog(StackPane stackPane, String tittle1, String body1, String textOk, String txtCancel, String tittle2, String body2, String txtConfirm) {
        try {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text(tittle1));
            content.setBody(new Text(body1));
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
            JFXButton cancelButton = new JFXButton(txtCancel);
            JFXButton OkButton = new JFXButton(textOk);
            content.getStyleClass().addAll("dialog-style");
            OkButton.getStyleClass().addAll("button-dialog");
            content.setActions(OkButton, cancelButton);
            cancelButton.setOnAction(event1 -> {
                dialog.close();
            });
            OkButton.setOnAction(event -> {
                poup1Dialog(stackPane, tittle2, body2, txtConfirm);
                dialog.close();
            });
            dialog.show();
        } catch (Exception e) {

        }
    }

    public void popMakeOrder(StackPane stackPane, PaymentActivity mContext, AnchorPane anchorPane, Customer mCustomer, String tittle1, String body1, String textOk, String txtCancel, String tittle2, String body2, String txtConfirm) {
        try {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text(tittle1));
            content.setBody(new Text(body1));
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
            JFXButton cancelButton = new JFXButton(txtCancel);
            JFXButton OkButton = new JFXButton(textOk);
            content.getStyleClass().addAll("dialog-style");
            OkButton.getStyleClass().addAll("button-dialog");
            content.setActions(OkButton, cancelButton);
            cancelButton.setOnAction(event1 -> {
                dialog.close();
            });
            OkButton.setOnAction(event -> {
                try {
                    connection = dbHelper.getConnection();
                } catch (Exception e) {
                    System.out.println("GetConnection Error");
                }
                dbHelper.addtoCustomerLog(connection, mCustomer); //trigger
                dbHelper.deleteLastCustomer(connection);
                dbHelper.deleteAllItemsCart(connection);
                confirmPayment(stackPane, mContext, anchorPane, tittle2, body2, txtConfirm);
                dialog.close();
            });
            dialog.show();
        } catch (Exception e) {

        }
    }

    public void delete2Dialog(StackPane stackPane, Cart mCart, CartActivity mContext, String tittle1, String body1, String textOk, String txtCancel, String tittle2, String body2, String txtConfirm) {
        try {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text(tittle1));
            content.setBody(new Text(body1));
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
            JFXButton cancelButton = new JFXButton(txtCancel);
            JFXButton OkButton = new JFXButton(textOk);
            content.getStyleClass().addAll("dialog-style");
            OkButton.getStyleClass().addAll("button-dialog");
            content.setActions(OkButton, cancelButton);
            cancelButton.setOnAction(event1 -> {
                dialog.close();
            });
            OkButton.setOnAction(event -> {
                try {
                    connection = dbHelper.getConnection();
                } catch (Exception e) {
                    System.out.println("GetConnection Error");
                }
                dbHelper.deleteItemsCartbyId(connection, mCart.getId());
                dbHelper.updateStatus(connection, mCart.getId(), 0);
                if (mContext instanceof CartActivity) {
                    ((ListChangedCallback) mContext).listChanged();
                }
                poup1Dialog(stackPane, tittle2, body2, txtConfirm);
                dialog.close();
            });
            dialog.show();
        } catch (Exception e) {

        }
    }

    public void deleteAllDialog(StackPane stackPane, CartActivity mContext, String tittle1, String body1, String textOk, String txtCancel, String tittle2, String body2, String txtConfirm) {
        try {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text(tittle1));
            content.setBody(new Text(body1));
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
            JFXButton cancelButton = new JFXButton(txtCancel);
            JFXButton OkButton = new JFXButton(textOk);
            content.getStyleClass().addAll("dialog-style");
            OkButton.getStyleClass().addAll("button-dialog");
            content.setActions(OkButton, cancelButton);
            cancelButton.setOnAction(event1 -> {
                dialog.close();
            });
            OkButton.setOnAction(event -> {
                try {
                    connection = dbHelper.getConnection();
                } catch (Exception e) {
                    System.out.println("GetConnection Error");
                }
                dbHelper.deleteAllItemsCart(connection);
                dbHelper.setStatustoDefault(connection);
                if (mContext instanceof CartActivity) {
                    ((ListChangedCallback) mContext).listChanged();
                }
                poup1Dialog(stackPane, tittle2, body2, txtConfirm);
                dialog.close();
            });
            dialog.show();
        } catch (Exception e) {

        }
    }

    public void poup2Menu(StackPane stackPane, JFXButton manuButton, String menu1, String menu2, Foods foods) {
        JFXPopup popup = new JFXPopup(manuButton);
        JFXButton btnMenu1 = new JFXButton(menu1);
        JFXButton btnMenu2 = new JFXButton(menu2);
        btnMenu1.setPadding(new Insets(10, 50, 10, 10));
        btnMenu2.setPadding(new Insets(10, 50, 10, 10));
        VBox vBox = new VBox(btnMenu1, btnMenu2);
        btnMenu1.setOnAction(event -> {
            if (foods != null) {
                poup2Dialog(stackPane, "Hapus Item", "Apakah anda ingin menghapus " + foods.getName(),
                        "IYA", "TIDAK", "Berhasil", foods.getName() + " berhasil di hapus", "OK");
            } else {
                toast(stackPane, "Silakan pilih item terlebih dahulu");
            }
        });
        btnMenu2.setOnAction(event -> {
            String message = "Menu 2 Pressed";
            System.out.println(message);
            poup2Dialog(stackPane, "Hapus Item", "Apakah anda ingin menghapus \nsemuaitem dalam keranjang?",
                    "IYA", "TIDAK", "Berhasil", "Item berhasil di hapus", "OK");
        });
        popup.setPopupContent(vBox);
        popup.setAutoHide(true);
        popup.show(manuButton, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
    }

    public void poup2MenuCart(StackPane stackPane, JFXButton manuButton, String menu1, String menu2, Cart mCart, CartActivity mContext) {
        JFXPopup popup = new JFXPopup(manuButton);
        JFXButton btnMenu1 = new JFXButton(menu1);
        JFXButton btnMenu2 = new JFXButton(menu2);
        this.mContext = mContext;
        btnMenu1.setPadding(new Insets(10, 50, 10, 10));
        btnMenu2.setPadding(new Insets(10, 50, 10, 10));
        VBox vBox = new VBox(btnMenu1, btnMenu2);
        // Delete Button
        btnMenu1.setOnAction(event -> {
            if (mCart != null) {
                delete2Dialog(stackPane, mCart, mContext, "Hapus Item", "Apakah anda ingin menghapus " + mCart.getName(),
                        "IYA", "TIDAK", "Berhasil", mCart.getName() + " berhasil di hapus", "OK");
            } else {
                toast(stackPane, "Silakan pilih item terlebih dahulu");
            }
        });
        // Delete All Button
        btnMenu2.setOnAction(event -> {
            String message = "Menu 2 Pressed";
            System.out.println(message);
            deleteAllDialog(stackPane, mContext, "Hapus Item", "Apakah anda ingin menghapus \nsemuaitem dalam keranjang?",
                    "IYA", "TIDAK", "Berhasil", "Item berhasil di hapus", "OK");
        });
        popup.setPopupContent(vBox);
        popup.setAutoHide(true);
        popup.show(manuButton, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
    }

    public void poupMenu(StackPane stackPane, JFXButton manuButton, String menu, Foods foods) {
        JFXPopup popup = new JFXPopup(manuButton);
        JFXButton btnMenu = new JFXButton(menu);
        btnMenu.setPadding(new Insets(10, 50, 10, 10));
        VBox vBox = new VBox(btnMenu);
        btnMenu.setOnAction(event -> {
            poup2Dialog(stackPane, "Hapus Item", "Apakah anda ingin menghapus " + foods.getName() + "?",
                    "IYA", "TIDAK", "Berhasil", foods.getName() + " berhasil di hapus", "OK");
        });
        popup.setPopupContent(vBox);
        popup.setAutoHide(true);
        popup.show(manuButton, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
    }

    public void poupMenuCart(StackPane stackPane, JFXButton manuButton, String menu, Cart mCart, CartActivity mContext) {
        JFXPopup popup = new JFXPopup(manuButton);
        JFXButton btnMenu = new JFXButton(menu);
        this.mContext = mContext;
        btnMenu.setPadding(new Insets(10, 50, 10, 10));
        VBox vBox = new VBox(btnMenu);
        //Delete Button
        btnMenu.setOnAction(event -> {
            delete2Dialog(stackPane, mCart, mContext, "Hapus Item", "Apakah anda ingin menghapus " + mCart.getName() + "?",
                    "IYA", "TIDAK", "Berhasil", mCart.getName() + " berhasil di hapus", "OK");
        });
        popup.setPopupContent(vBox);
        popup.setAutoHide(true);
        popup.show(manuButton, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
    }

    public void toast(StackPane stackPane, String message) {
        Text error = new Text(message);
        error.setFill(Color.WHITE);
        JFXSnackbar snackbar = new JFXSnackbar(stackPane);
        snackbar.getStyleClass().addAll("jfx-snackbar-content");
        snackbar.enqueue(new JFXSnackbar.SnackbarEvent(error));
    }


}
