package states;

import packets.Packet;

@SuppressWarnings({"UnusedReturnValue", "SameReturnValue", "unused"})
public interface State {
    enum StateId { SI_NONE, SI_TITLE, SI_SIGNUP, SI_CHARACTER_LIST, SI_CITY, SI_DUNGEON, SI_CHARACTER_CREATION }

    void requestStackPush(StateId stateId);

    void requestStackPop();

    void requestStackClear();

    boolean update();

    boolean handlePacket(Packet packet);

}
