package com.example.JavaMailHandlingPractice.Service;

import com.example.JavaMailHandlingPractice.Configuration.MimeMailDetails;
import com.example.JavaMailHandlingPractice.Configuration.ReceiverProperties;
import com.example.JavaMailHandlingPractice.Configuration.SimpleEmailDetails;
import com.example.JavaMailHandlingPractice.Dto.EmailDto;
import jakarta.mail.*;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.search.*;
import org.jsoup.Jsoup;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final ReceiverProperties receiverProperties;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine, ReceiverProperties receiverProperties) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.receiverProperties = receiverProperties;
    }

    @Async("taskExecutor")
    public String sendSimpleMail(SimpleEmailDetails simpleEmailDetails) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(simpleEmailDetails.getTo());
        message.setSubject(simpleEmailDetails.getSubject());
        message.setText(simpleEmailDetails.getBody());
        javaMailSender.send(message);
        return "Mail has been sent successfully to " + simpleEmailDetails.getTo();
    }

    @Async("taskExecutor")
    public String sendMimeMailWithAttachment(MimeMailDetails mimeMailDetails) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(mimeMailDetails.getTo());
            helper.setSubject(mimeMailDetails.getSubject());

            Context context = new Context();
            context.setVariable("name", mimeMailDetails.getReceiverName());
            String htmlContent = templateEngine.process("email_template", context);
            helper.setText(htmlContent, true);

            String fileName;
            if (mimeMailDetails.getAttachment() != null && !mimeMailDetails.getAttachment().isEmpty()) {
                fileName = mimeMailDetails.getAttachment().getOriginalFilename();
                helper.addAttachment(
                        fileName,
                        mimeMailDetails.getAttachment());
            } else {
                ClassPathResource attachment = new ClassPathResource("Attachment/sample.pdf");
                fileName = attachment.getFilename();
                helper.addAttachment(fileName, attachment);
            }

            javaMailSender.send(message);
            return "Mail has been sent successfully to " + mimeMailDetails.getTo() + ", with the attachment " + fileName;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Async("taskExecutor")
    public void readMails(){
        Properties properties = initializeProperties();

        Session session = Session.getInstance(properties);
        try(Store store = session.getStore()){

            store.connect(
                    receiverProperties.getHost(),
                    receiverProperties.getUserName(),
                    receiverProperties.getPassword());

            try(Folder inbox = store.getFolder("INBOX")) {

                inbox.open(Folder.READ_ONLY);

                Message[] messages = inbox.getMessages();

                System.out.println("Total Messages" + messages.length);

                System.out.println(processMessages(messages));
            }

        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    //Practicing flags
    @Async("taskExecutor")
    public void readMailsWithFlags(String flag){

        Properties properties = initializeProperties();

        Session session = Session.getInstance(properties);

        try(Store store = session.getStore()){

            store.connect(
                    receiverProperties.getHost(),
                    receiverProperties.getUserName(),
                    receiverProperties.getPassword());

            try(Folder inbox = store.getFolder("INBOX")){

                inbox.open(Folder.READ_ONLY);

                SearchTerm searchTerm;
                switch (flag.toUpperCase().trim()){
                    case "SEEN":
                        Flags seenFlag = new Flags(Flags.Flag.SEEN);
                        searchTerm = new FlagTerm(seenFlag, true);
                        break;
                    case "ANSWERED":
                        Flags answeredFlag = new Flags(Flags.Flag.ANSWERED);
                        searchTerm = new FlagTerm(answeredFlag, true);
                        break;
                    case "DELETED":
                        Flags deletedFlag = new Flags(Flags.Flag.DELETED);
                        searchTerm = new FlagTerm(deletedFlag, true);
                        break;
                    case "FLAGGED":
                        Flags flaggedFlag = new Flags(Flags.Flag.FLAGGED);
                        searchTerm = new FlagTerm(flaggedFlag, true);
                        break;
                    case "RECENT":
                        Flags recentFlag = new Flags(Flags.Flag.RECENT);
                        searchTerm = new FlagTerm(recentFlag, true);
                        break;
                    case "DRAFT":
                        Flags draftFlag = new Flags(Flags.Flag.DRAFT);
                        searchTerm = new FlagTerm(draftFlag, true);
                        break;
                    default:
                        System.out.println("Invalid FLag");
                        return;
                }

                Message[] messages = inbox.search(searchTerm);

                System.out.println("Found " + messages.length + " messages");

                System.out.println(processMessages(messages));

            }
        } catch (NoSuchProviderException e){
            throw new RuntimeException();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    //Practicing SearchTerms
    @Async("taskExecutor")
    public void readMailWithSearchTerms(String term, String pattern){
        Properties properties = initializeProperties();
        Session session = Session.getInstance(properties);
        try(Store store = session.getStore()){
            store.connect(
                    receiverProperties.getHost(),
                    receiverProperties.getUserName(),
                    receiverProperties.getPassword());
            try(Folder inbox = store.getFolder("INBOX")){

                inbox.open(Folder.READ_ONLY);

                SearchTerm searchTerm;
                switch(term.toLowerCase().trim()){
                    case "subject":
                        searchTerm = new SubjectTerm(pattern);
                        break;
                    case "from":
                        searchTerm = new FromStringTerm(pattern);
                        break;
                    case "body":
                        searchTerm = new BodyTerm(pattern);
                        break;
                    default:
                        if(term.startsWith("recipient")){
                            if(term.endsWith("to")){
                                searchTerm = new RecipientStringTerm(Message.RecipientType.TO, pattern);
                                break;
                            } else if(term.endsWith("cc")){
                                searchTerm = new RecipientStringTerm(Message.RecipientType.CC, pattern);
                                break;
                            } else if(term.endsWith("bcc")) {
                                searchTerm = new RecipientStringTerm(Message.RecipientType.BCC, pattern);
                                break;
                            } else {
                                System.out.println("Invalid recipient type");
                                return;
                            }
                        } else if (term.equals("sent-date")){
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.DAY_OF_MONTH, -7);
                            Date sevenDaysAgo = calendar.getTime();
                            searchTerm = new SentDateTerm(ComparisonTerm.GT, sevenDaysAgo);
                            break;
                        } else {
                            System.out.println("Invalid term");
                            return;
                        }
                }

                Message[] messages = inbox.search(searchTerm);
                System.out.println("Found " + messages.length + " messages");
                System.out.println(processMessages(messages));

            }

        } catch(NoSuchProviderException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e){
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    //Practicing SearchTerms, LogicalTerms and Flags
    @Async("taskExecutor")
    public List<EmailDto> readImportantMail(){
        List<EmailDto> emailDtoList = null;
        Properties properties = initializeProperties();
        Session session = Session.getInstance(properties);
        try(Store store = session.getStore()){

            store.connect(
                    receiverProperties.getHost(),
                    receiverProperties.getUserName(),
                    receiverProperties.getPassword()
            );

            try(Folder inbox = store.getFolder("INBOX")){

                inbox.open(Folder.READ_ONLY);

                SearchTerm unreadTerm = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
                SearchTerm fromTerm1 = new FromStringTerm("rahulneeli2003@gmail.com");
                SearchTerm fromTerm2 = new FromStringTerm("aravindreddy1177@gmail.com");
                SearchTerm fromTerm = new OrTerm(fromTerm1, fromTerm2);
                SearchTerm isUrgentTerm = new SubjectTerm("Urgent");
                SearchTerm[] allTerms = {unreadTerm, fromTerm, isUrgentTerm};
                SearchTerm finalTerm = new AndTerm(allTerms);

                Message[] messages = inbox.search(finalTerm);
                System.out.println("Found " + messages.length + "messages");
                if(messages.length == 0){
                    return null;
                }

                emailDtoList = processMessages(messages);

            }

        } catch (NoSuchProviderException e){
            throw new RuntimeException(e);
        } catch (MessagingException e){
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return emailDtoList;
    }

    @Async("taskExecutor")
    public List<EmailDto> readEmail(){

        Properties properties = initializeProperties();
        List<EmailDto> emailDtoList = null;

        try(Store store = Session.getInstance(properties).getStore()){

            store.connect(
                    receiverProperties.getHost(),
                    receiverProperties.getUserName(),
                    receiverProperties.getPassword()
            );

            try(Folder inbox = store.getFolder("INBOX")){

                inbox.open(Folder.READ_ONLY);

                SearchTerm unreadTerm = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
                SearchTerm fromTerm = new FromStringTerm("captainrogers577@gmail.com");
                SearchTerm[] allTerms = {unreadTerm, fromTerm};
                SearchTerm finalSearchTerm = new AndTerm(allTerms);

                Message[] messages = inbox.search(finalSearchTerm);
                System.out.println(messages.length);
                if(messages.length == 0){
                    return null;
                }

                emailDtoList = processMessages(messages);

            }
        } catch (IOException | MessagingException e){
            throw new RuntimeException(e);
        }

        return emailDtoList;

    }

    //HELPER METHODS
    private Properties initializeProperties(){
        Properties properties = new Properties();
        properties.put("mail.store.protocol", receiverProperties.getProtocol());
        properties.put("mail.imaps.host", receiverProperties.getHost());
        properties.put("mail.imaps.port", receiverProperties.getPort());
        properties.put("mail.imaps.ssl.enable", "true");
        properties.put("mail.imaps.auth", "true");
        return properties;
    }

    private List<EmailDto> processMessages(Message[] messages) throws MessagingException, IOException {

        List<EmailDto> emailDtoList = new ArrayList<>();

        for(Message message : messages){

            EmailDto emailDto = new EmailDto();
            emailDto.setSubject(message.getSubject());
            emailDto.setFrom(message.getFrom()[0].toString());
            emailDto.setRecievedDate(message.getReceivedDate());

            StringBuilder result = new StringBuilder();
            List<String> attachmentFileNames = new ArrayList<>();

            if(message.isMimeType("text/plain")){
                result.append(message.getContent() + "\n");
            } else if (message.isMimeType("text/html")){
                result.append(Jsoup.parse(message.getContent().toString()).text());
            } else if (message.isMimeType("multipart/*")){
                EmailDto emailDto1 = (handleMultipart((Multipart) message.getContent()));
                result.append(emailDto1.getContent());
                attachmentFileNames.addAll(emailDto1.getAttachmentFileNames());
            } else if (Part.ATTACHMENT.equalsIgnoreCase(message.getDisposition()) && message.getFileName() != null){
                attachmentFileNames.add(handleAttachment((MimeBodyPart) message.getContent(), receiverProperties.getSaveDirectory(), message.getFileName()));
            }

            emailDto.setContent(result.toString());
            emailDto.setAttachmentFileNames(attachmentFileNames);
            emailDtoList.add(emailDto);

            message.setFlag(Flags.Flag.SEEN, false);

        }

        return emailDtoList;

    }

    private EmailDto handleMultipart(Multipart multipart) throws MessagingException, IOException {

        EmailDto emailDto = new EmailDto();
        StringBuilder result = new StringBuilder();
        List<String> attachmentFileNames = new ArrayList<>();

        for(int i = 0; i < multipart.getCount(); i++){

            BodyPart part = multipart.getBodyPart(i);

            if(part.isMimeType("text/plain")){
                result.append(part.getContent());
            } else if (part.isMimeType("text/html")){
                result.append(Jsoup.parse(part.getContent().toString()).text());
            } else if (part.isMimeType("multipart/*")){
                EmailDto emailDto1 = handleMultipart((Multipart) part.getContent());
                result.append(emailDto1.getContent());
                attachmentFileNames.addAll(emailDto1.getAttachmentFileNames());
            } else if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition()) && part.getFileName() != null){
                attachmentFileNames.add(handleAttachment((MimeBodyPart) part, receiverProperties.getSaveDirectory(), part.getFileName()));
            }

            emailDto.setContent(result.toString());
            emailDto.setAttachmentFileNames(attachmentFileNames);

        }

        return emailDto;
    }

    private String handleAttachment(MimeBodyPart multiBodyPart, String saveDirectory, String fileName) throws MessagingException, IOException {
        File saveDir = new File(saveDirectory);
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        File finalPath = new File(saveDir, fileName);
        multiBodyPart.saveFile(finalPath);
        return fileName;
    }

    private String handleAttach(String hello){
        System.out.println("Hello, how are you man?");
        return hello + " man!";
    }
}