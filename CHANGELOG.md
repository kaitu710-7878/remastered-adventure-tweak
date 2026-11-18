# Changelog / 変更履歴

## 1.20.1-1.0.0-beta.2

### English
- Added server-side-only multiplayer support (`IExtensionPoint.DisplayTest` with `NetworkConstants.IGNORESERVERONLY` and `displayTest="IGNORE_SERVER_VERSION"` in `mods.toml`) so clients without this mod can join servers running it.

### 日本語
- マルチプレイにおいてサーバー側のみに導入した場合でも未導入クライアントが参加できるよう、`IExtensionPoint.DisplayTest`（`NetworkConstants.IGNORESERVERONLY`）および `mods.toml` の `displayTest="IGNORE_SERVER_VERSION"` を設定。

## 1.20.1-1.0.0-beta.1

### English
- Promoted to Beta (`1.20.1-1.0.0-beta.1`) after in-game loot functionality and loot table display verification using Advanced Loot Info.
- Updated `README.md` with loot viewer compatibility notes and testing details.

### 日本語
- ゲーム内でのドロップ動作確認および Advanced Loot Info を用いたルートテーブル表記対応テストの完了に伴い、Beta 版（`1.20.1-1.0.0-beta.1`）へ昇格。
- `README.md` にルートテーブル表示対応および Advanced Loot Info でのテスト実績を追記。

## 1.20.1-1.0.0-alpha.3

### English
- Replaced Global Loot Modifier with `LootTableLoadEvent` direct `LootPool` injection so mob drops and structure loot appear in loot-viewer mods (e.g., JER, EMI Loot).
- Enforced player-kill requirement via vanilla `LootItemKilledByPlayerCondition` (`killed_by_player`) and vanilla Looting scaling via `LootItemRandomChanceWithLootingCondition` (`random_chance_with_looting`).
- Identified hostile mob loot tables directly from `ForgeRegistries.ENTITY_TYPES` (`MobCategory.MONSTER` and `EntityType.ENDER_DRAGON`).

### 日本語
- モブドロップ表示・ルートテーブル確認系MOD（JER、EMI Loot 等）でドロップ情報が表示されるよう、Global Loot Modifier から `LootTableLoadEvent` による `LootPool` 直接注入方式へ変更。
- プレイヤーによるキルのみに限定するためバニラ標準の `LootItemKilledByPlayerCondition`（`killed_by_player`）を適用し、ドロップ増加補正もバニラ標準の `LootItemRandomChanceWithLootingCondition`（`random_chance_with_looting`）に変更。
- `ForgeRegistries.ENTITY_TYPES`（`MobCategory.MONSTER` および `EntityType.ENDER_DRAGON`）から敵対モブのデフォルトルートテーブルを正確に特定するよう改善。

## 1.20.1-1.0.0-alpha.2

### English
- Improved loot table classification to also detect modded entity tables via `DAMAGE_SOURCE` and nested chest/archaeology paths (`/chests/`, `chest/`, `/archaeology/`).
- Prevented caching an empty item list in `RewardItemRegistry` if queried before registry population.

### 日本語
- MOD追加の敵対モブを `DAMAGE_SOURCE` でも検出できるよう改善し、ネストされたチェスト／考古学パス（`/chests/`, `chest/`, `/archaeology/`）も構造物ルートテーブルとして判定するように改善。
- レジストリ登録完了前に参照された場合に空リストをキャッシュしないよう `RewardItemRegistry` にガードを追加。

## 1.20.1-1.0.0-alpha.1

### English
- Initial alpha release for Minecraft 1.20.1 (Forge).
- Added Global Loot Modifier `remastered_adventure_tweak:remastered_loot` for all hostile mob loot tables (0.01% base chance, Looting bonus) and all structure loot tables (0.05% chance).
- Configured dynamic ID lookup for the 21 target items (`endrem` 16 eyes + `nether_remastered` `seal_crystal` and `seal_piece_1`..`4`).
- Added common configuration (`remastered_adventure_tweak-common.toml`) and EN/JA documentation.

### 日本語
- Minecraft 1.20.1 (Forge) 向けの初期 Alpha 版をリリース。
- すべての敵対モブのルートテーブル（基本確率 0.01%、ドロップ増加補正対応）およびすべての構造物のルートテーブル（確率 0.05%）に報酬を追加する Global Loot Modifier `remastered_adventure_tweak:remastered_loot` を実装。
- 対象21種（`endrem` 16種 + `nether_remastered` の `seal_crystal`, `seal_piece_1`〜`4`）をアイテムIDの動的参照のみで登録。
- 共通コンフィグ（`remastered_adventure_tweak-common.toml`）および日英ドキュメントを追加。
