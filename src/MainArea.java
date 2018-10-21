import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;

import java.math.*;
import java.net.URL;
import java.util.*;
import java.awt.geom.Line2D;
import java.awt.image.*;
import java.awt.*;

public class MainArea extends Applet implements MouseListener
{	
	int x=0,y=0;
	static int i=0,transcount=0;
	int s1=-1,s2=-1;
	boolean flag=false;
	public static State[] state=new State[20];
	public static int initialstate=-1;
	static Transition[] trans=new Transition[100];
	boolean rejectit=false,clear=true;
	TextField tf=new TextField();
	Font f=new Font("TimesRoman", Font.PLAIN,72);
	boolean nostaterepaint=false;
	String output=new String(""),inp1=new String("");
	String s=new String("");
	int code,statecount=0,nop;
	String[][] prod;
	char mapp[]=new char[20];
	int y1,y2;
	Transition[] t1;//=new Transition[20];
	Transition[] t2;//=new Transition[20];
	char tape[];
	
	Graphics g1;
	
	
	String stackarray="$";
	int salen=1;
	
	
	MainArea()
	{
		code=Interface.machine;
		init();
		System.out.print("------------Transcount---------" + transcount + "\n");
	}
	
	
	public void init()
	{
		if(Interface.machine!=4 && Interface.machine!=11)this.addMouseListener(this);
		
		
		setBackground(Color.PINK);
		
		
		
		//this.setLayout(new GridLayout(1,1));
		
		//draw.setBounds(0,0,this.getWidth(), this.getHeight());
		//this.add(draw);
	    i=0;
	    //transcount=0;
	  //  initialstate=0;
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		x=arg0.getX();
		y=arg0.getY();
		System.out.print(x+ " + " + y + "\n");
		System.out.print("\ncode= " + code + "   " + Interface.machine + "\n");
		if(Interface.machine!=11 && Interface.machine!=4)repaint();
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0){}
	@Override
	public void mouseExited(MouseEvent arg0){}
	@Override
	public void mousePressed(MouseEvent arg0){}
	@Override
	public void mouseReleased(MouseEvent arg0){}
	
	
	
