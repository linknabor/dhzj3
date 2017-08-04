package com.yumu.hexie.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.yumu.hexie.model.community.Message;
import com.yumu.hexie.model.community.MessageRepository;
import com.yumu.hexie.model.community.RegionInfo;
import com.yumu.hexie.model.community.RegionInfoRepository;
import com.yumu.hexie.model.user.Feedback;
import com.yumu.hexie.model.user.FeedbackRepository;
import com.yumu.hexie.service.user.MessageService;

@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {


	@Inject
	private MessageRepository messageRepository;
	@Inject
	private RegionInfoRepository regionInfoRepository;
	@Inject
	private FeedbackRepository feedbackRepository;
	@Override
	public List<Message> queryMessages(int type, long provinceId, long cityId,
			long countyId, long xiaoquId,int page, int pageSize) {
		page = page<0?0:page;
		pageSize = pageSize<0?10:pageSize;
		return messageRepository.queryMessageByRegions(type, provinceId, cityId, countyId, xiaoquId, new PageRequest(page,pageSize));
	}
	
	@Override
	public List<Message> queryMessages(long sect_id, int msgType, int page, int pageSize){
		
		List<Long> list = new ArrayList<Long>();
		
		if(sect_id !=0)
		{
			List<RegionInfo> regions = regionInfoRepository.findAllById(sect_id);
			if(regions.size()!=0)
			{
				RegionInfo region = regions.get(0);
				saveList(list, region.getId());
				saveList(list, region.getSuper_regionId());
				saveList(list, region.getSuper_regionId2());
				saveList(list, region.getSuper_regionId3());
			}
		}else
		{
			List<RegionInfo> regions = regionInfoRepository.queryRegionInfoByRegionType();
			if(regions.size()!=0)
			{
				for (int i = 0; i < regions.size(); i++) {
					RegionInfo r = regions.get(i);
					saveList(list, r.getId());
				}
			}
		}
		return messageRepository.queryMessagesByStatus(msgType, list, new PageRequest(page,pageSize));
	}
	
	public void saveList(List<Long> list,long id)
	{
		if(id!=0)
		{
			list.add(id);
		}
	}
	
	@Override
	public Message findOne(long messageId) {
		return messageRepository.findOne(messageId);
	}
	@Override
	public Feedback reply(long userId,String userName,String userHeader, long messageId, String content) {
		Feedback f = new Feedback(userId, userName,userHeader,messageId, content);
		return feedbackRepository.save(f);
	}
	@Override
	public List<Feedback> queryReplays(long messageId, int page, int pageSize) {
		return feedbackRepository.findAllByArticleId(messageId, new PageRequest(page,pageSize));
	}

}
