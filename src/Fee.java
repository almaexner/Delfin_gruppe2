import java.time.LocalDate;

public interface Fee {
    int calculateFee();
    LocalDate calculateDueDate(LocalDate date);
}
