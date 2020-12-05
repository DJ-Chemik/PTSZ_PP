package pl.chemik.project2.Algorithm2;

import java.util.ArrayList;

public class Machine {
    private int number;
    private float speed;
    private float time;
    private float summaryTime;
    private ArrayList<Integer> tasksIDs;

    public Machine(int number, float speed) {
        this.number = number;
        this.speed = speed;
        reset();
    }

    public void reset() {
        this.time = 0;
        this.summaryTime = 0;
        this.tasksIDs = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public float getSpeed() {
        return speed;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void addTime(float time) {
        this.time += time;
    }

    public float getSummaryTime() {
        return summaryTime;
    }

    public void addSumScheduleTime(float sumScheduleTime) {
        this.summaryTime += sumScheduleTime;
    }

    public void addTaskToSchedule(int taskId) {
        tasksIDs.add(taskId);
    }

    public ArrayList<Integer> getTasksIDs() {
        return tasksIDs;
    }
}
