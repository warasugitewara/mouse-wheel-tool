# Linux セットアップガイド

Linux ユーザー向けの詳細なセットアップ手順です。

## 🚀 クイックスタート（推奨）

### 方法 1: GitHub Release から直接ダウンロード（最も簡単）

```bash
# JAR ファイルをダウンロード
wget https://github.com/warasugitewara/mouse-wheel-tool/releases/download/v1.1.0/MouseWheelTool-1.0.0-jar-with-dependencies.jar

# 実行
java -jar MouseWheelTool-1.0.0-jar-with-dependencies.jar
```

### 方法 2: ソースコードからビルド

#### 1️⃣ 必要なツールをインストール

**Ubuntu/Debian の場合:**
```bash
# Java 21 のインストール
sudo apt update
sudo apt install -y openjdk-21-jdk

# Maven のインストール
sudo apt install -y maven

# git のインストール（オプション）
sudo apt install -y git
```

**CentOS/RHEL の場合:**
```bash
sudo yum install -y java-21-openjdk java-21-openjdk-devel maven git
```

**Fedora の場合:**
```bash
sudo dnf install -y java-21-openjdk java-21-openjdk-devel maven git
```

#### 2️⃣ ソースコードを取得

**git を使用:**
```bash
git clone https://github.com/warasugitewara/mouse-wheel-tool.git
cd mouse-wheel-tool
```

**または、ZIP をダウンロード:**
```bash
# GitHub から ZIP ダウンロード
wget https://github.com/warasugitewara/mouse-wheel-tool/archive/refs/tags/v1.1.0.zip
unzip v1.1.0.zip
cd mouse-wheel-tool-1.1.0
```

#### 3️⃣ ビルド

```bash
# Maven でビルド
mvn clean package -DskipTests
```

**出力例:**
```
[INFO] Building jar: target/MouseWheelTool-1.0.0.jar
[INFO] Building jar: target/MouseWheelTool-1.0.0-jar-with-dependencies.jar
[INFO] BUILD SUCCESS
```

#### 4️⃣ 実行

```bash
java -jar target/MouseWheelTool-1.0.0-jar-with-dependencies.jar
```

---

## ⚙️ Linux 環境での詳細設定

### グローバルホットキー（xbindkeys）

#### インストール

**Ubuntu/Debian:**
```bash
sudo apt install -y xbindkeys xbindkeys-config
```

**CentOS/RHEL:**
```bash
sudo yum install -y xbindkeys xbindkeys-config
```

**Fedora:**
```bash
sudo dnf install -y xbindkeys xbindkeys-config
```

#### セットアップ

```bash
# 設定ファイルを生成
xbindkeys --defaults > ~/.xbindkeysrc

# テキストエディタで編集
nano ~/.xbindkeysrc
```

#### ~/.xbindkeysrc に追加

ファイルの最後に以下を追加：

```bash
# Mouse Wheel Tool - Start
"java -jar /home/YOUR_USERNAME/mouse-wheel-tool/target/MouseWheelTool-1.0.0-jar-with-dependencies.jar --start"
    F9

# Mouse Wheel Tool - Stop
"java -jar /home/YOUR_USERNAME/mouse-wheel-tool/target/MouseWheelTool-1.0.0-jar-with-dependencies.jar --stop"
    F10
```

**`/home/YOUR_USERNAME` をあなたのホームディレクトリに置き換えてください**

```bash
# 確認方法
echo $HOME
```

#### xbindkeys 起動

```bash
# 前回のプロセスを停止
pkill xbindkeys

# 起動
xbindkeys -f ~/.xbindkeysrc

# バックグラウンドで実行
xbindkeys -f ~/.xbindkeysrc &
```

#### 自動起動設定（オプション）

`.bashrc` または `.zshrc` に追加：

```bash
# xbindkeys 自動起動
if ! pgrep -x xbindkeys > /dev/null; then
    xbindkeys -f ~/.xbindkeysrc &
fi
```

---

## 🖥️ 環境確認

### Java バージョン確認

```bash
java -version

# 出力例:
# openjdk version "21.0.x" 2024-xx-xx LTS
# OpenJDK Runtime Environment ...
```

### Maven バージョン確認

```bash
mvn -version

# 出力例:
# Apache Maven 3.8.1
# Maven home: /usr/share/maven
# Java version: 21.0.x, ...
```

### DISPLAY 確認（GUI アプリケーション用）

```bash
echo $DISPLAY

# 出力例:
# :0           # X11
# wayland-0    # Wayland
# (何も出力されない = 設定なし)
```

---

## 🐛 トラブルシューティング

### エラー: "mvn: command not found"

