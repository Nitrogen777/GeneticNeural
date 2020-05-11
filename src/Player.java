import java.util.ArrayList;

public class Player implements Comparable<Player>{
    private Network brain;
    private double score;

    public Player(int[] sizes){
        brain = new Network(sizes);
        score = 0;
    }

    public Player(int[] sizes, ArrayList<Weight> weights){
        brain = new Network(sizes, weights);
        score = 0;
    }

    public double[] calculateOutput(double[] input){
        return brain.getOutput(input);
    }

    private int maxIndex(double[] out){
        double max = 0;
        int index = 0;

        for(int i = 0; i < out.length; i++){
            if(out[i] > max){
                max = out[i];
                index = i;
            }
        }
        return index;
    }

    public int maxNeuronIndex(double[] input){
        return maxIndex(calculateOutput(input));
    }

    public ArrayList<Weight> getWeight(){
        return brain.getWeightArr();
    }

    //Absolutely not efficient in any way. God bless my CPU.
    public Weight getWeightFromIndex(int layerFrom, int indexFrom, int layerTo, int indexTo){
        for(Weight w : getWeight()){
            if(w.getFromLayer() == layerFrom && w.getFromIndex() == indexFrom
                    && w.getToLayer() == layerTo && w.getToIndex() == indexTo){
                return w;
            }
        }
        return null;
    }
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }


    @Override
    public int compareTo(Player o) {
        if(this.score > o.score){
            return -1;
        }else if(this.score < o.score){
            return 1;
        }
        return 0;
    }
}
