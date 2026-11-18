package io.github.remasteredadventure.tweak.loot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Holds the 21 target item IDs referenced solely by ResourceLocation strings
 * without any compile-time or asset dependency on the target mods.
 */
public final class RewardItemRegistry {

    public static final List<String> TARGET_ITEM_ID_STRINGS = List.of(
            // End Remastered Eyes (16)
            "endrem:black_eye",
            "endrem:cold_eye",
            "endrem:corrupted_eye",
            "endrem:cryptic_eye",
            "endrem:cursed_eye",
            "endrem:evil_eye",
            "endrem:exotic_eye",
            "endrem:guardian_eye",
            "endrem:lost_eye",
            "endrem:magical_eye",
            "endrem:nether_eye",
            "endrem:old_eye",
            "endrem:rogue_eye",
            "endrem:undead_eye",
            "endrem:witch_eye",
            "endrem:wither_eye",
            // Nether Remastered Nether Crystal & Mysterious Shards (5)
            "nether_remastered:seal_crystal",
            "nether_remastered:seal_piece_1",
            "nether_remastered:seal_piece_2",
            "nether_remastered:seal_piece_3",
            "nether_remastered:seal_piece_4"
    );

    private static volatile List<Item> cachedItems;

    private RewardItemRegistry() {
    }

    /**
     * Resolves and caches all registered target items from the Forge item registry.
     */
    public static List<Item> getResolvedItems() {
        List<Item> items = cachedItems;
        if (items != null) {
            return items;
        }
        List<Item> resolved = new ArrayList<>(TARGET_ITEM_ID_STRINGS.size());
        for (String idString : TARGET_ITEM_ID_STRINGS) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(idString));
            if (item != null && item != Items.AIR) {
                resolved.add(item);
            }
        }
        List<Item> immutable = Collections.unmodifiableList(resolved);
        if (!immutable.isEmpty()) {
            cachedItems = immutable;
        }
        return immutable;
    }

    /**
     * Picks one random item uniformly from the resolved target items, or returns null if none are registered.
     */
    public static Item pickRandomItem(RandomSource random) {
        List<Item> items = getResolvedItems();
        if (items.isEmpty()) {
            return null;
        }
        return items.get(random.nextInt(items.size()));
    }
}
