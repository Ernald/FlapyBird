package flapybird;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Flapybird implements ActionListener,MouseListener,KeyListener {
 public static Flapybird flapybird;
 public final int Width=800,Height=800; 
 public Renderer renderer;
 public Rectangle bird;
 public int tick,yMotion,score;
 public ArrayList<Rectangle> columns;
 boolean gameOver,started;
 public Random rand;
 
 public Flapybird() {
	 
	 JFrame frame =new JFrame();
	 Timer timer=new Timer(20,(ActionListener) this);
	
	 renderer = new Renderer();
	 rand=new Random();
	 
	 frame.setTitle("Flappy Bird");
	 frame.add(renderer);
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frame.addMouseListener(this);
	 frame.addKeyListener(this);
	 frame.setSize(Width, Height);
	 frame.setResizable(false);
	 frame.setVisible(true);
	
	 
	 bird=new Rectangle(Width/2-10,Height/2-10,20,20);
	 columns=new ArrayList<Rectangle>();
	 
	 addColumn(true);
	 addColumn(true);
	 addColumn(true);
	 addColumn(true);
	 
	 timer.start();
 }
 
 
 
public void addColumn(boolean start) {
	int space=300;
	int width=100;
	int height=50+rand.nextInt(300);
	
	if(start) {
	columns.add(new Rectangle(Width+width+columns.size()*300,Height-height-120,width,height));
	columns.add(new Rectangle(Width+width+(columns.size()-1)*300,0,width,Height-height-space));
	}else {
		columns.add(new Rectangle(columns.get(columns.size()-1).x +600,Height-height-120,width,height));
		columns.add(new Rectangle(columns.get(columns.size()-1).x ,0,width,Height-height-space));
	}
}

 public void paintColumn(Graphics g,Rectangle column) {
	 g.setColor(Color.green.darker());
	 g.fillRect(column.x, column.y, column.width, column.height);
 }
 
 public void jump() {
	 if(gameOver) {
		 
		 bird=new Rectangle(Width/2-10,Height/2-10,20,20);
		 columns.clear();
		 yMotion=0;
		 
		 addColumn(true);
		 addColumn(true);
		 addColumn(true);
		 addColumn(true);
		 
		 gameOver=false;
	 }
	 if(!started) {
		 started=true;
	 }else if(!gameOver) {
		 if(yMotion>0) {
			 yMotion=0;
		 }
		 yMotion-=10;
	 }
 }
 
 @Override
 public void actionPerformed(ActionEvent e) {
	 int speed=6;
	 tick++;
	 for(int i=0;i<columns.size();i++) {
		 Rectangle column=columns.get(i);
		 column.x-=speed;	 
	 }
	 
	 if(tick%2==0 && yMotion<14) {
		yMotion+=2; 
	 }
	 
	 for(int i=0;i<columns.size();i++) {
		 Rectangle column=columns.get(i);
		 if(column.x+column.width<0) {
			 columns.remove(column);
			 if(column.y==0) {
				 addColumn(false);
			 }
		 }
	 }
	 
	 
	 bird.y+=yMotion;
	 
	 for(Rectangle column:columns) {
		 
		 if(column.y ==0 && bird.x+bird.width/2>column.x+column.width/2-10 && bird.x+bird.width/2<column.x+column.width/2+10) {
			score++;
		 }
		 
		 
		 if(column.intersects(bird)) {
			 gameOver=true;
			 bird.x=column.x-bird.width;
		 } 
	 }
	 
	 if(bird.y>Height -120 || bird.y<0) {
		 gameOver=true;
	 }
	 if(bird.y+yMotion>=Height-120) {
		 bird.y=Height-120-bird.height;
	 }
	 
	 renderer.repaint();
 }
 
 public void repaint(Graphics g) {
	g.setColor(Color.blue);
	g.fillRect(0, 0, Width, Height);
	
	g.setColor(Color.orange);
	g.fillRect(0, Height-120, Width, 120);
	
	g.setColor(Color.green);
	g.fillRect(0, Height-120, Width, 20);
	
	g.setColor(Color.red);
	g.fillRect(bird.x, bird.y, bird.width, bird.height);
	
	for(Rectangle column : columns) {
		paintColumn(g,column);
	}
	

	g.setColor(Color.white);
	g.setFont(new Font("Arial",1,100));
	
	if(!started) {
		g.drawString("Click to Start !",100,Height/2-50);
	}
	
	if(gameOver) {
		g.drawString("Game Over",100,Height/2-50);
	}
	if(!gameOver && started) {
		g.drawString(String.valueOf(score),Width/2-25,100);
	}
 }
 
  public static void main(String[]args) {
	flapybird =new Flapybird(); 
 }

@Override
public void mouseClicked(MouseEvent arg0) {
    jump();
}

@Override
public void keyReleased(KeyEvent e) {
	if(e.getKeyCode()==KeyEvent.VK_SPACE) {
		 jump();
}}

@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}



@Override
public void keyPressed(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}



@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}



@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}



@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}



@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

}
