package Pen.src.com.example.pen;

public interface IRefill {
    void write();
    boolean isWorkable();
    Ink getInk();
    Nib getNib();
}