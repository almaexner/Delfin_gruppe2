import java.util.ArrayList;
import java.util.List;

public class ClubManagement {
    List<Member> members = new ArrayList<>();
    List<Swimmer> swimmers = new ArrayList<>();
    UserInterface ui;

    public ClubManagement(){

    }
    public void addMember(Member member){
        members.add(member);
    }
    public void removeMember(Member member){
        members.remove(member);
    }
    public void addSwimmer(Swimmer swimmer){
        swimmers.add(swimmer);
    }
    public void removeSwimmer(Swimmer swimmer){
        swimmers.remove(swimmer);
    }
    public List<Member> getMembers(){
        return members;
    }
    public List<Swimmer> getSwimmers(){
        return swimmers;
    }


}
