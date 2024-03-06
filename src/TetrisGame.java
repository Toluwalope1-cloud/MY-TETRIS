/*
   CSCI282 TetrisPart3: TetrisGame Class
   Tolu Kuseju and Yvonne Ariri
   5/1/2023
 */

import java.util.*;
import java.io.*;
import javax.swing.*;

public class TetrisGame 
{
    private int rows;
    private int cols;
    private int numBricktypes;
    private ArrayList<String> savedGames = new ArrayList<>();
    private TetrisBrick fallingBrick;
    private int [][] background; 
    private Random randomGen = new Random();
    private int state;
    private int score = 0;
    private JLabel scoreLabel = new JLabel("Score: "+getScore());
    
    public TetrisGame(int ros,int cos)
    {
        rows = ros;
        cols = cos;
        initBoard(); 
        recoverGames();
        spawnBrick();
      
    }
            
    public void clearRows() 
    { 
        int counter = 0; // initialize counter variable
        for (int row = getRows()-1; row >= 0 ; row--) 
        { 
            boolean lineFilled = true;

            for (int col = 0; col < getCols(); col++) 
            {
                if (background[row][col] == 0) 
                {
                    lineFilled = false;
                    break;
                }            
            }

            if(lineFilled) {
                clearRow(row);
                shiftDown(row);
                clearRow(0);                

                counter++; // increment counter when a row is cleared
                row++; 
            }                     
        } 
        setScore(calculateScore(counter));
        scoreLabel.setText("Score: " + getScore());
    }

    private int calculateScore(int numClearedRows) 
    {
        int scoreIncrease = 0;
        switch (numClearedRows) {
            case 1:
                scoreIncrease = 100;
                break;
            case 2:
                scoreIncrease = 300;
                break;
            case 3:
                scoreIncrease = 600;
                break;
            case 4:
                scoreIncrease = 1200;
                break;
            default:
                break;
        }
        score += scoreIncrease;       
        return score;
    }
    
    private void clearRow(int ro)
    {
        for(int col = 0; col < getCols(); col++)
            {
                background[ro][col]= 0;
            }
    }
    
    private void shiftDown(int rols)
    {
        for(int row = rols; row > 0; row--)
        {
            for(int col = 0; col < getCols(); col ++)
            {
                background[row][col] = background[row - 1][col];
            }
        }
        clearRow(0);   
    }

    public String toString() 
    {
    String state = "";
    if (fallingBrick != null) {
        state += fallingBrick.toString();
    } else {
        state += "No current shape.\n";
    }
    state += getScore() + "\n";

    for (int row = 0; row < getBackground().length; row++) {
        for (int col = 0; col < getBackground()[row].length; col++) {
            if (getBackground()[row][col] == 0) {
                state += ".";
            } else {
                state += getBackground()[row][col];
            }
        }
        state += "\n";
    }
    return state;
}
      
    public void saveToFile(String fileName) 
    {   
        File directory = new File("savedGames");
        if (!directory.exists()) 
        {
            directory.mkdir();
        }

        File gameFile = new File(directory, fileName);

        if (gameFile.exists() && !gameFile.canWrite()) 
        {
            System.out.println("File already exists.");
            return;
        }
                          
        try 
        {
            savedGames.add(fileName);
            name_directory(fileName);
            FileWriter writer = new FileWriter(gameFile);
            writer.write(this.toString());            

            writer.close();
        }
        catch (IOException e) 
        {
            System.err.println("Error writing game state to file: " + e.getMessage());
        }       
    }
    
    private void name_directory(String new_name)
    {
        File fileConnection = new File("All_saved_games.dat");
        if(fileConnection.exists() && !fileConnection.canWrite()){
            JOptionPane.showMessageDialog(null,"Trouble opening to Write, file: ");
            return;
        }
        try{
            FileWriter outWriter =new FileWriter (fileConnection, true);
            outWriter.write(new_name+"\n");
            outWriter.close();
        }
        catch (IOException ioe){
            JOptionPane.showMessageDialog(null,"Trouble writing to file:");
        }
    }

    
    private void recoverGames() 
    {
        File directory_file = new File("All_saved_games.dat");
        if (!directory_file.exists()) 
        {
            JOptionPane.showMessageDialog(null, "No saved games found.");
            return;
        }

        try (Scanner inScan = new Scanner(directory_file)) 
        {
            while (inScan.hasNextLine()) 
            {
                String fileName = inScan.nextLine().trim();
                if (!fileName.isEmpty()) 
                {
                    savedGames.add(fileName);
                }
            }
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Error reading saved games: " + e.getMessage());
        }
    }

