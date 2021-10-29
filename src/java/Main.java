//This is the package for the project, please keep using this.
package com.example.frontendclient;

//Imports.

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

//____________________________________________________________________________________-
// Main class.

public class Main extends Application
{
    //All buttons or other elements are defined here.
    @FXML
    private Button joinButton, connectButton, PBPlus1, PBPlus2, PBPlus3, PBMinus1, PBMinus2, PBMinus3;
    @FXML
    private TextField ipField, portField;

    public static void main(String[] args)
    {
        Jeoparty jeoparty = new Jeoparty(); //custom class, representing the application instance
        Scanner scanner = new Scanner(System.in); //handles input
        launch();

        while(true)
        {
            //> if this client has joined,
            if (isHost) {
                System.out.println("Give me name"); //prompt to provide name
                String userInput = scanner.next();

                if (!userInput.equals("")) { //only if the user sents something that is NOT blank space, the following
                    // lines will be executed
                    try {
                        jeoparty.sendMessage(userInput);    //the name is sent to the server
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(jeoparty.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //> if this client has not joined
            else {
                //> prompt and input
                System.out.println("Write start to join the server");
                String userInput = scanner.next();

                //> if the user writes "start"
                if (userInput.equals("start")) {
                    System.out.println("Write IP-Address: ");   //prompt to provide IP-address
                    String hostInput = scanner.next();
                    System.out.println("Write Port: "); //prompt to provide port
                    String portInput = scanner.next();
                    try {
                        jeoparty.joinServer(hostInput, Integer.parseInt(portInput));    //joins server, using the jeoparty
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // class' function .joinServer()
                    isHost = true;   //now the client has joined, and the relevant boolean is set to true
                }
            }
        }
    }


    //This is the main start function.
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader homePage = new FXMLLoader(Main.class.getResource("HomePage.fxml"));

        Scene homeScene = new Scene(homePage.load(), 1080, 600);

        stage.setTitle("Jeoparty");
        stage.setScene(homeScene);
        stage.show();
    }

    //
    @FXML
    private void joinButtonPress(ActionEvent event) throws Exception
    {
        Stage stage = (Stage) joinButton.getScene().getWindow();
        FXMLLoader ipPage = new FXMLLoader(Main.class.getResource("InputIPPage.fxml"));
        Scene ipScene = new Scene(ipPage.load(), 1080, 600);
        stage.setScene(ipScene);
        stage.show();
    }

    @FXML
    private void connectButtonPressed(ActionEvent event) throws Exception
    {
        String ipText = ipField.getText();
        int portText = Integer.parseInt(portField.getText());
        joinServer(ipText, portText; //we join the server with ip address, and port (typecasted from
        // string to int using Integer.parseInt())

        /*------------------------------
         --------------------------------*/

        Stage stage = (Stage) connectButton.getScene().getWindow();
        FXMLLoader nameSubmitPage = new FXMLLoader(Main.class.getResource("InputName.fxml"));
        Scene nameSubmitScene = new Scene(nameSubmitPage.load(), 1080, 600);
        stage.setScene(nameSubmitScene);
        stage.show();
    }

        /*if (event.getSource() == joinButton && !isHost)
        {
            FXMLLoader joinPage = new FXMLLoader(Main.class.getResource("JoinPage.fxml"));
            Scene joinScene = new Scene(joinPage.load(), 1080, 600);
            stage.setScene(joinScene);
            stage.show();
        }

        if (event.getSource() == joinButton && isHost)
        {
            FXMLLoader hostPage = new FXMLLoader(Main.class.getResource("HostPage.fxml"));
            Scene hostScene = new Scene(hostPage.load(), 1080, 600);
            stage.setScene(hostScene);
            stage.show();

        }
         */


    public void joinServer(String host, int port) throws IOException
    {
        Socket socket = new Socket(host,port);
    }
}