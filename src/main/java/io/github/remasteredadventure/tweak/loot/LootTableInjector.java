package io.github.remasteredadventure.tweak.loot;

import io.github.remasteredadventure.tweak.RemasteredAdventureTweak;
import io.github.remasteredadventure.tweak.config.ModConfigHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Injects vanilla LootPools directly into hostile mob and structure loot tables on load,
 * ensuring player-kill enforcement, vanilla Looting scaling, and compatibility with loot-viewer mods.
 */
@Mod.EventBusSubscriber(modid = RemasteredAdventureTweak.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class LootTableInjector {

    private static volatile Set<ResourceLocation> hostileEntityLootTables;

    private LootTableInjector() {
    }

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation tableId = event.getName();
        if (tableId == null) {
            return;
        }

        List<Item> items = RewardItemRegistry.getResolvedItems();
        if (items.isEmpty()) {
            return;
        }

        if (getHostileEntityLootTables().contains(tableId)) {
            float baseChance = ModConfigHandler.MOB_DROP_CHANCE.get().floatValue();
            float lootingBonus = ModConfigHandler.MOB_LOOTING_BONUS_PER_LEVEL.get().floatValue();
            if (baseChance > 0.0F || lootingBonus > 0.0F) {
                event.getTable().addPool(buildMobLootPool(items, baseChance, lootingBonus));
            }
            return;
        }

        if (LootTableClassifier.isStructureLootTable(tableId, ModConfigHandler.EXCLUDE_SPAWN_BONUS_CHEST.get())) {
            float structureChance = ModConfigHandler.STRUCTURE_DROP_CHANCE.get().floatValue();
            if (structureChance > 0.0F) {
                event.getTable().addPool(buildStructureLootPool(items, structureChance));
            }
        }
    }

    private static Set<ResourceLocation> getHostileEntityLootTables() {
        Set<ResourceLocation> cached = hostileEntityLootTables;
        if (cached != null) {
            return cached;
        }
        Set<ResourceLocation> tables = new HashSet<>();
        for (EntityType<?> entityType : ForgeRegistries.ENTITY_TYPES.getValues()) {
            if (isHostileEntityType(entityType)) {
                ResourceLocation lootTable = entityType.getDefaultLootTable();
                if (lootTable != null) {
                    tables.add(lootTable);
                }
            }
        }
        Set<ResourceLocation> immutable = Set.copyOf(tables);
        if (!immutable.isEmpty()) {
            hostileEntityLootTables = immutable;
        }
        return immutable;
    }

    private static boolean isHostileEntityType(EntityType<?> entityType) {
        return entityType.getCategory() == MobCategory.MONSTER || entityType == EntityType.ENDER_DRAGON;
    }

    private static LootPool buildMobLootPool(List<Item> items, float baseChance, float lootingBonus) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(RemasteredAdventureTweak.MOD_ID + "_mob_loot")
                .setRolls(ConstantValue.exactly(1.0F))
                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(baseChance, lootingBonus));

        for (Item item : items) {
            builder.add(LootItem.lootTableItem(item).setWeight(1));
        }
        return builder.build();
    }

    private static LootPool buildStructureLootPool(List<Item> items, float structureChance) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(RemasteredAdventureTweak.MOD_ID + "_structure_loot")
                .setRolls(ConstantValue.exactly(1.0F))
                .when(LootItemRandomChanceCondition.randomChance(structureChance));

        for (Item item : items) {
            builder.add(LootItem.lootTableItem(item).setWeight(1));
        }
        return builder.build();
    }
}
