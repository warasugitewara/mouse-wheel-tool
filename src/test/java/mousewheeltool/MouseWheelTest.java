package mousewheeltool;

import java.awt.*;

/**
 * マウスホイール動作テスト用プログラム
 */
public class MouseWheelTest {
    public static void main(String[] args) {
        try {
            MouseWheelRotator rotator = new MouseWheelRotator();
            
            System.out.println("=== Mouse Wheel Test ===");
            System.out.println("Testing mouse wheel rotation...");
            System.out.println();
            
            // テスト 1: 下方向に 5 回転
            System.out.println("Test 1: Scrolling DOWN 5 times...");
            for (int i = 0; i < 5; i++) {
                rotator.rotate(1);
                Thread.sleep(200);
            }
            
            Thread.sleep(500);
            
            // テスト 2: 上方向に 5 回転
            System.out.println("Test 2: Scrolling UP 5 times...");
            for (int i = 0; i < 5; i++) {
                rotator.rotate(-1);
                Thread.sleep(200);
            }
            
            System.out.println();
            System.out.println("Test completed!");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
