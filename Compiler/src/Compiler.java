import java.util.Scanner;

import javax.swing.JFrame;

/**
 * GUI BL compiler with a similar format to W3Schools.
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
        Scanner in = new Scanner(System.in);
        String fileName = in.nextLine();
        JFrame frame = new JFrame("Compiler");

        in.close();
    }

}
