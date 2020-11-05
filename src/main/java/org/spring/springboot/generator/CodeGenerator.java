package org.spring.springboot.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @Author : fuzhong
 * @CreateTime : 2020/4/14 10:12
 * @Description : mybatis-plus 代码生成器文件
 * 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 **/
public class CodeGenerator {
    // ================= 必须修改的配置 start =================
    // 数据源配置
    public static String jdbcUrl = "jdbc:mysql://localhost:3306/demospringboot?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    public static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    public static String jdbcUsername = "root";
    public static String jdbcPassword = "root";
    // 父级包名配置
    public static String parentPackage = "org.spring.springboot";
    // 生成代码的 @author 值
    public static String author = "fuzhong";
    // 要生成代码的表名配置
    public static String[] tables = {
            "mini_area",
            //"mini_in_out_record",
    };
    // ================= 必须修改的配置 end =================

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");  //生成路径
        //gc.setOutputDir("C://Users/Administrator/Desktop/备份/ssm/src/main/java");	//生成文件的输出目录
        gc.setAuthor(author);				    //作者
        gc.setFileOverride(true);				//是否覆盖已有文件 默认值：false
        gc.setOpen(false);						//是否打开输出目录 默认值:true
        //gc.setSwagger2(true);					//开启 swagger2 模式 默认false
        gc.setBaseColumnList(true);				//开启 baseColumnList 默认false
        gc.setBaseResultMap(true);				//开启 BaseResultMap 默认false
        gc.setEntityName("%sEntity");			//实体命名方式  默认值：null 例如：%sEntity 生成 UserEntity
        gc.setMapperName("%sMapper");			//mapper 命名方式 默认值：null 例如：%sDao 生成 UserDao
        gc.setXmlName("%sMapper");				//Mapper xml 命名方式   默认值：null 例如：%sDao 生成 UserDao.xml
        gc.setServiceName("%sService");			//service 命名方式   默认值：null 例如：%sBusiness 生成 UserBusiness
        gc.setServiceImplName("%sServiceImpl");	//service impl 命名方式  默认值：null 例如：%sBusinessImpl 生成 UserBusinessImpl
        gc.setControllerName("%sController");	//controller 命名方式    默认值：null 例如：%sAction 生成 UserAction
        gc.setIdType(IdType.AUTO);              //主键策略  数据库自增
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);		//数据库类型	该类内置了常用的数据库类型【必须】
        dsc.setUrl(jdbcUrl);
        dsc.setDriverName(jdbcDriver);
        // dsc.setSchemaName("public");
        dsc.setUsername(jdbcUsername);
        dsc.setPassword(jdbcPassword);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parentPackage);//父包名
        mpg.setPackageInfo(pc);

        // 策略配置	数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);	//表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        //strategy.setCapitalMode(true);			// 全局大写命名 ORACLE 注意
        //strategy.setTablePrefix("prefix");		//表前缀
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");	//自定义继承的Entity类全称，带包名
        //strategy.setSuperEntityColumns(new String[] { "test_id", "age" }); 	//自定义实体，公共字段
        //strategy.setEntityLombokModel(true);	//【实体】是否为lombok模型（默认 false
        strategy.setRestControllerStyle(true);	//生成 @RestController 控制器
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");	//自定义继承的Controller类全称，带包名
        //strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));		//需要包含的表名，允许正则表达式（与exclude二选一配置）
        strategy.setInclude(tables);//需要包含的表名
        //strategy.setInclude(new String[] { "user" }); // 需要生成的表可以多张表
        //strategy.setExclude(new String[]{"test"}); // 排除生成的表
        strategy.setControllerMappingHyphenStyle(true);	//驼峰转连字符
        strategy.setTablePrefix(pc.getModuleName() + "_");	//是否生成实体时，生成字段注解
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }








}