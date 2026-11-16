import java.time.LocalDate;

public class CompetetiveSwimmer extends Member{
    SwimmerType swimmerType;
    Disciplin disciplin;

    public CompetetiveSwimmer(String name, int memberId, LocalDate dateOfBirth, MembershipType membershipType, Disciplin disciplin, SwimmerType swimmerType) {
        super(name, memberId, dateOfBirth, membershipType);
        this.disciplin = disciplin;
        this.swimmerType = swimmerType;
    }
}
