import java.time.LocalDateTime;

public class Todo {

    private String text;
    private LocalDateTime due;
    private Category cat;
    private Importance importance;
    private Status status;

    public Todo(String text, LocalDateTime due, Category cat, Importance importance, Status status) {
        this.text = text;
        this.due = due;
        this.cat = cat;
        this.importance = importance;
        this.status = status;
    }

    @Override
    public String toString() {
        return text + ", due: " + due +
                ", importance: " + importance +
                ", status: " + status;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDue() {
        return due;
    }
    public void setDue(LocalDateTime due) {
        this.due = due;
    }

    public Category getCat() {
        return cat;
    }
    public void setCat(Category cat) {
        this.cat = cat;
    }

    public Importance getImportance() {
        return importance;
    }
    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}