package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: cms_jd1908
 * @description: 文章控制器类
 * @author: charles
 * @create: 2019-11-12 15:00
 **/
@Api("文章相关接口")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @GetMapping("findAll")
    public Message findAll(){
    	List<Article> list = articleService.findAll();
        return MessageUtil.success(list);
    }

    @GetMapping("cascadeFindAll")
    public Message cascadeFindAll(){
    	List<ArticleExtend> list = articleService.cascadeFindAll();
        return MessageUtil.success(list);
    }
    
    @GetMapping("findById")
    public Message findById(long id){
    	ArticleExtend articleExtend = articleService.findById(id);
    	
        return MessageUtil.success(articleExtend);
    }
    
    /*
     * 这里使用了多个注解
     * swagger中的注解：
     * @ApiOperation-对接口的说明
     * @ApiParam-对参数的说明，还要再swagger页面中，这个值可以设置是否必需写
     * springMVC:
     * @RequestParam-绑定参数必需为注解中要求的
     * @NonNull-数据验证，表示传过来的不能为空，要不然直接报错
     * 
     * */
    @ApiOperation(value = "保存或更新")
    @PostMapping("saveOrUpdate")
    public Message saveOrUpdate(
    		@ApiParam(value = "编号") @RequestParam(value = "id",required = false) Long id,
    		@ApiParam(value = "标题",required = true) @RequestParam(value = "title")   @NonNull String title,
    		@ApiParam(value = "内容",required = true) @RequestParam(value = "content") @NonNull String content,
    		@ApiParam(value = "原内容",required = true) @RequestParam(value = "source" ,required = false)  String source,
    		@ApiParam(value = "栏目id",required = true) @RequestParam(value = "categoryId",required = false) @NonNull Long categoryId,
    		@ApiParam(value = "作者id",required = true) @RequestParam(value = "authorId",required = false) @NonNull Long authorId
    ) {
    	Article article = new Article();
    	article.setId(id);
    	article.setTitle(title);
    	article.setContent(content);
    	article.setSource(source);
    	article.setCategoryId(categoryId);
    	article.setAuthorId(authorId);
    	articleService.saveOrUpdate(article);
		return MessageUtil.success("更新成功");
    }
    
    @ApiOperation(value = "删除文章")
    @ApiImplicitParams(
    		@ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query")
    		)
    @GetMapping("deleById")
    public Message deleteById(long id) {
    	articleService.deleById(id);
    	return MessageUtil.success("删除成功");
    }
}
