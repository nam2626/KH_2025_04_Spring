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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class SpringFilterAopApplicationTests {

	@Autowired
	private MajorService service;

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


}
