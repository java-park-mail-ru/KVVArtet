package states;

import statemachine.PendingStack;
import packets.Packet;

import java.lang.*;

public class DungeonState extends AbstractState {
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public DungeonState(PendingStack stack) {
        super(stack);
    }

    @Override
    public boolean update() {
        logger.info("DungeonState updated");
        return true;
    }

    @Override
    public boolean handlePacket(final Packet packet) {
        logger.info("DungeonState handles packet");
        return true;
    }
}
