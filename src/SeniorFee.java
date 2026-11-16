import java.time.LocalDate;

public class SeniorFee implements Fee {
    int age;

    public SeniorFee(int age){
        this.age = age;
    }

    @Override
    public int calculateFee(){
        if (age > 60){ //25% af 1600 = 1200
            return 1200;
        }
        return 1600;
    }

    @Override
    public LocalDate calculateDueDate(LocalDate date){
        return LocalDate.of(date.getYear()+1, 1, 1); //date.getYear()+1 tager aktuelle år og ligger 1 år til. Derefter dato 1/1
    }
}
