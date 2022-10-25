package tracko.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import tracko.commons.core.GuiSettings;
import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.commands.item.AddItemCommand;
import tracko.model.Model;
import tracko.model.ReadOnlyTrackO;
import tracko.model.ReadOnlyUserPrefs;
import tracko.model.TrackO;
import tracko.model.item.Item;
import tracko.model.order.Order;
import tracko.testutil.ItemBuilder;

public class AddItemCommandTest {
    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddItemCommand(null));
    }

    @Test
    public void execute_itemAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingItemAdded modelStub = new ModelStubAcceptingItemAdded();
        Item validItem = new ItemBuilder().build();

        AddItemCommand command = new AddItemCommand(validItem);

        CommandResult commandResult = command.execute(modelStub);

        assertEquals(String.format(AddItemCommand.MESSAGE_SUCCESS, validItem), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validItem), modelStub.itemsAdded);
    }

    @Test
    public void execute_repeatedItemRejectedByModel_throwsCommandException() throws Exception {
        ModelStubAcceptingItemAdded modelStub = new ModelStubAcceptingItemAdded();
        Item validItem = new ItemBuilder().build();

        String expectedMessage = AddItemCommand.MESSAGE_ITEM_EXISTS;

        AddItemCommand command = new AddItemCommand(validItem);

        CommandResult commandResult = command.execute(modelStub);

        assertEquals(String.format(AddItemCommand.MESSAGE_SUCCESS, validItem), commandResult.getFeedbackToUser());

        //throws an error when same item is added to the model again
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(modelStub));
    }

    @Test
    public void equals() {
        Item key = new ItemBuilder().withItemName("key").build();
        Item flour = new ItemBuilder().withItemName("flour").build();
        AddItemCommand addKeyCommand = new AddItemCommand(key);
        AddItemCommand addFlourCommand = new AddItemCommand(flour);

        // same object -> returns true
        assertTrue(addKeyCommand.equals(addKeyCommand));

        // same values -> returns true
        AddItemCommand addKeyCommandCopy = new AddItemCommand(key);
        assertTrue(addKeyCommand.equals(addKeyCommandCopy));

        // different types -> returns false
        assertFalse(addKeyCommand.equals(1));

        // null -> returns false
        assertFalse(addKeyCommand.equals(null));

        // different item -> returns false
        assertFalse(addKeyCommand.equals(addFlourCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTrackOFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTrackOFilePath(Path trackOFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOrder(Order orderToEdit, Order editedOrder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markOrder(Order orderToMark, boolean isPaid, boolean isDelivered) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearOrdersList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addItem(Item item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Item getItem(String itemName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Item> getFilteredItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteItem(Item item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasItem(Item item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItem(Item target, Item editedItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearItemsList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredItemList(Predicate<Item> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getFilteredItemListSize() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Item> getInventoryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTrackO(ReadOnlyTrackO newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTrackO getTrackO() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void refreshData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredOrderList(Predicate<Order> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getSortedOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedOrderList(Comparator<Order> comparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingItemAdded extends AddItemCommandTest.ModelStub {
        final ArrayList<Item> itemsAdded = new ArrayList<>();

        @Override
        public void addItem(Item item) {
            requireNonNull(item);
            itemsAdded.add(item);
        }

        @Override
        public boolean hasItem(Item item) {
            requireNonNull(item);
            return itemsAdded.contains(item);
        }

        @Override
        public ReadOnlyTrackO getTrackO() {
            return new TrackO();
        }
    }
}
