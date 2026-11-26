public class Trainer {
    private final String name;
    private final int trainerId;

    public Trainer(String name, int trainerId){
        this.name = name;
        this.trainerId = trainerId;
    }
    public String getName(){
        return name;
    }
    public int getTrainerId(){
        return trainerId;
    }
    @Override
    public String toString(){
        return "Træner: " + this.name + "(" + this.trainerId + ")";
    }
}
