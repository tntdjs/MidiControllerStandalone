package com.tntdjs;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.tntdjs.midi.controllers.MidiControllerDefMgrTest;
import com.tntdjs.midi.controllers.data.config.MidiDeviceXMLHelperTest;
import com.tntdjs.utils.FilePathUtilsTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	CommonTest.class,
	FilePathUtilsTest.class,
	MidiControllerDefMgrTest.class,
	MidiDeviceXMLHelperTest.class	
})

public class AllTests {   

	
}
