import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        Network n = new Network(new int[]{2,5,5,3});
        Network n1 = new Network(new int[]{2,5,5,3}, n.getWeightArr());
        System.out.println(Arrays.toString(n.getOutput(new double[]{0,1})));
        System.out.println(Arrays.toString(n1.getOutput(new double[]{0,1})));
    }
}
