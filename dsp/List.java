package List ; 

public class List 
{
public Node head ; 

List()
{
	head = null ; 
}

public void insert (int value)
{
	Node add = new Node(); 
	Node.val = value ; 
	add.next = head ; 
	head = add ; 
}

public void append (int data)
{
	Node add = new Node(); 
	add.val = data ; 
	add.next = null ; 
	//remember you are working on designing the method, it is not related to your leet code q' 
	if (head == null) 
		head = add ; 
	else
	{
	Node temp = head; 
	while (temp.next != null) 
	{
		temp = temp.next ; 
	} 
	temp.next = add ;
	}
}

public Node search (int val)
{
	Node temp = head; 
	while (temp != null)
	{
		if (temp.val == val)
			return temp ; 
		temp = temp.next ; 
	}

	return temp ; 
}

public boolean update (int oldV, int newV)
{
	Node temp = search(oldV) ; 
	if (temp != null)
	{
		temp.val = newV ; 
		return true ; 
	}
	else
		return false; 
}

public boolean delete (int data)
{
	Node current = head; 
	if (current == null)
		return false; 
	if (current.val == data)
	{
		head = current.next  ; 
		current = null ; 
		return true ; 
	}

	Node fast = head.next ; 
	Node slow = head ; 

	while (fast != null)
	{
		if (fast.val == data)
		{
			slow.next = fast.next ;
			fast  = null ;  
			return true ; 
		}
		fast = fast.next ; 
		slow = slow.next ; 
	}
	return false ; 
}
}