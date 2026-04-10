package Pen.src.com.example.pen;

public class Nib {
    private final NibSize nibSize;
    private final String material; // e.g., "steel", "gold", "plastic"

    public Nib(NibSize nibSize, String material) {
        this.nibSize = nibSize;
        this.material = material;
    }

    public NibSize getNibSize() {
        return nibSize;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "Nib{size=" + nibSize + ", material='" + material + "'}";
    }
}