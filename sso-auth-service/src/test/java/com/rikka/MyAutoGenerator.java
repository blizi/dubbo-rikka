package com.rikka;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class MyAutoGenerator {
    public static void main(String[] args) {

        //1. 创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //2. 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); // 获取当前项目的路径
        gc.setOutputDir(projectPath + "/sso-auth-service/src/main/java");
        gc.setAuthor("rikka");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成文件的时候是否会覆盖
        gc.setIdType(IdType.ASSIGN_ID); //主键策略
        gc.setServiceName("%sService"); //所有自动生成的Service接口首字母I去除
        gc.setDateType(DateType.ONLY_DATE);//设置日期类型
        gc.setSwagger2(true);  // 实体属性 Swagger2 注解

        mpg.setGlobalConfig(gc);

        //3. 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.75.128:3306/appqx?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        //4. 生成的包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        pc.setParent("com.rikka.sso");
        pc.setEntity("pojo");
        pc.setController("controller");
        pc.setMapper("mapper");
        pc.setService("service");

        mpg.setPackageInfo(pc);

        // 5. 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //strategy.setInclude("ucenter"+"_\\w*"); // 映射的表明
        strategy.setInclude("user_role"); // 映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据映射到实体类的策略
        //strategy.setTablePrefix("ucenter");//不生成表的前缀

        strategy.setEntityLombokModel(true);// 自动添加lombok的注解

        // 逻辑删除
        strategy.setLogicDeleteFieldName("deleted"); // 逻辑删除字段名
        strategy.setEntityBooleanColumnRemoveIsPrefix(true); //去除布尔值的 is_ 前缀

        // 自动填充
        TableFill gmtCreate = new TableFill("create_time", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);

        //乐观锁的列
        //strategy.setVersionFieldName("version");

        // RestFUL
        strategy.setRestControllerStyle(true);
        // url驼峰命名 转化为 _
        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);

        //6.执行
        mpg.execute();

    }
}
