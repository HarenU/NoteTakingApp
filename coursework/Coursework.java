/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.WEST;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author hu5910v
 */
public class Coursework extends JFrame implements ActionListener, KeyListener {

    JTextField searchTextField = new JTextField();
    ArrayList<String> course = new ArrayList<>();
    JComboBox courseList = new JComboBox();
    String crse = "";
    AllNotes allNotes = new AllNotes();
    private CommonCode cc = new CommonCode();
    private Hackathon Hack =  new Hackathon();
    private Hackathon2 Hack2 =  new Hackathon2();
    private AllNotes Note = new AllNotes();
    JPanel pnl = new JPanel(new BorderLayout());
    JTextArea txtNewNote = new JTextArea();
    JTextArea txtDisplayNotes = new JTextArea();
    ArrayList<String> note = new ArrayList<>();
    JPanel est = new JPanel();

    

    private String getDateAndTime() {
        String UK_DATE_FORMAT_NOW = "dd-MM-yyyy HH:mm:ss";
        String ukDateAndTime;
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat uksdf = new SimpleDateFormat(UK_DATE_FORMAT_NOW);
        ukDateAndTime = uksdf.format(cal.getTime());

        return ukDateAndTime;
    }

    protected JMenuItem makeMenuItem(
            String txt,
            String actionCommand,
            String toolTipText,
            Font fnt) {
        JMenuItem mnuItem = new JMenuItem();
        mnuItem.setText(txt);
        mnuItem.setActionCommand(actionCommand);
        mnuItem.setToolTipText(toolTipText);
        mnuItem.setFont(fnt);
        mnuItem.addActionListener(this);
        return mnuItem;
    }

