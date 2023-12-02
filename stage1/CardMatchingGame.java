package stage1;

import stage2.Application;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardMatchingGame {
    public Integer[] cardDeck = {1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8};
    int row = 6;
    int col = 3;
    int[][] cardField = new int[col][row];
    char[][] xField = new char[col][row];
    char[][] temp = new char[col][row];
    public static void main(String[] args) {
        CardMatchingGame game = new CardMatchingGame();         //card game에 대한 인스턴스
//        System.out.println(Arrays.toString(game.cardDeck));     //섞기전
        game.shuffle();
//        System.out.println(Arrays.toString(game.cardDeck));     //섞은후
        game.drawField();

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
            }
        }
        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                temp[i][j]='x';
            }
        }
        for (int i=0; i<col; i++){                      //테스트 섞인 카드들을 출력
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
}
