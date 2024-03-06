/*
  CSCI282 TetrisPart3: StackBrick Class
  Tolu Kuseju and Yvonne Ariri
  5/1/2023
 */

public class StackBrick extends TetrisBrick
{
   
    public StackBrick(int centercol) 
    {
        initPosition(centercol); 
        colorNum = 1;
        
    }
    
    @Override
    public void initPosition(int centerCol)
    {
        position = new int[][]{{0,centerCol},{1,centerCol-1},{1,centerCol},{1,centerCol+1}};
    }
    
    public void rotate(int centerCol, int centerRow) {
    int num_of_rotation = 4;
    int next_orientation = (orientation + 1) % num_of_rotation;

    if (next_orientation == 0)
        position = new int[][]{{centerRow - 1, centerCol},
                               {centerRow, centerCol-1},
                               {centerRow, centerCol},
                               {centerRow, centerCol+1}};
    if (next_orientation == 1)
        position = new int[][]{{centerRow + 1, centerCol+1},
                               {centerRow + 1, centerCol},
                               {centerRow, centerCol},
                               {centerRow + 2, centerCol}};
    if (next_orientation == 2)
        position = new int[][]{{centerRow+1, centerCol},
                               {centerRow, centerCol+1},
                               {centerRow, centerCol},
                               {centerRow, centerCol-1}};
    if (next_orientation == 3)
        position = new int[][]{{centerRow,centerCol-1},
                               {centerRow+1, centerCol},
                               {centerRow,centerCol},
                               {centerRow-1,centerCol}};

    orientation = next_orientation;
}


   public void unrotate(int centerCol, int centerRow) {
    int num_of_rotation = 4;

    int prev_orientation = orientation - 1;
    if (prev_orientation < 0) 
        prev_orientation = num_of_rotation;

    if (prev_orientation == 0) {
        position = new int[][]{{centerRow - 1, centerCol},
                               {centerRow, centerCol - 1},
                               {centerRow, centerCol},
                               {centerRow, centerCol + 1}};
    }
    if (prev_orientation == 1) {
        position = new int[][]{{centerRow + 1, centerCol + 1},
                               {centerRow + 1, centerCol},
                               {centerRow, centerCol},
                               {centerRow + 2, centerCol}};
    }
    if (prev_orientation == 2) {
         position = new int[][]{{centerRow + 1, centerCol},
                               {centerRow, centerCol + 1},
                               {centerRow, centerCol},
                               {centerRow, centerCol - 1}};
    }
    if (prev_orientation == 3) {
        position = new int[][]{{centerRow, centerCol - 1},
                               {centerRow + 1, centerCol},
                               {centerRow, centerCol},
                               {centerRow - 1, centerCol}};
    }

    orientation = prev_orientation;
}

}
