package com.example.task.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.task.model.DoctorsModel;
import com.example.task.repository.DoctorsRepository;
import com.example.task.response.CommonConstants;
import com.example.task.response.ConversionConstants;
import com.example.task.util.BaseResponse;


@Service
public class DoctorService implements DoctorInterface{

	@Autowired
	private DoctorsRepository doctorsAppointmentRepository; 
	
	@Value("${spring.mail.host}")
	private String mailHost;
	
	@Value("${spring.mail.port}")
	private Integer mailPort;
	
	@Value("${spring.mail.username}")
	private String mailUsername;
	
	@Value("${spring.mail.password}")
	private String mailPassword;
	
	@Override
	public BaseResponse<DoctorsModel> saveDetails(DoctorsModel doctorsAppointment) {
		BaseResponse<DoctorsModel> baseResponse=new BaseResponse<>();
		try
		{
			doctorsAppointment.setAppointmentsPatientReports(ConversionConstants.byteToString(doctorsAppointment.getAppointmentsPatientReports()));
			DoctorsModel doctorsAppointmentResponse=doctorsAppointmentRepository.save(doctorsAppointment);
			if(doctorsAppointmentResponse!=null)
			{
				baseResponse.setStatus(CommonConstants.SUCCESS);
				baseResponse.setResponseObject(doctorsAppointmentResponse);
				return baseResponse;
			}
			else
			{
				baseResponse.setStatus(CommonConstants.FAIL);
				baseResponse.setResponseObject(doctorsAppointmentResponse);
				return baseResponse;
			}
		}
		catch(Exception e)
		{
			baseResponse.setStatus(CommonConstants.SUCCESS);
			return baseResponse;
		}
	}

	@Override
	public BaseResponse<DoctorsModel> sendMail(String date, String value) throws ParseException, MessagingException {
		
		BaseResponse<DoctorsModel> baseResponse=new BaseResponse<>();
		String MailBody1=null;
		String MailBody2=null;
		List<DoctorsModel> ListdoctorsAppointment=doctorsAppointmentRepository.findAll();
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date); 
		for(DoctorsModel doctorsAppointment:ListdoctorsAppointment)
		{
			if(doctorsAppointment.getAppointmentsDateOfAppointment().equals(date1) && value.equalsIgnoreCase("YES"))
			{
				JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
				mailSender.setHost(mailHost);
				mailSender.setPort(mailPort);
				mailSender.setUsername(mailUsername);
				mailSender.setPassword(mailPassword);
				mailSender.setDefaultEncoding("UTF-8");
				
				
				Properties props = mailSender.getJavaMailProperties();
		        props.put("mail.transport.protocol", "smtp");
		       // props.put("mail.smtp.auth", "true");
		        props.put("mail.smtp.starttls.enable", "true");
		        props.put("mail.debug", "true");
		        
		        MimeMessage mimeMessage = mailSender.createMimeMessage();
		        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		        
		        MailBody1=replaceHtmlvariables(CommonConstants.HTML_MSG,"name","DOCTOR IS AVAILABLE");
		        MailBody2=replaceHtmlvariables(MailBody1,"DATE",new Date().toString());
		        helper.setText(MailBody2, true); // Use this or above line.
		        helper.setTo(doctorsAppointment.getAppointmentsEmail());
		        helper.setSubject(" Notification");
		        helper.setFrom("ap4447628@gmail.com");
		        mailSender.send(mimeMessage);
		        baseResponse.setStatus(CommonConstants.SUCCESS);
		        return baseResponse;
		        
		        
			}
			else
			{
				baseResponse.setStatus(CommonConstants.FAIL);
		        return baseResponse;
			}
		}
		baseResponse.setStatus(CommonConstants.FAIL);
        return baseResponse;
	
	}
	public static String replaceHtmlvariables(String   mailbody,String regex,String replacewith)
	{
			Pattern pattern=Pattern.compile(Pattern.quote(regex));
			Matcher matcher=pattern.matcher(mailbody);
			String output=matcher.replaceAll(replacewith);
		
		
		return output;
	}

}
