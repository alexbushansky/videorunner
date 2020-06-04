package com.videorunner.controllers;


import com.videorunner.models.Comment;
import com.videorunner.models.User;
import com.videorunner.models.Video;
import com.videorunner.repo.IVideoRepository;
import com.videorunner.services.VideoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class VideoController {


    private final IVideoRepository videoRepository;

    private VideoService videoService;

    @Value("${upload.path}")
    private String uploadPath;

    public VideoController(IVideoRepository videoRepository, VideoService videoService) {
        this.videoRepository = videoRepository;
        this.videoService = videoService;
    }

    public String showUsersVideo()
    {
        return "user-video";
    }

    @GetMapping("/add-video")
    public String addVideoPage()
    {

        return "add-video";
    }

    @GetMapping("/all-video")
    public String allVideoPage(Model model)
    {

        model.addAttribute("videos",videoService.getAllVideo());

        return "all-video";
    }

    @PostMapping("/add-video")
    public String addVideo(
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file,
            @RequestParam("image") MultipartFile image,
            @RequestParam String description,
            @RequestParam String nameOfVideo,
            Model model) throws IOException {

        if (file != null && image !=null)
        {
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists())
            {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();

            String resultNameVideo =  (uuidFile +"_" + file.getOriginalFilename()).replaceAll("\\s","");
            String resultNameImage = (uuidFile+"_" + image.getOriginalFilename()).replaceAll("\\s","");

            model.addAttribute("message", file.getOriginalFilename());

            file.transferTo(new File(uploadPath+ "/" + resultNameVideo));
            image.transferTo(new File(uploadPath+"/" + resultNameImage));

            Video video = new Video(resultNameVideo,resultNameImage,nameOfVideo,description,user);
            videoRepository.save(video);
        }


        return "redirect:all-video";
    }


    @GetMapping("/video/{id}")
    public String videoPage(@PathVariable Long id, Model model)
    {

       Video video = videoService.getVideoById(id).orElse(null);
       if(video.getComments().size()>0) {
           List<Comment> comments = new ArrayList<>(video.getComments());
           Collections.sort(comments);
           model.addAttribute("comments",comments);
       }
        model.addAttribute("video",video);


        return "video-page";
    }



}
