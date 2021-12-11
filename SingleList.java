public class SingleList {
    
    Node head;
    int size;

    //default constructor
    public SingleList(){
        this.head = null;
        this.size = 0;
    }


    //Makes the single linked-list empty
    void makeEmpty(){  
        while(head != null){
            head = head.next;
        }
        //System.out.println(head);
        size = 0;
    }


    //Checks if the single linked-list is empty
    boolean isEmpty(){
        boolean flag = false;
        if(head == null){
            flag = true;
        }
        return flag;
    }


    //Inserts the new element val in the end of the single linked-list
    void insertLast(int val){
        Node n = new Node(val);
        if(head == null){
            head = n;
        }else{
            Node current = head;
            //check if i am at the last node
            while(current.next != null){
                current = current.next;
            }
            current.next = n;
        }
        size++;
    }


    //Inserts the new element val after Node n
    void insertAfter(Node n, int val){
        Node current = head;
        
        //Find the spot where the node needs to be added
        while(current != null){
            if(current.value == n.value){
                Node m = new Node(val);
                m.next = current.next;
                current.next = m;
                break;
            }
            current = current.next;
        }
        size++;
    }


    //Deletes the first two elements of the single linked-list
    void deleteFirstTwo(){
        if(head != null){
            Node del_one = head;
            Node del_two = head.next;
            head = del_two.next;
            System.out.println("The nodes deleted were " + del_one.value + " and " + del_two.value);
        }
        size -= 2;
    }


    //Function to display the single-linked list
    void display(){
        Node current = head;
        while(current != null){
            System.out.print(current.value + "--->");
            current = current.next;
        }
    }


    //Creates a single linked-list and then adds a node with 15 as its value
    void myTest(){
        insertLast(10);
        insertLast(12);
        insertLast(18);
        insertLast(22);
        Node newNode = new Node(12);
        insertAfter(newNode, 15);
    }


    public static void main(String[] args){
        SingleList listN = new SingleList();
        System.out.println(listN.isEmpty());
        listN.myTest();
        System.out.println(listN.size);
        // listN.deleteFirstTwo();
        //listN.makeEmpty();
        System.out.println("The linked list is:");
        listN.display();
    }
}