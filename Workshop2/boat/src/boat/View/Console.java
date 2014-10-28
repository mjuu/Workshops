package boat.View;




import boat.Controller.SystemController;
import boat.Model.Boat;
import boat.Model.Member;
import java.util.List;
import java.util.Scanner;


public class Console {

    public Scanner scan = new Scanner(System.in);
    public Scanner selectionScanner = new Scanner(System.in);
    public SystemController controller = new SystemController();
    public int choice;


    public void startMenu(){
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~Welcome to Boat Club \"Seaman\"~~~~~~~~~~~~~~" +
                "\n[1]Add Member " +
                "\n[2]View Member " +
                "\n[3]Edit Member " +
                "\n[4]Remove Member" +
                "\n[5]Add Boat " +
                "\n[6]Edit Boat " +
                "\n[7]Remove Boat " +
                "\n[8]Compact List of Members " +
                "\n[9]Full List of Members " +
                "\n[10]Exit\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        while(choice != 9){
            choice = selectionScanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("~~~~~~~~~~~~~~Add Member~~~~~~~~~~~~~~");
                    addMember();
                    break;
                case 2:
                    System.out.println("~~~~~~~~~~~~~~View Member~~~~~~~~~~~~~~");
                    viewMember();
                    break;
                case 3:
                    System.out.println("~~~~~~~~~~~~~~Edit Member~~~~~~~~~~~~~~");
                    editMember();
                    break;
                case 4:
                    System.out.println("~~~~~~~~~~~~~~Remove Member~~~~~~~~~~~~~~");
                    removeMember();
                    break;
                case 5:
                    System.out.println("~~~~~~~~~~~~~~Add Boat~~~~~~~~~~~~~~");
                    addBoat();
                    break;
                case 6:
                    System.out.println("~~~~~~~~~~~~~~Edit Boat~~~~~~~~~~~~~~");
                    editBoat();
                    break;
                case 7:
                    System.out.println("~~~~~~~~~~~~~~Remove Boat~~~~~~~~~~~~~~");
                    removeBoat();
                    break;
                case 8:
                    System.out.println("~~~~~~~~~~~~~~Compact List of Members~~~~~~~~~~~~~~");
                    viewCompactList(controller.getMemberList());
                    break;
                case 9:
                    System.out.println("~~~~~~~~~~~~~~Full List of Members~~~~~~~~~~~~~~");
                    viewFullList(controller.getMemberList());
                    break;
                case 10:
                    System.out.println("~~~~~~~~~~~~~~Sea you later, don´t get eaten by a`n alligator~~~~~~~~~~~~~~");
                    System.exit(0);
                    break;
            }
        }

        selectionScanner.close();
    }

    public void addMember(){
        System.out.println("Name: ");
        String memberName = scan.nextLine();

        System.out.println("PersonalNr (yymmddxxxx) ");
        String personalNr = scan.nextLine();

        controller.addMember(memberName, personalNr);
        scan.close();

    }

    public void editMember(){
        System.out.println("Name: ");
        String memberName = scan.nextLine();

        System.out.println("PersonalNr (yyymmddxxxx) ");
        String personalNr = scan.nextLine();

        System.out.println("[1]Edit name"
                +"\n[2]Edit PersonalNr");

        if (choice == 1) {
            System.out.println("Enter the new name");
            String newMemberName = scan.nextLine();

            controller.editMemberName(memberName, newMemberName);
            scan.close();
        }
        else if (choice == 2){
            System.out.println("Enter the new personalNr");
            String newPNr = scan.nextLine();

            controller.editPersonalNr(personalNr,newPNr);
            scan.close();
        }
        scan.close();
    }


    public void viewMember(){
        System.out.println("Enter the personalNr of the member:  ");
        String personalNr = scan.nextLine();

        Member member = controller.getMember(personalNr);

        System.out.println("PersonalNr: " + member.getName()
                + "\n" + "Name: " + member.getPersonId()
                + "\nMemberId: " + member.getMemberId());

        for (Boat boat : member.getBoatList()){
            System.out.println("---------------"
                    +"\nBoatNr: "+boat.getBoatId()
                    +"\nBoat Type: "+boat.getBoatType()
                    +"\nBoat Length: "+boat.getBoatLength()+" meter");
        }
        scan.close();
    }

    public void removeMember(){
        System.out.println("Enter personalNr");
        String personalNr = scan.nextLine();

        controller.removeMember(personalNr);
        scan.close();
    }

    public void addBoat(){

        System.out.println("What type of boat do you want to add: "+
                "\n[11]Sailingboat"+
                "\n[12]Motorsailer"+
                "\n[13]Motorboat"+
                "\n[14]Cayaq/Canoot"+
                "\n[15]Other"+
                "\n[16]Back");
        choice = selectionScanner.nextInt();

        System.out.println("What is the length of the boat in (m): ");
        String boatLength = scan.nextLine();

        System.out.println("Enter the personalId of the member you want to register the boat to: ");
        String personalId = scan.nextLine();

        controller.addBoat(choice, boatLength, personalId);
        scan.close();
        selectionScanner.close();
    }

    public void editBoat(){
        System.out.println("Enter your personalNr: ");
        String personalId = scan.nextLine();

        System.out.println("Enter the boatNr: ");
        int boatId = selectionScanner.nextInt();

        System.out.println("What type of boat do you want to edit to: "+
                "\n[11]Sailingboat"+
                "\n[12]Motorsailer"+
                "\n[13]Motorboat"+
                "\n[14]Cayaq/Canoot"+
                "\n[15]Other"+
                "\n[16]Back");
        choice = selectionScanner.nextInt();

        System.out.println("What is the length of the boat in (m): ");
        String boatLength = scan.nextLine();

        controller.editBoat(personalId, boatId, choice, boatLength);
        scan.close();
        selectionScanner.close();
    }

    public void removeBoat() {
        System.out.println("Enter your personalNr: ");
        String personalId = scan.nextLine();

        System.out.println("Type the boatNr of the boat you want to remove:");
        int boatId = selectionScanner.nextInt();

        controller.removeBoat(personalId, boatId);
        scan.close();
        selectionScanner.close();
    }

    public void viewCompactList(List<Member> memberList){

        for (Member member : memberList){

            System.out.println("PersonalNr: "+member.getPersonId()
                    +"\nName: "+member.getName()
                    +"\nNr of Boats: "+member.getBoatList().size());
        }
    }

    public void viewFullList(List<Member> memberList){

        for (Member member : memberList){

            System.out.println("PersonalNr: "  + member.getName()
                    + "\nName: "+ member.getPersonId()
                    + "\nMemberId: " + member.getMemberId());

            for (Boat boat : member.getBoatList()){

                System.out.println("BoatNr: "+ boat.getBoatId()
                        +"\nBoat Type: "+boat.getBoatType()
                        +"\nBoat Length: "+boat.getBoatLength()+" meter");
            }
        }
    }
}
