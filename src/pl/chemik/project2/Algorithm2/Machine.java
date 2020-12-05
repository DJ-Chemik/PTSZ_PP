package pl.chemik.project2.Algorithm2;

import pl.chemik.project2.Task;

import java.util.ArrayList;

public class Machine {
    private int number;
    private float speed;
    private float time;
    private float sumScheduleTime;
    private ArrayList<Integer> schedule;

    public Machine(int number, float speed) {
        this.number = number;
        this.speed = speed;
        this.time = 0;
        this.sumScheduleTime = 0;
        this.schedule = new ArrayList<>();
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

    public float getSumScheduleTime() {
        return sumScheduleTime;
    }

    public void addSumScheduleTime(float sumScheduleTime) {
        this.sumScheduleTime += sumScheduleTime;
    }

    public void addTaskToSchedule(int taskId) {
        schedule.add(taskId);
    }

    public ArrayList<Integer> getSchedule() {
        return schedule;
    }
}
