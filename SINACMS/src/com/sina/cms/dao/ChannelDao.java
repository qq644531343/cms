package com.sina.cms.dao;

import java.util.List;

import com.sina.cms.model.ChannelModel;

public interface ChannelDao {
	
	public List<ChannelModel> queryChannelList(int pageSize, int offset, String titleKey) ;
	
	public ChannelModel queryChannel(int cid) ;
	
	public int queryCountChannel(String titleKey);
	
	public boolean addChannel(ChannelModel channel) ;
	
	public boolean deleteChannels(String[] cids) ;
	
	public boolean updateChannel(ChannelModel channel);
}
