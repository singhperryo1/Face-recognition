package litt ; 

public class bt 
{
public TreeN root ; 

bt ()
{
	this.root = null ; 
}

public void insert (int n)
{
	TreeN add = new TreeN () ; 
	add.data = n ; 
	add.left = null ; 
	add.right = null ; 

	if (root == null)
	{
		root = add ;
		return ;  
	}

	TreeN curo = root ; 

	while (true)
	{
		if (curo.data > n)
		{
			if (curo.data == n)
			{
				curo.left = add ;
				return ; 
			}
			else
			curo = curo.left ;
		} 
		else
		{
			curo = curo.right ;
			if (curo.right == null)
			{
				curo.right = add ; 
				return ;  
			}
			else
			{
				curo = curo.right ; 
			}
		}
	}
}

public TreeN search (int n)
{
	TreeN curo = root ; 

	while (curo != null)
	{
		if (curo.data == n)
			return curo ; 
		else if (curo.data > n)
			curo = curo.left ; 
		else
			curo = curo.right ; 
	}

	return null ; 
}

public TreeN rn (TreeN rem)
{
	TreeN succP = rem ; 
	TreeN succ = rem ; 
	TreeN curo = succ.right ; 

	while (curo != null)
	{
		succP = succ ; 
		succ = curo ; 
		curo = curo.left ; 
	}

	if (succ != rem.right)
	{
		succP.left = succ.right ; 
		succ.right = rem.right ; 
	}

	return succ ; 
}

public boolean remove (int n)
{
	if (root == null)
		return false ; 

	TreeN curo = root ; 
	TreeN p = root ; 
	boolean goneL = ture ; 

	while (curo.data != n)
	{
		parent = curo ; 
		if (curo.data > n)
		{
			goneL = true ; 
			curo = curo.left ; 
		}
		else
		{
			goneL = false ; 
			curo = curo.right ; 
		}

		if (curo == null)
			return false ; //could not find the given n 
	}

	//now that we have found the n to delete, lets find the successor or check conditions

	//1st case : if node is leaf

	if (curo.left == null && curo.right == null)
		{
		if (parent == curo) //meaning its is root
			root = null ; 
			else if (goneL) //if gone left or not
				parent.left = null ; 
			else
				parent.right = null ; 
		} 
	else if (curo.left == null)   //2nd case, the node to be deleted has one child 
			{
				if (parent == curo)
					root = curo.right ; 
				else if (goneL)
					parent.left = curo.right ; 
				else
					parent.right = curo.right ; 
			}
	else if (curo.right == null)
	{
		if (parent == curo)
			root = curo.left ; 
		else if (goneL)
			parent.left = curo.left ;  
		else
			parent.right = curo.left ; 
	}  
	else   //3rd case, the node to be deleted has two childs
	{
		TreeN replace = rN (curo) ; 
		if (parent == curo)
			root = replace ; 
		else if (goneL)
			parent.left = replace ; 
		else
			parent.right = replace ;

		replace.left = curo.left ; 
	}

return true ; 
}

}