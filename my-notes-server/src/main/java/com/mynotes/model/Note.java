package com.mynotes.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;

/**
 * The Note model
 * 
 * @author Frank
 * Apr 1, 2018 11:00:32 AM
 */
public class Note {

	private Long id;
	private String title;
	private String content;

    public static final int TITLE_MAX_LENGTH = 255;
    public static final int CONTENT_MAX_LENGTH = 1024;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @param validationErrors - this is filled in with description of the validation
	 * 					errors if note is not valid 
	 * @return - a boolean indicating the validity of this note
	 */
	public boolean validate(Map<String, String> validationErrors) {
		boolean valid = true;
		// validate title
		if(StringUtils.isBlank(this.getTitle())) {
			validationErrors.put("title", "Title is required.");
			valid = valid && false;
		}else if(this.getTitle().trim().length() > TITLE_MAX_LENGTH) {
			validationErrors.put("title", String.format("Title can't be more than %d characters.", TITLE_MAX_LENGTH));
			valid = valid && false;
		}
		//validate content
		if(StringUtils.isBlank(this.getContent())) { 
			validationErrors.put("content", "Content is required.");
			valid = valid && false;
		}else if(this.getContent().trim().length() > CONTENT_MAX_LENGTH) { 
			validationErrors.put("content", String.format("Content can't be more than %d characters.", CONTENT_MAX_LENGTH));
			valid = valid && false;
		}
		
		return valid;
	}

	// this would normally be the persistence layer
    private static final Map<Long, Note> myNotes = new ConcurrentHashMap<Long, Note>();
    private static final AtomicLong idGenerator = new AtomicLong();
    
	public void save() {
		this.setId(idGenerator.incrementAndGet());
		myNotes.put(this.id, this);
	}

	public static Collection<Note> fetchAll() {
		return myNotes.values();
	}
	
	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", content=" + content + "]";
	}

}
