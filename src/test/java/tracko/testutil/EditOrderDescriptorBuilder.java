package tracko.testutil;

import javafx.util.Pair;
import tracko.logic.commands.order.EditOrderCommand.EditOrderDescriptor;
import tracko.model.order.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class to help with building EditOrderDescriptor objects.
 */
public class EditOrderDescriptorBuilder {

    private EditOrderDescriptor descriptor;

    public EditOrderDescriptorBuilder() {
        descriptor = new EditOrderDescriptor();
    }

    public EditOrderDescriptorBuilder(EditOrderDescriptor descriptor) {
        this.descriptor = new EditOrderDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditOrderDescriptor} with fields containing {@code Order}'s details
     */
    public EditOrderDescriptorBuilder(Order order) {
        descriptor = new EditOrderDescriptor();
        descriptor.setName(order.getName());
        descriptor.setPhone(order.getPhone());
        descriptor.setEmail(order.getEmail());
        descriptor.setAddress(order.getAddress());
        descriptor.setItemList(order.getItemList());
    }

    /**
     * Sets the {@code Name} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code List<ItemQuantityPair>} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withItemList() {
        List<ItemQuantityPair> defaultPairList = new ArrayList<>();
        defaultPairList.add(new ItemQuantityPairBuilder().build());
        defaultPairList.add(new ItemQuantityPairBuilder().withItem(TypicalItems.ITEM_2).withQuantity(10).build());
        descriptor.setItemList(defaultPairList);
        return this;
    }

    /**
     * Sets the {@code List<ItemQuantityPair>} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withSecondItemList() {
        List<ItemQuantityPair> defaultPairList = new ArrayList<>();
        defaultPairList.add(new ItemQuantityPairBuilder().withItem(TypicalItems.ITEM_5).withQuantity(25).build());
        defaultPairList.add(new ItemQuantityPairBuilder().withItem(TypicalItems.ITEM_4).withQuantity(20).build());
        descriptor.setItemList(defaultPairList);
        return this;
    }

    public EditOrderDescriptorBuilder withItemList(List<ItemQuantityPair> customList) {
        descriptor.setItemList(customList);
        return this;
    }

    public EditOrderDescriptorBuilder withUnlinkedPair(Pair<String, Integer> rawItemQuantityPair) {
        descriptor.setUnlinkedItemToEdit(rawItemQuantityPair);
        return this;
    }

    public EditOrderDescriptor build() {
        return descriptor;
    }
}
