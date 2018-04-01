package com.mynotes.controller;

import java.util.Collection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mynotes.model.Note;

/**
 * REST Controller for Note operations
 * 
 * @author Frank
 * Apr 1, 2018 11:24:14 AM
 */
@RestController
public class MyNotesController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyNotesController.class);	

    /**
     * REST method for creating a new note
     * 
     * @param newNote
     * @return - the response body will include a 'created' boolean flag to indicate
     *  		whether note was successfully created or not. If successful, the newly 
     *  		created note will also be included in the response body. If not successful, 
     *  		errors as to why note can't be created will be included in the response.
     */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Object> createNote(@RequestBody Note newNote) {
		
    	logger.info(String.format("Attempting to create note: %s", newNote));
		HashMap<String, Object> content = new HashMap<String, Object>();
    	
    	try {
    		HashMap<String, String> validationErrors = new HashMap<String, String>();
			boolean valid = newNote.validate(validationErrors);
    		if(valid) {
        		newNote.save();
        		logger.info(String.format("New note created: %s", newNote));
        		
        		// pass newly created note in response body
        		content.put("created", Boolean.TRUE);
        		content.put("note", newNote);
        		return ResponseEntity.status(HttpStatus.CREATED)
        				.body(content);
    		}else{
        		logger.info(String.format("Note can't be created. It has validation errors. %s", newNote));
    			// pass validation errors in response body
        		content.put("created", Boolean.FALSE);
    			content.put("validationErrors", validationErrors);
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        				.body(content);
    		}
    	}catch(Exception ex) {
    		logger.error("Unable to create Note.", ex);
			// pass error in response body
    		content.put("created", Boolean.FALSE);
    		content.put("error", "Unable to create note. Error: " + ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(content);    		
    	}
    }

	/**
	 * REST method for fetching all notes
	 * 
	 * @return - a collection of notes
	 */
    @RequestMapping(value = "/notes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Collection<Note> fetchAll() {
    	return Note.fetchAll();
    }    
    
}
