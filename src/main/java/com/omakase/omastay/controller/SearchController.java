package com.omakase.omastay.controller;

import com.omakase.omastay.dto.custom.AccommodationResponseDTO;
import com.omakase.omastay.dto.custom.FilterDTO;
import com.omakase.omastay.dto.custom.ResultAccommodationsDTO;
import com.omakase.omastay.service.FacilitiesService;
import com.omakase.omastay.vo.StartEndVo;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final FacilitiesService facilitiesService;

    public SearchController(FacilitiesService facilitiesService) {
        this.facilitiesService = facilitiesService;
    }

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //숙소 세부사항 창
    @RequestMapping("/detail-host")
    public ModelAndView detailHost() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("search/detail_host");
        mv.addObject("includeSearchBar", true);
        return mv;
    }

    @GetMapping(value = "/domestic-accommodations")
    public ModelAndView search(@ModelAttribute @Valid FilterDTO search,
                               BindingResult bindingResult,
                               @RequestParam(name = "checkIn") String checkIn,
                               @RequestParam(name = "checkOut") String checkOut,
                               @RequestParam(name = "page", defaultValue = "1") int page,
                               @RequestParam(name = "size", defaultValue = "1") int size)
    {
        // Validation error 존재시 처리
        if (bindingResult.hasErrors()) {
            return new ModelAndView("errorView"); // 에러가 발생한 경우의 뷰 이름을 여기에 작성
        }
        // yyyy-MM-dd 형식의 문자열을 LocalDate로 파싱
        LocalDate checkInDate = LocalDate.parse(checkIn, DATE_FORMATTER);
        LocalDate checkOutDate = LocalDate.parse(checkOut, DATE_FORMATTER);

        // LocalDate를 LocalDateTime으로 변환
        LocalDateTime checkInDateTime = checkInDate.atStartOfDay(); // 00:00:00 시간으로 설정
        LocalDateTime checkOutDateTime = checkOutDate.atStartOfDay(); // 00:00:00 시간으로 설정

        // StartEndVo 객체 생성 후 FilterDTO에 설정
        search.setStartEndDay(new StartEndVo(checkInDateTime, checkOutDateTime));

        System.out.println("서치: "  + search);

        Pageable pageable = PageRequest.of(page - 1, size);
        AccommodationResponseDTO accommodationResponseDTO = facilitiesService.search(search, pageable);

        List<ResultAccommodationsDTO> resultAccommodations = accommodationResponseDTO.getAccommodations();
        List<ResultAccommodationsDTO> resultAccommodationsMap = accommodationResponseDTO.getAccommodationsMap();

        System.out.println(resultAccommodations);

        ModelAndView mv = new ModelAndView();
        mv.addObject("resultAccommodations", resultAccommodations);
        mv.addObject("pageNation", accommodationResponseDTO.getPageNation());
        mv.addObject("resultAccommodationsMap", resultAccommodationsMap);
        mv.addObject("includeSearchBar", true);
        mv.setViewName("search/domestic-accommodations");

        return mv;
    }


    @PostMapping(value = "/domestic-accommodations")
    public ResponseEntity<Map<String, Object>> searchModal(@ModelAttribute @Valid FilterDTO search,
                                                           BindingResult bindingResult,
                                                           @RequestParam(name = "checkIn") String checkIn,
                                                           @RequestParam(name = "checkOut") String checkOut,
                                                           @RequestParam(name = "page", defaultValue = "1") int page,
                                                           @RequestParam(name = "size", defaultValue = "1") int size) {
        // Validation error 존재시 처리
        if (bindingResult.hasErrors()) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Validation failed");
            response.put("details", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(response);
        }

        // yyyy-MM-dd 형식의 문자열을 LocalDate로 파싱
        LocalDate checkInDate = LocalDate.parse(checkIn, DATE_FORMATTER);
        LocalDate checkOutDate = LocalDate.parse(checkOut, DATE_FORMATTER);

        // LocalDate를 LocalDateTime으로 변환
        LocalDateTime checkInDateTime = checkInDate.atStartOfDay();
        LocalDateTime checkOutDateTime = checkOutDate.atStartOfDay();

        // StartEndVo 객체 생성 후 FilterDTO에 설정
        search.setStartEndDay(new StartEndVo(checkInDateTime, checkOutDateTime));

        Pageable pageable = PageRequest.of(page - 1, size);
        AccommodationResponseDTO accommodationResponseDTO = facilitiesService.search(search, pageable);

        List<ResultAccommodationsDTO> resultAccommodations = accommodationResponseDTO.getAccommodations();
        List<ResultAccommodationsDTO> resultAccommodationsMap = accommodationResponseDTO.getAccommodationsMap();

        Map<String, Object> response = new HashMap<>();
        response.put("resultAccommodations", resultAccommodations);
        response.put("pageNation", accommodationResponseDTO.getPageNation());
        response.put("resultAccommodationsMap", resultAccommodationsMap);
        response.put("includeSearchBar", true);

        return ResponseEntity.ok(response);
    }



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // facilities 리스트 커스텀 에디터 등록
        binder.registerCustomEditor(List.class, "facilities", new PropertyEditorSupport() {
            @Override
            @SuppressWarnings("unchecked")
            public void setAsText(String text) throws IllegalArgumentException {
                List<Integer> facilities = Arrays.stream(text.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                setValue(facilities);
            }
        });
    }
}
