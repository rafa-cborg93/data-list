package com.test.datalist;

import com.test.datalist.model.DataConnection;
import com.test.datalist.service.PersistService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SearchViewController {

    @FXML
    private TextField txt_search_database;
    @FXML
    private ChoiceBox<String> select_search_ambiente;
    @FXML
    private Label txt_host;
    @FXML
    private Label txt_port;
    @FXML
    private Label txt_username;
    @FXML
    private Label txt_password;
    @FXML
    private Label txt_database;
    @FXML
    private Label txt_error;

    private PersistService persistService;

    public SearchViewController() {
        this.persistService = new PersistService();
    }

    @FXML
    protected void initialize() {
        select_search_ambiente.setItems(FXCollections.observableArrayList("DEV", "STG", "PRD"));
    }

    @FXML
    protected void onSearchButtonClick() {
        String database = txt_search_database.getText();
        String ambiente = select_search_ambiente.getValue();

        Object result = this.persistService.getDataConnection(database, ambiente);

        if (result instanceof DataConnection) {
            showDataConnection((DataConnection) result);
        } else {
            txt_error.setText(result.toString());
        }
    }

    private void showDataConnection(DataConnection result) {
        txt_host.setText(result.getHost());
        txt_port.setText(result.getPort());
        txt_username.setText(result.getUsername());
        txt_password.setText(result.getPassword());
        txt_database.setText(result.getDatabase());
    }
}
