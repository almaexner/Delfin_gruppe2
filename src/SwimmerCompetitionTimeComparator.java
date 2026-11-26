import java.util.Comparator;

public class SwimmerCompetitionTimeComparator implements Comparator<CompetetiveSwimmer> {
    private Disciplin disciplin;

    public SwimmerCompetitionTimeComparator(Disciplin disciplin) {
        this.disciplin = disciplin;
    }

    @Override
    public int compare(CompetetiveSwimmer competetiveSwimmer1, CompetetiveSwimmer competetiveSwimmer2) {
        int time1 = competetiveSwimmer1.getBestCompetitionTime(disciplin).convertToMilliseconds();
        int time2 = competetiveSwimmer2.getBestCompetitionTime(disciplin).convertToMilliseconds();

        return Integer.compare(time1, time2);
    }
}
