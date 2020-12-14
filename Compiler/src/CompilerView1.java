import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Compiler view implementation. The code here was written with the guidance of
 * the CSE 2221 GUI NaturalNumberCalculator.
 *
 * @author Stella Zhang
 */
public final class CompilerView1 extends JFrame implements CompilerView {
    /**
     * Controller object registered with this view to observe user-interaction
     * events.
     */
    private CompilerController controller;

    /**
     * Text areas.
     */
    private final JTextArea userEditingWindow, consoleWindow;

    /**
     * Buttons.
     */
    private final JButton compileButton;

    /**
     * Useful constants.
     */
    private static final int JFRAME_HEIGHT = 100, JFRAME_WIDTH = 100,
            SUB_WINDOW_HEIGHT = 50, SUB_WINDOW_WIDTH = 50, MAIN_GRID_ROWS = 2,
            MAIN_GRID_COLUMNS = 1, NUM_BUTTONS = 1, BUTTON_PANEL_HEIGHT = 1,
            BOTH_PANELS_HEIGHT = 1, BOTH_PANELS_WIDTH = 2;

    /**
     * Constructor.
     */
    public CompilerView1() {

        //create the jframe
        super("Compiler");

        //TODO current state variable?

        //Initialize buttons, text areas, etc.
        this.userEditingWindow = new JTextArea(SUB_WINDOW_WIDTH,
                SUB_WINDOW_HEIGHT);
        this.consoleWindow = new JTextArea(SUB_WINDOW_WIDTH, SUB_WINDOW_HEIGHT);
        this.compileButton = new JButton("Compile");

        //user editing window wraps lines and is editable
        this.userEditingWindow.setEditable(true);
        this.userEditingWindow.setLineWrap(true);
        this.userEditingWindow.setWrapStyleWord(true);

        //console window wraps lines but is not editable
        this.consoleWindow.setEditable(false);
        this.consoleWindow.setLineWrap(true);
        this.consoleWindow.setWrapStyleWord(true);

        //initially the compile button should be disabled because the user
        //editing window is empty
        this.compileButton.setEnabled(false);

        JScrollPane userEditingWindowScroll = new JScrollPane(
                this.userEditingWindow);
        JScrollPane consoleWindowScroll = new JScrollPane(this.consoleWindow);

        //make top layer button panel
        JPanel buttonPanel = new JPanel(
                new GridLayout(BUTTON_PANEL_HEIGHT, NUM_BUTTONS));
        //TODO plans to add other buttons like "clear" etc
        buttonPanel.add(this.compileButton);

        JPanel bothTextPanels = new JPanel(
                new GridLayout(BOTH_PANELS_HEIGHT, BOTH_PANELS_WIDTH));
        bothTextPanels.setLayout(new GridLayout(1, 2, 10, 0));
        bothTextPanels.add(userEditingWindowScroll);
        bothTextPanels.add(consoleWindowScroll);

        this.setLayout(new GridLayout(MAIN_GRID_ROWS, MAIN_GRID_COLUMNS));

        //add components to main window
        this.add(buttonPanel);
        this.add(bothTextPanels);

        //set up observers for buttons
        this.compileButton.addActionListener(this);

        //style color/font etc of text panes TODO
        //TODO delete
        this.userEditingWindow.setText("user editing window");
        this.consoleWindow.setText("consoleWindow");
        //TODO delete

        //set up main application window
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //set wait cursor
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        //determine which method needs to be processed based on the event
        //that occurred
        Object source = e.getSource();
        if (source == this.compileButton) {
            this.controller.processCompilation();
        }

        //set cursor back to normal
        this.setCursor(Cursor.getDefaultCursor());

    }

    @Override
    public void updateCompileAllowed(boolean allowed) {
        this.compileButton.setEnabled(allowed);
    }

    @Override
    public void updateConsoleDisplay(Queue<String> errors) {
        this.consoleWindow.setText("remember to fix this lol");

    }

    @Override
    public void registerObserver(CompilerController controller) {
        this.controller = controller;
    }
}
