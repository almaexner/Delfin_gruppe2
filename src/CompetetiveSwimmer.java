public class CompetetiveSwimmer extends Swimmer{
    SwimmerType swimmerType;

    public CompetetiveSwimmer(Member member, SwimmerAgeType swimmerAgeType, Disciplin disciplin, SwimmerType swimmerType) {
        super(member, swimmerAgeType, disciplin);
        this.swimmerType = swimmerType;
    }
}
