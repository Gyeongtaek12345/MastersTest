# Code Masters Test
## #️⃣[1단계] <카드 맞추기 게임 > 기본 로직 구현하기#️⃣
### 💡요구사항💡
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
### 🏃동작 예시🏃
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

### 🔎단계별 풀이 및 코드 설명🔎
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

## #️⃣[2단계] 2인 게임으로 구현하기#️⃣
### 💡요구사항💡
#### 기능
- 프로그램 시작시 1P와 2P의 이름을 입력받고, 1P부터 게임을 시작한다.
- 플레이어 스코어 표시 기능을 추가한다.
- 카드 맞추기에 성공할 경우 다시 입력 찬스를 부여한다.
- 카드를 한 번 맞출경우 10점 그리고 연속으로 맞출 경우 점수가 두 배씩 증가한다.
- 모든 카드를 맞추면 게임을 종료한다.
- 게임 종료 후 각 플레이어별 스코어와 승리자를 표시한다.

### 🏃동작 예시🏃
실행 후 플레이어 정보 입력

    !!!카드 맞추기 게임!!!
    ________________________________________________________________________
    플레이어1의 이름을 입력하세요: 짱구
    플레이어2의 이름을 입력하세요: 철수

플레이어1의 턴 시작 그리고 카드를 못 맞출경우 턴이 플레이어2에게로 넘어간다.

    [x][x][x][x][x][x]
    [x][x][x][x][x][x]
    [x][x][x][x][x][x]
    ________________________________________________________________________
    플레이어: 짱구 시도 횟수: 0 남은 카드 수: 18 점수: 0
    카드 위치에 해당하는 죄표를 2개 입력해주세요 eg.1,2
    좌표1: 0,0
    좌표2: 0,1
    ________________________________________________________________________
    [4][8][x][x][x][x]
    [x][x][x][x][x][x]
    [x][x][x][x][x][x]
    ________________________________________________________________________
    [x][x][x][x][x][x]
    [x][x][x][x][x][x]
    [x][x][x][x][x][x]
    ________________________________________________________________________
    플레이어: 철수 시도 횟수: 0 남은 카드 수: 18 점수: 0
    카드 위치에 해당하는 죄표를 2개 입력해주세요 eg.1,2

플레이어가 카드의 값을 맞출경우 점수를 얻고 추가 턴을 얻게된다.

    [x][x][6][6][x][x]
    [x][x][x][x][x][x]
    [x][x][x][x][x][x]
    ________________________________________________________________________
    [x][x][ ][ ][x][x]
    [x][x][x][x][x][x]
    [x][x][x][x][x][x]
    ________________________________________________________________________
    플레이어: 철수 시도 횟수: 1 남은 카드 수: 16 점수: 10
    카드 위치에 해당하는 죄표를 2개 입력해주세요 eg.1,2

플레이어가 연속으로 맞출 경우 획득 점수가 2 배로 증가한다.

    플레이어: 철수 시도 횟수: 2 남은 카드 수: 14 점수: 30

게임이 종료되면 승리자와 각 플레이어의 시도 횟수와 점수를 표시한다.

    [ ][ ][ ][ ][ ][ ]
    [ ][ ][ ][x][ ][x]
    [ ][ ][x][ ][x][ ]
    ________________________________________________________________________
    철수 승리!!!
    짱구 시도 횟수: 5 점수: 20
    철수 시도 횟수: 7 점수: 70

### 🔎단계별 풀이 및 코드 설명🔎
- *(이름 입력)* setUp 메서드에서 스캐너를 이용해서 두개의 이름을 플레이어에게서 받아온다.


        System.out.print("플레이어1의 이름을 입력하세요: ");
        Scanner player1 = new Scanner(System.in);
        player1Name = player1.nextLine();
        System.out.print("플레이어2의 이름을 입력하세요: ");
        Scanner player2 = new Scanner(System.in);
        player2Name = player2.nextLine();
        turn = true;    //플레이어1 턴

- *(스코어 계산)* 만약 플레이어가 처음으로 점수를 얻을 경우 10점을 얻고 계속해서 카드를 맞출경우 for루프를 이용해서 스코어를 계속해서 두 배로 늘려서 점수를 부여한다.


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
        }
        scoreRound++;   //플레이어가 연속해서 플레이한 횟수

