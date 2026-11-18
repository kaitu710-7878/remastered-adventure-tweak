package io.github.remasteredadventure.tweak.loot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LootTableClassifierTest {

    @Test
    void entityLootTablePathDetection() {
        assertTrue(LootTableClassifier.isEntityLootTablePath("entities/zombie"));
        assertTrue(LootTableClassifier.isEntityLootTablePath("entities/custom_boss"));
        assertTrue(LootTableClassifier.isEntityLootTablePath("custom/entities/boss"));
        assertFalse(LootTableClassifier.isEntityLootTablePath("chests/simple_dungeon"));
        assertFalse(LootTableClassifier.isEntityLootTablePath(null));
    }

    @Test
    void structureLootTableDetection() {
        assertTrue(LootTableClassifier.isStructureLootTable("minecraft", "chests/simple_dungeon", true));
        assertTrue(LootTableClassifier.isStructureLootTable("betterstrongholds", "chests/grand_library", true));
        assertTrue(LootTableClassifier.isStructureLootTable("somemod", "structures/chests/tower", true));
        assertTrue(LootTableClassifier.isStructureLootTable("somemod", "chest/dungeon_loot", true));
        assertTrue(LootTableClassifier.isStructureLootTable("minecraft", "archaeology/desert_pyramid", true));
        assertTrue(LootTableClassifier.isStructureLootTable("somemod", "ruins/archaeology/rare", true));

        assertFalse(LootTableClassifier.isStructureLootTable("minecraft", "chests/spawn_bonus_chest", true));
        assertTrue(LootTableClassifier.isStructureLootTable("minecraft", "chests/spawn_bonus_chest", false));

        assertFalse(LootTableClassifier.isStructureLootTable("minecraft", "entities/zombie", true));
        assertFalse(LootTableClassifier.isStructureLootTable("minecraft", "blocks/stone", true));
        assertFalse(LootTableClassifier.isStructureLootTable("minecraft", "gameplay/fishing", true));
        assertFalse(LootTableClassifier.isStructureLootTable((String) null, "chests/simple_dungeon", true));
        assertFalse(LootTableClassifier.isStructureLootTable("minecraft", null, true));
    }

    @Test
    void mobDropChanceWithLooting() {
        assertEquals(0.0001D, LootTableClassifier.calculateMobDropChance(0.0001D, 0.0001D, 0), 1e-9);
        assertEquals(0.0002D, LootTableClassifier.calculateMobDropChance(0.0001D, 0.0001D, 1), 1e-9);
        assertEquals(0.0004D, LootTableClassifier.calculateMobDropChance(0.0001D, 0.0001D, 3), 1e-9);
        assertEquals(0.0001D, LootTableClassifier.calculateMobDropChance(0.0001D, 0.0001D, -1), 1e-9);
        assertEquals(1.0D, LootTableClassifier.calculateMobDropChance(0.9D, 0.2D, 3), 1e-9);
    }

    @Test
    void shouldDropBoundaryBehavior() {
        assertTrue(LootTableClassifier.shouldDrop(0.0001D, 0.00009D));
        assertFalse(LootTableClassifier.shouldDrop(0.0001D, 0.0001D));
        assertFalse(LootTableClassifier.shouldDrop(0.0D, 0.0D));
    }
}
