import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClubManagementTest {

    @Test
    void addFeeTest() {
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

    @Test
    void addMemberTest() {
        //Arrange
        ClubManagement cM = new ClubManagement();
        Member member = new Member("Alma", 1, LocalDate.of(1999, 9, 18), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);

        //Act
        cM.addMember(member);
        List<Member> expected = List.of(member);

        //Assert
        assertEquals(1, cM.getMembers().size()); //Sammenligner antal medlemmer i listen.
        assertEquals(expected, cM.getMembers()); //Sammenligner hvad der står i listen.
    }

    @Test
    void removeMemberTest() {
        //Arrange
        ClubManagement cM = new ClubManagement();
        Member member1 = new Member("Alma", 1, LocalDate.of(1999, 9, 18), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        Member member2 = new Member("Harry", 2, LocalDate.of(2003, 10, 8), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        Member member3 = new Member("Jacob", 3, LocalDate.of(1997, 7, 14), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);

        cM.addMember(member1); //3 medlemmer er tilføjet
        cM.addMember(member2);
        cM.addMember(member3);

        //Act
        cM.removeMember(member1);
        // List<Member> expected = List.of(member2, member3);

        //Assert
        assertEquals(2, cM.getMembers().size()); //Check at den har fjernet 1 medlem og nu har 2 i listen
    }

    @Test
    public void findMemberByMemberIdTest() {
        //Arrange
        ClubManagement cM = new ClubManagement();
        Member member1 = new Member("Alma", 1, LocalDate.of(1999, 9, 18), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        Member member2 = new Member("Harry", 2, LocalDate.of(2003, 10, 8), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);

        cM.addMember(member1);
        cM.addMember(member2);

        //Act
        Member expected = cM.findMemberByMemberId(2); //expected = 2

        //Assert
        assertEquals(expected, member2);

    }

    @Test
    void couldNotFindMemberByMemberIdTest(){
        //Arrange
        ClubManagement cM = new ClubManagement();
        Member member1 = new Member("Alma", 1, LocalDate.of(1999, 9, 18), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        Member member2 = new Member("Harry", 2, LocalDate.of(2003, 10, 8), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);

        cM.addMember(member1);
        cM.addMember(member2);

        //Act
        Member expected = cM.findMemberByMemberId(10); //expected = 10

        //Assert
        assertNotEquals(expected, member1);
    }

    @Test
    void makePaymentSeniorFeeTest(){
        //Arrange
        ClubManagement cM = new ClubManagement();
        Member member1 = new Member("Alma", 1, LocalDate.of(1999, 9, 18), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        Member member2 = new Member("Harry", 2, LocalDate.now().minusYears(18), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        Member member3 = new Member("Jacob", 1, LocalDate.now().minusYears(60), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);

        cM.addMember(member1);
        cM.addMember(member2);
        cM.addMember(member3);

        //Act
        PaymentResult expected1 = cM.makePayment(member1);
        PaymentResult expected2 = cM.makePayment(member2);
        PaymentResult expected3 = cM.makePayment(member3);

        //Assert

        //Test af member
        assertEquals(expected1, PaymentResult.SENIOR_ACTIVE);
        assertEquals(1, cM.getFees().size());
        assertEquals(1600, cM.getMemberFee(1).getFee()); //Check om medlemsnr1 betaler 1600,-

        //Test af member 18 år
        assertEquals(expected2, PaymentResult.SENIOR_ACTIVE);
        assertEquals(1600, cM.getMemberFee(2).getFee());
    }

}
