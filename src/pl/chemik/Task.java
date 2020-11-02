package pl.chemik;

public class Task {
    private int id;
    private int p;
    private int r;
    private int d;
    private int w;

    public Task() {
    }

    public Task(int p, int r, int d, int w) {
        this.p = p;
        this.r = r;
        this.d = d;
        this.w = w;
    }

    public Task(int id, int p, int r, int d, int w) {
        this.id = id;
        this.p = p;
        this.r = r;
        this.d = d;
        this.w = w;
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
