import java.util.* ; 

public class algorithms 
{

public static void main (String [] args)
{

// int [] b = {-2, -4, 0, 8, -1, 11, 5} ; 
// rqs(b, 0, b.length - 1) ; 

// for (int i = 0 ; i < b.length ; i++)
// 	System.out.println(b[i]) ; 

String test = "12340056" ; 
for (int i = 0 ; i < test.length() ; i++)
{
	System.out.println(test.charAt(i)- '1') ;  
}

}

//binary search iterative way 

public int bs (int [] a, int val)
{
	int low = 0 ; 
	int high = a.length - 1 ; 
	while (high >= low)
	{
		int half = (high + low) / 2 ; 
		if (a[half] == val)
			return half ; 
		else if (a[half] > val)
			high = half - 1 ; 
		else
			low = half + 1 ; 
	}
	return -1 ; 
}

//binary search recursive way 
public static int bsRecursive (int [] a, int val, int low, int high)
{
	int ans ;  

	if (low > high)
		return -1 ;  

	int mid = (high + low) / 2 ; 


	if (a[mid] == val)
		return mid ; 

	else if (a[mid] > val)
		ans = bsRecursive(a, val, low, mid - 1) ; 
	else 
	 ans = bsRecursive(a, val, mid + 1, high) ;

    return ans ; 
}


public static void mS (int [] a, int low, int high)
{ 
	if (low == high)
		return ; 

	int mid = (high + low) / 2 ; 

	mS(a, low, mid) ; 
	mS(a, mid + 1, high) ; 

	merge(a,low, mid + 1, high) ; 

}

public static void merge (int [] a, int low, int mid , int high)
{
	int [] temp = new int [high + 1] ; 
	int lowEnd = low ; 
	int index = 0 ; 
	int lowBound = mid ; 
	int fl = high - low + 1  ; 
	while (lowEnd < lowBound && mid < high + 1)
	{
		if (a[lowEnd] > a[mid])
			temp[index++] = a[mid++] ;
			else
			temp[index++] = a[lowEnd++] ; 
	}

	while (lowEnd < lowBound)
		temp[index++] = a[lowEnd++] ; 

	while (mid < high + 1)
		temp[index++] = a[mid++] ; 

for (int c = 0 ; c < fl ; c++)
{
	a[low + c] = temp[c] ;  
}

}

public static void rqs (int [] a, int l, int r)
{
	if ((r - l) <= 0 )   //base case for quick sort
		return ; 
	else
	{
		int pivot = a[r] ;

		//here pi is partition index 

		int pi = partition (a,l,r,pivot) ; 
		rqs (a, l, pi - 1) ; 
		rqs (a, pi + 1, r) ; 
	}
}

public static int partition (int [] a, int l, int r, int pivot)
{
	int lptr = l - 1 ; 
	int rptr = r ; 

	while (true)
	{
		while (a[++lptr] < pivot) ; 
		while (rptr > 0 && a[--rptr] > pivot) ; 

		if (lptr >= rptr)
			break ; 
		else
		{
			int temp = a[lptr] ; 
			a[lptr] = a[rptr] ; 
			a[rptr] = temp ;  
		}
	}

	int t = a[lptr] ; 
	a[lptr] = a[r] ; 
	a[r] = t ; 
	return lptr ;  
}
}