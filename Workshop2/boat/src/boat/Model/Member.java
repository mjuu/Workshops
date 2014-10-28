package boat.Model;

import java.util.ArrayList;
import java.util.List;


public class Member{

    private String name;
    private String personId;
    private int memberID;
    private int uniqueID;
    private List<Boat> boatList;


    public Member(){
        boatList = new ArrayList<Boat>();
        uniqueID++;
        memberID = uniqueID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonId(String personId) {
        this.personId = personId;

    }

    public void setMemberId(int memberId) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public String getPersonId() {
        return personId;
    }

    public int getMemberId() {
        return memberID;
    }

    public void addBoat(Boat boat){
        boatList.add(boat);
    }

    public void deleteBoat(Boat boat){
        boatList.remove(boat);
    }

    public List<Boat> getBoatList(){
        return boatList;
    }
}
