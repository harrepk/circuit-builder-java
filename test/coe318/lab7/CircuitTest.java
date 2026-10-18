package coe318.lab7;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * JUnit tests for the {@link Circuit} class. These exercise two of its methods:
 * {@code size}, which reports how many elements have been added, and
 * {@code toSpice}, which renders the circuit as a SPICE netlist with each type
 * of element numbered independently.
 *
 * @author Harre PK
 */
public class CircuitTest {

    /**
     * Verifies that {@code size} grows by exactly one for every element added
     * and starts at zero for a fresh circuit.
     */
    @Test
    public void sizeCountsEveryElementAdded() {
        Circuit sampleCircuit = new Circuit();
        assertEquals(0, sampleCircuit.size());

        sampleCircuit.add(new Resistor(1, 2, 0.25));
        sampleCircuit.add(new VoltageSource(1, 0, 2.0));
        sampleCircuit.add(new Resistor(2, 3, 0.5));

        assertEquals(3, sampleCircuit.size());
    }

    /**
     * Verifies that {@code toSpice} numbers resistors and sources separately and
     * reproduces the worked example printed in the lab handout.
     */
    @Test
    public void toSpiceMatchesHandoutExample() {
        Circuit handoutCircuit = new Circuit();
        handoutCircuit.add(new VoltageSource(1, 0, 2.0));
        handoutCircuit.add(new Resistor(1, 2, 0.25));
        handoutCircuit.add(new VoltageSource(2, 0, 3.0));
        handoutCircuit.add(new Resistor(2, 3, 0.5));
        handoutCircuit.add(new Resistor(3, 0, 1.0));

        String expectedNetlist =
                  "V1 1 0 DC 2.0" + System.lineSeparator()
                + "R1 1 2 0.25" + System.lineSeparator()
                + "V2 2 0 DC 3.0" + System.lineSeparator()
                + "R2 2 3 0.5" + System.lineSeparator()
                + "R3 3 0 1.0";

        assertEquals(expectedNetlist, handoutCircuit.toSpice());
    }

    /**
     * Verifies that an empty circuit produces an empty SPICE description.
     */
    @Test
    public void toSpiceOnEmptyCircuitIsEmpty() {
        Circuit emptyCircuit = new Circuit();
        assertEquals("", emptyCircuit.toSpice());
    }
}
