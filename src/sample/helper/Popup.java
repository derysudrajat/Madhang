package sample.helper;

import com.jfoenix.controls.*;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Popup {
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

    public void poup2Menu(StackPane stackPane, JFXButton manuButton, String menu1, String menu2) {
        JFXPopup popup = new JFXPopup(manuButton);
        JFXButton btnMenu1 = new JFXButton(menu1);
        JFXButton btnMenu2 = new JFXButton(menu2);
        btnMenu1.setPadding(new Insets(10, 50, 10, 10));
        btnMenu2.setPadding(new Insets(10, 50, 10, 10));
        VBox vBox = new VBox(btnMenu1, btnMenu2);
        btnMenu1.setOnAction(event -> {
            String message = "Menu 1 Pressed";
            System.out.println(message);
            poup1Dialog(stackPane, menu1, message, "OK");
        });
        btnMenu2.setOnAction(event -> {
            String message = "Menu 2 Pressed";
            System.out.println(message);
            poup1Dialog(stackPane, menu2, message, "OK");
        });
        popup.setPopupContent(vBox);
        popup.setAutoHide(true);
        popup.show(manuButton, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
    }

    public void poupMenu(StackPane stackPane, JFXButton manuButton, String menu) {
        JFXPopup popup = new JFXPopup(manuButton);
        JFXButton btnMenu = new JFXButton(menu);
        btnMenu.setPadding(new Insets(10, 50, 10, 10));
        VBox vBox = new VBox(btnMenu);
        btnMenu.setOnAction(event -> {
            String message = "Menu 1 Pressed";
            System.out.println(message);
            poup1Dialog(stackPane, menu, message, "OK");
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
