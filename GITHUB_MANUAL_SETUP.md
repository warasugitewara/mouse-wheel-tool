# GitHub リポジトリ設定ガイド（手動設定版）

## 📍 リポジトリ URL
https://github.com/warasugitewara/mouse-wheel-tool

---

## 🔧 Settings から設定すべき項目

### 1️⃣ Description（説明）を設定

**パス**: Settings > General > Description

**入力内容**:
```
Java GUI tool for automated mouse wheel rotation with F9/F10 hotkeys - Windows/Linux compatible
```

### 2️⃣ About（概要）を設定

**パス**: Settings > General > About section (右側のサイドバー)

**入力内容**:
```
マウスホイール回転ツール - F9/F10 ホットキーで自動回転
```

### 3️⃣ Topics（トピック）を追加

**パス**: Settings > Options > Topics

**以下をすべて追加**:
- `java`
- `maven`
- `gui`
- `automation`
- `mouse-wheel`
- `hotkey`
- `swing`
- `windows`
- `linux`
- `desktop-app`

### 4️⃣ README（オプション）

リポジトリのトップページに表示される README.md は既に設定済みです。

---

## ✅ CLI で自動設定する方法

GitHub CLI（`gh`）を使用した自動設定:

```bash
# 説明を設定
gh repo edit warasugitewara/mouse-wheel-tool \
  --description "Java GUI tool for automated mouse wheel rotation with F9/F10 hotkeys - Windows/Linux compatible"

# Topics を追加
gh repo edit warasugitewara/mouse-wheel-tool \
  --add-topic java \
  --add-topic maven \
  --add-topic gui \
  --add-topic automation \
  --add-topic mouse-wheel \
  --add-topic hotkey \
  --add-topic swing \
  --add-topic windows \
  --add-topic linux \
  --add-topic desktop-app
```

---

## 🎯 設定後のリポジトリ表示

設定完了後、以下のように表示されます:

```
📌 マウスホイール回転ツール
Java GUI tool for automated mouse wheel rotation with...

⭐ Stars | 🔄 Forks | 👁️ Watchers

📚 Topics: java • maven • gui • automation • mouse-wheel • ...

📝 Files
  README.md
  LICENSE
  CHANGELOG.md
  ...

🔗 Releases
  ✓ v1.1.0 - マウスホイール回転ツール v1.1.0
```

---

## 📊 リポジトリ構成（確認済み）

```
✓ README.md
✓ LICENSE (MIT)
✓ CONTRIBUTING.md
✓ CHANGELOG.md
✓ pom.xml
✓ src/ (Java ソース)
✓ Releases (v1.1.0 リリース済み)
✓ FAT JAR (ダウンロード可能)
```

---

## 🎉 次のステップ

1. ✅ リポジトリ作成完了
2. ✅ コード push 完了
3. ✅ リリース作成完了
4. ⏳ **手動設定**（このガイドに従う）
5. 📢 SNS で共有

---

**すべて設定完了後、素晴らしい GitHub リポジトリが完成します！** 🚀
