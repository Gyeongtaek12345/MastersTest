# Code Masters Test
## #️⃣[1단계] <카드 맞추기 게임 > 기본 로직 구현하기#️⃣
### 💡요구사항
#### 준비
- 카드 덱에는 1, 2, 3, 4, 5, 6, 7, 8 8장의 카드가 각 세 장씩 총 24장이 들어있다.
- 게임을 시작하면 카드 덱의 카드를 랜덤하게 섞는다.
- 카드를 섞어둔 덱에서 카드 18장을 순서대로 뽑아와서 3행 6열로 배치한다.
#### 플레이
1. (초기 시작) 게임을 시작하면 화면에는 카드를 뒤집어져 있는 상태로 출력한다. 뒤집힌 마크는 X로 표시한다. 카드 사이는 공백으로 표시한다.
2. (입력 메뉴) 콘솔에는 시도 횟수, 남은 카드, 입력 메시지를 표시한다. 플레이어는 (1, 1) ~ (3, 6) 까지 카드 위치에 해당하는 좌표를 두 번 입력한다.
3. (카드 출력) 플레이어가 입력한 자리에 있는 카드를 뒤집어서 카드의 종류를 보여준다.
4. (카드 제거) 만약 두 카드가 일치하면 해당 카드를 제거하고 카드가 뒤집힌 화면을 출력한다.
5. (종료 조건) 모든 카드를 맞추거나 더 이상 남은 짝이 없을 경우 축하메시지를 출력하고 종료한다.
6. (반복 하기) 게임이 끝나지 않았을 경우 1부터 반복한다.
### 🏃동작 예시
게임 실행

    !!!카드 맞추기 게임!!!
    x x x x x x 
    x x x x x x
    x x x x x x
    ________________________________________________________________________
    시도 횟수: 0 남은 카드 수: 18
    카드 위치에 해당하는 죄표를 2개 입력해주세요 예시: 1,2
    좌표1: 0,0
    좌표2: 0,5
    ________________________________________________________________________
    4 x x x x 4
    x x x x x x
    x x x x x x
    ________________________________________________________________________
      x x x x   
    x x x x x x
    x x x x x x
    ________________________________________________________________________
    시도 횟수: 1 남은 카드 수: 16
    카드 위치에 해당하는 죄표를 2개 입력해주세요 예시: 1,2
    좌표1: 0,1
    좌표2: 2,0

게임 종료

            x x
          x x 7 
              7 
    ________________________________________________________________________
            x x 
          x x   

    ________________________________________________________________________
    축하드립니다 게임을 클리어 했습니다!!!
    시도 횟수: 8 남은 카드 수: 4

### 🔎단계별 풀이 및 코드 설명
#### 준비
- 카드 덱에는 1부터 8까지의 카드가 3세트 들어가있다 그러므로 array를 이용하여 미리 준비된 덱을 만들어준다.


        public Integer[] cardDeck = {1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8};

- 덱을 섞어주기 위해 array를 list로 변환 후 Collection 의 shuffle 함수를 이용해준다.


        public void shuffle(){
            List<Integer> deckList = Arrays.asList(cardDeck);
            Collections.shuffle(deckList);
            deckList.toArray(cardDeck);
        }

- 섞어준 덱의 카드들을 2D array (cardField) 안에 3행 6열로 배치한다.


        int count = 0;
        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                cardField[i][j]=cardDeck[count];
                count++;
            }
        }

### 플레이
1. *(초기 시작)* 뒤집힌 카드를 표시하는 x를 x가 들어있는 2D array (xField) 를 for 루프를 이용하여 3행 6열로 콘솔에 프린트한다.


        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                System.out.print(xField[i][j]+" ");
            }
            System.out.println();
        }

2. *(입력 메뉴)* 변수 numTry 와 변수 numX 를 이용하여 시도 횟수와 남은 카드의 수를 가져오고 Scanner를 이용해서 유저한테서 좌표(y,x)을 받아서 splite을 이용해 나눠준다.


        System.out.print("좌표1: ");
        Scanner coordinate = new Scanner(System.in);
        String coordString = coordinate.nextLine();
        String[] data = coordString.split(",");
        y=Integer.parseInt(data[0]);
        x=Integer.parseInt(data[1]);

3. *(카드 출력)* 유저에게 받은 2개의 좌표가 나타내는 값을 cardField array에서 찿은후 temp array의 같은 좌표 값을 바꿔서 콘솔에 프린트한다.


        int pos1 = cardField[y][x];
        int pos2 = cardField[y2][x2];
        temp[y][x] = Character.forDigit(pos1,10);
        temp[y2][x2] = Character.forDigit(pos2,10);
        루프출력문

4. *(카드 제거)* 2개의 좌표가 나타내는 값이 같을 경우 array에서 값을 지우고 카드가 뒤집어진 상태로 출력한다.


        xField[y][x] = ' ';
        xField[y2][x2] = ' ';
        temp[y][x] = ' ';
        temp[y2][x2] = ' ';        
        cardField[y][x] = ' ';
        cardField[y2][x2] = ' ';
        루프출력문

5. *(종료 조건)* 루프문을 이용하여 각각의 숫자가 배열안에 얼마나 있는지 체크한 후 모든 숫자가 2개 아래인 경우 참값을 리턴한다.


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

6. *(반복 하기)* while을 이용해서 종료 조건이 참일경우 프로그램 종료한다.


        while(!game.winCondition())
## 2단계
    
## 3단계

