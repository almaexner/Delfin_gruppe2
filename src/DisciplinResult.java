import java.time.LocalDate;

public class DisciplinResult {
    Disciplin disciplin;
    ResultTime resultTime;
    LocalDate resultDate;

    public DisciplinResult(Disciplin disciplin, ResultTime resultTime, LocalDate resultDate){
        this.disciplin = disciplin;
        this.resultTime = resultTime;
        this.resultDate = resultDate;
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
    @Override
    public String toString(){
        return "Tid: " + this.resultTime + "\n"
                + "Disciplin: " + this.disciplin + "\n"
                + "Dato: " + this.resultDate;
    }

}

