package com.briup.apps.cms.dao.extend;

import com.briup.apps.cms.bean.extend.CategoryExtend;

public interface CategoryExtendMapper {

    CategoryExtend selectById(long id);
    
    CategoryExtend findByIdWithSon(long id);
    
    int batchDelete(long[] ids);
}
