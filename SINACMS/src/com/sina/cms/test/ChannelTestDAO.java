package com.sina.cms.test;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sina.cms.dao.ChannelDaoImpl;
import com.sina.cms.model.ChannelModel;

public class ChannelTestDAO {
	
	private static ChannelDaoImpl dao = null;
	
	@BeforeClass
	public static void setup() {
			dao = new ChannelDaoImpl();
	}
	
	@Test
	public void testQuery() {
		System.out.println(dao.queryChannelList(5, 0, null));
	}
	
	@Test
	public void testAdd() {
		ChannelModel channel = new ChannelModel();
		channel.setTitle("hello");
		channel.setDescription("asdfsadfsad");
		boolean rs = dao.addChannel(channel);
		System.out.println(rs);
	}
	
	@Test
	public void deleteChannel() {
		System.out.println(dao.deleteChannels(new String[]{"4"}));
	}
	
	@Test
	public void updateChannel() {
		List<ChannelModel> list = dao.queryChannelList(5, 0, null);
		ChannelModel channel = list.get(0);
		channel.setTitle("xxx22xxxxx");
		System.out.println(dao.updateChannel(channel));
	}
}
