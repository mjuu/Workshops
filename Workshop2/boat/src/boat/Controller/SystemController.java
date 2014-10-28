package boat.Controller;

import boat.Model.Boat;
import boat.Model.Member;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class SystemController {

    private final String filePath = "boatClubSystem.xml";
    public List<Member> memberList = new ArrayList<Member>();

    public SystemController() {
        //readFromSystem();
    }

    /*
   Adds the member to the memberList, then saves the systemfile
    */
    public void addMember(String name, String personalNr) {

        Member member = new Member();
        member.setName(name);
        member.setPersonId(personalNr);

        memberList.add(member);

        saveSystem();
    }

    public void editMemberName(String oldMemberName, String newMemberName) {

        Member member = getMember(oldMemberName);
        member.setName(newMemberName);

        saveSystem();
        System.out.println("Member name was edited!");
    }

    public void editPersonalNr(String oldPersonalNr, String newPersonalNr){

        Member member = getMember(oldPersonalNr);
        member.setPersonId(newPersonalNr);

        saveSystem();
        System.out.println("Member personal number was edited!");
    }

    /*

     */
    public void removeMember(String personalNr) {
        Member member = getMember(personalNr);

        if (personalNr.equals(member.getPersonId())) {

            memberList.remove(member);
            saveSystem();
            System.out.println("Member was removed!");

        } else {
            System.out.println("Member could not be removed!");
        }
    }

    /*
    Adds the boat to the given personalNr that the user input
    If the member exists, then the boat is added to that members boatList
    and saves the systemfile
     */
    public void addBoat(int typeChoice, String length, String personalNr) {

        Member member = getMember(personalNr);
        Boat boat = new Boat();

        boat.setBoatType(typeChoice);
        boat.setBoatLength(length);

        member.addBoat(boat);
        saveSystem();
        System.out.println("Boat was added!");
    }

    /*
    Loops throught memberList and the member boatList
    to find the specific member, if the boatID matches
    some boat in the boatList, that one is edited.
     */
    public void editBoat(String personalNr, int boatId, int choice, String length) {

        Member member = getMember(personalNr);

        if (personalNr.equals(member.getPersonId())) {

            for (Boat boat : member.getBoatList()) {

                if (boatId == boat.getBoatId()) {

                    boat.setBoatType(choice);
                    boat.setBoatLength(length);

                    saveSystem();
                    System.out.println("Boat was edited!");
                }
            }
        }
    }

    /*
    Deletes the boat from the member boatlist if personalId
    and the boatId is correct and found!
     */
    public void removeBoat(String personalNr, int boatId) {

        Member member = getMember(personalNr);

        for (Boat boat : member.getBoatList()) {

            if (boatId == boat.getBoatId()) {

                member.deleteBoat(boat);

                saveSystem();
                System.out.println("Boat was deleted!");
            }
        }
    }

    /*
    Searches and finds the member by its personalNr
    and returns the member
     */
    public Member getMember(String personalNr) {

        Member member = null;

        for (Member m : memberList) {

            if (personalNr.equals(m.getPersonId())) {

                return member = m;
            } else {
                System.out.println("Could not find member with that personalNr");
            }
        }
        return member;
    }

    /*
    Saves the system information to .xml format file
    and store every data.
     */
    public void saveSystem () {
        FileOutputStream outputStream = null;
        XMLStreamWriter sWriter = null;

        try {

            outputStream = new FileOutputStream(filePath);
            sWriter = XMLOutputFactory.newInstance()
                    .createXMLStreamWriter(outputStream);

            sWriter.writeStartDocument();
            sWriter.writeCharacters("\n");
            sWriter.writeStartElement("Members");
            sWriter.writeCharacters("\n\t");
            for (int i = 0; i < memberList.size(); i++) {

                Member member = memberList.get(i);

                sWriter.writeStartElement("Member");
                sWriter.writeAttribute("memberId", Integer.toString(member.getMemberId()));

                sWriter.writeCharacters("\n\t\t");

                sWriter.writeStartElement("name");
                sWriter.writeCharacters(member.getName());
                sWriter.writeEndElement();

                sWriter.writeCharacters("\n\t\t");

                sWriter.writeStartElement("personalNr");
                sWriter.writeCharacters(member.getPersonId());
                sWriter.writeEndElement();


                if (member.getBoatList().size() > 0) {
                    sWriter.writeCharacters("\n\t\t");
                    sWriter.writeStartElement("Boats");


                    for (int j = 0; j < member.getBoatList().size(); j++) {

                        Boat boat = member.getBoatList().get(j);

                        if (j == 0) {
                            sWriter.writeCharacters("\n\t\t\t");
                        }

                        sWriter.writeStartElement("boat");
                        sWriter.writeAttribute("id", Integer.toString(boat.getBoatId()));

                        sWriter.writeCharacters("\n\t\t\t\t");

                        sWriter.writeStartElement("type");
                        sWriter.writeCharacters(boat.getBoatType());
                        sWriter.writeEndElement();

                        sWriter.writeCharacters("\n\t\t\t\t");

                        sWriter.writeStartElement("length");
                        sWriter.writeCharacters(boat.getBoatLength() + "m");
                        sWriter.writeEndElement();

                        sWriter.writeCharacters("\n\t\t\t");

                        sWriter.writeEndElement();

                        if (j != member.getBoatList().size() - 1) {
                            sWriter.writeCharacters("\n\t\t\t");
                        }
                    }

                    sWriter.writeCharacters("\n\t\t");

                    sWriter.writeEndElement();
                }

                sWriter.writeCharacters("\n\t");

                sWriter.writeEndElement();

                if (i != memberList.size() - 1) {
                    sWriter.writeCharacters("\n\t");
                } else {
                    sWriter.writeCharacters("\n");
                }
            }
            sWriter.writeEndElement();
            sWriter.flush();
            sWriter.close();
        } catch (XMLStreamException e) {

        } catch (IOException e) {

        }
        System.out.println("System was successfully updated!");
    }

    /*
    Reads the system information from the file and
    adds it all in the memberList.
     */
    public void readFromSystem () {

        File file = null;
        Builder builder = null;
        Document doc = null;

        try {

            file = new File(filePath);
            builder = new Builder();
            doc = builder.build(file);

            Element root = doc.getRootElement();

            Elements members = root.getChildElements();


            for (int i = 0; i < members.size(); i++) {

                Elements element = members.get(i).getChildElements();
                Member member = new Member();

                member.setMemberId(Integer.parseInt(element.get(i).getAttribute("memberId").getValue()));
                member.setPersonId(element.get(0).getValue());
                member.setName(element.get(1).getValue());

                memberList.add(member);

                if (members.get(i).getChildElements().size() == 3) {

                    Elements boats = element.get(2).getChildElements();

                    for (int j = 0; j < boats.size(); j++) {

                        Boat b = new Boat();

                        b.setBoatId(Integer.parseInt(boats.get(j).getAttribute("boatId").getValue()));
                        b.setBoatType(Integer.parseInt(boats.get(j).getChildElements().get(1).getValue()));
                        b.setBoatLength(boats.get(j).getChildElements().get(2).getValue());

                    }
                }
            }
        } catch (IOException e) {
            System.out.print("Could not read from the system");
        } catch (nu.xom.ParsingException e) {
            System.out.print("Parsing was unsuccessful!");
        } catch (NullPointerException e) {
            System.out.println("Something is wrong in the system file");
        }
    }

    /*
    The list of members
     */
    public List<Member> getMemberList() {
        return memberList;
    }

}