package com.oitsjustjose.headdons.common.events;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class DaveBlockBreakEvent {
    @SubscribeEvent
    public void registerEvent(BlockEvent.BreakEvent event) {
        var block = event.getState().getBlock();
        var stack = event.getPlayer().getMainHandItem();

        if (event.getPlayer().isCreative()) return;
        if (!Objects.requireNonNull(block.getRegistryName()).getNamespace().equals("davebuildingmod")) return;
        if (!(event.getWorld() instanceof ServerLevel serverLevel)) return;

        // Only force the drop if the block drop is broken in the first place
        if (!block.canHarvestBlock(event.getState(), serverLevel, event.getPos(), event.getPlayer())) {
            // Make sure the tool is at least the *right* one
            if (!stack.isCorrectToolForDrops(event.getState())) return;

            var builder = new LootContext.Builder(serverLevel)
                    .withParameter(LootContextParams.ORIGIN, new Vec3(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ()))
                    .withParameter(LootContextParams.TOOL, stack);
            block.getDrops(event.getState(), builder).forEach(drop -> {
                var dropped = new ItemEntity(serverLevel, (double) event.getPos().getX() + 0.5D, event.getPos().getY(), (double) event.getPos().getZ() + 0.5D, drop);
                dropped.setPickUpDelay(10);
                event.getWorld().addFreshEntity(dropped);
            });
        }
    }
}
