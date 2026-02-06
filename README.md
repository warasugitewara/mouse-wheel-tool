# Mouse Wheel Tool

Java 21/22/25 対応のマウスホイール自動回転ツール。**Windows/Linux** クロスプラットフォーム対応。

![Version](https://img.shields.io/badge/version-1.1.0-blue)
![License](https://img.shields.io/badge/license-MIT-green)
![Java](https://img.shields.io/badge/java-21%2B-orange)

## 🎯 機能

### グローバルホットキー（Windows）
- **F9**: マウスホイール回転開始（持続回転）
- **F10**: マウスホイール回転停止

別画面フォーカス時でも反応します。

### GUI コントロール
- 🎚️ **回転速度調整**: スライダーと数値入力フィールド（10～500ms）
- 🔄 **回転量調整**: スライダーと数値入力フィールド（1～20 ticks）
- ↕️ **回転方向切り替え**: 上/下
- 🖱️ **ボタンクリック操作**: Start/Stop ボタン

### リアルタイム同期
- スライダーと数値フィールドが常に同期
- 設定変更は即座に反映

## 💻 動作環境

| OS | ホットキー | マウス制御 | GUI |
|---|-----------|----------|-----|
| **Windows** | ✅ グローバル (JNA) | ✅ 完全対応 | ✅ Swing |
| **Linux** | ⚠️ xbindkeys推奨 | ✅ X11対応 | ✅ Swing |
| **macOS** | ❌ 未実装 | ❓ 確認必要 | ✅ Swing |

### 必要なもの
- **Java**: 21, 22, 25 以上
- **Maven**: 3.6+ (ビルド時のみ)

## 🚀 クイックスタート

### 1️⃣ ビルド

```bash
git clone <repository-url>
cd MouseWheelTool
mvn clean package -DskipTests
```

### 2️⃣ 実行

**Windows:**
```bash
java -jar target/MouseWheelTool-1.0.0-jar-with-dependencies.jar
```

または バッチファイル:
```bash
run.bat
```

**Linux:**
```bash
java -jar target/MouseWheelTool-1.0.0-jar-with-dependencies.jar
```

または シェルスクリプト:
```bash
bash run.sh
```

## 📖 使い方

### Windows（グローバルホットキー）

1. アプリケーションを起動
2. **F9** を押す → 回転開始
3. **F10** を押す → 回転停止
4. GUI で設定調整

※ アプリケーションの背景にいても F9/F10 は動作します

### Linux（xbindkeys 推奨）

**セットアップ:**

```bash
# xbindkeys をインストール
sudo apt install xbindkeys  # Ubuntu/Debian
# または
sudo yum install xbindkeys  # RedHat/CentOS

# 設定ファイルを生成
xbindkeys --defaults > ~/.xbindkeysrc

# エディタで編集
nano ~/.xbindkeysrc
```

**~/.xbindkeysrc に追加:**

```bash
# Mouse Wheel Tool - Start
"java -jar /path/to/MouseWheelTool-1.0.0-jar-with-dependencies.jar --start"
    F9

# Mouse Wheel Tool - Stop  
"java -jar /path/to/MouseWheelTool-1.0.0-jar-with-dependencies.jar --stop"
    F10
```

**起動:**

```bash
xbindkeys -f ~/.xbindkeysrc
```

または、GUI ウィンドウをアクティブにしておけば、キーボード入力リスナーが動作します。

## ⚙️ GUI 設定

### Rotation Speed (回転間隔)
- **スライダー**: 直感的に調整
- **数値フィールド**: 正確な値を入力
- **範囲**: 10～500ms（デフォルト: 50ms）
- **低い値** = 高速回転
- **高い値** = ゆっくり回転

### Rotation Amount (回転量)
- **スライダー**: 1～20 ticks
- **数値フィールド**: 正確な値を入力
- **デフォルト**: 1 tick
- **意味**: 1回の操作で回転するホイール「段」の数

### Direction (回転方向)
- **Down (↓)**: 下方向にスクロール（ページ送り）
- **Up (↑)**: 上方向にスクロール（ページ戻る）

## 🏗️ アーキテクチャ

```
MouseWheelToolApp (エントリーポイント)
  ├── MouseWheelToolGUI (Swing GUI)
  │   ├── Sliders (スライダー)
  │   ├── Spinners (数値入力)
  │   └── Buttons (開始/停止)
  ├── RotationController (制御ロジック)
  │   ├── start/stop (状態管理)
  │   └── rotationLoop (バックグラウンド回転)
  ├── MouseWheelRotator (マウス操作)
  │   └── Robot API (java.awt.Robot)
  ├── GlobalHotKeyListener (ホットキー検出)
  │   ├── Windows: JNA + Windows API
  │   └── Linux: xbindkeys 推奨
  └── OSUtils (OS判定)
```

### スレッド構成

| スレッド | 用途 |
|---------|-----|
| **EDT (Event Dispatch Thread)** | Swing GUI イベント処理 |
| **RotationThread** | マウスホイール定期操作 |
| **HotKeyListenerThread** | グローバルホットキー検出 (Windows) |

## 🔧 カスタマイズ

### デフォルト値変更

`RotationController.java`:
```java
private volatile int rotationSpeed = 50;      // 回転間隔（ミリ秒）
private volatile int rotationAmount = 1;      // 回転量（ticks）
private volatile int rotationDirection = 1;   // 1=下, -1=上
```

### GUI レイアウト変更

`MouseWheelToolGUI.java` のコンストラクタで調整可能。

## 🧪 テスト

### マウスホイール動作確認

```bash
mvn test-compile
java -cp "target/classes:target/test-classes" mousewheeltool.MouseWheelTest
```

**出力例:**
```
=== Mouse Wheel Test ===
Testing mouse wheel rotation...

Test 1: Scrolling DOWN 5 times...
[MouseWheel] Rotating: 1
[MouseWheel] Rotating: 1
[MouseWheel] Rotating: 1
[MouseWheel] Rotating: 1
[MouseWheel] Rotating: 1
Test 2: Scrolling UP 5 times...
[MouseWheel] Rotating: -1
[MouseWheel] Rotating: -1
[MouseWheel] Rotating: -1
[MouseWheel] Rotating: -1
[MouseWheel] Rotating: -1

Test completed!
```

## 🐛 トラブルシューティング

### Windows

#### F9/F10 が反応しない
- ✅ 別のアプリ が F9/F10 をグローバルホットキーとして登録していないか確認
- ✅ 管理者権限で実行を試みてください
- ✅ コンソール出力でエラーメッセージを確認

#### マウスホイール動作が遅い
- スライダーを右に移動（回転間隔を短くする）
- 回転量を増やす

### Linux

#### xbindkeys が F9/F10 を認識しない
```bash
# xbindkeys テスト
xbindkeys -v

# キーの確認
xbindkeys -k
```

#### マウスホイール が動作しない
- X11 環境か Wayland 環境か確認
- Wayland の場合、まず X11 をお試しください

```bash
# X11 確認
echo $DISPLAY
```

#### GUI キーリスナーを使用する場合
- ウィンドウをアクティブ（フォーカス）にして F9/F10 を押してください

### macOS

- グローバルホットキー: ❌ 未実装
- マウスホイール制御: ? 確認中

## 📊 システム要件

- **CPU**: 最小限（バックグラウンドスレッド使用）
- **RAM**: 100MB 以上
- **ディスク**: 50MB（JAR + 依存関係）

## 📦 依存ライブラリ

| ライブラリ | 用途 | バージョン |
|-----------|-----|-----------|
| JNA | Windows API 呼び出し | 5.14.0 |
| SLF4J | ロギング | 2.0.11 |
| Swing | GUI | Java 標準 |

## 🤝 貢献

貢献を歓迎します！以下を参照してください:

- [CONTRIBUTING.md](CONTRIBUTING.md) - 貢献ガイド
- [Issues](../../issues) - バグ報告・機能リクエスト

## 📝 ライセンス

[MIT License](LICENSE) - 詳細はファイルを参照してください

## 📝 変更履歴

### v1.1.0 (Latest)
- ✨ 数値入力フィールド追加（スライダーとの連動）
- 📈 最大回転量を 20 に拡張
- 🐧 Linux 対応（xbindkeys 推奨）
- 🖥️ OS 判定ユーティリティ
- 📊 起動時にシステム情報表示

詳細は [CHANGELOG.md](CHANGELOG.md) を参照。

## 🚀 今後の予定

- [ ] macOS ネイティブサポート
- [ ] GUI テーマカスタマイズ
- [ ] 設定ファイル保存・読み込み
- [ ] ホットキーのカスタマイズ機能
- [ ] ロギングをファイルに保存

## 👨‍💻 開発情報

```bash
# ローカル開発
git clone <repository>
cd MouseWheelTool

# Maven でコンパイル
mvn compile

# テスト
mvn test

# パッケージ
mvn package -DskipTests

# 実行
java -jar target/MouseWheelTool-1.0.0-jar-with-dependencies.jar
```

## 📮 サポート

問題が発生した場合:

1. [Troubleshooting](#-トラブルシューティング) を確認
2. [Issues](../../issues) で同じ問題がないか検索
3. 新しい Issue を作成（OS、Java バージョン、エラーメッセージを含める）

---

**Happy scrolling! 🎉**

