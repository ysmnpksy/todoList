import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImportGUI extends JFrame implements ActionListener {

    //Filename text box
    JTextField enterFilename;

    //Buttons
    JButton openDefault;
    JButton openFilename;

    //JLabel
    JLabel text;

    public ImportGUI(){
        //setting frame
        this.setSize(470,240);
        this.setTitle("Open To-Do List");
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 300);

        //setting layout
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3,10,3,10);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.weighty = 1.0;

        //adding title
        JLabel title = new JLabel("Welcome to your to-do list");
        title.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c.gridwidth = 3;
        c.gridx= 0;
        c.gridy = 0;
        add(title, c);

        //adding instruction
        JLabel instruction = new JLabel("To open your default todo list, click 'Open Default'.");
        instruction.setFont(new Font("Tahoma", Font.PLAIN, 12));
        c.gridwidth = 3;
        c.gridx= 0;
        c.gridy = 1;
        add(instruction, c);

        //adding instruction
        JLabel instruction2 = new JLabel("To open a specific to-do list, enter the name of the list and click 'Open File'.");
        instruction2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        add(instruction2, c);

        //adding label and text field
        JLabel label = new JLabel("File name:", SwingConstants.RIGHT);
        c.gridwidth = 1;
        c.gridx= 0; //x location of the button
        c.gridy = 3; //y location of the button
        add(label, c);

        enterFilename = new JTextField(100);
        c.gridwidth = 2;
        c.gridx= 1; //x location of the button
        c.gridy = 3; //y location of the button
        add(enterFilename, c);

        //adding default button
        openDefault = new JButton("Open Default");
        openDefault.addActionListener(this);
        openDefault.setFont(new Font("Tahoma", Font.PLAIN, 12));
        c.gridwidth = 1;
        c.gridx= 2; //x location of the button
        c.gridy = 5; //y location of the button
        add(openDefault, c);

        //adding file button
        openFilename = new JButton("Open File");
        openFilename.addActionListener(this);
        openFilename.setFont(new Font("Tahoma", Font.BOLD, 12));
        c.gridwidth = 1;
        c.gridx= 2; //x location of the button
        c.gridy = 4; //y location of the button
        add(openFilename, c);

        //adding text label for error messages
        text = new JLabel("   ");
        text.setFont(new Font("Tahoma", Font.PLAIN, 12));
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 6;
        add(text, c);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //checking which button was pressed
        if (e.getActionCommand().equals("Open File")){
            boolean imp = CLIMenu.importList(enterFilename.getText()); //importing file

            if (!imp){ //if false, file wasn't found, displays error and return
                text.setText("File can't be found. Please make sure the file is in this programs folder.");
                return;
            }
        }
        else {
            CLIMenu.importList("default"); //importing from default file
        }

        //running new TodoGUI
        SwingUtilities.invokeLater(TodoGUI::new);
        //closing current GUI
        this.dispose();
    }
}
