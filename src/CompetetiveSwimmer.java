public class CompetetiveSwimmer extends Swimmer{
    SwimmerType swimmerType;
    Disciplin disciplin;

    public CompetetiveSwimmer(Member member, SwimmerAgeType swimmerAgeType, Disciplin disciplin, SwimmerType swimmerType) {
        super(member, swimmerAgeType);
        this.disciplin = disciplin;
        this.swimmerType = swimmerType;
    }
}
