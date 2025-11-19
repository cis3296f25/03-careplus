package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils; // Assuming BeanUtils is from Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bindings.DCFeedBackReportInputs;
import com.example.demo.bindings.PlanSelectionOptionsInputs;
import com.example.demo.bindings.DataCollectionIncomeInputs;
import com.example.demo.bindings.DataCollectionEducationInputs;
import com.example.demo.bindings.DataCollectionChildrenInputs;
import com.example.demo.bindings.CitizenAppRegistrationInputs;

import com.example.demo.entity.DataCollectionCaseEntity;
import com.example.demo.entity.CitizenAppRegistrationEntity;
import com.example.demo.entity.PlanOptionsEntity;
import com.example.demo.entity.DataCollectionIncomeEntity;
import com.example.demo.entity.DataCollectionEducationEntity;
import com.example.demo.entity.DataCollectionChildrenEntity;
import com.example.demo.repository.DataCollectionCaseRepository;
import com.example.demo.repository.CitizenAppRegistrationRepository;
import com.example.demo.repository.PlanOptionsRepository;
import com.example.demo.repository.DataCollectionIncomeRepository;
import com.example.demo.repository.DataCollectionEducationRepository;
import com.example.demo.repository.DataCollectionChildrenRepository;


/**
 * Implementation of the DataCollectionInterfaceService, handling
 * the business logic for data collection and application registration.
 */
@Service
public class DataCollectionManagementImplService implements DataCollectionInterfaceService {

    @Autowired
    private DataCollectionCaseRepository caseRepo;

    @Autowired
    private CitizenAppRegistrationRepository citizenAppRepo;

    @Autowired
    private PlanOptionsRepository planRepo;

    @Autowired
    private DataCollectionIncomeRepository incomeRepo;

    @Autowired
    private DataCollectionEducationRepository educationRepo;

    @Autowired
    private DataCollectionChildrenRepository childrenRepo;

    @Override
    public Integer generateCaseNo(Integer appId) {
        // Load Citizen Data
        Optional<CitizenAppRegistrationEntity> optCitizen = citizenAppRepo.findById(appId);

        if (optCitizen.isPresent()) {
            DataCollectionCaseEntity caseEntity = new DataCollectionCaseEntity();
            caseEntity.setAppId(appId);
            // Assuming getCaseNo() is used to retrieve the generated ID after save
            return caseRepo.save(caseEntity).getCaseNo(); // //save obj operation
        }
        return 0;
    }

    @Override
    public List<String> showAllPlanNames() {
        List<PlanOptionsEntity> plansList = planRepo.findAll();
        // Get only plan names using streaming api
        List<String> planNamesList = plansList.stream()
            .map(plan -> plan.getPlanName())
            .collect(Collectors.toList());
        return planNamesList;
    }

    @Override
    public Integer savePlanSelection(PlanSelectionOptionsInputs plan) {
        // Load DataCollectionCaseEntity object
        Optional<DataCollectionCaseEntity> optCase = caseRepo.findById(plan.getCaseNo()); // Assuming PlanSelectionOptions has getCaseNo()

        if (optCase.isPresent()) {
            DataCollectionCaseEntity caseEntity = optCase.get();
            caseEntity.setPlanId(plan.getPlanId()); // Assuming PlanSelectionOptions has getPlanId()

            // update the DataCollectionCaseEntity with plan id
            caseRepo.save(caseEntity);  //update obj operation
            return caseEntity.getCaseNo();
        }
        return 0;
    }

    @Override
    public Integer saveIncomeDetails(DataCollectionIncomeInputs income) {
        // Convert binding obj data to Entity class obj data
        DataCollectionIncomeEntity incomeEntity = new DataCollectionIncomeEntity();
        BeanUtils.copyProperties(income, incomeEntity);

        // save the income details
        incomeRepo.save(incomeEntity);
        // // return caseNo
        return income.getCaseNo(); // Assuming DataCollectionIncomeInputs has getCaseNo()
    }

    @Override
    public Integer saveEducationDetails(DataCollectionEducationInputs education) {
        // Convert Binding obj to Entity object
        DataCollectionEducationEntity educationEntity = new DataCollectionEducationEntity();
        BeanUtils.copyProperties(education, educationEntity);

        // save the obj
       // educationRepo.save(educationEntity);
        educationRepo.save(educationEntity);
        

        // //return the caseNumber
        return education.getCaseNo(); // Assuming DataCollectionEducationInputs has getCaseNo()
    }

    @Override
    public Integer saveChildrenDetails(List<DataCollectionChildrenInputs> children) {
       // Integer caseNo = null;

        // Convert each Binding class obj to each Entity class obj
        children.forEach(child->{
        	DataCollectionChildrenEntity childEntity = new DataCollectionChildrenEntity();
        	BeanUtils.copyProperties(child, childEntity);
        	 // save each entity obj
            childrenRepo.save(childEntity);
        });
         
        return children.get(0).getCaseNo();
    }

    @Override
    public DCFeedBackReportInputs showDcSummary(Integer caseNo) {
        // get multiple entity objs based on caseNo
        Optional<DataCollectionIncomeEntity> optIncomeEntity = incomeRepo.findByCaseNo(caseNo);
        Optional<DataCollectionEducationEntity> optEducationEntity = educationRepo.findByCaseNo(caseNo);
        List<DataCollectionChildrenEntity> childsEntityList = childrenRepo.findByCaseNo(caseNo);
        Optional<DataCollectionCaseEntity> optCaseEntity = caseRepo.findByCaseNo(caseNo);

        // get PlanName
        String planName = null;
        Integer appId = null;
        DataCollectionCaseEntity caseEntity = null;
        Optional<PlanOptionsEntity> optPlanEntity = Optional.empty();

        if (optCaseEntity.isPresent()) {
            caseEntity = optCaseEntity.get();
            Integer planId = caseEntity.getPlanId();
            appId = caseEntity.getAppId();
            optPlanEntity = planRepo.findById(planId);
            if (optPlanEntity.isPresent()) {
                planName = optPlanEntity.get().getPlanName();
            }
        }

        Optional<CitizenAppRegistrationEntity> optCitizenEntity = citizenAppRepo.findById(appId);
        CitizenAppRegistrationInputs citizenInput = null;

        if (optCitizenEntity.isPresent()) {
            CitizenAppRegistrationEntity citizenAppRegistrationEntity = optCitizenEntity.get();
            citizenInput = new CitizenAppRegistrationInputs();
            BeanUtils.copyProperties(citizenAppRegistrationEntity, citizenInput);
        }

        // convert Entity objs to Binding objs (DTOs)
        DataCollectionIncomeInputs income = new DataCollectionIncomeInputs();
        if (optIncomeEntity.isPresent()) {
            BeanUtils.copyProperties(optIncomeEntity.get(), income);
        }

        DataCollectionEducationInputs education = new DataCollectionEducationInputs();
        if (optEducationEntity.isPresent()) {
            BeanUtils.copyProperties(optEducationEntity.get(), education);
        }

        List<DataCollectionChildrenInputs> listChilds = new java.util.ArrayList<>();
        childsEntityList.forEach(childEntity -> {
            DataCollectionChildrenInputs child = new DataCollectionChildrenInputs();
            BeanUtils.copyProperties(childEntity, child);
            listChilds.add(child);
        });

        // prepare DCFeedBackReport obj
        DCFeedBackReportInputs report = new DCFeedBackReportInputs();
        report.setPlanName(planName);
        report.setIncomeDetails(income);
        report.setEducationDetails(education);
        report.setCitizenDetails(citizenInput);
        report.setChildrenDetails(listChilds);

        return report;
    }
}