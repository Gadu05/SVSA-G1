package org.example;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.io.font.constants.StandardFonts;
//import com.itextpdf.io.image.ImageData;
//import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
//import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.element.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;


import javax.swing.border.Border;

import static com.itextpdf.kernel.colors.Color.*;
import static java.awt.SystemColor.text;

public class GeneratePDF {
    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
        String path= "invoice.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);

        pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, new HeaderEventHandler());

        document.add(new Paragraph("\nConteúdo principal do documento"));
        pdfDocument.addNewPage();
        document.add(new Paragraph("\nConteúdo da segunda página"));


        document.close();

    }

    private static class HeaderEventHandler implements IEventHandler {
        @Override
        public void handleEvent(Event event) {
            // Acessando o evento como um PdfDocumentEvent
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfPage page = docEvent.getPage();
            PdfDocument pdfDoc = docEvent.getDocument();

            // Caminho para o logotipo
            try {
                String logoSaltoPath = "imagem/logoSalto.png";
                ImageData imageData = ImageDataFactory.create(logoSaltoPath);
                Image imageLogoSalto = new Image(imageData);
                imageLogoSalto.scaleToFit(80f,200f);
                PdfPage pageNumber = docEvent.getPage();
                imageLogoSalto.setFixedPosition(40, page.getPageSize().getTop() - 50);



                new Canvas(new PdfCanvas(page), page.getPageSize()).add(imageLogoSalto);
                SolidBorder border = new SolidBorder(ColorConstants.BLACK,1f/2f);


            }catch (MatchException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
