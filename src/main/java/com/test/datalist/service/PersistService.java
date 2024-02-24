package com.test.datalist.service;

import com.test.datalist.model.DataConnection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PersistService {

    System.Logger logger = System.getLogger(PersistService.class.getName());

    public String persist(DataConnection dataConnection) {
        logger.log(System.Logger.Level.INFO, "PersistService.persist() - Persisting data connection to file.");

        String filePath = "src/main/java/com/test/datalist/data/" + dataConnection.getDatabase() + "-" + dataConnection.getAmbiente() + ".json";

        return persistToFile(dataConnection, filePath);
    }

    private String persistToFile(DataConnection dataConnection, String filePath) {
        logger.log(System.Logger.Level.INFO, "PersistService.persistToFile() - Persisting data into filePath={" + filePath + "}");

        String host = dataConnection.getHost();
        String port = dataConnection.getPort();
        String username = dataConnection.getUsername();
        String password = dataConnection.getPassword();
        String database = dataConnection.getDatabase();
        String ambiente = dataConnection.getAmbiente();

        String result = String.format("{\"host\":\"%s\",\"port\":\"%s\",\"username\":\"%s\",\"password\":\"%s\",\"database\":\"%s\",\"ambiente\":\"%s\"}", host, port, username, password, database, ambiente);

        try (FileWriter file = new FileWriter(filePath);
             BufferedWriter writer = new BufferedWriter(file);
             PrintWriter printWriter = new PrintWriter(writer);) {
            printWriter.println(result);
            logger.log(System.Logger.Level.INFO, "Persisted data: " + result);
            return "Dados persistidos com sucesso.";
        } catch (IOException e) {
            logger.log(System.Logger.Level.ERROR, "Error persisting data: " + e.getMessage());
            return "Erro ao persistir os dados.";
        }
    }
}
