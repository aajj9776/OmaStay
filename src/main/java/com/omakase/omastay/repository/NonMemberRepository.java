package com.omakase.omastay.repository;

import com.omakase.omastay.entity.NonMember;
import com.omakase.omastay.repository.custom.NonMemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonMemberRepository extends JpaRepository<NonMember, Integer>, NonMemberRepositoryCustom {

}