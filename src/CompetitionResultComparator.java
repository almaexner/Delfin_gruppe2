import java.util.Comparator;

public class CompetitionResultComparator implements Comparator<CompetitionResult> {

    @Override
    public int compare(CompetitionResult o1, CompetitionResult o2) {
        return Integer.compare(o1.getResultTime().convertToMilliseconds(), o2.getResultTime().convertToMilliseconds());
    }
}
