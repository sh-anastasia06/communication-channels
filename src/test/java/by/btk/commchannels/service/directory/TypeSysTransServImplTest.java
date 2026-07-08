package by.btk.commchannels.service.directory;

import by.btk.commchannels.DTO.directory.STypeSysTransDTO;
import by.btk.commchannels.repository.directory.TypeSysTransRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TypeSysTransServImplTest {
    @Mock
    private TypeSysTransRepo typeSysTransRepo;

    @InjectMocks
    private TypeSysTransServImpl typeSysTransServ;

    @Test
    void findAll_ShouldReturnList() {
        // arrange
        String filter = "test";
        List<STypeSysTransDTO> expected = List.of(new STypeSysTransDTO(1, "T", "TestName"));
        when(typeSysTransRepo.findAll(filter)).thenReturn(expected);

        // act
        List<STypeSysTransDTO> result = typeSysTransServ.findAll(filter);

        // assert
        assertEquals(1, result.size());
        assertEquals("T", result.get(0).abbrevSt());

        verify(typeSysTransRepo, times(1)).findAll(filter);
    }

    @Test
    void insert_NullOrBlankFields_ShouldThrowException() {
        // arrange
        STypeSysTransDTO wrongDTO = new STypeSysTransDTO("", null);

        // act & assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> typeSysTransServ.insert(wrongDTO)
        );
        assertEquals("Все поля являются обязательными для заполнения", exception.getMessage());

        verify(typeSysTransRepo, times(0)).insert(any(STypeSysTransDTO.class));
    }

    @Test
    void updateNullId_ShouldThrowException() {
        // arrange
        STypeSysTransDTO wrongDTO = new STypeSysTransDTO(null, "T", "TestName");

        // act & assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> typeSysTransServ.update(wrongDTO)
        );
        assertEquals("ID записи не может быть пустым", exception.getMessage());

        verify(typeSysTransRepo, times(0)).update(any(STypeSysTransDTO.class));
    }
}
