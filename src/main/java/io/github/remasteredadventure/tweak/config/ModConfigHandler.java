package io.github.remasteredadventure.tweak.config;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * Common configuration specification for drop chances and loot table filtering.
 */
public final class ModConfigHandler {

    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue MOB_DROP_CHANCE;
    public static final ForgeConfigSpec.DoubleValue MOB_LOOTING_BONUS_PER_LEVEL;
    public static final ForgeConfigSpec.DoubleValue STRUCTURE_DROP_CHANCE;
    public static final ForgeConfigSpec.BooleanValue EXCLUDE_SPAWN_BONUS_CHEST;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("loot");

        MOB_DROP_CHANCE = builder
                .comment("Base drop chance from any hostile mob loot table (0.0001 = 0.01%).")
                .translation("config.remastered_adventure_tweak.mob_drop_chance")
                .defineInRange("mobDropChance", 0.0001D, 0.0D, 1.0D);

        MOB_LOOTING_BONUS_PER_LEVEL = builder
                .comment("Additional drop chance per Looting enchantment level on hostile mobs (0.0001 = +0.01% per level).")
                .translation("config.remastered_adventure_tweak.mob_looting_bonus_per_level")
                .defineInRange("mobLootingBonusPerLevel", 0.0001D, 0.0D, 1.0D);

        STRUCTURE_DROP_CHANCE = builder
                .comment("Drop chance from any structure chest or archaeology loot table (0.0005 = 0.05%).")
                .translation("config.remastered_adventure_tweak.structure_drop_chance")
                .defineInRange("structureDropChance", 0.0005D, 0.0D, 1.0D);

        EXCLUDE_SPAWN_BONUS_CHEST = builder
                .comment("If true, the starter spawn bonus chest is excluded from structure loot rewards.")
                .translation("config.remastered_adventure_tweak.exclude_spawn_bonus_chest")
                .define("excludeSpawnBonusChest", true);

        builder.pop();

        SPEC = builder.build();
    }

    private ModConfigHandler() {
    }
}
