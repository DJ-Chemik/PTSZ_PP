package pl.chemik.project2;

public class Task {
    private int id;
    private int p;
    private int r;

    public Task() {
    }

    public Task(int id, int p, int r) {
        this.id = id;
        this.p = p;
        this.r = r;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
