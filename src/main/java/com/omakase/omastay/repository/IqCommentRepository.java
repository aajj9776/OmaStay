package com.omakase.omastay.repository;

import com.omakase.omastay.entity.IqComment;
import com.omakase.omastay.repository.custom.IqCommentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IqCommentRepository extends JpaRepository<IqComment, Integer>, IqCommentRepositoryCustom {

}