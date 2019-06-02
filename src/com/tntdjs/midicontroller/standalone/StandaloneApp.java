package com.tntdjs.midicontroller.standalone;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tntdjs.midi.controllers.IMidiDeviceHandler;
import com.tntdjs.midi.controllers.MIDIDeviceHandler;
import com.tntdjs.midi.controllers.MidiControllerDefMgr;
import com.tntdjs.midi.controllers.data.config.MidiDeviceXMLHelper;
import com.tntdjs.midi.executer.ExecuterFactory;
import com.tntdjs.utils.i18n.TranslationMgr;

/**
 * MidiControllerStandaloneApp
 * 
 * @author tsenausk
 *
 */
public class StandaloneApp {
	private static final Logger LOGGER = LogManager.getLogger(StandaloneApp.class.getName());
	private static StandaloneApp INSTANCE;
	private final StandalondFrame FRAME;
	private ApplicationContext CONTEXT;
	private IMidiDeviceHandler MidiDeviceHandler;

	/**
	 * Constructor
	 */
	public StandaloneApp() {
		try (AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("spring-config.xml")) {
			setContext(appContext);

			MidiControllerDefMgr midiDefMgr = (MidiControllerDefMgr) CONTEXT.getBean("midiControllerDefMgr");
			
			LOGGER.info("InitUI");			
			FRAME = new StandalondFrame(midiDefMgr.getMidiControllerDef().getAppHeight(), 
					midiDefMgr.getMidiControllerDef().getAppWidth());
			
			FRAME.pushMsg(TranslationMgr.getInstance().getAppTitle());
			FRAME.pushMsg("Copyright (c) 2017\nTodd M. Senauskas and/or its affiliates.\nAll rights reserved.\n");

			initMidi();

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					FRAME.setVisible(true);
				}
			});
			FRAME.pushMsg("Application Initialized");
		} catch (Exception ex) {
			LOGGER.fatal("Error in main()", ex);
			throw new RuntimeException("Failed to start: Error in main()", ex);
		}
	}

	private void initMidi() {
		LOGGER.info("InitMidi");
		MidiDeviceXMLHelper.getInstance(CONTEXT);

		/**
		 * @to do refactor this to use a device factory
		 * @to do made this spring injected for now, looks okay but maybe a
		 *     device factory would help
		 *     LPD8XMLHelper.getInstance().getDevice().getMidiDevice()
		 */
		MidiDeviceHandler = new MIDIDeviceHandler();
		MidiDeviceHandler.setDeviceName(MidiDeviceXMLHelper.getInstance().getDevice().getName());
		MidiDeviceHandler.initMidi();
		FRAME.pushMsg("Device: " + MidiDeviceHandler.getDeviceName());

		/**
		 * @to do refactor bank so that it is not redefined here and in the midi
		 *     inputreceiver
		 * @to do refactor so that the XML helper used is based on the
		 *     MidiDeviceHandler defined above
		 */
		int BANK = 0;
		int exeButtonsGenerated;
		try {
			exeButtonsGenerated = ExecuterFactory.getInstance()
					.generateExecuters(MidiDeviceXMLHelper.getInstance().getMidiButtonSet(BANK));
		} catch (Exception ex) {
			LOGGER.fatal("Error in main()", ex);
			throw new RuntimeException("Failed to start: Error in main()", ex);
		}
		FRAME.pushMsg("Midi buttons/executers: " + exeButtonsGenerated);
	}

	/**
	 * Application Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.info("MidiControllerStandaloneApp :: application starting up...");
		INSTANCE = new StandaloneApp();
	}

	public static StandaloneApp getInstance() {
		return INSTANCE;
	}

	/**
	 * Close the application
	 */
	public void CloseApplication() {
		Object selectedValue = JOptionPane.showConfirmDialog(FRAME,
				TranslationMgr.getInstance().getText("ExitAreYouSure"), TranslationMgr.getInstance().getAppTitle(),
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (selectedValue.equals(JOptionPane.YES_OPTION)) {
			LOGGER.info("Application Exited Safely");
			System.exit(0);
		}
	}

	/**
	 * getFrame
	 * 
	 * @return the applications hidden JFrame
	 */
	public JFrame getFrame() {
		return FRAME;
	}

	public ApplicationContext getContext() {
		return CONTEXT;
	}

	private void setContext(AbstractApplicationContext argContext) {
		CONTEXT = argContext;
	}
}
