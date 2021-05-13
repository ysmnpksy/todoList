import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class CLIMenu {

    static ArrayList<Todo> todos; //arraylist to store all todos
    static String fileName; //String to store filename for file persistence

    //constructor to initialise arraylist and run ImportGUI
    public CLIMenu(){
        todos = new ArrayList<>();
        SwingUtilities.invokeLater(ImportGUI::new);
    }

    //method to load in to-dos from file
    public static boolean importList(String filename){
        fileName = filename; //saving filename to use in other method

        try{
            Scanner file = new Scanner(new File(filename+".txt")); //finding file
            //adding all the todos to the arraylist
            while (file.hasNextLine()) {
                String nextTodo = file.nextLine();
                String[] details = nextTodo.split(",");

                LocalDateTime date = LocalDateTime.parse(details[1]);
                todos.add(new Todo(details[0], date, Category.valueOf(details[2]), Importance.valueOf(details[3]), Status.valueOf(details[4])));
            }
        }catch(FileNotFoundException e){
            //if file not found return false
            return false;
        }
        return true; //when finished return true
    }

    //method to save to-dos to file when closing
    public static void updateFile(){
        try{
            //using fileWriter to empty file and save arraylist
            FileWriter writer = new FileWriter(fileName+".txt");
            writer.write("");

            for (Todo todo : todos) {
                writer.append("").append(todo.getText()).append(",").append(String.valueOf(todo.getDue())).append(",").append(String.valueOf(todo.getCat())).append(",").append(String.valueOf(todo.getImportance())).append(",").append(String.valueOf(todo.getStatus())).append(System.lineSeparator());
            }
            writer.close();

        }catch(IOException ignored){}
    }

    //method to add to-do
    public static void addTodo(String title, LocalDateTime dueDate, Category category, Importance importance, Status status) {
        //adding to arraylist
        todos.add(new Todo(title, dueDate, category, importance, status));
    }

    //method to update to-to
    public static void updateTodo(Todo todo, String title, LocalDateTime dueDate, Category category, Importance importance, Status status){
        //updating each component
        todo.setText(title);
        todo.setDue(dueDate);
        todo.setCat(category);
        todo.setImportance(importance);
        todo.setStatus(status);
    }

    //method to delete to-do
    public static void deleteTodo(Todo todo){
        //removing to-do
        todos.remove(todo);
    }
}