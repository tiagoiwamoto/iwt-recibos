package br.com.tiagoiwamoto.iwtrecibos.core.usecase.recibo;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 21/03/2022 | 06:36
 */

import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.ReciboDto;
import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@AllArgsConstructor
@Slf4j
public class EnviarReciboPorEmailUsecase {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    public void enviaReciboPorEmail(ReciboDto reciboDto) throws IOException, DocumentException, MessagingException {
        Context context = new Context();
        context.setVariable("recibo", reciboDto);

        var templatePdf = templateEngine.process("modelo_moderno", context);
        var templateHtml = templateEngine.process("modelo_corpo_email", context);

        Path tempFile = Files.createTempFile("iwt_", "pdf_");

        // write a line
        Files.write(tempFile, templatePdf.getBytes(StandardCharsets.UTF_8));
        OutputStream outputStream = new FileOutputStream(tempFile.toFile().getAbsolutePath());
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(templatePdf);
        renderer.layout();
        renderer.createPDF(outputStream);

        MimeMessage mimeMessage = this.emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom("tiago.iwamoto@outlook.com");
        messageHelper.setTo(reciboDto.getPagador().getEmail());
        messageHelper.setCc(reciboDto.getAssinante().getEmail());
        messageHelper.setSubject("Recibo referente a ".concat(reciboDto.getReferente()));
        messageHelper.setText(templateHtml, true);
        FileSystemResource file = new FileSystemResource(tempFile.toFile());
        messageHelper.addAttachment("recibo_".concat(reciboDto.getReferente().concat(".pdf")), file);
        emailSender.send(mimeMessage);
        outputStream.close();
    }

}
