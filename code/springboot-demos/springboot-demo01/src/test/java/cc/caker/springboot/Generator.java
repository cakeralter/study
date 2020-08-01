package cc.caker.springboot;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @author cakeralter
 * @date 2020/5/11
 */
public class Generator {

    @Test
    public void test() {
        Generator.execute("springboot-demo01");
    }

    private static void execute(String moduleName) {
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("D:\\workspace\\mine\\study\\code\\springboot-demos\\springboot-demo01" + "/src/test/java");
        gc.setAuthor("cakeralter");
        // 生成后是否打开资源管理器
        gc.setOpen(true);
        // 重新生成时文件是否覆盖
        gc.setFileOverride(true);
        // 去掉Service接口的首字母I
        // 主键策略
        gc.setIdType(IdType.AUTO);
        gc.setServiceName("%sService");
        // 定义生成的实体类中日期类型
        gc.setDateType(DateType.TIME_PACK);
        // 开启Swagger2模式
        gc.setSwagger2(true);
        gc.setBaseColumnList(true);

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://116.85.4.107/springboot_demo?useSSL=false&useUnicode=true&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("caker1996");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        // 模块名
        pc.setModuleName(moduleName);
        pc.setParent("cc.caker.springboot");
        pc.setController("controller");
        pc.setEntity("repo.model");
        pc.setService("service");
        pc.setMapper("repo.mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 设置要映射的表名
        strategy.setInclude("\\w*");
        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 设置表前缀不生成
        strategy.setTablePrefix("t_");
        // 是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);

        // 数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // lombok 模型 @Accessors(chain = true) setter链式操作
        strategy.setEntityLombokModel(true);

        // 逻辑删除字段名
        //strategy.setLogicDeleteFieldName("is_deleted");
        // 去掉布尔值的is_前缀
        //strategy.setEntityBooleanColumnRemoveIsPrefix(true);

        //自动填充
        //TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        //TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        //ArrayList<TableFill> tableFills = new ArrayList<>();
        //tableFills.add(gmtCreate);
        //tableFills.add(gmtModified);
        //strategy.setTableFillList(tableFills);

        //strategy.setVersionFieldName("version");//乐观锁列
        // restful api风格控制器
        strategy.setRestControllerStyle(true);
        // url中驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 6、执行
        mpg.execute();
    }
}

