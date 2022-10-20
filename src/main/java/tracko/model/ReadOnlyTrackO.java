package tracko.model;

import javafx.collections.ObservableList;
import tracko.model.item.Item;
import tracko.model.order.Order;

/**
 * Unmodifiable view of the TrackO application.
 */
public interface ReadOnlyTrackO {

    /**
     * Returns an unmodifeable view of the orders list.
     */
    ObservableList<Order> getOrderList();

    /**
     * Returns an unmodifeable view of the inventory list.
     */
    ObservableList<Item> getInventoryList();

}
