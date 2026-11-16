public class FitnessSwimmer extends Swimmer{
    SwimmerType swimmerType;

    public FitnessSwimmer(Member member, SwimmerAgeType swimmerAgeType, Disciplin disciplin, SwimmerType swimmerType) {
        super(member, swimmerAgeType, disciplin);
        this.swimmerType = swimmerType;
        this.disciplin = disciplin;
    }
}
