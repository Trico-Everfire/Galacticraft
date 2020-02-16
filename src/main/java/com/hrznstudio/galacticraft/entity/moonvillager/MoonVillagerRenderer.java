package com.hrznstudio.galacticraft.entity.moonvillager;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class MoonVillagerRenderer extends MobEntityRenderer<MoonVillagerEntity, MoonVillagerModel<MoonVillagerEntity>> {

    public MoonVillagerRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
        super(entityRenderDispatcher_1, new MoonVillagerModel<>(1, 64, 64), 1);
    }

    @Override
    protected Identifier getTexture(MoonVillagerEntity moonVillagerEntity) {
        return new Identifier("galacticraft-rewoven:textures/entity/moon_villager/moon_villager.png");
    }
}
