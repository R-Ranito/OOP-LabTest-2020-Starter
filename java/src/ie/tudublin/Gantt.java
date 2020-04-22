package ie.tudublin;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.data.Table;

public class Gantt extends PApplet {
    private float Graph = 100f;
    private int GraphGap = 40;
    private float GraphSize = width / 3f;
    
    

    private ArrayList<Task> tasks;
    // set the size of the window

    public void settings() {
        size(800, 600);
    }


    //load the csv file
    public void loadTasks() {
        Table list = loadTable("tasks.csv", "header");

        list.rows().forEach(tableRow -> tasks.add(new Task(tableRow)));
    }
    
    public void printTasks() {
        tasks.forEach(System.out::println);
    }


    public void mousePressed()
	{
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}


    public void displayTasks() {

        //draw the graph
        stroke(255);

        for (int i = 1; i <= 30; i++) {
            float position = map(i, 1, 30, Graph, width - GraphSize);

            line(position, GraphSize, position, height - GraphSize);
            fill(255);
            text(i, position - 2, GraphSize / 2);
        }

//display the list in the graph and the color for each task in the list
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            float Y = map(i, 0, tasks.size(), GraphSize * 2,height - GraphSize);

            fill(255);
            text(task.getTask(), GraphSize, Y);

            noStroke();
            fill(map(i, 0, tasks.size(), 0, 255), 255, 255);

            float TaskStart = map(task.getStart(), 1, 30, Graph, width - GraphSize);
            float TaskEnd = map(task.getEnd(), 1, 30, Graph, width - GraphSize);
            rect(TaskStart, Y - (GraphGap / 2), TaskEnd - TaskStart, GraphGap, 5, 5, 5, 5);
        }
    }

    public void setup() {
        tasks = new ArrayList<>();
        loadTasks();
        printTasks();
    }

    public void draw() {

        background(0);
        colorMode(HSB);
        displayTasks();
        
    }
}