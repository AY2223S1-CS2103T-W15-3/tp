---
layout: page
title: User Guide
---

TrackO is a **desktop app built for small business owners to help them manage orders and their inventory, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you prefer and are fast at typing, TrackO can get your order and inventory management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `TrackO.jar` from [here](https://github.com/AY2223S1-CS2103T-W15-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TrackO.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`listo`** and pressing Enter will show a list of existing orders.<br>
   Some example commands you can try:

    * **`listo`** : Lists all orders.

    * **`addi`**`n/Keychain q/200 d/This is a metal keychain sp/15.00 cp/10.00` : Adds an inventory item named `Keychain`, which has quantity `200`, description `This is a metal keychain`, selling price `$15.00` and cost price `$10.00` to the inventory list.

    * **`deleteo`**`3` : Deletes the 3rd order shown in the current list.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `listi`, `listo` and `exit`) will be ignored.<br>
  e.g. if the command specifies `listi 123`, it will be interpreted as `list`.

</div>

### Adding an order: `addo`

Adds an order to the list of tracked orders.

Format: `addo i/ITEM_NAME q/ORDER_QUANTITY cn/CUSTOMER_NAME ca/CUSTOMER_ADDRESS ce/CUSTOMER_EMAIL cc/CUSTOMER_CONTACT`

* Adds an order to be tracked in the application.
* The added orders will track the time that it was created.

Examples:
* `addo i/Fountain Pen q/3 cn/John Doe ca/48 Westwood Terrace ce/johndoe@example.com cc/91234567`
* `addo i/White Socks q/2 cn/Betty White ca/39 Ocean Drive ce/bettywhite@example.com cc/92345678`

### Listing all orders : `listo`

Lists all the orders a store has.

Format: `listo`

### Locating orders by keyword: `findo`

Finds order with item names containing any of the given keywords.

Format: `findo KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `keychain` will match `Keychain`
* The order of the keywords does not matter. e.g. `apple keychain` will match `Keychain Apple`
* Only the name is searched.
* Only full words will be matched e.g. `keychains` will not match `keychain`
* Orders matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `apple keychain` will return `apple painting`, `banana keychain`

Examples:
* `findo keychain` returns `banana keychain` and `keychain`
* `findo apple keychain` returns `apple painting`, `banana keychain`<br>

### Sorting orders by time created: `sorto`

Sorts the displayed list of orders by the time at which they were created.

Format: `sorto new` or `sorto old`

* The keyword `new` and `old` are case-insensitive.
* `sorto new` sorts the order list such that newest orders are at the top
* `sorto old` sorts the order list such that oldest orders are at the top

Examples:
* `listo` followed by `sorto old` sorts all orders such that oldest orders are at the top
* `findo Chair` followed by `sorto new` sorts all orders found using `findo Chair` such that newest orders are at the 
top


### Deleting an order: `deleteo`

Deletes an order from the list of tracked orders.

Format: `deleteo INDEX`

* Deletes the order at the specified INDEX.
* The index refers to the index number shown in the displayed order list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* `listo` followed by `deleteo 2` deletes the 2nd order from the order list.
* `findo Paper` followed by `deleteo 1` deletes the 1st item in the results of the `findo` command.
* `sorto new` followed by `deleteo 1` deletes the most recently created order

### Editing details of an order: `edito`

Edits an existing order in the order list.

Format: `edito INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/ITEM_NAME q/QUANTITY]`

* Edits the order at the specified `INDEX`.
* This feature is case-insensitive.
* The index refers to the index number shown in the displayed order list.
* The index **must be a positive integer** 1, 2, 3, …​
* Every field is optional, but if you were to include `i/ITEM_NAME`, you must also include 
  `q/QUANTITY`. Both fields need to be present to update an order's list of ordered items.
* You can only edit an order's list of ordered items to consist of items that exists in your inventory. <br> 
  e.g. If your inventory does not have `Apples`, then you cannot edit any of your order's list of ordered items to `Apples`.
* Editing an item that does not exist in your order's list of ordered items, but exists in your inventory 
  will add the item to the order's list of ordered items. <br> e.g. Your inventory has `Apples` and `Bananas`. Right now, the third order's list of ordered items only has `Apples`.
  `edito 3 i/Bananas q/3` will add `3` `Bananas` to the third order's list of ordered items.
* Setting `q/0` to any item in the order's list of ordered items will remove the item from the list. <br> e.g. The fourth order's list of ordered items has
`2` `Apples` and `3` `Bananas`. Inputting `edito 4 i/Bananas q/0` will remove the `Bananas` from the fourth order's list of ordered items, leaving only
the `2` `Apples`.

Note:
* The order's created time cannot be edited.
* An order's paid status and delivery status also cannot be edited through this command. To modify those, 
  see [`marko`](#marking-an-order-as-paid-or-delivered-marko) instead.

Examples:
* `edito 2 n/Peter p/98765432 e/peter@email.com a/123 Apartment Unit, #05-11`
  Edits the name, phone, email, and address of the second order in the list to `Peter`,`98765432`, `peter@email.com`, 
  and `123 Apartment Unit, #05-11` respectively.
* When the third order in the list has `Chairs` in quantity `5`, `edito 3 i/chairs q/0` 
  will remove the item from the order list. 
