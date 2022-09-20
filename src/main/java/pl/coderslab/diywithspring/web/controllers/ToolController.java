package pl.coderslab.diywithspring.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.diywithspring.models.CurrentUser;
import pl.coderslab.diywithspring.models.Tool;
import pl.coderslab.diywithspring.services.ToolService;
import pl.coderslab.diywithspring.services.UserService;

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
        return "user/tool/add";
    }

    @PostMapping("tool/add")
    public String processAddToolForm(Tool tool, @AuthenticationPrincipal CurrentUser currentUser){
        Tool toolToSave=new Tool();
        toolToSave.setDescription(tool.getDescription());
        toolToSave.setName(tool.getName());
        toolToSave.setLink(tool.getLink());
        toolToSave.setUser(currentUser.getUser());
        toolService.saveTool(toolToSave);
        return "redirect:/user/tools/";
    }
}
