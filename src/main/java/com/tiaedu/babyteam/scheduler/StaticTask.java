package com.tiaedu.babyteam.scheduler;

import com.tiaedu.babyteam.entity.Goods;
import com.tiaedu.babyteam.service.GoodsService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StaticTask {
    @Resource
    private GoodsService goodsService;
    @Resource
    private Configuration freemarkerConfig;
    //public void  xxx(){}
    //@Scheduled(cron = "* * * * * ?"): 每秒钟执行一次
    //@Scheduled(cron = "0 * * * * ?"): 每分钟(第0秒)执行一次
    //@Scheduled(cron = "0,15,30,45 0 2 1 * ?"): 每月1日2:00的0，15，30，45秒各执行一次
    //秒 分 小时 日 月 星期
    //* 代表所有时间
    //以下：表示每5分钟执行一次
    @Scheduled(cron = "0 0/5 * * * ?")
    public void doStatic() throws IOException, TemplateException {
        //获取模板对象
        System.out.println("testtest");
        Template template = freemarkerConfig.getTemplate("goods.ftlh");
        List<Goods> allGoods = goodsService.findLast5M();
        for (Goods g : allGoods) {
            Long gid = g.getGoodsId();
            Map param = new HashMap();
            param.put("goods", goodsService.getGoods(gid));
            param.put("covers", goodsService.findCovers(gid));
            param.put("details", goodsService.findDetails(gid));
            param.put("params", goodsService.findParams(gid));
            File targetFile = new File("e:/babyteam/goods/" + gid + ".html");
            FileWriter out = new FileWriter(targetFile);
            template.process(param , out);
            System.out.println(new Date()+ ":" +targetFile +"已生成！");
            out.close();
        }
    }
}
