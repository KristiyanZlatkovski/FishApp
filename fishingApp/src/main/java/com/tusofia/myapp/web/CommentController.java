package com.tusofia.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tusofia.myapp.model.Comment;
import com.tusofia.myapp.service.CommentService;
import com.tusofia.myapp.service.UserService;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @Autowired
    private UserService userService;


    @PostMapping("/makeCommentTournament")
    public String makeNewComment(Model model, @RequestParam Long id, @RequestParam String body, @AuthenticationPrincipal Object principal) {
        String username = ((UserDetails) principal).getUsername();
        Comment comment = commentService.makeNewComment(id, userService.findByUsername(username), "tournament", body);
        commentService.save(comment);

        model.addAttribute("comments", commentService.findAllByParentIdAndParentType(id, "tournament"));
        return "tournamentInfo :: #commentsDiv";

    }


    @PostMapping("/makeCommentFish")
    public String makeNewCommentFish(Model model, @RequestParam Long id, @RequestParam String body, @AuthenticationPrincipal Object principal) {

        String username = ((UserDetails) principal).getUsername();
        Comment comment = commentService.makeNewComment(id, userService.findByUsername(username), "fish", body);
        commentService.save(comment);

        model.addAttribute("comments", commentService.findAllByParentIdAndParentType(id, "fish"));

        return "fishInfo :: #commentsDiv";

    }

    @PostMapping("/makeCommentWater")
    public String makeNewCommentWater(Model model, @RequestParam Long id, @RequestParam String body, @AuthenticationPrincipal Object principal) {

        String username = ((UserDetails) principal).getUsername();
        Comment comment = commentService.makeNewComment(id, userService.findByUsername(username), "water", body);
        commentService.save(comment);

        model.addAttribute("comments", commentService.findAllByParentIdAndParentType(id, "water"));

        return "waterInfo :: #commentsDiv";

    }

}
