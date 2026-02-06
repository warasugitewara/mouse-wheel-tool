package mousewheeltool;

import javax.swing.*;
import java.awt.*;

/**
 * マウスホイール回転ツール用 GUI
 */
public class MouseWheelToolGUI extends JFrame {
    private final RotationController controller;
    private final JLabel statusLabel;
    private final JLabel speedLabel;
    private final JLabel amountLabel;
    private final JSlider speedSlider;
    private final JSlider amountSlider;
    private final JSpinner speedSpinner;
    private final JSpinner amountSpinner;
    private final JComboBox<String> directionCombo;
    private final JButton startButton;
    private final JButton stopButton;

    public MouseWheelToolGUI(RotationController controller) {
        this.controller = controller;
        
        // ウィンドウ設定
        setTitle("Mouse Wheel Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // メインパネル
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ステータスラベル
        statusLabel = new JLabel("Status: Stopped");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(statusLabel);
        mainPanel.add(Box.createVerticalStrut(15));

        // 速度スライダー
        mainPanel.add(new JLabel("Rotation Speed (ms):"));
        JPanel speedPanel = new JPanel(new BorderLayout(10, 0));
        
        speedSlider = new JSlider(JSlider.HORIZONTAL, 10, 500, 50);
        speedSlider.setMajorTickSpacing(50);
        speedSlider.setMinorTickSpacing(10);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(false);
        
        speedLabel = new JLabel("50 ms");
        speedLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        speedLabel.setPreferredSize(new Dimension(50, 20));
        
        speedSpinner = new JSpinner(new SpinnerNumberModel(50, 10, 500, 5));
        speedSpinner.setPreferredSize(new Dimension(80, 30));
        
        speedSlider.addChangeListener(e -> {
            if (!speedSlider.getModel().getValueIsAdjusting()) {
                int speed = speedSlider.getValue();
                speedSpinner.setValue(speed);
            }
        });
        
        speedSpinner.addChangeListener(e -> {
            int speed = (int) speedSpinner.getValue();
            speedSlider.setValue(speed);
            controller.setRotationSpeed(speed);
            speedLabel.setText(speed + " ms");
        });
        
        speedPanel.add(speedSlider, BorderLayout.CENTER);
        speedPanel.add(speedLabel, BorderLayout.WEST);
        speedPanel.add(speedSpinner, BorderLayout.EAST);
        mainPanel.add(speedPanel);
        mainPanel.add(Box.createVerticalStrut(10));

        // 回転量スライダー
        mainPanel.add(new JLabel("Rotation Amount (ticks):"));
        JPanel amountPanel = new JPanel(new BorderLayout(10, 0));
        
        amountSlider = new JSlider(JSlider.HORIZONTAL, 1, 20, 1);
        amountSlider.setMajorTickSpacing(5);
        amountSlider.setMinorTickSpacing(1);
        amountSlider.setPaintTicks(true);
        amountSlider.setPaintLabels(false);
        
        amountLabel = new JLabel("1");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        amountLabel.setPreferredSize(new Dimension(50, 20));
        
        amountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        amountSpinner.setPreferredSize(new Dimension(80, 30));
        
        amountSlider.addChangeListener(e -> {
            if (!amountSlider.getModel().getValueIsAdjusting()) {
                int amount = amountSlider.getValue();
                amountSpinner.setValue(amount);
            }
        });
        
        amountSpinner.addChangeListener(e -> {
            int amount = (int) amountSpinner.getValue();
            amountSlider.setValue(amount);
            controller.setRotationAmount(amount);
            amountLabel.setText(String.valueOf(amount));
        });
        
        amountPanel.add(amountSlider, BorderLayout.CENTER);
        amountPanel.add(amountLabel, BorderLayout.WEST);
        amountPanel.add(amountSpinner, BorderLayout.EAST);
        mainPanel.add(amountPanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // 方向選択
        JPanel directionPanel = new JPanel(new BorderLayout(10, 0));
        directionPanel.add(new JLabel("Direction:"), BorderLayout.WEST);
        directionCombo = new JComboBox<>(new String[]{"Down (↓)", "Up (↑)"});
        directionCombo.setSelectedIndex(0);
        directionCombo.setMaximumSize(new Dimension(150, 30));
        directionCombo.addActionListener(e -> {
            int direction = (directionCombo.getSelectedIndex() == 0) ? 1 : -1;
            controller.setRotationDirection(direction);
        });
        directionPanel.add(directionCombo, BorderLayout.CENTER);
        mainPanel.add(directionPanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // ボタンパネル
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

        startButton = new JButton("Start (F9)");
        startButton.setFont(new Font("Arial", Font.BOLD, 13));
        startButton.setPreferredSize(new Dimension(120, 40));
        startButton.addActionListener(e -> controller.start());
        buttonPanel.add(startButton);

        stopButton = new JButton("Stop (F10)");
        stopButton.setFont(new Font("Arial", Font.BOLD, 13));
        stopButton.setPreferredSize(new Dimension(120, 40));
        stopButton.addActionListener(e -> controller.stop());
        buttonPanel.add(stopButton);

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // ステータスメッセージ
        JTextArea infoArea = new JTextArea(3, 40);
        infoArea.setText("Press F9/F10 anywhere (global hotkey)\n" +
                        "Or click buttons, or use spinner controls\n" +
                        "Adjust settings in real-time");
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setFont(new Font("Arial", Font.ITALIC, 11));
        infoArea.setForeground(Color.GRAY);
        infoArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.add(infoArea);

        add(mainPanel);

        // 更新タイマー（ステータス表示用）
        Timer updateTimer = new Timer(500, e -> updateStatus());
        updateTimer.start();
    }

    /**
     * ステータス表示を更新
     */
    private void updateStatus() {
        if (controller.isRunning()) {
            statusLabel.setText("Status: Running ◉");
            statusLabel.setForeground(new Color(0, 128, 0));
        } else {
            statusLabel.setText("Status: Stopped ◎");
            statusLabel.setForeground(Color.BLACK);
        }
    }
}

