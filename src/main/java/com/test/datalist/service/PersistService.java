package com.test.datalist.service;

import com.google.gson.Gson;
import com.test.datalist.model.DataConnection;

import java.io.*;

public class PersistService {

    private Gson gson;
    System.Logger logger = System.getLogger(PersistService.class.getName());

    public PersistService() {
        this.gson = new Gson();
    }

    public String persist(DataConnection dataConnection) {
        logger.log(System.Logger.Level.INFO, "PersistService.persist() - Persisting data connection to file.");

        String filePath = "src/main/java/com/test/datalist/data/" + dataConnection.getDatabase() + "-" + dataConnection.getAmbiente() + ".json";

        return persistToFile(dataConnection, filePath);
    }

    public Object getDataConnection(String database, String ambiente) {
        logger.log(System.Logger.Level.INFO, "PersistService.getDataConnection() - Getting data connection from file.");
        String filePath = "src/main/java/com/test/datalist/data/" + database + "-" + ambiente + ".json";

        return getDataConnectionFromFile(filePath);
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

    private Object getDataConnectionFromFile(String filePath) {
        logger.log(System.Logger.Level.INFO, "PersistService.getDataConnectionFromFile() - Getting data from filePath={" + filePath + "}");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Ler o conteúdo do arquivo JSON como uma string
            StringBuilder jsonContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            // Converter a string JSON de volta para um objeto DataConnection usando Gson
            DataConnection dataConnection = gson.fromJson(jsonContent.toString(), DataConnection.class);

            logger.log(System.Logger.Level.INFO, "DataConnection: " + dataConnection);
            return dataConnection;
        } catch (IOException e) {
            logger.log(System.Logger.Level.ERROR, "Error getting data: " + e.getMessage());
            return "Error:" + e.getMessage();
        }
    }
}
