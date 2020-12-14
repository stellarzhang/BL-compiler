import java.util.Queue;

/**
 * Compiler controller implementation.
 *
 * @author Stella Zhang
 */
public final class CompilerController1 implements CompilerController {

    /**
     * Model object.
     */
    private final CompilerModel model;

    /**
     * View object.
     */
    private final CompilerView view;

    /**
     * Useful constants.
     */
    private static final String t = ""; //TODO

    /**
     * Updates this.view to display this.model and to allow only operations that
     * are legal given this.model.
     */
    private static void updateViewToMatchModel(CompilerModel model,
            CompilerView view) {
        Program1Parse1 program = model.userWindowContent();
        Queue<String> errors = program.reportErrors();
        view.updateConsoleDisplay(errors);

        boolean compileAllowed = false;
        //if userWindow is blank then disable compile button
        if (program.equals(program.newInstance())) {
            compileAllowed = true;
        }

        view.updateCompileAllowed(compileAllowed);
    }

    /**
     * Constructor.
     */
    public CompilerController1(CompilerModel model, CompilerView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processCompilation() {
        Program1Parse1 p = this.model.userWindowContent();
        Queue<String> consoleWindow = this.model.consoleWindow();
        consoleWindow.clear();
        Queue<String> errors = p.reportErrors();
        while (!errors.isEmpty()) {
            consoleWindow.add(errors.remove());
        }

        updateViewToMatchModel(this.model, this.view);
    }

}
