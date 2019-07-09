package com.hp.k8s.apiclient;

import com.hp.k8s.apiclient.imp.Params;

public interface RestfulClient {
         public String get(Params params); //获得单个资源对象
         public String list(Params params); //获得资源对象列表
         public String create(Params params); //创建资源对象
         public String delete(Params params) ; //删除某个资源对象
         public String update(Params params) ; //部分更新某个资源对象
         public String updateWithMediaType(Params params, String mediaType); //通过 media Type，实现Merge
         public String replace(Params params) ; //替换某个资源对象
         public String options(Params params) ;
         public String head(Params params);
         public void close();

}
