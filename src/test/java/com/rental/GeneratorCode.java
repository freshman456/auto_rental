package com.rental;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/12
 */
@SpringBootTest
public class GeneratorCode {
    private static final String AUTHOR = "YinHang";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/auto_rental";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin123";
    private static final String OUT_DIR = ".\\src\\main\\java";
    private static final String PACKAGE_NAME = "com.rental";
    private static final String MODULE_NAME = "auto_rental";
    private static final String[] TABLES = {
            "auto_maker", "auto_brand", "auto_info",
            "sys_dept", "sys_permission", "sys_role", "sys_user", "sys_role_permission", "sys_user_role",
            "busi_customer", "busi_maintain", "busi_violation", "busi_order", "busi_rental_type"
    };
    private static final String[] PREFIX = {"busi_", "sys_" };

    @Test
    void generator() {
        FastAutoGenerator.create(JDBC_URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author(AUTHOR)
                            .enableSwagger()
                            .outputDir(OUT_DIR);
                })
                .packageConfig(builder -> {
                    builder.parent(PACKAGE_NAME);
                    builder.moduleName(MODULE_NAME)
                            .pathInfo(Collections.singletonMap(OutputFile.xml, ".\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(TABLES)
                            .addInclude(PREFIX)
                            .entityBuilder()
                            .enableLombok()
                            .enableChainModel()
                            .controllerBuilder()
                            .enableRestStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
