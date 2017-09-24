package com.demo;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.utilities.Base62;


@RunWith(SpringRunner.class)
public class IntegerToSlugEncoderTests {
	
	@Test
	public void addLinkTest() {
		
		assertEquals("Expected encoding", "a", Base62.fromLong(0L));
		assertEquals("Expected encoding", "b", Base62.fromLong(1L));
		assertEquals("Expected encoding", "c", Base62.fromLong(2L));
		assertEquals("Expected encoding", "d", Base62.fromLong(3L));
		assertEquals("Expected encoding", "A", Base62.fromLong(26L));
		assertEquals("Expected encoding", "0", Base62.fromLong(52L));
		assertEquals("Expected encoding", "ba", Base62.fromLong(62L));
		assertEquals("Expected encoding", "bVjJYjY", Base62.fromLong(100000000000L));
	}

}



