package com.kh;

import com.kh.service.MajorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class SpringFilterAopApplicationTests {

	@Autowired
	private MajorService service;

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
}
