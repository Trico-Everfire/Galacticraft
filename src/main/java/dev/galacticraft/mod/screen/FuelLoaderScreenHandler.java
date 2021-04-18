/*
 * Copyright (c) 2019-2021 HRZN LTD
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.galacticraft.mod.screen;

import dev.galacticraft.mod.block.entity.FuelLoaderBlockEntity;
import dev.galacticraft.mod.screen.property.BlockPosPropertyDelegate;
import dev.galacticraft.mod.screen.slot.ChargeSlot;
import dev.galacticraft.mod.util.FluidUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.slot.Slot;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class FuelLoaderScreenHandler extends MachineScreenHandler<FuelLoaderBlockEntity> {
    public FuelLoaderScreenHandler(int syncId, PlayerEntity player, FuelLoaderBlockEntity machine) {
        super(syncId, player, machine, GalacticraftScreenHandlerTypes.FUEL_LOADER_HANDLER);

        this.addSlot(new ChargeSlot(machine.getWrappedInventory(), 0, 8, 53));
        this.addSlot(new Slot(machine.getWrappedInventory(), 1, 80, 53) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return FluidUtils.isExtractable(stack);
            }
        });

        this.addProperties(new BlockPosPropertyDelegate(machine::getConnectionPos, machine::setConnectionPos));

        this.addPlayerInventorySlots(0, 84);
    }

    public FuelLoaderScreenHandler(int syncId, PlayerInventory inv, PacketByteBuf buf) {
        this(syncId, inv.player, (FuelLoaderBlockEntity) inv.player.world.getBlockEntity(buf.readBlockPos()));
    }
}