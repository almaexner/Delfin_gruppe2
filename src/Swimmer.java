public abstract class Swimmer {
    private final Member member;
    SwimmerAgeType swimmerAgeType;
    Disciplin disciplin;

    public Swimmer(Member member, SwimmerAgeType swimmerAgeType, Disciplin disciplin){
        this.member = member;
        this.swimmerAgeType = swimmerAgeType;
        this.disciplin = disciplin;
    }
    public Member getMember(){
        return member;
    }

    public Disciplin getDisciplin() {
        return disciplin;
    }
    @Override
    public String toString(){
        return member + " " + swimmerAgeType + " " + disciplin;
    }

}
