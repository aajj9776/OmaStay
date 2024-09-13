package com.omakase.omastay.repository.custom;

import java.util.List;

public interface ImageRepositoryCustom {


    List<String> findImageNamesByHostIds(List<Integer> hostIds);
}
