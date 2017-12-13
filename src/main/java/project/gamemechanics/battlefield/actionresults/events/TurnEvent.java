package project.gamemechanics.battlefield.actionresults.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import project.gamemechanics.interfaces.MapNode;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(ApplyEffectEvent.class),
        @JsonSubTypes.Type(CastEvent.class),
        @JsonSubTypes.Type(EndTurnEvent.class),
        @JsonSubTypes.Type(HitpointsChangeEvent.class),
        @JsonSubTypes.Type(MoveEvent.class),
        @JsonSubTypes.Type(RewardEvent.class),
        @JsonSubTypes.Type(RollbackEvent.class),
})
public interface TurnEvent {
    Integer getEventKind();

    default MapNode getWhere() {
        return null;
    }

    default Integer getAmount() {
        return 0;
    }
}
