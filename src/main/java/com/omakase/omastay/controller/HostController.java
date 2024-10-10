package com.omakase.omastay.controller;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.omakase.omastay.dto.AdminMemberDTO;
import com.omakase.omastay.dto.FacilitiesDTO;
import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.dto.PriceDTO;
import com.omakase.omastay.dto.ReviewCommentDTO;
import com.omakase.omastay.dto.ReviewDTO;
import com.omakase.omastay.dto.RoomInfoDTO;
import com.omakase.omastay.dto.ServiceDTO;
import com.omakase.omastay.dto.custom.CancelRequestDTO;
import com.omakase.omastay.dto.custom.HostCalculationDTO;
import com.omakase.omastay.dto.custom.HostInfoCustomDTO;
import com.omakase.omastay.dto.custom.HostMypageDTO;
import com.omakase.omastay.dto.custom.HostReservationDTO;
import com.omakase.omastay.dto.custom.HostReservationEmailDTO;
import com.omakase.omastay.dto.custom.HostRulesDTO;
import com.omakase.omastay.dto.custom.HostSalesDTO;
import com.omakase.omastay.dto.custom.RoomRegDTO;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.SCate;
import com.omakase.omastay.entity.enumurate.UserAuth;
import com.omakase.omastay.service.AdminMemberService;
import com.omakase.omastay.service.CalculationService;
import com.omakase.omastay.service.EmailService;
import com.omakase.omastay.service.FacilitiesService;
import com.omakase.omastay.service.FileUploadService;
import com.omakase.omastay.service.HostInfoService;
import com.omakase.omastay.service.ImageService;
import com.omakase.omastay.service.PriceService;
import com.omakase.omastay.service.ReservationService;
import com.omakase.omastay.service.ReviewCommentService;
import com.omakase.omastay.service.ReviewService;
import com.omakase.omastay.service.RoomInfoService;
import com.omakase.omastay.service.SalesService;
import com.omakase.omastay.service.ServiceService;
import com.omakase.omastay.vo.FileImageNameVo;

import io.jsonwebtoken.io.IOException;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/host")
@RequiredArgsConstructor  
public class HostController {

    @Autowired
    private AdminMemberService adminMemberService;

    @Autowired
    private HostInfoService hostInfoService;

    @Autowired
    private FacilitiesService facilitiesService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private RoomInfoService roomInfoService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewCommentService reviewCommentService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private SalesService salesService;

    @Autowired
    private CalculationService calculationService; 

    @Autowired
    private ImageService imageService;

    @Autowired
    private ApplicationContext applicationContext;
    
    private final EmailService emailService;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    @Value("${upload}")
    private String upload;

    //세션 업데이트
    @RequestMapping("/updateSession")
    public ResponseEntity<String> updateSession(HttpSession session) {
        AdminMemberDTO adminMember = (AdminMemberDTO)session.getAttribute("adminMember");
        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);
        if (hostInfoDTO != null) {
            session.setAttribute("hStep", hostInfoDTO.getHStep() != null ? hostInfoDTO.getHStep().name() : null);
            session.setAttribute("hStatus", hostInfoDTO.getHStatus() != null ? hostInfoDTO.getHStatus().name() : null);
        }

