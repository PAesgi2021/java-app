module fr.java.client {
    requires javafx.controls;
    requires javafx.fxml;

    // exports
    exports fr.java.client;
    exports fr.java.client.components.todolist;
    exports fr.java.client.components.createList;
    exports fr.java.client.components.createTask;
    exports fr.java.client.components.profile;
    exports fr.java.client.entities;
    exports fr.java.client.services;
    exports fr.java.client.components.login;
    exports fr.java.client.components.taskConfig;
    exports fr.java.client.components.register;
    exports fr.java.client.components.space;
    exports fr.java.client.components.spaceCard;
    exports fr.java.client.components.createSpace;

    // opens
    opens fr.java.client.components.createList;
    opens fr.java.client.components.createTask;
    opens fr.java.client.components.todolist;
    opens fr.java.client.services;
    opens fr.java.client.components.login;
    opens fr.java.client.components.taskConfig;
    opens fr.java.client.components.profile;
    opens fr.java.client.components.register;
    opens fr.java.client.components.space;
    opens fr.java.client.components.spaceCard;
    opens fr.java.client.components.createSpace;

}