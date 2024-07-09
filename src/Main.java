import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JButton[][] button = new JButton[8][8];
    private JLabel[][] labels = new JLabel[8][8];
    private char[][] board = new char[8][8]; // 게임 보드 상태를 저장하는 2차원 배열
    private char currentPlayer = 'B'; // 현재 플레이어 ('B'는 흑, 'W'는 백)

    // 이미지 파일 경로를 지정
    private final String blackImagePath = "/Users/leeyunji/Desktop/2024_SummerStudy/Project6_OthelloGame/src/Image/black.png";
    private final String whiteImagePath = "/Users/leeyunji/Desktop/2024_SummerStudy/Project6_OthelloGame/src/Image/white.png";

    public Main() {
        super("게임하기");

        // 배경 이미지 패널 설정--------------------------------------------------------------------
        JPanel ImagePanel = new JPanel() {
            Image background = new ImageIcon(getClass().getResource("/Image/Othello.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        ImagePanel.setLayout(null);
        ImagePanel.setBackground(Color.YELLOW);
        ImagePanel.setBounds(0, 0, 1000, 750);
        add(ImagePanel);

        // 프레임 초기 설정--------------------------------------------------------------------------------
        setSize(1000, 750);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width - 100), (windowSize.height - frameSize.height) / 2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // 게임 시작 버튼
        JButton gameStart = new JButton("게임시작");
        gameStart.setBounds(845, 20, 100, 35);
        ImagePanel.add(gameStart);

        // 끝내기 버튼
        JButton finish = new JButton("끝내기");
        ImagePanel.add(finish);
        finish.setBounds(845, 60, 100, 35);

        // 흑백 돌 버튼 설정
        int x = 197, y = 126;
        int x1 = 205;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                button[i][j] = new JButton();
                button[i][j].setBounds(x, y, 78, 75);
                button[i][j].setBorderPainted(false); //버튼 투명하게 만들기
                button[i][j].setContentAreaFilled(false); //버튼 투명하게 만들기

                //바둑돌 그림 올릴 레이블
                labels[i][j] = new JLabel();
                labels[i][j].setBounds(x+10, y, 78, 75); //바둑돌그림 위치선정
                ImagePanel.add(labels[i][j]);

                int finalI = i;
                int finalJ = j;
                button[i][j].addActionListener(e -> handleButtonClick(finalI, finalJ));
                ImagePanel.add(button[i][j]);
                x += 73;
            }
            x = 198;
            y += 70;

        }
        // 초기 보드 상태 설정
        initializeBoard();
    }

    private void initializeBoard() {
        // 보드 초기화
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = ' ';
            }
        }
        // 초기 돌 배치
        board[3][3] = 'W';
        board[3][4] = 'B';
        board[4][3] = 'B';
        board[4][4] = 'W';
        updateBoard();
    }

    private void updateBoard() {
        // 보드 상태 업데이트
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'B') {
                    labels[i][j].setIcon(resizeIcon(blackImagePath, labels[i][j].getWidth()-5, labels[i][j].getHeight()));
                } else if (board[i][j] == 'W') {
                    labels[i][j].setIcon(resizeIcon(whiteImagePath, labels[i][j].getWidth()-5, labels[i][j].getHeight()));
                } else {
                    labels[i][j].setIcon(null);
                }
            }
        }
    }

    private ImageIcon resizeIcon(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width-13, height-13, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    private void handleButtonClick(int i, int j) {
        // 유효한 이동인지 확인하고 돌을 놓는 로직 추가
        if (isValidMove(i, j, currentPlayer)) {
            placeDisc(i, j, currentPlayer);
            currentPlayer = (currentPlayer == 'B') ? 'W' : 'B';
            updateBoard();
            // 게임 종료 조건 검사
            if (isGameOver()) {
                announceWinner();
            }
        }
    }

    private boolean isValidMove(int row, int col, char player) {
        // 유효한 이동인지 확인하는 로직 추가
        if (board[row][col] != ' ') return false;
        // 여기에 유효한 이동인지 확인하는 자세한 로직 추가
        return true;
    }

    private void placeDisc(int row, int col, char player) {
        // 돌을 놓고 뒤집는 로직 추가
        board[row][col] = player;
        // 여기에 돌을 뒤집는 자세한 로직 추가
    }

    private boolean isGameOver() {
        // 게임 종료 조건 검사 로직 추가
        // 여기에 게임 종료 조건 검사 로직 추가
        return false;
    }

    private void announceWinner() {
        // 승자 발표 로직 추가
        JOptionPane.showMessageDialog(this, "게임 종료! 승자는...");
    }

    public static void main(String[] args) {
        new Main();
    }
}
