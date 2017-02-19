import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FirstGame extends JFrame {
	JLabel b_password = new JLabel("password");
	JPasswordField text = new JPasswordField();
	public JButton jb =  new JButton ("Hello, Welcome for coming");
	
    public FirstGame() {
        initUI();
    }

    private void initUI() {
    	
        setTitle("十面埋伏");
        setSize(1000, 1000);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel jp = (JPanel)this.getContentPane();
        jp.setLayout(new GridLayout(1,1,1,1));

        TestPane testPanel = new TestPane(1000,1000,this);
        
        this.add(testPanel);
        jp.add(jb);
        
        
//        jp.add(b_password);
//        jp.add(text);
        
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