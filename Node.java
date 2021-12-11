public class Node {
    
    int value;
    Node next;
    
    //default constructor 
    public Node(){
        this.next = null;
    } 

    //constructor with arguements
    public Node(int val){

        this.value = val;
        this.next = null;
    }
}