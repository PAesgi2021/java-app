package fr.java.client.services;

public class Instance {


    private final UserService userService;
    private final SpaceService spaceService;
    private static Instance instance;


    private Instance() {
        this.userService = UserService.getInstance();
        this.spaceService = SpaceService.getInstance();
    }

    public static Instance getInstance() {
        if (Instance.instance == null) {
            instance = new Instance();
            return instance;
        }
        return instance;
    }


    public UserService getUserService() {
        return userService;
    }

    public SpaceService getSpaceService() {
        return spaceService;
    }
}
