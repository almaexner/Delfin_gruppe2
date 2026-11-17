import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Period;

public class MemberTest {
    Member member;
    LocalDate birthdate;

    @BeforeEach
    public void setup(){
        //Arrange
        birthdate = LocalDate.of(2000,1,1);
        member = new Member("Jørgen Leth", 1, birthdate, MembershipType.ACTIVE, null);

    }
    @Test
    public void testCalculateAge(){
        //Arrange
        LocalDate lc1 = LocalDate.of(2000,1,1);
        LocalDate lc2 = LocalDate.of(2012,1,1);
        Period period = Period.between(lc1, lc2);

        //Act
        int age = period.getYears();

        //Assert
        assertEquals(12, age);
    }
    @Test
    public void testSetSwimmerAgeType(){
        //Arrange
        LocalDate lc = LocalDate.of(2018, 1, 1);
        Period period = Period.between(birthdate, lc);
        int age = member.calculateAge(birthdate);

        member.setSwimmerAgeType();

        //Assert
        assertEquals(SwimmerAgeType.SENIOR, member.getSwimmerAgeType());
    }
}
