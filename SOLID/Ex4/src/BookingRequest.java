import java.util.*;

public class BookingRequest {
    public final int roomType;
    public final List<AddOn> addOns;

    public int getRoomType() {
        return roomType;
    }

    public List<AddOn> getAddOns() {
        return addOns;
    }

    public BookingRequest(int roomType, List<AddOn> addOns) {
        this.roomType = roomType;
        this.addOns = addOns;
    }
}
