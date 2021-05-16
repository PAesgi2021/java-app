package fr.java.client.services;

import fr.java.client.entities.Space;
import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;
import fr.java.client.entities.User;
import fr.java.client.utils.types.Roles;
import fr.java.client.utils.types.SpaceTab;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class SpaceService {

    private static SpaceService instance;
    private final TodolistService todolistService;

    private List<Space> spaces;
    private Space    currentSpace;
    private SpaceTab spaceTab;

    private SpaceService() {
        this.spaces = new ArrayList<>();
        this.todolistService = TodolistService.getInstance();

        this.templateMultipleSpaces();
    }

    public static SpaceService getInstance() {
        if (SpaceService.instance == null) {
            instance = new SpaceService();
            return instance;
        }
        return instance;
    }

    public void addSpace(Space space) {
        this.spaces.add(space);
    }

    public void addSpaces(List<Space> spaces) {
        for (Space space : spaces) {
            this.spaces.add(space);
        }
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(Space currentSpace) {
        this.currentSpace = currentSpace;
    }

    public TodolistService getTodolistService() {
        return todolistService;
    }

    public SpaceTab getSpaceTab() {
        return spaceTab;
    }

    public void setSpaceTab(SpaceTab spaceTab) {
        this.spaceTab = spaceTab;
    }

    private void templateMultipleSpaces() {
        this.addSpace(new Space("space_1gfd", "public", "Touriste",  new User("sds", "root", "henri", "name", Roles.Administrator)));
        this.addSpace(new Space("lol", "public", "Developer", new User("ds", "root", "ahme", "", Roles.Client)));
        this.addSpace(new Space("miam", "public", "Developer", new User("ds", "root", "mika", "", Roles.Client)));
        this.addSpace(new Space("gg", "public", "Developer", new User("ds", "root", "test", "", Roles.Client)));
        this.addSpace(new Space("pace_5", "public", "Toilette", new User("ds", "root", "prout", "", Roles.Client)));
        this.addSpace(new Space("space_1", "public", "Touriste", new User("ds", "root", "henri", "name", Roles.Administrator)));
        this.addSpace(new Space("lol", "public", "Developer", new User("ds", "root", "ahme", "", Roles.Client)));

        ////

        User myUser = new User("root", "root", "root", "root", LocalDateTime.parse("2021-05-16T02:05:14.175702300"));
        Space test1 = new Space("test1", "public", "Touriste", myUser);
        Space test2 = new Space("test2", "public", "Touriste", myUser);
        Space test3 = new Space("test3", "public", "Touriste", myUser);
        this.addSpaces(List.of(test1, test2, test3));

        ////

        User user = new User("root", "root", "root", "root", LocalDateTime.parse("2021-05-16T02:05:14.175702300"));
        Space space = new Space("template", "private", "Touriste", user);

        Todolist todolist = new Todolist("todolistExample");
        for (int i = 0; i < 8; i++) {
            todolist.addTask(new Task("String title", "String description", LocalDateTime.now().plus(10, ChronoUnit.DAYS)));
        }
        space.addTodolist(todolist);

        Todolist todolist2 = new Todolist("todolistExample2");
        for (int i = 0; i < 3; i++) {
            todolist2.addTask(new Task("String title2", "String description2", LocalDateTime.now().plus(10, ChronoUnit.DAYS)));
        }
        space.addTodolist(todolist2);

        this.addSpace(space);

        ////

    }

}
