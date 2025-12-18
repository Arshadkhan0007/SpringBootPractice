package com.example.JavaMailHandlingPractice.Controller;

import com.example.JavaMailHandlingPractice.Configuration.MimeMailDetails;
import com.example.JavaMailHandlingPractice.Configuration.SimpleEmailDetails;
import com.example.JavaMailHandlingPractice.Response.SuccessResponse;
import com.example.JavaMailHandlingPractice.Service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mail_handling")
public class EmailController {

    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String greeting(){
        return "Welcome to mail handling application";
    }

    @PostMapping("/send_simple_mail")
    public ResponseEntity<SuccessResponse<String>> sendSimpleMail(@RequestBody SimpleEmailDetails simpleEmailDetails){
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Operation has been completed",
                service.sendSimpleMail(simpleEmailDetails)
        ), HttpStatus.OK);
    }

    @PostMapping("/send_with_attachment")
    public ResponseEntity<SuccessResponse<String>> sendMimeMailWithCustomAttachment(@ModelAttribute MimeMailDetails mimeMailDetails){
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Operation has been completed",
                service.sendMimeMailWithAttachment(mimeMailDetails)
        ), HttpStatus.OK);
    }

    @GetMapping("/read_mails")
    public ResponseEntity<SuccessResponse<String>> receiveInboxMails(){
        service.readMails();
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Operation has been completed",
                "Results are printed on console"
        ), HttpStatus.OK);
    }

    @GetMapping("/important_mail")
    public ResponseEntity<SuccessResponse> receiveImportantMail(){
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Operation has been completed",
                service.readImportantMail() == null ? "No messages Found" : service.readImportantMail()
        ), HttpStatus.OK);
    }

    @GetMapping("/handle_attachment")
    public ResponseEntity<SuccessResponse> handleAttachments(){
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "Operation has been completed",
                service.readEmail() == null ? "No messages Found" : service.readEmail()
        ), HttpStatus.OK);
    }
}