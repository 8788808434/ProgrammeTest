package com.example.task.controller;

import java.text.ParseException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task.model.DoctorsModel;
import com.example.task.response.CommonConstants;
import com.example.task.service.DoctorInterface;
import com.example.task.util.BaseResponse;

@RestController
@RequestMapping("/v1")
public class DoctorController {
	
	@Autowired
	private DoctorInterface doctorAppointmentInterface;
	
	
	
	@PatchMapping("/saveDetails")
	public ResponseEntity<BaseResponse<DoctorsModel>> saveDetails(@RequestBody DoctorsModel doctorsAppointment)
	{
		BaseResponse<DoctorsModel> baseResponse=new BaseResponse<>(); 
		if(doctorsAppointment!=null)
		{
			BaseResponse<DoctorsModel>baseDoctorsAppointmentResponse=doctorAppointmentInterface.saveDetails(doctorsAppointment);
			if(baseDoctorsAppointmentResponse.getStatus().equalsIgnoreCase(CommonConstants.SUCCESS))
			{
				baseResponse.setStatus(CommonConstants.SUCCESS);
				baseResponse.setReasonText(CommonConstants.USERS_SAVED_SUCCESS);
				baseResponse.setResponseObject(baseDoctorsAppointmentResponse.getResponseObject());
				return new ResponseEntity(baseResponse,null,HttpStatus.ACCEPTED);
			}
			else
			{
				baseResponse.setStatus(CommonConstants.FAIL);
				baseResponse.setReasonText(CommonConstants.USERS_SAVED_FAILURE);
				baseResponse.setResponseObject(baseDoctorsAppointmentResponse.getResponseObject());
				return new ResponseEntity(baseResponse,null,HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			baseResponse.setStatus(CommonConstants.FAIL);
			baseResponse.setReasonText(CommonConstants.INPUT_OBJECT_NULL);
			return new ResponseEntity(baseResponse,null,HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@GetMapping("/sendMail/{date}/{value}")
	public ResponseEntity<BaseResponse<DoctorsModel>> sendMail(@PathVariable String date,@PathVariable String value) throws ParseException, MessagingException
	{
		BaseResponse<DoctorsModel> baseResponse=new BaseResponse<>();
		if(date!=null && value!=null)
		{
			BaseResponse<DoctorsModel>doctorsAppointmentResponse=doctorAppointmentInterface.sendMail(date, value);
			if(doctorsAppointmentResponse.getStatus().equalsIgnoreCase(CommonConstants.SUCCESS))
			{
				baseResponse.setStatus(doctorsAppointmentResponse.getStatus());
				baseResponse.setReasonText(CommonConstants.VERIFY);
				return new ResponseEntity<BaseResponse<DoctorsModel>>(baseResponse,null,HttpStatus.ACCEPTED);
			}
			else
			{
				baseResponse.setStatus(doctorsAppointmentResponse.getStatus());
				baseResponse.setReasonText(CommonConstants.IS_NOT_ACTIVE);
				return new ResponseEntity<BaseResponse<DoctorsModel>>(baseResponse,null,HttpStatus.NOT_ACCEPTABLE);
			}
		}
		else
		{
			
			baseResponse.setReasonText(CommonConstants.INPUT_OBJECT_NULL);
			return new ResponseEntity<BaseResponse<DoctorsModel>>(baseResponse,null,HttpStatus.ACCEPTED);
		}
	}

}