* When the fifth order in the list has `Tables` in quantity `3` in its order list, and you have `Chairs` in your inventory, 
` edito 5 i/chairs q/15` will add `15` `Chairs` to the order list.

### Marking an order as paid or delivered: `marko`

Placeholder content

### Adding an inventory item: `addi`

Adds an item to the list of tracked inventory.

Format: `addi n/ITEM_NAME q/QUANTITY d/DESCRIPTION [t/TAG]…​ sp/SELL_PRICE cp/COST_PRICE`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An inventory item can have any number of tags (including 0)
</div>

Examples:
* `addi n/Keychain q/20 d/Silicone keychain with a metal buckle sp/3.50 cp/1`
* `addi n/Chair q/10 d/This is a wooden dining chair t/Furniture sp/50 cp/20`

### Listing all inventory items: `listi`

Lists all the existing items in the store’s inventory.

Format: `listi`

### Finding an inventory item: `findi`

Finds an inventory item whose name fits any of the given keywords.

Format: `findi KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g. `keychain` will match `Keychain`
- The order of the keywords does not matter. e.g. `pants long` will match `long pants`
- Only the name of the item is searched.
- Only full words will be matched. e.g. `key` will not match `Keychain`
- Items matching at least one keyword will be returned (i.e. OR search).
  e.g. `shirt` will return `dress shirt`, `collared shirt`

Examples:
- `findi oil` returns `Olive Oil` and `Vegetable Oil`
- `findi blue` returns `Blue Shirt`, `Blue Pants`

### Deleting an inventory item : `deletei`

Deletes the specified item from the list of tracked inventory.

Format: `deletei INDEX`

* Deletes the item at the specified `INDEX`.
* The index refers to the index number shown in the displayed inventory list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listi` followed by `deletei 2` deletes the 2nd item in the list of tracked inventory.
* `findi Paper` followed by `deletei 1` deletes the 1st item in the results of the `findi` command.

### Editing an inventory item: `editi`

Edits an existing item in the inventory list.

Format: `edit INDEX [i/ITEM_NAME] [q/QUANTITY] [d/DESCRIPTION] [t/TAG]…​ [sp/SELL_PRICE] [cp/COST_PRICE]`

* Edits the item at the specified `INDEX`.
* The index refers to the index number shown in the displayed inventory list.
* The index **must be a positive integer** 1, 2, 3, …​
* You can remove all the item’s tags by typing `t/` without
  specifying any tags after it.

Examples:
* `editi 1 i/Table q/200 d/Metal Table t/Fragile`
  Edits the item name, quantity, description and tag of the 1st item to be
  `Table`, `200`, `Metal Table` and `Fragile` respectively.
* `editi 3 t/` removes the tags of the item at index 3.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Command summary

| Action                       | Format, Examples                                                                                                                                                                                                          |
|------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add An Order**             | `addo i/ITEM_NAME q/ORDER_QUANTITY cn/CUSTOMER_NAME ca/CUSTOMER_ADDRESS ce/CUSTOMER_EMAIL cc/CUSTOMER_CONTACT` <br> e.g., `addo i/Fountain Pen q/3 cn/John Doe ca/48 Westwood Terrace ce/johndoe@example.com cc/91234567` |
| **List All Orders**          | `listo`                                                                                                                                                                                                                   |
| **Find Order(s)**            | `findo KEYWORD [MORE_KEYWORDS]`                                                                                                                                                                                           |
| **Delete An Order**          | `deleteo INDEX` <br> e.g., `deleteo 2`                                                                                                                                                                                    |
| **Edit An Order**            | `edito INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/ITEM_NAME] [q/QUANTITY]` <br> e.g., `edito 2 n/Peter p/98765432 e/peter@email.com a/123 Apartment Unit, #05-11`                                                  |                                                                                                                                                                              
| **Sort Orders**              | `sorto new` or `sorto old`                                                                                                                                                                                                |
| **Add An Inventory Item**    | `addi n/NAME q/QUANTITY d/DESCRIPTION [t/TAG]…​ sp/SELL_PRICE cp/COST_PRICE` <br> e.g., `addi n/Chair q/20 d/Swedish Wooden chair t/Furniture sp/79.99 cp/50.00`                                                          |
| **Delete An Inventory Item** | `deletei INDEX`<br> e.g., `deletei 3`                                                                                                                                                                                     |                                                                                                                                                        
| **List All Inventory Items** | `listi`                                                                                                                                                                                                                   |
| **Find Inventory Item(s)**   | `findi KEYWORD [MORE_KEYWORDS]` <br/> e.g., `findi blue shirt`                                                                                                                                                            |
| **Edit An Inventory Item**   | `editi INDEX [i/ITEM_NAME] [q/QUANTITY] [d/DESCRIPTION] [t/TAG]…​ [sp/SELL_PRICE] [cp/COST_PRICE]`<br> e.g., `editi 2 i/Table q/200 d/Metal Table t/Fragile`                                                              |
| **Tag An Inventory Item**    | `tagi INDEX [t/TAG]…​` <br> e.g, `tagi 1 t/Perishable t/Premium`                                                                                                                                                          |
| **Exit**                     | `exit`                                                                                                                                                                                                                    |
