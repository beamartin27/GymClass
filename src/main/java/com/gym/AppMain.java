package com.gym;

import com.gym.repository.sqlite.SqliteDatabaseManager;
import com.gym.ui.utils.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // 1) Make sure DB/schema exists
        SqliteDatabaseManager.initializeDatabase();
        AppConfig.init();

        // 2) Store primary stage in SceneManager (we'll create it in step 3)
        SceneManager.setPrimaryStage(stage);

        // 3) Show login screen
        SceneManager.switchTo("/views/login.fxml", "Gym Class Booking - Login");
    }

    public static void main(String[] args) {
        launch();
    }
}
