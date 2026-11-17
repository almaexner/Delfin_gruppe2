import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClubManagementTest {
    List<Member> members = new ArrayList<>();

    @Test
    void addFeeTest(){
        //Arrange
        ClubManagement clubManagement = new ClubManagement();
        Member member = new Member("Alma", 1, LocalDate.of(1999, 9, 18), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        MemberFee memberFee = new MemberFee(member, 1600, LocalDate.of(2027, 1, 1));

        //Act
        clubManagement.addFee(memberFee);
        List<MemberFee> expected = List.of(memberFee);

        //Assert
        assertEquals(1, clubManagement.getFees().size()); //Sammenligner antal fee's i listen.
        assertEquals(expected, clubManagement.getFees()); //Sammenligner hvad der står i listen.
    }

    void addMemberTest(){

    }

}
