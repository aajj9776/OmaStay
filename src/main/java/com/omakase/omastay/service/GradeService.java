package com.omakase.omastay.service;

import com.omakase.omastay.entity.Grade;
import com.omakase.omastay.repository.GradeRepository;

import org.checkerframework.checker.units.qual.g;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public Grade getGrade(Integer gIdx) {
        return gradeRepository.findById(gIdx).get();
    }


}
