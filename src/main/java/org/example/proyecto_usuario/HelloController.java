package org.example.proyecto_usuario;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.io.*;
import java.net.Socket;

public class HelloController {

    @FXML TextField inputField;

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    private String username;

    @FXML
    public void initialize() {
        ConnectionDetails();
    }

    @FXML
    protected void handleSend() {

    }

    private void ConnectionDetails() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Connection Details");
        dialog.setHeaderText("Enter Server IP Address and Port Number");
        dialog.setContentText("IP Address:Port");

        String result = dialog.showAndWait().orElse(null);

        String[] parts = result.split(":");
        String serverAddress = parts[0];
        int port = Integer.parseInt(parts[1]);

        try {
            socket = new Socket(serverAddress, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            setUsername();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setUsername() throws IOException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nombre de usuario");
        dialog.setHeaderText("Por favor, ingrese su nombre de usuario:");

        username = dialog.showAndWait().orElse("Usuario");

        out.write(username + "\n");
        out.flush();
    }
}