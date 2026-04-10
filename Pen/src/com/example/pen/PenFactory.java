package Pen.src.com.example.pen;

public class PenFactory {
 
    public static Pen createPen(PenType type, String brand) {
        return switch (type) {
            case BALLPOINT -> {
                Ink ink  = new Ink("Blue", 100);
                Nib nib  = new Nib(NibSize.MEDIUM, "tungsten-carbide ball");
                yield new Pen(brand, PenType.BALLPOINT, new BallpointRefill(ink, nib));
            }
            case GEL -> {
                Ink ink  = new Ink("Black", 80);
                Nib nib  = new Nib(NibSize.FINE, "stainless steel");
                yield new Pen(brand, PenType.GEL, new GelRefill(ink, nib));
            }
            case FOUNTAIN -> {
                Ink ink  = new Ink("Indigo", 60);
                Nib nib  = new Nib(NibSize.BROAD, "gold-plated iridium");
                yield new Pen(brand, PenType.FOUNTAIN, new FountainRefill(ink, nib));
            }
        };
    }
}