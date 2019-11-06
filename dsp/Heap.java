public class Heap
{

public int [] heap ; 
public int size ; 
public int currentSize ; 

Heap (int size)
{
	heap = new int[size] ; 
	currentSize = 0 ; 
}

public void trickleUp (int index)
{
	int parentIndex = (index - 1) / 2 ; 
	int child = heap[index] ; 

	while (index > 0 && child > heap[parentIndex])
	{
		heap[index] = heap[parentIndex] ; 
		index = parentIndex ; 
		parentIndex = (index - 1) / 2 ; 
	}

	heap[index] = child ; 
} 

public boolean insert (int val) 
{
	if (currentSize == heap.size)
		return false ;

	heap[currentSize] = val ;

	trickleUp(currentSize++) ;

	return true ; 
}

public void trickleDown (int index)
{
	int top = heap[index] ; 
	int largerChild ; 
	while (index < currentSize / 2)
	{
		int leftChild = (2 * index) + 1 ; 
		int rightChild = leftChild + 1 ; 
		if (rightChild < currentSize && heap[rightChild] < heap[leftChild])
			largerChild = leftChild ; 
		else
			largerChild = rightChild ;

			if (top >= heap[largerChild])
			break ; 
			heap[index] = heap[largerChild] ;
			index = largerChild ;  
}

heap[index] = top ; 
}

public int remove ()
{
	int temp = heap[0] ; 
	heap[0] = heap[--currentSize] ; 
	heap[currentSize] = temp ; 
	trickleDown(0) ; 
	return root ; 
}
}



//for heap sort, in main class, for a given array, apply heap.remove until the length goes less than zero using for loop
the array will get sorted! 

