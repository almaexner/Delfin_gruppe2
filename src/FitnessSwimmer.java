import java.time.LocalDate;

public class FitnessSwimmer extends Member{
    private SwimmerType swimmerType;

    public FitnessSwimmer(String name, int memberId, LocalDate dateOfBirth, MembershipType membershipType, SwimmerAgeType swimmerAgeType, SwimmerType swimmerType) {
        super(name, memberId, dateOfBirth, membershipType, swimmerAgeType);
        this.swimmerType = swimmerType;
    }
}