        return ResponseEntity.ok("success");
    }

    @RequestMapping("/login")
    public String host() {
        return "host/host_login";
    }

    @RequestMapping("/register")
    public String hostRegister() {
        return "host/host_register";
    }

    @RequestMapping("/findpw")
    public String hostfindpw() {
        return "host/host_findpw";
    }

    //호스트 자주묻는질문
    @RequestMapping("/faq")
    public ModelAndView hostfaq() {
        
        ModelAndView mv = new ModelAndView();

        List<ServiceDTO> list = serviceService.getAllServices(SCate.FAQ, UserAuth.HOST);

        mv.addObject("list", list);
        
        mv.setViewName("host/host_faq");

        return mv;

    }

    //호스트 숙소소개
    @RequestMapping("/info")
    public ModelAndView hostinfo(){
        System.out.println("숙소소개 컨트롤러 옴");
        ModelAndView mv = new ModelAndView();

        List<FacilitiesDTO> facilities = facilitiesService.all();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        if (adminMember != null) {
            HostMypageDTO hostMypageDTO = hostInfoService.findHostMypageByAdminMember(adminMember);

            if (hostMypageDTO.getHostInfo() == null || hostMypageDTO.getHostInfo().getHStep() == null) {
                mv.addObject("errorMessage", "이전 단계를 완료해주세요.");
                mv.setViewName("host/host_mypage");
                return mv;
            }

            HostInfoCustomDTO hostInfoCustomDTO = hostInfoService.findHostInfoByHostInfoId(hostMypageDTO.getHostInfo().getId());
            List<ImageDTO> image = imageService.getHostImages(hostMypageDTO.getHostInfo().getId());
            mv.addObject("image", image);
            mv.addObject("hostMypageDTO", hostMypageDTO);
            mv.addObject("hostInfoCustomDTO", hostInfoCustomDTO);
            if (hostInfoCustomDTO.getHostInfo().getHCate() != null) {
            mv.addObject("hCate", hostInfoCustomDTO.getHostInfo().getHCate().name());
            }
        }
        System.out.println("storage:"+upload);
        mv.addObject("storage", upload);
        mv.addObject("facilities", facilities);
        mv.setViewName("host/host_info");

        return mv;
    }

    //호스트 메인
    @RequestMapping("/main")
    public ModelAndView hostmain() {

        ModelAndView mv = new ModelAndView();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        if (adminMember != null) {
            HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);
            if (hostInfoDTO != null) {
                mv.addObject("hostInfoDTO", hostInfoDTO);
                mv.addObject("hStep", hostInfoDTO.getHStep().name());
                System.out.println("hStep:"+hostInfoDTO.getHStep().name());
                if(hostInfoDTO.getHStatus() != null){
                mv.addObject("hStatus", hostInfoDTO.getHStatus().name());    
                }
                List<ReviewDTO> dayList = reviewService.getReviewDay(hostInfoDTO);
                if(dayList != null){
                    mv.addObject("revDayCount", dayList.size());
                    System.out.println("revDayCount:"+dayList.size());
                }
                List<ReviewDTO> weekList = reviewService.getReviewWeek(hostInfoDTO);
                if(weekList != null){
                    mv.addObject("revWeekCount", weekList.size());
                    System.out.println("revWeekCount:"+weekList.size());
                }
                List<ReviewDTO> monthList = reviewService.getReviewMonth(hostInfoDTO);
                if(monthList != null){
                    mv.addObject("revMonthCount", monthList.size());
                    System.out.println("revMonthCount:"+monthList.size());
                }
            } 
            List<RoomInfoDTO> roomInfoDTO = roomInfoService.getAllRoom(hostInfoDTO, BooleanStatus.TRUE);
            if(roomInfoDTO != null){
                List<HostReservationDTO> dayList = reservationService.getReservationsDay(roomInfoDTO);
                if(dayList != null){
                    mv.addObject("resDayCount", dayList.size());
                    System.out.println("resDayCount:"+dayList.size());
                }
                List<HostReservationDTO> weekList = reservationService.getReservationsWeek(roomInfoDTO);
                if(weekList != null){
                    mv.addObject("resWeekCount", weekList.size());
                    System.out.println("resWeekCount:"+weekList.size());
                }
                List<HostReservationDTO> monthList = reservationService.getReservationsMonth(roomInfoDTO);
                if(monthList != null){
                    mv.addObject("resMonthCount", monthList.size());
                    System.out.println("resMonthCount:"+monthList.size());
                }
            
                mv.addObject("roomInfoDTO", roomInfoDTO);
                
            }

        }

        mv.setViewName("host/host_main");
        return mv;
    }

    //호스트 마이페이지
    @RequestMapping("/mypage")
    public ModelAndView hostmypage() {

        ModelAndView mv = new ModelAndView();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        if (adminMember != null) {
            HostMypageDTO hostMypageDTO = hostInfoService.findHostMypageByAdminMember(adminMember);
            mv.addObject("hostMypageDTO", hostMypageDTO);
        }

        mv.setViewName("host/host_mypage");
        return mv;
    }

    @RequestMapping("/notice")
    public String hostnotice() {
        return "host/host_notice";
    }

    @RequestMapping("/noticedetail")
    public String hostnoticedetail() {
        return "host/host_noticedetail";
    }

    @RequestMapping("/payment")
    public String hostpayment() {
        return "host/host_payment";
    }

    @RequestMapping("/paymentdetail")
    public String hostpaymentdetail() {
        return "host/host_paymentdetail";
    }
    
    @RequestMapping("/reservation")
    public String hostreservation() {
        return "host/host_reservation";
    }

    @RequestMapping("/reservationdetail")
    public String hostreservationdetail() {
        return "host/host_reservationdetail";
    }

    @RequestMapping("/review")
    public String hostreview() {
        return "host/host_review";
    }

    @RequestMapping("/reviewdetail")
    public String hostreviewdetail() {
        return "host/host_reviewdetail";
    }

    @RequestMapping("/roomlist")
    public String hostroomlist() {
        return "host/host_roomlist";
    }

    //호스트 객실 등록 페이지 이동
    @RequestMapping("/roomreg")
    public ModelAndView hostroomreg() {

        ModelAndView mv = new ModelAndView();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        if (adminMember != null) {
            HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);
            PriceDTO priceDTO = priceService.findPriceDTO(hostInfoDTO);
            mv.addObject("hostInfoDTO", hostInfoDTO);  

            // hCate 값을 추출하여 문자열로 변환
            if (hostInfoDTO.getHCate() != null) {
                mv.addObject("hCate", hostInfoDTO.getHCate().name());
            }

            if (priceDTO.getPeakSet() == 1 && priceDTO.getSemi() != null && priceDTO.getSemi().getSemiStart() != null) {
                mv.addObject("semiPeak", priceDTO.getSemi().getSemiStart());
            }

            if (priceDTO.getPeakSet() == 1 && priceDTO.getPeakVo() != null && priceDTO.getPeakVo().getPeakStart() != null) {
                mv.addObject("peak", priceDTO.getPeakVo().getPeakStart());
            }
        }

        mv.setViewName("host/host_roomreg");
        return mv;
    }


    //호스트 이용규칙 등록
    @RequestMapping("/rules")
    public ModelAndView hostrules() {

        ModelAndView mv = new ModelAndView();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        if (adminMember != null) {
            HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);
            PriceDTO priceDTO = priceService.findPriceDTO(hostInfoDTO);

            if (priceDTO != null && priceDTO.getSemi() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime semiStart = priceDTO.getSemi().getSemiStart();
                LocalDateTime semiEnd = priceDTO.getSemi().getSemiEnd();
                String semiPeriod = "";

                if (semiStart != null && semiEnd != null) {
                    String formattedSemiStart = semiStart.format(formatter);
                    String formattedSemiEnd = semiEnd.format(formatter);
                    semiPeriod = formattedSemiStart + " ~ " + formattedSemiEnd;
                }
                mv.addObject("semiStart", semiStart);
                mv.addObject("semiEnd", semiEnd);
                mv.addObject("semiPeriod", semiPeriod);
            }
    
            if (priceDTO != null && priceDTO.getPeakVo() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime peakStart = priceDTO.getPeakVo().getPeakStart();
                LocalDateTime peakEnd = priceDTO.getPeakVo().getPeakEnd();
                String peakPeriod = "";
                if (peakStart != null && peakEnd != null) {
                    String formattedPeakStart = peakStart.format(formatter);
                    String formattedPeakEnd = peakEnd.format(formatter);
                    peakPeriod = formattedPeakStart + " ~ " + formattedPeakEnd;
                }
                mv.addObject("peakStart", peakStart);
                mv.addObject("peakEnd", peakEnd);
                mv.addObject("peakPeriod", peakPeriod);
            }

            mv.addObject("hostInfoDTO", hostInfoDTO);
            mv.addObject("priceDTO", priceDTO);
        }
        mv.setViewName("host/host_rules");
        return mv;
    }

    @RequestMapping("/sales")
    public String hostsales() {
        return "host/host_sales";
    }

    //호스트 회원가입 id 중복체크
    @RequestMapping("/idcheck")
    @ResponseBody
    public int hostidcheck(@RequestParam("id") String id) {

        int cnt = adminMemberService.hostidcheck(id);

        if(cnt == 0){
            return 0;
        }else{
            return 1;
        }
    }

    //호스트 회원가입, 마이페이지 이메일 발송
    @RequestMapping("/emailsend")  
    public ResponseEntity<String> sendEmailPath(@RequestParam("email") String email) throws MessagingException { 
        try {
            emailService.sendEmail(email);  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("success");  
    }  
    
    //호스트 회원가입, 마이페이지 이메일 인증번호 체크
    @RequestMapping("/emailchecknum")  
    public ResponseEntity<String> sendEmailAndCode(@RequestParam("email") String email, @RequestParam("code") String code){  
        if (emailService.verifyEmailCode(email, code)) {  
            return ResponseEntity.ok("success");  
        }  
        return ResponseEntity.notFound().build();  
    }

    //호스트 회원가입
    @RequestMapping("/hostregist")
    public ResponseEntity<String> hostregister(@RequestParam("id") String id, @RequestParam("pw") String pw, @RequestParam("email") String email) {
        boolean result = adminMemberService.hostregist(id, pw, email);
        if (result) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(500).body("Registration failed");
        }
    }

    //호스트 로그인
    @RequestMapping("/hostlogin")
    public ModelAndView hostlogin(@RequestParam("id") String id, @RequestParam("pw") String pw) {
        AdminMemberDTO adminMemberDTO = adminMemberService.hostlogin(id,pw);

        ModelAndView mv = new ModelAndView();
        if (adminMemberDTO != null) {
            session.setAttribute("adminMember", adminMemberDTO); // 세션에 사용자 정보 저장
            updateSession(session);
            mv.addObject("adminMember", adminMemberDTO);
            mv.setViewName("redirect:/host/main");
        } else {
            mv.addObject("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
            mv.setViewName("host/host_login");
        }

        return mv;
    }

    //호스트 pw 찾기 시 id, email 일치여부 확인 후 이메일 발송
    @RequestMapping("/idemailsend")  
    public ResponseEntity<String> sendIdEmail(@RequestParam("id") String id, @RequestParam("email") String email) throws MessagingException, java.io.IOException { 
        boolean isValid = adminMemberService.hostfindpw(id, email);

        if (isValid) {
            emailService.sendEmail(email);
            return ResponseEntity.ok("success");
        } else {
            String errorMessage = "아이디 또는 이메일이 일치하지 않습니다.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }  

    //호스트 비밀번호 변경
    @RequestMapping("/changepw")
    public ResponseEntity<String> hostchangepw(@RequestParam("id") String id, @RequestParam("pw") String pw, @RequestParam("email") String email) {
        boolean result = adminMemberService.hostchagepw(id, email, pw);
        if (result) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(500).body("Change failed");
        }
    }

    //호스트 로그아웃
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/host/login"; 
    }

    //호스트 마이페이지 등록
    @RequestMapping("/mypagereg")
    @ResponseBody
    public ResponseEntity<String> mypagereg(@RequestBody HostMypageDTO hostMypageDTO) {
        System.out.println(hostMypageDTO);
        System.out.println(hostMypageDTO.getHostInfo().getHname());
        System.out.println(hostMypageDTO.getHostInfo().getHphone());

        AdminMemberDTO adminMember = (AdminMemberDTO)session.getAttribute("adminMember");

        System.out.println(adminMember);

        String pw = hostMypageDTO.getPw();
            
        adminMemberService.hostchagepw(adminMember.getAdId(), adminMember.getAdminProfile().getEmail(), pw);    

        hostInfoService.saveHostMypage(hostMypageDTO, adminMember);

        return ResponseEntity.ok("success");
    }

    //호스트 숙소소개 등록
    @RequestMapping("/hostinforeg")
    public ResponseEntity<String> hostinforeg(@RequestPart("hostInfoCustomDTO") HostInfoCustomDTO hostInfoCustomDTO, @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        System.out.println(hostInfoCustomDTO.getHostInfo().getYAxis());
        System.out.println(hostInfoCustomDTO.getImages().size());
        AdminMemberDTO adminMember = (AdminMemberDTO)session.getAttribute("adminMember");

        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);
        String hName = hostInfoDTO.getHname();

        List<ImageDTO> existimagesDTO = hostInfoCustomDTO.getImages();

        for (ImageDTO image : hostInfoCustomDTO.getImages()) {
            System.out.println("fName: " + image.getImgName().getFName());
        }

        // 폼양식에서 첨부파일이 전달될 때 enctype이 지정된다.
        String c_type = request.getContentType();
        if (c_type.startsWith("multipart")) {
            List<ImageDTO> existImages = new ArrayList<>();
            List<String> existingFileNames = new ArrayList<>();

            if (hostInfoCustomDTO.getHostInfo() != null && hostInfoCustomDTO.getHostInfo().getId() != null) {
                existImages = imageService.getHostImages(hostInfoCustomDTO.getHostInfo().getId());
                if (existImages != null) {
                    existingFileNames = existImages.stream()
                            .map(imageDTO -> imageDTO.getImgName().getFName())
                            .collect(Collectors.toList());
                }
            }

            List<ImageDTO> newImages = new ArrayList<>();
            List<String> newFileNames = new ArrayList<>();

            // 기존 이미지 추가
            if (existimagesDTO != null) {
                for (ImageDTO imageDTO : existimagesDTO) {
                    newImages.add(imageDTO);
                    newFileNames.add(imageDTO.getImgName().getOName());
                }
            }

            if (images != null) {
            for (MultipartFile f : images) {
            if (f != null && f.getSize() > 0) {
                
                String hostupload = upload + "host";
                System.out.println("호스트이미지 실제 파일 경로: " + hostupload);

                String oname = f.getOriginalFilename();//실제파일명

                FileImageNameVo fvo = new FileImageNameVo();
                fvo.setOName(oname);

                String uniqueFileName = FileRenameGcs.checkSameFileName(oname, newFileNames);
                newFileNames.add(uniqueFileName);
                
                String fname = hName+"_"+FileRenameGcs.checkSameFileName(uniqueFileName, newFileNames);
                fvo.setFName(fname);

                try {
                   // 실제 파일 업로드를 서비스로 위임
                   String fileUrl = fileUploadService.uploadFile(f, "host", fname);
                   System.out.println("파일 업로드 URL: " + fileUrl);

                   ImageDTO imageDTO = new ImageDTO();
                   imageDTO.setImgName(fvo);
                   newImages.add(imageDTO);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } 
            }
        }
            hostInfoCustomDTO.setImages(newImages);
        }

        hostInfoService.saveHostInfo(hostInfoCustomDTO, adminMember);
        
        return ResponseEntity.ok("success");
    }

    
    //호스트 이용규칙 등록
    @RequestMapping("/rulesreg")
    public ResponseEntity<String> rulesreg(@RequestBody HostRulesDTO hostRulesDTO) {

        AdminMemberDTO adminMember = (AdminMemberDTO)session.getAttribute("adminMember");

        HostInfoDTO hostInfoDTO = hostInfoService.saverules(hostRulesDTO.getHostInfo(), adminMember);

        priceService.setpeak(hostInfoDTO, hostRulesDTO.getPrice());


        return ResponseEntity.ok("success");
    }

    // 호스트 객실추가
@RequestMapping("/roominforeg")
public ResponseEntity<String> roominforeg(@RequestPart("roomRegDTO") RoomRegDTO roomRegDTO, @RequestPart(value = "images", required = false) List<MultipartFile> images) {

    AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");
    String rName = roomRegDTO.getRoomInfo().getRoomName();
    HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);
    List<ImageDTO> existimagesDTO = roomRegDTO.getImages();

    for (ImageDTO image : roomRegDTO.getImages()) {
        System.out.println("fName: " + image.getImgName().getFName());
    }

    // 폼양식에서 첨부파일이 전달될 때 enctype이 지정된다.
    String c_type = request.getContentType();
    if (c_type.startsWith("multipart")) {
        List<ImageDTO> existImages = new ArrayList<>();
        List<String> existingFileNames = new ArrayList<>();

        if (roomRegDTO.getRoomInfo() != null && roomRegDTO.getRoomInfo().getId() != null) {
            existImages = imageService.getImages(roomRegDTO.getRoomInfo().getId());
            if (existImages != null) {
                existingFileNames = existImages.stream()
                        .map(imageDTO -> imageDTO.getImgName().getFName())
                        .collect(Collectors.toList());
            }
        }

        List<ImageDTO> newImages = new ArrayList<>();
        List<String> newFileNames = new ArrayList<>();

        // 기존 이미지 추가
        if (existimagesDTO != null) {
            for (ImageDTO imageDTO : existimagesDTO) {
                newImages.add(imageDTO);
                newFileNames.add(imageDTO.getImgName().getFName());
            }
        }

        // 새로 업로드된 이미지 추가
        if (images != null) {
            for (MultipartFile f : images) {
                if (f != null && f.getSize() > 0) {
                    String roomupload = upload + "room";
                    System.out.println("룸이미지 실제 파일 경로: " + roomupload);

                    String oname = f.getOriginalFilename(); // 실제파일명
                    FileImageNameVo fvo = new FileImageNameVo();
                    fvo.setOName(oname);

                    String uniqueFileName = FileRenameGcs.checkSameFileName(oname, newFileNames);
                    newFileNames.add(uniqueFileName);

                    String fname = rName+"_"+FileRenameGcs.checkSameFileName(uniqueFileName, newFileNames);
                    fvo.setFName(fname);

                    try {
                        // 실제 파일 업로드를 서비스로 위임
                        String fileUrl = fileUploadService.uploadFile(f, "room", fname);
                        System.out.println("파일 업로드 URL: " + fileUrl);

                        ImageDTO imageDTO = new ImageDTO();
                        imageDTO.setImgName(fvo);
                        newImages.add(imageDTO);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        roomRegDTO.setImages(newImages);
    }

    roomInfoService.saveRoomInfo(hostInfoDTO, roomRegDTO);

    return ResponseEntity.ok("success");
}

    //호스트 입점요청
    @RequestMapping("/requestadmin")
    public ModelAndView requestadmin() {

        ModelAndView mv = new ModelAndView();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        if (adminMember != null) {
            HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);
            if (hostInfoDTO != null) {
                hostInfoService.requestAdmin(hostInfoDTO);
            } 
        }

        mv.setViewName("redirect:/host/main");
        return mv;
    }

    // 호스트 객실 전체 리스트
    @RequestMapping("/roomlist/getList")
    @ResponseBody
    public Map<String, Object> roomlist() {
        Map<String, Object> map = new HashMap<>();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);

        List<RoomInfoDTO> list = roomInfoService.getAllRoom(hostInfoDTO, BooleanStatus.TRUE);

        map.put("data", list);

        return map;
    }

    // 호스트 객실 검색
    @RequestMapping("/roomlist/search")
    @ResponseBody
    public Map<String, Object> roomsearch(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "keyword", required = false) String keyword) {

                System.out.println("룸서치 왔다");
                System.out.println("type:"+type);
                System.out.println("keyword:"+keyword);

        Map<String, Object> map = new HashMap<>();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);

        List<RoomInfoDTO> list = roomInfoService.searchRoom(type, keyword, hostInfoDTO);

        System.out.println("컨트롤러리스트:"+list);

        map.put("list", list);

        return map;
    }

    // 호스트 객실 삭제
    @ResponseBody
    @RequestMapping("/roomlist/delete")
    public Map<String, Object> roomdelete(@RequestParam("ids") List<Integer> ids) {

        Map<String, Object> map = new HashMap<>();

        int cnt = roomInfoService.deleteRoom(ids);
        System.out.println("삭제 완료 개수 : " + cnt);

        map.put("cnt", cnt);

        return map;
    }

    // 호스트 객실 상세보기
    @RequestMapping(value = "/roomlist/view", method = RequestMethod.GET)
    public ModelAndView roomdetail(@RequestParam("id") String id) {

        ModelAndView mv = new ModelAndView();
        
        if (id != null) {
            RoomInfoDTO room = roomInfoService.getRoom(Integer.parseInt(id));
            mv.addObject("room", room);
        }
        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");
        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);
            PriceDTO priceDTO = priceService.findPriceDTO(hostInfoDTO);
            mv.addObject("hostInfoDTO", hostInfoDTO);  
            mv.addObject("priceDTO", priceDTO);  

            // hCate 값을 추출하여 문자열로 변환
            if (hostInfoDTO.getHCate() != null) {
                mv.addObject("hCate", hostInfoDTO.getHCate().name());
            }

            if (priceDTO.getPeakSet() == 1) {
                if (priceDTO.getSemi() != null && priceDTO.getSemi().getSemiStart() != null) {
                    mv.addObject("semiPeak", priceDTO.getSemi().getSemiStart());
                }
    
                if (priceDTO.getPeakVo() != null && priceDTO.getPeakVo().getPeakStart() != null) {
                    mv.addObject("peak", priceDTO.getPeakVo().getPeakStart());
                }
            }

            List<ImageDTO> image = imageService.getImages(Integer.parseInt(id));
            mv.addObject("image", image);

        mv.addObject("storage", upload);    
        mv.setViewName("host/host_roomchange");
        return mv;
    }

    // 호스트 공지사항 전체 리스트
    @RequestMapping("/noticelist/getList")
    @ResponseBody
    public Map<String, Object> noticelist() {
        Map<String, Object> map = new HashMap<>();

        List<ServiceDTO> list = serviceService.getAllServices(SCate.NOTICE, UserAuth.HOST);

        map.put("data", list);

        return map;
    }

    // 호스트 공지사항 검색
    @RequestMapping("/noticelist/search")
    @ResponseBody
    public Map<String, Object> noticesearch(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "keyword", required = false) String keyword) {

        Map<String, Object> map = new HashMap<>();

        List<ServiceDTO> list = serviceService.searchHostNotice(type, keyword, UserAuth.HOST, SCate.NOTICE);

        map.put("list", list);

        return map;
    }

    //호스트 공지사항 상세보기
    @RequestMapping(value = "noticelist/view", method = RequestMethod.GET)
    public ModelAndView noticedetail(@RequestParam("id") String id) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("host/host_noticedetail");
        
        if (id != null) {
            ServiceDTO sDto = serviceService.getServices(Integer.parseInt(id));
            mv.addObject("sDto", sDto);
            mv.addObject("storage", upload);
        }

        return mv;
    }

    //호스트 공지사항 첨부 다운로드
    @RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> fileDownload(@RequestParam("fName") String fName)
            throws FileNotFoundException, UnsupportedEncodingException {

        // 버킷 이름과 파일 이름을 추출
        String bucketName = upload.replace("https://storage.googleapis.com/", "").replace("/", "");
        String filePath = "notice/" + fName; // 파일 경로 설정

        Storage storage = StorageOptions.getDefaultInstance().getService();
        Blob blob = storage.get(bucketName, filePath);

        if (blob == null || !blob.exists()) {
            throw new IOException("File not found");
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(blob.getContent());
        InputStreamResource resource = new InputStreamResource(bis);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=" + new String(fName.getBytes("UTF-8"), "ISO-8859-1"));
        headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream;charset=8859_1");
        headers.add(HttpHeaders.CONTENT_ENCODING, "binary");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(blob.getSize())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // 호스트 리뷰 전체 리스트
    @RequestMapping("/reviewlist/getList")
    @ResponseBody
    public Map<String, Object> reviewlist() {
        Map<String, Object> map = new HashMap<>();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);

        List<ReviewDTO> list = reviewService.getAllReview(hostInfoDTO);

        map.put("data", list);

        return map;
    }

    // 호스트 리뷰 검색
    @RequestMapping("/reviewlist/search")
    @ResponseBody
    public Map<String, Object> reviewsearch(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "keyword", required = false) String keyword) {

        Map<String, Object> map = new HashMap<>();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);

        int hIdx = hostInfoDTO.getId();

        List<ReviewDTO> list = reviewService.searchHostReview(type, keyword, hIdx);

        map.put("list", list);

        return map;
    }

    //호스트 리뷰 상세보기
    @RequestMapping(value = "reviewlist/view", method = RequestMethod.GET)
    public ModelAndView reviewdetail(@RequestParam("id") String id) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("host/host_reviewdetail");
        
        
        ReviewDTO review = reviewService.getReview(Integer.parseInt(id));
        mv.addObject("review", review);
        if(review.getRevFileImageNameVo().getFName() != null){
            String fnames = review.getRevFileImageNameVo().getFName();
            List<String> fname= Arrays.asList(fnames.split(","));
            mv.addObject("fname", fname);
        }
        
        ReviewCommentDTO reviewCommentDTO = reviewCommentService.getRevComment(review);
        mv.addObject("reviewCommentDTO", reviewCommentDTO);

        mv.addObject("storage", upload);
        
        return mv;
    }

    //호스트 리뷰답변 등록
    @RequestMapping(value = "/regRevComment")
