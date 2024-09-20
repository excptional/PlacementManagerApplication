package com.exceptional.PlacementManager.service;

import com.exceptional.PlacementManager.dto.OfferDto;
import com.exceptional.PlacementManager.entity.OfferEntity;
import com.exceptional.PlacementManager.repository.CollegeRepository;
import com.exceptional.PlacementManager.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final OfferRepository offerRepository;
    private final CollegeRepository collegeRepository;
    private final CollegeService collegeService;
    private final AuthService authService;

    public ResponseEntity<String> addJobOffer(OfferDto offerDto) {

        String college = authService.getCurrentUser().getCollege().getName();
        if(collegeRepository.findByName(college) == null)
            return ResponseEntity.badRequest().body("College is not registered");
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setCompany(offerDto.getCompany());
        offerEntity.setCriteria(offerDto.getCriteria());
        offerEntity.setOffered_ctc(offerDto.getOffered_ctc());
        offerEntity.setArriving_time(offerDto.getArriving_time());
        offerEntity.setJob_type(offerDto.getJob_type());
        offerEntity.setJob_role(offerDto.getJob_role());
        offerEntity.setAdditional_info(offerDto.getAdditional_info());
        offerEntity.setJob_location(offerDto.getJob_location());
        offerEntity.setDepartments(offerDto.getDepartments());
        offerEntity.setSelected_candidates(offerDto.getSelected_candidates());
        offerEntity.setCollege(collegeService.findOrCreateCollege(college));
        offerEntity.setLogo(offerDto.getLogo());
        offerRepository.save(offerEntity);
        return ResponseEntity.ok("Company registered successfully");
    }

    public List<String> getSelectedCandidates(String company) {
        return offerRepository.findByCompany(company).getSelected_candidates();
    }

}
