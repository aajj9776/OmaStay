package com.omakase.omastay.dto.custom;

import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.dto.PointDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MemberPointDTO {
    private MemberDTO member;
    private List<FormattedPointDTO> formattedPoints; // 포맷된 포인트 날짜를 담는 리스트
    private int totalPointSum;

    // 생성자: MemberDTO와 PointDTO 리스트를 사용
    public MemberPointDTO(MemberDTO member, List<PointDTO> points) {
        this.member = member;
        this.formattedPoints = formatPoints(points); // 포맷된 날짜 리스트로 변환
        this.totalPointSum = calculateTotalPointSum(points); // 총 포인트 합계 계산
    }

    // 포인트 리스트를 포맷된 문자열 리스트로 변환
    private List<FormattedPointDTO> formatPoints(List<PointDTO> points) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return points.stream()
            .map(point -> new FormattedPointDTO(
                point.getPValue(), 
                point.getPContent(), 
                point.getPDate() != null ? point.getPDate().format(formatter) : "날짜 없음"))
            .collect(Collectors.toList());
    }

    // 포인트의 총합 계산
    private int calculateTotalPointSum(List<PointDTO> pointDTOList) {
        return pointDTOList.isEmpty() ? 0 : pointDTOList.get(pointDTOList.size() - 1).getPSum();
    }

    //포인트 정보를 담는 내부 클래스
    @Data
    @NoArgsConstructor
    public static class FormattedPointDTO {
        private int pValue;
        private String pContent;
        private String formattedDate;

        public FormattedPointDTO(int pValue, String pContent, String formattedDate) {
            this.pValue = pValue;
            this.pContent = pContent;
            this.formattedDate = formattedDate;
        }
    }
    public void setTotalPointSum(int totalPointSum) {
        this.totalPointSum = totalPointSum;
    }
}
