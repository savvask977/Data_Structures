/* 
Author: Karampelas Savvas
AEM: 9005
email: savvaska@ece.auth.gr
*/

public class Move {
    
    //coordinates of last move
    protected int x, y;

    //default constructor
    public Move(){
        this.x = 0;
        this.y = 0;
    }
    
    public Move(int x, int y){
        this.x = x;
        this.y = y;
    }
}
