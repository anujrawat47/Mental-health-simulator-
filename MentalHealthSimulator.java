import java.util.*;

// Module 1: Mood Module
class MoodModule {
    private List<String> moods = Arrays.asList("Happy", "Sad", "Angry", "Tired", "Stressed", "Relaxed");

    public String askMood(Scanner sc) {
        System.out.println("Select your mood:");
        for (int i = 0; i < moods.size(); i++) {
            System.out.println((i + 1) + ". " + moods.get(i));
        }
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline
        String selectedMood = moods.get(choice - 1);
        System.out.println("You are feeling: " + selectedMood);
        return selectedMood;
    }
}

// Module 2: Quote Module
class QuoteModule {
    private List<String> quotes = Arrays.asList(
            "Keep your face always toward the sunshine – Walt Whitman",
            "Happiness is not something ready made. It comes from your own actions – Dalai Lama",
            "The greatest wealth is health – Virgil",
            "The mind is everything. What you think you become – Buddha"
    );

    public void displayRandomQuote() {
        Random rand = new Random();
        System.out.println("Quote of the day: " + quotes.get(rand.nextInt(quotes.size())));
    }

    public void displayAllQuotes() {
        System.out.println("All Quotes:");
        for (String q : quotes) System.out.println("- " + q);
    }
}

// Module 3: Activity Module
class ActivityModule {
    private Map<Integer, String> activityMap = new HashMap<>();
    private int nextId = 1;

    public ActivityModule() {
        addActivity("Meditation");
        addActivity("Breathing Exercise");
        addActivity("Gratitude Journaling");
        addActivity("Mindful Walking");
    }

    public int addActivity(String activityName) {
        int id = nextId++;
        activityMap.put(id, activityName);
        System.out.println("Added activity: " + activityName + " (ID: " + id + ")");
        return id;
    }

    public void deleteActivity(int id) {
        if (activityMap.containsKey(id)) {
            System.out.println("Deleted activity: " + activityMap.remove(id));
        } else {
            System.out.println("Activity ID not found.");
        }
    }

    public void displayActivities() {
        if (activityMap.isEmpty()) {
            System.out.println("No activities available.");
        } else {
            System.out.println("Activities:");
            activityMap.forEach((id, name) -> System.out.println(id + ". " + name));
        }
    }

    public boolean isValidActivityId(int id) {
        return activityMap.containsKey(id);
    }
}

// Module 4: Exercise Module
class ExerciseModule {
    private Map<Integer, String> exercises = new HashMap<>();
    private Map<Integer, String> levels = new HashMap<>();
    private int nextId = 1;

    public ExerciseModule() {
        addExercise("Yoga", "Easy");
        addExercise("Cardio", "Medium");
        addExercise("Strength Training", "Hard");
        addExercise("Stretching", "Easy");
    }

    public int addExercise(String name, String level) {
        int id = nextId++;
        exercises.put(id, name);
        levels.put(id, level);
        System.out.println("Added Exercise: " + name + " (ID: " + id + ", Level: " + level + ")");
        return id;
    }

    public void displayExercises() {
        System.out.println("Exercises Available:");
        exercises.forEach((id, name) -> System.out.println(id + ". " + name + " (Level: " + levels.get(id) + ")"));
    }

    public boolean isValidExerciseId(int id) {
        return exercises.containsKey(id);
    }
}

// Module 5: User Module
class UserModule {
    private Map<Integer, String> userMap = new HashMap<>();
    private int nextUserId = 1;

    public UserModule() {
        addUser("Anuj");
        addUser("Anuj Rawat");
        addUser("Anuj rwt");
    }

    public int addUser(String name) {
        int id = nextUserId++;
        userMap.put(id, name);
        System.out.println("User registered: " + name + " (ID: " + id + ")");
        return id;
    }

    public void displayUsers() {
        if (userMap.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            System.out.println("Users:");
            userMap.forEach((id, name) -> System.out.println(id + ". " + name));
        }
    }

    public boolean isValidUserId(int id) {
        return userMap.containsKey(id);
    }
}

// Module 6: Assignment & Progress Module
class AssignmentModule {
    private Map<Integer, Queue<Integer>> userActivities = new HashMap<>();
    private Map<Integer, Queue<Integer>> userExercises = new HashMap<>();
    private Stack<String> history = new Stack<>();
    private ActivityModule activityModule;
    private UserModule userModule;
    private ExerciseModule exerciseModule;

    public AssignmentModule(ActivityModule actModule, UserModule usrModule, ExerciseModule exModule) {
        this.activityModule = actModule;
        this.userModule = usrModule;
        this.exerciseModule = exModule;
    }

    public void assignActivity(int userId, int activityId) {
        userActivities.putIfAbsent(userId, new LinkedList<>());
        userActivities.get(userId).add(activityId);
        history.push("Assigned Activity " + activityId + " to User " + userId);
        System.out.println("Activity " + activityId + " assigned to User " + userId);
    }

    public void assignExercise(int userId, int exerciseId) {
        userExercises.putIfAbsent(userId, new LinkedList<>());
        userExercises.get(userId).add(exerciseId);
        history.push("Assigned Exercise " + exerciseId + " to User " + userId);
        System.out.println("Exercise " + exerciseId + " assigned to User " + userId);
    }

