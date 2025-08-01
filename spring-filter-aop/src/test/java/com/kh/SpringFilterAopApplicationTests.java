package com.kh;

import com.kh.dto.MajorDTO;
import com.kh.service.MajorService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class SpringFilterAopApplicationTests {

	@Autowired
	private MajorService service;

	//전체 테스트 수행 전에 실행할 메서드 - 반드시 static 처리
	@BeforeAll
	public static void initAll(){
		System.out.println("전체 테스트 수행 전 맨처음 한번만 실행되는 코드");
	}

	//모든 메서드 실행 후 한번만 실행 - 반드시 static 처리
	@AfterAll
	public static void endTestAll() {
		System.out.println("테스트 종료 후 코드");
	}

	//각 테스트 메서드 실행 전에 실행하는 메서드
	@BeforeEach
	public void testBefore(TestInfo info) {
		System.out.println("테스트 메서드 수행전에 수행하는 메서드");
		System.out.println(info.getTestMethod().get().getName() + " - "
						+ info.getDisplayName());
	}

	//각 테스트 메서드 실행 후에 실행하는 메서드
	@AfterEach
	public void testAfter() {
		System.out.println("테스트 메서드 수행후에 수행하는 메서드");
	}

	@Order(2)
	@DisplayName("학과 정보 등록 테스트")
	@Test
	public void insertTest() {
		System.out.println("학과 정보 등록 테스트 시작");
		Map<String,Object> map = new HashMap<>();
		map.put("mno", "S03");
		map.put("mname", "테스트 학과");
		int result = 0;
		try {
			result = service.insertMajor(map);
		}catch (Exception e){
			//테스트 실패시 fail 호출하면 해당 테스트가 강제 종료
			fail("중복된 학과 추가시 SQL 오류");
		}

		assertEquals(1,result,"학과 등록 테스트 실패");
		
		System.out.println("학과 정보 등록 테스트 종료");
	}

	//삭제 테스트 메서드
	@Order(1)
	@DisplayName("학과 정보 삭제 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"S01","S02","S03"})
	public void deleteTest(String value){
		assertEquals(1, service.deleteMajor(value),value + " 삭제 테스트 실패");
	}

	@Order(3)
	@DisplayName("Null/ Not Null 테스트")
	@Test
	public void selectTest(TestInfo info){
		System.out.println(info.getTestMethod().get().getName() + " - " + info.getDisplayName());
//		assertNotNull(new MajorDTO("A01","TEST"),"Not Null 테스트 실패");
//		assertNotNull(null,"Not Null 테스트 실패");
		assertNull(new MajorDTO("A01","TEST"),"Null 테스트 실패");

	}

	@DisplayName("배열 비교 테스트")
	@Order(4)
	@Test
	public void arrayTest() {
		assertArrayEquals(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3});
	}

	@DisplayName("결과값 True/False 테스트 확인")
	@Order(5)
	@Test
	public void assertTrueFalseTest() {
//		assertTrue(3 < 2);
		assertFalse(3 < 2);

	}

	@DisplayName("여러개의 테스트 확인")
	@Order(6)
	@Test
	public void assertAllTest() {
		assertAll(
						() -> assertEquals(4, 2 + 2),
						() -> assertTrue(3 > 3)
		);
	}
}
