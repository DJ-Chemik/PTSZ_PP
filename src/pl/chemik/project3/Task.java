package pl.chemik.project3;

import java.util.ArrayList;

public class Task {
    private int id;
    private ArrayList<Integer> p;
    private int d;
    private int w;

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getP() {
        return p;
    }

    public void setP(ArrayList<Integer> p) {
        this.p = p;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
}
