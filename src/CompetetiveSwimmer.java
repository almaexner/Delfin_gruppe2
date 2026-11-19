import java.time.LocalDate;

public class CompetetiveSwimmer extends Member {
    private Disciplin disciplin;

    public CompetetiveSwimmer(String name, int memberId, LocalDate dateOfBirth, MembershipType membershipType, SwimmerAgeType swimmerAgeType, Disciplin disciplin) {
        super(name, memberId, dateOfBirth, membershipType, swimmerAgeType);
        this.disciplin = disciplin;
    }

    public Disciplin getDisciplin() {
        return disciplin;
    }
}
