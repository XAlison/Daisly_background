package com.icekredit.service;


import com.icekredit.pojo.CollectionResult;
import com.icekredit.pojo.CollectionTestCase;
import com.icekredit.pojo.po.TestCaseResultPoForTable;
import com.icekredit.pojo.po.statistic.CollectionResultStatisticInfo;
import com.icekredit.pojo.vo.CollectionVo;

import java.util.List;

public interface CollectionService {

    void addCollection(CollectionVo collectionVo);

    List<CollectionVo> getAllCollections(String projectId);

    List<CollectionTestCase> getCollectionTestCase(CollectionVo collectionVo);

    void runCollection(String collectionId);

    void updateCollection(CollectionVo collectionVo);

    CollectionResultStatisticInfo queryCollectionResultInfo(String collectionResultId);

    List<CollectionResult> getCollectionResult(String collectionId);

    List<TestCaseResultPoForTable> getCollectionTestCaseResult(String collectionResultId);

    void removeTestCaseId(String collectionId, String testCaseId);
}