- *(턴 변경)* 플레이어가 카드를 못 맞출경우 turnChanger 메서드를 실행 하여 상대턴으로 넘어가게된다. 턴이 넘어갈때 플레이한 횟수를 초기화한다.


        turn = !turn;
        scoreRound = 0;

- *(게임 종료)* 게임 종료시 두 플레이어의 점수를 비교하여 더 높은 쪽이 승리하게 된다. 두 플레이어의 시도한 횟수와 점수를 화면에 표시한다


        if (player1Score == player2Score){
            System.out.println("무승부 플레이어간의 점수가 같습니다");
        }else if(player1Score > player2Score){
            System.out.println(player1Name + " 승리!!!");
        }else{
            System.out.println(player2Name + " 승리!!!");
        }
        System.out.println(player1Name + " 시도 횟수: "+ numTry + " 점수: " + player1Score);
        System.out.println(player2Name + " 시도 횟수: "+ numTry2 + " 점수: " + player2Score);

## #️⃣[3단계] GUI로 구현하기#️⃣
### 💡요구사항💡
#### 기능
- 각 언어에서 제공하는 GUI 도구를 활용하여 게임을 GUI로 구현한다.
- 카드를 숫자 대신 적절한 이미지로 대처한다.
### 🏃동작 예시🏃
게임 실행 시 메인 화면

<img src = "./img/main.png">

두 플레이어의 이름을 입력후 Start Game 버튼을 누른후 게임 화면

<img src = "./img/game1.png"/>

플레이어가 고른 두 카드가 같지 않은 경우

<img src = "./img/game2.png"/>

플레이어가 고른 두 카드가 같은 경우

<img src = "./img/game4.png"/>

필드위의 같은 카드를 모두 찾아서 게임 종료

<img src = "./img/game5.png"/>

### 🔎단계별 풀이 및 코드 설명🔎
#### (메인 메뉴 UI 설정) 간단한 UI 코드작성

1. 프레임 제목, 크기, 위치 지정


        setTitle("CardMatchingGame");
        setSize(400,400);
        setLocationRelativeTo(null);

2. 3개의 패널을 생성


        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel(new GridLayout(2,2,10,10));
        JPanel panel3=new JPanel();
        
3. 각각의 패널에 JLabel,JTextField,JButton을 추가
        

        panel1.add(titileLabel);
        panel2.add(name1Label);
        panel2.add(name1TextField);
        panel3.add(startButton);

4. JFrame에 3개의 패널을 추가
        

        this.add(panel1,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.CENTER);
        this.add(panel3,BorderLayout.SOUTH);

5. 화면 출력 및 종료


        close 버튼을 누를시 프로그램 종료
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        화면에 보이게 바꿔준다
        setVisible(true);


6. 버튼을 누르면 플레이어가 입력한 이름을 게임화면으로 받아온다.


        버튼에 액션 리스너 추가
        startBtn.addActionListener(this);

        버튼을 누를시 이름들을 JTextField로 부터 받아오고 게임화면을 실행한다 그리고 메인 화면을 종료한다.
        @Override
        public void actionPerformed(ActionEvent e) {
            name1 = name1TF.getText();
            name2 = name2TF.getText();
            new GameGUI(name1,name2).f.setVisible(true);
            this.dispose();
        }

#### (게임 화면의 구성) 카드들이 저장되어 있는 버튼들과 다른 UI들의 출력

1. 게임에 필요한 버튼을 보여주는 panel1과 레이블을 출력하는 panel2 생성


        JButton[][] btn = new JButton[col][row];
        
        버튼 array에 버튼들을 넣어준다.
        for(int i = 0; i<col;i++) {
            for(int j = 0;j<row;j++) {
                btn[i][j] = new JButton();
            }
        }
        
        만들어진 버튼들을 패널 안에 넣어준다.
        for(int i = 0;i<col;i++) {
            for (int j = 0 ;j<row;j++) {
                panel1.add(btn[i][j]);
                btn[i][j].addActionListener(this);
            }
        }
        
        패널2에는 플레이어에게 필요한 정보를 나타내는 JLabel을 넣어준다.
        nameL = new JLabel(name1 + "의 턴 점수: " + player1Score);
        scoreL = new JLabel("점수표 "+name1+": "+player1Score+" "+name2+": "+player2Score);
        panel2.add(nameL);
        panel2.add(scoreL);

