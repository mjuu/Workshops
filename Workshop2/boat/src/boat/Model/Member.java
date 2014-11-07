package boat.Model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Member{

    private String name;
    private String personId;
    private String uniqueID;
    private int boatID = 1;
    private List<Boat> boatList;


    public Member(){
        boatList = new ArrayList<Boat>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonId(String personId) {
        this.personId = personId;

    }

    public void setMemberId(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getName() {
        return name;
    }

    public String getPersonId() {
        return personId;
    }

    public String getMemberId() {
        return uniqueID;
    }

    public void addBoat(Boat boat){
        boat.setBoatId(boatID++);
        boatList.add(boat);
    }

    public void remove(Boat boat){
        boatList.remove(boat);
    }

    public List<Boat> getBoatList(){
        return boatList;
    }

    public int getNumberOfBoats(){
        return boatList.size();
    }
}
