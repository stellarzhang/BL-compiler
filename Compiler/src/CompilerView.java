import java.awt.event.ActionListener;

/**
 * Compiler view interface.
 *
 * @author Stella Zhang
 */
public interface CompilerView extends ActionListener {
    /**
     * Updates right window that either prints error or successfully compiles
     * the user's program.
     */
    void updateConsoleDisplay();
}
