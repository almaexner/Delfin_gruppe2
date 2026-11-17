import java.time.LocalDate;

public class FitnessSwimmer extends Member{
    SwimmerType swimmerType;

    public FitnessSwimmer(String name, int memberId, LocalDate dateOfBirth, MembershipType membershipType) {
        super(name, memberId, dateOfBirth, membershipType);
        this.swimmerType = swimmerType;
    }
}
