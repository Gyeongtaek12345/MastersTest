package stage2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Application {
    public Integer[] cardDeck = {1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8};
    int row = 6,col = 3;
    int[][] cardField = new int[col][row];
    char[][] xField = new char[col][row];
    char[][] temp = new char[col][row];
    int x,x2,y,y2,numTry,numTry2,scoreRound,player1Score,player2Score;                    //플레이어가 선택한 좌표1,2  플레이어가 시도한횟수     //필드에 남은 카드의 수
    String player1Name, player2Name;
    boolean turn;
    public static void main(String[] args) {
        Application game = new Application();          //card game에 대한 인스턴스
        System.out.println("!!!카드 맞추기 게임!!!");              //title
        System.out.println("________________________________________________________________________");
        game.setUp();
        System.out.println("________________________________________________________________________");
        game.shuffle();
        game.drawField();
        System.out.println("________________________________________________________________________");
        while(!game.winCondition()){
            while(true){
                try{
                    game.startGame();
                    System.out.println("________________________________________________________________________");
                    game.reveal();
                    break;
                }catch (Exception e){
                    System.out.println("알맞은 좌표를 입력해주세요! (범위 0,0 ~ 2,5)");
                }
            }
            game.cardCount();
            System.out.println("________________________________________________________________________");
        }
        System.out.println("축하드립니다 게임을 클리어 했습니다!!!");
        System.out.println("시도 횟수: " + game.numTry + " 남은 카드 수: " + game.cardCount());



    }
    public void setUp(){
        System.out.print("플레이어1의 이름을 입력하세요: ");
        Scanner player1 = new Scanner(System.in);
        player1Name = player1.nextLine();
        System.out.print("플레이어2의 이름을 입력하세요: ");
        Scanner player2 = new Scanner(System.in);
        player2Name = player2.nextLine();
        turn = true;                                            //플레이어1 턴 시작
    }
    //게임이 진행됨에있어 필요한 메세지의 출력과 게임에 사용될 2개의 좌표를 유저에게서 받아온다.
    public void startGame(){
        if (turn){
            System.out.println("플레이어: " + player1Name + " 시도 횟수: "+numTry+" 남은 카드 수: " + cardCount() + " 점수: " + player1Score);
        }else {
            System.out.println("플레이어: " + player2Name + " 시도 횟수: "+numTry2+" 남은 카드 수: " + cardCount() + " 점수: " + player2Score);
        }
        //System.out.println("시도 횟수: "+numTry+" 남은 카드 수: " + cardCount());
        System.out.println("카드 위치에 해당하는 죄표를 2개 입력해주세요 eg.1,2");
        System.out.print("좌표1: ");
        Scanner coordinate = new Scanner(System.in);
        String coordString = coordinate.nextLine();
        String[] data = coordString.split(",");
        y=Integer.parseInt(data[0]);
        x=Integer.parseInt(data[1]);
        System.out.print("좌표2: ");
        Scanner coordinate2 = new Scanner(System.in);
        String coordString2 = coordinate2.nextLine();
        String[] data2 = coordString2.split(",");
        y2=Integer.parseInt(data2[0]);
        x2=Integer.parseInt(data2[1]);
    }
    //Collection을 이용하여 cardDeck array를 list로 변환후에 shuffle을 사용해서 섞어준뒤 array로 변환해준다.
    public void shuffle(){
        List<Integer> deckList = Arrays.asList(cardDeck);
        Collections.shuffle(deckList);
        deckList.toArray(cardDeck);
    }
    //cardDeck의 섞인 카드들을 cardField array에 넣어주고 뒤집혀있는 카드들인 xField array를 필드에 출력한다.
    public void drawField(){
        int count = 0;
        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                cardField[i][j]=cardDeck[count];
                count++;
            }
        }
        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                xField[i][j]='x';
                temp[i][j]='x';
            }
        }
        for (int i=0; i<col; i++){                      //테스트 섞인 카드들을 출력
            for (int j=0; j<row; j++){
                System.out.print(cardField[i][j]+" ");
            }
            System.out.println();
        }
        makeField(xField);
    }
    //유저에게 받은 2개의 좌표의 값을 서로 비교하고 두값이 같을 경우 두카드를 제거하는 Remove()를 실행한다 그러나 두값이 다를경우 원래 상태로 돌아간다.
    public void reveal(){
        int pos1 = cardField[y][x];
        int pos2 = cardField[y2][x2];
        temp[y][x] = Character.forDigit(pos1,10);
        temp[y2][x2] = Character.forDigit(pos2,10);
        makeField(temp);
        System.out.println("________________________________________________________________________");
        if (pos1 == ' ' || pos2 == ' ' || x == x2 && y == y2){
            makeField(xField);
            temp[y][x] = 'x';
            temp[y2][x2] = 'x';
            if (pos1 == ' '){
                temp[y][x] = ' ';
            }
            if (pos2 == ' '){
                temp[y2][x2] = ' ';
            }
            System.out.println("올바른 2지점의 좌표를 입력해주세요! (카드가 없는 좌표와 좌표 중복 선택 불가)");
        }
        else if (pos1 == pos2){
            if (turn){
                numTry++;
            }else{
                numTry2++;
            }
            remove();
        }
        else {
            if (turn){
                numTry++;
            }else{
                numTry2++;
            }
            makeField(xField);
            temp[y][x] = 'x';
            temp[y2][x2] = 'x';
            turnChanger();
        }
    }
    //좌표 값에 있는 array 값들을 제거하고 필드에 재출력한다.
    public void remove(){
        xField[y][x] = ' ';
        xField[y2][x2] = ' ';
        temp[y][x] = ' ';
        temp[y2][x2] = ' ';
        cardField[y][x] = ' ';
        cardField[y2][x2] = ' ';
        makeField(xField);
        scoreCalculate();
    }
    //for 루프문을 이용하여 각각의 숫자가 array에 얼마나 있는지 체크한 후 모든 숫자가 2개 아래인 경우 조건문의 참값을 반환한다
    public boolean winCondition(){
        int[]count = new int[9];
        for (int z=1; z<=8; z++) {
            for (int i=0; i<col; i++){
                for (int j=0; j<row; j++){
                    if (cardField[i][j]==z){
                        count[z]++;
                    }
                }
            }
        }
        return count[1] < 2 && count[2] < 2 && count[3] < 2 && count[4] < 2 && count[5] < 2 && count[6] < 2 && count[7] < 2 && count[8] < 2;
    }
    //for 루프문을 이용하여 몇장의 카드가 필드에 남았는지 확인한다
    public int cardCount(){
        int numX = 0;
        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                if (xField[i][j] == 'x'){
                    numX++;
                }
            }
        }
        return numX;
    }
    //코드에 반복되는 코드를 줄이기위에 2D array를 콘솔에 적어주는 메서드
    public void makeField(char[][] field){
        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                System.out.print("|"+field[i][j]+"|");
            }
            System.out.println();
        }
    }
    //누구의 턴인지 체크후에 점수를 추가 부여한다. 계속해서 카드를 맞출경우 부여되는 점수가 2배가된다
    public void scoreCalculate(){
        int score = 10;
        if (turn){
            if (scoreRound >= 1){
                for (int i = 0;i < scoreRound;i++){
                    score = score*2;
                }
                player1Score += score;
            }
            else {
                player1Score += 10;
            }
        }else {
            if (scoreRound >= 1){
                for (int i = 0;i < scoreRound;i++){
                    score = score*2;
                }
                player2Score += score;
            }
            else {
                player2Score += 10;
            }
        }
        scoreRound++;
    }
    public void turnChanger(){
        turn = !turn;
        scoreRound = 0;
    }
}
