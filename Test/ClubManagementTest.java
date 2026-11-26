import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClubManagementTest {

    @Test
    void addFeeTest() {
        //Arrange
        ClubManagement clubManagement = new ClubManagement();
        FitnessSwimmer fitnessSwimmer = new FitnessSwimmer("Alma", 1, LocalDate.of(1999, 9, 18), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        MemberFee memberFee = new MemberFee(fitnessSwimmer, 1600, LocalDate.of(2027, 1, 1), false);

        //Act
        clubManagement.addFee(memberFee);
        List<MemberFee> actual = List.of(memberFee);

        //Assert
        assertEquals(1, clubManagement.getFees().size()); //Sammenligner antal fee's i listen.
        assertEquals(clubManagement.getFees(), actual); //Sammenligner hvad der står i listen.
    }

    @Test
    void addMemberTest() {
        //Arrange
        ClubManagement cM = new ClubManagement();
        FitnessSwimmer fitnessSwimmer = new FitnessSwimmer("Alma", 1, LocalDate.of(1999, 9, 18), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);

        //Act
        cM.addMember(fitnessSwimmer);
        List<Member> actual = List.of(fitnessSwimmer);

        //Assert
        assertEquals(1, cM.getMembers().size()); //Sammenligner antal medlemmer i listen.
        assertEquals(cM.getMembers(), actual); //Sammenligner hvad der står i listen.
    }

    @Test
    void removeMemberTest() {
        //Arrange
        ClubManagement cM = new ClubManagement();
        FitnessSwimmer member1 = new FitnessSwimmer("Alma", 1, LocalDate.of(1999, 9, 18), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        FitnessSwimmer member2 = new FitnessSwimmer("Harry", 2, LocalDate.of(2003, 10, 8), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        FitnessSwimmer member3 = new FitnessSwimmer("Jacob", 3, LocalDate.of(1997, 7, 14), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);

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
        FitnessSwimmer member1 = new FitnessSwimmer("Alma", 1, LocalDate.of(1999, 9, 18), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        FitnessSwimmer member2 = new FitnessSwimmer("Harry", 2, LocalDate.of(2003, 10, 8), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);

        cM.addMember(member1);
        cM.addMember(member2);

        //Act
        Member actual = cM.findMemberByMemberId(2); //actual = 2

        //Assert
        assertEquals(member2, actual);

    }

    @Test
    void couldNotFindMemberByMemberIdTest() {
        //Arrange
        ClubManagement cM = new ClubManagement();
        FitnessSwimmer member1 = new FitnessSwimmer("Alma", 1, LocalDate.of(1999, 9, 18), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        FitnessSwimmer member2 = new FitnessSwimmer("Harry", 2, LocalDate.of(2003, 10, 8), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);

        cM.addMember(member1);
        cM.addMember(member2);

        //Act
        Member actual = cM.findMemberByMemberId(10); //actual = 10

        //Assert
        assertNotEquals(member1, actual);
    }

    @Test
    void createFeeSeniorActiveFeeTest() {
        //Arrange
        ClubManagement cM = new ClubManagement();
        FitnessSwimmer member1 = new FitnessSwimmer("Alma", 1, LocalDate.of(1999, 9, 18), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        FitnessSwimmer member2 = new FitnessSwimmer("Harry", 2, LocalDate.now().minusYears(18), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        FitnessSwimmer member3 = new FitnessSwimmer("Jacob", 3, LocalDate.now().minusYears(60), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);

        cM.addMember(member1);
        cM.addMember(member2);
        cM.addMember(member3);

        //Act
        PaymentResult actual1 = cM.createFee(member1);
        PaymentResult actual2 = cM.createFee(member2);
        PaymentResult actual3 = cM.createFee(member3);

        //Assert

        //Test af member
        assertEquals(PaymentResult.SENIOR_ACTIVE, actual1);
        assertEquals(3, cM.getFees().size()); //Check af at alle memberFees er der
        assertEquals(1600, cM.getMemberFee(1).getFee()); //Check om medlemsnr1 betaler 1600,-

        //Test af member 18 år
        assertEquals(PaymentResult.SENIOR_ACTIVE, actual2);
        assertEquals(1600, cM.getMemberFee(2).getFee());

        //Test af member 60 år
        assertEquals(PaymentResult.SENIOR_ACTIVE, actual3);
        assertEquals(1600, cM.getMemberFee(3).getFee());
    }

    @Test
    void createFeeSeniorPassiveFeeTest() {
        //Arrange
        ClubManagement cM = new ClubManagement();
        FitnessSwimmer member1 = new FitnessSwimmer("Alma", 1, LocalDate.now().minusYears(18), MembershipType.PASSIVE, SwimmerAgeType.SENIOR);
        FitnessSwimmer member2 = new FitnessSwimmer("Harry", 2, LocalDate.now().minusYears(61), MembershipType.PASSIVE, SwimmerAgeType.SENIOR);

        cM.addMember(member1);
        cM.addMember(member2);

        //Act
        PaymentResult actual1 = cM.createFee(member1);
        PaymentResult actual2 = cM.createFee(member2);

        //Assert
        assertEquals(PaymentResult.PASSIVE, actual1);
        assertEquals(500, cM.getMemberFee(1).getFee());

        //Test at den ikke giver svømmer Senior discount hvis de er passive
        assertEquals(PaymentResult.PASSIVE, actual2);
        assertEquals(500, cM.getMemberFee(2).getFee());
    }

    @Test
    void createFeeSeniorActiveDiscountFeeTest() {
        //Arrange
        ClubManagement cM = new ClubManagement();
        FitnessSwimmer member1 = new FitnessSwimmer("Alma", 1, LocalDate.now().minusYears(61), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        FitnessSwimmer member2 = new FitnessSwimmer("Harry", 2, LocalDate.now().minusYears(60), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);

        cM.addMember(member1);
        cM.addMember(member2);

        //Act
        PaymentResult actual1 = cM.createFee(member1);
        PaymentResult actual2 = cM.createFee(member2);

        //Assert
        assertEquals(PaymentResult.SENIOR_ACTIVE_DISCOUNT, actual1); //Check af PaymentResult er det samme som SwimmerAgeType i makePayment
        assertEquals(1200, cM.getMemberFee(1).getFee());

        //Check af at den ikke giver normal senior pris
        assertNotEquals(PaymentResult.SENIOR_ACTIVE_DISCOUNT, actual2);
    }

    @Test
    void createFeeYouthActiveFeeTest() {
        //Arrange
        ClubManagement cM = new ClubManagement();
        FitnessSwimmer member1 = new FitnessSwimmer("Alma", 1, LocalDate.now().minusYears(17), MembershipType.ACTIVE, SwimmerAgeType.JUNIOR);

        cM.addMember(member1);

        //Act
        PaymentResult actual = cM.createFee(member1);

        //Assert
        assertEquals(PaymentResult.YOUTH_ACTIVE, actual);
        assertEquals(1000, cM.getMemberFee(1).getFee());
    }

    @Test
    void createFeeYouthPassiveFeeTest() {
        //Arrange
        ClubManagement cM = new ClubManagement();
        FitnessSwimmer member1 = new FitnessSwimmer("Alma", 1, LocalDate.now().minusYears(17), MembershipType.PASSIVE, SwimmerAgeType.JUNIOR);

        cM.addMember(member1);

        //Act
        PaymentResult actual = cM.createFee(member1);

        //Assert
        assertEquals(PaymentResult.PASSIVE, actual);
        assertEquals(500, cM.getMemberFee(1).getFee());
    }
    @Test
    void testCreatedMemberGetsAutomaticallyAssignedMemberId() {
        //Arrange
        UserInterface ui;
        ClubManagement clubManagement = new ClubManagement();
        List<Member> members = new ArrayList<>();

        //Act
        FitnessSwimmer member1 = new FitnessSwimmer("Jørgen Leth", clubManagement.getNewMemberId(), LocalDate.of(2000,1,1), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        clubManagement.addMember(member1);
        FitnessSwimmer member2 = new FitnessSwimmer("Jørgen Leth2", clubManagement.getNewMemberId(), LocalDate.of(2000,1,1), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
        clubManagement.addMember(member2);
        FitnessSwimmer member3 = new FitnessSwimmer("Jørgen Leth3", clubManagement.getNewMemberId(), LocalDate.of(2000,1,1), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);

        clubManagement.addMember(member3);

        //Assert
        assertEquals(1, member1.getMemberId());
        assertEquals(2, member2.getMemberId());
        assertEquals(3, member3.getMemberId());
    }
//    @Test
//    void testCreateAndAssignTrainingResult(){
//        //Arrange
//        ClubManagement clubManagement = new ClubManagement();
//        UserInterface ui = new UserInterface(clubManagement);
//        CompetetiveSwimmer swimmer = new CompetetiveSwimmer("Jørgen Leth", clubManagement.getNewMemberId(), LocalDate.of(2000, 1,1), MembershipType.ACTIVE, SwimmerAgeType.SENIOR);
//        //Act
//        Disciplin disciplin = Disciplin.CRAWL;
//        double resultTime =
//    }
}
