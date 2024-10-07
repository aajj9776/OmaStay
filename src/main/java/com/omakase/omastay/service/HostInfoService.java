package com.omakase.omastay.service;

import com.omakase.omastay.dto.AccountDTO;
import com.omakase.omastay.dto.AdminMemberDTO;
import com.omakase.omastay.dto.FacilitiesDTO;
import com.omakase.omastay.dto.HostFacilitiesDTO;
import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.dto.custom.HostInfoCustomDTO;
import com.omakase.omastay.dto.custom.HostMypageDTO;
import com.omakase.omastay.dto.custom.HostRequestInfoDTO;
import com.omakase.omastay.entity.Account;
import com.omakase.omastay.entity.AdminMember;
import com.omakase.omastay.entity.Facilities;
import com.omakase.omastay.entity.HostFacilities;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Image;
import com.omakase.omastay.entity.Price;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.HCate;
import com.omakase.omastay.entity.enumurate.HStatus;
import com.omakase.omastay.entity.enumurate.HStep;
import com.omakase.omastay.entity.enumurate.ImgCate;
import com.omakase.omastay.mapper.AccountMapper;
import com.omakase.omastay.mapper.AdminMemberMapper;
import com.omakase.omastay.mapper.FacilitiesMapper;
import com.omakase.omastay.mapper.HostInfoMapper;
import com.omakase.omastay.mapper.ImageMapper;
import com.omakase.omastay.mapper.PriceMapper;
import com.omakase.omastay.mapper.RoomInfoMapper;
import com.omakase.omastay.repository.AccountRepository;
import com.omakase.omastay.repository.AdminMemberRepository;
import com.omakase.omastay.repository.FacilitiesRepository;
import com.omakase.omastay.repository.HostFacilitiesRepository;
import com.omakase.omastay.repository.HostInfoRepository;
import com.omakase.omastay.repository.ImageRepository;
import com.omakase.omastay.repository.PriceRepository;
import com.omakase.omastay.repository.RoomInfoRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Optional;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HostInfoService {

    @Autowired
    private HostInfoRepository hostInfoRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HostFacilitiesRepository hostFacilitiesRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FacilitiesRepository facilitiesRepository;

    @Autowired
    private RoomInfoRepository roomInfoRepository;

    @Autowired
    private AdminMemberRepository adminMemberRepository;

    @Autowired
    private PriceRepository priceRepository;

    public HostInfoDTO findHostInfoDTO(AdminMemberDTO adminMemberDTO) {
        AdminMember adminMember = AdminMemberMapper.INSTANCE.toAdminMember(adminMemberDTO);
        HostInfo hostInfo = hostInfoRepository.findByAdminMemberId(adminMember.getId());
        return HostInfoMapper.INSTANCE.toHostInfoDTO(hostInfo);
    }

    public void saveHostMypage(HostMypageDTO hostMypageDTO, AdminMemberDTO adminMemberDTO) {
        System.out.println(hostMypageDTO);

        System.out.println(adminMemberDTO);
        
        AdminMember adminMember = AdminMemberMapper.INSTANCE.toAdminMember(adminMemberDTO);
        
        HostInfo hostInfo = hostInfoRepository.findByAdminMemberId(adminMember.getId());
        if (hostInfo == null) {
            hostInfo = new HostInfo();
            hostInfo.setAdminMember(adminMember); // AdminMember 설정
            hostInfo.setHStep(HStep.MYPAGE); // hStep을 0으로 설정
        }
        hostInfo.setHostContactInfo(hostMypageDTO.getHostInfo().getHostContactInfo());
        hostInfo.setHurl(hostMypageDTO.getHostInfo().getHurl());
        hostInfo.setHname(hostMypageDTO.getHostInfo().getHname());
        hostInfo.setHphone(hostMypageDTO.getHostInfo().getHphone());
        hostInfoRepository.save(hostInfo);

        Account account = accountRepository.findByHostInfoId(hostInfo.getId());
        if(account == null) {
            account = new Account();
            account.setHostInfo(hostInfo);
        }
        account.setAcBank(hostMypageDTO.getAccount().getAcBank());
        account.setAcName(hostMypageDTO.getAccount().getAcName());
        account.setAcNum(hostMypageDTO.getAccount().getAcNum());
        accountRepository.save(account); 
    }

    public HostMypageDTO findHostMypageByAdminMember(AdminMemberDTO adminMember) {
        System.out.println("마이페이지 서비스 왔다");
        HostInfo hostInfo = hostInfoRepository.findByAdminMemberId(adminMember.getId());
        System.out.println(hostInfo);
        Account account = null;
        if (hostInfo != null) {
            account = accountRepository.findByHostInfoId(hostInfo.getId());
            System.out.println(account);
            System.out.println(account.getHostInfo());
        }

        AccountDTO accountDTO = null;
        HostInfoDTO hostInfoDTO = null;

        if (account != null) {
            try {
                accountDTO = AccountMapper.INSTANCE.toAccountDTO(account);
                System.out.println(accountDTO);
                if (account.getHostInfo() != null) {
                    System.out.println(account.getHostInfo());
                } else {
                    System.out.println("HostInfo is null for the given account");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if (hostInfo != null) {
            try {
                hostInfoDTO = HostInfoMapper.INSTANCE.toHostInfoDTO(hostInfo);
                System.out.println(hostInfoDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return new HostMypageDTO(accountDTO, hostInfoDTO);
    }

    public void saveHostInfo(HostInfoCustomDTO hostInfoCustomDTO, AdminMemberDTO adminMemberDTO) {
        System.out.println(hostInfoCustomDTO.getHostInfo().getAddressVo().getStreet());
        System.out.println(adminMemberDTO);
        
        AdminMember adminMember = AdminMemberMapper.INSTANCE.toAdminMember(adminMemberDTO);
        
        HostInfo hostInfo = hostInfoRepository.findByAdminMemberId(adminMember.getId());

        if(hostInfo.getHStep() == HStep.MYPAGE) {
            hostInfo.setHStep(HStep.INFO); // hStep을 1로 설정
        }
        
        hostInfo.setHCate(HCate.valueOf(hostInfoCustomDTO.getHostInfo().getHCate().name()));
        hostInfo.setRegion(hostInfoCustomDTO.getHostInfo().getRegion());
        hostInfo.setXAxis(hostInfoCustomDTO.getHostInfo().getXAxis());
        hostInfo.setYAxis(hostInfoCustomDTO.getHostInfo().getYAxis());
        hostInfo.setDirections(hostInfoCustomDTO.getHostInfo().getDirections());
        hostInfo.setHostAddress(hostInfoCustomDTO.getHostInfo().getAddressVo());
        hostInfo.setHostOwnerInfo(hostInfoCustomDTO.getHostInfo().getHostOwnerInfo());
        hostInfoRepository.save(hostInfo);

        List<HostFacilities> hostFacilities = hostFacilitiesRepository.findByHostInfoId(hostInfo.getId());
        
        List<FacilitiesDTO> newFacilities = hostInfoCustomDTO.getFacilities();;

        if (hostFacilities != null) {
            // 기존 Facilities 목록에서 새로운 목록에 없는 항목 삭제
            List<HostFacilities> facilitiesToRemove = hostFacilities.stream()
                    .filter(hostFacility -> !newFacilities.contains(hostFacility.getFacilities()))
                    .collect(Collectors.toList());
            hostFacilitiesRepository.deleteAll(facilitiesToRemove);
        }
        
        // 새로운 Facilities 목록 추가
        for (FacilitiesDTO facility : newFacilities) {
            boolean exists = hostFacilities != null && hostFacilities.stream()
            .anyMatch(hostFacility -> hostFacility.getFacilities().equals(facility));
            if (!exists) {
                HostFacilities newHostFacility = new HostFacilities();
                newHostFacility.setHostInfo(hostInfo);
                newHostFacility.setFacilities(FacilitiesMapper.INSTANCE.toFacilities(facility));
                hostFacilitiesRepository.save(newHostFacility);
            }
        }

        List<Image> images = imageRepository.findByHostInfoId(hostInfo.getId());

        for (ImageDTO imageDTO : hostInfoCustomDTO.getImages()) {
        boolean exists = images.stream()
                .anyMatch(image -> image.getImgName().equals(imageDTO.getImgName()));
        if (!exists) {
            Image newImage = new Image();
            newImage.setRoomInfo(null);
            newImage.setHostInfo(hostInfo);
            newImage.setImgName(imageDTO.getImgName());
            newImage.setImgCate(ImgCate.HOST);
            newImage.setImgStatus(BooleanStatus.TRUE);
            imageRepository.save(newImage);
        }
    }
    }

    public HostInfoCustomDTO findHostInfoByHostInfoId(int hIdx) {

        HostInfo hostInfo = hostInfoRepository.findById(hIdx);

        List<FacilitiesDTO> facilitiesDTO= null;
        List<ImageDTO> imageDTO = null;
        if (hostInfo != null) {
            List<HostFacilities> hostFacilitiesList = hostFacilitiesRepository.findByHostInfoId(hostInfo.getId());
            facilitiesDTO = hostFacilitiesList.stream()
                .map(hostFacilities -> {
                    Optional<Facilities> facilitiesOptional = facilitiesRepository.findById(hostFacilities.getFacilities().getId());
                    Facilities facilities = facilitiesOptional.orElse(null);
                    return FacilitiesMapper.INSTANCE.toFacilitiesDTO(facilities);
                })
                .collect(Collectors.toList());
            
            List<Image> imageList = imageRepository.findByHostInfoId(hostInfo.getId());
            imageDTO = imageList.stream()
                .map(image -> ImageMapper.INSTANCE.toImageDTO(image))
                .collect(Collectors.toList());
        }

        HostInfoCustomDTO hostInfoCustomDTO = new HostInfoCustomDTO();
        hostInfoCustomDTO.setHostInfo(HostInfoMapper.INSTANCE.toHostInfoDTO(hostInfo));
        hostInfoCustomDTO.setFacilities(facilitiesDTO);
        hostInfoCustomDTO.setImages(imageDTO);
        
        return hostInfoCustomDTO;
    }

    public HostInfoDTO saverules(HostInfoDTO hostInfoDTO, AdminMemberDTO adminMemberDTO) {

        AdminMember adminMember = AdminMemberMapper.INSTANCE.toAdminMember(adminMemberDTO);
        
        HostInfo hostInfo = hostInfoRepository.findByAdminMemberId(adminMember.getId());

        if(hostInfo.getHStep() == HStep.INFO) {
            hostInfo.setHStep(HStep.RULE); // hStep을 2로 설정
        }

        hostInfo.setCheckin(hostInfoDTO.getCheckin());
        hostInfo.setCheckout(hostInfoDTO.getCheckout());
        hostInfo.setRules(hostInfoDTO.getRules());
        hostInfo.setPriceAdd(hostInfoDTO.getPriceAdd());
        hostInfoRepository.save(hostInfo);

        return HostInfoMapper.INSTANCE.toHostInfoDTO(hostInfo);
    }

    public void requestAdmin(HostInfoDTO hostInfoDTO) {

        HostInfo hostInfo = HostInfoMapper.INSTANCE.toHostInfo(hostInfoDTO);

        if(hostInfo.getHStep() == HStep.ROOM) {
            if(hostInfo.getHStatus() != HStatus.APPLY && hostInfo.getHStatus() != HStatus.APPROVE) {
                hostInfo.setHStatus(HStatus.APPLY); // hStep을 3으로 설정
            }
        }

        hostInfoRepository.save(hostInfo);
    }



    /* 관리자에서 쓰는 거 */
    public List<HostInfoDTO> getAllHostInfos() {
        List<HostInfo> hostInfos = hostInfoRepository.hostInfos();
        return HostInfoMapper.INSTANCE.toHostInfoDTOList(hostInfos);
    }

    public HostRequestInfoDTO getHostRequestInfo(int id){
        HostRequestInfoDTO hostRequestInfoDTO = new HostRequestInfoDTO();

        HostInfo hostInfo = hostInfoRepository.findById(id);
        System.out.println(hostInfo);
         // adminMember 엔티티를 명시적으로 로드
        
        hostRequestInfoDTO.setHostInfo(HostInfoMapper.INSTANCE.toHostInfoDTO(hostInfo));

        Account account = accountRepository.findByHostInfoId(hostInfo.getId());
        System.out.println(account);
        hostRequestInfoDTO.setAccount(AccountMapper.INSTANCE.toAccountDTO(account));

        List<HostFacilitiesDTO> hostFacilities = hostFacilitiesRepository.findByHostInfoIdAndFacilities(hostInfo.getId());
        System.out.println(hostFacilities);

        List<Facilities> facilities = new ArrayList<>();

        for(HostFacilitiesDTO hf : hostFacilities){
            Facilities facility = facilitiesRepository.findById2(hf.getFIdx());
            facilities.add(facility);
        }
        System.out.println(facilities);
        hostRequestInfoDTO.setFacilities(FacilitiesMapper.INSTANCE.toFacilitiesDTOList(facilities));

        List<RoomInfo> roomInfos = roomInfoRepository.findByHostInfo(hostInfo);
        System.out.println(roomInfos);
        hostRequestInfoDTO.setRoomInfo(RoomInfoMapper.INSTANCE.toRoomInfoDTOList(roomInfos)); 
        
        Price price = priceRepository.findFirstByHostInfoId(hostInfo.getId());

        hostRequestInfoDTO.setPrice(PriceMapper.INSTANCE.toPriceDTO(price));

        List<Image> images = imageRepository.findByHostInfoId(hostInfo.getId());
        System.out.println(images);
        hostRequestInfoDTO.setImages(ImageMapper.INSTANCE.toImageDTOList(images));

        Hibernate.initialize(hostRequestInfoDTO.getHostInfo().getAdIdx());

        return hostRequestInfoDTO;
    }

    @Transactional 
    public void approveHost(int hidx){
        hostInfoRepository.approveHost(hidx);
    }

    @Transactional 
    public void rejectHost(int hidx){
        hostInfoRepository.rejectHost(hidx);
    }

    public List<HostInfo> getDetailHostInfo(Integer hIdx){
        return hostInfoRepository.findHostInfoAll(hIdx);

    }

}
