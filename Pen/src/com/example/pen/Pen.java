package Pen.src.com.example.pen;

public class Pen {
    private final String brand;
    private final PenType penType;
    private IRefill refill;
 
    public Pen(String brand, PenType penType, IRefill refill) {
        this.brand   = brand;
        this.penType = penType;
        this.refill  = refill;
    }
 
    public void write() {
        if (!refill.isWorkable()) {
            System.out.println("[" + brand + "] Pen not functional. Replace or refill.");
            return;
        }
        refill.write();
    }
 
    // Supports replacing the refill (e.g., swap a cartridge)
    public void changeRefill(IRefill newRefill) {
        System.out.println("[" + brand + "] Replacing refill...");
        this.refill = newRefill;
    }
 
    public boolean isWorkable()  { return refill.isWorkable(); }
    public String getBrand()     { return brand; }
    public PenType getPenType()  { return penType; }
    public IRefill getRefill()   { return refill; }
 
    @Override
    public String toString() {
        return "Pen{brand='" + brand + "', type=" + penType
                + ", ink=" + refill.getInk()
                + ", nib=" + refill.getNib() + "}";
    }
}