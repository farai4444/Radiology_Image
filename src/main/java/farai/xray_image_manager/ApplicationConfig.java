package farai.xray_image_manager;
import farai.xray_image_manager.Patient.OtherService;
import farai.xray_image_manager.Patient.Patient;
import farai.xray_image_manager.Patient.PatientService;
import farai.xray_image_manager.image.ImageRepo;
import farai.xray_image_manager.image.ImageService;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "farai.xray_image_manager")
@EnableTransactionManagement
public class ApplicationConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/radiology");
        return dataSource;
    }
    //@Bean
  //  public PlatformTransactionManager transactionManager() {
    //    return new JpaTransactionManager(entityManagerFactory());
    //}
    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        return hibernateProp;
    }
    /*
    @Bean
    public DataSource appSource(){
        return DataSourceBuilder.create().build();
    }
    */
    @Bean
    public EntityManagerFactory entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("farai.xray_image_manager");
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public ImageService imageService () { return new ImageService();}
    @Bean
    public ImageRepo imageRepo () {return new ImageRepo();}
    @Bean
    public Patient patient () {return new Patient();}
    @Bean
    public PatientService patientRepo () {return new PatientService();}
    @Bean
    public OtherService otherService () {return new OtherService();}
}