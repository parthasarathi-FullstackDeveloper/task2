package com.example.HashAgileTask.repository;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient.Builder;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import java.io.FileReader;

import java.io.IOException;
import java.util.List;

@Repository
public class SolrRepository {

    private static final String SOLR_URL = "http://localhost:8989/solr/";

    private HttpSolrClient getSolrClient(String collectionName) {
        return new Builder().withBaseSolrUrl(SOLR_URL + collectionName).build();
    }

    public void createCollection(String collectionName) {
        System.out.println("Collection " + collectionName + " should be created manually in Solr Admin.");
    }


    public SolrDocumentList searchByColumn(String collectionName, String columnName, String columnValue) throws SolrServerException, IOException {
        HttpSolrClient solr = getSolrClient(collectionName);
        SolrQuery query = new SolrQuery(columnName + ":" + columnValue);
        QueryResponse response = solr.query(query);
        return response.getResults();
    }

    public long getEmpCount(String collectionName) throws SolrServerException, IOException {
        HttpSolrClient solr = getSolrClient(collectionName);
        SolrQuery query = new SolrQuery("*:*");
        QueryResponse response = solr.query(query);
        return response.getResults().getNumFound();
    }

    public void delEmpById(String collectionName, String employeeId) throws SolrServerException, IOException {
        HttpSolrClient solr = getSolrClient(collectionName);
        solr.deleteByQuery("EmployeeID:" + employeeId);
        solr.commit();
    }

    public void getDepFacet(String collectionName) throws SolrServerException, IOException {
        HttpSolrClient solr = getSolrClient(collectionName);
        SolrQuery query = new SolrQuery("*:*");
        query.setFacet(true);
        query.addFacetField("Department");
        QueryResponse response = solr.query(query);
        response.getFacetField("Department").getValues().forEach(facet -> {
            System.out.println(facet.getName() + ": " + facet.getCount());
        });
    }
}
