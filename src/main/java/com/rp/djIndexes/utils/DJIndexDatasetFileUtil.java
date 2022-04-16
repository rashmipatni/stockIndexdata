package com.rp.djIndexes.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rp.djIndexes.model.DJIndex;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * FileName  - DJIndexDatasetFileUtil
 */
public class DJIndexDatasetFileUtil {
	private static final String indexFileExtension = "data";

	public static List<DJIndex> parseCsvFile(InputStream is) {

		List<DJIndex> djIndexes = new ArrayList<>();
        Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
        try {

			// create csv bean reader
			CsvToBean<DJIndex> csvToBean = new CsvToBeanBuilder(reader)
					.withType(DJIndex.class)
					.withIgnoreLeadingWhiteSpace(true)
					.build();

			// convert `CsvToBean` object to list of DJIndex
			djIndexes = csvToBean.parse();

		} catch (Exception e) {
			System.out.println(" Index file Reading Error!");
			e.printStackTrace();
		} finally {
			try {
                reader.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader Error!");
				e.printStackTrace();
			}
		}

		return djIndexes;
	}
	
	public static boolean isDJIndexFile(MultipartFile file) {
		String extension = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];

		return extension.equals(indexFileExtension);
	}

}
