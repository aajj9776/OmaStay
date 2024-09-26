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

import org.codehaus.groovy.runtime.dgmimpl.arrays.IntegerArrayGetAtMetaMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.mysql.cj.conf.HostInfo;
import com.omakase.omastay.dto.GradeDTO;
import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.dto.ReviewCommentDTO;
import com.omakase.omastay.dto.ReviewDTO;
import com.omakase.omastay.dto.RoomInfoDTO;
import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.ReviewComment;
import com.omakase.omastay.service.ReviewCommentService;
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

    @Autowired
    private ReviewCommentService reviewCommentService;


    @RequestMapping("/review_insert")
    @ResponseBody
    public ResponseEntity<Integer> addReview(@RequestParam Map<String, String> params) { 
        System.out.println("들어는 왔니?");
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
           
        } 

        Integer newRevIdx = reviewService.addReview(reviewDTO, onames, fnames);

        return ResponseEntity.ok(newRevIdx);   
    }

    @RequestMapping("/review_list")
    @ResponseBody
    public ResponseEntity<List<Map<String, Object>>> allReviewList() {
           List<Review> reviewList = reviewService.findAllReview(); // 전체 리뷰 목록 조회

            List<Map<String, Object>> responseList = new ArrayList<>();
            for (Review review : reviewList) {
                ReviewDTO reviewDTO = new ReviewDTO();
                reviewDTO.setRevContent(review.getRevContent());
                reviewDTO.setRevRating(review.getRevRating());
                reviewDTO.setMemIdx(review.getMember().getId()) ;
                reviewDTO.setResIdx(review.getReservation().getId()); 
                reviewDTO.setHIdx(review.getHostInfo().getId());
                reviewDTO.setRevDate(review.getRevDate());

                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setMemName(review.getMember().getMemName()); 

                RoomInfoDTO roomInfoDTO = new RoomInfoDTO();
                roomInfoDTO.setRoomName(review.getReservation().getRoomInfo().getRoomName());

                HostInfoDTO hostInfoDTO = new HostInfoDTO();
                hostInfoDTO.setHname(review.getHostInfo().getHname());

                GradeDTO gradeDTO = new GradeDTO();
                gradeDTO.setGCate(review.getMember().getGrade().getGCate());

                Map<String, Object> response = new HashMap<>();
                response.put("review", reviewDTO);
                response.put("member", memberDTO);
                response.put("hostinfo",hostInfoDTO);
                response.put("room", roomInfoDTO);
                response.put("grade",gradeDTO);

                responseList.add(response);
            

            }
                return ResponseEntity.ok(responseList);
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
    
    
