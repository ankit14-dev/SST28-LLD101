public class PricingFactory {
    public static PricingComponent room(int roomType) {
        switch (roomType) {
            case LegacyRoomTypes.SINGLE:
                return new SingleRoomPricing();
            case LegacyRoomTypes.DOUBLE:
                return new DoubleRoomPricing();
            case LegacyRoomTypes.TRIPLE:
                return new TripleRoomPricing();
            default:
                return new DeluxeRoomPricing();
        }

    }
    public static PricingComponent addOn(AddOn addOn) {
        switch (addOn) {
            case MESS:
                return new MessAddOn();
            case LAUNDRY:
                return new LaundryAddOn();
            case GYM:
                return new GymAddOn();
            default:
                throw new IllegalArgumentException("Unsupported add-on: " + addOn);
        }
    }
}
