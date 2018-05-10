// Hard_Level.java : Defines the hard level for the java application.
// AI Puzzle: Level based of player's mood
// Gameplay features: Collision Detection, AI based level 
// Author: Shweta Patil
// Copyright: Shweta Patil © 2018

package Mood_Puzzle;

import static Mood_Puzzle.Easy_Level.frame;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;

/**
 *
 * @author Shweta
 */
public class Hard_Level extends Canvas implements MouseListener, MouseMotionListener{
    //Constructor
    public Hard_Level(){
        setBackground(Color.white);
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    
    //Draw elements
    Rectangle rect = new Rectangle(50, 50, 30, 50);
    Rectangle goal = new Rectangle(300, 300, 200, 200);
    Rectangle obstacle1 = new Rectangle(200, 350, 70, 70);
    Rectangle obstacle2 = new Rectangle(350, 200, 90, 90);
    Rectangle obstacle3 = new Rectangle(220, 300, 40, 40);
    Rectangle obstacle4 = new Rectangle(330, 200, 20, 90);
    Rectangle obstacle5 = new Rectangle(240, 265, 20, 20);
    Rectangle obstacle6 = new Rectangle(300, 200, 20, 90);

    //Intialize
    Graphics2D g2;
    int X, Y;
    boolean isFirstTime = true;
    Rectangle area;
    boolean pressOut = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        X = rect.x - e.getX();
        Y = rect.y - e.getY();

        if (rect.contains(e.getX(), e.getY()))
          updateLocation(e);
        else {
          pressOut = true;
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (rect.contains(e.getX(), e.getY()))
        updateLocation(e);
        else {
        pressOut = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (!pressOut)
            updateLocation(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void updateLocation(MouseEvent e) {
        rect.setLocation(X + e.getX(), Y + e.getY());
        repaint();
  }
    
    @Override
    public void paint (Graphics graphics){
        update(graphics);
    }
    
    public void update(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Dimension dim = getSize();
        int w = (int) dim.getWidth();
        int h = (int) dim.getHeight();
        g2.setStroke(new BasicStroke(8.0f));

        if (isFirstTime) {
          area = new Rectangle(dim);
          rect.setLocation(w / 2 - 50, h / 2 - 25);
          isFirstTime = false;
        }

        //Clear the rectangle that was previously drawn.
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, w, h);

        //Player
        g2.setColor(Color.gray);
        g2.draw(rect);
        g2.setColor(Color.orange);
        g2.fill(rect);
    
        //Draw theme
        //Goal
        g2.setColor(Color.darkGray);
        g2.draw(goal);
        g2.setColor(Color.red);
        g2.fill(goal);

        //Obastacles
        g2.setColor(Color.LIGHT_GRAY);
        g2.draw(obstacle1);
    
        g2.setColor(Color.LIGHT_GRAY);
        g2.draw(obstacle2);

        g2.setColor(Color.GRAY);
        g2.draw(obstacle3);

        g2.setColor(Color.GRAY);
        g2.draw(obstacle4);

        g2.setColor(Color.DARK_GRAY);
        g2.draw(obstacle5);
    
        g2.setColor(Color.DARK_GRAY);
        g2.draw(obstacle6);
    
        //Check losing condition
        if (checkLoose())
        { 
            System.out.println("Returned lost");
            frame.setVisible(false);
            new Lose().setVisible(true);
        }

        //Check winning condition
        if (checkWin())
        { 
            System.out.println("Returned won");
            frame.setVisible(false);
            new Win().setVisible(true);
        }
    
    }
    
    boolean checkLoose(){
        if(obstacle1.intersects(rect) || obstacle2.intersects(rect) || 
        obstacle3.intersects(rect) || obstacle4.intersects(rect) || 
        obstacle5.intersects(rect) || obstacle6.intersects(rect))
        {
            System.out.println("Lost!!!");
            this.setEnabled(false);
            return true;
        }
        return false;
    }
  
   boolean checkWin(){
        if(goal.contains(rect))
        {
            System.out.println("Won!!!");
            this.setEnabled(false);
            return true;
        }
        return false;
        }

       boolean checkRect() {
        if (area == null) {
          return false;
        }

        if (area.contains(rect.x, rect.y, 100, 50)) {
          return true;
        }

        int new_x = rect.x;
        int new_y = rect.y;

        if ((rect.x + 100) > area.getWidth()) {
          new_x = (int) area.getWidth() - 99;
        }
        if (rect.x < 0) {
          new_x = -1;
        }
        if ((rect.y + 50) > area.getHeight()) {
          new_y = (int) area.getHeight() - 49;
        }
        if (rect.y < 0) {
          new_y = -1;
        }

        rect.setLocation(new_x, new_y);
        return false;
  }
   
   //Create new Window
    static JFrame frame = new JFrame();
        
   public static void main(String[] args) {
        //Set up the drawing canvas
        Hard_Level canvas = new Hard_Level();
        //Set Window Size
        frame.setSize(500, 500);
        //Disable window resizze option
        frame.setResizable(false);
        //Set frame at the center
        frame.setLocationRelativeTo(null);
        //Make Sure Program Ends when Window Exit Button is Clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Draw to Canvas
        frame.getContentPane().add(canvas);
        //Show Window
        frame.setVisible(true);
    }
}
