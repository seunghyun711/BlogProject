package com.cos.blog.contorller.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Long> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글쓰기(board,principal.getUser());
        return new ResponseDto<Long>(HttpStatus.OK.value(), 1L);
    }



    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Long> deleteById(@PathVariable Long id) {
        boardService.글삭제하기(id);
        return new ResponseDto<Long>(HttpStatus.OK.value(), 1L);
    }

    @PutMapping("api/board/{id}")
    public ResponseDto<Long> update(@PathVariable Long id, @RequestBody Board board) {
        boardService.글수정하기(id, board);
        return new ResponseDto<Long>(HttpStatus.OK.value(), 1L);
    }

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Long> replySave(@PathVariable Long boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal) {

        boardService.댓글쓰기(principal.getUser(),boardId,reply);
        return new ResponseDto<Long>(HttpStatus.OK.value(), 1L);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Long> replyDelete(@PathVariable Long replyId) {
        boardService.댓글삭제(replyId);
        return new ResponseDto<Long>(HttpStatus.OK.value(), 1L);
    }
}
