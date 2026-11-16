public abstract class Swimmer {
    Member member;
    SwimmerAgeType swimmerAgeType;

    public Swimmer(Member member, SwimmerAgeType swimmerAgeType){
        this.member = member;
        this.swimmerAgeType = swimmerAgeType;
    }
}
