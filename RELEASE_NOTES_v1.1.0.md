## マウスホイール回転ツール v1.1.0

Java で開発された、Windows/Linux 対応のマウスホイール自動回転ツールです。F9/F10 ホットキーで簡単に操作できます。

### 🎯 主な機能

#### グローバルホットキー
- **F9**: マウスホイール回転開始（持続）
- **F10**: マウスホイール回転停止
- 別画面フォーカス時でも反応（Windows）

#### GUI コントロール
- 🎚️ **スライダー + 数値入力**: 回転速度（10～500ms）と回転量（1～20 ticks）をリアルタイム調整
- ↕️ **方向選択**: 上/下方向を選択可能
- 🖱️ **ボタン操作**: Start/Stop ボタンで手動制御

#### クロスプラットフォーム対応
- ✅ **Windows**: グローバルホットキー（JNA + Windows API）
- ✅ **Linux**: X11 環境対応、xbindkeys 連携ガイド
- ✅ **Java**: 21, 22, 25 すべてに対応

### 📦 v1.1.0 での改善

#### 新機能
- **数値入力フィールド**: スライダーと同期した数値入力で正確な値を設定可能
- **最大値拡張**: 回転量が最大 20 ticks に拡張
- **OS 判定**: 自動的に Windows/Linux を判別して最適な動作モードを選択
- **Linux 対応**: xbindkeys のセットアップガイドを提供

#### 改善点
- GUI レイアウト最適化（スピナーコントロール追加）
- ロギング・デバッグ出力強化
- 起動時にシステム情報（OS、Java バージョン）を表示
- スレッド管理の改善

### 🚀 クイックスタート

#### ビルド
```bash
mvn clean package -DskipTests
```

#### 実行
```bash
# Windows
java -jar target/MouseWheelTool-1.0.0-jar-with-dependencies.jar

# または実行スクリプト使用
run.bat              # Windows
bash run.sh          # Linux
```

#### 使い方
1. アプリケーションを起動
2. **F9** を押す → 回転開始
3. **F10** を押す → 回転停止
4. GUI スライダーやスピナーで設定を調整

### 💻 システム要件

- **Java**: 21 以上（推奨: 21 LTS または 25）
- **OS**: Windows 10/11、Linux（Ubuntu 20.04 以上）
- **Maven**: 3.6+ （ビルド時のみ）

### 📋 依存ライブラリ

- **JNA** (5.14.0): Windows API 呼び出し（グローバルホットキー）
- **SLF4J** (2.0.11): ロギング
- **Swing**: GUI（Java 標準）

### 📖 詳細ドキュメント

- **README.md**: 完全なセットアップガイド
- **CONTRIBUTING.md**: 開発・貢献方法
- **CHANGELOG.md**: 各バージョンの変更履歴

### ⚙️ Windows での使用例

```
1. アプリケーションを起動
2. 画面外でブラウザなどを操作中
3. F9 を押す → ページが自動スクロール開始
4. F10 を押す → スクロール停止
5. GUI で回転速度や量を調整可能
```

### 🐧 Linux での使用例

xbindkeys セットアップ後、F9/F10 で Windows と同様に動作します。

### 🐛 トラブルシューティング

**F9/F10 が反応しない:**
- Windows: 他のアプリケーションがホットキーを使用していないか確認
- Linux: xbindkeys のセットアップを確認

**マウスホイール動作が遅い:**
- GUI の「Rotation Speed」スライダーを右に移動（間隔を短くする）
- 「Rotation Amount」を増やす

### 📝 ライセンス

MIT License - 自由に使用・改変・配布が可能です

### 🔗 リンク

- **GitHub**: https://github.com/warasugitewara/mouse-wheel-tool
- **Issue/Bug Report**: GitHub Issues で報告してください

---

**Happy scrolling! 🎉**

本ツールで作業効率が向上することを願います。
質問や提案がある場合は、GitHub Issues でお知らせください。
