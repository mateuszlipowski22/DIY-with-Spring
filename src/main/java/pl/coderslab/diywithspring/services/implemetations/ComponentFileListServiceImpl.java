package pl.coderslab.diywithspring.services.implemetations;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pl.coderslab.diywithspring.models.Component;
import pl.coderslab.diywithspring.services.interfaces.ComponentFileListService;
import pl.coderslab.diywithspring.services.interfaces.ProjectService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ComponentFileListServiceImpl implements ComponentFileListService {

    private final ProjectService projectService;

    public ComponentFileListServiceImpl(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public File generateHtmlToPdf(Long projectID) throws Exception {
//        File inputHTML = generateHTMLList(projectID);
        File inputHTML = generateHTMLListFast(projectID);
        Document inputHtml = createWellFormedHtml(inputHTML);
        File outputPdf = new File("src/main/webapp/resources/pdfList/componentListInstance.pdf");
        xhtmlToPdf(inputHtml, outputPdf);
        return outputPdf;
    }


    @Override
    public File generateHTMLList(Long projectID) {

        String htmlTemplateString = null;

        try {
            htmlTemplateString = Files.readString(Path.of("src/main/webapp/resources/pdfList/listTemplate.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer componentList = new StringBuffer();

        AtomicInteger counter = new AtomicInteger(1);
        List<Component> components = projectService.findProjectByID(projectID).getComponents();
        components.stream().forEach(
                component -> componentList
                        .append("<tr>\n")
                        .append("<td>").append(counter.getAndIncrement()).append("</td>\n")
                        .append("<td>").append(component.getDescription()).append("</td>\n")
                        .append("<td>").append(component.getQuantity()).append("</td>\n")
                .append("</tr>\n"));
        String htmlTemplateInstanceString = htmlTemplateString
                .replace("$Project_Component_list", componentList.toString())
                .replace("$Project_Name", projectService.findProjectByID(projectID).getTitle());
        File componentListExample = new File("src/main/webapp/resources/pdfList/componentListInstance.html");
        try {
            FileUtils.writeStringToFile(componentListExample,htmlTemplateInstanceString, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return componentListExample;
    }


    @Override
    public File generateHTMLListFast(Long projectID) {

        StringBuffer htmlTemplateHeader = null;
        StringBuffer htmlTemplateFooter = null;

        try {
            htmlTemplateHeader = new StringBuffer(Files.readString(Path.of("src/main/webapp/resources/pdfList/listTemplateHeader.html")));
            htmlTemplateFooter = new StringBuffer(Files.readString(Path.of("src/main/webapp/resources/pdfList/listTemplateFooter.html")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer componentList = new StringBuffer();

        AtomicInteger counter = new AtomicInteger(1);
        List<Component> components = projectService.findProjectByID(projectID).getComponents();
        components.stream().forEach(
                component -> componentList
                        .append("<tr>\n")
                        .append("<td>").append(counter.getAndIncrement()).append("</td>\n")
                        .append("<td>").append(component.getDescription()).append("</td>\n")
                        .append("<td>").append(component.getQuantity()).append("</td>\n")
                        .append("</tr>\n"));


        StringBuffer htmlTemplate = htmlTemplateHeader.append(componentList).append(htmlTemplateFooter);

        File componentListInstance = new File("src/main/webapp/resources/pdfList/componentListInstance.html");
        try {
            FileUtils.writeStringToFile(componentListInstance,htmlTemplate.toString(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return componentListInstance;
    }


    @Override
    public Document createWellFormedHtml(File inputHTML) throws IOException {
        Document document = Jsoup.parse(inputHTML, "UTF-8");
        document.outputSettings()
                .syntax(Document.OutputSettings.Syntax.xml);
        return document;
    }

    @Override
    public void xhtmlToPdf(Document xhtml, File outputPdf) throws Exception {
        try (OutputStream outputStream = new FileOutputStream(outputPdf)) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(xhtml.html());
            renderer.layout();
            renderer.createPDF(outputStream);
        }
    }
}
