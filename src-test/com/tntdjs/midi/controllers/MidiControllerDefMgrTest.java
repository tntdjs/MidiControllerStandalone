package com.tntdjs.midi.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MidiControllerDefMgrTest {
	private ApplicationContext CONTEXT;
	private MidiControllerDefMgr midiDefMgr = null;
	
	@Before
	public void setUp() throws Exception {		
		AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("spring-config.xml");
		CONTEXT = appContext;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void midiControllerDefMgr() {
		
		midiDefMgr = (MidiControllerDefMgr) CONTEXT.getBean("midiControllerDefMgr");		
		String test = "Current Controller is: " + 
				midiDefMgr.getMidiControllerDef().getMidiControllerPath() + 
					midiDefMgr.getMidiControllerDef().getMidiControllerName() +
					"\r\nXML Configuration is:" +
					midiDefMgr.getMidiControllerDef().getXmlConfiguration();
		System.out.println(test);

	}
	
}
