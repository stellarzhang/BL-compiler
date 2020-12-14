import java.util.Queue;

/**
 * Compiler model interface.
 *
 * @author Stella Zhang
 */
public interface CompilerModel {
    /**
     * Reports what is in the user editing window.
     *
     * @return Abstract Program object of user's source code.
     */
    Program1Parse1 userWindowContent();

    /**
     * Reports what is in the console window.
     */
    Queue<String> consoleWindow();
}
