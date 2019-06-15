package sample.helper;

import javafx.scene.layout.AnchorPane;

public interface UiLoaderCallback {
    void loadUI(String layout, AnchorPane pane);
}
