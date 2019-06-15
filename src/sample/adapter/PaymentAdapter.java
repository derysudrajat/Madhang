package sample.adapter;

import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import sample.entity.Foods;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentAdapter extends ListCell<Foods> implements Initializable {

    @Override
    protected void updateItem(Foods item, boolean empty) {
        super.updateItem(item, empty);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
