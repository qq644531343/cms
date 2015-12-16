package com.sina.cms.model;

public class ChannelModel {
	private String title;
	private long cid;
	private String description;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "ChannelModel [title=" + title + ", cid=" + cid
				+ ", description=" + description + "]";
	}
	
	
}
