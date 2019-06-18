package sample.helper;

import com.jfoenix.controls.JFXSnackbar;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Popup {
    public void toast(StackPane stackPane, String message){
        Text error = new Text(message);
        error.setFill(Color.WHITE);
        JFXSnackbar snackbar = new JFXSnackbar(stackPane);
        snackbar.getStyleClass().addAll("jfx-snackbar-content");
        snackbar.enqueue(new JFXSnackbar.SnackbarEvent(error));
    }
}
