


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.*;

public class GraphicProcess extends JPanel {

	private Color[] color= new Color[MAX_SIZE];
	private Rectangle2D.Float[] myRect = new Rectangle2D.Float[MAX_SIZE] ;
	private JLabel [] PIDlabel = new JLabel[MAX_SIZE];
	JLabel CPU;
	JLabel Disk1;
	JLabel Disk2;
	int size=0;
	int lastXCPU=30;
	int lastXDisk1=30;
	int lastXDisk2=30;
	int PID_CPU=-1;
	int PID_DISK=-1;
	public static final int MAX_SIZE=200;
	public static final int CPU_Y=40;
	public static final int DISK1_Y=100;
	public static final int DISK2_Y=160;
	public static final int WIDTH=20;
	public static final int TICK_LENGTH=30;

	

	public GraphicProcess() {
		Arrays.fill(color, null);
		Arrays.fill(myRect, null);
		Arrays.fill(PIDlabel, null);
		CPU = new JLabel("CPU");
		Disk1 = new JLabel("Disk1");
		Disk2 = new JLabel("Disk2");
		add(CPU);
		add(Disk1);
		add(Disk2);
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		CPU.setBounds(0, CPU_Y-10, 40, 40);
		Disk1.setBounds(0, DISK1_Y-10, 40, 40);
		Disk2.setBounds(0, DISK2_Y-10, 40, 40);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		for(int i=0; i<size; i++)
		{	
			
			g2d.setColor(color[i]);
			g2d.fill(myRect[i]);
			add(PIDlabel[i]);
			repaint();
		}
		
	}

	public void redraw(int index, int x, int y, int length, Color color)
	{
		myRect[index].width=length;
		myRect[index].y=y;
		myRect[index].x=x;
		this.color[index] = color;
		repaint();
	}
	public void addRect( int x, int y, int length, Color color, int PID)
	{
		myRect[size]=new Rectangle2D.Float(x, y, length, WIDTH);
		this.color[size]= color;
		PIDlabel[size]= new JLabel(String.valueOf(PID));
		PIDlabel[size].setBounds(x+10, y-30, 40, 40);
		size++;
		repaint();
	}

	public void processOnCPU(int PID)
	{
		PID_CPU=PID;
	}
	public void processLostCPU (int PID)
	{
		addRect(lastXCPU, CPU_Y, TICK_LENGTH, getPIDcolor(PID), PID);
		lastXCPU+=TICK_LENGTH;
	}

	private Color getPIDcolor(int PID) {
		if(PID%2==0){
			if(PID%3==0)
				return Color.RED;
			else		
				return Color.GREEN;
		}
		else{
			if(PID%3==0)		
				return Color.BLUE;
			else
				return Color.PINK;
		}
	}

	public void processOnDiskOne(int PID)
	{
		PID_DISK=PID;
	}
	public void processDoneDiskOne (int PID, int ticks)
	{
		addRect(lastXDisk1, DISK1_Y, TICK_LENGTH*ticks, getPIDcolor(PID), PID);
		lastXDisk1+=(TICK_LENGTH*ticks);
	
	}
	
	public void processOnDiskTwo(int PID)
	{
		PID_DISK=PID;
	}
	public void processDoneDiskTwo (int PID, int ticks)
	{
		addRect(lastXDisk2, DISK2_Y, TICK_LENGTH*ticks, getPIDcolor(PID), PID);
		lastXDisk2+=(TICK_LENGTH*ticks);
	
	}

	public void initGraphicProcessWindow(GraphicProcess m)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("processGraphic");
		Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		frame.setSize(maxBounds.width-20, 300); //maximiza na horizontal
		JScrollPane scrollPane=new JScrollPane(m);
		scrollPane.setPreferredSize(new Dimension(maxBounds.width-20,300));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		frame.add(scrollPane);  
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.setLocation(10, maxBounds.height-220);
		frame.pack();
		frame.setVisible(true);
	}


	//set ui visible//
//	public static void main(String args[]) {
//
//		GraphicProcess m = new GraphicProcess();
//		m.initGraphicProcessWindow(m);
//
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		String user_input;
//		while(true){
//			try {
//				user_input = reader.readLine();
//				if(user_input.equals("redraw")){
//					user_input = reader.readLine();
//					m.processLostCPU(Integer.parseInt(user_input));
//				}
//				if(user_input.equals("add")){
//					user_input = reader.readLine();
//					int PID =Integer.parseInt(user_input);
//					user_input = reader.readLine();
//					int ticks =Integer.parseInt(user_input);
//					m.processDoneDisk(PID, ticks);
//				}
//			} catch (IOException e) {
//				
//				e.printStackTrace();
//			}
//		}


	//}









}