    private void view() {
        Font fnt = new Font("Comic Sans", Font.PLAIN, 24);
        JPanel pnl = new JPanel(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu note = new JMenu();

//        note = new JMenu("Note");
//        note.setToolTipText("Note tasks");
//        note.setFont(fnt);
//
//        note.add(makeMenuItem("New", "NewNote", "Create a new note.", fnt));
//        note.addSeparator();
//        note.add(makeMenuItem("Object Orientated Programming", "NewNote", "Create a new note.", fnt));
//        note.addSeparator();
//        note.add(makeMenuItem("Smart Systems", "NewNote", "Create a new note.", fnt));
//        note.addSeparator();
//        note.add(makeMenuItem("Close", "Close", "Clear the current note.", fnt));
//
//        menuBar.add(note);
//        menuBar.add(makeMenuItem("Exit", "Exit", "Close this program", fnt));
 

        for (String crse : course) {

            courseList.addItem(crse);
        }
        courseList.setFont(fnt);
        courseList.setMaximumSize(courseList.getPreferredSize());
        courseList.addActionListener(this);
        courseList.setActionCommand("Course");
        menuBar.add(courseList);

        this.setJMenuBar(menuBar);
        
        JToolBar toolBar = new JToolBar();
        // Setting up the ButtonBar
        JButton button = null;
                button = makeButton("Date", "Date check",
                "Check highest thing",
                "Close");
        toolBar.add(button);
        toolBar.addSeparator();
        button = makeButton("exit", "Exit",
                "Exit from this program.",
                "Exit");
        toolBar.add(button);
        
        button = makeButton("Create", "AddCourse", 
                "Add an new Course.",
                "New");
        toolBar.add(button);
        
        button = makeButton("Notes", "AddModule", 
                "Add an new Module.",
                "New");
        toolBar.add(button);

        
        setVisible(true);
        
        toolBar.addSeparator();
        // This forces anything after it to the right.
        toolBar.add(Box.createHorizontalGlue());
        searchTextField.setMaximumSize(new Dimension(6900, 30));
        searchTextField.setFont(fnt);
        toolBar.add(searchTextField);
        toolBar.addSeparator();
        button = makeButton("search", "SearchKeyword",
        "Search for this text.",
        "Search");
        toolBar.add(button);

        add(toolBar, BorderLayout.NORTH);
        
        
        est.setPreferredSize(new Dimension(500,0));
        est.setBorder(BorderFactory.createLineBorder(Color.black));
        est.setLayout(new BoxLayout(est, BoxLayout.Y_AXIS));
        add(est, BorderLayout.EAST);

        
        JPanel pnlWest = new JPanel();
        JPanel pnlTest = new JPanel();
        pnlTest.setLayout(new BoxLayout(pnlTest, BoxLayout.X_AXIS));
        pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
        pnlWest.setBorder(BorderFactory.createLineBorder(Color.black));
        txtNewNote.setPreferredSize(new Dimension(100, 300));
        pnlTest.add(Box.createHorizontalGlue());
        button = makeButton("Create", "NewNote",
        "Create a new note.",
        "New");
        pnlTest.add(button);
        
        button = makeButton("Delete", "Close",
                "Close this note.",
                "Close");
        pnlTest.add(button);    
        
        pnlWest.add(pnlTest);
        
        txtNewNote.setFont(fnt);
        pnlWest.add(txtNewNote);

        
       

        JPanel cen = new JPanel();
        cen.setLayout(new BoxLayout(cen, BoxLayout.Y_AXIS));
        cen.setBorder(BorderFactory.createLineBorder(Color.black));
        txtDisplayNotes.setFont(fnt);
        JScrollPane TheySeeMeScrolling = new JScrollPane(txtDisplayNotes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        TheySeeMeScrolling.setPreferredSize(TheySeeMeScrolling.getPreferredSize());
        cen.add(TheySeeMeScrolling);
        cen.add(pnlWest);
        add(cen, BorderLayout.CENTER);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Coursework - Haren Upadhayay");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        

        setVisible(true);  // Needed to ensure that the items can be seen.
    }

    private void model() {
// crse = course.get(0);
//
//        crse = course.get(0);
        ArrayList<String> course = cc.readTextFile(cc.appDir + "\\Courses.txt");
        for (String crse : course) {
            courseList.addItem(crse);
        }
    }

     private void addNote(String text) {
        allNotes.addNote(allNotes.getMaxID(), crse, text);
        addAllNotes();
    }
    
    
    private void controller() {
        addAllNotes();
    }

    JButton btn = new JButton("Click here");

    public static void main(String[] args) {
        // This is required for the coursework.
        JOptionPane.showMessageDialog(null, "Haren Upadhayay - hu5910v");
        Coursework prg;
        prg = new Coursework();
    }
    // Using MVC

    public Coursework() {
        model();
        view();
        controller();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("NewNote".equals(ae.getActionCommand())) {
//            note.add(txtNewNote.getText());
            Note nt = new Note();
            nt.setNoteID(allNotes.getMaxID());
            nt.setDayte(getDateAndTime());
            nt.setCourse(crse);
            nt.setNote(txtNewNote.getText());
            allNotes.addNote(nt.getNoteID(), nt.getCourse(), nt.getNote());
            txtNewNote.setText("");
            addAllNotes();
        }
        if ("Close".equals(ae.getActionCommand())) {
            txtNewNote.setText("");
        }
        if ("Split".equals(ae.getActionCommand())) {
            String Sentence = JOptionPane.showInputDialog("Enter Sentence: ");
            Hack.SplitWords(Sentence);
        }
        if ("SrchWord".equals(ae.getActionCommand())) {
            String Search = JOptionPane.showInputDialog("Enter word to search for: ");
            boolean found  = Hack.SearchWord(Search);
            if (found) { 
                JOptionPane.showMessageDialog(null, "True");
            }
            else {
                JOptionPane.showMessageDialog(null, "False");
            }
                    }     
            
        if ("SearchKeyword".equals(ae.getActionCommand())) {
            JOptionPane.showMessageDialog(this, "Found " + allNotes.wordOccurrence(searchTextField.getText(),0,0) + " occurrence(s) of " + searchTextField.getText() + "\n" + allNotes.searchAllNotesByKeyword("",searchTextField.getText()));
        }
        if ("OccurWord".equals(ae.getActionCommand())) {
            String s = JOptionPane.showInputDialog("Enter Word to count Occurance of: ");
            JOptionPane.showMessageDialog(this, "Found " + allNotes.wordOccurrence(s,0 ,0) + " occurrence(s) of " + s + "\n" + allNotes.searchAllNotesByKeyword("",s));
        }
        if ("Date check".equals(ae.getActionCommand())) {
            JOptionPane.showMessageDialog(this, allNotes.dateMostNotesWereWritten("", 0));
        }
        
        if ("Exit".equals(ae.getActionCommand())) {
            System.exit(0);
        }
        if ("AddCourse".equals(ae.getActionCommand())) {
            String newCourseName = JOptionPane.showInputDialog("Enter course name: ");
            ArrayList<String> oldCourses = cc.readTextFile(cc.appDir + "\\Courses.txt");
            oldCourses.add(newCourseName);
            courseList.addItem(newCourseName);
            try {
                cc.writeTextFile(cc.appDir + "\\Courses.txt", oldCourses);
            } catch (IOException ex) {
                Logger.getLogger(Coursework.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if ("AddModule".equals(ae.getActionCommand())) {
            String Title = JOptionPane.showInputDialog("Add title");
            String Text = JOptionPane.showInputDialog("Add Module Description");
            String TitleWithCourse = Title +" - " + courseList.getSelectedItem().toString();
            addTitle(TitleWithCourse);
            addTitle(Text);

            
        }

        
        if ("Course".equals(ae.getActionCommand())) {
            crse = courseList.getSelectedItem().toString();
            System.out.println(crse);
            /**if (crse.contentEquals("COMP1752")) {
            //    crse = course.get(0);

            }
            if (crse.contentEquals("COMP1753")) {
                crse = course.get(1);

            }
            if (crse.contentEquals("Delete Course")) {
                System.out.println("Delete");

            }
            **/

        }

    }


    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    {
        addAllNotes();
    }

    protected JButton makeButton(
            String imageName,
            String actionCommand,
            String toolTipText,
            String altText) {
        //Create and initialize the button.
        JButton button = new JButton();
        button.setToolTipText(toolTipText);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);

        //Look for the image.
        String imgLocation = System.getProperty("user.dir")
                + "\\icons\\"
                + imageName
                + ".png";

        File fyle = new File(imgLocation);
        if (fyle.exists() && !fyle.isDirectory()) {
            // image found
            Icon img;
            img = new ImageIcon(imgLocation);
            button.setIcon(img);
        } else {
            // image NOT found
            button.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }

        return button;
    }

    private void addAllNotes() {
        String txtNotes = "";

        for (Note n : allNotes.getAllNotes()) {
            txtNotes += n.getNote() + "\n";
        }

        txtDisplayNotes.setText(txtNotes);
    }

    public void addTitle(String title) {
            JLabel jlabel = new JLabel();
            jlabel.setText(title);
            jlabel.setFont(new Font("Verdana",1,20));
            est.add(jlabel);
            setVisible(true);
    }
    public void addText(String text) {
            JTextArea moduledesc = new JTextArea();
            moduledesc.setText(text);
            moduledesc.setFont(new Font("ComicSans",0,5));
            moduledesc.setLineWrap(true);
            moduledesc.setWrapStyleWord(true);
            JScrollPane module =  new JScrollPane(moduledesc, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            module.setPreferredSize(module.getPreferredSize());
            est.add(module);
            setVisible(true);
    }
    

}
