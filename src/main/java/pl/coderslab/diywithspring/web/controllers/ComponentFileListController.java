package pl.coderslab.diywithspring.web.controllers;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.diywithspring.services.interfaces.ComponentFileListService;
import pl.coderslab.diywithspring.services.interfaces.ProjectService;

import java.io.File;
import java.nio.file.Files;


@RestController
public class ComponentFileListController {

    private final ProjectService projectService;
    private final ComponentFileListService componentFileListService;

    public ComponentFileListController(ProjectService projectService, ComponentFileListService componentFileListService) {
        this.projectService = projectService;
        this.componentFileListService = componentFileListService;
    }


    @GetMapping("/user/project/component/downloadList")
    public ResponseEntity<Resource> downloadFile(@RequestParam Long projectId) throws Exception {

        File componentListPDF = componentFileListService.generateHtmlToPdf(projectId);

        return  ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=componentList")
                .body(new ByteArrayResource(Files.readAllBytes(componentListPDF.toPath())));
    }
}