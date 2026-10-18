package coe318.lab7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds the ordered collection of {@link CircuitElement}s that make up a circuit
 * and is able to reproduce the circuit as a SPICE netlist. Elements are numbered
 * per type, in the order they were added, which is exactly the behaviour the
 * {@code spice} command requires (resistors become R1, R2, ... while sources
 * become V1, V2, ... independently of one another).
 *
 * @author Harre PK
 */
public class Circuit {

    /** Every element added to this circuit, kept in the order it was entered. */
    private final List<CircuitElement> assembledElements = new ArrayList<>();

    /**
     * Appends an element to the circuit. The insertion order is preserved so
     * that the SPICE listing reflects the order in which the user described the
     * components.
     *
     * @param element the resistor or voltage source to add
     */
    public void add(CircuitElement element) {
        assembledElements.add(element);
    }

    /**
     * @return how many elements the circuit currently contains
     */
    public int size() {
        return assembledElements.size();
    }

    /**
     * Produces the SPICE description of the circuit gathered so far. Each type
     * of element keeps its own running counter, so resistors are numbered
     * R1, R2, ... and voltage sources V1, V2, ... independently. The returned
     * string has no trailing newline; the caller decides how to print it.
     *
     * @return the multi-line SPICE netlist for the current circuit
     */
    public String toSpice() {
        Map<String, Integer> serialByLetter = new HashMap<>();
        StringBuilder netlist = new StringBuilder();
        for (CircuitElement element : assembledElements) {
            String letter = element.spiceLetter();
            int nextSerial = serialByLetter.getOrDefault(letter, 0) + 1;
            serialByLetter.put(letter, nextSerial);
            if (netlist.length() > 0) {
                netlist.append(System.lineSeparator());
            }
            netlist.append(element.toSpiceLine(nextSerial));
        }
        return netlist.toString();
    }
}
