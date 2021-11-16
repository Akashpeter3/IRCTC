package com.irctc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irctc.dto.TrainIdDTO;
import com.irctc.model.Train;
import com.irctc.repository.TrainRepository;

@Service
public class TrainService {
	@Autowired
	private TrainRepository trainrepository;

	public TrainIdDTO createTrain(Train train) {
		final Train savedTrain = trainrepository.save(train);
		TrainIdDTO trainResponseDTO = new TrainIdDTO(savedTrain.getTrainId());
		return trainResponseDTO;

	}

	public String updateFareByTrainId(Integer trainId, Double trainFare) {
		Train train = trainrepository.findById(trainId).get();
		String expected = null;
		if (train != null) {
			train.setFare(trainFare);
			;
			final Train updatedtrain = trainrepository.save(train);
			expected = "Sucessfully updated";
			return expected;
		} else {
			return null;
		}
	}

}