    public void retrieveFromFile() 
    {
    try {
        Object[] savedGame = savedGames.toArray();
        if (savedGames.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "No saved games found.");
            return;
        }
        int choice = JOptionPane.showOptionDialog(null, "Saved Games", "Test", 0, 0, null, savedGame, savedGame[0]);
        String fileName = savedGame[choice].toString();
        File inFile = new File("savedGames", fileName);
        Scanner inScan = new Scanner(inFile);

        int color = inScan.nextInt();
        int ori = inScan.nextInt();
        int centerRow = inScan.nextInt();
        int centerCol = inScan.nextInt();
        int curScore = inScan.nextInt();       

        // Read the background information
        
        int rows = getRows();
        int cols = getCols();
        int[][] bg = new int[rows][cols];
      
            for (int row = 0; row < rows && inScan.hasNextLine(); row++) 
            {
                String line = inScan.nextLine();
                char[] chars = line.toCharArray();
                for (int col = 0; col < cols && col < chars.length; col++) 
                {
                    char c = chars[col];
                    if (c == '.') {
                        bg[row][col] = 0;
                    } else {
                        bg[row][col] = Character.getNumericValue(c);
                    }
                }
            }


        setRows(rows);
        setCols(cols);
        setScore(curScore);
//        fallingBrick.colorNum = color;
//        fallingBrick = new TetrisBrick();
//        fallingBrick.orientation = ori;
//        
        setBackground(bg);

        inScan.close();

    } catch (IOException e) {
        System.err.println("Error reading game state from file: " + e.getMessage());
    }
}

    public boolean validateMove()
    {
        for(int seg = 0; seg < fallingBrick.getNumSegment(); seg++)
        {
            if(this.getSegRow(seg) >= this.getRows())
                return false;
            if(this.getSegRow(seg) < 0)
                return false;
                    
            if(this.getSegCol(seg) > this.getCols() || this.getSegCol(seg) < 1)
                return false;   
            if(getBackground()[getSegRow(seg)][getSegCol(seg)-1] != 0 )
                return false;
        }
        return true; 
    }
    
    public boolean stopGame()
    {
        for(int col = 0; col < getCols(); col++)
        {
            if(getBackground()[0][col] != 0)
                return true;                    
        }
        return false;
    }
    
    public void deleteHighScores() 
    {
        File folder = new File("path/to/directory");
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Recursive call for subdirectories
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }

    }
    
    private static void deleteDirectory(File directory) 
    {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            directory.delete();
        }
    }
    
