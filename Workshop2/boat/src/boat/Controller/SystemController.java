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
import java.util.UUID;

public class SystemController {

    private List<Member> memberList;
    private final String filePath = "boatClubMembers.xml";
    private int uniqueID = 0;

    public SystemController() {
        memberList = new ArrayList<Member>();
        readFromSystem();
    }

    /*
       The list of members
        */
    public List<Member> getMemberList() {
        return memberList;
    }

    /*
   Adds the member to the memberList, then saves to the system
    */
    public void addMember(String name, String personalNr) {

        Member member = new Member();
        member.setName(name);
        member.setPersonId(personalNr);
        member.setMemberId(generateUniqueID());


        memberList.add(member);
        saveToSystem();
    }

    private String generateUniqueID(){
        uniqueID = memberList.size();
        uniqueID++;
        String uid = Integer.toString(uniqueID);
        return uid;
    }

    /*
        Edits the returned member name to a new given name
     */
    public void editMemberName(String personalNr, String newMemberName) {

        Member member = getMember(personalNr);
        member.setName(newMemberName);

        saveToSystem();
        System.out.println("Member name was edited!");
    }
    /*
       Edits the returned member name to a new given personal Nr
     */
    public void editPersonalNr(String personalNr, String newPersonalNr){

        Member member = getMember(personalNr);
        member.setPersonId(newPersonalNr);

        saveToSystem();
        System.out.println("Member personal number was edited!");
    }

    /*

     */
    public void removeMember(String personalNr) {
        Member member = getMember(personalNr);

        if (personalNr.equals(member.getPersonId())) {
            memberList.remove(member);
            System.out.println("Member was removed!");
            saveToSystem();
        } else {
            System.out.println("Personal nr did not match any existing member");
        }
    }

    /*
    Adds the boat to the given personalNr that the user input
    If the member exists, then the boat is added to that members boatList
    and saves the systemfile
     */
    public void addBoat(int boatType, String length, String personalNr) {

        Member member = getMember(personalNr);
        Boat boat = new Boat();

        boat.setBoatType(boatType);
        boat.setBoatLength(length);
        member.addBoat(boat);
        System.out.println("Boat was added!");

        saveToSystem();
    }

    /*
    Loops throught memberList and the member boatList
    to find the specific member, if the boatID matches
    some boat in the boatList, that one is edited.
     */
    public void editBoat(String personalNr, int boatId, int boatType, String length) {

        Member member = getMember(personalNr);

        if (personalNr.equals(member.getPersonId())) {

            for (Boat boat : member.getBoatList()) {

                if (boatId == boat.getBoatID()) {

                    boat.setBoatType(boatType);
                    boat.setBoatLength(length);

                    System.out.println("Boat was edited!");
                    saveToSystem();
                }
            }
        }
        else {
            System.out.println("Boat number was not found");
        }
    }

    /*
    Deletes the boat from the member boatlist if personalId
    and the boatId is correct and found!
     */
    public void removeBoat(String personalNr, int boatId) {

        Member member = getMember(personalNr);

        for (Boat boat : member.getBoatList()) {

            if (boatId == boat.getBoatID()) {

                member.remove(boat);

                System.out.println("Boat was deleted!");
                saveToSystem();
            }
        }

    }

    /*
    Searches and finds the member by its personalNr
    and returns the member
     */
    public Member getMember(String personalNr) {

        for (Member m : memberList) {

            if (personalNr.equals(m.getPersonId())) {
                Member member;
                return member = m;

            } else {
//                System.out.println("Could not find member with that personal nr");
            }
        }
        return null;
    }

    /*
    Saves the system information to .xml format file
    and store every data.
     */
    private void saveToSystem() {
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

            for (Member member : memberList){
                sWriter.writeStartElement("Member");
                sWriter.writeAttribute("memberId", member.getMemberId());
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
                        sWriter.writeAttribute("id", Integer.toString(boat.getBoatID()));
                        sWriter.writeCharacters("\n\t\t\t");
                        sWriter.writeStartElement("type");
                        sWriter.writeCharacters(boat.getBoatType());
                        sWriter.writeEndElement();
                        sWriter.writeCharacters("\n\t\t\t\t");
                        sWriter.writeStartElement("length");
                        sWriter.writeCharacters(boat.getBoatLength());
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

            }
            sWriter.writeEndElement();
            sWriter.flush();
            sWriter.close();
        } catch (XMLStreamException e) {
            System.out.println("Something wrong with the XML output");
        } catch (IOException e) {
            System.out.println("Could not write to the system file");

        }
        System.out.println("System was successfully updated!");
    }

    /*
    Reads the system information from the file and
    adds it all in the memberList.
     */
    private void readFromSystem() {

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

                /*
                Member id
                */
                member.setMemberId(members.get(i).getAttribute("memberId").getValue());
                /*
                Personal Nr
                 */
                member.setName(element.get(0).getValue());
                /*
                Member Name
                 */
                member.setPersonId(element.get(1).getValue());
                memberList.add(member);

                if (members.get(i).getChildElements().size() == 3) {

                    Elements boats = element.get(2).getChildElements();

                    for (int j = 0; j < boats.size(); j++) {

                        Boat b = new Boat();
                        /*
                        Boat Id
                         */
                        b.setBoatId(Integer.parseInt(boats.get(j).getAttribute("id").getValue()));
                        /*
                        Boat Type
                         */
                        b.setBoatTypeString(boats.get(j).getChildElements().get(0).getValue());
                        /*
                        Boat Lenght
                         */
                        String length = boats.get(j).getChildElements().get(1).getValue();
                        int lengthInt = Integer.parseInt(length.substring(0, length.length() - 1)); //- 1));
                        b.setBoatLength(length);
                        
                        member.addBoat(b);

                    }
                }
            }
        } catch (IOException e) {
            System.out.print("File is not created yet!");
        } catch (nu.xom.ParsingException e) {
            System.out.print("Parsing was unsuccessful!");
        }
    }

}
