package com.omakase.omastay.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.omakase.omastay.dto.ServiceDTO;
import com.omakase.omastay.service.ServiceService;
import com.omakase.omastay.util.FileRenameUtil;
import com.omakase.omastay.vo.FileImageNameVo;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // 파일 업로드 경로 -> application.properties에 설정
    // @Value("${upload}")
    private String upload = "/upload/admin";

    @Autowired
    ServiceService ss;

    @Autowired
    private ServletContext application;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/login")
    public String login() {
        return "admins/login";
    }

    @RequestMapping("/main")
    public String main() {
        return "admins/main";
    }

    @RequestMapping("/request")
    public String request() {
        return "admins/request";
    }

    @RequestMapping("/request_detail")
    public String request_detail() {
        return "admins/request_detail";
    }

    @RequestMapping("/request_room")
    public String request_room() {
        return "admins/modals/request_room";
    }

    @RequestMapping("/payment")
    public String payment() {
        return "admins/payment";
    }

    @RequestMapping("/payment_detail")
    public String payment_detail() {
        return "admins/payment_detail";
    }

    @RequestMapping("/sales")
    public String sales() {
        return "admins/sales";
    }

    /****************************** 가맹점 공지사항 ******************************/
    // 가맹점 공지사항 리스트로 이동
    @RequestMapping("/host_notice")
    public String host_notice() {
        return "admins/host_notice";
    }

    // 가맹점 공지사항 전체 리스트 가져오기
    @RequestMapping("/host_notice/getList")
    @ResponseBody
    public Map<String, Object> host_notice_list() {
        Map<String, Object> map = new HashMap<>();

        List<ServiceDTO> list = ss.getAllHostNotice();

        map.put("data", list);

        return map;
    }

    // 가맹점 공지사항 검색하기
    @RequestMapping("/host_notice/search")
    @ResponseBody
    public Map<String, Object> host_notice_search(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "date", required = false) String date) {

        Map<String, Object> map = new HashMap<>();

        List<ServiceDTO> list = ss.searchHostNotice(type, keyword, date);

        map.put("list", list);

        return map;
    }

    // 가맹점 공지사항 삭제하기
    @ResponseBody
    @RequestMapping("/host_notice/delete")
    public Map<String, Object> host_notice_delete(@RequestParam("ids") List<Integer> ids) {

        Map<String, Object> map = new HashMap<>();

        int cnt = ss.deleteHostNotice(ids);
        System.out.println("삭제 완료 개수 : " + cnt);

        map.put("cnt", cnt);

        return map;
    }

    // 가맹점 공지사항 세부 조회
    @RequestMapping(value = "/host_notice/view", method = RequestMethod.GET)
    public ModelAndView host_notice_detail(@RequestParam("id") String id) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("admins/host_notice_view");
        
        if (id != null) {
            ServiceDTO sDto = ss.getHostNotice(Integer.parseInt(id));
            mv.addObject("sDto", sDto);
        }

        return mv;
    }

    // 가맹점 공지사항 수정하기
    @RequestMapping(value = "/host_notice/modify", method = RequestMethod.GET)
    public ModelAndView host_notice_modify(@RequestParam("id") String id) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("admins/host_notice_modify");

        if (id != null) {
            ServiceDTO sDto = ss.getHostNotice(Integer.parseInt(id));
            mv.addObject("sDto", sDto);
        }

        return mv;
    }

    // 가맹점 공지사항 수정하기로 저장
    @RequestMapping(value = "/host_notice/modify", method=RequestMethod.POST)
    public String requestMethodName(ServiceDTO modified, @RequestParam("file") MultipartFile f, @RequestParam("selectedFile") String selectedFile) {
    
        ServiceDTO sDto = ss.getHostNotice(modified.getId());
        
        // 해당 id에 대해 modified 객체의 값으로 수정한다.
        sDto.setSTitle(modified.getSTitle());
        sDto.setSContent(modified.getSContent());

        // 파일이 수정되었을 경우
        if(f.getSize() > 0) {
            String realPath = application.getRealPath(upload);
            String fname = f.getOriginalFilename();
            FileImageNameVo fvo = new FileImageNameVo();
            fvo.setOName(fname);
            fname = FileRenameUtil.checkSameFileName(fname, realPath);
            fvo.setFName(fname);
            try {
                File uploadDir = new File(realPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                File dest = new File(uploadDir, fname);
                f.transferTo(dest);
                sDto.setFileName(fvo);

                //ss에서 업데이트 하기
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(selectedFile.length()<1 && sDto.getFileName() != null) { //파일이 있었는데 삭제된 경우 -> fileName을 null로 바꾸고 업데이트
            //파일이 있었는데 삭제된 경우 -> fileName을 null로 바꾸고 업데이트
            sDto.setFileName(null);

        } else {
            // 그냥 title이랑 content만 업데이트하는 경우
            // 1) 파일이 원래 없었고 file name도 nulll일때
            // 2) 파일이 계속 유지되는 경우 -> f == null fileName은 있는 경우
        }

        ss.modifyHostNotice(sDto); // 업데이트: sDto에는 수정된 값이 들어있음

        return "redirect:/admin/host_notice/view?id=" + sDto.getId();
    }
    
    // 가맹점 공지사항 글쓰기로 이동
    @RequestMapping(value = "/host_notice/write", method = RequestMethod.GET)
    public String host_notice_write() {

        return "admins/host_notice_write";
    }

    // 가맹점 공지사항 글쓰기로 저장
    @RequestMapping(value = "/host_notice/write", method = RequestMethod.POST)
    public ModelAndView write(ServiceDTO sDto, @RequestParam("file") MultipartFile f) {
        // 폼양식에서 첨부파일이 전달될 때 enctype이 지정된다.
        String c_type = request.getContentType();
        if (c_type.startsWith("multipart")) {

            String fname = null;
            if (f != null && f.getSize() > 0) {
                String realPath = application.getRealPath(upload);

                fname = f.getOriginalFilename();
                FileImageNameVo fvo = new FileImageNameVo();
                fvo.setOName(fname);
                fname = FileRenameUtil.checkSameFileName(fname, realPath);
                fvo.setFName(fname);

                try {
                    File uploadDir = new File(realPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    // 파일 업로드(upload폴더에 저장)
                    File dest = new File(uploadDir, fname);
                    f.transferTo(dest);

                    sDto.setFileName(fvo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            ss.saveHostNotice(sDto);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/admin/host_notice");

        return mv;
    }

    // 가맹점 공지사항 글쓰기 이미지 첨부
    @RequestMapping(value = "/saveImg", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> saveImg(@RequestParam("s_file") MultipartFile f) throws java.io.IOException {

        // 반환객체 생성
        Map<String, String> map = new HashMap<String, String>();
        String fname = null;

        if (f.getSize() > 0) { 

            String realPath = application.getRealPath(upload); //실행되는 tomcat 서버의 경로

            fname = f.getOriginalFilename();
            fname = FileRenameUtil.checkSameFileName(fname, realPath);

            try { 
                File uploadDir = new File(realPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // 전달된 파일을 저장합니다.
                File dest = new File(uploadDir, fname);
                f.transferTo(dest);
                map.put("fname", fname);

            } catch (IOException e) {
                e.printStackTrace();
                map.put("error", "File upload failed");
            }
        } else {
            map.put("error", "File is empty");
        }

        map.put("url", upload + System.getProperty("file.separator") + fname);

        return map;
    }

    @RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> fileDownload(@RequestParam("fName") String fName)
            throws FileNotFoundException, UnsupportedEncodingException {

        String realPath = application.getRealPath(upload);

        // 전체경로를 만들어서 File객체 생성
        String fullPath = realPath + System.getProperty("file.separator") + fName;
        File file = new File(fullPath);

        if (!file.exists() || !file.isFile()) {
            throw new IOException("File not found");
        }
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        InputStreamResource resource = new InputStreamResource(bis);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=" + new String(fName.getBytes("UTF-8"), "ISO-8859-1"));
        headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream;charset=8859_1");
        headers.add(HttpHeaders.CONTENT_ENCODING, "binary");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    /***************************** 가맹점 공지사항 *****************************/

    @RequestMapping("/host_inquiry")
    public String host_inquiry() {
        return "admins/host_inquiry";
    }

    @RequestMapping("/host_inquiry_answer")
    public String host_inquiry_answer() {
        return "admins/host_inquiry_answer";
    }

    @RequestMapping("/member")
    public String member() {
        return "admins/member";
    }

    @RequestMapping("/user_notice")
    public String user_notice() {
        return "admins/user_notice";
    }

    @RequestMapping("/user_notice_write")
    public String user_notice_write() {
        return "admins/user_notice_write";
    }

    @RequestMapping("/user_event")
    public String user_event() {
        return "admins/user_event";
    }

    @RequestMapping("/user_event_write")
    public String user_event_write() {
        return "admins/user_event_write";
    }

    @RequestMapping("/user_qna")
    public String user_qna() {
        return "admins/user_qna";
    }

    @RequestMapping("/user_qna_write")
    public String user_qna_write() {
        return "admins/user_qna_write";
    }

    @RequestMapping("/grade")
    public String grade() {
        return "admins/grade";
    }

    @RequestMapping("/coupon")
    public String coupon() {
        return "admins/coupon";
    }

    @RequestMapping("/coupon_history")
    public String coupon_history() {
        return "admins/modals/coupon_history";
    }

    @RequestMapping("/add_coupon")
    public String add_coupon() {
        return "admins/modals/add_coupon";
    }

    @RequestMapping("/point")
    public String point() {
        return "admins/point";
    }

    @RequestMapping("/add_point")
    public String add_point() {
        return "admins/modals/add_point";
    }

    @RequestMapping("/recommendation")
    public String recommend() {
        return "admins/recommendation";
    }

}
