package coe318.lab7;

/**
 * Abstract base for any two-terminal element that can live inside a
 * {@link Circuit}. Every element remembers the two nodes it bridges and knows
 * how to render itself as a single line of SPICE netlist text. Concrete
 * subclasses (for example {@link Resistor} and {@link VoltageSource}) only have
 * to supply the SPICE type letter and the value portion of the line.
 *
 * @author Harre PK
 */
public abstract class CircuitElement {

    /** Node that the first listed terminal of this element attaches to. */
    private final int firstTerminal;

    /** Node that the second listed terminal of this element attaches to. */
    private final int secondTerminal;

    /**
     * Stores the two nodes this element is wired between.
     *
     * @param firstTerminal node attached to the first terminal
     * @param secondTerminal node attached to the second terminal
     */
    protected CircuitElement(int firstTerminal, int secondTerminal) {
        this.firstTerminal = firstTerminal;
        this.secondTerminal = secondTerminal;
    }

    /**
     * @return the node wired to the first terminal of this element
     */
    public int getFirstTerminal() {
        return firstTerminal;
    }

    /**
     * @return the node wired to the second terminal of this element
     */
    public int getSecondTerminal() {
        return secondTerminal;
    }

    /**
     * The single capital letter that SPICE uses to label this kind of element
     * (for example {@code "R"} for a resistor or {@code "V"} for a source).
     *
     * @return the SPICE type letter for this element
     */
    public abstract String spiceLetter();

    /**
     * The trailing portion of the SPICE line that is specific to this element,
     * i.e. everything that comes after the node pair. For a resistor this is the
     * resistance value; for a voltage source it is {@code "DC <volts>"}.
     *
     * @return the element-specific tail of the SPICE description
     */
    public abstract String spiceTail();

    /**
     * Builds the complete SPICE netlist line for this element. The serial number
     * is supplied by the owning {@link Circuit} so that elements of the same
     * type are numbered sequentially (R1, R2, ... and V1, V2, ...).
     *
     * @param serialNumber the sequence number for this element's type
     * @return a line such as {@code "R1 1 2 0.25"} or {@code "V2 2 0 DC 3.0"}
     */
    public String toSpiceLine(int serialNumber) {
        return spiceLetter() + serialNumber + " "
                + firstTerminal + " " + secondTerminal + " "
                + spiceTail();
    }
}
