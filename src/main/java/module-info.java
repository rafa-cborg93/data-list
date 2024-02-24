module com.test.datalist {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires com.google.gson;

    opens com.test.datalist to javafx.fxml;
    exports com.test.datalist;
}