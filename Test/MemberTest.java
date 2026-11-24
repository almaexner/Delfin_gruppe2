import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class MemberTest {
    FitnessSwimmer member;
    LocalDate birthdate;

    @BeforeEach
    public void setup(){
        //Arrange
        birthdate = LocalDate.of(2000,1,1);
        member = new FitnessSwimmer("Jørgen Leth", 1, birthdate, MembershipType.ACTIVE, null);

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
    @Test
    public void testComparingMembersByID(){
        //Arrange
        FitnessSwimmer m2 = new FitnessSwimmer("Donald Trump", 2, LocalDate.now(), MembershipType.ACTIVE, null);
        FitnessSwimmer m3 = new FitnessSwimmer("Lars Larsen", 3, LocalDate.now(), MembershipType.ACTIVE, null);

        //Act
        List<Member> members = new ArrayList<>();
        members.add(member);
        members.add(m2);
        members.add(m3);
        members.sort(Member::compareTo);

        //Assert
        assertEquals(member, members.get(0));
        assertEquals(m2, members.get(1));
        assertEquals(m3, members.get(2));
    }
    @Test
    public void getMemberSwimmingAgeType(){
        //Act
        member.setSwimmerAgeType();
        SwimmerAgeType result = member.getSwimmerAgeType();

        //Assert
        assertEquals(SwimmerAgeType.SENIOR, result);
    }

    @Test
    public void getMembershipType(){
        //Act
        MembershipType result = member.getMembershipType();

        //Assert
        assertEquals(MembershipType.ACTIVE, result);
    }
}
