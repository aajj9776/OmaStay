package com.omakase.omastay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.omakase.omastay.dto.ServiceDTO;
import com.omakase.omastay.service.ServiceService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ServiceService ss;

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

    //가맹점 공지사항 전체 보기
    @RequestMapping("/host_notice")
    public ModelAndView host_notice() {

        ModelAndView mv = new ModelAndView();

        List<ServiceDTO> list = ss.getAllHostNotice();
        mv.addObject("list", list);
        mv.setViewName("admins/host_notice");
        
        return mv;
    }

    //가맹점 공지사항 삭제하기
    @ResponseBody
    @RequestMapping("/host_notice/delete")
    public Map<String, Object> host_notice_delete(@RequestParam("ids") List<Integer> ids) {
        
        Map<String, Object> map =new HashMap<>();
        System.out.println("도착");
        int cnt = ss.deleteHostNotice(ids);
        System.out.println("cnt : " + cnt);
        
        return map;
    }

    @RequestMapping("/host_notice_write")
    public String host_notice_write() {
        return "admins/host_notice_write";
    }

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
