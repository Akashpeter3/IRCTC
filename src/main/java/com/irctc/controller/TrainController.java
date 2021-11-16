package com.irctc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irctc.dto.TrainIdDTO;
import com.irctc.model.Train;
import com.irctc.service.TrainService;

@RestController
public class TrainController {
	@Autowired
	private TrainService trainservice;
	private static final Logger logger = LoggerFactory.getLogger(RouteController.class);

	// Requirement 7
	@PostMapping(path = "/trains", consumes = "application/json", produces = "application/json")
	public ResponseEntity<TrainIdDTO> createTrain(@RequestBody Train train) {
		logger.info("creating train..");
		TrainIdDTO trainResponseDTO = trainservice.createTrain(train);
		logger.info("Train created..");
		return new ResponseEntity<TrainIdDTO>(trainResponseDTO, HttpStatus.CREATED);
	}

	// Requirement 8
	@PutMapping(value = "/trains/{trainId}")
	public ResponseEntity<String> updateFareByTrainId(@PathVariable(value = "trainId") Integer trainId,
			@RequestParam(value = "trainFare", required = true) Double trainFare) {
		logger.info("updating fare with trainId" + trainId);
		String newTrain = trainservice.updateFareByTrainId(trainId, trainFare);
		logger.info("updated fare with trainId" + trainId);
		return new ResponseEntity<String>(newTrain, HttpStatus.OK);
	}
}
