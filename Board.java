class Board
{
    enum Orientation{
        HORIZONTAL, VERTICAL
    }
    int[][] board;
    Board(){
        board=new int[10][10];
    }
    public int[][] returnMatrix(){
        return board;
    }
    public boolean damage(int x, int y)
    {
        switch (board[y-1][x-1]) {
            case 0:
                board[y-1][x-1]=2;
                return true;
            case 1:
                board[y-1][x-1]=3;
                return true;
            case 2:
                return false;
            case 3:
                return false;
            default:
                return false;
        }  
    }
    public void show(boolean isEnemy)
    {
        if(!isEnemy){
            System.out.println("   A B C D E F G H I J");
            for(int i = 0 ; i<10;i++){
                if(i+1!=10)
                System.out.print(" ");
                System.out.print(i+1+(i+1!=10?" ":" "));
                for(int j=0; j<10; j++) {
                    if(board[i][j]==0)
                    System.out.print("~"+" ");
                    if(board[i][j]==1)
                    System.out.print("S"+" ");
                    if(board[i][j]==2)
                    System.out.print("O"+" ");
                    if(board[i][j]==3)
                    System.out.print("X"+" ");
                }
                System.out.println();
            }
        }
        else{
            System.out.println("   A B C D E F G H I J");
            for(int i = 0 ; i<10;i++){
                if(i+1!=10)
                System.out.print(" ");
                System.out.print(i+1+(i+1!=10?" ":" "));
                for(int j=0; j<10; j++) {
                    if(board[i][j]!=1){
                        if(board[i][j]==0)
                        System.out.print("~"+" "); 
                        if(board[i][j]==2)
                        System.out.print("O"+" ");
                        if(board[i][j]==3)
                        System.out.print("X"+" ");
                    }
                    else
                    System.out.print("~"+" ");
                }
                System.out.println();
            }
        }
    }
    public boolean loseCondition()
    {
        for(int i = 0 ; i<10;i++){
            for(int j=0; j<10; j++) {
                if(board[i][j]==1)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public void placeShips()
    {
        int size=4;
        int n=1;
        while(size>0)
        {
            for(int i=0; i<n; i++)
            {
                boolean f=false;
                while(!f)
                {
                    Orientation orientation=Math.random()*10.0>=5?Orientation.HORIZONTAL:Orientation.VERTICAL;
                    int x=(int)(Math.random()*10.0);
                    int y=(int)(Math.random()*10.0);
                    f=placeOneShip(x, y, size, orientation);
                }
               
            }
            size--;
            n++;
        }
        
       
    }
    private boolean placeOneShip(int x, int y, int size, Orientation orientation){     
            
            switch (orientation) {
                case HORIZONTAL:
                     if(x+size>10)
                     return false;  
                    for(int i=(y<=0?0:y-1);i<(y+2>=10?10:y+2);i++){
                         for(int j=(x<=0?0:x-1);j<(x+size+1<=10?x+size+1:10);j++){
                             
                             if(board[i][j]==1)
                             {
                                 return false;
                             }
                        }
                     }
                     for(int i=y; i<(y==10?y:y+1); i++){
                          for(int j=x;j<(x+size<=10?x+size:10);j++){
                             board[i][j]=1;
                         }
                      }
                    return true;
                case VERTICAL:
                      if(y+size>10)
                      return false;
                     for(int i=(y<=0?0:y-1);i<(y+size+1<=10?y+size+1:10);i++){
                         for(int j=(x<=0?0:x-1);j<(x+2>=10?10:x+2);j++){
                            if(board[i][j]==1)
                             {
                                 return false;
                             }
                        }
                    }
                    for(int i=y; i<(y+size<=10?y+size:10); i++){
                        for(int j=x;j<(x==10?x:x+1);j++){
                           board[i][j]=1;
                       }
                    }
                    return true;
                default:
                    return false;
                    
            }
    }
    @Override
    public String toString(){
        String s=new String();
        for(int i=0;i<10;i++){
            for(int j=0; j<10; j++){
                s+=Integer.toString(board[i][j]);
            }
        }
        return s;
    }
    public void setMatrix(String s){
        int k=0;
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++)
            {
                board[i][j]=Character.getNumericValue(s.charAt(k));
                k++;
            }
        }
        
    }
}