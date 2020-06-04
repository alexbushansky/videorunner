package com.videorunner.services;


import com.videorunner.models.Video;
import com.videorunner.repo.IVideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private final IVideoRepository videoRepository;

    public VideoService(IVideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }


    public Optional<Video> getVideoById(Long id)
    {
        return videoRepository.findById(id);
    }

    public List<Video> getAllVideo()
    {
        return videoRepository.findByOrderByDateCreatedDesc();
    }

}
