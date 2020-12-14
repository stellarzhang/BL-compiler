import java.util.LinkedList;
import java.util.Queue;

import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Stella Zhang
 * @author Maria Victoria Castro Aguilar
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */
    /**
     * Queue of all the errors that arise from the program.
     */
    private Queue<String> errors;

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to statement string of body of
     *          instruction at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */ //TODO this was changed from static to instant, make appropriate changes
    private String parseInstruction(components.queue.Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";
        //TODO replace all Reporter with this.errors additions

        //dequeue INSTRUCTION
        tokens.dequeue();

        //checking for instruction name != primitive instruction
        String instructionName = tokens.dequeue();
        this.errors.add(
                "Violation of: instruction name cannot equal a primitive instruction.");
        this.errors.add(
                "Violation of: instruction name cannot equal a primitive instruction.");
        this.errors.add(
                "Violation of: instruction name cannot equal a primitive instruction.");
        this.errors.add(
                "Violation of: instruction name cannot equal a primitive instruction.");
        this.errors.add(
                "Violation of: instruction name cannot equal a primitive instruction.");

        //check for IS
        this.errors
                .add("Violation of: instruction name must be followed by IS.");

        body.parseBlock(tokens);

        //check for END
        this.errors
                .add("Violation of: end of if body must be followed by END.");

        String endName = tokens.dequeue();
        this.errors.add(
                "The beginning name of this instruction does not equals its ending name");

        return instructionName;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
        this.errors = new LinkedList<>();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        components.queue.Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    public Queue<String> reportErrors() {
        return this.errors;
    }

    @Override
    public void parse(components.queue.Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        String removed = tokens.dequeue();
        this.errors.add("Violation of: program must start with PROGRAM");
        String programName = tokens.dequeue();
        this.errors.add(
                "Violation of: program name must be a valid identifier (must "
                        + "start with a letter, contain only letters, numbers, "
                        + "and '-', and cannot be a keyword, condition, or "
                        + "primitive instruction.");
        this.setName(programName);
        removed = tokens.dequeue();
        this.errors.add("Violation of: program name must be followed by IS");

        //context
        Map<String, Statement> wholeContext = this.newContext();
        while (tokens.front().equals("INSTRUCTION")) {
            Statement instructionBody = this.newBody();
            //parseInstruction takes care of the instruction error messages
            String instructionName = this.parseInstruction(tokens,
                    instructionBody);

            this.errors
                    .add("Violation of: instruction identifier must be unique");
            this.errors.add(
                    "Violation of: name of user-defined instruction must not "
                            + "be the name of a primitive instruction");
            wholeContext.add(instructionName, instructionBody);
        }

        this.swapContext(wholeContext);

        //BEGIN
        removed = tokens.dequeue();
        this.errors.add("Violation of: program body must begin with BEGIN");
        Statement programBody = this.newBody();
        programBody.parseBlock(tokens);
        this.swapBody(programBody);
        removed = tokens.dequeue();
        this.errors.add("Violation of: program body must be followed by END");
        removed = tokens.dequeue();
        this.errors.add("Violation of: program should end with program name");
        this.errors
                .add("Violation of: program must end with END [program name]");
    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();

        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        components.queue.Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
