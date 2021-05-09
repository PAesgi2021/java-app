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
    opens fr.java.client.components.createTask;
    opens fr.java.client.components.todolist;
    exports fr.java.client.services;
    opens fr.java.client.services;
    exports fr.java.client.components.login;
    opens fr.java.client.components.login;
}