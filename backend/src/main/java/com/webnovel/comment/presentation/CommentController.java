 package com.webnovel.comment.presentation;

 import static org.springframework.http.HttpStatus.OK;

 import org.springframework.beans.factory.annotation.Autowired;

 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.data.web.PageableDefault;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.HttpStatusCode;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.RestController;

 import com.webnovel.comment.dto.CommentCreateDto;
 import com.webnovel.comment.dto.CommentDeleteDto;
 import com.webnovel.comment.dto.CommentResponseDto;

 import com.webnovel.comment.dto.CommentsRequestDto;
 import com.webnovel.comment.dto.CommentsResponseDto;
 import com.webnovel.comment.exception.CommentNotFoundException;
 import com.webnovel.comment.exception.LengthOverException;
 import com.webnovel.comment.exception.LengthUnderException;
 import com.webnovel.comment.service.CommentService;
 import com.webnovel.global.exception.WebnovelException;

 import lombok.RequiredArgsConstructor;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;

 @RestController
 @RequiredArgsConstructor
 @RequestMapping("/comment/")
 public class CommentController {
     @Autowired
     private CommentService commentService;

     @GetMapping("create")
     public ResponseEntity<CommentResponseDto> createComment(CommentCreateDto request) {
         return ResponseEntity
                 .status(OK)
                 .body(commentService.createComment(request));
     }

     @GetMapping("delete")
     public ResponseEntity<Void> deleteComment(CommentDeleteDto request) {
         commentService.deleteComment(request);

         return ResponseEntity
                 .status(HttpStatus.OK)
                 .body(null);
     }

     @GetMapping("read")
     public ResponseEntity<Page<CommentsResponseDto>> readComments(CommentsRequestDto request, @PageableDefault(size = 10) Pageable pageable) {
         var response = commentService.getCommentsPageWithName(request, pageable);

         return ResponseEntity
                 .status(OK)
                 .body(response);
     }
 }
