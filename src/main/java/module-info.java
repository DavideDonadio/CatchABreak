module com.catchabreak {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dustinredmond.fxtrayicon;
    requires java.sql;
    requires java.prefs;
    requires java.desktop;

    opens com.catchabreak to javafx.fxml;
    exports com.catchabreak;
}
