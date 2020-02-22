package com.example.autocodetemplate;

import com.example.autocodetemplate.controller.support.CustomConnectionKeepAliveStrategy;
import com.example.autocodetemplate.controller.web.RequestHandlerInterceptor;
import com.example.autocodetemplate.ohter.practice.base.ReflectTest;
import io.lettuce.core.ReadFrom;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

// spring boot特性
// 方便地创建可独立运行的Spring应用程序
// 直接内嵌toncat jetty
// 简化了项目的构建配置
// 为Spring及第三方库提供自动配置
// 提供生产级特性
// 无需生成代码或进行XML配置
//
//spring boot核心：自动配置 2. 起步依赖 3. 命令行界面 4. Actuator
/**
 * TempgeleratorApplication.java不仅是启动引导类，还是配置类
 *
 * @SpringBootApplication          ←---开启组件扫描和自动配置
 * @SpringBootApplication将三个有用的注解组合在了一起。
 * @Configuration：标明该类使用Spring基于Java的配置。
 * @EnableScheduling 使用定时任务
 * @EnableAsync 运行异步方法 @Async所修饰的函数不要定义为static类型,不然不会生效
 * @ComponentScan：启用组件扫描@EnableAutoConfiguration：也可以称为@Abracadabra2，开启了Spring Boot自动配置的魔力，
 * 自动配置通过众多conditional条件注解类去判断是否配置bean
 * 通过启动命令行添加 --debug，查看哪些配置生效，未生效，Exclude
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching(proxyTargetClass = true) // 如果proxy-target-class 属性值被设置为true，那么基于类的代理将起作用（这时需要cglib库）。如果proxy-target-class属值被设置为false或者这个属性被省略
@EnableAsync
public class AutocodetemplateApplication implements WebMvcConfigurer {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(AutocodetemplateApplication.class, args);

        ReflectTest reflectTest = new ReflectTest();
        reflectTest.sendSms();
//        String resource="/mybatis/mybatis-config.xml";
//        InputStream inputStream=null;
//        try {
//            inputStream = Resources.getResourceAsStream(resource);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        SqlSessionFactory sqlSessionFactory=null;
//        sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession=null;
//        try {
//            sqlSession=sqlSessionFactory.openSession();
//            TargetTableDao roleMapper=sqlSession.getMapper(TargetTableDao.class);
//            Collection<TableStructure> role=roleMapper.descTableStru("");
//            System.out.println(role.toArray().toString());
//            sqlSession.commit();
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            sqlSession.rollback();
//            e.printStackTrace();
//        }finally {
//            sqlSession.close();
//        }

    }

    @Bean
    @ConfigurationProperties("redis")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean(destroyMethod = "close")
    public JedisPool jedisPool(@Value("${redis.host}") String host) {
        return new JedisPool(jedisPoolConfig(), host);
    }

    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
    }

    /**
     * 配置对jackson的配置
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
        return builder -> {
            builder.indentOutput(true);
            builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        };
    }

    /**
     * 指定拦截的路由
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestHandlerInterceptor())
                .addPathPatterns("/spring-test/**");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // return new RestTemplate();
        return builder.setConnectTimeout(Duration.ofMillis(100))
                .setReadTimeout(Duration.ofMillis(500))
                .requestFactory(this::requestFactory)
                .build();
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8090").build();
    }

    @Bean
    public Jackson2HalModule jackson2HalModule() {
        return new Jackson2HalModule();
    }

    /**
     * 设置HTTP  keep-alive属性
     * @return
     */
    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory() {
        PoolingHttpClientConnectionManager connectionManager =
                new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(20);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .disableAutomaticRetries()
                // 有 Keep-Alive 认里面的值，没有的话永久有效
                //.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                // 换成自定义的
                .setKeepAliveStrategy(new CustomConnectionKeepAliveStrategy())
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        return requestFactory;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {

    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
