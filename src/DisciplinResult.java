import java.time.LocalDate;

public class DisciplinResult implements Comparable<DisciplinResult>{
    Disciplin disciplin;
    double resultTime;
    LocalDate resultDate;

    public DisciplinResult(Disciplin disciplin, double resultTime, LocalDate resultDate){
        this.disciplin = disciplin;
        this.resultTime = resultTime;
        this.resultDate = resultDate;
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
    @Override
    public String toString(){
        return "Tid: " + this.resultTime + "\n"
                + "Disciplin: " + this.disciplin + "\n"
                + "Dato: " + this.resultDate;
    }
    @Override
    public int compareTo(DisciplinResult other){
        if (this.resultTime < other.getResultTime()) return -1;
        if (this.resultTime > other.getResultTime()) return 1;
        return 0;
    }
}