2. 뒤집힌 카드 이미지들을 버튼들안에 넣어준다.


        for(int i=0 ; i<col;i++) {
            for(int j=0;j<row;j++) {
                btn[i][j].setIcon(new ImageIcon ("./image/back.png"));
            }

        }

#### (게임 플레이 로직) 게임 플레이간의 사용되는 코드

1. 초기 카드 설정 (카드를 섞고 섞인 값을 array에 저장)


        1,2 단계와 마찬가지로 array를 list로 바꿔서 섞어준다.
        List<Integer> deckList = Arrays.asList(cardDeck);
        Collections.shuffle(deckList);
        deckList.toArray(cardDeck);

        섞인 카드들을 cardAnswer안에 순서대로 넣어준다.
        int count = 0;
        for (int i=0; i<col; i++){
            for (int j=0; j<row; j++){
                cardAnswer[i][j]=cardDeck[count];
                count++;
            }
        }

2.  버튼 액션 이벤트 추가 (카드 클릭시 그카드의 좌표에 있는 카드를 공개)


        JButton b = (JButton)e.getSource();
        winnerMessage();                                                                 //게임이 끝날 경우 메세지 출력
        for(int i=0 ; i<col;i++) {
            for(int j=0;j<row;j++) {
                if(b == btn[i][j]) {
                    if(firstButton != null && secondButton != null){                     //두카드가 다를경우 카드 뒤집기
                        firstButton.setIcon(new ImageIcon("./image/back.png"));
                        secondButton.setIcon(new ImageIcon("./image/back.png"));
                        firstButton = null;
                        secondButton = null;
                    } else if(firstButton == null && secondButton == null) {             //첫번째 카드를 공개
                        firstButton = b;
                        y = i;
                        x = j;
                        firstButton.setIcon(new ImageIcon("./image/0" + cardAnswer[y][x] + ".png"));

                    }else if(firstButton != null && firstButton!=b){                     //두번째 카드를 공개
                        secondButton = b;
                        y2 = i;
                        x2 = j;
                        secondButton.setIcon(new ImageIcon("./image/0" + cardAnswer[y2][x2] + ".png"));
                        getPoint();                                                      //두카드가 같을 경우 점수를 얻음
                    }
                }
            }
        }

3. 점수 계산 (1,2 단계와 마찬가지로 첫번째 맞추면 10점을 계속해서 맞출경우 두배의 점수를 부여)


        getPoint 메서드 첫번째 카드와 두번째 카드의 좌표값을 가져와서 cardAnswer array에 대입해보고
        값이 같을 경우 점수를 부여한다.
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

        2 단계에서 가져온 누구의 턴인지 체크후 그플레이어에게 점수를 부여하는 메서드를 사용한다.
        public void scoreCalculate()

4. UI 업데이트 (턴이 지남에 따라 JLabel을 업데이트 해준다.)


        labelChange메서드 턴을 확인 후 그에대한 label수정
        if (!turn){
            nameL.setText(name1 + "의 턴 점수: " + player1Score);
        }else{
            nameL.setText(name2 + "의 턴 점수: " + player2Score);
        }
        scoreL.setText(("점수표: "+name1+": "+player1Score+" "+name2+": "+player2Score));

5. 승리조건과 메세지 출력 (카드를 선택 할때마다 승리조건을 체크하고 게임이 끝날경우 그에맞는 메세지를 보여준다.)


        2 단계에서 가져온 cardAnswer에서 같은 카드가 남지 않은 경우 참값을 반환하는 메서드를 사용한다.
        public boolean winCondition()

        만약 winCondition()이 참 값을 반환하면 플레이어간의 점수를 비교하고 그에 맞는 메세지를 츨력한다.
        if (winCondition()){
            if (player1Score > player2Score){
                JOptionPane.showMessageDialog(null,name1+"님이 승리했습니다.\n"+name1+"의 점수: "+player1Score+"\n"+name2+"의 점수: "+player2Score);
            }else if(player2Score > player1Score){
                JOptionPane.showMessageDialog(null,name2+"님이 승리했습니다.\n"+name1+"의 점수: "+player1Score+"\n"+name2+"의 점수: "+player2Score);
            }else {
                JOptionPane.showMessageDialog(null,"무승부입니다.\n"+name1+"의 점수: "+player1Score+"\n"+name2+"의 점수: "+player2Score);
            }
        }