	void simulate_fsm()
	{
		repaint();
	    inp1=ToolBox.inp;
		int inplength=inp1.length();
		int current=initialstate,next,j;
		next=-1;
		rejectit=false;
		
		while(inplength>0)
		{	
			state[current].drawCurrent(inp1,inplength,"");
		     
		     for(j=0;j<transcount;j++)
		     {
		    	 if(trans[j].init==current && (trans[j].symbol).charAt(0)==inp1.charAt(inp1.length()-inplength))
		    	 {
		    		 next=trans[j].fin;
		    		 break;
		    	 }
		     }
		     
		    if(j==transcount){rejectit=true;break;}
		    nostaterepaint=true;
		    repaint();
		    nostaterepaint=false;
		    try{Thread.sleep(1000);}catch(Exception e){}
		     
		     state[current].removecurrent(output);
		     
		     current=next;
		     inplength--;
		}
		state[current].drawCurrent(inp1,inplength,"");
		repaint();
		if(state[current].isfinal && rejectit==false)
		{
			JOptionPane.showMessageDialog(new JFrame(),"Input String Accepted","Accepted",JOptionPane.PLAIN_MESSAGE);
		//	repaintit();
			
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(),"Input String Rejected","Rejected",JOptionPane.ERROR_MESSAGE);
			
			//repaintit();
		}
		state[current].removecurrent(output);
		inp1="";
		output="";
		nostaterepaint=false;
		ToolBox.inp="";
		repaint();
	}
	
	
	void repaintit(Graphics g)
	{
	    
		System.out.println("\n nostaterepaint    i:" + nostaterepaint + i+"   machine is:"+Interface.machine);
		
		if(nostaterepaint==false)
		{
		for(int k=0;k<i;k++)
	    {
	    	state[k].drawit(0);
	    	if(state[k].isfinal)state[k].drawfinal();
	    }
		}
		
	    
	    
		if(initialstate!=-1)
		{
		int tx=0,ty=0;
		tx=state[initialstate].x+15;
		ty=state[initialstate].y+25;
		int px[]={tx-50,tx-20,tx-50,tx-50};
		int py[]={ty+20,ty,ty-20,ty+20};
		g.drawPolygon(px,py,4);
		}
		
		System.out.println("machine is:"+Interface.machine);
	/*	if(Interface.machine==3)           // drawing stack
		{
			
	
			
			g.drawLine(10, 480, 50, 480);
			g.drawLine(10, 480, 10, 300);
			g.drawLine(50, 480, 50, 300);
			
			
				Font f1=g.getFont();
				 
			    g.setFont(new Font("Times New Roman",Font.PLAIN,20));
			    System.out.println("stackarray thisis= "+stackarray+"  "+salen);
			//	g.drawString(""+stackarray.charAt(k), 26,470-k*40);
				g.drawChars(stackarray.toCharArray(),0,stackarray.length(), 20, 20);
				g.setFont(f1);
			
		   
		}
		*/
		
		for(int k=0;k<transcount;k++)
		{
			int s_1=0,s_2=0;
			s_1=trans[k].init;
			s_2=trans[k].fin;
			String inp=trans[k].symbol,out=trans[k].out,top=trans[k].top;
		double dy = state[s_2].y - state[s_1].y;  
        double dx = state[s_2].x - state[s_1].x;  
        double theta = Math.atan2(dy, dx);    
        double xx, yy, rho = theta + Math.toRadians(40);   
        
    //    Graphics g=this.getGraphics();
		g.setColor(Color.RED);
	    Font f1=g.getFont();
	 
	    g.setFont(new Font("Times New Roman",Font.PLAIN,20));
		if(state[s_1].x != state[s_2].x && state[s_1].y != state[s_2].y)
		{
		g.drawLine(state[s_1].x+15,state[s_1].y+25,state[s_2].x+15,state[s_2].y+25);
		
        for(int k1 = 0; k1 < 2; k1++)  
        {  
            xx = state[s_2].x+15 - 10 * Math.cos(rho);  
            yy = state[s_2].y+25 - 10 * Math.sin(rho);  
            g.drawLine(state[s_2].x+15, state[s_2].y+25, (int)xx,(int) yy);  
            rho = theta - Math.toRadians(40); ;  
        }  
		
        if(Interface.machine==1)
        {
        g.drawString(inp+" / "+out,(state[s_1].x+state[s_2].x)/2 +15 , (state[s_1].y+state[s_2].y)/2 +25);
        }
        else if(Interface.machine==3)
        {
        	g.drawString(inp+","+ out+"-->"+top ,(state[s_1].x+state[s_2].x)/2 +15 , (state[s_1].y+state[s_2].y)/2 +25);
        }
        else
        {
        //g.drawString(inp,(state[s_1].x+state[s_2].x)/2 +15 , (state[s_1].y+state[s_2].y)/2 +25);
        g.drawString(inp,(((state[s_1].x+state[s_2].x)/2)+state[s_1].x)/2 +15 ,(((state[s_1].y+state[s_2].y)/2)+state[s_1].y)/2+25);
        }
		}
		else
		{
			g.drawLine(state[s_1].x+15,state[s_1].y+25,state[s_2].x+40,state[s_2].y-20);
			g.drawLine(state[s_2].x+40,state[s_2].y-20,state[s_2].x,state[s_2].y-20);
			g.drawLine(state[s_2].x,state[s_2].y-20,state[s_1].x+15,state[s_1].y+25);
			g.drawLine(state[s_1].x+15,state[s_1].y+25,state[s_1].x+5,state[s_1].y+25-10);
			g.drawLine(state[s_1].x+15,state[s_1].y+25,state[s_1].x+15,state[s_1].y+25-15);
			g.drawString(inp,state[s_1].x+15,state[s_1].y-22);
		}
        g.setColor(Color.BLACK);
        g.setFont(f1);
		}
	}
	
	int genauto(int sstate,char symbol)
	{
		int i,j,k;
		int finstate=0;
		boolean stflag;
		String temp="";
		int curstate=0;
		int endst=0;
		
		
		state[statecount++]=new State(x,y,'#',this,0,"");
		state[statecount-1].isfinal=false;
		//state[statecount-1].drawfinal();
		endst=statecount-1;
		if(statecount%2==0){y2-=20;y=y2;}
		else 
		{y1+=20;y=y1;}
		
		x+=70;
		
		
		for(j=0;j<=nop;j++)
		{
			curstate=sstate;
			if(prod[j][0].charAt(0)==symbol)
			{
                
				for(k=0;k<prod[j][1].length();k++)
				{
					if(prod[j][1].charAt(k)>=65 && prod[j][1].charAt(k)<91)
					{
						
						stflag=true;
						int k1;
						for(k1=0;k1<statecount;k1++){if(state[k1].tag==prod[j][1].charAt(k)){stflag=false;finstate=k1;break;}}
						
						if(stflag==false)
							{
							trans[transcount++]= new Transition(curstate,finstate,temp,"","");
							curstate=finstate;
							
							}
						else 
						{
							state[statecount++]=new State(x,y,prod[j][1].charAt(k),this,0,"");
							state[statecount-1].isfinal=false;
							if(statecount%2==0){y2-=20;y=y2;}
							else 
							{y1+=20;y=y1;}
							
							x+=70;
							finstate=statecount-1;
							
							trans[transcount++]= new Transition(curstate,finstate,temp,"","");
							curstate=genauto(finstate,state[k1].tag);
							temp="";
						}
							
					}
					else
					{
						temp+=prod[j][1].charAt(k);
					}
				}
				
				if(true)
				{
					
					trans[transcount++]= new Transition(curstate,endst,temp,"","");
					curstate=endst;
					temp="";
				}
			}
		}
		
		
		return endst;
	}
	
	
	
	public void paint(Graphics g)
	{
		g1=g;
		
		g.setColor(Color.WHITE);
		
		//if(Interface.machine==3)g.fillRect(60, 0,800,600);
		//else
       	g.fillRect(0, 0,900,600);
		g.setColor(Color.BLACK);
	
		//
		
		if(Interface.machine==11)
		{
			int x=50,y=50,y1=50,y2=400;
			y=y1;
			for(int j=0;j<statecount;j++)
			{
				state[j]=new State(x,y,j,this,0,"");
				if(j%2==0){y=y2;y2-=20;}
				else 
				{y=y1;y1+=20;}
				
				x+=70;
			}
			
			
			for(int j=0;j<t1[0].total;j++)
			{
			
				g.setColor(Color.red);
				g.drawLine((state[t1[j].init].x)+15,(state[t1[j].init].y)+25,(state[t1[j].fin].x)+15,(state[t1[j].fin].y)+25);
				
				
				if(t1[j].isfin){state[t1[j].fin].drawfinal();state[t1[j].fin].isfinal=true;}
				if(t1[j].isinit)
				{
					initialstate=t1[j].init;
					
					int tx,ty;
					tx=state[t1[j].init].x+15;
					ty=state[initialstate].y+25;
					int px[]={tx-50,tx-20,tx-50,tx-50};
					int py[]={ty+20,ty,ty-20,ty+20};
					g.drawPolygon(px,py,4);
					
				}
				
				g.setColor(Color.red);
				s1=t1[j].init;
				s2=t1[j].fin;
				double dy = state[s2].y - state[s1].y;  
		        double dx = state[s2].x - state[s1].x;  
		        double theta = Math.atan2(dy, dx);    
		        double xx, yy, rho = theta + Math.toRadians(40);   
		        for(int k = 0; k < 2; k++)  
		        {  
		            xx = state[s2].x+15 - 20 * Math.cos(rho);  
		            yy = state[s2].y+25 - 20 * Math.sin(rho);  
		            g.drawLine(state[s2].x+15, state[s2].y+25, (int)xx,(int) yy);  
		            rho = theta - Math.toRadians(40); 
		            
		        }  
		        g.setColor(Color.red);					        
		        g.drawString(t1[j].symbol,(((state[s1].x+state[s2].x)/2)+state[s1].x)/2 +15 ,(((state[s1].y+state[s2].y)/2)+state[s1].y)/2+25);
		        g.setColor(Color.black);
			}
			
			
		}
		
		
		else if(Interface.machine==12)
		{
			System.out.print("paint 1");
			 x=50;
			 y=50;y1=50;y2=400;
			y=y1;
			statecount=0;
			boolean stflag=true;
			initialstate=0;
			/*for(int j=0;j<=nop;j++)
			{
				stflag=true;
				for(int k=0;k<statecount;k++)
				{
					if(state[k].tag==prod[j][0].charAt(0))
					{
						stflag=false;
						
						break;
					}
				}
				if(stflag==true)
				{
				mapp[statecount]=prod[j][0].charAt(0);
				state[statecount++]=new State(x,y,prod[j][0].charAt(0),this,0,"");
				state[statecount-1].isfinal=false;
				
				if(j%2==0){y=y2;y2-=20;}
				else 
				{y=y1;y1+=20;}
				
				x+=70;
			}
			}*/
			
			
			int curstate=0,finstate=0;
			char cursymb;
			String temp="";
			transcount=0;
			char initsymb=prod[0][0].charAt(0);
			for(int j=0;j<=nop && prod[j][0].charAt(0)==initsymb;j++)
			{
				cursymb=prod[j][0].charAt(0);
				for(int k1=0;k1<statecount;k1++){if(state[k1].tag==prod[j][0].charAt(0)){stflag=false;curstate=k1;break;}}
				if(stflag!=false)
				{
					stflag=true;
					state[statecount++]=new State(x,y,prod[j][0].charAt(0),this,0,"");
					state[statecount-1].isfinal=false;
					curstate=statecount-1;
					System.out.println("i am at correct place222");
					if(statecount%2==0){
						y2-=20;
						y=y2;}
					else 
					{
						y1+=20;
						y=y1;
					}
					
					x+=70;
					
				}
				//for(int k=0;k<statecount;k++){if(cursymb==mapp[k]){curstate=k;break;}}
				stflag=true;
				for(int k=0;k<prod[j][1].length();k++)
				{
					if(prod[j][1].charAt(k)>=65 && prod[j][1].charAt(k)<91)
					{	
						stflag=true;
						int k1;
						for(k1=0;k1<statecount;k1++){if(state[k1].tag==prod[j][1].charAt(k)){stflag=false;finstate=k1;break;}}
						
						if(stflag==false)
							{
							trans[transcount++]= new Transition(curstate,finstate,temp,"","");
							curstate=finstate;
							}
						else 
						{
							System.out.println("i am at correct place");
							state[statecount++]=new State(x,y,prod[j][1].charAt(k),this,0,"");
							state[statecount-1].isfinal=false;
							if(statecount%2==0){y=y2;y2-=20;}
							else 
							{y=y1;y1+=20;}
							
							x+=70;
							finstate=statecount-1;
							trans[transcount++]= new Transition(curstate,finstate,temp,"","");
							curstate=genauto(finstate,state[k1].tag);
						}
						temp="";
						
						
						
					}
					else
					{
						temp+=prod[j][1].charAt(k);
					}
				}
				
				if(true)
				{
					state[statecount++]=new State(x,y,'#',this,0,"");
					state[statecount-1].isfinal=true;
					state[statecount-1].drawfinal();
					if(statecount%2==0){y=y2;y2-=20;}
					else 
					{y=y1;y1+=20;}
					
					x+=70;
					
					trans[transcount++]= new Transition(curstate,statecount-1,temp,"","");
					temp="";
				}
				
				
				
				
			}
			
			for(int j=0;j<transcount;j++)
			{
		System.out.println(trans[j].init+ "     "+trans[j].fin+ "     "+trans[j].symbol+ "     ");		
			}
			
			/// initial state
			int tx,ty;
			tx=state[initialstate].x+15;
			ty=state[initialstate].y+25;
			int px[]={tx-50,tx-20,tx-50,tx-50};
			int py[]={ty+20,ty,ty-20,ty+20};
		//	Graphics g=this.getGraphics();
			g.drawPolygon(px,py,4);
			
		//	repaintit(g);
			
			
			
			// drawing transitions------------------------
			for(int j=0;j<transcount;j++)
			{
			
				g.setColor(Color.red);
				g.drawLine((state[trans[j].init].x)+15,(state[trans[j].init].y)+25,(state[trans[j].fin].x)+15,(state[trans[j].fin].y)+25);
				
				
				g.setColor(Color.red);
				s1=trans[j].init;
				s2=trans[j].fin;
				if(s1!=s2)
				{
				double dy = state[s2].y - state[s1].y;  
		        double dx = state[s2].x - state[s1].x;  
		        double theta = Math.atan2(dy, dx);    
		        double xx, yy, rho = theta + Math.toRadians(40);   
		        for(int k = 0; k < 2; k++)  
		        {  
		            xx = state[s2].x+15 - 20 * Math.cos(rho);  
		            yy = state[s2].y+25 - 20 * Math.sin(rho);  
		            g.drawLine(state[s2].x+15, state[s2].y+25, (int)xx,(int) yy);  
		            rho = theta - Math.toRadians(40); 
		            
		        }  
		        g.setColor(Color.red);
		        if(trans[j].symbol=="") trans[j].symbol="e";
		        g.drawString(trans[j].symbol,(((state[s1].x+state[s2].x)/2)+state[s1].x)/2 +15 ,(((state[s1].y+state[s2].y)/2)+state[s1].y)/2+25);
		        g.setColor(Color.black);
				}
				else
				{
		        g.drawLine(state[s1].x+15,state[s1].y+25,state[s2].x+40,state[s2].y-20);
				g.drawLine(state[s2].x+40,state[s2].y-20,state[s2].x,state[s2].y-20);
				g.drawLine(state[s2].x,state[s2].y-20,state[s1].x+15,state[s1].y+25);
				g.drawLine(state[s1].x+15,state[s1].y+25,state[s1].x+5,state[s1].y+25-10);
				g.drawLine(state[s1].x+15,state[s1].y+25,state[s1].x+15,state[s1].y+25-15);
				g.drawString(trans[j].symbol,state[s1].x+15,state[s1].y-22);
				}
			}
			
			
			
			
			
			
			
			
		}
	    
		
	     
		else if(Interface.machine!=11)
	     {
	    	 repaintit(g);
		if(ToolBox.choice==0) 
		{
			flag=false;
			
			
			
			if(Interface.machine==2)
			{
			String output =  JOptionPane.showInputDialog(this,"Enter output symbol");
			state[i]=new State(x,y,i,this,0,output);
			}
			try{Thread.sleep(200);}
			catch(Exception e){}
			
			if(Interface.machine==0 || Interface.machine==1|| Interface.machine==3 )
			{
			state[i]=new State(x,y,i,this,0,"");
			}
			
			i++;
		}
		
			
		if(ToolBox.choice==1)     //getting transition
		{
			for(int j=0;j<i;j++)
			{
				if(x<state[j].x+55  && x>state[j].x-25 && y<state[j].y+65 && y>state[j].y-15)
				{
					
					if(flag==false)
					{
						state[j].drawit(3);
						s1=j;
						flag=true;
						System.out.print("\nfound s1=" + s1 +  "   " + state[s1].x + "\n");
					}
					else 
					{
					
						state[j].drawit(3);
						s2=j;
						
						System.out.print("\nfound s2=" + s2 +  "   " + state[s2].x+ "\n");
						String input =  JOptionPane.showInputDialog(this,"Enter input symbol");
						
						try{Thread.sleep(200);}catch(Exception e){};
						
						
						if(Interface.machine==1)
						{
						output =  JOptionPane.showInputDialog(this,"Enter output symbol");
						}
						else if(Interface.machine==3)
						{
							output =  JOptionPane.showInputDialog(this,"Enter top of stack");
							s=JOptionPane.showInputDialog(this,"Enter updated Symbol");
						
						}
						
						
						System.out.print("Drawing line");
						g.setColor(Color.BLACK);
					//	System.out.print(state[s1].x+15 +"   " +  state[s1].y+25 + "  " +   state[s2].x+15 + "   " +  state[s2].y+25);
						System.out.print("S1=" + s1 + " s2=" + s2);
						g.drawLine((state[s1].x)+15,(state[s1].y)+25,(state[s2].x)+15,(state[s2].y)+25);
					    
						
						if(Interface.machine==1)
						{
							trans[transcount]=new Transition(s1,s2,input,output,"");
						}
						else if(Interface.machine==3)
						{
							trans[transcount]=new Transition(s1,s2,input,output,s);
						
						}
						else
						{
						trans[transcount]=new Transition(s1,s2,input,"","");
						}
						transcount++;
						
						
						double dy = state[s2].y - state[s1].y;  
				        double dx = state[s2].x - state[s1].x;  
				        double theta = Math.atan2(dy, dx);    
				        double xx, yy, rho = theta + Math.toRadians(40);   
				        for(int k = 0; k < 2; k++)  
				        {  
				            xx = state[s2].x+15 - 10 * Math.cos(rho);  
				            yy = state[s2].y+25 - 10 * Math.sin(rho);  
				            g.drawLine(state[s2].x+15, state[s2].y+25, (int)xx,(int) yy);  
				            rho = theta - Math.toRadians(40); ;  
				        }  
				        
				        if(Interface.machine==1)
				        {
					        g.drawString(input+" / "+output ,(state[s1].x+state[s2].x)/2 +15 , (state[s1].y+state[s2].y)/2 +25);
				        }
				        else if(Interface.machine==3)
				        {
				        	g.drawString(input+","+ output+"-->"+s ,(state[s1].x+state[s2].x)/2 +15 , (state[s1].y+state[s2].y)/2 +25);
				        }
				        else
				        {
				        g.drawString(input,(state[s1].x+state[s2].x)/2 +15 , (state[s1].y+state[s2].y)/2 +25);
				        }
						state[s1].drawit(0);
						state[s2].drawit(0);
						flag=false;
					}
					
					
				}
			}
		}
		
		
		else if(ToolBox.choice==3)          // getting initial state
		{
			if(initialstate==-1)
			{
			
			for(int j=0;j<i;j++)
			{
				if(x<state[j].x+40  && x>state[j].x-40 && y<state[j].y+40 && y>state[j].y-40)
				{
					initialstate=j;
					int tx,ty;
					tx=state[j].x+15;
					ty=state[j].y+25;
					int px[]={tx-50,tx-20,tx-50,tx-50};
					int py[]={ty+20,ty,ty-20,ty+20};
				//	Graphics g=this.getGraphics();
					g.drawPolygon(px,py,4);
				}
			}
			}
			
		}
		
		
		else if(ToolBox.choice==4)           //getting final state
		{
			
			for(int j=0;j<i;j++)
			{
				if(x<state[j].x+40  && x>state[j].x-40 && y<state[j].y+40 && y>state[j].y-40)
				{
					state[j].isfinal=true;
					state[j].drawfinal();
					
					System.out.print(state[j].isfinal+"\n");
				}
			}
		}
	     }//System.out.println("<><> X: " + arg0.getX() + "; Y: " + arg0.getY() + " <><>" + ToolBox.choice);
	}

	
	void simulate_mealy()
	{
		repaint();
		String inp1=ToolBox.inp;
		int inplength=inp1.length();
		int current=initialstate,next,j;
		next=-1;
		rejectit=false;
		
		
		while(inplength>0)
		{	
			state[current].drawCurrent(inp1,inplength,"");
		     
		     for(j=0;j<transcount;j++)
		     {
		    	 if(trans[j].init==current && (trans[j].symbol).charAt(0)==inp1.charAt(inp1.length()-inplength))
		    	 {
		    		 next=trans[j].fin;
		    		 output+=trans[j].out;
		    		 break;
		    	 }
		     }
		     
		    if(j==transcount){rejectit=true;break;}
		    nostaterepaint=true;
		    repaint();
		    nostaterepaint=false;
		    try{Thread.sleep(1000);}catch(Exception e){}
		     
		     state[current].removecurrent(output);
		     
		     current=next;
		     inplength--;
		}
		
		/*
		if(state[current].isfinal && rejectit==false)
		{
			JOptionPane.showMessageDialog(new JFrame(),"Input String Accepted","Accepted",JOptionPane.PLAIN_MESSAGE);
		//	repaintit();
			
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(),"Input String Rejected","Rejected",JOptionPane.ERROR_MESSAGE);
			
			//repaintit();
		}
		*/
		state[current].drawCurrent(inp1,inplength,"");
		try{Thread.sleep(1000);}catch(Exception e){}
		state[current].removecurrent(output);
		inp1="";
		output="";
		nostaterepaint=false;
		
		repaint();

	}
	
	void simulate_pda()
	{
		repaint();
	
		Stack st=new Stack();

		st.push(new Character('$'));
		
		inp1=ToolBox.inp;
		//inp1=inp1+'$';
		int inplength=inp1.length();
		int current=initialstate,next,j;
		next=-1;
		rejectit=false;
		
		while(inplength>0)
		{	
			state[current].drawCurrent(inp1,inplength,stackarray);
		     System.out.println("My Transcount:"+transcount+" --"+st.peek()+ " inplength="+inplength);
		     for(j=0;j<transcount;j++)
		     {
		    System.out.println(trans[j].init  +  "   "+ current +  "   "+ (trans[j].symbol).charAt(0)+ "   "+ inp1.charAt(inp1.length()-inplength) +  "   "+ trans[j].out + "   "+  st.peek() );
		  	    
if(trans[j].init==current && (trans[j].symbol).charAt(0)==inp1.charAt(inp1.length()-inplength) && trans[j].out.equals((st.peek().toString())) )
		    	 {
		    		 next=trans[j].fin;
		    		 System.out.println("i am in");
		    		
		    		     st.pop();
		    		     salen--;
		    		     if(salen-1>=0)stackarray=stackarray.substring(0,salen-1);
		    		     else stackarray="$";
		    		     
		    			 if(trans[j].top.length()>1)
		    				 {
		    				 salen++;
		    				 stackarray=stackarray+(trans[j].top).charAt(1);
		    				 st.push(new Character((trans[j].top).charAt(1)));
		    				 System.out.println("stackarray is:"+stackarray+"  "+salen);
		    				 }
		    			 if(trans[j].top.length()>0)
		    				 {
		    				 salen++;
		    				 stackarray=stackarray+(trans[j].top).charAt(0);
		    				 st.push(new Character((trans[j].top).charAt(0)));
		    				 System.out.println("stackarray is:"+stackarray+"  "+salen);
		    				 }
		    			 
		    			 //repaint();
		    		 break;
		    	 }
		     }
		     
		   
		    nostaterepaint=true;
		    System.out.println("yeah rapainting : "+stackarray);
		    
		    
		    try{Thread.sleep(1000);}catch(Exception e){}
		    repaint();
		    if(j==transcount){rejectit=true;break;} 
		    nostaterepaint=false;
		   
		     
		     state[current].removecurrent(output);
		     
		     current=next;
		     inplength--;
		}
		
		
		repaint();
		if(state[current].isfinal && rejectit==false && (st.peek().toString()).equals("$"))
		{
			JOptionPane.showMessageDialog(new JFrame(),"Input String Accepted","Accepted",JOptionPane.PLAIN_MESSAGE);
		//	repaintit();
			
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(),"Input String Rejected","Rejected",JOptionPane.ERROR_MESSAGE);
			
			//repaintit();
		}
		state[current].removecurrent(output);
		inp1="";
		output="";
		nostaterepaint=false;
		//state[current].drawCurrent(inp1,inplength);
		repaint();
		
	
		
	}
	
	
	void simulate_moore()
	{
		repaint();
		String inp1=ToolBox.inp;
		int inplength=inp1.length();
		int current=initialstate,next,j;
		next=-1;
		rejectit=false;
		
		
		while(inplength>0)
		{	
			state[current].drawCurrent(inp1,inplength,"");
		    output+=state[current].output; 
		     for(j=0;j<transcount;j++)
		     {
		    	 if(trans[j].init==current && (trans[j].symbol).charAt(0)==inp1.charAt(inp1.length()-inplength))
		    	 {
		    		 next=trans[j].fin;
		    		 break;
		    	 }
		     }
		     
		    if(j==transcount){rejectit=true;break;}
		    nostaterepaint=true;
		    repaint();
		    nostaterepaint=false;
		    try{Thread.sleep(1000);}catch(Exception e){}
		     
		     state[current].removecurrent(output);
		     
		     current=next;
		     inplength--;
		}
		
	/*	if(state[current].isfinal && rejectit==false)
		{
			JOptionPane.showMessageDialog(new JFrame(),"Input String Accepted","Accepted",JOptionPane.PLAIN_MESSAGE);
		//	repaintit();
			
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(),"Input String Rejected","Rejected",JOptionPane.ERROR_MESSAGE);
			
			//repaintit();
		}
		
		*/
		state[current].removecurrent(output);
		inp1="";
		output="";
		nostaterepaint=false;
		repaint();
	}

	
	public void makeAutoMata(String[][] pro,MainArea ma,int nop)
	{
		this.prod=pro;
		this.nop=nop;
		
		for(int j=0;j<=nop;j++)
		{
			System.out.println(prod[j][0]+"--->"+prod[j][1]);
		}		
}
	
	public String toBinary(int x)
	{
		if(x<2)
			return x+"";
		return(toBinary(x/2)+(x%2));		
	}
	
	public void drawtape(Graphics g,int width,char[] tape,int head,int length,int step)
	{
		g.setColor(Color.white);
		Image im;
		g.fillRect(325,250,250,50);
		g.fillRect(10,200,900,60);
		for(int i=0;i<length;i++)
		{
		    if(i==head)g.setColor(Color.red);
			g.fillRect(20+width*i, 200, width, 40);
			g.setColor(Color.black);
			g.drawRect(20+width*i, 200, width, 40);
			g.setColor(Color.white);
		}
		
		Font f1;
		f1=g.getFont();
		g.setColor(Color.black);
		g.setFont(new Font("Times New Roman",Font.PLAIN,20));
		g.drawString("Number Of Steps:  "+step,330 ,275 );
		
		for(int i=0;i<length;i++)
		{
			g.drawString(""+tape[i],30+width*i, 235);
		}
		g.setFont(f1);
	}

	public void simulateAddTuringMachine(String x,String y)
	{
		int step=0;
		x=toBinary(Integer.parseInt(x));		
		y=toBinary(Integer.parseInt(y));
		
		String S = "*#"+x+"##"+y+"#";
		System.out.println(S);
		tape=S.toCharArray();
		for(int i=0;i<S.length();i++)
		{
			System.out.println(tape[i]);
		}
		int head=0;
	
		boolean tflag=true;
		int zero;
		
		
		Graphics g=this.getGraphics();
		int width=(int)800/S.length();
		
		drawtape(g,width,tape,head,S.length(),step);
		
		while(tflag)
		{
			if(tape[1]=='#')
			{
			while(tape[head]!='#')
			{
				head++;
				step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			}
			head++;
			step++;
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			while(tape[head]!='#')
			{
				head++;
				step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			head--;step++;
			
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			
			while(tape[head]!='0' && tape[head]!='#')
			{
				tape[head]='0';
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
				head--;
				step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			
			tape[head]='1';
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			while(tape[head]!='#')
			{
				head++;step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			} 
			head++;step++;
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			head++;step++;
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			
			while(tape[head]!='#')
			{
				head++;step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			
			head--;step++;
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			
			
			while(tape[head]!='1' && tape[head]!='#')
			{
			    tape[head]='1';
			    drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
				head--;step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			if(tape[head]=='#')
			{
				tape[head]='0';
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			else
			{
				tape[head]='0';
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
				while(tape[head]!='#'){head--;step++;drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}}
				
			}
			
			
			
			
				while(tape[head]!='#'){head--;step++;drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}}
				head--;step++;drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
				
			
			
			head++;
			step++;
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			head++;
			step++;
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			zero=0;
			while(tape[head]!='#')
			{
			
				if(tape[head]=='1')
				{
					zero=1;
				}
				head++;step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			
			if(zero==0)
				tflag=false;
				
			while(tape[head]!='*')
			{
				head--;step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			drawtape(g,width,tape,head,S.length(),step);
		try{Thread.sleep(500);}catch(Exception e){}
		}
	
		//g.drawString("no of steps:"+step,200,550);
		
		for(int i=0;i<S.length();i++)
		{
			System.out.println(tape[i]);
		}
		//System.out.println(tape.toString());

	}
	
	public void simulateSubTuringMachine(String x, String y)
	{
		int step=0;
		x=toBinary(Integer.parseInt(x));		
		y=toBinary(Integer.parseInt(y));
		
		String S = "*#"+x+"##"+y+"#";
		System.out.println(S);
		char tape[]=S.toCharArray();
		for(int i=0;i<S.length();i++)
		{
			System.out.println(tape[i]);
		}
		int head=0;
	
		boolean tflag=true;
		int zero;
		
		
		Graphics g=this.getGraphics();
		int width=(int)800/S.length();
		
		drawtape(g,width,tape,head,S.length(),step);
		
		while(tflag)
		{
			if(tape[1]=='#')
			{
			while(tape[head]!='#')
			{
				head++;
				step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			}
			head++;
			step++;
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			while(tape[head]!='#')
			{
				head++;
				step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			head--;step++;
			
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			
			while(tape[head]!='1' && tape[head]!='#')
			{
				tape[head]='1';
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
				head--;
				step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			
			tape[head]='0';
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			while(tape[head]!='#')
			{
				head++;step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			} 
			head++;step++;
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			head++;step++;
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			
			while(tape[head]!='#')
			{
				head++;step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			
			head--;step++;
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			
			
			while(tape[head]!='1' && tape[head]!='#')
			{
			    tape[head]='1';
			    drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
				head--;step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			if(tape[head]=='#')
			{
				tape[head]='0';
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			else
			{
				tape[head]='0';
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
				while(tape[head]!='#'){head--;step++;drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}}
				
			}
			
			
			
			
				while(tape[head]!='#'){head--;step++;drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}}
				head--;step++;drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
				
			
			
			head++;
			step++;
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			head++;
			step++;
			drawtape(g,width,tape,head,S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
			zero=0;
			while(tape[head]!='#')
			{
			
				if(tape[head]=='1')
				{
					zero=1;
				}
				head++;step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			
			if(zero==0)
				tflag=false;
				
			while(tape[head]!='*')
			{
				head--;step++;
				drawtape(g,width,tape,head,S.length(),step);
				try{Thread.sleep(500);}catch(Exception e){}
			}
			drawtape(g,width,tape,head,S.length(),step);
		try{Thread.sleep(500);}catch(Exception e){}
		}
	
		//g.drawString("no of steps:"+step,200,550);
		
		for(int i=0;i<S.length();i++)
		{
			System.out.println(tape[i]);
		}
		//System.out.println(tape.toString());
		
	}
	
	public void makedfa(String regex,MainArea ma)
	{
		String postreg="";
		char exp[]= regex.toCharArray(); 
		
		Stack st1= new Stack();
	
		for (int i=0;i<regex.length();i++)
		{
			switch(exp[i])
			{
			case '*':
				if(st1.isEmpty()==false)
				{
				      while((st1.peek().toString().charAt(0))=='*')
				      {
				    	  char c=st1.pop().toString().charAt(0);
				    	  postreg+=c;
				    	  if(st1.isEmpty())break;
				      }
				}
				      st1.push('*');
				      break;
				      
			case '.':
				if(st1.isEmpty()==false)
				{
				while((st1.peek().toString().charAt(0))=='*' || (st1.peek().toString().charAt(0))=='.' )
			      {
			    	  char c=st1.pop().toString().charAt(0);
			    	  postreg+=c;
			    	  if(st1.isEmpty())break;
			      }
				}
				st1.push('.');
			      break;
			      
			case '+':
				if(st1.isEmpty()==false)
				{
				while((st1.peek().toString().charAt(0))=='*' || (st1.peek().toString().charAt(0))=='.' ||(st1.peek().toString().charAt(0))=='+' )
			      {
			    	  char c=st1.pop().toString().charAt(0);
			    	  postreg+=c;
			    	  if(st1.isEmpty())break;
			      }
				}
				st1.push('+');
			      break;
			 
			case '(':
					st1.push('(');
					break;
			
			case ')':
				if(st1.isEmpty()==false)
				{
					while((st1.peek()).toString().charAt(0)!='(')
					{
						char c=st1.pop().toString().charAt(0);
						postreg+=c;
						  if(st1.isEmpty())break;
				     }
				}
					st1.pop();
					break;
		   default: 
			    	  postreg+=exp[i];
			    	  break;
				
			}
			
		}
		while(st1.isEmpty()==false)
		{
			char c=st1.pop().toString().charAt(0);
			postreg+=c;
		}
		System.out.println("");
		System.out.println("regex is"+regex+"   postreg:  "+postreg);
		
		
		
/*------------------------------------------------- evaluate-------------------------------------------------------------------*/		
		
		
		st1=new Stack();
		char a1,b1;
		char pexp[]=postreg.toCharArray();
		
		
		
		statecount=0;
	
		for(i=0;i<postreg.length();i++)
		{
			
			switch(pexp[i])
			{
			case '*':
				
	                  t1=(Transition[])st1.pop(); 
	                  int intemp=0,prevtotal;
	                  
	                  prevtotal=t1[0].total;
	                  
	                  for(int j=0;j<t1[0].total;j++)
					   {
						   if(t1[j].isinit)
						   {
							intemp=t1[j].init;
							break;
						   }
					   }
	                  
	                  for(int j=0;j<t1[0].total;j++)
					   {
						   if(t1[j].isfin)
						   {
							   t1[prevtotal]=new Transition(t1[j].fin,intemp,"&","","");
							   t1[prevtotal].isfin=true;
							   t1[prevtotal].isinit=false;
							   prevtotal++;
						   }
					   }
	                  
	                  t1[0].total=prevtotal;
	              
	                  
	                  st1.push(t1);
				      break;
				      
			case '.':
				
				   t2=(Transition[])st1.pop();
				   t1=(Transition[])st1.pop();
				   
          intemp=0;
				   
				   
				   
				   for(int j=0;j<t2[0].total;j++)
				   {
					   if(t2[j].isinit)
					   {
						   intemp=t2[j].init;
						   t2[j].isinit=false;
						   break;
					   }
				   }
				   
				   prevtotal=t1[0].total;
				   
				   for(int j=0;j<t1[0].total;j++)
				   {
					   if(t1[j].isfin)
					   {
						   t1[prevtotal]=new Transition(t1[j].fin,intemp,"&","","");
						   t1[prevtotal].isfin=false;
						   t1[prevtotal].isinit=false;
						   t1[j].isfin=false;
						   prevtotal++;
					   }
				   }
				   
				   
				   t1[0].total=prevtotal+t2[0].total;
				   
				   for(int j=prevtotal;j<t1[0].total;j++)
				   {
					   t1[j]=t2[j-prevtotal];
				   }
				   
				   st1.push(t1);
			      break;
			      
			case '+':

				   t2=(Transition[])st1.pop();
				   t1=(Transition[])st1.pop();
				   
int init1=0,init2=0;
				   
				   
				 
				   for(int j=0;j<t2[0].total;j++)
				   {
					   if(t2[j].isinit)
					   {
						   init2=t2[j].init;
						   t2[j].isinit=false;
					   }
				   }
				   
				   for(int j=0;j<t1[0].total;j++)
				   {
					   if(t1[j].isinit)
					   {
						   init1=t1[j].init;
						   t1[j].isinit=false;
					   }
				   }
				   
				   t1[t1[0].total]=new Transition(statecount,init1,"&","","");
				   t1[t1[0].total].isinit=true;
				   t1[t1[0].total].isfin=false;
				   t1[t1[0].total+1]=new Transition(statecount,init2,"&","","");
				   t1[t1[0].total+1].isinit=true;
				   t1[t1[0].total+1].isfin=false;
				   
				   statecount+=1;
				   prevtotal=t1[0].total+2;
				   t1[0].total+=2+t2[0].total;
				   
				   
				   for(int j=prevtotal;j<t1[0].total;j++)
				   {
					   t1[j]=t2[j-prevtotal];
				   }
				   
				   st1.push(t1);
			      break;
			 
			
		   default:	   
			   t1=new Transition[20];
			         t1[0]=new Transition(statecount,statecount+1,""+pexp[i],"","");
			         t1[0].total=1;
			         t1[0].isinit=true;
			         t1[0].isfin=true;
			    	 st1.push(t1);
			    	 statecount+=2;
			    		
			    	  break;
				
			}
		   
		}
		
		System.out.println("----------------------------"); 
		t1=(Transition[])st1.peek();
		for(int j=0;j<t1[0].total;j++)
		{
	System.out.println(t1[j].init+ "     "+t1[j].fin+ "     "+t1[j].symbol+ "     "+t1[j].isinit+ "     "+t1[j].isfin);		
		}
	
	repaint();
		
	
		
	}
	
	
    public void simulateTM(char tape[])
	{
    	this.removeMouseListener(this);
		int i;
		int currentstate=0;
		int head=0,step=0;
		boolean flag=true;
		currentstate=initialstate;
		
		Graphics g=this.getGraphics();
		int width=(int)800/tape.length;
		drawtape(g,width,tape,head,tape.length,step);
		while(flag)
		{
		for(i=0;i<transcount;i++)
		{
			
			if(trans[i].init==currentstate && trans[i].symbol.charAt(0)==tape[head])
			{
				currentstate=trans[i].fin;
				tape[head]=trans[i].out.charAt(0);
				if(trans[i].dir=='L'){head--;step++;}
				if(trans[i].dir=='R'){head++;step++;}
				try{Thread.sleep(200);}catch(Exception e){}
				drawtape(g,width,tape,head,tape.length,step);
			}
			
			
			if(state[currentstate].isaccepting==true || state[currentstate].isrejecting==true)flag=false;
			
		}
		}
		
		Font f1;
		f1=g.getFont();
		
		g.setFont(new Font("Times New Roman",Font.PLAIN,25));
		Interface.machine=19;
		if(state[currentstate].isaccepting==true){g.setColor(Color.green);g.drawString("String Accepted",330,330);}
		else if(state[currentstate].isrejecting==true){g.setColor(Color.red);g.drawString("String Rejected",330,330);}
		g.setColor(Color.black);
	}
	

    public void simulateTM1(char tape[],char c)
	{
    	this.removeMouseListener(this);
		int i;
		int currentstate=0;
		int head=0,step=0;
		boolean flag=true;
		
		Graphics g=this.getGraphics();
		int width=(int)800/tape.length;
		drawtape(g,width,tape,head,tape.length,step);
		while(tape[head]!=c)
		      {
			   head++;
			   step++;
			   drawtape(g,width,tape,head,tape.length,step);
			   try{Thread.sleep(200);}catch(Exception e){}
				
		      }
		currentstate=initialstate;
		
		
		
		while(flag)
		{
		for(i=0;i<transcount;i++)
		{
			
			if(trans[i].init==currentstate && trans[i].symbol.charAt(0)==tape[head])
			{   
				currentstate=trans[i].fin;
				tape[head]=trans[i].out.charAt(0);
				if(trans[i].dir=='L'){head--;step++;}
				if(trans[i].dir=='R'){head++;step++;}
				try{Thread.sleep(200);}catch(Exception e){}
				drawtape(g,width,tape,head,tape.length,step);
			}
			
			
			if(state[currentstate].isaccepting==true || state[currentstate].isrejecting==true)flag=false;
			
		}
		}
		
	}

    public void simulatePower2TuringMachine(String xx)
	{
		String S = "*"+xx+"###";
		
		
		tape=S.toCharArray();
	    x=50;
	    y=50;
	    y1=50;
	    y2=400;
		y=y1;
		statecount=8;
		for(int j=0;j<statecount;j++)
		{
			state[j]=new State(x,y,j,this,0,"");
			if(j%2==0){y=y2;y2-=20;}
			else 
			{y=y1;y1+=20;}
			
			x+=70;
			state[j].isaccepting=false;
			state[j].isrejecting=false;
		}
		
		state[6].isaccepting=true;
		state[7].isrejecting=true;
		initialstate=0;
    	transcount=0;
    	trans[transcount]=new Transition(0,1,"*","*","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(1,2,"0","0","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,2,"1","1","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,6,"#","#","");trans[transcount].dir='S';transcount++;
    	trans[transcount]=new Transition(2,3,"0","1","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(3,4,"0","0","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(3,3,"1","1","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(4,3,"0","1","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(4,4,"1","1","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(4,7,"#","#","");trans[transcount].dir='S';transcount++;
    	trans[transcount]=new Transition(3,5,"#","#","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(5,5,"1","1","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(5,5,"0","0","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(5,1,"*","*","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(1,1,"1","1","");trans[transcount].dir='R';transcount++;
    	
    	for(int i=0;i<S.length();i++)
    	{
    		System.out.println(tape[i]);
    	}
        simulateTM(tape);
		
	}


	public void TuringCopier(String x) 
	{
		
		String S = "*"+x+"#";
		int step=0;
		
		System.out.println(S);
		char tape[]=new char[2*S.length()];
		char c[]=S.toCharArray();
		for(int i=0;i<S.length();i++)
		{
			tape[i]=c[i];
		}
		for(int i=S.length();i<2*S.length();i++)
		{
			tape[i]='#';
		}
		int head=0;
		char memory;
		Graphics g=this.getGraphics();
		int width=(int)800/(2*S.length());
		
		drawtape(g,width,tape,head,2*S.length(),step);
		try{Thread.sleep(500);}catch(Exception e){}
		head++;
		step++;
		drawtape(g,width,tape,head,2*S.length(),step);
		try{Thread.sleep(500);}catch(Exception e){}
		while(tape[head]!='#')
		{
			
		memory=tape[head];
		tape[head]='#';
		drawtape(g,width,tape,head,2*S.length(),step);
		try{Thread.sleep(500);}catch(Exception e){}
		head++;
		step++;
		drawtape(g,width,tape,head,2*S.length(),step);
		try{Thread.sleep(500);}catch(Exception e){}
		while(tape[head]!='#')
		{
			head++;
			step++;
			drawtape(g,width,tape,head,2*S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
		}
		head++;
		step++;
		drawtape(g,width,tape,head,2*S.length(),step);
		try{Thread.sleep(500);}catch(Exception e){}
		while(tape[head]!='#')
		{
			head++;
			step++;
			drawtape(g,width,tape,head,2*S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
		}
		
		tape[head]=memory;
		drawtape(g,width,tape,head,2*S.length(),step);
		try{Thread.sleep(500);}catch(Exception e){}
		while(tape[head]!='#')
		{
			head--;
			step++;
			drawtape(g,width,tape,head,2*S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
		}
		head--;
		step++;
		drawtape(g,width,tape,head,2*S.length(),step);
		try{Thread.sleep(500);}catch(Exception e){}
		while(tape[head]!='#')
		{
			head--;
			step++;
			drawtape(g,width,tape,head,2*S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
		}
		tape[head]=memory;
		drawtape(g,width,tape,head,2*S.length(),step);
		try{Thread.sleep(500);}catch(Exception e){}
		head++;
		step++;
		drawtape(g,width,tape,head,2*S.length(),step);
		try{Thread.sleep(500);}catch(Exception e){}
		
		
		}
		
		while(tape[head]!='*')
		{
			head--;step++;
			drawtape(g,width,tape,head,2*S.length(),step);
			try{Thread.sleep(500);}catch(Exception e){}
		}
		
		
		for(int i=0;i<2*S.length();i++)
		{
			System.out.println(tape[i]);
		}
		
	}


	public void simulatePalindromeTuring(String xx) 
	{
        String S = "*#"+xx+"###";
		
		
		tape=S.toCharArray();
	    x=50;
	    y=50;
	    y1=50;
	    y2=400;
		y=y1;
		statecount=10;
		for(int j=0;j<statecount;j++)
		{
			state[j]=new State(x,y,j,this,0,"");
			if(j%2==0){y=y2;y2-=20;}
			else 
			{y=y1;y1+=20;}
			
			x+=70;
			state[j].isaccepting=false;
			state[j].isrejecting=false;
		}
		state[9].isrejecting=true;
		state[8].isaccepting=true;
		initialstate=0;
    	transcount=0;
    	trans[transcount]=new Transition(0,1,"*","*","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(1,2,"#","#","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,3,"a","#","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,6,"b","#","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,8,"#","#","");trans[transcount].dir='S';transcount++;
    	trans[transcount]=new Transition(3,3,"a","a","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(3,3,"b","b","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(3,4,"#","#","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(4,8,"#","#","");trans[transcount].dir='S';transcount++;
    	trans[transcount]=new Transition(4,5,"a","#","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(5,5,"a","a","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(5,5,"b","b","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(5,2,"#","#","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(6,6,"a","a","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(6,6,"b","b","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(6,7,"#","#","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(7,5,"b","#","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(7,8,"#","#","");trans[transcount].dir='S';transcount++;
    	trans[transcount]=new Transition(4,9,"b","b","");trans[transcount].dir='S';transcount++;
    	trans[transcount]=new Transition(7,9,"a","a","");trans[transcount].dir='S';transcount++;
    	
    	
    	for(int i=0;i<S.length();i++)
    	{
    		System.out.println(tape[i]);
    	}
        simulateTM(tape);

		
	}


	public void simulateDeleteTuring(String xx, char c)
	{
		String S = "*#"+xx+"###";
		
		
		tape=S.toCharArray();
	    x=50;
	    y=50;
	    y1=50;
	    y2=400;
		y=y1;
		statecount=7;
		for(int j=0;j<statecount;j++)
		{
			state[j]=new State(x,y,j,this,0,"");
			if(j%2==0){y=y2;y2-=20;}
			else 
			{y=y1;y1+=20;}
			
			x+=70;
			state[j].isaccepting=false;
			state[j].isrejecting=false;
		}
		state[6].isaccepting=true;
		initialstate=1;
    	transcount=0;
    	trans[transcount]=new Transition(0,1,"*","*","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(1,2,"a","#","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(1,2,"b","#","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(1,2,"#","#","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,2,"a","a","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,2,"b","b","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,3,"#","#","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(3,4,"a","#","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(3,5,"b","#","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(3,6,"#","#","");trans[transcount].dir='S';transcount++;
    	trans[transcount]=new Transition(4,4,"a","a","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(4,5,"b","a","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(4,6,"#","a","");trans[transcount].dir='S';transcount++;
    	trans[transcount]=new Transition(5,5,"b","b","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(5,4,"a","b","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(5,6,"#","b","");trans[transcount].dir='S';transcount++;
    	
    	for(int i=0;i<S.length();i++)
    	{
    		System.out.println(tape[i]);
    	}
        simulateTM1(tape,c);


		
	}


	public void simulateEraserTuring(String xx)
	{
		String S = "*#"+xx+"###";
		
		
		tape=S.toCharArray();
	    x=50;
	    y=50;
	    y1=50;
	    y2=400;
		y=y1;
		statecount=5;
		for(int j=0;j<statecount;j++)
		{
			state[j]=new State(x,y,j,this,0,"");
			if(j%2==0){y=y2;y2-=20;}
			else 
			{y=y1;y1+=20;}
			
			x+=70;
			state[j].isaccepting=false;
			state[j].isrejecting=false;
		}
		state[4].isaccepting=true;
		initialstate=0;
    	transcount=0;
    	trans[transcount]=new Transition(0,1,"*","*","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(1,2,"#","#","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,2,"a","a","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,2,"b","a","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,3,"#","#","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(3,3,"a","#","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(3,4,"#","#","");trans[transcount].dir='S';transcount++;
    	
    	for(int i=0;i<S.length();i++)
    	{
    		System.out.println(tape[i]);
    	}
        simulateTM1(tape,'*');
		
	}


	public void simulateIncrementerTuring(String xx)
	{
		xx=toBinary(Integer.parseInt(xx));
		
String S = "*#"+xx+"###";
		
		
		tape=S.toCharArray();
	    x=50;
	    y=50;
	    y1=50;
	    y2=400;
		y=y1;
		statecount=5;
		for(int j=0;j<statecount;j++)
		{
			state[j]=new State(x,y,j,this,0,"");
			if(j%2==0){y=y2;y2-=20;}
			else 
			{y=y1;y1+=20;}
			
			x+=70;
			state[j].isaccepting=false;
			state[j].isrejecting=false;
		}
		state[4].isaccepting=true;
		initialstate=0;
    	transcount=0;
    	trans[transcount]=new Transition(0,1,"*","*","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(1,2,"#","#","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,2,"0","0","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,2,"1","1","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,3,"#","#","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(3,3,"1","0","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(3,4,"#","1","");trans[transcount].dir='S';transcount++;
    	trans[transcount]=new Transition(3,4,"0","1","");trans[transcount].dir='S';transcount++;
    	
    	for(int i=0;i<S.length();i++)
    	{
    		System.out.println(tape[i]);
    	}
        simulateTM1(tape,'*');
		
	}


	public void simulateDecrementerTuring(String xx)
	{
		xx=toBinary(Integer.parseInt(xx));
		
		String S = "*#"+xx+"###";
				
				
				tape=S.toCharArray();
			    x=50;
			    y=50;
			    y1=50;
			    y2=400;
				y=y1;
				statecount=5;
				for(int j=0;j<statecount;j++)
				{
					state[j]=new State(x,y,j,this,0,"");
					if(j%2==0){y=y2;y2-=20;}
					else 
					{y=y1;y1+=20;}
					
					x+=70;
					state[j].isaccepting=false;
					state[j].isrejecting=false;
				}
				state[4].isaccepting=true;
				initialstate=0;
		    	transcount=0;
		    	trans[transcount]=new Transition(0,1,"*","*","");trans[transcount].dir='R';transcount++;
		    	trans[transcount]=new Transition(1,2,"#","#","");trans[transcount].dir='R';transcount++;
		    	trans[transcount]=new Transition(2,2,"0","0","");trans[transcount].dir='R';transcount++;
		    	trans[transcount]=new Transition(2,2,"1","1","");trans[transcount].dir='R';transcount++;
		    	trans[transcount]=new Transition(2,3,"#","#","");trans[transcount].dir='L';transcount++;
		    	trans[transcount]=new Transition(3,3,"0","1","");trans[transcount].dir='L';transcount++;
		    	trans[transcount]=new Transition(3,4,"#","0","");trans[transcount].dir='S';transcount++;
		    	trans[transcount]=new Transition(3,4,"1","0","");trans[transcount].dir='S';transcount++;
		    	
		    	for(int i=0;i<S.length();i++)
		    	{
		    		System.out.println(tape[i]);
		    	}
		        simulateTM1(tape,'*');

		
	}


	public void simulateEqual0and1Turing(String xx) 
	{
		String S = "*#"+xx+"###";
		
		
		tape=S.toCharArray();
	    x=50;
	    y=50;
	    y1=50;
	    y2=400;
		y=y1;
		statecount=8;
		for(int j=0;j<statecount;j++)
		{
			state[j]=new State(x,y,j,this,0,"");
			if(j%2==0){y=y2;y2-=20;}
			else 
			{y=y1;y1+=20;}
			
			x+=70;
			state[j].isaccepting=false;
			state[j].isrejecting=false;
		}
		state[6].isaccepting=true;
		state[7].isrejecting=true;
		initialstate=0;
    	transcount=0;
    	trans[transcount]=new Transition(0,1,"*","*","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(1,2,"#","#","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,2,"A","A","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,3,"0","A","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,4,"1","A","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(2,6,"#","#","");trans[transcount].dir='S';transcount++;
    	trans[transcount]=new Transition(3,3,"A","A","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(3,3,"0","0","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(3,5,"1","A","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(4,4,"A","A","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(4,4,"1","1","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(4,5,"0","A","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(5,5,"0","0","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(5,5,"1","1","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(5,5,"A","A","");trans[transcount].dir='L';transcount++;
    	trans[transcount]=new Transition(5,2,"#","#","");trans[transcount].dir='R';transcount++;
    	trans[transcount]=new Transition(4,7,"#","#","");trans[transcount].dir='S';transcount++;
    	trans[transcount]=new Transition(3,7,"#","#","");trans[transcount].dir='S';transcount++;
    	
    	for(int i=0;i<S.length();i++)
    	{
    		System.out.println(tape[i]);
    	}
        simulateTM(tape);
		
	}
	
	
}
