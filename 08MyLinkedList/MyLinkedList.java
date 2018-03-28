public class MyLinkedList{
    private Node start, end;
    private int size;


    public MyLinkedList(){
    }

    private class Node{
	private Integer data;
	private Node next;

	private Node(Integer value){
	    data = value;
	}

	private Node(Integer value, Node nextNode){
	    Node(value);
	    next = nextNode;
	}

	private Integer getData(){
	    return data;
	}

	private boolean setData(Integer a){
	    data = a;
	    return true;
	}

	private Node getNextNode(){
	    if(next != null){
		return next;
	    }
	    return null;
	}

	private boolean setNextNode(Node a){
	    next = a;
	    return true;
	}

	private String toString(){
	    return "[data: " + data + "]";
	}
	
    }
    
}
