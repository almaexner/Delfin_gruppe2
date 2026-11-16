import java.time.LocalDate;

public class YouthFee implements Fee {

    @Override
    public int calculateFee(){
        return 1000;
    }

    @Override
    public LocalDate calculateDueDate(LocalDate date){
        return LocalDate.of(date.getYear()+1, 1, 1); //date.getYear()+1 tager aktuelle år og ligger 1 år til. Derefter dato 1/1
    }
}
