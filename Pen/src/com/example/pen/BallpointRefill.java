package Pen.src.com.example.pen;

public class BallpointRefill implements IRefill {
    private final Ink ink;
    private final Nib nib;

    public BallpointRefill(Ink ink, Nib nib) {
        this.ink = ink;
        this.nib = nib;
    }

    @Override
    public void write() {
        if (!isWorkable()) {
            System.out.println("[BallpointRefill] Cannot write – ink is empty.");
            return;
        }
        ink.consume(1);
        System.out.println("[BallpointRefill] Writing with " + ink.getColor()
                + " ink (nib: " + nib.getNibSize() + "). Ink level: " + ink.getLevel());
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
