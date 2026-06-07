package rvt;

public class TodoItem {
    private int id;
    private String task;

    public TodoItem(int id, String task) {
        this.id = id;
        this.task = task;
    }

    public int getId() {
        return this.id;
    }

    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return this.id + ": " + this.task;
    }
}