public ResponseEntity<String> regRevComment(@RequestParam("revIdx") String revIdx, @RequestParam("rcComment") String rcComment) {
    
    ReviewDTO reviewDTO = reviewService.getReview(Integer.parseInt(revIdx));
    reviewCommentService.regRevComment(reviewDTO, rcComment);

    return ResponseEntity.ok("success");
    }

    //호스트 리뷰답변 삭제
    @ResponseBody
    @RequestMapping("/delRevComment")
    public ResponseEntity<String> delRevComment(@RequestParam("revIdx") String revIdx) {


        ReviewDTO reviewDTO = reviewService.getReview(Integer.parseInt(revIdx));

        reviewCommentService.delRevComment(reviewDTO);
        

        return ResponseEntity.ok("success");
    }

    // 호스트 예약 전체 리스트
    @RequestMapping("/reslist/getList")
    @ResponseBody
    public Map<String, Object> reslist() {
        Map<String, Object> map = new HashMap<>();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);

        List<RoomInfoDTO> roomInfoDTO = roomInfoService.getAllRoom(hostInfoDTO, BooleanStatus.TRUE);

        List<HostReservationDTO> list = reservationService.getAllRes(roomInfoDTO);

        map.put("data", list);

        return map;
    }

    // 호스트 예약 검색
    @RequestMapping("/reslist/search")
    @ResponseBody
    public Map<String, Object> resSearch(
            @RequestParam(value = "resStatus", required = false) String resStatus,
            @RequestParam(value = "dateValue", required = false) String dateValue) {

        System.out.println("resStatus:"+resStatus);
        System.out.println("dateValue:"+dateValue);
        
        Map<String, Object> map = new HashMap<>();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);

        List<RoomInfoDTO> roomInfoDTO = roomInfoService.getAllRoom(hostInfoDTO, BooleanStatus.TRUE);

        List<HostReservationDTO> list = reservationService.searchRes(resStatus, dateValue, roomInfoDTO);

        map.put("list", list);

        return map;
    }

    // 호스트 예약 확정
    @ResponseBody
    @RequestMapping("/reslist/confirm")
    public Map<String, Object> resConfirm(@RequestParam("ids") List<Integer> ids) throws java.io.IOException {

        Map<String, Object> map = new HashMap<>();

        int cnt = reservationService.confirmRes(ids);

        map.put("cnt", cnt);

        // 확정 이메일 발송
        if (cnt > 0) {
            for (Integer id : ids) {
                HostReservationEmailDTO reservationDTO = reservationService.getRes(id);
                try {
                    emailService.sendResConfirmEmail(reservationDTO);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }

        return map;
    }

    // 호스트 예약 취소
    @ResponseBody
    @RequestMapping("/reslist/reject")
    public Map<String, Object> resReject(@RequestParam("ids") List<Integer> ids) throws java.io.IOException {

        Map<String, Object> map = new HashMap<>();

        int cnt = reservationService.rejectRes(ids);
        System.out.println("취소 완료 개수 : " + cnt);
        map.put("cnt", cnt);

        if(cnt > 0){
          for (Integer id : ids) {  
          HostReservationDTO reservationDTO = reservationService.getHostRes(id);
          System.out.println("reservationDTO:"+reservationDTO);
          if (reservationDTO != null && reservationDTO.getPayIdx() != null) {
            int payIdx = reservationDTO.getPayIdx();
            System.out.println("payIdx:"+payIdx);
            String paymentKey = reservationDTO.getPaymentKey();
            System.out.println("paymentKey:"+paymentKey);
            CancelRequestDTO cancelRequest = new CancelRequestDTO();
            cancelRequest.setResIdx(id);  
            cancelRequest.setPayIdx(payIdx);
            cancelRequest.setPaymentKey(paymentKey);
            cancelRequest.setCancelReason("호스트가 예약을 취소하였습니다.");
            PaymentController paymentController = applicationContext.getBean(PaymentController.class);
            paymentController.cancelPayment(cancelRequest);
          }
          HostReservationEmailDTO reservationEmailDTO = reservationService.getRes(id);
            try {
                emailService.sendResCancelEmail(reservationEmailDTO);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        }

        return map;
    }

    // 호스트 예약 상세보기
    @RequestMapping(value = "/reslist/view", method = RequestMethod.GET)
    public ModelAndView resDetail(@RequestParam("id") String id) {

        ModelAndView mv = new ModelAndView();
        
        if (id != null) {
            HostReservationDTO hres = reservationService.getHostRes(Integer.parseInt(id));
            mv.addObject("hres", hres);
            LocalDate start = hres.getStartEndVo().getStart().toLocalDate();
            LocalDate end = hres.getStartEndVo().getEnd().toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime payDate = hres.getPayDate();
            String formattedPayDate = payDate.format(formatter);
            mv.addObject("start", start);
            mv.addObject("end", end);
            mv.addObject("payDate", formattedPayDate);
            int diff = end.compareTo(start);
            mv.addObject("diff", diff);
        }

        mv.setViewName("host/host_reservationdetail");
        return mv;
    }

    // 호스트 매출 전체 리스트
    @RequestMapping("/saleslist/getList")
    @ResponseBody
    public Map<String, Object> saleslist() {
        Map<String, Object> map = new HashMap<>();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);

        List<HostSalesDTO> list = salesService.getHostSales(hostInfoDTO.getId());

        Set<String> roomType = list.stream()
                                         .map(HostSalesDTO::getRoomType)
                                         .collect(Collectors.toSet());

        map.put("data", list);
        map.put("roomType", roomType);

        System.out.println("매출전체리스트출력 완료:"+list);

        return map;
    }
 
    // 호스트 매출 검색
    @RequestMapping("/saleslist/search")
    @ResponseBody
    public Map<String, Object> salesSearch(
            @RequestParam(value = "roomType", required = false) String roomType,
            @RequestParam(value = "dateValue", required = false) String dateValue) {

        System.out.println("roomType:"+roomType);
        System.out.println("dateValue:"+dateValue);
        
        Map<String, Object> map = new HashMap<>();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);

        if ("ALL".equals(roomType)) {
            roomType = null;
        }

        List<HostSalesDTO> list = salesService.searchHostSales(roomType, dateValue, hostInfoDTO.getId());

        map.put("list", list);

        return map;
    }

    // 호스트 정산 현재년도 전체 리스트
    @RequestMapping("/paylist/getList")
    @ResponseBody
    public Map<String, Object> paylist() {
        Map<String, Object> map = new HashMap<>();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);

        Integer nowYear = LocalDate.now().getYear();

        List<HostCalculationDTO> list = calculationService.getAllHostCal(hostInfoDTO.getId(),nowYear);

        map.put("data", list);

        return map;
    }

    // 호스트 정산 년도 검색 리스트
    @RequestMapping("/paylist/search")
    @ResponseBody
    public Map<String, Object> paySearch(@RequestParam(value = "year", required = false) Integer year) {
        System.out.println("정산년도 검색 왔다");
        System.out.println("year:"+year);
        Map<String, Object> map = new HashMap<>();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);

        List<HostCalculationDTO> list = calculationService.getAllHostCal(hostInfoDTO.getId(),year);

        map.put("list", list);

        return map;
    }

    // 호스트 정산 요청하기
    @RequestMapping("/paylist/request")
    @ResponseBody
    public Map<String, Object> payRequest(@RequestBody List<HostCalculationDTO> items) {

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);

        Integer hIdx = hostInfoDTO.getId();

        Map<String, Object> map = new HashMap<>();
        
        int cnt = 0;

        for(HostCalculationDTO item : items){
            cnt += calculationService.insertCal(item, hIdx);
        }
        if(cnt > 0){
            map.put("cnt", cnt);
        }else{
            map.put("cnt", "정산 요청 가능한 상태가 아닙니다.");
        }

        return map;
    }

    // 호스트 정산 상세보기
    @RequestMapping(value = "/paylist/view", method = RequestMethod.GET)
    public ModelAndView payDetail(@RequestParam("year") Integer year, @RequestParam("month") Integer month) throws Exception {

        ModelAndView mv = new ModelAndView();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);

        List<HostSalesDTO> list = calculationService.getMonthHostCal(hostInfoDTO.getId(),year,month);
        // ObjectMapper를 사용하여 list를 JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonList = objectMapper.writeValueAsString(list);

        mv.addObject("list", jsonList); // JSON 문자열을 추가
        mv.addObject("year", year);
        mv.addObject("month", month);
        mv.setViewName("host/host_paymentdetail");
        
        return mv;
        
    }

    // 호스트 입실예정 리스트
    @RequestMapping("/checkInList/getList")
    @ResponseBody
    public Map<String, Object> checkInList() {
        Map<String, Object> map = new HashMap<>();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");
        if (adminMember != null) {
            HostInfoDTO hostInfoDTO = hostInfoService.findHostInfoDTO(adminMember);
            if (hostInfoDTO != null) {
                List<RoomInfoDTO> roomInfoDTO = roomInfoService.getAllRoom(hostInfoDTO, BooleanStatus.TRUE);
                if(roomInfoDTO != null){
                    List<HostReservationDTO> checkInList = reservationService.findReservationsByCheckIn(roomInfoDTO);
                    map.put("data", checkInList);
                }
            }
        }

        return map;
    }


}
