/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author hu5910v
 */
public class Hackathon {
ArrayList<String> wordArrayList = new ArrayList<String>();

    void SplitWords(String Sentence) {
        for(String word : Sentence.split(" ")) {
        wordArrayList.add(word);
    }
JOptionPane.showMessageDialog(null, wordArrayList);
    }

    boolean SearchWord(String Search) {
       if (wordArrayList.contains(Search)) {
           return true;
       }
       else {
           return false;
       }
    }
   

}
