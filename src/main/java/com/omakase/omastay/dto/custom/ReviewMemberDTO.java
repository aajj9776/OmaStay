package com.omakase.omastay.dto.custom;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewMemberDTO {
    private Integer id;
    private String revWriter;
    private String revContent;
    private LocalDateTime revDate;
    private Float revRating;
    private String revFname;
    private String revOname;
    
    private List<String> imageUrls;  // 이미지 URL 리스트 추가
    private String hotelName;        // 호텔명
    private String roomName;         // 객실명
}
