import java.time.LocalDate;

public class FitnessSwimmer extends Member {
    public FitnessSwimmer(String name, int memberId, LocalDate dateOfBirth, MembershipType membershipType, SwimmerAgeType swimmerAgeType) {
        super(name, memberId, dateOfBirth, membershipType, swimmerAgeType);
    }
}
