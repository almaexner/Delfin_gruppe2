import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompetetiveSwimmer extends Member {
    private List<Disciplin> disciplin = new ArrayList<>();

    public CompetetiveSwimmer(String name, int memberId, LocalDate dateOfBirth, MembershipType membershipType, SwimmerAgeType swimmerAgeType) {
        super(name, memberId, dateOfBirth, membershipType, swimmerAgeType);
    }

    public List<Disciplin> getDisciplin() {
        return disciplin;
    }
}
