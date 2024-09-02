package com.omakase.omastay.service;

import com.omakase.omastay.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;


}
