package com.christian.aguayo.architecture.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "note_table")
public class Note {

	@PrimaryKey(autoGenerate = true)
	private int id;

	private String title;
	private String description;
	private int priority;

	public Note(String title, String description, int priority) {
		this.title = title;
		this.description = description;
		this.priority = priority;

	}


	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public int getPriority() {
		return this.priority;
	}
}
