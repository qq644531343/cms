package com.sina.cms.model;

import java.util.Date;

public class ArticleModel {

	private String title;
	private long tid;
	private String originUrl;
	private long clickNums;
	private int replyNums;
	private Date createDate;
	private Date updateDate;
	private Date publishDate;
	private int status;
	private String ownerUsername;
	private String content;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getOriginUrl() {
		return originUrl;
	}
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}
	public long getClickNums() {
		return clickNums;
	}
	public void setClickNums(long clickNums) {
		this.clickNums = clickNums;
	}
	public int getReplyNums() {
		return replyNums;
	}
	public void setReplyNums(int replyNums) {
		this.replyNums = replyNums;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOwnerUserId() {
		return ownerUsername;
	}
	public void setOwnerUserId(String ownerUserId) {
		this.ownerUsername = ownerUserId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "ArticleModel [title=" + title + ", tid=" + tid + ", originUrl="
				+ originUrl + ", clickNums=" + clickNums + ", replyNums="
				+ replyNums + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", publishDate=" + publishDate + ", status="
				+ status + ", ownerUserId=" + ownerUsername + "]";
	}
	
	
}
