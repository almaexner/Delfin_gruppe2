import java.time.LocalDate;
import java.time.Period;

public class Member {
    private final String name;
    private final int memberId;
    private final LocalDate dateOfBirth;
    private MembershipType membershipType;

    public Member(String name, int memberId, LocalDate dateOfBirth, MembershipType membershipType){
        this.name = name;
        this.memberId = memberId;
        this.dateOfBirth = dateOfBirth;
        this.membershipType = membershipType;
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
        return periodBetween.getYears();
    }

}
