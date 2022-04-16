package com.rp.djIndexes.controller;

import com.rp.djIndexes.message.Response;
import com.rp.djIndexes.model.DJIndex;
import com.rp.djIndexes.service.DJIndexMasterService;
import com.rp.djIndexes.utils.DJIndexDatasetFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/dj")
public class DJIndexMasterRestController {
	@Autowired
	private DJIndexMasterService djIndexMasterService;

	/**
	 * This method search index by ticker(stock)
	 * http://localhost:8080/api/dj/indexes?stock=AA
	 * @param stock
	 * @return ResponseEntity object
	 */
	@GetMapping("/indexes")
	@ResponseBody
	public ResponseEntity<List<DJIndex>> getIndexByStock(@RequestParam String stock) {
		List<DJIndex> djIndexes = djIndexMasterService.getIndexByStock(stock);
		return ResponseEntity.ok().body(djIndexes);
	}

	/**
	 * This method is used to creat new Index entry
	 * @param index
	 * @return ResponseEntity object
	 */

	@PostMapping("/index")
	public ResponseEntity<Void>  addIndex(@RequestBody DJIndex index) {
		djIndexMasterService.addIndex(index);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * This method is used to upload dow-jones data set file
	 * http://localhost:8080/api/dj/indexes/upload
	 * @param indexFile
	 * @return Response object
	 */

	@PostMapping("/indexes/upload")
	public Response uploadStockIndexFile(@RequestParam("indexFile") MultipartFile indexFile) {
		Response response = new Response();
		// Checking the upload-file's name before processing
		if (Objects.requireNonNull(indexFile.getOriginalFilename()).isEmpty()) {
			response.setMessage("No File is selected to upload! ");
			response.setStatus("fail");
			return response;
		}
	
		// checking the upload file's extension is .data
		if(!DJIndexDatasetFileUtil.isDJIndexFile(indexFile)) {
			response.setMessage(indexFile.getOriginalFilename()+" - Error: this is not a DJ index file!");
			response.setStatus("fail");
			return response;
		}

		try {
			// save file data to database
			djIndexMasterService.store(indexFile.getInputStream());
			response.setMessage(indexFile.getOriginalFilename()+" - Upload File Successfully!");
			response.setStatus("ok");

		} catch (Exception e) {
			response.setMessage(indexFile.getOriginalFilename()+" - Upload Failed!");
			response.setStatus("fail");

		}
		return response;
	}
}