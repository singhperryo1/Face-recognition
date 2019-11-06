package murali ; 

public class Trie 
{
public Tn root ; 

Trie()
{
	root = new Tn(); 
}

public void add (String word)
{
	HashMap<Character, Tn> child = root.child ; 
	for (int i = 0 ; i < word.length(); i++)
	{
		char c = word.charAt(i) ; 
		Tn insert ; 
		if (child.containsKey(c))
			insert = child.get(c); 
		else
		{
			insert = new Tn(c); 
			child.put(c, insert) ; 
		}

		child = insert.child ; 

		if (i == word.length() - 1)
			insert.isLeaf = true ; 
	}
}

public Tn search (String word)
{
	HashMap <Character, Tn> child = root.child ;
	Tn ans ;  
	for (int i = 0 ; i < word.length() ; i++)
	{
		char c = word.charAt(i) ; 
		if (child.containsKey(c))
		{
			ans = child.get(c) ;
			child = ans.child ;  
		}
		else
			return null ; 
	}

	return ans ; 
}

public boolean startWith (String prefix)
{
	if (search(prefix) != null)
		return true ; 
	else
		return false; 
}


}