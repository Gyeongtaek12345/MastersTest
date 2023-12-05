package stage3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGUI extends JFrame implements ActionListener{
    String name1,name2;
    JTextField name1TF,name2TF;
    JButton startBtn = new JButton("Start Game");
    public StartGUI(){
        Font titleF = new Font("",Font.BOLD,40);
        Font nameF = new Font("",Font.BOLD,20);
        JLabel titileL = new JLabel("카드 맞추기 게임", JLabel.CENTER);
        JLabel name1L = new JLabel("플레이어1: ", JLabel.CENTER);
        JLabel name2L = new JLabel("플레이어2: ", JLabel.CENTER);

        setTitle("CardMatchingGame");
        setSize(400,400);
        setLocationRelativeTo(null);

        JPanel panel1=new JPanel();
        titileL.setFont(titleF);
        panel1.add(titileL);

        JPanel panel2=new JPanel(new GridLayout(2,2,10,10));
        name1TF=new JTextField(10);
        name2TF=new JTextField(10);

        name1L.setFont(nameF);
        panel2.add(name1L);
        panel2.add(name1TF);
        name1TF.setToolTipText("이름을 입력하세요");

        name2L.setFont(nameF);
        panel2.add(name2L);
        panel2.add(name2TF);
        name2TF.setToolTipText("이름을 입력하세요");

        JPanel panel3=new JPanel();
        panel3.add(startBtn);

        this.add(panel1,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.CENTER);
        this.add(panel3,BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        startBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        name1 = name1TF.getText();
        name2 = name2TF.getText();
        new GameGUI(name1,name2).f.setVisible(true);
        this.dispose();
    }
}
