package Pen.src.com.example.pen;

public class FountainRefill implements IRefill {
    private Ink ink;
    private final Nib nib;

    public FountainRefill(Ink ink, Nib nib) {
        this.ink = ink;
        this.nib = nib;
    }

    @Override
    public void write() {
        if (!isWorkable()) {
            System.out.println("[FountainRefill] Cannot write – ink cartridge empty.");
            return;
        }
        ink.consume(3); // fountain pens flow more freely
        System.out.println("[FountainRefill] Elegant fountain writing with " + ink.getColor()
                + " ink (nib: " + nib.getNibSize() + "). Ink level: " + ink.getLevel());
    }

    // Fountain pens uniquely support ink cartridge swapping
    public void refillInk(Ink newInk) {
        this.ink = newInk;
        System.out.println("[FountainRefill] Ink refilled: " + newInk);
    }

    @Override
    public boolean isWorkable() {
        return !ink.isEmpty();
    }

    @Override
    public Ink getInk() {
        return ink;
    }

    @Override
    public Nib getNib() {
        return nib;
    }
}