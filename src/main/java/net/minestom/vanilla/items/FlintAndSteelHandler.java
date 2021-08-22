package net.minestom.vanilla.items;

import net.minestom.server.coordinate.Point;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.event.player.PlayerUseItemOnBlockEvent;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.minestom.vanilla.inventory.InventoryManipulation;

public class FlintAndSteelHandler implements VanillaItemHandler {
    public FlintAndSteelHandler() {}

    @Override
    public boolean onUseOnBlock(PlayerUseItemOnBlockEvent event) {
        // TODO: check if flammable
        Point pos = event.getPosition();
        Player player = event.getPlayer();
        Instance instance = player.getInstance();
        ItemStack itemStack = event.getItemStack();
        Player.Hand hand = event.getHand();

        Block atFirePosition = instance.getBlock(pos);

        if (atFirePosition.isAir()) {
            InventoryManipulation.damageItemIfNotCreative(player, itemStack, hand);
            instance.setBlock(pos, Block.FIRE);
            return true;
        }

        return false;
    }
}
