import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JButton[][] button = new JButton[8][8]; //버튼
    private JLabel[][] labels = new JLabel[8][8]; //버튼 위에 바둑돌 사진
    private char[][] board = new char[8][8]; // 게임 보드 상태를 저장하는 2차원 배열
    private char currentPlayer = 'B'; // 게임 턴 - 흑인지 백인지

    // 바둑돌 사진
    private final String blackImagePath = "/Users/leeyunji/Desktop/2024_SummerStudy/Project6_OthelloGame/src/Image/black.png";
    private final String whiteImagePath = "/Users/leeyunji/Desktop/2024_SummerStudy/Project6_OthelloGame/src/Image/white.png";

    public Main() {

    // 프레임 초기 설정--------------------------------------------------------------------------------
        setSize(1000, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // 배경 이미지 패널 설정
        JPanel ImagePanel = new JPanel() {
            Image background = new ImageIcon(getClass().getResource("/Image/Othello.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        ImagePanel.setLayout(null);
        ImagePanel.setBounds(0, 0, 1000, 750);
        add(ImagePanel);


        // 게임 시작 버튼
        JButton gameStart = new JButton("게임시작");
        gameStart.setBounds(845, 20, 100, 35);
        ImagePanel.add(gameStart);

        //버튼을 누르면 사진이 올려지도록
        gameStart.addActionListener(e -> initializeBoard());

        // 끝내기 버튼
        JButton finish = new JButton("끝내기");
        ImagePanel.add(finish);
        finish.setBounds(845, 60, 100, 35);

        // 흑&백 돌 버튼 설정--------------------------------------------------------------------------------------------
        int x = 197, y = 126; //버튼 생성 첫 좌표

        for (int i = 0; i < 8; i++) { //가로로 8개
            for (int j = 0; j < 8; j++) { //세로로 8개
                button[i][j] = new JButton();
                button[i][j].setBounds(x, y, 78, 75);
                button[i][j].setBorderPainted(false); //버튼 투명하게 만들기
                button[i][j].setContentAreaFilled(false); //버튼 투명하게 만들기

                //바둑돌 그림 올릴 레이블
                labels[i][j] = new JLabel();
                labels[i][j].setBounds(x+10, y, 78, 75); //바둑돌그림 위치선정 (버튼보다 오른쪽을 10정도 더 옆으러..)
                ImagePanel.add(labels[i][j]);


                //클릭하는 곳의 인덱스
                int clickX = i;
                int clickY = j;

                //버튼을 누르면 사진이 올려지도록
                button[i][j].addActionListener(e -> handleButtonClick(clickX, clickY));
                ImagePanel.add(button[i][j]);
                x += 73;
            }
            x = 198; //x좌표 초기화
            y += 70;

        }
        // 초기 보드 상태 설정
        initializeBoard();

    } //Main()


    //바둑돌 이미지사이즈 조절하기 (버튼 크기랑 안맞음)
    private ImageIcon resizeIcon(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width-13, height-13, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    //게임의 가장 초기상태 -> 가운데 흰2 검2
    private void initializeBoard() {
        // 보드 초기화
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = ' ';
            }
        }
        // 초기 돌 4개 가운데 세팅
        board[3][3] = 'W';
        board[3][4] = 'B';
        board[4][3] = 'B';
        board[4][4] = 'W';
        currentPlayer = 'B';
        turnOver(); // 돌 뒤집기
        }


    //바둑돌 뒤집기
    private void turnOver() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'B') { //보드게임상태가 블랙턴이면 검정 바둑돌 사진을 해당 인덱스에 올리기
                    labels[i][j].setIcon(resizeIcon(blackImagePath, labels[i][j].getWidth()-5, labels[i][j].getHeight()));
                } else if (board[i][j] == 'W') { //보드게임상태가 화이트 턴이면 검정 바둑돌 사진을 해당 인덱스에 올리기
                    labels[i][j].setIcon(resizeIcon(whiteImagePath, labels[i][j].getWidth()-5, labels[i][j].getHeight()));
                } else {
                    labels[i][j].setIcon(null);
                }
            }
        }
    }


    private void handleButtonClick(int i, int j) {
        // 유효한 이동인지 확인하고 돌을 놓는 로직 추가
        if (isValidMove(i, j, currentPlayer)) {
            placeDisc(i, j, currentPlayer);

            // 현재 플레이어 턴 넘기기
            currentPlayer = (currentPlayer == 'B') ? 'W' : 'B';
            turnOver(); // 돌 뒤집기

            // 게임 종료 조건 검사
            if (isGameOver()) {
                announceWinner();
            }
        }
    }

    private boolean isValidMove(int row, int col, char player) {
        // 유효한 이동인지 확인하는 로직 추가
        if (board[row][col] != ' ') return false;
        //  유효한 이동인지 확인하는 자세한 로직 추가
        return true;
    }

    private void placeDisc(int row, int col, char player) {
        // 돌을 놓고 뒤집기
        board[row][col] = player;
        //  돌을 뒤집는 자세한 로직 추가
    }

    // 게임 종료 조건
    private boolean isGameOver() {
        //  게임 종료 조건
        return false;
    }

    private void announceWinner() {
        // 승자 발표 로직 추가
        JOptionPane.showMessageDialog(this, "게임 종료");
    }

    public static void main(String[] args) {
        new Main();
    }
}
