package mousewheeltool;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * メインアプリケーションエントリーポイント
 */
public class MouseWheelToolApp {
    private static final Logger logger = Logger.getLogger(MouseWheelToolApp.class.getName());

    public static void main(String[] args) {
        try {
            System.out.println("=========================================");
            System.out.println("  Mouse Wheel Tool v1.1.0");
            System.out.println("  OS: " + OSUtils.getOSName());
            System.out.println("  Java: " + System.getProperty("java.version"));
            System.out.println("=========================================");
            System.out.println();
            
            // Java 21+ の外観設定
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // コンポーネント初期化
            MouseWheelRotator rotator = new MouseWheelRotator();
            RotationController controller = new RotationController(rotator);
            GlobalHotKeyListener hotKeyListener = new GlobalHotKeyListener(controller);

            // GUI 作成・表示
            SwingUtilities.invokeLater(() -> {
                MouseWheelToolGUI frame = new MouseWheelToolGUI(controller);
                frame.setVisible(true);
                
                // ウィンドウクローズ時の処理
                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        try {
                            controller.stop();
                            hotKeyListener.unregister();
                            System.exit(0);
                        } catch (Exception ex) {
                            logger.log(Level.SEVERE, "シャットダウンエラー", ex);
                            System.exit(1);
                        }
                    }
                });
            });

            // グローバルホットキーリスナー開始
            hotKeyListener.register();
            logger.info("アプリケーション起動完了");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "初期化エラー", e);
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.getMessage(),
                "Mouse Wheel Tool",
                JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }
    }
}

