import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName; //JuniorTeam eller SeniorTeam
    private SwimmerAgeType swimmerAgeType;
    private List<CompetetiveSwimmer> competetiveSwimmers = new ArrayList<>();

    public Team(String teamName, SwimmerAgeType swimmerAgeType) {
        this.teamName = teamName;
        this.swimmerAgeType = swimmerAgeType;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<CompetetiveSwimmer> getCompetetiveSwimmers() {
        return competetiveSwimmers;
    }

    public boolean addSwimmerToTeam(CompetetiveSwimmer competetiveSwimmer) {
        if (competetiveSwimmer.getSwimmerAgeType() != swimmerAgeType) { //Hvis ikke alder passer med holdets aldersgruppe
            return false;
        }
        if (competetiveSwimmers.contains(competetiveSwimmer)) { //Hvis svømmeren allerede er tilføjet på teamet
            return false;
        }
        return competetiveSwimmers.add(competetiveSwimmer);
    }

    public void sortSwimmers() {
        competetiveSwimmers.sort(new MemberNameComparator());
    }
}
