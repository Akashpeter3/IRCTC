package com.irctc.controller;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irctc.IRCTCApplication;
import com.irctc.dto.RoutIdDTO;
import com.irctc.dto.RouteRequestDTO;
import com.irctc.model.Route;
import com.irctc.model.Train;
import com.irctc.service.RouteService;

@RestController
@RequestMapping(value = "/irctc")
public class RouteController {

	private static final Logger logger = LoggerFactory.getLogger(RouteController.class);
	@Autowired
	private RouteService routeService;

	// Requirement 1
	@PostMapping(path = "/routes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<RoutIdDTO> addRute(@RequestBody RouteRequestDTO dto) {
		logger.info("Getting routeId from route table.... ");
		RoutIdDTO ridto = routeService.createRoute(dto);
		logger.info("routeId is -> " + ridto.getRouteId().toString());
		String a="++";

		return new ResponseEntity<RoutIdDTO>(ridto, HttpStatus.OK);

	}

	// Requirement 2
	@GetMapping(value = "/routes/{routeId}")
	ResponseEntity<?> getRouteById(@PathVariable("routeId") Integer routeId) {
		logger.info("Getting Route by id ->" + routeId);
		Route rout = routeService.getRouteById(routeId);
		logger.info("source is ->" + rout.getSource() + "destination is ->" + rout.getDestination());
		return new ResponseEntity<Route>(rout, HttpStatus.OK);

	}

	// Requirement 3
	@GetMapping(value = "/routes/trains")
	ResponseEntity<?> getTrainsBySourceAndDestination(@RequestParam(value = "source", required = true) String source,
			@RequestParam(value = "destination", required = true) String destination) {
		logger.info("fetching trains for the source" + source + "and destination" + destination + "..");
		List<Train> rout = routeService.getTrainsBySourceAndDestination(source, destination);
		logger.info("Availabe trains are " + rout);
		return new ResponseEntity<List<Train>>(rout, HttpStatus.OK);
	}

	// Requirement 4
	@PutMapping(value = "/routes/{routeId}")
	ResponseEntity<?> updateSourceAndDestinationById(@PathVariable(value = "routeId") Integer routeId,
			@RequestParam(value = "source", required = true) String source,
			@RequestParam(value = "destination", required = true) String destination) {
		logger.info("updating source and destination info with routeId" + routeId + ", source" + source + ",destination"
				+ destination);
		Route rout = routeService.updateSourceAndDestinationBtId(routeId, source, destination);
		logger.info("updated source and destination with routeId " + routeId);
		return new ResponseEntity<Route>(rout, HttpStatus.OK);
	}

	// Requirement 5
	@DeleteMapping(value = "/routes/{routeId}/{trainId}")
	ResponseEntity<String> deleteTrainByRouteIdAndTrainId(@PathVariable("routeId") Integer routeid,
			@PathVariable("trainId") Integer trainId) {
		logger.info("Deleting train with routId" + routeid + "and trainId" + trainId);
		String s = routeService.deleteTrainByRouteIdAndTrainId(routeid, trainId);
		logger.info("Deleted train with routeId" + routeid + "and trainId" + trainId);
		return new ResponseEntity<String>(s, HttpStatus.OK);
	}

	// Requirement 6
	@PutMapping(value = "/routes/{routeId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Route> updateTrainByRouteId(@PathVariable(value = "routeId") Integer routeId,
			@RequestBody Train train) {
		logger.info("updating train with routeId" + routeId);
		Route rout = routeService.updateTrainByRouteId(routeId, train);
		logger.info("updated train with routeId" + routeId);
		return new ResponseEntity<Route>(rout, HttpStatus.OK);

	}

}
