 
public class Transition 
{
	int init,fin;
	String symbol,out,top;
	int total;
	boolean isinit,isfin;
	char dir;
	
	Transition(int s1,int s2,String input,String output,String s )
	{
		init=s1;
		fin=s2;
		symbol=input;
		out=output;
		top=s;
	}

}
