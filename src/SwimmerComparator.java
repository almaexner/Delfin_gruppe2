import java.util.Comparator;

public class SwimmerComparator implements Comparator<Swimmer> {


    @Override
    public int compare(Swimmer o1, Swimmer o2) {
        return o1.getDisciplin().compareTo(o2.getDisciplin());
    }
}
