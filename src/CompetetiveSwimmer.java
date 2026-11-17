import java.time.LocalDate;

public class CompetetiveSwimmer extends Member{
    Disciplin disciplin;

    public CompetetiveSwimmer(String name, int memberId, LocalDate dateOfBirth, MembershipType membershipType, Disciplin disciplin) {
        super(name, memberId, dateOfBirth, membershipType, disciplin);
        this.disciplin = disciplin;
    }
    public Disciplin getDisciplin() {
        return disciplin;
    }
}
