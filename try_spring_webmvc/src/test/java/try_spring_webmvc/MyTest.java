package try_spring_webmvc;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyTest {

	@Test
	void test() {
		assertEquals( 1, 1 );
	}

	@Test
	void test_assertj() {
		final String target = "hoge";

		// 参考URL: https://qiita.com/opengl-8080/items/b07307ab0d33422be9c5
		assertThat( target ).as( "文字列比較" )
			.isEqualTo( "hoge" ).hasSize( 4 ).contains( "o", "e" ).startsWith( "h" );
	}

}
