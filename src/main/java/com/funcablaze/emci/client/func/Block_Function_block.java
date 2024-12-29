package com.funcablaze.emci.client.func;

import com.funcablaze.emci.client.gui.Screen_FunctionBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Block_Function_block extends Block implements BlockEntityProvider {

    public Block_Function_block(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntity_Function_block(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            // 检查玩家权限
            if (player.hasPermissionLevel(2)) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity instanceof BlockEntity_Function_block FunctionBlock_Entity) {
                    MinecraftClient.getInstance().setScreen(new Screen_FunctionBlock(player, FunctionBlock_Entity));
                    return ActionResult.SUCCESS;
                }
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (world.isClient) {
            // 返回原始状态，不破坏方块
            if (placer != null && placer.hasPermissionLevel(2)) {
                super.onPlaced(world, pos, state, placer, itemStack);
            }
        }
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (world.isClient) {
            if (player.hasPermissionLevel(2)) {
                return super.onBreak(world, pos, state, player);
            } else {
                return state; // 返回原始状态，不破坏方块
            }
        } else {
            return super.onBreak(world, pos, state, player);
        }
    }
}