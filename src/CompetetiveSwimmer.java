import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompetetiveSwimmer extends Member {
    private List<Disciplin> disciplins = new ArrayList<>();
    private List<CompetitionResult> competitionResults = new ArrayList<>();
    private List<TrainingResult> trainingResults = new ArrayList<>();

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

    public void setDisciplinResults(List<TrainingResult> trainingResults) {
        this.trainingResults = trainingResults;
    }

    public void setCompetitionResults(List<CompetitionResult> competitionResults) {
        this.competitionResults = competitionResults;
    }

    public List<CompetitionResult> getCompetitionResults() {
        return competitionResults;
    }

    public List<TrainingResult> getDisciplinResults() {
        return trainingResults;
    }

    public ResultTime getBestTrainingTime(Disciplin disciplin) {
        ResultTime bestTime = null;

        for (TrainingResult trainingResult : trainingResults) {
            if (trainingResult.getDisciplin() == disciplin) {
                if (bestTime == null) {
                    bestTime = trainingResult.getResultTime();
                } else if (trainingResult.getResultTime().convertToMilliseconds() < bestTime.convertToMilliseconds()) {
                    bestTime = trainingResult.getResultTime();
                }
            }
        }
        return bestTime;
    }

    public ResultTime getBestCompetitionTime(Disciplin disciplin) {
        ResultTime bestTime = null;

        for (CompetitionResult competitionResult : competitionResults) {
            if (competitionResult.getDisciplin() == disciplin) {
                if (bestTime == null) {
                    bestTime = competitionResult.getResultTime();
                } else if (competitionResult.getResultTime().convertToMilliseconds() < bestTime.convertToMilliseconds()) {
                    bestTime = competitionResult.getResultTime();
                }
            }
        }
        return bestTime;
    }


    @Override
    public String toString() {
        return "Navn: " + getName() + " (ID: " + getMemberId() + ")\n" + "Disciplin(er): " + getDisciplins();
    }
}
