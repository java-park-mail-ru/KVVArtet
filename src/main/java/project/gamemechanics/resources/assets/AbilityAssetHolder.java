package project.gamemechanics.resources.assets;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import project.gamemechanics.effects.IngameEffect;
import project.gamemechanics.flyweights.abilities.AbilityBehaviors;
import project.gamemechanics.flyweights.abilities.IngameAbility;
import project.gamemechanics.globals.MappingIndices;
import project.gamemechanics.interfaces.Ability;
import project.gamemechanics.resources.holders.GameResourceHolder;
import project.gamemechanics.resources.holders.ResourceHolder;
import project.gamemechanics.resources.models.GameResource;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbilityAssetHolder extends AbstractAssetHolder<Ability> implements AssetHolder.AbilityHolder {
    public AbilityAssetHolder(@NotNull String fileName) {
        super();
        fillFromFile(fileName);
    }

    private void fillFromFile(@NotNull String fileName) {
        final ObjectMapper mapper = new ObjectMapper();
        //noinspection TryWithIdenticalCatches
        try {
            final ResourceHolder holder = mapper.readValue(new File(fileName), GameResourceHolder.class);
            final Map<Integer, GameResource> abilityResources = holder.getAllResources();

            for (Integer abilityID : abilityResources.keySet()) {
                final GameResource abilityResource = abilityResources.get(abilityID);

                final List<GameResource> effectResources = abilityResource.getAllInlaid();
                final List<IngameEffect.EffectModel> effectModels = new ArrayList<>();
                for (GameResource effectResource : effectResources) {
                    effectModels.add(new IngameEffect.EffectModel(effectResource.getName(),
                            effectResource.getDescription(), effectResource.getID(),
                            effectResource.getAllAffectors()));
                }

                final IngameAbility.AbilityModel abilityModel = new IngameAbility.AbilityModel(abilityID,
                        abilityResource.getName(), abilityResource.getDescription(),
                        abilityResource.getAllProperties(), abilityResource.getAllAffectors(),
                        effectModels, AbilityBehaviors.getBehavior(
                        abilityResource.getMapping(MappingIndices.ABILITY_BEHAVIOR_MAPPING).get(0)));
                assets.put(abilityModel.id, new IngameAbility(abilityModel));
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
