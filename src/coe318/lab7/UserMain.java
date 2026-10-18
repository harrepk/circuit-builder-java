package coe318.lab7;

import java.util.Scanner;

/**
 * Entry point for the simple resistive circuit solver. It reads a circuit
 * description from standard input one line at a time, builds up a
 * {@link Circuit} from the resistor ({@code r}) and voltage-source ({@code v})
 * lines, and responds to the two single-word commands {@code spice} and
 * {@code end}.
 * <p>
 * Recognised input lines:
 * <ul>
 *   <li>{@code r nodeA nodeB ohms} &ndash; add a resistor</li>
 *   <li>{@code v posNode negNode volts} &ndash; add a DC voltage source</li>
 *   <li>{@code spice} &ndash; print the SPICE netlist entered so far</li>
 *   <li>{@code end} &ndash; print {@code All Done} and stop</li>
 * </ul>
 *
 * @author Harre PK
 */
public class UserMain {

    /**
     * Reads and interprets circuit lines from stdin until the {@code end}
     * command is entered (or the input stream is exhausted).
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Circuit workingCircuit = new Circuit();

        while (keyboard.hasNextLine()) {
            String rawLine = keyboard.nextLine().trim();

            // Skip blank lines so stray newlines do not break parsing.
            if (rawLine.isEmpty()) {
                continue;
            }

            String[] tokens = rawLine.split("\\s+");
            String keyword = tokens[0].toLowerCase();

            switch (keyword) {
                case "end":
                    System.out.println("All Done");
                    return;
                case "spice":
                    System.out.println(workingCircuit.toSpice());
                    break;
                case "r": {
                    int nodeA = Integer.parseInt(tokens[1]);
                    int nodeB = Integer.parseInt(tokens[2]);
                    double ohms = Double.parseDouble(tokens[3]);
                    workingCircuit.add(new Resistor(nodeA, nodeB, ohms));
                    break;
                }
                case "v": {
                    int plusNode = Integer.parseInt(tokens[1]);
                    int minusNode = Integer.parseInt(tokens[2]);
                    double volts = Double.parseDouble(tokens[3]);
                    workingCircuit.add(new VoltageSource(plusNode, minusNode, volts));
                    break;
                }
                default:
                    // Unknown lines are ignored so a typo does not crash the run.
                    break;
            }
        }
    }
}
