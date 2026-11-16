import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Period;

public class MemberTest {
    @Test
    public void calculateAge(){
        //Arrange
        LocalDate lc1 = LocalDate.of(2000,1,1);
        LocalDate lc2 = LocalDate.of(2012,1,1);
        Period period = Period.between(lc1, lc2);

        //Act
        int age = period.getYears();

        //Assert
        assertEquals(12, age);

    }
}