    public void trackActivityProgress(int userId) {
        Queue<Integer> queue = userActivities.get(userId);
        if (queue == null || queue.isEmpty()) {
            System.out.println("No pending activities for user " + userId);
            return;
        }
        int completed = queue.poll();
        history.push("Completed Activity " + completed + " by User " + userId);
        System.out.println("User " + userId + " completed activity " + completed);
    }

    public void trackExerciseProgress(int userId) {
        Queue<Integer> queue = userExercises.get(userId);
        if (queue == null || queue.isEmpty()) {
            System.out.println("No pending exercises for user " + userId);
            return;
        }
        int completed = queue.poll();
        history.push("Completed Exercise " + completed + " by User " + userId);
        System.out.println("User " + userId + " completed exercise " + completed);
    }

    public void displayUserActivities(int userId) {
        Queue<Integer> queue = userActivities.get(userId);
        if (queue == null || queue.isEmpty()) {
            System.out.println("No pending activities.");
        } else {
            System.out.println("Pending activities for user " + userId + ": " + queue);
        }
    }

    public void displayUserExercises(int userId) {
        Queue<Integer> queue = userExercises.get(userId);
        if (queue == null || queue.isEmpty()) {
            System.out.println("No pending exercises.");
        } else {
            System.out.println("Pending exercises for user " + userId + ": " + queue);
        }
    }

    public void undoLast() {
        if (history.isEmpty()) {
            System.out.println("No actions to undo.");
        } else {
            System.out.println("Undoing: " + history.pop());
        }
    }
}

// Main Class
public class MentalHealthSimulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MoodModule moodModule = new MoodModule();
        QuoteModule quoteModule = new QuoteModule();
        ActivityModule activityModule = new ActivityModule();
        ExerciseModule exerciseModule = new ExerciseModule();
        UserModule userModule = new UserModule();
        AssignmentModule assignmentModule = new AssignmentModule(activityModule, userModule, exerciseModule);

        System.out.println("----- Session Start -----");
        System.out.print("Enter your name: ");
        String userName = sc.nextLine();

        // Ask mood
        moodModule.askMood(sc);

        // Display random quote
        quoteModule.displayRandomQuote();

        boolean exit = false;
        while (!exit) {
            System.out.println("\nMental Health Simulator Menu:");
            System.out.println("1. Add Activity");
            System.out.println("2. Delete Activity");
            System.out.println("3. Display Activities");
            System.out.println("4. Add Exercise");
            System.out.println("5. Display Exercises");
            System.out.println("6. Add User");
            System.out.println("7. Assign Activity to User");
            System.out.println("8. Assign Exercise to User");
            System.out.println("9. Track Activity Progress");
            System.out.println("10. Track Exercise Progress");
            System.out.println("11. Display User Activities");
            System.out.println("12. Display User Exercises");
            System.out.println("13. Undo Last Action");
            System.out.println("14. Display All Quotes");
            System.out.println("15. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter activity name: ");
                    String activityName = sc.nextLine();
                    activityModule.addActivity(activityName);
                    break;
                case 2:
                    System.out.print("Enter activity ID to delete: ");
                    int delId = sc.nextInt();
                    sc.nextLine();
                    activityModule.deleteActivity(delId);
                    break;
                case 3:
                    activityModule.displayActivities();
                    break;
                case 4:
                    System.out.print("Enter exercise name: ");
                    String exName = sc.nextLine();
                    System.out.print("Enter difficulty level (Easy/Medium/Hard): ");
                    String level = sc.nextLine();
                    exerciseModule.addExercise(exName, level);
                    break;
                case 5:
                    exerciseModule.displayExercises();
                    break;
                case 6:
                    System.out.print("Enter new user name: ");
                    String newUser = sc.nextLine();
                    userModule.addUser(newUser);
                    break;
                case 7:
                    userModule.displayUsers();
                    activityModule.displayActivities();
                    System.out.print("Enter user ID: ");
                    int uId = sc.nextInt();
                    System.out.print("Enter activity ID to assign: ");
                    int aId = sc.nextInt();
                    assignmentModule.assignActivity(uId, aId);
                    break;
                case 8:
                    userModule.displayUsers();
                    exerciseModule.displayExercises();
                    System.out.print("Enter user ID: ");
                    int ueId = sc.nextInt();
                    System.out.print("Enter exercise ID to assign: ");
                    int exId = sc.nextInt();
                    assignmentModule.assignExercise(ueId, exId);
                    break;
                case 9:
                    userModule.displayUsers();
                    System.out.print("Enter user ID to track activity progress: ");
                    int trackId = sc.nextInt();
                    assignmentModule.trackActivityProgress(trackId);
                    break;
                case 10:
                    userModule.displayUsers();
                    System.out.print("Enter user ID to track exercise progress: ");
                    int trackExId = sc.nextInt();
                    assignmentModule.trackExerciseProgress(trackExId);
                    break;
                case 11:
                    userModule.displayUsers();
                    System.out.print("Enter user ID to view activities: ");
                    int viewId = sc.nextInt();
                    assignmentModule.displayUserActivities(viewId);
                    break;
                case 12:
                    userModule.displayUsers();
                    System.out.print("Enter user ID to view exercises: ");
                    int viewExId = sc.nextInt();
                    assignmentModule.displayUserExercises(viewExId);
                    break;
                case 13:
                    assignmentModule.undoLast();
                    break;
                case 14:
                    quoteModule.displayAllQuotes();
                    break;
                case 15:
                    exit = true;
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        sc.close();
    }
}
