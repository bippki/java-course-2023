package edu.hw10.Test10_1.dto;

import lombok.Getter;

import java.util.UUID;

public class Player {
    @Getter
    protected UUID uuid;
    @Getter
    protected String nickname;
    @Getter
    protected int blocksMined;
    public Player(UUID uuid, String nickname, int blocksMined) {
        super();
        this.uuid = uuid;
        this.nickname = nickname;
        this.blocksMined = blocksMined;
    }

    @Override
    public String toString() {
        return "Player [UUID: " + uuid + ", NICKNAME: " + nickname + ", BLOCKS_MINED: " + blocksMined + "]";
    }
}
