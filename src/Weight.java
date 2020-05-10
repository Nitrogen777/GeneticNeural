public class Weight {
    private Neuron from;
    private Neuron to;
    private double val;

    public Weight(Neuron from, Neuron to, double val) {
        this.from = from;
        this.to = to;
        this.val = val;
    }

    public Neuron getFrom() {
        return from;
    }

    public void setFrom(Neuron from) {
        this.from = from;
    }

    public Neuron getTo() {
        return to;
    }

    public void setTo(Neuron to) {
        this.to = to;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }
}
