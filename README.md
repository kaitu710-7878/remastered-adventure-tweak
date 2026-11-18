# Remastered Adventure Tweak

- [English](#english)
- [日本語](#日本語)

## English

### Overview

**Remastered Adventure Tweak** is a Minecraft 1.20.1 (Forge) mod that adds the 16 Eyes from **End Remastered** and the Nether Crystal and 4 Mysterious Shards from **Nether Remastered** (21 items total) to all hostile mob loot tables and all structure loot tables.

### Features

- **All Hostile Mobs (0.01% base chance, player-kill only, Looting supported):**
  - Hostile mob loot tables (`MobCategory.MONSTER` and `EntityType.ENDER_DRAGON`) receive a vanilla `LootPool` with `killed_by_player` and `random_chance_with_looting` (0.01% base chance, `+0.01%` per Looting level by default).
- **All Structures (0.05% chance):**
  - Structure chests and archaeology loot tables (`chests/*`, `archaeology/*`, excluding `minecraft:chests/spawn_bonus_chest` by default) receive a vanilla `LootPool` with `random_chance` (0.05% chance by default).
- **Loot Viewer Mod Compatibility:**
  - Because `LootPool` entries and conditions are injected directly on `LootTableLoadEvent`, drops and probabilities are visible in loot-viewer mods.
  - Loot table display compatibility was tested using **Advanced Loot Info** (ALI). Because the default drop rates are very low, ALI displays the chance as `0%` by default; however, raising the rates in the config confirms that the displayed chance properly increases above `0%`.
- **ID-Only Dependency:**
  - Declares mod dependencies in `mods.toml` and references items solely by their namespaced IDs at runtime.

### Installation

1. Install Minecraft Forge 1.20.1.
2. Install **End Remastered** (`endrem`) and **Nether Remastered** (`nether_remastered`).
3. Put `remastered_adventure_tweak-<version>.jar` into `mods/`.

## 日本語

### 概要

**Remastered Adventure Tweak** は、**End Remastered** の各種アイ（16種）および **Nether Remastered** のネザークリスタル・各種ミステリアスシャード（5種）の計21種を、すべての敵対モブのルートテーブルおよびすべての構造物のルートテーブルに追加する Minecraft 1.20.1 (Forge) 用MODです。

### 特徴

- **すべての敵対モブ（基本確率 0.01%、プレイヤーキル限定、ドロップ増加補正あり）:**
  - すべての敵対モブ（`MobCategory.MONSTER` および `EntityType.ENDER_DRAGON`）のルートテーブルに、バニラ標準の `killed_by_player` および `random_chance_with_looting` 条件を備えた `LootPool` を直接注入します（デフォルト基本確率 0.01%、ドロップ増加1レベルあたり `+0.01%`）。
- **すべての構造物（確率 0.05%）:**
  - すべての構造物のチェスト・考古学ルートテーブル（`chests/*`, `archaeology/*`。デフォルトでスポーンボーナスチェストは除外）に、`random_chance` 条件を備えた `LootPool` を直接注入します（デフォルト確率 0.05%）。
- **ルートテーブル表示系 MOD 対応:**
  - `LootTableLoadEvent` によりバニラ標準の `LootPool` として注入しているため、ルートテーブル確認系 MOD からもドロップ情報と確率を確認できます。
  - ルートテーブル表記対応テストは **Advanced Loot Info** (ALI) を用いて実施しています。デフォルトの出現確率が非常に低いため ALI 上では確率 `0%` と表示されますが、コンフィグで確率を上げることでしっかり `0%` より大きい確率として表示されることを確認しています。
- **独立した依存設計:**
  - `mods.toml` での依存関係宣言とアイテムID文字列による動的参照のみを行い、依存先MODのコードやアセットとは完全に無関係です。

### 導入手順

1. Minecraft Forge 1.20.1 を導入します。
2. **End Remastered** (`endrem`) と **Nether Remastered** (`nether_remastered`) を導入します。
3. `remastered_adventure_tweak-<version>.jar` を `mods/` フォルダへ配置します。
