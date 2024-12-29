package com.funcablaze.emci.client.func;

import com.funcablaze.emci.Emci;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BlockEntity_Function_block extends BlockEntity {

    public File Text;
    public int a = 0;

    public BlockEntity_Function_block(BlockPos pos, BlockState state) {
        super(Emci.BLOCK_ENTITY, pos, state);
        Text = new File();
        Text.setText("""
                use mc.client.Player;

                @fn activate() {
                    Player.println(Player.get().getName);
                }""");
    }

    // 序列化方块实体
    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putString("text", Text.getText());
    }

    // 反序列化方块实体
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Text.setText(nbt.getString("text"));
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public static class File {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String txt) {
            text = txt;
        }
    }
}
