import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CsvMapperTest {

    private CsvMapper mapper = new CsvMapper();

    @Test
    void csvToMember() {
        //Arrenge
        Member expectedMember = new Member("Alma", 1, LocalDate.of(1999, 9, 13), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        String memberCsv = "Alma,1,1999-09-13,ACTIVE,SENIOR";

        //act
        Member actualMember = mapper.csvToMember(memberCsv);

        //Assert
        assertEquals(expectedMember, actualMember);

    }

    @Test
    void memberToCsv() {
        //Arrange
        String expectedMemberCsv = "Alma,1,1999-09-13,ACTIVE,SENIOR";
        Member member = new Member("Alma", 1, LocalDate.of(1999, 9,13), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);

        //Act
        String actualCsv = mapper.memberToCsv(member);

        //Assert
        assertEquals(expectedMemberCsv, actualCsv);
    }

    @Test
    void csvToFitnessSwimmer() {
        //Arrange
        FitnessSwimmer expectedFitnessSwimmer = new FitnessSwimmer("Jacob", 2, LocalDate.of(1993, 6, 2),MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        String fitnessSwimmerCsv = "Jacob,2,1993-06-02,ACTIVE,SENIOR";

        //Act
        FitnessSwimmer actualFitnessSwimmer = mapper.csvToFitnessSwimmer(fitnessSwimmerCsv);

        //Assert
        assertEquals(expectedFitnessSwimmer, actualFitnessSwimmer);
    }

    @Test
    void fitnessSwimmerToCsv() {
        //Arrange
        String expectedFitnessSwimmerCsv = "Jacob,2,1993-06-02,ACTIVE,SENIOR";
        FitnessSwimmer fitnessSwimmer = new FitnessSwimmer("Jacob", 2, LocalDate.of(1993, 6, 2), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);

        //Act
        String actualFitnessSwimmer = mapper.fitnessSwimmerToCsv(fitnessSwimmer);

        //Assert
        assertEquals(expectedFitnessSwimmerCsv, actualFitnessSwimmer);
    }

    @Test
    void csvCompeteiveSwimmer() {
        //Arrange
        CompetetiveSwimmer expectedCompetetiveSwimmer = new CompetetiveSwimmer("Harry", 3, LocalDate.of(2003, 10, 8), MembershipType.ACTIVE, SwimmerAgeType.SENIOR, Disciplin.BUTTERFLY) ;
        String competeiveSwimmerCsv = "Harry,3,2003-10-08,ACTIVE,SENIOR,BUTTERFLY";

        //Act
        CompetetiveSwimmer actualCompetiveSwimmer = mapper.CsvCompeteiveSwimmer(competeiveSwimmerCsv);

        //Assert
        assertEquals(expectedCompetetiveSwimmer, actualCompetiveSwimmer);
    }

    @Test
    void competetiveSwimmertToCsv() {
        //Arrange
        String expectedCompetiveSwimmerCsv = "Harry,3,2003-10-08,ACTIVE,SENIOR,BUTTERFLY";
        CompetetiveSwimmer competetiveSwimmer = new CompetetiveSwimmer("Harry", 3, LocalDate.of(2003, 10, 8), MembershipType.ACTIVE, SwimmerAgeType.SENIOR, Disciplin.BUTTERFLY);

        //Act
        String actualCompetiveSwimmerCsv = mapper.competetiveSwimmertToCsv(competetiveSwimmer);

        //Assert
        assertEquals(expectedCompetiveSwimmerCsv, actualCompetiveSwimmerCsv);
    }
}