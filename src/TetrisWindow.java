/*
  CSCI282 TetrisPart3: TetrisWindow Class
  Tolu Kuseju and Yvonne Ariri
  5/1/2023
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;

public class TetrisWindow extends JFrame implements ActionListener
{  
    private int winwidth = 500;
    private int win_height = 500 ;
    private int game_rows = 20;
    private int game_cols = 12;
    private TetrisGame game;
    JLabel scoreLabel = new JLabel();
    private TetrisDisplay display;
    
    public TetrisWindow()
    {
        this.setTitle("My Tetris Game          Tolu and Yvonne"); 
        this.setSize(winwidth, win_height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        game = new TetrisGame(game_rows, game_cols); 
        
        
        display = new TetrisDisplay(game);
        this.add(display);
        initMenus();
        
             
        this.setVisible(true);
    }
    
    public void initMenus()
    {
       // this.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        //menuBar.setBackground(Color.black);

        this.setJMenuBar(menuBar);
        JMenu menu1 = new JMenu("Score");

        JMenuItem item1 = new JMenuItem("Leader Board");

        JMenuItem item2 = new JMenuItem("Delete high score");   
        menu1.add(item1);
        menu1.addSeparator();   
        menu1.add(item2);
        item1.addActionListener(this);
        item2.addActionListener(this);


        JMenu menu2 = new JMenu("Game");


        JMenuItem item3 = new JMenuItem("New Game"); 
        JMenuItem item4 = new JMenuItem("Save Game"); 
        JMenuItem item5 = new JMenuItem("Retrieve Game"); 

        item3.addActionListener(this);
        item4.addActionListener(this);
        item5.addActionListener(this);

        menu2.add(item3);
        menu2.addSeparator();
        menu2.add(item4);
        menu2.addSeparator();
        menu2.add(item5);

        menuBar.add(menu1);
        menuBar.add(menu2);


        JPanel northPan = new JPanel();

        scoreLabel = game.getScoreLabel();
        int med_font_size = 20;
        Font font = new Font("Arial", 1, med_font_size);
        scoreLabel.setFont(font);        
        northPan.add(scoreLabel, northPan);

        this.add(northPan, BorderLayout.WEST);                    
    }

    public void actionPerformed(ActionEvent ae)
    {
        Object clickedObject = ae.getSource();
        if(clickedObject instanceof JMenuItem)
        {
            JMenuItem clickItem = (JMenuItem)clickedObject;

            if (clickItem.getText().equals("Leader Board"))
            {
                display.getTimer().stop();
                JOptionPane.showMessageDialog(null, "help!");
            }

            if(clickItem.getText().equals("Delete high score"))
            {
                display.getTimer().stop();
                String[] options = {"Yes", "No"};
                int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to delete this file?", 
                        "Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        options, options[1]);
                if (choice == 0)
                    game.deleteHighScores();
                else 
                    JOptionPane.showMessageDialog(null, "Deletion has been cancelled.");
            }

            if(clickItem.getText().equals("New Game"))
            {
                game.newGame();  
            }

            if(clickItem.getText().equals("Save Game"))
            {
                display.getTimer().stop();
                String fileName = JOptionPane.showInputDialog("What do you want to name your game?");                    
                game.saveToFile(fileName);
            }

            if(clickItem.getText().equals("Retrieve Game"))
            {
                display.getTimer().stop();                
                game.retrieveFromFile();
                display.repaint();
            }
        }
    }
    
    public String updateScoreLabel() 
    {
        String scoreText = "Score: " + game.getScore();
        return scoreText;
    }

    public static void main(String[] args)
    {        
        TetrisWindow aw = new TetrisWindow();        
    }
}  