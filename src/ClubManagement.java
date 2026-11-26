import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClubManagement {
    private List<Member> members = new ArrayList<>();
    private List<MemberFee> fees = new ArrayList<>();
    private Team juniorTeam = new Team("JuniorTeam", SwimmerAgeType.JUNIOR);
    private Team seniorTeam = new Team("SeniorTeam", SwimmerAgeType.SENIOR);

    public ClubManagement() {
    }

    public void addFee(MemberFee memberFee) {
        fees.add(memberFee);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<CompetetiveSwimmer> getCompetetiveSwimmers() {
        ArrayList<CompetetiveSwimmer> competetiveSwimmers = new ArrayList<>();
        for (Member member : members) {
            if (member instanceof CompetetiveSwimmer) {
                CompetetiveSwimmer competetiveSwimmer = (CompetetiveSwimmer) member;
                competetiveSwimmers.add(competetiveSwimmer);
            }
        }
        return competetiveSwimmers;
    }

    public List<FitnessSwimmer> getFitnessSwimmers() {
        ArrayList<FitnessSwimmer> fitnessSwimmers = new ArrayList<>();
        for (Member member : members) {
            if (member instanceof FitnessSwimmer) {
                FitnessSwimmer fitnessSwimmer = (FitnessSwimmer) member;
                fitnessSwimmers.add(fitnessSwimmer);
            }
        }
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
        for (Member member : members) {
            if (member instanceof CompetetiveSwimmer) {
                CompetetiveSwimmer competetiveSwimmer = (CompetetiveSwimmer) member;
                if (competetiveSwimmer.getMemberId() == memberId) {
                    return competetiveSwimmer;
                }
            }
        }
        return null;
    }

    public FitnessSwimmer findFitnessSwimmerByMemberId(int memberId) {
        for (Member member : members) {
            if (member instanceof FitnessSwimmer) {
                FitnessSwimmer fitnessSwimmer = (FitnessSwimmer) member;
                if (fitnessSwimmer.getMemberId() == memberId) {
                    return fitnessSwimmer;
                }
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
        PassiveFeeCalculator passiveFee = new PassiveFeeCalculator();
        YouthFeeCalculator youthFee = new YouthFeeCalculator();
        SeniorFeeCalculator seniorFee = new SeniorFeeCalculator(memberAge);
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

    public CompetetiveSwimmer fitnessSwimmerToCompetetiveSwimmer(int memberId) {
        Member member = findMemberByMemberId(memberId);

        if (member == null) {
            return null;
        }

        if (member instanceof CompetetiveSwimmer) {
            return (CompetetiveSwimmer) member;
        }
        CompetetiveSwimmer competetiveSwimmer = new CompetetiveSwimmer(member.getName(),
                member.getMemberId(),
                member.getDateOfBirth(),
                member.getMembershipType(),
                member.getSwimmerAgeType());

        for (MemberFee memberFee : fees) {
            if (memberFee.getMember().getMemberId() == memberId) {
                memberFee.setMember(competetiveSwimmer);
            }
        }
        members.remove(member);
        members.add(competetiveSwimmer);
        return competetiveSwimmer;
    }
    public void assignTrainingResult(CompetetiveSwimmer competetiveSwimmer, DisciplinResult disciplinResult){
        List<DisciplinResult> results = new ArrayList<>(competetiveSwimmer.getDisciplinResults());
        Comparator<DisciplinResult> resultComparator = new ResultTimeComparator();
        results.add(disciplinResult);
        results.sort(resultComparator);
        competetiveSwimmer.setDisciplinResults(results);
    }
    public void assignCompetitionResult(CompetetiveSwimmer competetiveSwimmer, CompetitionResult competitionResult){
        List<CompetitionResult> results = new ArrayList<>(competetiveSwimmer.getCompetitionResults());
        results.add(competitionResult);
        results.sort(new CompetitionResultComparator());
        competetiveSwimmer.setCompetitionResults(results);
    }
    public ResultTime convertTime(String resultTime){
        String[] tokens = resultTime.split(":");
        int minutes = Integer.parseInt(tokens[0]);
        int seconds = Integer.parseInt(tokens[1]);
        int milliseconds = Integer.parseInt(tokens[2]);

        return new ResultTime(minutes, seconds, milliseconds);
    }

    public void makeTeam() {
        juniorTeam.getCompetetiveSwimmers().clear();
        seniorTeam.getCompetetiveSwimmers().clear();

        for (CompetetiveSwimmer competetiveSwimmer : getCompetetiveSwimmers()) {
            competetiveSwimmer.setSwimmerAgeType();

            if (competetiveSwimmer.getSwimmerAgeType() == SwimmerAgeType.JUNIOR) {
                juniorTeam.addSwimmerToTeam(competetiveSwimmer);
            } else seniorTeam.addSwimmerToTeam(competetiveSwimmer);
        }

        juniorTeam.sortSwimmers();
        seniorTeam.sortSwimmers();
    }

    public Team getJuniorTeam() {
        makeTeam();
        return juniorTeam;
    }

    public Team getSeniorTeam() {
        makeTeam();
        return seniorTeam;
    }
}
