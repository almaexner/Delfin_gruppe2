import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClubManagement {
    private List<Member> members = new ArrayList<>();
    private List<MemberFee> fees = new ArrayList<>();
    private List<Integer> memberIds = new ArrayList<>();
    int[] numbers;
    private List<CompetetiveSwimmer> competetiveSwimmers = new ArrayList<>();
    private List<FitnessSwimmer> fitnessSwimmers = new ArrayList<>();

    public ClubManagement() {
    }

    public void addFee(MemberFee memberFee) {
        fees.add(memberFee);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void addCompetetiveSwimmer(CompetetiveSwimmer competetiveSwimmer) {
        competetiveSwimmers.add(competetiveSwimmer);
    }

    public void addFitnessSwimmers(FitnessSwimmer fitnessSwimmer) {
        fitnessSwimmers.add(fitnessSwimmer);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }

    public void removeCompetetiveSwimmer(CompetetiveSwimmer competetiveSwimmer) {
        competetiveSwimmers.remove(competetiveSwimmer);
    }

    public void removeFitnessSwimmer(FitnessSwimmer fitnessSwimmer) {
        fitnessSwimmers.remove(fitnessSwimmer);
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<CompetetiveSwimmer> getCompetetiveSwimmers() {
        return competetiveSwimmers;
    }

    public List<FitnessSwimmer> getFitnessSwimmers() {
        return fitnessSwimmers;
    }

    public Member findMemberByMemberId(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public CompetetiveSwimmer findCompetetiveSwimmerByMemberId(int memberId) {
        for (CompetetiveSwimmer competetiveSwimmer : competetiveSwimmers) {
            if (competetiveSwimmer.getMemberId() == memberId) {
                return competetiveSwimmer;
            }
        }
        return null;
    }

    public FitnessSwimmer findFitnessSwimmerByMemberId(int memberId) {
        for (FitnessSwimmer fitnessSwimmer : fitnessSwimmers) {
            if (fitnessSwimmer.getMemberId() == memberId) {
                return fitnessSwimmer;
            }
        }
        return null;
    }

    public List<MemberFee> getFees() {
        return fees;
    }

    public List<MemberFee> getUnpaidFees(int memberId) {
        List<MemberFee> undpaidFees = new ArrayList<>();
        for (MemberFee memberFee : fees) {
            if (memberFee.getMember().getMemberId() == memberId && !memberFee.isPaid()) {
                undpaidFees.add(memberFee);
            }
        }
        return undpaidFees;
    }

    public boolean registerPayment(MemberFee memberFee) {
        if (memberFee.isPaid()) {
            return false;
        }
        memberFee.setPaid(true); //Ændrer værdien af paid fra false til true og regningen er derfor betalt.
        return true;
    }

    public PaymentResult createFee(Member member) {
        int memberAge = member.calculateAge(member.getDateOfBirth());
        MembershipType membershipType = member.getMembershipType();
        PassiveFee passiveFee = new PassiveFee();
        YouthFee youthFee = new YouthFee();
        SeniorFee seniorFee = new SeniorFee(memberAge);
        if (membershipType.equals(MembershipType.ACTIVE) && (memberAge < 18)) {
            addFee(new MemberFee(member, youthFee.calculateFee(), youthFee.calculateDueDate(LocalDate.now()), false));
            return PaymentResult.YOUTH_ACTIVE;
        }
        if (membershipType.equals(MembershipType.ACTIVE) && (memberAge <= 60)) {
            addFee(new MemberFee(member, seniorFee.calculateFee(), seniorFee.calculateDueDate(LocalDate.now()), false));
            return PaymentResult.SENIOR_ACTIVE;
        }
        if (membershipType.equals(MembershipType.ACTIVE) && (memberAge > 60)) {
            addFee(new MemberFee(member, seniorFee.calculateFee(), seniorFee.calculateDueDate(LocalDate.now()), false));
            return PaymentResult.SENIOR_ACTIVE_DISCOUNT;
        } else {
            addFee(new MemberFee(member, passiveFee.calculateFee(), passiveFee.calculateDueDate(LocalDate.now()), false));
            return PaymentResult.PASSIVE;
        }
    }

    public MemberFee getMemberFee(int memberId) {
        Member memberFound = null;
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                memberFound = member;
            }
        }
        if (memberFound != null) {
            for (MemberFee memberFee : fees) {
                if (memberFee.getMember().equals(memberFound)) {
                    return memberFee;
                }
            }
        }
        return null;
    }

    public int getNewMemberId() { //Finder højeste memberId i listen og ligger +1 oveni det nye der
        //bliver tildelt
        int highestMemberId = 0;

        for (Member member : members) {
            if (member.getMemberId() > highestMemberId) {
                highestMemberId = member.getMemberId();
            }
        }
        return highestMemberId + 1;
    }

    public List<MemberFee> getOverdueFees(LocalDate date) {
        List<MemberFee> overdueFees = new ArrayList<>();

        for (MemberFee memberFee : fees) {
            if (!memberFee.isPaid() && memberFee.getDueDate().isBefore(date)) {
                overdueFees.add(memberFee);
            }
        }
        return overdueFees;
    }
}
