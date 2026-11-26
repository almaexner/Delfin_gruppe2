import java.time.LocalDate;

public class CompetitionResult {
    private final Disciplin disciplin;
    private final ResultTime resultTime;
    private final LocalDate resultDate;
    private final int placement;

    public CompetitionResult(Disciplin disciplin, ResultTime resultTime, LocalDate resultDate, int placement){
        this.disciplin = disciplin;
        this.resultTime = resultTime;
        this.resultDate = resultDate;
        this.placement = placement;
    }
    public Disciplin getDisciplin(){
        return disciplin;
    }
    public ResultTime getResultTime(){
        return resultTime;
    }
    public LocalDate getResultDate(){
        return resultDate;
    }
    public int getPlacement(){
        return placement;
    }
}
