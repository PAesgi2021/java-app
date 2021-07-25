package fr.java.client.utils;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fr.java.client.entities.Todolist;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

import static com.itextpdf.text.Element.ALIGN_CENTER;

public class ExportTasksToPDF {

    private static String         FILE      = "./TasksExportPdf.pdf";
    private static Font           catFont   = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font           redFont   = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font           subFont   = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font           smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private        List<Todolist> todolistsList;
    private Document document;

    public void create(List<Todolist> todolists) {
        try {
            this.todolistsList = todolists;
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private void addMetaData(Document document) {
        document.addTitle("Tasks export");
        document.addSubject("From Ergon");
        document.addKeywords("Java, PDF");
        document.addAuthor("Ergon");
    }

    private void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Tasks Export", catFont));

        preface.add(new Paragraph(
                "This is the list of tasks you export ",
                smallBold));
        document.add(preface);
    }

    private void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("First Chapter", catFont);
        anchor.setName("First Chapter");


        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara    = new Paragraph("List : ", subFont);
        Section   subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph(""));

        // add a table
        createTable(subCatPart);

    }

    private void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Title"));
        c1.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Description"));
        c1.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Deadline"));
        c1.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        this.todolistsList.forEach(todolist -> {
            todolist.getTasks().forEach(task -> {
                table.addCell(task.getTitle());
                table.addCell(task.getDescription());
                table.addCell(LocalDate.now().toString());
            });
        });

        try {
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }


    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
