package fr.java.client.services;

import fr.java.client.components.login.dto.LoginDTO;
import fr.java.client.components.space.dto.SpaceDTO;
import fr.java.client.components.space.dto.SpaceListDTO;
import fr.java.client.components.todolist.dto.TodolistDTO;
import fr.java.client.entities.Space;
import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;
import fr.java.client.entities.User;
import fr.java.client.utils.types.ConfirmationDTO;
import fr.java.client.utils.types.Roles;
import fr.java.client.utils.types.SpaceTab;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class SpaceService {

    private static SpaceService instance;
    private final TodolistService todolistService;

    private final AsyncService asyncService = AsyncService.getInstance();
    private Space    currentSpace;
    private SpaceTab spaceTab;

    private static final String SPACE_URL = "/er-space";
    private static final String SAVE_URL = "/save";
    private static final String UPDATE_URL = "/update";

    private SpaceService() {
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
        SpaceDTO spaceToSave = new SpaceDTO();
        spaceToSave.setAuthor(new LoginDTO(space.getAuthor()));
        spaceToSave.setVisibility(space.getVisibility());
        spaceToSave.setDescription(space.getDescription());
        spaceToSave.setName(space.getName());
        spaceToSave.setTag(space.getTag());

        try {
           this.asyncService.post(SPACE_URL + SAVE_URL, SpaceDTO.class, spaceToSave);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Space> getSpaces() {
        List<Space> spaces = new ArrayList<>();
        try {
            SpaceListDTO response = asyncService.get(SPACE_URL, SpaceListDTO.class);
            response.getSpaces().forEach( space -> {
                 Space spaceToAdd = new Space(space);
                 spaces.add(spaceToAdd);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

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

}
