package com.omakase.omastay.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.omakase.omastay.dto.AdminMemberDTO;
import com.omakase.omastay.dto.FacilitiesDTO;
import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.dto.custom.HostInfoCustomDTO;
import com.omakase.omastay.dto.custom.HostMypageDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.service.AdminMemberService;
import com.omakase.omastay.service.EmailService;
import com.omakase.omastay.service.FacilitiesService;
import com.omakase.omastay.service.HostInfoService;
import com.omakase.omastay.util.FileRenameUtil;
import com.omakase.omastay.vo.FileImageNameVo;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletContext;
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
    
    private final EmailService emailService;

    @Autowired
    private HttpSession session;

    private String upload = "/upload/host";

    @Autowired
    private ServletContext application;

    @Autowired
    private HttpServletRequest request;
    
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

    @RequestMapping("/faq")
    public String hostfaq() {
        return "host/host_faq";
    }

    @RequestMapping("/info")
    public ModelAndView hostinfo(){
        System.out.println("숙소소개 컨트롤러 옴");
        ModelAndView mv = new ModelAndView();

        List<FacilitiesDTO> facilities = facilitiesService.all();

        AdminMemberDTO adminMember = (AdminMemberDTO) session.getAttribute("adminMember");

        if (adminMember != null) {
            HostMypageDTO hostMypageDTO = hostInfoService.findHostMypageByAdminMember(adminMember);

            if (hostMypageDTO.getHostInfo().getHStep() == null) {
                mv.addObject("errorMessage", "이전 단계를 완료해주세요.");
                mv.setViewName("host/host_mypage");
                return mv;
            }

            HostInfoCustomDTO hostInfoCustomDTO = hostInfoService.findHostInfoByHostInfoId(hostMypageDTO.getHostInfo().getId());
            mv.addObject("hostMypageDTO", hostMypageDTO);
            mv.addObject("hostInfoCustomDTO", hostInfoCustomDTO);
        }


        mv.addObject("facilities", facilities);
        mv.setViewName("host/host_info");

        return mv;
    }

    @RequestMapping("/inquiry")
    public String hostinquiry() {
        return "host/host_inquiry";
    }

    @RequestMapping("/inquirydetail")
    public String hostinquirydetail() {
        return "host/host_inquirydetail";
    }

    @RequestMapping("/inquiryreg")
    public String hostinquiryreg() {
        return "host/host_inquiryreg";
    }

    @RequestMapping("/main")
    public String hostmain() {
        return "host/host_main";
    }

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

    @RequestMapping("/roomreg")
    public String hostroomreg() {
        return "host/host_roomreg";
    }

    @RequestMapping("/rules")
    public String hostrules() {
        return "host/host_rules";
    }

    @RequestMapping("/sales")
    public String hostsales() {
        return "host/host_sales";
    }

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

    @RequestMapping("/emailsend")  
    public ResponseEntity<String> sendEmailPath(@RequestParam("email") String email) throws MessagingException { 
        try {
            emailService.sendEmail(email);  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("success");  
    }  
  
    @RequestMapping("/emailchecknum")  
    public ResponseEntity<String> sendEmailAndCode(@RequestParam("email") String email, @RequestParam("code") String code){  
        if (emailService.verifyEmailCode(email, code)) {  
            return ResponseEntity.ok("success");  
        }  
        return ResponseEntity.notFound().build();  
    }

    @RequestMapping("/hostregist")
    public ResponseEntity<String> hostregister(@RequestParam("id") String id, @RequestParam("pw") String pw, @RequestParam("email") String email) {
        boolean result = adminMemberService.hostregist(id, pw, email);
        if (result) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(500).body("Registration failed");
        }
    }

    @RequestMapping("/hostlogin")
    public ModelAndView hostlogin(@RequestParam("id") String id, @RequestParam("pw") String pw) {
        AdminMemberDTO adminMemberDTO = adminMemberService.hostlogin(id,pw);

        ModelAndView mv = new ModelAndView();
        if (adminMemberDTO != null) {
            session.setAttribute("adminMember", adminMemberDTO); // 세션에 사용자 정보 저장
            mv.addObject("adminMember", adminMemberDTO);
            mv.setViewName("host/host_main");
        } else {
            mv.addObject("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
            mv.setViewName("host/host_login");
        }

        return mv;
    }

    @RequestMapping("/idemailsend")  
    public ResponseEntity<String> sendIdEmail(@RequestParam("id") String id, @RequestParam("email") String email) throws MessagingException { 
        boolean isValid = adminMemberService.hostfindpw(id, email);

        if (isValid) {
            emailService.sendEmail(email);
            return ResponseEntity.ok("success");
        } else {
            String errorMessage = "아이디 또는 이메일이 일치하지 않습니다.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }  

    @RequestMapping("/changepw")
    public ResponseEntity<String> hostchangepw(@RequestParam("id") String id, @RequestParam("pw") String pw, @RequestParam("email") String email) {
        boolean result = adminMemberService.hostchagepw(id, email, pw);
        if (result) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(500).body("Change failed");
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("adminMember");
        return "host/host_login"; 
    }

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

    @RequestMapping("/hostinforeg")
    public ResponseEntity<List<String>> hostinforeg(@RequestPart("hostInfoCustomDTO") HostInfoCustomDTO hostInfoCustomDTO, @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        System.out.println(hostInfoCustomDTO.getHostInfo().getYAxis());
        System.out.println(hostInfoCustomDTO.getImages().size());
        AdminMemberDTO adminMember = (AdminMemberDTO)session.getAttribute("adminMember");

        List<String> imageUrls = new ArrayList<>();

        // 폼양식에서 첨부파일이 전달될 때 enctype이 지정된다.
        String c_type = request.getContentType();
        if (c_type.startsWith("multipart")) {
            List<ImageDTO> imageDTOList = new ArrayList<>();
            if (images != null) {
            for (MultipartFile f : images) {
            if (f != null && f.getSize() > 0) {
                String realPath = request.getServletContext().getRealPath(upload);

                String oname = f.getOriginalFilename();//실제파일명
                FileImageNameVo fvo = new FileImageNameVo();
                fvo.setOName(oname);
                String fname = FileRenameUtil.checkSameFileName(oname, realPath);
                fvo.setFName(fname);

                try {
                    File uploadDir = new File(realPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    // 파일 업로드(upload폴더에 저장)
                    File hostFile = new File(uploadDir, fname);
                    if (hostFile.exists()) {
                        System.out.println("파일 이름이 중복되어 업로드를 중단합니다: " + fname);
                        continue; // 중복된 파일이 있으면 업로드를 중단하고 다음 파일로 넘어갑니다.
                    }

                    ImageDTO imageDTO = new ImageDTO();
                    imageDTO.setImgName(fvo);
                    imageDTOList.add(imageDTO);

                    String imageUrl = "/upload/" + fname;
                    imageUrls.add(imageUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } 
            }
        }
            hostInfoCustomDTO.setImages(imageDTOList);
        }

        hostInfoService.saveHostInfo(hostInfoCustomDTO, adminMember);

        //html 에서 hostInfoCustomDTO 받아야함
        
        return ResponseEntity.ok(imageUrls);
    }
}
