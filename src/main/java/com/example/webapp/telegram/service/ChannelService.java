package com.example.webapp.telegram.service;

import com.example.webapp.telegram.entities.ChannelEntity;
import com.example.webapp.telegram.repo.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ChannelService {

    private static ChannelRepository channelRepository;

    @Autowired
    private ChannelRepository tChannelRepository;

    @PostConstruct
    public void init() {
        channelRepository = tChannelRepository;
    }

    public static ChannelEntity getChannel(Long Id) throws Exception {
        ChannelEntity ChannelEntity = null;
        try {
            ChannelEntity = channelRepository.findById(Id).get();
            return ChannelEntity;
        }

        catch (Exception e)
        {
            throw new Exception("Invalid Channel ID");
        }
    }

    public static void saveChannel(ChannelEntity ChannelEntity) {
        channelRepository.save(ChannelEntity);
    }
}
