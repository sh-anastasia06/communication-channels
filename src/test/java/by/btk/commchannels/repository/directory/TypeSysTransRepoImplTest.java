package by.btk.commchannels.repository.directory;

import by.btk.commchannels.DTO.directory.STypeSysTransDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TypeSysTransRepoImplTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:18");

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private TypeSysTransRepoImpl repo;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void setUp() {
        repo = new TypeSysTransRepoImpl(jdbcTemplate);

        jdbcTemplate.getJdbcTemplate().execute(
                "CREATE TABLE IF NOT EXISTS public.s_type_sys_trans (" +
                "id_type_st SERIAL PRIMARY KEY, " +
                "abbrev_st VARCHAR(3) NOT NULL UNIQUE, " +
                "name VARCHAR(20) NOT NULL);"
        );

        jdbcTemplate.getJdbcTemplate().execute("TRUNCATE TABLE public.s_type_sys_trans RESTART IDENTITY;");
    }

    @Test
    void findAll_WithFilter_ShouldReturnFilteredResults() {
        // arrange
        repo.insert(new STypeSysTransDTO("E", "Ethernet"));
        repo.insert(new STypeSysTransDTO("C", "CWDM"));

        // act
        List<STypeSysTransDTO> result = repo.findAll("ethernet");

        // assert
        assertEquals(1, result.size());
        assertEquals("E", result.get(0).abbrevSt());
    }

    @Test
    void update_ShouldChangeExistingRecord() {
        // arrange
        repo.insert(new STypeSysTransDTO("E", "Ethernet"));
        Integer generatedId = repo.findAll(null).get(0).idTypeSt();

        // act
        STypeSysTransDTO updatedDto = new STypeSysTransDTO(generatedId, "C", "CWDM");
        repo.update(updatedDto);

        // assert
        List<STypeSysTransDTO> updated = repo.findAll(null);
        assertEquals(1, updated.size());
        assertEquals("C", updated.get(0).abbrevSt());
    }
}
