public class FitnessSwimmer extends Swimmer{
    SwimmerType swimmerType;
    Disciplin disciplin;

    public FitnessSwimmer(Member member, SwimmerAgeType swimmerAgeType, Disciplin disciplin, SwimmerType swimmerType) {
        super(member, swimmerAgeType);
        this.swimmerType = swimmerType;
        this.disciplin = disciplin;
    }

    public Disciplin getDisciplin() {
        return disciplin;
    }
}
