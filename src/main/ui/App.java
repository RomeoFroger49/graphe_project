package main.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Graph Viewer");
        stage.setScene(new Scene(new StackPane(new Label("Hello JavaFX")), 800, 600));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
