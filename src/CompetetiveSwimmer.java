import java.time.LocalDate;

public class CompetetiveSwimmer extends Member{
    private SwimmerType swimmerType;
    private Disciplin disciplin;

    public CompetetiveSwimmer(String name, int memberId, LocalDate dateOfBirth, MembershipType membershipType, SwimmerAgeType swimmerAgeType, Disciplin disciplin, SwimmerType swimmerType) {
        super(name, memberId, dateOfBirth, membershipType, swimmerAgeType);
        this.disciplin = disciplin;
        this.swimmerType = swimmerType;
    }
}
