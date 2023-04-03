import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameFrame extends JFrame implements ActionListener {
    public int WIDTH = 900;
    public int HEIGHT = 900;

    //pig position
    public int pigX = 0;
    public int pigY= 0;

    //injection position
    public int injectionX = 400;
    public int injectionY = 600;

    //functional library
    Timer timer;
    Random random;
    boolean running = false;

    Image pig;
    ImageIcon injection;
    JLabel injectionLabel;
    public
    GameFrame(){
        injection = new ImageIcon("src\\injection.png");
        injectionLabel = new JLabel();
        injectionLabel.setIcon(injection);
        injectionLabel.setBounds(injectionX,injectionY,100,200);

        this.add(injectionLabel);
        this.addKeyListener(new MyKeyAdapter());
        this.setTitle("Injection Pig");
        this.setSize(WIDTH,HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);

        pig = new ImageIcon("src\\pig.png").getImage();
        startGame();
    }
    public void startGame(){
        random = new Random();

        timer = new Timer(1000,this);
        timer.start();

        newPig();
        running = true;
    }
    public void newPig(){
        pigY= 0;
        pigX = random.nextInt((int)(WIDTH/100))*100;
    }
    public void movePig(){

        pigY = pigY + 50;
    }

    public void checkCollision(){
        int collisionX = injectionLabel.getY();
        if(collisionX == pigY){
            newPig();
        }
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(pig,pigX,pigY,null);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            movePig();
            checkCollision();
        }
        repaint();

    }
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e){
            switch(e.getKeyChar()){
                case 'a': injectionLabel.setLocation(injectionLabel.getX()-50,injectionLabel.getY());
                break;
                case 'd': injectionLabel.setLocation(injectionLabel.getX()+50,injectionLabel.getY());
                break;
            }
        }
    }
}
