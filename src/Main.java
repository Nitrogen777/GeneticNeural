import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        Network n = new Network(new int[]{2,5,5,3});
        System.out.println(Arrays.toString(n.getOutput(new double[]{0,1})));
    }
}
