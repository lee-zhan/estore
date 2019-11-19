package com.briup.apps.cms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.service.ICommentService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("评论相关接口")
@RequestMapping("/comment")
@RestController
public class CommentController {
	
	@Autowired
	private ICommentService commentService;
	
	
	@ApiOperation("保存评论")
	@PostMapping("saveComment")
	public Message save(Comment comment) {
		
		commentService.saveComment(comment);
		
		return MessageUtil.success("保存成功");
	}
}
