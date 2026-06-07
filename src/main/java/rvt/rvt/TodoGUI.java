package rvt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TodoGUI extends JFrame {
    private TodoDB database;
    private DefaultListModel<TodoItem> listModel;
    private JList<TodoItem> taskList;
    private JTextField taskField;

    public TodoGUI() {
        this.database = new TodoDB();

        setTitle("To do list");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        this.listModel = new DefaultListModel<>();
        this.taskList = new JList<>(listModel);
        this.taskField = new JTextField();

        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove selected");

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(taskField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(removeButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(event -> addTask());
        removeButton.addActionListener(event -> removeTask());

        loadTasks();

        setVisible(true);
    }

    private void loadTasks() {
        listModel.clear();

        ArrayList<TodoItem> tasks = database.findAll();

        for (TodoItem task : tasks) {
            listModel.addElement(task);
        }
    }

    private void addTask() {
        String task = taskField.getText().trim();

        if (task.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task cannot be empty.");
            return;
        }

        if (task.length() < 3) {
            JOptionPane.showMessageDialog(this, "Task must be at least 3 characters long.");
            return;
        }

        database.add(task);
        taskField.setText("");
        loadTasks();
    }

    private void removeTask() {
        TodoItem selectedTask = taskList.getSelectedValue();

        if (selectedTask == null) {
            JOptionPane.showMessageDialog(this, "Select a task first.");
            return;
        }

        database.removeById(selectedTask.getId());
        loadTasks();
    }
}