//    private void recordScore(int score){
//        File fileConnection = new File("Score.dat");
//        if(fileConnection.exists() && !fileConnection.canWrite()){
//            JOptionPane.showMessageDialog(null,"Trouble opening to Write, file: Score");
//            return;
//        }
//        try{
//            FileWriter outWriter =new FileWriter (fileConnection, true);
//            outWriter.write(score+"\n");
//            outWriter.close();
//        }
//        catch (IOException ioe){
//            JOptionPane.showMessageDialog(null,"Trouble writing to file:");
//        }
//    }
//
//    
//    public String score_board(String file_name){
//        ArrayList<Integer> unsorted_Scores = new ArrayList();
//        String scores = "";
//        try {
//            File directory_name = new File(file_name);
//            Scanner inScan = new Scanner(directory_name);
//            while(inScan.hasNextLine()){
//                unsorted_Scores.add(Integer.parseInt(inScan.nextLine()));
//                scores = score_sorter(unsorted_Scores);
//            }
//            inScan.close();
//        }
//        catch (FileNotFoundException e){
//            JOptionPane.showMessageDialog(null,"Trouble opening file: All_saved_games.dat");
//        }
//        return scores;
//    }
//    private String score_sorter(ArrayList scores){
//        String new_scores = "";
//        int number = 1;
//        Collections.sort(scores);
//        for(int dex = scores.size()-1; dex > 0; dex--){
//            new_scores += "Score "+number+": "+scores.get(dex).toString() +"\n";
//            number ++;
//        }
//        return new_scores;
//    }


    public void initBoard()
    {
       setBackground(new int[rows][cols]);
       for (int row = 0; row < rows; row++)
        for (int col = 0; col <cols; col++)
        {              
            background[row][col] = 0;              
        }    
    }
   
    public void newGame() 
    {
        initBoard();
        spawnBrick();
    }
    
    public int fetchBoardPosition(int row, int col)
    {    
        return getBackground()[row][col];               
    }
    
    public void transferColor()
    {   
        for(int seg = 0; seg < this.getNumSegs(); seg++)
            {
                background[getSegRow(seg)][getSegCol(seg)-1] = getFallingbrickColor();       
            }
    }
    
    public void spawnBrick()
    {        
        int cNum = randomGen.nextInt(1,8);
        int centerCol = getCols()/2;
        
        if(!stopGame())
        {
            switch(cNum)
            {
                case 1:
                    fallingBrick = new StackBrick(centerCol);              
                    break;
                case 2:
                    fallingBrick = new SquareBrick(centerCol);                
                    break;
                case 3:
                    fallingBrick = new LongBrick(centerCol);               
                    break;
                case 4:
                    fallingBrick = new JayBrick(centerCol);              
                    break;
                case 5:
                    fallingBrick = new EssBrick(centerCol);              
                    break;
                case 6:
                    fallingBrick = new ElBrick(centerCol);               
                    break;
                case 7:
                    fallingBrick = new ZeeBrick(centerCol);               
                    break;
            }
        }
    }
    
    public void makeMove(char code)
    {
        int center_col = 2;
        switch (code)
        {
            case 'D':
                clearRows();
                fallingBrick.moveDown();
                

                if(!validateMove())
                {
                    fallingBrick.moveUp();
                    if(validateMove())
                        transferColor();
                    
                    spawnBrick();                    
                } 
                break;
                
            case 'R':
                fallingBrick.rotate(getSegCol(center_col), getSegRow(center_col));
                if(!validateMove())
                {
                    fallingBrick.unrotate(getSegCol(center_col), getSegRow(center_col));
                }
                break;
            case 'E':
                fallingBrick.unrotate(getSegCol(center_col), getSegRow(center_col));
                if(!validateMove())
                {
                    fallingBrick.rotate(getSegCol(center_col), getSegRow(center_col));
                }
                break;
            case 'W':
                fallingBrick.moveRight();
                if(!validateMove())
                    fallingBrick.moveLeft();
                break;
            case 'L':
                fallingBrick.moveLeft();
                if (!validateMove())
                    fallingBrick.moveRight();
                break;       
            case 'N':
                newGame();
                break;
        }                
    }
    
    public void clearScore(){
        try{
            FileWriter file = new FileWriter("Score.dat", false);
            PrintWriter flusher = new PrintWriter(file, false);
            flusher.flush();
            flusher.close();
            file.close();
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null,"Cant find file: Score");
        }
    }

    
    public int getRows()
    {
        return rows;
    }

    public int getCols() 
    {
        return cols;
    }
    
    public int getSegRow(int seg)
    {
        return fallingBrick.position[seg][0];
    }
    
    public int getSegCol(int seg)
    {
        return fallingBrick.position[seg][1];
    }

    public int getFallingbrickColor()
    {
        return fallingBrick.getColorNumber();
    }

    public int getNumSegs() 
    {
        return fallingBrick.getNumSegment();
    } 
    
    public int[][] getBackground()
    {
        return background;
    }

    public int getScore() 
    {
        return score;
    }

    public void setRows(int rows) 
    {
        this.rows = rows;
    }

    public void setCols(int cols)
    {
        this.cols = cols;
    }

    public void setBackground(int[][] background) 
    {
        this.background = background;
    }

    public void setScore(int score) 
    {
        this.score = score;
    }

    public JLabel getScoreLabel() 
    {
        return scoreLabel;
    }
}