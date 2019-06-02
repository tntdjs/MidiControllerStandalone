package com.tntdjs.midi.controllers.data.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MidiDeviceXMLHelperTest {
	private ApplicationContext CONTEXT;
	
	@Before
	public void setUp() throws Exception {
		AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("spring-config.xml");
		CONTEXT = appContext; 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMidiDeviceXMLHelper() {
		MidiDeviceXMLHelper.getInstance(CONTEXT);	
	}

}
