package fr.java.client.services;

import fr.java.client.components.createTask.dto.TaskDTO;
import fr.java.client.components.todolist.dto.TodolistDTO;
import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;

public class TodolistService {
    private static TodolistService instance;

    private final AsyncService asyncService = AsyncService.getInstance();

    private final String TODOLIST_URL = "/er-todolist";
    private final String TODOLIST_SAVE_URL = "/save";
    private final String TASK_URL = "/er-tasks";
    private final String TASK_SAVE_URL = "/save";


    private Todolist currentTodolist;
    private Task currentTask;

    private TodolistService() {
    }


    public static TodolistService getInstance() {
        if (TodolistService.instance == null) {
            instance = new TodolistService();
            return instance;
        }
        return instance;
    }

    public Todolist saveOrUpdateTodolist(Todolist todolist, Integer spaceId) {
        TodolistDTO todolistToSave = new TodolistDTO(todolist, spaceId);
        TodolistDTO response = new TodolistDTO();
        try {
           response = this.asyncService.post(TODOLIST_URL + TODOLIST_SAVE_URL, TodolistDTO.class, todolistToSave);
            System.out.println("updated todolist");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Todolist(response);
    }

    public void deleteTodolist(Todolist todolist) {
        System.out.println("delete todo");
        try {
            this.asyncService.delete(TODOLIST_URL + "/" + todolist.getId() , String.class);
            System.out.println("deleted todolist");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteTask(Task task) {
        System.out.println("delete task");
        try {
            this.asyncService.delete(TASK_URL + "/" + task.getId() , String.class);
            System.out.println("deleted todolist");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Task saveOrUpdateTask(Task task, Integer todolistId) {
        TaskDTO taskToSave = new TaskDTO(task, todolistId);
        TaskDTO response = new TaskDTO();
        try {
            response = this.asyncService.post(TASK_URL + TASK_SAVE_URL, TaskDTO.class, taskToSave);
            System.out.println("updated task");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Task(response);
    }


    public Todolist getCurrentTodolist() {
        return currentTodolist;
    }

    public void setCurrentTodolist(Todolist currentTodolist) {
        this.currentTodolist = currentTodolist;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }
}
