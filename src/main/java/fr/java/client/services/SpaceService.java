package fr.java.client.services;

import fr.java.client.entities.Space;
import fr.java.client.entities.User;
import fr.java.client.utils.types.Roles;

import java.util.ArrayList;
import java.util.List;

public class SpaceService {

    private static SpaceService instance;
    private final TodolistService todolistService;

    private List<Space> spaces;
    private Space currentSpace;

    private SpaceService() {
        this.spaces = new ArrayList<>();
        this.addSpace(new Space("space_1", new User("root", "root", "henri", "name", Roles.Administrator), "private", "Touriste"));
        this.addSpace(new Space("lol", new User("root", "root", "ahme", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("miam", new User("root", "root", "mika", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("gg", new User("root", "root", "test", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("pace_5", new User("root", "root", "prout", "", Roles.Client), "private", "Toilette"));
        this.addSpace(new Space("space_1", new User("root", "root", "henri", "name", Roles.Administrator), "private", "Touriste"));
        this.addSpace(new Space("lol", new User("root", "root", "ahme", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("miam", new User("root", "root", "mika", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("gg", new User("root", "root", "test", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("pace_5", new User("root", "root", "prout", "", Roles.Client), "private", "Toilette"));
        this.addSpace(new Space("space_1", new User("root", "root", "henri", "name", Roles.Administrator), "private", "Touriste"));
        this.addSpace(new Space("lol", new User("root", "root", "ahme", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("miam", new User("root", "root", "mika", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("gg", new User("root", "root", "test", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("pace_5", new User("root", "root", "prout", "", Roles.Client), "private", "Toilette"));this.addSpace(new Space("space_1", new User("root", "root", "henri", "name", Roles.Administrator), "private", "Touriste"));
        this.addSpace(new Space("lol", new User("root", "root", "ahme", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("miam", new User("root", "root", "mika", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("gg", new User("root", "root", "test", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("pace_5", new User("root", "root", "prout", "", Roles.Client), "private", "Toilette"));this.addSpace(new Space("space_1", new User("root", "root", "henri", "name", Roles.Administrator), "private", "Touriste"));
        this.addSpace(new Space("lol", new User("root", "root", "ahme", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("miam", new User("root", "root", "mika", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("gg", new User("root", "root", "test", "", Roles.Client), "public", "Developer"));
        this.addSpace(new Space("pace_5", new User("root", "root", "prout", "", Roles.Client), "private", "Toilette"));


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
