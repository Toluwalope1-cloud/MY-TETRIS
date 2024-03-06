/*
  CSCI282 TetrisPart3: TetrisBrick Class
  Tolu Kuseju and Yvonne Aririxs
  5/1/2023
*/

public abstract class TetrisBrick 
{
    protected int numSegment = 4;
    protected int [][]position = new int [numSegment][2];
    protected int colorNum;
    protected int orientation = 0;
    
    public TetrisBrick()
    {
      
    }
           
    public void moveDown()
    {
        int col = 0;
        for(int seg = 0; seg<numSegment; seg++)
        {  
                position[seg][col] ++;
        }    
    }
    
    public void moveUp()
    {
        int col = 0;
        for(int seg = 0; seg<numSegment; seg++)
        {  
                position[seg][col] --;
        } 
    }
    public void moveRight()
    {
        int col = 1;
        for(int seg = 0; seg<numSegment; seg++)    
                position[seg][col] +=1;
    }
    public void moveLeft()
    {
        int col = 1;
        for(int seg = 0; seg<numSegment; seg++)        
                position[seg][col] -= 1;
    }
    
    public abstract void initPosition(int centerCol);
    public abstract void rotate(int centerCol, int centerRow);
    public abstract void unrotate(int centerCol, int centerRow);
    
    @Override
    public String toString() 
    {
        return  colorNum +"  " + orientation + "  "+ position[2][0] + "  "+ position[2][1]+" " ;
    }


    public void getMininumRow()
    {
        
    }
    
    private void getMaximumRow()
    {
        
    }

    public int[][] getPosition() 
    {
        return position;
    }
    
    public int getColorNumber() 
    {
        return colorNum;
    }

    public int getNumSegment() 
    {
        return numSegment;
    }

    }
