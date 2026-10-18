package coe318.lab7;

/**
 * A non-polarized resistor wired between two nodes and characterised by its
 * resistance in ohms. Because a resistor is symmetric, the order in which its
 * two nodes are listed carries no electrical meaning.
 *
 * @author Harre PK
 */
public class Resistor extends CircuitElement {

    /** Resistance of this component, measured in ohms. */
    private final double resistanceOhms;

    /**
     * Creates a resistor connected between two nodes.
     *
     * @param firstTerminal one of the two nodes the resistor spans
     * @param secondTerminal the other node the resistor spans
     * @param resistanceOhms the resistance of the component in ohms
     */
    public Resistor(int firstTerminal, int secondTerminal, double resistanceOhms) {
        super(firstTerminal, secondTerminal);
        this.resistanceOhms = resistanceOhms;
    }

    /**
     * @return the resistance of this component in ohms
     */
    public double getResistanceOhms() {
        return resistanceOhms;
    }

    /**
     * {@inheritDoc} A resistor is labelled with the letter {@code "R"} in SPICE.
     */
    @Override
    public String spiceLetter() {
        return "R";
    }

    /**
     * {@inheritDoc} For a resistor the tail of the SPICE line is simply the
     * resistance value.
     */
    @Override
    public String spiceTail() {
        return Double.toString(resistanceOhms);
    }
}
