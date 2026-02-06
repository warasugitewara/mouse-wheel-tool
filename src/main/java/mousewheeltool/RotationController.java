package mousewheeltool;

import java.util.logging.Logger;

/**
 * マウスホイール回転の制御と状態管理
 */
public class RotationController {
    private static final Logger logger = Logger.getLogger(RotationController.class.getName());
    
    private final MouseWheelRotator rotator;
    private volatile boolean isRunning = false;
    private volatile int rotationSpeed = 50;     // デフォルト回転速度（ミリ秒）
    private volatile int rotationAmount = 1;     // デフォルト回転量
    private volatile int rotationDirection = 1;  // 1=下, -1=上
    
    private Thread rotationThread;
    private final Object lock = new Object();

    public RotationController(MouseWheelRotator rotator) {
        this.rotator = rotator;
    }

    /**
     * マウスホイール回転開始
     */
    public void start() {
        synchronized (lock) {
            if (isRunning) {
                logger.info("既に実行中");
                return;
            }
            isRunning = true;
            rotationThread = new Thread(this::rotationLoop, "MouseWheelRotationThread");
            rotationThread.setDaemon(true);
            rotationThread.start();
            logger.info("マウスホイール回転開始 (Speed: " + rotationSpeed + "ms, Amount: " + rotationAmount + ")");
        }
    }

    /**
     * マウスホイール回転停止
     */
    public void stop() {
        synchronized (lock) {
            if (!isRunning) {
                logger.info("既に停止中");
                return;
            }
            isRunning = false;
            if (rotationThread != null) {
                rotationThread.interrupt();
                try {
                    // スレッド終了を待機（最大1秒）
                    rotationThread.join(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            logger.info("マウスホイール回転停止");
        }
    }

    /**
     * 回転ループ
     */
    private void rotationLoop() {
        logger.info("回転スレッド開始");
        try {
            while (isRunning) {
                int amount = rotationAmount * rotationDirection;
                rotator.rotate(amount);
                Thread.sleep(rotationSpeed);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.info("回転スレッド中断");
        } finally {
            logger.info("回転スレッド終了");
        }
    }

    /**
     * 回転速度を設定（ミリ秒）
     */
    public void setRotationSpeed(int speedMs) {
        int newSpeed = Math.max(10, speedMs);
        if (this.rotationSpeed != newSpeed) {
            this.rotationSpeed = newSpeed;
            logger.info("回転速度変更: " + newSpeed + "ms");
        }
    }

    /**
     * 回転量を設定
     */
    public void setRotationAmount(int amount) {
        int newAmount = Math.max(1, amount);
        if (this.rotationAmount != newAmount) {
            this.rotationAmount = newAmount;
            logger.info("回転量変更: " + newAmount);
        }
    }

    /**
     * 回転方向を設定（1=下, -1=上）
     */
    public void setRotationDirection(int direction) {
        int newDirection = (direction > 0) ? 1 : -1;
        if (this.rotationDirection != newDirection) {
            this.rotationDirection = newDirection;
            logger.info("回転方向変更: " + (newDirection > 0 ? "下" : "上"));
        }
    }

    /**
     * 現在の回転速度を取得
     */
    public int getRotationSpeed() {
        return rotationSpeed;
    }

    /**
     * 現在の回転量を取得
     */
    public int getRotationAmount() {
        return rotationAmount;
    }

    /**
     * 回転中かどうかを取得
     */
    public boolean isRunning() {
        return isRunning;
    }
}
