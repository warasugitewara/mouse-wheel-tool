# GitHub リポジトリアップロード手順

## 1️⃣ GitHub で新しいリポジトリを作成

以下のステップで GitHub にリポジトリを作成してください:

1. GitHub にログイン（https://github.com）
2. **New Repository** をクリック
3. リポジトリ名: `mouse-wheel-tool` または `MouseWheelTool`
4. 説明: `Java GUI tool for automated mouse wheel rotation with F9/F10 hotkeys (Windows/Linux)`
5. **Public** を選択（またはプライベート）
6. **Initialize repository** はチェックなし（既に初期化済み）
7. **Create repository** をクリック

## 2️⃣ ローカルリポジトリをリモートに接続

```bash
cd C:\Users\waras\Workspace\MouseWheelTool

# リモート追加（<USERNAME>/<REPO_NAME> に置き換え）
git remote add origin https://github.com/<USERNAME>/mouse-wheel-tool.git

# または SSH の場合:
# git remote add origin git@github.com:<USERNAME>/mouse-wheel-tool.git
```

## 3️⃣ メインブランチに変更（オプション）

GitHub のデフォルトが `main` の場合:

```bash
git branch -M main
```

## 4️⃣ リモートに push

```bash
git push -u origin master  # または main
```

## 5️⃣ 確認

GitHub のリポジトリページで以下が表示されることを確認:

- ✅ README.md
- ✅ LICENSE
- ✅ CONTRIBUTING.md
- ✅ CHANGELOG.md
- ✅ ソースコード

## 📝 GitHub リポジトリのセットアップ（推奨）

### Topics を追加

Settings > Topics に以下を追加:

```
java
maven
gui
automation
mouse-wheel
hotkey
swing
windows
linux
```

### Description を追加

```
Java GUI tool for automated mouse wheel rotation with F9/F10 hotkeys - Windows/Linux compatible
```

### Social Preview を設定

README.md のスクリーンショットまたはロゴを設定（オプション）

## 🔄 継続的な更新

コード変更後:

```bash
git add .
git commit -m "Description of changes"
git push origin master  # または main
```

## 🏷️ リリースタグを作成（オプション）

```bash
git tag -a v1.1.0 -m "Release v1.1.0: Added spinners, Linux support, increased max rotation to 20"
git push origin v1.1.0
```

## ⚠️ .gitignore 確認

以下が `.target` に含まれているか確認:

```
target/
.idea/
*.iml
*.class
*.jar
build/
```

---

**これでプロジェクトが GitHub で公開されます！🎉**
