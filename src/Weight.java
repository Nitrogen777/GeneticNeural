public class Weight {
    private int fromLayer;
    private int fromIndex;
    private double val;
    private int toLayer;
    private int toIndex;


    public Weight(int fromLayer, int fromIndex, double val, int toLayer, int toIndex) {
        this.fromLayer = fromLayer;
        this.fromIndex = fromIndex;
        this.toLayer = toLayer;
        this.toIndex = toIndex;
        this.val = val;
    }

    public Weight(Weight other, double val){
        this.fromLayer = other.fromLayer;
        this.fromIndex = other.fromIndex;
        this.toLayer = other.toLayer;
        this.toIndex = other.toIndex;
        this.val = val;
    }

    public int getFromLayer() {
        return fromLayer;
    }

    public void setFromLayer(int fromLayer) {
        this.fromLayer = fromLayer;
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }

    public int getToLayer() {
        return toLayer;
    }

    public void setToLayer(int toLayer) {
        this.toLayer = toLayer;
    }

    public int getToIndex() {
        return toIndex;
    }

    public void setToIndex(int toIndex) {
        this.toIndex = toIndex;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "From: " + fromLayer + ", " + fromIndex + "\n"
                + "To: " + toLayer + ", " + toIndex + "\n"
                + "Value: " + val;
    }
}
