package coe318.lab7;

/**
 * A DC voltage source. Unlike a resistor, a source is polarized, so the first
 * node is treated as the positive terminal and the second node as the negative
 * terminal. Reversing the nodes therefore negates the source voltage, matching
 * the rule that {@code v 1 0 2.0} is equivalent to {@code v 0 1 -2.0}.
 *
 * @author Harre PK
 */
public class VoltageSource extends CircuitElement {

    /** Potential difference of the source in volts (positive terminal first). */
    private final double emfVolts;

    /**
     * Creates a DC voltage source with a defined polarity.
     *
     * @param positiveNode node attached to the positive terminal
     * @param negativeNode node attached to the negative terminal
     * @param emfVolts the source voltage in volts
     */
    public VoltageSource(int positiveNode, int negativeNode, double emfVolts) {
        super(positiveNode, negativeNode);
        this.emfVolts = emfVolts;
    }

    /**
     * @return the source voltage in volts (measured from the positive terminal)
     */
    public double getEmfVolts() {
        return emfVolts;
    }

    /**
     * {@inheritDoc} A voltage source is labelled with the letter {@code "V"} in
     * SPICE.
     */
    @Override
    public String spiceLetter() {
        return "V";
    }

    /**
     * {@inheritDoc} For a voltage source the tail of the SPICE line marks it as
     * a DC source and states the voltage, e.g. {@code "DC 2.0"}.
     */
    @Override
    public String spiceTail() {
        return "DC " + Double.toString(emfVolts);
    }
}
