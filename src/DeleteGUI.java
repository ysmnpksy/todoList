import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteGUI extends JFrame implements ActionListener {

    //To-do to delete
    Todo selectedTodo;

    //Buttons
    JButton deleteButton;
    JButton cancelButton;

    public DeleteGUI(Todo todo){
        selectedTodo = todo;

        //setting frame
        this.setSize(400,150);
        this.setTitle("Delete To-Do");
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocation(300, 300);

        //setting layout
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20,20,20,20);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.weighty = 1.0;

        //adding message
        JLabel title = new JLabel("Are you sure you want to delete this to-do?");
        title.setFont(new Font("Tahoma", Font.BOLD, 14));
        c.gridwidth = 2;
        c.gridx= 0;
        c.gridy = 0;
        add(title, c);

        //adding cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setPreferredSize(new Dimension(150, 40));
        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        c.gridwidth = 1;
        c.gridx= 0; //x location of the button
        c.gridy = 1; //y location of the button
        add(cancelButton, c);

        //adding delete button
        deleteButton = new JButton("Delete Todo");
        deleteButton.addActionListener(this);
        deleteButton.setPreferredSize(new Dimension(150, 40));
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        c.gridwidth = 1;
        c.gridx= 1; //x location of the button
        c.gridy = 1; //y location of the button
        add(deleteButton, c);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if delete pressed, deleting the to-do
        if (e.getActionCommand().equals("Delete Todo")){
            CLIMenu.deleteTodo(selectedTodo);
        }

        //running TodoGUI
        SwingUtilities.invokeLater(TodoGUI::new);
        //closing this GUI
        this.dispose();
    }
}
