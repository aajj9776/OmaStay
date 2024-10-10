package com.omakase.omastay.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.text.DecimalFormat;


import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.dto.PriceDTO;
import com.omakase.omastay.dto.RecommendationDTO;
import com.omakase.omastay.dto.ServiceDTO;
import com.omakase.omastay.dto.custom.EventPathDTO;
import com.omakase.omastay.entity.Recommendation;
import com.omakase.omastay.entity.enumurate.SCate;
import com.omakase.omastay.entity.enumurate.UserAuth;
import com.omakase.omastay.service.PriceService;
import com.omakase.omastay.service.RecommendationService;
import com.omakase.omastay.service.ReviewService;
import com.omakase.omastay.service.ServiceService;

@Controller
public class MainController {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ServiceService serviceService;

    @Value("${upload}")
    private String realPath;


    @GetMapping()
    public String index(@RequestParam(value = "activeSearch", required = false)
                            String activeSearch, Model model) {

        List<Recommendation> recommList = recommendationService.getRecommHost();
        List<Map<String, Object>> priceList = priceService.getHostPriceList();
        List<Map<String, Object>> responseList = new ArrayList<>();
        Map<Integer, Map<String, Object>> reviewCountMap = reviewService.getRecomReviewCount();

        List<ServiceDTO> serviceList = serviceService.getAllServices(SCate.EVENT, UserAuth.USER);
        List<EventPathDTO> eventPathDTOList = new ArrayList<>(); // 여러 개의 EventPathDTO를 저장할 리스트
        
        for (ServiceDTO serviceDTO : serviceList) {
            EventPathDTO ep = new EventPathDTO();
            
            // 파일 경로 생성
            String path = realPath + "notice/" + serviceDTO.getFileName().getFName();
            
            // 각각의 ServiceDTO와 경로를 EventPathDTO에 설정
            ep.setPath(path);
            ep.setService(serviceDTO);
            
            // 리스트에 추가
            eventPathDTOList.add(ep);
        }
        
        model.addAttribute("event", eventPathDTOList); // 여러 개의 EventPathDTO를 모델에 추가
        
        
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
            System.out.println("이미지나오냐? " + image);
            String img = realPath + "host/" + image.getImgName().getFName();
            System.out.println("경로: " + img);

            Map<String, Object> stats = reviewCountMap.getOrDefault(hIdx, new HashMap<>());
            Long reviewCount = (Long) stats.getOrDefault("reviewCount", 0L);
            Double averageRating = (Double) stats.getOrDefault("averageRating", 0.0);
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String formattedAverageRating = decimalFormat.format(averageRating);


            Integer matchedPrice = null;
            String formattedPrice = null;

            for (Map<String, Object> priceData : priceList) {
                if (priceData.get("hIdx").equals(hIdx)) {
                    matchedPrice = (Integer) priceData.get("matchedPrice");
                    
                    if (matchedPrice != null) {
                        DecimalFormat formatter = new DecimalFormat("#,###");
                        formattedPrice = formatter.format(matchedPrice);
                    }
                    
                    break;
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("recommendation", recommendationDTO);
            response.put("hostinfo", hostInfoDTO);
            response.put("image", img);
            response.put("reviewCount", reviewCount);
            response.put("averageRating", formattedAverageRating);
            response.put("price", formattedPrice);
            responseList.add(response);

            System.out.println("응답 데이터: " + response);
        }
        model.addAttribute("path");
        model.addAttribute("recomList", responseList);
        System.out.println("제발요"+responseList);
        model.addAttribute("activeSearch", activeSearch);

        return "index";
    }


}
