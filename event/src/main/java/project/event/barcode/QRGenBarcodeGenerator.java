package project.event.barcode;

import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import net.glxn.qrgen.core.scheme.Schema;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;
import project.event.manageEvent.dto.PeopleDto;
import project.event.manageEvent.entity.Event;
import project.event.manageEvent.entity.People;
import project.event.manageEvent.repository.PeopleRepository;

import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RequiredArgsConstructor
public class QRGenBarcodeGenerator {


    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {

        ByteArrayOutputStream stream = QRCode
                .from(barcodeText)
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

        return ImageIO.read(bis);
    }

}
