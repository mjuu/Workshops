/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import boat.View.Console;
import org.jdom2.JDOMException;

import java.io.IOException;

/**
 *
 * @author Benjamin
 */
public class BoatMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, JDOMException{

        Console con = new Console();
        con.startMenu();
    }


}
