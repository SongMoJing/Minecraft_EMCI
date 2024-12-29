package com.funcablaze.emci.client.gui;

import com.funcablaze.emci.Emci;
import com.funcablaze.emci.client.func.BlockEntity_Function_block;
import net.minecraft.block.CommandBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.EditBoxWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

public class Screen_FunctionBlock extends Screen {

    PlayerEntity player;
    BlockEntity_Function_block BLOCK;
    BlockEntity_Function_block.File file;

    ButtonWidget btn_confirm;
    ButtonWidget btn_cancel;
    EditBoxWidget input;

    public Screen_FunctionBlock(PlayerEntity player, BlockEntity_Function_block BLOCK) {
        super(Text.literal("编辑函数方块 Edit the function block"));
        this.player = player;
        this.BLOCK = BLOCK;
        this.file = BLOCK.Text;
        init();
    }

    @Override
    protected void init() {
        super.init();
        clearChildren();
        btn_confirm = ButtonWidget.builder(Text.literal("更改"), (button) -> {
                    // 保存NBT标签
//                    NbtCompound nbt = new NbtCompound();
//                    nbt.putString("text", input.getText());
//                    BLOCK.readNbt(nbt);
                    // 保存文件
                    file.setText(input.getText());
                    player.sendMessage(Text.literal("成功保存函数方块"), false);
                    this.close();
        })
                .dimensions(5, height - 50, 70, 18)
                .tooltip(Tooltip.of(Text.literal("更改")))
                .build();
        btn_cancel = ButtonWidget.builder(Text.literal("取消"), (button) -> this.close())
                .dimensions(5, height - 25, 70, 18)
                .tooltip(Tooltip.of(Text.literal("取消")))
                .build();
        input = new EditBoxWidget(MinecraftClient.getInstance().textRenderer, 80, 10, width - 90, height - 18, Text.literal("编辑此函数方块的内容"), Text.literal("编辑此函数方块的内容"));
        input.setText(file.getText());
        input.setChangeListener(this::onChangeListener);
        addDrawableChild(btn_confirm);
        addDrawableChild(btn_cancel);
        addDrawableChild(input);
    }

    private void onChangeListener(String s) {
        Emci.LOGGER.info("change:" + s);
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, "编辑此函数方块", 10, 10, 0xFFFFFF);
    }
}
