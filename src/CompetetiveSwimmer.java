public class CompetetiveSwimmer extends Swimmer{
    SwimmerType swimmerType;

    public CompetetiveSwimmer(Member member, SwimmerAgeType swimmerAgeType, SwimmerType swimmerType) {
        super(member, swimmerAgeType);
        this.swimmerType = swimmerType;
    }
}
