package pl.coderslab.diywithspring.web.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.diywithspring.models.CurrentUser;
import pl.coderslab.diywithspring.models.Tool;
import pl.coderslab.diywithspring.services.ToolService;
import pl.coderslab.diywithspring.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/user/")
public class ToolController {

    private final ToolService toolService;
    private final UserService userService;

    public ToolController(ToolService toolService, UserService userService) {
        this.toolService = toolService;
        this.userService = userService;
    }



    @GetMapping("tools/")
    public String showUserTools(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("tools", toolService.getUserToolList(currentUser.getUser().getId()));
        return "user/tool/userToolList";
    }

    @GetMapping("tool/add")
    public String showAddToolForm(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("tool", new Tool());
        return "user/tool/addTool";
    }

    @PostMapping("tool/add")
    public String processAddToolForm(Tool tool,BindingResult bindingResult, @AuthenticationPrincipal CurrentUser currentUser, @RequestParam("imageFile") MultipartFile imageFile){
        if(bindingResult.hasErrors()){
            return "user/tool/addTool";
        }
        Tool toolToSave=new Tool();
        toolToSave.setDescription(tool.getDescription());
        toolToSave.setName(tool.getName());
        toolToSave.setLink(tool.getLink());
        toolToSave.setUser(currentUser.getUser());
        toolToSave.setImage(toolService.getByteImage(imageFile));
        toolService.saveTool(toolToSave);
        return "redirect:/user/tools/";
    }

    @GetMapping("{userId}/tool/{toolId}/edit")
    public String showEditToolForm(Model model, @PathVariable Long toolId, @PathVariable String userId){
        model.addAttribute("tool", toolService.getToolByID(toolId));
        return "user/tool/edit";
    }

    @PostMapping("{userId}/tool/{toolId}/edit")
    public String processEditToolForm(Tool tool, BindingResult bindingResult,
                                      @RequestParam("imageFile") MultipartFile imageFile,
                                      @PathVariable Long toolId,
                                      @PathVariable Long userId){

        if(bindingResult.hasErrors()){
            return "user/tool/edit";
        }
        Tool toolFromDb=toolService.getToolByID(toolId);
        toolFromDb.setDescription(tool.getDescription());
        toolFromDb.setName(tool.getName());
        toolFromDb.setLink(tool.getLink());
        toolFromDb.setUser(userService.findUserById(userId));
        toolFromDb.setImage(toolService.getByteImage(imageFile));
        toolService.saveTool(toolFromDb);
        return "redirect:/user/tools/";
    }

    @GetMapping("{userId}/tool/{toolId}/showPicture")
    private void renderImageFromDB(HttpServletResponse response, @PathVariable Long toolId, @PathVariable Long userId) {
        byte[] byteArray = new byte[toolService.getToolByID(toolId).getImage().length];

        int i=0;
        for (Byte wrappedByte : toolService.getToolByID(toolId).getImage()){
            byteArray[i++]=wrappedByte;
        }

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        try {
            IOUtils.copy(is, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("{userId}/tool/{toolId}/show")
    public String showTool(Model model, @PathVariable Long toolId, @PathVariable Long userId){
        model.addAttribute("tool", toolService.getToolByID(toolId));
        return "user/tool/show";
    }


    @GetMapping("{userId}/tool/{toolId}/delete")
    public String showToolToDelete(Model model, @PathVariable Long toolId, @PathVariable Long userId){
        model.addAttribute("tool", toolService.getToolByID(toolId));
        return "user/tool/delete";
    }


    @PostMapping("tool/delete")
    public String processToolDelete(Long id) {
        toolService.deleteToolById(id);
        return "redirect:/user/tools/";
    }



}
