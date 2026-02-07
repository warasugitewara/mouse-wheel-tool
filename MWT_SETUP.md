# MouseWheelTool ターミナルコマンド設定ガイド

`mwt` コマンドをターミナルから直接実行できるようにするための設定方法です。

## 前提条件

- Java 21 以上がインストールされていること
- JAR ファイルのパス: `$HOME/Workspace/MouseWheelTool/target/MouseWheelTool-1.0.0-jar-with-dependencies.jar`

---

## Windows (PowerShell/CMD)

### オプション 1: `.bin` ディレクトリを使用（推奨）

`.bin` ディレクトリが既に PATH に登録されている場合:

```bash
# mwt.bat が既に配置されている場合
mwt --version
```

**配置済みファイル**: `C:\Users\{username}\.bin\mwt.bat`

### JAR パスをカスタマイズする

JAR ファイルが異なるディレクトリにある場合、`mwt.bat` を編集してください：

```batch
@echo off
# 以下のパスを変更してください
set JAR_PATH=C:\path\to\your\MouseWheelTool-1.0.0-jar-with-dependencies.jar

if not exist "%JAR_PATH%" (
    echo Error: JAR file not found at %JAR_PATH%
    exit /b 1
)

java -jar "%JAR_PATH%" %*
```

**便利な方法: 環境変数を使用**

```powershell
# PowerShell で環境変数を設定（管理者権限）
[Environment]::SetEnvironmentVariable("MWT_JAR", "C:\path\to\your\MouseWheelTool-1.0.0-jar-with-dependencies.jar", "User")

# mwt.bat を以下のように編集
# @echo off
# java -jar "%MWT_JAR%" %*
```

その後、`mwt.bat` を以下のように編集：

```batch
@echo off
java -jar "%MWT_JAR%" %*
```

### オプション 2: PATH に .bin を追加

`.bin` が PATH に登録されていない場合:

```powershell
# PowerShell (管理者権限で実行)
[Environment]::SetEnvironmentVariable("Path", "$env:Path;$env:USERPROFILE\.bin", "User")

# その後、PowerShell を再起動
mwt --version
```

### オプション 3: エイリアスを設定

```powershell
# PowerShell プロフィールに追加 ($PROFILE を編集)
function mwt { java -jar "$env:USERPROFILE\Workspace\MouseWheelTool\target\MouseWheelTool-1.0.0-jar-with-dependencies.jar" @args }
```

### オプション 4: 動的にパスを検索（より柔軟）

JAR ファイルの位置がころころ変わる場合、`mwt.bat` を以下のように編集：

```batch
@echo off
REM JAR ファイルを動的に検索
for /f "delims=" %%A in ('dir /s /b %USERPROFILE%\*MouseWheelTool*jar-with-dependencies.jar 2^>nul ^| findstr /r ".*" ^| head -1') do (
    set "JAR_PATH=%%A"
)

if not defined JAR_PATH (
    echo Error: MouseWheelTool JAR not found
    exit /b 1
)

java -jar "%JAR_PATH%" %*
```

---

## Linux / macOS

### オプション 1: `.bin` ディレクトリを使用（推奨）

`.bin` が PATH に登録されている場合:

```bash
# mwt スクリプトが既に配置されている場合
mwt --version
```

**配置済みファイル**: `~/.bin/mwt`

**必要な設定**（初回のみ）:

```bash
chmod +x ~/.bin/mwt
```

### オプション 2: PATH に .bin を追加

`.bin` が PATH に登録されていない場合:

```bash
# ~/.bashrc または ~/.zshrc に追加
export PATH="$HOME/.bin:$PATH"

# 設定を反映
source ~/.bashrc  # bash の場合
# または
source ~/.zshrc   # zsh の場合
```

### オプション 3: /usr/local/bin にシンボリックリンク作成

```bash
ln -s ~/.bin/mwt /usr/local/bin/mwt
```

### オプション 4: 直接実行

```bash
java -jar ~/Workspace/MouseWheelTool/target/MouseWheelTool-1.0.0-jar-with-dependencies.jar
```

---

## 使用例

```bash
# バージョン表示
mwt --version

# ヘルプ表示
mwt --help

# オプション付き実行
mwt <オプション>
```

---

## トラブルシューティング

### Windows で `mwt` コマンドが認識されない

```powershell
# PATH をリロード
$env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";" + [System.Environment]::GetEnvironmentVariable("Path","User")

# または PowerShell を再起動
```

### Linux で `mwt: command not found`

```bash
# 実行権限を確認
ls -la ~/.bin/mwt

# 実行権限を追加（必要に応じて）
chmod +x ~/.bin/mwt

# PATH に .bin が含まれているか確認
echo $PATH | grep .bin

# PATH に追加されていない場合は ~/.bashrc または ~/.zshrc に追加
echo 'export PATH="$HOME/.bin:$PATH"' >> ~/.bashrc
source ~/.bashrc
```

### JAR ファイルが見つからない

```bash
# JAR ファイルの位置を確認
ls -la ~/Workspace/MouseWheelTool/target/MouseWheelTool-1.0.0-jar-with-dependencies.jar

# または find で検索
find ~ -name "MouseWheelTool*.jar" 2>/dev/null
```

### Java がインストールされていない

```bash
# Java バージョン確認
java -version

# インストールされていない場合はインストール
# Ubuntu/Debian
sudo apt-get install openjdk-21-jre

# macOS (Homebrew を使用)
brew install openjdk@21
```

---

## まとめ

| OS | 推奨方法 | ファイル |
|---|---|---|
| Windows | `.bin` ディレクトリ | `mwt.bat` |
| Linux/macOS | `.bin` ディレクトリ | `mwt` (シェルスクリプト) |

どちらの方法でも `.bin` ディレクトリが PATH に登録されていれば、`mwt` コマンドで直接実行できます。
