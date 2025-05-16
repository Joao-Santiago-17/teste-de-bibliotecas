package aor.paj.PdfTest;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import csvTest.Pessoa;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

public class PdfManager {


    /*Fluxo de criação de pdf

    1. criar doc
    2. chamar writter
    3. open()
    4. adicionar conteúdo
    5. close()
     */
    public static File convertPdf(){
        File pdf = new File("pessoa.pdf");

        try {
            List<Pessoa> pessoas = Arrays.asList(
                    new Pessoa("João", "25"),
                    new Pessoa("Maria", "30")
            );

            Document document= new Document(PageSize.A4); // podemos usar tamanhos standart da biblioteca ou criar o nosso próprio formato new Rectangle(width, height)
            PdfWriter.getInstance(document, new FileOutputStream(pdf));

            document.open();

            // Adicionar título
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Paragraph title = new Paragraph("Lista de Pessoas", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Criar tabela
            PdfPTable table = new PdfPTable(2); // 2 colunas
            table.setWidthPercentage(100);

            // Adicionar cabeçalho da tabela
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            PdfPCell headerCell1 = new PdfPCell(new Phrase("Nome", headerFont));
            PdfPCell headerCell2 = new PdfPCell(new Phrase("Idade", headerFont));

            headerCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell2.setHorizontalAlignment(Element.ALIGN_CENTER);

            headerCell1.setPadding(8);
            headerCell2.setPadding(8);

            table.addCell(headerCell1);
            table.addCell(headerCell2);

            // Adicionar dados à tabela
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            for (Pessoa pessoa : pessoas) {
                PdfPCell cell1 = new PdfPCell(new Phrase(pessoa.getNome(), dataFont));
                PdfPCell cell2 = new PdfPCell(new Phrase(pessoa.getIdade(), dataFont));


                cell1.setPadding(6);
                cell2.setPadding(6);

                table.addCell(cell1);
                table.addCell(cell2);
            }

            document.add(table);

            // Fechar o documento
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pdf;
    }
}

/*
ELEMENTOS IMPORTANTES DA BIBLIOTECA OPENPDF

Parágrafos e texto
Paragraph paragraph = new Paragraph("Este é o nosso grupo cheio de luz e alegria");
paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
paragraph.setIndentationLeft(50);
paragraph.setSpacingAfter(15);
document.add(paragraph);

Fontes e Formatação
// Criar diferentes fontes
Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
Font italicFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12, BaseColor.BLUE);

// Usar as fontes
document.add(new Paragraph("Texto normal", normalFont));
document.add(new Paragraph("Texto em negrito", boldFont));
document.add(new Paragraph("Texto em itálico e azul", italicFont));

Tabelas
// Criar uma tabela com 3 colunas
PdfPTable table = new PdfPTable(3);
table.setWidthPercentage(100); // Ocupar 100% da largura
table.setSpacingBefore(10f);   // Espaço antes da tabela
table.setSpacingAfter(10f);    // Espaço após a tabela

// Definir larguras relativas das colunas
float[] columnWidths = {2f, 1f, 1f}; // Proporção 2:1:1
table.setWidths(columnWidths);

// Adicionar cabeçalhos
PdfPCell header1 = new PdfPCell(new Paragraph("Nome"));
PdfPCell header2 = new PdfPCell(new Paragraph("Idade"));
PdfPCell header3 = new PdfPCell(new Paragraph("Cidade"));

// Formatar cabeçalhos
header1.setBackgroundColor(BaseColor.LIGHT_GRAY);
header1.setBorderWidth(2);
header1.setHorizontalAlignment(Element.ALIGN_CENTER);

// Adicionar cabeçalhos à tabela
table.addCell(header1);
table.addCell(header2);
table.addCell(header3);

// Adicionar dados
table.addCell("João");
table.addCell("25");
table.addCell("São Paulo");

// Adicionar tabela ao documento
document.add(table);


Imagens
// Adicionar uma imagem
Image img = Image.getInstance("caminho/para/imagem.jpg");
img.scaleToFit(300, 300); // Redimensionar
img.setAlignment(Element.ALIGN_CENTER); // Alinhar ao centro
document.add(img);


// Lista com marcadores
List list = new List(List.UNORDERED);
list.add(new ListItem("Item 1"));
list.add(new ListItem("Item 2"));
list.add(new ListItem("Item 3"));
document.add(list);

// Lista numerada
List orderedList = new List(List.ORDERED);
orderedList.add(new ListItem("Primeiro passo"));
orderedList.add(new ListItem("Segundo passo"));
orderedList.add(new ListItem("Terceiro passo"));
document.add(orderedList);
 */


/*
ELEMENTOS AVANÇADOS
class HeaderFooter extends PdfPageEventHelper {
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();

        // Adicionar número de página no rodapé
        String text = "Página " + writer.getPageNumber();
        float textSize = 8;
        float textBase = document.bottom() - 20;

        cb.beginText();
        cb.setFontAndSize(BaseFont.createFont(), textSize);
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, text,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                textBase, 0);
        cb.endText();
    }
}

// Usar o evento de página
PdfWriter writer = PdfWriter.getInstance(document, outputStream);
HeaderFooter event = new HeaderFooter();
writer.setPageEvent(event);

 */

