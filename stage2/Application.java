package stage1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class Application {
    public Integer[] cardDeck = {1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8};
    int row = 6;
    int col = 3;
    int[][] cardField = new int[col][row];
    char[][] xField = new char[col][row];
    char[][] temp = new char[col][row];
    int x,x2,y,y2,numTry;
    int numX = 18;
    public static void main(String[] args) {
        Application app = new Application();
        System.out.println("!!!카드 맞추기 게임!!!");
        app.Shuffle();
        app.Draw_Field();
        System.out.println("________________________________________________________________________");
        while(!app.Win_Condition()){
            app.Start_Game();
            System.out.println("________________________________________________________________________");
            app.Reveal();
            app.numTry++;
            app.Card_Count();
            System.out.println("________________________________________________________________________");
        }
        System.out.println("축하드립니다 게임을 클리어 했습니다!!!");
        System.out.println("시도 횟수: "+app.numTry+" 남은 카드 수: "+app.numX);


    }

    //게임이 진행됨에있어 필요한 메세지의 출력과 게임에 사용될 2개의 좌표를 유저에게서 받아온다.
    public void Start_Game(){
        System.out.println("시도 횟수: "+numTry+" 남은 카드 수: "+numX);
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

    //Java에 collection을 이용하여 cardDeck array를 list로 변환후에 shuffle을 사용해서 섞어준뒤 array로 변환해준다.
    public void Shuffle(){
        List<Integer> deckList = Arrays.asList(cardDeck);
        Collections.shuffle(deckList);
        deckList.toArray(cardDeck);
    }
    //cardDeck의 섞인 카드들을 cardField array에 넣어주고 뒤집혀있는 카드들인 xField array를 필드에 출력한다.
    public void Draw_Field(){
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
            }
        }
        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                temp[i][j]='x';
            }
        }
        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                System.out.print(cardField[i][j]+" ");
            }
            System.out.println();
        }
        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                System.out.print(xField[i][j]+" ");
            }
            System.out.println();
        }
    }
    //유저에게 받은 2개의 좌표의 값을 서로 비교하고 두값이 같을 경우 두카드를 제거하는 Remove()를 실행한다 그러나 두값이 다를경우 원래 상태로 돌아간다.
    public void Reveal(){
        int pos1 = cardField[y][x];
        int pos2 = cardField[y2][x2];
        temp[y][x] = Character.forDigit(pos1,10);
        temp[y2][x2] = Character.forDigit(pos2,10);
        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                System.out.print(temp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("________________________________________________________________________");
        if (pos1 == pos2){
            Remove();
        }
        else {
            for (int i=0; i<col; i++){
                for (int j=0; j<row; j++){
                    System.out.print(xField[i][j]+" ");
                }
                System.out.println();
            }
            temp[y][x] = 'x';
            temp[y2][x2] = 'x';
        }
    }
    //좌표 값에 있는 array 값들을 제거하고 필드에 재출력한다.
    public void Remove(){
        xField[y][x] = ' ';
        xField[y2][x2] = ' ';
        temp[y][x] = ' ';
        temp[y2][x2] = ' ';
        cardField[y][x] = ' ';
        cardField[y2][x2] = ' ';
        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                System.out.print(xField[i][j]+" ");
            }
            System.out.println();
        }
    }
    //for 루프문을 이용하여 각각의 숫자가 array에 얼마나 있는지 체크한 후 모든 숙자가 2개 아래인 경우 조건문의 참값을 반환한다
    public boolean Win_Condition(){
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
    public void Card_Count(){
        numX = 0;
        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                if (xField[i][j] == 'x'){
                    numX++;
                }
            }
        }
    }
}
