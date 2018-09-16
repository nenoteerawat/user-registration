package com.teerawat.registration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.teerawat.registration.db.domains.Role;
import com.teerawat.registration.db.domains.User;
import com.teerawat.registration.db.repositories.RoleRepository;
import com.teerawat.registration.db.repositories.UserRepository;
import com.teerawat.registration.models.NormalUserModel;
import com.teerawat.registration.models.NormalViewModel;
import com.teerawat.registration.services.RegisterService;
import com.teerawat.registration.services.RetrieveService;
import com.teerawat.registration.services.components.RegisterHelper;
import com.teerawat.registration.services.components.RegisterHelperImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRegistrationApplicationTests {

	@Test
	public void contextLoads() throws Exception {
	}

	private NormalUserModel normalUserTest = NormalUserModel.builder()
											.firstName("Teerawat")
											.lastName("Yamsai")
											.address("1234")
											.phone("0995970594")
											.username("nenoteerawat1")
											.password("password1")
											.salary("14000")
											.build();
	@Test
	public void registerTest_whenUserIsExitingInDatabase() {
		normalUserTest.setSalary("40000");
		Role role = Role.builder()
					.id(1)
					.roleName("STANDARD_USER")
					.description("Standard User - Has no admin rights").build();
		User user = User.builder()
					.firstName(normalUserTest.getFirstName())
					.lastName(normalUserTest.getLastName())
					.address(normalUserTest.getAddress())
					.phone("0995970594")
					.username(normalUserTest.getUsername())
					.password(normalUserTest.getPassword())
					.salary(15000)
					.referenceCode("201809160594")
					.classification("Silver")
					.roles(Arrays.asList(role)).build();
		RoleRepository mockRoleRepository = mock(RoleRepository.class);
		UserRepository mockUserRepository = mock(UserRepository.class);
		RegisterHelper registerHelper = new RegisterHelperImpl();
		PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
		RegisterService registerService = new RegisterService(mockUserRepository, mockRoleRepository, 
												registerHelper, passwordEncoder);
		when(mockRoleRepository.findByRoleName(anyString())).thenReturn(role);
		when(mockUserRepository.findByUsername(anyString())).thenReturn(user);
		User resultTestUser = registerService.register(normalUserTest);
		assertNull(resultTestUser);
	}
	
	@Test
	public void registerTest_whenUserSalrayLowerThan15K() {
		normalUserTest.setSalary("14000");
		Role role = Role.builder()
				.id(1)
				.roleName("STANDARD_USER")
				.description("Standard User - Has no admin rights").build();
		User user = null;
		RoleRepository mockRoleRepository = mock(RoleRepository.class);
		UserRepository mockUserRepository = mock(UserRepository.class);
		RegisterHelper registerHelper = new RegisterHelperImpl();
		PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
		RegisterService registerService = new RegisterService(mockUserRepository, mockRoleRepository, 
												registerHelper, passwordEncoder);
		when(mockUserRepository.findByUsername(anyString())).thenReturn(user);
		when(mockRoleRepository.findByRoleName(anyString())).thenReturn(role);
		User resultTestUser = registerService.register(normalUserTest);
		assertThat(resultTestUser.getClassification()).isEqualTo("UnClassify");
	}
	
	@Test
	public void registerTest_whenUserSalray15KTo30K() {
		normalUserTest.setSalary("20000");
		Role role = Role.builder()
				.id(1)
				.roleName("STANDARD_USER")
				.description("Standard User - Has no admin rights").build();
		User user = null;
		RoleRepository mockRoleRepository = mock(RoleRepository.class);
		UserRepository mockUserRepository = mock(UserRepository.class);
		RegisterHelper registerHelper = new RegisterHelperImpl();
		PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
		RegisterService registerService = new RegisterService(mockUserRepository, mockRoleRepository, 
												registerHelper, passwordEncoder);
		when(mockUserRepository.findByUsername(anyString())).thenReturn(user);
		when(mockRoleRepository.findByRoleName(anyString())).thenReturn(role);
		when(mockUserRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);
		User resultTestUser = registerService.register(normalUserTest);
		assertThat(resultTestUser.getClassification()).isEqualTo("Silver");
	}
	
	@Test
	public void registerTest_whenUserSalray30KTo50K() {
		normalUserTest.setSalary("40000");
		Role role = Role.builder()
				.id(1)
				.roleName("STANDARD_USER")
				.description("Standard User - Has no admin rights").build();
		User user = null;
		RoleRepository mockRoleRepository = mock(RoleRepository.class);
		UserRepository mockUserRepository = mock(UserRepository.class);
		RegisterHelper registerHelper = new RegisterHelperImpl();
		PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
		RegisterService registerService = new RegisterService(mockUserRepository, mockRoleRepository, 
												registerHelper, passwordEncoder);
		when(mockUserRepository.findByUsername(anyString())).thenReturn(user);
		when(mockRoleRepository.findByRoleName(anyString())).thenReturn(role);
		when(mockUserRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);
		User resultTestUser = registerService.register(normalUserTest);
		assertThat(resultTestUser.getClassification()).isEqualTo("Gold");
	}
	
	@Test
	public void registerTest_whenUserSalrayMoreThan50K() {
		normalUserTest.setSalary("60000");
		Role role = Role.builder()
				.id(1)
				.roleName("STANDARD_USER")
				.description("Standard User - Has no admin rights").build();
		User user = null;
		RoleRepository mockRoleRepository = mock(RoleRepository.class);
		UserRepository mockUserRepository = mock(UserRepository.class);
		RegisterHelper registerHelper = new RegisterHelperImpl();
		PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
		RegisterService registerService = new RegisterService(mockUserRepository, mockRoleRepository, 
												registerHelper, passwordEncoder);
		when(mockUserRepository.findByUsername(anyString())).thenReturn(user);
		when(mockRoleRepository.findByRoleName(anyString())).thenReturn(role);
		when(mockUserRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);
		User resultTestUser = registerService.register(normalUserTest);
		assertThat(resultTestUser.getClassification()).isEqualTo("Platinum");
	}
	
	@Test
	public void retrieveTest_whenGetAllUser() {
		RoleRepository mockRoleRepository = mock(RoleRepository.class);
		UserRepository mockUserRepository = mock(UserRepository.class);
		when(mockUserRepository.findAll()).thenReturn(Arrays.asList(User.builder().build()));
		RetrieveService retrieveService = new RetrieveService(mockUserRepository, mockRoleRepository);
		List<User> userInfos = (List<User>) retrieveService.getAllUserInfo();
		assertThat(userInfos.size()).isEqualTo(1);
	}
	
	@Test
	public void retrieveTest_whenGetNormalUserInfo() {
		Role roleAdmin = Role.builder()
				.id(2)
				.roleName("ADMIN_USER")
				.description("Admin User - Has permission to perform admin tasks").build();
		Role roleStandard = Role.builder()
				.id(1)
				.roleName("STANDARD_USER")
				.description("Standard User - Has no admin rights").build();
		User user1 = User.builder()
				.firstName(normalUserTest.getFirstName())
				.lastName(normalUserTest.getLastName())
				.address(normalUserTest.getAddress())
				.phone("0995970594")
				.username(normalUserTest.getUsername())
				.password(normalUserTest.getPassword())
				.salary(15000)
				.referenceCode("201809160594")
				.classification("Silver")
				.roles(Arrays.asList(roleStandard)).build();
		User user2 = User.builder()
				.firstName(normalUserTest.getFirstName())
				.lastName(normalUserTest.getLastName())
				.address(normalUserTest.getAddress())
				.phone("0995970594")
				.username(normalUserTest.getUsername())
				.password(normalUserTest.getPassword())
				.salary(15000)
				.referenceCode("201809160594")
				.classification("Silver")
				.roles(Arrays.asList(roleAdmin)).build();
		RoleRepository mockRoleRepository = mock(RoleRepository.class);
		UserRepository mockUserRepository = mock(UserRepository.class);
		when(mockUserRepository.findAll()).thenReturn(Arrays.asList(user1,user2));
		when(mockRoleRepository.findByRoleName(anyString())).thenReturn(roleAdmin);
		RetrieveService retrieveService = new RetrieveService(mockUserRepository, mockRoleRepository);
		List<NormalViewModel> userInfos = (List<NormalViewModel>) retrieveService.getAllNormalUserInfo();
		assertThat(userInfos.size()).isEqualTo(1);
	}
}
