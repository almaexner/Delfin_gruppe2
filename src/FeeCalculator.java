import java.time.LocalDate;

public interface FeeCalculator {
    int calculateFee();

    LocalDate calculateDueDate(LocalDate date);
}
