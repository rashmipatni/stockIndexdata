package com.rp.djIndexes.service;

import com.rp.djIndexes.model.DJIndex;

import java.io.InputStream;
import java.util.List;

/**
 * FileName - DJIndexMasterService.java
 */
public interface DJIndexMasterService {

    List<DJIndex>  getIndexByStock(String stock);
    void addIndex(DJIndex djIndex);
    void store(InputStream file);

}
