public class FitnessSwimmer extends Swimmer{
    SwimmerType swimmerType;

    public FitnessSwimmer(Member member, SwimmerAgeType swimmerAgeType, SwimmerType swimmerType) {
        super(member, swimmerAgeType);
        this.swimmerType = swimmerType;
    }
}
