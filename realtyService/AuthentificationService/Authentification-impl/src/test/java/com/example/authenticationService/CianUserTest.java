package com.example.authenticationService;


import com.example.authenticationService.controller.CianUserController;
import com.example.authenticationService.exception.cianuser.CianUserAlreadyExistsException;
import com.example.authenticationService.grpc.GrpcChatServiceImpl;
import com.example.authenticationService.model.jooq.schema.tables.pojos.VerificationTokenEntity;
import com.example.authenticationService.rabbit.MyRabbitListener;
import com.example.authenticationService.repository.VerificationTokenRepository;
import com.example.authenticationService.service.CianUserService;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import dto.request.cianuser.CianUserRequest;
import dto.request.cianuser.CianUserUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.consul.ConsulContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@Testcontainers
@SpringBootTest
@ContextConfiguration(initializers = {CianUserTest.Initializer.class})
public class CianUserTest {
    @Autowired
    CianUserController cianUserController;

    private MockMvc mockMvc;

    @Autowired
    CianUserService cianUserService;


    @Rule
    public static Network network = Network.newNetwork();

    @Container
    public static PostgreSQLContainer<?> authentificationDb = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("AuthentificationService")
            .withUsername("postgres")
            .withPassword("LandRover2013");


    @Container
    public static GenericContainer redis = new GenericContainer("redis")
            .withExposedPorts(6379)
            .withNetwork(network)
            .withCommand("redis-server")
            .withNetworkAliases("redis");


    @Container
    public static GenericContainer rabbitmq = new RabbitMQContainer("rabbitmq:3.9.5-management")
            .withExposedPorts(5672, 15672)
            .withNetwork(network)
//            .withCreateContainerCmdModifier(cmd->cmd.withHostConfig(
//                    new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(5672), new ExposedPort(5672)))
//            ))
//            .withCreateContainerCmdModifier(cmd->cmd.withHostConfig(
//                    new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(15672), new ExposedPort(15672)))
//            ))
            .withNetworkAliases("rabbitmq");


    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {


            TestPropertyValues.of(
                    "spring.datasource.url=" + authentificationDb.getJdbcUrl(),
                    "spring.datasource.username=" + authentificationDb.getUsername(),
                    "spring.datasource.password=" + authentificationDb.getPassword(),
                    "spring.liquibase.enabled=true",
                    "spring.rabbitmq.host: " + rabbitmq.getContainerIpAddress(),
                    "spring.rabbitmq.port: " + rabbitmq.getMappedPort(5672),
                    "spring.rabbitmq.username: guest",
                    "spring.rabbitmq.password: guest",
                    "spring.cloud.consul.config.enabled: false",
                    "spring.cloud.consul.host: host.docker.internal",
                    "spring.cloud.consul.discovery.instanceId: authenticationService-8080-8001",
                    "spring.cloud.consul.discovery.prefer-ip-address: true",
                    "spring.cloud.consul.discovery.health-check-critical-timeout: \"1m\"",
                    "spring.redis.host: " + redis.getContainerIpAddress(),
                    "spring.redis.port: " + redis.getMappedPort(6379),
                    "spring.redis.timeout: 500",
                    "spring.redis.database: 0"
            ).applyTo(configurableApplicationContext.getEnvironment());

        }
    }


    @MockBean
    GrpcChatServiceImpl chatServiceImpl;


    @Test
    void successfulCreateNewUser() {

        CianUserRequest cianUserRequest = CianUserRequest.builder()
                .firstName("q")
                .lastName("q")
                .patronymic("q")
                .phone("+7962342933")
                .login("daniilstepkin0@gmail.com")
                .password("q")
                .build();


        CianUserResponse cianUserResponse = cianUserController.createNewCianUser(cianUserRequest);


        Mockito.verify(chatServiceImpl, Mockito.times(1))
                .createNotificationRoom(ArgumentMatchers.any(java.util.UUID.class), ArgumentMatchers.eq(cianUserRequest.getLogin()));
        assertEquals(cianUserResponse.getFirstName(), "q");
        assertEquals(cianUserResponse.getLastName(), "q");
        assertEquals(cianUserResponse.getPatronymic(), "q");
        assertEquals(cianUserResponse.getPhone(), "+7962342933");
        assertEquals(cianUserResponse.getLogin(), "daniilstepkin0@gmail.com");
    }


    @Test
    void createNewUserWithLoginThatAlreadyExists() {

        Exception exception = assertThrows(CianUserAlreadyExistsException.class, () -> {
            CianUserRequest cianUserRequest = CianUserRequest.builder()
                    .firstName("q")
                    .lastName("q")
                    .patronymic("q")
                    .phone("+7962342933")
                    .login("daniilstepkin0@gmail.com")
                    .password("q")
                    .build();

            CianUserResponse cianUserResponse = cianUserController.createNewCianUser(cianUserRequest);

            cianUserRequest = CianUserRequest.builder()
                    .firstName("q1")
                    .lastName("q1")
                    .patronymic("q1")
                    .phone("+7962342933")
                    .login("daniilstepkin0@gmail.com")
                    .password("q1")
                    .build();

            cianUserResponse=cianUserController.createNewCianUser(cianUserRequest);
        });

        String expectedMessage="already exists";
        String actualMessage=exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
