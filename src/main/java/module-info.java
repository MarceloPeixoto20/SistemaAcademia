module com.mycompany.projetoacademia {
    requires javafx.controls;
    requires java.base;
    requires java.sql;
    requires javafx.fxml;
    exports com.mycompany.projetoacademia;
    opens Controller.TelaLoginController;

}
