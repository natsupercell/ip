import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Utility class
 * Manage files from hard disk
 */
public class DataManager {
    private static final String FOLDER_NAME = "data";
    private static final String FILE_NAME = "task.txt";
    private static final File FOLDER = new File(FOLDER_NAME);
    private static final File FILE = new File(FOLDER, FILE_NAME);

    /**
     * Private constructor, so that it cannot be instantiated (expected behavior)
     */
    private DataManager() {}

    /**
     * Checks if the task data file and its parent folder already exist, if not create new ones
     */
    public static void initializeFile() {
        try {
            FOLDER.mkdirs();
            FILE.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Reads from the data file, then create a TaskContainer based on the data
     * @return A TaskContainer corresponds to saved data
     */
    public static TaskContainer read() {
        TaskContainer tc = new TaskContainer();
        try (Scanner scanner = new Scanner(FILE)) {
            while (scanner.hasNextLine()) {
                Task task = Task.dataToTask(scanner.nextLine());
                tc.loadTask(task);
            }
        } catch (IOException e) {
            System.out.println("Couldn't load data. Data file is corrupted.");
        }
        return tc;
    }

    /**
     * Save data from a TaskContainer to hard disk
     * @param tc TaskContainer to save data from
     */
    public static void save(TaskContainer tc) {
        try (PrintWriter writer = new PrintWriter(FILE)) {
            writer.print(tc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
