package stage1;

import stage2.Application;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardMatchingGame {
    public Integer[] cardDeck = {1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8};
    public static void main(String[] args) {
        CardMatchingGame game = new CardMatchingGame();         //card game에 대한 인스턴스
        System.out.println(Arrays.toString(game.cardDeck));     //섞기전
        game.shuffle();
        System.out.println(Arrays.toString(game.cardDeck));     //섞은후
    }
    //Collection을 이용하여 cardDeck array를 list로 변환후에 shuffle을 사용해서 섞어준뒤 array로 변환해준다.
    public void shuffle(){
        List<Integer> deckList = Arrays.asList(cardDeck);
        Collections.shuffle(deckList);
        deckList.toArray(cardDeck);
    }
}
