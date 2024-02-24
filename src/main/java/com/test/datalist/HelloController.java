package com.test.datalist;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class HelloController {

    @FXML
    private Pane pane;

    @FXML
    private void onMenuCreateClick() {
        loadFXML("create-view.fxml");
    }
    @FXML
    private void onMenuSearchClick() {
        loadFXML("search-view.fxml");
    }

    private void loadFXML(String fxmlFile) {
        try {
            // Carrega o arquivo FXML correspondente
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane newLoadedPane = loader.load();

            // Limpa o mainPane e define o novo conteúdo
            pane.getChildren().clear();
            pane.getChildren().add(newLoadedPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
