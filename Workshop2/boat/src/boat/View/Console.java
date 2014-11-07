package boat.View;

import boat.Controller.SystemController;
import boat.Model.Boat;
import boat.Model.Member;
import java.util.List;
import java.util.Scanner;


public class Console {

    private Scanner scanner = new Scanner(System.in);
    private Scanner integerScan = new Scanner(System.in);
    private SystemController controller = new SystemController();
    public String input;
    public int boatType;

    public void startMenu(){
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~Welcome to Boat Club \"Seaman\"~~~~~~~~~~~~~~" +
                "\n Press a to Add Member " +
                "\n Press v to View Member " +
                "\n Press e to Edit Member " +
                "\n Press r to Remove Member" +
                "\n Press n to Add Boat " +
                "\n Press u to Edit Boat " +
                "\n Press d tp Remove Boat " +
                "\n Press c to View Compact List of Members " +
                "\n Press f to View Full List of Members " +
                "\n Press g to Show Menu " +
                "\n Press x to Exit\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        while (input != ""){
            while (scanner.hasNextLine()){
                input = scanner.nextLine();
                switch(input) {
                    case "a":
                        System.out.println("~~~~~~~~~~~~~~Add Member~~~~~~~~~~~~~~");
                        addMember();
                        break;
                    case "v":
                        System.out.println("~~~~~~~~~~~~~~View Member~~~~~~~~~~~~~~");
                        viewMember();
                        break;
                    case "e":
                        System.out.println("~~~~~~~~~~~~~~Edit Member~~~~~~~~~~~~~~");
                        editMember();
                        break;
                    case "r":
                        System.out.println("~~~~~~~~~~~~~~Remove Member~~~~~~~~~~~~~~");
                        removeMember();
                        break;
                    case "n":
                        System.out.println("~~~~~~~~~~~~~~Add Boat~~~~~~~~~~~~~~");
                        addBoat();
                        break;
                    case "u":
                        System.out.println("~~~~~~~~~~~~~~Edit Boat~~~~~~~~~~~~~~");
                        editBoat();
                        break;
                    case "d":
                        System.out.println("~~~~~~~~~~~~~~Remove Boat~~~~~~~~~~~~~~");
                        removeBoat();
                        break;
                    case "c":
                        System.out.println("~~~~~~~~~~~~~~Compact List of Members~~~~~~~~~~~~~~");
                        viewCompactList(controller.getMemberList());
                        break;
                    case "f":
                        System.out.println("~~~~~~~~~~~~~~Full List of Members~~~~~~~~~~~~~~");
                        viewFullList(controller.getMemberList());
                        break;
                    case "g":
                        System.out.println("~~~~~~~~~~~~~~Show menu~~~~~~~~~~~~~~");
                        startMenu();
                        break;
                    case "x":
                        System.out.println("~~~~~~~~~~~~~~Goodbye for now, i hope!~~~~~~~~~~~~~~");
                        System.exit(0);
                        break;
                }
            }
        }
    }

    public void addMember(){
        System.out.println("Name: ");
        String memberName = scanner.nextLine();

        System.out.println("Personal nr [YYMMDDXXXX] : ");
        String personalNr = scanner.nextLine();

        controller.addMember(memberName, personalNr);
    }

    public void editMember() {
        System.out.println("Personal nr [YYMMDDXXXX] : ");
        String personalNr = scanner.nextLine();
        Member member = controller.getMemberByPersonalNr(personalNr);

        if (member == null){
            System.out.println("Member is not in system!");
            startMenu();
        }
        else{
            System.out.println("Press m to edit name"
                    + "\nPress e to edit personal nr"
                    + "\nPress q to cancel");

            while (input != "") {
                while (scanner.hasNextLine()) {
                    input = scanner.nextLine();

                    switch (input) {
                        case "m":

                            System.out.println("Enter new name: ");
                            String newMemberName = scanner.nextLine();
                            controller.editMemberName(personalNr, newMemberName);
                            editMember();
                            break;
                        case "e":
                            System.out.println("Enter new personal nr: ");
                            String newPersonalNr = scanner.nextLine();
                            controller.editPersonalNr(personalNr, newPersonalNr);
                            editMember();
                            break;
                        case "q":
                            System.out.println("Cancel");
                            startMenu();
                            break;
                    }
                }
            }
        }
    }


    public void viewMember(){
        System.out.println("Enter personal nr [YYMMDDXXXX] : ");
        String personalNr = scanner.nextLine();
        Member member = controller.getMemberByPersonalNr(personalNr);

        if (member == null){
            System.out.println("Member is not in system!");
            startMenu();
        }
        else{
            System.out.println("Personal Nr: " + member.getPersonId()
                    + "\nName: " + member.getName()
                    + "\nMemberId: " + member.getMemberId());

            for (Boat boat : member.getBoatList()){
                System.out.println("~~~~~~~~~"
                        +"\nBoat Nr: "+boat.getBoatID()
                        +"\nBoat Type: "+boat.getBoatType()
                        +"\nBoat Length: "+boat.getBoatLength()+" cm");
            }
        }
    }

    public void removeMember(){
        System.out.println("Enter personal nr [YYMMDDXXXX] : ");
        String personalNr = scanner.nextLine();
        Member member = controller.getMemberByPersonalNr(personalNr);

        if(member == null){
            System.out.println("Member not in system!");
        }
        else{
            controller.removeMember(personalNr);
        }
    }

    public void addBoat(){

        System.out.println("Personal nr of the member [YYMMDDXXXX] : ");
        String personalNr = scanner.nextLine();

        System.out.println("What type of boat do you want to add: "+
                "\nPress 1 for Sailingboat"+
                "\nPress 2 for Motorsailer"+
                "\nPress 3 for Motorboat"+
                "\nPress 4 for Cayaq/Canoot"+
                "\nPress 5 for Other");
        boatType = integerScan.nextInt();

        System.out.println("Lenght of the boat in meters ");
        String boatLength = scanner.nextLine();

        controller.addBoat(boatType,boatLength,personalNr);
    }

    public void editBoat(){
        System.out.println("Enter personal nr: ");
        String personalNr = scanner.nextLine();

        System.out.println("Enter boat id: ");
        int boatId = integerScan.nextInt();

        System.out.println("Length of the boat in meters: ");
        String boatLength = scanner.nextLine();

        System.out.println("What type of boat do you want to add: "+
                "\nPress 1 for Sailingboat"+
                "\nPress 2 for Motorsailer"+
                "\nPress 3 for Motorboat"+
                "\nPress 4 for Cayaq/Canoot"+
                "\nPress 5 for Other");
        boatType = scanner.nextInt();

        controller.editBoat(personalNr,boatId,boatType,boatLength);
    }

    public void removeBoat() {
        System.out.println("Enter personal nr: ");
        String personalNr = scanner.nextLine();

        System.out.println("Boat id of the boat you wish to remove: ");
        int boatId = scanner.nextInt();

        controller.removeBoat(personalNr,boatId);
    }

    public void viewCompactList(List<Member> memberList){

        for (Member member : memberList){
            System.out.println("Personal nr: "+member.getPersonId()
                    +"\nName: "+member.getName()
                    +"\nNumber of boats: "+member.getNumberOfBoats());
        }
    }

    public void viewFullList(List<Member> memberList){

        for (Member member : memberList){
            System.out.println("MemberId: "+ member.getMemberId()
                    +"\nPersonal nr: "  + member.getPersonId()
                    +"\nName: "+ member.getName());

            for (Boat boat : member.getBoatList()){
                System.out.println("~~~~~~~~~"
                        +"\nBoat nr: "+ boat.getBoatID()
                        +"\nBoat type: "+boat.getBoatType()
                        +"\nBoat lenght: "+boat.getBoatLength()+" meters");
            }
        }
    }
}
