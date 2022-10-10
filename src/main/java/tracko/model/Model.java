package tracko.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import tracko.commons.core.GuiSettings;
import tracko.model.items.Item;
import tracko.model.order.Order;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Item> PREDICATE_SHOW_ALL_ITEMS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    // TrackO =======================================================================================

    // TODO: add item related methods

    /**
     * Returns the user pref's orders file path.
     */
    Path getOrdersFilePath();

    /**
     * Sets the user pref's orders file path.
     */
    void setOrdersFilePath(Path ordersFilePath);

    /**
     * Replaces application data with the data in {@code trackO}
     */
    void setTrackO(ReadOnlyTrackO trackO);

    /** Returns the TrackO */
    ReadOnlyTrackO getTrackO();

    /**
     * Adds the given order.
     */
    void addOrder(Order order);

    /**
     * Deletes the given order.
     */
    void deleteOrder(Order order);

    /**
     * Adds the given item.
     */
    void addItem(Item item);

    /**
     * Returns an unmodifiable view of the filtered person list.
     */
    ObservableList<Item> getFilteredItemList();

    /**
     * Updates the filter of the filtered item list to filter by the given {@code predicate}.
     *
     * @param predicate The predicate given to filter the items in the item list.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItemList(Predicate<Item> predicate);

    /**
     * Returns the number of items in the filtered item list.
     */
    int getFilteredItemListSize();

    /**
     * Deletes the given item.
     */
    void deleteItem(Item item);

    /**
     * Returns the item list.
     */
    ObservableList<Item> getInventoryList();

    /**
     * Returns the order list.
     */
    ObservableList<Order> getOrderList();

    /**
     * Returns an unmodifiable view of the filtered order list
     */

    ObservableList<Order> getFilteredOrderList();

    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Predicate<Order> predicate);
}
