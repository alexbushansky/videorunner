package com.videorunner.repo;


import com.videorunner.models.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IVideoRepository  extends CrudRepository<Video,Long> {

    Optional<Video> findById(Long id);
    List<Video> findByOrderByDateCreatedDesc();
}
