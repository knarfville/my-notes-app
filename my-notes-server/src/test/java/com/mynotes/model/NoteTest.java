package com.mynotes.model;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for important methods of the Note
 * 
 * @author Frank
 * Apr 1, 2018 11:47:54 AM
 */
public class NoteTest {
	
	@Test
	public void validateTest_happyPath() {
		Note note = new Note();
		note.setTitle("this is the title");
		note.setContent("this is the content");
		
		Map<String, String> validationErrors = new HashMap<String, String>();
		boolean valid = note.validate(validationErrors );
		
		Assert.assertTrue("The note should be valid", valid);
		Assert.assertTrue("There should be no validation errors.", validationErrors.isEmpty());
	}
	
	@Test
	public void validateTest_titleIsEmpty() {
		Note note = new Note();
		note.setTitle("   ");
		note.setContent("this is the content");
		
		Map<String, String> validationErrors = new HashMap<String, String>();
		boolean valid = note.validate(validationErrors );
		
		Assert.assertFalse("The note should be invalid", valid);
		Assert.assertFalse("There should be a validation error on the title.", validationErrors.isEmpty());
		Assert.assertNotNull("There should be a validation error on the title.", validationErrors.get("title"));
		
	}
	
	@Test
	public void validateTest_titleIsTooLong() {
		Note note = new Note();
		String title = " sadfjsdlfj lskdjfsdklfjsdk  slkdfjfsdfsdfsdffsdklfjsdkl  lkfjsdklfjsdkl  lkgsdhfsdkfh  "
				+ "ddkhsfksdj ljk jlkjdfsdfsdffsdfhsdk  lsdfdsfklsdjf jfsdhfsdkhf lksj fsdfs fddfklsdjfkdsjf  s"
				+ "ddkhsfksdj ljk jlkjdfsdfsdffsdfhsdk  lsdfdsfklsdjf jfsdhfsdkhf lksj fsdfs fddfklsdjfkdsjf  s";
		note.setTitle(title);
		note.setContent("this is the content");
		
		Map<String, String> validationErrors = new HashMap<String, String>();
		boolean valid = note.validate(validationErrors );
		
		Assert.assertTrue("Title is more than 255 chars in length.", title.length() > Note.TITLE_MAX_LENGTH);
		Assert.assertFalse("The note should be invalid", valid);
		Assert.assertFalse("There should be a validation error on the title.", validationErrors.isEmpty());
		Assert.assertNotNull("There should be a validation error on the title.", validationErrors.get("title"));
		
	}
	
	// TODO: add more test on validating the content

	
}
