import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoGUI extends JFrame implements ListSelectionListener, ActionListener {

    DefaultListModel<Todo> todos; //Implementation of list model

    //JLabel
    JLabel text;

    //Variable to track selected to-do
    Todo currentTodo;

    //JList to list all to-dos
    JList todoJList;

    //Buttons
    JButton addButton;
    JButton updateButton;
    JButton deleteButton;
    JButton newListButton;

    public TodoGUI(){
        //setting frame
        this.setSize(700,300);
        this.setTitle("To Do List Application");
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(closeOp());
        this.setLocation(300, 300);

        //setting layout
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,10,5,10);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.weighty = 1.0;

        //adding title
        JLabel title = new JLabel("Your To-Do List");
        title.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c.gridwidth = 3;
        c.gridx= 0;
        c.gridy = 0;
        add(title, c);

        //adding add button
        addButton = new JButton("Add To-do");
        addButton.addActionListener(this);
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        c.gridwidth = 1;
        c.gridx= 0;
        c.gridy = 1;
        add(addButton, c);

        //adding update button
        updateButton = new JButton("Update To-do");
        updateButton.addActionListener(this);
        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        c.gridx= 1;
        c.gridy = 1;
        this.add(updateButton, c);

        //adding delete button
        deleteButton = new JButton("Delete To-do");
        deleteButton.addActionListener(this);
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        c.gridx= 2;
        c.gridy = 1;
        this.add(deleteButton, c);

        //adding new list button
        newListButton = new JButton("Open Another List");
        newListButton.addActionListener(this);
        newListButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        c.gridx= 3;
        c.gridy = 1;
        this.add(newListButton, c);

        //setting default list model and jlist
        todos = new DefaultListModel();
        todos.addAll(CLIMenu.todos);
        todoJList = new JList(todos);

        //changing colours of the todos to match their category
        todoJList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Todo) {
                    Todo nextTodo = (Todo) value;
                    setText(nextTodo.toString());

                    //White changed to orange for better UI
                    if (nextTodo.getCat() == Category.Orange) {
                        setForeground(new Color(255, 153, 51));
                    } else if (nextTodo.getCat() == Category.Red){
                        setForeground(new Color(255,51,51));
                    } else if (nextTodo.getCat() == Category.Blue){
                        setForeground(new Color(25, 102, 255));
                    } else if (nextTodo.getCat() == Category.Yellow){
                        setForeground(new Color(254, 221, 0));
                    } else if (nextTodo.getCat() == Category.Green){
                        setForeground(new Color(0, 255, 0));
                    } else {
                        setForeground(new Color(170, 0, 255));
                    }
                }
                return c;
            }
        });

        //adding scroll list to frame
        todoJList.addListSelectionListener(this);
        todoJList.setFont(new Font("Tahoma", Font.PLAIN, 12));
        JScrollPane itemsScroll = new JScrollPane(todoJList);
        itemsScroll.setMinimumSize(new Dimension(600,1000));
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 4; //width is equal to 3 cells within the grid
        c.gridx = 0;
        c.gridy = 2;
        add(itemsScroll, c);

        //adding label
        text = new JLabel("  ");
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 4; //width is equal to 3 cells within the grid
        c.gridx = 0;
        c.gridy = 3;
        add(text, c);

        //setting frame as visible
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //checking which button has been pressed
        if (e.getActionCommand().equals("Add To-do")){ //checking if add pressed
            //when add pressed, running AddGUI and closing current GUI
            SwingUtilities.invokeLater(AddGUI::new);
            this.dispose();
        }
        else if (e.getActionCommand().equals("Update To-do")){ //checking if update pressed
            if (currentTodo==null){ //if no to-do selected to update, setting error message
                text.setText("Please select a todo to update.");
            }
            else {
                //if to-to to update is selected, running UpdateGUI and closing currentGUI
                SwingUtilities.invokeLater(() -> new UpdateGUI(currentTodo));
                this.dispose();
            }
        }
        else if (e.getActionCommand().equals("Delete To-do")){ //checking if delete pressed
            if (currentTodo==null){ //if no to-do selected to delete, setting error message
                text.setText("Please select a todo to delete.");
            }
            else {
                //if to-to to delete is selected, running UpdateGUI and closing currentGUI
                SwingUtilities.invokeLater(() -> new DeleteGUI(currentTodo));
                this.dispose();
            }
        }
        else {
            //running new ImportGUI
            SwingUtilities.invokeLater(ImportGUI::new);
            //closing current GUI
            this.dispose();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //setting selected value to currentTodo variable to use for update and delete
        currentTodo = (Todo) todoJList.getSelectedValue();
        //setting message showing which to-do is selected
        text.setText("'"+currentTodo.getText()+"' is selected.");
    }

    //closing operation
    public int closeOp(){
        //calling updateFile to save all changes made
        CLIMenu.updateFile();
        //returning 3 as it represents exit_on_close
        return 3;
    }
}