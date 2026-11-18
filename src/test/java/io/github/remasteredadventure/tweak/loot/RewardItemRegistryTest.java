package io.github.remasteredadventure.tweak.loot;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RewardItemRegistryTest {

    @Test
    void containsExactly21UniqueTargetItems() {
        assertEquals(21, RewardItemRegistry.TARGET_ITEM_ID_STRINGS.size());

        Set<String> uniqueIds = new HashSet<>(RewardItemRegistry.TARGET_ITEM_ID_STRINGS);
        assertEquals(21, uniqueIds.size());

        long endRemCount = uniqueIds.stream().filter(id -> id.startsWith("endrem:")).count();
        long netherRemasteredCount = uniqueIds.stream().filter(id -> id.startsWith("nether_remastered:")).count();

        assertEquals(16, endRemCount);
        assertEquals(5, netherRemasteredCount);
    }
}
