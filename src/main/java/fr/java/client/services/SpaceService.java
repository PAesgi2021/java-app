package fr.java.client.services;

import fr.java.client.entities.Space;
import fr.java.client.entities.User;
import fr.java.client.utils.types.Roles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SpaceService {

    private static SpaceService instance;
    private final TodolistService todolistService;

    private List<Space> spaces;
    private Space currentSpace;

    private SpaceService() {
        this.spaces = new ArrayList<>();
        this.addSpace(new Space("space_1", "private", "Touriste",  new User("root", "root", "henri", "name", Roles.Administrator)));
        this.addSpace(new Space("lol", "public", "Developer", new User("root", "root", "ahme", "", Roles.Client)));
        this.addSpace(new Space("miam", "public", "Developer", new User("root", "root", "mika", "", Roles.Client)));
        this.addSpace(new Space("gg", "public", "Developer", new User("root", "root", "test", "", Roles.Client)));
        this.addSpace(new Space("pace_5", "private", "Toilette", new User("root", "root", "prout", "", Roles.Client)));
        this.addSpace(new Space("space_1", "private", "Touriste", new User("root", "root", "henri", "name", Roles.Administrator)));
        this.addSpace(new Space("lol", "public", "Developer", new User("root", "root", "ahme", "", Roles.Client)));

        User myUser = new User("root", "root", "root", "root", LocalDateTime.now());
        Space testIsPresentWithRoot = new Space("space_1", "private", "Touriste", myUser);
        this.addSpace(testIsPresentWithRoot);


        this.todolistService = TodolistService.getInstance();
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


}
