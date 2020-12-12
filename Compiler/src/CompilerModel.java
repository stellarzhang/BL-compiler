import components.program.Program;

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
    Program userWindow();
}
