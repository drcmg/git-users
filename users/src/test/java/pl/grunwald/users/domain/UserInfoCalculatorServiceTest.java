package pl.grunwald.users.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.grunwald.users.dto.UserInfoResult;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class UserInfoCalculatorServiceTest {


    private final UserInfoCalculatorService calculatorService = new UserInfoCalculatorService();

    @MethodSource("testData")
    @ParameterizedTest(name = "For followers = \"{0}\", publicRepos = \"{1}\" expect = \"{2}\"")
    void test(Integer followers, Integer publicRepos, BigDecimal expected) {

        final var userInfo = UserInfoResult.builder()
                .followers(followers)
                .publicRepos(publicRepos)
                .build();

        final var result = calculatorService.calculate(userInfo);

        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> testData() {
        return Stream.of(
                caseWith(null, null, null),
                caseWith(0, 0, null),
                caseWith(0, 1, null),
                caseWith(1, null, null),
                caseWith(1, 0, "12"),
                caseWith(1, 1, "18"),
                caseWith(231, 33, "0.9090909100")
        );
    }

    private static Arguments caseWith(Integer followers, Integer publicRepos, String expect ) {

        final var expectedValue = Optional.ofNullable(expect)
                .map(e -> new BigDecimal(e).setScale(10, RoundingMode.HALF_UP))
                .orElse(null);

        return arguments(followers, publicRepos, expectedValue);
    }

}