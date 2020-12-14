import java.awt.event.ActionListener;
import java.util.Queue;

/**
 * Compiler view interface.
 *
 * @author Stella Zhang
 */
public interface CompilerView extends ActionListener {
    /**
     * Updates right window that either prints error or successfully compiles
     * the user's program.
     *
     * @param p
     */
    void updateConsoleDisplay(Queue<String> errors);

    /**
     * Depending on if there is source code in the user editing window, the
     * compile button will either be enabled or disabled.
     *
     * @param allowed
     */
    void updateCompileAllowed(boolean allowed);

    /**
     * Register argument as observer/listener of this.
     *
     * @author Paolo Bucci (?)
     * @param controller
     */
    void registerObserver(CompilerController controller);

}
