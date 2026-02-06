package mousewheeltool;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;
import java.util.logging.Logger;

/**
 * グローバルホットキーリスナー（Windows/Linux 対応）
 */
public class GlobalHotKeyListener {
    private static final Logger logger = Logger.getLogger(GlobalHotKeyListener.class.getName());
    
    private static final int VK_F9 = 0x78;   // F9
    private static final int VK_F10 = 0x79;  // F10
    
    private static final int ID_F9_HOTKEY = 1;
    private static final int ID_F10_HOTKEY = 2;
    
    private final RotationController controller;
    private Thread listenerThread;
    private volatile boolean isListening = false;
    
    public GlobalHotKeyListener(RotationController controller) {
        this.controller = controller;
    }
    
    /**
     * ホットキーリスナー開始
     */
    public void register() {
        if (isListening) {
            logger.info("既にリスニング中");
            return;
        }
        
        isListening = true;
        
        if (OSUtils.isWindows()) {
            listenerThread = new Thread(this::listenWindows, "GlobalHotKeyListener-Windows");
        } else if (OSUtils.isLinux()) {
            listenerThread = new Thread(this::listenLinux, "GlobalHotKeyListener-Linux");
        } else {
            logger.warning("未サポートの OS: " + OSUtils.getOSName());
            isListening = false;
            return;
        }
        
        listenerThread.setDaemon(true);
        listenerThread.start();
        logger.info("グローバルホットキーリスナー開始 (" + OSUtils.getOSName() + ")");
    }
    
    /**
     * ホットキーリスナー停止
     */
    public void unregister() {
        isListening = false;
        if (listenerThread != null) {
            listenerThread.interrupt();
        }
        logger.info("グローバルホットキーリスナー停止");
    }
    
    /**
     * Windows でのホットキー検出ループ
     */
    private void listenWindows() {
        try {
            User32 user32 = User32.INSTANCE;
            
            // F9 登録
            user32.RegisterHotKey(null, ID_F9_HOTKEY, 0, VK_F9);
            logger.info("F9 ホットキー登録 (Windows)");
            
            // F10 登録
            user32.RegisterHotKey(null, ID_F10_HOTKEY, 0, VK_F10);
            logger.info("F10 ホットキー登録 (Windows)");
            
            // メッセージループ
            WinUser.MSG msg = new WinUser.MSG();
            int result;
            
            while (isListening) {
                result = user32.GetMessage(msg, null, 0, 0);
                
                if (result > 0) {
                    if (msg.message == WinUser.WM_HOTKEY) {
                        int id = msg.wParam.intValue();
                        if (id == ID_F9_HOTKEY) {
                            logger.info("F9 ホットキー検出 (Windows)");
                            controller.start();
                        } else if (id == ID_F10_HOTKEY) {
                            logger.info("F10 ホットキー検出 (Windows)");
                            controller.stop();
                        }
                    }
                } else if (result < 0) {
                    logger.warning("GetMessage エラー");
                    break;
                }
            }
            
            // クリーンアップ
            user32.UnregisterHotKey(null, ID_F9_HOTKEY);
            user32.UnregisterHotKey(null, ID_F10_HOTKEY);
            logger.info("ホットキー登録解除 (Windows)");
            
        } catch (Exception e) {
            logger.warning("Windows ホットキー処理エラー: " + e.getMessage());
        }
    }
    
    /**
     * Linux でのホットキー検出ループ
     * 注: Linux では xdotool コマンドを使用するか、キーボードイベントを監視
     * ここでは簡易的に xdotool コマンドラインを試みます
     */
    private void listenLinux() {
        try {
            logger.info("Linux グローバルホットキー: xdotool を使用してください");
            logger.info("セットアップコマンド:");
            logger.info("xbindkeys --defaults > ~/.xbindkeysrc");
            logger.info("# 以下を .xbindkeysrc に追加:");
            logger.info("\"java -cp target/classes mousewheeltool.LinuxHotKeyClient start\"");
            logger.info("    F9");
            logger.info("\"java -cp target/classes mousewheeltool.LinuxHotKeyClient stop\"");
            logger.info("    F10");
            
            // xdotool を使用した簡易実装は環境依存性が高いため、
            // GUI キーリスナーの使用を推奨
            while (isListening) {
                Thread.sleep(1000);
            }
            
        } catch (Exception e) {
            logger.warning("Linux ホットキー処理エラー: " + e.getMessage());
        }
    }
}
