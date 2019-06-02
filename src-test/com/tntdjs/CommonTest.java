package com.tntdjs;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tntdjs.utils.i18n.TranslationMgr;

public class CommonTest {
		
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(!TranslationMgr.getInstance().getAppTitle().isEmpty());
		assertTrue(!TranslationMgr.getInstance().getText("CopyrightStatement").isEmpty());
				
	}

}
