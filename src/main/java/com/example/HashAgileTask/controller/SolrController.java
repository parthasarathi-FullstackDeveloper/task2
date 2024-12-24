package com.example.HashAgileTask.controller;

import com.example.HashAgileTask.service.SolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/solr")
public class SolrController {

    @Autowired
    private SolrService solrService;

    @PostMapping("/createCollection")
    public void createCollection(@RequestParam String collectionName) {
        solrService.createCollection(collectionName);
    }


    @GetMapping("/searchByColumn")
    public SolrDocumentList searchByColumn(@RequestParam String collectionName, @RequestParam String columnName, @RequestParam String columnValue) throws SolrServerException, IOException {
        return solrService.searchByColumn(collectionName, columnName, columnValue);
    }

    @GetMapping("/getEmpCount")
    public long getEmpCount(@RequestParam String collectionName) throws SolrServerException, IOException {
        return solrService.getEmpCount(collectionName);
    }

    @DeleteMapping("/delEmpById")
    public void delEmpById(@RequestParam String collectionName, @RequestParam String employeeId) throws SolrServerException, IOException {
        solrService.delEmpById(collectionName, employeeId);
    }

    @GetMapping("/getDepFacet")
    public void getDepFacet(@RequestParam String collectionName) throws SolrServerException, IOException {
        solrService.getDepFacet(collectionName);
    }
}

