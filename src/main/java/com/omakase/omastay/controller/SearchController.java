package com.omakase.omastay.controller;

import com.omakase.omastay.dto.FacilitiesDTO;
import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.dto.PriceDTO;
import com.omakase.omastay.dto.RecommendationDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.dto.RoomInfoDTO;
import com.omakase.omastay.dto.custom.AccommodationResponseDTO;
import com.omakase.omastay.dto.custom.FilterDTO;
import com.omakase.omastay.dto.custom.ResultAccommodationsDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Recommendation;
import com.omakase.omastay.service.FacilitiesService;
import com.omakase.omastay.service.HostFacilitiesService;
import com.omakase.omastay.service.HostInfoService;
import com.omakase.omastay.service.PriceService;
import com.omakase.omastay.service.RecommendationService;
import com.omakase.omastay.service.ReservationService;
import com.omakase.omastay.service.ReviewService;
import com.omakase.omastay.service.RoomInfoService;
import com.omakase.omastay.vo.StartEndVo;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.*;
import java.util.stream.Collectors;
import java.text.DecimalFormat;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private HostInfoService hostInfoService;

    @Autowired
    private HostFacilitiesService hostFacilitiesService;

    @Autowired
    private RoomInfoService roomInfoService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReservationService reservationService;

    private final FacilitiesService facilitiesService;

    public SearchController(FacilitiesService facilitiesService) {
        this.facilitiesService = facilitiesService;
    }
    
    @Value("${upload}")
    private String realPath;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //숙소 세부사항 창
    @RequestMapping("/detail-host")
    public String detailHost(@RequestParam("id") Integer hIdx, 
                         @RequestParam(value = "checkIn", required = false) 
                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
                         @RequestParam(value = "checkOut", required = false) 
                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate,
                         @RequestParam(value = "person", required = false) Integer person, Model model ) {

            List<HostInfo> hostInfoList = hostInfoService.getDetailHostInfo(hIdx);
            List<FacilitiesDTO> facilitiesList = facilitiesService.getDetailFacilities(hIdx);
            System.out.println("졸려" + hostInfoList);
            System.out.println("진짜졸려" + facilitiesList);
            List<RoomInfoDTO> allRoomList = roomInfoService.getAllRoom(hIdx);
            System.out.println("그냥 방 잘 나오냐1"+allRoomList);

            List<RoomInfoDTO> availableRooms = roomInfoService.getAvailableRooms(hIdx, startDate, endDate, person);
            System.out.println("일치하는 방 잘 나오냐"+availableRooms);
            Map<Integer, String> roomPrices = new HashMap<>();
            Map<Integer, String> totalPrices = new HashMap<>();  // 총 가격을 담을 Map

            for (RoomInfoDTO roomDto : availableRooms) {
                Integer roomIdx = roomDto.getId();  // 방 ID 추출
                System.out.println("방 번호: " + roomIdx);
                
                // 방 번호에 맞는 평균 가격 계산
                Map<String, String> priceMap = priceService.calculateAveragePrice(hIdx, roomIdx, startDate, endDate);
                
                
                // 방 번호와 계산된 가격을 Map에 저장
                String averagePrice = priceMap.get("averagePrice");
                String totalPrice = priceMap.get("totalPrice");
                System.out.println("방 번호: " + roomIdx + "의 평균 가격: " + averagePrice);
                System.out.println("방 번호: " + roomIdx + "의 총 가격: " + totalPrice);

                roomPrices.put(roomIdx, averagePrice);
                totalPrices.put(roomIdx, totalPrice);  
            }
            
           
            List<ImageDTO> hostImages = recommendationService.getAllHostImage(hIdx);
            for(ImageDTO image : hostImages){
                System.out.println("이미지나오냐?"+hostImages);
                String hostImg = realPath + "host/" +image.getImgName().getFName();
                System.out.println("이건 나오냐"+hostImg);

            }

            List<ImageDTO> roomImages = recommendationService.getAllRoomImage(hIdx);
            Map<Integer, String> roomImageMap = new HashMap<>(); 
            for(ImageDTO image : roomImages){
                Integer roomIdx = image.getRId();
                String rommImg = realPath + "room/" +image.getImgName().getFName();
                roomImageMap.put(roomIdx, rommImg);
                System.out.println("룸 이미지인데?"+roomImages);
                System.out.println("얜 나오냐"+rommImg);

            }

            String reviewStats = reviewService.getReviewStatsByHostId(hIdx);
            System.out.println("별별"+reviewStats);
          
        
            List<HostInfoDTO> hostInfoDTOList = new ArrayList<>();
            for (HostInfo hostInfo : hostInfoList) {
                HostInfoDTO hostInfoDTO = new HostInfoDTO();
                hostInfoDTO.setId(hostInfo.getId());
                hostInfoDTO.setAdIdx(hostInfo.getAdminMember().getId());
                hostInfoDTO.setHCate(hostInfo.getHCate());
                hostInfoDTO.setXAxis(hostInfo.getXAxis());
                hostInfoDTO.setYAxis(hostInfo.getYAxis());
                hostInfoDTO.setRegion(hostInfo.getRegion());
                hostInfoDTO.setHphone(hostInfo.getHphone());
                hostInfoDTO.setHostContactInfo(hostInfo.getHostContactInfo());
                hostInfoDTO.setAddressVo(hostInfo.getHostAddress());
                hostInfoDTO.setHostOwnerInfo(hostInfo.getHostOwnerInfo());
                hostInfoDTO.setHurl(hostInfo.getHurl());
                hostInfoDTO.setCheckin(hostInfo.getCheckin());
                hostInfoDTO.setCheckout(hostInfo.getCheckout());
                hostInfoDTO.setDirections(hostInfo.getDirections());
                hostInfoDTO.setRules(hostInfo.getRules());
                hostInfoDTO.setPriceAdd(hostInfo.getPriceAdd());
                hostInfoDTO.setHStatus(hostInfo.getHStatus());
                hostInfoDTO.setHStep(hostInfo.getHStep());
                hostInfoDTO.setHname(hostInfo.getHname());
        
                hostInfoDTOList.add(hostInfoDTO);
            }
        
            model.addAttribute("hostInfo", hostInfoDTOList);
            model.addAttribute("facilities", facilitiesList);
            model.addAttribute("hostImages", hostImages);
            model.addAttribute("roomImages", roomImages);
            model.addAttribute("hIdx", hIdx);
            model.addAttribute("roomInfo",availableRooms);
            model.addAttribute("roomPrices", roomPrices);
            model.addAttribute("matchImages", roomImageMap);
            model.addAttribute("allRoom", allRoomList);
            model.addAttribute("reviewStats", reviewStats);
            model.addAttribute("totalPrices", totalPrices);
            model.addAttribute("includeSearchBar", true);
            
        
            return "search/detail_host";
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
        AccommodationResponseDTO accommodationResponseDTO = facilitiesService.search(search, pageable, false);

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
        AccommodationResponseDTO accommodationResponseDTO = facilitiesService.search(search, pageable, true);

        List<ResultAccommodationsDTO> resultAccommodationsMap = accommodationResponseDTO.getAccommodationsMap();

        Map<String, Object> response = new HashMap<>();
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

    
     //사진모두보기 모달창 띄우기
     @GetMapping("/detail_image")
     public String showAllPhotoModal() {
        System.out.println("넌 뜨니");
         return "/detail_image";
     }

   
    }

