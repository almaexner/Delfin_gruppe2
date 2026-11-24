import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompetetiveSwimmer extends Member {
    private List<Disciplin> disciplins = new ArrayList<>();
    private List<CompetitionResult> competitionResults = new ArrayList<>();

    public CompetetiveSwimmer(String name, int memberId, LocalDate dateOfBirth, MembershipType membershipType, SwimmerAgeType swimmerAgeType) {
        super(name, memberId, dateOfBirth, membershipType, swimmerAgeType);
    }

    public boolean addDisciplin(Disciplin disciplin) {
        if (disciplins.contains(disciplin)) {
            return false;
        }
        disciplins.add(disciplin);
        return true;
    }

    public List<Disciplin> getDisciplins() {
        return disciplins;
    }
    public List<CompetitionResult> getCompetitionResults () {
        return competitionResults;
    }
    public void addCompetitionResults (CompetitionResult competitionResult){
        competitionResults.add(competitionResult);
    }
}
