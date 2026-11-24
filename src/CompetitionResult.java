import java.time.LocalDate;

public class CompetitionResult implements Comparable<CompetitionResult> {
    private final Disciplin disciplin;
    private final double resultTime;
    private final LocalDate resultDate;
    private final String location;

    public CompetitionResult(Disciplin disciplin, double resultTime, LocalDate resultDate, String location){
        this.disciplin = disciplin;
        this.resultTime = resultTime;
        this.resultDate = resultDate;
        this.location = location;
    }
    public Disciplin getDisciplin(){
        return disciplin;
    }
    public double getResultTime(){
        return resultTime;
    }
    public LocalDate getResultDate(){
        return resultDate;
    }
    public String getLocation(){
        return location;
    }

    @Override
    public int compareTo(CompetitionResult other){
        if (this.resultTime < other.getResultTime()) return -1;
        if (this.resultTime > other.getResultTime()) return 1;
        return 0;
    }
}
