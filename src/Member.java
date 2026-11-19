import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

public class Member implements Comparable<Member>{
    private final String name;
    private final int memberId;
    private final LocalDate dateOfBirth;
    private MembershipType membershipType;
    private SwimmerAgeType swimmerAgeType;
    private List<MemberFee> memberFees;

    public Member(String name, int memberId, LocalDate dateOfBirth, MembershipType membershipType, SwimmerAgeType swimmerAgeType){
        this.name = name;
        this.memberId = memberId;
        this.dateOfBirth = dateOfBirth;
        this.membershipType = membershipType;
        this.swimmerAgeType = swimmerAgeType;
    }

    //Getters
    public int getMemberId(){
        return memberId;
    }
    public String getName(){
        return name;
    }
    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
    public MembershipType getMembershipType(){
        return membershipType;
    }
    //Setters
    public void setMembershipType(MembershipType membershipType){
        this.membershipType = membershipType;
    }

    public SwimmerAgeType getSwimmerAgeType(){
        return swimmerAgeType;
    }
    public int calculateAge(LocalDate dateOfBirth){
        LocalDate dateNow = LocalDate.now();
        Period periodBetween = Period.between(dateOfBirth, dateNow);
        int age = periodBetween.getYears();
        return age;
    }

    public void setSwimmerAgeType(){
        int age = calculateAge(dateOfBirth);

        if (age < 18){
            this.swimmerAgeType = SwimmerAgeType.JUNIOR;
        }
        else {
            this.swimmerAgeType = SwimmerAgeType.SENIOR;
        }
    }

    @Override
    public String toString(){
        return String.format("""
                %s (ID: %d)
                Medlemskab: %s""", name, memberId, membershipType);
    }
    @Override
    public int compareTo(Member o){
        return Integer.compare(this.memberId, o.memberId);
    }

    //equals
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return memberId == member.memberId && Objects.equals(name, member.name) && Objects.equals(dateOfBirth, member.dateOfBirth) && membershipType == member.membershipType && swimmerAgeType == member.swimmerAgeType && Objects.equals(memberFees, member.memberFees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, memberId, dateOfBirth, membershipType, swimmerAgeType, memberFees);
    }
}
