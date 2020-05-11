import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class Game {
    protected Player[] starterGeneration;
    protected int genSize;
    protected int[] brainSizes;
    protected int topPart;
    protected double mutation;

    public Game(int genSize, int[] brainSizes, int topPart, double mutation){
        this.genSize = genSize;
        this.brainSizes = brainSizes;
        this.topPart = topPart;
        this.mutation = mutation;
        starterGeneration = new Player[genSize];
        for(int i = 0; i < genSize; i++){
            starterGeneration[i] = new Player(brainSizes);
        }
    }

    protected abstract double score(Player p);

    public void calculateScores(Player[] gen){
        for(Player p : gen){
            p.setScore(score(p));
        }
    }

    public Player[] getTop(Player[] gen){
        Player[] top = new Player[genSize/topPart];
        Arrays.sort(gen);
        for(int i = 0; i < genSize/topPart; i++){
            top[i] = gen[i];
        }
        return top;
    }

    public Player[] breed(Player[] top){
        Player[] newGeneration = new Player[genSize];
        Random r = new Random();
        for(int i = 0; i < genSize; i++){
            int weightNumber = top[0].getWeight().size(); //hopefully its the same
            ArrayList<Weight> weights = new ArrayList<>();
            for(int j = 0; j < weightNumber; j++){
                Player m = top[r.nextInt(genSize/topPart)];
                Player f = top[r.nextInt(genSize/topPart)];
                Weight mw = m.getWeight().get(j);
                Weight fw = f.getWeightFromIndex(mw.getFromLayer(), mw.getFromIndex(), mw.getToLayer(),
                        mw.getToIndex());
                weights.add(new Weight(mw, (mw.getVal() + fw.getVal())/2 + Math.random()*(mutation*2)-mutation)); //oh god this is horrible
            }
            newGeneration[i] = new Player(brainSizes, weights);
        }
        return newGeneration;
    }

    public abstract void run();
}
