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
        JButton b = (JButton)e.getSource();
        winnerMessage();
        for(int i=0 ; i<col;i++) {
            for(int j=0;j<row;j++) {
                if(b == btn[i][j]) {
                    if(firstButton != null && secondButton != null){
                        firstButton.setIcon(new ImageIcon("./image/back.png"));
                        secondButton.setIcon(new ImageIcon("./image/back.png"));
                        firstButton = null;
                        secondButton = null;
                    } else if(firstButton == null && secondButton == null) {
                        firstButton = b;
                        y = i;
                        x = j;
                        firstButton.setIcon(new ImageIcon("./image/0" + cardAnswer[y][x] + ".png"));

                    }else if(firstButton != null && firstButton!=b){
                        secondButton = b;
                        y2 = i;
                        x2 = j;
                        secondButton.setIcon(new ImageIcon("./image/0" + cardAnswer[y2][x2] + ".png"));
                        getPoint();
                    }
                }
            }
        }
    }
    //점수로 확인되는 조건을 cardAnswer의 좌표를 비교하여 체크한다.
    public void getPoint(){
        if (cardAnswer[y][x] == cardAnswer[y2][x2]){
            //점수를 부여
            scoreCalculate();
            firstButton.setEnabled(false);
            secondButton.setEnabled(false);
            cardAnswer[y][x] = ' ';
            cardAnswer[y2][x2] = ' ';
            firstButton = null;
            secondButton = null;
        }else if(cardAnswer[y][x] != cardAnswer[y2][x2]){
            //턴을 종료한다 (상대턴 시작)
            turnChanger();
        }
        //ui 업데이트
        labelChange();
    }
    //단계1,2에서 가져온 누구의 턴인지 체크후 그플레이어에게 점수를 부여하는 메서드
    public void scoreCalculate() {
        int score = 10;
        if (!turn) {
            if (scoreRound >= 1) {
                for (int i = 0; i < scoreRound; i++) {
                    score = score * 2;
                }
                player1Score += score;
            } else {
                player1Score += 10;
            }
        } else {
            if (scoreRound >= 1) {
                for (int i = 0; i < scoreRound; i++) {
                    score = score * 2;
                }
                player2Score += score;
            } else {
                player2Score += 10;
            }
        }
        scoreRound++;
    }
    //상대턴으로 바꿔준다.
    public void turnChanger(){
        turn = !turn;
        scoreRound = 0;
    }
    //턴이 지남에 따라 레이블을 업데이트 해준다.
    public void labelChange(){
        if (!turn){
            nameL.setText(name1 + "의 턴 점수: " + player1Score);
        }else{
            nameL.setText(name2 + "의 턴 점수: " + player2Score);
        }
        scoreL.setText(("점수표: "+name1+": "+player1Score+" "+name2+": "+player2Score));
    }
    //남은 카드중 똑같은 카드가 없을 경우 참값을 반환
    public boolean winCondition(){
        int[]count = new int[9];
        for (int z=1; z<=8; z++) {
            for (int i=0; i<col; i++){
                for (int j=0; j<row; j++){
                    if (cardAnswer[i][j]==z){
                        count[z]++;
                    }
                }
            }
        }
        return count[1] < 2 && count[2] < 2 && count[3] < 2 && count[4] < 2 && count[5] < 2 && count[6] < 2 && count[7] < 2 && count[8] < 2;
    }
    //게임이 끝난후에 메세지출력 (승리자 및 두 플레이어의 점수)
    public void winnerMessage()
    {
        if (winCondition()){
            if (player1Score > player2Score){
                JOptionPane.showMessageDialog(null,name1+"님이 승리했습니다.\n"+name1+"의 점수: "+player1Score+"\n"+name2+"의 점수: "+player2Score);
            }else if(player2Score > player1Score){
                JOptionPane.showMessageDialog(null,name2+"님이 승리했습니다.\n"+name1+"의 점수: "+player1Score+"\n"+name2+"의 점수: "+player2Score);
            }else {
                JOptionPane.showMessageDialog(null,"무승부입니다.\n"+name1+"의 점수: "+player1Score+"\n"+name2+"의 점수: "+player2Score);
            }
        }
    }
}

