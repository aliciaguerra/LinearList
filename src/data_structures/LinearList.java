/*
 * Alicia Guerra
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class LinearList<E> implements LinearListADT<E>{
int currentSize, maxSize;
private Node<E> head=null;
private Node<E> tail;

/*Constructs an empty list.*/
  public LinearList() {
      currentSize=0;
      maxSize=DEFAULT_MAX_CAPACITY;
    }

    @Override
    public void addLast(E obj) {
     Node<E> newNode = new Node<E>(obj);
        if (isEmpty()) {
        head = tail = newNode;
        }
    /*We will insert the new node after the last.*/    
        else {
        tail.next = (Node<Object>) newNode;
        tail = newNode;
        }
    /*We're going to keep increasing the size by 1.*/
        currentSize++;
    }

    @Override
    public void addFirst(E obj) {
    /*We will create a new node.*/
        Node<E> newNode = new Node<E>(obj);
        if (isEmpty()) {
        head = tail = newNode;
        }
    /*We will insert the node before the first.*/   
        else {
        newNode.next = (Node<Object>) tail;
        head = newNode;
        }
    /*We will increment the count by one.*/
        currentSize++;
    }

    @Override
    public void insert(E obj, int location) {
     if(head==null || tail==null)
			return;
		if(location==0)
		{
			addFirst(obj);
			return;
		}
		if(location==currentSize)
		{
			addLast(obj);
			return;
		}
		if(location>currentSize)
			return;
		
		Node currentNode=head;
		for(int i=0;i<location;i++)
			currentNode=currentNode.next;
		
		Node node=new Node;
		node.obj=obj;
		Node nextNode=currentNode.next;
		
		currentNode.next=node;
		node.next=nextNode;
		currentSize++;   
    }

    @Override
    public E remove(E obj) {
    /*We will be traversing the list until we find the object.*/
    Node<E> previous = null, current = head;
    while (current != null && ((Comparable<Object>) obj).compareTo(current.data) != 0) {
        previous = current;
        current = (Node<E>) current.next;
        }
    if (current == null)
    return null;
    /*If there's only 1 item in the list, we come to this if statement.*/
        if (head == tail)
        {
            head = tail = null;
        }
        else if (previous == null) {
            head = (Node<E>) head.next;
        }
        else if (current == tail) {
            previous.next = null;
            tail = previous;
        }
    /*If none of the others apply, that means there are two or more nodes and 
    it's in the middle.*/
        else 
        {
        previous.next = current.next;
        }
        currentSize--;
        return (E) current.data;

    }


    @Override
    public boolean contains(E obj) {
    }
    

    @SuppressWarnings("unchecked")
    public int locate(E obj) {
        for(E obj : this)
        {
            if (((Comparable<E>) obj).compareTo(((E) obj)) == 0)
            return obj;
        }
        return null;
    }

    @Override
    public void clear() {
        head=null; 
        currentSize=0;
    }


    @Override
    public int size() {
        return currentSize; 
    }


    @Override
    public boolean isEmpty() {
        return currentSize==0; 
    }

    @Override
    public E remove(int location) {
       if(head==null || tail==null)
			return null;
	
		if(location==0)
			return removeFirst();
		if(location==currentSize)
			return removeLast();
		if(location>currentSize)
			return null;
		
		Node currentNode=head;
		for(int i=0;i<location-1;i++)
			currentNode=currentNode.next;
		obj result=currentNode.next.obj;
		currentNode.next=currentNode.next.next;
		currentSize--;
		return result;
    }

    @Override
    public E removeFirst() {
     if (isEmpty())
            return null;

        E temp = (E) head.data;

        if (head == tail) {
            head = tail = null;
        }
        else
            head = (Node<E>) head.next;
    /*We are going to decrement our size by 1.*/
        currentSize--;
        return temp;
    }

    @Override
    public E removeLast() {
        if(tail==null)
			return null;
		Object result=tail.obj;
		if(head==tail)
			head=tail=null;
		else
		{
			Node currentNode=head;
			while(currentNode.next!=tail);
				currentNode=currentNode.next;
			currentNode.next=null;
			tail=currentNode;
		}
		currentSize--;
		
		return (E) result;
    }

    @Override
    public E get(int location) {
     ListIterator it = new ListIterator();

	   for(int i = 0; i<location; i++)
		   {it.next();}
	   return it.next();    
    }

    @Override
    public Iterator<E> iterator() {
       return new ListIterator(); 
    }
 
    public class ListIterator implements Iterator<E> {

        Node<E> iterPtr;

        public ListIterator() {
            iterPtr = head;
        }

        @Override
        public boolean hasNext() {
            return iterPtr != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E temp = (E) iterPtr.data;
            iterPtr = (Node<E>) iterPtr.next;
            return temp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

/*The Node Class*/
public class Node<E> {

        public Node(E obj) {
            this.data=obj;
        }
        public Node<Object> next;
        public Node<Object> previous;
        public Object data;

    }

}