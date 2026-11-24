import java.time.LocalDate;

public class SeniorFeeCalculator implements FeeCalculator {
    private int age;
    private int fee = 1600;

    public SeniorFeeCalculator(int age) {
        this.age = age;
    }

    @Override
    public int calculateFee() {
        if (age > 60) {
            int discount = fee * 25 / 100;
            return fee - discount;
        }
        return fee;
    }

    @Override
    public LocalDate calculateDueDate(LocalDate date) {
        return LocalDate.of(date.getYear() + 1, 1, 1); //date.getYear()+1 tager aktuelle år og ligger 1 år til. Derefter dato 1/1
    }
}
