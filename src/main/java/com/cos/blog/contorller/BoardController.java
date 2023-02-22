package com.cos.blog.contorller;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 3, sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("boards",boardService.글목록(pageable));
        return "index"; // viewResolver작동 -> 해당 인덱스 페이지로 모델의 정보를 가지고 이동한다.
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.글상세보기(id));

        return "board/detail";
    }

    // User 권한 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }
}
