package io.github.remasteredadventure.tweak.loot;

import net.minecraft.resources.ResourceLocation;

/**
 * Pure helper functions for classifying loot tables and computing drop probabilities.
 */
public final class LootTableClassifier {

    private static final String SPAWN_BONUS_NAMESPACE = "minecraft";
    private static final String SPAWN_BONUS_PATH = "chests/spawn_bonus_chest";

    private LootTableClassifier() {
    }

    /**
     * Returns true when the loot table path belongs to an entity drop table.
     */
    public static boolean isEntityLootTablePath(String path) {
        return path != null && (path.startsWith("entities/") || path.contains("/entities/"));
    }

    /**
     * Returns true when the loot table represents a structure chest or archaeology table.
     */
    public static boolean isStructureLootTable(ResourceLocation tableId, boolean excludeSpawnBonusChest) {
        if (tableId == null) {
            return false;
        }
        return isStructureLootTable(tableId.getNamespace(), tableId.getPath(), excludeSpawnBonusChest);
    }

    /**
     * Pure string overload for classifying structure chest and archaeology loot tables.
     */
    public static boolean isStructureLootTable(String namespace, String path, boolean excludeSpawnBonusChest) {
        if (namespace == null || path == null) {
            return false;
        }
        if (excludeSpawnBonusChest && SPAWN_BONUS_NAMESPACE.equals(namespace) && SPAWN_BONUS_PATH.equals(path)) {
            return false;
        }
        return path.startsWith("chests/")
                || path.contains("/chests/")
                || path.startsWith("chest/")
                || path.contains("/chest/")
                || path.startsWith("archaeology/")
                || path.contains("/archaeology/");
    }

    /**
     * Computes effective hostile mob drop chance including Looting bonus, clamped to [0.0, 1.0].
     */
    public static double calculateMobDropChance(double baseChance, double lootingBonusPerLevel, int lootingLevel) {
        int effectiveLevel = Math.max(0, lootingLevel);
        double chance = baseChance + (effectiveLevel * lootingBonusPerLevel);
        return Math.max(0.0D, Math.min(1.0D, chance));
    }

    /**
     * Returns true if a uniform roll in [0.0, 1.0) succeeds for the given probability.
     */
    public static boolean shouldDrop(double chance, double roll) {
        return chance > 0.0D && roll < chance;
    }
}
