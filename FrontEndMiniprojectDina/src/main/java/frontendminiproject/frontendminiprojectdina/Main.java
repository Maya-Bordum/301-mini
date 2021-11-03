//This is the package for the project, please keep using this.
package frontendminiproject.frontendminiprojectdina;

//Imports.
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//____________________________________________________________________________________-
// Main class.

public class Main extends Application
{

    //____________________________ Global data etc. __________________________

    //All buttons or other elements are defined here.
    @FXML
    private Button joinButton, connectButton, submitButton;
    @FXML
    private TextField ipField, portField, nameField, playerOneTF, playerTwoName;
    DataOutputStream output;
    DataInputStream input;
    static Socket clientSocket;
    static Boolean isHost = false;
    

    //___________________________ Main Start Method _________________________

    public static void main(String[] args)
    {
        launch();
    }


    //_______________________ FXML Scene shifts and Button Action methods _________________________

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
        FXMLLoader ipPage = new FXMLLoader(Main.class.getResource("ConnectIPPage.fxml"));
        Scene ipScene = new Scene(ipPage.load(), 1080, 600);
        stage.setScene(ipScene);
        stage.show();
    }

    //Button action method that happens when the button on ConnectIPPage is pressed.
    @FXML
    private void connectButtonPressed(ActionEvent event) throws Exception
    {
        String ipText = ipField.getText(); //The IP is gotten from the first text field in ConnectIPPage.
        int portText = Integer.parseInt(portField.getText()); //The port is gotten from the second text field, and typecast/changed to an integer.
        joinServer(ipText, portText); //we join the server with ip address, and port (The port is typecast/changed to an integer by using parseInt.

        //___________________________________________________________

        Stage stage = (Stage) connectButton.getScene().getWindow();
        FXMLLoader nameSubmitPage = new FXMLLoader(Main.class.getResource("InputName.fxml"));
        Scene nameSubmitScene = new Scene(nameSubmitPage.load(), 1080, 600);
        stage.setScene(nameSubmitScene);
        stage.show();
    }

    //Method for when clicking submit in the naming screen. Also sends the written name to the server.
    @FXML
    private void goToLobby(ActionEvent event) throws Exception
    {

        Stage stage = (Stage) submitButton.getScene().getWindow();
        if (event.getSource() == submitButton && !isHost)
        {
            System.out.println("loading host not");
            FXMLLoader joinPage = new FXMLLoader(Main.class.getResource("JoinPage.fxml"));
            Scene joinScene = new Scene(joinPage.load(), 1080, 600);
            stage.setScene(joinScene);
            stage.show();
            sendNameInput();
        }

        if (event.getSource() == submitButton && isHost)
        {
            System.out.println("loading host plage");
            FXMLLoader hostPage = new FXMLLoader(Main.class.getResource("HostPage.fxml"));
            Scene hostScene = new Scene(hostPage.load(), 1080, 600);
            stage.setScene(hostScene);
            stage.show();
            sendNameInput();

        }

    }

    //____________________________ Other important methods _______________________

    //A small method that takes a string host and integer port, and tries joining the server based on the text gotten from the user in ConnectIPPage.fxml.
    public void joinServer(String host, int port) throws IOException
    {
        Socket socket = new Socket(host,port);
        input = new DataInputStream(socket.getInputStream());
        isHost = input.readBoolean();
        System.out.println(isHost);
        setSocket(socket);
    }
    public void setSocket(Socket socket)
    {
        clientSocket = socket;
    }

    public void sendNameInput() throws IOException
    {
        System.out.println("Socket:" + clientSocket);
        System.out.println("Name:" + nameField.getText());
        output = new DataOutputStream(clientSocket.getOutputStream());
        output.writeUTF(nameField.getText());
    }
}