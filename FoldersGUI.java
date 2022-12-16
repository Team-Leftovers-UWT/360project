/**
 * This class creates the frame for the 2nd user story 
 * which categorizes the folders.
 * This class is called by AppGUI
 *
 * @author Deep Singh
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FoldersGUI {
	/** 
	 * creates the frame and initializes all buttons used
	 * @author Deep
	 */
	public static void start() {
		JFrame frame = new JFrame("Select Folder");
		frame.setVisible(true);
		frame.setSize(550, 350);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel temppanel = new JPanel();
		JPanel panel = new JPanel();
		panel.setSize(550,350);
		frame.add(panel);
		panel.setLayout(new BorderLayout());
		panel.add(temppanel, BorderLayout.CENTER);
		temppanel.setLayout(new GridLayout(3,2));
		
		JButton bathroom = new JButton("Bathroom");
		JButton bedroom = new JButton("Bedroom");
		JButton garage = new JButton("Garage");
		JButton kitchen = new JButton("Kitchen");
		JButton livingroom = new JButton("Living Room");
		JButton other = new JButton("Other");
		
		JButton back = new JButton("Back");
		//back.setPreferredSize(new Dimension(20,20));
		panel.add(back, BorderLayout.SOUTH);
		
		temppanel.add(bathroom); temppanel.add(bedroom); temppanel.add(garage);
		temppanel.add(kitchen); temppanel.add(livingroom); temppanel.add(other);
		
		bathroom.addActionListener(e -> {
			frame.dispose();
			Iteration2 theGUI = new Iteration2();
        });
		
		bedroom.addActionListener(e -> {
			frame.dispose();
			Iteration2 theGUI = new Iteration2();
        });
		
		garage.addActionListener(e -> {
            frame.dispose();
            Iteration2 theGUI = new Iteration2();
        });
		
		kitchen.addActionListener(e -> {
			frame.dispose();
			Iteration2 theGUI = new Iteration2();
        });
		
		livingroom.addActionListener(e -> {
			frame.dispose();
			Iteration2 theGUI = new Iteration2();
        });
		
		other.addActionListener(e -> {
			frame.dispose();
			Iteration2 theGUI = new Iteration2();
        });
		
		back.addActionListener(e -> {
			frame.dispose();
            AppGUI theGUI = new AppGUI();
        });
	
	}
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        @Override
	       public void run() {
	            FoldersGUI myFrame = new FoldersGUI();
	            myFrame.start();
	        }
	    });
	}
}
