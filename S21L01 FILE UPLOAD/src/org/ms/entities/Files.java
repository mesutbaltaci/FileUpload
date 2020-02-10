package org.ms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name="files")
@Table(name="files")
public class Files {
	@Id 
	@Column (name="id")
	private int id;
	@Column (name="filename")
	private String fileName;
	@Column (name="label")
	private String label;
	@Column (name="caption")
	private String caption;
	
	public Files() {
		
	}
	
	public Files(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	
	public Files(int id, String label, String caption) {
		super();
		this.id = id;
		this.label = label;
		this.caption = caption;
	}

	public Files(int id, String fileName, String label, String caption) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.label = label;
		this.caption = caption;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Override
	public String toString() {
		return "Files [id=" + id + ", fileName=" + fileName + ", label=" + label + ", caption=" + caption + "]";
	}

}
