/*
  CSCI282 TetrisPart3: TetrisDisplay Class
  Tolu Kuseju and Yvonne Ariri
  5/1/2023
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisDisplay extends JPanel
{
    private TetrisGame game;
    private int start_x = 100;
    private int start_y = 100;
    private int cell_size = 15;
    private int speed = 300;
    private Timer timer;
    private boolean pause = true;
    private Color[] colors = {Color.BLACK, Color.BLUE, Color.ORANGE, Color.GREEN, Color.MAGENTA,
                                Color.PINK, Color.RED, Color.YELLOW};
    
    public TetrisDisplay(TetrisGame ga)
    {
        game = ga;
        timer = new Timer(speed, new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                cycleMove();
                repaint();             
            }
        });
        
        timer.start();
        
        this.addKeyListener( new KeyAdapter()
        {
            public void keyPressed(KeyEvent ke)
            {
               translateKey(ke);
            }
        });
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }        
    public void translateKey(KeyEvent ke)                                
    {
        int code = ke.getKeyCode();
        switch(code)
        {
        case KeyEvent.VK_UP:
            game.makeMove('R');
            break;
        case KeyEvent.VK_DOWN:
            game.makeMove('E');
            break;
        case KeyEvent.VK_RIGHT:
            game.makeMove('W');
            break;
       case KeyEvent.VK_LEFT:
            game.makeMove('L');
            break;
        case KeyEvent.VK_SPACE:
            isPaused();
            break;
        case KeyEvent.VK_N:
            game.makeMove('N');
            getTimer().start();
            break;         
        }
        repaint();
    }
    
    private void isPaused()
    {
        if(getTimer().isRunning() == pause)
            getTimer().stop();
        else
            getTimer().start();
    }   
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawWell(g);
        drawBackground(g);
        drawBrick(g);
        if(game.stopGame())
        {
            isPaused();
            drawGameOver(g);
        }
          
        
    }
    
    public void drawWell(Graphics r)
    {   
        r.setColor(Color.WHITE);
        r.fillRect(start_x, start_y, cell_size* game.getCols(), cell_size*game.getRows());
        r.setColor(colors[0]);
        r.drawRect(start_x, start_y, cell_size * game.getCols(), 0 );
        r.fillRect (start_x-cell_size , start_y , cell_size, game.getRows()*cell_size +cell_size );
        r.fillRect(start_x - cell_size , start_y +game.getRows()*cell_size , cell_size * game.getCols() + cell_size, cell_size);
        r.fillRect(start_x  +game.getCols()*cell_size, start_y, cell_size, cell_size + cell_size*game.getRows());   
    }
    
    private void drawBrick(Graphics g)
    {
        for(int seg = 0; seg < game.getNumSegs(); seg++)
        {
            g.setColor(colors[game.getFallingbrickColor()]);
            g.fillRect(start_x - cell_size + game.getSegCol(seg)*cell_size, start_y + game.getSegRow(seg)*cell_size, cell_size, cell_size);
            g.setColor(colors[0]);
            g.drawRect(start_x -cell_size+ game.getSegCol(seg)*cell_size, start_y + game.getSegRow(seg)*cell_size, cell_size, cell_size);
        }
    }
    
    public void drawBackground(Graphics g)
    {   
        for (int seg = 0; seg < game.getNumSegs(); seg++)
        {
            for (int row = 0; row < game.getRows(); row++)
            {
                for(int col = 0; col < game.getCols(); col++)
                {                
                    if (game.fetchBoardPosition(row, col) != 0)
                    {
                       g.setColor(colors[game.fetchBoardPosition(row, col)]);
                        g.fillRect(start_x+col*cell_size, start_y+row*cell_size, cell_size, cell_size);
                        g.setColor(colors[0]);
                        g.drawRect(start_x+col*cell_size, start_y+row*cell_size, cell_size, cell_size);
                    }
                }
            }
        }
    }
   
    public void drawGameOver(Graphics g) 
    {
        // Set the font and color
        g.setFont(new Font("SansSerif", Font.BOLD, 40));
        g.setColor(Color.RED);

        // Draw the message in the center of the screen
        int x = start_x - 2*cell_size;
        int y = start_y + game.getRows()/2 * cell_size;
        g.drawString("GAME OVER", x, y);
    }
    
    private void cycleMove()
    {
        
        game.makeMove('D');         
    }

    public Timer getTimer() {
        return timer;
    }
}
