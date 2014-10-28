package boat.Model;

public class Boat {

    private String boatType;
    private String boatLength;
    private int boatId = 0;

    public void setBoatId(int id){
        boatId++;
        boatId = id;
    }

    public String setBoatType(int choice){
        switch (choice) {
            case 11:		boatType = "Sailingboat";
                break;
            case 12:		boatType = "Motorsailer";
                break;
            case 13:		boatType = "Motorboat";
                break;
            case 14:		boatType = "Cayaq/Canoot";
                break;
            case 15:		boatType = "Other";
                break;
        }
        return boatType;
    }

    public String setBoatLength(String boatLength){
        return this.boatLength = boatLength;
    }

    public String getBoatType(){
        return boatType;
    }

    public String getBoatLength(){
        return boatLength;
    }

    public int getBoatId(){
        return boatId;
    }

}




