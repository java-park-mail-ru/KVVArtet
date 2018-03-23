package project.gamemechanics.services.databaseModels;

import project.gamemechanics.components.properties.SingleValueProperty;
import project.gamemechanics.interfaces.Slot;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static project.gamemechanics.components.properties.PropertyCategories.PC_ITEMS_QUANTITY;
import static project.gamemechanics.components.properties.PropertyCategories.PC_ITEM_ID;

@SuppressWarnings("unused")
public class SlotImpl implements Slot {

    private Map<Integer, SingleValueProperty> itemsToQuantity = new HashMap<>();

    public SlotImpl(){

    };

    public SlotImpl(Integer itemID, Integer itemsQuantity){
       itemsToQuantity.put(PC_ITEM_ID, new SingleValueProperty(itemID));
       itemsToQuantity.put(PC_ITEMS_QUANTITY, new SingleValueProperty(itemsQuantity));
    };

    public SlotImpl(Map<Integer, SingleValueProperty> inputMap){
        itemsToQuantity = inputMap;
    };

    @Override
    public Map<Integer, SingleValueProperty> getAllProperties() {
        return itemsToQuantity;
    }

    @Override
    public @NotNull Boolean hasProperty(@NotNull Integer propertyKind) {
        return itemsToQuantity.keySet().contains(propertyKind);
    }

    @Override
    public @NotNull Set<Integer> getAvailableProperties() {
        return itemsToQuantity.keySet();
    }

    @Override
    public @NotNull Integer getProperty(@NotNull Integer propertyKind, @NotNull Integer propertyIndex) {
        return (hasProperty(propertyKind)) ? itemsToQuantity.get(propertyKind).getProperty(propertyIndex) : null;
    }

    @Override
    public @NotNull Integer getProperty(@NotNull Integer propertyIndex) {
        return null;
    }
}
