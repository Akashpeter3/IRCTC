package com.irctc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irctc.dto.RoutIdDTO;
import com.irctc.dto.RouteRequestDTO;
import com.irctc.dto.RouteResponseDTO;
import com.irctc.dto.TrainDTO;
import com.irctc.model.Route;
import com.irctc.model.Train;
import com.irctc.repository.RouteRepository;
import com.irctc.repository.TrainRepository;

@Service
public class RouteService {

	@Autowired
	RouteRepository routeRepository;

	@Autowired
	TrainRepository trainrepository;

	public RoutIdDTO createRoute(RouteRequestDTO dto) {
		Route route = new Route();
		route.setSource(dto.getSource());
		route.setDestination(dto.getDestination());
		List<Train> trainList = new ArrayList<Train>();
		for (TrainDTO tdto : dto.getTrainList()) {
			Train train = new Train(tdto.getTrainName(), tdto.getArrivalTime(), tdto.getDepartureTime(),
					tdto.getTrainFare());

			train.setRoute(route);
			trainList.add(train);
		}
		route.setTrain(trainList);
		Route getsavedrouteID = routeRepository.saveAndFlush(route);
		RoutIdDTO routIdDTO = new RoutIdDTO(getsavedrouteID.getRouteId());
		return routIdDTO;

	}

	public Route getRouteById(Integer routeId) {
		Route route = routeRepository.findById(routeId).get();
		return route;
	}

	public List<Train> getTrainsBySourceAndDestination(String source, String destination) {
		List<Route> routList = routeRepository.findAll();
		List<Train> trainList = new ArrayList<Train>();
		for (Route rout : routList) {
			if (rout.getSource().contains(source) && rout.getDestination().contains(destination)) {
				rout.getTrains();
				trainList.addAll(rout.getTrains());
			}

		}
		return trainList;
	}

	public Route updateSourceAndDestinationBtId(Integer routeId, String source, String destination) {
		Route route = routeRepository.findById(routeId).get();
		if (route != null) {
			route.setSource(source);
			route.setDestination(destination);
			Route newRout = routeRepository.save(route);
			return newRout;
		} else {
			return null;
		}

	}

	public String deleteTrainByRouteIdAndTrainId(Integer routeid, Integer trainId) {
		String expected = "Succesfully deleted train!";
		Route route = routeRepository.findById(routeid).get();
		if (route != null) {
			List<Train> trains = route.getTrains();
			for (Train tempTrain : trains) {
				if (tempTrain.getTrainId() == trainId) {
					trainrepository.delete(tempTrain);
					route.setTrain(trains);
					return expected;
				}
			}
		}
		return null;
	}

	public Route updateTrainByRouteId(Integer routeId, Train train) {
		Route route = routeRepository.findById(routeId).get();
		boolean flag = false;
		if (route != null) {
			List<Train> trains = route.getTrains();
			Train trainn = new Train();
			for (Train tempTrain : trains) {
				if (tempTrain.getTrainId() == train.getTrainId()) {
					flag = true;
					trainn = tempTrain;
					break;
				}
			}
			if (flag) {
				train.setRoute(trainn.getRoute());
				trains.remove(trainn);
				trains.add(train);
				route.setTrain(trains);
				Route updatedRoute = routeRepository.save(route);
				return updatedRoute;
			}
		}

		return null;
	}

}
