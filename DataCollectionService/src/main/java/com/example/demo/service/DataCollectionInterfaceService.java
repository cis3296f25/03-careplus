package com.example.demo.service;




import java.util.List;

import com.example.demo.bindings.DCFeedBackReportInputs;
import com.example.demo.bindings.DataCollectionChildrenInputs;
import com.example.demo.bindings.DataCollectionEducationInputs;
import com.example.demo.bindings.DataCollectionIncomeInputs;
import com.example.demo.bindings.PlanSelectionOptionsInputs;


public interface DataCollectionInterfaceService {

    
    public Integer generateCaseNo(Integer appId);

    public List<String> showAllPlanNames();

   
    public Integer savePlanSelection(PlanSelectionOptionsInputs plan);

   
    public Integer saveIncomeDetails(DataCollectionIncomeInputs income);

    public Integer saveEducationDetails(DataCollectionEducationInputs education);

    public Integer saveChildrenDetails(List<DataCollectionChildrenInputs> children);

    public DCFeedBackReportInputs showDcSummary(Integer caseNo);
}
