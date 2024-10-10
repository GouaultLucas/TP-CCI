module com.gouaultlucas.tp5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.gouaultlucas.tp5 to javafx.fxml;
    exports com.gouaultlucas.tp5;
    exports com.gouaultlucas.tp5.controleurs;
    opens com.gouaultlucas.tp5.controleurs to javafx.fxml;
}