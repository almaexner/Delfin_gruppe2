import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompetetiveSwimmer extends Member {
    private List<CompetitionResult> competitionResults = new ArrayList<>();

    public CompetetiveSwimmer(String name, int memberId, LocalDate dateOfBirth, MembershipType membershipType, SwimmerAgeType swimmerAgeType) {
        super(name, memberId, dateOfBirth, membershipType, swimmerAgeType);
    }

    public List<CompetitionResult> getCompetitionResults(){
        return competitionResults;
    }
    public void addCompetitionResults(CompetitionResult competitionResult){
        competitionResults.add(competitionResult);
    }
}
