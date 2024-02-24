package com.test.datalist;

import com.test.datalist.model.DataConnection;
import com.test.datalist.service.PersistService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateViewController {

    @FXML
    private TextField txt_host;
    @FXML
    private Label lbl_host;
    @FXML
    private TextField txt_port;
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_database;
    @FXML
    private ChoiceBox<String> select_ambiente;
    @FXML
    private Button btn_submit;
    @FXML
    private Label welcomeText;

    private PersistService persistService;
    System.Logger logger = System.getLogger(HelloController.class.getName());

    public CreateViewController() {
        this.persistService = new PersistService();
    }

    @FXML
    protected void initialize() {
        select_ambiente.setItems(FXCollections.observableArrayList("DEV", "STG", "PRD"));
    }

    @FXML
    protected void onSubmitButtonClick() {
        String host = txt_host.getText();
        String port = txt_port.getText();
        String username = txt_username.getText();
        String password = txt_password.getText();
        String database = txt_database.getText();
        String ambiente = select_ambiente.getValue();
        DataConnection dataConnection = new DataConnection(host, port, username, password, database, ambiente);

        String result = this.persistService.persist(dataConnection);
        logger.log(System.Logger.Level.INFO, "Persisted data: " + result);
        clearFields();
        welcomeText.setText(result);
    }

    private void clearFields() {
        txt_host.clear();
        txt_port.clear();
        txt_username.clear();
        txt_password.clear();
        txt_database.clear();
        select_ambiente.setValue(null);
    }
}
