module com.catchabreak {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dustinredmond.fxtrayicon;

    opens com.catchabreak to javafx.fxml;
    exports com.catchabreak;
}
