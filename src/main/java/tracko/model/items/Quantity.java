package tracko.model.items;

import static tracko.commons.util.AppUtil.checkArgument;
import static tracko.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents the quantity of an item.
 */
public class Quantity {
    public static final String MESSAGE_CONSTRAINTS =
            "Quantity should not be negative.";

    public final int quantity;

    /**
     * Constructs a {@code Quantity}.
     * @param quantity The quantity of an item.
     */
    public Quantity(int quantity) {
        requireAllNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        this.quantity = quantity;
    }

    public static boolean isValidQuantity(int test) {
        return !(test < 0);
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    @Override
    public String toString() {
        return String.valueOf(quantity);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Quantity)) {
            return false;
        }

        Quantity otherQuantity = (Quantity) other;
        return this.equals(otherQuantity.getQuantity());
    }
}
