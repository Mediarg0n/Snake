import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InputFrame extends JFrame implements ActionListener {
    private Dimension screenSize;
    private GridLayout gridLayout;
    private JTextField widht, hight, scale, buffer;
    private JButton button;
    private GameControllerInterface gameController;
    private JCheckBox cBox;

    public InputFrame(GameControllerInterface gameController) {

        this.gameController = gameController;
        Font font = new Font("Arial", 0, 20);
        this.setLayout(gridLayout = new GridLayout(6, 2));

        JLabel wLabel = new JLabel("Widht: ");
        wLabel.setFont(font);
        this.add(wLabel);
        this.add(widht = new JTextField("15"));

        JLabel hLabel = new JLabel("Hight: ");
        hLabel.setFont(font);
        this.add(hLabel);
        this.add(hight = new JTextField("15"));

        JLabel sLabel = new JLabel("Scale: ");
        sLabel.setFont(font);
        this.add(sLabel);
        this.add(scale = new JTextField("20"));

        JLabel bLabel = new JLabel("Timebuffer: ");
        bLabel.setFont(font);
        this.add(bLabel);
        this.add(buffer = new JTextField("100"));

        JLabel wallLabel = new JLabel("Wall?");
        wallLabel.setFont(font);
        this.add(wallLabel);
        this.add(cBox = new JCheckBox());
        cBox.setSelected(true);

        this.add(button = new JButton("Starten"));
        button.addActionListener(this);
        this.setSize(300, 300);

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(((int) screenSize.getWidth() - this.getWidth()) / 2, ((int) screenSize.getHeight() - this.getHeight()) / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public int getWidht() {
        return Integer.parseInt(widht.getText());
    }

    public int getHidht() {
        return Integer.parseInt(hight.getText());
    }

    public int getScale() {
        return Integer.parseInt(scale.getText());
    }

    public int getBuffer() {
        return Integer.parseInt(buffer.getText());
    }

    public boolean getWall() {
        return cBox.isSelected();
    }


    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.setVisible(false);
        gameController.startGame(true);
    }

}
