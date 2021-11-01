module frontendminiproject.frontendminiprojectdina {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens frontendminiproject.frontendminiprojectdina to javafx.fxml;
    exports frontendminiproject.frontendminiprojectdina;
}