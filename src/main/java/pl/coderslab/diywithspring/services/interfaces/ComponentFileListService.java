package pl.coderslab.diywithspring.services.interfaces;

import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public interface ComponentFileListService {

    File generateHTMLList(Long projectID);

    File generateHtmlToPdf(Long projectID) throws Exception;

    Document createWellFormedHtml(File inputHTML) throws IOException;

    void xhtmlToPdf(Document xhtml, File outputPdf) throws Exception;
}
