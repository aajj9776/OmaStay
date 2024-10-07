package com.omakase.omastay.controller;

import com.omakase.omastay.dto.FacilitiesDTO;
import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.dto.PriceDTO;
import com.omakase.omastay.dto.RecommendationDTO;
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

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



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

    private final FacilitiesService facilitiesService;

    public SearchController(FacilitiesService facilitiesService) {
        this.facilitiesService = facilitiesService;
    }
    
    @Value("${upload}")
    private String realPath;

    @RequestMapping("/detail-host")
    public String detailHost(@RequestParam("hIdx") Integer hIdx, 
                            @RequestParam(value = "startEndDay.start", required = false) String startDate,
                            @RequestParam(value = "startEndDay.end", required = false) String endDate,
                            @RequestParam(value = "person", required = false) int person, Model model) {

            List<HostInfo> hostInfoList = hostInfoService.getDetailHostInfo(hIdx);
            List<FacilitiesDTO> facilitiesList = facilitiesService.getDetailFacilities(hIdx);
            System.out.println("졸려" + hostInfoList);
            System.out.println("진짜졸려" + facilitiesList);

            List<ImageDTO> hostImages = recommendationService.getAllHostImage(hIdx);
            for(ImageDTO image : hostImages){
                System.out.println("이미지나오냐?"+hostImages);
                String hostImg = realPath + "host/" +image.getImgName().getFName();
                System.out.println("이건 나오냐"+hostImg);

            }

            List<ImageDTO> roomImages = recommendationService.getAllRoomImage(hIdx);
            for(ImageDTO image : roomImages){
                System.out.println("이미지나오냐?"+roomImages);
                String rommImg = realPath + "room/" +image.getImgName().getFName();
                System.out.println("이건 나오냐"+rommImg);

            }
        
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
        
            return "search/detail_host";
        }
        
    @GetMapping(value = "/search")
    public ModelAndView search(@ModelAttribute @Valid FilterDTO search,
                               @RequestParam(name = "page", defaultValue = "1") int page,
                               @RequestParam(name = "size", defaultValue = "5") int size)
    {
        Pageable pageable = PageRequest.of(page - 1, size);
        AccommodationResponseDTO accommodationResponseDTO = facilitiesService.search(search, pageable);

        List<ResultAccommodationsDTO> resultAccommodations = accommodationResponseDTO.getAccommodations();


        System.out.println(resultAccommodations);

        ModelAndView mv = new ModelAndView();
        mv.addObject("resultAccommodations", resultAccommodations);
        mv.addObject("pageNation", accommodationResponseDTO.getPageNation());
        mv.addObject("includeSearchBar", true);
        mv.setViewName("search/domestic-accommodations");

        return mv;
    }

    //숙소 검색 필터링
    public FilterDTO filtering(@RequestBody @Valid FilterDTO filterDTO) {
        System.out.println(filterDTO);
        List<ResultAccommodationsDTO> resultAccommodations = facilitiesService.filteringAccommodations(filterDTO);
        return null;
    }

    

    @RequestMapping("/recomm_host")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> allRecommHost() {
        System.out.println("오긴 왔니?");
        List<Recommendation> recommList = recommendationService.getRecommHost();
        List<PriceDTO> priceList = priceService.getHostPrice();
        List<Map<String, Object>> responseList = new ArrayList<>();
        
        for (Recommendation recommendation : recommList) {
            RecommendationDTO recommendationDTO = new RecommendationDTO();
            recommendationDTO.setHIdx(recommendation.getId());
            recommendationDTO.setRecPoint(recommendation.getRecPoint());

            
            HostInfoDTO hostInfoDTO = new HostInfoDTO();
            hostInfoDTO.setId(recommendation.getHostInfo().getId());
            hostInfoDTO.setHname(recommendation.getHostInfo().getHname());
            hostInfoDTO.setHCate(recommendation.getHostInfo().getHCate());
            hostInfoDTO.setDirections(recommendation.getHostInfo().getDirections());
            
            Integer hIdx = recommendation.getHostInfo().getId();
            ImageDTO image = recommendationService.getImage(hIdx);
            System.out.println("이미지나오냐?"+image);
            String img = realPath + "host/" +image.getImgName().getFName();
            System.out.println("경로 왜 안나와"+realPath);


            List<PriceDTO> matchedPriceList = new ArrayList<>();
            for (PriceDTO price : priceList) {
                if (price.getHIdx().equals(hIdx)) { 
                    matchedPriceList.add(price); 
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("recommendation",recommendationDTO);
            response.put("hostinfo",hostInfoDTO);
            response.put("image",img);
            response.put("hostPrice", matchedPriceList.isEmpty() ? null : matchedPriceList); 
            responseList.add(response);
            System.out.println("왜 콘솔 안 찍히냐"+response);
        }
    
        Map<String, Object> response = new HashMap<>();
        response.put("recommList", responseList); 
    
        return ResponseEntity.ok(response);
    }

    
     //사진모두보기 모달창 띄우기
     @GetMapping("/detail_image")
     public String showAllPhotoModal() {
        System.out.println("넌 뜨니");
         return "/detail_image";
     }

   
    }

