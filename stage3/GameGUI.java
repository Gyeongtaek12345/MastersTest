package stage3;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class GameGUI implements ActionListener {
    //변수 생성
    JFrame f;
    int col = 3,row = 6;
    JButton[][] btn = new JButton[col][row];
    int[][] cardAnswer = new int[col][row];
    public Integer[] cardDeck = {1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8};
    JButton firstButton,secondButton;
    int x,y,x2,y2,scoreRound,player1Score,player2Score;
    String name1,name2;
    JLabel nameL,scoreL;
    boolean turn;
    public static void main(String[] args) {
    }
    //단계 1,2와 똑같은 방법으로 카드를 섞어 cardDeck array에 넣어준다.
    public void shuffle(){
        List<Integer> deckList = Arrays.asList(cardDeck);
        Collections.shuffle(deckList);
        deckList.toArray(cardDeck);
    }
    //cardDeck에 있는 카드들을 2D array인 cardAnswer안에 순서대로 넣어준다
    public void setCardAnswer(){
        int count = 0;
        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                cardAnswer[i][j]=cardDeck[count];
                count++;
            }
        }
    }
    //인스턴스 생성시 실행 이름을 받아와 저장하고 초기 상태을 실행
    public GameGUI(String name1, String name2){
        this.name1 = name1;
        this.name2 = name2;
        f=new JFrame("카드 게임");
        for(int i = 0; i<col;i++) {
            for(int j = 0;j<row;j++) {
                btn[i][j] = new JButton();
            }
        }
        addLayout();
        shuffle();
        setCardAnswer();
        cardBegin();
    }
    //ui 레이아웃과 초기 화면을 구성한다
    public void addLayout(){
        Font gameF = new Font("",Font.BOLD,40);
        Font scoreF = new Font("",Font.BOLD,20);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.setLayout(new GridLayout(col,row,10,10));
        panel2.setLayout(new GridLayout(2,1));
        for(int i = 0;i<col;i++) {
            for (int j = 0 ;j<row;j++) {
                panel1.add(btn[i][j]);
                btn[i][j].addActionListener(this);
            }
        }
        nameL = new JLabel(name1 + "의 턴 점수: " + player1Score);
        scoreL = new JLabel("점수표 "+name1+": "+player1Score+" "+name2+": "+player2Score);
        nameL.setFont(gameF);
        scoreL.setFont(scoreF);
        nameL.setHorizontalAlignment(JLabel.CENTER);
        scoreL.setHorizontalAlignment(JLabel.CENTER);
        panel2.add(nameL);
        panel2.add(scoreL);

        f.add(panel1,BorderLayout.NORTH);
        f.add(new JSeparator(), BorderLayout.CENTER);
        f.add(panel2,BorderLayout.SOUTH);

        f.setSize(1600, 1000);
        f.setLocationRelativeTo(null);
        f.setVisible(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //처음 보여지는 화면 for루프를 이용해서 버튼에 카드 뒷면 이미지를 넣어준다.
    public void cardBegin(){
        for(int i=0 ; i<col;i++) {
            for(int j=0;j<row;j++) {
                btn[i][j].setIcon(new ImageIcon ("./image/back.png"));
            }

        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //버튼 클릭시 실행
    }
}

