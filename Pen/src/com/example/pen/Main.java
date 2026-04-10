package Pen.src.com.example.pen;

public class Main {
 
    public static void main(String[] args) {
 
        System.out.println("=== Ballpoint Pen ===");
        Pen ballpoint = PenFactory.createPen(PenType.BALLPOINT, "BIC");
        System.out.println(ballpoint);
        ballpoint.write();
        ballpoint.write();
 
        System.out.println("\n=== Gel Pen ===");
        Pen gel = PenFactory.createPen(PenType.GEL, "Pilot G2");
        System.out.println(gel);
        gel.write();
 
        System.out.println("\n=== Fountain Pen ===");
        Pen fountain = PenFactory.createPen(PenType.FOUNTAIN, "Montblanc");
        System.out.println(fountain);
        fountain.write();
 
        // Demonstrate refill swap (strategy pattern)
        System.out.println("\n=== Swapping Ballpoint Refill ===");
        Ink newInk = new Ink("Red", 100);
        Nib newNib = new Nib(NibSize.FINE, "stainless steel");
        ballpoint.changeRefill(new BallpointRefill(newInk, newNib));
        ballpoint.write();
 
        // Drain ink to show empty-pen behavior
        System.out.println("\n=== Draining Gel Pen Ink ===");
        Ink tinyInk = new Ink("Green", 4);
        Nib tinyNib = new Nib(NibSize.FINE, "steel");
        gel.changeRefill(new GelRefill(tinyInk, tinyNib));
        gel.write(); // consumes 2 → level 2
        gel.write(); // consumes 2 → level 0
        gel.write(); // empty
    }
}
 