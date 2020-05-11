import java.util.ArrayList;
import java.util.HashMap;

public class Network {
    private Neuron[][] netArray;
    private ArrayList<Weight> weightArr;
    private HashMap<Neuron, ArrayList<Weight>> weightMap;

    public Network(int[] sizes){
        netArray = new Neuron[sizes.length][];
        weightArr = new ArrayList<>();
        weightMap = new HashMap<>();
        for(int i = 0; i < sizes.length; i++){
            netArray[i] = new Neuron[sizes[i]];
            for(int j = 0; j < sizes[i]; j++){
                netArray[i][j] = new Neuron(0);
            }
        }

        for(int i = 0; i < netArray.length-1; i++){
            for(int j = 0; j < netArray[i].length; j++){
                for(int k = 0; k < netArray[i+1].length; k++){
                    //This is absolutely not a good way to do this. Too bad!
                    Neuron to = netArray[i+1][k];
                    Weight w = new Weight(i ,j, Math.random()*2 - 1, i+1, k);
                    weightArr.add(w);
                    if(weightMap.containsKey(to)){
                        weightMap.get(to).add(w);
                    }else{
                        ArrayList<Weight> toWeight = new ArrayList<>();
                        toWeight.add(w);
                        weightMap.put(to, toWeight);
                    }
                }
            }
        }
    }
    public Network(int[] sizes, ArrayList<Weight> weightArr){
        netArray = new Neuron[sizes.length][];

        for(int i = 0; i < sizes.length; i++){
            netArray[i] = new Neuron[sizes[i]];
            for(int j = 0; j < sizes[i]; j++){
                netArray[i][j] = new Neuron(0);
            }
        }

        this.weightArr = (ArrayList)weightArr.clone();
        for(Weight w : this.weightArr){
            Neuron to = netArray[w.getToLayer()][w.getToIndex()];
            if(weightMap.containsKey(to)){
                weightMap.get(to).add(w);
            }else{
                ArrayList<Weight> toWeight = new ArrayList<>();
                toWeight.add(w);
                weightMap.put(to, toWeight);
            }
        }
    }

    public ArrayList<Weight> getWeightArr() {
        return weightArr;
    }

    public void setWeightArr(ArrayList<Weight> weightArr) {
        this.weightArr = weightArr;
    }

    public double[] getOutput(double[] input){
        for(int i = 0; i < netArray[0].length; i++){
            netArray[0][i].setVal(input[i]);
        }
        for(int i = 1; i < netArray.length; i++){
            for(int j = 0; j < netArray[i].length; j++){
                netArray[i][j].setVal(sigmoid(getExpression(netArray[i][j])));
            }
        }

        double[] out = new double[netArray[netArray.length-1].length];
        for(int i = 0; i < netArray[netArray.length-1].length; i++){
            out[i] = netArray[netArray.length-1][i].getVal();
        }
        return out;
    }
    private double getExpression(Neuron n){
        double sum = 0;

        for(Weight w : weightMap.get(n)){
            sum += w.getVal() * netArray[w.getFromLayer()][w.getFromIndex()].getVal();
        }

        return sum;
    }

    private double sigmoid(double x){
        return 1.0 / (1 + Math.pow(Math.E, -x));
    }
}
