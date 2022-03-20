package br.com.tiagoiwamoto.iwtrecibos.entrypoint;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 02/03/2022 | 07:39
 */

import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.ReciboDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping(path = "/email")
@RequiredArgsConstructor
@Slf4j
public class EmailResource {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @PostMapping
    public String enviarEmail(@RequestBody ReciboDto reciboDto) throws MessagingException, FileNotFoundException {
        try{
            Context context = new Context();
            context.setVariable("recibo", reciboDto);

            var result = templateEngine.process("modelo_moderno", context);

            Path tempFile = Files.createTempFile(null, null);

            // write a line
            Files.write(tempFile, result.getBytes(StandardCharsets.UTF_8));
            OutputStream outputStream = new FileOutputStream(tempFile.toFile().getAbsolutePath());
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(result);
            renderer.layout();
            renderer.createPDF(outputStream);

            MimeMessage mimeMessage = this.emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom("tiago.iwamoto@outlook.com");
            messageHelper.setTo("tiago.iwamoto@gmail.com");
            messageHelper.setSubject("Recibo referente a ".concat(""));
            messageHelper.setText(result, true);
            FileSystemResource file = new FileSystemResource(tempFile.toFile());
            messageHelper.addAttachment("document.pdf", file);
            emailSender.send(mimeMessage);
            outputStream.close();
            return "Email enviado";
        }catch (Exception e){
            e.printStackTrace();
            return "email não enviado";
        }
    }

}
