package Pen.src.com.example.pen;

public class Ink {
    private final String color;
    private int level; // 0–100
 
    public Ink(String color, int level) {
        this.color = color;
        this.level = level;
    }
 
    public String getColor() { return color; }
    public int getLevel()    { return level; }
 
    public boolean isEmpty() { return level <= 0; }
 
    public void consume(int amount) {
        level = Math.max(0, level - amount);
    }
 
    @Override
    public String toString() {
        return "Ink{color='" + color + "', level=" + level + "}";
    }
}