```bash
# Maven をインストール
sudo apt install maven

# または、パスを確認
which mvn
export PATH="/usr/bin:$PATH"
```

### エラー: "java: command not found"

```bash
# Java をインストール
sudo apt install openjdk-21-jdk

# Java のパスを確認
which java
```

### エラー: "target フォルダーが見つからない"

```bash
# ビルドが必要です
mvn clean package -DskipTests

# target フォルダーが生成されることを確認
ls -la target/
```

### マウスホイールが動作しない

```bash
# X11 環境か確認
echo $DISPLAY

# X11 の場合、DISPLAY を明示的に設定
export DISPLAY=:0
java -jar target/MouseWheelTool-1.0.0-jar-with-dependencies.jar
```

### xbindkeys が動作しない

```bash
# xbindkeys を詳細モードで実行
xbindkeys -v -f ~/.xbindkeysrc

# キーバインディングをテスト
xbindkeys -k

# F9 を押して、キーコードが表示されるか確認
```

### GUI が表示されない

```bash
# X11 ディスプレイサーバーが起動しているか確認
ps aux | grep Xvfb
ps aux | grep X

# または、リモートディスプレイ接続の場合
export DISPLAY=localhost:10.0
```

---

## 📝 よくある質問（FAQ）

### Q: Wayland で使用できますか？

**A:** Wayland は X11 とは異なるディスプレイプロトコルです。Java Robot API の互換性が限定される可能性があります。

**対策:**
1. X11 セッションに切り替える
2. または X11 互換性レイヤーを使用

```bash
# X11 セッションで起動
startx

# または、ログイン時に "X11" セッションを選択
```

### Q: リモートマシンから実行できますか？

**A:** SSH X11 フォワーディングを使用すれば可能です。

```bash
# X11 フォワーディング有効で接続
ssh -X user@remote-host

# リモート上で実行
java -jar MouseWheelTool-1.0.0-jar-with-dependencies.jar
```

### Q: Docker コンテナ内で実行できますか？

**A:** X11 フォワーディングが必要です。詳細は Docker ドキュメント参照。

### Q: 管理者権限（sudo）は必要ですか？

**A:** 一般的には不要です。ただし、一部のシステムではグローバルホットキー機能が制限される場合があります。

```bash
# 管理者権限で試す
sudo java -jar MouseWheelTool-1.0.0-jar-with-dependencies.jar
```

---

## 📖 Linux ディストリビューション別ガイド

### Ubuntu 22.04 LTS

```bash
# 基本セットアップ
sudo apt update
sudo apt install -y openjdk-21-jdk maven git

# クローンとビルド
git clone https://github.com/warasugitewara/mouse-wheel-tool.git
cd mouse-wheel-tool
mvn clean package -DskipTests

# 実行
java -jar target/MouseWheelTool-1.0.0-jar-with-dependencies.jar

# xbindkeys セットアップ（オプション）
sudo apt install -y xbindkeys
xbindkeys --defaults > ~/.xbindkeysrc
# nano ~/.xbindkeysrc # で上記の設定を追加
xbindkeys -f ~/.xbindkeysrc &
```

### Fedora 39

```bash
sudo dnf install -y java-21-openjdk java-21-openjdk-devel maven git xbindkeys

git clone https://github.com/warasugitewara/mouse-wheel-tool.git
cd mouse-wheel-tool
mvn clean package -DskipTests

java -jar target/MouseWheelTool-1.0.0-jar-with-dependencies.jar
```

### Arch Linux

```bash
sudo pacman -S jdk21-openjdk maven git xbindkeys

git clone https://github.com/warasugitewara/mouse-wheel-tool.git
cd mouse-wheel-tool
mvn clean package -DskipTests

java -jar target/MouseWheelTool-1.0.0-jar-with-dependencies.jar
```

---

## ✅ 動作確認チェックリスト

- [ ] Java 21+ がインストールされている
- [ ] Maven がインストールされている
- [ ] Git がインストールされている（ソースコード取得時）
- [ ] ビルド成功（`mvn clean package -DskipTests` で BUILD SUCCESS）
- [ ] target フォルダーが存在する
- [ ] `target/MouseWheelTool-1.0.0-jar-with-dependencies.jar` が存在する
- [ ] JAR ファイルが実行可能（`java -jar ...` で GUI が表示される）
- [ ] xbindkeys がセットアップされている（グローバルホットキー使用時）

---

## 📞 サポート

問題が発生した場合：

1. このガイドのトラブルシューティングセクションを確認
2. ディストリビューション別ガイドを確認
3. [GitHub Issues](https://github.com/warasugitewara/mouse-wheel-tool/issues) で報告

---

**Happy scrolling on Linux! 🐧**
