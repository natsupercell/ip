package hihihaha;

import hihihaha.message.Task;
import hihihaha.message.TaskContainer;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A utility class responsible for persistent data storage operations. This
 * class handles reading task data from and saving task data to the local hard
 * disk, ensuring that user data is preserved across application sessions.
 */
public class DataManager {
    private static final String FOLDER_NAME = "data";
    private static final String FILE_NAME = "task.txt";
    private static final File FOLDER = new File(FOLDER_NAME);
    private static final File FILE = new File(FOLDER, FILE_NAME);

    /**
     * Prevents instantiation of this utility class. All methods in this class are
     * static; therefore, it should not be instantiated.
     */
    private DataManager() {
    }

    /**
     * Ensures that the data file exists in the intended relative location. If it
     * does not exist, create new file (and its parent folders, if needed).
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
     * Reads from the data file, then create a TaskContainer based on the data.
     * 
     * @return A TaskContainer corresponds to saved data.
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
     * Saves data from a TaskContainer to hard disk.
     * 
     * @param tc
     *            TaskContainer to save data from.
     */
    public static void save(TaskContainer tc) {
        try (PrintWriter writer = new PrintWriter(FILE)) {
            writer.print(tc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
