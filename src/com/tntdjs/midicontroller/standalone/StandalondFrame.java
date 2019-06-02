package com.tntdjs.midicontroller.standalone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tntdjs.mediaplayer.MediaPlayerController;
import com.tntdjs.utils.SystemPropertyMgr;
import com.tntdjs.utils.i18n.TranslationMgr;

/**
 * MidiControllerStandalondFrame
 * @author tsenausk
 *
 */
public class StandalondFrame extends JFrame implements ActionListener {
	private static final Logger LOGGER = LogManager.getLogger(StandalondFrame.class.getName());
	private static final long serialVersionUID = 1L;
	private JTextArea NORTH_MSG = new JTextArea();
	private JPanel ROOT_PANEL = new JPanel();
	private JButton soundTest = new JButton("Sound Test");
	private static final String SoundTest = "SoundTest"; 
	
	public StandalondFrame(int frameHeight, int frameWidth) {
		LOGGER.info("Create JFrame");
		setTitle(TranslationMgr.getInstance().getAppTitle());
		setBackground(Color.WHITE);
		
		try {
			ImageIcon im = new ImageIcon(ImageIO.read(new File("res/images/app-icon-trans.png")));
			this.setIconImage(im.getImage());
		} catch (IOException e) {
			LOGGER.error("Error loading app-icon", e);
		}

		if (SystemPropertyMgr.getInstance().getString("app.frame.decorated").equalsIgnoreCase("false")) {
			setUndecorated(true);
		}
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    addWindowListener( new WindowAdapter() {
	        public void windowClosing(WindowEvent we) {
	        	StandaloneApp.getInstance().CloseApplication();
	        }
	    } );
		
		setSize(frameWidth, frameHeight);
		
		if (SystemPropertyMgr.getInstance().getString("app.maximize").equalsIgnoreCase("true")) {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		
		ROOT_PANEL.setLayout(new BorderLayout());		
		ROOT_PANEL.add(new JScrollPane(NORTH_MSG), BorderLayout.CENTER);
//		setJMenuBar(SPMenuBar.getInstance());		
		soundTest.setActionCommand(SoundTest);
		soundTest.addActionListener(this);
		ROOT_PANEL.add(soundTest, BorderLayout.SOUTH);
		getContentPane().add(ROOT_PANEL);        
	}
	
	/**
	 * pushMsg
	 * @param msg String text to display
	 */
	public void pushMsg(String msg) {
		NORTH_MSG.setText(NORTH_MSG.getText() + msg.concat(System.lineSeparator()));
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (SoundTest.equalsIgnoreCase(actionEvent.getActionCommand())) {
			String song = SystemPropertyMgr.getInstance().getString("audio.test");
			LOGGER.info("Standalone Midi Controller ::  Sound Test with file " + song);
			MediaPlayerController mpc = new MediaPlayerController(song);
			mpc.play();
		}
	}
}
