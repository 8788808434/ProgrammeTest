package com.example.task.service;

import java.text.ParseException;

import javax.mail.MessagingException;

import com.example.task.model.DoctorsModel;
import com.example.task.util.BaseResponse;

public interface DoctorInterface {

	public BaseResponse<DoctorsModel> saveDetails(DoctorsModel doctorsAppointment);
	
	public BaseResponse<DoctorsModel> sendMail(String date,String value) throws ParseException, MessagingException;
}
