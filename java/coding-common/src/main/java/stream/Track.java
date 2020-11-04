package stream;

public class Track {
    private String name;
    private int length;

    public Track(String name, int i) {
        this.name = name;
        this.length = i;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
