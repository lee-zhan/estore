package com.briup.apps.cms.service.Impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.bean.extend.CommentExtend;
import com.briup.apps.cms.dao.CommentMapper;
import com.briup.apps.cms.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {
	
	@Resource
	CommentMapper commentMapper;
	
	
	@Override
	public void saveComment(Comment comment) {
		comment.setCommentTime(new Date().getTime());
		comment.setStatus(CommentExtend.STATUS_UNCHECK);
		commentMapper.insert(comment);
	}

}
