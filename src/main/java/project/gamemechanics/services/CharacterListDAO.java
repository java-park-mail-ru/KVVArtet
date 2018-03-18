package project.gamemechanics.services;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public interface CharacterListDAO {

    @Nullable
    List<Integer> getCharacters(@NotNull Integer id);

    void setCharacterInCharacterList(@NotNull Integer id, @NotNull Integer characterId);

    void createDefaultEmptyCharacterList(@NotNull Integer id);

}
