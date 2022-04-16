package com.rp.djIndexes.util;

import com.rp.djIndexes.model.DJIndex;
import com.rp.djIndexes.utils.DJIndexDatasetFileUtil;

import java.io.InputStream;
import java.util.List;

public class DJIndexFileUtilTest {


    public static void main(String ar[]){
        readFromDJIndexFile();
    }

    private static void readFromDJIndexFile(){
        InputStream is = ClassLoader.getSystemResourceAsStream("sample-index.data");
        List<DJIndex> djIndexList = DJIndexDatasetFileUtil.parseCsvFile(is);
        if(djIndexList!=null) {
            System.out.println("Total Stock Indexes - " + djIndexList.size());
            System.out.println("" + djIndexList.get(1).getStock());
        }
    }
}
