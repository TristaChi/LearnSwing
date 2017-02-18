import javax.swing.*;
import java.awt.*;

public class FirstGame extends JFrame {
    public FirstGame() {
        initUI();
    }

    private void initUI() {
        setTitle("十面埋伏");
        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        TestPane testPanel = new TestPane(600,500);
        this.add(testPanel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run() {
                 FirstGame ex = new FirstGame();
                ex.setVisible(true);
            }
        });
    }
}