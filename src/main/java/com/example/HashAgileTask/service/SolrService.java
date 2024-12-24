package com.example.HashAgileTask.service;

import com.example.HashAgileTask.repository.SolrRepository;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.solr.client.solrj.SolrServerException;
import java.io.IOException;

@Service
public class SolrService {

    @Autowired
    private SolrRepository solrRepository;

    public void createCollection(String collectionName) {
        solrRepository.createCollection(collectionName);
    }



    public SolrDocumentList searchByColumn(String collectionName, String columnName, String columnValue) throws SolrServerException, IOException {
        return solrRepository.searchByColumn(collectionName, columnName, columnValue);
    }

    public long getEmpCount(String collectionName) throws SolrServerException, IOException {
        return solrRepository.getEmpCount(collectionName);
    }

    public void delEmpById(String collectionName, String employeeId) throws SolrServerException, IOException {
        solrRepository.delEmpById(collectionName, employeeId);
    }

    public void getDepFacet(String collectionName) throws SolrServerException, IOException {
        solrRepository.getDepFacet(collectionName);
    }
}

