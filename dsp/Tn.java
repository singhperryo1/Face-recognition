package murali ; 
public class Tn 
{
	public char c ; 
	public HashMap <Character, Tn> child = new HashMap<>();   //child has all the links to the child of a Tn node  
	public boolean isLeaf ; 

	Tn () {} ; 
	Tn (char c) 
	{
		this.c = c ; 
	}
}