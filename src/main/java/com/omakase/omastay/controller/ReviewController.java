package com.omakase.omastay.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.omakase.omastay.dto.ReviewDTO;
import com.omakase.omastay.service.ReviewService;
import com.omakase.omastay.util.FileRenameUtil;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @RequestMapping("/review_insert")
    @ResponseBody
    public String addReview(@RequestParam Map<String, String> params) { 
        System.out.println("param"+params);
        ReviewDTO reviewDTO = new ReviewDTO();
        String revContent = params.get("revContent");
        String revRatingStr = params.get("revRating"); 
        float revRating = Float.parseFloat(revRatingStr);

        reviewDTO.setRevContent(revContent);
        reviewDTO.setRevRating(revRating);

        List<String> onames = new ArrayList<>();
        List<String> fnames = new ArrayList<>();

        for (int i = 0; ; i++) {
            String onameKey = "oname_" + i;
            String fnameKey = "fname_" + i;

            if (params.containsKey(onameKey) && params.containsKey(fnameKey)) {
                onames.add(params.get(onameKey));
                fnames.add(params.get(fnameKey));
            } else {
                break; 
            }
           reviewService.addReview(reviewDTO, onames, fnames);
        }
    return null;
    }
    
    //review 모달창 띄우기
    @GetMapping("/review_write")
    public String showReviewWriteModal() {
        return "search/review_write";
    }

    @PostMapping("/upload_image")
    @ResponseBody
    public Map<String, String> uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String realUploadDir = "C:/Final/OmaStay/src/main/resources/static/upload"; // 실제 파일 저장 경로
        Map<String, String> map = new HashMap<>();
        
        if (file.getSize() > 0) {
            // 디렉토리 생성
            File uploadDir = new File(realUploadDir);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            
            // 파일명 생성
            String oname = file.getOriginalFilename();
            String fname = FileRenameUtil.checkSameFileName(oname, realUploadDir);
            
            // 파일 저장
            file.transferTo(new File(realUploadDir, fname));
            
            // 파일을 제공할 URL 생성
            map.put("url", "/upload/" + fname);
            map.put("fname", fname);
            map.put("oname",oname);
        }
        return map;
    }

    @GetMapping("/upload/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable("filename") String filename) {
        String realUploadDir = "C:/Final/OmaStay/src/main/resources/static/upload";
        Path file = Paths.get(realUploadDir).resolve(filename);
        Resource resource;

        try {
            resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
            } else {
                throw new RuntimeException("파일을 찾을 수 없습니다.");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("파일을 찾을 수 없습니다.", e);
        }
    }

    
}
    
    
