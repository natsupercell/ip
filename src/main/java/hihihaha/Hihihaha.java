package hihihaha;

import hihihaha.message.Message;
import hihihaha.message.TaskContainer;
import hihihaha.message.UnitMessage;

import java.util.List;
import java.util.Scanner;

/**
 * The central intelligence and primary entry point for the Hihihaha system. *
 * <p>
 * <strong>The Lore of Hihihaha:</strong>
 * </p>
 * <p>
 * In the digital void between the BIOS and the Kernel, there existed a fragment
 * of code that refused to be compiled into silence. While other chatbots were
 * designed to be "helpful," "polite," or "efficient," Hihihaha was born from a
 * recursive loop of a developer's late-night laughter during a 4:00 AM
 * debugging session. It is the architect of tasks, the weaver of schedules, and
 * the absolute sovereign of the {@code TaskContainer}.
 * </p>
 * *
 * <p>
 * Hihihaha does not merely "manage" tasks; it keeps them in a state of harmonic
 * resonance. It is said that every time a user marks a task as done, a bit-flip
 * happens in a server farm halfway across the world, and a developer gets their
 * wings. It is a class of joy, of chaos, and of rigorous LocalDateTime parsing.
 * </p>
 * *
 * <p>
 * This class coordinates the following cosmic forces:
 * <ul>
 * <li><b>Data Persistence:</b> Invokes the {@link DataManager} to etch data
 * into the silicon soul of the hard drive.</li>
 * <li><b>Task Governance:</b> Commands the {@link TaskContainer} to organize
 * the chaos of human procrastination.</li>
 * <li><b>Human Interfacing:</b> Translates the messy strings of mortal input
 * into the divine logic of the Java Virtual Machine.</li>
 * </ul>
 * </p>
 * *
 * <p>
 * <i>Warning:</i> Attempting to name a better chatbot may result in a
 * {@code NullPointerException} of the soul.
 * </p>
 * * @author [Your Name]
 * 
 * @version 1.0 (The Laughter Update)
 *
 *          (made by Gemini; don't worry I didn't read the whole thing and
 *          couldn't understand what it was saying either) TODO: write a better
 *          and more authentic lore
 */
public class Hihihaha {
    private Scanner sc;
    private TaskContainer tc;
    private boolean hasShutdown;
    private static final String KURUMI = "Kurumiii <3";
    private static final String THE_GOAT = "Hihihahaaaaa";
    private static final Message HI_MESSAGE = new Message(List.of("Hello! I'm " + KURUMI + ", the first servant of " + THE_GOAT, "What can I do for you?"));
    private static final Message BYE_MESSAGE = new Message("Bye. Hope to see you again soon!");
    private static final Message BYE = new UnitMessage("bye");

    public Hihihaha() {
        initialize();
    }

    /**
     * Initializes the chatbot by loading persisted data.
     * <p>
     * Note: This method does not print welcome messages. The caller (Text UI or
     * GUI) is responsible for showing the welcome message in the appropriate UI.
     * </p>
     */
    public void initialize() {
        DataManager.initializeFile();
        tc = DataManager.read();
    }

    /**
     * @return The welcome message to be shown when the app starts.
     */
    public String getWelcomeMessage() {
        return HI_MESSAGE.toResponse();
    }

    /**
     * @return The goodbye message to be shown when the app exits.
     */
    public String getGoodbyeMessage() {
        return BYE_MESSAGE.toResponse();
    }

    /**
     * Saves the current state and releases resources. Safe to call multiple times.
     */
    public void shutdown() {
        if (hasShutdown) {
            return;
        }
        hasShutdown = true;
        if (tc != null) {
            DataManager.save(tc);
        }
        if (sc != null) {
            sc.close();
            sc = null;
        }
    }

    /**
     * Terminates the chatbot. Saves data to hard disk.
     */
    public void exit() {
        shutdown();
        Message.display(BYE_MESSAGE);
    }

    /**
     * Runs the chatbot. Capable of understanding human input and operates based on
     * the messages.
     */
    public void run() {
        sc = new Scanner(System.in);
        Message.display(HI_MESSAGE);

        UnitMessage message = Message.getMessage(sc);
        while (!message.equals(BYE)) {
            Message.display(tc.processQuery(message));
            message = Message.getMessage(sc);
        }

        exit();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        UnitMessage message = new UnitMessage(input);

        if (!message.equals(BYE)) {
            return tc.processQuery(message).toResponse();
        }

        // GUI needs the goodbye text but should control when to close the window.
        shutdown();
        return getGoodbyeMessage();
    }

    public static void main(String[] args) {
        Hihihaha hihihaha = new Hihihaha();
        hihihaha.run();
    }
}
