/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author hu5910v
 */
public class AllNotes {
      

    private int maxID = 0;
    public int getMaxID() {
    maxID++;
    return maxID;
}


    
    private CommonCode cc = new CommonCode();
    private ArrayList<Note> allNotes = new ArrayList<>();
    private ArrayList<Note> allModules = new ArrayList<>();
    private String crse = "";

    AllNotes() {
        readAllNotes();
    }

    private void readAllNotes() {
        ArrayList<String> readNotes = new ArrayList<>();

        readNotes = cc.readTextFile(cc.appDir + "\\Notes.txt");

        if ("File not found".equals(readNotes.get(0))) {
        } else {
            allNotes.clear();
            for (String str : readNotes) {
                String[] tmp = str.split("\t");

                Note n = new Note();
                n.setNoteID(Integer.parseInt(tmp[0]));
                n.setCourse(tmp[1]);
                n.setDayte(tmp[2]);
                n.setNote(tmp[3]);

                allNotes.add(n);
            }
        }
    }
    
        private void readAllModules() {
        ArrayList<String> readModules = new ArrayList<>();

        readModules = cc.readTextFile(cc.appDir + "\\Modules.txt");

        if ("File not found".equals(readModules.get(0))) {
        } else {
            allModules.clear();
            for (String str : readModules) {
                String[] tmp = str.split("\t");

                Note n = new Note();
                n.setNote(tmp[0]);

                allModules.add(n);
            }
        }
    }

public void addNote(int maxID, String course, String note) {
    if (note.equals("")) {
        JOptionPane.showMessageDialog(null, "Error: Please enter a note to add (This cannot be empty)");
        
    }
    else {
    Note myNote = new Note(maxID, course, note);
    allNotes.add(myNote);
    writeAllNotes();
    }
}

public void addModule(String course, String note) {
    if (note.equals("")) {
        JOptionPane.showMessageDialog(null, "Error: Please enter a note to add (This cannot be empty)");
        
    }
    else {
    Note myNote = new Note(maxID, course, note);
    allNotes.add(myNote);
    writeAllNotes();
    }
}

    public ArrayList<Note> getAllNotes() {
        return allNotes;
    }

    private void writeAllNotes() {
        String path = cc.appDir + "\\Notes.txt";
        ArrayList<String> writeNote = new ArrayList<>();
        for (Note n : allNotes) {
            String tmp = n.getNoteID() + "\t";
            tmp += n.getCourse() + "\t";
            tmp += n.getDayte() + "\t";
            tmp += n.getNote();
            writeNote.add(tmp);
        }
    
try {
    cc.writeTextFile(path, writeNote);
    } catch (IOException ex) {
        System.out.println("Problem! " + path);
        }
    }

    private void writeAllModules() {
        String path = cc.appDir + "\\Modules.txt";
        ArrayList<String> writeNote = new ArrayList<>();
        for (Note n : allModules) {
            String tmp = n.getNote();
            writeNote.add(tmp);
        }
    
try {
    cc.writeTextFile(path, writeNote);
    } catch (IOException ex) {
        System.out.println("Problem! " + path);
        }
    }

    public String searchAllNotesByKeyword(String noteList, String s) {
    for (int i = 0; i < allNotes.size(); i++) {
        if (allNotes.get(i).getNote().contains(s)) {
            noteList += allNotes.get(i).getNote() + "\n";
        }
       
   }
    return noteList;
    }

     int wordOccurrence(String s, int count, int i) {
        if(i >= allNotes.size()) {
            return count;
        }
        count += allNotes.get(i).getNote().split(s).length -1;
        return wordOccurrence(s, count, i+1); 
    }

    
    ArrayList<String> dateMostNotesWereWritten(String allDates, int i) {
        if(i >= allNotes.size()) {
            String[] tmp = allDates.split(" ");
            Set<String> datesSet = new HashSet<>(Arrays.asList(tmp));
            int highestCount = 0;
            String highestDate = tmp[0];
            for(String date: datesSet) {
                int count = 0;
                for(String tmpDate: tmp) {
                    if(tmpDate.equals(date)) {
                        count++;
                    }
                }
                if (count == highestCount) {
                    highestDate += "," + date;
                }
                else if(count > highestCount) {
                    highestCount = count;
                    highestDate = date;
                }
            }
            ArrayList<String> output = new ArrayList<>();
            output.add(highestDate);
            output.add(Integer.toString(highestCount));
            return output;
        }
        allDates += allNotes.get(i).getDayte().split(" ")[0] + " ";
        return dateMostNotesWereWritten(allDates, i+1);
    }
    
       
    
}
