package boat.Model;

public class Boat {

    private String boatType;
    private String boatLength;
    private int boatID;

    public void setBoatId(int boatID){
        this.boatID = boatID;
    }

    public void setBoatType(int choice){

        switch (choice) {
            case 1:
                boatType = "Sailingboat";
                break;
            case 2:
                boatType = "Motorsailer";
                break;
            case 3:
                boatType = "Motorboat";
                break;
            case 4:
                boatType = "Cayaq/Canoot";
                break;
            case 5:
                boatType = "Other";
                break;
            default:
                boatType = "Other";
        }
    }
    
    public void setBoatTypeString(String boatType) {
      this.boatType = boatType;
    }

    public void setBoatLength(String boatLength){
        this.boatLength = boatLength;
    }

    public String getBoatType(){
        return boatType;
    }

    public String getBoatLength(){
        return boatLength;
    }

    public int getBoatID(){
        return boatID;
    }

}