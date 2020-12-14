import javax.swing.JFrame;

/**
 * GUI BL compiler with a similar format to W3Schools.
 *
 * TODO properly cite all code from partner and professor.
 *
 * @author Stella Zhang
 */
public final class Compiler extends JFrame {

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        CompilerModel model = new CompilerModel1();
        CompilerView view = new CompilerView1();
        CompilerController controller = new CompilerController1(model, view);

        view.registerObserver(controller);
    }
}
