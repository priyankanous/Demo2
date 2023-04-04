package com.nous.rollingrevenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.HolidayCalendarService;
import com.nous.rollingrevenue.vo.HolidayCalendarVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/holiday-calendar")
@CrossOrigin(origins = "*")
public class HolidayCalendarController {

	@Autowired
	HolidayCalendarService holidayCalendarService;

	@Operation(summary = "Save Calendar")
	@PostMapping
	public WSResponse<HolidayCalendarVO> saveCalendar(@RequestBody @Valid HolidayCalendarVO holidayCalendarVO) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, holidayCalendarService.addCalendar(holidayCalendarVO));
	}

	@Operation(summary = "Update Calendar by Id")
	@PutMapping(path = "{holidayId}")
	public WSResponse<HolidayCalendarVO> updateLocation(@PathVariable @Valid Long holidayId,
			@RequestBody @Valid HolidayCalendarVO holidayCalendarVO) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, holidayCalendarService.updateHolidayCalendar(holidayId, holidayCalendarVO));
	}

	@Operation(summary = "Get Calendar by Id")
	@GetMapping(path = "{holidayId}")
	public WSResponse<HolidayCalendarVO> getHolidayCalendar(@PathVariable @Valid Long holidayId) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, holidayCalendarService.getHolidayCalendar(holidayId));
	}

	@Operation(summary = "Delete Calendar by Id")
	@DeleteMapping(path = "{holidayId}")
	public WSResponse<String> removeCalendar(@PathVariable @Valid Long holidayId) {
		holidayCalendarService.deleteHolidayCalendar(holidayId);
		String data = "HolidayCalendar id  " + holidayId + " removed successfully";
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, data);
	}

	@Operation(summary = "Get Calendar")
	@GetMapping
	public WSResponse<List<HolidayCalendarVO>> getCalendars() {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, holidayCalendarService.getCalendars());
	}

}

