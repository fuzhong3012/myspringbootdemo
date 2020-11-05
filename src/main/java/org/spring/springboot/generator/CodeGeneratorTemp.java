package org.spring.springboot.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @Author : fuzhong
 * @CreateTime : 2020/4/14 9:52
 * @Description : 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 **/
public class CodeGeneratorTemp {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

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
        gc.setAuthor("fuzhong");				//作者
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
        //dsc.setUrl("jdbc:mysql://localhost:3306/mybatis?useUnicode=true&useSSL=false&characterEncoding=utf8");
        //dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://localhost:3306/demospringboot?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        // dsc.setSchemaName("public");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.spring.springboot");//父包名
        pc.setModuleName(scanner("模块名"))
                .setController("controller")
                .setEntity("beans")
                .setService("service")
                .setMapper("mapper")
                .setXml("mapper");
        mpg.setPackageInfo(pc);

        // 自定义配置 自定义属性注入
        InjectionConfig cfg = new InjectionConfig() {
            //自定义属性注入:abc
            //在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

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
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));		//需要包含的表名，允许正则表达式（与exclude二选一配置）
        //strategy.setInclude(new String[] { "user" }); // 需要生成的表可以多张表
        //strategy.setExclude(new String[]{"test"}); // 排除生成的表
        strategy.setControllerMappingHyphenStyle(true);	//驼峰转连字符
        strategy.setTablePrefix(pc.getModuleName() + "_");	//是否生成实体时，生成字段注解
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
