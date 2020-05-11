public class HelloWorld extends Game {
    private String goal;

    public HelloWorld(int genSize,int[] brainSizes, int topPart, double mutation, String goal){
        super(genSize, brainSizes, topPart, mutation);
        this.goal = goal;
    }

    private String generate(Player p){
        String generated = "";
        generated += (char)Math.round(p.calculateOutput(new double[]{0 / 255.0, 0})[0] * 255.0);
        for(int i = 1; i < goal.length(); i++){
            generated += (char)Math.round(p.calculateOutput(new double[]{(int)generated.charAt(i-1) / 255.0, i})[0] * 255.0);
        }
        return generated;
    }
    @Override
    protected double score(Player p) {
        double difference = 0;
        String generated = generate(p);
        for(int i = 0; i < goal.length(); i++){
            difference -= Math.abs(generated.charAt(i) - goal.charAt(i));
        }
        return difference;
    }

    public void run(){
        calculateScores(starterGeneration);
        Player[] top = getTop(starterGeneration);
        String s = generate(top[0]);
        System.out.println(s);
        Player[] newGen = breed(top);
        int i = 1;
        while(!s.equals(goal)){
            calculateScores(newGen);
            top = getTop(newGen);
            s = generate(top[0]);
            System.out.println(s + ", Generation: " + i);
            newGen = breed(top);
            i++;
        }

    }
}
