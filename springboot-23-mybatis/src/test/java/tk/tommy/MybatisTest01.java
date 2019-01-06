package tk.tommy;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Assert;
import org.junit.Test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;
import tk.tommy.model.RegionSport;

@Slf4j
public class MybatisTest01 {

	private static SqlSessionFactory getSqlSessionFactory() throws IOException {
		// 根據xml創建一個sqlSessionFactory
		String resource = "tk/tommy/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		Assert.assertNotNull(sqlSessionFactory);
		log.debug("{}", sqlSessionFactory);
		return sqlSessionFactory;
	}

	private SqlSessionFactory getSqlSessionFactory2() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.jdbc.Driver");
		config.setJdbcUrl(
			"jdbc:mysql://192.168.79.50:3306/dbBR?autoReconnect=true&useSSL=false&characterEncoding=UTF-8");
		config.setUsername("latdbdev");
		config.setPassword("l@tThe0ne");
		config.addDataSourceProperty("cachePrepStmts", true);
		config.addDataSourceProperty("prepStmtCacheSize", 500);
		config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
		config.setConnectionTestQuery("SELECT 1");
		config.setAutoCommit(true);
		// 池中最小空闲链接数量
		config.setMinimumIdle(5);
		// 池中最大链接数量
		config.setMaximumPoolSize(50);

		DataSource dataSource = new HikariDataSource(config);

		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);

		configuration.addMapper(RegionSportDao.class);

		return new SqlSessionFactoryBuilder().build(configuration);
	}

	@Test
	public void test01() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

		try (SqlSession session = sqlSessionFactory.openSession();) {

			List<RegionSport> regionSports = session.selectList("selectBySportId", 1);
			log.info("{}", ToStringBuilder.reflectionToString(regionSports, ToStringStyle.MULTI_LINE_STYLE));
		}
	}

	@Test
	public void test02() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory2();
		try (SqlSession session = sqlSessionFactory.openSession();) {
			RegionSportDao mapper = session.getMapper(RegionSportDao.class);
			List<RegionSport> regionSports = mapper.selectByRegionNum("1");
			log.info("{}", ToStringBuilder.reflectionToString(regionSports, ToStringStyle.MULTI_LINE_STYLE));
		}
	}

    /**
     * 獲取自增主鍵
     * useGeneratedKeys="true"
     * keyProperty="empId"
     *
     * <pre>
     * oracle
     * keyProperty:查出的主鍵封裝給empId
     * resultType：empId返回類型
     * <selectKey keyProperty="empId" order="BEFORE" resultType="Integer">
     *     select xx_seq.nextval from dual
     * </selectKey>
     *
     *
     * <insert id="insertAuthor">
     *     <selectKey keyProperty="id" resultType="int" order="BEFORE">
     *         select CAST(RANDOM()*1000000 as INTEGER) a from SYSIBM.SYSDUMMY1
     *     </selectKey>  insert into Author    (id, username, password, email,bio, favourite_section)
     *     values    (#{id}, #{username}, #{password}, #{email}, #{bio}, #{favouriteSection,jdbcType=VARCHAR})
     * </insert>
     * </pre>
     *
     *
     */
    @Test
    public void test03CRUD() throws IOException {
        // TODO mysql oracle
    }
}
