package by.btk.commchannels.DTO.directory;

public record STypeSysTransDTO(Integer idTypeSt, String abbrevSt, String name) {
    // для insert
    public STypeSysTransDTO(String abbrevSt, String name) {
        this(null, abbrevSt, name);
    }
}
