package com.oitsjustjose.headdons.common.Item;

import com.oitsjustjose.headdons.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.StonecutterMenu;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class PortableStoneCutterItem extends Item {
    private static final Component CONTAINER_TITLE = new TranslatableComponent("container." + Constants.MODID + ".portable_stonecutter");

    public PortableStoneCutterItem() {
        super(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_DECORATIONS));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (!level.isClientSide()) {
            player.openMenu(getMenuProvider(level, player.getOnPos()));
            player.awardStat(Stats.INTERACT_WITH_STONECUTTER);
            player.swing(hand);
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @Nullable
    public MenuProvider getMenuProvider(Level level, BlockPos pos) {
        return new SimpleMenuProvider((p_57074_, inv, player) -> {
            return new PortableStonecutterMenu(p_57074_, inv, ContainerLevelAccess.create(level, pos));
        }, CONTAINER_TITLE);
    }

    private static class PortableStonecutterMenu extends StonecutterMenu {
        public PortableStonecutterMenu(int unk, Inventory inv, ContainerLevelAccess acc) {
            super(unk, inv, acc);
        }

        @Override
        public boolean stillValid(@NotNull Player ignored) {
            return true;
        }
    }
}
