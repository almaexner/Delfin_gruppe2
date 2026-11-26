import java.util.Comparator;

public class SwimmerTrainingTimeComparator implements Comparator<CompetetiveSwimmer> {
    private Disciplin disciplin;

    public SwimmerTrainingTimeComparator(Disciplin disciplin) {
        this.disciplin = disciplin;
    }

    @Override
    public int compare(CompetetiveSwimmer competetiveSwimmer1, CompetetiveSwimmer competetiveSwimmer2) {
        int time1 = competetiveSwimmer1.getBestTrainingTime(disciplin).convertToMilliseconds();
        int time2 = competetiveSwimmer2.getBestTrainingTime(disciplin).convertToMilliseconds();

        return Integer.compare(time1, time2);
    }
}
