/* 
    Requirements
    - The task management system should allow users to create, update, and delete tasks.
    - Each task should have a title, description, due date, priority, and status (e.g., pending, in progress, completed).
    - Users should be able to assign tasks to other users and set reminders for tasks.
    - The system should support searching and filtering tasks based on various criteria (e.g., priority, due date, assigned user).
    - Users should be able to mark tasks as completed and view their task history.
    - The system should handle concurrent access to tasks and ensure data consistency.
    - The system should be extensible to accommodate future enhancements and new features.
 * 
 */


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

enum STATUS {
    TODO,
    IN_PROGRESS,
    COMPLETED
}

enum PRIORITY {
    LOW,
    MEDIUM,
    HIGH
}

class User {
    private final String id;
    private String name;
    private String email;


    public User(String name, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

class Task {
    private final String id;
    private String title;
    private String description;
    private Date dueDate;
    private STATUS status;
    private PRIORITY priority;
    private User assignedUser;

    public Task(String title, String description, Date dueDate, PRIORITY priority, User user) {
        this.id = UUID.randomUUID().toString();
        this.title =  title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = STATUS.TODO;
        this.assignedUser = user;
    }


    public synchronized void assignUser(User user) {
        this.assignedUser = user;
    }

    public synchronized void updateStatus(STATUS status) {
        this.status = status;
    }

    public synchronized void updatePriority(PRIORITY priority) {
        this.priority = priority;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public PRIORITY getPriority() {
        return priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public STATUS getStatus() {
        return status;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

}

class TaskManager {
    private static TaskManager instance;
    private Map<String, User> users;
    private Map<String, Task> tasks;

    private TaskManager() {
        users = new ConcurrentHashMap<>();
        tasks = new ConcurrentHashMap<>();
    }

    public static synchronized TaskManager getInstance() {
        if (instance == null) {
            return new TaskManager();
        }
        return instance;
    }

    public User createUser(String name, String email) {
        User user = new User(name, email);
        users.put(user.getId(), user);
        return user;
    }

    public Task createTask(String userId, String title, String description, Date dueDate, PRIORITY priority) {
        User user = users.get(userId); // by default task is assigned to user who created it.
        Task task = new Task(title, description, dueDate, priority, user);
        tasks.put(task.getId(), task);
        return task;
    }

    public Task getTaskById(String taskId) {
        if (!tasks.containsKey(taskId)) {
            throw new RuntimeException("TaskId not found");
        }
        return tasks.get(taskId);
    }

    public void updateTaskStatus(String taskId, STATUS status) {
        this.getTaskById(taskId).updateStatus(status);
    }

    public void deleteTask(String taskId) {
       tasks.remove(taskId);
    }

    public void assignUserToTask(String taskId, String userId) {
        User user = users.get(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        this.getTaskById(taskId).assignUser(user);
    }

    public List<Task> listTasksByUser(String userId) {
        User user = users.get(userId);
        return tasks.values().stream().filter(task -> user.equals(task.getAssignedUser())).toList();
    }

    public List<Task> listTasksByStatus(STATUS status) {
        return tasks.values().stream().filter(task -> task.getStatus() == status).collect(Collectors.toList());
    }

    public List<Task> searchTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();

        for(Task task: tasks.values()) {
            if (task.getTitle().contains(keyword) || task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        
        return matchingTasks;
    }
}

public class TaskManagementSystemDemo {
    public static void main(String args[]) {
        System.out.println("starting TaskManagementSystemDemo ");


         TaskManager taskManager = TaskManager.getInstance();

        // Create users
        User user1 = taskManager.createUser("John Doe", "john@example.com");
        User user2 = taskManager.createUser("Jane Smith", "jane@example.com");

        // Create tasks
        Task task1 = taskManager.createTask(user1.getId(), "Task 1", "Description 1",
                new Date(), PRIORITY.LOW);
        Task task2 = taskManager.createTask(user1.getId(), "Task 2", "Description 2",
                new Date(), PRIORITY.MEDIUM);
        Task task3 = taskManager.createTask(user2.getId(), "Task 3", "Description 3",
                new Date(), PRIORITY.HIGH);

        // Update task status
        taskManager.updateTaskStatus(task2.getId(), STATUS.IN_PROGRESS);

        // Assign task
        taskManager.assignUserToTask(task2.getId(), user2.getId());

        // Search tasks
        List<Task> searchResults = taskManager.searchTasks("Task");
        System.out.println("\nTasks with keyword Task:");
        for (Task task : searchResults) {
            System.out.println(task.getTitle());
        }

        // Filter tasks by status
        List<Task> filteredTasks = taskManager.listTasksByStatus(STATUS.TODO);
        System.out.println("\nTODO Tasks:");
        for (Task task : filteredTasks) {
            System.out.println(task.getTitle());
        }

        // Mark a task as done
        taskManager.updateTaskStatus(task2.getId(), STATUS.COMPLETED);

        // Get tasks assigned to a user
        List<Task> userTaskList = taskManager.listTasksByUser(user2.getId());
        System.out.println("\nTask for " + user2.getName() + ":");
        for (Task task : userTaskList) {
            System.out.println(task.getTitle());
        }

        // Delete a task
        taskManager.deleteTask(task3.getId());

    }


}


