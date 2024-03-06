/*
  CSCI282 TetrisPart3: SquareBrick Class
  Tolu Kuseju and Yvonne Ariri
  5/1/2023
 */

public class SquareBrick extends TetrisBrick 
{
    public SquareBrick(int centercol)
    {
       initPosition(centercol);
       colorNum = 2;   
    }

    @Override
    public void initPosition(int centerCol)
    {
        position = new int[][]{{0,centerCol},{0,centerCol+1},{1,centerCol},{1,centerCol+1}};
    } 
    
    public void rotate(int centerCol, int centerRow)
    {
                           
    }   
    public void unrotate(int centerCol, int centerRow)
    {       
        
    }
}
   