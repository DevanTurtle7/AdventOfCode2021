package Day04;

public class Position {
    private int value;
    private boolean marked;

    public Position(int value) {
        this.value = value;
        this.marked = false;
    }

    public void mark() {
        this.marked = true;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isMarked() {
        return this.marked;
    }

    @Override
    public String toString() {
        if (marked) {
            return "[" + this.value + "]";
        } else {
            return this.value + "";
        }
    }
}
