package gamemechanics.resources.assets;

import com.fasterxml.jackson.databind.ObjectMapper;
import gamemechanics.aliveentities.npcs.NonPlayerCharacterRole;
import gamemechanics.globals.MappingIndices;
import gamemechanics.interfaces.Ability;
import gamemechanics.interfaces.CharacterRole;
import gamemechanics.resources.holders.GameResourceHolder;
import gamemechanics.resources.holders.ResourceHolder;
import gamemechanics.resources.models.GameResource;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NpcRoleAssetHolder extends AbstractAssetHolder<CharacterRole> implements AssetHolder.NpcRoleHolder {
    public NpcRoleAssetHolder(@NotNull String fileName, @NotNull Map<Integer, Ability> abilities) {
        super();
        fillFromFile(fileName, abilities);
    }

    private void fillFromFile(@NotNull String fileName, @NotNull Map<Integer, Ability> abilities) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ResourceHolder holder = mapper.readValue(new File(fileName), GameResourceHolder.class);
            Map<Integer, GameResource> npcRoleResources = holder.getAllResources();
            for (Integer roleId : npcRoleResources.keySet()) {
                GameResource npcRoleResource = npcRoleResources.get(roleId);

                List<Integer> resourceBehaviorIds =
                        npcRoleResource.getMapping(MappingIndices.NPC_ROLE_BEHAVIOR_MAPPING);

                List<Integer> resourceAbilityIds =
                        npcRoleResource.getMapping(MappingIndices.NPC_ROLE_ABILITY_MAPPING);
                Map<Integer, Ability> roleAbilities = new HashMap<>();
                for (Integer abilityId : resourceAbilityIds) {
                    Ability ability = abilities.getOrDefault(abilityId, null);
                    if (ability != null) {
                        roleAbilities.put(ability.getID(), ability);
                    }
                }

                NonPlayerCharacterRole.NPCRoleModel model = new NonPlayerCharacterRole.NPCRoleModel(roleId,
                        npcRoleResource.getName(), npcRoleResource.getDescription(), npcRoleResource.getAllAffectors(),
                        resourceBehaviorIds, roleAbilities);
                assets.put(model.id, new NonPlayerCharacterRole(model));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}