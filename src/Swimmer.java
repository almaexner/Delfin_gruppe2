public abstract class Swimmer {
    private final Member member;
    SwimmerAgeType swimmerAgeType;

    public Swimmer(Member member, SwimmerAgeType swimmerAgeType){
        this.member = member;
        this.swimmerAgeType = swimmerAgeType;
    }
    public Member getMember(){
        return member;
    }

    @Override
    public String toString(){
        return member + " " + swimmerAgeType;
    }

}
