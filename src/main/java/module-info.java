module com.catchabreak {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.catchabreak to javafx.fxml;
    exports com.catchabreak;
}
