module core {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;

    opens core to javafx.fxml;
    opens core.menuControllers to javafx.fxml;
    exports core;
}


