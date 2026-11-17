import java.time.LocalDate;
import java.time.Period;
import java.util.List;

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

}
