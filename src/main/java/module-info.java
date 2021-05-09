module fr.java.client {
    requires javafx.controls;
    requires javafx.fxml;

    // exports
    exports fr.java.client;
    exports fr.java.client.components.todolist;
    exports fr.java.client.components.createList;
    exports fr.java.client.components.createTask;
    exports fr.java.client.entities;

    // opens
    opens fr.java.client.components.createList;
    opens fr.java.client.components.todolist;
    exports fr.java.client.Service;
    opens fr.java.client.Service;
    exports fr.java.client.components.login;
    opens fr.java.client.components.login;
}