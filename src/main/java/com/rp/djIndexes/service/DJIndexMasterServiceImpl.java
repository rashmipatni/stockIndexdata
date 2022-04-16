package com.rp.djIndexes.service;

import com.rp.djIndexes.model.DJIndex;
import com.rp.djIndexes.repository.DJIndexRepository;
import com.rp.djIndexes.utils.DJIndexDatasetFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * FileName - DJIndexMasterServiceImpl.java
 */
@Service
public class DJIndexMasterServiceImpl  implements DJIndexMasterService{

    @Autowired
    private DJIndexRepository djIndexRepository;

    /**
     *
     * @param stock
     * @return
     */
    public List<DJIndex>  getIndexByStock(String stock) {
        List<DJIndex> djIndexes  = djIndexRepository.findByStock(stock);
        return djIndexes;
    }

    /**
     *
     * @param djIndex
     * @return
     */
    public synchronized void addIndex (DJIndex djIndex){
        djIndexRepository.save(djIndex);
    }

    /**
     *
     * @param file
     */
    // Store DJ Index File data to db
    public void store(InputStream file) {
        try {
            // Using ApacheCommons Csv Utils to parse CSV file
            List<DJIndex> djIndexList = DJIndexDatasetFileUtil.parseCsvFile(file);

            // Save customers to database
            djIndexRepository.saveAll(djIndexList);
        } catch(Exception e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }
}
