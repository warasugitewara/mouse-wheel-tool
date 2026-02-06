package mousewheeltool;

import java.awt.*;

/**
 * マウスホイール回転を制御するクラス
 */
public class MouseWheelRotator {
    private final Robot robot;

    public MouseWheelRotator() throws AWTException {
        this.robot = new Robot();
    }

    /**
     * マウスホイールを回転させる
     * @param amount 回転量（正=下、負=上）
     */
    public void rotate(int amount) {
        try {
            // デバッグ出力
            System.out.println("[MouseWheel] Rotating: " + amount);
            robot.mouseWheel(amount);
        } catch (Exception e) {
            System.err.println("[MouseWheel] Error: " + e.getMessage());
        }
    }

    /**
     * マウスホイールを上に回転（スクロール）
     * @param amount 回転量
     */
    public void scrollUp(int amount) {
        rotate(-Math.abs(amount));
    }

    /**
     * マウスホイールを下に回転（スクロール）
     * @param amount 回転量
     */
    public void scrollDown(int amount) {
        rotate(Math.abs(amount));
    }
}
