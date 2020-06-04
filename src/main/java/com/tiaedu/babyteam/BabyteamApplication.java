package com.tiaedu.babyteam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.tiaedu.babyteam") //mybatis在SpringBoot启动的时候自动扫描mybatis实现的接口
@EnableScheduling//自动任务调度
public class BabyteamApplication {
	public static void main(String[] args) {
		SpringApplication.run(BabyteamApplication.class, args);
	}
}
