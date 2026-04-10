package Pen.src.com.example.pen;

public class GelRefill implements IRefill {
    private final Ink ink;
    private final Nib nib;
 
    public GelRefill(Ink ink, Nib nib) {
        this.ink = ink;
        this.nib = nib;
    }
 
    @Override
    public void write() {
        if (!isWorkable()) {
            System.out.println("[GelRefill] Cannot write – ink is empty.");
            return;
        }
        ink.consume(2); //gel uses more ink per stroke
        System.out.println("[GelRefill] Smooth gel writing with " + ink.getColor()
                + " ink (nib: " + nib.getNibSize() + "). Ink level: " + ink.getLevel());
    }
 
    @Override
    public boolean isWorkable() { return !ink.isEmpty(); }
 
    @Override public Ink getInk() { return ink; }
    @Override public Nib getNib() { return nib; }
}