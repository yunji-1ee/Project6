import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public Main() {

        super("게임하기"); //타이틀

        // 벼경에 이미지 넣기----------------------------------------------------------------------
        JPanel ImagePanel = new JPanel() {
            Image background = new ImageIcon(getClass().getResource("/Image/Othello.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        ImagePanel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
        ImagePanel.setBackground(Color.YELLOW);
        ImagePanel.setBounds(0, 0, 1000, 750); // 위치와 크기 설정
        add(ImagePanel);

        //프레임 초기설정---------------------------------------------------------------------

        setSize(1000, 750); //창 크기 설정

        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width-100) ,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);

        // 게임시작 버튼----------------------------------------------------------------------

        JButton gameStart = new JButton("게임시작");
        gameStart.setBounds(845, 20, 100, 35);
        ImagePanel.add(gameStart);


        // 끝내기 버튼----------------------------------------------------------------------

        JButton finish = new JButton("끝내기");

        ImagePanel.add(finish);
        finish.setBounds(845, 60, 100, 35);
/*
        //로그인버튼 리스너 -----------------------------------------------------------
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                setVisible(false); // 창 안보이게 하기!
            }
        });

        //회원가입버튼 리스너 -----------------------------------------------------------
        join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Join().setVisible(true);
                setVisible(false); // 창 안보이게 하기
            }
        });
        */
//흑백 돌 버튼 넣기--------------------------------------------------------------------------
        int i = 0;
        int j = 0;
        int x = 197 ,y = 126 ;
        JButton button[][] = new JButton[8][8];

        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                button[i][j] = new JButton();
                button[i][j].setBounds(x , y , 78, 75);
                button[i][j].setBorderPainted(false); //버튼 투명하게 하기
                button[i][j].setContentAreaFilled(false); //버튼 투명하게 하기
                ImagePanel.add(button[i][j]);


                x+=73;
            } //j반복믄
            x=197; // x값 초기화
            y+=70;
        } //i 반복문
    }

    ImageIcon img = new ImageIcon("/Users/leeyunji/Desktop/2024_SummerStudy/Project6/src/Image/black.png");
    ImageIcon img2 = new ImageIcon("/Users/leeyunji/Desktop/2024_SummerStudy/Project6/src/Image/black.png");




    //몇대 몇 띄우기 --------------------------------------------------------
    JLabel score_player1 = new JLabel("0");
    JLabel score_player2 = new JLabel("0");

    //현재 자신의 색 돌 수
    JLabel mystone = new JLabel("0");






    public static void main(String[] args) {
        new Main();
    }
}

