package com.omakase.omastay.service;

import java.util.List;

import com.omakase.omastay.entity.ReviewComment;
import com.omakase.omastay.repository.ReviewCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewCommentService {

    @Autowired
    private ReviewCommentRepository reviewCommentRepository;


}
